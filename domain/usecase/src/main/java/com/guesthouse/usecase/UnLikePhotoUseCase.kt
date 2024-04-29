package com.guesthouse.usecase

import com.guesthouse.repository.UnSplashRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnLikePhotoUseCase @Inject constructor(
   private val unSplashRepository: UnSplashRepository
) {
    operator fun invoke(id: String): Flow<Unit> = unSplashRepository.deleteUnLikePhoto(id)
}