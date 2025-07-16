package com.rs.game.player;

import com.rs.game.player.content.FriendChatsManager;


public class Starter {

	public static final int MAX_STARTER_COUNT = 10;

	public static void appendStarter(Player player) {
		player.getHintIconsManager().removeUnsavedHintIcon();
		player.getMusicsManager().reset();
		player.getCombatDefinitions().setAutoRelatie(false);
		player.getCombatDefinitions().refreshAutoRelatie();
		player.starter = 1;
		player.starterstage = 1;
		FriendChatsManager.joinChat("Multak", player);
		FriendChatsManager.refreshChat(player);
	}
}