@file:Suppress("unused")

object Kotlin {
    @Suppress("MemberVisibilityCanBePrivate")
    const val version = "1.8.20"
    const val kspVersion = "$version-1.0.10"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
    const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0"
    const val dateTime = "org.jetbrains.kotlinx:kotlinx-datetime:0.4.0"
}

object Coroutines {
    private const val version = "1.6.4"
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    const val serializationConverter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
}

object Okhttp {
    private const val version = "4.10.0"
    const val client = "com.squareup.okhttp3:okhttp:$version"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
}

object Di {
    private const val version = "3.4.0"
    private const val koinKspVersion = "1.2.0"
    const val koinAndroid = "io.insert-koin:koin-android:$version"
    const val koinAnnotations = "io.insert-koin:koin-annotations:$koinKspVersion"
    const val koinKspCompiler = "io.insert-koin:koin-ksp-compiler:$koinKspVersion"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:$version"
}

object Material {
    const val material = "com.google.android.material:material:1.9.0-rc01"
}

object AndroidX {
    @Suppress("MemberVisibilityCanBePrivate")
    const val composeVersion = "1.4.1"
    const val composeCompilerVersion = "1.4.5"
    private const val lifecycleVersion = "2.6.1"
    private const val testVersion = "1.5.0"
    private const val extVersion = "1.1.5"
    private const val roomVersion = "2.5.1"
    private const val coreKtxVersion = "1.10.0"
    private const val appcompatVersion = "1.6.1"
    private const val fragmentVersion = "1.5.6"
    private const val constrainyLayoutVersion = "2.1.4"
    private const val navigationVersion = "2.5.3"
    private const val splashScreenVersion = "1.0.1"

    const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"

    const val appCompat = "androidx.appcompat:appcompat:$appcompatVersion"
    const val fragment = "androidx.fragment:fragment-ktx:$fragmentVersion"

    const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constrainyLayoutVersion"

    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:$navigationVersion"

    const val splashScreen = "androidx.core:core-splashscreen:$splashScreenVersion"

    const val composeRuntime = "androidx.compose.runtime:runtime:$composeVersion"
    const val composeFoundation = "androidx.compose.foundation:foundation:$composeVersion"
    const val composeLayout = "androidx.compose.foundation:foundation-layout:$composeVersion"
    const val composeUi = "androidx.compose.ui:ui:$composeVersion"
    const val composeMaterial = "androidx.compose.material:material:$composeVersion"
    const val composeMaterialIconsExtended = "androidx.compose.material:material-icons-extended:$composeVersion"
    const val composeAnimations = "androidx.compose.animation:animation:$composeVersion"
    const val activityCompose = "androidx.activity:activity-compose:1.4.0"
    const val navigationCompose = "androidx.navigation:navigation-compose:$navigationVersion"
    const val lifecycleViewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion"
    const val composeTooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
    const val composeTestManifest = "androidx.compose.ui:ui-test-manifest:$composeVersion"
    const val composeTestJunit = "androidx.compose.ui:ui-test-junit4:$composeVersion"

    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"

    const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
    const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    const val roomCompiler = "androidx.room:room-compiler:$roomVersion"

    const val testCore = "androidx.test:core:$testVersion"
    const val testRules = "androidx.test:rules:$testVersion"
    const val testEspressoCore = "androidx.test.espresso:espresso-core:3.4.0"
    const val testExtJunit = "androidx.test.ext:junit-ktx:$extVersion"
}

object ThirdParties{
    private const val viewBindingVersion = "1.5.9"
    private const val glideVersion = "4.15.1"
    private const val glideTransformationsVersion = "4.3.0"
    private const val loggerVersion = "5.0.1"

    const val viewBinding = "com.github.kirich1409:viewbindingpropertydelegate-noreflection:$viewBindingVersion"
    const val glide = "com.github.bumptech.glide:glide:$glideVersion"
    const val glideTransformations = "jp.wasabeef:glide-transformations:$glideTransformationsVersion"
    const val logger = "com.jakewharton.timber:timber:$loggerVersion"
}

object UnitTests {
    private const val version = "4.13.2"
    const val junit = "junit:junit:$version"
}