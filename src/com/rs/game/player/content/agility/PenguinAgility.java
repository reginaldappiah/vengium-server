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

public class PenguinAgility {

	public static void SteppingStone1(final Player player, final WorldObject object) {
		if(!Agility.hasLevel(player, 90))
			return;
		player.lock(1);
		player.setNextAnimation(new Animation(5669));
		final WorldTile toTile = new WorldTile(2631, 4057, 1);
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 1, ForceMovement.EAST));
		 WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.setNextWorldTile(toTile);
				player.getSkills().addXp(Skills.AGILITY, 15);
				stop();
			}
			 
		 }, 1);
	}

	public static void SteppingStone2(final Player player, final WorldObject object) {
		if (player.getX() != 2631 && player.getY() != 4059
				)
			return;
		player.lock(1);
		player.setNextAnimation(new Animation(5669));
		final WorldTile toTile = new WorldTile(2631, 4059, 1);
		player.setNextForceMovement(new ForceMovement(player, 1, toTile, 2, ForceMovement.NORTH));
		 WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.setNextWorldTile(toTile);
				player.getSkills().addXp(Skills.AGILITY, 15);
				stop();
			}
			 
		 }, 1);
	}
	
	public static void SteppingStone3(final Player player, final WorldObject object) {
		if(!Agility.hasLevel(player, 90))
			return;
		player.lock(1);
		player.setNextAnimation(new Animation(5669));
		final WorldTile toTile = new WorldTile(2633, 4059, 1);
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 1, ForceMovement.EAST));
		 WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.setNextWorldTile(toTile);
				player.getSkills().addXp(Skills.AGILITY, 15);
				stop();
			}
			 
		 }, 1);
	}
	public static void SteppingStone4(final Player player, final WorldObject object) {
		if(!Agility.hasLevel(player, 90))
			return;
		player.lock(1);
		player.setNextAnimation(new Animation(5669));
		final WorldTile toTile = new WorldTile(2635, 4059, 1);
		player.setNextForceMovement(new ForceMovement(player, 1, toTile, 2, ForceMovement.EAST));
		 WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.setNextWorldTile(toTile);
				player.getSkills().addXp(Skills.AGILITY, 15);
				stop();
			}
			 
		 }, 1);
	}
	public static void SteppingStone5(final Player player, final WorldObject object) {
		if(!Agility.hasLevel(player, 90))
			return;
		player.lock(1);
		player.setNextAnimation(new Animation(5669));
		final WorldTile toTile = new WorldTile(2635, 4061, 1);
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 1, ForceMovement.NORTH));
		 WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.setNextWorldTile(toTile);
				player.getSkills().addXp(Skills.AGILITY, 15);
				stop();
			}
			 
		 }, 1);
	}
	public static void SteppingStone6(final Player player, final WorldObject object) {
		if(!Agility.hasLevel(player, 90))
			return;
		player.lock(1);
		player.setNextAnimation(new Animation(5669));
		final WorldTile toTile = new WorldTile(2635, 4063, 1);
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 1, ForceMovement.NORTH));
		 WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.setNextWorldTile(toTile);
				player.getSkills().addXp(Skills.AGILITY, 15);
				stop();
			}
			 
		 }, 1);
	}
	public static void SteppingStone7(final Player player, final WorldObject object) {
		if(!Agility.hasLevel(player, 90))
			return;
		player.lock(1);
		player.setNextAnimation(new Animation(5669));
		final WorldTile toTile = new WorldTile(2635, 4065, 1);
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 1, ForceMovement.NORTH));
		 WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.setNextWorldTile(toTile);
				player.getSkills().addXp(Skills.AGILITY, 15);
				stop();
			}
			 
		 }, 1);
	}
public static void removeKethsiStage(Player player) {
	player.getTemporaryAttributtes().remove("PenguinCourse");
}

public static void setKethsiStage(Player player, int stage) {
	player.getTemporaryAttributtes().put("PenguinCourse", stage);
}

public static int getKethsiStage(Player player) {
	Integer stage = (Integer) player.getTemporaryAttributtes().get(
			"PenguinCourse");
	if (stage == null)
		return -1;
	return stage;
}
}