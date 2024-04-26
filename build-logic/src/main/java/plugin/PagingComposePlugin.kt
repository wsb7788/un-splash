package plugin

import extention.getVersionCatalog
import extention.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.dsl.DependencyHandler

internal class PagingComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configurePagingCompose()
        }
    }
}

internal fun Project.configurePagingCompose() {
    applyDependency(dependencies, getVersionCatalog())
}

private fun applyDependency(handler: DependencyHandler, libs: VersionCatalog) = with(handler) {
    implementation(libs.findLibrary("paging-compose").get())
    implementation(libs.findLibrary("paging-runtime").get())
}