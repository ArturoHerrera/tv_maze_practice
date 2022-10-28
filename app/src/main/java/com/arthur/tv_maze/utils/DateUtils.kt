package com.arthur.tv_maze.utils

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object DateUtils {

    fun getCurrentDate(patter: String = "yyyy-MM-dd"): String =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now().format(DateTimeFormatter.ofPattern(patter))
        } else {
            SimpleDateFormat(patter).format(Calendar.getInstance().time)
        }
}