
class Dependencies {

    object Test {
        const val junit = "junit:junit:${Versions.Test.junit}"
        const val junitExt = "androidx.test.ext:${Versions.Test.junit}"
        const val mockk = "io.mockk:mockk:${Versions.Test.mockk}"
        const val mockitoKotlin =
            "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.Test.mockitoKotlin}"
        const val mockitoCore = "org.mockito:mockito-core:${Versions.Test.mockitoKotlin}"
        const val mockitoInline = "org.mockito:mockito-inline:${Versions.Test.mockitoInline}"
        const val mockitoAndroid = "org.mockito:mockito-android:${Versions.Test.mockitoAndroid}"
        const val coroutinesTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Kotlin.coroutines}"
        const val archCoreTest = "androidx.arch.core:core-testing:${Versions.Test.archCore}"
        const val mockWebServer =
            "com.squareup.okhttp3:mockwebserver:${Versions.Test.mockWebServer}"
        const val robolectric = "org.robolectric:robolectric:${Versions.Test.robolectric}"
        const val koinTest = "io.insert-koin:koin-test:${Versions.Koin.koin}"
        const val koinTestJunit = "io.insert-koin:koin-test-junit4:${Versions.Koin.koin}"
        const val koinCore = "io.insert-koin:koin-test-core:${Versions.Koin.koin}"
    }

    object AndroidTest {
        const val core = "androidx.test:core:${Versions.AndroidTest.core}"
        const val androidjunit = "androidx.test.ext:junit:${Versions.AndroidTest.androidjunit}"
        const val androidjunitKtx =
            "androidx.test.ext:junit-ktx:${Versions.AndroidTest.androidjunit}"
        const val espressoCore =
            "androidx.test.espresso:espresso-core:${Versions.AndroidTest.espressoCore}"
        const val espressoContrib =
            "androidx.test.espresso:espresso-contrib:${Versions.AndroidTest.espressoCore}"
        const val roomTest = "androidx.room:room-testing:${Versions.AndroidX.room}"
        const val runner = "androidx.test:runner:${Versions.AndroidTest.runner}"
        const val rule = "androidx.test:rules:${Versions.AndroidTest.rule}"
        const val testCoreKtx = "androidx.test:core-ktx:${Versions.AndroidTest.testCoreKtx}"
        const val fragmentTesting =
            "androidx.fragment:fragment-testing:${Versions.AndroidTest.fragmentTesting}"
        const val coreTesting =
            "androidx.arch.core:core-testing:${Versions.AndroidTest.coreTesting}"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompat}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.AndroidX.coreKtx}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintLayout}"
        const val material = "com.google.android.material:material:${Versions.AndroidX.material}"
        const val room = "androidx.room:room-runtime:${Versions.AndroidX.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.AndroidX.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.AndroidX.room}"
        const val roomCoroutines = "androidx.room:room-coroutines:${Versions.AndroidX.room}"
        const val fragment = "androidx.fragment:fragment-ktx:${Versions.AndroidX.fragment}"
        const val navigation =
            "androidx.navigation:navigation-ui-ktx:${Versions.AndroidX.navigation}"
        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.AndroidX.navigation}"
        const val navigationRuntime =
            "androidx.navigation:navigation-runtime-ktx:${Versions.AndroidX.navigation}"
        const val lifecycleViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.lifecycle}"
        const val lifecycleExtensions =
            "androidx.lifecycle:lifecycle-extensions:${Versions.AndroidX.lifecycleExtension}"
        const val lifecycleCommon =
            "androidx.lifecycle:lifecycle-common-java8:${Versions.AndroidX.lifecycle}"
        const val lifecycleRuntime =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.lifecycle}"
        const val lifecycleLiveData =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.AndroidX.lifecycle}"

        const val support = "androidx.legacy:legacy-support-v4:${Versions.support}"
        const val crypto = "androidx.security:security-crypto:${Versions.crypto}"
        const val paging = "androidx.paging:paging-runtime:${Versions.AndroidX.paging}"
        const val multidex = "androidx.multidex:multidex:${Versions.AndroidX.multidex}"

        const val javaxInject = "javax.inject:javax.inject:1"

    }

    object AndroidTools {
        const val desugaring = "com.android.tools:desugar_jdk_libs:${Versions.desugaring}"
    }

    object Dagger {
        const val daggerGoogleHilt = "com.google.dagger:hilt-android:${Versions.Dagger.dagger}"
        const val daggerGoogleHiltCompiler =
            "com.google.dagger:hilt-compiler:${Versions.Dagger.dagger}"
        const val daggerGoogleHiltCompilerAndroid =
            "com.google.dagger:hilt-android-compiler:${Versions.Dagger.dagger}"
        const val daggerHiltCompilerCore = "com.google.dagger:hilt-core:${Versions.Dagger.dagger}"
        const val daggerHiltWork = "androidx.hilt:hilt-work:${Versions.Dagger.dagger}"
        const val hiltAndroidxCompiler = "androidx.hilt:hilt-compiler:${Versions.Dagger.dagger1_}"
        const val hiltAndroidxViewModel =
            "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.Dagger.dagger1_}"
        const val hiltNavigationFragment =
            "androidx.hilt:hilt-navigation-fragment:${Versions.Dagger.dagger1_}"
    }


    object Koin {
        const val koinCore = "io.insert-koin:koin-core:${Versions.Koin.koin2_3}"
        const val koin = "io.insert-koin:koin-android:${Versions.Koin.koin2_3}"
        const val koin2 = "org.koin:koin-android:${Versions.Koin.koin2}"
        const val koin2ViewModel = "org.koin:koin-android-viewmodel:${Versions.Koin.koin2}"

        const val koinCoreExt = "io.insert-koin:koin-core-ext:${Versions.Koin.koin2_3}"
        const val koinExt = "io.insert-koin:koin-androidx-ext:${Versions.Koin.koin2_3}"
        const val koinFragment = "io.insert-koin:koin-androidx-fragment:${Versions.Koin.koin2_3}"

        //        const val koinCompiler = "io.insert-koin:koin-androidx-scope:${Versions.Koin.koin}"
        const val koinViewModel = "io.insert-koin:koin-androidx-viewmodel:${Versions.Koin.koin2_3}"
        const val koinScope = "io.insert-koin:koin-androidx-scope:${Versions.Koin.koin2_3}"
    }

    object Kotlin {
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Kotlin.kotlin}"
        const val stdlibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.Kotlin.kotlin}"
        const val stdlibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.Kotlin.kotlin}"
        const val ktxCore = "androidx.core:core-ktx:${Versions.Kotlin.ktx}"
        const val ktxSerializationCore =
            "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.Kotlin.ktxSerialization}"
        const val coroutineAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutines}"
        const val coroutineCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutines}"
    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }

    object OkHttp {
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
        const val httpLoggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

        //        const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
//        const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    }

    object DeveloperRetrofitTools {
        const val retrofitKotlinCoroutines =
            "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.DeveloperToolsKotlinRetrofit.kotlinCoroutines}"
    }

    object DeveloperTools {
        const val leakCanary =
            "com.squareup.leakcanary:leakcanary-android:${Versions.DeveloperTools.leakCanary}"
        const val timber = "com.jakewharton.timber:timber:${Versions.DeveloperTools.timber}"
    }
}