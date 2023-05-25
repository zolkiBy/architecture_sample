plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.base.persistentstorage"
    compileSdk = AppConfig.COMPILE_SDK

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        jvmToolchain(11)
    }
}

dependencies {
    implementation(project(mapOf("path" to ":base:model")))
    implementation(project(mapOf("path" to ":base:di")))

    implementation(AndroidX.roomRuntime)
    annotationProcessor(AndroidX.roomCompiler)
    ksp(AndroidX.roomCompiler)
    implementation(AndroidX.roomKtx)
}