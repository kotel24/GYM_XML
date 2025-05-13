package ru.mygames.gym_xml

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideTestingDatabase(@ApplicationContext context: Context): AppDataBase =
        AppDataBase.getDatabase(context)

    @Provides
    @Singleton
    fun provideDao(database: AppDataBase): FavoriteDao {
        return database.projectDAO()
    }
}