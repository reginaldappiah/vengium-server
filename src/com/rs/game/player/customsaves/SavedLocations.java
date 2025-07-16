package com.rs.game.player.customsaves;

import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.minigames.CastleWars;
import com.rs.game.player.Player;
import com.rs.game.player.content.Magic;

public class SavedLocations {

	Player player;
	
	public SavedLocations(Player player) {
		this.player = player;
	}
	/*
	 * 	Location 4
	 */
	public void removeLocation4(Player player) {
	if (player.removedLocation4 == true)
		player.sm("<img=15><col=ff0000>You have already deleted your 4th location.");
	else if (player.removedLocation4 == false)
		player.setNextGraphics(new Graphics(1556));
		player.sm("<img=15><col=ff0000>You have deleted your 4th location.");
		player.savedLocation4 = false;
		player.removedLocation4 = true;
		player.totallocations--;
		player.SAVEDX4 = 2598;
		player.SAVEDY4 = 3409;
		player.SAVEDZ4 = 0;		
	}
	public void saveLocation4(Player player) {
		if (player.savedLocation4 == true)
		player.sm("<img=15><col=ff0000>You have already saved your 4th location! If you want to override your actually saved location, delete it, then save again.");
		else if ((player.savedLocation4 == false & (player.removedLocation4 == true)))
		player.savedLocation4 = true;
		player.removedLocation4 = false;
		player.totallocations++;
		player.setNextGraphics(new Graphics(1592));
		player.sm("<col=00FF09>You have saved your 4th location.");
		player.SAVEDX4 = player.getX();
		player.SAVEDY4 = player.getY();
		player.SAVEDZ4 = player.getPlane();		
	}
	public void teletoLocation4(Player player) {
		if ((player.savedLocation4) == false & (player.removedLocation4 == true))
		player.sm("<img=15><col=ff0000>Your 4th location was recently removed and unsaved! Please save your location before teleporting to it.");
		if ((player.removedLocation4 == true))
		player.sm("<img=15><col=ff0000>Your 4th location was recently removed! Please save your location before teleporting to it.");
		if (((player.savedLocation4) == false & (player.removedLocation4 == false)))
		player.sm("<img=15><col=ff0000>You must save before teleporting to your 4th location!");
		if (((player.savedLocation4) == false))
		player.sm("<img=15><col=ff0000>You must save before teleporting to your 4th location!");
		else if (player.savedLocation4 == true || (player.removedLocation4 == false))
		player.sm("<col=00FF09>You have teleported to your recently saved 4th location.");
		Magic.sendDungeonRootTeleport(player, 0, 0.0D, new WorldTile((player.SAVEDX4), (player.SAVEDY4), (player.SAVEDZ4)), new int[0]);
	}
	/*
	 * 	Location 3
	 */
	public void removeLocation3(Player player) {
		if (player.removedLocation3 == true)
			player.sm("<img=15><col=ff0000>You have already deleted your 3rd location.");
		else if (player.removedLocation3 == false)
			player.setNextGraphics(new Graphics(1556));
			player.sm("<img=15><col=ff0000>You have deleted your 3rd location.");
			player.savedLocation3 = false;
			player.removedLocation3 = true;
			player.totallocations--;
			player.SAVEDX3 = 2598;
			player.SAVEDY3 = 3409;
			player.SAVEDZ3 = 0;		
		}
		public void saveLocation3(Player player) {
			if (player.savedLocation3 == true)
			player.sm("<img=15><col=ff0000>You have already saved your 3rd location! If you want to override your actually saved location, delete it, then save again.");
			else if ((player.savedLocation3 == false & (player.removedLocation3 == true)))
			player.savedLocation3 = true;
			player.removedLocation3 = false;
			player.totallocations++;
			player.setNextGraphics(new Graphics(1592));
			player.sm("<col=00FF09>You have saved your 3rd location.");
			player.SAVEDX3 = player.getX();
			player.SAVEDY3 = player.getY();
			player.SAVEDZ3 = player.getPlane();		
		}
		public void teletoLocation3(Player player) {
			if ((player.savedLocation3) == false & (player.removedLocation3 == true))
			player.sm("<img=15><col=ff0000>Your 3rd location was recently removed and unsaved! Please save your location before teleporting to it.");
			if ((player.removedLocation3 == true))
			player.sm("<img=15><col=ff0000>Your 3rd location was recently removed! Please save your location before teleporting to it.");
			if (((player.savedLocation3) == false & (player.removedLocation3 == false)))
			player.sm("<img=15><col=ff0000>You must save before teleporting to your 3rd location!");
			if (((player.savedLocation3) == false))
			player.sm("<img=15><col=ff0000>You must save before teleporting to your 3rd location!");
			else if (player.savedLocation3 == true || (player.removedLocation3 == false))
			player.sm("<col=00FF09>You have teleported to your recently saved 3rd location.");
			Magic.sendDungeonRootTeleport(player, 0, 0.0D, new WorldTile((player.SAVEDX3), (player.SAVEDY3), (player.SAVEDZ3)), new int[0]);
		}
	
