object Libs {

    // Kotlin
    const val kotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerializationVersion}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"

    // Support
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"

    // Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitRxJava2Adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitRxJava2AdapterVersion}"
    const val kotlinSerializationConverter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.kotlinSerializationConverterVersion}"

    // OkHttp
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"
    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLoggingInterceptorVersion}"

    // RxJava
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJavaVersion}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"

    // Koin
    const val koinCore = "io.insert-koin:koin-core:${Versions.koinVersion}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koinVersion}"
    const val koinViewModel = "io.insert-koin:koin-androidx-viewmodel:${Versions.koinVersion}"
    const val koinTest = "io.insert-koin:koin-test:${Versions.koinVersion}"

    // Tests
    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val jUnitAndroid = "androidx.test.ext:junit:${Versions.jUnitAndroidVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockitoVersion}"
    const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInlineVersion}"
    const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlinVersion}"
    const val arcCore = "androidx.arch.core:core-testing:${Versions.androidCoreVersion}"
    const val fragmentTesting = "androidx.fragment:fragment-testing:${Versions.fragmentTestingVersion}"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espressoContribVersion}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.mockWebServerVersion}"

    // Layout
    const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2Version}"
    const val materialDesign = "com.google.android.material:material:${Versions.materialDesignVersion}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottieVersion}"
}