package com.rs.game.player.content.magic;

import java.util.HashMap;
import java.util.Map;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;

public class Enchanting {
	
	public enum Sapphire {
		
		RING(1637, 2550),
		NECKLACE(1656, 3853),
		AMULET(1694, 1727),
		BRACELET(11072, 11074);
		
		private static Map<Integer, Sapphire> sapJewelry = new HashMap<Integer, Sapphire>();

		public static Sapphire forId(int unenchanted) {
			return sapJewelry.get(unenchanted);
		}

		static {
			for (Sapphire ring : Sapphire.values()) {
				sapJewelry.put(ring.getUnenchanted(), ring);
			}
		}
		
		private int unenchanted;
		private int enchanted;
		
		private Sapphire(int unenchanted, int enchanted) {
			this.unenchanted = unenchanted;
			this.enchanted = enchanted;
		}
		
		public int getUnenchanted() {
			return unenchanted;
		}
		
		public int getEnchanted() {
			return enchanted;
		}
	};
	
	public enum Emerald {
		
		RING(1639, 2552),
		NECKLACE(1658, 5521),
		AMULET(1677, 1729),
		BRACELET(11076, 11079);
		
		private static Map<Integer, Emerald> sapJewelry = new HashMap<Integer, Emerald>();

		public static Emerald forId(int unenchanted) {
			return sapJewelry.get(unenchanted);
		}

		static {
			for (Emerald ring : Emerald.values()) {
				sapJewelry.put(ring.getUnenchanted(), ring);
			}
		}
		
		private int unenchanted;
		private int enchanted;
		
		private Emerald(int unenchanted, int enchanted) {
			this.unenchanted = unenchanted;
			this.enchanted = enchanted;
		}
		
		public int getUnenchanted() {
			return unenchanted;
		}
		
		public int getEnchanted() {
			return enchanted;
		}
	};
	
	public enum Ruby {
		
		RING(1641, 2568),
		NECKLACE(1660, 11194),
		AMULET(1698, 1725),
		BRACELET(11085, 11088);
		
		private static Map<Integer, Ruby> sapJewelry = new HashMap<Integer, Ruby>();

		public static Ruby forId(int unenchanted) {
			return sapJewelry.get(unenchanted);
		}

		static {
			for (Ruby ring : Ruby.values()) {
				sapJewelry.put(ring.getUnenchanted(), ring);
			}
		}
		
		private int unenchanted;
		private int enchanted;
		
		private Ruby(int unenchanted, int enchanted) {
			this.unenchanted = unenchanted;
			this.enchanted = enchanted;
		}
		
		public int getUnenchanted() {
			return unenchanted;
		}
		
		public int getEnchanted() {
			return enchanted;
		}
	};
	
	public enum Diamond {
		
		RING(1643, 2570),
		NECKLACE(1662, 11090),
		AMULET(1700, 1731),
		BRACELET(11092, 11095);
		
		private static Map<Integer, Diamond> sapJewelry = new HashMap<Integer, Diamond>();

		public static Diamond forId(int unenchanted) {
			return sapJewelry.get(unenchanted);
		}

		static {
			for (Diamond ring : Diamond.values()) {
				sapJewelry.put(ring.getUnenchanted(), ring);
			}
		}
		
		private int unenchanted;
		private int enchanted;
		
		private Diamond(int unenchanted, int enchanted) {
			this.unenchanted = unenchanted;
			this.enchanted = enchanted;
		}
		
		public int getUnenchanted() {
			return unenchanted;
		}
		
		public int getEnchanted() {
			return enchanted;
		}
	};
	
	public enum Dragonstone {
		
		RING(1645, 2572),
		NECKLACE(1664, 11105),
		AMULET(1702, 1712),
		BRACELET(11115, 11118);
		
		private static Map<Integer, Dragonstone> sapJewelry = new HashMap<Integer, Dragonstone>();

		public static Dragonstone forId(int unenchanted) {
			return sapJewelry.get(unenchanted);
		}

		static {
			for (Dragonstone ring : Dragonstone.values()) {
				sapJewelry.put(ring.getUnenchanted(), ring);
			}
		}
		
		private int unenchanted;
		private int enchanted;
		
		private Dragonstone(int unenchanted, int enchanted) {
			this.unenchanted = unenchanted;
			this.enchanted = enchanted;
		}
		
		public int getUnenchanted() {
			return unenchanted;
		}
		
		public int getEnchanted() {
			return enchanted;
		}
	};
	
	public enum Onyx {
		
		RING(6575, 6583),
		NECKLACE(6577, 11128),
		AMULET(6581, 6585),
		BRACELET(11130, 11133);
		
		private static Map<Integer, Onyx> sapJewelry = new HashMap<Integer, Onyx>();

		public static Onyx forId(int unenchanted) {
			return sapJewelry.get(unenchanted);
		}

		static {
			for (Onyx ring : Onyx.values()) {
				sapJewelry.put(ring.getUnenchanted(), ring);
			}
		}
		
		private int unenchanted;
		private int enchanted;
		
