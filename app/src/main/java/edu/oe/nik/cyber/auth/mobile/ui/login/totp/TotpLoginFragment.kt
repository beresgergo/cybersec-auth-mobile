package edu.oe.nik.cyber.auth.mobile.ui.login.totp

import androidx.fragment.app.viewModels
import edu.oe.nik.cyber.auth.mobile.ui.base.BaseFragment
import javax.inject.Inject

class TotpLoginFragment @Inject constructor() : BaseFragment() {

    private val viewModel: TotpLoginViewModel by viewModels()
}