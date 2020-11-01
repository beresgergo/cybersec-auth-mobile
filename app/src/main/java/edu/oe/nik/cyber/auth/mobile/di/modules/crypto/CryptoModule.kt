package edu.oe.nik.cyber.auth.mobile.di.modules.crypto

import dagger.Module
import dagger.Provides
import java.security.KeyStore
import javax.inject.Singleton


@Module
object CryptoModule {

    private const val ANDROID_KEY_STORE = "AndroidKeyStore"

    @Provides
    @Singleton
    fun provideAndroidKeystore(): KeyStore = KeyStore.getInstance(ANDROID_KEY_STORE).apply { load(null) }
}