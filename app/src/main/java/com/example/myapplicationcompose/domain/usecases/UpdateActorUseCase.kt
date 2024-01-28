package com.example.myapplicationcompose.domain.usecases

import com.example.myapplicationcompose.data.ActoresRepository
import com.example.myapplicationcompose.domain.modelo.Actor
import javax.inject.Inject

class UpdateActorUseCase @Inject constructor(private val actorRepository: ActoresRepository){
    operator fun invoke(actor: Actor) = actorRepository.updateActores(actor)
}