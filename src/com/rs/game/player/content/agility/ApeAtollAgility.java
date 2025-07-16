package com.rs.game.player.content.agility;


import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.ForceMovement;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;
import com.rs.utils.Utils.EntityDirection;

public class ApeAtollAgility {

	 public static void climbDownTropicalTree(final Player player, final WorldObject object) {
			if (!Agility.hasLevel(player, 48)) {
				player.sm("You must have an agility level of atleast 48 to perform this action.");
			    return;
			}
			if (player.getEquipment().getWeaponId() != 4024) {
			    player.sm("You need to transform into a ninja monkey to use the ape atoll courses.");
			    return;
			}
			player.lock();
			final WorldTile toTile = new WorldTile(2770, 2747, 0);
			final WorldTile toTile2 = new WorldTile(2769, 2746, 1);
			player.setNextForceMovement(new ForceMovement(player, 1, toTile2, 7, ForceMovement.NORTH));
			player.addWalkSteps(2758, 2735, 3);
			player.getAppearence().setRenderEmote(740);
			player.sm("You climb the vine...");
			WorldTasksManager.schedule(new WorldTask() {
			    @Override
			    public void run() {
				player.sm("..And make it carefully to the end of it.");
				player.setNextWorldTile(toTile);
				player.getSkills().addXp(Skills.AGILITY, 55);
				player.getAppearence().setRenderEmote(-1);
				player.unlock();
				stop();
			    }
			}, 5, 3);
	    }

	    public static void climbDownVine(final Player player, WorldObject object) {
			if (!Agility.hasLevel(player, 48)) {
				player.sm("You must have an agility level of atleast 48 to perform this action.");
			    return;
			}
			if (player.getEquipment().getWeaponId() != 4024) {
			    player.getPackets().sendGameMessage("You need to transform into a ninja monkey to use the ape atoll courses.");
			    return;
			}
			player.lock(3);
			final WorldTile toTile = new WorldTile(player.getX(), player.getY(), 0);
			WorldTasksManager.schedule(new WorldTask() {
			    @Override
			    public void run() {
				player.sm("You climb down the vine.");
				player.setNextWorldTile(toTile);
				player.getSkills().addXp(Skills.AGILITY, 36);
				stop();
			    }
			}, 1);
	    }

	    public static void climbUpSkullSlope(final Player player, final WorldObject object) {
			if (!Agility.hasLevel(player, 48)) {
				player.sm("You must have an agility level of atleast 48 to perform this action.");
			    return;
			}
			if (player.getEquipment().getWeaponId() != 4024) {
			    player.sm("You need to transform into a ninja monkey to use the ape atoll courses.");
			    return;
			}
			player.lock(4);
			final WorldTile toTile = new WorldTile(2743, 2741, 0);
			player.setNextForceMovement(new ForceMovement(player, 0, toTile, 3, ForceMovement.WEST));
			player.getAppearence().setRenderEmote(739);
			WorldTasksManager.schedule(new WorldTask() {
			    @Override
			    public void run() {
				player.sm("You climb up the skull slope.");
				player.setNextWorldTile(toTile);
				player.getSkills().addXp(Skills.AGILITY, 45);
				player.getAppearence().setRenderEmote(-1);
				stop();
			    }
			}, 2);
	    }

	    public static void climbUpTropicalTree(final Player player, WorldObject object) {
			if (!Agility.hasLevel(player, 48)) {
				player.sm("You must have an agility level of atleast 48 to perform this action.");
			    return;
			}
			if (player.getEquipment().getWeaponId() != 4024) {
			    player.sm("You need to transform into a ninja monkey to use the ape atoll courses.");
			    return;
			}
			player.lock(3);
			final WorldTile toTile = new WorldTile(2752, 2742, 2);
			WorldTasksManager.schedule(new WorldTask() {
			    @Override
			    public void run() {
				player.sm("You climb up the tree...");
				player.setNextAnimation(new Animation(1382));
				player.setNextWorldTile(toTile);
				player.getSkills().addXp(Skills.AGILITY, 25);
				stop();
			    }
			}, 1);
	    }

