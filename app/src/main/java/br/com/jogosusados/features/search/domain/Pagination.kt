package br.com.jogosusados.features.search.domain

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import br.com.redcode.base.utils.Constants.EMPTY_STRING
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Pagination<T>(
    private val scope: CoroutineScope,
    private val filter: (() -> String?)? = null,

    private val onPreExecute: () -> Unit,
    private val doInBackground: suspend (String?, Int) -> (Paginated<T>)?,
    private val onPostExecute: (List<T>, Any?) -> Unit,
    private val handleEmptyData: (Boolean) -> Unit,

    private var count: Int = 0,
    private var total: Int = 0,
    private var started: Boolean = false,
) {

    var dataSource: MyDatasource? = null
    private var lastFilter: String? = EMPTY_STRING
    val data: LiveData<PagedList<T>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(COUNT_OF_ITEMS_IN_PAGE)
            .setEnablePlaceholders(false)
            .build()

        data = initializedPagedListBuilder(config).build()
    }

    fun invalidate() {
        total = 0
        count = 0
        started = false
        lastFilter = null
        dataSource?.invalidate()
    }

    private fun initializedPagedListBuilder(config: PagedList.Config): LivePagedListBuilder<Int, T> {

        val dataSourceFactory = object : DataSource.Factory<Int, T>() {
            override fun create(): Pagination<T>.MyDatasource {
                val filterCurrent = filter?.invoke()
                dataSource = MyDatasource(filter = filterCurrent)
                return dataSource as MyDatasource
            }
        }

        return LivePagedListBuilder<Int, T>(dataSourceFactory, config)
    }

    inner class MyDatasource(val filter: String?) : PageKeyedDataSource<Int, T>() {

        override fun loadInitial(
            p: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, T>
        ) {
            paginatedLoad(INDEX_OF_FIRST_PAGE) { total, list ->
                callback.onResult(list, null, 1)
            }
        }

        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
            val page = params.key + 1
            paginatedLoad(page) { _, list ->
                callback.onResult(list, page)
            }
        }

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {

        }

        private fun paginatedLoad(page: Int, onLoaded: (Int, List<T>) -> Unit) {
            if (hasMoreToLoad(page)) {
                scope.launch(Dispatchers.Main) { onPreExecute.invoke() }

                if (started.not()) {
                    started = true
                }

                scope.launch {
                    doInBackground.invoke(filter, page)?.let { result ->

                        total = result.totalInAllPages
                        count += result.data.size

                        onPostExecute.invoke(result.data, result)
                        handleEmptyData.invoke(total == 0)

                        onLoaded(total, result.data)
                        lastFilter = filter
                    }
                }
            }
        }

        private fun hasMoreToLoad(page: Int): Boolean {
            val isValidPage = page >= INDEX_OF_FIRST_PAGE
            val firstCase = started.not() && total == 0 && count == 0
            val hasMore = count < total || firstCase
            val hasChangedFilter = lastFilter.isNullOrBlank() || lastFilter != filter
            return isValidPage && hasMore && hasChangedFilter
        }
    }

    companion object {
        private const val INDEX_OF_FIRST_PAGE: Int = 0
        private const val COUNT_OF_ITEMS_IN_PAGE: Int = 10
    }
}
