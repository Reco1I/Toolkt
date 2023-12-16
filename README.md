# Toolkt
[![](https://jitpack.io/v/Reco1I/Toolkt.svg)](https://jitpack.io/#Reco1I/Toolkt)

Toolkt is a library that contains functions and extensions to improve Android development. Originally intended to be a module in the [rimu! project](https://github.com/Reco1I/rimu).

# Usage
In order to use this library in your project you add the dependency in your app's module `build.gradle`.
```gradle
dependencies {
    implementation 'com.github.Reco1I:Toolkt:1.0.2'
}
```

Make sure to have Maven and Jitpack repository declared in the root `build.gradle`.
```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

Or you can add the project as a library module in your app.
