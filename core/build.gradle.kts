import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    kapt {
        arguments {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }
    buildFeatures.viewBinding = true
}

dependencies {
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))


    testImplementation(Dependencies.Tests.junit)
    androidTestImplementation(Dependencies.Tests.espresso)
    androidTestImplementation(Dependencies.Tests.extJunit)

    implementation(platform(Dependencies.Kotlin.coroutinesBom))
    implementation(Dependencies.Kotlin.coroutinesCore)
    implementation(Dependencies.Kotlin.coroutinesAndroid)

    implementation(Dependencies.Android.core)
    implementation(Dependencies.Android.paging)

    implementation(Dependencies.Hilt.android)
    kapt(Dependencies.Hilt.compiler)

    implementation(Dependencies.Lifecycle.liveData)
    implementation(Dependencies.Lifecycle.runtime)
    implementation(Dependencies.Lifecycle.compiler)

    implementation(Dependencies.Room.core)
    implementation(Dependencies.Room.extensions)
    kapt(Dependencies.Room.compiler)

    implementation(Dependencies.Square.loggingInterceptor)
    implementation(Dependencies.Square.retrofit)
    implementation(Dependencies.Square.gsonConverter)

}