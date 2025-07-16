package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class LyreFR2 extends Dialogue {
		
	@Override
	public void start() {
	sendOptionsDialogue("Where would you like to film?", "Sinclair Mansion", "Tai Bwo Wannai", "Canifis", "Ape Atoll", "More Options...");
		stage = 1;
	}
	public void run(int interfaceId, int componentId) {
	if (stage == 1) {
	if(componentId == OPTION_1) {
		Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(2741, 3564, 0));
		end();
	}
	if(componentId == OPTION_2) {
		Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(2797, 3069, 0));
		end();
	}
    if(componentId == OPTION_3) {
    	Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(3447, 3471, 0));
		end();
	}
	if(componentId == OPTION_4) {
		Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(2735, 2743, 0));
		end();
	}
	if(componentId == OPTION_5) {
		stage = 2;
		sendOptionsDialogue("Where would you like to film?", "Jungle Spiders", "Mos Le' Harmless Island", "Wizard's Tower", "Musa Point", "More Options...");
	}
	} else if (stage == 2) {
	if(componentId == OPTION_1) {
		Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(2682, 3081, 0));
		end();
	}
	if(componentId == OPTION_2) {
		Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(3763, 2930, 0));
		end();
	}
    	if(componentId == OPTION_3) {
    	Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(3108, 3150, 0));
		end();
	}
	if(componentId == OPTION_4) {
		Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(2900, 3111, 0));
		end();
	}
	if(componentId == OPTION_5) {
		stage = 3;
		sendOptionsDialogue("Where would you like to film?", "Glacor Cave", "Edgeville", "Kandarin: Snowy Hunter", "Nardah", "More Options...");
	}
	} else if (stage == 3) {
	if(componentId == OPTION_1) {
		Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(4183, 5725, 0));
		end();
	}
	if(componentId == OPTION_2) {
		Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(3129, 3495, 0));
		end();
	}
    if(componentId == OPTION_3) {
    	Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(2744, 3720, 0));
		end();
	}
	if(componentId == OPTION_4) {
		Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(3423, 3015, 0));
		end();
	}
	if(componentId == OPTION_5) {
		stage = 4;
		sendOptionsDialogue("Where do you want to film?", "Witchaven Island", "Penguin Island", "Abyss", "Ardougne Zoo", "Fairy Ring Categories...");
	}
	} else if (stage == 4) {
	if(componentId == OPTION_1) {
		Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(2700, 3247, 0));
		end();
	}
	if(componentId == OPTION_2) {
		Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(2500, 3896, 0));
		end();
	}
	if(componentId == OPTION_3) {
		Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(3059, 4875, 0));
		end();
	}
	if(componentId == OPTION_4) {
		Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(2635, 3266, 0));
		end();
	}
	if(componentId == OPTION_5) {
		player.getDialogueManager().startDialogue("LyreFRCat");
	}
	}
	}
	
	@Override
	public void finish() {

	}
}