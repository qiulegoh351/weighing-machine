package com.farmtech.weighingmachine

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.farmtech.weighingmachine.ui.components.utils.isEmpty
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object AuthRepository {
    private const val KEY_TOKEN = "TOKEN"

    // MutableStateFlow to hold the auth state
    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> get() = _isAuthenticated
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        preferences = EncryptedSharedPreferences.create(
            context,
            "secret_shared_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )
        // Set initial auth state
        _isAuthenticated.value = !isEmpty(preferences.getString(KEY_TOKEN, null))
    }

    fun getToken(): String? {
        return preferences.getString(KEY_TOKEN, null)
    }

    fun signIn(token: String) {
        if (!isEmpty(token)) {
            _isAuthenticated.value = true
            preferences.edit().apply {
                putString(KEY_TOKEN, token)
                apply()
            }
        }
    }

    fun signOut() {
        _isAuthenticated.value = false
        preferences.edit().apply {
            remove(KEY_TOKEN)
            apply()
        }
    }
}