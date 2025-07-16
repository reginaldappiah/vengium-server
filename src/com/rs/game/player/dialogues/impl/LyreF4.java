package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class LyreF4 extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Where would you like to film?", "Juliet Balcony", "Varrock Museum", "Chaos Altar Stars", "Games Room", "More Options...");
			stage = 1;
		} 
	public void run(int interfaceId, int componentId) { 
	if (stage == 1) {
	if(componentId == OPTION_1) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3166, 3433, 1));
		end();
	}
	if(componentId == OPTION_2) {
		//Add museum displays
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1759, 4963, 0));
		end();
	}
	if(componentId == OPTION_3) {
		//Add shooting stars
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2142, 4845, 0));
		end();
	}
	if(componentId == OPTION_4) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2208, 4956, 0));
		end();
	}
	if(componentId == OPTION_5) { 
	  stage = 2;
		sendOptionsDialogue("Where would you like to film?", "Temporary POH", "Mage Arena", "Wizard Tower Bridge", "Sorcerer's Tower", "More Options...");
	}    
	} else  if (stage == 2) {
	if(componentId == OPTION_1) {
		//Make workbench and throne work
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3804, 5726, 0));
		end();
		}
	if(componentId == OPTION_2) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3363, 3294, 0));
		end();
	}
	if(componentId == OPTION_3) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3114, 3192, 0));
		end();
	}
	if(componentId == OPTION_4) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2702, 3405, 3));
	end();
	}
	if(componentId == OPTION_5) { 
	  stage = 3;
		sendOptionsDialogue("Where would you like to film?", "Al' Kharid Sparkling Pool", "Battlefield", "Whirlpool", "Ardougne Bridge", "More Options...");
	}    
	} else  if (stage == 3) {
	if(componentId == OPTION_1) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3288, 3230, 0));
		end();
	}
	if(componentId == OPTION_2) {
		//Add NPCs fighting
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2512, 3237, 0));
		end();
	}
	if(componentId == OPTION_3) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3512, 3518, 0));
		end();
	}
	if(componentId == OPTION_4) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2599, 3296, 0));
		end();
	}
	if(componentId == OPTION_5) { 
	  stage = 4;
		sendOptionsDialogue("Where would you like to film?", "ZMI Altar", "Sophanem Bridge", "First Vexation Home", "Second Vexation Home", "Famous Categories");
	}    
	} else  if (stage == 4) {
	if(componentId == OPTION_1) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2454, 3232, 0));
		end();
	}
	if(componentId == OPTION_2) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3274, 2785, 0));
		end();
	}
	if(componentId == OPTION_3) {
		//Put Musician and Butterflies
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3433, 2894, 0));
		end();
	}
	if(componentId == OPTION_4) {
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3012, 3360, 0));
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