game
====

This project implements the game of Breakout.

Name:  Doherty Guirand

### Timeline

Start Date: 01/12/2020

Finish Date: 1/20/2020

Hours Spent: 20

### Resources Used
UTAs 

### Running the Program

Main class: Main.java

Data files needed: I added more image files
I made a paddle using adobe illustrator. I made the splashscreen images using canva. 

Key/Mouse inputs:
1) UP will change SplashScreen screens and begin ball movement
2) DOWN will remove final splash screen 
3) RIGHT moves paddle right
4) LEFT moves paddle left
5) SPACE resets ball and paddle 
6) 1 change to level one
7) 2 change to level two
8) 3 change to level three
9) L adds new lives

Cheat keys:
1) SPACE resets ball and paddle 
2) 1 change to level one
3) 2 change to level two
4) 3 change to level three
5) L adds new lives

Known Bugs:
Sometimes the ball gets caught in the paddle and the bricks. I think this happens when the ball hits the side of the brick or them paddle.
If I had more time I would write code to handle when this happens. 

Extra credit:
Points system 

### Notes/Assumptions

The player loses a life when the ball falls to the bottom. 
I was able to display two of the three scenarios : when the play loses and when the player goes to the next level.
I implemented multiple splash screens to explain the rules and how the game works. 
During the game, the player is able to see their score and how many lives are left. 

There are three different types of bricks
1) a normal brick - breaks with one hit
2) bricks that take three hits to break
3) bricks that will never break - are not counted to go to next level and do not add to score

The paddle has three different add ons
1) it speeds up the longer it goes either right or left
2) The middle section of the paddle causes the ball to bounce normall ie. only changes y direction. The outside sections cause the ball to change its x direction and
y direction
3) the paddle warps from one side of the screen to the other

There are three different types of powerups 
1) will extend the legnth of the paddle for only three hits per powerup
2) will add an extra ball
3) is a negitive power up will add more bricks to the screen that need to be broken. This does add to the score **need to update splash screen**.

The points system works as follows:
1) each ball broken adds 150 points
2) if more than only brick is broken before the ball hits the paddle there is a point multipler 


### Impressions
The game used to function better when I only had one level. As I began to implement more levels, I ran into some bugs. With more time, I would be
able to work out the kinks. 
