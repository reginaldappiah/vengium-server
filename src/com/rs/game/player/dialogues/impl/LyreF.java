package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class LyreF extends Dialogue {

	@Override
	public void start() {
		
			sendOptionsDialogue("Where would you like to film?", "Clocktower Fire", "Ruins of Uzer", "Underground Pass", "Ardougne Gallow", "More Options...");
			stage = 1;
		} 
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2609, 9639, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3552, 4964, 0));
			end();
		}
        if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2447, 3315, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2524, 3305, 0));
			end();
		}
		if(componentId == OPTION_5) {
		    stage = 12; 
		    sendOptionsDialogue("Where would you like to film?", "H.A.M. Lair", "Map Table", "Killer-Watt Clouds", "Strange Creatures", "More Options...");
		}
		} else if (stage == 12) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3160, 9634, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1693, 5469, 2));
			end();
		}
        if(componentId == OPTION_3) {
        	Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2659, 5216, 2));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3228, 9526, 2));
			end();
		}
		if(componentId == OPTION_5) {
		    stage = 13; 
		    sendOptionsDialogue("Where would you like to film?", "LightHouse", "Guthix Circle", "Arandar", "Ruins of Ullek", "More Options...");
		}
		} else if (stage == 13) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2508, 3645, 2));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2540, 5772, 0));
			end();
		}
        if(componentId == OPTION_3) {
        	Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2347, 3301, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3409, 2800, 1));
			end();
		}
		if(componentId == OPTION_5) {
		    stage = 14; 
		    sendOptionsDialogue("Where would you like to film?", "Lunar Mine", "Ritual of Mahjarrat", "Rooftop", "Old Lumbridge", "Famous Categories...");
		}
		} else if (stage == 14) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2339, 10337, 2));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2887, 3864, 0));
			end();
		}
        if(componentId == OPTION_3) {
        	Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2701, 3472, 3));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2340, 5719, 0));
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