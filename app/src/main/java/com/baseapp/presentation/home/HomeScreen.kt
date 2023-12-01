package com.baseapp.presentation.home

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.baseapp.presentation.sign_in.LoginVm
import com.baseapp.R
import com.baseapp.presentation.sign_in.GoogleAuthUiClient
import com.domain.model.FeedItem
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val viewModel : HomeVm = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()
    val burgers = viewModel.burgers.collectAsStateWithLifecycle()

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
