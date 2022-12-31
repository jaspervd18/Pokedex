@file:Suppress("DEPRECATION")

package com.example.pokedex.login

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.auth0.android.result.Credentials

object CredentialsManager {
    private const val ACCESS_TOKEN = "access_token"

    private lateinit var editor: SharedPreferences.Editor

    fun saveCredentials(context: Context, credentials: Credentials) {

        val masterKeyAlias: String = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        val sp: SharedPreferences = EncryptedSharedPreferences.create(
            "secret_shared_prefs",
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        editor = sp.edit()
        editor.putString(ACCESS_TOKEN, credentials.accessToken)
            .apply()
    }

    fun getAccessToken(context: Context): String? {
        val masterKeyAlias: String = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        val sp: SharedPreferences = EncryptedSharedPreferences.create(
            "secret_shared_prefs",
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        return sp.getString(ACCESS_TOKEN, null)
    }
}
