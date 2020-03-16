package com.example.laboratorio5apps.models.DAOs

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.laboratorio5apps.models.entities.Poll
import com.example.laboratorio5apps.models.entities.Question
/**
 * @author Olivverde
 * In cooperation with other students from Computer Science career:
 * @julioH
 * Class purpose: Actually an interface, holds the methods for poll stuff
 * */
@Dao
interface PollDAO {

    /**
     * Insert a poll
     */
    @Insert
    fun insert(poll: Poll)

    /**
     * Get last poll
     */
    @Query("SELECT poll_id FROM poll_table ORDER BY poll_id DESC LIMIT 1")
    fun getLastId(): Int

    /**
     * Get all the polls
     */
    @Query("SELECT * FROM poll_table ORDER BY poll_id DESC")
    fun getAll(): LiveData<List<Poll>>

    /**
     * Kill every poll
     */
    @Query("DELETE FROM poll_table")
    fun deleteAll()

    @Query(value = "SELECT COUNT(poll_id) FROM poll_table")
    fun count(): LiveData<Int>
}
