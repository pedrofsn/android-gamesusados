package br.com.jogosusados.features.my.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import br.com.jogosusados.R
import br.com.jogosusados.databinding.FragmentMyGamesAnnouncementsBinding
import br.com.jogosusados.features.my.MyGamesAnnouncementsViewModel
import br.com.jogosusados.features.my.data.LabelMyGamesAnnouncements
import br.com.jogosusados.features.my.di.MyGamesModules
import br.com.redcode.base.mvvm.extensions.observer
import br.com.redcode.base.mvvm.restful.databinding.impl.FragmentMVVMDataBinding
import br.com.redcode.easyrecyclerview.library.extension_functions.setCustomAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

class MyGamesAnnouncementsFragment :
    FragmentMVVMDataBinding<FragmentMyGamesAnnouncementsBinding, MyGamesAnnouncementsViewModel>() {

    override val classViewModel = MyGamesAnnouncementsViewModel::class.java
    override val layout = R.layout.fragment_my_games_announcements

    private val myGamesAnnouncementsViewModel: MyGamesAnnouncementsViewModel by viewModel {
        parametersOf(this@MyGamesAnnouncementsFragment.requireActivity())
    }

    private val observer = observer<LabelMyGamesAnnouncements> { updateUI(it) }

    private val adapter = MyGamesAnnouncementAdapter { item, position ->
        toast(item.owner.name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(MyGamesModules.instance)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        viewModel = myGamesAnnouncementsViewModel
        defineMVVM(this)
        return binding.root
    }

    override fun setupUI() {
        super.setupUI()
        viewModel.liveData.observe(this, observer)
    }

    override fun afterOnCreate() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.setCustomAdapter(adapter, layoutManager)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.load()
    }

    private fun updateUI(label: LabelMyGamesAnnouncements) {
        adapter.setCustomList(label.games)
        hideProgress()
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(MyGamesModules.instance)
    }

}
