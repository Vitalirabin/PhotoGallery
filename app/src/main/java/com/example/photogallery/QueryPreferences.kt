package com.example.photogallery

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

private const val PREF_SEARCH_QUERY = " searchQuery"

object QueryPreferences {
    fun getStoredQuery(context: Context): String {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(PREF_SEARCH_QUERY, "")!!
    }

    fun setStoredQuery(context: Context, query: String) {
        context.getSharedPreferences(PREF_SEARCH_QUERY, Context.MODE_PRIVATE)
            .edit()
            .putString(PREF_SEARCH_QUERY, query)
            .commit()
    }
}