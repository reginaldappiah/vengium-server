package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class LyreF2 extends Dialogue {

	@Override
	public void start() {
			
		sendOptionsDialogue("Where would you like to film?", "Railroad", "Tower Of Life Underground", "Abysaal Rift", "Baxatorian Falls", "More Options...");
			stage = 1;
		} 
	public void run(int interfaceId, int componentId) { 
	if (stage == 1) {
	if(componentId == OPTION_1) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2615, 3501, 0));
		end();
	}
	if(componentId == OPTION_2) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3040, 4390, 0));
		end();
	}
    if(componentId == OPTION_3) {
    	Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3038, 4833, 0));
		end();
	}
	if(componentId == OPTION_4) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2456, 4568, 0));
		end();
	}
	if(componentId == OPTION_5) {
	    stage = 2;
	    sendOptionsDialogue("Where would you like to film?", "Legendary Magic Trees", "Lletya Overview", "Clan Citadel Dancefloor", "Underground Pass Well", "More Options...");
	}
	} else if (stage == 2) {
	if(componentId == OPTION_1) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1300, 4588, 0));
		end();
	}
	if(componentId == OPTION_2) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2343, 3172, 1));
		end();
	}
    if(componentId == OPTION_3) {
    	Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(5376, 4714, 1));
		end();
	}
	if(componentId == OPTION_4) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2342, 9621, 0));
		end();
	}
	if(componentId == OPTION_5) {
		 stage = 3;
		    sendOptionsDialogue("Where would you like to film?", "Catharede Abbey Bell", "Ecotfongus Underground", "Chaos Altar(Wilderness)", "Varrock Rooftop", "More Options...");
	}
	} else if (stage == 3) {
	if(componentId == OPTION_1) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3450, 3178, 3));
		end();
	}
	if(componentId == OPTION_2) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3683, 9888, 0));
		end();
	}
    if(componentId == OPTION_3) {
        Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3240, 3613, 0));
        player.getControlerManager().startControler("Wilderness");
		end();
	}
	if(componentId == OPTION_4) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3212, 3467, 2));
		end();
	}
	if(componentId == OPTION_5) {
		stage = 4;
			sendOptionsDialogue("Where would you like to film?", "Camelot Pillars", "Ice Mountain", "VWB", "Witchaven Pillars", "Famous Categories...");
	}
	} else if (stage == 4) {
	if(componentId == OPTION_1) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2780, 3515, 0));
		end();
	}
	if(componentId == OPTION_2) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3003, 3470, 0));
		end();
	}
    if(componentId == OPTION_3) {
    	Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3185, 3440, 0));
		end();
	}
	if(componentId == OPTION_4) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2697, 3283, 2));
		end();
	}
	if(componentId == OPTION_5) {
	player.getDialogueManager().startDialogue("LyreFCat");
	}
	}
	}

	@Override
	public void finish() {

	}
}