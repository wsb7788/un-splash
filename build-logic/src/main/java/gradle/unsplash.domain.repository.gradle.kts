import extention.implementation
import plugin.configureKotlin
import plugin.configurePagingCommon

configureKotlin()
configurePagingCommon()

dependencies {
    implementation(project(":domain:entity"))
}