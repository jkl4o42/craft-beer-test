package jkl.craftbeertest.main.sl

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class BeerApp : Application(), ProvideViewModel {

    private lateinit var viewModelsFactory: ViewModelsFactory
    private lateinit var dependencyContainer: DependencyContainer.Base

    override fun onCreate() {
        super.onCreate()
        val provideInstance = ProvideInstance.Release()
        dependencyContainer = DependencyContainer.Base(this, Core.Base(provideInstance))
        viewModelsFactory = ViewModelsFactory(dependencyContainer)
    }

    override fun <T : ViewModel> provideViewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T =
        ViewModelProvider(owner, viewModelsFactory)[clazz]
}