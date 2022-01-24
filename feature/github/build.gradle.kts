plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(Configurations.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Configurations.minSdkVersion)
        targetSdkVersion(Configurations.targetSdkVersion)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    implementation(project(":core:network-support"))
    implementation(project(":core:theme"))

    api(project(":core:splash-screen"))

    implementation(AndroidX.activityCompose)
    implementation(AndroidX.lifecycleViewModel)
    implementation(AndroidX.lifecycleViewModelCompose)
    implementation(AndroidX.lifecycleLiveData)
    implementation(Compose.material)
    implementation(Compose.runtimeLiveData)
    implementation(Google.material)
    implementation(Google.gson)
    implementation(Square.retrofit)
    implementation(Kotlin.coroutines)

    implementation(Libs.Google.Dagger.hilt)
    kapt(Libs.Google.Dagger.hiltCompiler)
}

kapt {
    correctErrorTypes = true
}