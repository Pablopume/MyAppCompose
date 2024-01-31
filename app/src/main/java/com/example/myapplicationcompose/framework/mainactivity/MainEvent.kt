package com.example.myapplicationcompose.framework.mainactivity

import com.example.myapplicationcompose.domain.modelo.Actor

sealed class MainEvent {

    data object ErrorVisto : MainEvent()
    data object DeleteActor : MainEvent()
    class AddActor(val actor: Actor) : MainEvent()
    class UpdateActor(val actor: Actor) : MainEvent()
    data object GetSiguiente : MainEvent()
    data object GetAnterior : MainEvent()
}