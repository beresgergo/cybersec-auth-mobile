package edu.oe.nik.cyber.auth.mobile.storage

import android.content.SharedPreferences
import javax.inject.Inject

class CredentialStorage @Inject constructor(
    val insecureSharedPreferences: SharedPreferences,
    val secureSharedPreferences: SharedPreferences
){

    companion object SharedPrefKeys {
        const val JWT_KEY = "jwt"
        const val TOTP_SECRET = "totpSecret"
        const val PREFERRED_AUTH_TYPE = "preferredAuthType"
    }

    var jwt: String
        get() = insecureSharedPreferences.getString(SharedPrefKeys.JWT_KEY, "")
        set(value) {
            with(insecureSharedPreferences.edit()) {
                putString(SharedPrefKeys.JWT_KEY, value)
                apply()
            }
            with(secureSharedPreferences.edit()) {
                putString(SharedPrefKeys.JWT_KEY, value)
                apply()
            }
        }

    var totpSecret: String
        get() = insecureSharedPreferences.getString(SharedPrefKeys.TOTP_SECRET, "")
        set(value) {
            with(insecureSharedPreferences.edit()) {
                putString(SharedPrefKeys.TOTP_SECRET, value)
                apply()
            }
            with(secureSharedPreferences.edit()) {
                putString(SharedPrefKeys.TOTP_SECRET, value)
                apply()
            }
        }

    var preferredAuthType: String
        get() = insecureSharedPreferences.getString(SharedPrefKeys.PREFERRED_AUTH_TYPE, "")
        set(value) {
            with(insecureSharedPreferences.edit()) {
                putString(SharedPrefKeys.PREFERRED_AUTH_TYPE, value)
                apply()
            }
            with(secureSharedPreferences.edit()) {
                putString(SharedPrefKeys.PREFERRED_AUTH_TYPE, value)
                apply()
            }
        }
}
