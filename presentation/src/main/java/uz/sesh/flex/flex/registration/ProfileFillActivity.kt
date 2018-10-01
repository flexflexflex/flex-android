package uz.sesh.flex.flex.registration

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uz.sesh.flex.flex.R

import kotlinx.android.synthetic.main.activity_profile_fill.*
import kotlinx.android.synthetic.main.content_profile_fill.*
import uz.sesh.flex.data.datasource.repositoryProviders.UserRepositoryProvider
import uz.sesh.flex.domain.model.User
import uz.sesh.flex.domain.repository.UserRepository
import uz.sesh.flex.flex.BaseView
import java.util.*
import android.widget.TextView
import java.lang.reflect.AccessibleObject.setAccessible
import android.support.design.widget.TextInputLayout
import uz.sesh.flex.flex.BaseActivity
import uz.sesh.flex.flex.main.MainActivity


class ProfileFillActivity : BaseActivity(), BaseView {
    var isFreeUsername: Boolean = false
    override fun showLoading(show: Boolean) {

    }

    lateinit var userRepository: UserRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_fill)
        setSupportActionBar(toolbar)
        userRepository = UserRepositoryProvider().provideUserRepository(this)
        fab.setOnClickListener { view ->
            if (isFreeUsername && nameTextInputLayout.editText?.text.toString().isNotBlank()) {
                saveUser(usernameTextInputLayout.editText?.text.toString(), nameTextInputLayout.editText?.text.toString())
            }

        }
        usernameTextInputLayout.requestFocus()
        usernameTextInputLayout.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var username = s.toString()
                //if (username.length>3)
                chechUserIsFree(username)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }

    fun chechUserIsFree(username: String) {
        userRepository.isUsernameIsFree(username = username)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    showLoading(true)
                }
                .doFinally {
                    showLoading(false)
                }
                .subscribe({
                    showInvalidUsername(it, username = username)
                    isFreeUsername = it

                }, {
                    Snackbar.make(fab, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                })
    }

    fun showInvalidUsername(show: Boolean, username: String) {
        if (show) {
            usernameTextInputLayout.isErrorEnabled = false
        } else {
            usernameTextInputLayout.isErrorEnabled = true
            usernameTextInputLayout.error = "Username $username is exist"
            setErrorTextColor(usernameTextInputLayout, R.color.colorAccent)

        }
    }

    fun saveUser(username: String, name: String) {
        userRepository.partialUpdateUser(User(username = username, firstName = name))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    showLoading(true)
                }
                .doFinally {
                    showLoading(false)
                }
                .subscribe({
                    openActivityClear(MainActivity::class.java)
                }, {
                    it.printStackTrace()
                })
    }

    inline fun setErrorTextColor(textInputLayout: TextInputLayout, color: Int) {
        try {
            val fErrorView = TextInputLayout::class.java.getDeclaredField("mErrorView")
            fErrorView.isAccessible = true
            val mErrorView = fErrorView.get(textInputLayout) as TextView
            val fCurTextColor = TextView::class.java.getDeclaredField("mCurTextColor")
            fCurTextColor.isAccessible = true
            fCurTextColor.set(mErrorView, color)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


}
