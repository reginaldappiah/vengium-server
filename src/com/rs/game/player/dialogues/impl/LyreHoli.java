package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class LyreHoli extends Dialogue {
	
	@Override
    public void start() {
        sendOptionsDialogue("<col=ff0000><shad=ff0000>Teleport Lyre.", "Halloween",
                "Easter/Thanksgiving", "Christmas");
        stage = 2;
    }
 
    @Override
    public void run(int interfaceId, int componentId) {
         if (stage == 2) {
            if (componentId == OPTION_1) {
                sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to go?", "2006 Hallowe'en",
                        "2007 Hallowe'en", "2009 Hallowe'en", "Brimhaven.", "More Options");
                stage = 3;
            } else if (componentId == OPTION_2) {
                sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to go?", "Abandoned Mines.",
                        "Clan Wars.", "Rellekka.", "Tree Gnome Stronghold.", "More Options");
                stage = 8;
            } else if (componentId == OPTION_3) {
                sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to go?", "Zanaris.",
                        "Tolna's Rift.", "Lletya.", "Isdafar.", "More Options");
                stage = 13;
            }           
        }  else if (stage == 3) {
			if (componentId == OPTION_1)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2413, 2850, 0));
			else if (componentId == OPTION_2)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2572, 2980, 0));
			else if (componentId == OPTION_3)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(4319, 5338, 0));
			else if (componentId == OPTION_4)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2761, 3178, 0));
			else if (componentId == OPTION_5) {
				stage = 4;
				sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to go?",
						"Mos Le'harmless.", "Sand Quarry.", "Neitiznot.",
						"Yanille", "More Options");
			}
		} else if (stage == 4) {
			if (componentId == OPTION_1) {
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(3663, 2972, 0));
			} else if (componentId == OPTION_2)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(3170, 2913, 0));
			else if (componentId == OPTION_3)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2321, 3804, 0));
			else if (componentId == OPTION_4)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2552, 3089, 0));
			else if (componentId == OPTION_5) {
				stage = 5;
				sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to go?",
						"Golden Apple Tree.", "Eagles Peak.", "Oo'Glog.",
						"Shilo Village.", "More Options");
			}
		} else if (stage == 5) {
			if (componentId == OPTION_1) {
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2778, 3608, 0));
			} else if (componentId == OPTION_2) {
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2338, 3526, 0));
			} else if (componentId == OPTION_3)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2560, 2846, 0));
			else if (componentId == OPTION_4) {
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2846, 2961, 0));
			} else if (componentId == OPTION_5) {
				stage = 6;
				sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to go?",
						"Sophanem.", "Citherade Abbey.", "Digsite.", "Miscellenia.",
						"More Options");
			}
		} else if (stage == 6) {
			if (componentId == OPTION_1) {
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(3304, 2788, 0));
			} else if (componentId == OPTION_2)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(3427, 3163, 0));
			else if (componentId == OPTION_3)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(3323, 3403, 0));
			else if (componentId == OPTION_4) {
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2529, 3859, 0));
			} else if (componentId == OPTION_5) {
				stage = 7;
				sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to go?",
						"Enchanted Valley.", "Rat catcher.", "Familiarisation.", "Castle Wars.",
						"Back");
			}
		} else if (stage == 7) {
			if (componentId == OPTION_1)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(3034, 4504, 0));
			else if (componentId == OPTION_2)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2848, 5071, 0));
			else if (componentId == OPTION_3)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(3710, 5571, 0));
			else if (componentId == OPTION_4)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2459, 3094, 0));
			else if (componentId == OPTION_5) {
				sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to go?",
						"Moblising Armies.", "Feldip Hills.", "Soul Wars.", "Brimhaven.",
						"More Options.");
				stage = 3;
			}
		} else if (stage == 8) {
			if (componentId == OPTION_1)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(3451, 3228, 0));
			else if (componentId == OPTION_2)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2994, 9679, 0));
			else if (componentId == OPTION_3)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2660, 3658, 0));
			else if (componentId == OPTION_4)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2462, 3444, 0));
			else if (componentId == OPTION_5) {
				stage = 9;
				sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to go?",
						"H.A.M. Lair.", "Trollheim.", "Temple of Ikov.",
						"Morytania Swamp", "More Options");
			}
		} else if (stage == 9) {
			if (componentId == OPTION_1) {
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(3160, 9634, 0));
			} else if (componentId == OPTION_2)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2836, 3667, 0));
			else if (componentId == OPTION_3)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(3418, 3477, 0));
			else if (componentId == OPTION_4)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(3456, 3434, 0));
			else if (componentId == OPTION_5) {
				stage = 10;
				sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to go?",
						"Port Phasmatys.", "Fishers Colony.", "Poison Waste.",
						"Burgh de Rott.", "More Options");
			}
		} else if (stage == 10) {
			if (componentId == OPTION_1) {
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(3662, 3494, 0));
			} else if (componentId == OPTION_2) {
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2345, 3691, 0));
			} else if (componentId == OPTION_3)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2212, 3100, 0));
			else if (componentId == OPTION_4) {
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(3505, 3217, 0));
			} else if (componentId == OPTION_5) {
				stage = 11;
				sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to go?",
						"Mort'ton.", "Dungeoneering.", "Old spirit tree.", "West Ardougne.",
						"More Options");
			}
		} else if (stage == 11) {
			if (componentId == OPTION_1) {
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(3503, 3284, 0));
			} else if (componentId == OPTION_2)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(3450, 3697, 0));
			else if (componentId == OPTION_3)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2335, 3111, 0));
			else if (componentId == OPTION_4) {
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2531, 3306, 0));
			} else if (componentId == OPTION_5) {
				stage = 12;
				sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to go?",
						"Keldagrim.", "Living Rock Cavern.", "Fist of Guthix.", "Goblins vs Gnomes.",
						"Back");
			}
		} else if (stage == 12) {
			if (componentId == OPTION_1)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2894, 10223, 0));
			else if (componentId == OPTION_2)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(3659, 5117, 0));
			else if (componentId == OPTION_3)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(1697, 5600, 0));
			else if (componentId == OPTION_4)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2147, 4961, 0));
			else if (componentId == OPTION_5) {
				sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to go?",
						"Abandoned Mines.", "Clan Wars.", "Relekka.", "Tree Gnome Stronghold.",
						"More Options.");
				stage = 8;
			}
		} else if (stage == 13) {
			if (componentId == OPTION_1)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2409, 4449, 0));
			else if (componentId == OPTION_2)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(3297, 9824, 0));
			else if (componentId == OPTION_3)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2331, 3171, 0));
			else if (componentId == OPTION_4)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2249, 3151, 0));
			else if (componentId == OPTION_5) {
				stage = 14;
				sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to go?",
						"Prifddinas Gate.", "Ancient Cavern.", "Dorgesh-Kaan.",
						"Jadinko Lair", "More Options");
			}
		} else if (stage == 14) {
			if (componentId == OPTION_1) {
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2242, 3272, 0));
			} else if (componentId == OPTION_2)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(1751, 5291, 1));
			else if (componentId == OPTION_3)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2717, 5322, 0));
			else if (componentId == OPTION_4)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(3034, 9234, 0));
			else if (componentId == OPTION_5) {
				stage = 15;
				sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to go?",
						"Mushroom Lair.", "Yu'biusk.", "Cosmic Entity's plane.",
						"Heaven.", "More Options");
			}
		} else if (stage == 15) {
			if (componentId == OPTION_1) {
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2598, 4198, 0));
			} else if (componentId == OPTION_2) {
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2203, 4263, 1));
			} else if (componentId == OPTION_3)
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(2079, 4825, 0));
			else if (componentId == OPTION_4) {
				Magic.sendLyreTeleport(player, 0, 0, new WorldTile(4382, 5918, 0));
			} else if (componentId == OPTION_5) {
				stage = 13;
				sendOptionsDialogue("<col=ff0000><shad=ff0000>Where would you like to go?",
						"Zanaris.", "Tolna's Rift.", "Lletya.", "Isdafar.",
						"back");
			}
		}
	}
 
    @Override
    public void finish() {
 
    }
}