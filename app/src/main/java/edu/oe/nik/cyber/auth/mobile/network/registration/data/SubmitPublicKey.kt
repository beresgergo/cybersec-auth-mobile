package edu.oe.nik.cyber.auth.mobile.network.registration.data

data class SubmitPublicKeyRequest(
    val sessionId: String,
    val publicKey: String
)

data class SubmitPublicKeyResponse(
    val status: String,
    var message: String?
)