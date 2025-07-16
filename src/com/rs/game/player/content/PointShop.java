package com.rs.game.player.content;

import com.rs.game.item.Item;
import com.rs.game.player.Player;

public class PointShop {

	public static void handle(int componentId, int interfaceId, Player player) {
		int id = interfaceId;
		int cid = componentId;
		if(id == 60) {
			switch (cid) {
			case 93:
				if(player.skillpoints >= 500) {
				player.getInventory().addItem(new Item(4151));
				player.getPackets().sendIComponentText(60, 96, "Abbysal whip");
				player.skillpoints -= 500;
				//Maaruh hoe maak je dat er pnts afgaan doe maar 500
				} else
					player.getPackets().sendGameMessage("not enough points u need atleast : 500 points");
				break;
			case 114:
				player.skillpoints += 1000; // dit delete je gwn later dit is puur voor test dat je niet 50 bomen hoeft te hakken
			break;
		
			case 60:
				player.getPackets().sendIComponentText(60, 96, "Abbysal whip");
				player.getPackets().sendIComponentText(60, 13, "Weapons");
				player.getPackets().sendIComponentText(60, 16, "Armour");
				player.getPackets().sendIComponentText(60, 19, "Skilling armour");
				break; 
				
			
			default:
				break;
			}
		}
		
	}

}
