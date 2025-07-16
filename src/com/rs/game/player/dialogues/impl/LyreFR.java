package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class LyreFR extends Dialogue {
	
	@Override
	public void start() {

		    sendOptionsDialogue("Where would you like to film?", "Mudskipper Point", "Slayer Cave", "Piscatoris Hunter", "Jungle Hunter", "More Options...");
			stage = 1;
	}
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
		if(componentId == OPTION_1) {
			Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(2996, 3114, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(2780, 3613, 0));
			end();
		}
        if(componentId == OPTION_3) {
        	Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(2319, 3619, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(2571, 2956, 0));
			end();
		}
		if(componentId == OPTION_5) {
			stage = 2;
			 sendOptionsDialogue("Where would you like to film?", "Gu'Tanoth", "Haunted Woods", "McGrubor's Wood", "Polymore Dungeon", "More Options...");
		}
		} else if (stage == 2) {
		if(componentId == OPTION_1) {
			Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(2472, 3027, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(3597, 3495, 0));
			end();
		}
        if(componentId == OPTION_3) {
        	Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(2644, 3496, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(3410, 3325, 0));
			end();
		}
		if(componentId == OPTION_5) {
			stage = 3;
			sendOptionsDialogue("Where would you like to film?", "Kalphite Hive", "Ancient Cavern", "Fisher's Realm", "Castle Wars", "More Options...");
		}
		} else if (stage == 3) {
		if(componentId == OPTION_1) {
			Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(3251, 3097, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(1738, 5343, 0));
			end();
		}
        if(componentId == OPTION_3) {
        	Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(2634, 4694, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(2385, 3035, 0));
			end();
		}
		if(componentId == OPTION_5) {	
			stage = 4;
			sendOptionsDialogue("Where would you like to film?", "Mort Myre Swamp", "TzHaar Area", "Legend's Guild", "Miscellania", "Fairy Ring Categories...");
		}
		} else if (stage == 4) {
		if(componentId == OPTION_1) {
			Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(3468, 3431, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(4622, 5147, 0));
			end();
		}
        if(componentId == OPTION_3) {
        	Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(2740, 3352, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendFairyRingTeleport(player, 0, 0, new WorldTile(2529, 3127, 0));
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
