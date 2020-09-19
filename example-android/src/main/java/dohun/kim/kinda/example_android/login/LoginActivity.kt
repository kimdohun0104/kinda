package dohun.kim.kinda.example_android.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import dohun.kim.kinda.example_android.R
import dohun.kim.kinda.example_android.count.CountActivity
import dohun.kim.kinda.kinda_android.KindaActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : KindaActivity<LoginState, LoginEvent, LoginSideEffect>() {

    override val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        et_email.onTextChanged { viewModel.intent(LoginEvent.InputEmail(it)) }
        et_password.onTextChanged { viewModel.intent(LoginEvent.InputPassword(it)) }

        btn_login.setOnClickListener { viewModel.intent(LoginEvent.AttemptLogin) }
    }

    override fun render(state: LoginState) {
        pb_loading.visibility = if (state.isLoading) View.VISIBLE else View.INVISIBLE
        btn_login.setBackgroundColor(
            ContextCompat.getColor(
                this,
                if (state.isLoginBtnEnable) R.color.colorPrimary else R.color.colorGray500
            )
        )

        state.navigateEvent.getData()?.let {
            startActivity(Intent(this, CountActivity::class.java))
            finish()
        }

        state.toastEvent.getData()?.let { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun EditText.onTextChanged(onChanged: (text: String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                onChanged(p0.toString())
            }
        })
    }
}