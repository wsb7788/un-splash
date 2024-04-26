package plugin

import extention.getVersionCatalog
import extention.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

internal class FeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureFeature()
        }
    }
}

internal fun Project.configureFeature() {
    applyDependency(dependencies, getVersionCatalog())
}

private fun applyDependency(handler: DependencyHandler, libs: VersionCatalog) = with(handler){
    implementation(project(":domain:entity"))
    implementation(project(":domain:usecase"))


    implementation(libs.findLibrary("hilt-compose").get())
}