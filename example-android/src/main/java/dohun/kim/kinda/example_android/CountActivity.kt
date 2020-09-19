package dohun.kim.kinda.example_android

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import dohun.kim.kinda.kinda_android.KindaActivity
import dohun.kim.kinda.kinda_android.KindaViewModel
import kotlinx.android.synthetic.main.activity_count.*

class CountActivity : KindaActivity<CountState, CountEvent, CountSideEffect>() {

    override val viewModel: KindaViewModel<CountState, CountEvent, CountSideEffect> by lazy {
        ViewModelProvider(this)[CountViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count)

        btn_increase.setOnClickListener {
            viewModel.intent(CountEvent.Increase)
        }

        btn_decrease.setOnClickListener {
            viewModel.intent(CountEvent.Decrease)
        }

        btn_magic.setOnClickListener {
            viewModel.intent(CountEvent.AttemptMagic)
        }
    }

    override fun render(state: CountState) {
        tv_count.text = state.count.toString()

        state.toastEvent.getData()?.let { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}
