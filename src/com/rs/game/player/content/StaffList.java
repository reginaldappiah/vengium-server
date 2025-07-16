package com.rs.game.player.content;

import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.player.Player;

/**
 * @author Multak
 * 8/19/14
 */

public class StaffList {

	public static int INTER = 1158;

	public static void sendStaffInter(Player player) {
		player.getInterfaceManager().sendInterface(INTER);
		player.getPackets().sendIComponentText(INTER, 74, "Vengium Staff List");
		player.getPackets().sendIComponentText(INTER, 8, "1.");
		player.getPackets().sendIComponentText(INTER, 9, "Reg");
		player.getPackets().sendIComponentText(INTER, 10, "Creator of Vengium");
		player.getPackets().sendIComponentText(INTER, 11, World.containsPlayer("multak") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
		player.getPackets().sendIComponentText(INTER, 13, "2.");
		player.getPackets().sendIComponentText(INTER, 14, "Cole");
		player.getPackets().sendIComponentText(INTER, 15, "Head-Administrator of Vengium");
		player.getPackets().sendIComponentText(INTER, 16, World.containsPlayer("toshero") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
		player.getPackets().sendIComponentText(INTER, 18, "3.");
		player.getPackets().sendIComponentText(INTER, 19, "Oz");
		player.getPackets().sendIComponentText(INTER, 20, "Lead-Administrator of Vengium");
		player.getPackets().sendIComponentText(INTER, 21, World.containsPlayer("oz") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
		player.getPackets().sendIComponentText(INTER, 23, "4.");
		player.getPackets().sendIComponentText(INTER, 24, "Wally");
		player.getPackets().sendIComponentText(INTER, 25, "Hmm-Administrator of Vengium");
		player.getPackets().sendIComponentText(INTER, 26, World.containsPlayer("wally") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
		player.getPackets().sendIComponentText(INTER, 28, "5.");
		player.getPackets().sendIComponentText(INTER, 29, "Kait");
		player.getPackets().sendIComponentText(INTER, 30, "Supervillain-Administrator of Vengium");
		player.getPackets().sendIComponentText(INTER, 31, World.containsPlayer("misfit_hita") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
		player.getPackets().sendIComponentText(INTER, 33, "6.");
		player.getPackets().sendIComponentText(INTER, 34, "Jon");
		player.getPackets().sendIComponentText(INTER, 35, "JoneMarrow-Administrator of Vengium");
		player.getPackets().sendIComponentText(INTER, 36, World.containsPlayer("jon") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
		player.getPackets().sendIComponentText(INTER, 38, "7.");
		player.getPackets().sendIComponentText(INTER, 39, "Angel");
		player.getPackets().sendIComponentText(INTER, 40, "Halo-Administrator of Vengium");
		player.getPackets().sendIComponentText(INTER, 41, World.containsPlayer("angel") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
		player.getPackets().sendIComponentText(INTER, 43, "8.");
		player.getPackets().sendIComponentText(INTER, 44, "Chase");
		player.getPackets().sendIComponentText(INTER, 45, "Head-Moderator of Vengium");
		player.getPackets().sendIComponentText(INTER, 46, World.containsPlayer("chase") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
		player.getPackets().sendIComponentText(INTER, 48, "9.");
		player.getPackets().sendIComponentText(INTER, 49, "Hunter");
		player.getPackets().sendIComponentText(INTER, 50, "The Gainz-Moderator of Vengium");
		player.getPackets().sendIComponentText(INTER, 51, World.containsPlayer("zangetsu") || World.containsPlayer("hunter")  ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
		player.getPackets().sendIComponentText(INTER, 53, "10");
		player.getPackets().sendIComponentText(INTER, 54, "Lane");
		player.getPackets().sendIComponentText(INTER, 55, "Djent-Moderator of Vengium");
		player.getPackets().sendIComponentText(INTER, 56, World.containsPlayer("ahxchurch") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
	}

}
