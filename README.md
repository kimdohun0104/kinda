### Kinda는 무엇인가요?
Kinda는 프로그램의 **상태**를 **직관적**이고 **안전한** 방법으로 관리할 수 있는 기능을 제공하는 라이브러리입니다.  
[Redux](https://redux.js.org/), FLUX, MVI 등 **단방향 아키텍처**에서 많은 영감을 받았으며, MVI 패턴에 가장 근접합니다.  
MVI패턴에 대해 익숙하지 않다면, 저의 [개인 블로그](https://medium.com/@dikolight203/mvi-%ED%8C%A8%ED%84%B4%EC%97%90-%EB%8C%80%ED%95%9C-%EA%B3%A0%EC%B0%B0-%EC%9D%B4%EC%9C%A0%EC%99%80-%EB%B0%A9%EB%B2%95-%EA%B7%B8%EB%A6%AC%EA%B3%A0-%ED%95%9C%EA%B3%84-767cc9973c98)를 참고할 수 있습니다.

---

### Kinda 시작하기
Kinda를 시작하기 위해서 [WIKI](https://github.com/kimdohun0104/kinda-mvi/wiki)를 참고해주세요!

---

### 감사의 글
Kinda가 존재할 수 있었던 이유는 수 많은 오픈소스와 자료들 덕분이었습니다. 감사합니다.  
아래는 제가 영감을 얻고 참고했던 자료들입니다. 

| 작성자 | 오픈소스 | 발표, 자료 |
|---|---|---|
| [Spotify](https://www.spotify.com/kr-ko/why-not-available/) |  [Mobius](https://github.com/spotify/mobius) | [Mobius: A Loopy UI Architecture - Ahmed Nawara, Spotify](https://www.facebook.com/watch/?v=2025571921049235) |
| [Airbnb](https://www.airbnb.co.kr/) | [MvRx](https://github.com/airbnb/MvRx) | |
| [Tinder](https://tinder.com/) |[StateMachine](https://github.com/Tinder/StateMachine) | |
| [MyRealTrip](https://www.myrealtrip.com/) | [box](https://github.com/myrealtrip/box) | |
---

### Download [![](https://jitpack.io/v/kimdohun0104/kinda-mvi.svg)](https://jitpack.io/#kimdohun0104/kinda-mvi)
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation "com.github.kimdohun0104.kinda:kinda-core:${kinda_version}"
    implementation "com.github.kimdohun0104.kinda:kinda-dsl:${kinda_version}"
    implementation "com.github.kimdohun0104.kinda:kinda-android:${kinda_version}"
}
```

---

### License
```
Copyright 2020 kimdohun0104

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
