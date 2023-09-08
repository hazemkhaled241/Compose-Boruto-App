package com.hazem.boruto.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hazem.boruto.utils.Constants.HERO_REMOTE_KEY_DATA_BASE_TABLE

@Entity(tableName = HERO_REMOTE_KEY_DATA_BASE_TABLE)
data class HeroRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage:Int?
)
