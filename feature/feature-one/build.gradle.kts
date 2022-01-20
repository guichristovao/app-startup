plugins {
    id("com.android.library")
    kotlin("android")
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
    implementation(project(":core:theme"))
    implementation(project(":core:network-support"))

    implementation(AndroidX.activityCompose)
    implementation(AndroidX.lifecycleViewModel)
    implementation(AndroidX.lifecycleViewModelCompose)
    implementation(AndroidX.lifecycleLiveData)
    implementation(Compose.ui)
    implementation(Compose.material)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.runtimeLiveData)
    implementation(Google.material)
    implementation(Google.gson)
    implementation(Square.retrofit)
    implementation(Kotlin.coroutines)
}