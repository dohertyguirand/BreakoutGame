### Brick Breaker 
Doherty Guirand 

I decided to structure the game using 7 classes, each of the classes represent a feature in the game. 
The main class is where I called the core functions to demonstrate, run and interact with the game. The functions to demonstrate the game include are start() and 
setUpGame() these methods are what builds the application and scene. The method to run the game is step(). This method runs when the player is still in game play. The methods called by step() are mostly included in the Gameplay.java class.
To interact with the game, the handelKeyInput() method is called. Moreover, four more important methods are included in this class: removeNodeFromRoot(Node node), removeCollectionFromRoot(), addNodeToRoot(), and allCollectionToRoot(). These classes
are called in all of the other classes to add and remove images from the game.

The GamePlay class has methods that help run the game. An important method in this class is called everyStep().
This method calls every method that needs to be called at every instant (every few miliseconds.) The Lives, Balls, PowerUps, and Texts class all have methods that manage their respective objects.
All of the object classes point to GamePlay and Main. I think in further design projects, I will work to have it so that each class has an active role instead of just holding information. 

While designing my game, I wanted it to be easy to add new levels and powerups. With my methods, I wanted the game designer to be able to just draw a new level
using illustrator or xd, and be able to easily and mindlessly input the pixel locations for each brick/ powerUp. I assumed that the game designer would 
already have the pixcel locations already in mind. In further development of this game, I would have used contants instead of magic numbers. This is because, this design doesn't allow for reactive webpages. For example, if the game designer decides to increase the screen size to be 
1000 pixels instead of 700. The locations set for the bricks ans the power ups wouldn't accurate anymore. Furthermore, I wanted it to be easy to add new text, by creating a text class. I hope that adding new text is as easy as adding a new method to the class. 

I would add a new feature that reads each of the brick locations using a txt file. I think that could make it a lot easier to add new levels to the game. Instead of the game designer having to manually type each pixel 
location into the Bricks class, the designer could just upload a txt file. Furthermore, I would create a method to create a random level.
Talking to other students in the course that implemented a random level generator, I would use a random number generator to create new levels. 