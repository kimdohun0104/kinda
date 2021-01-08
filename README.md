### Kinda는 무엇인가요?
Kinda는 프로그램의 **상태**를 **직관적**이고 **안전한** 방법으로 관리할 수 있는 기능을 제공하는 라이브러리입니다.  
[Redux](https://redux.js.org/), FLUX, MVI 등 **단방향 아키텍처**에서 많은 영감을 받았으며, MVI 패턴에 가장 근접합니다.  
MVI패턴에 대해 익숙하지 않다면, 저의 [개인 블로그](https://medium.com/@dikolight203/mvi-%ED%8C%A8%ED%84%B4%EC%97%90-%EB%8C%80%ED%95%9C-%EA%B3%A0%EC%B0%B0-%EC%9D%B4%EC%9C%A0%EC%99%80-%EB%B0%A9%EB%B2%95-%EA%B7%B8%EB%A6%AC%EA%B3%A0-%ED%95%9C%EA%B3%84-767cc9973c98)를 참고할 수 있습니다.  


[Kinda와 함께 MVI 테스트 작성하기](https://medium.com/@kimdohun0104/kinda%EC%99%80-%ED%95%A8%EA%BB%98-mvi-%ED%85%8C%EC%8A%A4%ED%8A%B8-%EC%9E%91%EC%84%B1%ED%95%98%EA%B8%B0-7f589e50846c)  
[MVI 패턴에 대한 고찰, 이유와 방법 그리고 한계](https://medium.com/@kimdohun0104/mvi-%ED%8C%A8%ED%84%B4%EC%97%90-%EB%8C%80%ED%95%9C-%EA%B3%A0%EC%B0%B0-%EC%9D%B4%EC%9C%A0%EC%99%80-%EB%B0%A9%EB%B2%95-%EA%B7%B8%EB%A6%AC%EA%B3%A0-%ED%95%9C%EA%B3%84-767cc9973c98)

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
| [Airbnb](https://www.airbnb.co.kr/) | [MvRx](https://github.com/airbnb/MvRx) | [Better Android Testing at Airbnb](https://medium.com/airbnb-engineering/better-android-testing-at-airbnb-part-4-testing-viewmodels-550d929126c8) |
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
    
    testImplementation "com.github.kimdohun0104.kinda:kinda-android-test:${kinda_version}"
}
```

---

### License
```
MIT License

Copyright (c) 2020 Dohun Kim

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
