package com.mostafaKamal.moviesexplorer.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import com.mostafaKamal.moviesexplorer.datasource.local.dao.LatestMoviesDao
import com.mostafaKamal.moviesexplorer.datasource.local.dao.PopularMoviesDao
import com.mostafaKamal.moviesexplorer.datasource.local.dao.UpcomingMoviesDao
import com.mostafaKamal.moviesexplorer.model.MovieModel

@Database(entities = [MovieModel::class], version = 4, exportSchema = false)
@TypeConverters(MovieConverter::class)

abstract class MovieAppDatabase: RoomDatabase() {
    abstract fun latestMoviesDao(): LatestMoviesDao
    abstract fun popularMoviesDao(): PopularMoviesDao
    abstract fun upcomingMoviesDao(): UpcomingMoviesDao
}