package jkl.craftbeertest.detail.presentation

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import jkl.craftbeertest.beer.presentation.DetailsBeerUi
import jkl.craftbeertest.main.presentation.BaseFragment
import jkl.craftbeertesttest.R

class BeerDetailsFragment : BaseFragment<BeerDetailsViewModel>() {

    override val layoutId: Int = R.layout.fragment_beer_details
    override val viewModelClass: Class<BeerDetailsViewModel> = BeerDetailsViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val abvIbuTextView: TextView = view.findViewById(R.id.abvAndIbuTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
        val maltTextView: TextView = view.findViewById(R.id.maltTextView)
        val hopsTextView: TextView = view.findViewById(R.id.hopsTextView)
        val foodPairingTextView: TextView = view.findViewById(R.id.foodPairingTextView)

        val mapper = DetailsBeerUi(
            imageView,
            nameTextView,
            abvIbuTextView,
            descriptionTextView,
            maltTextView,
            hopsTextView,
            foodPairingTextView
        )
        viewModel.read()?.map(mapper)
    }

}