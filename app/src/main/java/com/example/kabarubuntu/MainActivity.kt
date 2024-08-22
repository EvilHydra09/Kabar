package com.example.kabarubuntu

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.kabarubuntu.data.local.NewsDao
import com.example.kabarubuntu.domain.model.Article
import com.example.kabarubuntu.domain.model.Source
import com.example.kabarubuntu.presentation.nvgraph.NavGraph
import com.example.kabarubuntu.ui.theme.KabarUbuntuTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<MainViewModel>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.splashCondition
            }
        }
        enableEdgeToEdge()
        setContent {
            KabarUbuntuTheme {
                NavGraph(startDestination = viewModel.startDestination)
            }
        }
    }
}

