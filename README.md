# SiD RoboRally 

## General Info

### Contents Of This File

 * General Info
 * [About the Application](#build-info)
 * [How to Run the Application and Play the Game](#how-to-run-the-application-and-play-the-game)
 * [Manual Tests](#manual-tests)
 * [Troubleshooting](#troubleshooting)
 * [Maintainers and Roles](#maintainers-and-roles)

### Build-Info

Master:<br/>
[![Build Status](https://travis-ci.com/inf112-v21/SiD.svg?branch=master)](https://travis-ci.com/inf112-v21/SiD)

Utvikling: <br/>
[![Build Status](https://travis-ci.com/inf112-v21/SiD.svg?branch=utvikling)](https://travis-ci.com/inf112-v21/SiD)

Libraries:
- libgdx. 

### Known bugs
* Can't use cards yet.

***

## About the Application

* This application enables you to play the game **roborally** which is a board-game.

### Roborally (The Game)

<p>Roborally's story is: After work-hours robots will move around on a factory floor and try not to die.<p/>
<p>Each player has a robot/avatar that they can move with program cards dealt each round (to program the robot).
  The goal is to touch all checkpoints (flags) in the right order to win, and avoiding others winning at the same time.
  In addition to the challenge of planning movement, you will have constraints put on you when your health goes down, and
  different elements can damage you, help you, or annoy you. That's the game.</p>

***

## How to Run the Application and Play the Game
<p>Run Main in src/main/java/sid/roborally/Main to start up the game.</p>

<p>Once you've started up the game you use the WASD keybinds or the arrow keys to move the player around.
The goal of the game is to reach the flag in order to win, and avoid any hazards on the map to not die.</p>

* Currently you'll have to run the game in the IDE. If you run the sid.roborally.Main class you should be able to start the game.
* When in the application, navigate the menu's by entering 1 in each menu. Then you should get a screen pop-up.
* As of now you will only be able to move the player with keyboard-input (WASD or arrows). Exit game with Esc.
* In the next update you'll get to play the game more like it should be played.

***

## Manual Tests

* Navigate into the folder prosjektdokumenter and select manual_tests.txt. Follow the instructions.

***

## Troubleshooting
 * If you can't run on mac, control that the VM is given the following argument: -XstartOnFirstThread

***

## Maintainers and Roles

Current maintainers:
 * Terje: Master of Spesification.
 * Andreas: Game-Expert/Product-owner.
 * Emil: Chief Map-Maker.
 * Daniel: Refactoring-enthusiast.
 * Markus: Game-Expert/Product-owner.
