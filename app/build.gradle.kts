plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(Configurations.compileSdkVersion)

    defaultConfig {
        applicationId = Configurations.applicationId
        minSdkVersion(Configurations.minSdkVersion)
        targetSdkVersion(Configurations.targetSdkVersion)
        versionCode = Configurations.versionCode
        versionName = Configurations.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core:network-impl"))
    implementation(project(":feature:profile"))

    implementation(Libs.AndroidX.activityCompose)
    implementation(Libs.AndroidX.appCompat)
    implementation(Libs.AndroidX.startupRuntime)
    implementation(Libs.AndroidX.Core.core)
    implementation(Libs.AndroidX.Core.splashScreen)
    implementation(Libs.AndroidX.Navigation.compose)
    implementation(Libs.Google.Dagger.Hilt.hilt)

    debugImplementation(Libs.AndroidX.Compose.Ui.tooling)

    kapt(Libs.Google.Dagger.Hilt.compiler)

    testImplementation(Libs.JUnit.junit)

    androidTestImplementation(Libs.AndroidX.Test.junit)
    androidTestImplementation(Libs.AndroidX.Test.espresso)
    androidTestImplementation(Libs.AndroidX.Compose.Ui.junit)
}

kapt {
    correctErrorTypes = true
}