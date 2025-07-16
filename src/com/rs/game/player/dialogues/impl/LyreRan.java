package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class LyreRan extends Dialogue {
	
	@Override
	public void start() {

		sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to travel?", "Lost And Found", "Freaky Forester", "Maze", "Drill Demon", "More Options...");
		stage = 1;
}
public void run(int interfaceId, int componentId) {
	if (stage == 1) {
    if(componentId == OPTION_1) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2335, 4771, 0));
		end();
	}
	if(componentId == OPTION_2) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2605, 4774, 0));
		end();
	}
    	if(componentId == OPTION_3) {
    	player.getPackets().sendGameMessage("<col=ff0000>Use shift-clicking to maneuver here.");
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2910, 4577, 0));
		end();
	}
	if(componentId == OPTION_4) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2760, 3177, 0));
		end();
	}
	if(componentId == OPTION_5) {
	    stage = 2;
	}
	
	} else if (stage == 2) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3665, 2971, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3169, 2913, 0));
			end();
		}
        	if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2322, 3803, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2606, 3093, 0));
			end();
		}
		if(componentId == OPTION_5) {
		    stage = 3; 
		    sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to travel?", "Golden Apple Tree", "Eagle's Peak", "Oo'glog", "Shilo village", "More Options...");
		}
		} else if (stage == 3) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2780, 3607, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2338, 3526, 0));
			end();
		}
        	if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2560, 2846, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2848, 2961, 0));
			end();
		}
		if(componentId == OPTION_5) {
		    stage = 4; 
		    sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to travel?", "Sophanem", "Citharede Abbey", "Digsite", "Miscellenia", "More Options...");
		}
		} else if (stage == 4) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3304, 2789, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3425, 3164, 0));
			end();
		}
        	if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3321, 3402, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2529, 3859, 0));
			end();
		}
		if(componentId == OPTION_5) {
		    stage = 5; 
		    sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to travel?", "Enchanted Valley", "Rat Catcher", "Familiarisation", "Castle Wars", "More Options...");
		}
		} else if (stage == 5) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3034, 4504, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2848, 5071, 0));
			end();
		}
        	if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3710, 5571, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2399, 3103, 0));
			end();
		}
		if(componentId == OPTION_5) {
		    stage = 6;
		    sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to travel?", "Frost Castle", "Nomad Throne", "Massive Boat", "Secluded Island", "First Page...");
		}
		} else if (stage == 6) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2907, 3930, 1));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3360, 5852, 0));
			end();
		}
        	if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1500, 4972, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3033, 2973, 0));
			end();
		}
		if(componentId == OPTION_5) {
		    player.getDialogueManager().startDialogue("LyreE");
		}
	
		}}
	@Override
	public void finish() {

	}
}