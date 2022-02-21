package br.com.jogosusados.domain

import android.content.Context
import android.graphics.Bitmap
import androidx.core.content.FileProvider
import br.com.jogosusados.image.compressImage
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

suspend fun Context.prepareFileToUpload(
    file: File,
    propertyName: String = "file"
): MultipartBody.Part {
    val authorityClassPath = "${this.packageName}.GenericFileProvider"

    val fileUri = FileProvider.getUriForFile(this, authorityClassPath, file)
    val type = contentResolver.getType(fileUri).orEmpty()
    val mediaType = type.toMediaType()

    val compressed = compressImage(file = file, quality = 86, format = Bitmap.CompressFormat.JPEG)
    val fileCompressed = File(compressed)

    val requestFile = fileCompressed.asRequestBody(mediaType)

    return MultipartBody.Part.createFormData(propertyName, fileCompressed.name, requestFile)
}
