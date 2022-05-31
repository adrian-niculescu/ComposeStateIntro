package com.example.adriann.state1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HelloViewModel : ViewModel() {
    private val _name = MutableLiveData("")
    val name: LiveData<String>
        get() = _name

    fun onNameChange(newName: String) {
        _name.value = newName
    }
}

class HelloViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HelloViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HelloViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}