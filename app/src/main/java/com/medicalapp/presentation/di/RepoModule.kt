package com.medicalapp.presentation.di

import com.medicalapp.data.local.MedicalDataDAO
import com.medicalapp.data.remote.APIService
import com.medicalapp.data.repo.MedicalRepositoryImpl
import com.medicalapp.domain.repo.MedicalDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideRepo(apiService: APIService, medicalDataDAO: MedicalDataDAO): MedicalDataRepository {
        return MedicalRepositoryImpl(apiService,medicalDataDAO)
    }
}