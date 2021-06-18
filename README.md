# Link Resolver for Android

[![Latest Release](https://jitpack.io/v/BenMMcLean/LinkResolver.svg)](https://jitpack.io/#BenMMcLean/LinkResolver/)

A simple library for handling links in Android.

## Installation

Add JitPack to your project level `build.gradle` file
```groovy
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```
Then, include the modules as required
```groovy
dependencies {
    implementation 'com.github.BenMMcLean.LinkResolver:0.5.0'
}
```

## Usage

A link resolver can be instantiated using the static ```create()``` methods:
```kotlin
val resolver = LinkResolver.create(object : GlobalContextStrategy {
    override fun fetchGlobalContext(): Context {
      return YourApplicationSubclass.staticApplicationContextInstance
    }
})
```
If a DI framework like Dagger is being used, this code (along with attached interceptors) should be packaged in a module.


Additional link interceptors can then be attached; for example, if NavControllers are being used, the following code will automatically handle package deeplinks
```kotlin
resolver.register(PackageDeeplinkHandler(navController))
```
