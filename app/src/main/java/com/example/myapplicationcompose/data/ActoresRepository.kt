package com.example.myapplicationcompose.data

import com.example.myapplicationcompose.data.service.ActoresRoomDao
import com.example.myapplicationcompose.domain.modelo.Actor
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject
@ActivityRetainedScoped
class ActoresRepository @Inject constructor(private val actoresRoomDao: ActoresRoomDao  ){
    fun getAllActores() = actoresRoomDao.getAllActores()
    fun insertActores(actor: Actor) = actoresRoomDao.insertActor(actor)
    fun deleteActores(id: Int) = actoresRoomDao.deleteActor(id)
    fun updateActores(actor: Actor) = actoresRoomDao.updateActor(actor)
}