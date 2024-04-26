import extention.implementation
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import plugin.configureInject
import plugin.configureKotlin
import plugin.configurePagingCommon

configureKotlin()
configureInject()
configurePagingCommon()

dependencies {
    implementation(project(":domain:repository"))
    implementation(project(":domain:entity"))
}