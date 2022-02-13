package br.com.jogosusados.features.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys


class StorageImpl(private val fileName: String, private val context: Context) : Storage {

    private val sharedPreferences: SharedPreferences by lazy {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        EncryptedSharedPreferences.create(
            fileName,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override fun save(key: String, value: Any?) {
        if (key.isNotBlank()) {
            sharedPreferences.edit()?.run {
                when (value) {
                    null -> remove(key)
                    is String -> putString(key, value)
                    is Long -> putLong(key, value)
                    is Boolean -> putBoolean(key, value)
                    is Int -> putInt(key, value)
                    is Float -> putFloat(key, value)
                    is Set<*> -> takeIf { value.size > 0 && value.first() is String }
                        ?.let { value as? Set<String> }
                        ?.let { putStringSet(key, it) }
                    else -> throw RuntimeException("Não foi possível salvar: [$key] = '$value'")
                }
            }?.apply()
        }
    }

    override fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

    override fun delete(key: String) {
        sharedPreferences.edit().remove(key)?.apply()
    }

    override fun delete(vararg keys: String) {
        for (key in keys) delete(key)
    }

    override fun getStringSet(key: String, defaultValue: MutableSet<String>): MutableSet<String> {
        return sharedPreferences.getStringSet(key, defaultValue) ?: mutableSetOf()
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    override fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue).orEmpty()
    }

    override fun getFloat(key: String, defaultValue: Float): Float {
        return sharedPreferences.getFloat(key, defaultValue)
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }
}
