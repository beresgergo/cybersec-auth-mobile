package edu.oe.nik.cyber.auth.mobile.di.modules.otp

import dagger.Module
import dagger.Provides
import dev.turingcomplete.kotlinonetimepassword.HmacAlgorithm
import dev.turingcomplete.kotlinonetimepassword.TimeBasedOneTimePasswordConfig
import dev.turingcomplete.kotlinonetimepassword.TimeBasedOneTimePasswordGenerator
import edu.oe.nik.cyber.auth.mobile.storage.CredentialStorage
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object OTPModule {

    @Provides
    @Singleton
    fun provideTotpGenerator(credentialStorage: CredentialStorage): TimeBasedOneTimePasswordGenerator {
        val config = TimeBasedOneTimePasswordConfig(
            codeDigits = 6,
            hmacAlgorithm = HmacAlgorithm.SHA1,
            timeStep = 30,
            timeStepUnit = TimeUnit.SECONDS
        )

        return TimeBasedOneTimePasswordGenerator(credentialStorage.totpSecret.toByteArray(), config)
    }
}