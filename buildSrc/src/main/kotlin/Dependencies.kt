object Libs {
    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:1.4.1"
        const val activityCompose = "androidx.activity:activity-compose:1.4.0"
        const val startupRuntime = "androidx.startup:startup-runtime:1.1.0"

        object Compose {
            const val version = "1.1.0-rc01"

            const val material = "androidx.compose.material:material:$version"
            const val runtimeLiveData = "androidx.compose.runtime:runtime-livedata:$version"

            object Ui {
                const val ui = "androidx.compose.ui:ui:$version"
                const val tooling = "androidx.compose.ui:ui-tooling:$version"
                const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
                const val junit = "androidx.compose.ui:ui-test-junit4:$version"
            }
        }

        object Core {
            const val core = "androidx.core:core-ktx:1.7.0"
            const val splashScreen = "androidx.core:core-splashscreen:1.0.0-beta01"
        }

        object Lifecycle {
            private const val version = "2.4.0"

            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
            const val liveData = "androidx.lifecycle:lifecycle-livedata-core-ktx:$version"
        }

        object Test {
            const val junit = "androidx.test.ext:junit:1.1.3"
            const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
        }
    }

    object Coil {
        const val coilCompose = "io.coil-kt:coil-compose:1.4.0"
    }

    object Google {
        const val material = "com.google.android.material:material:1.5.0"
        const val gson = "com.google.code.gson:gson:2.8.5"

        object Dagger {
            private const val version = "2.38.1"
            const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
            const val hilt = "com.google.dagger:hilt-android:$version"
            const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$version"
        }

        object Accompanist {
            const val placeholderMaterial =
                "com.google.accompanist:accompanist-placeholder-material:0.24.1-alpha"
        }
    }

    object JUnit {
        const val junit = "junit:junit:4.13.2"
    }

    object Square {
        private const val version = "2.9.0"

        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val converterGson = "com.squareup.retrofit2:converter-gson:$version"
    }

    object Kotlin {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9"
    }
}
