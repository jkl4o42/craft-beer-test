package jkl.craftbeertest.beer.domain

import jkl.craftbeertest.beer.data.model.Ingredients
import jkl.craftbeertest.beer.presentation.BeerUi

class BeersUiMapper : BeerFact.Mapper<BeerUi> {
    override fun map(
        id: Int,
        name: String,
        tagline: String,
        first_brewed: String,
        description: String,
        image_url: String,
        abv: Double,
        ibu: Double,
        ingredients: Ingredients,
        food_pairing: List<String>
    ): BeerUi {
        return BeerUi(
            id,
            name,
            tagline,
            first_brewed,
            description,
            image_url,
            abv,
            ibu,
            ingredients,
            food_pairing
        )
    }
}