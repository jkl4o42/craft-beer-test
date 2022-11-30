package jkl.craftbeertest.beer.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jkl.craftbeertest.beer.domain.BeersInteractor
import jkl.craftbeertest.main.presentation.NavigationCommunication
import jkl.craftbeertest.main.presentation.NavigationStrategy
import jkl.craftbeertest.main.presentation.Screen

interface BeerViewModel : FetchBeers, ObserveBeers {

    fun showBeerDetails(beerUi: BeerUi)

    fun likeBeer(item: Int)

    fun unlikeBeer(item: Int)

    class Base(
        private val handleResult: HandleBeersRequest,
        private val handleDataResult: HandleBeersData,
        private val interactor: BeersInteractor,
        private val navigationCommunication: NavigationCommunication.Mutate,
        private val communications: BeersCommunications
    ) : ViewModel(), BeerViewModel {

        override fun showBeerDetails(beerUi: BeerUi) {
            interactor.saveBeer(beerUi)
            navigationCommunication.map(NavigationStrategy.Add(Screen.Details))
        }

        override fun likeBeer(item: Int) = handleDataResult.handle(viewModelScope) {
            interactor.likeBeer(item)
        }

        override fun unlikeBeer(item: Int) = handleDataResult.handle(viewModelScope) {
            interactor.unlikeBeer(item)
        }

        override fun updateBeersList(page: Int) = handleResult.handle(viewModelScope) {
            interactor.beersByPage(page)
        }

        override fun observeList(owner: LifecycleOwner, observer: Observer<List<BeerUi>>) {
            communications.observeList(owner, observer)
        }

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) {
            communications.observeProgress(owner, observer)
        }

        override fun observeUpdateItem(owner: LifecycleOwner, observer: Observer<Int>) {
            communications.observeUpdateItem(owner, observer)
        }
    }
}

interface FetchBeers {
    fun updateBeersList(page: Int)
}