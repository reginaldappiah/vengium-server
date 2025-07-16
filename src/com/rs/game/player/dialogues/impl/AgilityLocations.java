package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class AgilityLocations extends Dialogue {
	
	@Override
	public void start() {

		sendOptionsDialogue("Where would you like to go?", "Gnome Stronghold",
				"Barbarian Outpost", "Wilderness", "Bandos Throne Room", "More options...");
		stage = 1;
	}
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
	if (componentId == OPTION_1) { //Gnome Stronghold
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2474, 3440, 0));
		end();
	}	
	if (componentId == OPTION_2) { //Barbarian Outpost
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2548, 3554, 0));
		end();
	}	
	if (componentId == OPTION_3) { //Wilderness
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2998, 3916, 0));;
		end();
	}
	if(componentId == OPTION_4) {//Bandos Throne Room
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2332, 4243, 0));
		end();
	
	}	
	if (componentId == OPTION_5) {//MORE OPTIONS
	stage = 2;
	sendOptionsDialogue("Where would you like to go?",
			"Kethsi", "Brimhaven", "Agility Pyramid",
			"Ape Atoll", "More Options...");
		}
	} else if (stage == 2) {
	if(componentId == OPTION_1) {//Kethsi
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4014, 5704, 0));
		end();
	}
    if(componentId == OPTION_2) {//Brimhaven
    	Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2805, 9589, 3));
		end();
    }
	if(componentId == OPTION_3) {//Agility Pyramid
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3346, 2829, 0));
		end();
	}
	if(componentId == OPTION_4) {//Ape Atoll
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2758, 2751, 0));
		end();
	}
	if(componentId == OPTION_5) {
	stage = 3;
	sendOptionsDialogue("Where would you like to go?",
			"Werewolf Skullball", "Penguin", "Dorgesh-Kaan", "Burthope",
			"More Options...");
	}
	} else if (stage == 3) {
	if(componentId == OPTION_1) {//Werewolf Skullball
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3538, 9866, 0));
		end();
	}
	if(componentId == OPTION_2) {//Penguin
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2641, 4049, 1));	
	}
    if(componentId == OPTION_3) {//Dorgesh-Kaan
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2722, 5243, 3));
	}
	if(componentId == OPTION_4) {//Burthope
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2918, 3549, 0));
	}
	if(componentId == OPTION_5) { // More options
        end();
		}
	}
}
@Override
public void finish() {

	}
}
