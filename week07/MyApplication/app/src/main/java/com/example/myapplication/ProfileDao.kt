package com.example.myapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProfileDao {
    @Insert
    fun insert(profile: Profile)

    @Update
    fun update(prifile: Profile)

    @Delete
    fun delete(profile: Profile)

    @Query("SELECT * FROM Profile")
    fun getAll(): List<Profile>

    @Query("DELETE FROM Profile WHERE name = :name")
    fun deleteUserByName(name: String)
}