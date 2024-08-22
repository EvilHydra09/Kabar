package com.example.kabarubuntu.presentation.detail.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.twotone.Bookmark
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kabarubuntu.ui.theme.KabarUbuntuTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    modifier: Modifier = Modifier,
    onBrowserClick: () -> Unit,
    onShareClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    onBackClick: () -> Unit,
    isBookmarked: Boolean
) {
    TopAppBar(
        title = { },
        modifier = modifier.fillMaxWidth(),
        navigationIcon = {
            IconButton(onClick = onBackClick ) {
                Icon(imageVector = Icons.Default.ArrowBackIosNew , contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick =  onBrowserClick ) {
                Icon(imageVector = Icons.Default.Public , contentDescription = null)
            }
            IconButton(onClick =  onShareClick ){
                Icon(imageVector = Icons.Default.Share , contentDescription = null)
            }
            IconButton(onClick =  onBookmarkClick ) {
                Icon(
                    imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.TwoTone.Bookmark,
                    contentDescription = null
                )
            }
        }

    )

}

@Preview(showBackground = true)
@Preview(showBackground = true,uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DetailsTopBarPreview() {
    KabarUbuntuTheme {
        DetailsTopBar(
            onBrowserClick = { /*TODO*/ },
            onShareClick = { /*TODO*/ },
            onBookmarkClick = { /*TODO*/ },
            onBackClick = { /*TODO*/ },
            isBookmarked = false)
    }
}

