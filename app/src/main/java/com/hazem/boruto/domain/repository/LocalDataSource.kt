package com.hazem.boruto.domain.repository

import com.hazem.boruto.domain.model.Hero

interface LocalDataSource {

    suspend fun getSelectedHero(heroId: Int): Hero
}