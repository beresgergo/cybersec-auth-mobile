package edu.oe.nik.cyber.auth.mobile.di.modules.crypto

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.biometric.BiometricManager
import dagger.Module
import dagger.Provides
import edu.oe.nik.cyber.auth.mobile.di.qualifiers.ApplicationContext
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.Signature
import java.util.*
import javax.inject.Singleton
import javax.security.auth.x500.X500Principal


@Module
object CryptoModule {

    private const val ANDROID_KEY_STORE = "AndroidKeyStore"

    private const val KEY_ALIAS = "RegistrationKey"

    @Provides
    @Singleton
    fun provideAndroidKeystore(): KeyStore = KeyStore.getInstance(ANDROID_KEY_STORE).apply { load(null) }

    @Provides
    @Singleton
    fun provideRsaKeyPairGenerator(): KeyPairGenerator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, ANDROID_KEY_STORE)

    @Provides
    @Singleton
    fun provideSignature(): Signature = Signature.getInstance("SHA256withRSA")

    @Provides
    @Singleton
    fun provideBiometricManager(@ApplicationContext context: Context): BiometricManager = BiometricManager.from(context)

    @Provides
    @Singleton
    fun provideKeyGenParameterSpec(): KeyGenParameterSpec {
        val startDate = GregorianCalendar()
        val endDate = GregorianCalendar()

        return KeyGenParameterSpec.Builder(KEY_ALIAS,
            KeyProperties.PURPOSE_SIGN).run {
            setCertificateSubject(X500Principal("CN=$KEY_ALIAS"))     //Subject used for the self-signed certificate of the generated key pair, default is CN=fake
            setDigests(KeyProperties.DIGEST_SHA256)                         //Set of digests algorithms with which the key can be used
            setSignaturePaddings(KeyProperties.SIGNATURE_PADDING_RSA_PKCS1) //Set of padding schemes with which the key can be used when signing/verifying
            setCertificateNotBefore(startDate.time)                         //Start of the validity period for the self-signed certificate of the generated, default Jan 1 1970
            setCertificateNotAfter(endDate.time)                            //End of the validity period for the self-signed certificate of the generated key, default Jan 1 2048
            setUserAuthenticationRequired(true)                             //Sets whether this key is authorized to be used only if the user has been authenticated, default false
            setUserAuthenticationValidityDurationSeconds(30)                //Duration(seconds) for which this key is authorized to be used after the user is successfully authenticated
            build()
        }
    }
}