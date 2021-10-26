# Multiplayer_Minesweeper




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
