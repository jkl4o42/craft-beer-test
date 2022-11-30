package jkl.craftbeertest.beer.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import jkl.craftbeertest.beer.data.model.Ingredients
import jkl.craftbeertesttest.R

class BeerUi(
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
) : Mapper<Boolean, BeerUi> {

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

    override fun map(source: BeerUi): Boolean = source.id == id
}

class DetailsBeerUi(
    private val imageView: ImageView,
    private val nameTextView: TextView,
    private val abvIbuTextView: TextView,
    private val descriptionTextView: TextView,
    private val maltTextView: TextView,
    private val hopsTextView: TextView,
    private val foodPairingTextView: TextView,
) : BeerUi.Mapper<Unit> {

    @SuppressLint("SetTextI18n")
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
    ) {

        nameTextView.text = name
        abvIbuTextView.text = "ABV - ${abv}% IBU - $ibu"
        descriptionTextView.text = description
        maltTextView.text = ingredients.malt.joinToString(", ") { it.name }
        hopsTextView.text = ingredients.hops.joinToString(", ") { it.name }
        foodPairingTextView.text = food_pairing.joinToString("\n")

        Glide.with(imageView.context).load(image_url).placeholder(R.drawable.ic_image)
            .into(imageView)
    }
}

class ListItemUi(
    private val imageView: ImageView,
    private val nameView: TextView,
    private val abvView: TextView,
    private val favoriteView: ImageButton
) : BeerUi.Mapper<Unit> {

    @SuppressLint("SetTextI18n")
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
    ) {
        nameView.text = name
        abvView.text = "ABV: ${abv}%"

        val shared =
            favoriteView.context.getSharedPreferences("sharedPreference", Context.MODE_PRIVATE)
        val gson = Gson()
        val favorite = shared.getString("favorite", null)
        val itemType = object : TypeToken<ArrayList<Int>>() {}.type
        val list = gson.fromJson<ArrayList<Int>>(favorite, itemType) ?: emptyList()

        val icon = if (list.contains(id)) R.drawable.ic_favorite else R.drawable.ic_favorite_border
        favoriteView.setBackgroundResource(icon)

        Glide.with(imageView.context).load(image_url).placeholder(R.drawable.ic_image)
            .into(imageView)
    }
}