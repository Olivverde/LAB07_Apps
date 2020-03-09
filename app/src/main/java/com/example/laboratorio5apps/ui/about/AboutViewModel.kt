package com.example.laboratorio5apps.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutViewModel : ViewModel() {

    private val _name = MutableLiveData<String>().apply {
        value = "Oliver Milian 19270"
    }
    val name: LiveData<String> = _name
    private val _course = MutableLiveData<String>().apply {
        value = "Apps"
    }
    val course: LiveData<String> = _course
    private val _description = MutableLiveData<String>().apply {
        value = "Survey app: Uses co-routine and persistence"
    }
    val description: LiveData<String> = _description
}