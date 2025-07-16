package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class LyreO extends Dialogue {
	
	@Override
	public void start() {

		    sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to travel?", "Lumbridge", "Grand Tree", "Rat Catcher", "Tutorial Island", "More Options...");
			stage = 1;
	}
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
	    if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2340, 5719, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2337, 5397, 0));
			end();
		}
        	if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2847, 5078, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3099, 3107, 0));
			end();
		}
		if(componentId == OPTION_5) {
		    stage = 2;
		    sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to travel?", "Turtorial Island #2", "Tzhaar", "Isafdar", "Varrock", "More Options...");
		}
		} else if (stage == 2) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3106, 3110, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2480, 5169, 0));
			end();
		}
        	if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2341, 4582, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1888, 5512, 0));
			end();
		}
		if(componentId == OPTION_5) {
		    stage = 3; 
		    sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to travel?", "Soul Wars", "Yanille", "TBA", "TBA", "First Page...");
		}
	} else if (stage == 3) {
	if(componentId == OPTION_1) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3595, 5420, 0));
		end();
	}
	if(componentId == OPTION_2) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2906, 4716, 0));
		end();
	}
    	if(componentId == OPTION_3) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2596, 3411, 0));
		end();
	}
	if(componentId == OPTION_4) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2596, 3411, 0));
		end();
	}
	if(componentId == OPTION_5) {
		player.getDialogueManager().startDialogue("LyreE2");
	    end();
	}}
}

	@Override
	public void finish() {

	}
}