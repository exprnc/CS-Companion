package com.exprnc.cscompanion.presentation.features.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exprnc.cscompanion.domain.model.User
import com.exprnc.cscompanion.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class MapsViewModel : ViewModel() {

    private val _users = MutableLiveData<Resource<List<User>>>()
    val users: LiveData<Resource<List<User>>> = _users

    init {

    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _users.value = Resource.Loading()

        }
    }
}