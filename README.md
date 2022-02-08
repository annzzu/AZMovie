# AZMovie

![ANDROID](https://badgen.net/badge/OS/Android?icon=https://raw.githubusercontent.com/androiddevnotes/awesome-android-kotlin-apps/master/assets/android.svg&color=3ddc84)
[![Gradle](https://img.shields.io/badge/gradle-7.0.2-blue.svg)](https://lv.binarybabel.org/catalog/gradle/latest)
[![LANGUAGE](https://badgen.net/badge/language/Kotlin?)](https://android-arsenal.com/api?level=21)
![API](https://img.shields.io/badge/API-21%2B-blue.svg?style=flat)
![Github](https://img.shields.io/badge/GitHub-annzzu-blue.svg?style=flat)
![GMAIL](https://img.shields.io/badge/Gmail-anaz.zurabashvili@gmail.com-blue.svg?style=flat)

I have no right over the api itself and I do not guarantee that the material that will be present in this repository will be up-to-date and precise.

##  _AZMovie is Android Small App for watching movies and series without any ads_ AZMOVIE allows users to do the following



<img src="screenshots/demo.gif" width="336" align="right" hspace="20">

### AZMOVIE allows users to do the following

1. View popular, now playing, upcoming and top rated movies ans series.
2. View Individual movies and get the movie data such as cast, movie duration, summary and description.
3. Search for Movies and Series.
4. Watch Movies And Series with subtitles.



## Project characteristics and tech-stack
* Tech-stack
    * [100% Kotlin](https://kotlinlang.org/) + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform background operations
    * [Retrofit](https://square.github.io/retrofit/) - networking
    * [Jetpack](https://developer.android.com/jetpack)
        * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) - in-app navigation
        * [Flow](https://developer.android.com/kotlin/flow) 
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way
    * [Kodein](https://docs.kodein.org/) - dependency injection
    * [Glide](https://github.com/bumptech/glide) - image loading library
    * [Lottie](http://airbnb.io/lottie) - animation library
    * [Lottie](http://airbnb.io/lottie) - animation library

* Modern Architecture
    * Clean Architecture (at feature module level)
    * Single activity architecture using [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)
    * MVVM  (presentation layer)
    * [Dynamic feature modules](https://developer.android.com/studio/projects/dynamic-delivery)
    * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), 
    * [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation))
    * [Exoplayer](https://developer.android.com/guide/topics/media/exoplayer) - for streaming films

* UI
    * [Material design](https://material.io/design)
    * [Exoplayer](https://developer.android.com/guide/topics/media/exoplayer)
* Gradle
    * [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
    * Custom tasks
    * Plugins ([SafeArgs](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args),
      [android-junit5](https://github.com/mannodermaus/android-junit5))
    * [Dependency locks](https://docs.gradle.org/current/userguide/dependency_locking.html)
    * [Versions catalog](https://docs.gradle.org/7.0-milestone-1/userguide/platforms.html)




## Movie Subtitles -> 

![Movie Subtitle Demo](screenshots/movie_subtitle.gif "Movie Subtitle")
<!-- 
![Home Page](screenshots/home_page.jpg "Home Page for Top films and Series also Trailers -> Search button")
![Movie Page](screenshots/movie_buttom_sheet_shot.png "Movie Page with its details and buttom sheet for start stream the language you choose)
![Series Buttom Sheet](screenshots/rick&morty3.jpg "Series buttom sheet to choose season and then serie ")


![Home Page](screenshots/home_page.jpg "Home Page for Top films and Series also Trailers -> Search button")
![Movie Page](screenshots/movie_buttom_sheet_shot.png "Movie Page with its details and buttom sheet for start stream the language you choose)
![Series Buttom Sheet](screenshots/rick&morty3.jpg "Series buttom sheet to choose season and then serie ")
![Search](screenshots/search_shrek.jpg "Series buttom sheet to choose season and then serie ") -->

<div>
   <img src="screenshots/home_page.jpg" width="240" >
   <img src="screenshots/movie_bottom_sheet_shot.jpg" width="240" >
   <img src="screenshots/rick&morty3.jpg" width="240" >
   <img src="screenshots/search_shrek.jpg" width="240">
 </div>
   
## Getting started

There are a few ways to open this project.

### Android Studio

1. `Android Studio` -> `File` -> `New` -> `From Version control` -> `Git`
2. Enter `https://github.com/annzzu/AZMovie.git` into URL field an press `Clone` button

### Command-line + Android Studio

1. Run `git clone https://github.com/annzzu/AZMovie.git` command to clone project
2. Open `Android Studio` and select `File | Open...` from the menu. Select cloned directory and press `Open` button


`gradle.properties App API`
```gradle.properties
SERVER_URL=https://api.adjaranet.com/api/v1/
```

## Inspiration

This is project is a movie app, to watching your favorite series and movies with subtitles without any adds, for full comfort.





### Android projects by me
- [Covid Restrictions](https://github.com/annzzu/AZ) - AZ - For finding worldwide covid restriction and planing Travel.
