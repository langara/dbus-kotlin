plugins {
    kotlin("multiplatform")
    `maven-publish`
}

group = Konf.group
version = Konf.verStr

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()
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
