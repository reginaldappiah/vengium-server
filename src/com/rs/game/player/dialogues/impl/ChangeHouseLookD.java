package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class ChangeHouseLookD extends Dialogue {

	@Override
	public void start() {
		if (!player.hasHouse) {
			player.getDialogueManager().startDialogue("SimpleMessage", "You don't own a house.");
			return;// ok test it
		}
		sendOptionsDialogue("Choose your look", "Basic wood", "Whitewashed stone", "Fremennik-style wood", "Tropical wood", "More options");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if(stage == -1) {
			if (componentId == OPTION_1) {
				player.getHouse().setLook((byte) 1);
			} else if (componentId == OPTION_2) {
				player.getHouse().setLook((byte) 2);
			} else if (componentId == OPTION_3) {
				player.getHouse().setLook((byte) 3);
			} else if (componentId == OPTION_4) {
				player.getHouse().setLook((byte) 4);
			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("Choose your look", "Basic stone", "Zenevivia's dark stone", "Back");
				stage = 0;
				return;
			}
			end();
			if (player.getHouse().isLoaded()) {
				player.getHouse().expelGuests();
				if (player.getHouse().isOwnerInside())
					player.getHouse().refreshHouse();
			}
		} else {
			if (componentId == OPTION_1) {//ok now im  done? SO THE WALLS AREPART OF IT? i done the walls they work now
				player.getHouse().setLook((byte) 5);
			} else if (componentId == OPTION_2) {
				player.getHouse().setLook((byte) 6);
			} else if (componentId == OPTION_3) {
				sendOptionsDialogue("Choose your look", "Basic wood", "Tropical wood", "Fremennik-style wood", "Whitewashed stone", "More options");
				stage = -1;
				return;
			}
			end();
			if (player.getHouse().isLoaded()) {
				player.getHouse().expelGuests();
				if (player.getHouse().isOwnerInside())
					player.getHouse().refreshHouse();
			}
		}
		
	
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
