package com.example.myapplicationcompose.domain.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplicationcompose.domain.Constantes

@Entity(tableName = Constantes.ACTOR_TABLE)
data class Actor(


    val nombre: String = Constantes.EMPTY,
    val vivo: Boolean = true,
    val peliculaFamosa: String = Constantes.EMPTY,
    val premiosOscar: Int = 0,
    val genero: String = Constantes.EMPTY,
    @PrimaryKey(autoGenerate = true)
    var id: Int= 0
)