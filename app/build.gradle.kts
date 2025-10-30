plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services") // PLUGIN WAJIB FIREBASE
}

android {
    namespace = "com.example.bloombelly"
    compileSdk = 36 // This is correct, usually defined directly under 'android'

    defaultConfig {
        applicationId = "com.example.bloombelly"

        // PASTE minSdk DI SINI:
        minSdk = 29 // Fix: Ensure this is inside the 'defaultConfig' block

        targetSdk = 36 // This is also correct
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    // AndroidX & Core Dependencies
    implementation(libs.appcompat)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Material & UI (Pilih versi 1.12.0)
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.cardview:cardview:1.0.0") // Tetap simpan CardView

    implementation("com.google.android.gms:play-services-auth:21.1.1")

    // Firebase
    implementation("com.google.firebase:firebase-auth:23.1.0")
    implementation("com.google.android.gms:play-services-auth:21.1.1")
    implementation("com.google.firebase:firebase-analytics:22.1.2")
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)


    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)


}
