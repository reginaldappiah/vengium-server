package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class LyreE extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Main Menu: Pick a Category.","Famous Categories", "Bright Categories", "Moody Categories","Fairy Ring Categories", "Extra Categories...");
		stage = 1;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			switch(componentId) {
			case OPTION_1:
				player.getDialogueManager().startDialogue("LyreFCat");
				break;
			case OPTION_2:
				player.getDialogueManager().startDialogue("LyreBCat");
				break;
			case OPTION_3:
				player.getDialogueManager().startDialogue("LyreMCat");
				break;
			case OPTION_4:
				player.getDialogueManager().startDialogue("LyreFRCat");
				break;
			case OPTION_5:
				player.getDialogueManager().startDialogue("LyreE2");
				break;
			}
		}
	}

	@Override
	public void finish() {

	}
}