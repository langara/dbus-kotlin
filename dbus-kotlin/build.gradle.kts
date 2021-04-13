plugins {
    kotlin("multiplatform") version "1.4.32"
    `maven-publish`
    id("pl.mareklangiewicz.deps")
}

group = DBusKotlinKonf.group
version = DBusKotlinKonf.verStr

kotlin {
    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
                useIR = true
            }
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform {
                if (System.getenv("JITPACK") != null) excludeTags("integration")
            }
        }
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(Deps.kotlinReflect)
                implementation(Deps.kotlinxCoroutinesCore)
                implementation(Deps.dbusJava)
//                implementation(Deps.dbusJavaOsgi)
                implementation(project(":dbus-generated"))

                implementation(Deps.slf4jSimple)
//                implementation(Deps.log4j2api)
//                implementation(Deps.log4j2core)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(Deps.dbusJavaUtils)
                implementation(kotlin("test-junit5"))
                implementation(Deps.junit5)
                runtimeOnly(Deps.junit5engine)
                runtimeOnly(Deps.uspek)
            }
        }
    }
}
