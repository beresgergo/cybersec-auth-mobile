package edu.oe.nik.cyber.auth.mobile.network.management.data

data class ChangePreferredAuthTypeRequest(
    val token: String,
    val preferredAuthType: String
)

data class ChangePreferredAuthTypeResponse(
    val status: String,
    var message: String?
)