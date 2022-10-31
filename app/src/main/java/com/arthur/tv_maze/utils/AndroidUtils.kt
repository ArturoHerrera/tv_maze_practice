package com.arthur.tv_maze.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

object IntentUtils {
    fun openExternalUrl(externalUrl: String, context: Context){
        val urlIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(externalUrl)
        )
        context.startActivity(urlIntent)
    }
}