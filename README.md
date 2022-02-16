<img src="https://user-images.githubusercontent.com/35379633/153341610-4ea46111-e1c7-44e1-ab34-c760958fd461.gif" alt="Banner with the project name and the following description: An Android project implementing some good practices on starting an app. It also shows an animated preview of a sample app."/>

</br>

<div align="center">
  
  # :construction: Work in progress! :construction:
  
</div>
  
</br>

# Table of contents
1. [Introduction](#introduction)
2. [Architecture](#architecture)
    - [Structure](#structure)
    - [Diagram](#diagram)
    - [Stack](#stack)
3. [Implementation](#implementation)
    - [Tests](#tests)
4. [Roadmap](#roadmap)

# Introduction
:construction:

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
    ├── menu            # Feature which doesn't depends on network requests (android-library)
    └── profile         # Feature which depends on network requests (android-library)
```

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
:construction:

# Tests
:construction:

# Roadmap
- Add UI tests.
- Add [Room](https://developer.android.com/training/data-storage/room) to cache network data.
- Add [GitHub Actions](https://github.com/features/actions).
