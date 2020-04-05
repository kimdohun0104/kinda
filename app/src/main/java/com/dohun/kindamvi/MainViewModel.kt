package com.dohun.kindamvi

import androidx.lifecycle.ViewModel
import com.dohun.kinda.KindaStateMachine
import com.dohun.kinda.android.KindaViewModel
import com.dohun.kinda.buildKindaStateMachine
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.lang.reflect.Array.get

class MainViewModel : KindaViewModel<MainState, MainEvent, MainSideEffect>() {

    override val stateMachine: KindaStateMachine<MainState, MainEvent, MainSideEffect>
        get() = buildKindaStateMachine(MainState(count = 0)) {
            whenEvent<MainEvent.Increase> {
                next(copy(count = count + 1))
            }

            whenEvent<MainEvent.Decrease> {
                next(copy(count = count - 1))
            }

            whenEvent<MainEvent.Increase1000> {
                next(copy(count = count + 1000))
            }

            whenEffect<MainSideEffect> {
                flow {
                    delay(1000)
                    emit(MainEvent.Increase1000)
                }
            }
        }
}