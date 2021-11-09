package br.com.jogosusados.features.games

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.jogosusados.R
import br.com.jogosusados.databinding.FragmentGamesBinding
import br.com.redcode.base.mvvm.extensions.observer
import br.com.redcode.base.mvvm.restful.databinding.impl.FragmentMVVMDataBinding
import br.com.redcode.easyrecyclerview.library.extension_functions.setCustomAdapter

class GamesFragment : FragmentMVVMDataBinding<FragmentGamesBinding, GamesViewModel>() {

    override val classViewModel = GamesViewModel::class.java
    override val layout = R.layout.fragment_games

    private val observer = observer<LabelGames> { updateUI(it) }

    private val adapter = AdapterGameItem { item, position ->

    }

    override fun setupUI() {
        super.setupUI()
        viewModel.liveData.observe(this, observer)
    }

    override fun afterOnCreate() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.setCustomAdapter(adapter, layoutManager)
    }

    override fun onResume() {
        super.onResume()
        viewModel.load()
    }

    private fun updateUI(data: LabelGames) {
        adapter.setCustomList(data.games)
        hideProgress()
    }

}
