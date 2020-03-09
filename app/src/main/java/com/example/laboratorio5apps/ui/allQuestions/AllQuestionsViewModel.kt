package com.example.laboratorio5apps.ui.allQuestions

import android.app.Application
import androidx.lifecycle.*
import com.example.laboratorio5apps.models.DataBase
import com.example.laboratorio5apps.models.entities.Question
import com.example.laboratorio5apps.repositories.QuestionRepository
/**
 * @author Olivverde
 * In cooperation with other students from Computer Science career:
 * @julioH
 * Class purpose:View Model
 * */
class AllQuestionsViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val questionRepository: QuestionRepository
    // LiveData gives us updated words when they change.
    val allQuestions: LiveData<List<Question>>

    init {
        // Gets reference to QuestionDAO from RoomDatabase to construct
        // the correct QuestionRepository.
        val questionDAO = DataBase.getInstance(application, viewModelScope).questionDAO()
        questionRepository = QuestionRepository(questionDAO)
        allQuestions = questionRepository.allQuestions
    }

    /**
     * Kill job
     */
    override fun onCleared() {
        super.onCleared()
        questionRepository.cancelJob()
    }
}