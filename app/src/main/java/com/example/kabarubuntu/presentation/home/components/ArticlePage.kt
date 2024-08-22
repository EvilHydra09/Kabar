package com.example.kabarubuntu.presentation.home.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.kabarubuntu.domain.model.Article
import com.example.kabarubuntu.domain.model.Source
import com.example.kabarubuntu.presentation.home.util.getRelativeTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArticlePage(article: Article, onClick: () -> Unit) {
    OutlinedCard(onClick = { onClick() }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
        ) {
            AsyncImage(
                model = article.urlToImage,
                contentDescription = "Page Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = 100f // Adjust the start position of the gradient
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .align(Alignment.BottomStart)
            ) {
                Row {
                    Text(
                        text = article.source.name,
                        color = Color.White,
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = " . " + getRelativeTime(article.publishedAt),
                        color = Color.White,
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                    )
                }
                Text(
                    text = article.title,
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun ArticlePagePreview() {
    ArticlePage(article = Article(
        author = "John Doe",
        content = "This is the content of the dummy article. It contains a lot of interesting information about a specific topic.",
        description = "This is a brief description of the dummy article.",
        publishedAt = "2024-08-21T10:00:00Z",
        source = Source(id = "dummy-source", name = "Dummy Source"),
        title = "Dummy Article Title",
        url = "https://www.example.com/dummy-article",
        urlToImage = "https://www.example.com/images/dummy-article.jpg"
    ),
        onClick = {}
    )
}