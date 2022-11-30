package jkl.craftbeertest.beer.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface BeersCommunications : ObserveBeers {

    fun showList(list: List<BeerUi>)

    fun showProgress(show: Int)

    fun updateItem(item: Int)

    class Base(
        private val beersList: BeersListCommunication,
        private val progress: BeersProgressCommunication,
        private val updateItem: BeerItemCommunication
    ) : BeersCommunications {

        override fun showProgress(show: Int) = progress.map(show)

        override fun showList(list: List<BeerUi>) = beersList.map(list)

        override fun updateItem(item: Int) = updateItem.map(item)

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) =
            progress.observe(owner, observer)

        override fun observeList(owner: LifecycleOwner, observer: Observer<List<BeerUi>>) =
            beersList.observe(owner, observer)

        override fun observeUpdateItem(owner: LifecycleOwner, observer: Observer<Int>) =
            updateItem.observe(owner, observer)
    }
}

interface ObserveBeers {
    fun observeList(owner: LifecycleOwner, observer: Observer<List<BeerUi>>)
    fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>)
    fun observeUpdateItem(owner: LifecycleOwner, observer: Observer<Int>)
}

interface BeersListCommunication : Communication.Mutable<List<BeerUi>> {
    class Base : Communication.Post<List<BeerUi>>(), BeersListCommunication
}

interface BeersProgressCommunication : Communication.Mutable<Int> {
    class Base : Communication.Post<Int>(), BeersProgressCommunication
}

interface BeerItemCommunication : Communication.Mutable<Int> {
    class Base : Communication.Post<Int>(), BeerItemCommunication
}