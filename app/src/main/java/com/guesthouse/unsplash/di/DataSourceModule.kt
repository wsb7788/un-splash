package com.guesthouse.unsplash.di

import android.content.Context
import androidx.room.Room
import com.guesthouse.local.dao.LikedPhotoDao
import com.guesthouse.local.dao.PhotoDao
import com.guesthouse.unsplash.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java, "un_splash_database"
        ).build()

    @Singleton
    @Provides
    fun providePhotoDao(appDatabase: AppDatabase): PhotoDao =
        appDatabase.getPhotoDao()

    @Singleton
    @Provides
    fun provideLikedPhotoDao(appDatabase: AppDatabase): LikedPhotoDao =
        appDatabase.getLikedPhotoDao()

}