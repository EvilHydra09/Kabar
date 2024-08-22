package com.example.kabarubuntu.presentation.home.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun getRelativeTime(publishedAt: String): String {
    // Define the correct DateTimeFormatter for the given format
    val inputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a", Locale.ENGLISH)

    // Parse the publishedAt date string using the defined formatter
    val publishedDate = LocalDateTime.parse(publishedAt, inputFormatter)

    // Get the current time
    val now = LocalDateTime.now()

    // Calculate the duration between now and the published date
    val duration = ChronoUnit.MINUTES.between(publishedDate, now)

    // Return the relative time as a string
    return when {
        duration < 1 -> "Just now"
        duration < 60 -> "${duration} minutes ago"
        duration < 1440 -> "${duration / 60} hours ago"
        else -> "${duration / 1440} days ago"
    }
}