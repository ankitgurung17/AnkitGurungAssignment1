package com.example.ankitgurungmapd721assignment1.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserStore (private val context: Context) {
    companion object {
        private val Context.dataUsername: DataStore<Preferences> by preferencesDataStore("Username")
        private val USERNAME = stringPreferencesKey("user_name")

        private val Context.dataEmail: DataStore<Preferences> by preferencesDataStore("Email")
        private val EMAIL = stringPreferencesKey("email")

        private val Context.dataID: DataStore<Preferences> by preferencesDataStore("studentID")
        private val STUDENT_ID = stringPreferencesKey("student_id")
    }

    val getUsername: Flow<String> = context.dataUsername.data.map { preferences ->
        preferences[USERNAME] ?: ""
    }

    val getEmail: Flow<String> = context.dataEmail.data.map { preferences ->
        preferences[EMAIL] ?: ""
    }

    val getStudentID: Flow<String> = context.dataID.data.map { preferences ->
        preferences[STUDENT_ID] ?: ""
    }

    suspend fun saveUsername(username: String) {
        context.dataUsername.edit { preferences ->
            preferences[USERNAME] = username
        }
    }

    suspend fun saveEmail(email: String) {
        context.dataEmail.edit { preferences ->
            preferences[EMAIL] = email
        }
    }

    suspend fun saveStudentID(studentID: String) {
        context.dataID.edit { preferences ->
            preferences[STUDENT_ID] = studentID
        }
    }
}