package com.baseapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.usecase.IFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVm @Inject constructor(
    private val feedUseCase: IFeedUseCase
) : ViewModel() {

    private val _testLivedata = MutableLiveData<String>("hola")
    val testLiveData: LiveData<String> = _testLivedata

    fun changeLiveData() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = feedUseCase()
            val name = if (response.isSuccess) {
                 response.getOrNull()?.firstOrNull()?.name
            } else {
                 response.exceptionOrNull().toString()
            }
            _testLivedata.postValue(name)
        }

    }
}
