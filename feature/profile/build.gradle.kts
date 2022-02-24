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
        kotlinCompilerExtensionVersion = Libs.AndroidX.Compose.version
    }
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:ui"))

    api(project(":core:splash-screen"))

    implementation(Libs.AndroidX.activityCompose)
    implementation(Libs.AndroidX.Lifecycle.viewModel)
    implementation(Libs.AndroidX.Lifecycle.viewModelCompose)
    implementation(Libs.AndroidX.Compose.material)
    implementation(Libs.AndroidX.Compose.Ui.tooling)
    implementation(Libs.Google.material)
    implementation(Libs.Google.gson)
    implementation(Libs.Square.retrofit)
    implementation(Libs.Kotlin.Coroutines.android)
    implementation(Libs.Google.Dagger.Hilt.hilt)
    implementation(Libs.Google.Dagger.Hilt.navigationCompose)

    kapt(Libs.Google.Dagger.Hilt.compiler)

    testImplementation(Libs.turbine)
    testImplementation(Libs.mockk)
    testImplementation(Libs.Google.truth)
    testImplementation(Libs.Google.Dagger.Hilt.testing)
    testImplementation(Libs.Kotlin.Coroutines.test)
}

kapt {
    correctErrorTypes = true
}