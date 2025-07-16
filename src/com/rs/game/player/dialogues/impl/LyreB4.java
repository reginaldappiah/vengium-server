package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class LyreB4 extends Dialogue {
	
	@Override
	public void start() {
		
				sendOptionsDialogue("Where would you like to film?", "Ardougne Graveyard", "Entrana", "Dwarf Mines", "Al' Kharid Castle", "More Options...");
			stage = 1;
		}
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if(componentId == OPTION_1) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2504, 3288, 0));
				end();
			}
			if(componentId == OPTION_2) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2847, 3336, 0));
				end();
			}
	        	if(componentId == OPTION_3) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2580, 3479, 0));
				end();
			}
			if(componentId == OPTION_4) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3292, 3177, 0));
				end();
			}
			if(componentId == OPTION_5) {
			    stage = 14; 
			    sendOptionsDialogue("Where would you like to film?", "Soul Wars Portal", "Falador Cave", "Lum Bridge (Get it?)", "Falador Farm Patch", "More Options...");
			}
			} else if (stage == 14) {
			if(componentId == OPTION_1) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3082, 3476, 0));
				end();
			}
			if(componentId == OPTION_2) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2973, 3433, 0));
				end();
			}
	        	if(componentId == OPTION_3) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3245, 3226, 0));
				end();
			}
			if(componentId == OPTION_4) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3053, 3306, 0));
				end();
			}
			if(componentId == OPTION_5) {
			    stage = 15; 
			    sendOptionsDialogue("Where would you like to film?", "Courthouse", "Fishing Platform", "Yanille Bank", "Warrior Statues", "More Options...");
			}
			} else if (stage == 15) {
			if(componentId == OPTION_1) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2736, 3474, 0));
				end();
			}
			if(componentId == OPTION_2) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2583, 3528, 0));
				end();
			}
	        if(componentId == OPTION_3) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2605, 3093, 0));
				end();
			}
			if(componentId == OPTION_4) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2679, 3402, 0));
				end();
			}
			if(componentId == OPTION_5) {
			    stage = 16; 
			    sendOptionsDialogue("Where would you like to film?", "Ardougne Zoo", "Ape Atoll", "Varrock Stone Table", "Santa's Home", "Bright Categories...");
			}
			
			
			} else if (stage == 16) {
			if(componentId == OPTION_1) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2613, 3276, 0));
				end();
			}
			if(componentId == OPTION_2) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2744, 2741, 0));
				end();
			}
	        if(componentId == OPTION_3) {
	        	Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3227, 3368, 0));
				end();
			}
			if(componentId == OPTION_4) {
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2832, 3862, 0));
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