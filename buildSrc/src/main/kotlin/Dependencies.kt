object AndroidX {
    val core by lazy { "androidx.core:core-ktx:${Versions.androidXCore}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.androidXAppCompat}" }
    val lifecycleRuntime by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntime}" }
    val activityCompose by lazy { "androidx.activity:activity-compose:${Versions.activityCompose}" }

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

    /**
     * Test
     */
    val compose by lazy { "androidx.compose.ui:ui-test-junit4:${Versions.compose}" }
}

object Google {
    val material by lazy { "com.google.android.material:material:${Versions.material}" }
}

object Test {
    val junit by lazy { "junit:junit:${Versions.junit}" }
}