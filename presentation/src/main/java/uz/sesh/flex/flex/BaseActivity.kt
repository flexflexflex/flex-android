package uz.sesh.flex.flex

import android.content.Intent
import android.support.v7.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    fun <T> openActivity(clazz: Class<T>) {
        var intent: Intent = Intent(this, clazz)
        startActivity(intent)
    }
}