package dohun.kim.kinda.count

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import dohun.kim.kinda.kinda_compose.KindaConsumer
import dohun.kim.kinda.kinda_compose.KindaViewModelProvider
import dohun.kim.kinda.kinda_compose.MultipleKindaViewModelProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.dsl.module

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MultipleKindaViewModelProvider(
                getViewModel<CountViewModel>()
            ) {
                Column {
                    KindaConsumer<CountState> { viewModel, state ->
                        Button(onClick = {
                            viewModel.intent(CountEvent.Increase)
                        }) {
                            Text("Increase")
                        }

                        Text(state.count.toString())
                    }

                    Box(modifier = Modifier.height(100.dp))

                    KindaConsumer<CountState> { viewModel, state ->
                        Button(onClick = {
                            viewModel.intent(CountEvent.Decrease)
                        }) {
                            Text("Decrease")
                        }
                        Text(state.count.toString())
                    }
                }
            }
        }
    }
}

val countModule = module {

    factory<CountRepository> { DefaultCountRepository() }

    viewModel { CountViewModel(get()) }
}