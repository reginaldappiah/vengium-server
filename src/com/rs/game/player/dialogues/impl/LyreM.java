package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class LyreM extends Dialogue {
	
	@Override
	public void start() {
		
		    sendOptionsDialogue("Where would you like to film?", "Abandoned Mines", "Clan Wars", "Rellekka", "Treegnome Stronghold", "More Options...");
			stage = 1;
		}
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
	    if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3451, 3228, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2994, 9679, 0));
			end();
		}
        	if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2660, 3658, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2462, 3444, 0));
			end();
		}
		if(componentId == OPTION_5) {
		    stage = 7; 
		    sendOptionsDialogue("Where would you like to film?", "Trollheim", "Temple of Ikov", "Morytania Swamp", "Zamorak Altar", "More Options...");
		}
		} else if (stage == 7) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2889, 3669, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3418, 3475, 0));
			end();
		}
        	if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3429, 3382, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3240, 3612, 0));
			end();
		}
		if(componentId == OPTION_5) {
		    stage = 8; 
		    sendOptionsDialogue("Where would you like to film?", "Port Phasmatys", "Fisher's Colony", "Mausoleum", "Burgh De Rott", "More Options...");
		}
		} else if (stage == 8) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3662, 3492, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2345, 3691, 0));
			end();
		}
        	if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3504, 3573, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3504, 3215, 0));
			end();
		}
		if(componentId == OPTION_5) {
		    stage = 9; 
		    sendOptionsDialogue("Where would you like to film?", "Mort'ton", "Dungeoneering", "Old Spirit Tree", "Mysterious Castle", "Moody Categories...");
		}
		} else if (stage == 9) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3501, 3284, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3448, 3695, 0));
			end();
		}
        	if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2336, 3118, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1891, 4522, 2));
			end();
		}
		if(componentId == OPTION_5) {	
			player.getDialogueManager().startDialogue("LyreMCat");
	}
	}
	}

	@Override
	public void finish() {

	}
}