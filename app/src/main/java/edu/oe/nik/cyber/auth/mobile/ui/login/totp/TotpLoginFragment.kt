package edu.oe.nik.cyber.auth.mobile.ui.login.totp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import edu.oe.nik.cyber.auth.mobile.R
import edu.oe.nik.cyber.auth.mobile.databinding.TotpLoginFragmentBinding
import edu.oe.nik.cyber.auth.mobile.ui.base.BaseFragment
import javax.inject.Inject

class TotpLoginFragment @Inject constructor() : BaseFragment() {

    private val viewModel: TotpLoginViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: TotpLoginFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.totp_login_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }
}