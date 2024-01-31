package com.example.myapplicationcompose.framework.mainactivity

import com.example.myapplicationcompose.domain.Constantes
import com.example.myapplicationcompose.domain.modelo.Actor

data class MainState(
    val actor: Actor = Actor(Constantes.EMPTY, true, Constantes.EMPTY, 0, Constantes.EMPTY),
    val actores: List<Actor> = emptyList(),
    val error: String? = null,
    val botonIzquierda: Boolean = false,
    val botonDerecha: Boolean = true
)