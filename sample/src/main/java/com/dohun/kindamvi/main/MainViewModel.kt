package com.dohun.kindamvi.main

import com.dohun.kinda.android.KindaViewModel
import com.dohun.kinda.core.stateMachine.KindaStateMachine
import com.dohun.kinda.core.stateMachine.buildStateMachine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MainViewModel : KindaViewModel<MainState, MainEvent, MainSideEffect, MainViewEffect>() {

    override val stateMachine: KindaStateMachine<MainState, MainEvent, MainSideEffect>
        get() = buildStateMachine(MainState()) {
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
                dispatch(MainSideEffect.DelayForIncrease)
            }

            whenSideEffect<MainSideEffect.DelayForIncrease> {
                withContext(Dispatchers.IO) {
                    delay(1000)
                    MainEvent.Increase1000
                }
            }
        }
}