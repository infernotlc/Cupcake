package com.example.cupcake.test

import androidx.navigation.NavController
import junit.framework.TestCase.assertEquals
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun NavController.assertCurrentRouteName(expectedRouteName: String){
    assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
}

    fun getFormattedDate(): String {
    val calendar = Calendar.getInstance()
    calendar.add(java.util.Calendar.DATE, 1)
    val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
    return formatter.format(calendar.time)
}

