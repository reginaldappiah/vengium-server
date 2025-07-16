package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class Dunghandler extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		if (Settings.ECONOMY) {
			player.getPackets().sendGameMessage("Mr.Ex is in no mood to talk to you.");
			end();
			return;
		}
		npcId = (Integer) parameters[0];
		sendEntityDialogue(SEND_2_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"Im Your Dungeoneering Master",
						"I show your my options." }, IS_NPC, npcId, 9827);
	}

	@Override
	
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { player.getDisplayName(), "Okay." },
					IS_PLAYER, player.getIndex(), 9827);
			stage = 1;
		} else if (stage == 1) {
			sendOptionsDialogue("Options", "Open the shop.", "How much points do i have?", "Teleport me to Dungeoneering");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1)
				player.getDialogueManager().startDialogue("Dungeoneering");
			if (componentId == OPTION_2)
				player.getInterfaceManager().closeChatBoxInterface();
				player.sendMessage("You've got "+ player.dungpoints +" Dungeoneering points");
				if (componentId == OPTION_3)
					player.teleportPlayer(3505, 9676, 0);
				
				
				
		}
	

	}
		

	@Override
	public void finish() {

	}
}
