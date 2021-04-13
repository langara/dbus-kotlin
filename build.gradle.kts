plugins {
    id("pl.mareklangiewicz.deps")
}

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

version = DBusKotlinKonf.verStr
group = DBusKotlinKonf.group
description = DBusKotlinKonf.description

allprojects {
    repositories {
        mavenCentral()
        maven(Repos.jitpack)
    }
}
