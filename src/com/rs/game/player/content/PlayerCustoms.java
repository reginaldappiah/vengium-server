package com.rs.game.player.content;


import com.rs.Settings;
import com.rs.game.player.Player;
import com.rs.game.player.content.Magic;
import com.rs.game.player.content.interfaces.AccountSettings;
import com.rs.game.player.content.interfaces.DTMonsters;
import com.rs.game.Graphics;
import com.rs.game.WorldTile;


/**
*
* @author Multak
*/
public class PlayerCustoms {
	
public static void handleButtons(Player player, int componentId) {
if (player.isRSMVerRank()) {
		if (componentId == 2) {
		Magic.sendBroomTeleport(player, 0, 0.0D, new WorldTile(2598, 3409, 0), new int[0]);
		player.sm("<col=FCFF00>Welcome home, " + player.getDisplayName() + "!");
		player.getInterfaceManager().closeChatBoxInterface();
		player.getInterfaceManager().closeOverlay(true);
		player.getControlerManager().forceStop();
		player.getControlerManager().removeControlerWithoutCheck();
		return;
		}
		
		else if (componentId == 4) {//Staff List
		StaffList.sendStaffInter(player);
		return;
		}
		
		else if (componentId == 6) {//RSMV Set
		Settings.getRSMVSet(player);
		}
		
		else if (componentId == 8) {//Quick Accessories
		player.getDialogueManager().startDialogue("QuickAccessories");
		return;
		}
		
		else if (componentId == 10) {//Vengium Websites
			player.getDialogueManager().startDialogue("VengiumSites");
		return;
		}
		else if (componentId == 12) {//Account
		AccountSettings.sendInter(player);
		return;
		}
		else if (componentId == 14) {//Bank
		player.getBank().openBank();
		return;
		}
	}
if (player.isPlayer()) {
		if (componentId == 2) {
		Magic.sendBroomTeleport(player, 0, 0.0D, new WorldTile(2598, 3409, 0), new int[0]);
		player.sm("<col=FCFF00>Welcome home, " + player.getDisplayName() + "!");
		player.getInterfaceManager().closeChatBoxInterface();
		player.getInterfaceManager().closeOverlay(true);
		player.getControlerManager().forceStop();
		player.getControlerManager().removeControlerWithoutCheck();
		return;
		}
		
		else if (componentId == 4) {//Staff List
		StaffList.sendStaffInter(player);
		return;
		}
		
		else if (componentId == 6) {//Web-store
		Settings.getRSMVSet(player);
		}
		
		else if (componentId == 8) {//Training
		player.getDialogueManager().startDialogue("QuickAccessories");
		return;
		}
		
		else if (componentId == 10) {//Monsters
			DTMonsters.sendOptions(player);
		return;
		}
		else if (componentId == 12) {//Skilling
		AccountSettings.sendInter(player);
		return;
		}
		else if (componentId == 14) {//Mini-games
		player.getBank().openBank();
		return;
		}
	}
}


}