package br.com.jogosusados.features.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import br.com.jogosusados.R
import br.com.jogosusados.databinding.FragmentSearchBinding
import br.com.jogosusados.features.games.list.GameItem
import br.com.jogosusados.features.search.di.SearchGamesModules
import br.com.redcode.base.extensions.handleEnterKeyboard
import br.com.redcode.base.mvvm.extensions.observer
import br.com.redcode.base.mvvm.restful.databinding.impl.FragmentMVVMDataBinding
import br.com.redcode.easyrecyclerview.library.extension_functions.setCustomAdapter
import br.com.redcode.easyvalidation.extensions.hideKeyboard
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

class SearchFragment : FragmentMVVMDataBinding<FragmentSearchBinding, SearchViewModel>() {

    override val classViewModel = SearchViewModel::class.java
    override val layout = R.layout.fragment_search

    private val searchGamesViewModel: SearchViewModel by viewModel {
        parametersOf(this@SearchFragment.requireActivity())
    }

    private val observer = observer<PagedList<GameItem>> { updateUI(it) }

    private val adapter = AdapterPaginatedGameItem { item, _ ->
        /*   val data = Intent()
           data.putExtra(GameSelectActivity.TAG_GAME_ITEM, item)
           setResult(AppCompatActivity.RESULT_OK, data)
           finish() */
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(SearchGamesModules.instance)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        viewModel = searchGamesViewModel
        defineMVVM(this)
        return binding.root
    }

    override fun setupUI() {
        super.setupUI()
        viewModel.pagination.data.observe(this, observer)
    }

    override fun afterOnCreate() {
        setupRecyclerView()
        setupSearchView()
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.setCustomAdapter(adapter, layoutManager)
    }

    private fun setupSearchView() {
        val editText = binding.searchView.findViewById<EditText>(R.id.search_src_text)
        editText.handleEnterKeyboard {
            searchGamesViewModel.pagination.invalidate()
            editText.hideKeyboard()
        }
    }

    private fun updateUI(items: PagedList<GameItem>) {
        adapter.submitList(items)
        hideProgress()
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(SearchGamesModules.instance)
    }

}


