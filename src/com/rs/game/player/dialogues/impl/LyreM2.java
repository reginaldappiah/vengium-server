package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class LyreM2 extends Dialogue {
	
	@Override
	public void start() {
			
			sendOptionsDialogue("Where would you like to film?", "Keldagrim", "Living Rock Caverns", "Fist of Guthix", "Sanguinesti Region", "More Options...");
			stage = 1;
		}
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2894, 10223, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3651, 5121, 0));
			end();
		}
        if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1698, 5602, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3688, 3313, 0));
			end();
		}
		if(componentId == OPTION_5) {
			stage = 2; 
		    sendOptionsDialogue("Where would you like to film?", "Meiyerditch Coffin", "Sarcophagus", "Lletya Dark Woods", "Jatizso", "More Options...");
		}
		} else if (stage == 2) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3557, 9769, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1491, 4816, 0));
			end();
		}
        if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2382, 3180, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2417, 3781, 0));
			end();
		}
		if(componentId == OPTION_5) {
			stage = 3; 
		    sendOptionsDialogue("Where would you like to film?", "West Ardougne Village", "Lumbridge Swamp", "Draynor Manor", "Draynor Obelisk", "More Options...");
		}
		} else if (stage == 3) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2485, 3302, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3186, 3181, 0));
			end();
		}
        if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3109, 3353, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3095, 3221, 0));
			end();
		}
		if(componentId == OPTION_5) {
			stage = 4; 
		    sendOptionsDialogue("Where would you like to film?", "Barrows", "Vampyre Tomb", "Ghoul Gravestones", "Slayer Tower Enterance", "Moody Categories...");
		}
		} else if (stage == 4) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3565, 3276, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3570, 3405, 0));
			end();
		}
        if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3432, 3462, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3429, 3534, 0));
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
