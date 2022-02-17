<img src="https://user-images.githubusercontent.com/35379633/153341610-4ea46111-e1c7-44e1-ab34-c760958fd461.gif" alt="Banner with the project name and the following description: An Android project implementing some good practices on starting an app. It also shows an animated preview of a sample app."/>

# Table of contents
1. [Introduction](#introduction)
2. [Variations](#variations)
3. [Architecture](#architecture)
    - [Structure](#structure)
    - [Diagram](#diagram)
    - [Stack](#stack)
4. [Implementation](#implementation)
    - [Tests](#tests)
5. [Roadmap](#roadmap)

# Introduction
<!-- This is an Android showcase project with the objective of implementing the best practices recommended by Google on how to start an app. -->
This is an Android showcase project based on a simple feature using [GitHub API](https://docs.github.com/en/rest).

The main goal of this project is to implement the best practices on how to start an app, as recommended by Google and seen here:
- [App startup time](https://developer.android.com/topic/performance/vitals/launch-time)
- [App Startup](https://developer.android.com/topic/libraries/app-startup)
- [Splash screens](https://developer.android.com/guide/topics/ui/splash-screen)
- [Remove the custom splash screen Activity](https://developer.android.com/guide/topics/ui/splash-screen/migrate#remove_the_custom_splash_screen_activity)
- [Use Room to fetch and cache data](https://developer.android.com/docs/quality-guidelines/build-for-billions/connectivity#network-arch)
- [Placeholder UI](https://material.io/design/communication/launch-screen.html#placeholder-ui)
- [Branded launch](https://material.io/design/communication/launch-screen.html#branded-launch)

# Variations
This project hosts two sample apps in separate repository branches.

|     Branch    |  Description  |
| ------------- | ------------- |
| [main](https://github.com/guichristovao/app-startup/tree/main) | Default sample for this project. Uses profile feature as first loading screen. |
| lazy-initialization* | Uses an static feature as first loading screen and implements [lazy initialization](https://developer.android.com/topic/libraries/app-startup#manual) of other components. |
<!-- | [lazy-initialization*](https://github.com/guichristovao/app-startup/tree/lazy-initialization)<br/>[[compare](https://github.com/guichristovao/app-startup/compare/lazy-initialization#files_bucket)] | Uses an static feature as first loading screen and implements [lazy initialization](https://developer.android.com/topic/libraries/app-startup#manual) of other components. | -->

> \* work in progress

# Architecture
App Startup is a multi-module project built with [MVVM Architecture](https://developer.android.com/jetpack/guide#recommended-app-arch).

## Structure
```
.
├── app                 # Project sample (android-application)
├── buildSrc            # Dependency management with Kotlin DSL
├── core
│   ├── network         # Network layer abstraction (android-library)
│   ├── network-impl    # Network layer implementation (android-library)
│   ├── splash-screen   # Splash screen configurations (android-library)
│   └── ui              # Theme and components (android-library)
└── feature
    ├── menu*           # Feature which doesn't depends on network requests (android-library)
    └── profile         # Feature which depends on network requests (android-library)
```

> \* the **feature:menu** module is used only on [lazy-initialization variation](#variations)

## Diagram
<img src="https://user-images.githubusercontent.com/35379633/153996531-d10a5231-d221-47ef-bc00-62eb532d1010.gif" alt="Project's architecture flowchart"/>

## Stack
- [Kotlin](https://developer.android.com/kotlin)
  - [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
- [Coroutines](https://developer.android.com/kotlin/coroutines)
  - [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#stateflow)
- [Jetpack](https://developer.android.com/jetpack)
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
  - [Compose](https://developer.android.com/jetpack/compose)
  - [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
  - [App Startup](https://developer.android.com/topic/libraries/app-startup)
- [Splash Screen](https://developer.android.com/guide/topics/ui/splash-screen)
- [Accompanist](https://github.com/google/accompanist)
- [Coil](https://github.com/coil-kt/coil)
- [Retrofit](https://github.com/square/retrofit)
- [Gson](https://github.com/google/gson)
### Testing
- [Truth](https://github.com/google/truth)
- [Mockk](https://github.com/mockk/mockk)
- [Turbine](https://github.com/cashapp/turbine)

# Implementation

## [App Startup](https://developer.android.com/topic/libraries/app-startup)
It provides a performant way to initialize components and explicitly define their dependencies.

To automatically initialize components at startup, you must define a component initializer for each component that the app needs to initialize.</br>
This can be done by implementing the [Initializer\<T\>](https://developer.android.com/reference/kotlin/androidx/startup/Initializer) interface and [setting the manifest entries](https://developer.android.com/topic/libraries/app-startup#manifest-entries).

<details open>
  <summary>ProfileInitializer</summary>
  
  ```kotlin
  class ProfileInitializer : Initializer<Unit> {
      override fun create(context: Context) {
          ProfileFeature.init(NetworkImpl)
      }
      override fun dependencies(): List<Class<out Initializer<*>>> {
          //  This is only called after NetworkInitializer is initialized
          return listOf(NetworkInitializer::class.java)
      }
  }
  ```
</details>

<details>
  <summary>NetworkInitializer</summary>

  ```kotlin
  class NetworkInitializer : Initializer<Network> {
      override fun create(context: Context): Network {
          return NetworkImpl
      }
      override fun dependencies(): List<Class<out Initializer<*>>> {
          // No dependencies on other components
          return emptyList()
      }
  }
  ```
</details>

<img align="right" src="https://user-images.githubusercontent.com/35379633/154383745-46aacd08-c5ec-4169-a07b-3a5d78f1e06a.gif" alt="Preview of the splash screen opening, followed by a screen with a loading placeholder UI." width="280" style="display: inline; float: right"/>

## [Splash Screen](https://developer.android.com/guide/topics/ui/splash-screen)
Includes an into-app motion at launch, a splash screen showing your app icon, and a transition to your app itself.</br>
As recommended by [Material Design](https://material.io/design/communication/launch-screen.html#placeholder-ui), a branded indicator is displayed until a [placeholder UI](#placeholder-ui) loads.

```kotlin
// feature:profile
override fun onCreate(savedInstanceState: Bundle?) {
    showSplashScreen { viewModel.uiState.value is ProfileViewModel.UiState.Default }
    super.onCreate(savedInstanceState)
    // ...
}

// core:splash-screen
fun Activity.showSplashScreen(keepOnScreenCondition: () -> Boolean) {
    installSplashScreen().run {
        setKeepOnScreenCondition(keepOnScreenCondition)
        setOnExitAnimationListener { ... }
    }
}
```

It's also possible to [keep the splash screen on-screen for longer periods](https://developer.android.com/guide/topics/ui/splash-screen#suspend-drawing) by setting a condition on an observable value such as [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#stateflow) or [LiveData](https://developer.android.com/topic/libraries/architecture/livedata).

```kotlin
showSplashScreen { viewModel.uiState.value is ProfileViewModel.UiState.Success }
```

In this approach the [Placeholder UI](#placeholder-ui) is not used, since the first screen will only be shown to the user when its contents has fully loaded. 
  
## [Placeholder UI](https://material.io/design/communication/launch-screen.html#placeholder-ui)

<img align="right" src="https://user-images.githubusercontent.com/35379633/154398183-068394b3-36d7-488a-801f-3e7cc960829f.gif" alt="A placeholder UI loading its content on a small white retangular container." width="280" style="display: inline; float: right"/>

Also known as [skeleton screen](https://www.lukew.com/ff/entry.asp?1797). It shows a content placeholder while network-based data is being fetched, instead of displaying a blank screen or a default loading animation.
  
This was done using [Accompanist-placeholder](https://github.com/google/accompanist/tree/main/placeholder-material), a library for [Jetpack Compose](https://developer.android.com/jetpack/compose).

As recommended [here](https://developer.android.com/guide/topics/ui/splash-screen/migrate#remove_the_custom_splash_screen_activity) and [here](https://developer.android.com/docs/quality-guidelines/build-for-billions/connectivity#network-arch), the placeholder is most likely to be shown only when a user opens the app for the first time. The next time this user returns to the app, we can show a cached content while a more recent content is loaded. 
  
# Tests
- Unit tests
    - [UI-State layer](https://developer.android.com/jetpack/guide/ui-layer) (ViewModel)
    - [Data layer](https://developer.android.com/jetpack/guide/data-layer) (Repository and Data Sources)
- UI tests
    - :construction: Work in progress :construction:

# Roadmap
- [ ] UI tests.
- [ ] [lazy-initialization variation](#variations) branch.
- [ ] [Room](https://developer.android.com/training/data-storage/room) to cache network data.
- [ ] [animated icon to splash screen](https://developer.android.com/reference/kotlin/androidx/core/splashscreen/SplashScreen#themes). 
- [ ] [GitHub Actions](https://github.com/features/actions).
