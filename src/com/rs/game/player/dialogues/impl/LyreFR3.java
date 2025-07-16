package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class LyreFR3 extends Dialogue {
	
	@Override
	public void start() {

		    sendOptionsDialogue("Where do you want to film?", "Enchanted Valley", "Yu'buisk", "ScapeRune", "Cosmic Entity's Plane", "More Options...");
			stage = 1;
	}
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
		if(componentId == OPTION_1) {
			Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(3035, 4509, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(2228, 4244, 1));
			end();
		}
        	if(componentId == OPTION_3) {
        	Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(3424, 4775, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(2075, 4848, 0));
			end();
		}
		if(componentId == OPTION_5) {
			stage = 2;
			 sendOptionsDialogue("Where do you want to film?", "Cosmic Altar", "Goraks' Plane", "Poison Waste", "Kethsi", "More Options...");
		}
		} else if (stage == 2) {
		if(componentId == OPTION_1) {
			Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(2142, 4828, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(3038, 5348, 0));
			end();
		}
        	if(componentId == OPTION_3) {
        	Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(2213, 3099, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(4026, 5699, 0));
			end();
		}
		if(componentId == OPTION_5) {
			stage = 3;
			 sendOptionsDialogue("Where do you want to film?", "Ork's Rift", "Quest Rift", "Fairy Ring Categories...");
		}	
		} else if (stage == 3) {
		if(componentId == OPTION_1) {
			Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(1626, 4176, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(1582, 4138, 0));
			end();
		}
		if(componentId == OPTION_3) {
			player.getDialogueManager().startDialogue("LyreFRCat");
		}
		}
		}
	
	@Override
	public void finish() {

	}
}

