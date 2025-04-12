plugins {
    alias(libs.plugins.convetion.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

android {
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    namespace = "ru.drweb.test.core"
}

dependencies {
    // Kotlin
    implementation(libs.kotlinx.datetime)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // UI
    implementation(libs.bundles.compose)
    implementation(libs.bundles.accompanist)

    // DI
    implementation(libs.koin)

    // Logging
    implementation(libs.logger.kermit)

    implementation(libs.security.crypto)
    implementation(libs.security.crypto.ktx)

    // Architecture
    implementation(libs.bundles.decompose)
    implementation(libs.bundles.replica)
    api(libs.moko.resources)
    implementation(libs.moko.resourcesCompose)

    // Debugging
    debugImplementation(libs.bundles.hyperion)
}
