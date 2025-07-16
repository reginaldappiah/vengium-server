//Author: Multak aka Superkickabout

package com.rs.game.player.content;
import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class PetRock {

	public static void HandleDirections(final Player player) {

	if (player.getDirection() == 0) {//SOUTH
		WorldTasksManager.schedule(new WorldTask() {
			int stage;
		@Override
		public void run() {
			if (stage == 0) {
				player.lock();
				player.setDirection(player.getDirection());
				player.setNextAnimation(new Animation(6665));
				player.setNextGraphics(new Graphics(1157));
			} else if (stage == 2) {
				World.sendProjectile(player, (new WorldTile(player.getX(), player.getY(), player.getPlane())), (new WorldTile(player.getX(), (player.getY()-2), player.getPlane())), 1158, 50, 10, 3, 35, 4, 0);
			} else if (stage == 4) {
				World.sendGraphics(player, new Graphics(1159),
						new WorldTile(player.getX(), player.getY() - 2,
								player.getPlane()));
			} else if (stage == 8) {
				stop();
				player.unlock();
				}
				stage++;
			}
			 
	 }, 0, 1);
	}
	if (player.getDirection() == 2048) {//SOUTHWEST
		WorldTasksManager.schedule(new WorldTask() {
			int stage;
		@Override
		public void run() {
			if (stage == 0) {
				player.lock();
				player.setDirection(player.getDirection());
				player.setNextAnimation(new Animation(6665));
				player.setNextGraphics(new Graphics(1157));
			} else if (stage == 2) {
				World.sendProjectile(player, (new WorldTile(player.getX(), player.getY(), player.getPlane())), (new WorldTile((player.getX()-1), (player.getY()-2), player.getPlane())), 1158, 50, 10, 3, 35, 4, 0);
			} else if (stage == 4) {
				World.sendGraphics(player, new Graphics(1159),
						new WorldTile((player.getX()-1), player.getY() - 2,
								player.getPlane()));
			} else if (stage == 8) {
				stop();
				player.unlock();
				}
				stage++;
			}
			 
	 }, 0, 1);
	}
	if (player.getDirection() == 14336) {//SOUTHEAST
		WorldTasksManager.schedule(new WorldTask() {
			int stage;
		@Override
		public void run() {
			if (stage == 0) {
				player.lock();
				player.setDirection(player.getDirection());
				player.setNextAnimation(new Animation(6665));
				player.setNextGraphics(new Graphics(1157));
			} else if (stage == 2) {
				World.sendProjectile(player, (new WorldTile(player.getX(), player.getY(), player.getPlane())), (new WorldTile((player.getX()+1), (player.getY()-2), player.getPlane())), 1158, 50, 10, 3, 35, 4, 0);
			} else if (stage == 4) {
				World.sendGraphics(player, new Graphics(1159),
						new WorldTile((player.getX()+1), player.getY() - 2,
								player.getPlane()));
			} else if (stage == 8) {
				stop();
				player.unlock();
				}
				stage++;
			}
			 
	 }, 0, 1);
	}
	if (player.getDirection() == 8192) {//NORTH
		WorldTasksManager.schedule(new WorldTask() {
			int stage;
		@Override
		public void run() {
			if (stage == 0) {
				player.lock();
				player.setDirection(player.getDirection());
				player.setNextAnimation(new Animation(6665));
				player.setNextGraphics(new Graphics(1157));
			} else if (stage == 2) {
				World.sendProjectile(player, (new WorldTile(player.getX(), player.getY(), player.getPlane())), (new WorldTile(player.getX(), (player.getY()+2), player.getPlane())), 1158, 50, 10, 3, 35, 4, 0);
			} else if (stage == 4) {
				World.sendGraphics(player, new Graphics(1159),
						new WorldTile(player.getX(), player.getY() + 2,
								player.getPlane()));
			} else if (stage == 8) {
				stop();
				player.unlock();
				}
				stage++;
			}
			 
	 }, 0, 1);
	}
	if (player.getDirection() == 6144) {//NORTHWEST
		WorldTasksManager.schedule(new WorldTask() {
			int stage;
		@Override
		public void run() {
			if (stage == 0) {
				player.lock();
				player.setDirection(player.getDirection());
				player.setNextAnimation(new Animation(6665));
				player.setNextGraphics(new Graphics(1157));
			} else if (stage == 2) {
				World.sendProjectile(player, (new WorldTile(player.getX(), player.getY(), player.getPlane())), (new WorldTile((player.getX()-1), (player.getY()+2), player.getPlane())), 1158, 50, 10, 3, 35, 4, 0);
			} else if (stage == 4) {
				World.sendGraphics(player, new Graphics(1159),
						new WorldTile((player.getX()-1), player.getY() + 2,
								player.getPlane()));
			} else if (stage == 8) {
				stop();
				player.unlock();
				}
				stage++;
			}
			 
	 }, 0, 1);
	}
	if (player.getDirection() == 10240) {//NORTHEAST
		WorldTasksManager.schedule(new WorldTask() {
			int stage;
		@Override
		public void run() {
			if (stage == 0) {
				player.lock();
				player.setDirection(player.getDirection());
				player.setNextAnimation(new Animation(6665));
				player.setNextGraphics(new Graphics(1157));
			} else if (stage == 2) {
				World.sendProjectile(player, (new WorldTile(player.getX(), player.getY(), player.getPlane())), (new WorldTile((player.getX()+1), (player.getY()+2), player.getPlane())), 1158, 50, 10, 3, 35, 4, 0);
			} else if (stage == 4) {
				World.sendGraphics(player, new Graphics(1159),
						new WorldTile((player.getX()+1), player.getY() + 2,
								player.getPlane()));
			} else if (stage == 8) {
				stop();
				player.unlock();
				}
				stage++;
			}
			 
	 }, 0, 1);
	}
	if (player.getDirection() == 4096) {//WEST
		WorldTasksManager.schedule(new WorldTask() {
			int stage;
		@Override
		public void run() {
			if (stage == 0) {
				player.lock();
				player.setDirection(player.getDirection());
				player.setNextAnimation(new Animation(6665));
				player.setNextGraphics(new Graphics(1157));
			} else if (stage == 2) {
				World.sendProjectile(player, (new WorldTile(player.getX(), player.getY(), player.getPlane())), (new WorldTile((player.getX()-2), player.getY(), player.getPlane())), 1158, 50, 10, 3, 35, 4, 0);
			} else if (stage == 4) {
				World.sendGraphics(player, new Graphics(1159),
						new WorldTile(player.getX() - 2, player.getY(),
								player.getPlane()));
			} else if (stage == 8) {
				stop();
				player.unlock();
				}
				stage++;
			}
			 
	 }, 0, 1);
	}
	if (player.getDirection() == 12288) {//EAST
		WorldTasksManager.schedule(new WorldTask() {
			int stage;
		@Override
		public void run() {
			if (stage == 0) {
				player.lock();
				player.setDirection(player.getDirection());
				player.setNextAnimation(new Animation(6665));
				player.setNextGraphics(new Graphics(1157));
			} else if (stage == 2) {
				World.sendProjectile(player, (new WorldTile(player.getX(), player.getY(), player.getPlane())), (new WorldTile((player.getX()+2), player.getY(), player.getPlane())), 1158, 50, 1, 1, 35, 10, 0);
			} else if (stage == 4) {
				World.sendGraphics(player, new Graphics(1159),
						new WorldTile(player.getX() + 2, player.getY(),
								player.getPlane()));
			} else if (stage == 8) {
				stop();
				player.unlock();
				}
				stage++;
			}
			 
	 }, 0, 1);
	}
}
}