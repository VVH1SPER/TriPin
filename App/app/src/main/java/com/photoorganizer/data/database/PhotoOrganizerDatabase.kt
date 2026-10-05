package com.photoorganizer.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.photoorganizer.model.FolderEntity
import com.photoorganizer.model.PhotoEntity
import com.photoorganizer.model.PhotoTagCrossRef
import com.photoorganizer.model.TagEntity

@Database(
    entities = [FolderEntity::class, PhotoEntity::class, TagEntity::class, PhotoTagCrossRef::class],
    version = 1,
    exportSchema = false
)
abstract class PhotoOrganizerDatabase : RoomDatabase() {
    abstract fun folderDao(): FolderDao
    abstract fun photoDao(): PhotoDao
    abstract fun tagDao(): TagDao

    companion object {
        @Volatile
        private var INSTANCE: PhotoOrganizerDatabase? = null

        fun getDatabase(context: Context): PhotoOrganizerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhotoOrganizerDatabase::class.java,
                    "photo_organizer.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}
