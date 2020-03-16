package com.example.laboratorio5apps.ui.results

import android.app.Application
import androidx.lifecycle.*
import com.example.laboratorio5apps.models.DataBase
import com.example.laboratorio5apps.repositories.AnswerRepository
import com.example.laboratorio5apps.repositories.PollRepository

class ResultsViewModel(application: Application) : AndroidViewModel(application) {


    private val answerRepository: AnswerRepository
    private val pollRepository: PollRepository

    val rating: LiveData<Double>
    val count: LiveData<Int>

    init {
        val answerDAO = DataBase.getInstance(application, viewModelScope).answerDAO()
        answerRepository = AnswerRepository(answerDAO)
        val pollDAO = DataBase.getInstance(application, viewModelScope).pollDAO()
        pollRepository = PollRepository(pollDAO)
        rating = answerRepository.rating
        count = pollRepository.count
    }

    private val _text = MutableLiveData<String>().apply {
        value = "Resultados"
    }

    val text: LiveData<String> = _text

}