package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class LyreMCat extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Moody Category: Pick a Category.","Moody I", "Moody II", "Moody III","Dungeons - Coming Soon", "Main Menu.");
		stage = 1;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			switch(componentId) {
			case OPTION_1:
				player.getDialogueManager().startDialogue("LyreM");
				break;
			case OPTION_2:
				player.getDialogueManager().startDialogue("LyreM2");
				break;
			case OPTION_3:
				player.getDialogueManager().startDialogue("LyreM3");
				break;
			case OPTION_4:
				player.getDialogueManager().startDialogue("LyreMCat");
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