package ru.practicum.android.diploma.favorite.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.models.Vacancy

interface FavoriteRepository {
    suspend fun addFavorite(favorite: DetailVacancy)
    suspend fun deleteFavorite(id: String)
    fun getFavorites(): Flow<List<Vacancy>>
    fun getFavorite(id: String): Flow<DetailVacancy>
}
