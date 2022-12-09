package com.itelligent.ldfandroidproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itelligent.ldfandroidproject.data.model.UserResponse
import com.itelligent.ldfandroidproject.data.repository.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepo: UserRepo
): ViewModel(){

     val userList = MutableLiveData<ArrayList<UserResponse>>()

    fun getUsers()  = viewModelScope.launch {
        userRepo.getUsers().let {
            if (it.isSuccessful){
                userList.postValue(it.body())
            }
        }
    }

}