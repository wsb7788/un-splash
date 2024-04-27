package plugin

import extention.getPluginId
import extention.getVersionCatalog
import extention.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.plugins.PluginManager
import org.gradle.kotlin.dsl.getByType

internal class SerializationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureSerialization()
        }
    }
}

internal fun Project.configureSerialization() {
    applyPlugin(pluginManager, getVersionCatalog())
    applyDependency(dependencies, getVersionCatalog())
}

private fun applyDependency(handler: DependencyHandler, libs: VersionCatalog) = with(handler) {
    implementation(libs.findLibrary("serialization").get())
}

private fun applyPlugin(manager: PluginManager, libs: VersionCatalog) = with(manager) {
    apply(libs.getPluginId("kotlin-jvm"))
    apply(libs.getPluginId("serialization"))
}
