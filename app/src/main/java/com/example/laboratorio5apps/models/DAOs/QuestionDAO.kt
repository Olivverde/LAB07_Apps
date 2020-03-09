package com.example.laboratorio5apps.models.DAOs

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

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.laboratorio5apps.models.entities.Question
/**
 * @author Olivverde
 * In cooperation with other students from Computer Science career:
 * @julioH
 * Class purpose:Actually an interface, holds the methods for answer
 */
@Dao
interface QuestionDAO {
    /**
     * Insert an answer
     */
    @Insert
    fun insert(question: Question)

    /**
     * Clear the table
     */
    @Query("DELETE FROM question_table WHERE is_default = 0")
    fun clear()

    /**
     * Get the value
     */
    @Query("SELECT * FROM question_table WHERE question_id = :key")
    fun get(key: Long): Question?

    /**
     * Get all the values
     */
    @Query("SELECT * FROM question_table ORDER BY question_id DESC")
    fun getAll(): LiveData<List<Question>>

    /**
     * Kill everything
     */
    @Query("DELETE FROM question_table")
    fun deleteAll()

    /**
     * Counts af
     */
    @Query(value = "SELECT COUNT(question_id) FROM question_table")
    fun count(): Int
}
