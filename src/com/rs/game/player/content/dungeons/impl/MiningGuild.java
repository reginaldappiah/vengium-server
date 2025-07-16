package com.rs.game.player.content.dungeons.impl;

import com.rs.game.WorldObject;
import com.rs.game.player.Player;
import com.rs.game.player.content.dungeons.DungeonsHandler;

/**
 * 
 * @author Fuzen Seth
 * 
 */
public class MiningGuild extends DungeonsHandler {
	
	@Override
	public String getDungeonName() {
		return "You enter succesfully to the Mining Guild."; 
	}

	@Override
	public boolean handleObjects(Player player, WorldObject object) {
		switch (object.getId()) { //we switch object id to cases here
		case 123123: //Dungeon entrance, this is the mining guild entrace
			player.sendMessage(getDungeonName()); //<- Auto-send message l0l
			return true; // < Closes the case.
		case 1231232:
			/**
			 * Exit action
			 */
			return true;
		}
		return false;
	}

}
