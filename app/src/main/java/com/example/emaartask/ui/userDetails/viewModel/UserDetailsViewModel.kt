package com.example.emaartask.ui.userDetails.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emaartask.data.repository.UserListRepository
import com.example.emaartask.database.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val userListRepository: UserListRepository,
) : ViewModel() {
    val userData = MutableLiveData<UserModel>()


    fun getUserByID(userID: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    userData.postValue(userListRepository.getUserByID(userID))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

}