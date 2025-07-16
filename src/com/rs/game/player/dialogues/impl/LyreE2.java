package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class LyreE2 extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Extra Categories: Pick a Category.", "Old Graphic Locations", "Random Event Locations", "Holiday Event Locations", "Main Menu.");
		stage = 1;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			switch(componentId) {
			case OPTION_1:
				player.getDialogueManager().startDialogue("LyreO");
				break;
			case OPTION_2:
				player.getDialogueManager().startDialogue("LyreRan");
				break;
			case OPTION_3:
				player.getDialogueManager().startDialogue("LyreHoli");
				break;
			case OPTION_4:
				player.getDialogueManager().startDialogue("LyreE");
				break;
			case OPTION_5:
				end();
				break;
			}
		}
	}

	@Override
	public void finish() {

	}
}