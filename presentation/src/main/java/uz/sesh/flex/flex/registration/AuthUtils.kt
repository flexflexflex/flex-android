package uz.sesh.flex.flex.registration

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.activity_registration.*

class AuthUtils {
    public interface OnTextCorrectListener {
        fun onCorrect()
        fun onInvalid()
    }

    public class PhoneTextWatcher(val onTextCorrectListener: OnTextCorrectListener) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            println(s.toString())
            println("start: $start")
            println("count: $count")
            println("after: $after")
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (!s.isNullOrBlank() && s?.length!! >= 12) {
                onTextCorrectListener.onCorrect()
            } else {
                onTextCorrectListener.onInvalid()
            }
        }
    }
}

