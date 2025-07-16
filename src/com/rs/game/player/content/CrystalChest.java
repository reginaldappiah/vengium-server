package com.rs.game.player.content;

import com.rs.game.player.Player;
import com.rs.game.Animation;
import com.rs.utils.Utils;

/**
 * @author Taurus
 *
 */

public class CrystalChest {
	
	private static final int[] seeds = {5290, 5288, 5289};
	private static final int[] runes = {563, 562, 564, 560, 561, 559, 558, 557, 556, 555, 554};
	private static final int[] rune_bottoms = {1079, 1093};
	
	public static final int key = 989;
	public static final int tooth = 985;
	public static final int loop = 987;
	
	public static boolean hasHalves(Player p){
		return (p.getInventory().containsItem(tooth, 1) && p.getInventory().containsItem(loop, 1));
	}
	
	public static boolean hasKey(Player p){
		return (p.getInventory().containsItem(key, 1));
	}
	
	public static void joinHalves(Player p) {
		if (hasHalves(p)){
			p.getInventory().deleteItem(tooth, 1);
			p.getInventory().deleteItem(loop, 1);
			p.getInventory().addItem(key, 1);
			p.sendMessage("You join the two halves of the key together.");
		}
	}
	
	public static void getReward(final Player p) {
		int possibility = Utils.random(11) + 1;
		p.getInventory().addItem(1631, 1);
		switch(possibility) {
			case 1:
				p.getInventory().addItem(seeds[Utils.random(seeds.length - 1)], 1);
			break;
			case 2:
				p.getInventory().addItem(5302, 2);
			break;
			case 3:
				p.getInventory().addItem(219, 2);
			break;
			case 4:
				for(int rune = 0; rune < runes.length; rune++) {
					if(runes[rune] == 563 || runes[rune] == 562 || runes[rune] == 564 || runes[rune] == 560 || runes[rune] == 561) {
						p.getInventory().addItem(runes[rune], 50);
					} else {
						p.getInventory().addItem(runes[rune], 200);
					}
				}
			break;
			case 5:
				p.getInventory().addItem(441, 200);
			break;
			case 6:
				p.getInventory().addItem(454, 150);
			break;
			case 7:
				p.getInventory().addItem(2363, 3);
			break;
			case 8:
				p.getInventory().addItem(1617, 3);
				p.getInventory().addItem(1619, 3);
			break;
			case 9:
				p.getInventory().addItem(987, 1);
			break;
			case 10:
				p.getInventory().addItem(985, 1);
			break;
			case 11:
				p.getInventory().addItem(1127, 1);
			break;
			case 12:
				p.getInventory().addItem(rune_bottoms[Utils.random(rune_bottoms.length - 1)], 1);
			break;
		}
	}
	
	public static void lootChest(final Player p) {
		if (hasKey(p)){
			p.setNextAnimation(new Animation(536));
			p.sendMessage("You unlock the chest with your key.");
			p.getInventory().deleteItem(key, 1);
			getReward(p);
			p.sendMessage("You find some treasure in the chest!");
		} else {
			p.sendMessage("The chest is locked.");
		}
	}
	
}