package br.com.jogosusados.domain

import androidx.fragment.app.Fragment

fun Fragment.getLong(key: String, defaultValue: Long? = null): Long {
    return arguments?.getLong(key) ?: (defaultValue ?: -1L)
}

