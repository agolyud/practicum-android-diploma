package ru.practicum.android.diploma.favorite.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.ItemViewBinding
import ru.practicum.android.diploma.search.domain.models.Vacancy

class FavoriteAdapter(val onVacancyClickedCB: (String) -> Unit) : RecyclerView.Adapter<FavoriteViewHolder>() {

    var vacancyList = mutableListOf<Vacancy>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FavoriteViewHolder(
            ItemViewBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = vacancyList.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(vacancyList[position])
        holder.itemView.setOnClickListener {
            onVacancyClickedCB(vacancyList[position].id)
        }
    }
}
