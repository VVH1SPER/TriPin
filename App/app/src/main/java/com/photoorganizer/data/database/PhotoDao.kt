package com.photoorganizer.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.photoorganizer.model.PhotoEntity
import com.photoorganizer.model.PhotoTagCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photos ORDER BY createdAt DESC")
    fun getAllPhotos(): Flow<List<PhotoEntity>>

    @Query("SELECT * FROM photos WHERE folderId = :folderId ORDER BY createdAt DESC")
    fun getPhotosByFolder(folderId: Long): Flow<List<PhotoEntity>>

    @Query("SELECT * FROM photos WHERE id = :photoId")
    suspend fun getPhotoById(photoId: String): PhotoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(photo: PhotoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotoTags(photoTags: List<PhotoTagCrossRef>)

    @Query("DELETE FROM photo_tags WHERE photoId = :photoId")
    suspend fun clearPhotoTags(photoId: String)

    @Query("SELECT * FROM photo_tags WHERE photoId = :photoId")
    suspend fun getPhotoTagRelations(photoId: String): List<PhotoTagCrossRef>

    @Query("UPDATE photos SET folderId = :folderId WHERE id = :photoId")
    suspend fun movePhotoToFolder(photoId: String, folderId: Long)

    @Query("DELETE FROM photos WHERE id = :photoId")
    suspend fun deletePhotoById(photoId: String)
}

