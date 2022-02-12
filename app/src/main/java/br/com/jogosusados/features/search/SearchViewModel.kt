package br.com.jogosusados.features.search

import androidx.databinding.ObservableField
import br.com.jogosusados.features.search.domain.Pagination
import br.com.jogosusados.features.search.repository.SearchGamesRepository
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class SearchViewModel(callback: CallbackNetworkRequest?) : BaseViewModel(), KoinComponent {

    val title = ObservableField<String>()
    val showEmptyView = ObservableField(false)

    private val sarchGamesRepository: SearchGamesRepository by inject {
        parametersOf(this@SearchViewModel, callback)
    }

    val pagination by lazy {
        return@lazy Pagination(
            scope = this,
            onPreExecute = { showProgressbar(true) },
            doInBackground = { _, page -> sarchGamesRepository.searchGames(page, title.get()) },
            onPostExecute = { _, _ -> showProgressbar(false) },
            handleEmptyData = { show -> showEmptyView.set(show) }
        )
    }
}
