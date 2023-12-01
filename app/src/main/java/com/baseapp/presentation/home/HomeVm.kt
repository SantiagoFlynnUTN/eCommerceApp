package com.baseapp.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.model.FeedItem
import com.domain.usecase.IAuthenticationUseCase
import com.domain.usecase.IFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVm @Inject constructor(
    private val feedUseCase: IFeedUseCase,
    private val authenticationUseCase: IAuthenticationUseCase,
) : ViewModel() {

    private val _burgers = MutableStateFlow<List<FeedItem?>>(emptyList())
    val burgers: StateFlow<List<FeedItem?>> = _burgers

    init {
        viewModelScope.launch {
            feedUseCase.invoke().getOrNull()?.let {
                _burgers.emit(it)
            }
        }
    }
}