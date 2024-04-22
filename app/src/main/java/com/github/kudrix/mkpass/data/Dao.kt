package com.github.kudrix.mkpass.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    suspend fun insertPassword(password: Password)

    @Query("SELECT * FROM passwords")
    fun getAllPasswords() : Flow<List<Password>>

    @Query("SELECT * FROM passwords WHERE service = :newServices")
    fun getPasswordByServices(newServices : String) : Flow<List<Password>>

    @Update
    fun updatePassword(password: Password)

    @Delete
    fun deletePassword(password: Password)
}