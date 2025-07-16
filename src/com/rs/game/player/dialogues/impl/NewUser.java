package com.rs.game.player.dialogues.impl;


import com.rs.game.player.content.Magic;
import com.rs.game.player.content.PlayerManager;
import com.rs.game.player.dialogues.Dialogue;

public class NewUser extends Dialogue {



	@Override
	public void start() {
		sendEntityDialogue(IS_NPC, "RSMV Server Guide", 7969, 9827,
				"Hello, I'm glad you are here.", 
				"Welcome to Vengium.");
	}
	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			player.lock();
			stage = 0;
			sendEntityDialogue(IS_NPC, "RSMV Server Guide", 7969, 9827, "Vengium is an RSPS that provides RSMVers with the upmost capabilty to produce RSMVs.");
			break;
		case 0:
			stage = 1;
			player.getInventory().addItem(3690, 1);
			sendEntityDialogue(IS_NPC, "RSMV Server Guide", 7969, 9827,
					"This kit may come in handy on your journey.",
					"I hope you enjoy your stay.",
					"I wish you the best of luck!");
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
