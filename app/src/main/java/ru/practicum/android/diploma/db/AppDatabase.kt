package ru.practicum.android.diploma.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.practicum.android.diploma.db.dao.FavoriteDao

@Database(
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
