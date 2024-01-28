package com.example.myapplicationcompose.domain.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actores")
data class Actor(


    val nombre: String = "",
    val vivo: Boolean = true,
    val peliculaFamosa: String = "",
    val premiosOscar: Int = 0,
    val genero: String = "",
    @PrimaryKey(autoGenerate = true)
    val id: Int= 0
) {
    override fun toString(): String {
        return "Actores(nombre='$nombre', vivo='$vivo', peliculaFamosa='$peliculaFamosa', premiosOscar=$premiosOscar, genero='$genero')"
    }

}