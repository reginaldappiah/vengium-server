package com.rs.game.player.content;

import com.rs.game.Animation;
import com.rs.game.ForceTalk;
import com.rs.game.player.Player;
import com.rs.game.player.PublicChatMessage;
import com.rs.utils.Utils;

/**
 * Slappin' dat marcupial.
 * @author Taylor Moon<Axter>
 *
 */
public class ToyHorsey {
	
	
	public static String[] chats = {//Weird ikr?
			"Come on Dobbin, we can win the race!", 
			"Hi-ho, Silver and away!",
			"Neaahhhyyy! " };
	/**
	 * uses the horsey.
	 * @Param player
	 */
	public static void useBrownHorsey(Player player) {
		player.lock();
		int random = Utils.random(3);
		int i = Utils.random(3);
		player.sendPublicChatMessage(new PublicChatMessage(chats[random], 0));
		player.setNextAnimation(new Animation (918));
		player.unlock();
	}
	public static void useWhiteHorsey(Player player) {
		player.lock();
		int random = Utils.random(3);
		int i = Utils.random(3);
		player.sendPublicChatMessage(new PublicChatMessage(chats[random], 0));
		player.setNextAnimation(new Animation (919));
		player.unlock();
	}
	public static void useBlackHorsey(Player player) {
		player.lock();
		int random = Utils.random(3);
		int i = Utils.random(3);
		player.sendPublicChatMessage(new PublicChatMessage(chats[random], 0));
		player.setNextAnimation(new Animation (920));
		player.unlock();
	}
	public static void useGrayHorsey(Player player) {
		player.lock();
		int random = Utils.random(3);
		int i = Utils.random(3);
		player.sendPublicChatMessage(new PublicChatMessage(chats[random], 0));
		player.setNextAnimation(new Animation (921));
		player.unlock();
	}
	

}