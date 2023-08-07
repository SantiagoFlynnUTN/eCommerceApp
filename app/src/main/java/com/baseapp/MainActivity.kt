package com.baseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.baseapp.ui.theme.BaseAppTheme
import com.domain.HiltTestInterface
import com.domain.usecase.IFeedUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    @Named("hiltStorage")
    protected lateinit var hiltTest: HiltTestInterface

    @Inject
    protected lateinit var feedUseCase: IFeedUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    var text by remember { mutableStateOf("") }

                    LaunchedEffect(Unit) {
                        val result = withContext(Dispatchers.IO) {
                            val response = feedUseCase()
                            if (response.isSuccess) {
                                return@withContext response.getOrNull()?.firstOrNull()?.name
                            } else {
                                return@withContext response.exceptionOrNull().toString()
                            }
                        }
                        text = result ?: ""
                    }

                    Greeting("Hello ${hiltTest.getString()} $text flavorApp: ${BuildConfig.FLAVOR_app} FlavorEnv: ${BuildConfig.FLAVOR_env} ")
                }
            }
        }
    }
}

@Composable
fun Greeting(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BaseAppTheme {
        Greeting("Android")
    }
}
