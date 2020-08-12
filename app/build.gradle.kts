import org.jetbrains.kotlin.config.KotlinCompilerVersion
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("com.google.firebase.crashlytics")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(Versions.compile)

    defaultConfig {
        minSdkVersion(Versions.minimum)
        targetSdkVersion(Versions.target)
        versionCode = Versions.versionCode
        versionName = Versions.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFile("proguard-rules.pro")
    }

    buildTypes {
        getByName("release") {
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            resValue("string", "app_name", "Yescapa")
        }
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            signingConfig = signingConfigs.getByName("debug")
            resValue("string", "app_name", "Yescapa Dev")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    (kotlinOptions as KotlinJvmOptions).apply {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures.viewBinding = true
}

dependencies {
    implementation(project(":core"))

    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))

    testImplementation(Dependencies.Tests.junit)
    androidTestImplementation(Dependencies.Tests.espresso)
    androidTestImplementation(Dependencies.Tests.extJunit)

    implementation(platform(Dependencies.Kotlin.coroutinesBom))
    implementation(Dependencies.Kotlin.coroutinesCore)
    implementation(Dependencies.Kotlin.coroutinesAndroid)

    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.activity)
    implementation(Dependencies.Android.constraintLayout)
    implementation(Dependencies.Android.core)
    implementation(Dependencies.Android.paging)

    implementation(platform(Dependencies.Firebase.bom))
    implementation(Dependencies.Firebase.analytics)
    api(Dependencies.Firebase.crashlytics)

    implementation(Dependencies.Google.material)

    implementation(Dependencies.Hilt.android)
    kapt(Dependencies.Hilt.compiler)
    implementation(Dependencies.Hilt.viewModel)
    kapt(Dependencies.Hilt.viewModelCompiler)

    implementation(Dependencies.Lifecycle.viewModel)
    implementation(Dependencies.Lifecycle.liveData)
    implementation(Dependencies.Lifecycle.runtime)
    implementation(Dependencies.Lifecycle.compiler)

    implementation(Dependencies.Room.core)

    implementation(Dependencies.Square.picasso)
}

apply {
    plugin("com.google.gms.google-services")
}
