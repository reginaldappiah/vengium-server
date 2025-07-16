package com.rs.game.player.content.agility;

import com.rs.game.Animation;
import com.rs.game.ForceMovement;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class KethsiAgility {

public static void ClimbUpRamp(final Player player) {
player.teleportPlayer(4020, 5707, 1);
}
public static void ClimbDownRamp(final Player player) {
player.teleportPlayer(4019, 5712, 0);
}
public static void runUpWall(final Player player, WorldObject object) {
	if(!Agility.hasLevel(player, 90))
		return;
	player.lock(10);
	final WorldTile toTile = new WorldTile(4013, 5725, 2);
	 WorldTasksManager.schedule(new WorldTask() {
		 
		boolean secondLoop;
		@Override
		public void run() {
			
			if(!secondLoop) {
				player.setNextForceMovement(new ForceMovement(player, 7, toTile, 8, ForceMovement.NORTH));
				player.setNextAnimation(new Animation(10492));
				secondLoop = true;
			}else{
				player.setNextAnimation(new Animation(10493));
				player.setNextWorldTile(toTile);
				player.getSkills().addXp(Skills.AGILITY, 15);
				stop();
			}
			
		}
		 
	 }, 1, 6);
}
public static void climbUpWall(final Player player) {
	if(!Agility.hasLevel(player, 90)) {
		player.getPackets().sendGameMessage("You need 85 Agility to use this.");
		return;
	}
	player.getPackets().sendGameMessage("You climb the wall majestically...", true);
	player.useStairs(10023, new WorldTile((player.getX()-1), player.getY(), (player.getPlane()+1)), 3, 4,
			"...to the plantform above.");
	WorldTasksManager.schedule(new WorldTask() {
		@Override
		public void run() {
			if (getKethsiStage(player) == 0)
			setKethsiStage(player, 1);
			player.getSkills().addXp(Skills.AGILITY, 19);
		}
	}, 1);
}
public static void PreSwing(final Player player, final WorldObject object) {
	if (player.getX() != 4010 || player.getY() != 5724
			|| player.getPlane() != 3)
	player.lock(3);
	player.setNextAnimation(new Animation(11784));
	final WorldTile toTile = new WorldTile(player.getX(), 5726, object.getPlane());
	player.setNextForceMovement(new ForceMovement(player, 0, toTile, 2, ForceMovement.NORTH));
	 WorldTasksManager.schedule(new WorldTask() {
		 int stage;
		@Override
		public void run() {
			if(stage == 1) {
				player.setNextWorldTile(toTile);
				player.setNextAnimation(new Animation(11785));
				Swing(player,object);
				stop();
				}
				stage++;
			}
		 
	 }, 0, 1);
}
public static void Swing(final Player player, final WorldObject object) {
	if(!Agility.hasLevel(player, 90))
		return;
	player.lock(4);
	final WorldTile toTile = new WorldTile(player.getX(), 5731, object.getPlane());
	player.setNextForceMovement(new ForceMovement(player, 0, toTile, 1, ForceMovement.NORTH));
	 WorldTasksManager.schedule(new WorldTask() {
		 int stage;
		@Override
		public void run() {
			if (stage == 0) {
				player.setNextAnimation(new Animation(11789));
				player.setNextWorldTile(toTile);
			} else if (stage == 1) {
				Swing1(player,object);
				stop();
				}
				stage++;
			}
		 
	 }, 0, 1);
}
public static void Swing1(final Player player, final WorldObject object) {
	if(!Agility.hasLevel(player, 90))
		return;
	player.lock(4);
	final WorldTile NextTile = new WorldTile(player.getX(), 5735, object.getPlane());
	player.setNextForceMovement(new ForceMovement(player, 2, NextTile, 3, ForceMovement.NORTH));
	 WorldTasksManager.schedule(new WorldTask() {
		 int stage;
		@Override
		public void run() {
		 if (stage == 3) {				
				player.setNextWorldTile(NextTile);
				Swing2(player,object);
				stop();
				}
				stage++;
			}
		 
	 }, 0, 1);
}
public static void Swing2(final Player player, final WorldObject object) {
	if(!Agility.hasLevel(player, 90))
		return;
	player.lock(3);
	final WorldTile LastTile = new WorldTile(player.getX(), 5738, object.getPlane());
	player.setNextForceMovement(new ForceMovement(player, 0, LastTile, 1, ForceMovement.NORTH));
	 WorldTasksManager.schedule(new WorldTask() {
		 int stage;
		@Override
		public void run() {
		 if (stage == 2) {				
				player.setNextWorldTile(LastTile);
				stop();
				}
				stage++;
			}
		 
	 }, 0, 1);
}
public static void crossBalanceBeam(final Player player, final WorldObject object) {
	if(!Agility.hasLevel(player, 90))
		return;
	player.lock(4);
	final WorldTile toTile = new WorldTile(4013, 5739, 3);
	player.setNextForceMovement(new ForceMovement(player, 1, toTile, 3, ForceMovement.EAST));
	player.setNextAnimation(new Animation(16079));		
	player.getAppearence().setRenderEmote(330);
	 WorldTasksManager.schedule(new WorldTask() {
		@Override
		public void run() {
			player.setNextWorldTile(toTile);
			player.getSkills().addXp(Skills.AGILITY, 15);
			player.setNextAnimation(new Animation(-1));
			stop();
		}
		 
	 }, 2);
}
public static void jumpOverGap(final Player player, final WorldObject object) {
	if(!Agility.hasLevel(player, 90))
		return;
	player.lock(1);
	player.setNextAnimation(new Animation(2586));
	player.getAppearence().setRenderEmote(-1);
	 WorldTasksManager.schedule(new WorldTask() {
		@Override
		public void run() {
			player.setNextWorldTile(new WorldTile(4015, 5739, 2));
			player.setNextAnimation(new Animation(2588));
			player.getSkills().addXp(Skills.AGILITY, 15);
			stop();
		}
		 
	 }, 0);
}
public static void jumpFloor(final Player player, final WorldObject object) {
	if(!Agility.hasLevel(player, 90))
		return;
	player.lock(3);
	player.setNextAnimation(new Animation(11230));
	final WorldTile toTile = new WorldTile(4017, 5735, 2);
	player.setNextForceMovement(new ForceMovement(player, 0, toTile, 2, ForceMovement.SOUTH));
	 WorldTasksManager.schedule(new WorldTask() {
		@Override
		public void run() {
			player.setNextWorldTile(toTile);
			player.setNextAnimation(new Animation(11230));
			player.getSkills().addXp(Skills.AGILITY, 15);
			stop();
		}
		 
	 }, 1);
}
public static void jumpFloor2(final Player player, final WorldObject object) {
	if(!Agility.hasLevel(player, 90))
		return;
	player.lock(3);
	player.setNextAnimation(new Animation(11230));
	final WorldTile toTile = new WorldTile(4017, 5738, 2);
	player.setNextForceMovement(new ForceMovement(player, 0, toTile, 2, ForceMovement.NORTH));
	 WorldTasksManager.schedule(new WorldTask() {
		@Override
		public void run() {
			player.setNextWorldTile(toTile);
			player.setNextAnimation(new Animation(11230));
			player.getSkills().addXp(Skills.AGILITY, 15);
			stop();
		}
		 
	 }, 1);
}
public static void enterObstaclePipe(final Player player, WorldObject object) {
	if(!Agility.hasLevel(player, 35))
		return;
	 player.lock(4);
	 player.setNextAnimation(new Animation(10580));
	 final WorldTile toTile = new WorldTile(object.getX(), player.getY() >= 5736 ?  5739 : 5736, object.getPlane());
	 player.setNextForceMovement(new ForceMovement(player, 0, toTile, 2, player.getY() >= 5736 ? ForceMovement.SOUTH : ForceMovement.NORTH));
	 WorldTasksManager.schedule(new WorldTask() {

		@Override
		public void run() {
			player.setNextWorldTile(toTile);
			player.getSkills().addXp(Skills.AGILITY, 1/20);
		}	
		 
	 }, 1);
}
public static void RunGnomeBoard(final Player player, final WorldObject object) {
	player.setNextAnimation(new Animation(2922));
	final WorldTile toTile = new WorldTile(4009, 5749, object.getPlane());
	player.setNextForceMovement(new ForceMovement(player, 1, toTile, 3, ForceMovement.WEST));
	player.getSkills().addXp(Skills.AGILITY, 22);
	player.getPackets().sendGameMessage("You swegfully run across the running-board", true);
	 WorldTasksManager.schedule(new WorldTask() {

		@Override
		public void run() {
			player.setNextWorldTile(toTile);
			if (getKethsiStage(player) == 0)
			setKethsiStage(player, 1);
		}	
		 
	 }, 1);
}
public static void RunGnomeBoard2(final Player player, final WorldObject object) {
	player.setNextAnimation(new Animation(2922));
	final WorldTile toTile = new WorldTile(3996, 5750, object.getPlane());
	player.setNextForceMovement(new ForceMovement(player, 1, toTile, 3, ForceMovement.WEST));
	player.getSkills().addXp(Skills.AGILITY, 22);
	player.getPackets().sendGameMessage("You swegfully run across the running-board", true);
	 WorldTasksManager.schedule(new WorldTask() {

		@Override
		public void run() {
			player.setNextWorldTile(toTile);
			if (getKethsiStage(player) == 0)
			setKethsiStage(player, 1);
		}	
		 
	 }, 1);
}








public static void removeKethsiStage(Player player) {
	player.getTemporaryAttributtes().remove("KethsiCourse");
}

public static void setKethsiStage(Player player, int stage) {
	player.getTemporaryAttributtes().put("KethsiCourse", stage);
}

public static int getKethsiStage(Player player) {
	Integer stage = (Integer) player.getTemporaryAttributtes().get(
			"KethsiCourse");
	if (stage == null)
		return -1;
	return stage;
}
}
