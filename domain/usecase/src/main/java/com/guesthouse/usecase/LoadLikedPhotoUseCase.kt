package com.guesthouse.usecase

import com.guesthouse.repository.UnSplashRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadLikedPhotoUseCase @Inject constructor(
    private val unSplashRepository: UnSplashRepository
){

    operator fun invoke() = unSplashRepository.getLikedPhotos()

}