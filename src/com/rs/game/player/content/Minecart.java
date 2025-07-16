package com.rs.game.player.content;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.ForceMovement;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.player.content.FadingScreen;
import com.rs.game.player.content.agility.BarbarianOutpostAgility;
import com.rs.game.WorldObject;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.game.minigames.CastleWars;

/**
 * 
 * @author JazzyYaYaYa | Nexon | Fuzen Seth
1648 - cranking wheel ectophuntus
1649 - putting bones into machine
1651 - ectophuntus pray emote
1652 - shake vial
 */
public class Minecart {

	Player player;
	
	public Minecart(Player player) {
		this.player = player;
	}

	/**
	 * Teleport Landing Coords
	 */
	public final int LANDING_X = 2938;
	public final int LANDING_Y = 10173;

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
					BarbarianOutpostAgility.Minecart(player, object);
					player.setNextWorldTile(new WorldTile(2909, 10171, 0));
				} else if (loop == 6) {
					player.getInterfaceManager().NoRemoteCart();
					player.setNextWorldTile(new WorldTile(2909, 10170, 0));
				player.closeInterfaces();
				stop();
				}
				loop++;
				}
			}, 0, 1);
		}
	
}