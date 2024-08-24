package com.example.kabarubuntu.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kabarubuntu.domain.usecase.appentry.SaveAppEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val saveAppEntryUseCase: SaveAppEntry
) :ViewModel() {

    fun onEvent(event: OnBoardingEvent){
        when(event){
            is OnBoardingEvent.SaveAppEntry ->{
                saveAppEntry()
            }
        }

    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            saveAppEntryUseCase()
        }
    }

}