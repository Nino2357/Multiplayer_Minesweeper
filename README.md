# Multiplayer_Minesweeper



Discover = 
0 : nothing 
1 : open case
2 : mark flag


Communication between client and server : 

Server -> Client :
-1: nothing
0 : error
1 : quit
201 : CaseValue
	2010 : flag
	2011 : case num
	2012 : case mine





Client -> Server :
-1: nothing
0 : error
1 : quit
101 : get case value