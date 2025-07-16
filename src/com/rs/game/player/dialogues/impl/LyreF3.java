package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class LyreF3 extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Where would you like to film?", "Gift of Peace", "Grain of Plenty", "Box of Health", "Cradle of Life", "More Options...");
			stage = 1;
		} 
	public void run(int interfaceId, int componentId) { 
	if (stage == 1) {
	if(componentId == OPTION_1) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1908, 5222, 0));
		end();
	}
	if(componentId == OPTION_2) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2023, 5215, 0));
		end();
	}
	if(componentId == OPTION_3) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2145, 5280, 0));
		end();
	}
	if(componentId == OPTION_4) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2345, 5214, 0));
		end();
	}
	if(componentId == OPTION_5) { 
	  stage = 2;
		sendOptionsDialogue("Where would you like to film?", "Gu'Tanoth Bridge", "Mudskipper Point", "Port Sarim Hill", "Falador High Point", "More Options...");
	}    
	} else  if (stage == 2) {
	if(componentId == OPTION_1) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2536, 3012, 0));
		end();
		}
	if(componentId == OPTION_2) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2994, 3114, 0));
		end();
	}
	if(componentId == OPTION_3) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3007, 3144, 0));
		end();
	}
	if(componentId == OPTION_4) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2995, 3342, 3));
	end();
	}
	if(componentId == OPTION_5) { 
	  stage = 3;
		sendOptionsDialogue("Where would you like to film?", "Party Room", "Blue Moon Inn", "Edgeville School", "Monuments", "More Options...");
	}    
	} else  if (stage == 3) {
	if(componentId == OPTION_1) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3046, 3377, 0));
		end();
	}
	if(componentId == OPTION_2) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3222, 3402, 0));
		end();
	}
	if(componentId == OPTION_3) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3081, 3456, 0));
		end();
	}
	if(componentId == OPTION_4) {
		player.getPackets().sendGameMessage("<col=FF0000>You have unlocked a new music track: Lolita's Medicine");
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3420, 9887, 0));
		end();
	}
	if(componentId == OPTION_5) { 
	  stage = 4;
		sendOptionsDialogue("Where would you like to film?", "Desert Pyramid", "Marko's Spot(Wilderness)", "Isadafar Butterflies", "Karamja Volcano", "Famous Categories");
	}    
	} else  if (stage == 4) {
	if(componentId == OPTION_1) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3233, 2903, 0));
		end();
	}
	if(componentId == OPTION_2) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3099, 3624, 0));
		player.getControlerManager().startControler("Wilderness");
		end();
	}
	if(componentId == OPTION_3) {
		//Put Musician
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2245, 3183, 0));
		end();
	}
	if(componentId == OPTION_4) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2845, 3168, 0));
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