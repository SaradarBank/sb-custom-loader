# Show-Loader-Library
[![](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16#l16)	[![](https://img.shields.io/badge/Platform-Android-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16#l16)	[![](https://img.shields.io/badge/Language-Kotlin-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16#l16)	[![](https://img.shields.io/badge/Version-0.1.0-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16#l16)

An Android library that shows a simple loader.

## Installation

Add the dependency in your `build.gradle`:
```groovy
repositories {
		maven {
			url 'https://jitpack.io' 
		}
}

dependencies {
        implementation 'com.github.SaradarBank:Show-Loader-Library:0.1.0'   
}
```
Or Maven:
```groovy
<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
<dependency>
	    <groupId>com.github.SaradarBank</groupId>
	    <artifactId>Show-Loader-Library</artifactId>
	    <version>0.1.0</version>
	</dependency>
```

## Example

1) To show the loader:
```java
val showloading = ShowLoader()
showloading.setLoaderMessage("Please Wait")
showloading.show(supportFragmentManager, "")
```
2) To hide the loader:

```java
showloading.dismiss()
```
