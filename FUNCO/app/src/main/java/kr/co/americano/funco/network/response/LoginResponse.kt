package kr.co.americano.funco.network.response

data class LoginResponse (
    val status: Int,
    val message : String,
    val token: String
)