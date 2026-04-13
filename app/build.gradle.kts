plugins {
    alias(libs.plugins.android.application)
//    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp) // instead of kapt
//    id("com.google.devtools.ksp")
//    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.nic.mparivahan"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.nic.mparivahan"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

//    sourceSets { getByName("main") { java.srcDir("build/generated/ksp/main/kotlin") } }
//    sourceSets["main"].java.srcDirs("src/main/java", "src/main/kotlin")
    sourceSets["main"].java.directories.addAll(listOf("src/main/java", "src/main/kotlin", "build/generated/ksp/main/kotlin"))

}

kotlin {
    compilerOptions { jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        freeCompilerArgs = listOf ("-XXLanguage:+PropertyParamAnnotationDefaultTargetMode") } }

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx.v284)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)

    // ---------- Hilt ----------
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // ---------- Room ----------
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // ---------- Retrofit / OkHttp ----------
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.retrofit.scalars)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    implementation("com.google.firebase:firebase-messaging:23.4.1")

//    implementation("com.squareup.retrofit2:retrofit:2.9.0")
//    implementation("com.squareup.okhttp3:okhttp:4.9.3")
//    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
//    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")
//    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")
//    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    implementation("com.google.code.gson:gson:2.10.1")

    implementation("androidx.datastore:datastore-preferences:1.2.1")

    implementation("androidx.security:security-crypto:1.1.0-alpha06")
    implementation("org.bouncycastle:bcprov-jdk18on:1.78")
}

ksp {
    arg("correctErrorTypes", "true")
    arg("room.incremental", "true")
    arg("room.schemaLocation", "$projectDir/schemas")
}

