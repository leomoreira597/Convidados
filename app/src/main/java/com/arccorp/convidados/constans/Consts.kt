package com.arccorp.convidados.constans

class Consts private constructor() {

    object Guest{
        const val ID = "guestid"
        const val TABLE_NAME = "Guest"

        object Coluns{
            const val ID = "id"
            const val NAME = "name"
            const val PRESENCE = "presence"
        }
    }

}