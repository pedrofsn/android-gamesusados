package br.com.jogosusados.features.my.di

import br.com.jogosusados.features.my.MyGamesAnnouncementsViewModel
import br.com.jogosusados.features.my.repository.MyGamesAPI
import br.com.jogosusados.features.my.repository.MyGamesAnnouncementsRepository
import br.com.jogosusados.features.my.repository.MyGamesRepositoryImpl
import br.com.jogosusados.network.NetworkModule
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object MyGamesModules {

    val instance = module {

        single { get<Retrofit>(NetworkModule.NetworkRegular).create(MyGamesAPI::class.java) }

        factory<MyGamesAnnouncementsRepository> { (callback: CallbackNetworkRequest?) ->
            MyGamesRepositoryImpl(
                api = get(),
                callbackNetworkRequest = callback
            )
        }

        viewModel { (callback: CallbackNetworkRequest?) -> MyGamesAnnouncementsViewModel(callback) }
    }
}
