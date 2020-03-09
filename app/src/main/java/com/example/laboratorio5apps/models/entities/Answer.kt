package com.example.laboratorio5apps.models.entities
/**
 * @author Olivverde
 * In cooperation with other students from Computer Science career:
 * @julioH
 * Class purpose: Hold answer's table data
 */
import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(tableName = "answer_table", foreignKeys = [
    //Entry from question table
    ForeignKey(entity = Question::class, parentColumns = ["question_id"],
        childColumns = ["question_id"], onDelete = CASCADE),
    //Entry from survey table
    ForeignKey(entity = Poll::class, parentColumns = ["poll_id"],
        childColumns = ["poll_id"], onDelete = CASCADE)],
    //Entry from question ID
    indices = arrayOf(Index(value = arrayOf("question_id"), unique = true),
    Index(value = arrayOf("poll_id"), unique = true)))

/**
 * Keep the answer's data stored
 */
data class Answer (
    //Table
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0L,
    @ColumnInfo(name = "poll_id")
    var pollId: Long,
    @ColumnInfo(name = "question_id")
    var questionId: Long,
    @ColumnInfo(name = "answer_text")
    var answerText: String,
    @ColumnInfo(name = "answer_number")
    var answerNumber: Int,
    @ColumnInfo(name = "answer_rating")
    var answerRating: Double
)