	/*
	 *  Location 2
	 */
		public void removeLocation2(Player player) {
			if (player.removedLocation2 == true)
				player.sm("<img=15><col=ff0000>You have already deleted your 2nd location.");
			else if (player.removedLocation2 == false)
				player.setNextGraphics(new Graphics(1556));
				player.sm("<img=15><col=ff0000>You have deleted your 2nd location.");
				player.savedLocation2 = false;
				player.removedLocation2 = true;
				player.totallocations--;
				player.SAVEDX2 = 2598;
				player.SAVEDY2 = 3409;
				player.SAVEDZ2 = 0;		
			}
			public void saveLocation2(Player player) {
				if (player.savedLocation2 == true)
				player.sm("<img=15><col=ff0000>You have already saved your 2nd location! If you want to override your actually saved location, delete it, then save again.");
				else if ((player.savedLocation2 == false & (player.removedLocation2 == true)))
				player.savedLocation2 = true;
				player.removedLocation2 = false;
				player.totallocations++;
				player.setNextGraphics(new Graphics(1592));
				player.sm("<col=00FF09>You have saved your 2nd location.");
				player.SAVEDX2 = player.getX();
				player.SAVEDY2 = player.getY();
				player.SAVEDZ2 = player.getPlane();		
			}
			public void teletoLocation2(Player player) {
				if ((player.savedLocation2) == false & (player.removedLocation2 == true))
				player.sm("<img=15><col=ff0000>Your 2nd location was recently removed and unsaved! Please save your location before teleporting to it.");
				if ((player.removedLocation2 == true))
				player.sm("<img=15><col=ff0000>Your 2nd location was recently removed! Please save your location before teleporting to it.");
				if (((player.savedLocation2) == false & (player.removedLocation2 == false)))
				player.sm("<img=15><col=ff0000>You must save before teleporting to your 2nd location!");
				if (((player.savedLocation2) == false))
				player.sm("<img=15><col=ff0000>You must save before teleporting to your 2nd location!");
				else if (player.savedLocation2 == true || (player.removedLocation2 == false))
				player.sm("<col=00FF09>You have teleported to your recently saved 2nd location.");
				Magic.sendDungeonRootTeleport(player, 0, 0.0D, new WorldTile((player.SAVEDX2), (player.SAVEDY2), (player.SAVEDZ2)), new int[0]);
			}
	
	/*
	 * Location 1
	 */
	
			public void removeLocation1(Player player) {
				if (player.removedLocation1 == true)
					player.sm("<img=15><col=ff0000>You have already deleted your 1st location.");
				else if (player.removedLocation1 == false)
					player.setNextGraphics(new Graphics(1556));
					player.sm("<img=15><col=ff0000>You have deleted your 1st location.");
					player.savedLocation1 = false;
					player.removedLocation1 = true;
					player.totallocations--;
					player.SAVEDX1 = 2598;
					player.SAVEDY1 = 3409;
					player.SAVEDZ1 = 0;		
				}
				public void saveLocation1(Player player) {
					if (player.savedLocation1 == true)
					player.sm("<img=15><col=ff0000>You have already saved your 1st location! If you want to override your actually saved location, delete it, then save again.");
					else if ((player.savedLocation1 == false & (player.removedLocation1 == true)))
					player.savedLocation1 = true;
					player.removedLocation1 = false;
					player.totallocations++;
					player.setNextGraphics(new Graphics(1592));
					player.sm("<col=00FF09>You have saved your 1st location.");
					player.SAVEDX1 = player.getX();
					player.SAVEDY1 = player.getY();
					player.SAVEDZ1 = player.getPlane();		
				}
				public void teletoLocation1(Player player) {
					if ((player.savedLocation1) == false & (player.removedLocation1 == true))
					player.sm("<img=15><col=ff0000>Your 1st location was recently removed and unsaved! Please save your location before teleporting to it.");
					if ((player.removedLocation1 == true))
					player.sm("<img=15><col=ff0000>Your 1st location was recently removed! Please save your location before teleporting to it.");
					if (((player.savedLocation1) == false & (player.removedLocation1 == false)))
					player.sm("<img=15><col=ff0000>You must save before teleporting to your 1st location!");
					if (((player.savedLocation1) == false))
					player.sm("<img=15><col=ff0000>You must save before teleporting to your 1st location!");
					else if (player.savedLocation1 == true || (player.removedLocation1 == false))
					player.sm("<col=00FF09>You have teleported to your recently saved 1st location.");
					Magic.sendDungeonRootTeleport(player, 0, 0.0D, new WorldTile((player.SAVEDX1), (player.SAVEDY1), (player.SAVEDZ1)), new int[0]);
				}
}
