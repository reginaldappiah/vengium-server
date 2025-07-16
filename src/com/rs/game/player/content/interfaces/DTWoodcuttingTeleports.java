package com.rs.game.player.content.interfaces;

import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.minigames.CastleWars;
import com.rs.game.player.Player;
import com.rs.game.player.content.Magic;

public class DTWoodcuttingTeleports {
	public static void sendOptions(Player player) {
		player.viewingagilityteles = false;
		player.viewingminigameteles = false;
		player.viewingmonsterteles = false;
		player.viewingwoodcuttingteles = true;
		player.viewingskillingteles = false;
		player.viewingtrainingteles = false;
		player.getInterfaceManager().sendInterface(1156);
		player.getPackets().sendIComponentText(1156, 190,
				"Woodcutting Skill Locations");
		player.getPackets().sendIComponentText(1156, 108, "Lumbridge Trees");
		player.getPackets().sendIComponentText(1156, 109,
				"Perfect for starters for normal trees are plenty.");
		player.getPackets().sendIComponentText(1156, 90, "Teleport!");
		
		player.getPackets().sendIComponentText(1156, 113, "Draynor Village Trees");
		player.getPackets().sendIComponentText(1156, 114,
				"Willow trees nearby for low leveled woodcutters.");
		player.getPackets().sendIComponentText(1156, 206, "Teleport!");
		
		player.getPackets().sendIComponentText(1156, 137, "Karamja Trees");
		player.getPackets().sendIComponentText(1156, 138,
				"Mahogany and teak trees for medium levels.");
		player.getPackets().sendIComponentText(1156, 254, "Teleport!");
		
		player.getPackets().sendIComponentText(1156, 110, "Camelot Trees");
		player.getPackets().sendIComponentText(1156, 111,
				"Known for a diversity of trees, any level can cut here.");
		player.getPackets().sendIComponentText(1156, 200, "Teleport!");
		
		player.getPackets().sendIComponentText(1156, 116, "Underground Magic Trees");
		player.getPackets().sendIComponentText(1156, 117,
				"Cut down the highest level trees here!");
		player.getPackets().sendIComponentText(1156, 212, "Teleport!");
		
		player.getPackets().sendIComponentText(1156, 134,"Sorcerer's Tower");
		player.getPackets().sendIComponentText(1156, 135,
				"More magic trees incase one is overpopulated.");
		player.getPackets().sendIComponentText(1156, 248, "Teleport!");
		
		player.getPackets().sendIComponentText(1156, 119, "");
		player.getPackets().sendIComponentText(1156, 120,
				"");
		player.getPackets().sendIComponentText(1156, 218, "");
		
		player.getPackets().sendIComponentText(1156, 131, "");
		player.getPackets().sendIComponentText(1156, 132,
				"");
		player.getPackets().sendIComponentText(1156, 242, "");
		
		player.getPackets().sendIComponentText(1156, 140, "");
		player.getPackets().sendIComponentText(1156, 141,
				"");
		player.getPackets().sendIComponentText(1156, 260, "");
		
		player.getPackets().sendIComponentText(1156, 149, "");
		player.getPackets().sendIComponentText(1156, 150,
				"");
		player.getPackets().sendIComponentText(1156, 278, "");
		
		player.getPackets().sendIComponentText(1156, 152, "");
		player.getPackets().sendIComponentText(1156, 153,
				"");
		player.getPackets().sendIComponentText(1156, 284, "");
		
		player.getPackets().sendIComponentText(1156, 122, "Neitiznot Arctice Pine Trees");
		player.getPackets().sendIComponentText(1156, 123,
				"The only arctic pine trees in the game!");
		player.getPackets().sendIComponentText(1156, 230, "Teleport!");
		
		player.getPackets().sendIComponentText(1156, 128, "");
		player.getPackets().sendIComponentText(1156, 129,
				"");
		player.getPackets().sendIComponentText(1156, 236, "");
		
		player.getPackets().sendIComponentText(1156, 125, "");
		player.getPackets().sendIComponentText(1156, 126,
				"");
		player.getPackets().sendIComponentText(1156, 224, "");
		
		player.getPackets().sendIComponentText(1156, 143, "");
		player.getPackets().sendIComponentText(1156, 144,
				"");
		player.getPackets().sendIComponentText(1156, 266, "");
		
		player.getPackets().sendIComponentText(1156, 146, "");
		player.getPackets().sendIComponentText(1156, 147,
				"");
		player.getPackets().sendIComponentText(1156, 272, "");
		
		player.getPackets().sendIComponentText(1156, 167, "");
		player.getPackets().sendIComponentText(1156, 168,
				"");
		player.getPackets().sendIComponentText(1156, 308, "");
		
		player.getPackets().sendIComponentText(1156, 155, "");
		player.getPackets().sendIComponentText(1156, 157,
				"");
		player.getPackets().sendHideIComponent(1156, 156, true);
		player.getPackets().sendIComponentText(1156, 290, "");
		
		player.getPackets().sendIComponentText(1156, 159, "");
		player.getPackets().sendHideIComponent(1156, 160, true);
		player.getPackets().sendIComponentText(1156, 161,
				"");
		player.getPackets().sendIComponentText(1156, 296, "");
		
		player.getPackets().sendIComponentText(1156, 163, "");
		player.getPackets().sendHideIComponent(1156, 164, true);
		player.getPackets().sendIComponentText(1156, 165,
				"");
		player.getPackets().sendIComponentText(1156, 302, "");
		
		player.getPackets().sendIComponentText(1156, 170, "");
		player.getPackets().sendIComponentText(1156, 171,
				"");
		player.getPackets().sendIComponentText(1156, 314, "");
		
		player.getPackets().sendIComponentText(1156, 318, "");
		player.getPackets().sendIComponentText(1156, 319,
				"");
		player.getPackets().sendIComponentText(1156, 326, "");
	}
	public static void handleButtons(Player player, int componentId) {
			if (componentId == 88) {//Lumbridge Trees
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3175, 3238, 0));
				player.viewingwoodcuttingteles = false;
			
			} else if (componentId == 115) {//Draynor Village Trees
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3091, 3234, 0));
				player.viewingwoodcuttingteles = false;
			
			} else if (componentId == 139) {//Karamja Trees
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2817, 3088, 0));
				player.viewingwoodcuttingteles = false;
			
			} else if (componentId == 112) {//Catherby Trees
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2716, 3499, 0));
				player.viewingwoodcuttingteles = false;
			
			} else if (componentId == 118) {//Underground Magic Trees
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1300, 4582, 0));
				player.viewingwoodcuttingteles = false;
			
			} else if (componentId == 136) {//Sorcerer's Tower
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2701, 3393, 0));
				player.viewingwoodcuttingteles = false;
			
			} else if (componentId == 124) {//Arctic Pine Trees
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2326, 3850, 0));
				player.viewingwoodcuttingteles = false;
			
			/*} else if (componentId == 130) {//Altar of Zaros
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3182, 5713, 0));
				player.viewingwoodcuttingteles = false;
			
			} else if (componentId == 127) {//Trollweiss Sledding
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2777, 3866, 0));
				player.viewingwoodcuttingteles = false;
			
			} else if (componentId == 145) {//Circus Tightrope
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3551, 5602, 2));
				player.viewingwoodcuttingteles = false;
			
			} else if (componentId == 148) {//Zanaris Crop Centre
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2427, 4446, 0));
				player.viewingwoodcuttingteles = false;
			
			} else if (componentId == 121) {//Light Creatures
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3228, 9526, 2));
				player.viewingwoodcuttingteles = false;
			
			} else if (componentId == 133) {//Bush Search
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2642, 3210, 0));
				player.viewingwoodcuttingteles = false;
			
			} else if (componentId == 142) {//Mos Le'Harmless
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3787, 2821, 0));
				player.viewingwoodcuttingteles = false;
			
			} else if (componentId == 151) {//Yu'Buisk Strange Box
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2194, 4260, 1));
				player.viewingwoodcuttingteles = false;
			
			} else if (componentId == 154) {//Falador Grapple
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3006, 3396, 0));
				player.viewingwoodcuttingteles = false;
			
			} else if (componentId == 169) {//Singing Bowl
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2388, 9814, 0));
				player.viewingwoodcuttingteles = false;
			
			} else if (componentId == 158) {//Clan Citadel Theatre
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4875, 4028, 0));
				player.viewingwoodcuttingteles = false;
			
			} else if (componentId == 162) {//Brimhaven Matrix
		    	Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2773, 9568, 3));
				player.viewingwoodcuttingteles = false;
			
			} else if (componentId == 166) {//Magic Wheat Field
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2592, 4312, 0));
				player.viewingwoodcuttingteles = false;
			
			} else if (componentId == 172) {//Advanced Gnome Course
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2473, 3418, 3));
				player.viewingwoodcuttingteles = false;
			
			} else if (componentId == 320) {//Blast Furnace Pedals
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1948, 4966, 0));
				player.viewingwoodcuttingteles = false;*/
			} 
		}
}
