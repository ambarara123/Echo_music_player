package com.android.echo.databases

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.android.echo.Songs


class EchoDatabase : SQLiteOpenHelper {
    var songList: ArrayList<Songs>? = null


    object Staticated {
        var DB_VERSION = 1
        val DATABASE_NAME = "FavouriteDatabase"
        var TABLE_NAME = "FavouriteTable"
        var COLUMN_ID = "SongID"
        var COLUMN_SONG_TITLE = "SongTitle"
        var COLUMN_SONG_ARTIST = "SongArtist"
        var COLUMN_SONG_PATH = "SongPath"
    }

    override fun onCreate(database: SQLiteDatabase?) {

        database?.execSQL("CREATE TABLE " + Staticated.TABLE_NAME + "( " + Staticated.COLUMN_ID + " INTEGER," + Staticated.COLUMN_SONG_ARTIST + " STRING," +
                Staticated.COLUMN_SONG_TITLE + " STRING," + Staticated.COLUMN_SONG_PATH + " STRING);")


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    constructor(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : super(context, name, factory, version)
    constructor(context: Context?) : super(context, Staticated.DATABASE_NAME, null, Staticated.DB_VERSION)


    fun storeAsFavourite(id: Int?, artist: String?, title: String?, path: String?) {
        val db = this.writableDatabase
        var contentValues = ContentValues()
        contentValues?.put(Staticated.COLUMN_ID, id)
        contentValues?.put(Staticated.COLUMN_SONG_ARTIST, artist)
        contentValues?.put(Staticated.COLUMN_SONG_TITLE, title)
        contentValues?.put(Staticated.COLUMN_SONG_PATH, path)

        db?.insert(Staticated.TABLE_NAME, null, contentValues)
        db?.close()
    }

    fun queryDbList(): ArrayList<Songs>? {

        try {

            val db = this.readableDatabase
            val queryParam = "SELECT * FROM " + Staticated.TABLE_NAME
            var cSor = db.rawQuery(queryParam, null)

            if (cSor.moveToFirst()) {
                do {

                    var id = cSor.getInt(cSor.getColumnIndexOrThrow(Staticated.COLUMN_ID))
                    var songArtist = cSor.getString(cSor.getColumnIndexOrThrow(Staticated.COLUMN_SONG_ARTIST))
                    var songTitle = cSor.getString(cSor.getColumnIndexOrThrow(Staticated.COLUMN_SONG_TITLE))
                    var songPath = cSor.getString(cSor.getColumnIndexOrThrow(Staticated.COLUMN_SONG_PATH))
                    songList?.add(Songs(id.toLong(), songTitle, songArtist, songPath, 0))

                } while (cSor.moveToNext())
            } else {
                return null
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return songList
    }

    fun checkIfIdExist(id: Int): Boolean {
        var storeId = -1090
        val db = this.readableDatabase
        val queryParam = "SELECT * FROM " + Staticated.TABLE_NAME + " WHERE SongID = '$id'"
        val cSor = db.rawQuery(queryParam, null)
        if (cSor.moveToFirst()) {
            do {

                storeId = cSor.getInt(cSor.getColumnIndexOrThrow(Staticated.COLUMN_ID))

            } while (cSor.moveToNext())
        } else {
            return false
        }
        return storeId != -1090

    }

    fun deleteFavourite(id: Int?) {
        val db = this.writableDatabase
        db.delete(Staticated.TABLE_NAME, Staticated.COLUMN_ID + "=" + id, null)
        db.close()
    }
}

