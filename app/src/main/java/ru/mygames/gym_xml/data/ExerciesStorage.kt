package ru.mygames.gym_xml.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.mygames.gym_xml.domain.exercies.Exercies

object ExerciesStorage {

    private const val PREFS_NAME = "exercies_prefs"
    private const val KEY_EXERCIES_LIST = "key_exercies_list"

    private val gson = Gson()

    fun saveExerciesList(context: Context, list: List<Exercies>) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = gson.toJson(list)
        prefs.edit().putString(KEY_EXERCIES_LIST, json).apply()
    }

    fun loadExerciesList(context: Context): List<Exercies> {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_EXERCIES_LIST, null) ?: return emptyList()
        val type = object : TypeToken<List<Exercies>>() {}.type
        return gson.fromJson(json, type)
    }
}