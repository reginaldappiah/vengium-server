package com.rs.game.player.content.interfaces;

import com.rs.game.player.Player;

public class AccountInformation {
	
	private static int category;
	
	public static void sendInterface(Player player) {
		
		player.getInterfaceManager().sendInterface(1158);
		player.getPackets().sendIComponentText(1158, 74, "Vengium Profile: " + player.getDisplayName());
		player.getPackets().sendIComponentText(1158, 8, "#");
		player.getPackets().sendIComponentText(1158, 9, "SETTINGS");
		player.getPackets().sendIComponentText(1158, 11, "STATUS");
		player.getPackets().sendIComponentText(1158, 10, "<col=A1A1A1>DESCRIPTION</col>");
		
		player.getPackets().sendIComponentText(1158, 13, "1.");
		player.getPackets().sendIComponentText(1158, 14, "RSMV Points:");
		player.getPackets().sendIComponentText(1158, 16, ""+player.getRSMVerPoints() +"/1000");
		player.getPackets().sendIComponentText(1158, 15, "<col=A1A1A1>Obtained by completing RSMV-related trivias and minigames.</col>");
		
		player.getPackets().sendIComponentText(1158, 18, "1.");
		player.getPackets().sendIComponentText(1158, 19, "RSMV Rank:");
		player.getPackets().sendIComponentText(1158, 21, ""+player.getRSMVRank()+"/10");
		player.getPackets().sendIComponentText(1158, 20, "<col=A1A1A1>Your RSMV rank increases after you prestige for every 100 RSMV points. </col>");
		
		player.getPackets().sendIComponentText(1158, 23, "2.");
		player.getPackets().sendIComponentText(1158, 24, "Community Points:");
		player.getPackets().sendIComponentText(1158, 26, ""+player.getCommunityPoints()+"/250");
		player.getPackets().sendIComponentText(1158, 25, "<col=A1A1A1>Staff members give you these points for being nice and helping people on the server.</col>");
		
		player.getPackets().sendIComponentText(1158, 28, "3.");
		player.getPackets().sendIComponentText(1158, 29, "OUTFIT 1:");
		player.getPackets().sendIComponentText(1158, 30, ""+player.getOutfitName1());
		player.getPackets().sendIComponentText(1158, 31, (player.savedOutfit1 == true) ? "<col=1ADB3E>SAVED</col>" : "<col=ff0000>EMPTY</col>");
		
		player.getPackets().sendIComponentText(1158, 33, "4.");
		player.getPackets().sendIComponentText(1158, 34, "OUTFIT 2:");
		player.getPackets().sendIComponentText(1158, 35, ""+player.getOutfitName2());
		player.getPackets().sendIComponentText(1158, 36, (player.savedOutfit2 == true) ? "<col=1ADB3E>SAVED</col>" : "<col=ff0000>EMPTY</col>");
		
		player.getPackets().sendIComponentText(1158, 38, "5.");
		player.getPackets().sendIComponentText(1158, 39, "OUTFIT 3:");
		player.getPackets().sendIComponentText(1158, 40, ""+player.getOutfitName3());
		player.getPackets().sendIComponentText(1158, 41, (player.savedOutfit3 == true) ? "<col=1ADB3E>SAVED</col>" : "<col=ff0000>EMPTY</col>");
		
		player.getPackets().sendIComponentText(1158, 43, "6.");
		player.getPackets().sendIComponentText(1158, 44, "OUTFIT 4:");
		player.getPackets().sendIComponentText(1158, 45, ""+player.getOutfitName4());
		player.getPackets().sendIComponentText(1158, 46, (player.savedOutfit4 == true) ? "<col=1ADB3E>SAVED</col>" : "<col=ff0000>EMPTY</col>");
		
		player.getPackets().sendIComponentText(1158, 48, "7.");
		player.getPackets().sendIComponentText(1158, 49, "TELELOCK:");
		player.getPackets().sendIComponentText(1158, 50, "<col=A1A1A1>Disable or enable any teleportation action from items to objects. ;;switchtelelock to toggle.</col>");
		player.getPackets().sendIComponentText(1158, 51, (player.telelocked == false) ? "<col=ff0000>DISABLED</col>" : "<col=1ADB3E>ENABLED</col>");
		
		player.getPackets().sendIComponentText(1158, 52, "8.");
		
		player.getPackets().sendIComponentText(1158, 53, "9.");
		/*player.getPackets().sendIComponentText(1158, 26, ""+player.getSavedRemotes());
		player.getPackets().sendIComponentText(1158, 25, "<col=A1A1A1>Obtained via training Dungeoneering.</col>");
		player.getPackets().sendIComponentText(1158, 29, "Quest Points:");
		player.getPackets().sendIComponentText(1158, 31, ""+player.getSavedAnimgfxs());
		player.getPackets().sendIComponentText(1158, 30, "<col=A1A1A1>Obtained via quest completion.</col>");
		player.getPackets().sendIComponentText(1158, 34, "Trivia Points");
		player.getPackets().sendIComponentText(1158, 35, "<col=A1A1A1>Obtained via Answering the trivia system correctly.");
		player.getPackets().sendIComponentText(1158, 36, "" + player.getSavedLocations(category));
		player.getPackets().sendIComponentText(1158, 49, "Raid Alerts:");
		player.getPackets().sendIComponentText(1158, 51, (player.getSavedTeleports == true) ? "<col=ff0000>Disabled</col>" : "<col=1ADB3E>Enabled</col>");
		player.getPackets().sendIComponentText(1158, 50, "<col=A1A1A1>Type ::togglera to toggle this on and off.</col>");
		player.getPackets().sendIComponentText(1158, 54, "Currency Alerts:");
		player.getPackets().sendIComponentText(1158, 56, (player.elveronCurrencyAlerts == true) ? "<col=ff0000>Disabled</col>" : "<col=1ADB3E>Enabled</col>");
		player.getPackets().sendIComponentText(1158, 55, "<col=A1A1A1>You may enable or disable credit messages.</col>");*/
	}

}
