@file:Suppress("unused")

object Common {
    const val logger = "com.jakewharton.timber:timber:5.0.1"
}

object Kotlin {
    @Suppress("MemberVisibilityCanBePrivate")
    const val version = "1.6.21"
    const val kspVersion = "$version-1.0.5"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
    const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3"
    const val dateTime = "org.jetbrains.kotlinx:kotlinx-datetime:0.3.2"
}

object Coroutines {
    private const val version = "1.6.1"
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    const val serializationConverter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
}

object Okhttp {
    private const val version = "4.9.3"
    const val client = "com.squareup.okhttp3:okhttp:$version"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
}

object Di {
    private const val version = "3.2.0"
    private const val koinKspVersion = "1.0.0-beta-2"
    const val koinAndroid = "io.insert-koin:koin-android:$version"
    const val koinAnnotations = "io.insert-koin:koin-annotations:$koinKspVersion"
    const val koinKspCompiler = "io.insert-koin:koin-ksp-compiler:$koinKspVersion"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:$version"
}

object Material {
    const val material = "com.google.android.material:material:1.6.0"
}

object AndroidX {
    @Suppress("MemberVisibilityCanBePrivate")
    const val composeVersion = "1.2.0-beta02"
    private const val lifecycleVersion = "2.4.1"
    private const val testVersion = "1.4.0"
    private const val extVersion = "1.1.3"
    private const val roomVersion = "2.4.2"

    const val coreKtx = "androidx.core:core-ktx:1.7.0"

    const val appCompat = "androidx.appcompat:appcompat:1.4.1"

    const val composeRuntime = "androidx.compose.runtime:runtime:$composeVersion"
    const val composeFoundation = "androidx.compose.foundation:foundation:$composeVersion"
    const val composeLayout = "androidx.compose.foundation:foundation-layout:$composeVersion"
    const val composeUi = "androidx.compose.ui:ui:$composeVersion"
    const val composeMaterial = "androidx.compose.material:material:$composeVersion"
    const val composeMaterialIconsExtended = "androidx.compose.material:material-icons-extended:$composeVersion"
    const val composeAnimations = "androidx.compose.animation:animation:$composeVersion"
    const val activityCompose = "androidx.activity:activity-compose:1.4.0"
    const val navigationCompose = "androidx.navigation:navigation-compose:2.4.1"
    const val lifecycleViewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion"
    const val composeTooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
    const val composeTestManifest = "androidx.compose.ui:ui-test-manifest:$composeVersion"
    const val composeTestJunit = "androidx.compose.ui:ui-test-junit4:$composeVersion"

    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"

    const val runtime = "androidx.room:room-runtime:$roomVersion"
    const val ktx = "androidx.room:room-ktx:$roomVersion"
    const val compiler = "androidx.room:room-compiler:$roomVersion"

    const val testCore = "androidx.test:core:$testVersion"
    const val testRules = "androidx.test:rules:$testVersion"
    const val testEspressoCore = "androidx.test.espresso:espresso-core:3.4.0"
    const val testExtJunit = "androidx.test.ext:junit-ktx:$extVersion"
}

object UnitTests {
    private const val version = "4.13.2"
    const val junit = "junit:junit:$version"
}