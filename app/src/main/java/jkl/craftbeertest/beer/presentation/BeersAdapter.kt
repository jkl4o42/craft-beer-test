package jkl.craftbeertest.beer.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import jkl.craftbeertesttest.R

class BeersAdapter(
    private val clickListenerItem: ClickListener,
    private val clickListenerFavorite: ClickListener) :
    RecyclerView.Adapter<BeersViewHolder>(), Mapper.Unit<List<BeerUi>> {

    private val list = mutableListOf<BeerUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BeersViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.beer_layout, parent, false),
        clickListenerItem,
        clickListenerFavorite
    )

    override fun onBindViewHolder(holder: BeersViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount() = list.size

    override fun map(source: List<BeerUi>) {
        val diff = DiffUtilCallback(list, source)
        list.addAll(source)
        notifyItemRangeChanged(diff.oldListSize, diff.newListSize)
    }
}

class BeersViewHolder(
    view: View,
    private val clickListenerItem: ClickListener,
    private val clickListenerFavorite: ClickListener
) :
    RecyclerView.ViewHolder(view) {

    private val image = itemView.findViewById<ImageView>(R.id.beerImageView)
    private val name = itemView.findViewById<TextView>(R.id.beerName)
    private val abv = itemView.findViewById<TextView>(R.id.beerABV)
    private val favorite = itemView.findViewById<ImageButton>(R.id.favoriteImageButton)
    private val mapper = ListItemUi(image, name, abv, favorite)

    fun bind(model: BeerUi) {
        model.map(mapper)
        itemView.setOnClickListener { clickListenerItem.click(model, layoutPosition) }
        favorite.setOnClickListener {
            clickListenerFavorite.click(model,layoutPosition)
        }
    }
}

interface ClickListener {
    fun click(item: BeerUi, position: Int)
}

class DiffUtilCallback(
    private val oldList: List<BeerUi>,
    private val newList: List<BeerUi>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].map(newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].equals(newList[newItemPosition])
}