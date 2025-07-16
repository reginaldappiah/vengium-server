package com.rs.game.player.dialogues.impl.starter;

import com.rs.Settings;
import com.rs.game.player.dialogues.Dialogue;



public class NewPlayerTutorial extends Dialogue {

	/**
	 * Starts The Tutorial.
	 */

	@Override
	public void start() {
		player.getPackets().sendConfig(1021, 2); // Flashing Icon
		player.refreshHitPoints();
		player.getInterfaceManager().closeCombatStyles(); // Disables Combat Tab
		player.getInterfaceManager().closeSkills(); // Disables Skills Tab
		player.getInterfaceManager().closePlayerPanel(); // Disables Quest Tab
		player.getInterfaceManager().closeInventory(); // Disables Inventory Tab
		player.getInterfaceManager().closeEquipment(); // Disables Equipment Tab
		player.getInterfaceManager().closePrayerBook(); // Disables Prayer Tab
		player.getInterfaceManager().closeMagicBook(); // Disables Magic Tab
		player.getInterfaceManager().closeEmotes(); // Disables Emotes Tab
		player.getInterfaceManager().closeSettings(); // Disables Settings Tab
		player.getHintIconsManager().removeUnsavedHintIcon();

		stage = 1;
		sendDialogue("<img=20><col=FF0000>Vengium Guide</col><img=20>", "<col=000000>Welcome to Vengium, " + player.getUsername() + ".", "<col=000000>Please click the flashing tab, then click continue.");
		stage = 3;
	}

	/**
	 * Second Stage of Tutorial.
	 */

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 3) {
			player.getPackets().sendConfig(1021, 0);
			sendDialogue("<img=20><col=FF0000>This is the Player Support Tab.</col><img=20>", "<col=000000>Here, you can sumbit help tickets whenever you need", 
															"<col=000000>assistance, view your account information, and answer",
															"<col=000000>trivia questions with the click of a button.");
			stage = 4;

		}
		/**
		 * Fourth Stage of Tutorial.
		 */

		else if (stage == 4) {
			player.getInterfaceManager().sendPlayerPanel();
			player.getPackets().sendConfig(1021, 4);
			sendDialogue("<col=FF0000>Now, please click on the flashing tab", "<col=FF0000>then click continue.");
			stage = 5;
		}

		/**
		 * Fifth Stage of Tutorial.
		 */

		else if (stage == 5) {
			player.getPackets().sendConfig(1021, 0);
			sendDialogue("<img=20><col=FF0000>This is the Vengium Panel.</col><img=20>", "<col=000000>It is used for quick access to things like", 
														"<col=000000>teleports, accessories, websites, etc.");
			stage = 6;
		}

		/**
		 * Sixth Stage of Tutorial.
		 */

		else if (stage == 6) {
			sendDialogue("<col=FF0000>Please use any of the available options", "<col=FF0000>to your advantage. They are there for your own use.");
			stage = 9;
		}


		/**
		 * The Ninth Stage of Tutorial.
		 */

		else if (stage == 9) {
			sendDialogue("<col=FF0000><shad=FF0000>Go ahead and embark on your adventure. There are many things" 
						,"<col=FF0000><shad=FF0000>to achieve and discover. Have fun!");
			stage = 10;
		}

		/**
		 * The Tenth Stage of Tutorial.
		 */

		else if (stage == 10) {
			player.getInterfaceManager().sendInterfaces();
			player.closeInterfaces();
			player.starterstage = 2;
			player.getDialogueManager().startDialogue("StarterClass");
		}

		/**
		 * The Eleventh Stage of Tutorial.
		 */

		else if (stage == 11) {
			player.unlock();
			player.getInterfaceManager().sendInterfaces();
			player.closeInterfaces();
			player.welcomeInterface();
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
