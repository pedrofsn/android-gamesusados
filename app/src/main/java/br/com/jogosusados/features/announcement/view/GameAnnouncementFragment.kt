package br.com.jogosusados.features.announcement.view


import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import br.com.jogosusados.R
import br.com.jogosusados.databinding.FragmentGameAnnouncementBinding
import br.com.jogosusados.domain.getLong
import br.com.jogosusados.features.announcement.GameAnnouncementViewModel
import br.com.jogosusados.features.announcement.data.Announcement
import br.com.jogosusados.features.announcement.di.GameAnnouncementModules
import br.com.jogosusados.features.announcement.repository.DetailGameAnnouncement
import br.com.jogosusados.features.search.data.GameItem
import br.com.jogosusados.features.toreport.ToReport
import br.com.jogosusados.features.toreport.ToReportBottomSheet.Companion.BUNDLE_INPUT
import br.com.jogosusados.features.toreport.ToReportBottomSheet.Companion.REQUEST_KEY_DATA
import br.com.jogosusados.features.toreport.ToReportBottomSheet.Companion.REQUEST_KEY_INPUT
import br.com.redcode.base.mvvm.extensions.observer
import br.com.redcode.base.mvvm.restful.databinding.impl.FragmentMVVMDataBinding
import br.com.redcode.easyglide.library.load
import br.com.redcode.easyrecyclerview.library.extension_functions.setCustomAdapter
import com.google.android.material.snackbar.Snackbar
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

    private val adapter = AdapterAnnouncement { item, _ ->
        toReport(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(GameAnnouncementModules.instance)
        super.onCreate(savedInstanceState)

        setFragmentResultListener(REQUEST_KEY_INPUT) { requestKey: String, bundle: Bundle ->
            bundle.getParcelable<ToReport>(REQUEST_KEY_DATA)?.let { reportData ->
                bundle.getString(BUNDLE_INPUT)?.takeIf { it.isNotBlank() }?.let { input ->
                    viewModel.toReport(reportData, input)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        viewModel = gameAnnouncementViewModel
        defineMVVM(this)
        setupToolbar()
        return binding.root
    }

    private fun setupToolbar() {
        setHasOptionsMenu(true)
        binding.toolbar.apply {
            inflateMenu(R.menu.game_announcement_menu)
            setOnMenuItemClickListener { item: MenuItem ->
                return@setOnMenuItemClickListener when (item.itemId) {
                    R.id.to_report -> {
                        toReportGame()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun toReportGame() {
        viewModel.liveData.value?.game?.let { game -> toReport(game) }
    }

    private fun toReport(parcelable: Parcelable) {
        when (parcelable) {
            is Announcement -> GameAnnouncementFragmentDirections.toReportAnnouncement(parcelable)
            is GameItem -> GameAnnouncementFragmentDirections.toReportGame(parcelable)
            else -> null
        }?.let { directions -> findNavController().navigate(directions) }
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

    override fun handleEvent(event: String, obj: Any?) {
        when {
            "onReported" == event && obj != null && obj is String -> {
                showMessage(
                    message = obj,
                    duration = Snackbar.LENGTH_LONG
                )
            }
            else -> super.handleEvent(event, obj)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(GameAnnouncementModules.instance)
    }
}
