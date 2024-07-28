package com.example.kabarubuntu.presentation.nvgraph

sealed class Route(val route: String) {

    data object OnBoardingScreen : Route(route = "onBoardingScreen")
    data object HomeScreen :  Route(route = "homeScreen")
    data object SearchScreen : Route(route = "searchScreen")
    data object BookMarkScreen : Route(route = "bookMarkScreen")
    data object DetailScreen : Route(route = "detailScreen")
    data object NewsNavigatorScreen : Route(route = "newsNavigatorScreen")

    //Sub graph

    data object AppStartNavigation: Route(route = "appStartNavigation")
    data object NewsNavigation : Route(route = "newsNavigation")


}