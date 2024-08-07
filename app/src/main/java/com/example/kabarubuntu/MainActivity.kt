package com.example.kabarubuntu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.kabarubuntu.presentation.nvgraph.NavGraph
import com.example.kabarubuntu.ui.theme.KabarUbuntuTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            KabarUbuntuTheme {
                NavGraph(startDestination = viewModel.startDestination)
            }
        }
    }
}

