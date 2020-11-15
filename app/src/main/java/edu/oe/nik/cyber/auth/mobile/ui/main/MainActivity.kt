package edu.oe.nik.cyber.auth.mobile.ui.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import edu.oe.nik.cyber.auth.mobile.BuildConfig
import edu.oe.nik.cyber.auth.mobile.R
import edu.oe.nik.cyber.auth.mobile.storage.CredentialStorage
import edu.oe.nik.cyber.auth.mobile.storage.PreferredAuthenticationType
import edu.oe.nik.cyber.auth.mobile.ui.base.BaseActivity
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private lateinit var navController: NavController
    private lateinit var navGraph: NavGraph

    @Inject
    lateinit var credentialStorage: CredentialStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        credentialStorage.clearStorage()
        credentialStorage.username = "usernam12"
        credentialStorage.jwt = "jwt"
        credentialStorage.totpSecret = ""
        credentialStorage.preferredAuthType = PreferredAuthenticationType.RSA

        setupNavGraph()
    }

    private fun setupNavGraph() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val graphInflater = navHostFragment.navController.navInflater

        navGraph = graphInflater.inflate(R.navigation.nav_graph)
        navController = navHostFragment.navController

        val destination = if (credentialStorage.hasStoredCredential()) R.id.totpLoginFragment
        else R.id.startRegistrationFragment

        navGraph.startDestination = destination
        navController.graph = navGraph
    }
}