package com.rs.game.player.content.dungeons;

import com.rs.game.WorldObject;
import com.rs.game.player.Player;
/**
 * @author Fuzen Seth
 */
public abstract class DungeonsHandler {
	/**
	 * Current dungeon's name
	 * @return
	 */
	public abstract String getDungeonName();
	/**
	 * Handles entrances, exits
	 * @param player
	 * @param object
	 * @return
	 */
	public abstract boolean handleObjects(Player player, WorldObject object);
	

	
}
