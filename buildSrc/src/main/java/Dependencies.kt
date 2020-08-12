object Dependencies {

    object Android {
        const val gradle = "com.android.tools.build:gradle:4.0.0"
        const val activity = "androidx.activity:activity-ktx:1.2.0-alpha07"
        const val appCompat = "androidx.appcompat:appcompat:1.3.0-alpha01"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta4"
        const val core = "androidx.core:core-ktx:1.5.0-alpha01"
        const val paging = "androidx.paging:paging-runtime-ktx:3.0.0-alpha04"
    }

    object Firebase {
        const val bom = "com.google.firebase:firebase-bom:25.7.0"
        const val analytics = "com.google.firebase:firebase-analytics-ktx"
        const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
        const val crashlyticsPlugin = "com.google.firebase:firebase-crashlytics-gradle"
    }

    object Google {
        const val material = "com.google.android.material:material:1.3.0-alpha02"
    }

    object Gms {
        const val plugin = "com.google.gms:google-services:4.3.3"
    }

    object Kotlin {
        const val coroutinesBom = "org.jetbrains.kotlinx:kotlinx-coroutines-bom:1.3.8"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android"
    }

    object Hilt {
        const val plugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
        const val android = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
        const val viewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltJetpack}"
        const val viewModelCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltJetpack}"
    }

    object Lifecycle {
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val compiler = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    }

    object Square {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
        const val picasso = "com.squareup.picasso:picasso:2.71828"
    }

    object Room {
        const val core = "androidx.room:room-runtime:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
        const val extensions = "androidx.room:room-ktx:${Versions.room}"
    }

    object Tests {
        const val junit = "junit:junit:4.12"
        const val extJunit = "androidx.test.ext:junit:1.1.1"
        const val espresso = "androidx.test.espresso:espresso-core:3.2.0"
    }
}