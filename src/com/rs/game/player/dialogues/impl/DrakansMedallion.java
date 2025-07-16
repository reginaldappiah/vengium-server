package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class DrakansMedallion extends Dialogue {
	
	@Override
	public void start() {

		    sendOptionsDialogue("<col=ff0000><shad=ff0000>Letters glow dimly on the metal", "Barrows", "Burgh de Rott", "Meiyerditch", "Darkmeyer", "Meryerditch Laboratories");
			stage = 1;
	}
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
	    if(componentId == OPTION_1) {
			Magic.sendDrakanTeleport(player, 0, 0, new WorldTile(3565, 3316, 0));
			end();
		}
		if(componentId == OPTION_2) {
			Magic.sendDrakanTeleport(player, 0, 0, new WorldTile(3496, 3205, 0));
			end();
		}
        	if(componentId == OPTION_3) {
			Magic.sendDrakanTeleport(player, 0, 0, new WorldTile(3626, 3195, 0));
			end();
		}
		if(componentId == OPTION_4) {
			Magic.sendDrakanTeleport(player, 0, 0, new WorldTile(3638, 3365, 0));
			end();
		}
		if(componentId == OPTION_5) {
			Magic.sendDrakanTeleport(player, 0, 0, new WorldTile(3633, 9693, 0));
			end();
		}
		}}
			@Override
			public void finish() {

			}
		}