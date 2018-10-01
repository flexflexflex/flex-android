package uz.sesh.flex.flex.registration

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_sms_confirmation.*
import kotlinx.android.synthetic.main.content_sms_confirmation.*
import uz.sesh.flex.data.datasource.repositoryProviders.AuthRepositoryProvider
import uz.sesh.flex.domain.repository.AuthRepository
import uz.sesh.flex.flex.BaseActivity
import uz.sesh.flex.flex.R
import uz.sesh.flex.flex.main.MainActivity

class SmsConfirmationActivity : BaseActivity() {
    private var authRepository: AuthRepository ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        authRepository = AuthRepositoryProvider().provideAuthRepository(context = this)
    }

    private fun initUi() {
        setContentView(R.layout.activity_sms_confirmation)
        setSupportActionBar(toolbar)

        fab.hide()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        fab.setOnClickListener {
            confirmPhoneBySmsCode(intent.getStringExtra("phone"),textInputConfirmationCode.editText?.text.toString())
        }
        textInputConfirmationCode.editText?.apply {
            addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                @SuppressLint("RestrictedApi")
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (!s.isNullOrBlank() && s?.length!! >= 4) {
                        fab.show()
                    } else {
                        fab.hide()
                    }
                }

            })
            requestFocus()
        }

    }

    private fun confirmPhoneBySmsCode(phone: String, code: String) {
        authRepository?.confirmPhoneBySms(phone, code)
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.subscribe(
                        { result ->
                            if (result.newUser) {
                                openActivityClear(ProfileFillActivity::class.java)
                            }else{
                                openActivityClear(MainActivity::class.java)
                            }
                        },
                        { error ->
                            error.printStackTrace()
                        })

    }


}
