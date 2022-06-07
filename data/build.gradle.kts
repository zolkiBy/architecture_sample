import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlinx-serialization")
}

android {
    namespace = "com.example.data"
    compileSdk = 32

    defaultConfig {
        minSdk = 23
        targetSdk = 32

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
    implementation(
        project(
            mapOf(
                "path" to ":base:common",
                "path" to ":data-api",
            )
        )
    )

    implementation(Kotlin.serialization)

    implementation(Okhttp.client)
    implementation(Okhttp.loggingInterceptor)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.serializationConverter)

    testImplementation (UnitTests.junit)
    testImplementation (Coroutines.test)
}