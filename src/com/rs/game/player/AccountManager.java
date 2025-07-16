package com.rs.game.player;

import com.rs.game.player.Player;

/**
 * Author dsnop
 * 6/16/14
 */

public class AccountManager {
	
	private static int category;
	
	public static void sendInterface(Player player) {
		
		player.getInterfaceManager().sendInterface(1158);
		player.getPackets().sendIComponentText(1158, 74, "Elveron Profile: " + player.getDisplayName());
		player.getPackets().sendIComponentText(1158, 9, "Veron Credits:");
		//player.getPackets().sendIComponentText(1158, 11, ""+player.getVeronPoints());
		player.getPackets().sendIComponentText(1158, 10, "<col=A1A1A1>Obtained through completing invasions.</col>");
		player.getPackets().sendIComponentText(1158, 14, "Vote Points:");
		player.getPackets().sendIComponentText(1158, 16, ""+player.getVotes());
		player.getPackets().sendIComponentText(1158, 15, "<col=A1A1A1>Obtained through voting.</col>");
		player.getPackets().sendIComponentText(1158, 19, "Loyalty Points:");
		//player.getPackets().sendIComponentText(1158, 21, ""+player.getLoyaltyPoints());
		player.getPackets().sendIComponentText(1158, 20, "<col=A1A1A1>Obtain through being active.</col>");
		player.getPackets().sendIComponentText(1158, 24, "Dung Tokens:");
		//player.getPackets().sendIComponentText(1158, 26, ""+player.getDungTokens());
		player.getPackets().sendIComponentText(1158, 25, "<col=A1A1A1>Obtained via training Dungeoneering.</col>");
		player.getPackets().sendIComponentText(1158, 29, "Quest Points:");
		//player.getPackets().sendIComponentText(1158, 31, ""+player.questPoints);
		player.getPackets().sendIComponentText(1158, 30, "<col=A1A1A1>Obtained via quest completion.</col>");
		player.getPackets().sendIComponentText(1158, 34, "Trivia Points");
		player.getPackets().sendIComponentText(1158, 35, "<col=A1A1A1>Obtained via Answering the trivia system correctly.");
		//player.getPackets().sendIComponentText(1158, 36, "" + player.getTriviaPoints(category));
		player.getPackets().sendIComponentText(1158, 49, "Raid Alerts:");
		//player.getPackets().sendIComponentText(1158, 51, (player.raidAlertsDisabled == true) ? "<col=ff0000>Disabled</col>" : "<col=1ADB3E>Enabled</col>");
		player.getPackets().sendIComponentText(1158, 50, "<col=A1A1A1>Type ::togglera to toggle this on and off.</col>");
		player.getPackets().sendIComponentText(1158, 54, "Currency Alerts:");
		//player.getPackets().sendIComponentText(1158, 56, (player.elveronCurrencyAlerts == true) ? "<col=ff0000>Disabled</col>" : "<col=1ADB3E>Enabled</col>");
		player.getPackets().sendIComponentText(1158, 55, "<col=A1A1A1>You may enable or disable credit messages.</col>");
	}

}
