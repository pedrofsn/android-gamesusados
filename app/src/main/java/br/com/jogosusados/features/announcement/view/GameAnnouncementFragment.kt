package br.com.jogosusados.features.announcement.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import br.com.jogosusados.R
import br.com.jogosusados.databinding.FragmentGameAnnouncementBinding
import br.com.jogosusados.domain.getLong
import br.com.jogosusados.features.announcement.GameAnnouncementViewModel
import br.com.jogosusados.features.announcement.di.GameAnnouncementModules
import br.com.jogosusados.features.announcement.repository.DetailGameAnnouncement
import br.com.redcode.base.mvvm.extensions.observer
import br.com.redcode.base.mvvm.restful.databinding.impl.FragmentMVVMDataBinding
import br.com.redcode.easyglide.library.load
import br.com.redcode.easyrecyclerview.library.extension_functions.setCustomAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

class GameAnnouncementFragment :
    FragmentMVVMDataBinding<FragmentGameAnnouncementBinding, GameAnnouncementViewModel>() {

    override val classViewModel = GameAnnouncementViewModel::class.java
    override val layout = R.layout.fragment_game_announcement

    private val gameAnnouncementViewModel: GameAnnouncementViewModel by viewModel {
        parametersOf(requireActivity())
    }

    private val id by lazy { getLong("id") }

    private val observer = observer<DetailGameAnnouncement> { updateUI(it) }

    private val adapter = AdapterAnnouncement { item, position ->

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(GameAnnouncementModules.instance)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        viewModel = gameAnnouncementViewModel
        defineMVVM(this)
        return binding.root
    }

    override fun setupUI() {
        super.setupUI()
        viewModel.liveData.observe(this, observer)
    }

    override fun afterOnCreate() {
        binding.toolbar.setNavigationIcon(android.R.drawable.ic_menu_close_clear_cancel)
        binding.toolbar.setNavigationOnClickListener { closeScreen() }
        binding.recyclerView.setCustomAdapter(adapter)
        viewModel.load(id)
    }

    private fun closeScreen() {
        findNavController().popBackStack()
    }

    private fun updateUI(data: DetailGameAnnouncement) {
        adapter.setCustomList(data.announcements)
        binding.imageView.load(data.game.image)
        hideProgress()
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(GameAnnouncementModules.instance)
    }
}
