package edu.oe.nik.cyber.auth.mobile.di.modules.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.Module
import dagger.Provides
import edu.oe.nik.cyber.auth.mobile.di.qualifiers.ApplicationContext
import edu.oe.nik.cyber.auth.mobile.storage.CredentialStorage
import javax.inject.Named
import javax.inject.Singleton

@Module
object StorageModule {

    private const val SHARED_PREF = "shared_pref"
    private const val SHARED_PREF_ENCRYPTED = "shared_pref_encrypted"

    private val MASTER_KEY_ALIAS = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);

    @Provides
    @Named("insecure")
    @Singleton
    fun provideInSecureSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)

    @Provides
    @Named("secure")
    @Singleton
    fun provideSecureSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        EncryptedSharedPreferences.create(
            SHARED_PREF_ENCRYPTED,
            MASTER_KEY_ALIAS,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    @Provides
    @Singleton
    fun provideCredentialStorage(
        @Named("insecure") insecureSharedPreferences: SharedPreferences,
        @Named("secure") secureSharedPreferences: SharedPreferences
    ): CredentialStorage = CredentialStorage(insecureSharedPreferences, secureSharedPreferences)
}