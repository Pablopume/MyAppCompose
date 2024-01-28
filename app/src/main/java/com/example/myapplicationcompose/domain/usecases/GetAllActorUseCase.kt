package com.example.myapplicationcompose.domain.usecases

import com.example.myapplicationcompose.data.ActoresRepository
import javax.inject.Inject

class GetAllActorUseCase @Inject constructor(private val actorRepository: ActoresRepository){
    fun invoke() = actorRepository.getAllActores()
}