package dohun.kim.kinda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.viewModel
import dohun.kim.kinda.kinda_compose.KindaConsumer
import dohun.kim.kinda.kinda_compose.KindaViewModelProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KindaViewModelProvider(viewModel = viewModel(CountViewModel::class.java)) {

                Column {
                    KindaConsumer<CountState> { viewModel, state ->
                        Button(onClick = {
                            viewModel.intent(CountEvent.Increase)
                        }) {
                            Text(state.count.toString())
                        }

                        Text(state.count.toString())
                    }
                }

            }
        }
    }
}