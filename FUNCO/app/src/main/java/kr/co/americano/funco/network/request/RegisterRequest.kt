package kr.co.americano.funco.network.request

data class RegisterRequest(
    val email: String,
    val password: String,
    val password2: String,
    val name: String
)
