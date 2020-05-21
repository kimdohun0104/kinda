package dependencies

object Deps {

    const val material = "com.google.android.material:material:1.1.0"
    const val lifecycleExt = "android.arch.lifecycle:extensions:1.1.1"

    object Gradle {
        const val gradle = "com.android.tools.build:gradle:3.6.3"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
    }

    object Kotlin {
        const val version = "1.3.61"
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
        const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5"
    }

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:1.1.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta4"
        const val recyclerView = "androidx.recyclerview:recyclerview:1.1.0"

        object Lifecycle {
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0"
            const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"
        }
    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:4.11.0"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:2.8.1"
        const val gson = "com.squareup.retrofit2:converter-gson:2.8.1"
    }
}