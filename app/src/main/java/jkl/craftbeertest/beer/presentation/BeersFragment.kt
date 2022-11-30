package jkl.craftbeertest.beer.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import jkl.craftbeertest.main.presentation.BaseFragment
import jkl.craftbeertesttest.R

class BeersFragment : BaseFragment<BeerViewModel.Base>() {

    override val layoutId: Int = R.layout.fragment_beers
    override val viewModelClass: Class<BeerViewModel.Base> = BeerViewModel.Base::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        val itemClickListener = object : ClickListener {
            override fun click(item: BeerUi, position: Int) = viewModel.showBeerDetails(item)
        }
        val itemFavoriteClickListener = object : ClickListener {
            override fun click(item: BeerUi, position: Int) {
                val shared = activity?.getSharedPreferences("sharedPreference", Context.MODE_PRIVATE)
                val gson = Gson()
                val favorite = shared?.getString("favorite", null)
                val itemType = object : TypeToken<ArrayList<Int>>() {}.type
                val list = gson.fromJson<ArrayList<Int>>(favorite, itemType) ?: emptyList()
                if (list.contains(position + 1)) {
                    viewModel.unlikeBeer(position + 1)
                } else {
                    viewModel.likeBeer(position + 1)
                }
            }
        }
        val adapter = BeersAdapter(itemClickListener, itemFavoriteClickListener)

        recyclerView.adapter = adapter

        viewModel.observeList(viewLifecycleOwner) {
            adapter.map(it)
        }

        viewModel.observeUpdateItem(viewLifecycleOwner) {
            adapter.notifyItemChanged(it)
        }

        recyclerView.addOnScrollListener(object : RecyclerViewPaginator(recyclerView) {
            override fun loadMore(page: Int) {
                viewModel.updateBeersList(page)
            }
        })

        viewModel.observeProgress(viewLifecycleOwner) {
            progressBar.visibility = it
        }

        viewModel.updateBeersList(1)
    }
}