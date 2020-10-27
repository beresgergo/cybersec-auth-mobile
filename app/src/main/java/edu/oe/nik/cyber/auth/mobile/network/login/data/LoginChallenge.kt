package edu.oe.nik.cyber.auth.mobile.network.login.data

data class LoginChallengeRequest(
    val sessionId: String
)

data class LoginChallengeResponse(
    val status: String,
    val challenge: String
)