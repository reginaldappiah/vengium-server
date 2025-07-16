package com.rs.game.player.content.interfaces;

import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.minigames.CastleWars;
import com.rs.game.player.Player;
import com.rs.game.player.content.Magic;

public class DTRSMVActionLocs {
	public static void sendOptions(Player player) {
		player.viewingagilityteles = false;
		player.viewingminigameteles = false;
		player.viewingmonsterteles = false;
		player.viewingrsmvactionlocs = true;
		player.viewingskillingteles = false;
		player.viewingtrainingteles = false;
		player.getInterfaceManager().sendInterface(1156);
		player.getPackets().sendIComponentText(1156, 190,
				"Popular RSMV Action Locations");
		player.getPackets().sendIComponentText(1156, 108, "Whirlpool  <col=FF0000><shad=FF0000>HOT</shad></col>");
		player.getPackets().sendIComponentText(1156, 109,
				" <col=FF0000>TURN CHARACTER SHADOWS OFF!</shad></col>");
		player.getPackets().sendIComponentText(1156, 90, "Teleport!");
		player.getPackets().sendIComponentText(1156, 113, "Electric Chair  <col=FF0000><shad=FF0000>HOT</shad></col>");
		player.getPackets().sendIComponentText(1156, 114,
				"Sit down in the chair and get zapped.");
		player.getPackets().sendIComponentText(1156, 206, "Teleport!");
		player.getPackets().sendIComponentText(1156, 137, "Minecart");
		player.getPackets().sendIComponentText(1156, 138,
				"This is maybe the only vehicle on Runescape.");
		player.getPackets().sendIComponentText(1156, 254, "Teleport!");
		player.getPackets().sendIComponentText(1156, 110, "Underwater");
		player.getPackets().sendIComponentText(1156, 111,
				"Hope you don't drown, I won't be there.");
		player.getPackets().sendIComponentText(1156, 200, "Teleport!");
		player.getPackets().sendIComponentText(1156, 116, "Fish Trawler Swim");
		player.getPackets().sendIComponentText(1156, 117,
				"It's like the Titanic, but without drowning.");
		player.getPackets().sendIComponentText(1156, 212, "Teleport!");
		player.getPackets().sendIComponentText(1156, 134,"Draynor Coffin");
		player.getPackets().sendIComponentText(1156, 135,
				"Yep, just go and randomly open someone's coffin.");
		player.getPackets().sendIComponentText(1156, 248, "Teleport!");
		player.getPackets().sendIComponentText(1156, 119, "Light Creatures");
		player.getPackets().sendIComponentText(1156, 120,
				"These unidentified creatures will just lift you up.");
		player.getPackets().sendIComponentText(1156, 218, "Teleport!");
		player.getPackets().sendIComponentText(1156, 131, "Bush Search");
		player.getPackets().sendIComponentText(1156, 132,
				"Think someone's hiding in a bush? Search that shit.");
		player.getPackets().sendIComponentText(1156, 242, "Teleport!");
		player.getPackets().sendIComponentText(1156, 140, "Mos Le'Harmless  <col=FF0000><shad=FF0000>HOT</shad></col>");
		player.getPackets().sendIComponentText(1156, 141,
				"It's not as harmless as you'd think.");
		player.getPackets().sendIComponentText(1156, 260, "Teleport!");
		player.getPackets().sendIComponentText(1156, 149, "Yu'Buisk Strange Box");
		player.getPackets().sendIComponentText(1156, 150,
				"The strange box that can teleport you to an agility course.");
		player.getPackets().sendIComponentText(1156, 278, "Teleport!");
		player.getPackets().sendIComponentText(1156, 152, "Falador Grapple");
		player.getPackets().sendIComponentText(1156, 153,
				"There is a well-known shortcut in Falador with a grapple involved.");
		player.getPackets().sendIComponentText(1156, 284, "Teleport!");
		player.getPackets().sendIComponentText(1156, 122, "Pier Dive");
		player.getPackets().sendIComponentText(1156, 123,
				"Just a little jump, that's all.");
		player.getPackets().sendIComponentText(1156, 230, "Teleport!");
		player.getPackets().sendIComponentText(1156, 128, "Altar of Zaros");
		player.getPackets().sendIComponentText(1156, 129,
				"Initially used to switch to curses.");
		player.getPackets().sendIComponentText(1156, 236, "Teleport!");
		player.getPackets().sendIComponentText(1156, 125, "Trollweiss Sledding");
		player.getPackets().sendIComponentText(1156, 126,
				"Get a friend and have a sled race.");
		player.getPackets().sendIComponentText(1156, 224, "Teleport!");
		player.getPackets().sendIComponentText(1156, 143, "Circus Tightrope");
		player.getPackets().sendIComponentText(1156, 144,
				"Just don't look down!");
		player.getPackets().sendIComponentText(1156, 266, "Teleport!");
		player.getPackets().sendIComponentText(1156, 146,"Zanaris Crop Centre  <col=FF0000><shad=FF0000>HOT</shad></col>");
		player.getPackets().sendIComponentText(1156, 147,
				"Initially used to teleport to Puro Puro.");
		player.getPackets().sendIComponentText(1156, 272, "Teleport!");
		player.getPackets().sendIComponentText(1156, 167, "Singing Bowl");
		player.getPackets().sendIComponentText(1156, 168,
				"The bowl doesn't sing at all.");
		player.getPackets().sendIComponentText(1156, 308, "Teleport!");
		player.getPackets().sendIComponentText(1156, 155, "<col=FF0000>Clan Citadel Theatre</col>");
		player.getPackets().sendIComponentText(1156, 157,
				"An RSMVer's dream");
		player.getPackets().sendHideIComponent(1156, 156, true);
		player.getPackets().sendIComponentText(1156, 290, "Teleport!");
		player.getPackets().sendIComponentText(1156, 159, "Brimhaven Matrix");
		player.getPackets().sendHideIComponent(1156, 160, true);
		player.getPackets().sendIComponentText(1156, 161,
				"The darts actually hit you though.");
		player.getPackets().sendIComponentText(1156, 296, "Teleport!");
		player.getPackets().sendIComponentText(1156, 163, "Magical Wheat Field");
		player.getPackets().sendHideIComponent(1156, 164, true);
		player.getPackets().sendIComponentText(1156, 165,
				"It shouldn't really take that long to get past some wheat.");
		player.getPackets().sendIComponentText(1156, 302, "Teleport!");
		player.getPackets().sendIComponentText(1156, 170,"Lumbridge Flagpole  <col=FF0000><shad=FF0000>NEW</shad></col>");
		player.getPackets().sendIComponentText(1156, 171,
				"This part of the course is used fairly often.");
		player.getPackets().sendIComponentText(1156, 314, "Teleport!");
		player.getPackets().sendIComponentText(1156, 318,"Blast Furnace Pedals  <col=FF0000><shad=FF0000>NEW</shad></col>");
		player.getPackets().sendIComponentText(1156, 319,
				"Pedal away on a makeshift bike.");
		player.getPackets().sendIComponentText(1156, 326, "Teleport!");
	}
	
