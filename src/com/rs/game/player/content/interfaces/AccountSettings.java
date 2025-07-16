package com.rs.game.player.content.interfaces;

import com.rs.Settings;
import com.rs.game.item.Item;
import com.rs.game.player.Player;

/**
 * 
 * @author Miles Black
 *
 */

public class AccountSettings {
	
	public static void sendInter(Player player) {
		player.getPackets().sendIComponentText(72, 55, "Primary Account Settings");
		player.getPackets().sendIComponentText(72, 31, "<col=0A9700>Save Outfits");
		player.getPackets().sendIComponentText(72, 32, "<col=ff0000>Save Locations");
		player.getPackets().sendIComponentText(72, 33, "<col=FFF700>My Titles");
		player.getPackets().sendIComponentText(72, 34, "<col=BDFF06>Empty Inventory");
		player.getPackets().sendIComponentText(72, 35, "<col=1FB07B>Clearbank");
		
		player.getPackets().sendIComponentText(72, 36, "<col=000000>SERVER MESSAGES");
		player.getPackets().sendIComponentText(72, 37, "<col=000000>TRIVIA QUESTIONS");
		//TRIVIA
		if (player.TriviaOn == false && (player.getRSMVRank() <= 2)) {
		player.getPackets().sendIComponentText(72, 38, "<col=646464><shad=646464>TRIVIA QUESTIONS:"+" ["+"RSMVer 3+"+"]" );
		}
		else if (player.TriviaOn == true 	&& (player.getRSMVRank() <= 2)) {
		player.getPackets().sendIComponentText(72, 38, "<col=646464><shad=646464>TRIVIA QUESTIONS:"+" ["+"RSMVer 3+"+"]" );
		}
		else if (player.TriviaOn == false) {
		player.getPackets().sendIComponentText(72, 38, "<col=646464><shad=646464>TRIVIA QUESTIONS:</shad>"+"<col=FF0000> ["+"DISABLED"+"]" );
		}
		else if (player.TriviaOn == true) {
		player.getPackets().sendIComponentText(72, 38, "<col=646464><shad=646464>TRIVIA QUESTIONS:</shad>"+"<col=13FF06> ["+"ENABLED"+"]" );
		}
		//TRIVIA
		if (player. NewsOn == false && (player.getRSMVRank() <= 2)) {
		player.getPackets().sendIComponentText(72, 39, "<col=646464><shad=646464>NEWS MESSAGES:"+" ["+"RSMVer 3+"+"]" );
		}
		else if (player.NewsOn == true 	&& (player.getRSMVRank() <= 2)) {
		player.getPackets().sendIComponentText(72, 39, "<col=646464><shad=646464>NEWS MESSAGES:"+" ["+"RSMVer 3+"+"]" );
		}
		else if (player.NewsOn == false) {
		player.getPackets().sendIComponentText(72, 39, "<col=646464><shad=646464>NEWS MESSAGES:</shad>"+"<col=FF0000> ["+"DISABLED"+"]" );
		}
		else if (player.NewsOn == true) {
		player.getPackets().sendIComponentText(72, 39, "<col=646464><shad=646464>NEWS MESSAGES:</shad>"+"<col=13FF06> ["+"ENABLED"+"]" );
		}
		//TELELOCK
		if (player.telelocked == false && (player.getRSMVRank() <= 2)) {
		player.getPackets().sendIComponentText(72, 40, "<col=646464><shad=646464>TELELOCK:"+" ["+"RSMVer 3+"+"]" );
		}
		else if (player.telelocked == true 	&& (player.getRSMVRank() <= 2)) {
		player.getPackets().sendIComponentText(72, 40, "<col=646464><shad=646464>TELELOCK:"+" ["+"RSMVer 3+"+"]" );
		}
		else if (player.telelocked == false) {
		player.getPackets().sendIComponentText(72, 40, "<col=646464><shad=646464>TELELOCK:</shad>"+"<col=FF0000> ["+"DISABLED"+"]" );
		}
		else if (player.telelocked == true) {
		player.getPackets().sendIComponentText(72, 40, "<col=646464><shad=646464>TELELOCK:</shad>"+"<col=13FF06> ["+"ENABLED"+"]" );
		}
		player.getInterfaceManager().sendInterface(72);
		player.getInventory().refresh();
	}
	
	public static void handleButtons(Player player, int componentId) {
		if (componentId == 68)
			player.getDialogueManager().startDialogue("SavedOutfits");
		else if (componentId == 67)
			player.getDialogueManager().startDialogue("SavedLocations");
		else if (componentId == 66)
			player.getDialogueManager().startDialogue("MyTitles");
		else if (componentId == 65) //Empty Bank
			return;
		else if (componentId == 64) //Miles
			return;
		else if (componentId == 73)
			return;
		else if (componentId == 72) //Jolo
			return;
		else if (componentId == 71 && (player.getRSMVRank() <= 2)) { //Trivia
			player.sm("<img=15><col=FF0000>You must be prestige 3 or higher to toggle trivia messages!");
	}
		else if (componentId == 71 && (player.getRSMVRank() >= 3)) { //Trivia
			player.toggleTrivia();
			player.getInterfaceManager().closeScreenInterface();
	}
		else if (componentId == 70 && (player.getRSMVRank() <= 2)) { //News
			player.sm("<img=15><col=FF0000>You must be prestige 3 or higher to toggle news messages!");
	}
		else if (componentId == 70 && (player.getRSMVRank() >= 3)) { //News
			player.toggleNews();
			player.getInterfaceManager().closeScreenInterface();
	}
		else if (componentId == 69 && (player.getRSMVRank() <= 2)) {//Telelock
			player.sm("<img=15><col=FF0000>You must be prestige 3 or higher to use this the telelock feature!");
	}
		else if (componentId == 69 && (player.getRSMVRank() >= 3)) {//Telelock
			player.toggleTelelock();
			player.getInterfaceManager().closeScreenInterface();
	}
	}
	
	public static void EmptyInventory(Player player) {
    	player.getInventory().reset();
    }
    public void ClearBank(Player player) {
    	for (Item item : player.getBank().getContainerCopy()) {
			if (item == null) {
				continue;
			}
			int[] slot = player.getBank().getItemSlot(item.getId());
			if (slot == null) {
				continue;
			}
			player.getBank().removeItem(slot, item.getAmount(), true, false);
		}
		player.getBank().collapse(0);
        player.getBank().collapse(1);
        player.getBank().collapse(2);
        player.getBank().collapse(3);
        player.getBank().collapse(4);
        player.getBank().collapse(5);
        player.getBank().collapse(6);
        player.getBank().collapse(7);
        player.getBank().collapse(8);
        player.getBank().collapse(9);
        player.closeInterfaces();
        player.getPackets().sendGameMessage("<col=00FF09>You have successfully cleared your bank!");;
    }
	
}
