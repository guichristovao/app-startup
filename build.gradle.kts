buildscript {
    extra.apply{
        set("kotlinVersion", "1.6.0")
    }
    val gradleVersion = "7.0.4"
    val kotlinVersion = extra.get("kotlinVersion") as String

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:$gradleVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}