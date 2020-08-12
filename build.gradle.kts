
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin

buildscript {

    repositories {
        google()
        jcenter()
        mavenCentral()
    }

    dependencies {
        classpath(kotlin("gradle-plugin", Versions.kotlin))
        classpath(Dependencies.Android.gradle)
        classpath(platform(Dependencies.Firebase.bom))
        classpath(Dependencies.Firebase.crashlyticsPlugin)
        classpath(Dependencies.Hilt.plugin)
        classpath(Dependencies.Gms.plugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
    }
}

subprojects {
    // Accessing the `PluginContainer` in order to use `whenPluginAdded` function
    project.plugins.configure(project = project)
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

// Extension function on `PluginContainer`
fun PluginContainer.configure(project: Project) {
    whenPluginAdded {
        when (this) {
            is LibraryPlugin -> {
                project.extensions
                    .getByType<LibraryExtension>()
                    .apply {
                        applyCommons()
                    }
            }
        }
    }
}

// Extension function on `LibraryExtension`
fun LibraryExtension.applyCommons() {
    compileSdkVersion(Versions.compile)

    defaultConfig.apply {
        minSdkVersion(Versions.minimum)
        targetSdkVersion(Versions.target)
        versionCode = Versions.versionCode
        versionName = Versions.versionName
        consumerProguardFile("proguard-rules.pro")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions.apply {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
