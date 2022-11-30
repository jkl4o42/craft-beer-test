package jkl.craftbeertest.main.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import jkl.craftbeertest.main.sl.ProvideViewModel
import jkl.craftbeertesttest.R

class MainActivity : AppCompatActivity(), ProvideViewModel {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = provideViewModel(MainViewModel::class.java, this)
        viewModel.observe(this) { strategy ->
            strategy.navigate(supportFragmentManager, R.id.container)
        }
        viewModel.init(savedInstanceState == null)
    }

    override fun <T : ViewModel> provideViewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T =
        (application as ProvideViewModel).provideViewModel(clazz, owner)

}