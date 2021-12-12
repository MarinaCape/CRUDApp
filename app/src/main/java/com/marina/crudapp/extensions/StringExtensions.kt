package com.marina.crudapp.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.parseDate(): String{
    val dateTransition = SimpleDateFormat(
        "yyyy-MM-dd'T'HH:mm:ss",
        Locale.getDefault()).parse(this)

    return SimpleDateFormat(
        "dd.MM.yyyy",
        Locale.getDefault()
    ).format(dateTransition)
}

fun String.parseDateComplete(): String{
    val dateTransition = SimpleDateFormat(
        "dd.MM.yyyy",
        Locale.getDefault()).parse(this)

    return SimpleDateFormat(
        "yyyy-MM-dd'T'HH:mm:ss",
        Locale.getDefault()
    ).format(dateTransition)
}

fun Date.parseDateToString(): String{
    return SimpleDateFormat(
        "dd.MM.yyyy",
        Locale.getDefault()
    ).format(this)
}

fun String.parseStringToDate(): Date?{
    return SimpleDateFormat(
        "dd.MM.yyyy",
        Locale.getDefault()
    ).parse(this)
}