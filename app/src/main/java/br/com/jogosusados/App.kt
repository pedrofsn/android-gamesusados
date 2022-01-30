package br.com.jogosusados

import android.app.Application
import br.com.jogosusados.network.NetworkModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.GlobalContext.startKoin

class App : Application(), KoinComponent {

    private val modules = listOf(NetworkModule.instance)

    override fun onCreate() {
        super.onCreate()
        initializeDependencies()
    }

    private fun initializeDependencies() {
        startKoin {
            androidLogger(org.koin.core.logger.Level.ERROR)
            modules(modules)
        }
    }

}
