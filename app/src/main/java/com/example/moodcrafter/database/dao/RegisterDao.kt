package com.example.moodcrafter.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.moodcrafter.model.Register

@Dao
interface RegisterDao
{
    @Query("SELECT * FROM register")
    fun list(): List<Register>
    @Query("DELETE FROM register WHERE titulo = :titulo")
    fun delete(titulo : String)
/*
    @Query("SELECT * FROM register WHERE eleccion1 = :eleccion1")
    fun listelec1(eleccion1 : String?): List<Register>

    @Query("SELECT * FROM register WHERE eleccion2 = :eleccion2")
    fun listelec2(eleccion2 : String?): List<Register>

    @Query("SELECT * FROM register WHERE eleccion3 = :eleccion3")
    fun listelec3(eleccion3 : String?): List<Register>
*/
    @Insert
    fun save(register: Register)
}