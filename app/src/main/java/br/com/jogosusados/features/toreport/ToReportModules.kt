package br.com.jogosusados.features.toreport

import br.com.jogosusados.R
import br.com.jogosusados.features.toreport.ToReportType.ANNOUNCEMENT
import br.com.jogosusados.features.toreport.ToReportType.GAME
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object ToReportModules {

    val instance = module {

//        single { get<Retrofit>(NetworkModule.NetworkRegular).create(GameAnnouncementsAPI::class.java) }
//
//        factory<GameAnnouncementRepository> { (callback: CallbackNetworkRequest?) ->
//            GameAnnouncementRepositoryImpl(
//                api = get(),
//                callbackNetworkRequest = callback
//            )
//        }

        single(named(GAME)) { titleGame ->
            val text = titleGame.get<String>()
            androidContext().getString(R.string.to_report_hint_game, text)
        }

        single(named(ANNOUNCEMENT)) { titleAnnouncement ->
            val text = titleAnnouncement.get<String>()
            androidContext().getString(R.string.to_report_hint_announcement, text)
        }

        viewModel { ToReportViewModel() }
    }
}
