package jkl.craftbeertest.beer.domain

interface HandleRequest {

    suspend fun handle(page: Int, block: suspend () -> Unit): BeersResult

    class Base(
        private val handleError: HandleError<String>,
        private val repository: BeersRepository
    ) : HandleRequest {

        override suspend fun handle(page: Int, block: suspend () -> Unit) = try {
            BeersResult.Success(repository.beersByPage(page))
        } catch (e: Exception) {
            BeersResult.Failure(handleError.handle(e))
        }
    }
}