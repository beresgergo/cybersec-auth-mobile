package edu.oe.nik.cyber.auth.mobile.network.management.data

data class DeleteUserRequest(
    val token: String
)

data class DeleteUserResponse(
    val status: String,
    var message: String?
)