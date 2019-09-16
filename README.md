# Jack's Arcade

### Background
As part of the KP Fellows process, one of the challenges was to design an interactive card game that runs on a Linux console.

I didn't know <em>anything</em> about designing console-based games, but I had seen some really cool console entertainments before. My favorite was [Hitchhikers Guide to the Galaxy](), which I once spent an entire day playing, and the [Star Wars IV: A New Hope]() that ran entirely in a terminal session, and used ASCII art for every scene.

With that in mind, I set out to design my first console game - which turned into multiple games. Hope you have as much fun playing as I had making!

### Games

#### Slapjack

Slapjack was *the* card game I played as a kid, and heck, sometimes still play. The basic premise is each player has a set of cards, face down. Each player takes the top card of their stack, places it in the middle of all the players, and then flips it to reveal the card. If the card is not a Jack, then nothing happens. The pile grows larger. If the card is a Jack, the first player to "slap" the card wins the round, and takes every card in the pile. The goal is to gather all of the cards.

If a player accidently slaps a card that is not the Jack, typical rules say that they must "burn" a card in a discard pile, which the next winner takes. In our modified version, if the player slaps a wrong card, they lose the round.

In this version, the CPU will never accidently slap a Jack. In fact, the computer actually slaps faster as the game progresses. Think fast!

#### White Jack

Black Jack is a very common game, mostly played at Casinos. 

#### Magic Jacques

When I started this project, my goal was to create a "magic trick" game involving playing cards. As I researched online, I found that mostly what makes a magic trick "magical" is that it's performed in person. Replicating this on a computer, and especially in a console environment, could be very challenging.

Nevertheless, I chose to make a game based on a different kind of magic - the magic of story. I *loved* playing Hitchhikers Guide to the Galaxy, and always wanted to create a CLI-based adventure game. So I decided to make a card-based fantasy adventure!

I won't reveal too much here - part of the game is understanding the story. I will give you a hint though - read carefully!

### Usage

After installing the project directory, type `bash build.sh` and let the magic begin.