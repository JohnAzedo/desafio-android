package com.picpay.desafio.android.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.android.room.models.UserModel

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<UserModel>)

    @Query("SELECT * FROM users")
    fun getAll(): List<UserModel>
}