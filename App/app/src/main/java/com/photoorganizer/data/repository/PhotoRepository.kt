package com.photoorganizer.data.repository

import android.content.Context
import com.photoorganizer.data.database.PhotoOrganizerDatabase
import com.photoorganizer.model.FolderEntity
import com.photoorganizer.model.PhotoEntity
import com.photoorganizer.model.PhotoTagCrossRef
import com.photoorganizer.model.TagEntity
import kotlinx.coroutines.flow.Flow
import java.io.File
import java.util.UUID

class PhotoRepository private constructor(
    private val context: Context,
    private val database: PhotoOrganizerDatabase
) {
    private val folderDao = database.folderDao()
    private val photoDao = database.photoDao()
    private val tagDao = database.tagDao()

    fun observeFolders(): Flow<List<FolderEntity>> = folderDao.getAllFolders()
    fun observePhotos(): Flow<List<PhotoEntity>> = photoDao.getAllPhotos()
    fun observeTags(): Flow<List<TagEntity>> = tagDao.getAllTags()

    suspend fun ensureSystemFolder(): FolderEntity {
        val systemFolders = folderDao.getSystemFolders()
        if (systemFolders.isNotEmpty()) {
            return systemFolders.first()
        }

        val folder = FolderEntity(
            name = "Uncategorized",
            parentId = null,
            isSystem = true
        )
        val id = folderDao.insertFolder(folder)
        return folderDao.getFolderById(id) ?: folder
    }

    suspend fun createFolder(name: String, parentId: Long? = null): FolderEntity? {
        val normalized = name.trim()
        if (normalized.isEmpty()) return null

        val folder = FolderEntity(
            name = normalized,
            parentId = parentId,
            isSystem = false
        )
        val id = folderDao.insertFolder(folder)
        return folderDao.getFolderById(id)
    }

    suspend fun renameFolder(folderId: Long, newName: String) {
        val normalized = newName.trim()
        if (normalized.isNotEmpty()) {
            folderDao.renameFolder(folderId, normalized)
        }
    }

    suspend fun deleteFolder(folderId: Long) {
        folderDao.deleteFolder(folderId)
    }

    suspend fun createTag(name: String): TagEntity? {
        val normalized = name.trim()
        if (normalized.isEmpty()) return null

        val tag = TagEntity(
            name = normalized,
            normalizedName = normalized.lowercase()
        )
        val id = tagDao.insertTag(tag)
        return tagDao.getTagById(id)
    }

    suspend fun renameTag(tagId: Long, newName: String) {
        val normalized = newName.trim()
        if (normalized.isNotEmpty()) {
            tagDao.renameTag(tagId, normalized, normalized.lowercase())
        }
    }

    suspend fun deleteTag(tagId: Long) {
        tagDao.clearTagRelations(tagId)
        tagDao.deleteTag(tagId)
    }

    suspend fun savePhoto(
        sourcePath: String,
        folderId: Long,
        tagIds: List<Long>,
        createdAt: Long = System.currentTimeMillis(),
        latitude: Double? = null,
        longitude: Double? = null
    ): PhotoEntity? {
        val sourceFile = File(sourcePath)
        if (!sourceFile.exists()) return null

        val validFolderId = if (folderId <= 0L) {
            ensureSystemFolder().id
        } else {
            folderId
        }

        val targetDir = File(context.filesDir, "photos").apply { mkdirs() }
        val targetFile = File(targetDir, "${UUID.randomUUID()}.jpg")
        sourceFile.copyTo(targetFile, overwrite = false)

        val photo = PhotoEntity(
            id = UUID.randomUUID().toString(),
            fileName = targetFile.name,
            folderId = validFolderId,
            createdAt = createdAt,
            savedAt = System.currentTimeMillis(),
            sizeBytes = targetFile.length(),
            width = 0,
            height = 0,
            mimeType = "image/jpeg",
            latitude = latitude,
            longitude = longitude
        )

        photoDao.insertPhoto(photo)
        if (tagIds.isNotEmpty()) {
            photoDao.insertPhotoTags(tagIds.map { PhotoTagCrossRef(photo.id, it) })
        }

        return photo
    }

    suspend fun getPhotoById(photoId: String): PhotoEntity? = photoDao.getPhotoById(photoId)

    suspend fun getTagsForPhoto(photoId: String): List<TagEntity> {
        val relations = photoDao.getPhotoTagRelations(photoId)
        if (relations.isEmpty()) return emptyList()
        return tagDao.getTagsByIds(relations.map { it.tagId })
    }

    suspend fun setPhotoTags(photoId: String, tagIds: List<Long>) {
        photoDao.clearPhotoTags(photoId)
        if (tagIds.isEmpty()) return
        photoDao.insertPhotoTags(tagIds.map { PhotoTagCrossRef(photoId, it) })
    }

    suspend fun movePhoto(photoId: String, folderId: Long) {
        photoDao.movePhotoToFolder(photoId, folderId)
    }

    suspend fun deletePhoto(photoId: String) {
        photoDao.clearPhotoTags(photoId)
        photoDao.deletePhotoById(photoId)
    }

    suspend fun getFolderById(folderId: Long): FolderEntity? = folderDao.getFolderById(folderId)

    fun getPhotoFile(photo: PhotoEntity): File = File(context.filesDir, "photos/${photo.fileName}")

    companion object {
        @Volatile
        private var INSTANCE: PhotoRepository? = null

        fun getInstance(context: Context): PhotoRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = PhotoRepository(
                    context = context.applicationContext,
                    database = PhotoOrganizerDatabase.getDatabase(context.applicationContext)
                )
                INSTANCE = instance
                instance
            }
        }
    }
}
