package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class OffMod extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Moderate Category: Pick a Punishment.","Ban", "Mute", "Jail", "Main Menu.");
		stage = 1;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			switch(componentId) {
			case OPTION_1:
				player.getDialogueManager().startDialogue("MinBan");
				break;
			case OPTION_2:
				player.getDialogueManager().startDialogue("MinMute");
				break;
			case OPTION_3:
				player.getDialogueManager().startDialogue("MinJail");
				break;
			case OPTION_4:
				if (player.getRights() == 3)
				player.getDialogueManager().startDialogue("ModCat");
				else if (player.getRights() == 4)
				player.getDialogueManager().startDialogue("AdminCat");
				break;
				
			}
		}
	}

	@Override
	public void finish() {

	}
}