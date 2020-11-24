package edu.oe.nik.cyber.auth.mobile.ui.login.rsa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import edu.oe.nik.cyber.auth.mobile.R
import edu.oe.nik.cyber.auth.mobile.databinding.RsaLoginFragmentBinding
import edu.oe.nik.cyber.auth.mobile.storage.PreferredAuthenticationType
import edu.oe.nik.cyber.auth.mobile.ui.base.BaseFragment
import kotlinx.android.synthetic.main.rsa_login_fragment.*
import javax.inject.Inject

class RsaLoginFragment @Inject constructor() : BaseFragment() {

    private val viewModel: RsaLoginViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: RsaLoginFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.rsa_login_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        observeViewModel()
        setupPromptInfo()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        when (viewModel.credentialStorage.preferredAuthType) {
            PreferredAuthenticationType.RSA -> viewModel.initiateLogin()
            else -> viewModel.retrieveChallenge()
        }

        viewModel.initSign()
        setupEventHandlers()
    }

    private fun setupEventHandlers() {
        executor = ContextCompat.getMainExecutor(context)
        login_rsa_next_button.setOnClickListener {
            biometricPrompt = BiometricPrompt(this, executor, object:  BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    result.cryptoObject?.signature?.let {
                        viewModel.signChallenge(it)
                    }
                }
            })
            biometricPrompt.authenticate(biometricPromptInfo, BiometricPrompt.CryptoObject(viewModel.signature))
        }
    }

    private fun observeViewModel() {
        viewModel.loginRepository.initiateLoginResult.observe(
            viewLifecycleOwner, Observer {
                when (it.message.isNullOrBlank()) {
                    true -> {
                        viewModel.storeSessionId(it.sessionId)
                        viewModel.retrieveChallenge()
                    }
                    false -> showNetworkAlertDialog()
                }
            }
        )

        viewModel.loginRepository.getLoginChallengeResult.observe(
            viewLifecycleOwner, Observer {
                when (it.message.isNullOrBlank()) {
                    true -> viewModel.updateChallenge(it.challenge)
                    false -> showNetworkAlertDialog()
                }
            }
        )

        viewModel.loginRepository.submitSignedChallengeResult.observe(
            viewLifecycleOwner, Observer {
                when (it.message.isNullOrBlank()) {
                    true -> viewModel.getJWT()
                    false -> login_rsa_fragment_rsa_signature_invalid_warning.visibility = View.VISIBLE
                }
            }
        )

        viewModel.loginRepository.getJwtResult.observe(
            viewLifecycleOwner, Observer {
                when (it.message.isNullOrBlank()) {
                    true -> {
                        viewModel.storeJwt(it.token)
                        val action = RsaLoginFragmentDirections.actionRsaLoginFragmentToAuthenticatedFragment()
                        findNavController().navigate(action)
                    }
                    else -> showNetworkAlertDialog()
                }
            }
        )
    }
}