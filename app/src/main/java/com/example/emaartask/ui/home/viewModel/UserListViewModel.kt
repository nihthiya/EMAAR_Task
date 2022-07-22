package com.example.emaartask.ui.home.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emaartask.Application
import com.example.emaartask.R
import com.example.emaartask.data.model.response.FailureResponse
import com.example.emaartask.data.repository.UserListRepository
import com.example.emaartask.database.model.UserModel
import com.example.emaartask.utils.ErrorResponseUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userListRepository: UserListRepository
) : ViewModel() {
    val localUserData = MutableLiveData<List<UserModel>>()
    var userList = ArrayList<UserModel>()
    val errorResponse = MutableLiveData<FailureResponse>()

    fun getAllUsers(context: Context, pageCount: Int) {

        when {
            Application.isConnected() -> {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        try {

                            userListRepository.getUserList(pageCount).let {
                                if (it.isSuccessful) {
                                    it.body()?.results!!.let { item ->
                                        for (i in 0 until item.size) {
                                            item[i]?.let { iterator ->
                                                userList.add(
                                                    UserModel(
                                                        0,
                                                        iterator.login?.uuid!!,
                                                        iterator.name?.first!!,
                                                        iterator.email!!,
                                                        iterator.location?.country!!,
                                                        iterator.registered?.date!!,
                                                        iterator.picture?.thumbnail!!,
                                                        iterator.picture.large!!,
                                                        iterator.dob?.age!!,
                                                        iterator.dob.date!!,
                                                        iterator.location.city!!,
                                                        iterator.location.state!!,
                                                        iterator.location.postcode!!
                                                    )
                                                )
                                            }
                                        }
                                    }
                                    insertIntoDB(userList)
                                    getUserFromDB()
                                }
                            }

                        } catch (throwable: Throwable) {
                            ErrorResponseUtils.error(throwable) {
                                errorResponse.postValue(it)
                            }
                        }

                    }

                }
            }
            else -> Toast.makeText(
                context,
                context.resources.getString(R.string.connection),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun insertIntoDB(userList: ArrayList<UserModel>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    userListRepository.insertUser(userList)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun getUserFromDB() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    localUserData.postValue(userListRepository.getUserFromDB())
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun clear() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    userListRepository.deleteAll()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

}