# galaga2.0
CSC 1351 Video Game Project

Faris Khattak
CSC 1351 Final Project based on BasicGraphics
5/5/22

Game Name: Galaga 2.0
This game is Space Invaders and Galaga inspired, with different enemies and items.

Controls:
1) WASD or ARROW keys to move around
2) Q to use the invincibility star if obtained
3) R to use the spreadshot item if obtained


Objective:
The main objective of the game is to shoot the enemies and advance to the next level.


Enemies:
The player can lose a life by physically colliding with the enemies. If the lives > 0, then the player enters a grace period
for a few seconds.

1) The green enemies (Enemy1 class) move vertically down towards the player.
	a) If these enemies reach the bottom, then the game is over and the player loses.

2) The horizontal enemies (HorizontalEnemy class) move from the left to right side of the screen. They essentially act the
   same as the Enemy1 class but are just horizontally moving.

3) The shooting enemies (EnemyShooter class) move side to side horizontally and shoot green lasers.
	a) If these lasers hit the player, the player loses a life and enters a grace period.

4) The boss enemy (BossEnemy class) moves side to side horizontally, shoots lasers toward the player, and has 3 phases.
	a) First or Green phase
		- slowest moving and shooting phase
		- spawns no enemies
		- occurs when health >= 50%
	b) Second or Yellow phase
		- boss speeds up, in both shooting and moving
		- starts to spawn Enemy1 enemies on a regular interval
		- occurs when 25% <= health < 50%
	c) Third or Red phase
		- boss speeds up more, in both shooting and moving
		- stops spawning Enemy1 enemies but spawns EnemyShooter enemies on a regular interval
		- starts shooting giant lasers
		- occurs when health < 25%

Items:
1) LifeHeart item
	a) randomnly drops from a dead enemy of any type
	b) when player comes into contact, it increase the lives of the player

2) InvcStar item
	a) randomnly floats in from the left side of the screen
	b) when player comes into contact, a star pops up in the bottom right corner to indicate that the item is able to be used
	c) when Q is pressed, the player transforms into an invcinibility spaceship for a certain amount of time
	d) one time use unless another star is obtained

3) SpreadShot item
	a) randomnly floats in from the right side of the screen
	b) when player comes into contact, the item pops up in the bottom right corner to indicate that the item is able to be used
	c) when R is pressed, the player shoots 5 lasers in different directions (a spread out formation)
	d) each item holds 5 shots
	e) the player can have a max of 4 spreadshot items or 20 shots

Bugs:
Sometimes the game won't advance levels if the player kills the last enemies at the same time. I believe I fixed this bug but
there is a small chance that this won't work.
