package com.example.laboratorio5apps.ui.question

import android.app.Application
import androidx.lifecycle.*
import com.example.laboratorio5apps.models.DataBase
import com.example.laboratorio5apps.models.entities.Answer
import com.example.laboratorio5apps.models.entities.Poll
import com.example.laboratorio5apps.models.entities.Question
import com.example.laboratorio5apps.repositories.AnswerRepository
import com.example.laboratorio5apps.repositories.PollRepository
import com.example.laboratorio5apps.repositories.QuestionRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class QuestionViewModel(application: Application) : AndroidViewModel(application) {

    private val questionRepository: QuestionRepository
    private val answerRepository: AnswerRepository
    private val pollRepository: PollRepository

    val allQuestions: LiveData<List<Question>>
    var lastId: Int = -1

    init {
        val questionDAO = DataBase.getInstance(application, viewModelScope).questionDAO()
        questionRepository = QuestionRepository(questionDAO)
        val answerDAO = DataBase.getInstance(application, viewModelScope).answerDAO()
        answerRepository = AnswerRepository(answerDAO)
        val pollDAO = DataBase.getInstance(application, viewModelScope).pollDAO()
        pollRepository = PollRepository(pollDAO)
        allQuestions = questionRepository.allQuestions
    }

    private fun insertAnswer(answer: Answer) = viewModelScope.launch {
        answerRepository.insert(answer)
    }

    fun addAnswerToQuestion(answerT: String, answerN: Int, answerD: Double, questionId: Long)
        = viewModelScope.launch {
        answerRepository.insert(Answer(0, lastId.toLong(), questionId, answerT, answerN, answerD))
    }

    fun addPoll() {
        viewModelScope.launch {
            async {
                pollRepository.insert(Poll(0))
            }.await()
        }
        lastId = pollRepository.lastId
    }

    override fun onCleared() {
        super.onCleared()
        questionRepository.cancelJob()
    }
}