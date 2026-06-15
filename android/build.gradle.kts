group = "com.imin.vicescreen.imin_vice_screen"
version = "1.0"

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.7.0")
    }
}

rootProject.allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

apply(plugin = "com.android.library")

extensions.configure<com.android.build.gradle.LibraryExtension>("android") {
    namespace = "com.imin.vicescreen.imin_vice_screen"
    compileSdk = 35

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    defaultConfig {
        minSdk = 21
        multiDexEnabled = true
    }

    testOptions {
        unitTests.all {
            it.testLogging {
                events("passed", "skipped", "failed", "standardOut", "standardError")
                showStandardStreams = true
            }
            it.outputs.upToDateWhen { false }
        }
    }
}

dependencies {
    "testImplementation"("junit:junit:4.13.2")
    "testImplementation"("org.mockito:mockito-core:5.14.2")
    "implementation"(files("libs/freeimagelibrary-4-v1.3_24022700.jar"))
    "implementation"("androidx.multidex:multidex:2.0.1")
    "implementation"("com.github.bumptech.glide:glide:4.16.0")
    "annotationProcessor"("com.github.bumptech.glide:compiler:4.16.0")
}
