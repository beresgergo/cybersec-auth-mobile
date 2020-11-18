package edu.oe.nik.cyber.auth.mobile.ui.registration.rsa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import edu.oe.nik.cyber.auth.mobile.R
import edu.oe.nik.cyber.auth.mobile.databinding.GenerateRsaKeypairFragmentBinding
import edu.oe.nik.cyber.auth.mobile.ui.base.BaseFragment
import kotlinx.android.synthetic.main.generate_rsa_keypair_fragment.*
import java.util.concurrent.Executor
import javax.inject.Inject

class GenerateRsaKeypairFragment @Inject constructor() : BaseFragment() {

    private val viewModel: GenerateRsaKeypairViewModel by viewModels()

    private lateinit var executor: Executor

    private lateinit var biometricPromptInfo: BiometricPrompt.PromptInfo

    private lateinit var biometricPrompt: BiometricPrompt

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: GenerateRsaKeypairFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.generate_rsa_keypair_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        observeModel()
        setupPromptInfo()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setupEventHandler()
    }

    private fun setupEventHandler() {
        executor = ContextCompat.getMainExecutor(context)
        registration_generate_rsa_fragment_generate_button.setOnClickListener {
            biometricPrompt = BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    viewModel.generatePublicKey()
                }
            })
            biometricPrompt.authenticate(biometricPromptInfo)
        }
    }

    private fun setupPromptInfo() {
        biometricPromptInfo = with(BiometricPrompt.PromptInfo.Builder()) {
            setTitle("Please authenticate yourself")
            setSubtitle("Authenticate yourself with biometric credentials stored on your device.")
            setNegativeButtonText("How about nope?")
            build()
        }
    }


    private fun observeModel() {
        viewModel.publicKeyGenerated.observe(viewLifecycleOwner, Observer {
            registration_generate_rsa_fragment_next_button.isEnabled = true
        })
    }
}