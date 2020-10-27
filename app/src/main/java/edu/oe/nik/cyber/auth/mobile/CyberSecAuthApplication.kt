package edu.oe.nik.cyber.auth.mobile

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import edu.oe.nik.cyber.auth.mobile.di.components.DaggerAppComponent

open class CyberSecAuthApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}