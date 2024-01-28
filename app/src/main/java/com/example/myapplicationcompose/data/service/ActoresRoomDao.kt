package com.example.myapplicationcompose.data.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplicationcompose.domain.modelo.Actor

@Dao
interface ActoresRoomDao {
@Query("SELECT * FROM actores")
fun getAllActores(): List<Actor>
@Insert(onConflict = OnConflictStrategy.REPLACE)
fun insertActor(actor: Actor)
@Query("DELETE FROM actores WHERE id = :id")
fun deleteActor(id: Int)
@Update
fun updateActor(actor: Actor)
}