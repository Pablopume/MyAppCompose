package com.example.myapplicationcompose.framework.mainactivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationcompose.domain.Constantes
import com.example.myapplicationcompose.domain.modelo.Actor
import com.example.myapplicationcompose.domain.usecases.AddActorUseCase
import com.example.myapplicationcompose.domain.usecases.DeleteActorUseCase
import com.example.myapplicationcompose.domain.usecases.GetAllActorUseCase
import com.example.myapplicationcompose.domain.usecases.UpdateActorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val addActorUseCase: AddActorUseCase,
    private val deleteActorUseCase: DeleteActorUseCase,
    private val getAllActorUseCase: GetAllActorUseCase,
    private val updateActorUseCase: UpdateActorUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainState())
    val uiState: StateFlow<MainState> get() = _uiState.asStateFlow()


    init {
        getActorDataBase()
    }

    private fun getActorDataBase() {
        viewModelScope.launch(Dispatchers.IO) {
            val actores = getAllActorUseCase.invoke()
            _uiState.value = _uiState.value.copy(actores = actores)
            getFirstActor()
        }


    }

    private fun getFirstActor() {

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(botonIzquierda = false)
            if (uiState.value.actores.isNotEmpty()) {
                _uiState.value = _uiState.value.copy(botonDerecha = true)
                _uiState.value = _uiState.value.copy(actor = uiState.value.actores[0])
            } else {
                _uiState.value = _uiState.value.copy(botonDerecha = false)
                _uiState.value = _uiState.value.copy(actor = Actor())
            }
            if (uiState.value.actores.size == 1) {
                _uiState.value = _uiState.value.copy(botonDerecha = false)
            }
        }
    }

    fun handleEvent(event: MainEvent) {
        when (event) {


            MainEvent.ErrorVisto -> _uiState.value = _uiState.value.copy(error = null)

            is MainEvent.DeleteActor -> {
                deleteActor()

            }

            is MainEvent.UpdateActor -> {
                updateActor(event.actor)

            }

            is MainEvent.AddActor -> {
                addActor(event.actor)

            }

            is MainEvent.GetSiguiente -> {
                getSiguiente()

            }

            is MainEvent.GetAnterior -> {
                getAnterior()

            }

        }
    }

    private fun getAnterior() {
        viewModelScope.launch(Dispatchers.IO) {
            val position: Int = uiState.value.actores.indexOf(uiState.value.actor)
            _uiState.value =
                _uiState.value.copy(actor = uiState.value.actores[position - 1])

            if (position == 1) {
                _uiState.value = uiState.value.copy(botonIzquierda = false)
            }
            if (position == uiState.value.actores.size - 1) {
                _uiState.value = uiState.value.copy(botonDerecha = true)
            }
        }
    }

    private fun getSiguiente() {

        viewModelScope.launch(Dispatchers.IO) {
            val position: Int = uiState.value.actores.indexOf(uiState.value.actor)
            if (position == uiState.value.actores.size - 2) {
                _uiState.value = uiState.value.copy(botonDerecha = false)
            }
            if (position == 0) {
                _uiState.value = uiState.value.copy(botonIzquierda = true)
            }

            _uiState.value =
                _uiState.value.copy(actor = uiState.value.actores[position + 1])
        }
    }


    private fun addActor(actor: Actor) {

        viewModelScope.launch(Dispatchers.IO) {
            var actores: List<Actor> = ArrayList()
            if (uiState.value.actores.contains(actor)) {
                _uiState.value = _uiState.value.copy(error = Constantes.ACTOR_EXISTE)

            } else {
                addActorUseCase(actor)
                actores = getAllActorUseCase.invoke()
                actor.id = actores[actores.size - 1].id
                _uiState.value = _uiState.value.copy(
                    actores = actores,
                    actor = actor,
                    botonDerecha = false,
                    botonIzquierda = true,
                    error = Constantes.ACTOR_ANYADIDO
                )
            }
            if (actores.size == 1) {
                _uiState.value = _uiState.value.copy(botonIzquierda = false)
            }
        }

    }

    private fun updateActor(actor: Actor) {
        viewModelScope.launch(Dispatchers.IO) {
            updateActorUseCase(actor)
            _uiState.value = _uiState.value.copy(
                actor = actor,
                actores = getAllActorUseCase.invoke(),
                error = Constantes.ACT
            )
        }
    }

    private fun deleteActor() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteActorUseCase(uiState.value.actor.id)
            getActorDataBase()
        }

    }
}