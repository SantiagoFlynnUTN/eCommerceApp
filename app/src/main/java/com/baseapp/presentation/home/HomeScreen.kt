package com.baseapp.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.domain.model.FeedItem

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val viewModel : HomeVm = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()
    val burgers = viewModel.items.collectAsStateWithLifecycle()

    LazyColumn(modifier = Modifier
        .fillMaxSize()) {
        items(items = burgers.value) { burger ->
            Button(modifier = Modifier.size(300.dp, 65.dp),
                onClick = {goCheckout(burger)}) {
                Text(text = burger?.name ?: "not found")
            }
        }
    }
}

fun goCheckout(burger: FeedItem?) {

}
