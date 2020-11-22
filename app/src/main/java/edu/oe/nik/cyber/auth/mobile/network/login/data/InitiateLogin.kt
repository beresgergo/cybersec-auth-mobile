package edu.oe.nik.cyber.auth.mobile.network.login.data

data class InitiateLoginResponse(
    val status: String,
    val preferredAuthType: String,
    val sessionId: String,
    var message: String?
)