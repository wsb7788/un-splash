package com.guesthouse.usecase

import com.guesthouse.repository.UnSplashRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchPhotoUseCase @Inject constructor(
    private val unsplashRepository: UnSplashRepository,
) {
    operator fun invoke(query: String) = unsplashRepository.getSearchPhotos(query)
}