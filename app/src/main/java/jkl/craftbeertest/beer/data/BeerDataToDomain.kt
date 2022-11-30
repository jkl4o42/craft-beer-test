package jkl.craftbeertest.beer.data

import jkl.craftbeertest.beer.data.model.BeerData
import jkl.craftbeertest.beer.data.model.Ingredients
import jkl.craftbeertest.beer.domain.BeerFact

class BeerDataToDomain : BeerData.Mapper<BeerFact> {
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
    ): BeerFact =
        BeerFact(
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