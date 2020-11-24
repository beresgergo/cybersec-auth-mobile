package edu.oe.nik.cyber.auth.mobile.ui.base

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import edu.oe.nik.cyber.auth.mobile.R
import edu.oe.nik.cyber.auth.mobile.ui.main.MainActivity
import java.util.concurrent.Executor
import javax.inject.Inject

abstract class BaseFragment : Fragment(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var biometricPromptInfo: BiometricPrompt.PromptInfo

    protected lateinit var biometricPrompt: BiometricPrompt

    protected lateinit var executor: Executor

    protected var navController: NavController? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun androidInjector(): AndroidInjector<Any>? = androidInjector

    fun goBack() = NavHostFragment.findNavController(this).popBackStack()

    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory = viewModelFactory

    fun showNetworkAlertDialog() {
        context?.let {
            val builder = AlertDialog.Builder(it)
            with(builder) {
                setTitle(R.string.dialog_network_error_title)
                setMessage(R.string.dialog_network_error_text)

                show()
            }
        }
    }

    fun setupPromptInfo() {
        biometricPromptInfo = with(BiometricPrompt.PromptInfo.Builder()) {
            setTitle(resources.getString(R.string.biometric_prompt_title))
            setSubtitle(resources.getString(R.string.biometric_prompt_subtitle))
            setNegativeButtonText(resources.getString(R.string.biometric_prompt_negative_text))
            build()
        }
    }

    fun restartApp() {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        activity?.finish()
        startActivity(intent)
    }
}