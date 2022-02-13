package br.com.jogosusados.features.select.view

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import br.com.jogosusados.R
import br.com.jogosusados.databinding.ActivityGameSelectBinding
import br.com.jogosusados.features.search.data.GameItem
import br.com.jogosusados.features.select.GameSelectViewModel
import br.com.jogosusados.features.select.di.GameSelectModules
import br.com.redcode.base.mvvm.extensions.observer
import br.com.redcode.base.mvvm.restful.databinding.domain.ActivityMVVM
import br.com.redcode.easyrecyclerview.library.extension_functions.setCustomAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

class GameSelectActivity : ActivityMVVM<ActivityGameSelectBinding, GameSelectViewModel>() {

    override val classViewModel = GameSelectViewModel::class.java
    override val layout = R.layout.activity_game_select

    private val gameSelectViewModel: GameSelectViewModel by viewModel {
        parametersOf(this)
    }

    private val observer = observer<List<GameItem>> { updateUI(it) }
    private val idPlatform by lazy { intent?.getLongExtra(TAG_ID_PLATFORM, -1) ?: -1L }

    private val adapter = AdapterGameItem { item, _ ->
        val data = Intent()
        data.putExtra(TAG_GAME_ITEM, item)
        setResult(RESULT_OK, data)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(GameSelectModules.instance)
        super.onCreate(savedInstanceState)
    }

    override fun setupLayout() {
        binding = DataBindingUtil.setContentView(this, layout)
        viewModel = gameSelectViewModel
        defineMVVM(this)
        setupUI()

        observeEvents()
        lifecycle.addObserver(viewModel)
    }

    override fun setupUI() {
        super.setupUI()
        viewModel.liveData.observe(this, observer)
    }

    override fun afterOnCreate() {
        enableHomeAsUpActionBar()
        val layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.setCustomAdapter(adapter, layoutManager)
    }

    override fun onResume() {
        super.onResume()
        viewModel.load(idPlatform)
    }

    private fun updateUI(items: List<GameItem>) {
        adapter.setCustomList(items)
        hideProgress()
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(GameSelectModules.instance)
    }

    companion object {
        const val TAG_ID_PLATFORM = "idPlataform"
        const val TAG_GAME_ITEM = "gameITem"
    }

}
