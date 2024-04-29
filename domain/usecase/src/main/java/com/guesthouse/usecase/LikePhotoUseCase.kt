package com.guesthouse.usecase

import com.guesthouse.entity.Photo
import com.guesthouse.repository.UnSplashRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LikePhotoUseCase @Inject constructor(
    private val unSplashRepository: UnSplashRepository
) {

    operator fun invoke(photo: Photo) = unSplashRepository.updatePhotoLiked(photo)

}