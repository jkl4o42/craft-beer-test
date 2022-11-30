package jkl.craftbeertest.detail.presentation

import androidx.lifecycle.ViewModel
import jkl.craftbeertest.beer.presentation.BeerUi
import jkl.craftbeertest.detail.data.BeerFactDetails

class BeerDetailsViewModel(
    private val data: BeerFactDetails.Read
) : ViewModel(), BeerFactDetails.Read {

    override fun read(): BeerUi? = data.read()
}