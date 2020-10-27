package edu.oe.nik.cyber.auth.mobile.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import edu.oe.nik.cyber.auth.mobile.CyberSecAuthApplication
import edu.oe.nik.cyber.auth.mobile.di.qualifiers.ApplicationContext

@Module
object AppModule {
    @Provides
    @ApplicationContext
    fun provideContext(cyberSecAuthApplication: CyberSecAuthApplication): Context = cyberSecAuthApplication.applicationContext
}