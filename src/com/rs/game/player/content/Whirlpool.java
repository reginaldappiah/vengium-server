package com.rs.game.player.content;

import com.rs.game.Animation;
import com.rs.game.Graphics;
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
public class Whirlpool {

	Player player;
	
	public Whirlpool(Player player) {
		this.player = player;
	}

	/**
	 * Teleport Landing Coords
	 */
	public final int LANDING_X = 1764;
	public final int LANDING_Y = 5365;

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
					BarbarianOutpostAgility.WhirlpoolWalk(player, object);
				} else if (loop == 3) {
					BarbarianOutpostAgility.Whirlpool(player, object);
				} else if (loop == 6) {
					FadingScreen.fade(player, new Runnable() {
							@Override
							public void run() {
								player.getPackets().sendBlackOut(0);
								player.getPackets().sendConfig(1241, 0);
							}

						});
				} else if (loop == 8) {
					
					player.setNextWorldTile(new WorldTile (LANDING_X, LANDING_Y, 1));
				player.closeInterfaces();
				stop();
				
				} else if (loop == 8) {
					player.getDialogueManager().startDialogue("Whirpool");
				}
				
				loop++;
				}
			
			}, 0, 1);
		}
	
}