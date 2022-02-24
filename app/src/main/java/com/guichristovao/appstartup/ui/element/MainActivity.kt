package com.guichristovao.appstartup.ui.element

import android.animation.ObjectAnimator
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        showSplashScreen()
        super.onCreate(savedInstanceState)
        setContent { App() }
    }
}

private fun Activity.showSplashScreen(
    condition: SplashScreen.KeepOnScreenCondition? = null
) {
    installSplashScreen().run {
        condition?.let { ::setKeepOnScreenCondition }
        setOnExitAnimationListener { provider ->
            ObjectAnimator.ofFloat(
                provider.view,
                View.ALPHA,
                0f
            ).run {
                interpolator = LinearInterpolator()
                duration = 500L
                doOnEnd { provider.remove() }
                start()
            }
        }
    }
}
