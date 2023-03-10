package com.medicalapp.presentation.di

import android.content.Context
import androidx.room.Room
import com.medicalapp.data.local.DBConfig
import com.medicalapp.data.local.MedicalDB
import com.medicalapp.data.local.MedicalDataDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MedicalDB =
        Room.databaseBuilder(
            context,
            MedicalDB::class.java,
            DBConfig.Constants.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideDao(database: MedicalDB): MedicalDataDAO =
        database.medicalDao()
}