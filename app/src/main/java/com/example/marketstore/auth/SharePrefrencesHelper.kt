package com.example.marketstore.auth

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context) {

    private val PREF_NAME = "SharedPreferencesKey" // nama data untuk menyimpan user dan password
    private val sharePref: SharedPreferences
    private val editPref: SharedPreferences.Editor

    // seperti pendeklarasian database

    // untuk memulai penggunaan shared preferences saat dipanggil
    init { // hanya bisa digunakan dalam project
        sharePref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        editPref = sharePref.edit()
    }

    // untuk menyimpan data string
    fun putString(key: String, value: String) {
        editPref.putString(key, value).apply()
    }

    // untuk mengambil data string
    fun getString(key: String): String? {
        return sharePref.getString(key, null)
    }

    // untuk menyimpan data boolean
    fun putBoolean(key: String, value: Boolean) {
        editPref.putBoolean(key, value).apply()
    }

    // untuk mengambil data boolean
    fun getBoolean(key: String): Boolean {
        return sharePref.getBoolean(key, false)
    }

    // untuk menghapus data
    fun clear() {
        editPref.clear().apply()
    }
}
