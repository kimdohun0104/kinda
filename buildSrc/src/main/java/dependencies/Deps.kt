package dependencies

object Deps {

    const val material = "com.google.android.material:material:1.1.0"
    const val lifecycleExt = "android.arch.lifecycle:extensions:1.1.1"

    object Gradle {
        const val gradle = "com.android.tools.build:gradle:3.6.3"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
        const val androidMavenGradlePlugin = "com.github.dcendents:android-maven-gradle-plugin:2.1"
        const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Hilt.version}"
    }

    object Kotlin {
        const val version = "1.4.10"
        private const val coroutineVersion = "1.3.5"
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion"
        const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion"
    }

    object AndroidX {
        const val activityKtx = "androidx.activity:activity-ktx:1.1.0"
        const val appCompat = "androidx.appcompat:appcompat:1.1.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta4"
        const val recyclerView = "androidx.recyclerview:recyclerview:1.1.0"

        object Lifecycle {
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0"
            const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"
        }

        object Hilt {
            const val version = "1.0.0-alpha02"
            const val lifecycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:$version"
            const val compiler = "androidx.hilt:hilt-compiler:$version"
        }
    }

    object Hilt {
        const val version = "2.28.3-alpha"
        const val android = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-android-compiler:$version"
    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:4.11.0"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:2.8.1"
        const val gson = "com.squareup.retrofit2:converter-gson:2.8.1"
    }

    object OkHttp {
        const val logging = "com.squareup.okhttp3:logging-interceptor:4.9.0"
    }

    object Test {
        const val junit4 = "junit:junit:4.13"
        const val coreTesting = "androidx.arch.core:core-testing:2.1.0"
        const val mockitoCore = "org.mockito:mockito-core:3.5.13"
    }
}