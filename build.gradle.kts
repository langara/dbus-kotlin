buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Deps.kotlinGradlePlugin)
    }
}

version = Konf.verStr
group = Konf.group
description = Konf.description

allprojects {
    repositories {
        mavenCentral()
        maven(Repos.jitpack)
    }
}
