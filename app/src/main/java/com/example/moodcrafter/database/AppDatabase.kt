package com.example.moodcrafter.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moodcrafter.model.Register
import com.example.moodcrafter.database.dao.RegisterDao

// Meterlo también aquí arriba.
@Database(entities = [Register::class], version = 3, exportSchema = false)
abstract class AppDatabase: RoomDatabase()
{
    companion object
    {
        val DATABASE_NAME = "library"
    }

    // Se pueden meter tantos DAOs como quieras.
    abstract fun registerDao(): RegisterDao
}