package com.arccorp.convidados.repository

import android.content.ContentValues
import android.content.Context
import com.arccorp.convidados.constans.Consts
import com.arccorp.convidados.model.GuestModel

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

    fun insert(guest: GuestModel): Boolean{
        return try{
            val db = guestDataBase.writableDatabase
            val values = ContentValues()
            val presence = if(guest.presence) 1 else 0

            values.put(Consts.Guest.Coluns.NAME, guest.name)
            values.put(Consts.Guest.Coluns.PRESENCE, presence)

            db.insert(Consts.Guest.TABLE_NAME, null, values )
            true
        } catch (e: Exception){
            false
        }
    }

}