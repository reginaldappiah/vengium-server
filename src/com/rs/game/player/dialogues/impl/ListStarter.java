package com.rs.game.player.dialogues.impl;


import com.rs.game.player.content.Magic;
import com.rs.game.player.content.PlayerManager;
import com.rs.game.player.dialogues.Dialogue;

public class ListStarter extends Dialogue {



	@Override
	public void start() {
		sendEntityDialogue(IS_NPC, "RSMV Server Guide", 7969, 9827,
				"Hello, nice to see you again.", 
				"Welcome back.");
	}
	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			player.lock();
			stage = 0;
			sendEntityDialogue(IS_NPC, "RSMV Server Guide", 7969, 9827, "Multak recently added a command list for each rank.");
			break;
		case 0:
			stage = 1;
			player.getInventory().addItem(1856, 1);
			sendEntityDialogue(IS_NPC, "RSMV Server Guide", 7969, 9827,
					"This information book holds all the commands.",
					"Hope it helps and see you later!");
			
		break;
		case 1:
			player.getInterfaceManager().closeChatBoxInterface();
			player.unlock();
			end();
		break;
		default:
			end();
			break;
		}
	}

	@Override
	public void finish() {

	}

}
