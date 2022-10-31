package com.arthur.tv_maze.utils

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object DateUtils {

    fun getCurrentDate(pattern: String = "yyyy-MM-dd"): String =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern))
        } else {
            SimpleDateFormat(pattern).format(Calendar.getInstance().time)
        }

    fun getCurrentDateLarge(pattern: String = "EEEE dd 'de' MMMM uuuu"): String =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern, Locale("es", "ES")))
                .replaceFirstChar { a -> a.uppercase() }
        } else {
            SimpleDateFormat(pattern, Locale("es", "ES")).format(Calendar.getInstance().time)
                .replaceFirstChar { a -> a.uppercase() }
        }
}

object StringUtils {
    fun returnWordOfArrayString(
        stringList: List<String>,
        subStringRangeLimit: Int? = null,
        spacer: String = "  "
    ): String {
        var mUniqueWord = ""
        stringList.mapIndexed { index, word ->
            subStringRangeLimit?.let {
                if (stringList.size == 1 || stringList.last() == word) {
                    mUniqueWord += word.substring(0, 3).uppercase()
                    return@mapIndexed
                }
                mUniqueWord = mUniqueWord + word.substring(0, 3).uppercase() + spacer
            } ?: run {
                if (stringList.size == 1 || stringList.last() == word) {
                    mUniqueWord += word.uppercase()
                    return@mapIndexed
                }
                mUniqueWord = mUniqueWord + word.uppercase() + spacer
            }
        }
        return mUniqueWord
    }
}