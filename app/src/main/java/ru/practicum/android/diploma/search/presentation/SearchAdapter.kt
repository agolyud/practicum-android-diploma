package ru.practicum.android.diploma.search.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.ItemViewBinding
import ru.practicum.android.diploma.search.domain.models.Vacancy

class SearchAdapter(val onVacancyClickedCB: (String) -> Unit) : RecyclerView.Adapter<SearchViewHolder>() {

    var vacancyList = mutableListOf<Vacancy>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SearchViewHolder(
            ItemViewBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = vacancyList.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(vacancyList[position])
        holder.itemView.setOnClickListener {
            onVacancyClickedCB(vacancyList[position].id)
        }
    }
}
