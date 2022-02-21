package br.com.jogosusados.image

import android.content.Context
import android.graphics.Bitmap
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.format
import id.zelory.compressor.constraint.quality
import java.io.File

suspend fun Context.compressImage(file: File, quality: Int, format: Bitmap.CompressFormat): String {
    return try {
        Compressor.compress(this, file) {
            quality(quality)
            format(format)
        }.absolutePath
    } catch (_: Exception) {
        file.absolutePath
    }
}
