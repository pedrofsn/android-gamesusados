package br.com.jogosusados.features.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import br.com.jogosusados.R
import br.com.jogosusados.databinding.FragmentAddBinding
import br.com.jogosusados.domain.addSafeMarginInPrefix
import br.com.jogosusados.domain.getSelectedChip
import br.com.jogosusados.features.add.data.GameAnnouncement
import br.com.jogosusados.features.add.data.IdWithTitle
import br.com.jogosusados.features.add.di.AddModules
import br.com.jogosusados.features.games.list.GameItem
import br.com.redcode.base.mvvm.extensions.observer
import br.com.redcode.base.mvvm.restful.databinding.impl.FragmentMVVMDataBinding
import br.com.redcode.easymask.handleMoney
import br.com.redcode.easyvalidation.Validate
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

class AddFragment : FragmentMVVMDataBinding<FragmentAddBinding, AddViewModel>() {

    override val classViewModel = AddViewModel::class.java
    override val layout = R.layout.fragment_add

    private val addViewModel: AddViewModel by viewModel {
        parametersOf(this@AddFragment.requireActivity())
    }

    private val observer = observer<LabelAddGame> { updateUI(it) }

    private lateinit var onGameSelected: ActivityResultLauncher<Long>

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(AddModules.instance)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        viewModel = addViewModel
        defineMVVM(this)
        return binding.root
    }

    override fun setupUI() {
        super.setupUI()
        viewModel.liveData.observe(this, observer)
    }

    override fun afterOnCreate() {
        registerCallbackToSelectGame()
        setupListeners()
        binding.textInputEditText.handleMoney(hasSymbol = false)
        binding.textInputLayout.addSafeMarginInPrefix(requireActivity() as AppCompatActivity)
        viewModel.load()
    }

    private fun registerCallbackToSelectGame() {
        onGameSelected = registerForActivityResult(SelectGameContract()) { gameItem: GameItem? ->
            viewModel.updateGame(gameItem)
        }
    }

    private fun setupListeners() {
        binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
            val idPlatform = binding.chipGroup.getSelectedChip(checkedId).tag.toString().toLong()
            viewModel.onPlatformSelected(idPlatform)
        }

        binding.textViewAdd.setOnClickListener {
            openScreenToSelectGame()
            binding.textViewAction.setOnClickListener { openScreenToSelectGame() }
        }
    }

    private fun openScreenToSelectGame() {
        val idPlatform = viewModel.idPlataform.value
        onGameSelected.launch(idPlatform)
    }

    private fun updateUI(labelAddGame: LabelAddGame) {
        binding.chipGroup.removeAllViews()
        labelAddGame.platforms.forEach { addChip(it) }
    }

    private fun addChip(idWithTitle: IdWithTitle) {
        val styleChoice = R.style.Widget_MaterialComponents_Chip_Choice
        Chip(requireContext()).apply {
            val chipDrawable = ChipDrawable.createFromAttributes(context, null, 0, styleChoice)
            setChipDrawable(chipDrawable)
            this.text = idWithTitle.title
            this.tag = idWithTitle.id
            binding.chipGroup.addView(this)
        }
    }

    override fun handleEvent(event: String, obj: Any?) {
        when (event) {
            "requireValue" -> requireValue()
            "clearErrorValue" -> clearErrorValue()
            "onGameAnnouncementSaved" -> onGameAnnouncementSaved(obj)
            else -> super.handleEvent(event, obj)
        }
    }

    private fun onGameAnnouncementSaved(obj: Any?) {
        if(obj != null && obj is GameAnnouncement) {
            findNavController().navigate(R.id.myFragment)
        }
    }

    private fun requireValue() {
        Validate.isFilled(binding.textInputEditText)
    }

    private fun clearErrorValue() {
        binding.textInputLayout.error = null
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(AddModules.instance)
    }
}
