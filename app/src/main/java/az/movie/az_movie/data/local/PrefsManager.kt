package az.movie.az_movie.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import az.movie.az_movie.extensions.STRINGS
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class PrefsManager @Inject constructor(@ApplicationContext private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(context.resources.getString(STRINGS.prefs_manager))

    suspend fun setAnotherTimeLaunch() {
        context.dataStore.edit { _dataStore ->
            _dataStore[IS_FIRST_TIME_LAUNCH] = false
        }
    }

    suspend fun isFirstTimeLaunch(): Boolean {
        val preferences = context.dataStore.data.first()
        return preferences[IS_FIRST_TIME_LAUNCH] ?: true
    }

    companion object {
        val IS_FIRST_TIME_LAUNCH = booleanPreferencesKey("IS_FIRST_TIME_LAUNCH")
    }

}