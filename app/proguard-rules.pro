# Add any project-specific keep rules here

# Preserve Kotlin metadata
-keepattributes *Annotation*, InnerClasses
-dontnote kotlin.internal.**
-dontnote kotlin.reflect.jvm.**
-keep class kotlin.Metadata { *; }

# Preserve Compose classes
-keep class androidx.compose.** { *; }

# Preserve application class
-keep class com.mashaffer.myiteminjarestimator.** { *; }

# Keep all Android support libraries
-keep class androidx.** { *; }

# Prevent obfuscation of Android lifecycle components
-keepnames class androidx.lifecycle.** { *; }

# Keep native methods
-keepclasseswithmembernames,includedescriptorclasses class * {
    native <methods>;
}