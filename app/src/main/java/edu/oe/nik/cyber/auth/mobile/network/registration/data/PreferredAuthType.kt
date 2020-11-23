package edu.oe.nik.cyber.auth.mobile.network.registration.data

data class PreferredAuthTypeRequest(
    val sessionId: String,
    val preferredAuthType: String
)

data class PreferredAuthTypeResponse(
    val status: String,
    var message: String?
)