	public static void handleButtons(Player player, int componentId) {
	if (componentId == 88) {//Whirlpool
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2512, 3511, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingrsmvactionlocs = false;
	} else if (componentId == 115) {//Electric Chair
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1959, 5149, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingrsmvactionlocs = false;
	} else if (componentId == 139) {//Minecart
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2909, 10172, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingrsmvactionlocs = false;
	} else if (componentId == 112) {//Underwater
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2967, 9483, 2));
		player.getAppearence().setRenderEmote(267);
		player.viewingrsmvactionlocs = false;
	} else if (componentId == 118) {//Fish Trawler Swim
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1953, 4825, 0));
		player.getAppearence().setRenderEmote(930);
		player.viewingrsmvactionlocs = false;
	} else if (componentId == 136) {//Draynor Coffin
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3080, 9782, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingrsmvactionlocs = false;
	} else if (componentId == 124) {//Pier Dive
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3048, 2979, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingrsmvactionlocs = false;
	} else if (componentId == 130) {//Altar of Zaros
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3182, 5713, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingrsmvactionlocs = false;
	} else if (componentId == 127 && player.getEquipment().getWeaponId() != -1) {//Trollweiss Sledding
		player.getPackets().sendGameMessage("<img=20><col=FF0000>You don't feel comfortable going sledding while holding an item.");
		}
		if(componentId == 127 && player.getEquipment().getWeaponId() == -1) {//Trollweiss Sledding
			CastleWars.setWeapon(player, new Item(4084, 1));
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2777, 3866, 0));
			player.viewingrsmvactionlocs = false;
	} else if (componentId == 145) {//Circus Tightrope
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3551, 5602, 2));
		player.viewingrsmvactionlocs = false;
		player.getAppearence().setRenderEmote(330);
	} else if (componentId == 148) {//Zanaris Crop Centre
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2427, 4446, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingrsmvactionlocs = false;
	} else if (componentId == 121) {//Light Creatures
		player.getPackets().sendGameMessage("<img=20><col=FF0000>Click on the Clump of rocks to travel to the other side.");
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3228, 9526, 2));
		player.getAppearence().setRenderEmote(-1);
		player.viewingrsmvactionlocs = false;
	} else if (componentId == 133) {//Bush Search
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2642, 3210, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingrsmvactionlocs = false;
	} else if (componentId == 142) {//Mos Le'Harmless
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3787, 2821, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingrsmvactionlocs = false;
	} else if (componentId == 151) {//Yu'Buisk Strange Box
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2194, 4260, 1));
		player.getAppearence().setRenderEmote(-1);
		player.viewingrsmvactionlocs = false;
	} else if (componentId == 154) {//Falador Grapple
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3006, 3396, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingrsmvactionlocs = false;
	} else if (componentId == 169) {//Singing Bowl
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2388, 9814, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingrsmvactionlocs = false;
	} else if (componentId == 158) {//Clan Citadel Theatre
		player.getPackets().sendGameMessage("<img=20><col=FF0000>Use the Prop Machine's Make-backdrop option to change the backgrounds.");
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4875, 4028, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingrsmvactionlocs = false;
	} else if (componentId == 162) {//Brimhaven Matrix
		player.getPackets().sendGameMessage("<img=20><col=FF0000>Tag the Ticket Dispenser!");
    	Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2773, 9568, 3));
		player.getAppearence().setRenderEmote(-1);
		player.viewingrsmvactionlocs = false;
	} else if (componentId == 166) {//Magic Wheat Field
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2592, 4312, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingrsmvactionlocs = false;
	} else if (componentId == 172) {//Lumbridge Flagpole
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3210, 3217, 3));
		player.getAppearence().setRenderEmote(-1);
		player.viewingrsmvactionlocs = false;
	} else if (componentId == 320) {//Blast Furnace Pedals
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1948, 4966, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingrsmvactionlocs = false;
	} 
	}
}
