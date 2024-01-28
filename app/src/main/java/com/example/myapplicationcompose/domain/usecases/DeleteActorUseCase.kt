package com.example.myapplicationcompose.domain.usecases

import com.example.myapplicationcompose.data.ActoresRepository
import javax.inject.Inject

class DeleteActorUseCase @Inject constructor(private val actorRepository: ActoresRepository){
    operator fun invoke(id: Int) = actorRepository.deleteActores(id)
}