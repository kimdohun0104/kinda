package com.dohun.kindamvi.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelStore
import com.dohun.kinda.android.KindaActivity
import com.dohun.kinda.android.KindaViewModel
import com.dohun.kindamvi.R
import com.dohun.kindamvi.databinding.ActivityLoginBinding
import com.dohun.kindamvi.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : KindaActivity<LoginState, LoginEvent, LoginSideEffect, ActivityLoginBinding>(), LoginNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        binding.etEmail.onTextChanged {
            viewModel.intent(LoginEvent.EmailInputChanged(it))
        }

        binding.etPassword.onTextChanged {
            viewModel.intent(LoginEvent.PasswordInputChanged(it))
        }
    }

    override val viewModel: LoginViewModel = LoginViewModel(this)
    override val layoutResourceId: Int = R.layout.activity_login

    override fun onStateChanged(state: LoginState) {
    }

    override fun navigateToMain() =
        startActivity(Intent(this, MainActivity::class.java))

    private fun EditText.onTextChanged(onChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                onChanged(s.toString())
            }

        })
    }
}