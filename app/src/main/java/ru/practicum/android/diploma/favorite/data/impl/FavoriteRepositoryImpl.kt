package ru.practicum.android.diploma.favorite.data.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.db.AppDatabase
import ru.practicum.android.diploma.db.converter.FavoriteDbConverter
import ru.practicum.android.diploma.detail.domain.models.DetailVacancy
import ru.practicum.android.diploma.favorite.domain.api.FavoriteRepository
import ru.practicum.android.diploma.search.domain.models.Vacancy

class FavoriteRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val favoriteDbConverter: FavoriteDbConverter,
) : FavoriteRepository {
    override suspend fun addFavorite(favorite: DetailVacancy) {
        appDatabase.favoriteDao().addFavorite(favoriteDbConverter.map(favorite))
    }

    override suspend fun deleteFavorite(id: String) {
        appDatabase.favoriteDao().deleteFavorite(id)
    }

    override fun getFavorites(): Flow<List<Vacancy>> = flow {
        val favorites = appDatabase.favoriteDao().getFavorites()
        emit(favorites.map {
            favorite -> favoriteDbConverter.map2(favorite)
        })
    }

    override fun getFavorite(id: String): Flow<DetailVacancy> = flow {
        val favorite = appDatabase.favoriteDao().getFavorite(id)
        emit(favoriteDbConverter.map(favorite))
    }
}
