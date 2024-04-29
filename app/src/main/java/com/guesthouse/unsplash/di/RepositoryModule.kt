package com.guesthouse.unsplash.di

import com.guesthouse.local.LocalDataSource
import com.guesthouse.remote.RemoteDataSource
import com.guesthouse.repository.UnSplashRepository
import com.guesthouse.repository.UnSplashRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUnSplashRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
    ) = UnSplashRepositoryImpl(remoteDataSource, localDataSource)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryImplModule {

    @Binds
    abstract fun bindUnSplashRepository(unSplashRepositoryImpl: UnSplashRepositoryImpl): UnSplashRepository

}
