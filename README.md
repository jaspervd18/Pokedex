![Pokedex Logo](https://rajgaurav99.github.io/PokeCards-WEB/images/banner.png)

---

Are you not viewing this README from GitHub? [Click on me](https://github.com/jaspervd18/Pokedex/)!

Hello there fellow Pokémon lover! Are you ready to catch them all?

This app, specifically build for Android, includes all 905 pokémon from the 9 existing generations. Have hours of fun (literally) going through all of them and save your favorite ones for easier access later on.

Those more interested in the application and its specifications/details can read the information listed below.

# API

The API used for this project is the [PokéAPI](https://pokeapi.co/) (all rights reserved)

My first idea for the app was to let the user scroll through a list of pokémon, then let the user select a pokémon of chose and show more details about this pokémon (save it, etc...) but the API offers some challenges...

While it is possible to get more than 1 pokémon at a time, the only information given is the name and URL which leads to the more detailed page of that pokémon.

    {
      "name": "bulbasaur",
      "url": "https://pokeapi.co/api/v2/pokemon/1/"
    },
    {
      "name": "ivysaur",
      "url": "https://pokeapi.co/api/v2/pokemon/2/"
    },
    {
        ...
    },

To show a scrollable list of pokémon, I would have need to make an API call for a x amount of pokémon, then for each pokémon on the screen I would have need to make a call to get an image (because a list of just pokémon names doesn't really sound that interesting you know). Trying to get 20 pokémon images at a time from 20 different API calls (even more if you scroll faster) does not sound performant. With this in my mind I decided to show the user just one pokémon and its details at a time. With a next and previous function, the user can go through all the pokémon and only on API call is done at a time. It's not how I wanted it to be, but it I had to work with what I got (and can do).

# Testing

Testing was somewhat difficult. My application doesn't really have a lot, let alone any, business logic. This makes it hard to create unit tests.

The database is tested. I also wanted to test a View but I couldn't figure out how. I created a [test class](./app/src/test/java/com/example/pokedex/screens/pokemons/PokemonTest), but it doesn't work, I left it there to show that I tried, but didn't fully comprehend how or what. Sorry...

# Ktlint

Ktlint was used to help my code adhere to the kotlin code style.

Open a terminal and run `./gradlew ktlintCheck`, the build will succeed (normally). If not, run `./gradlew ktlintFormat` and rerun the first command again.

# Lifecycle

Just like testing, there isn't a whole lot which needs lifecycling. There are a few lifecycle methods implemented in the [Pokemon Fragment](./app/src/main/java/com/example/pokedex/screens/pokemons/PokemonFragment.kt) which keep the track of the last visited pokemon (with OnSaveInstances).

It didn't seem very interesting to implement them elsewhere (or once again I just don't know how to use them properly), but I do know they exist.

# Documentation

The app has a lot of inline comments to help understand certain methods, variables,...

Dokka is additionally used to help document and understand this application better. The Dokka [documentation](./documentation) can be found in the root of this repository.

The documenatation can be best viewed from within Visual Studio Code with Live Server, for example.

# Authentication

There exists a Login Fragment which uses Auth0. Unfortunately there remains an unsolved mystery with so called 'production keys'.

Because a simple Pokédex does not require any account/login/authentication function AND it is not required for the rubrics, the Login functionality remains but becomes obsolete.
