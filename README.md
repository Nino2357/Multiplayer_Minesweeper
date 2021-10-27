# Multiplayer_Minesweeper

---

## Presentation

Multiplayer minesweeper game. 

This game is developed in java. It proposes to play the Minesweeper in 2 modes (solo, multiplayer).
The solo mode offers a choice of levels. 
You just have to make a left click to discover a square and a right click to add or remove the flags.

The multiplayer mode offers to play with several players in easy level. 
Players can play simultaneously. Each player is assigned a color and the cells are colored with the player's color. 
Scores are counted: discovering a cell brings 1 point and discovering a bomb makes you lose 10 points.
The goal is to get as many points as possible.


---

#### Play

##### Solo

Execute ./src/MainSolo.java

Features :
- Reset Grid
- Change level (EASY,MEDIUM,HARD)
- put/remove flag
- Quit

##### Multi	

Execute ./src/Server.java

**For each player :**

Execute ./src/Client.java

Features :
- Score (+1 open case number / -10 open mine)
- put/remove flag
- different color case for each player 


##### Switch GameMode

MenuBar -> Mode -> Solo / Multi

---

#### Technical Choice

Using swing library for graphical interface.

Using socket for Serveur/Client communication

Using thread for waiting message from Server/client

---

#### Communication codes

##### Case Status Discover 

Discover = 
- 0 : nothing 
- 1 : open case
- 2 : mark flag
- 3 : remove case

##### Communication between client and server 

###### Server -> Client :
- -1: nothing
- 0 : error
- 1 : quit
- 201 : CaseValue
- 2010 : flag
- 2011 : case num
- 2012 : case mine
- 202 : sendScore

###### Client -> Server :
- -1: nothing
- 0 : error
- 1 : quit
- 101 : get case value
- 102 : get score
