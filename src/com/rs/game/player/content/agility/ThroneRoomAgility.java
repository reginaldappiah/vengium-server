package com.rs.game.player.content.agility;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.ForceMovement;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;
import com.rs.utils.Utils.EntityDirection;

public class ThroneRoomAgility {

	public static void handlejumps(final Player player) {
		
	}
	public static void Jumpto1 (final Player player) {
		if(!Agility.hasLevel(player, 90))
			return;
		player.lock(1);
		player.setNextAnimation(new Animation(11230));
		final WorldTile toTile = (new WorldTile(2338, 4238, 1));
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 1, ForceMovement.SOUTH));
		 WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.setNextWorldTile(new WorldTile(2338, 4238, 1));
				player.getSkills().addXp(Skills.AGILITY, 15);
				stop();
			}
			 
		 }, 0);
	}
	public static void Jumpto2 (final Player player) {
		if(!Agility.hasLevel(player, 90))
			return;
		if (player.getDirection()==1)
		player.lock(1);
		player.setNextAnimation(new Animation(11230));
		 WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.setNextWorldTile(new WorldTile(2338, 4240, 1));
				player.getSkills().addXp(Skills.AGILITY, 15);
				stop();
			}
			 
		 }, 0);
	}
	public static void Jumpto3 (final Player player) {
		if(!Agility.hasLevel(player, 90))
			return;
		player.lock(1);
		player.setNextAnimation(new Animation(11230));
		final WorldTile toTile = (new WorldTile(2339, 4239, 1));
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 1, ForceMovement.NORTHEAST));
		 WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.setNextWorldTile(new WorldTile(2339, 4239, 1));
				player.getSkills().addXp(Skills.AGILITY, 15);
				stop();
			}
			 
		 }, 0);
	}
	public static void Jumpto4 (final Player player) {
		if(!Agility.hasLevel(player, 90))
			return;
		player.lock(1);
		player.setNextAnimation(new Animation(11230));
		 WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.setNextWorldTile(new WorldTile((player.getX()+1), (player.getY()-3), (player.getPlane()+1)));
				player.getSkills().addXp(Skills.AGILITY, 15);
				stop();
			}
			 
		 }, 0);
	}
	public static void Jumpto5 (final Player player) {
		if(!Agility.hasLevel(player, 90))
			return;
		player.lock(1);
		player.setNextAnimation(new Animation(11230));
		 WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.setNextWorldTile(new WorldTile((player.getX()+1), (player.getY()-3), (player.getPlane()+1)));
				player.getSkills().addXp(Skills.AGILITY, 15);
				stop();
			}
			 
		 }, 0);
	}
	public static void Jump1 (final Player player) {
		if(!Agility.hasLevel(player, 90))
			return;
		player.lock(1);
		player.setNextAnimation(new Animation(11230));
		final WorldTile toTile = (new WorldTile((player.getX()+1), (player.getY()-3), (player.getPlane()+1)));
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 2, ForceMovement.SOUTH));
		 WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				//player.setNextWorldTile(new WorldTile((player.getX()+1), (player.getY()-3), (player.getPlane()+1)));
				player.getSkills().addXp(Skills.AGILITY, 15);
				stop();
			}
			 
		 }, 0);
	}
	
public static void removeStage(Player player) {
	player.getTemporaryAttributtes().remove("ThroneRoomCourse");
}

public static void setStage(Player player, int stage) {
	player.getTemporaryAttributtes().put("ThroneRoomCourse", stage);
}

public static int getStage(Player player) {
	Integer stage = (Integer) player.getTemporaryAttributtes().get(
			"ThroneRoomCourse");
	if (stage == null)
		return -1;
	return stage;
}

}