package ru.practicum.android.diploma.detail.presentation.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentDetailBinding
import ru.practicum.android.diploma.detail.domain.models.DetailVacancy
import ru.practicum.android.diploma.detail.presentation.detail.DetailState.Error
import ru.practicum.android.diploma.detail.presentation.detail.DetailState.Loading
import ru.practicum.android.diploma.detail.presentation.detail.DetailState.NoConnect
import ru.practicum.android.diploma.detail.presentation.detail.DetailState.Success
import ru.practicum.android.diploma.detail.presentation.similar.VACANCY

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val viewModel: DetailViewModel by viewModel()

    private val binding get() = _binding!!
    private lateinit var detailVacancy: DetailVacancy

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

        viewModel.stateFavorite.observe(viewLifecycleOwner) { result ->
            setFavorite(result)
        }

        binding.back.setOnClickListener {
            view.findNavController().popBackStack()
        }
    }



    private fun render(state: DetailState) {
        when (state) {
            is Success -> {
                setData(state.data)
                detailVacancy = state.data
            }

            is Error -> {
                showError(
                    message = getString(R.string.server_error),
                    image = R.drawable.error_server_1
                )
            }

            is Loading -> {
                showProgress()
            }

            is NoConnect -> {
                showError(
                    message = getString(R.string.internet_connection_issue),
                    image = R.drawable.error_server_1
                )
            }

        }
    }

    private fun setFavorite(isFavourite: Boolean) {
        when (isFavourite) {
            true -> binding.favorite.setImageResource(R.drawable.favorite)
            else -> binding.favorite.setImageResource(R.drawable.not_favorite2)
        }
    }


    @SuppressLint("SetTextI18n")
    private fun setData(detailVacancy: DetailVacancy) {
        binding.detailPlaceholder.root.isVisible = true
        with(binding.detailPlaceholder) {
            vacancyName.text = detailVacancy.name
            salary.text = detailVacancy.salary?.getSalaryToText() ?: getString(R.string.not_salary)

            grExperience.isVisible = detailVacancy.idExperience != null
            experience.text = detailVacancy.nameExperience.orEmpty()

            employment.isVisible = detailVacancy.idEmployment != null
            employment.text = detailVacancy.nameEmployment
            employerName.text = detailVacancy.nameEmployer.orEmpty()
            employerCity.text = detailVacancy.address.orEmpty()

            Glide
                .with(requireContext())
                .load(detailVacancy.logoUrlsEmployerOriginal)
                .placeholder(R.drawable.ic_logo)
                .into(employerLogo)

            if (detailVacancy.description != null && detailVacancy.description != "...") {
                grDescription.isVisible = true
                description.text =
                    Html.fromHtml(detailVacancy.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
            } else {
                grDescription.isVisible = false
            }

            grSkills.isVisible = !detailVacancy.keySkills.isNullOrEmpty()
            keySkillsContent.text = detailVacancy.keySkills.orEmpty()

            contacts.isVisible = !(detailVacancy.comment == null
                && detailVacancy.phone == null
                && detailVacancy.email == null
                && detailVacancy.contactName == null)

            grComment.isVisible = !detailVacancy.comment.isNullOrEmpty()
            comment.text = detailVacancy.comment.orEmpty()

            grContact.isVisible = !detailVacancy.contactName.isNullOrEmpty()
            contactName.text = detailVacancy.contactName.orEmpty()

            grPhone.isVisible = !detailVacancy.phone.isNullOrEmpty()
            phone.text = detailVacancy.phone.orEmpty()

            grEmail.isVisible = !detailVacancy.email.isNullOrEmpty()
            email.text = detailVacancy.email.orEmpty()
        }

        setDetailsContentListeners(detailVacancy)

        binding.progress.isVisible = false
        //  binding.scroll.isVisible = true
        binding.errorPlaceholder.root.isVisible = false
        // binding.btSimilar.isVisible = !fromDB
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun setDetailsContentListeners(detailVacancy: DetailVacancy) {
        binding.detailPlaceholder.email.setOnClickListener {
            actionSendEmail(detailVacancy.email)
        }

        binding.detailPlaceholder.phone.setOnClickListener {
            actionDial(detailVacancy.phone)
        }

        binding.btSimilar.setOnClickListener {
            val bundle = bundleOf(VACANCY to detailVacancy.id)
            view?.findNavController()
                ?.navigate(R.id.action_detailFragment_to_similarFragment, bundle)
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

            viewModel.onFavoriteClick(detailVacancy, !viewModel.stateFavorite.value!!)
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
        // binding.scroll.isVisible = false
        binding.errorPlaceholder.root.isVisible = false
    }

    private fun showError(
        message: String,
        image: Int
    ) {
        binding.detailPlaceholder.root.isVisible = false
        with(binding.errorPlaceholder) {
            tvError.text = message
            imError.setImageResource(image)
        }
        binding.errorPlaceholder.root.isVisible = true
        binding.progress.isVisible = false
        // binding.scroll.isVisible = false
    }

    companion object {
        private const val ID = "id"
        fun createArgs(id: String): Bundle {
            return bundleOf(ID to id)
        }
    }
}
