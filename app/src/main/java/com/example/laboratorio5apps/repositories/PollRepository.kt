package com.example.laboratorio5apps.repositories

import androidx.lifecycle.LiveData
import com.example.laboratorio5apps.models.DAOs.PollDAO
import com.example.laboratorio5apps.models.DAOs.QuestionDAO
import com.example.laboratorio5apps.models.entities.Poll
import com.example.laboratorio5apps.models.entities.Question
import kotlinx.coroutines.*
/**
 * @author Olivverde
 * In cooperation with other students from Computer Science career:
 * @julioH
 * Class purpose: poll repository
 */
class PollRepository(val pollDAO: PollDAO) {

    //Job utilities
    private var viewModelJob = Job()

    /**
     * Kill the job anytime
     */
    fun cancelJob() {
        viewModelJob.cancel()
    }

    //initialize the uiScope
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    //init
    init {
        initialize()
    }

    /**
     * Launch the scope
     */
    fun initialize() {
        uiScope.launch {
            getLastId()
        }
    }

    val allPolls: LiveData<List<Poll>> = pollDAO.getAll()
    var lastId: Int = -1
    suspend fun insert(poll: Poll) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                pollDAO.insert(poll)
            }
        }
    }

    /**
     * Get last ID
     */
    suspend fun getLastId() {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                val last: Int = pollDAO.getLastId()
                lastId = last
            }
        }
    }

}
