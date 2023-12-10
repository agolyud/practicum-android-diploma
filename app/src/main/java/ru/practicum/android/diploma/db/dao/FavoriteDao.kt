package ru.practicum.android.diploma.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: Any)

    @Query("DELETE FROM favorite_table WHERE id LIKE :id")
    suspend fun deleteFavorite(id: String)

    @Query("SELECT * FROM favorite_table")
    suspend fun getFavorites(): List<Any>

    @Query("SELECT * FROM favorite_table WHERE id LIKE :id")
    suspend fun getFavorite(id: String): Any
}
