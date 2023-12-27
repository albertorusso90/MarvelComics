# Marvel Character Viewer App
This Android application is developed using the MVVM design pattern and implements clean architecture principles. It utilizes various libraries and frameworks including Dagger-Hilt, Coroutines, Retrofit, Jetpack Compose, and Coil.

## Getting Started
You will need to get a public and private key from here https://developer.marvel.com/documentation/getting_started 

To run this application locally, you need to define the following keys in your local.properties file:

```
marvel.api.key="publicKey"
marvel.api.hash="md5(ts+privateKey+publicKey)"
```

Ensure you replace "publicKey" and calculate the hash "md5(ts+privateKey+publicKey)" using your actual Marvel API public and private keys.

To calculate the Hash you can go to the following website https://www.md5hashgenerator.com/ 


## Setup Instructions
1. Clone this repository.
2. Open the project in Android Studio.
3. Create a local.properties file in the root directory of the project if not already present.
4. Add the Marvel API keys as mentioned in the 'Getting Started' section.
5. Build and run the application on an emulator or a physical device.


## Technologies Used
- MVVM Design Pattern
- Clean Architecture
- Dagger-Hilt
- Coroutines
- Retrofit
- Jetpack Compose
- Coil

## Features
The application comprises two main views:

1. Marvel Character List

   Displays a list of Marvel characters.
   
   <img src="https://github.com/albertorusso90/MarvelComics/blob/develop/screenshots/characterList.png" alt="App Screenshot" width="500" height="1500">

3. Marvel Character Details

   Provides detailed information about a selected Marvel character.
   <img src="https://github.com/albertorusso90/MarvelComics/blob/develop/screenshots/characterDetails.png" alt="App Screenshot" width="500" height="1500">
