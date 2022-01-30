package br.com.jogosusados.domain

import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.addSafeMarginInPrefix(activity: AppCompatActivity) {
    val current = prefixTextView.layoutParams

    val displayMetrics = DisplayMetrics()
    activity.windowManager.defaultDisplay.getMetrics(displayMetrics)

    current.width = (displayMetrics.widthPixels * 0.1).toInt()
    prefixTextView.layoutParams = current
}
