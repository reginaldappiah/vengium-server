package com.rs.game.player.content.agility;

import com.rs.game.Animation;
import com.rs.game.ForceMovement;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class DorgeshKaanAgility {
	
	public static void walkDorgRope(final Player player) {
		final boolean running = player.getRun();
		player.setRunHidden(false);
		player.lock(7);
		player.addWalkSteps(2721, 5230, -1, false);
		WorldTasksManager.schedule(new WorldTask() {
			boolean secondloop;

			@Override
			public void run() {
				if (!secondloop) {
					secondloop = true;
					player.getAppearence().setRenderEmote(155);
				} else {
					player.getAppearence().setRenderEmote(-1);
					player.setRunHidden(running);
					if (getDorgStage(player) == 2)
						setDorgStage(player, 3);
					player.getSkills().addXp(Skills.AGILITY, 7);
					player.getPackets().sendGameMessage(
							"You passed the obstacle succesfully.", true);
					stop();
				}
			}
		}, 0, 8);
	}
	public static void walkBackDorgRope(final Player player) {
		final boolean running = player.getRun();
		player.setRunHidden(false);
		player.lock(7);
		player.addWalkSteps(2721, 5241, -1, false);
		WorldTasksManager.schedule(new WorldTask() {
			boolean secondloop;

			@Override
			public void run() {
				if (!secondloop) {
					secondloop = true;
					player.getAppearence().setRenderEmote(155);
				} else {
					player.getAppearence().setRenderEmote(-1);
					player.setRunHidden(running);
					player.getSkills().addXp(Skills.AGILITY, 7);
					player.getPackets().sendGameMessage(
							"You passed the obstacle with RSMV quickness.", true);
					stop();
				}
			}
		}, 0, 8);
	}
	public static void swingOnRopeSwing(final Player player, WorldObject object) {
		if(!Agility.hasLevel(player, 35))
			return;
		if(player.getX() != 2728 || player.getY() != 5228 || player.getPlane() != 3 ) {
			 player.getPackets().sendGameMessage("You'll need to get closer to make this jump.");
			 return;
		}
		 player.lock(4);
		 player.setNextAnimation(new Animation(751));
		 World.sendObjectAnimation(player, object, new Animation(497));
		 final WorldTile toTile = new WorldTile(2732, object.getY(), object.getPlane());
		 player.setNextForceMovement(new ForceMovement(player, 1, toTile, 3, ForceMovement.EAST));
		player.getSkills().addXp(Skills.AGILITY, 22);
		player.getPackets().sendGameMessage("You skilfully swing across.", true);
		 WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.setNextWorldTile(toTile);
				setDorgStage(player, 0);
			}	
			 
		 }, 1);
	}
	public static void swingOnRopeSwing2(final Player player, WorldObject object) {
		if(!Agility.hasLevel(player, 35))
			return;
		if(player.getX() != 2733) {
			 player.getPackets().sendGameMessage("You'll need to get closer to make this jump.");
			 return;
		}
		 player.lock(4);
		 player.setNextAnimation(new Animation(751));
		 World.sendObjectAnimation(player, object, new Animation(497));
		 final WorldTile toTile = new WorldTile(2729, object.getY(), object.getPlane());
		 player.setNextForceMovement(new ForceMovement(player, 1, toTile, 3, ForceMovement.WEST));
		player.getSkills().addXp(Skills.AGILITY, 22);
		player.getPackets().sendGameMessage("You skilfully swing across.", true);
		 WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.setNextWorldTile(toTile);
				setDorgStage(player, 1);
			}	
		
		 }, 0);
	}
	public static void crossMonkeyBars(final Player player, final WorldObject object) {
		if (!Agility.hasLevel(player, 48)) {
			player.sm("You must have an agility level of atleast 48 to perform this action.");
			return;
		}
		player.lock(4);
		final WorldTile toTile = new WorldTile(2740, 5215, 0);
		final WorldTile toTile2 = new WorldTile(2740, 5215, 2);
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
	public static void swingOnRopeSwing3(final Player player, WorldObject object) {
		if(!Agility.hasLevel(player, 35))
			return;
		if(player.getY() != 5216) {
			 player.getPackets().sendGameMessage("You'll need to get closer to make this jump.");
			 return;
		}
		 player.lock(4);
		 player.setNextAnimation(new Animation(751));
		 World.sendObjectAnimation(player, object, new Animation(497));
		 final WorldTile toTile = new WorldTile(2728, object.getY(), object.getPlane());
		 player.setNextForceMovement(new ForceMovement(player, 1, toTile, 3, ForceMovement.EAST));
		player.getSkills().addXp(Skills.AGILITY, 22);
		player.getPackets().sendGameMessage("You skilfully swing across.", true);
		 WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.setNextWorldTile(toTile);
				setDorgStage(player, 0);
			}	
			 
		 }, 1);
	}
	
	public static void swingOnRopeSwing4(final Player player, WorldObject object) {
		if(!Agility.hasLevel(player, 35))
			return;
		if(player.getY() != 2727) {
			 player.getPackets().sendGameMessage("You'll need to get closer to make this jump.");
			 return;
		}
		 player.lock(4);
		 player.setNextAnimation(new Animation(751));
		 World.sendObjectAnimation(player, object, new Animation(497));
		 final WorldTile toTile = new WorldTile(2731, object.getY(), object.getPlane());
		 player.setNextForceMovement(new ForceMovement(player, 1, toTile, 3, ForceMovement.WEST));
		player.getSkills().addXp(Skills.AGILITY, 22);
		player.getPackets().sendGameMessage("You skilfully swing across.", true);
		 WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.setNextWorldTile(toTile);
				setDorgStage(player, 1);
			}	
		
		 }, 0);
	}
	public static void SqueezeThroughWall(final Player player, WorldObject object) {
		if(!Agility.hasLevel(player, 35))
			return;
		player.lock(4);
		final WorldTile toTile = new WorldTile(2721, 5212, 3);
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 4, ForceMovement.EAST));
		player.getAppearence().setRenderEmote(157);
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
	public static void enterTunnel(final Player player, WorldObject object) {
		if(!Agility.hasLevel(player, 35))
			return;
		 player.lock(4);
		 player.setNextAnimation(new Animation(10580));
		 final WorldTile toTile = new WorldTile(object.getX(), player.getY() >= 5205 ?  5208 : 5205, object.getPlane());
		 player.setNextForceMovement(new ForceMovement(player, 0, toTile, 2, player.getY() >= 5208 ? ForceMovement.SOUTH : ForceMovement.NORTH));
		 WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.setNextWorldTile(toTile);
				player.getSkills().addXp(Skills.AGILITY, 1/20);
			}	
			 
		 }, 1);
	}
	public static void walkBackDorgRope2(final Player player) {
		if (player.getX() != 2720 || player.getY() != 5194
				|| player.getPlane() != 3)
			return;
		final boolean running = player.getRun();
		player.setRunHidden(false);
		player.lock(7);
		player.addWalkSteps(2720, 5205, -1, false);
		WorldTasksManager.schedule(new WorldTask() {
			boolean secondloop;

			@Override
			public void run() {
				if (!secondloop) {
					secondloop = true;
					player.getAppearence().setRenderEmote(155);
				} else {
					player.getAppearence().setRenderEmote(-1);
					player.setRunHidden(running);
					if (getDorgStage(player) == 2)
						setDorgStage(player, 3);
					player.getSkills().addXp(Skills.AGILITY, 7);
					player.getPackets().sendGameMessage(
							"You passed the obstacle succesfully.", true);
					stop();
				}
			}
		}, 0, 5);
	}
	public static void walkDorgRope2(final Player player) {
		if (player.getX() != 2720 || player.getY() != 5205
				|| player.getPlane() != 3)
			return;
		final boolean running = player.getRun();
		player.setRunHidden(false);
		player.lock(7);
		player.addWalkSteps(2720, 5194, -1, false);
		WorldTasksManager.schedule(new WorldTask() {
			boolean secondloop;

			@Override
			public void run() {
				if (!secondloop) {
					secondloop = true;
					player.getAppearence().setRenderEmote(155);
				} else {
					player.getAppearence().setRenderEmote(-1);
					player.setRunHidden(running);
					player.getSkills().addXp(Skills.AGILITY, 7);
					player.getPackets().sendGameMessage(
							"You passed the obstacle succesfully.", true);
					stop();
				}
			}
		}, 0, 5);
	}
	public static void crossMonkeyBars2(final Player player, final WorldObject object) {
		if (!Agility.hasLevel(player, 48)) {
			player.sm("You must have an agility level of atleast 48 to perform this action.");
			return;
		}
		player.lock(4);
		final WorldTile toTile = new WorldTile(2708, 5192, 0);
		final WorldTile toTile2 = new WorldTile(2708, 5192, 2);
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
public static void removeDorgStage(Player player) {
	player.getTemporaryAttributtes().remove("DorgCourse");
}

public static void setDorgStage(Player player, int stage) {
	player.getTemporaryAttributtes().put("DorgCourse", stage);
}

public static int getDorgStage(Player player) {
	Integer stage = (Integer) player.getTemporaryAttributtes().get(
			"DorgCourse");
	if (stage == null)
		return -1;
	return stage;
}
}