Snake Game
By: Steven Zhu

Demo here: 
https://youtu.be/b7I0bXB3DcQ

To run game, download source code and type in terminal:
make
make run

Game Description:
Arrow keys to move (up down left right)
Shortcuts during game: r to restart game, p to pause, s to resume

Window.java is responsible for the Splash screen and creating the jframe window

Snake.java is responsible for all game logic, such as game update or game draw timings

Grid.java is responsible for all painting functions:
Black area is all valid Snake movement area
White area on the bottom is for text and score information
"Gameover!!!" or "Paused!" text will pop up if one of the two actions are invoked. 
Snake head - pink
Snake body - white
Fruit - blue
Powerup(speedup) - red
Powerup(slowdown) - yellow
Powerup(sizedown) - green

OS - Ubuntu 16.04
JDK - 1.8.0_91

FPS - 1-100 working
SPEED - 1-10 working
To change arguments, run make and then run "java Window (fps) (speed)"
Defaults to 60 fps 5 speed

Enhancements:
3/1000 chance for any of the 3 powerups to appear per game logic update (snake moves one block)

Powerup - speedup increases speed to a maximum of 10
Powerup - slowdown decreases speed to a minimum of 1
Powerup - sizedown decreases size of snake by size*(4/5) rounded down, if the size of snake is 1 then its size doesn't change.
