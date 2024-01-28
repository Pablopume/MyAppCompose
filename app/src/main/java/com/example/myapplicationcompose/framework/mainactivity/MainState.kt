package com.example.myapplicationcompose.framework.mainactivity

import com.example.myapplicationcompose.domain.modelo.Actor

data class MainState (
    val actor: Actor = Actor("", true,"", 0,""),
    val actores: List<Actor> = emptyList(),
    val error: String? = null,
    val botonIzquierda: Boolean = false,
    val botonDerecha: Boolean = true)