package edu.oe.nik.cyber.auth.mobile.ui.registration.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.start_registration_fragment.*
import androidx.navigation.fragment.findNavController
import edu.oe.nik.cyber.auth.mobile.R
import edu.oe.nik.cyber.auth.mobile.databinding.StartRegistrationFragmentBinding
import edu.oe.nik.cyber.auth.mobile.ui.base.BaseFragment
import javax.inject.Inject

class StartRegistrationFragment @Inject constructor() : BaseFragment() {

    private val viewModel: StartRegistrationViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: StartRegistrationFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.start_registration_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        observeViewModel()

        return binding.root
    }

    private fun observeViewModel() {
        viewModel.registrationResult.observe(
            viewLifecycleOwner,
            Observer { state ->

                when (state) {
                    InitiateRegistrationResult.OK -> {
                        val action = StartRegistrationFragmentDirections.actionStartRegistrationFragmentToSubmitTotpSecretFragment()
                        findNavController().navigate(action)
                    }
                    InitiateRegistrationResult.USERNAME_ALREADY_REGISTERED -> {
                        start_registration_fragment_user_exist_warning.visibility = View.VISIBLE
                    }
                    InitiateRegistrationResult.NETWORK_FAILURE -> showNetworkAlertDialog()
                }
            })
    }

}