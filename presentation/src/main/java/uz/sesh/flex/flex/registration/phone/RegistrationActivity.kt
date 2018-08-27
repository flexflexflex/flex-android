package uz.sesh.flex.flex.registration.phone

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.content_registration.*
import uz.sesh.flex.data.datasource.repositoryProviders.AuthRepositoryProvider
import uz.sesh.flex.domain.repository.AuthRepository
import uz.sesh.flex.flex.R
import uz.sesh.flex.flex.registration.AuthUtils
import uz.sesh.flex.flex.registration.SmsConfirmationActivity

class RegistrationActivity : AppCompatActivity(), View.OnClickListener {
    private var authRepository: AuthRepository? = null
    override fun onClick(v: View?) {
        when (v?.id) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        authRepository= AuthRepositoryProvider().provideAuthRepository(context = baseContext)

    }

    private fun initUi() {
        setContentView(R.layout.activity_registration)
        setSupportActionBar(toolbar)
        fab.hide()
        phoneInputLayout.requestFocus()
        phoneInputLayout.editText?.addTextChangedListener(
                AuthUtils.PhoneTextWatcher(object : AuthUtils.OnTextCorrectListener {
                    override fun onCorrect() {
                        fab.show()
                    }

                    override fun onInvalid() {
                        fab.hide()
                    }

                })
        )
        fab.setOnClickListener {
            signByPhone(getPhoneNumber())
        }
    }

    private fun getPhoneNumber(): String {
        return validatePhone(phoneInputLayout.editText?.text.toString())
    }

    private fun signByPhone(phoneNumber: String) {
        authRepository!!.signByPhoneNumber(phoneNumber)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    setInputsEnable(false)
                }
                .doFinally {
                    setInputsEnable(true)
                }
                .subscribe({
                    goToConfirmationActivity(phoneNumber)
                }, {
                    onError(it)
                })
    }

    private fun goToConfirmationActivity(phoneNumber: String) {
        startActivity(Intent(this, SmsConfirmationActivity::class.java).apply {
            putExtra("phone", phoneNumber)
        })
    }

    private fun setInputsEnable(enable: Boolean) {
        fab.apply {
            if (enable)
                fab.show()
            else
                fab.hide()
        }
        phoneInputLayout.isEnabled = enable
    }

    private fun onError(error: Throwable) {
        error.printStackTrace()
        snackbarMake(error.message!!)
        phoneInputLayout.editText?.isEnabled = true
        fab.show()
    }

    private fun validatePhone(phoneNumber: String): String {
        return phoneNumber.replace("+", "")
    }

    private fun snackbarMake(string: String) {
        Snackbar.make(phoneInputLayout, string, Snackbar.LENGTH_LONG).show()
    }


}
