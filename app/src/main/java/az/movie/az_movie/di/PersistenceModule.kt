package az.movie.az_movie.di

import android.content.Context
import az.movie.az_movie.data.local.PrefsManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context): PrefsManager {
        return PrefsManager(context)
    }

}