package edu.oe.nik.cyber.auth.mobile.di.components

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import edu.oe.nik.cyber.auth.mobile.CyberSecAuthApplication
import edu.oe.nik.cyber.auth.mobile.di.modules.AppModule
import edu.oe.nik.cyber.auth.mobile.di.modules.ui.ActivityModule
import edu.oe.nik.cyber.auth.mobile.di.modules.ui.ViewModelModule
import edu.oe.nik.cyber.auth.mobile.di.modules.ui.mainactivity.MainActivityModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityModule::class,
    ViewModelModule::class
])
interface AppComponent : AndroidInjector<CyberSecAuthApplication> {
    @Component.Builder
    abstract class Factory : AndroidInjector.Builder<CyberSecAuthApplication>()
}