	    public static void crossMonkeyBars(final Player player, final WorldObject object) {
			if (!Agility.hasLevel(player, 48)) {
				player.sm("You must have an agility level of atleast 48 to perform this action.");
				return;
			}
			if (player.getEquipment().getWeaponId() != 4024) {
			    player.sm("You need to transform into a ninja monkey to use the ape atoll courses.");
			    return;
			}
			player.lock(4);
			final WorldTile toTile = new WorldTile(2747, 2741, 0);
			final WorldTile toTile2 = new WorldTile(2747, 2741, 2);
			player.setNextForceMovement(new ForceMovement(player, 0, toTile2, 4, ForceMovement.WEST));
			player.getAppearence().setRenderEmote(744);
			player.sm("You jump to the monkey bars...");
			WorldTasksManager.schedule(new WorldTask() {
			    @Override
			    public void run() {
				player.sm("..And made it carefully to the other side.");
				player.getAppearence().setRenderEmote(-1);
				player.setNextWorldTile(toTile);
				player.getSkills().addXp(Skills.AGILITY, 35);
				stop();
			    }
			}, 3);
	    }

	    public static void jumpSteppingStone(final Player player, WorldObject object) {
			if (!Agility.hasLevel(player, 48)) {
				player.sm("You must have an agility level of atleast 48 to perform this action.");
			    return;
			}
			if (player.getEquipment().getWeaponId() != 4024) {
			    player.sm("You need to transform into a ninja monkey to use the ape atoll courses.");
			    return;
			}
			player.lock(3);
			final WorldTile toTile = new WorldTile(object.getX(), object.getY(), object.getPlane());
			final WorldTile toTile2 = new WorldTile(player.getX() == 2755 ? 2753 : 2755, 2742, object.getPlane());
			final WorldTile WaterTile = new WorldTile(2756, 2746, object.getPlane());
			final WorldTile Land = new WorldTile(2757, 2746, object.getPlane());
			WorldTasksManager.schedule(new WorldTask() {
			    @Override
			    public void run() {
				player.sm("You jump to the stepping stone...");
				player.setNextAnimation(new Animation(1381));
				player.setNextWorldTile(toTile);
				WorldTasksManager.schedule(new WorldTask() {
				    @Override
				    public void run() {
					if (Utils.random(5) == 0) {
					    player.setNextAnimation(new Animation(1381));
					    player.sm("..And accidently fall to the water.");
					    player.applyHit(new Hit(player, Utils.random(200),
						    HitLook.REGULAR_DAMAGE));
					    player.setNextForceMovement(new ForceMovement(player, 0, WaterTile, 3, ForceMovement.NORTH));
					    player.getAppearence().setRenderEmote(741);
					    WorldTasksManager.schedule(new WorldTask() {
						@Override
						public void run() {
						    player.getAppearence().setRenderEmote(-1);
						    player.setNextWorldTile(Land);
						    stop();
						}
					    }, 1);
					    stop();
					} else {
					    player.setNextAnimation(new Animation(1381));
					    player.setNextWorldTile(toTile2);
					    player.getSkills().addXp(Skills.AGILITY, 15);
					    player.sm("..And made it carefully to the other side.");
					    stop();
					}
				    }
				}, 1);
				stop();
			    }
			}, 1);
	    }

	    public static void swingRope(final Player player, WorldObject object) {
			if (!Agility.hasLevel(player, 48)) {
				player.sm("You must have an agility level of atleast 48 to perform this action.");
			    return;
			}
			if (player.getX() == 2756)
			    return;
			if (player.getEquipment().getWeaponId() != 4024) {
			    player.sm("You need to be a ninja monkey to be able to do this agility.");
			    return;
			}
			player.lock(4);
			player.setNextAnimation(new Animation(1388));
			World.sendObjectAnimation(player, object, new Animation(497));
			final WorldTile toTile = new WorldTile(2756, 2731, object.getPlane());
			player.setNextForceMovement(new ForceMovement(player, 1, toTile, 3, ForceMovement.EAST));
			player.getSkills().addXp(Skills.AGILITY, 22);
			player.sm("You skilfully swing across.", true);
			WorldTasksManager.schedule(new WorldTask() {
			    @Override
			    public void run() {
				player.setNextWorldTile(toTile);
				stop();
			    }
			}, 1);
	    }
	}