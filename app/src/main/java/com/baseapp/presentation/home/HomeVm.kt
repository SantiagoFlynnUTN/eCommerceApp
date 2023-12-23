package com.baseapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.model.FeedItem
import com.domain.usecase.IAuthenticationUseCase
import com.domain.usecase.ICreateNewClientUseCase
import com.domain.usecase.IFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVm @Inject constructor(
    private val feedUseCase: IFeedUseCase,
    private val createClientUseCase: ICreateNewClientUseCase,
    private val authenticationUseCase: IAuthenticationUseCase,
) : ViewModel() {

    private val _items = MutableStateFlow<List<FeedItem?>>(emptyList())
    val items: StateFlow<List<FeedItem?>> = _items

    init {
        viewModelScope.launch(Dispatchers.IO) {
            feedUseCase.invoke().getOrNull()?.let {
                _items.emit(it)
            }
            //createClientUseCase.invoke()
        }
    }
}