import extention.implementation
import org.gradle.kotlin.dsl.dependencies
import plugin.configureInject
import plugin.configureKotlin
import plugin.configurePagingCommon

configureKotlin()
configurePagingCommon()
configureInject()

dependencies {
    implementation(project(":data:local"))
    implementation(project(":data:remote"))
    implementation(project(":data:model"))
    implementation(project(":domain:repository"))
    implementation(project(":domain:entity"))
}