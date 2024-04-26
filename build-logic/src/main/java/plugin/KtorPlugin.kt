package plugin

import extention.getVersionCatalog
import extention.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.dsl.DependencyHandler

internal class KtorPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureKtor()
        }
    }
}

internal fun Project.configureKtor() {
    applyDependency(dependencies, getVersionCatalog())
}

private fun applyDependency(handler: DependencyHandler, libs: VersionCatalog) = with(handler) {
    implementation(libs.findBundle("ktor").get())
}