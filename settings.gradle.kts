pluginManagement {
    repositories {
        jcenter()
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

includeBuild("../deps.kt")

rootProject.name = "dbus-kotlin" // should always be kept the same as Konf.name

include(":dbus-kotlin")
include(":dbus-generated")
