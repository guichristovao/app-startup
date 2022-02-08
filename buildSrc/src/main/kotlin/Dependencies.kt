object Libs {
    object Coil {
        const val coilCompose = "io.coil-kt:coil-compose:1.4.0"
    }

    object Google {
        object Dagger {
            private const val version = "2.38.1"
            const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
            const val hilt = "com.google.dagger:hilt-android:$version"
            const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$version"
        }
    }
}

object AndroidX {
    val core by lazy { "androidx.core:core-ktx:${Versions.androidXCore}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.androidXAppCompat}" }
    val lifecycleRuntime by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}" }
    val lifecycleViewModel by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}" }
    val lifecycleLiveData by lazy { "androidx.lifecycle:lifecycle-livedata-core-ktx:${Versions.lifecycle}" }
    val lifecycleViewModelCompose by lazy { "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}" }
    val activityCompose by lazy { "androidx.activity:activity-compose:${Versions.activityCompose}" }
    val coreSplashScreen by lazy { "androidx.core:core-splashscreen:${Versions.coreSplashScreen}" }
    val startupRuntime by lazy { "androidx.startup:startup-runtime:${Versions.startupRuntime}" }

    /**
     * Test
     */
    val junit by lazy { "androidx.test.ext:junit:${Versions.androidXJunit}" }
    val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.espresso}" }
}

object Compose {
    val ui by lazy { "androidx.compose.ui:ui:${Versions.compose}" }
    val material by lazy { "androidx.compose.material:material:${Versions.compose}" }
    val uiToolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview:${Versions.compose}" }
    val uiTooling by lazy { "androidx.compose.ui:ui-tooling:${Versions.compose}" }
    val runtimeLiveData by lazy { "androidx.compose.runtime:runtime-livedata:${Versions.compose}" }

    /**
     * Test
     */
    val junit by lazy { "androidx.compose.ui:ui-test-junit4:${Versions.compose}" }
}

object Google {
    val material by lazy { "com.google.android.material:material:${Versions.material}" }
    val gson by lazy { "com.google.code.gson:gson:${Versions.gson}" }
}

object Square {
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    val converterGson by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofit}" }
}

object Kotlin {
    val coroutines by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}" }
}

object Test {
    val junit by lazy { "junit:junit:${Versions.junit}" }
}