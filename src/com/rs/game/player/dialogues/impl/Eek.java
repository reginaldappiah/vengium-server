package com.rs.game.player.dialogues.impl;

import com.rs.utils.Utils;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.player.dialogues.Dialogue;

public class Eek extends Dialogue {

//@Author Baki

	int npcId;

	
	private static final String[] SALUTATIONS = {"Hello there little guy...",
		"Hello there little guy...",
		"Hello there little guy...",
		"Hello there little guy...",
		"Hello there little guy...",
		"Hello there little guy..."};
	@Override
	public void start() {
		sendPlayerDialogue(9827, SALUTATIONS[Utils.random(SALUTATIONS.length)]);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			stage = -2;
			switch(Utils.random(10)) {
			case 0:
				sendEntityDialogue(IS_NPC, "Eek", 6506, 9827, "I would like some bugs please thank you!"  );
			break;
			case 1:
				sendEntityDialogue(IS_NPC, "Eek", 6506, 9827, "Mmm Don't you look rather tasty!"  );
			break;
			case 2:
				sendEntityDialogue(IS_NPC, "Eek", 6506, 9827, "Now that is a body I can sink my teeth into!"  );
			break;
			case 3:
				sendEntityDialogue(IS_NPC, "Eek", 6506, 9827, "I will lay eggs in your ears do you understand?"  );
			break;
			case 4:
				sendEntityDialogue(IS_NPC, "Eek", 6506, 9827, "Let us take a trip to Draynor Mansion some time."  );
			break;
			case 5:
				sendEntityDialogue(IS_NPC, "Eek", 6506, 9827, "I make webs out of my ass.. You think you have it bad."  );
			break;
			case 6:
				sendEntityDialogue(IS_NPC, "Eek", 6506, 9827, "Glad I never have babies because I don't want to die." );
			break;
			case 7:
				sendEntityDialogue(IS_NPC, "Eek", 6506, 9827, "Lets go slay some birds or other small critters!" );
			break;
			case 8:
				sendEntityDialogue(IS_NPC, "Eek", 6506, 9827, "Play with me! You look like you need some fun.");
			break;
			case 9:
				sendEntityDialogue(IS_NPC, "Eek", 6506, 9827, "Well hello there big dummy!");
			break;
			}
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
