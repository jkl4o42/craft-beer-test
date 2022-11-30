package jkl.craftbeertest.beer.domain

abstract class DomainException : IllegalStateException()

class NoInternetConnectionException : DomainException()

class ServiceUnavailableException : DomainException()