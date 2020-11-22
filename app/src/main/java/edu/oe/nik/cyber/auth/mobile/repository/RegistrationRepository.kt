package edu.oe.nik.cyber.auth.mobile.repository

import edu.oe.nik.cyber.auth.mobile.network.registration.RegistrationApi
import javax.inject.Inject

class RegistrationRepository @Inject constructor(
   private val registrationApi: RegistrationApi
) {
}