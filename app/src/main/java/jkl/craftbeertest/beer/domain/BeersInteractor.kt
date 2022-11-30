package jkl.craftbeertest.beer.domain

import jkl.craftbeertest.beer.presentation.BeerUi
import jkl.craftbeertest.detail.data.BeerFactDetails

interface BeersInteractor {

    suspend fun beersByPage(page: Int): BeersResult

    suspend fun likeBeer(item: Int): Int

    suspend fun unlikeBeer(item: Int): Int

    fun saveBeer(beer: BeerUi)

    class Base(
        private val repository: BeersRepository,
        private val handleRequest: HandleRequest,
        private val beerFactDetails: BeerFactDetails.Save
    ) : BeersInteractor {

        override suspend fun beersByPage(page: Int): BeersResult = handleRequest.handle(page) {}

        override suspend fun likeBeer(item: Int): Int = repository.likeBeer(item)

        override suspend fun unlikeBeer(item: Int): Int = repository.unlikeBeer(item)

        override fun saveBeer(beer: BeerUi) = beerFactDetails.save(beer)
    }
}