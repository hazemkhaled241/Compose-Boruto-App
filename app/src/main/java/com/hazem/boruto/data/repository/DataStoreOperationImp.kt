package com.hazem.boruto.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.hazem.boruto.domain.repository.DataStoreOperation
import com.hazem.boruto.utils.Constants.PREFERENCES_KEY
import com.hazem.boruto.utils.Constants.PREFERENCES_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class DataStoreOperationImp(context: Context) : DataStoreOperation {

    private object PreferenceKey {
        val onBoardingKey = booleanPreferencesKey(name = PREFERENCES_KEY)
    }

    private val dataStore = context.dataStore
    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preference ->
            preference[PreferenceKey.onBoardingKey] = completed
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preference ->
            val onBoardingState = preference[PreferenceKey.onBoardingKey] ?: false
            onBoardingState
        }
    }


}