package com.photoorganizer.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.photoorganizer.model.TagEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TagDao {
    @Query("SELECT * FROM tags ORDER BY name ASC")
    fun getAllTags(): Flow<List<TagEntity>>

    @Query("SELECT * FROM tags WHERE id IN (:tagIds) ORDER BY name ASC")
    suspend fun getTagsByIds(tagIds: List<Long>): List<TagEntity>

    @Query("SELECT * FROM tags WHERE id = :tagId")
    suspend fun getTagById(tagId: Long): TagEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTag(tag: TagEntity): Long

    @Query("UPDATE tags SET name = :newName, normalizedName = :normalizedName WHERE id = :tagId")
    suspend fun renameTag(tagId: Long, newName: String, normalizedName: String)

    @Query("DELETE FROM tags WHERE id = :tagId")
    suspend fun deleteTag(tagId: Long)

    @Query("DELETE FROM photo_tags WHERE tagId = :tagId")
    suspend fun clearTagRelations(tagId: Long)
}
