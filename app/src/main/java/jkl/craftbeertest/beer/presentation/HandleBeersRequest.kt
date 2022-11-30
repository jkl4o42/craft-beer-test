package jkl.craftbeertest.beer.presentation

import android.view.View
import jkl.craftbeertest.beer.domain.BeersResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

interface HandleBeersRequest {

    fun handle(
        coroutineScope: CoroutineScope,
        block: suspend () -> BeersResult
    )

    class Base(
        private val dispatchers: DispatchersList,
        private val communications: BeersCommunications,
        private val beersResultMapper: BeersResult.Mapper<Unit>
    ) : HandleBeersRequest {
        override fun handle(
            coroutineScope: CoroutineScope,
            block: suspend () -> BeersResult
        ) {
            communications.showProgress(View.VISIBLE)
            coroutineScope.launch(dispatchers.io()) {
                val result = block.invoke()
                communications.showProgress(View.GONE)
                result.map(beersResultMapper)
            }
        }
    }
}