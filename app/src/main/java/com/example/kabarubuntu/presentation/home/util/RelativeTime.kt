package com.example.kabarubuntu.presentation.home.util

import android.os.Build
import java.text.SimpleDateFormat

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.Locale


fun getRelativeTime(publishedAt: String): String {
    val duration: Long

    try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Define the correct DateTimeFormatter for the given format
            val inputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a")

            // Trim any excess whitespace from the input string
            val trimmedPublishedAt = publishedAt.trim()

            // Parse the publishedAt date string using the defined formatter
            val publishedDate = LocalDateTime.parse(trimmedPublishedAt, inputFormatter)

            // Get the current time
            val now = LocalDateTime.now()

            // Calculate the duration between now and the published date
            duration = ChronoUnit.MINUTES.between(publishedDate, now)
        } else {
            // Define the correct SimpleDateFormat for the given format
            val inputFormatter = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.ENGLISH)

            // Parse the publishedAt date string using the defined formatter
            val publishedDate = inputFormatter.parse(publishedAt.trim()) ?: throw IllegalArgumentException("Invalid date format")

            // Get the current time
            val now = Date()

            // Calculate the duration between now and the published date
            duration = (now.time - publishedDate.time) / (1000 * 60) // Convert milliseconds to minutes
        }

        // Return the relative time as a string
        return when {
            duration < 1 -> "Just now"
            duration < 60 -> "$duration minutes ago"
            duration < 1440 -> "${duration / 60} hours ago"
            else -> "${duration / 1440} days ago"
        }

    } catch (e: Exception) {
        e.printStackTrace()
        // Handle the exception and return a fallback value
        return "Invalid date"
    }
}