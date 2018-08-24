package uz.sesh.flex.flex.registration

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.content_registration.*
import uz.sesh.flex.data.datasource.repository.AuthRepositoryImpl
import uz.sesh.flex.data.datasource.repositoryProviders.AuthRepositoryProvider
import uz.sesh.flex.domain.repository.AuthRepository
import uz.sesh.flex.flex.R

class RegistrationActivity : AppCompatActivity(), View.OnClickListener {
    val authRepository: AuthRepository = AuthRepositoryProvider().provideAuthRepository()
    override fun onClick(v: View?) {
        when (v?.id) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()

    }

    private fun initUi() {
        setContentView(R.layout.activity_registration)
        setSupportActionBar(toolbar)
        fab.hide()
        phoneInputLayout.requestFocus()
        phoneInputLayout.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrBlank() && s?.length!! >= 13) {
                    fab.show()
                } else {
                    fab.hide()
                }
            }

        })
        fab.setOnClickListener { view ->
            authRepository.signByPhoneNumber(phoneNumber = phoneInputLayout.editText?.text.toString())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ result ->
                        startActivity(Intent(this, SmsConfirmationActivity::class.java))
                    }, { error ->
                        error.printStackTrace()

                    })
        }
    }
}
