package plugin

import extention.getVersionCatalog
import extention.implementation
import extention.kapt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.PluginManager
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

internal class RoomPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureRoom()
        }
    }
}

internal fun Project.configureRoom() {
    applyPlugin(pluginManager, getVersionCatalog())
    applyDependency(dependencies, getVersionCatalog())
    applyKapt(extensions.getByType())
}

private fun applyPlugin(manager: PluginManager, libs: VersionCatalog) = with(manager) {
    apply("kotlin-kapt")
}

private fun applyDependency(handler: DependencyHandler, libs: VersionCatalog) = with(handler){
    implementation(libs.findLibrary("room-runtime").get())
    implementation(libs.findLibrary("room-ktx").get())
    implementation(libs.findLibrary("room-paging").get())
    kapt(libs.findLibrary("room-compiler").get())
}

private fun applyKapt(extension: KaptExtension) = with(extension) {
    correctErrorTypes = true
}