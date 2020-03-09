package com.example.laboratorio5apps.models.DAOs
/**
 * @author Olivverde
 * In cooperation with other students from Computer Science career:
 * @julioH
 * Class purpose:Actually an interface, holds the methods that are going to be used
 */
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.laboratorio5apps.models.entities.Answer

@Dao
interface AnswerDAO {

    @Insert
    fun insert(answer: Answer)

    /**
     * Delete no-default values
     */
    @Query("DELETE FROM answer_table WHERE question_id > 2")
    fun clear()

    /**
     * Get a answer from the dataBase
     */
    @Query("SELECT * FROM answer_table WHERE id = :key")
    fun get(key: Long): Answer?

    /**
     * Recollect all the answers
     */
    @Query("SELECT * FROM answer_table")
    fun getAll(): LiveData<List<Answer>>

    /**
     * Kill everything
     */
    @Query("DELETE FROM answer_table")
    fun deleteAll()
}
