package com.rs.game.player.content.dungeons.impl;

import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.content.dungeons.DungeonsHandler;

/**
 * @author Fuzen Seth
 */
public class PolyporeDungeon extends DungeonsHandler {
	
	@Override
	public String getDungeonName() {
		return "You enter succesfully to the Polypore dungeon."; 
	}

	@Override
	public boolean handleObjects(Player player, WorldObject object) {
		switch (object.getId()) { //we switch object id to cases here
		case 123123: //Dungeon entrance, this is the mining guild entrace
			player.sendMessage(getDungeonName()); //<- Auto-send message l0l
			return true; // < Closes the case.
		case 1231232: //Leaves a dungeon for an example
		player.setNextWorldTile(new WorldTile(1,1,1)); //<- Teleport
			return true;
		case 12312: //<-- maybe some random object in dungeon that you want a teleport action
			/**
			 * Action
			 */
			return true;
		}
		return false;
	}

}
