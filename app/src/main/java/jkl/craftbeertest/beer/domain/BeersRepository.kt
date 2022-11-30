package jkl.craftbeertest.beer.domain

interface BeersRepository {

    suspend fun beersByPage(page: Int): List<BeerFact>

    suspend fun likeBeer(beerId: Int): Int

    suspend fun unlikeBeer(beerId: Int): Int
}