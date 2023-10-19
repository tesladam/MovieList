package freelancer.istanbul.movielist.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import freelancer.istanbul.movielist.util.extensions.tryCatch
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefsManager @Inject constructor(@ApplicationContext context: Context) {

    companion object {
        private const val APP_PREFS = "MOVIE_LIST_PREFS"
    }

    val mPref = createEncryptedSharedPreferences(context)

    private fun createEncryptedSharedPreferences(context: Context): SharedPreferences {
        val masterKey = MasterKey.Builder(context.applicationContext)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build();
        return EncryptedSharedPreferences.create(
            context.applicationContext,
            APP_PREFS,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    inline fun <reified T : Any> getResponse(key: String): T? {
        val json = mPref.getString(key, null)
        if (json?.isNotBlank() == true) {
            return Gson().fromJson(json, T::class.java)
        }
        return null
    }

    inline fun <reified T : Any> setResponse(key: String, type: T) {
        tryCatch {
            mPref.edit().putString(key, Gson().toJson(type)).apply()
        }
    }

}