package com.guichristovao.appstartup.splash_screen

import android.animation.ObjectAnimator
import android.app.Activity
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.splashscreen.SplashScreen.KeepOnScreenCondition

fun Activity.showSplashScreen(
    condition: KeepOnScreenCondition? = null
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
