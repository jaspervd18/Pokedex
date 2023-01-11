![Pokedex Logo](https://rajgaurav99.github.io/PokeCards-WEB/images/banner.png)

---

Hello there fellow Pokémon lover! Are you ready to catch them all?

This app, specifically build for Android, includes all 905 pokémon from the 9 existing generations. Have hours of fun (literally) going through all of them and save your favorite ones for easier access later on.

Those more interested in the application its specifications and details can read the information listed below.

# API

The API used for this project is the [PokéAPI](https://pokeapi.co/) (all rights reserved). 

My first idea for the app was to let the user scroll to a list of pokémon, let the user select a pokémon of chose, and see more details about this pokémon (save it, etc...) but the API offers some challenges...

While it is possible to get more than 1 pokémon at a time, the only information given is the name and URL which leads to the more detailed page of that pokémon.

    {
      "name": "bulbasaur",
      "url": "https://pokeapi.co/api/v2/pokemon/1/"
    },
    {
      ...
    },

To show a scrollable list of pokémon, I would need to make an API call for a certain amount of pokémon, and for each pokémon on the screen I would then need to make a call to get an image (because a list of just pokémon names doesn't really sound that interesting you know). Trying to get 20 pokémon images at a time from 20 different API calls (even more if you scroll faster) does not sound performant. With this in my mind I decided to show the user one pokémon and its details at a time. With a next and previous function, the user can go through all the pokémon and only on API call is done at a time. It's not how I wanted it to be, but it I had to work with what I got (and can do).

