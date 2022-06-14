package com.arccorp.convidados.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.arccorp.convidados.constans.Consts

class GuestDataBase(
    context: Context,
) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object{
        private const val NAME = "guestdb"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table "
                + Consts.Guest.TABLE_NAME+
                " ("+Consts.Guest.Coluns.ID+" integer primary key autoincrement, "+Consts.Guest.Coluns.NAME+" text, "+Consts.Guest.Coluns.PRESENCE+" integer);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldversion: Int, newVersion: Int) {
    }

}