package com.rs.game.player.dialogues.impl;

import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.ShopsHandler;



public class EstateAgent extends Dialogue {

	public static int SKILLCAPE = 9748;
	public static int SKILLHOOD = 9749;
	public static int ONE = 1;

	private int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendEntityDialogue(SEND_2_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name, "Hello " + player.getUsername() + " how can I help you?" }, IS_NPC, npcId, 9760);
		stage = -1;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Select a Option", "May I purchase a house?", "I want change my house look", "Nevermind");
			stage = 1;
		} else if (stage == 1) {
			if (componentId == OPTION_1) {
				if (player.hasHouse == true) {
					sendEntityDialogue(SEND_2_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name, "You already own a house!" }, IS_NPC, npcId, 9760);
					stage = 5;
				} else {
					sendEntityDialogue(SEND_2_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name, "Sure, you can have it for free." }, IS_NPC, npcId, 9760);
					stage = 2;
				}
			}
			if(componentId == OPTION_2) 
				player.getDialogueManager().startDialogue("ChangeHouseLookD");
			if (componentId == OPTION_3) {
				end();
			}
		} else if (stage == 2) {
			sendOptionsDialogue("Select a Option", "That's a deal!", "Nevermind!");
			stage = 3;
		} else if (stage == 3) {
			if (componentId == OPTION_1) {
					sendEntityDialogue(SEND_2_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name, "Congratulations, you now own a house!" }, IS_NPC, npcId, 9760);
					player.hasHouse = true;
					stage = 5;
			} else if (componentId == OPTION_2) {
				sendEntityDialogue(SEND_2_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name, "You are truly missing out." }, IS_NPC, npcId, 9760);
			}
		} else if (stage == 5) {
			end();
		}
	}

	@Override
	public void finish() {

	}

}
