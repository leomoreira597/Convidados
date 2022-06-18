package com.arccorp.convidados.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.arccorp.convidados.constans.Consts
import com.arccorp.convidados.model.GuestModel

//class GuestDataBase(
//    context: Context,
//) : SQLiteOpenHelper(context, NAME, null, VERSION) {

@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDataBase : RoomDatabase() {

    abstract fun guestDAO(): GuestDAO

    companion object {
        private lateinit var INSTANCE: GuestDataBase

        fun getDataBase(context: Context): GuestDataBase {
            if (!::INSTANCE.isInitialized) {
                synchronized(GuestDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context, GuestDataBase::class.java, "guestdb")
                        .addMigrations(MIGRATION)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        private val MIGRATION: Migration = object : Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DELETE FROM Guest")
            }

        }

    }



    /*companion object{
        private const val NAME = "guestdb"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table "
                + Consts.Guest.TABLE_NAME+
                " ("+Consts.Guest.Coluns.ID+" integer primary key autoincrement, "+Consts.Guest.Coluns.NAME+" text, "+Consts.Guest.Coluns.PRESENCE+" integer);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldversion: Int, newVersion: Int) {
    }*/

}