package com.rs.game.player.content;

import com.rs.game.player.Player;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.Animation;
import com.rs.utils.Utils;

/**
 * @author Taurus
 *
 */

public class FountainOfHeroes {
	/*
	private static final int[] AMULETS = {1704, 1706, 1708, 1710};
	private static final int[] RINGS = {2572, 20653, 20655, 20657};
	private static final int[] BRACELETS = {11120, 11122, 11124, 11126};
	
	public static boolean hasJewellery(Player p, int itemId) {
		return (p.getInventory().containsItem(itemId, 1));
	}
	
	public static void rechargeJewellery(Player p, int itemId) {
		if (hasJewellery(p)) {
			p.setNextAnimation(new Animation(827));
			p.lock(2);
			for(int AMULET : AMULETS) {
				for(int RING : RINGS) {
					for(int BRACELET : BRACELETS) {
						sendDialogue(p);
						handleRecharge(p, itemId);
					}
				}
			}
		}
	}
	
	public static void handleRecharge(Player p, int itemId) {
		for(int AMULET : AMULETS) {
			if(itemId == AMULET) {
				//
			}
		}
		for(int RING : RINGS) {
			if(itemId == RING) {
				//
			}
		}
		for(int BRACELET : BRACELETS) {
			if(itemId == BRACELET) {
				//
			}
		}
	}
	
	public static void sendDialogue(Player p, int itemId) {
		switch(itemId) {
			case AMULET:
				sendHandedItem(itemId, "You feel a power emanating from the fountain as it",
										"recharges all your amulets. You can now rub the",
										"amulet to teleport and wear them to get more gems",
										"whilst mining.");
			break;
			case RING:
				sendHandedItem(itemId, "You feel a power emanating from the fountain as it",
										"recharges all your rings. You can now rub the",
										"amulet to teleport and wear them to get more gems",
										"whilst mining.");
			break;
			case BRACELET:
				sendHandedItem(itemId, "You feel a power emanating from the fountain as it",
										"recharges all your bracelets. You can now rub the",
										"amulet to teleport and wear them to get more gems",
										"whilst mining.");
			break;
		}
	}
	*/
}