package com.hazem.boruto.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hazem.boruto.data.local.dao.HeroDao
import com.hazem.boruto.data.local.dao.HeroRemoteKeyDao
import com.hazem.boruto.domain.model.Hero
import com.hazem.boruto.domain.model.HeroRemoteKey

@Database(entities = [Hero::class,HeroRemoteKey::class], version = 1)
@TypeConverters(DataBaseConverter::class)
abstract class HeroDataBase : RoomDatabase() {
    
    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao
}