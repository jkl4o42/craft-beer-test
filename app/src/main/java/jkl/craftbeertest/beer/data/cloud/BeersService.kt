package jkl.craftbeertest.beer.data.cloud

import jkl.craftbeertest.beer.data.model.BeerData
import retrofit2.http.GET
import retrofit2.http.Query

interface BeersService {

    @GET("beers?per_page=20")
    suspend fun beers(@Query("page") page: Int): List<BeerData>

}