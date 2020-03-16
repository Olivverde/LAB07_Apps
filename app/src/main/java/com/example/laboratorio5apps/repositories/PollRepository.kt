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

    //Aplicaciones con Job y uiScope
    private var viewModelJob = Job()

    fun cancelJob() {
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    //Data
    //obtiene todas nomas asi por la igualacion
    val allPolls: LiveData<List<Poll>> = pollDAO.getAll()
    var lastId = -1
    val count = pollDAO.count()

    //Metodos crud
    suspend fun insert(poll: Poll) {
        withContext(Dispatchers.IO) {
            async {
                pollDAO.insert(poll)
            }.await()
        }
    }

    suspend fun getLastId(): Int {
        return withContext(Dispatchers.IO) {
            val last = pollDAO.getLastId()
            lastId = last
            last
        }
    }

}