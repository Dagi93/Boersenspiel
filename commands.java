HELP         ("help", 		"  						* list all commands"),
EXIT         ("exit", 		"  						* exit program"),
CREATEPLAYER ("crp",  		"<name> 					* create a new player by name", String.class), 
BUYSHARE     ("bs",   		"<playername> <sharename> <amount> 		* buy that amount of shares", String.class, String.class, int.class),
SELLSHARE    ("ss",   		"<playername> <sharename> <amount> 		* sell that amount of shares", String.class, String.class, int.class),