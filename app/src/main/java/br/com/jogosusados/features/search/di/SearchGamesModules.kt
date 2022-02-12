package br.com.jogosusados.features.search.di

import br.com.jogosusados.features.search.SearchViewModel
import br.com.jogosusados.features.search.repository.SearchGamesAPI
import br.com.jogosusados.features.search.repository.SearchGamesRepository
import br.com.jogosusados.features.search.repository.SearchGamesRepositoryImpl
import br.com.jogosusados.network.NetworkModule
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object SearchGamesModules {

    val instance = module {

        single { get<Retrofit>(NetworkModule.NetworkRegular).create(SearchGamesAPI::class.java) }

        factory<SearchGamesRepository> { (callback: CallbackNetworkRequest?) ->
            SearchGamesRepositoryImpl(
                api = get(),
                callbackNetworkRequest = callback
            )
        }

        viewModel { (callback: CallbackNetworkRequest?) -> SearchViewModel(callback) }
    }
}
