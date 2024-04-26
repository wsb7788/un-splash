package plugin

import com.android.build.api.dsl.CommonExtension
import extention.androidExtension
import extention.debugImplementation
import extention.getVersionCatalog
import extention.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.dsl.DependencyHandler

internal class ComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureCompose()
        }
    }
}

internal fun Project.configureCompose() {
    applyAndroidExtensions(androidExtension, getVersionCatalog())
    applyDependency(dependencies, getVersionCatalog())

}

private fun applyAndroidExtensions(
    androidExtension: CommonExtension<*, *, *, *, *>,
    libs: VersionCatalog
) = with(androidExtension) {

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion =
            libs.findVersion("kotlinCompilerExtensionVersion").get().toString()
    }

}

private fun applyDependency(handler: DependencyHandler, libs: VersionCatalog) = with(handler) {

    implementation(platform(libs.findLibrary("compose-bom").get()))
    implementation(libs.findLibrary("material3").get())
    implementation(libs.findLibrary("activity-compose").get())

    implementation(libs.findLibrary("ui").get())
    implementation(libs.findLibrary("ui-tooling-preview").get())
    debugImplementation(libs.findLibrary("ui-tooling").get())

    //compose-lifecycle(collectAsStateWithLifecycle)
    implementation(libs.findLibrary("lifecycle-runtime-compose").get())

    implementation(libs.findLibrary("navigation-compose").get())
}