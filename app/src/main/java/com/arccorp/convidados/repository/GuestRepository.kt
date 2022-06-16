package com.arccorp.convidados.repository

import android.content.ContentValues
import android.content.Context
import com.arccorp.convidados.constans.Consts
import com.arccorp.convidados.model.GuestModel

class GuestRepository private constructor(context: Context) {

    private val guestDataBase = GuestDataBase(context)

    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!::repository.isInitialized) {
                repository = GuestRepository(context)
            }

            return repository
        }
    }

//    fun get(): GuestModel{
//
//    }

    fun insert(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase
            val values = ContentValues()
            val presence = if (guest.presence) 1 else 0

            values.put(Consts.Guest.Coluns.NAME, guest.name)
            values.put(Consts.Guest.Coluns.PRESENCE, presence)

            db.insert(Consts.Guest.TABLE_NAME, null, values)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase
            val presence = if(guest.presence) 1 else 0
            val values = ContentValues()
            values.put(Consts.Guest.Coluns.PRESENCE,presence)
            values.put(Consts.Guest.Coluns.NAME, guest.name)

            val selection = Consts.Guest.Coluns.ID + " = ?"
            val args = arrayOf(guest.id.toString())

            db.update(Consts.Guest.TABLE_NAME, values, selection,args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun delete(id: Int): Boolean {
        return try {
            val db = guestDataBase.writableDatabase
            val selection = Consts.Guest.Coluns.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(Consts.Guest.TABLE_NAME, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAll(): List<GuestModel> {

        val list = mutableListOf<GuestModel>()

        try{
            val db = guestDataBase.readableDatabase
            val projection = arrayOf(
                Consts.Guest.Coluns.ID,
                Consts.Guest.Coluns.NAME,
                Consts.Guest.Coluns.PRESENCE
            )

            val cursor = db.query(Consts.Guest.TABLE_NAME, projection, null, null, null, null, null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(Consts.Guest.Coluns.ID))
                    val name = cursor.getString(cursor.getColumnIndex(Consts.Guest.Coluns.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(Consts.Guest.Coluns.PRESENCE))

                    list.add(GuestModel(id, name, presence == 1))
                }
            }

            cursor.close()
        }
        catch (e: Exception){
            return list
        }

        return  list

    }

    fun getConvidado(id: Int): GuestModel? {

        var guest: GuestModel? = null

        try{
            val db = guestDataBase.readableDatabase
            val projection = arrayOf(
                Consts.Guest.Coluns.ID,
                Consts.Guest.Coluns.NAME,
                Consts.Guest.Coluns.PRESENCE
            )

            val selection = Consts.Guest.Coluns.ID + " = ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(Consts.Guest.TABLE_NAME, projection, selection, args, null, null, null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val name = cursor.getString(cursor.getColumnIndex(Consts.Guest.Coluns.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(Consts.Guest.Coluns.PRESENCE))

                   guest = GuestModel(id, name, presence == 1)
                }
            }

            cursor.close()
        }
        catch (e: Exception){
            return guest
        }

        return  guest

    }

    fun getPresent(): List<GuestModel>{
        val list = mutableListOf<GuestModel>()

        try{
            val db = guestDataBase.readableDatabase
            val cursor = db.rawQuery("select id, name, presence from Guest where presence = 1", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(Consts.Guest.Coluns.ID))
                    val name = cursor.getString(cursor.getColumnIndex(Consts.Guest.Coluns.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(Consts.Guest.Coluns.PRESENCE))

                    val guest = GuestModel(id, name, presence == 1)
                }
            }

            cursor.close()
        }
        catch (e: Exception){
            return list
        }

        return  list
    }

    fun getAbsent(): List<GuestModel>{
        val list = mutableListOf<GuestModel>()

        try{
            val db = guestDataBase.readableDatabase
            val cursor = db.rawQuery("select id, name, presence from Guest where presence = 0", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(Consts.Guest.Coluns.ID))
                    val name = cursor.getString(cursor.getColumnIndex(Consts.Guest.Coluns.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(Consts.Guest.Coluns.PRESENCE))

                    val guest = GuestModel(id, name, presence == 1)
                }
            }

            cursor.close()
        }
        catch (e: Exception){
            return list
        }

        return  list
    }


}