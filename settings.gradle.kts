try {
    val pe = Class.forName("java.lang.ProcessEnvironment")
    val envField = pe.getDeclaredField("theUnmodifiableEnvironment")
    envField.isAccessible = true
    val unmodifiableEnv = envField.get(null)
    if (unmodifiableEnv != null) {
        val mField = unmodifiableEnv.javaClass.getDeclaredField("m")
        mField.isAccessible = true
        @Suppress("UNCHECKED_CAST")
        (mField.get(unmodifiableEnv) as? MutableMap<String, String>)?.remove("ANDROID_PREFS_ROOT")
    }
} catch (e: Throwable) {}

try {
    val pe = Class.forName("java.lang.ProcessEnvironment")
    val envField = pe.getDeclaredField("theCaseInsensitiveEnvironment")
    envField.isAccessible = true
    @Suppress("UNCHECKED_CAST")
    (envField.get(null) as? MutableMap<String, String>)?.remove("ANDROID_PREFS_ROOT")
} catch (e: Throwable) {}

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PhotoOrganizerApp"
include(":app")
project(":app").projectDir = file("App/app")