		private Onyx(int unenchanted, int enchanted) {
			this.unenchanted = unenchanted;
			this.enchanted = enchanted;
		}
		
		public int getUnenchanted() {
			return unenchanted;
		}
		
		public int getEnchanted() {
			return enchanted;
		}
	};

	public static void handleEnchanting(Player player, Item item, int comp1) {
		player.getInterfaceManager().openGameTab(7);
		if (!player.canAlch()) {
			return;
		}
		switch(comp1) {
		//Sapphire
		case 29: {
			Sapphire sapphire = Sapphire.forId(item.getId());
			if (sapphire != null) {
				if (Magic.checkSpellRequirements(player, 7, true, 564, 1, Magic.WATER_RUNE, 1)) {
					player.setNextAnimation(new Animation(719));
					player.setNextGraphics(new Graphics(114, 0, 100));
					player.getInventory().deleteItem(sapphire.getUnenchanted(), 1);
					player.getInventory().addItem(sapphire.getEnchanted(), 1);
					player.getSkills().addXp(Skills.MAGIC, 17);
					player.setAlchDelay(1000L);
				}
			} else {
				player.getPackets().sendGameMessage("That item cannot be enchanted with this spell.");
			}
		}
		break;
		//Emerald
		case 41: {
			Emerald emerald = Emerald.forId(item.getId());
			if (emerald != null) {
				if (Magic.checkSpellRequirements(player, 27, true, 564, 1, Magic.AIR_RUNE, 3)) {
					player.setNextAnimation(new Animation(719));
					player.setNextGraphics(new Graphics(114, 0, 100));
					player.getInventory().deleteItem(emerald.getUnenchanted(), 1);
					player.getInventory().addItem(emerald.getEnchanted(), 1);
					player.getSkills().addXp(Skills.MAGIC, 37);
					player.setAlchDelay(1000L);
				}
			} else {
				player.getPackets().sendGameMessage("That item cannot be enchanted with this spell.");
			}
		}
		break;
		//Ruby
		case 53: {
			Ruby ruby = Ruby.forId(item.getId());
			if (ruby != null) {
				if (Magic.checkSpellRequirements(player, 49, true, 564, 1, Magic.FIRE_RUNE, 5)) {
					player.setNextAnimation(new Animation(719));
					player.setNextGraphics(new Graphics(114, 0, 100));
					player.getInventory().deleteItem(ruby.getUnenchanted(), 1);
					player.getInventory().addItem(ruby.getEnchanted(), 1);
					player.getSkills().addXp(Skills.MAGIC, 59);
					player.setAlchDelay(1000L);
				}
			} else {
				player.getPackets().sendGameMessage("That item cannot be enchanted with this spell.");
			}
		}
		break;
		//Diamond
		case 61: {
			Diamond diamond = Diamond.forId(item.getId());
			if (diamond != null) {
				if (Magic.checkSpellRequirements(player, 57, true, 564, 1, Magic.EARTH_RUNE, 10)) {
					player.setNextAnimation(new Animation(719));
					player.setNextGraphics(new Graphics(114, 0, 100));
					player.getInventory().deleteItem(diamond.getUnenchanted(), 1);
					player.getInventory().addItem(diamond.getEnchanted(), 1);
					player.getSkills().addXp(Skills.MAGIC, 67);
					player.setAlchDelay(1000L);
				}
			} else {
				player.getPackets().sendGameMessage("That item cannot be enchanted with this spell.");
			}
		}
		break;
		//Dragonstone
		case 76: {
			Dragonstone dragonstone = Dragonstone.forId(item.getId());
			if (dragonstone != null) {
				if (Magic.checkSpellRequirements(player, 68, true, 564, 1, Magic.EARTH_RUNE, 15, Magic.WATER_RUNE, 15)) {
					player.setNextAnimation(new Animation(719));
					player.setNextGraphics(new Graphics(114, 0, 100));
					player.getInventory().deleteItem(dragonstone.getUnenchanted(), 1);
					player.getInventory().addItem(dragonstone.getEnchanted(), 1);
					player.getSkills().addXp(Skills.MAGIC, 78);
					player.setAlchDelay(1000L);
				}
			} else {
				player.getPackets().sendGameMessage("That item cannot be enchanted with this spell.");
			}
		}
		break;
		//Onyx
		case 88: {
			Onyx onyx = Onyx.forId(item.getId());
			if (onyx != null) {
				if (Magic.checkSpellRequirements(player, 87, true, 564, 1, Magic.FIRE_RUNE, 20, Magic.EARTH_RUNE, 20)) {
					player.setNextAnimation(new Animation(719));
					player.setNextGraphics(new Graphics(114, 0, 100));
					player.getInventory().deleteItem(onyx.getUnenchanted(), 1);
					player.getInventory().addItem(onyx.getEnchanted(), 1);
					player.getSkills().addXp(Skills.MAGIC, 97);
					player.setAlchDelay(1000L);
				}
			} else {
				player.getPackets().sendGameMessage("That item cannot be enchanted with this spell.");
			}
		}
		break;
		}
	}
	
}
