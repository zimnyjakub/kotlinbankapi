package domain

data class Currency(val CentFactor: Int, val StringRepresentation: String){
    companion object Factory {
        fun PLN(): Currency = Currency(100, "PLN")
        fun USD(): Currency = Currency(100, "USD")
        fun EUR(): Currency = Currency(100, "EUR")

    }
}