package edu.oe.nik.cyber.auth.mobile.network.login.data

data class RetrieveTokenRequest(
    val sessionId: String
)

data class RetrieveTokenResponse(
    val status: String,
    val token: String,
    var message: String?
)