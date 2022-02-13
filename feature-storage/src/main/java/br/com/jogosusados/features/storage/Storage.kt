package br.com.jogosusados.features.storage

interface Storage {

    fun save(key: String, value: Any?)

    fun delete(vararg keys: String)
    fun delete(key: String)

    fun getStringSet(key: String, defaultValue: MutableSet<String>): MutableSet<String>
    fun getBoolean(key: String, defaultValue: Boolean): Boolean
    fun getString(key: String, defaultValue: String): String
    fun getFloat(key: String, defaultValue: Float): Float
    fun getLong(key: String, defaultValue: Long): Long
    fun getInt(key: String, defaultValue: Int): Int

    fun clearAll()
}
