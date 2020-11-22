package edu.oe.nik.cyber.auth.mobile.network.registration.data

data class InitiateRegistrationResponse(
    val status : String,
    val sessionId : String,
    var message: String?
)
