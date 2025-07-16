package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class LyreFRCat extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Fairy Ring Category: Pick a Category.","Fairy Ring I", "Fairy Ring II", "Fairy Ring III", "Main Menu.");
		stage = 1;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			switch(componentId) {
			case OPTION_1:
				player.getDialogueManager().startDialogue("LyreFR");
				break;
			case OPTION_2:
				player.getDialogueManager().startDialogue("LyreFR2");
				break;
			case OPTION_3:
				player.getDialogueManager().startDialogue("LyreFR3");
				break;
			case OPTION_4:
				player.getDialogueManager().startDialogue("LyreE");
				break;
			}
		}
	}

	@Override
	public void finish() {

	}
}