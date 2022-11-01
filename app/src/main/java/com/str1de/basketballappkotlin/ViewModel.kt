package com.str1de.basketballappkotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class ViewModel: ViewModel() {
    val messageOfMadeShot : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val messageOfMissShot : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val messageOfAllShots : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

}