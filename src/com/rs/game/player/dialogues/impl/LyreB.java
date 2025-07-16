package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class LyreB extends Dialogue {
	
	@Override
	public void start() {

		    sendOptionsDialogue("Where would you like to film?", "Mobilising Armies", "Feldip Hills", "Soul Wars", "Brimhaven", "More Options...");
			stage = 1;
	}
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
	    if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2415, 2848, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2574, 2979, 0));
			end();
		}
        if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1885, 3231, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2764, 3184, 0));
			end();
		}
		if(componentId == OPTION_5) {
		    stage = 2;
		    sendOptionsDialogue("Where would you like to film?", "Mos Le'harmless", "Sand Quarry", "Neitiznot", "Mage Arena", "More Options...");
		}
		} else if (stage == 2) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3681, 2974, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3169, 2913, 0));
			end();
		}
        if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2322, 3803, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3363, 3294, 0));
			end();
		}
		if(componentId == OPTION_5) {
		    stage = 3; 
		    sendOptionsDialogue("Where would you like to film?", "Swaying Tree", "Eagle's Peak", "Oo'glog", "Shilo village", "More Options...");
		}
		} else if (stage == 3) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2740, 3638, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2318, 3487, 0));
			end();
		}
        if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2560, 2846, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2848, 2961, 0));
			end();
		}
		if(componentId == OPTION_5) {
		    stage = 4; 
		    sendOptionsDialogue("Where would you like to film?", "Sophanem", "Citharede Abbey", "Digsite", "Miscellenia", "Bright Categories...");
		}
		} else if (stage == 4) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3304, 2789, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3425, 3164, 0));
			end();
		}
        if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3331, 3412, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2529, 3859, 0));
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