package com.example.kabarubuntu.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kabarubuntu.presentation.Dimens
import com.example.kabarubuntu.presentation.common.NewsButton
import com.example.kabarubuntu.presentation.common.NewsTextButton
import com.example.kabarubuntu.presentation.onboarding.component.OnBoardingPage
import com.example.kabarubuntu.presentation.onboarding.component.PageIndicator
import com.example.kabarubuntu.presentation.onboarding.component.pages
import com.example.kabarubuntu.ui.theme.KabarUbuntuTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    event: (OnBoardingEvent) -> Unit
) {

    val pagerState = rememberPagerState(pageCount = {
        pages.size
    })

    val buttonState = remember {
        derivedStateOf {
            when (pagerState.currentPage) {
                0 -> listOf("", "Next")
                1 -> listOf("Back", "Next")
                2 -> listOf("Back", "Get Started")
                else -> listOf("", "")
            }
        }
    }

    Surface {
        Column {

            HorizontalPager(state = pagerState) {
                OnBoardingPage(page = pages[it])
            }

            Spacer(modifier = Modifier.weight(1f))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(horizontal = Dimens.Medium),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PageIndicator(pageSize = pages.size, selectedPage = pagerState.currentPage)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    val scope = rememberCoroutineScope()
                    if (buttonState.value[0].isNotEmpty()) {
                        NewsTextButton(onClick = {
                            scope.launch {
                              pagerState.animateScrollToPage(page= pagerState.currentPage - 1)
                            }
                        }, text = buttonState.value[0])
                    }

                    Spacer(modifier = Modifier.width(Dimens.Medium))

                    NewsButton(onClick = {
                        scope.launch {
                            if (pagerState.currentPage == 2) {
                                event(OnBoardingEvent.SaveAppEntry)
                            } else {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage + 1
                                )
                            }
                        }
                    }, text = buttonState.value[1])
                }
            }
            Spacer(modifier = Modifier.height(Dimens.Medium))
        }
    }


}

@Preview(showBackground = true)
@Composable
private fun OnBoardingScreenPreview() {
    KabarUbuntuTheme {
        OnBoardingScreen({})
    }
}