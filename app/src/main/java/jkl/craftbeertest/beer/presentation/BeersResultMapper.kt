package jkl.craftbeertest.beer.presentation

import jkl.craftbeertest.beer.domain.BeerFact
import jkl.craftbeertest.beer.domain.BeersResult

class BeersResultMapper(
    private val communication: BeersCommunications,
    private val mapper: BeerFact.Mapper<BeerUi>
) : BeersResult.Mapper<Unit> {

    override fun map(list: List<BeerFact>, errorMessage: String) {
        if (errorMessage.isEmpty()) {
            if (list.isNotEmpty()) {
                communication.showList(list.map { it.map(mapper) })
            }
        }
    }
}