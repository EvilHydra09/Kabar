package com.example.kabarubuntu.presentation.nvgraph

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.kabarubuntu.presentation.home.HomeScreen
import com.example.kabarubuntu.presentation.home.HomeViewModel
import com.example.kabarubuntu.presentation.onboarding.OnBoardingScreen
import com.example.kabarubuntu.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(startDestination: String) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {

        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route){
                val viewmodel : OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = viewmodel::onEvent
                )
            }
        }
        navigation(route = Route.NewsNavigation.route, startDestination = Route.NewsNavigatorScreen.route){
            composable(route = Route.NewsNavigatorScreen.route){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    val viewModel :HomeViewModel = hiltViewModel()
                    val article = viewModel.news.collectAsLazyPagingItems()
                    HomeScreen(navigate = { /*TODO*/ }, article = article )
                }
            }
        }


    }


}