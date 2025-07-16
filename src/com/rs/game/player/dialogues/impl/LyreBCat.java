package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class LyreBCat extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Bright Category: Pick a Category.","Bright I", "Bright II", "Bright III","Bright IIII", "Main Menu.");
		stage = 1;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			switch(componentId) {
			case OPTION_1:
				player.getDialogueManager().startDialogue("LyreB");
				break;
			case OPTION_2:
				player.getDialogueManager().startDialogue("LyreB2");
				break;
			case OPTION_3:
				player.getDialogueManager().startDialogue("LyreB3");
				break;
			case OPTION_4:
				player.getDialogueManager().startDialogue("LyreB4");
				break;
			case OPTION_5:
				player.getDialogueManager().startDialogue("LyreE");
				break;
			}
		}
	}

	@Override
	public void finish() {

	}
};