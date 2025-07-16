package com.rs.game.player.dialogues.impl;


import com.rs.game.player.content.Magic;
import com.rs.game.player.content.PlayerManager;
import com.rs.game.player.dialogues.Dialogue;

public class Inconvenience1 extends Dialogue {



	@Override
	public void start() {
		sendEntityDialogue(IS_NPC, "RSMV Server Guide", 7969, 9827,
				"Hello, it's been a while.", 
				"Welcome back.");
	}
	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			player.lock();
			stage = 0;
			sendEntityDialogue(IS_NPC, "RSMV Server Guide", 7969, 9827, "Multak experienced a power outage yesterday.<br> So the server was down for that reason.");
			
			break;
		case 0:
			stage = 1;
			player.getInventory().addItem(1856, 1);
			sendEntityDialogue(IS_NPC, "RSMV Server Guide", 7969, 9827,
					"To make up, Multak has updated the lyre and",
					"regulated most of the staff ranks. Hope we",
					"didn't delay you too much and RSMV awaaaaaaaaaaaaaaaaaaay.");
			
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
