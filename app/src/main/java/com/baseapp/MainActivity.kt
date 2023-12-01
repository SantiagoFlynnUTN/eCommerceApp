package com.baseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.baseapp.presentation.home.HomeScreen
import com.baseapp.presentation.sign_in.LoginScreen
import com.baseapp.presentation.sign_in.LoginVm
import com.baseapp.ui.theme.BaseAppTheme
import com.domain.HiltTestInterface
import com.domain.usecase.IFeedUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    @Named("hiltAppModule")
    protected lateinit var hiltTest: HiltTestInterface

    @Inject
    protected lateinit var feedUseCase: IFeedUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseAppTheme {

                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                HomeScreen()
            }
        }
    }
}

/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun scaffoldComposable(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            if (currentRoute(navController) != "welcome_screen" &&
                currentRoute(navController) != "signin_screen" &&
                currentRoute(navController) != "signup_screen"
            ) {
                BottomNavigationBar(
                    items = listOf(

                        BottomNavItem("Market", "market_screen", Icons.Default.Home),
                        BottomNavItem("Search", "search_screen", Icons.Default.Search),
                        BottomNavItem(
                            "Favorite",
                            "favorites_screen",
                            Icons.Default.Favorite,
                        ),
                        BottomNavItem(
                            "Portfolio",
                            "portfolio_screen",
                            Icons.Default.List,
                        ),
                        BottomNavItem(
                            "Settings",
                            "settings_screen",
                            Icons.Default.Settings,
                        ),

                        ),
                    navController = navController,
                    onItemClick = {
                        navController.popBackStack()
                        navController.navigate(it.route)
                    },
                )
            }
        },
    ) {
        SetupNavGraph(navController)
    }
}*/

@Composable
private fun currentRoute(navController: NavController): String? {
    return navController.currentBackStackEntryAsState().value?.destination?.route
}

@Composable
fun surfaceComposable() {
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

@Composable
fun Greeting(text: String, modifier: Modifier = Modifier, vm: LoginVm) {
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
