package com.picpay.desafio.android.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.android.room.daos.UserDAO
import com.picpay.desafio.android.room.models.UserModel

@Database(entities = [UserModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDAO

    companion object {
        private lateinit var database: AppDatabase

        fun setupDatabase(context: Context){
            database = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "database-name"
            ).allowMainThreadQueries().build()
        }

        fun getUserDao() = database.userDao()
    }
}