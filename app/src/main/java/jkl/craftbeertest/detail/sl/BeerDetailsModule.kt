package jkl.craftbeertest.detail.sl

import jkl.craftbeertest.detail.presentation.BeerDetailsViewModel
import jkl.craftbeertest.main.sl.Module
import jkl.craftbeertest.main.sl.ProvideBeerDetails

class BeerDetailsModule(
    private val provideBeerDetails: ProvideBeerDetails
) : Module<BeerDetailsViewModel> {

    override fun viewModel(): BeerDetailsViewModel =
        BeerDetailsViewModel(provideBeerDetails.provideBeerDetails())
}