package jkl.craftbeertest.beer.data

import jkl.craftbeertest.beer.data.model.BeerData
import jkl.craftbeertest.beer.domain.BeerFact
import jkl.craftbeertest.beer.domain.HandleError
import java.lang.reflect.Executable

interface HandleDataRequest {

    suspend fun handle(block: suspend () -> List<BeerData>): List<BeerFact>

    class Base(
        private val mapperToDomain: BeerDataToDomain,
        private val handleError: HandleError<Exception>
    ) : HandleDataRequest {

        override suspend fun handle(block: suspend () -> List<BeerData>): List<BeerFact> = try {
            val result = block.invoke()
            result.map { it.map(mapperToDomain) }
        } catch (e: Exception) {
            throw handleError.handle(e)
        }
    }
}