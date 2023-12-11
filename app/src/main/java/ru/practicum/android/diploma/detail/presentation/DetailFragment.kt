package ru.practicum.android.diploma.detail.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentDetailBinding
import ru.practicum.android.diploma.detail.domain.models.DetailVacancy

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val viewModel: DetailViewModel by viewModel()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) { result ->
            render(result)
        }


        binding.back.setOnClickListener {
            view.findNavController().popBackStack()
        }
    }

    private fun render(state: DetailState) {
        when (state) {
            is DetailState.Success -> {
                setData(state.data)
            }

            is DetailState.Error -> {
                showError(state.message)
            }

            is DetailState.Loading -> {
                showProgress()
            }

            is DetailState.NoConnect -> {
                showError(state.message)
            }

            is DetailState.IsFavorite -> {
                setFavorite(state.isFavorite)
            }
        }
    }

    private fun setFavorite(isFavorite: Boolean) {
        // binding.favorite
    }

    @SuppressLint("SetTextI18n")
    private fun setData(detailVacancy: DetailVacancy) {
        binding.vacancyName.text = detailVacancy.name
        binding.salary.text = detailVacancy.salary?.getSalaryToText() ?: getString(R.string.not_salary)

        binding.grExperience.isVisible = detailVacancy.idExperience != null
        binding.experience.text = detailVacancy.nameExperience.orEmpty()

        binding.employment.isVisible = detailVacancy.idEmployment != null
        binding.employment.text = detailVacancy.nameEmployment
        binding.employerName.text = detailVacancy.nameEmployer.orEmpty()
        binding.employerCity.text = detailVacancy.address.orEmpty()

        Glide
            .with(requireContext())
            .load(detailVacancy.logoUrlsEmployerOriginal)
            .placeholder(R.drawable.ic_logo)
            .into(binding.employerLogo)

        if (detailVacancy.description != null && detailVacancy.description != "...") {
            binding.grDescription.isVisible = true
            binding.description.text =
                Html.fromHtml(detailVacancy.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
        } else {
            binding.grDescription.isVisible = false
        }

        binding.grSkills.isVisible = !detailVacancy.keySkills.isNullOrEmpty()
        binding.keySkillsContent.text = detailVacancy.keySkills.orEmpty()

        binding.contacts.isVisible = !(detailVacancy.comment == null
            && detailVacancy.phone == null
            && detailVacancy.email == null
            && detailVacancy.contactName == null)

        binding.grComment.isVisible = !detailVacancy.comment.isNullOrEmpty()
        binding.comment.text = detailVacancy.comment.orEmpty()

        binding.grContact.isVisible = !detailVacancy.contactName.isNullOrEmpty()
        binding.contactName.text = detailVacancy.contactName.orEmpty()

        binding.grPhone.isVisible = !detailVacancy.phone.isNullOrEmpty()
        binding.phone.text = detailVacancy.phone.orEmpty()

        binding.grEmail.isVisible = !detailVacancy.email.isNullOrEmpty()
        binding.email.text = detailVacancy.email.orEmpty()

        setDetailsContentListeners(detailVacancy)

        binding.progress.isVisible = false
        binding.scroll.isVisible = true
        binding.errorPlaceholder.root.isVisible =  false
        // binding.btSimilar.isVisible = !fromDB
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun setDetailsContentListeners(detailVacancy: DetailVacancy) {
        binding.email.setOnClickListener {
            actionSendEmail(detailVacancy.email)
        }

        binding.phone.setOnClickListener {
            actionDial(detailVacancy.phone)
        }

        binding.btSimilar.setOnClickListener {
            //viewModel.showSimilarVacancies(detailVacancy.id)
        }

        binding.share.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, detailVacancy.url)
            val chooserIntent = Intent.createChooser(intent, "Поделиться")
            if (context?.let { it1 -> intent.resolveActivity(it1.packageManager) } != null) {
                startActivity(chooserIntent)
            }
        }

        binding.favorite.setOnClickListener {
            // viewModel.onFavoriteClick()
        }
    }

    private fun actionSendEmail(email: String?) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:${email}")
        try {
            startActivity(intent)
        } catch (_: Exception) {
        }
    }

    private fun actionDial(phoneNumber: String?) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${phoneNumber}"))
        try {
            startActivity(intent)
        } catch (_: Exception) {
        }
    }

    private fun showProgress() {
        binding.progress.isVisible = true
        binding.scroll.isVisible = false
        binding.errorPlaceholder.root.isVisible =  false
    }

    private fun showError(
        message: String,
    ) {
        with(binding.errorPlaceholder) {
            tvError.text = message
        }
        binding.errorPlaceholder.root.isVisible = true
        binding.progress.isVisible = false
        binding.scroll.isVisible = false
    }

}
