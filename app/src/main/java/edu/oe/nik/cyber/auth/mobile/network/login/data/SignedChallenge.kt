package edu.oe.nik.cyber.auth.mobile.network.login.data

data class SignedChallengeRequest(
    val sessionId: String,
    val signedChallenge: String
)

data class SignedChallengeResponse(
    val message: String
)