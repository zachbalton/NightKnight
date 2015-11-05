# NightKnight
Night Knight is a work in progress Android Game developed with the [LibGDX Cross-platform Framework](https://libgdx.badlogicgames.com/)

## About this Code
This repository contains samples taken from the main codebase to highlight the underlying flow of the application. The classes within
make up all the basic requirements of a self contained game loop to operate. All other outlying code and asset files have been omitted.

## LibGDX and Android
LibGDX is a cross-platform development framework that acts as a wrapper for the LightWeightJavaGameLibrary (LWJGL) and underlying Android framework, providing the developer with a much more concise set of tools. While events such as input or hardware state changes are abstracted by the framework, Android still requires permissions and activities in the manifest are updated manually. Additionally, due to the way Android handles assets and its dependence on the auto-generated R.java class, all assets must be kept in the default /assets folder. All other platforms assets share a linked folder inside the Android modules path.

## Game Loops
 



