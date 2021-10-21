package kr.co.americano.funco.network.request

data class SecondPostingRequest(
    val title : String,
    val goal : Int,
    val closingYear: Int,
    val closingMonth: Int,
    val closingDay: Int,
    val story: String,
    val img: String
)
