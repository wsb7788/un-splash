import extention.implementation
import plugin.configureKotlin
import plugin.configureSerialization

configureKotlin()
configureSerialization()

dependencies {
    implementation(project(":domain:entity"))
}