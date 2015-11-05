# NightKnight
Night Knight is a work in progress Android Game developed with the [LibGDX Cross-platform Framework](https://libgdx.badlogicgames.com/)

## About this Code
This repository contains samples taken from the main codebase to highlight the underlying flow of the application. The classes within
make up all the basic requirements of a self contained game loop to operate. All other outlying code and asset files have been omitted.

## LibGDX and Android
LibGDX is a cross-platform development framework that acts as a wrapper for the LightWeightJavaGameLibrary (LWJGL) and underlying Android framework, providing the developer with a much more concise set of tools. While events such as input or hardware state changes are abstracted by the framework, Android still requires permissions and activities in the manifest are updated manually. Additionally, due to the way Android handles assets and its dependence on the auto-generated R.java class, all assets must be kept in the default /assets folder. All other platforms assets share a linked folder inside the Android modules path.

## Game Loops
Each target platform with a run configuration receives its own module in the project. Outside of the standard default Android file structure, an AndroidLauncher activity is used to set various options before launching the thread the game runs on, often referred to as the main game loop.

The 'core' module contains all of the components that make up and run the Game Loop. Night Knight's loop begins by initializing the Game class, in this case NightKnight, which serves to select the Screen to display, load assets, and ensure rendering begins. The remaining major components roughly follow the MVC pattern. A game's game loop and an application's main thread differ mainly in how drawing to the screen is handled. If the underlying hardware was the only determining factor in how fast the game loop was completing, playing the game on faster hardware would result in the game being sped up. This is why rendering methods all use the float deltaTime or runTime parameter. If the world is updated according to the change in time relative to the last update it can adjust accordingly to ensure framerate independent gameplay.

#### View
The View is made up of the Screen and Renderer classes. A Game Screen is similar to an Android activity in that it also has a closely monitored life cycle and is what determines what is being displayed. The Screen employs a Renderer (or WorldRenderer) which contains the instructions needed to draw out the visual representation of the world. The Renderer is passed an updated instance of the World (or WorldController) every deltaTime to tear down the previous view and build the updated one.


#### Model
The WorldController class (more Model than Controller here) is also kept updated every deltaTime to serve as the World's current state represenation. It holds a set of rules to govern how the models interact with the world, as well as information about the models relative to the model.


#### Controller
The InputHandler is more of a Controller. It is where all the various possible events of multiple platforms are handled (touch, mouse click, drag, etc.). The input handler determines what kind of input happened, and issues commands depending on the World's current state.

