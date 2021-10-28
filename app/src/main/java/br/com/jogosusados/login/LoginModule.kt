package br.com.jogosusados.login

import br.com.jogosusados.Api
import br.com.jogosusados.login.repository.LoginRepository
import br.com.jogosusados.login.repository.LoginRepositoryImpl
import br.com.jogosusados.network.NetworkModule
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import com.squareup.moshi.Moshi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object LoginModule {

    val instance = module {

        single { Moshi.Builder().build() }

        factory {
            get<Retrofit>(NetworkModule.NetworkRegular)
                .create(Api::class.java)
        }

        factory<LoginRepository> { (callback: CallbackNetworkRequest?) ->
            LoginRepositoryImpl(
                api = get(),
                callbackNetworkRequest = callback
            )
        }
/*
        factory { (callback: CallbackNetworkRequest?) ->
            NetworkAndErrorHandler(
                callbackNetworkRequest = callback
            )
        }
*/
        viewModel { LoginViewModel() }
    }
}