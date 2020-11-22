package edu.oe.nik.cyber.auth.mobile.ui.login.totp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import edu.oe.nik.cyber.auth.mobile.R
import edu.oe.nik.cyber.auth.mobile.databinding.TotpLoginFragmentBinding
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
        viewModel.initiateLoginResult.observe(
            viewLifecycleOwner, Observer { state ->
                when (state) {
                    InitiateLoginResult.OK -> viewModel.submitTotpCode()
                    InitiateLoginResult.NETWORK_FAILURE -> showNetworkAlertDialog()
                }
            }
        )

        viewModel.submitTotpTokenResult.observe(
            viewLifecycleOwner, Observer { state ->
                when (state) {
                    SubmitTotpTokenResult.OK -> showAuthSuccessDialog()
                    SubmitTotpTokenResult.INVALID_TOKEN -> login_totp_fragment_totp_token_invalid_warning.visibility = View.VISIBLE
                    SubmitTotpTokenResult.NEWORK_FAILURE -> showNetworkAlertDialog()
                }
            }
        )
    }
}