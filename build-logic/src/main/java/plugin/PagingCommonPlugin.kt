package plugin

import extention.getVersionCatalog
import extention.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.dsl.DependencyHandler

internal class PagingCommonPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configurePagingCommon()
        }
    }
}

internal fun Project.configurePagingCommon() {
    applyDependency(dependencies, getVersionCatalog())
}

private fun applyDependency(handler: DependencyHandler, libs: VersionCatalog) = with(handler) {
    implementation(libs.findLibrary("paging-common").get())
}