package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class LyreFCat extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Famous Category: Pick a Category.","Famous I", "Famous II", "Famous III","Famous IIII", "Main Menu.");
		stage = 1;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			switch(componentId) {
			case OPTION_1:
				player.getDialogueManager().startDialogue("LyreF");
				break;
			case OPTION_2:
				player.getDialogueManager().startDialogue("LyreF2");
				break;
			case OPTION_3:
				player.getDialogueManager().startDialogue("LyreF3");
				break;
			case OPTION_4:
				player.getDialogueManager().startDialogue("LyreF4");
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
}