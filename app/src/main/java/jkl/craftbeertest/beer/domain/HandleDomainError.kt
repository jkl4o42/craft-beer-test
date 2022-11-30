package jkl.craftbeertest.beer.domain

import java.net.UnknownHostException

class HandleDomainError : HandleError<Exception> {

    override fun handle(e: Exception) = when (e) {
        is UnknownHostException -> NoInternetConnectionException()
        else -> ServiceUnavailableException()
    }
}