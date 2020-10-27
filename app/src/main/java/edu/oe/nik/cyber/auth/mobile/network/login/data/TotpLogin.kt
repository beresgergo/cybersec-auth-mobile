package edu.oe.nik.cyber.auth.mobile.network.login.data

data class TotpLoginRequest(
    val sessionId: String,
    val token: String
)

data class TotpLoginResponse(
    val status: String
)