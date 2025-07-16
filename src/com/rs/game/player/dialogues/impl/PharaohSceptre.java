package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class PharaohSceptre extends Dialogue {
	
	@Override
	public void start() {
		sendOptionsDialogue("<col=BB0909>Which Pyramid do you want to teleport to?", "Jalsavrah", "Jaleustrophos", "Jaldraocht", "I'm happy where I am actually.");
		stage = 1;
}
public void run(int interfaceId, int componentId) {
	if (stage == 1) {
	if(componentId == OPTION_1) {
		Magic.PharaohTeleport(player, 0, 0, new WorldTile(1934, 4456, 2));
		end();
	}
	if(componentId == OPTION_2) {
		Magic.PharaohTeleport(player, 0, 0, new WorldTile(3343, 2828, 0));
		end();
	}
	if(componentId == OPTION_3) {
		Magic.PharaohTeleport(player, 0, 0, new WorldTile(3233, 9317, 0));
		end();
	}
	if(componentId == OPTION_4) {
		end();
	}
	}
	}
	@Override
	public void finish() {

	}
}