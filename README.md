# Spring MVC Template

## Introduce
This project is a template containing the basic features and configurations of a Spring MVC project.

Included in the project are the following

### Project Info
 - Web Framework: SpringBoot 2.7.12
 - Base Language: Kotlin
 - Dependecies
   - QueryDSL
   - Spock Framework for Testing
   - H2 Database
 - Configuration & Features
   - QuerydslRepositorySupport
     - You can use QuerydslRepositorySupport easily by extending `QuerydslBaseRepository` at CustomRepositoryImpl
     - QuerydslBaseRepository is located in `com.tkppp.springtemplate.common.domain`
   - H2 Database
     - By default, H2 Database work `local mode`
     - If you want to use `in-memory mode`, use test profile
   - API Logging
     - Simple api logging is implemented by filter, interceptor and ContentCachingRequest/ResponseWrapper
   - Exception Handling
     - RestControllerAdvice is implemented

## How to use
It is based on IntelliJ Ultimate

1. clone or fork project
2. edit `settings.gradle.kts` to changing module name
3. rename src directory name, `SpringTemplateApplication.kt` whatever you want