package com.example.kabarubuntu.presentation.detail

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.kabarubuntu.domain.model.Article
import com.example.kabarubuntu.domain.model.Source
import com.example.kabarubuntu.presentation.detail.component.DetailsTopBar
import com.example.kabarubuntu.ui.theme.KabarUbuntuTheme
import com.example.kabarubuntu.util.UIComponent

@Composable
fun DetailsScreen(
    article: Article,
    onBackClick: () -> Unit,
    event: (DetailsEvent) -> Unit,
    isBookmarked: Boolean,
    sideEffect: UIComponent?
) {

    val context = LocalContext.current
    LaunchedEffect(key1 = sideEffect) {
        sideEffect?.let {
            when(sideEffect){
                is UIComponent.Toast ->{
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                    event(DetailsEvent.RemoveSideEffect)
                }
                else -> Unit
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        DetailsTopBar(
            onBrowserClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBookmarkClick = {
                event(DetailsEvent.UpsertArticle(article))
            },
            onBackClick = onBackClick,
            isBookmarked = isBookmarked
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(all = 16.dp)
        ) {

            item {

                AsyncImage(
                    model = article.urlToImage,
                    contentDescription = "News Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(10.dp))


                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyLarge
                )

            }


        }


    }

}



@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    KabarUbuntuTheme {
        Surface {
            DetailsScreen(
                article = Article(
                    author = "John Doe",
                    content = "LONDON -- Britain's economy continued its recovery from recession at the end of last year, with its GDP increasing by 0.6% between April and June, official figures showed Thursday. \\r\\nThe second-quart… [+1722 chars]\"",
                    description = "A short description of the article.",
                    publishedAt = "2023-12-06T10:00:00Z",
                    source = Source(
                        id = "source-id", name = "Source Name"
                    ),
                    title = "Article Title",
                    url = "https://www.example.com/article",
                    urlToImage = "https://nikonrumors.com/wp-content/uploads/2014/09/Nikon-D750-sample-photo1.jpg"
                ),
                onBackClick = { /*TODO*/ },
                event = { /*TODO*/ },
                isBookmarked = false,
                sideEffect = null
            )
        }
    }

}