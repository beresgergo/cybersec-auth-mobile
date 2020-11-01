package edu.oe.nik.cyber.auth.mobile.di.modules.otp

import dagger.Module
import dagger.Provides
import dev.turingcomplete.kotlinonetimepassword.HmacAlgorithm
import dev.turingcomplete.kotlinonetimepassword.HmacOneTimePasswordConfig
import dev.turingcomplete.kotlinonetimepassword.HmacOneTimePasswordGenerator
import edu.oe.nik.cyber.auth.mobile.storage.CredentialStorage
import java.security.SecureRandom
import javax.inject.Singleton

@Module
object OTPModule {

    @Provides
    @Singleton
    fun provideTotpGenerator(credentialStorage: CredentialStorage): HmacOneTimePasswordGenerator {
        val config = HmacOneTimePasswordConfig(
            codeDigits = 6,
            hmacAlgorithm = HmacAlgorithm.SHA256
        )

        return HmacOneTimePasswordGenerator(credentialStorage.totpSecret.toByteArray(), config)
    }
}