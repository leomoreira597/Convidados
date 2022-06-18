package com.arccorp.convidados.repository

import android.content.Context
import com.arccorp.convidados.model.GuestModel

class GuestRepository(context: Context) {

    private val guestDataBase = GuestDataBase.getDataBase(context).guestDAO()


    fun insert(guest: GuestModel): Boolean {
       return guestDataBase.insert(guest) > 0
    }

    fun update(guest: GuestModel): Boolean {
        return guestDataBase.update(guest) > 0
    }

    fun delete(id: Int) {
        val guest = getConvidado(id)
        return guestDataBase.delete(guest)
    }

    fun getAll(): List<GuestModel> {
        return  guestDataBase.getAll()
    }

    fun getConvidado(id: Int): GuestModel {
        return guestDataBase.getConvidado(id)
    }

    fun getPresent(): List<GuestModel>{
       return guestDataBase.getPresent()
    }

    fun getAbsent(): List<GuestModel>{
       return guestDataBase.getAbsent()
    }


}