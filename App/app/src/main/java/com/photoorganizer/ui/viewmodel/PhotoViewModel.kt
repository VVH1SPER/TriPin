package com.photoorganizer.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.photoorganizer.data.repository.PhotoRepository
import com.photoorganizer.model.FolderEntity
import com.photoorganizer.model.PhotoEntity
import com.photoorganizer.model.TagEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PhotoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = PhotoRepository.getInstance(application)

    val folders: StateFlow<List<FolderEntity>> = repository.observeFolders()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val photos: StateFlow<List<PhotoEntity>> = repository.observePhotos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val tags: StateFlow<List<TagEntity>> = repository.observeTags()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    init {
        viewModelScope.launch {
            repository.ensureSystemFolder()
        }
    }

    fun createFolder(name: String, parentId: Long? = null) {
        viewModelScope.launch {
            repository.createFolder(name, parentId)
        }
    }

    fun renameFolder(folderId: Long, newName: String) {
        viewModelScope.launch {
            repository.renameFolder(folderId, newName)
        }
    }

    fun deleteFolder(folderId: Long) {
        viewModelScope.launch {
            repository.deleteFolder(folderId)
        }
    }

    fun createTag(name: String) {
        viewModelScope.launch {
            repository.createTag(name)
        }
    }

    fun renameTag(tagId: Long, newName: String) {
        viewModelScope.launch {
            repository.renameTag(tagId, newName)
        }
    }

    fun deleteTag(tagId: Long) {
        viewModelScope.launch {
            repository.deleteTag(tagId)
        }
    }

    fun savePhoto(
        sourcePath: String,
        folderId: Long,
        tagIds: List<Long>,
        createdAt: Long = System.currentTimeMillis(),
        latitude: Double? = null,
        longitude: Double? = null
    ) {
        viewModelScope.launch {
            repository.savePhoto(
                sourcePath = sourcePath,
                folderId = folderId,
                tagIds = tagIds,
                createdAt = createdAt,
                latitude = latitude,
                longitude = longitude
            )
        }
    }

    fun getPhotoById(photoId: String): StateFlow<PhotoEntity?> {
        return photos
            .map { list -> list.firstOrNull { it.id == photoId } }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = null
            )
    }

    fun deletePhoto(photoId: String) {
        viewModelScope.launch {
            repository.deletePhoto(photoId)
        }
    }

    fun movePhoto(photoId: String, folderId: Long) {
        viewModelScope.launch {
            repository.movePhoto(photoId, folderId)
        }
    }

    fun setPhotoTags(photoId: String, tagIds: List<Long>) {
        viewModelScope.launch {
            repository.setPhotoTags(photoId, tagIds)
        }
    }

    suspend fun getTagsForPhotoSuspend(photoId: String): List<TagEntity> {
        return repository.getTagsForPhoto(photoId)
    }
}
