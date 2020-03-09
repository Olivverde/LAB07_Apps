package com.example.laboratorio5apps.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
/**
* @author Olivverde
* In cooperation with other students from Computer Science career:
* @julioH
* Class purpose: holds the view model
*/
class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}