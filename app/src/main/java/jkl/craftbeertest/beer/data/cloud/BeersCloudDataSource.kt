package jkl.craftbeertest.beer.data.cloud

import jkl.craftbeertest.beer.data.model.BeerData

interface BeersCloudDataSource {

    suspend fun beersByPage(page: Int): List<BeerData>

    class Base(
        private val service: BeersService
    ) : BeersCloudDataSource {

        override suspend fun beersByPage(page: Int): List<BeerData> = service.beers(page)
    }

}