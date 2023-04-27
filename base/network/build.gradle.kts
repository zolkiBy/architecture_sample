import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlinx-serialization")
}

android {
    namespace = "com.example.base.network"
    compileSdk = AppConfig.COMPILE_SDK

    defaultConfig {
        minSdk = AppConfig.MIN_SDK
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        val localPropsFile = rootProject.file("local.properties")
        if (localPropsFile.exists()) {
            val props = Properties()
            props.load(FileInputStream(localPropsFile))

            val apiKey = props.getProperty("api.key", "")
            if (apiKey.isEmpty()) {
                println("WARNING: api key is empty")
            } else {
                println("Api key: $apiKey")
            }

            buildConfigField("String", "API_KEY", "\"${apiKey}\"")
        } else {
            println("WARNING: local.properties file not exist")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    api(Kotlin.serialization)
    api(Okhttp.client)
    api(Okhttp.loggingInterceptor)
    api(Retrofit.retrofit)
    api(Retrofit.serializationConverter)
}