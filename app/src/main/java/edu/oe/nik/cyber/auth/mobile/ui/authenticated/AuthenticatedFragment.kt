package edu.oe.nik.cyber.auth.mobile.ui.authenticated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import edu.oe.nik.cyber.auth.mobile.R
import edu.oe.nik.cyber.auth.mobile.databinding.AuthenticatedFragmentBinding
import edu.oe.nik.cyber.auth.mobile.storage.PreferredAuthenticationType
import edu.oe.nik.cyber.auth.mobile.ui.base.BaseFragment
import kotlinx.android.synthetic.main.authenticated_fragment.*
import javax.inject.Inject


class AuthenticatedFragment @Inject constructor() : BaseFragment() {

    private val viewModel: AuthenticatedViewModel by viewModels()

    private lateinit var adapter: ArrayAdapter<PreferredAuthenticationType>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: AuthenticatedFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.authenticated_fragment,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        observeModel()

        return binding.root
    }

    private fun observeModel() {
        viewModel.managementRepository.changePreferredAuthTypeResult.observe(
            viewLifecycleOwner, Observer { restartApp() }
        )

        viewModel.managementRepository.deleteUserResult.observe(
            viewLifecycleOwner, Observer { restartApp() }
        )

        viewModel.shouldRestart.observe(
            viewLifecycleOwner, Observer { restartApp() }
        )
    }

    override fun onResume() {
        super.onResume()
        viewModel.presetSelection()
        adapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_dropdown_item,
            PreferredAuthenticationType.values()
        )
        authenticated_spinner.adapter = adapter
        authenticated_spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
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

}