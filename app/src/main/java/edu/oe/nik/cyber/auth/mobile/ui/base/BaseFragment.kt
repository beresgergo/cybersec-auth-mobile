package edu.oe.nik.cyber.auth.mobile.ui.base

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import edu.oe.nik.cyber.auth.mobile.R
import javax.inject.Inject

abstract class BaseFragment : Fragment(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected var navController: NavController? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun androidInjector(): AndroidInjector<Any>? = androidInjector

    fun goBack() = NavHostFragment.findNavController(this).popBackStack()

    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory = viewModelFactory

    fun showNetworkAlertDialog(): Unit {
        context?.let {
            val builder = AlertDialog.Builder(it)
            with(builder) {
                setTitle(R.string.dialog_network_error_title)
                setMessage(R.string.dialog_network_error_text)

                show()
            }
        }
    }
}