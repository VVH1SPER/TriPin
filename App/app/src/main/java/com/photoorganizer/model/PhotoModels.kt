package com.photoorganizer.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "folders")
data class FolderEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val parentId: Long? = null,
    val isSystem: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "tags")
data class TagEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val normalizedName: String,
    val color: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "photos",
    indices = [Index(value = ["folderId"]), Index(value = ["createdAt"])],
    foreignKeys = [
        ForeignKey(
            entity = FolderEntity::class,
            parentColumns = ["id"],
            childColumns = ["folderId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PhotoEntity(
    @PrimaryKey val id: String = "",
    val fileName: String,
    val folderId: Long,
    val createdAt: Long,
    val savedAt: Long = System.currentTimeMillis(),
    val sizeBytes: Long,
    val width: Int,
    val height: Int,
    val mimeType: String = "image/jpeg",
    val latitude: Double? = null,
    val longitude: Double? = null
)

@Entity(
    tableName = "photo_tags",
    primaryKeys = ["photoId", "tagId"],
    indices = [Index(value = ["tagId"])],
    foreignKeys = [
        ForeignKey(
            entity = PhotoEntity::class,
            parentColumns = ["id"],
            childColumns = ["photoId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = TagEntity::class,
            parentColumns = ["id"],
            childColumns = ["tagId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PhotoTagCrossRef(
    val photoId: String,
    val tagId: Long
)

data class PhotoWithTags(
    val photo: PhotoEntity,
    val tags: List<TagEntity>
)
