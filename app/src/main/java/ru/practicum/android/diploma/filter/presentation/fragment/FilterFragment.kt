package ru.practicum.android.diploma.filter.presentation.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFilterBinding
import ru.practicum.android.diploma.filter.presentation.states.FilterStates
import ru.practicum.android.diploma.filter.presentation.viewmodel.FilterViewModel
import ru.practicum.android.diploma.search.presentation.SearchFragment

class FilterFragment : Fragment(R.layout.fragment_filter) {
    private lateinit var binding: FragmentFilterBinding
    private val viewModel: FilterViewModel by viewModel()
    private var hasFilterSettings = false

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getState().observe(viewLifecycleOwner) {
            when (it) {
                is FilterStates.HasFilters -> {
                    hasFilterSettings = it.hasFilterSettings
                    binding.apply {
                        salaryEditText.setText(it.salary)
                        filterCheckbox.isChecked = it.onlyWithSalary
                    }

                    if (it.country.name.isNotEmpty()) {
                        binding.apply {
                            placeOfWorkEditText.setText(it.country.name)
                            placeOfWorkButton.visibility = GONE
                            placeOfWorkClear.visibility = VISIBLE
                        }
                    } else {
                        binding.apply {
                            placeOfWorkEditText.setText("")
                            placeOfWorkButton.visibility = VISIBLE
                            placeOfWorkClear.visibility = GONE
                        }
                    }
                    setVisibilityForChooseBtn()

                    if (it.country.name.isNotEmpty() && it.region.name.isNotEmpty() ) {
                        binding.apply {
                            placeOfWorkEditText.setText("${it.country.name}, ${it.region.name}")
                            placeOfWorkButton.visibility = GONE
                            placeOfWorkClear.visibility = VISIBLE
                        }
                    }
                    setVisibilityForChooseBtn()
                    if (it.industry.name.isNotEmpty()) {
                        binding.apply {
                            industryEditText.setText(it.industry.name)
                            industryButton.visibility = GONE
                            industryClear.visibility = VISIBLE
                        }
                    } else {
                        binding.apply {
                            industryEditText.setText("")
                            industryButton.visibility = VISIBLE
                            industryClear.visibility = GONE
                        }
                    }
                    setVisibilityForChooseBtn()
                }

                FilterStates.ClearSettings -> {
                    binding.apply {
                        placeOfWorkEditText.setText("")
                        industryEditText.setText("")
                        placeOfWorkClear.visibility = GONE
                        placeOfWorkButton.visibility = VISIBLE
                        industryClear.visibility = GONE
                        industryButton.visibility = VISIBLE
                        salaryEditText.setText("")
                        filterCheckbox.isChecked = false
                    }
                }

                FilterStates.SaveSettings -> {
                    findNavController().popBackStack()
                }

                FilterStates.DeleteCountryAndRegion -> {
                    binding.apply {
                        placeOfWorkEditText.setText("")
                        placeOfWorkClear.visibility = GONE
                        placeOfWorkButton.visibility = VISIBLE
                    }
                    setVisibilityForChooseBtn()
                    //viewModel.getFilters()
                }

                FilterStates.DeleteIndustry -> {
                    binding.apply {
                        industryEditText.setText("")
                        industryClear.visibility = GONE
                        industryButton.visibility = VISIBLE
                    }
                    setVisibilityForChooseBtn()
                    //viewModel.getFilters()
                }
            }
        }

        initListeners()

        viewModel.getFilters()
    }


    private fun setVisibilityForChooseBtn() {
        if (binding.placeOfWorkEditText.text.toString().isNotEmpty() ||
            binding.industryEditText.text.toString().isNotEmpty() ||
            binding.filterCheckbox.isChecked ||
            binding.salaryEditText.text.toString().isNotEmpty() ||
            hasFilterSettings) {
            binding.apply {
                btnChoose.visibility = View.VISIBLE
                btnRemove.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                btnChoose.visibility = View.GONE
                btnRemove.visibility = View.GONE
            }
        }
    }

    private fun initListeners() {
        binding.placeOfWorkButton.setOnClickListener {
            findNavController().navigate(R.id.action_filterFragment_to_filterPlaceWorkFragment)
            setVisibilityForChooseBtn()
        }

        binding.industryButton.setOnClickListener {
            findNavController().navigate(R.id.action_filterFragment_to_filterIndustryFragment)
            setVisibilityForChooseBtn()
        }

        binding.back.setOnClickListener {
            findNavController().popBackStack()
            setVisibilityForChooseBtn()
        }

        binding.btnChoose.setOnClickListener {
            viewModel.setFilterSettings(binding.salaryEditText.text.toString(), binding.filterCheckbox.isChecked)
        }

        binding.btnRemove.setOnClickListener {
            viewModel.clearFilter()
            setVisibilityForChooseBtn()
        }

        binding.placeOfWorkClear.setOnClickListener {
            viewModel.deleteCountryAndRegionFilter()
            setVisibilityForChooseBtn()
        }

        binding.industryClear.setOnClickListener {
            viewModel.deleteIndustryFilter()
            setVisibilityForChooseBtn()
        }

        binding.filterCheckbox.setOnCheckedChangeListener { _, _ ->
            setVisibilityForChooseBtn()
        }

        binding.salaryEditText.doOnTextChanged { _, _, _, _ ->
            setVisibilityForChooseBtn()
        }

        binding.salaryEditText.addTextChangedListener(tWCreator())

        binding.clearIcon.setOnClickListener {
            binding.clearIcon.visibility = GONE
            binding.salaryEditText.setText("")
        }
    }

    private fun tWCreator() = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (!binding.salaryEditText.text.toString().isNullOrEmpty()) {
                binding.clearIcon.visibility = VISIBLE
            } else {
                binding.clearIcon.visibility = GONE
            }
        }

        override fun afterTextChanged(s: Editable?) {

        }
    }

    companion object {
        const val VISIBLE = View.VISIBLE
        const val GONE = View.GONE
    }
}
