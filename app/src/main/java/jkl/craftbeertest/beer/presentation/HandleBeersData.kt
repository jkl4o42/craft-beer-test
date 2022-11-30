package jkl.craftbeertest.beer.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

interface HandleBeersData {

    fun handle(
        coroutineScope: CoroutineScope,
        block: suspend () -> Int
    )

    class Base(
        private val dispatchers: DispatchersList,
        private val communications: BeersCommunications,
    ) : HandleBeersData {
        override fun handle(coroutineScope: CoroutineScope, block: suspend () -> Int) {
            coroutineScope.launch(dispatchers.io()) {
                val result = block.invoke()
                communications.updateItem(result -1)
            }
        }
    }
}