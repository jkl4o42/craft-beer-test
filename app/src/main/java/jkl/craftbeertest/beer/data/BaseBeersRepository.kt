package jkl.craftbeertest.beer.data

import jkl.craftbeertest.beer.data.cache.BeersCacheDataSource
import jkl.craftbeertest.beer.data.cloud.BeersCloudDataSource
import jkl.craftbeertest.beer.domain.BeerFact
import jkl.craftbeertest.beer.domain.BeersRepository

class BaseBeersRepository(
    private val cacheDataSource: BeersCacheDataSource,
    private val cloudDataSource: BeersCloudDataSource,
    private val handleDataRequest: HandleDataRequest,
) : BeersRepository {

    override suspend fun beersByPage(page: Int): List<BeerFact> = handleDataRequest.handle {
        cloudDataSource.beersByPage(page)
    }

    override suspend fun likeBeer(beerId: Int) = cacheDataSource.likeBeer(beerId)

    override suspend fun unlikeBeer(beerId: Int) = cacheDataSource.unlikeBeer(beerId)
}