package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class LyreB3 extends Dialogue {
	
	@Override
	public void start() {
		
				sendOptionsDialogue("Where would you like to film?", "Zanaris", "Tolna's Rift", "Letya", "Isafdar", "More Options...");
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
			    sendOptionsDialogue("Where would you like to film?", "Priffinas Gate", "Falador Garden", "Rimmington POH", "Jadinko Lair", "More Options...");
			}
			} else if (stage == 14) {
			if(componentId == OPTION_1) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2240, 3272, 0));
				end();
			}
			if(componentId == OPTION_2) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3000, 3383, 0));
				end();
			}
	        	if(componentId == OPTION_3) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2953, 3226, 0));
				end();
			}
			if(componentId == OPTION_4) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3032, 9234, 0));
				end();
			}
			if(componentId == OPTION_5) {
			    stage = 15; 
			    sendOptionsDialogue("Where would you like to film?", "Mushroom Lair", "Yu Buisk", "Citadel Enterance", "Heaven", "More Options...");
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
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2959, 3287, 0));
				end();
			}
			if(componentId == OPTION_4) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(4381, 5918, 0));
				end();
			}
			if(componentId == OPTION_5) {
			    stage = 16; 
			    sendOptionsDialogue("Where would you like to film?", "Runespan Low Level", "Runespan Medium Level", "Runespan High Level", "Fist of Guthix", "Bright Categories...");
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
			player.getDialogueManager().startDialogue("LyreBCat");
		}
		}
		}

		
	@Override
	public void finish() {

	}
}