package com.rs.game.player.dialogues.impl;

import com.rs.game.player.content.SpiritTree;
import com.rs.game.player.dialogues.Dialogue;


public class SpiritTreeD extends Dialogue {

	@Override
	public void start() {
			sendDialogue("If you are a friend of the gnome people, you are a friend of mine.", "Do you wish to travel, or do you wish to ask about the evil tree?");
			stage = -1;
		}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Travel", "Ask about the Evil Tree.", "Nothing");
			stage = 0;
			break;
		case 0:
			if (componentId == OPTION_1) {
				SpiritTree.sendSpiritTreeTeleport(player, SpiritTree.MAIN_SPIRIT_TREE);
			} else if (componentId == OPTION_2) {
					sendDialogue("The taint of the evil tree is not currently on the land.");
					stage = 1;
				}
			 else if (componentId == OPTION_3) {
				end();
			}
			break;
		case 1:
			end();
			break;
		case 2:
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Yes", "No");
			stage = 3;
			break;
		case 3:
			if (componentId == OPTION_1) {
				end();
			} else if (componentId == OPTION_2) {
				sendPlayerDialogue(9827, "No, thank you.");
				stage = -1;
			}
			break;
		}

	}

	@Override
	public void finish() {

	}

}