package jkl.craftbeertest.main.sl

import jkl.craftbeertest.beer.data.cloud.CloudModule

interface ProvideInstance {

    fun provideCloudModule(): CloudModule

    class Release() : ProvideInstance {
        override fun provideCloudModule() = CloudModule.Base()
    }
}

