package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class LyreM3 extends Dialogue {
	
	@Override
	public void start() {
			
			sendOptionsDialogue("Where would you like to film?", "Memorial", "Fenkenstrain's Castle", "Bonesack Area", "Lumber Yard", "More Options...");
			stage = 1;
		}
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3575, 3526, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3548, 3535, 0));
			end();
		}
        if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3369, 3495, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3314, 3507, 0));
			end();
		}
		if(componentId == OPTION_5) {
			stage = 2; 
		    sendOptionsDialogue("Where would you like to film?", "Dung Maple Trees", "Moonclan Island", "Webby House", "Southern Meiyerditch", "Moody Categories...");
		}
		} else if (stage == 2) {
		if(componentId == OPTION_1) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3499, 3621, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2569, 3118, 0));
			end();
		}
        if(componentId == OPTION_3) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2382, 3180, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3597, 3167, 1));
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
