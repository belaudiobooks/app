# Mobile application audiobooks.by

At this stage we assume application will represent highly available catalog with content from https://audiobooks.by/ for Android and iOS platforms.

![Build](https://github.com/belaudiobooks/app/actions/workflows/build.yml/badge.svg)

Application going to be implemented on [Kotlin Multiplatform Platform (KMP)](https://www.jetbrains.com/kotlin-multiplatform/) with separate UI part for each platform ([SwiftUI](https://developer.apple.com/xcode/swiftui/) for iOS and [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/) for Android).

In case you are new in KMP it is highly recommended to pass this two tutorials:
* [Get started with Compose Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform-getting-started.html)
* [Create a multiplatform app using Ktor and SQLDelight](https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-ktor-sqldelight.html)

More samples of KMP projects: https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-samples.html

Application will utilize https://audiobooks.by/data.json file as a datasource. Datasource contract description: https://github.com/belaudiobooks/website/blob/main/tests/schema.data.json

## Supported API versions

**Android** - tbd...

**iOS** - tbd...

## Project structure

Following [Kotlin Multiplatform Wizard](https://kmp.jetbrains.com/) generated structure:

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* `/shared` is for the code that will be shared between all targets in the project.
  The most important subfolder is `commonMain`. If preferred, you can add code to the platform-specific folders here too.

## Development environment

tbd...

## Test, build, run...

Clean:
```
./gradlew clean
```

Build (including tests for both platforms):
```
./gradlew build --stacktrace
```

Generate Kotlin classes:
```
./gradlew generateSqlDelightInterface
```

## Libraries

* [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) - json serialization
* [kotlinx-datetime](https://github.com/Kotlin/kotlinx-datetime) - date/time library
* [Ktor](https://ktor.io/docs/getting-started-ktor-client-multiplatform-mobile.html) - http client
* [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) - coroutines support
* [SQLDelight](https://cashapp.github.io/sqldelight/2.0.1/multiplatform_sqlite/) - data cache
