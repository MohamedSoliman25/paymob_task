# Movie Task
## Overview

MovieApp is a simple Android application that displays a list of movies fetched from The Movie Database (TMDb) API. Users can browse through movies, view detailed information, and mark movies as favorites. The app follows the Clean Architecture pattern with MVVM and uses Hilt for dependency injection, Retrofit for network requests, Room for local data storage, and ViewBinding for view management.

## Features

- **List of Movies**: Displays a list of movies released in 2024, sorted by vote average.
- **Movie Details**: View detailed information about a selected movie, including title, overview, release date, rating, original language, and poster.
- **Favorite Movies**: Users can mark movies as favorites, and the favorite status is stored locally in a Room database.
- **Offline Access**: Favorite movies are saved locally, allowing users to view their favorite list even when offline.

## Download

You can download the APK of the app from the link below:

[Download MovieApp APK][https://drive.google.com/your-google-drive-apk-link](https://drive.google.com/file/d/1WN7JAuoJjx9CfMw0HU0QTYMgfmlQqKSd/view?usp=sharing](https://drive.google.com/file/d/1WN7JAuoJjx9CfMw0HU0QTYMgfmlQqKSd/view))

## Architecture

The application follows the MVVM architecture pattern combined with Clean Architecture. The project is divided into three main layers:

- **Presentation Layer**: Contains UI-related classes such as Activities, and ViewModels.
- **Domain Layer**: Contains business logic.
- **Data Layer**: Manages data sources, including local database (Room) and remote service (Retrofit).


####
## Tools

- MVVM
- flow
- Coroutine
- Dependency Injection (Dagger Hilt)
- Room Database
- Repository Pattern
- View Binding
 ####

