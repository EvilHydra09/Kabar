package com.example.kabarubuntu.presentation.search

sealed class SearchEvent(){

    data class UpdateQuery(val query: String): SearchEvent()

    data object SearchNews : SearchEvent()
}
