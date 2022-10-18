package com.example.sqlliteaplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory

class AdminSQLite
    (
        Context: Context?,
        name : String?,
        factory: SQLiteDatabase.CursorFactory?,
        version: Int):SQLiteOpenHelper(Context,name,factory,version) {

    override fun onCreate(baseDeDatos: SQLiteDatabase?) {
        baseDeDatos?.execSQL("create table articulos(codigo int primary key,descripcon text, precio real)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}





