package com.rs.game.player.content;

import java.util.HashMap;
import java.util.Map;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;


public class Burying {

	public enum Bone {
        REGULAR_BONE(526, 4),
        BURNT_BONE(528, 4),
        BAT_BONE(530, 4),
        BIG_BONE(532, 15),
        MONKEY_BONE(3179, 5),
        WOLF_BONE(2859, 4),
        JOGRE_BONE(3125, 15),
        ZOGRE_BONE(4812, 22),
        BABY_DRAGON_BONE(534, 30),
        DRAGON_BONE(536, 72),
        WYVERN_BONE(6812, 50),
        DAG_BONE(6729, 125),
        FROST_BONE(18830, 250);

		private int id;
		private double experience;

		private static Map<Integer, Bone> bones = new HashMap<Integer, Bone>();

		static {
			for (Bone bone : Bone.values()) {
				bones.put(bone.getId(), bone);
			}
		}

		public static Bone forId(int id) {
			return bones.get(id);
		}

		private Bone(int id, double experience) {
			this.id = id;
			this.experience = experience;
		}

		public int getId() {
			return id;
		}

		public double getExperience() {
			return experience;
		}

		public static void bury(final Player player, int inventorySlot) {
			final Item item = player.getInventory().getItem(inventorySlot);
			if (item == null || Bone.forId(item.getId()) == null)
				return;
			if (player.getBoneDelay() > Utils.currentTimeMillis())
				return;
			final Bone bone = Bone.forId(item.getId());
			final ItemDefinitions itemDef = new ItemDefinitions(item.getId());
			player.addBoneDelay(2000);
			player.getPackets().sendSound(2738, 0, 1);
			player.setNextAnimation(new Animation(827));
			player.getPackets().sendGameMessage(
					"You dig a hole in the ground...");
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					player.getPackets().sendGameMessage(
							"You bury the " + itemDef.getName().toLowerCase());
					player.getInventory().deleteItem(item.getId(), 1);
					player.getSkills().addXp(Skills.PRAYER,
							bone.getExperience());
					stop();
				}

			}, 2);
		}
	}
	public static boolean bury(final Player player, int slotId) {
		final Item item = player.getInventory().getItem(slotId);
		if (item == null || Bone.forId(item.getId()) == null)
			return false;
		if (player.getBoneDelay() > Utils.currentTimeMillis())
			return true;
		final Bone bone = Bone.forId(item.getId());
		final ItemDefinitions itemDef = new ItemDefinitions(item.getId());
		player.addBoneDelay(2000);
		player.getPackets().sendSound(2738, 0, 1);
		player.setNextAnimation(new Animation(827));
		player.getPackets().sendGameMessage(
				"You dig a hole in the ground...");
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.getPackets().sendGameMessage(
						"You bury the " + itemDef.getName().toLowerCase());
				player.getInventory().deleteItem(item.getId(), 1);
				player.getSkills().addXp(Skills.PRAYER,
						bone.getExperience());
				stop();
			}

		}, 2);
		return false;
	}
}