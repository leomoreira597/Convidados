package com.arccorp.convidados.repository

import android.content.Context
import com.arccorp.convidados.GuestModel

class GuestRepository private constructor(context: Context){

    private val guestDataBase = GuestDataBase(context)

    companion object{
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if(!::repository.isInitialized){
                repository = GuestRepository(context)
            }

            return repository
        }
    }

//    fun get(): GuestModel{
//
//    }

}