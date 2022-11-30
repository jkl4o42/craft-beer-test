package jkl.craftbeertest.beer.domain

sealed class BeersResult {

    interface Mapper<T> {
        fun map(list: List<BeerFact>, errorMessage: String): T
    }

    abstract fun <T> map(mapper: Mapper<T>): T

    data class Success(private val list: List<BeerFact> = emptyList()) : BeersResult() {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(list, "")
    }

    data class Failure(private val message: String) : BeersResult() {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(emptyList(), message)
    }
}