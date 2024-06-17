package com.github.kudrix.mkpass.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Passwords")
data class Password(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val version: Int? = 1,
    val login: String,
    val service: String,
    val label: String? = "",
    var len: Int? = 32
)
