package com.dohun.kindamvi.main

import com.dohun.kinda.android.KindaViewModel
import com.dohun.kinda.core.KindaStateMachine
import com.dohun.kinda.core.buildStateMachine
import com.dohun.kindamvi.main.MainEvent
import com.dohun.kindamvi.main.MainSideEffect
import com.dohun.kindamvi.main.MainState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MainViewModel : KindaViewModel<MainState, MainEvent, MainSideEffect>() {

    override val stateMachine: KindaStateMachine<MainState, MainEvent, MainSideEffect>
        get() = buildStateMachine(MainState(count = 0)) {
            whenEvent<MainEvent.Increase> {
                next(copy(count = count + 1))
            }

            whenEvent<MainEvent.Decrease> {
                next(copy(count = count - 1))
            }

            whenEvent<MainEvent.Increase1000> {
                next(copy(count = count + 1000))
            }

            whenEvent<MainEvent.OnClickIncrease1000> {
                next(copy(), MainSideEffect.Nothing)
            }

            whenIoTask<MainSideEffect.Nothing> {
                withContext(Dispatchers.IO) {
                    delay(1000)
                    MainEvent.Increase1000
                }
            }
        }
}