package com.guesthouse.designsystem.widget

import android.content.Context
import android.widget.Toast

fun unSplashToast(
    context: Context,
    message: String
) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}