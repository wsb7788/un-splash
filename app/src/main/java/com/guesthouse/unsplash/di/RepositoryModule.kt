package com.guesthouse.unsplash.di

import com.guesthouse.repository.UnSplashRepository
import com.guesthouse.repository.UnSplashRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindUnSplashRepository(unSplashRepositoryImpl: UnSplashRepositoryImpl): UnSplashRepository

}
