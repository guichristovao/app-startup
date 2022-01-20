plugins {
    id("com.android.application")
    kotlin("android")
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
        kotlinCompilerExtensionVersion = Versions.compose
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":feature:feature-one"))

    implementation(AndroidX.core)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.lifecycleRuntime)
    implementation(AndroidX.activityCompose)
    implementation(Compose.ui)
    implementation(Compose.material)
    implementation(Compose.uiToolingPreview)

    implementation(Google.material)

    debugImplementation(Compose.uiTooling)

    testImplementation(Test.junit)

    androidTestImplementation(AndroidX.junit)
    androidTestImplementation(AndroidX.espresso)
    androidTestImplementation(Compose.junit)
}