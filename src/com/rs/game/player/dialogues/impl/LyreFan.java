package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class LyreFan extends Dialogue {
	
	@Override
	public void start() {
		
		    sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to travel?", "Zanaris", "Tolna's Rift", "Letya", "Isafdar", "More Options...");
			stage = 1;
		}
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if(componentId == OPTION_1) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2409, 4451, 0));
				end();
			}
			if(componentId == OPTION_2) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3298, 9824, 0));
				end();
			}
	        	if(componentId == OPTION_3) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2316, 3173, 0));
				end();
			}
			if(componentId == OPTION_4) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2200, 3223, 0));
				end();
			}
			if(componentId == OPTION_5) {
			    stage = 14; 
			    sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to travel?", "Priffinas Gate", "Grimy Dungeon", "Dorgesh Kaan", "Jadinko Lair", "More Options...");
			}
			} else if (stage == 14) {
			if(componentId == OPTION_1) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2240, 3272, 0));
				end();
			}
			if(componentId == OPTION_2) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3149, 4651, 0));
				end();
			}
	        	if(componentId == OPTION_3) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2717, 5322, 0));
				end();
			}
			if(componentId == OPTION_4) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3032, 9234, 0));
				end();
			}
			if(componentId == OPTION_5) {
			    stage = 15; 
			    sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to travel?", "Mushroom Lair", "Yu Buisk", "Cosmic Entity", "Heaven", "More Options...");
			}
			} else if (stage == 15) {
			if(componentId == OPTION_1) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2596, 4194, 0));
				end();
			}
			if(componentId == OPTION_2) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2203, 4263, 1));
				end();
			}
	        	if(componentId == OPTION_3) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2077, 4825, 0));
				end();
			}
			if(componentId == OPTION_4) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(4381, 5918, 0));
				end();
			}
			if(componentId == OPTION_5) {
			    stage = 16; 
			    sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to travel?", "Runespan Lvl.1", "Runespan Lvl.2", "Runespan Lvl.3", "F.O.G Circle", "First Page...");
			}
			
			
			} else if (stage == 16) {
			if(componentId == OPTION_1) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3994, 6106, 0));
				end();
			}
			if(componentId == OPTION_2) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(4166, 6056, 2));
				end();
			}
	        	if(componentId == OPTION_3) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(4306, 6031, 2));
				end();
			}
			if(componentId == OPTION_4) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1666, 5696, 0));
				end();
			}
			if(componentId == OPTION_5) {
				player.getDialogueManager().startDialogue("LyreE");
			}
			}
	}
	@Override
	public void finish() {

	}
}