pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    includeBuild("build-logic")
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "UnSplash"
include(":app")
include(":feature:home")
include(":domain:entity")
include(":domain:repository")
include(":domain:usecase")
include(":data:local")
include(":data:remote")
include(":data:model")
include(":data:repository")