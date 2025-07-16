package com.rs.game.player.content;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.player.content.FadingScreen;
import com.rs.game.player.content.agility.BarbarianOutpostAgility;
import com.rs.game.WorldObject;
import com.rs.game.tasks.WorldTasksManager;

/**
 * 
 * @author JazzyYaYaYa | Nexon | Fuzen Seth
1648 - cranking wheel ectophuntus
1649 - putting bones into machine
1651 - ectophuntus pray emote
1652 - shake vial
 */
public class BrimhavenMatrix {

	Player player;
	
	public BrimhavenMatrix(Player player) {
		this.player = player;
	}

	/**
	 * Teleport Landing Coords
	 */
	//public final int LANDING_X = 1764;
	//public final int LANDING_Y = 5365;

	/**
	 *  This is for Worship, this will refill ectohpial
	 * @param player
	 */
	
	public void ProcessTeleportation(final Player player) {
			WorldTasksManager.schedule(new WorldTask() {
			int loop;
			
			@Override
			public void run() {
				player.getInterfaceManager().closeChatBoxInterface();			
				WorldObject object = null;
				if (loop == 0) {
					BarbarianOutpostAgility.MatrixWalk(player, object);
				} else if (loop == 3) {
					World.sendProjectile(player, (new WorldTile((player.getX()+6), player.getY(), player.getPlane())), (new WorldTile((player.getX()-10), player.getY(), player.getPlane())), 270, 12, 12, 20, 15, 0, 0);
				} else if (loop == 4) {
					BarbarianOutpostAgility.Matrix(player, object);
				} else if (loop == 6) {
					player.unlock();
				}
				
				loop++;
				}
			
			}, 0, 1);
		}
	
}