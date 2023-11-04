package com.baseapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.baseapp.presentation.sign_in.GoogleAuthUiClient
import com.baseapp.presentation.sign_in.SignInScreen
import com.baseapp.ui.theme.BaseAppTheme
import com.domain.HiltTestInterface
import com.domain.usecase.IFeedUseCase
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    @Named("hiltAppModule")
    protected lateinit var hiltTest: HiltTestInterface

    @Inject
    protected lateinit var feedUseCase: IFeedUseCase

    val mainVm: MainVm by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    //Greeting("", vm = mainVm)
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "sign_in") {
                        composable("sign_in") {
                            LoginScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(text: String, modifier: Modifier = Modifier, vm: MainVm) {
    val testValue by vm.testLiveData.observeAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
    ) {
        item {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.Black),
                painter = painterResource(id = R.drawable.angus_brother_ic),
                contentDescription = "Angus Logo",
            )
        }
        item {
            Button(
                onClick = { vm.changeLiveData() },
                modifier = Modifier
                    .width(200.dp)
                    .height(120.dp)
                    .background(
                        Color(R.color.black),
                    ),
            ) {
                Text(text = "asdasd")
            }
            Text(
                text = testValue ?: "",
                modifier = modifier,
            )
            Text(
                text = "test",
                modifier = modifier,
            )
            Text(
                text = "test2",
                modifier = modifier,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BaseAppTheme {
    }
}
