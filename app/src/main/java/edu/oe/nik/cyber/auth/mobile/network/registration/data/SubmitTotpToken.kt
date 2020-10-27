package edu.oe.nik.cyber.auth.mobile.network.registration.data

data class SubmitTotpTokenRequest(
    val sessionId: String,
    val totpSecret: String
)

data class SubmitTotpTokenResponse(
    val status: String
)