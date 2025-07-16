package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.player.Player;




/**
 * @author dsnop
 * 5/25/14
 */

public class StaffList {

	public static int INTER = 1158;

	public static void sendStaffInter(Player player) {
		player.getInterfaceManager().sendInterface(INTER);
		player.getPackets().sendIComponentText(INTER, 74, "Vengium Staff List");
		player.getPackets().sendIComponentText(INTER, 8, "--");
		player.getPackets().sendIComponentText(INTER, 9, "Username");
		player.getPackets().sendIComponentText(INTER, 10, "<col=ff0000>Rank</col>");
		player.getPackets().sendIComponentText(INTER, 11, "Online Status");
		player.getPackets().sendIComponentText(INTER, 13, "1.");
		player.getPackets().sendIComponentText(INTER, 14, "Multak");
		player.getPackets().sendIComponentText(INTER, 15, "Creator of " + Settings.SERVER_NAME + "");
		player.getPackets().sendIComponentText(INTER, 16, World.containsPlayer("Multak") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
		player.getPackets().sendIComponentText(INTER, 18, "2.");
		player.getPackets().sendIComponentText(INTER, 19, "Cole");
		player.getPackets().sendIComponentText(INTER, 20, "Head-Administrator of " + Settings.SERVER_NAME + "");
		player.getPackets().sendIComponentText(INTER, 21, World.containsPlayer("Toshero") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
		player.getPackets().sendIComponentText(INTER, 23, "3.");
		player.getPackets().sendIComponentText(INTER, 24, "Troy");
		player.getPackets().sendIComponentText(INTER, 25, "Administrator of " + Settings.SERVER_NAME + "");
		player.getPackets().sendIComponentText(INTER, 26, World.containsPlayer("Wolf") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
		player.getPackets().sendIComponentText(INTER, 28, "4.");
		player.getPackets().sendIComponentText(INTER, 29, "Enzo");
		player.getPackets().sendIComponentText(INTER, 30, "Administrator of " + Settings.SERVER_NAME + "");
		player.getPackets().sendIComponentText(INTER, 31, World.containsPlayer("Enzo") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
		player.getPackets().sendIComponentText(INTER, 33, "5.");
		player.getPackets().sendIComponentText(INTER, 34, "Omar");
		player.getPackets().sendIComponentText(INTER, 35, "Administrator of " + Settings.SERVER_NAME + "");
		player.getPackets().sendIComponentText(INTER, 36, World.containsPlayer("Blackbandwar") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
		player.getPackets().sendIComponentText(INTER, 37, "6.");
		player.getPackets().sendIComponentText(INTER, 38, "Stian");
		player.getPackets().sendIComponentText(INTER, 39, "Administrator of " + Settings.SERVER_NAME + "");
		player.getPackets().sendIComponentText(INTER, 40, World.containsPlayer("Toiletguide") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
		player.getPackets().sendIComponentText(INTER, 41, "7.");
		player.getPackets().sendIComponentText(INTER, 42, "Trey");
		player.getPackets().sendIComponentText(INTER, 43, "Head-Administrator of " + Settings.SERVER_NAME + "");
		player.getPackets().sendIComponentText(INTER, 44, World.containsPlayer("99orWoh") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
		player.getPackets().sendIComponentText(INTER, 45, "8.");
		player.getPackets().sendIComponentText(INTER, 46, "Seb");
		player.getPackets().sendIComponentText(INTER, 47, "Moderator of" + Settings.SERVER_NAME + "");
		player.getPackets().sendIComponentText(INTER, 48, World.containsPlayer("Sebster107") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
		player.getPackets().sendIComponentText(INTER, 49, "9.");
		player.getPackets().sendIComponentText(INTER, 50, "Lane");
		player.getPackets().sendIComponentText(INTER, 51, "Moderator of" + Settings.SERVER_NAME + "");
		player.getPackets().sendIComponentText(INTER, 52, World.containsPlayer("Tosvillain") || World.containsPlayer("ahxchurch") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
		player.getPackets().sendIComponentText(INTER, 53, "10.");
		player.getPackets().sendIComponentText(INTER, 54, "Hunter");
		player.getPackets().sendIComponentText(INTER, 55, "Moderator of" + Settings.SERVER_NAME + "");
		player.getPackets().sendIComponentText(INTER, 56, World.containsPlayer("Zangetsu") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
	}

}
