package com.rs.game.player.dialogues.impl;

import com.feather.game.player.content.SpiritTree;
import com.rs.game.player.dialogues.Dialogue;

public class MainSpiritTreeDialogue extends Dialogue {

	@Override
	public void start() {
		sendDialogue(
				"If you are a friend of the gnome people, you are a friend of mine.",
				"Do you wish to travel, or do you wish to ask about the evil tree?");
		
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Travel",
					"Ask about the Evil Tree.", "Nothing");
			stage = 0;
			break;
		case 0:
			if (componentId == OPTION_1) {
				
				end();
			} else if (componentId == OPTION_2) {
				sendDialogue("The taint of the evil tree is not currently on the land.");
				stage = 1;
			} else if (componentId == OPTION_3) {
				end();
			}
			break;
		case 1:
			end();
			break;
		}
		
	}

	@Override
	public void finish() {
		
	}

}