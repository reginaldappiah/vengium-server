package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class LyreB2 extends Dialogue {
	
	@Override
	public void start() {
		
		    sendOptionsDialogue("Where would you like to film?", "Frost Castle", "Nomad Throne", "Familiarisation", "Secluded Island", "More Options ...");
			stage = 1;
	}
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2907, 3930, 1));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3360, 5852, 0));
			end();
		}
        if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3710, 5571, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3033, 2973, 0));
			end();
		}
		if(componentId == OPTION_5) {
			stage = 2;
			sendOptionsDialogue("Where would you like to film?", "Qu'Thanoth", "Massive Boat", "Varrock Museum", "Witchaven", "More Options...");
		}
		} else if (stage == 2) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3160, 9634, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1500, 4972, 0));
			end();
		}
        if(componentId == OPTION_3) {
        	Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1759, 4956, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2719, 3279, 0));
			end();
		}
		if(componentId == OPTION_5) {
		   stage = 3;
		   sendOptionsDialogue("Where would you like to film?", "Castle Wars", "Crandor", "Enchanted Valley", "Windmill", "More Options...");
		}
		} else if (stage == 3) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2399, 3103, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2840, 3244, 0));
			end();
		}
	    if(componentId == OPTION_3) {
	    	Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3034, 4504, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2634, 3375, 0));
			end();
		}
		if(componentId == OPTION_5) {
			stage = 4;
			sendOptionsDialogue("Where would you like to film?", "Edgeville Monastery", "Legends Guild", "Ardougne Garden", "Karamja Beach", "Bright Categories...");
		}
		} else if (stage == 4) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3051, 3501, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2729, 3363, 0));
			end();
		}
		if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2635, 3313, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2885, 3176, 0));
			end();
		}
		if(componentId == OPTION_5) {
			
			player.getDialogueManager().startDialogue("LyreBCat");
		}
		}
		}

		
	@Override
	public void finish() {

	}
}
