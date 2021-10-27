object Libs {

    // Kotlin
    const val kotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerializationVersion}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"

    // Support
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val materialDesign = "com.google.android.material:material:${Versions.materialDesignVersion}"

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

    // Tests
    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val jUnitAndroid = "androidx.test.ext:junit:${Versions.jUnitAndroidVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"

}