package com.medicalapp.presentation.di

import com.medicalapp.domain.repo.MedicalDataRepository
import com.medicalapp.domain.usecase.GetMedicalDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetMedicalDataUseCase(medicalDataRepository: MedicalDataRepository): GetMedicalDataUseCase {
        return GetMedicalDataUseCase(medicalDataRepository)
    }
}