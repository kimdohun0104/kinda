package com.dohun.kindamvi.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.dohun.kinda.android.KindaActivity
import com.dohun.kindamvi.R
import com.dohun.kindamvi.databinding.ActivityLoginBinding
import com.dohun.kindamvi.main.MainActivity

class LoginActivity : KindaActivity<LoginState, LoginEvent, LoginSideEffect, LoginViewEffect, ActivityLoginBinding>() {

    override val viewModel: LoginViewModel = LoginViewModel()
    override val layoutResourceId: Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        setupOnTextChanged()
    }

    override fun onViewEffect(viewEffect: LoginViewEffect) {
        when (viewEffect) {
            is LoginViewEffect.NavigateToMain -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun setupOnTextChanged() {
        binding.etEmail.onTextChanged { viewModel.intent(LoginEvent.EmailInputChanged(it)) }
        binding.etPassword.onTextChanged { viewModel.intent(LoginEvent.PasswordInputChanged(it)) }
    }

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