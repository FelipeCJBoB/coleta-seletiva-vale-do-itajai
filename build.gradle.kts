// Arquivo raiz: só declara os plugins. Quem usa de fato é o composeApp/build.gradle.kts.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.sqldelight) apply false
}
