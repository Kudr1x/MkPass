package com.github.kudrix.mkpass.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Password::class],
    version = 1
)

abstract class MainDb : RoomDatabase(){
    abstract val dao: Dao
}