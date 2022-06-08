package com.mostafaKamal.moviesexplorer.datasource.local.di

import android.content.Context
import androidx.room.Room

import com.mostafaKamal.moviesexplorer.datasource.local.dao.LatestMoviesDao
import com.mostafaKamal.moviesexplorer.datasource.local.dao.PopularMoviesDao
import com.mostafaKamal.moviesexplorer.datasource.local.dao.UpcomingMoviesDao
import com.mostafaKamal.moviesexplorer.datasource.local.database.MovieAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val DATABASE_NAME = "AppDatabase"

    @Provides
    fun provideDatabaseName(): String {
        return DATABASE_NAME
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): MovieAppDatabase {
        return Room.databaseBuilder(context, MovieAppDatabase::class.java, provideDatabaseName())
            .fallbackToDestructiveMigration()
            .build()
    }

    //DAO
    @Provides
    @Singleton
    fun provideLatestMoviesDao(appDatabase: MovieAppDatabase): LatestMoviesDao {
        return appDatabase.latestMoviesDao()
    }

    @Provides
    @Singleton
    fun providePopularMoviesDao(appDatabase: MovieAppDatabase): PopularMoviesDao {
        return appDatabase.popularMoviesDao()
    }

    @Provides
    @Singleton
    fun provideUpcomingMoviesDao(appDatabase: MovieAppDatabase): UpcomingMoviesDao {
        return appDatabase.upcomingMoviesDao()
    }

}