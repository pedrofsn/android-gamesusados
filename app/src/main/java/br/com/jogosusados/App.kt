package br.com.jogosusados

import android.app.Application
import br.com.jogosusados.connection.NetworkModule
import br.com.jogosusados.login.LoginModule
import org.koin.core.component.KoinComponent
import org.koin.core.context.GlobalContext.startKoin

class App : Application(), KoinComponent {

    private val modules = listOf(NetworkModule.instance, LoginModule.instance)

    override fun onCreate() {
        super.onCreate()
        initializeDependencies()
    }

    private fun initializeDependencies() {
        startKoin {
            printLogger()
            modules(modules)
        }
    }

}