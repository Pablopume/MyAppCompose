package com.example.myapplicationcompose.data.sources

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplicationcompose.data.service.ActoresRoomDao
import com.example.myapplicationcompose.domain.modelo.Actor

@Database(entities = [Actor::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun actoresDao(): ActoresRoomDao
}