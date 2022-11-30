package jkl.craftbeertest.beer.data.model

data class BeerData(
    private val id: Int,
    private val name: String,
    private val tagline: String,
    private val first_brewed: String,
    private val description: String,
    private val image_url: String,
    private val abv: Double,
    private val ibu: Double,
    private val ingredients: Ingredients,
    private val food_pairing: List<String>
) {

    interface Mapper<T> {
        fun map(
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
        ): T
    }

    fun <T> map(mapper: Mapper<T>): T = mapper.map(
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