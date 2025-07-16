package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class LyreBETA extends Dialogue {
	
	@Override
	public void start() {
		stage = 1;
		if (stage == 1) {
		    sendOptionsDialogue("<col=FF0000>Where would you like to travel?", "Mobilising Armies", "Feldip Hills", "Soul Wars", "Brimhaven", "More Options...");
			stage = 1; 
		}
	}
	
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
	    if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2674, 3706, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2326, 3801, 0));
			end();
		}
        if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1885, 3231, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1885, 3231, 0));
			end();
		}
		if(componentId == OPTION_5) {
		    stage = 2;
		    sendOptionsDialogue("Where would you like to travel?", "Mos Le'harmless", "Sand Quarry", "Neitiznot", "Yanille", "More Options...");
		}
		} else if (stage == 2) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3665, 2971, 0));
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
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2606, 3093, 0));
			end();
		}
		if(componentId == OPTION_5) {
		    stage = 3; 
		    sendOptionsDialogue("Where would you like to travel?", "Golden Apple Tree", "Eagle's Peak", "Oo'glog", "Shilo village", "More Options...");
		}
		} else if (stage == 3) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2780, 3607, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2338, 3526, 0));
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
		    sendOptionsDialogue("Where would you like to travel?", "Sophanem", "Citharede Abbey", "Digsite", "Miscellenia", "More Options...");
		}
		} else if (stage == 4) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2780, 3607, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2338, 3526, 0));
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
		    stage = 5; 
		    sendOptionsDialogue("Where would you like to travel?", "Enchanted Valley", "Rat Catcher", "Familiarisation", "Castle Wars", "More Options...");
		}
		} else if (stage == 5) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3034, 4504, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2848, 5071, 0));
			end();
		}
        	if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3710, 5571, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2399, 3103, 0));
			end();
		}
		if(componentId == OPTION_5) {
		    stage = 6; 
		    sendOptionsDialogue("Where would you like to travel?", "Abandoned Mines", "Clan Wars", "Rellekka", "Treegnome Stronghold", "More Options...");
		
		
		
		}
		} else if (stage == 6) {
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
		    sendOptionsDialogue("Where would you like to travel?", "Trollheim", "Temple of Ikov", "Morytania Swamp", "Zamorak Altar", "More Options...");
		}
		} else if (stage == 7) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3662, 3492, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2345, 3691, 0));
			end();
		}
        	if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(4743, 5169, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3240, 3612, 0));
			end();
		}
		if(componentId == OPTION_5) {
		    stage = 8; 
		    sendOptionsDialogue("Where would you like to travel?", "Port Phasmatys", "Fisher's Colony", "Mausoleum", "Burgh De Rott", "More Options...");
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
		    sendOptionsDialogue("Where would you like to travel?", "Mort'ton", "Dungeoneering", "Old Spirit Tree", "West Ardougne", "More Options...");
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
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2335, 3111, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2531, 3308, 0));
			end();
		}
		if(componentId == OPTION_5) {
		    stage = 10; 
		    sendOptionsDialogue("Where would you like to travel?", "Keldagrim", "Living Rock Caverns", "Fist of Guthix", "Sanguinesti Region", "More Options...");
		}
		} else if (stage == 10) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2894, 10223, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3659, 5177, 0));
			end();
		}
        	if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1698, 5602, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2621, 3364, 0));
			end();
		}
		if(componentId == OPTION_5) {
		    stage = 11; 
		    sendOptionsDialogue("Where would you like to travel?", "Clocktower", "Ruins of Uzer", "Underground Pass", "Dreamland", "More Options...");
		}
		} else if (stage == 11) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2609, 9639, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3552, 4964, 0));
			end();
		}
        	if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2884, 9799, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1819, 5084, 1));
			end();
		}
		if(componentId == OPTION_5) {
		    stage = 12; 
		    sendOptionsDialogue("Where would you like to travel?", "H.A.M. Lair", "TBA", "TBA", "TBA", "More Options...");
		}
		} else if (stage == 12) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3160, 9634, 0));
			end();
		}
		if(componentId == OPTION_2) {
			end();
		}
        	if(componentId == OPTION_3) {
			end();
		}
		if(componentId == OPTION_4) {
			end();
		}
		if(componentId == OPTION_5) {
		    stage = 1; 
		    sendOptionsDialogue("Donator Transformations", "Soul Esswraith", "Necrolord", "Sunfreet", "Giant Roc", "First Page...");
		}
		if(componentId == OPTION_5) {
		    stage = 13; 
		    sendOptionsDialogue("Where would you like to travel?", "Zanaris", "Tolna's Rift", "Letya", "Isafdar", "More Options...");
		}
		} else if (stage == 13) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2409, 4451, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3298, 9824, 0));
			end();
		}
        	if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2331, 3271, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2200, 3223, 0));
			end();
		}
		if(componentId == OPTION_5) {
		    stage = 14; 
		    sendOptionsDialogue("Where would you like to travel?", "Priffinas Gate", "Mystery Castle", "Dorgesh Kaan", "Jadinko Lair", "More Options...");
		}
		} else if (stage == 14) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2240, 3272, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2836, 4383, 0));
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
		    sendOptionsDialogue("Where would you like to travel?", "Mushroom Lair", "Yu Buisk", "Cosmic Entity", "Heaven", "More Options...");
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
		    stage = 1; 
		    sendOptionsDialogue("Where would you like to travel?", "Mobilising Armies", "Feldip Hills", "Soul Wars", "Brimhaven", "More Options...");
		}
	  }
	}

	@Override
	public void finish() {

	}

}