package com.example.laboratorio5apps.models

/**
 * @author Olivverde
 * In cooperation with other students from Computer Science career:
 * @julioH
 * Class purpose: Initialize the dataBase in SQLite
 */

/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.laboratorio5apps.models.DAOs.AnswerDAO
import com.example.laboratorio5apps.models.DAOs.PollDAO
import com.example.laboratorio5apps.models.DAOs.QuestionDAO
import com.example.laboratorio5apps.models.entities.Answer
import com.example.laboratorio5apps.models.entities.Poll
import com.example.laboratorio5apps.models.entities.Question
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//Entities within the database
@Database(entities = [Question::class, Answer::class, Poll::class], version = 7, exportSchema = false)
abstract class DataBase: RoomDatabase() {

    abstract fun questionDAO(): QuestionDAO
    abstract fun answerDAO(): AnswerDAO
    abstract fun pollDAO(): PollDAO

    /**
     * Call the dataBase in order to get feedback
     */
    private class DataBaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        /**
         * Launch the dataBase support, launch the scope as well
         */
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.questionDAO())
                }
            }
        }

        /**
         * Instance the dataBase's question
         */
        suspend fun populateDatabase(questionDAO: QuestionDAO) {
            withContext(Dispatchers.IO) {
                // If the table is empty
                if (questionDAO.count() == 0) {
                    // Add default questions
                    var question = Question(0,"¿How do you rate " +
                            "our service?",3,true)
                    questionDAO.insert(question)
                    question = Question(0,
                        "¿Any suggestion?",1,true)
                    questionDAO.insert(question)
                }
            }
        }
    }

    //Allow the use of only one instance, prevent the creation of a physical class
    //Singleton pattern
    companion object {

        @Volatile
        private var INSTANCE: DataBase? = null

        /**
         * Instance the dataBase
         */
        fun getInstance(context: Context, scope: CoroutineScope): DataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DataBase::class.java,
                        "lab6_db"
                    ).fallbackToDestructiveMigration()
                        .addCallback(DataBaseCallback(scope)).build()
                        //.allowMainThreadQueries().build()
                    /**
                     * AllowMainThreadQueries:
                     * Used to accept petitions from the dataBase using the main threat,
                     * blocking the UI. Initial data is added at the beggining of the execution.
                     */
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}