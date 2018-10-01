package uz.sesh.flex.data.datasource

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

class PreferenceManager(var context: Context) {
    var sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences("flex_prefs", Context.MODE_PRIVATE)
    }

    public fun saveToken(token: String) {
        sharedPreferences.edit().putString("token", token).apply()
    }

    public fun getToken(): String {
        return sharedPreferences.getString("token", "")
    }
    public fun clearToken(){
        sharedPreferences.edit().putString("token", "").apply()
    }
    public fun isLogined() = !sharedPreferences.getString("token", "").equals("")
}