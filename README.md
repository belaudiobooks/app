# Mobile application audiobooks.by

At this stage we assume application will represent highly available catalog with content from https://audiobooks.by/ for Android and iOS platforms.

Application going to be implemented on [Kotlin Multiplatform Platform (KMP)](https://www.jetbrains.com/kotlin-multiplatform/) with separate UI part for each platform ([SwiftUI](https://developer.apple.com/xcode/swiftui/) for iOS and [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/) for Android).

In case you are new in KMP it is highly recommended to pass this two tutorials:
* [Get started with Compose Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform-getting-started.html)
* [Create a multiplatform app using Ktor and SQLDelight](https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-ktor-sqldelight.html)

Application will utilize https://audiobooks.by/data.json file as a datasource. Data source contract description: https://github.com/belaudiobooks/website/blob/main/tests/schema.data.json

## Development environment

tbd...

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

## Build and run

tbd...