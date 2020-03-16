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

    /**
     * Rate
     */
    @Query(value = "SELECT (SUM(A.answer_rating)/COUNT(P.poll_id)) FROM answer_table AS A JOIN poll_table AS P ON A.poll_id = P.poll_id JOIN question_table AS Q ON A.question_id = Q.question_id WHERE Q.type = 3")
    fun ratingMean(): LiveData<Double>
}
