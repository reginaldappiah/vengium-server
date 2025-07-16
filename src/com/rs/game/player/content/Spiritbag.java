package com.rs.game.player.content;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

/**
 * 
 * @author JazzyYaYaYa | Nexon | Fuzen Seth
1648 - cranking wheel ectophuntus
1649 - putting bones into machine
1651 - ectophuntus pray emote
1652 - shake vial
 */
public class Spiritbag {

	Player player;
	
	public Spiritbag(Player player) {
		this.player = player;
	}

	private int FULL_ECTOPHIAL = 19967, EMPTY_ECTOPHIAL = 19967;
	/**
	 * Teleport Landing Coords
	 */
	public final int LANDING_X = 2953;
	public final int LANDING_Y = 2932;

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
				if (loop == 0) {
                player.setNextAnimation(new Animation(9897));
				} else if (loop == 1) {
		        player.setNextGraphics(new Graphics(267));
				} else if (loop == 2) {
                player.setNextAnimation(new Animation(7082));
                player.setNextGraphics(new Graphics(1228));
				} else if (loop == 4) {
				player.setNextWorldTile(new WorldTile (LANDING_X, LANDING_Y, 0));
				player.setNextAnimation(new Animation(7084));
				player.setNextGraphics(new Graphics(1229));
				player.closeInterfaces();
				stop();
				}
				loop++;
				}
			}, 0, 1);
		}
	
}