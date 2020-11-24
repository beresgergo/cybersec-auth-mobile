package edu.oe.nik.cyber.auth.mobile.ui.login.totp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import edu.oe.nik.cyber.auth.mobile.R
import edu.oe.nik.cyber.auth.mobile.databinding.TotpLoginFragmentBinding
import edu.oe.nik.cyber.auth.mobile.network.NetworkConstants
import edu.oe.nik.cyber.auth.mobile.storage.PreferredAuthenticationType
import edu.oe.nik.cyber.auth.mobile.ui.base.BaseFragment
import kotlinx.android.synthetic.main.totp_login_fragment.*
import javax.inject.Inject

class TotpLoginFragment @Inject constructor() : BaseFragment() {

    private val viewModel: TotpLoginViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: TotpLoginFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.totp_login_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        observeViewModel()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshTotpCode()
    }

    private fun observeViewModel() {
        viewModel.loginRepository.initiateLoginResult.observe(
            viewLifecycleOwner, Observer {
                when (it.message.isNullOrBlank()) {
                    true -> {
                        viewModel.storeSessionId(it.sessionId)
                        viewModel.submitTotpCode()
                    }
                    false -> showNetworkAlertDialog()
                }
            }
        )

        viewModel.loginRepository.submitTotpTokenResult.observe(
            viewLifecycleOwner, Observer {
                when (it.message.isNullOrBlank()) {
                    true -> {
                        if (viewModel.credentialStorage.preferredAuthType == PreferredAuthenticationType.TOTP) {
                            viewModel.getJwt()
                        }
                        else {
                            val action = TotpLoginFragmentDirections.actionTotpLoginFragmentToRsaLoginFragment()
                            findNavController().navigate(action)
                        }
                    }
                    false -> {
                        if (it.message == NetworkConstants.NETWORK_ERROR) {
                            showNetworkAlertDialog()
                        }
                        else {
                            login_totp_fragment_totp_token_invalid_warning.visibility = View.VISIBLE
                        }
                    }
                }
            }
        )

        viewModel.loginRepository.getJwtResult.observe(
            viewLifecycleOwner, Observer {
                when (it.message.isNullOrBlank()) {
                    true -> {
                        viewModel.storeJwt(it.token)
                        val action = TotpLoginFragmentDirections.actionTotpLoginFragmentToAuthenticatedFragment()
                        findNavController().navigate(action)
                    }
                    false -> showNetworkAlertDialog()
                }
            }
        )
    }
}