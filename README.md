# KindaMVI

Kind of Android MVI framework  

:exclamation: `Kinda is very ealry version.Some of the concepts are likely to change.`​

Do you have problems with handling state of your application? Do you want to handle events easier? You've come to right place. Let me introduce about most fabulous framework in history.  

Kinda is a framework based on MVI concept.
To learn about MVI, I recommend these articles or videos.  

[MVI — another member of the MV* band](https://proandroiddev.com/mvi-a-new-member-of-the-mv-band-6f7f0d23bc8a)  
[Translated to Korean](https://medium.com/@dikolight203/%EB%B2%88%EC%97%AD-mvi-mv-%ED%98%95%EC%A0%9C%EC%9D%98-%EC%83%88%EB%A1%9C%EC%9A%B4-%EB%A9%A4%EB%B2%84-46312e338802)  

[MVI 패턴에 대한 고찰, 이유와 방법 그리고 한계](https://medium.com/@dikolight203/mvi-%ED%8C%A8%ED%84%B4%EC%97%90-%EB%8C%80%ED%95%9C-%EA%B3%A0%EC%B0%B0-%EC%9D%B4%EC%9C%A0%EC%99%80-%EB%B0%A9%EB%B2%95-%EA%B7%B8%EB%A6%AC%EA%B3%A0-%ED%95%9C%EA%B3%84-767cc9973c98)  

[Mobius: A Loopy UI Architecture - Ahmed Nawara, Spotify](https://www.facebook.com/watch/?v=2025571921049235)  

If you want to use Kinda Framework perfectly, please check out this [glideline](https://github.com/kimdohun0104/KindaMVI/blob/master/GUIDELINE.md)

To solve common problem, check out this [common problem solution](https://github.com/kimdohun0104/KindaMVI/blob/master/COMMON_PLOBLEM.md)

<br>

## Download [![](https://jitpack.io/v/kimdohun0104/KindaMVI.svg)](https://jitpack.io/#kimdohun0104/KindaMVI)
You can simply use Kidna in your project! 
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
...
dependencies {
	implementation 'com.github.kimdohun0104:KindaMVI:(latest-release)'
}
```

## Feature

### State machine

State machine internally make new state by **event** or **side effect**. You can simply define it with `buildStateMachine`. Check out the sample code.

```kotlin
buildStateMachine(initialState = MainState()) {
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
		dispatch(MainSideEffect.Nothing)
	}
    
    whenEvent<MainEvent.NavigateToBonus> {
        viewEffect(MainViewEffect.NavigateToMain)
        noChange()
    }

	whenSideEffect<MainSideEffect.Nothing> {
		withContext(Dispatchers.IO) {
			delay(1000)
			MainEvent.Increase1000
		}
	}
}
```

<br>

#### whenEvent

`whenEvent` is very simple way to observe event. You can decide what you are going to do when certain event occurred. There are three different way to define what are you going to do next.

1. `next`

   ```kotlin
   fun STATE.next(state: STATE, sideEffect: SIDE_EFFECT? = null) =
   	KindaStateMachine.Next(state, sideEffect)
   ```

   This is the real code of `next` function. The requirement parameter is **state**. It means the state you passed to this function will be current state. 

   You can additionally pass **sideEffect** too. In this case, the state parameter will immediately apply and execute side effect task. 

2. `dispatch`

   ```kotlin
   fun STATE.dispatch(sideEffect: SIDE_EFFECT) = 
   	KindaStateMachine.Next(this, sideEffect)
   ```

   In some case, you might not need to change state but Just execute side effect. You can use `dispatch`  for just execute side effect.

3. `noChange`

   ```
   fun STATE.noChange() = 
   	KindaStateMachine.Next(this, null as SIDE_EFFECT?)
   ```

   `noChange` is kind of temporary solution for handle **ViewEffect**. You can check ViewEffect section for detail.

<br>

#### whenSideEffect

When you dispatch or pass **SideEffect**. The suspend function inside of `whenSideEffect` will execute. Unfortunately, Kinda only support coroutine suspend function. 

There's no plan for supporting RxJava. But I'm considering Coroutine Flow.

<br>

#### ViewEffect

In Android development, there's tons of single view events like toast, snackbar, navigating, etc. In MVI architecture, this is very complex situation. There's no best practice about single event so it can be a state, side effect or something else. 

So I decided to make new concept called ViewEffect. It's basically **SingleLiveEvent**. You can just call `viewEffect()` to single event. But it's not a dramatic solution, just temporary. ViewEffect can be replaced anytime.

---

### Using Kinda Activity/Fragment

Using Kinda Activity/Fragment helps you to avoid boilerplate. Check out the sample code.

```kotlin
class MainActivity : KindaActivity<MainState, MainEvent, MainSideEffect, MainViewEffect, ActivityMainBinding>() {

	override val viewModel: MainViewModel = MainViewModel()
    override val layoutResourceId: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }
    
    override fun onStateChanged(state: MainState) {
        
    }
    
    override fun onViewEffect(viewEffect: MainViewEffect) {
        
    }
}
```

---
### Logging
Kinda support logging for better debugging. Search **Kinda** in Logcat, then you might noticed every events and side effects left log. Like this:
```
Side Effect [com.dohun.kindamvi.main.MainSideEffect$DelayForIncrease@fc48aa1]
Event [com.dohun.kindamvi.main.MainEvent$Increase1000@baa5898]
    From  [MainState(count=0)]
    Next  [MainState(count=1000)]
```
You can check if the expected value is returned from a specific event.  
If you want to disable logging. Just call `KindaLogger.setIsLogEnable(false)`

<br>

### License

```licen
Copyright [2020] [kimdohun0104]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```



