package com.example.laboratorio5apps.repositories

import androidx.lifecycle.LiveData
import com.example.laboratorio5apps.models.DAOs.AnswerDAO
import com.example.laboratorio5apps.models.DAOs.QuestionDAO
import com.example.laboratorio5apps.models.entities.Answer
import com.example.laboratorio5apps.models.entities.Question
import kotlinx.coroutines.*
/**
 * @author Olivverde
 * In cooperation with other students from Computer Science career:
 * @julioH
 * Class purpose: Answer Repository
 */
class AnswerRepository(val answerDAO: AnswerDAO) {

    //Job utilities
    private var viewModelJob = Job()

    /**
     * Kills job anytime
     */
    fun cancelJob() {
        viewModelJob.cancel()
    }
    //Initialize uiScope
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val allAnswers: LiveData<List<Answer>> = answerDAO.getAll()

    /**
     * Raw method
     */
    suspend fun insert(answer: Answer) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                answerDAO.insert(answer)
            }
        }
    }

}
