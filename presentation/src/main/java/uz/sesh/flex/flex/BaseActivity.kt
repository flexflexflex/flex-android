package uz.sesh.flex.flex

import android.content.Intent
import android.support.v7.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    fun <T> openActivityClear(clazz: Class<T>) {
        var intent: Intent = Intent(this, clazz)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    fun <T> openActivity(clazz: Class<T>) {
        var intent: Intent = Intent(this, clazz)
        startActivity(intent)
    }
}