package edu.oe.nik.cyber.auth.mobile.ui.registration.totp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import edu.oe.nik.cyber.auth.mobile.R
import edu.oe.nik.cyber.auth.mobile.databinding.SubmitTotpSecretFragmentBinding
import edu.oe.nik.cyber.auth.mobile.ui.base.BaseFragment
import edu.oe.nik.cyber.auth.mobile.ui.registration.start.InitiateRegistrationResult
import edu.oe.nik.cyber.auth.mobile.ui.registration.start.StartRegistrationFragmentDirections
import kotlinx.android.synthetic.main.start_registration_fragment.*
import timber.log.Timber
import javax.inject.Inject

class SubmitTotpSecretFragment @Inject constructor() : BaseFragment() {

    private val viewModel: SubmitTotpSecretViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: SubmitTotpSecretFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.submit_totp_secret_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        observeModel()

        return binding.root
    }

    private fun observeModel() {
        viewModel.submitTotpSecretResult.observe(
            viewLifecycleOwner,
            Observer<SubmitTotpSecretResult> { state ->

                when (state) {
                    SubmitTotpSecretResult.OK -> {
                        Timber.d("Navigate to the next screen.")
                    }
                    SubmitTotpSecretResult.NETWORK_FAILURE -> showNetworkAlertDialog()
                }
            })
    }
}