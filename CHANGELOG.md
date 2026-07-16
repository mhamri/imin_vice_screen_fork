
## 1.1.1

* fixed: Updated native library (libfree_image.so) to support 16KB page size
  alignment for arm64-v8a, required by Android 15+ devices.
* changed: Upgraded freeimagelibrary to v1.5 (NDK r28, minSdk 24).

## 1.1.0

* changed: Modernized Android toolchain — Kotlin DSL build scripts, compileSdk 35,
  minSdk 21, Java 17, manifest `namespace`; plugin module on AGP 8.7.
* changed: Example app migrated to Flutter's declarative Gradle plugin loader and
  the Flutter 3.44 toolchain floor — AGP 8.11.1, Gradle 8.14, built-in Kotlin.
* fixed: Removed dead/invalid manifest permissions, including
  `MANAGE_EXTERNAL_STORAGE`, which blocked Google Play publication of apps using
  this plugin.
* changed: Bumped `flutter_lints` to ^5, `logger` to ^2,
  `plugin_platform_interface` to ^2.1.8; Dart SDK ^3.5.0 / Flutter >=3.24.0.

## 1.0.0

* added: Add LCD vice screen related methods

## 0.0.1

* added: Add secondary screen related methods
