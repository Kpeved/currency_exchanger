object Modules {
    const val core = ":core"
    const val coreNetwork = ":core_network"
    const val coreLocalization = ":core_localization"

    const val featureExchange = ":feature_exchange"
    const val featureExchangeNetwork = ":feature_exchange_network"
}

object Versions {
    const val kotlin = "1.3.71"
    const val compileSdk = 29
    const val targetSdk = 29
    const val minSdk = 21
    const val buildTools = "29.0.3"

    const val androidGradlePlugin = "3.5.3"
    const val gradleVersionPlugin = "0.28.0"

    const val coreKtx = "1.3.0-beta01"
    const val androidXConstraintLayout = "2.0.0-beta4"
    const val appCompat = "1.2.0-beta01"
    const val recyclerView = "1.2.0-alpha02"

    const val material = "1.2.0-alpha05"
    const val glide = "4.11.0"
    const val retrofit = "2.8.1"
    const val retrofitAdapter = "2.8.1"
    const val okhttpLogging = "4.5.0"

    const val koin = "2.1.5"
    const val timber = "4.7.1"

    const val viewModel = "2.3.0-alpha01"

    const val rxJava = "2.2.19"
    const val rxKotlin = "2.4.0"
    const val rxAndroid = "2.1.1"

    const val junit = "4.13"
    const val junitExt = "1.1.1"
    const val espresso = "3.2.0"
}

object Deps {

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val gradleVersionsPlugin = "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleVersionPlugin}"

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val androidXConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.androidXConstraintLayout}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"

    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitAdapter}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLogging}"

    const val koinScope = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val koinCore = "org.koin:koin-core:${Versions.koin}"
    const val koinExt = "org.koin:koin-core-ext:${Versions.koin}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModel}"
    const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.viewModel}"

    const val material = "com.google.android.material:material:${Versions.material}"

    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideAnnotationProcessor = "com.github.bumptech.glide:compiler:${Versions.glide}"

    const val junit = "junit:junit:${Versions.junit}"
    const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}