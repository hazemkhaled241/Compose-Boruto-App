package com.hazem.boruto.data.repository

import com.hazem.boruto.data.local.HeroDataBase
import com.hazem.boruto.domain.model.Hero
import com.hazem.boruto.domain.repository.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(
    private val heroDataBase: HeroDataBase
) : LocalDataSource {
    private val heroDao = heroDataBase.heroDao()
    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDao.getSelectedHero(heroId=heroId)
    }
}