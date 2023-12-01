package com.baseapp.presentation.sign_in

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.usecase.IAuthenticationUseCase
import com.domain.usecase.IFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginVm @Inject constructor(
    private val feedUseCase: IFeedUseCase,
    private val authenticationUseCase: IAuthenticationUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState> = _state

    private val _testLivedata = MutableLiveData<String>("hola")
    val testLiveData: LiveData<String> = _testLivedata

    fun changeLiveData() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = feedUseCase()
            val name: String = if (response.isSuccess) {
                 response.getOrNull()?.firstOrNull()?.name ?: ""
            } else {
                 response.exceptionOrNull().toString()
            }
            _testLivedata.postValue(name)
            authTest()
        }
    }

    fun authTest() {
        viewModelScope.launch(Dispatchers.IO) {
            authenticationUseCase.login("santi@gmail.com", "asdasd")
        }
    }

    fun onSignInResult(result: SignInResult){
        _state.update {
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMsg
            )
        }
    }

    fun resetState() {
        _state.update { SignInState() }
    }
}
