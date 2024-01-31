package com.example.myapplicationcompose.data.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplicationcompose.data.Constantes
import com.example.myapplicationcompose.domain.modelo.Actor

@Dao
interface ActoresRoomDao {
@Query(Constantes.QUERY1)
fun getAllActores(): List<Actor>
@Insert(onConflict = OnConflictStrategy.REPLACE)
fun insertActor(actor: Actor)
@Query(Constantes.QUERY2)
fun deleteActor(id: Int)
@Update
fun updateActor(actor: Actor)
}