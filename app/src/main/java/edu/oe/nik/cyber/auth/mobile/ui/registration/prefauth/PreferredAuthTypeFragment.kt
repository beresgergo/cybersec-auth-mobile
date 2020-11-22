package edu.oe.nik.cyber.auth.mobile.ui.registration.prefauth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import edu.oe.nik.cyber.auth.mobile.R
import edu.oe.nik.cyber.auth.mobile.databinding.RegistrationPreferredAuthenticationTypeBinding
import edu.oe.nik.cyber.auth.mobile.storage.PreferredAuthenticationType
import edu.oe.nik.cyber.auth.mobile.ui.base.BaseFragment
import kotlinx.android.synthetic.main.registration_preferred_authentication_type.*
import javax.inject.Inject

class PreferredAuthTypeFragment @Inject constructor() : BaseFragment() {

    private val viewModel: PreferredAuthTypeViewModel by viewModels()

    private lateinit var adapter: ArrayAdapter<PreferredAuthenticationType>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: RegistrationPreferredAuthenticationTypeBinding = DataBindingUtil.inflate(inflater, R.layout.registration_preferred_authentication_type, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        observeModel()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, PreferredAuthenticationType.values())
        registration_prefauth_spinner.adapter = adapter
        registration_prefauth_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                parent?.let {
                    viewModel.selection = (it.selectedItem as PreferredAuthenticationType)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun observeModel() {
        viewModel.finalizeRegistrationResult.observe(
            viewLifecycleOwner,
            Observer { state ->
                when (state) {
                    FinalizeRegistrationResult.OK -> {
                       val action = when (viewModel.credentialStorage.preferredAuthType) {
                            PreferredAuthenticationType.RSA -> PreferredAuthTypeFragmentDirections.actionPreferredAuthTypeFragmentToRsaLoginFragment()
                            else -> PreferredAuthTypeFragmentDirections.actionPreferredAuthTypeFragmentToTotpLoginFragment()
                        }
                        findNavController().navigate(action)
                    }
                    FinalizeRegistrationResult.NETWORK_FAILURE -> showNetworkAlertDialog()
                }
            }
        )
    }
}