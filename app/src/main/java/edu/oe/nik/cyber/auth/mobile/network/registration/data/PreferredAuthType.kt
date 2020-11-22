package edu.oe.nik.cyber.auth.mobile.network.registration.data

data class PreferredAuthTypeRequest(
    val preferredAuthType: String,
    val sessionId: String
)

data class PreferredAuthTypeResponse(
    val status: String,
    var message: String?
)