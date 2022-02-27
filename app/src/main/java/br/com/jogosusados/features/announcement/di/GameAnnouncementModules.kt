package br.com.jogosusados.features.announcement.di

import br.com.jogosusados.features.announcement.GameAnnouncementViewModel
import br.com.jogosusados.features.announcement.repository.GameAnnouncementRepository
import br.com.jogosusados.features.announcement.repository.GameAnnouncementRepositoryImpl
import br.com.jogosusados.features.announcement.repository.GameAnnouncementsAPI
import br.com.jogosusados.network.NetworkModule
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object GameAnnouncementModules {

    val instance = module {

        single { get<Retrofit>(NetworkModule.NetworkRegular).create(GameAnnouncementsAPI::class.java) }

        factory<GameAnnouncementRepository> { (callback: CallbackNetworkRequest?) ->
            GameAnnouncementRepositoryImpl(
                api = get(),
                callbackNetworkRequest = callback
            )
        }

        viewModel { (callback: CallbackNetworkRequest?) -> GameAnnouncementViewModel(callback) }
    }
}
