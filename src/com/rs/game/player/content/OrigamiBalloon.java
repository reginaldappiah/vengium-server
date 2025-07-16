package com.rs.game.player.content;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

/**
 * 
 * @author Multak190
 * Skype: Muletech
 */
public class OrigamiBalloon {

	static int SOUTHWEST = 2048;
	static int SOUTHEAST = 14366;
	static int SOUTH = 0;
	static int NORTHWEST = 6144;
	static int NORTHEAST = 10240;
	static int NORTH = 8192;
	static int WEST = 4096;
	static int EAST = 12288;
	
	public static void HandleNormalDirections(final Player player) {

if (player.getDirection() == SOUTH) {
		WorldTasksManager.schedule(new WorldTask() {
			int stage;
		@Override
		public void run() {
			if (stage == 0) {
				player.lock();
				player.setDirection(player.getDirection());
				player.setNextAnimation(new Animation(5142));
				player.setNextGraphics(new Graphics(880));
			} else if (stage == 1) {
				World.sendProjectile(player, (new WorldTile(player.getX(), player.getY(), player.getPlane())), (new WorldTile((player.getX()), (player.getY()-3), player.getPlane())), 881, 46, 46, 1, 24, -1, 0);
				World.sendProjectile(player, (new WorldTile(player.getX(), player.getY()-3, player.getPlane())), (new WorldTile((player.getX()), (player.getY()-12), player.getPlane())), 882, 0, 0, 1, 115, 0, 0);
			} else if (stage == 7) {
				stop();
				player.unlock();
				}
				stage++;
			}
			 
	 }, 0, 1);
}
if (player.getDirection() == NORTH) {
		WorldTasksManager.schedule(new WorldTask() {
			int stage;
		@Override
		public void run() {
			if (stage == 0) {
				player.lock();
				player.setDirection(player.getDirection());
				player.setNextAnimation(new Animation(5142));
				player.setNextGraphics(new Graphics(880));
			} else if (stage == 1) {
				World.sendProjectile(player, (new WorldTile(player.getX(), player.getY(), player.getPlane())), (new WorldTile((player.getX()), (player.getY()+3), player.getPlane())), 881, 46, 46, 1, 24, -1, 0);
				World.sendProjectile(player, (new WorldTile(player.getX(), player.getY()+3, player.getPlane())), (new WorldTile((player.getX()), (player.getY()+12), player.getPlane())), 882, 0, 0, 1, 115, 0, 0);
			} else if (stage == 7) {
				stop();
				player.unlock();
				}
				stage++;
			}
			 
	 }, 0, 1);
}

if (player.getDirection() == WEST) {
	WorldTasksManager.schedule(new WorldTask() {
		int stage;
	@Override
	public void run() {
		if (stage == 0) {
			player.lock();
			player.setDirection(player.getDirection());
			player.setNextAnimation(new Animation(5142));
			player.setNextGraphics(new Graphics(880));
		} else if (stage == 1) {
			World.sendProjectile(player, (new WorldTile(player.getX(), player.getY(), player.getPlane())), (new WorldTile((player.getX()-3), (player.getY()), player.getPlane())), 881, 46, 46, 1, 24, -1, 0);
			World.sendProjectile(player, (new WorldTile(player.getX()-3, player.getY(), player.getPlane())), (new WorldTile((player.getX()-12), (player.getY()), player.getPlane())), 882, 0, 0, 1, 115, 0, 0);
		} else if (stage == 7) {
			stop();
			player.unlock();
			}
			stage++;
		}
		 
 }, 0, 1);
}
if (player.getDirection() == EAST) {
	WorldTasksManager.schedule(new WorldTask() {
		int stage;
	@Override
	public void run() {
		if (stage == 0) {
			player.lock();
			player.setDirection(player.getDirection());
			player.setNextAnimation(new Animation(5142));
			player.setNextGraphics(new Graphics(880));
		} else if (stage == 1) {
			World.sendProjectile(player, (new WorldTile(player.getX(), player.getY(), player.getPlane())), (new WorldTile((player.getX()+3), (player.getY()), player.getPlane())), 881, 46, 46, 1, 24, -1, 0);
			World.sendProjectile(player, (new WorldTile(player.getX()+3, player.getY(), player.getPlane())), (new WorldTile((player.getX())+12, (player.getY()), player.getPlane())), 882, 0, 0, 1, 115, 0, 0);
		} else if (stage == 7) {
			stop();
			player.unlock();
			}
			stage++;
		}
		 
 }, 0, 1);
}
if (player.getDirection() == SOUTHWEST) {
	WorldTasksManager.schedule(new WorldTask() {
		int stage;
	@Override
	public void run() {
		if (stage == 0) {
			player.lock();
			player.setDirection(player.getDirection());
			player.setNextAnimation(new Animation(5142));
			player.setNextGraphics(new Graphics(880));
		} else if (stage == 1) {
			World.sendProjectile(player, (new WorldTile(player.getX(), player.getY(), player.getPlane())), (new WorldTile((player.getX()-3), (player.getY()-3), player.getPlane())), 881, 46, 46, 1, 24, -1, 0);
			World.sendProjectile(player, (new WorldTile(player.getX()-3, player.getY()-3, player.getPlane())), (new WorldTile((player.getX()-12), (player.getY()-12), player.getPlane())), 882, 0, 0, 1, 115, 0, 0);
		} else if (stage == 7) {
			stop();
			player.unlock();
			}
			stage++;
		}
		 
 }, 0, 1);
}
if (player.getDirection() == NORTHWEST) {
	WorldTasksManager.schedule(new WorldTask() {
		int stage;
	@Override
	public void run() {
		if (stage == 0) {
			player.lock();
			player.setDirection(player.getDirection());
			player.setNextAnimation(new Animation(5142));
			player.setNextGraphics(new Graphics(880));
		} else if (stage == 1) {
			World.sendProjectile(player, (new WorldTile(player.getX(), player.getY(), player.getPlane())), (new WorldTile((player.getX()-3), (player.getY()+3), player.getPlane())), 881, 46, 46, 1, 24, -1, 0);
			World.sendProjectile(player, (new WorldTile(player.getX()-3, player.getY()+3, player.getPlane())), (new WorldTile((player.getX()-12), (player.getY()+12), player.getPlane())), 882, 0, 0, 1, 115, 0, 0);
		} else if (stage == 7) {
			stop();
			player.unlock();
			}
			stage++;
		}
		 
 }, 0, 1);
}
if (player.getDirection() == SOUTHEAST) {
	WorldTasksManager.schedule(new WorldTask() {
		int stage;
	@Override
	public void run() {
		if (stage == 0) {
			player.lock();
			player.setDirection(player.getDirection());
			player.setNextAnimation(new Animation(5142));
			player.setNextGraphics(new Graphics(880));
		} else if (stage == 1) {
			World.sendProjectile(player, (new WorldTile(player.getX(), player.getY(), player.getPlane())), (new WorldTile((player.getX()+3), (player.getY()-3), player.getPlane())), 881, 46, 46, 1, 24, -1, 0);
			World.sendProjectile(player, (new WorldTile(player.getX()+3, player.getY()-3, player.getPlane())), (new WorldTile((player.getX()+12), (player.getY()-12), player.getPlane())), 882, 0, 0, 1, 115, 0, 0);
		} else if (stage == 7) {
			stop();
			player.unlock();
			}
			stage++;
		}
		 
 }, 0, 1);
}
if (player.getDirection() == NORTHEAST) {
	WorldTasksManager.schedule(new WorldTask() {
		int stage;
	@Override
	public void run() {
		if (stage == 0) {
			player.lock();
			player.setDirection(player.getDirection());
			player.setNextAnimation(new Animation(5142));
			player.setNextGraphics(new Graphics(880));
		} else if (stage == 1) {
			World.sendProjectile(player, (new WorldTile(player.getX(), player.getY(), player.getPlane())), (new WorldTile((player.getX()+3), (player.getY()+3), player.getPlane())), 881, 46, 46, 1, 24, -1, 0);
			World.sendProjectile(player, (new WorldTile(player.getX()+3, player.getY()-3, player.getPlane()+3)), (new WorldTile((player.getX()+12), (player.getY()+12), player.getPlane())), 882, 0, 0, 1, 115, 0, 0);
		} else if (stage == 7) {
			stop();
			player.unlock();
			}
			stage++;
		}
		 
 }, 0, 1);
}
}
}