package ru.practicum.android.diploma.util.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.practicum.android.diploma.util.storage.db.dao.FavoriteDao
import ru.practicum.android.diploma.util.storage.db.entity.FavoriteEntity

@Database(
    version = 1,
    entities = [FavoriteEntity::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
