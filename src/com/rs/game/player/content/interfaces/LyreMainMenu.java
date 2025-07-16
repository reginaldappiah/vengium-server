package com.rs.game.player.content.interfaces;

import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.minigames.CastleWars;
import com.rs.game.player.Player;
import com.rs.game.player.content.Magic;

public class LyreMainMenu {
	public static void sendMainMenu (Player player) {
		player.viewingagilityteles = false;
		player.viewingminigameteles = false;
		player.viewingmonsterteles = false;
		player.viewingrsmvactionlocs = false;
		player.viewingskillingteles = false;
		player.viewingtrainingteles = false;
		player.viewinglyreteles = true;
		player.viewingfamouscategories = false;
		player.viewingbrightcategories = false;
		player.viewingmoodycategories = false;
		player.viewingfairyringcategories = false;
		player.viewingextracategories = false;
		player.getInterfaceManager().sendInterface(1160);
		player.getPackets().sendIComponentText(1160, 75, "Main Menu: The RSMV Teleport Lyre");
		player.getPackets().sendIComponentText(1160, 41, "1."); player.getPackets().sendIComponentText(1160, 26, "Famous Categories"); 
		player.getPackets().sendIComponentText(1160, 44, "2."); player.getPackets().sendIComponentText(1160, 28, "Bright Categories");
		player.getPackets().sendIComponentText(1160, 46, "3."); player.getPackets().sendIComponentText(1160, 30, "Moody Categories");
		player.getPackets().sendIComponentText(1160, 48, "4."); player.getPackets().sendIComponentText(1160, 32, "Fairy Ring Categories");
		player.getPackets().sendIComponentText(1160, 50, "5."); player.getPackets().sendIComponentText(1160, 34, "Extra Categories");
		player.getPackets().sendIComponentText(1160, 52, "6."); player.getPackets().sendIComponentText(1160, 36, "Old Lyre Dialogue");
		player.getPackets().sendIComponentText(1160, 54, "7."); player.getPackets().sendIComponentText(1160, 39, "Random Teleport");
		player.getPackets().sendIComponentText(1160, 56, "8."); player.getPackets().sendIComponentText(1160, 40, "Nevermind");
	}
	public static void sendMainMenuOptions(Player player, int componentId) {
		if (componentId == 42) {//Famous Categories
			player.viewingagilityteles = false;
			player.viewingminigameteles = false;
			player.viewingmonsterteles = false;
			player.viewingrsmvactionlocs = false;
			player.viewingskillingteles = false;
			player.viewingtrainingteles = false;
			player.viewinglyreteles = true;
			player.viewingfamouscategories = true;
			player.viewingbrightcategories = false;
			player.viewingmoodycategories = false;
			player.viewingfairyringcategories = false;
			player.viewingextracategories = false;
			sendFamousCategories(player);
		} else if (componentId == 45) {//Bright Categories
			player.viewingagilityteles = false;
			player.viewingminigameteles = false;
			player.viewingmonsterteles = false;
			player.viewingrsmvactionlocs = false;
			player.viewingskillingteles = false;
			player.viewingtrainingteles = false;
			player.viewinglyreteles = true;
			player.viewingfamouscategories = false;
			player.viewingbrightcategories = true;
			player.viewingmoodycategories = false;
			player.viewingfairyringcategories = false;
			player.viewingextracategories = false;
			sendBrightCategories(player);
		} else if (componentId == 53) {//Old Lyre
			player.viewingagilityteles = false;
			player.viewingminigameteles = false;
			player.viewingmonsterteles = false;
			player.viewingrsmvactionlocs = false;
			player.viewingskillingteles = false;
			player.viewingtrainingteles = false;
			player.viewinglyreteles = false;
			player.viewingfamouscategories = false;
			player.viewingbrightcategories = false;
			player.viewingmoodycategories = false;
			player.viewingfairyringcategories = false;
			player.viewingextracategories = false;
			player.getDialogueManager().startDialogue("LyreE");
			player.getInterfaceManager().closeInterface(1, 2);
		} else if (componentId == 55) {//Random Teleporter
			
		} else if (componentId == 57) {//Nevermind
			player.viewingagilityteles = false;
			player.viewingminigameteles = false;
			player.viewingmonsterteles = false;
			player.viewingrsmvactionlocs = false;
			player.viewingskillingteles = false;
			player.viewingtrainingteles = false;
			player.viewinglyreteles = false;
			player.viewingfamouscategories = false;
			player.viewingbrightcategories = false;
			player.viewingmoodycategories = false;
			player.viewingfairyringcategories = false;
			player.viewingextracategories = false;
			player.getInterfaceManager().closeScreenInterface();
			
		}
	}
	//Famous Categories
	
	public static void sendFamousCategories (Player player) {
		player.viewingagilityteles = false;
		player.viewingminigameteles = false;
		player.viewingmonsterteles = false;
		player.viewingrsmvactionlocs = false;
		player.viewingskillingteles = false;
		player.viewingtrainingteles = false;
		player.viewinglyreteles = true;
		player.viewingfamouscategories = true;
		player.viewingbrightcategories = false;
		player.viewingmoodycategories = false;
		player.viewingfairyringcategories = false;
		player.viewingextracategories = false;
		player.getInterfaceManager().sendInterface(1164);
		player.getPackets().sendIComponentText(1164, 51, "Categories");
		player.getPackets().sendIComponentText(1164, 25, "Main Menu");
		player.getPackets().sendIComponentText(1164, 27, "<shad=000000><col=FF0000>~ Famous Categories ~</col></shad>");		
		player.getPackets().sendIComponentText(1164, 17, "Famous IV");
		player.getPackets().sendIComponentText(1164, 19, "Famous III");
		player.getPackets().sendIComponentText(1164, 21, "Famous II");
		player.getPackets().sendIComponentText(1164, 23, "Famous I");
		
	}
	
	public static void sendFamousMenuOptions(Player player, int componentId) {
			player.viewingagilityteles = false;
			player.viewingminigameteles = false;
			player.viewingmonsterteles = false;
			player.viewingrsmvactionlocs = false;
			player.viewingskillingteles = false;
			player.viewingtrainingteles = false;
			player.viewinglyreteles = true;
			player.viewingfamouscategories = true;
			player.viewingbrightcategories = false;
			player.viewingmoodycategories = false;
			player.viewingfairyringcategories = false;
			player.viewingextracategories = false;
		if (componentId == 42) {//Famous Categories
			player.getInterfaceManager().sendInterface(1160);
			sendFamousCategories(player);
		} else if (componentId == 26) {//Main Menu
			sendMainMenu(player);
		} else if (componentId == 28) {//Famous I
			player.viewingfamousIteles = true;
			player.viewingfamousIIteles = false;
			player.viewingfamousIIIteles = false;
			player.viewingfamousIIIIteles = false;
			player.viewingbrightIteles = false;
			player.viewingbrightIIteles = false;
			player.viewingbrightIIIteles = false;
			player.viewingbrightIIIIteles = false;
			player.viewingmoodyIteles = false;
			player.viewingmoodyIIteles = false;
			player.viewingmoodyIIIteles = false;
			player.viewingmoodyIIIIteles = false;
			player.viewingfairyIteles = false;
			player.viewingfairyIIteles = false;
			player.viewingfairyIIIteles = false;
			player.viewingfairyIIIIteles = false;
			player.viewingextraIteles = false;
			player.viewingextraIIteles = false;
			player.viewingextraIIIteles = false;
			player.viewingextraIIIIteles = false;
			sendFamousITeleports(player);
		} else if (componentId == 29) {//Famous II
			player.viewingfamousIteles = false;
			player.viewingfamousIIteles = true;
			player.viewingfamousIIIteles = false;
			player.viewingfamousIIIIteles = false;
			player.viewingbrightIteles = false;
			player.viewingbrightIIteles = false;
			player.viewingbrightIIIteles = false;
			player.viewingbrightIIIIteles = false;
			player.viewingmoodyIteles = false;
			player.viewingmoodyIIteles = false;
			player.viewingmoodyIIIteles = false;
			player.viewingmoodyIIIIteles = false;
			player.viewingfairyIteles = false;
			player.viewingfairyIIteles = false;
			player.viewingfairyIIIteles = false;
			player.viewingfairyIIIIteles = false;
			player.viewingextraIteles = false;
			player.viewingextraIIteles = false;
			player.viewingextraIIIteles = false;
			player.viewingextraIIIIteles = false;
			sendFamousIITeleports(player);
		} else if (componentId == 30) {//Famous III
			player.viewingfamousIteles = false;
			player.viewingfamousIIteles = false;
			player.viewingfamousIIIteles = true;
			player.viewingfamousIIIIteles = false;
			player.viewingbrightIteles = false;
			player.viewingbrightIIteles = false;
			player.viewingbrightIIIteles = false;
			player.viewingbrightIIIIteles = false;
			player.viewingmoodyIteles = false;
			player.viewingmoodyIIteles = false;
			player.viewingmoodyIIIteles = false;
			player.viewingmoodyIIIIteles = false;
			player.viewingfairyIteles = false;
			player.viewingfairyIIteles = false;
			player.viewingfairyIIIteles = false;
			player.viewingfairyIIIIteles = false;
			player.viewingextraIteles = false;
			player.viewingextraIIteles = false;
			player.viewingextraIIIteles = false;
			player.viewingextraIIIIteles = false;
			sendFamousIIITeleports(player);
		} else if (componentId == 31) {//Famous IIII
			player.viewingfamousIteles = false;
			player.viewingfamousIIteles = false;
			player.viewingfamousIIIteles = false;
			player.viewingfamousIIIIteles = true;
			player.viewingbrightIteles = false;
			player.viewingbrightIIteles = false;
			player.viewingbrightIIIteles = false;
			player.viewingbrightIIIIteles = false;
			player.viewingmoodyIteles = false;
			player.viewingmoodyIIteles = false;
			player.viewingmoodyIIIteles = false;
			player.viewingmoodyIIIIteles = false;
			player.viewingfairyIteles = false;
			player.viewingfairyIIteles = false;
			player.viewingfairyIIIteles = false;
			player.viewingfairyIIIIteles = false;
			player.viewingextraIteles = false;
			player.viewingextraIIteles = false;
			player.viewingextraIIIteles = false;
			player.viewingextraIIIIteles = false;
			sendFamousIIIITeleports(player);
		}
	}
	

	public static void sendFamousITeleports (Player player) {
			player.viewingagilityteles = false;
			player.viewingminigameteles = false;
			player.viewingmonsterteles = false;
			player.viewingrsmvactionlocs = false;
			player.viewingskillingteles = false;
			player.viewingtrainingteles = false;	
			player.viewinglyreteles = true;
			player.viewingfamousIteles = true;
			player.viewingfamousIIteles = false;
			player.viewingfamousIIIteles = false;
			player.viewingfamousIIIIteles = false;
			player.getInterfaceManager().sendInterface(1156);
			player.getPackets().sendIComponentText(1156, 190,
					"Famous I Teleports");
			player.getPackets().sendIComponentText(1156, 108, "Clocktower Fire");
			player.getPackets().sendIComponentText(1156, 109,
					"This place is literally on fire.");
			player.getPackets().sendIComponentText(1156, 90, "Teleport!");
			player.getPackets().sendIComponentText(1156, 113, "Ruinz oF Uzer");
			player.getPackets().sendIComponentText(1156, 114,
					"Best place to play the Charlie Charlie challenge.");
			player.getPackets().sendIComponentText(1156, 206, "Teleport!");
			player.getPackets().sendIComponentText(1156, 137, "Underground Pass");
			player.getPackets().sendIComponentText(1156, 138,
					"'Follow me into the dirt.'");
			player.getPackets().sendIComponentText(1156, 254, "Teleport!");
			player.getPackets().sendIComponentText(1156, 110, "Ardougne Gallow");
			player.getPackets().sendIComponentText(1156, 111,
					"Ne-ver fall a-sleep!");
			player.getPackets().sendIComponentText(1156, 200, "Teleport!");
			player.getPackets().sendIComponentText(1156, 116, "H.A.M Lair");
			player.getPackets().sendIComponentText(1156, 117,
					"'I hear you brother...'");
			player.getPackets().sendIComponentText(1156, 212, "Teleport!");
			player.getPackets().sendIComponentText(1156, 134,"Map Table");
			player.getPackets().sendIComponentText(1156, 135,
					"Hunter, I think I found your dad.");
			player.getPackets().sendIComponentText(1156, 248, "Teleport!");
			player.getPackets().sendIComponentText(1156, 122, "Killer-Watt Clouds");
			player.getPackets().sendIComponentText(1156, 123,
					"'I remembered black skies'");
			player.getPackets().sendIComponentText(1156, 218, "Teleport!");
			player.getPackets().sendIComponentText(1156, 128, "Strange Creatures");
			player.getPackets().sendIComponentText(1156, 129,
					"'I'd like to make myself believe...'");
			player.getPackets().sendIComponentText(1156, 242, "Teleport!");
			player.getPackets().sendIComponentText(1156, 125, "Lighthouse");
			player.getPackets().sendIComponentText(1156, 126,
					"'You show the lights that stop me'");
			player.getPackets().sendIComponentText(1156, 260, "Teleport!");
			player.getPackets().sendIComponentText(1156, 143, "Guthix Circle");
			player.getPackets().sendIComponentText(1156, 144,
					"'To stop the pain inside and feel again...'");
			player.getPackets().sendIComponentText(1156, 278, "Teleport!");
			player.getPackets().sendIComponentText(1156, 146, "Arandar");
			player.getPackets().sendIComponentText(1156, 147,
					"'If you love me, won't you let me go'");
			player.getPackets().sendIComponentText(1156, 284, "Teleport!");
			player.getPackets().sendIComponentText(1156, 119, "Ruinz of Ullek");
			player.getPackets().sendIComponentText(1156, 120,
					"'To the edge, of the earth.'");
			player.getPackets().sendIComponentText(1156, 230, "Teleport!");
			player.getPackets().sendIComponentText(1156, 131, "Lunar Mine");
			player.getPackets().sendIComponentText(1156, 132,
					"'If you take it from me!'");
			player.getPackets().sendIComponentText(1156, 242, "Teleport!");
			player.getPackets().sendIComponentText(1156, 140, "Ritual of Mahajarrat");
			player.getPackets().sendIComponentText(1156, 141,
					"'Now tell me all a-bout your pain down to the detail...'");
			player.getPackets().sendIComponentText(1156, 224, "Teleport!");
			player.getPackets().sendIComponentText(1156, 149, "Seers Rooftop");
			player.getPackets().sendIComponentText(1156, 150,
					"'We see the stones, falling from the sky...!");
			player.getPackets().sendIComponentText(1156, 266, "Teleport!");
			player.getPackets().sendIComponentText(1156, 152,"Old Lumbridge");
			player.getPackets().sendIComponentText(1156, 142,
					"'I'm a genius with a headache.");
			player.getPackets().sendIComponentText(1156, 272, "Teleport!");
			player.getPackets().sendIComponentText(1156, 167, "Blue Moon Inn  <col=FF0000><shad=FF0000>NEW</shad></col>");
			player.getPackets().sendIComponentText(1156, 168,
					"'Ain't no rest for the wicked'");
			player.getPackets().sendIComponentText(1156, 308, "Teleport!");
			player.getPackets().sendIComponentText(1156, 155, "Edgeville Bar  <col=FF0000><shad=FF0000>NEW</shad></col>");
			player.getPackets().sendIComponentText(1156, 157,
					"");
			player.getPackets().sendHideIComponent(1156, 156, true);
			player.getPackets().sendIComponentText(1156, 290, "Teleport!");
			player.getPackets().sendIComponentText(1156, 159, "Bandos Throne Room Portal  <col=FF0000><shad=FF0000>NEW</shad></col>");
			player.getPackets().sendHideIComponent(1156, 160, true);
			player.getPackets().sendIComponentText(1156, 161,
					"'It begins with a dark glowing ember.'");
			player.getPackets().sendIComponentText(1156, 296, "Teleport!");
			player.getPackets().sendIComponentText(1156, 163, "Fishing Colony  <col=FF0000><shad=FF0000>NEW</shad></col>");
			player.getPackets().sendHideIComponent(1156, 164, true);
			player.getPackets().sendIComponentText(1156, 165,
					"Rune Factor 3");
			player.getPackets().sendIComponentText(1156, 302, "Teleport!");
			player.getPackets().sendIComponentText(1156, 170,"Underground Pass Temple  <col=FF0000><shad=FF0000>NEW</shad></col>");
			player.getPackets().sendIComponentText(1156, 171,
					"'As two hands open doors...!'");
			player.getPackets().sendIComponentText(1156, 314, "Teleport!");
			player.getPackets().sendIComponentText(1156, 318,"Sorcercer's Garden Fountain  <col=FF0000><shad=FF0000>NEW</shad></col>");
			player.getPackets().sendIComponentText(1156, 319,
					"Pedal away on a makeshift bike.");
			player.getPackets().sendIComponentText(1156, 326, "Teleport!");	
		 }

	public static void handleFamousIButtons(Player player, int componentId) {
		if (componentId == 88) {//Clocktower Fire
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2609, 9639, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 115) {//Ruins of Uzer
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3552, 4964, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 139) {//Underground Pass
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2447, 3315, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 112) {//Ardougne Gallow
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2524, 3305, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 118) {//H.A.M Lair
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3160, 9634, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 136) {//Map Table
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1693, 5469, 2));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 124) {//KillerWatt Clouds
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2659, 5216, 2));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 130) {//Strange Creatures
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3228, 9526, 2));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 127) {//Lighthouse
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2508, 3645, 2));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 145) {//Guthix Circle
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2540, 5772, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 148) {//Arandar
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2347, 3301, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 121) {//Ruins of Ullek
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3409, 2800, 1));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 133) {//Lunar Mine
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2339, 10337, 2));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 142) {//Ritual of Mahjarrat
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2887, 3864, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 151) {//Seers Rooftop
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2701, 3472, 3));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 154) {//Old Lumbridge
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2340, 5719, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 169) {//Blue Moon Inn
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3222, 3399, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 158) {//Edgveille Bar
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3079, 3442, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 162) {//Bandos Throne Room Portal
	    	Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2320, 4242, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 166) {//Fishing Colony
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1501, 4314, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 172) {//Underground Pass Temple
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2146, 4648, 1));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 320) {//Sorcerer's Garden Fountain
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2913, 5471, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} 
	}
	
	public static void sendFamousIITeleports (Player player) {
		player.viewingagilityteles = false;
		player.viewingminigameteles = false;
		player.viewingmonsterteles = false;
		player.viewingrsmvactionlocs = false;
		player.viewingskillingteles = false;
		player.viewingtrainingteles = false;	
		player.viewinglyreteles = true;
		player.viewingfamousIteles = false;
		player.viewingfamousIIteles = true;
		player.viewingfamousIIIteles = false;
		player.viewingfamousIIIIteles = false;
		player.getInterfaceManager().sendInterface(1156);
		player.getPackets().sendIComponentText(1156, 190,
				"Famous II Teleports");
		player.getPackets().sendIComponentText(1156, 108, "Railroad");
		player.getPackets().sendIComponentText(1156, 109,
				"'Frozen in the lights of a locomotive'");
		player.getPackets().sendIComponentText(1156, 90, "Teleport!");
		player.getPackets().sendIComponentText(1156, 113, "Tower of Life Underground");
		player.getPackets().sendIComponentText(1156, 114,
				"'To make a final remedy.'");
		player.getPackets().sendIComponentText(1156, 206, "Teleport!");
		player.getPackets().sendIComponentText(1156, 137, "Abyssal Rift");
		player.getPackets().sendIComponentText(1156, 138,
				"Known as the road more or less travelled by.");
		player.getPackets().sendIComponentText(1156, 254, "Teleport!");
		player.getPackets().sendIComponentText(1156, 110, "Baxatorian Falls");
		player.getPackets().sendIComponentText(1156, 111,
				"'Tell me your real name...!'");
		player.getPackets().sendIComponentText(1156, 200, "Teleport!");
		player.getPackets().sendIComponentText(1156, 116, "Legendary Magic Trees");
		player.getPackets().sendIComponentText(1156, 117,
				"'I'm a slave...!'");
		player.getPackets().sendIComponentText(1156, 212, "Teleport!");
		player.getPackets().sendIComponentText(1156, 134,"Lletya Overview");
		player.getPackets().sendIComponentText(1156, 135,
				"'Am I a little sick?'");
		player.getPackets().sendIComponentText(1156, 248, "Teleport!");
		player.getPackets().sendIComponentText(1156, 122, "Clan Citadel Dancefloor");
		player.getPackets().sendIComponentText(1156, 123,
				"'Everyday I'm shufflin'...'");
		player.getPackets().sendIComponentText(1156, 218, "Teleport!");
		player.getPackets().sendIComponentText(1156, 128, "Underground Pass Well");
		player.getPackets().sendIComponentText(1156, 129,
				"'What can protect me from the past?'");
		player.getPackets().sendIComponentText(1156, 242, "Teleport!");
		player.getPackets().sendIComponentText(1156, 125, "Catharede Abbey Bell");
		player.getPackets().sendIComponentText(1156, 126,
				"''");
		player.getPackets().sendIComponentText(1156, 260, "Teleport!");
		player.getPackets().sendIComponentText(1156, 143, "Ecotfongus Underground");
		player.getPackets().sendIComponentText(1156, 144,
				"'One more time, I'm on my knees!'");
		player.getPackets().sendIComponentText(1156, 278, "Teleport!");
		player.getPackets().sendIComponentText(1156, 146, "Chaos Altar(Wilderness)");
		player.getPackets().sendIComponentText(1156, 147,
				"'They tore me apart!'");
		player.getPackets().sendIComponentText(1156, 284, "Teleport!");
		player.getPackets().sendIComponentText(1156, 119, "Varrock Rooftop");
		player.getPackets().sendIComponentText(1156, 120,
				"'We are the kings...!'");
		player.getPackets().sendIComponentText(1156, 230, "Teleport!");
		player.getPackets().sendIComponentText(1156, 131, "Camelot Pillars");
		player.getPackets().sendIComponentText(1156, 132,
				"'Will I become my worst enemy...?'");
		player.getPackets().sendIComponentText(1156, 242, "Teleport!");
		player.getPackets().sendIComponentText(1156, 140, "Ice Mountain");
		player.getPackets().sendIComponentText(1156, 141,
				"'And watch that Colorado Sunrise'");
		player.getPackets().sendIComponentText(1156, 224, "Teleport!");
		player.getPackets().sendIComponentText(1156, 149, "VWB");
		player.getPackets().sendIComponentText(1156, 150,
				"Basically Neverland, but as a bank.");
		player.getPackets().sendIComponentText(1156, 266, "Teleport!");
		player.getPackets().sendIComponentText(1156, 152,"Witchaven Pillars");
		player.getPackets().sendIComponentText(1156, 142,
				"'You know to keep your hopes.");
		player.getPackets().sendIComponentText(1156, 272, "Teleport!");
		player.getPackets().sendIComponentText(1156, 167, "N/A");
		player.getPackets().sendIComponentText(1156, 168,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 308, "Teleport!");
		player.getPackets().sendIComponentText(1156, 155, "N/A");
		player.getPackets().sendIComponentText(1156, 157,
				"Suggest for special prizes");
		player.getPackets().sendHideIComponent(1156, 156, true);
		player.getPackets().sendIComponentText(1156, 290, "Teleport!");
		player.getPackets().sendIComponentText(1156, 159, "N/A");
		player.getPackets().sendHideIComponent(1156, 160, true);
		player.getPackets().sendIComponentText(1156, 161,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 296, "Teleport!");
		player.getPackets().sendIComponentText(1156, 163, "N/A");
		player.getPackets().sendHideIComponent(1156, 164, true);
		player.getPackets().sendIComponentText(1156, 165,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 302, "Teleport!");
		player.getPackets().sendIComponentText(1156, 170,"N/A");
		player.getPackets().sendIComponentText(1156, 171,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 314, "Teleport!");
		player.getPackets().sendIComponentText(1156, 318,"N/A");
		player.getPackets().sendIComponentText(1156, 319,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 326, "Teleport!");	
	 }

	public static void handleFamousIIButtons(Player player, int componentId) {
	if (componentId == 88) {//Railroad
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2615, 3501, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 115) {//Tower Of Life Underground
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3040, 4390, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 139) {//Abysaal Rift
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3038, 4833, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 112) {//Baxatorian Falls
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2456, 4568, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 118) {//Legendary Magic Trees
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1300, 4588, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 136) {//Lletya Overview
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2343, 3172, 1));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 124) {//Clan Citadel Dancefloor
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(5376, 4714, 1));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 130) {//Underground Pass Well
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2342, 9621, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 127) {//Catharede Abbey Bell
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3450, 3178, 3));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 145) {//Ecotfongus Underground
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3683, 9888, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 148) {//Chaos Altar(Wilderness)
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3240, 3613, 0));
		player.getControlerManager().startControler("Wilderness");
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 121) {//Varrock Rooftop
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3212, 3467, 2));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 133) {//Camelot Pillars
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2780, 3515, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 142) {//Ice Mountain
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3003, 3470, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 151) {//VWB
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3185, 3440, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 154) {//Witchaven Pillars
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2697, 3283, 0));
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIteles = false;
	} else if (componentId == 169) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIteles = false;
	} else if (componentId == 158) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIteles = false;
	} else if (componentId == 162) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIteles = false;
	} else if (componentId == 166) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIteles = false;
	} else if (componentId == 172) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIteles = false;
	} else if (componentId == 320) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIteles = false;
	} 
}
	
	public static void sendFamousIIITeleports (Player player) {
		player.viewingagilityteles = false;
		player.viewingminigameteles = false;
		player.viewingmonsterteles = false;
		player.viewingrsmvactionlocs = false;
		player.viewingskillingteles = false;
		player.viewingtrainingteles = false;	
		player.viewinglyreteles = true;
		player.viewingfamousIteles = false;
		player.viewingfamousIIteles = false;
		player.viewingfamousIIIteles = true;
		player.viewingfamousIIIIteles = false;
		player.getInterfaceManager().sendInterface(1156);
		player.getPackets().sendIComponentText(1156, 190,
				"Famous III Teleports");
		player.getPackets().sendIComponentText(1156, 108, "Gift of Peace");
		player.getPackets().sendIComponentText(1156, 109,
				"'I couldn't face a life without your lights.'");
		player.getPackets().sendIComponentText(1156, 90, "Teleport!");
		player.getPackets().sendIComponentText(1156, 113, "Grain of Plenty");
		player.getPackets().sendIComponentText(1156, 114,
				"'What can I do?'");
		player.getPackets().sendIComponentText(1156, 206, "Teleport!");
		player.getPackets().sendIComponentText(1156, 137, "Box of Health");
		player.getPackets().sendIComponentText(1156, 138,
				"");
		player.getPackets().sendIComponentText(1156, 254, "Teleport!");
		player.getPackets().sendIComponentText(1156, 110, "Cradle of Life");
		player.getPackets().sendIComponentText(1156, 111,
				"'Sym-pa...-thy!'");
		player.getPackets().sendIComponentText(1156, 200, "Teleport!");
		player.getPackets().sendIComponentText(1156, 116, "Gu'Tanoth Bridge");
		player.getPackets().sendIComponentText(1156, 117,
				"");
		player.getPackets().sendIComponentText(1156, 212, "Teleport!");
		player.getPackets().sendIComponentText(1156, 134,"Mudskipper Point");
		player.getPackets().sendIComponentText(1156, 135,
				"");
		player.getPackets().sendIComponentText(1156, 248, "Teleport!");
		player.getPackets().sendIComponentText(1156, 122, "Port Sarim Hill");
		player.getPackets().sendIComponentText(1156, 123,
				"");
		player.getPackets().sendIComponentText(1156, 218, "Teleport!");
		player.getPackets().sendIComponentText(1156, 128, "Falador High Point");
		player.getPackets().sendIComponentText(1156, 129,
				"");
		player.getPackets().sendIComponentText(1156, 242, "Teleport!");
		player.getPackets().sendIComponentText(1156, 125, "Party Room");
		player.getPackets().sendIComponentText(1156, 126,
				"'Aint nobody dope as me.'");
		player.getPackets().sendIComponentText(1156, 260, "Teleport!");
		player.getPackets().sendIComponentText(1156, 143, "Blue Moon Inn");
		player.getPackets().sendIComponentText(1156, 144,
				"'I gave you everything to die with a smile.'");
		player.getPackets().sendIComponentText(1156, 278, "Teleport!");
		player.getPackets().sendIComponentText(1156, 146, "Edgeville School");
		player.getPackets().sendIComponentText(1156, 147,
				"'Give my props to the speak and spell!'");
		player.getPackets().sendIComponentText(1156, 284, "Teleport!");
		player.getPackets().sendIComponentText(1156, 119, "Monuments");
		player.getPackets().sendIComponentText(1156, 120,
				"'You have a grand stand seat here''");
		player.getPackets().sendIComponentText(1156, 230, "Teleport!");
		player.getPackets().sendIComponentText(1156, 131, "Desert Pyramid");
		player.getPackets().sendIComponentText(1156, 132,
				"'Be-hind...closed...doors...!'");
		player.getPackets().sendIComponentText(1156, 242, "Teleport!");
		player.getPackets().sendIComponentText(1156, 140, "Marko's Spot(Wilderness)");
		player.getPackets().sendIComponentText(1156, 141,
				"Hai Marko <3");
		player.getPackets().sendIComponentText(1156, 224, "Teleport!");
		player.getPackets().sendIComponentText(1156, 149, "VWB");
		player.getPackets().sendIComponentText(1156, 150,
				"Neverland but as a bank.");
		player.getPackets().sendIComponentText(1156, 266, "Teleport!");
		player.getPackets().sendIComponentText(1156, 152,"Isadafar Butterflies");
		player.getPackets().sendIComponentText(1156, 142,
				"");
		player.getPackets().sendIComponentText(1156, 272, "Teleport!");
		player.getPackets().sendIComponentText(1156, 167, "N/A");
		player.getPackets().sendIComponentText(1156, 168,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 308, "Teleport!");
		player.getPackets().sendIComponentText(1156, 155, "N/A");
		player.getPackets().sendIComponentText(1156, 157,
				"Suggest for special prizes");
		player.getPackets().sendHideIComponent(1156, 156, true);
		player.getPackets().sendIComponentText(1156, 290, "Teleport!");
		player.getPackets().sendIComponentText(1156, 159, "N/A");
		player.getPackets().sendHideIComponent(1156, 160, true);
		player.getPackets().sendIComponentText(1156, 161,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 296, "Teleport!");
		player.getPackets().sendIComponentText(1156, 163, "N/A");
		player.getPackets().sendHideIComponent(1156, 164, true);
		player.getPackets().sendIComponentText(1156, 165,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 302, "Teleport!");
		player.getPackets().sendIComponentText(1156, 170,"N/A");
		player.getPackets().sendIComponentText(1156, 171,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 314, "Teleport!");
		player.getPackets().sendIComponentText(1156, 318,"N/A");
		player.getPackets().sendIComponentText(1156, 319,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 326, "Teleport!");	
	 }

	public static void handleFamousIIIButtons(Player player, int componentId) {
	if (componentId == 88) {//"Gift of Peace
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1908, 5222, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 115) {//Grain of Plenty
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2023, 5215, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 139) {//Box of Health
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2145, 5280, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 112) {//Cradle of Life
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2345, 5214, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 118) {//Gu'Tanoth Bridge
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2536, 3012, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 136) {//Mudskipper Point
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2994, 3114, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 124) {//Port Sarim Hill
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3007, 3144, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 130) {//Falador High Point
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2995, 3342, 3));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 127) {//Party Room
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3046, 3377, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 145) {//Blue Moon Inn
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3222, 3402, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 148) {//Edgeville School)
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3081, 3456, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 121) {//Monuments
		player.getPackets().sendGameMessage("<col=FF0000>You have unlocked a new music track: Lolita's Medicine");
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3420, 9887, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 133) {//Desert Pyramid
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3233, 2903, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 142) {//Marko's Spot(Wilderness)
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3099, 3624, 0));
		player.getControlerManager().startControler("Wilderness");
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 151) {//Isadafar Butterflies
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2245, 3183, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 154) {//Karamja Volcano
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2845, 3168, 0));
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} else if (componentId == 169) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} else if (componentId == 158) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} else if (componentId == 162) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} else if (componentId == 166) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} else if (componentId == 172) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} else if (componentId == 320) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} 
}
	
	public static void sendFamousIIIITeleports (Player player) {
		player.viewingagilityteles = false;
		player.viewingminigameteles = false;
		player.viewingmonsterteles = false;
		player.viewingrsmvactionlocs = false;
		player.viewingskillingteles = false;
		player.viewingtrainingteles = false;	
		player.viewinglyreteles = true;
		player.viewingfamousIteles = false;
		player.viewingfamousIIteles = false;
		player.viewingfamousIIIteles = false;
		player.viewingfamousIIIIteles = true;
		player.getInterfaceManager().sendInterface(1156);
		player.getPackets().sendIComponentText(1156, 190,
				"Famous IIII Teleports");
		player.getPackets().sendIComponentText(1156, 108, "Juliet Balcony");
		player.getPackets().sendIComponentText(1156, 109,
				"");
		player.getPackets().sendIComponentText(1156, 90, "Teleport!");
		player.getPackets().sendIComponentText(1156, 113, "Varrock Museum");
		player.getPackets().sendIComponentText(1156, 114,
				"");
		player.getPackets().sendIComponentText(1156, 206, "Teleport!");
		player.getPackets().sendIComponentText(1156, 137, "Chaos Altar Stars");
		player.getPackets().sendIComponentText(1156, 138,
				"");
		player.getPackets().sendIComponentText(1156, 254, "Teleport!");
		player.getPackets().sendIComponentText(1156, 110, "Games Room");
		player.getPackets().sendIComponentText(1156, 111,
				"");
		player.getPackets().sendIComponentText(1156, 200, "Teleport!");
		player.getPackets().sendIComponentText(1156, 116, "Temporary POH");
		player.getPackets().sendIComponentText(1156, 117,
				"");
		player.getPackets().sendIComponentText(1156, 212, "Teleport!");
		player.getPackets().sendIComponentText(1156, 134,"Mage Arena");
		player.getPackets().sendIComponentText(1156, 135,
				"");
		player.getPackets().sendIComponentText(1156, 248, "Teleport!");
		player.getPackets().sendIComponentText(1156, 122, "Wizard Tower Bridge");
		player.getPackets().sendIComponentText(1156, 123,
				"");
		player.getPackets().sendIComponentText(1156, 218, "Teleport!");
		player.getPackets().sendIComponentText(1156, 128, "Sorcerer's Tower");
		player.getPackets().sendIComponentText(1156, 129,
				"");
		player.getPackets().sendIComponentText(1156, 242, "Teleport!");
		player.getPackets().sendIComponentText(1156, 125, "Al' Kharid Sparkling Pool");
		player.getPackets().sendIComponentText(1156, 126,
				"");
		player.getPackets().sendIComponentText(1156, 260, "Teleport!");
		player.getPackets().sendIComponentText(1156, 143, "Battlefield");
		player.getPackets().sendIComponentText(1156, 144,
				"");
		player.getPackets().sendIComponentText(1156, 278, "Teleport!");
		player.getPackets().sendIComponentText(1156, 146, "Whirlpool");
		player.getPackets().sendIComponentText(1156, 147,
				"");
		player.getPackets().sendIComponentText(1156, 284, "Teleport!");
		player.getPackets().sendIComponentText(1156, 119, "Ardougne Bridge");
		player.getPackets().sendIComponentText(1156, 120,
				"");
		player.getPackets().sendIComponentText(1156, 230, "Teleport!");
		player.getPackets().sendIComponentText(1156, 131, "ZMI Altar");
		player.getPackets().sendIComponentText(1156, 132,
				"");
		player.getPackets().sendIComponentText(1156, 242, "Teleport!");
		player.getPackets().sendIComponentText(1156, 140, "Sophanem Bridge");
		player.getPackets().sendIComponentText(1156, 141,
				"");
		player.getPackets().sendIComponentText(1156, 224, "Teleport!");
		player.getPackets().sendIComponentText(1156, 149, "First Vexation Home");
		player.getPackets().sendIComponentText(1156, 150,
				"");
		player.getPackets().sendIComponentText(1156, 266, "Teleport!");
		player.getPackets().sendIComponentText(1156, 152,"Second Vexation Home");
		player.getPackets().sendIComponentText(1156, 153,
				"");
		player.getPackets().sendIComponentText(1156, 272, "Teleport!");
		player.getPackets().sendIComponentText(1156, 167, "N/A");
		player.getPackets().sendIComponentText(1156, 168,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 308, "Teleport!");
		player.getPackets().sendIComponentText(1156, 155, "N/A");
		player.getPackets().sendIComponentText(1156, 157,
				"Suggest for special prizes");
		player.getPackets().sendHideIComponent(1156, 156, true);
		player.getPackets().sendIComponentText(1156, 290, "Teleport!");
		player.getPackets().sendIComponentText(1156, 159, "N/A");
		player.getPackets().sendHideIComponent(1156, 160, true);
		player.getPackets().sendIComponentText(1156, 161,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 296, "Teleport!");
		player.getPackets().sendIComponentText(1156, 163, "N/A");
		player.getPackets().sendHideIComponent(1156, 164, true);
		player.getPackets().sendIComponentText(1156, 165,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 302, "Teleport!");
		player.getPackets().sendIComponentText(1156, 170,"N/A");
		player.getPackets().sendIComponentText(1156, 171,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 314, "Teleport!");
		player.getPackets().sendIComponentText(1156, 318,"N/A");
		player.getPackets().sendIComponentText(1156, 319,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 326, "Teleport!");	
	 }

	public static void handleFamousIIIIButtons(Player player, int componentId) {
	if (componentId == 88) {//"Juliet Balcony
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3166, 3433, 1));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 115) {//Varrock Museum
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1759, 4963, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 139) {//Chaos Altar Stars
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2142, 4845, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 112) {//Games Room
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2208, 4956, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 118) {//Temporary POH
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3804, 5726, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 136) {//Mage Arena
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3363, 3294, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 124) {//Wizard Tower Bridge
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3114, 3192, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 130) {//Sorcerer's Tower
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2702, 3405, 3));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 127) {//Al' Kharid Sparkling Pool
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3288, 3230, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 145) {//Battlefield
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2512, 3237, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 148) {//Whirlpool
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3512, 3518, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 121) {//Ardougne Bridge
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2599, 3296, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 133) {//ZMI Altar
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2454, 3232, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 142) {//Sophanem Bridge
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3274, 2785, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 151) {//First Vexation Home
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3433, 2894, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 154) {//Second Vexation Home
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3012, 3360, 0));
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIIteles = false;
	} else if (componentId == 169) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIIteles = false;
	} else if (componentId == 158) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIIteles = false;
	} else if (componentId == 162) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIIteles = false;
	} else if (componentId == 166) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIIteles = false;
	} else if (componentId == 172) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIIteles = false;
	} else if (componentId == 320) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIIteles = false;
	} 
}

	//Bright Categories
	public static void sendBrightCategories (Player player) {
		player.viewingagilityteles = false;
		player.viewingminigameteles = false;
		player.viewingmonsterteles = false;
		player.viewingrsmvactionlocs = false;
		player.viewingskillingteles = false;
		player.viewingtrainingteles = false;
		player.viewinglyreteles = true;
		player.viewingfamouscategories = false;
		player.viewingbrightcategories = true;
		player.viewingmoodycategories = false;
		player.viewingfairyringcategories = false;
		player.viewingextracategories = false;
		player.getInterfaceManager().sendInterface(1164);
		player.getPackets().sendIComponentText(1164, 51, "Categories");
		player.getPackets().sendIComponentText(1164, 25, "Main Menu");
		player.getPackets().sendIComponentText(1164, 27, "<shad=000000><col=FF0000>~ Bright Categories ~</col></shad>");		
		player.getPackets().sendIComponentText(1164, 17, "Bright IV");
		player.getPackets().sendIComponentText(1164, 19, "Bright III");
		player.getPackets().sendIComponentText(1164, 21, "Bright II");
		player.getPackets().sendIComponentText(1164, 23, "Bright I");
		
	}
	
	public static void sendBrightMenuOptions(Player player, int componentId) {
			player.viewingagilityteles = false;
			player.viewingminigameteles = false;
			player.viewingmonsterteles = false;
			player.viewingrsmvactionlocs = false;
			player.viewingskillingteles = false;
			player.viewingtrainingteles = false;
			player.viewinglyreteles = true;
			player.viewingfamouscategories = false;
			player.viewingbrightcategories = true;
			player.viewingmoodycategories = false;
			player.viewingfairyringcategories = false;
			player.viewingextracategories = false;
		if (componentId == 42) {//Bright Categories
			player.getInterfaceManager().sendInterface(1160);
			sendBrightCategories(player);
		} else if (componentId == 26) {//Main Menu
			sendMainMenu(player);
		} else if (componentId == 28) {//Bright I
			player.viewingfamousIteles = false;
			player.viewingfamousIIteles = false;
			player.viewingfamousIIIteles = false;
			player.viewingfamousIIIIteles = false;
			player.viewingbrightIteles = true;
			player.viewingbrightIIteles = false;
			player.viewingbrightIIIteles = false;
			player.viewingbrightIIIIteles = false;
			player.viewingmoodyIteles = false;
			player.viewingmoodyIIteles = false;
			player.viewingmoodyIIIteles = false;
			player.viewingmoodyIIIIteles = false;
			player.viewingfairyIteles = false;
			player.viewingfairyIIteles = false;
			player.viewingfairyIIIteles = false;
			player.viewingfairyIIIIteles = false;
			player.viewingextraIteles = false;
			player.viewingextraIIteles = false;
			player.viewingextraIIIteles = false;
			player.viewingextraIIIIteles = false;
			sendBrightITeleports(player);
		} else if (componentId == 29) {//Bright II
			player.viewingfamousIteles = false;
			player.viewingfamousIIteles = false;
			player.viewingfamousIIIteles = false;
			player.viewingfamousIIIIteles = false;
			player.viewingbrightIteles = false;
			player.viewingbrightIIteles = true;
			player.viewingbrightIIIteles = false;
			player.viewingbrightIIIIteles = false;
			player.viewingmoodyIteles = false;
			player.viewingmoodyIIteles = false;
			player.viewingmoodyIIIteles = false;
			player.viewingmoodyIIIIteles = false;
			player.viewingfairyIteles = false;
			player.viewingfairyIIteles = false;
			player.viewingfairyIIIteles = false;
			player.viewingfairyIIIIteles = false;
			player.viewingextraIteles = false;
			player.viewingextraIIteles = false;
			player.viewingextraIIIteles = false;
			player.viewingextraIIIIteles = false;
			sendBrightIITeleports(player);
		} else if (componentId == 30) {//Bright III
			player.viewingfamousIteles = false;
			player.viewingfamousIIteles = false;
			player.viewingfamousIIIteles = false;
			player.viewingfamousIIIIteles = false;
			player.viewingbrightIteles = false;
			player.viewingbrightIIteles = false;
			player.viewingbrightIIIteles = true;
			player.viewingbrightIIIIteles = false;
			player.viewingmoodyIteles = false;
			player.viewingmoodyIIteles = false;
			player.viewingmoodyIIIteles = false;
			player.viewingmoodyIIIIteles = false;
			player.viewingfairyIteles = false;
			player.viewingfairyIIteles = false;
			player.viewingfairyIIIteles = false;
			player.viewingfairyIIIIteles = false;
			player.viewingextraIteles = false;
			player.viewingextraIIteles = false;
			player.viewingextraIIIteles = false;
			player.viewingextraIIIIteles = false;
			sendBrightIIITeleports(player);
		} else if (componentId == 31) {//Bright IIII
			player.viewingfamousIteles = false;
			player.viewingfamousIIteles = false;
			player.viewingfamousIIIteles = false;
			player.viewingfamousIIIIteles = false;
			player.viewingbrightIteles = false;
			player.viewingbrightIIteles = false;
			player.viewingbrightIIIteles = false;
			player.viewingbrightIIIIteles = true;
			player.viewingmoodyIteles = false;
			player.viewingmoodyIIteles = false;
			player.viewingmoodyIIIteles = false;
			player.viewingmoodyIIIIteles = false;
			player.viewingfairyIteles = false;
			player.viewingfairyIIteles = false;
			player.viewingfairyIIIteles = false;
			player.viewingfairyIIIIteles = false;
			player.viewingextraIteles = false;
			player.viewingextraIIteles = false;
			player.viewingextraIIIteles = false;
			player.viewingextraIIIIteles = false;
			sendBrightIIIITeleports(player);
		}
	}
	
	public static void sendBrightITeleports (Player player) {
			player.viewingagilityteles = false;
			player.viewingminigameteles = false;
			player.viewingmonsterteles = false;
			player.viewingrsmvactionlocs = false;
			player.viewingskillingteles = false;
			player.viewingtrainingteles = false;	
			player.viewinglyreteles = true;
			player.viewingfamousIteles = false;
			player.viewingfamousIIteles = false;
			player.viewingfamousIIIteles = false;
			player.viewingfamousIIIIteles = false;
			player.getInterfaceManager().sendInterface(1156);
			player.getPackets().sendIComponentText(1156, 190,
					"Bright I Teleports");
			player.getPackets().sendIComponentText(1156, 108, "Mobilising Armies");
			player.getPackets().sendIComponentText(1156, 109,
					"Located in the south-eastern most region.");
			player.getPackets().sendIComponentText(1156, 90, "Teleport!");
			player.getPackets().sendIComponentText(1156, 113, "Feldip Hills");
			player.getPackets().sendIComponentText(1156, 114,
					"Located near Mobilising Armies.");
			player.getPackets().sendIComponentText(1156, 206, "Teleport!");
			player.getPackets().sendIComponentText(1156, 137, "Soul Wars");
			player.getPackets().sendIComponentText(1156, 138,
					"A minigame where zeals are rewarded.");
			player.getPackets().sendIComponentText(1156, 254, "Teleport!");
			player.getPackets().sendIComponentText(1156, 110, "Brimhaven");
			player.getPackets().sendIComponentText(1156, 111,
					"The city known for the most popular housing location.");
			player.getPackets().sendIComponentText(1156, 200, "Teleport!");
			player.getPackets().sendIComponentText(1156, 116, "Mos Le' Harmless");
			player.getPackets().sendIComponentText(1156, 117,
					"Used for a quest to obtian prayer book.");
			player.getPackets().sendIComponentText(1156, 212, "Teleport!");
			player.getPackets().sendIComponentText(1156, 134,"Sand Quarry");
			player.getPackets().sendIComponentText(1156, 135,
					"A decent mining location for minors.");
			player.getPackets().sendIComponentText(1156, 248, "Teleport!");
			player.getPackets().sendIComponentText(1156, 122, "Neitiznot");
			player.getPackets().sendIComponentText(1156, 123,
					"Right next to the moody Jatizso.");
			player.getPackets().sendIComponentText(1156, 218, "Teleport!");
			player.getPackets().sendIComponentText(1156, 128, "Mage Arena");
			player.getPackets().sendIComponentText(1156, 129,
					"Located above the Duel Arena.");
			player.getPackets().sendIComponentText(1156, 242, "Teleport!");
			player.getPackets().sendIComponentText(1156, 125, "Swaying Tree");
			player.getPackets().sendIComponentText(1156, 126,
					"Located in the woods of Relleka.");
			player.getPackets().sendIComponentText(1156, 260, "Teleport!");
			player.getPackets().sendIComponentText(1156, 143, "Eagle's Peak");
			player.getPackets().sendIComponentText(1156, 144,
					"Located above Arandar.");
			player.getPackets().sendIComponentText(1156, 278, "Teleport!");
			player.getPackets().sendIComponentText(1156, 146, "Oo'glog");
			player.getPackets().sendIComponentText(1156, 147,
					"East of Mobilising Armies.");
			player.getPackets().sendIComponentText(1156, 284, "Teleport!");
			player.getPackets().sendIComponentText(1156, 119, "Shilo Village");
			player.getPackets().sendIComponentText(1156, 120,
					"Located in Karamja.");
			player.getPackets().sendIComponentText(1156, 230, "Teleport!");
			player.getPackets().sendIComponentText(1156, 131, "Sophanem");
			player.getPackets().sendIComponentText(1156, 132,
					"The first official home for Vexation.");
			player.getPackets().sendIComponentText(1156, 242, "Teleport!");
			player.getPackets().sendIComponentText(1156, 140, "	Catherby Abbey");
			player.getPackets().sendIComponentText(1156, 141,
					"Located in the south-eastern most region of Al-Kharid.");
			player.getPackets().sendIComponentText(1156, 224, "Teleport!");
			player.getPackets().sendIComponentText(1156, 149, "Digsite");
			player.getPackets().sendIComponentText(1156, 150,
					"Located east of Varrock.");
			player.getPackets().sendIComponentText(1156, 266, "Teleport!");
			player.getPackets().sendIComponentText(1156, 152,"Miscellenia");
			player.getPackets().sendIComponentText(1156, 142,
					"An island located above Relleka.");
			player.getPackets().sendIComponentText(1156, 272, "Teleport!");
			player.getPackets().sendIComponentText(1156, 167, "");
			player.getPackets().sendIComponentText(1156, 168,
					"");
			player.getPackets().sendIComponentText(1156, 308, "Teleport!");
			player.getPackets().sendIComponentText(1156, 155, "");
			player.getPackets().sendIComponentText(1156, 157,
					"");
			player.getPackets().sendHideIComponent(1156, 156, true);
			player.getPackets().sendIComponentText(1156, 290, "Teleport!");
			player.getPackets().sendIComponentText(1156, 159, "");
			player.getPackets().sendHideIComponent(1156, 160, true);
			player.getPackets().sendIComponentText(1156, 161,
					"");
			player.getPackets().sendIComponentText(1156, 296, "Teleport!");
			player.getPackets().sendIComponentText(1156, 163, "");
			player.getPackets().sendHideIComponent(1156, 164, true);
			player.getPackets().sendIComponentText(1156, 165,
					"");
			player.getPackets().sendIComponentText(1156, 302, "Teleport!");
			player.getPackets().sendIComponentText(1156, 170,"");
			player.getPackets().sendIComponentText(1156, 171,
					"");
			player.getPackets().sendIComponentText(1156, 314, "Teleport!");
			player.getPackets().sendIComponentText(1156, 318,"");
			player.getPackets().sendIComponentText(1156, 319,
					"");
			player.getPackets().sendIComponentText(1156, 326, "Teleport!");	
		 }

	public static void handleBrightIButtons(Player player, int componentId) {
		if (componentId == 88) {//Clocktower Fire
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2609, 9639, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 115) {//Ruins of Uzer
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3552, 4964, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 139) {//Underground Pass
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2447, 3315, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 112) {//Ardougne Gallow
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2524, 3305, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 118) {//H.A.M Lair
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3160, 9634, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 136) {//Map Table
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1693, 5469, 2));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 124) {//KillerWatt Clouds
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2659, 5216, 2));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 130) {//Strange Creatures
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3228, 9526, 2));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 127) {//Lighthouse
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2508, 3645, 2));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 145) {//Guthix Circle
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2540, 5772, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 148) {//Arandar
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2347, 3301, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 121) {//Ruins of Ullek
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3409, 2800, 1));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 133) {//Lunar Mine
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2339, 10337, 2));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 142) {//Ritual of Mahjarrat
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2887, 3864, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 151) {//Seers Rooftop
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2701, 3472, 3));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 154) {//Old Lumbridge
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2340, 5719, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 169) {//Blue Moon Inn
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3222, 3399, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 158) {//Edgveille Bar
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3079, 3442, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 162) {//Bandos Throne Room Portal
	    	Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2320, 4242, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 166) {//Fishing Colony
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1501, 4314, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 172) {//Underground Pass Temple
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2146, 4648, 1));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} else if (componentId == 320) {//Sorcerer's Garden Fountain
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2913, 5471, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIteles = false;
		} 
	}
	
	public static void sendBrightIITeleports (Player player) {
		player.viewingagilityteles = false;
		player.viewingminigameteles = false;
		player.viewingmonsterteles = false;
		player.viewingrsmvactionlocs = false;
		player.viewingskillingteles = false;
		player.viewingtrainingteles = false;	
		player.viewinglyreteles = true;
		player.viewingfamousIteles = false;
		player.viewingfamousIIteles = true;
		player.viewingfamousIIIteles = false;
		player.viewingfamousIIIIteles = false;
		player.getInterfaceManager().sendInterface(1156);
		player.getPackets().sendIComponentText(1156, 190,
				"Bright II Teleports");
		player.getPackets().sendIComponentText(1156, 108, "Railroad");
		player.getPackets().sendIComponentText(1156, 109,
				"'Frozen in the lights of a locomotive'");
		player.getPackets().sendIComponentText(1156, 90, "Teleport!");
		player.getPackets().sendIComponentText(1156, 113, "Tower of Life Underground");
		player.getPackets().sendIComponentText(1156, 114,
				"'To make a final remedy.'");
		player.getPackets().sendIComponentText(1156, 206, "Teleport!");
		player.getPackets().sendIComponentText(1156, 137, "Abyssal Rift");
		player.getPackets().sendIComponentText(1156, 138,
				"Known as the road more or less travelled by.");
		player.getPackets().sendIComponentText(1156, 254, "Teleport!");
		player.getPackets().sendIComponentText(1156, 110, "Baxatorian Falls");
		player.getPackets().sendIComponentText(1156, 111,
				"'Tell me your real name...!'");
		player.getPackets().sendIComponentText(1156, 200, "Teleport!");
		player.getPackets().sendIComponentText(1156, 116, "Legendary Magic Trees");
		player.getPackets().sendIComponentText(1156, 117,
				"'I'm a slave...!'");
		player.getPackets().sendIComponentText(1156, 212, "Teleport!");
		player.getPackets().sendIComponentText(1156, 134,"Lletya Overview");
		player.getPackets().sendIComponentText(1156, 135,
				"'Am I a little sick?'");
		player.getPackets().sendIComponentText(1156, 248, "Teleport!");
		player.getPackets().sendIComponentText(1156, 122, "Clan Citadel Dancefloor");
		player.getPackets().sendIComponentText(1156, 123,
				"'Everyday I'm shufflin'...'");
		player.getPackets().sendIComponentText(1156, 218, "Teleport!");
		player.getPackets().sendIComponentText(1156, 128, "Underground Pass Well");
		player.getPackets().sendIComponentText(1156, 129,
				"'What can protect me from the past?'");
		player.getPackets().sendIComponentText(1156, 242, "Teleport!");
		player.getPackets().sendIComponentText(1156, 125, "Catharede Abbey Bell");
		player.getPackets().sendIComponentText(1156, 126,
				"''");
		player.getPackets().sendIComponentText(1156, 260, "Teleport!");
		player.getPackets().sendIComponentText(1156, 143, "Ecotfongus Underground");
		player.getPackets().sendIComponentText(1156, 144,
				"'One more time, I'm on my knees!'");
		player.getPackets().sendIComponentText(1156, 278, "Teleport!");
		player.getPackets().sendIComponentText(1156, 146, "Chaos Altar(Wilderness)");
		player.getPackets().sendIComponentText(1156, 147,
				"'They tore me apart!'");
		player.getPackets().sendIComponentText(1156, 284, "Teleport!");
		player.getPackets().sendIComponentText(1156, 119, "Varrock Rooftop");
		player.getPackets().sendIComponentText(1156, 120,
				"'We are the kings...!'");
		player.getPackets().sendIComponentText(1156, 230, "Teleport!");
		player.getPackets().sendIComponentText(1156, 131, "Camelot Pillars");
		player.getPackets().sendIComponentText(1156, 132,
				"'Will I become my worst enemy...?'");
		player.getPackets().sendIComponentText(1156, 242, "Teleport!");
		player.getPackets().sendIComponentText(1156, 140, "Ice Mountain");
		player.getPackets().sendIComponentText(1156, 141,
				"'And watch that Colorado Sunrise'");
		player.getPackets().sendIComponentText(1156, 224, "Teleport!");
		player.getPackets().sendIComponentText(1156, 149, "VWB");
		player.getPackets().sendIComponentText(1156, 150,
				"Basically Neverland, but as a bank.");
		player.getPackets().sendIComponentText(1156, 266, "Teleport!");
		player.getPackets().sendIComponentText(1156, 152,"Witchaven Pillars");
		player.getPackets().sendIComponentText(1156, 142,
				"'You know to keep your hopes.");
		player.getPackets().sendIComponentText(1156, 272, "Teleport!");
		player.getPackets().sendIComponentText(1156, 167, "N/A");
		player.getPackets().sendIComponentText(1156, 168,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 308, "Teleport!");
		player.getPackets().sendIComponentText(1156, 155, "N/A");
		player.getPackets().sendIComponentText(1156, 157,
				"Suggest for special prizes");
		player.getPackets().sendHideIComponent(1156, 156, true);
		player.getPackets().sendIComponentText(1156, 290, "Teleport!");
		player.getPackets().sendIComponentText(1156, 159, "N/A");
		player.getPackets().sendHideIComponent(1156, 160, true);
		player.getPackets().sendIComponentText(1156, 161,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 296, "Teleport!");
		player.getPackets().sendIComponentText(1156, 163, "N/A");
		player.getPackets().sendHideIComponent(1156, 164, true);
		player.getPackets().sendIComponentText(1156, 165,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 302, "Teleport!");
		player.getPackets().sendIComponentText(1156, 170,"N/A");
		player.getPackets().sendIComponentText(1156, 171,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 314, "Teleport!");
		player.getPackets().sendIComponentText(1156, 318,"N/A");
		player.getPackets().sendIComponentText(1156, 319,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 326, "Teleport!");	
	 }

	public static void handleBrightIIButtons(Player player, int componentId) {
	if (componentId == 88) {//Railroad
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2615, 3501, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 115) {//Tower Of Life Underground
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3040, 4390, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 139) {//Abysaal Rift
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3038, 4833, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 112) {//Baxatorian Falls
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2456, 4568, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 118) {//Legendary Magic Trees
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1300, 4588, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 136) {//Lletya Overview
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2343, 3172, 1));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 124) {//Clan Citadel Dancefloor
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(5376, 4714, 1));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 130) {//Underground Pass Well
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2342, 9621, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 127) {//Catharede Abbey Bell
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3450, 3178, 3));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 145) {//Ecotfongus Underground
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3683, 9888, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 148) {//Chaos Altar(Wilderness)
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3240, 3613, 0));
		player.getControlerManager().startControler("Wilderness");
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 121) {//Varrock Rooftop
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3212, 3467, 2));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 133) {//Camelot Pillars
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2780, 3515, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 142) {//Ice Mountain
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3003, 3470, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 151) {//VWB
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3185, 3440, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIteles = false;
	} else if (componentId == 154) {//Witchaven Pillars
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2697, 3283, 0));
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIteles = false;
	} else if (componentId == 169) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIteles = false;
	} else if (componentId == 158) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIteles = false;
	} else if (componentId == 162) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIteles = false;
	} else if (componentId == 166) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIteles = false;
	} else if (componentId == 172) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIteles = false;
	} else if (componentId == 320) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIteles = false;
	} 
}
	
	public static void sendBrightIIITeleports (Player player) {
		player.viewingagilityteles = false;
		player.viewingminigameteles = false;
		player.viewingmonsterteles = false;
		player.viewingrsmvactionlocs = false;
		player.viewingskillingteles = false;
		player.viewingtrainingteles = false;	
		player.viewinglyreteles = true;
		player.viewingfamousIteles = false;
		player.viewingfamousIIteles = false;
		player.viewingfamousIIIteles = true;
		player.viewingfamousIIIIteles = false;
		player.getInterfaceManager().sendInterface(1156);
		player.getPackets().sendIComponentText(1156, 190,
				"Bright III Teleports");
		player.getPackets().sendIComponentText(1156, 108, "Gift of Peace");
		player.getPackets().sendIComponentText(1156, 109,
				"'I couldn't face a life without your lights.'");
		player.getPackets().sendIComponentText(1156, 90, "Teleport!");
		player.getPackets().sendIComponentText(1156, 113, "Grain of Plenty");
		player.getPackets().sendIComponentText(1156, 114,
				"'What can I do?'");
		player.getPackets().sendIComponentText(1156, 206, "Teleport!");
		player.getPackets().sendIComponentText(1156, 137, "Box of Health");
		player.getPackets().sendIComponentText(1156, 138,
				"");
		player.getPackets().sendIComponentText(1156, 254, "Teleport!");
		player.getPackets().sendIComponentText(1156, 110, "Cradle of Life");
		player.getPackets().sendIComponentText(1156, 111,
				"'Sym-pa...-thy!'");
		player.getPackets().sendIComponentText(1156, 200, "Teleport!");
		player.getPackets().sendIComponentText(1156, 116, "Gu'Tanoth Bridge");
		player.getPackets().sendIComponentText(1156, 117,
				"");
		player.getPackets().sendIComponentText(1156, 212, "Teleport!");
		player.getPackets().sendIComponentText(1156, 134,"Mudskipper Point");
		player.getPackets().sendIComponentText(1156, 135,
				"");
		player.getPackets().sendIComponentText(1156, 248, "Teleport!");
		player.getPackets().sendIComponentText(1156, 122, "Port Sarim Hill");
		player.getPackets().sendIComponentText(1156, 123,
				"");
		player.getPackets().sendIComponentText(1156, 218, "Teleport!");
		player.getPackets().sendIComponentText(1156, 128, "Falador High Point");
		player.getPackets().sendIComponentText(1156, 129,
				"");
		player.getPackets().sendIComponentText(1156, 242, "Teleport!");
		player.getPackets().sendIComponentText(1156, 125, "Party Room");
		player.getPackets().sendIComponentText(1156, 126,
				"'Aint nobody dope as me.'");
		player.getPackets().sendIComponentText(1156, 260, "Teleport!");
		player.getPackets().sendIComponentText(1156, 143, "Blue Moon Inn");
		player.getPackets().sendIComponentText(1156, 144,
				"'I gave you everything to die with a smile.'");
		player.getPackets().sendIComponentText(1156, 278, "Teleport!");
		player.getPackets().sendIComponentText(1156, 146, "Edgeville School");
		player.getPackets().sendIComponentText(1156, 147,
				"'Give my props to the speak and spell!'");
		player.getPackets().sendIComponentText(1156, 284, "Teleport!");
		player.getPackets().sendIComponentText(1156, 119, "Monuments");
		player.getPackets().sendIComponentText(1156, 120,
				"'You have a grand stand seat here''");
		player.getPackets().sendIComponentText(1156, 230, "Teleport!");
		player.getPackets().sendIComponentText(1156, 131, "Desert Pyramid");
		player.getPackets().sendIComponentText(1156, 132,
				"'Be-hind...closed...doors...!'");
		player.getPackets().sendIComponentText(1156, 242, "Teleport!");
		player.getPackets().sendIComponentText(1156, 140, "Marko's Spot(Wilderness)");
		player.getPackets().sendIComponentText(1156, 141,
				"Hai Marko <3");
		player.getPackets().sendIComponentText(1156, 224, "Teleport!");
		player.getPackets().sendIComponentText(1156, 149, "VWB");
		player.getPackets().sendIComponentText(1156, 150,
				"Neverland but as a bank.");
		player.getPackets().sendIComponentText(1156, 266, "Teleport!");
		player.getPackets().sendIComponentText(1156, 152,"Isadafar Butterflies");
		player.getPackets().sendIComponentText(1156, 142,
				"");
		player.getPackets().sendIComponentText(1156, 272, "Teleport!");
		player.getPackets().sendIComponentText(1156, 167, "N/A");
		player.getPackets().sendIComponentText(1156, 168,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 308, "Teleport!");
		player.getPackets().sendIComponentText(1156, 155, "N/A");
		player.getPackets().sendIComponentText(1156, 157,
				"Suggest for special prizes");
		player.getPackets().sendHideIComponent(1156, 156, true);
		player.getPackets().sendIComponentText(1156, 290, "Teleport!");
		player.getPackets().sendIComponentText(1156, 159, "N/A");
		player.getPackets().sendHideIComponent(1156, 160, true);
		player.getPackets().sendIComponentText(1156, 161,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 296, "Teleport!");
		player.getPackets().sendIComponentText(1156, 163, "N/A");
		player.getPackets().sendHideIComponent(1156, 164, true);
		player.getPackets().sendIComponentText(1156, 165,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 302, "Teleport!");
		player.getPackets().sendIComponentText(1156, 170,"N/A");
		player.getPackets().sendIComponentText(1156, 171,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 314, "Teleport!");
		player.getPackets().sendIComponentText(1156, 318,"N/A");
		player.getPackets().sendIComponentText(1156, 319,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 326, "Teleport!");	
	 }

	public static void handleBrightIIIButtons(Player player, int componentId) {
	if (componentId == 88) {//"Gift of Peace
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1908, 5222, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 115) {//Grain of Plenty
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2023, 5215, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 139) {//Box of Health
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2145, 5280, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 112) {//Cradle of Life
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2345, 5214, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 118) {//Gu'Tanoth Bridge
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2536, 3012, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 136) {//Mudskipper Point
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2994, 3114, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 124) {//Port Sarim Hill
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3007, 3144, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 130) {//Falador High Point
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2995, 3342, 3));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 127) {//Party Room
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3046, 3377, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 145) {//Blue Moon Inn
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3222, 3402, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 148) {//Edgeville School)
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3081, 3456, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 121) {//Monuments
		player.getPackets().sendGameMessage("<col=FF0000>You have unlocked a new music track: Lolita's Medicine");
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3420, 9887, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 133) {//Desert Pyramid
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3233, 2903, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 142) {//Marko's Spot(Wilderness)
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3099, 3624, 0));
		player.getControlerManager().startControler("Wilderness");
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 151) {//Isadafar Butterflies
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2245, 3183, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 154) {//Karamja Volcano
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2845, 3168, 0));
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} else if (componentId == 169) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} else if (componentId == 158) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} else if (componentId == 162) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} else if (componentId == 166) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} else if (componentId == 172) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} else if (componentId == 320) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} 
}
	
	public static void sendBrightIIIITeleports (Player player) {
		player.viewingagilityteles = false;
		player.viewingminigameteles = false;
		player.viewingmonsterteles = false;
		player.viewingrsmvactionlocs = false;
		player.viewingskillingteles = false;
		player.viewingtrainingteles = false;	
		player.viewinglyreteles = true;
		player.viewingfamousIteles = false;
		player.viewingfamousIIteles = false;
		player.viewingfamousIIIteles = false;
		player.viewingfamousIIIIteles = true;
		player.getInterfaceManager().sendInterface(1156);
		player.getPackets().sendIComponentText(1156, 190,
				"Bright IIII Teleports");
		player.getPackets().sendIComponentText(1156, 108, "Juliet Balcony");
		player.getPackets().sendIComponentText(1156, 109,
				"");
		player.getPackets().sendIComponentText(1156, 90, "Teleport!");
		player.getPackets().sendIComponentText(1156, 113, "Varrock Museum");
		player.getPackets().sendIComponentText(1156, 114,
				"");
		player.getPackets().sendIComponentText(1156, 206, "Teleport!");
		player.getPackets().sendIComponentText(1156, 137, "Chaos Altar Stars");
		player.getPackets().sendIComponentText(1156, 138,
				"");
		player.getPackets().sendIComponentText(1156, 254, "Teleport!");
		player.getPackets().sendIComponentText(1156, 110, "Games Room");
		player.getPackets().sendIComponentText(1156, 111,
				"");
		player.getPackets().sendIComponentText(1156, 200, "Teleport!");
		player.getPackets().sendIComponentText(1156, 116, "Temporary POH");
		player.getPackets().sendIComponentText(1156, 117,
				"");
		player.getPackets().sendIComponentText(1156, 212, "Teleport!");
		player.getPackets().sendIComponentText(1156, 134,"Mage Arena");
		player.getPackets().sendIComponentText(1156, 135,
				"");
		player.getPackets().sendIComponentText(1156, 248, "Teleport!");
		player.getPackets().sendIComponentText(1156, 122, "Wizard Tower Bridge");
		player.getPackets().sendIComponentText(1156, 123,
				"");
		player.getPackets().sendIComponentText(1156, 218, "Teleport!");
		player.getPackets().sendIComponentText(1156, 128, "Sorcerer's Tower");
		player.getPackets().sendIComponentText(1156, 129,
				"");
		player.getPackets().sendIComponentText(1156, 242, "Teleport!");
		player.getPackets().sendIComponentText(1156, 125, "Al' Kharid Sparkling Pool");
		player.getPackets().sendIComponentText(1156, 126,
				"");
		player.getPackets().sendIComponentText(1156, 260, "Teleport!");
		player.getPackets().sendIComponentText(1156, 143, "Battlefield");
		player.getPackets().sendIComponentText(1156, 144,
				"");
		player.getPackets().sendIComponentText(1156, 278, "Teleport!");
		player.getPackets().sendIComponentText(1156, 146, "Whirlpool");
		player.getPackets().sendIComponentText(1156, 147,
				"");
		player.getPackets().sendIComponentText(1156, 284, "Teleport!");
		player.getPackets().sendIComponentText(1156, 119, "Ardougne Bridge");
		player.getPackets().sendIComponentText(1156, 120,
				"");
		player.getPackets().sendIComponentText(1156, 230, "Teleport!");
		player.getPackets().sendIComponentText(1156, 131, "ZMI Altar");
		player.getPackets().sendIComponentText(1156, 132,
				"");
		player.getPackets().sendIComponentText(1156, 242, "Teleport!");
		player.getPackets().sendIComponentText(1156, 140, "Sophanem Bridge");
		player.getPackets().sendIComponentText(1156, 141,
				"");
		player.getPackets().sendIComponentText(1156, 224, "Teleport!");
		player.getPackets().sendIComponentText(1156, 149, "First Vexation Home");
		player.getPackets().sendIComponentText(1156, 150,
				"");
		player.getPackets().sendIComponentText(1156, 266, "Teleport!");
		player.getPackets().sendIComponentText(1156, 152,"Second Vexation Home");
		player.getPackets().sendIComponentText(1156, 153,
				"");
		player.getPackets().sendIComponentText(1156, 272, "Teleport!");
		player.getPackets().sendIComponentText(1156, 167, "N/A");
		player.getPackets().sendIComponentText(1156, 168,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 308, "Teleport!");
		player.getPackets().sendIComponentText(1156, 155, "N/A");
		player.getPackets().sendIComponentText(1156, 157,
				"Suggest for special prizes");
		player.getPackets().sendHideIComponent(1156, 156, true);
		player.getPackets().sendIComponentText(1156, 290, "Teleport!");
		player.getPackets().sendIComponentText(1156, 159, "N/A");
		player.getPackets().sendHideIComponent(1156, 160, true);
		player.getPackets().sendIComponentText(1156, 161,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 296, "Teleport!");
		player.getPackets().sendIComponentText(1156, 163, "N/A");
		player.getPackets().sendHideIComponent(1156, 164, true);
		player.getPackets().sendIComponentText(1156, 165,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 302, "Teleport!");
		player.getPackets().sendIComponentText(1156, 170,"N/A");
		player.getPackets().sendIComponentText(1156, 171,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 314, "Teleport!");
		player.getPackets().sendIComponentText(1156, 318,"N/A");
		player.getPackets().sendIComponentText(1156, 319,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 326, "Teleport!");	
	 }

	public static void handleBrightIIIIButtons(Player player, int componentId) {
	if (componentId == 88) {//"Juliet Balcony
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3166, 3433, 1));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 115) {//Varrock Museum
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1759, 4963, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 139) {//Chaos Altar Stars
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2142, 4845, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 112) {//Games Room
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2208, 4956, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 118) {//Temporary POH
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3804, 5726, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 136) {//Mage Arena
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3363, 3294, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 124) {//Wizard Tower Bridge
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3114, 3192, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 130) {//Sorcerer's Tower
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2702, 3405, 3));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 127) {//Al' Kharid Sparkling Pool
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3288, 3230, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 145) {//Battlefield
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2512, 3237, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 148) {//Whirlpool
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3512, 3518, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 121) {//Ardougne Bridge
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2599, 3296, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 133) {//ZMI Altar
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2454, 3232, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 142) {//Sophanem Bridge
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3274, 2785, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 151) {//First Vexation Home
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3433, 2894, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIIteles = false;
	} else if (componentId == 154) {//Second Vexation Home
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3012, 3360, 0));
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIIteles = false;
	} else if (componentId == 169) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIIteles = false;
	} else if (componentId == 158) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIIteles = false;
	} else if (componentId == 162) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIIteles = false;
	} else if (componentId == 166) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIIteles = false;
	} else if (componentId == 172) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIIteles = false;
	} else if (componentId == 320) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIIteles = false;
	} 
}

	//Moody Categories
	/*public static void sendMoodyCategories (Player player) {
			player.viewingagilityteles = false;
			player.viewingminigameteles = false;
			player.viewingmonsterteles = false;
			player.viewingrsmvactionlocs = false;
			player.viewingskillingteles = false;
			player.viewingtrainingteles = false;
			player.viewinglyreteles = true;
			player.viewingfamouscategories = false;
			player.viewingbrightcategories = true;
			player.viewingmoodycategories = false;
			player.viewingfairyringcategories = false;
			player.viewingextracategories = false;
			player.getInterfaceManager().sendInterface(1164);
			player.getPackets().sendIComponentText(1164, 51, "Categories");
			player.getPackets().sendIComponentText(1164, 25, "Main Menu");
			player.getPackets().sendIComponentText(1164, 27, "<shad=000000><col=FF0000>~ Moody Categories ~</col></shad>");		
			player.getPackets().sendIComponentText(1164, 17, "Moody IIII");
			player.getPackets().sendIComponentText(1164, 19, "Moody III");
			player.getPackets().sendIComponentText(1164, 21, "Moody II");
			player.getPackets().sendIComponentText(1164, 23, "Moody I");
			
		}
		
	public static void sendMoodyMenuOptions(Player player, int componentId) {
				player.viewingagilityteles = false;
				player.viewingminigameteles = false;
				player.viewingmonsterteles = false;
				player.viewingrsmvactionlocs = false;
				player.viewingskillingteles = false;
				player.viewingtrainingteles = false;
				player.viewinglyreteles = true;
				player.viewingfamouscategories = false;
				player.viewingbrightcategories = true;
				player.viewingmoodycategories = false;
				player.viewingfairyringcategories = false;
				player.viewingextracategories = false;
			if (componentId == 42) {//Moody Categories
				player.getInterfaceManager().sendInterface(1160);
				sendMoodyCategories(player);
			} else if (componentId == 26) {//Main Menu
				sendMainMenu(player);
			} else if (componentId == 28) {//Moody I
				player.viewingfamousIteles = false;
				player.viewingfamousIIteles = false;
				player.viewingfamousIIIteles = false;
				player.viewingfamousIIIIteles = false;
				player.viewingbrightIteles = true;
				player.viewingbrightIIteles = false;
				player.viewingbrightIIIteles = false;
				player.viewingbrightIIIIteles = false;
				player.viewingmoodyIteles = false;
				player.viewingmoodyIIteles = false;
				player.viewingmoodyIIIteles = false;
				player.viewingmoodyIIIIteles = false;
				player.viewingfairyIteles = false;
				player.viewingfairyIIteles = false;
				player.viewingfairyIIIteles = false;
				player.viewingfairyIIIIteles = false;
				player.viewingextraIteles = false;
				player.viewingextraIIteles = false;
				player.viewingextraIIIteles = false;
				player.viewingextraIIIIteles = false;
				sendMoodyITeleports(player);
			} else if (componentId == 29) {//Moody II
				player.viewingfamousIteles = false;
				player.viewingfamousIIteles = false;
				player.viewingfamousIIIteles = false;
				player.viewingfamousIIIIteles = false;
				player.viewingbrightIteles = false;
				player.viewingbrightIIteles = true;
				player.viewingbrightIIIteles = false;
				player.viewingbrightIIIIteles = false;
				player.viewingmoodyIteles = false;
				player.viewingmoodyIIteles = false;
				player.viewingmoodyIIIteles = false;
				player.viewingmoodyIIIIteles = false;
				player.viewingfairyIteles = false;
				player.viewingfairyIIteles = false;
				player.viewingfairyIIIteles = false;
				player.viewingfairyIIIIteles = false;
				player.viewingextraIteles = false;
				player.viewingextraIIteles = false;
				player.viewingextraIIIteles = false;
				player.viewingextraIIIIteles = false;
				sendMoodyIITeleports(player);
			} else if (componentId == 30) {//Moody III
				player.viewingfamousIteles = false;
				player.viewingfamousIIteles = false;
				player.viewingfamousIIIteles = false;
				player.viewingfamousIIIIteles = false;
				player.viewingbrightIteles = false;
				player.viewingbrightIIteles = false;
				player.viewingbrightIIIteles = true;
				player.viewingbrightIIIIteles = false;
				player.viewingmoodyIteles = false;
				player.viewingmoodyIIteles = false;
				player.viewingmoodyIIIteles = false;
				player.viewingmoodyIIIIteles = false;
				player.viewingfairyIteles = false;
				player.viewingfairyIIteles = false;
				player.viewingfairyIIIteles = false;
				player.viewingfairyIIIIteles = false;
				player.viewingextraIteles = false;
				player.viewingextraIIteles = false;
				player.viewingextraIIIteles = false;
				player.viewingextraIIIIteles = false;
				sendMoodyIIITeleports(player);
			} else if (componentId == 31) {//Moody IIII
				player.viewingfamousIteles = false;
				player.viewingfamousIIteles = false;
				player.viewingfamousIIIteles = false;
				player.viewingfamousIIIIteles = false;
				player.viewingbrightIteles = false;
				player.viewingbrightIIteles = false;
				player.viewingbrightIIIteles = false;
				player.viewingbrightIIIIteles = true;
				player.viewingmoodyIteles = false;
				player.viewingmoodyIIteles = false;
				player.viewingmoodyIIIteles = false;
				player.viewingmoodyIIIIteles = false;
				player.viewingfairyIteles = false;
				player.viewingfairyIIteles = false;
				player.viewingfairyIIIteles = false;
				player.viewingfairyIIIIteles = false;
				player.viewingextraIteles = false;
				player.viewingextraIIteles = false;
				player.viewingextraIIIteles = false;
				player.viewingextraIIIIteles = false;
				sendMoodyIIIITeleports(player);
			}
		}
		
	public static void sendMoodyITeleports (Player player) {
				player.viewingagilityteles = false;
				player.viewingminigameteles = false;
				player.viewingmonsterteles = false;
				player.viewingrsmvactionlocs = false;
				player.viewingskillingteles = false;
				player.viewingtrainingteles = false;	
				player.viewinglyreteles = true;
				player.viewingfamousIteles = true;
				player.viewingfamousIIteles = false;
				player.viewingfamousIIIteles = false;
				player.viewingfamousIIIIteles = false;
				player.getInterfaceManager().sendInterface(1156);
				player.getPackets().sendIComponentText(1156, 190,
						"Moody I Teleports");
				player.getPackets().sendIComponentText(1156, 108, "Clocktower Fire");
				player.getPackets().sendIComponentText(1156, 109,
						"This place is literally on fire.");
				player.getPackets().sendIComponentText(1156, 90, "Teleport!");
				player.getPackets().sendIComponentText(1156, 113, "Ruinz oF Uzer");
				player.getPackets().sendIComponentText(1156, 114,
						"Best place to play the Charlie Charlie challenge.");
				player.getPackets().sendIComponentText(1156, 206, "Teleport!");
				player.getPackets().sendIComponentText(1156, 137, "Underground Pass");
				player.getPackets().sendIComponentText(1156, 138,
						"'Follow me into the dirt.'");
				player.getPackets().sendIComponentText(1156, 254, "Teleport!");
				player.getPackets().sendIComponentText(1156, 110, "Ardougne Gallow");
				player.getPackets().sendIComponentText(1156, 111,
						"Ne-ver fall a-sleep!");
				player.getPackets().sendIComponentText(1156, 200, "Teleport!");
				player.getPackets().sendIComponentText(1156, 116, "H.A.M Lair");
				player.getPackets().sendIComponentText(1156, 117,
						"'I hear you brother...'");
				player.getPackets().sendIComponentText(1156, 212, "Teleport!");
				player.getPackets().sendIComponentText(1156, 134,"Map Table");
				player.getPackets().sendIComponentText(1156, 135,
						"Hunter, I think I found your dad.");
				player.getPackets().sendIComponentText(1156, 248, "Teleport!");
				player.getPackets().sendIComponentText(1156, 122, "Killer-Watt Clouds");
				player.getPackets().sendIComponentText(1156, 123,
						"'I remembered black skies'");
				player.getPackets().sendIComponentText(1156, 218, "Teleport!");
				player.getPackets().sendIComponentText(1156, 128, "Strange Creatures");
				player.getPackets().sendIComponentText(1156, 129,
						"'I'd like to make myself believe...'");
				player.getPackets().sendIComponentText(1156, 242, "Teleport!");
				player.getPackets().sendIComponentText(1156, 125, "Lighthouse");
				player.getPackets().sendIComponentText(1156, 126,
						"'You show the lights that stop me'");
				player.getPackets().sendIComponentText(1156, 260, "Teleport!");
				player.getPackets().sendIComponentText(1156, 143, "Guthix Circle");
				player.getPackets().sendIComponentText(1156, 144,
						"'To stop the pain inside and feel again...'");
				player.getPackets().sendIComponentText(1156, 278, "Teleport!");
				player.getPackets().sendIComponentText(1156, 146, "Arandar");
				player.getPackets().sendIComponentText(1156, 147,
						"'If you love me, won't you let me go'");
				player.getPackets().sendIComponentText(1156, 284, "Teleport!");
				player.getPackets().sendIComponentText(1156, 119, "Ruinz of Ullek");
				player.getPackets().sendIComponentText(1156, 120,
						"'To the edge, of the earth.'");
				player.getPackets().sendIComponentText(1156, 230, "Teleport!");
				player.getPackets().sendIComponentText(1156, 131, "Lunar Mine");
				player.getPackets().sendIComponentText(1156, 132,
						"'If you take it from me!'");
				player.getPackets().sendIComponentText(1156, 242, "Teleport!");
				player.getPackets().sendIComponentText(1156, 140, "Ritual of Mahajarrat");
				player.getPackets().sendIComponentText(1156, 141,
						"'Now tell me all a-bout your pain down to the detail...'");
				player.getPackets().sendIComponentText(1156, 224, "Teleport!");
				player.getPackets().sendIComponentText(1156, 149, "Seers Rooftop");
				player.getPackets().sendIComponentText(1156, 150,
						"'We see the stones, falling from the sky...!");
				player.getPackets().sendIComponentText(1156, 266, "Teleport!");
				player.getPackets().sendIComponentText(1156, 152,"Old Lumbridge");
				player.getPackets().sendIComponentText(1156, 142,
						"'I'm a genius with a headache.");
				player.getPackets().sendIComponentText(1156, 272, "Teleport!");
				player.getPackets().sendIComponentText(1156, 167, "Blue Moon Inn  <col=FF0000><shad=FF0000>NEW</shad></col>");
				player.getPackets().sendIComponentText(1156, 168,
						"'Ain't no rest for the wicked'");
				player.getPackets().sendIComponentText(1156, 308, "Teleport!");
				player.getPackets().sendIComponentText(1156, 155, "Edgeville Bar  <col=FF0000><shad=FF0000>NEW</shad></col>");
				player.getPackets().sendIComponentText(1156, 157,
						"");
				player.getPackets().sendHideIComponent(1156, 156, true);
				player.getPackets().sendIComponentText(1156, 290, "Teleport!");
				player.getPackets().sendIComponentText(1156, 159, "Bandos Throne Room Portal  <col=FF0000><shad=FF0000>NEW</shad></col>");
				player.getPackets().sendHideIComponent(1156, 160, true);
				player.getPackets().sendIComponentText(1156, 161,
						"'It begins with a dark glowing ember.'");
				player.getPackets().sendIComponentText(1156, 296, "Teleport!");
				player.getPackets().sendIComponentText(1156, 163, "Fishing Colony  <col=FF0000><shad=FF0000>NEW</shad></col>");
				player.getPackets().sendHideIComponent(1156, 164, true);
				player.getPackets().sendIComponentText(1156, 165,
						"Rune Factor 3");
				player.getPackets().sendIComponentText(1156, 302, "Teleport!");
				player.getPackets().sendIComponentText(1156, 170,"Underground Pass Temple  <col=FF0000><shad=FF0000>NEW</shad></col>");
				player.getPackets().sendIComponentText(1156, 171,
						"'As two hands open doors...!'");
				player.getPackets().sendIComponentText(1156, 314, "Teleport!");
				player.getPackets().sendIComponentText(1156, 318,"Sorcercer's Garden Fountain  <col=FF0000><shad=FF0000>NEW</shad></col>");
				player.getPackets().sendIComponentText(1156, 319,
						"Pedal away on a makeshift bike.");
				player.getPackets().sendIComponentText(1156, 326, "Teleport!");	
			 }

	public static void handleMoodyIButtons(Player player, int componentId) {
			if (componentId == 88) {//Clocktower Fire
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2609, 9639, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 115) {//Ruins of Uzer
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3552, 4964, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 139) {//Underground Pass
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2447, 3315, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 112) {//Ardougne Gallow
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2524, 3305, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 118) {//H.A.M Lair
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3160, 9634, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 136) {//Map Table
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1693, 5469, 2));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 124) {//KillerWatt Clouds
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2659, 5216, 2));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 130) {//Strange Creatures
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3228, 9526, 2));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 127) {//Lighthouse
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2508, 3645, 2));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 145) {//Guthix Circle
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2540, 5772, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 148) {//Arandar
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2347, 3301, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 121) {//Ruins of Ullek
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3409, 2800, 1));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 133) {//Lunar Mine
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2339, 10337, 2));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 142) {//Ritual of Mahjarrat
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2887, 3864, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 151) {//Seers Rooftop
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2701, 3472, 3));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 154) {//Old Lumbridge
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2340, 5719, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 169) {//Blue Moon Inn
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3222, 3399, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 158) {//Edgveille Bar
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3079, 3442, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 162) {//Bandos Throne Room Portal
		    	Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2320, 4242, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 166) {//Fishing Colony
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1501, 4314, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 172) {//Underground Pass Temple
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2146, 4648, 1));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 320) {//Sorcerer's Garden Fountain
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2913, 5471, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} 
		}
		
	public static void sendMoodyIITeleports (Player player) {
			player.viewingagilityteles = false;
			player.viewingminigameteles = false;
			player.viewingmonsterteles = false;
			player.viewingrsmvactionlocs = false;
			player.viewingskillingteles = false;
			player.viewingtrainingteles = false;	
			player.viewinglyreteles = true;
			player.viewingfamousIteles = false;
			player.viewingfamousIIteles = true;
			player.viewingfamousIIIteles = false;
			player.viewingfamousIIIIteles = false;
			player.getInterfaceManager().sendInterface(1156);
			player.getPackets().sendIComponentText(1156, 190,
					"Moody II Teleports");
			player.getPackets().sendIComponentText(1156, 108, "Railroad");
			player.getPackets().sendIComponentText(1156, 109,
					"'Frozen in the lights of a locomotive'");
			player.getPackets().sendIComponentText(1156, 90, "Teleport!");
			player.getPackets().sendIComponentText(1156, 113, "Tower of Life Underground");
			player.getPackets().sendIComponentText(1156, 114,
					"'To make a final remedy.'");
			player.getPackets().sendIComponentText(1156, 206, "Teleport!");
			player.getPackets().sendIComponentText(1156, 137, "Abyssal Rift");
			player.getPackets().sendIComponentText(1156, 138,
					"Known as the road more or less travelled by.");
			player.getPackets().sendIComponentText(1156, 254, "Teleport!");
			player.getPackets().sendIComponentText(1156, 110, "Baxatorian Falls");
			player.getPackets().sendIComponentText(1156, 111,
					"'Tell me your real name...!'");
			player.getPackets().sendIComponentText(1156, 200, "Teleport!");
			player.getPackets().sendIComponentText(1156, 116, "Legendary Magic Trees");
			player.getPackets().sendIComponentText(1156, 117,
					"'I'm a slave...!'");
			player.getPackets().sendIComponentText(1156, 212, "Teleport!");
			player.getPackets().sendIComponentText(1156, 134,"Lletya Overview");
			player.getPackets().sendIComponentText(1156, 135,
					"'Am I a little sick?'");
			player.getPackets().sendIComponentText(1156, 248, "Teleport!");
			player.getPackets().sendIComponentText(1156, 122, "Clan Citadel Dancefloor");
			player.getPackets().sendIComponentText(1156, 123,
					"'Everyday I'm shufflin'...'");
			player.getPackets().sendIComponentText(1156, 218, "Teleport!");
			player.getPackets().sendIComponentText(1156, 128, "Underground Pass Well");
			player.getPackets().sendIComponentText(1156, 129,
					"'What can protect me from the past?'");
			player.getPackets().sendIComponentText(1156, 242, "Teleport!");
			player.getPackets().sendIComponentText(1156, 125, "Catharede Abbey Bell");
			player.getPackets().sendIComponentText(1156, 126,
					"''");
			player.getPackets().sendIComponentText(1156, 260, "Teleport!");
			player.getPackets().sendIComponentText(1156, 143, "Ecotfongus Underground");
			player.getPackets().sendIComponentText(1156, 144,
					"'One more time, I'm on my knees!'");
			player.getPackets().sendIComponentText(1156, 278, "Teleport!");
			player.getPackets().sendIComponentText(1156, 146, "Chaos Altar(Wilderness)");
			player.getPackets().sendIComponentText(1156, 147,
					"'They tore me apart!'");
			player.getPackets().sendIComponentText(1156, 284, "Teleport!");
			player.getPackets().sendIComponentText(1156, 119, "Varrock Rooftop");
			player.getPackets().sendIComponentText(1156, 120,
					"'We are the kings...!'");
			player.getPackets().sendIComponentText(1156, 230, "Teleport!");
			player.getPackets().sendIComponentText(1156, 131, "Camelot Pillars");
			player.getPackets().sendIComponentText(1156, 132,
					"'Will I become my worst enemy...?'");
			player.getPackets().sendIComponentText(1156, 242, "Teleport!");
			player.getPackets().sendIComponentText(1156, 140, "Ice Mountain");
			player.getPackets().sendIComponentText(1156, 141,
					"'And watch that Colorado Sunrise'");
			player.getPackets().sendIComponentText(1156, 224, "Teleport!");
			player.getPackets().sendIComponentText(1156, 149, "VWB");
			player.getPackets().sendIComponentText(1156, 150,
					"Basically Neverland, but as a bank.");
			player.getPackets().sendIComponentText(1156, 266, "Teleport!");
			player.getPackets().sendIComponentText(1156, 152,"Witchaven Pillars");
			player.getPackets().sendIComponentText(1156, 142,
					"'You know to keep your hopes.");
			player.getPackets().sendIComponentText(1156, 272, "Teleport!");
			player.getPackets().sendIComponentText(1156, 167, "N/A");
			player.getPackets().sendIComponentText(1156, 168,
					"Suggest for special prizes");
			player.getPackets().sendIComponentText(1156, 308, "Teleport!");
			player.getPackets().sendIComponentText(1156, 155, "N/A");
			player.getPackets().sendIComponentText(1156, 157,
					"Suggest for special prizes");
			player.getPackets().sendHideIComponent(1156, 156, true);
			player.getPackets().sendIComponentText(1156, 290, "Teleport!");
			player.getPackets().sendIComponentText(1156, 159, "N/A");
			player.getPackets().sendHideIComponent(1156, 160, true);
			player.getPackets().sendIComponentText(1156, 161,
					"Suggest for special prizes");
			player.getPackets().sendIComponentText(1156, 296, "Teleport!");
			player.getPackets().sendIComponentText(1156, 163, "N/A");
			player.getPackets().sendHideIComponent(1156, 164, true);
			player.getPackets().sendIComponentText(1156, 165,
					"Suggest for special prizes");
			player.getPackets().sendIComponentText(1156, 302, "Teleport!");
			player.getPackets().sendIComponentText(1156, 170,"N/A");
			player.getPackets().sendIComponentText(1156, 171,
					"Suggest for special prizes");
			player.getPackets().sendIComponentText(1156, 314, "Teleport!");
			player.getPackets().sendIComponentText(1156, 318,"N/A");
			player.getPackets().sendIComponentText(1156, 319,
					"Suggest for special prizes");
			player.getPackets().sendIComponentText(1156, 326, "Teleport!");	
		 }

	public static void handleMoodyIIButtons(Player player, int componentId) {
		if (componentId == 88) {//Railroad
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2615, 3501, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 115) {//Tower Of Life Underground
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3040, 4390, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 139) {//Abysaal Rift
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3038, 4833, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 112) {//Baxatorian Falls
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2456, 4568, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 118) {//Legendary Magic Trees
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1300, 4588, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 136) {//Lletya Overview
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2343, 3172, 1));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 124) {//Clan Citadel Dancefloor
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(5376, 4714, 1));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 130) {//Underground Pass Well
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2342, 9621, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 127) {//Catharede Abbey Bell
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3450, 3178, 3));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 145) {//Ecotfongus Underground
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3683, 9888, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 148) {//Chaos Altar(Wilderness)
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3240, 3613, 0));
			player.getControlerManager().startControler("Wilderness");
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 121) {//Varrock Rooftop
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3212, 3467, 2));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 133) {//Camelot Pillars
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2780, 3515, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 142) {//Ice Mountain
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3003, 3470, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 151) {//VWB
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3185, 3440, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 154) {//Witchaven Pillars
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2697, 3283, 0));
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIteles = false;
		} else if (componentId == 169) {//
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIteles = false;
		} else if (componentId == 158) {//
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIteles = false;
		} else if (componentId == 162) {//
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIteles = false;
		} else if (componentId == 166) {//
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIteles = false;
		} else if (componentId == 172) {//
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIteles = false;
		} else if (componentId == 320) {//
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIteles = false;
		} 
	}
		
	public static void sendMoodyIIITeleports (Player player) {
		player.viewingagilityteles = false;
		player.viewingminigameteles = false;
		player.viewingmonsterteles = false;
		player.viewingrsmvactionlocs = false;
		player.viewingskillingteles = false;
		player.viewingtrainingteles = false;	
		player.viewinglyreteles = true;
		player.viewingfamousIteles = false;
		player.viewingfamousIIteles = false;
		player.viewingfamousIIIteles = true;
		player.viewingfamousIIIIteles = false;
		player.getInterfaceManager().sendInterface(1156);
		player.getPackets().sendIComponentText(1156, 190,
				"Moody III Teleports");
		player.getPackets().sendIComponentText(1156, 108, "Gift of Peace");
		player.getPackets().sendIComponentText(1156, 109,
				"'I couldn't face a life without your lights.'");
		player.getPackets().sendIComponentText(1156, 90, "Teleport!");
		player.getPackets().sendIComponentText(1156, 113, "Grain of Plenty");
		player.getPackets().sendIComponentText(1156, 114,
				"'What can I do?'");
		player.getPackets().sendIComponentText(1156, 206, "Teleport!");
		player.getPackets().sendIComponentText(1156, 137, "Box of Health");
		player.getPackets().sendIComponentText(1156, 138,
				"");
		player.getPackets().sendIComponentText(1156, 254, "Teleport!");
		player.getPackets().sendIComponentText(1156, 110, "Cradle of Life");
		player.getPackets().sendIComponentText(1156, 111,
				"'Sym-pa...-thy!'");
		player.getPackets().sendIComponentText(1156, 200, "Teleport!");
		player.getPackets().sendIComponentText(1156, 116, "Gu'Tanoth Bridge");
		player.getPackets().sendIComponentText(1156, 117,
				"");
		player.getPackets().sendIComponentText(1156, 212, "Teleport!");
		player.getPackets().sendIComponentText(1156, 134,"Mudskipper Point");
		player.getPackets().sendIComponentText(1156, 135,
				"");
		player.getPackets().sendIComponentText(1156, 248, "Teleport!");
		player.getPackets().sendIComponentText(1156, 122, "Port Sarim Hill");
		player.getPackets().sendIComponentText(1156, 123,
				"");
		player.getPackets().sendIComponentText(1156, 218, "Teleport!");
		player.getPackets().sendIComponentText(1156, 128, "Falador High Point");
		player.getPackets().sendIComponentText(1156, 129,
				"");
		player.getPackets().sendIComponentText(1156, 242, "Teleport!");
		player.getPackets().sendIComponentText(1156, 125, "Party Room");
		player.getPackets().sendIComponentText(1156, 126,
				"'Aint nobody dope as me.'");
		player.getPackets().sendIComponentText(1156, 260, "Teleport!");
		player.getPackets().sendIComponentText(1156, 143, "Blue Moon Inn");
		player.getPackets().sendIComponentText(1156, 144,
				"'I gave you everything to die with a smile.'");
		player.getPackets().sendIComponentText(1156, 278, "Teleport!");
		player.getPackets().sendIComponentText(1156, 146, "Edgeville School");
		player.getPackets().sendIComponentText(1156, 147,
				"'Give my props to the speak and spell!'");
		player.getPackets().sendIComponentText(1156, 284, "Teleport!");
		player.getPackets().sendIComponentText(1156, 119, "Monuments");
		player.getPackets().sendIComponentText(1156, 120,
				"'You have a grand stand seat here''");
		player.getPackets().sendIComponentText(1156, 230, "Teleport!");
		player.getPackets().sendIComponentText(1156, 131, "Desert Pyramid");
		player.getPackets().sendIComponentText(1156, 132,
				"'Be-hind...closed...doors...!'");
		player.getPackets().sendIComponentText(1156, 242, "Teleport!");
		player.getPackets().sendIComponentText(1156, 140, "Marko's Spot(Wilderness)");
		player.getPackets().sendIComponentText(1156, 141,
				"Hai Marko <3");
		player.getPackets().sendIComponentText(1156, 224, "Teleport!");
		player.getPackets().sendIComponentText(1156, 149, "VWB");
		player.getPackets().sendIComponentText(1156, 150,
				"Neverland but as a bank.");
		player.getPackets().sendIComponentText(1156, 266, "Teleport!");
		player.getPackets().sendIComponentText(1156, 152,"Isadafar Butterflies");
		player.getPackets().sendIComponentText(1156, 142,
				"");
		player.getPackets().sendIComponentText(1156, 272, "Teleport!");
		player.getPackets().sendIComponentText(1156, 167, "N/A");
		player.getPackets().sendIComponentText(1156, 168,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 308, "Teleport!");
		player.getPackets().sendIComponentText(1156, 155, "N/A");
		player.getPackets().sendIComponentText(1156, 157,
				"Suggest for special prizes");
		player.getPackets().sendHideIComponent(1156, 156, true);
		player.getPackets().sendIComponentText(1156, 290, "Teleport!");
		player.getPackets().sendIComponentText(1156, 159, "N/A");
		player.getPackets().sendHideIComponent(1156, 160, true);
		player.getPackets().sendIComponentText(1156, 161,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 296, "Teleport!");
		player.getPackets().sendIComponentText(1156, 163, "N/A");
		player.getPackets().sendHideIComponent(1156, 164, true);
		player.getPackets().sendIComponentText(1156, 165,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 302, "Teleport!");
		player.getPackets().sendIComponentText(1156, 170,"N/A");
		player.getPackets().sendIComponentText(1156, 171,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 314, "Teleport!");
		player.getPackets().sendIComponentText(1156, 318,"N/A");
		player.getPackets().sendIComponentText(1156, 319,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 326, "Teleport!");	
	 }

	public static void handleMoodyIIIButtons(Player player, int componentId) {
	if (componentId == 88) {//"Gift of Peace
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1908, 5222, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 115) {//Grain of Plenty
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2023, 5215, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 139) {//Box of Health
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2145, 5280, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 112) {//Cradle of Life
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2345, 5214, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 118) {//Gu'Tanoth Bridge
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2536, 3012, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 136) {//Mudskipper Point
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2994, 3114, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 124) {//Port Sarim Hill
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3007, 3144, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 130) {//Falador High Point
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2995, 3342, 3));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 127) {//Party Room
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3046, 3377, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 145) {//Blue Moon Inn
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3222, 3402, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 148) {//Edgeville School)
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3081, 3456, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 121) {//Monuments
		player.getPackets().sendGameMessage("<col=FF0000>You have unlocked a new music track: Lolita's Medicine");
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3420, 9887, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 133) {//Desert Pyramid
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3233, 2903, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 142) {//Marko's Spot(Wilderness)
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3099, 3624, 0));
		player.getControlerManager().startControler("Wilderness");
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 151) {//Isadafar Butterflies
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2245, 3183, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 154) {//Karamja Volcano
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2845, 3168, 0));
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} else if (componentId == 169) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} else if (componentId == 158) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} else if (componentId == 162) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} else if (componentId == 166) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} else if (componentId == 172) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} else if (componentId == 320) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} 
}
	
	public static void sendMoodyIIIITeleports (Player player) {
		player.viewingagilityteles = false;
		player.viewingminigameteles = false;
		player.viewingmonsterteles = false;
		player.viewingrsmvactionlocs = false;
		player.viewingskillingteles = false;
		player.viewingtrainingteles = false;	
		player.viewinglyreteles = true;
		player.viewingfamousIteles = false;
		player.viewingfamousIIteles = false;
		player.viewingfamousIIIteles = false;
		player.viewingfamousIIIIteles = true;
		player.getInterfaceManager().sendInterface(1156);
		player.getPackets().sendIComponentText(1156, 190,
				"Moody IIII Teleports");
		player.getPackets().sendIComponentText(1156, 108, "Juliet Balcony");
		player.getPackets().sendIComponentText(1156, 109,
				"");
		player.getPackets().sendIComponentText(1156, 90, "Teleport!");
		player.getPackets().sendIComponentText(1156, 113, "Varrock Museum");
		player.getPackets().sendIComponentText(1156, 114,
				"");
		player.getPackets().sendIComponentText(1156, 206, "Teleport!");
		player.getPackets().sendIComponentText(1156, 137, "Chaos Altar Stars");
		player.getPackets().sendIComponentText(1156, 138,
				"");
		player.getPackets().sendIComponentText(1156, 254, "Teleport!");
		player.getPackets().sendIComponentText(1156, 110, "Games Room");
		player.getPackets().sendIComponentText(1156, 111,
				"");
		player.getPackets().sendIComponentText(1156, 200, "Teleport!");
		player.getPackets().sendIComponentText(1156, 116, "Temporary POH");
		player.getPackets().sendIComponentText(1156, 117,
				"");
		player.getPackets().sendIComponentText(1156, 212, "Teleport!");
		player.getPackets().sendIComponentText(1156, 134,"Mage Arena");
		player.getPackets().sendIComponentText(1156, 135,
				"");
		player.getPackets().sendIComponentText(1156, 248, "Teleport!");
		player.getPackets().sendIComponentText(1156, 122, "Wizard Tower Bridge");
		player.getPackets().sendIComponentText(1156, 123,
				"");
		player.getPackets().sendIComponentText(1156, 218, "Teleport!");
		player.getPackets().sendIComponentText(1156, 128, "Sorcerer's Tower");
		player.getPackets().sendIComponentText(1156, 129,
				"");
		player.getPackets().sendIComponentText(1156, 242, "Teleport!");
		player.getPackets().sendIComponentText(1156, 125, "Al' Kharid Sparkling Pool");
		player.getPackets().sendIComponentText(1156, 126,
				"");
		player.getPackets().sendIComponentText(1156, 260, "Teleport!");
		player.getPackets().sendIComponentText(1156, 143, "Battlefield");
		player.getPackets().sendIComponentText(1156, 144,
				"");
		player.getPackets().sendIComponentText(1156, 278, "Teleport!");
		player.getPackets().sendIComponentText(1156, 146, "Whirlpool");
		player.getPackets().sendIComponentText(1156, 147,
				"");
		player.getPackets().sendIComponentText(1156, 284, "Teleport!");
		player.getPackets().sendIComponentText(1156, 119, "Ardougne Bridge");
		player.getPackets().sendIComponentText(1156, 120,
				"");
		player.getPackets().sendIComponentText(1156, 230, "Teleport!");
		player.getPackets().sendIComponentText(1156, 131, "ZMI Altar");
		player.getPackets().sendIComponentText(1156, 132,
				"");
		player.getPackets().sendIComponentText(1156, 242, "Teleport!");
		player.getPackets().sendIComponentText(1156, 140, "Sophanem Bridge");
		player.getPackets().sendIComponentText(1156, 141,
				"");
		player.getPackets().sendIComponentText(1156, 224, "Teleport!");
		player.getPackets().sendIComponentText(1156, 149, "First Vexation Home");
		player.getPackets().sendIComponentText(1156, 150,
				"");
		player.getPackets().sendIComponentText(1156, 266, "Teleport!");
		player.getPackets().sendIComponentText(1156, 152,"Second Vexation Home");
		player.getPackets().sendIComponentText(1156, 153,
				"");
		player.getPackets().sendIComponentText(1156, 272, "Teleport!");
		player.getPackets().sendIComponentText(1156, 167, "N/A");
		player.getPackets().sendIComponentText(1156, 168,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 308, "Teleport!");
		player.getPackets().sendIComponentText(1156, 155, "N/A");
		player.getPackets().sendIComponentText(1156, 157,
				"Suggest for special prizes");
		player.getPackets().sendHideIComponent(1156, 156, true);
		player.getPackets().sendIComponentText(1156, 290, "Teleport!");
		player.getPackets().sendIComponentText(1156, 159, "N/A");
		player.getPackets().sendHideIComponent(1156, 160, true);
		player.getPackets().sendIComponentText(1156, 161,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 296, "Teleport!");
		player.getPackets().sendIComponentText(1156, 163, "N/A");
		player.getPackets().sendHideIComponent(1156, 164, true);
		player.getPackets().sendIComponentText(1156, 165,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 302, "Teleport!");
		player.getPackets().sendIComponentText(1156, 170,"N/A");
		player.getPackets().sendIComponentText(1156, 171,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 314, "Teleport!");
		player.getPackets().sendIComponentText(1156, 318,"N/A");
		player.getPackets().sendIComponentText(1156, 319,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 326, "Teleport!");	
	 }

	public static void handleMoodyIIIIButtons(Player player, int componentId) {
		if (componentId == 88) {//"Juliet Balcony
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3166, 3433, 1));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 115) {//Varrock Museum
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1759, 4963, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 139) {//Chaos Altar Stars
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2142, 4845, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 112) {//Games Room
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2208, 4956, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 118) {//Temporary POH
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3804, 5726, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 136) {//Mage Arena
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3363, 3294, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 124) {//Wizard Tower Bridge
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3114, 3192, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 130) {//Sorcerer's Tower
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2702, 3405, 3));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 127) {//Al' Kharid Sparkling Pool
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3288, 3230, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 145) {//Battlefield
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2512, 3237, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 148) {//Whirlpool
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3512, 3518, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 121) {//Ardougne Bridge
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2599, 3296, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 133) {//ZMI Altar
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2454, 3232, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 142) {//Sophanem Bridge
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3274, 2785, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 151) {//First Vexation Home
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3433, 2894, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 154) {//Second Vexation Home
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3012, 3360, 0));
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIIIteles = false;
		} else if (componentId == 169) {//
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIIIteles = false;
		} else if (componentId == 158) {//
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIIIteles = false;
		} else if (componentId == 162) {//
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIIIteles = false;
		} else if (componentId == 166) {//
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIIIteles = false;
		} else if (componentId == 172) {//
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIIIteles = false;
		} else if (componentId == 320) {//
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIIIteles = false;
		} 
	}
	//Ring Categories
	
	public static void sendFairyRingCategories (Player player) {
		player.viewingagilityteles = false;
		player.viewingminigameteles = false;
		player.viewingmonsterteles = false;
		player.viewingrsmvactionlocs = false;
		player.viewingskillingteles = false;
		player.viewingtrainingteles = false;
		player.viewinglyreteles = true;
		player.viewingfamouscategories = false;
		player.viewingbrightcategories = true;
		player.viewingmoodycategories = false;
		player.viewingfairyringcategories = false;
		player.viewingextracategories = false;
		player.getInterfaceManager().sendInterface(1164);
		player.getPackets().sendIComponentText(1164, 51, "Categories");
		player.getPackets().sendIComponentText(1164, 25, "Main Menu");
		player.getPackets().sendIComponentText(1164, 27, "<shad=000000><col=FF0000>~ FairyRing Categories ~</col></shad>");		
		player.getPackets().sendIComponentText(1164, 17, "FairyRing IIII");
		player.getPackets().sendIComponentText(1164, 19, "FairyRing III");
		player.getPackets().sendIComponentText(1164, 21, "FairyRing II");
		player.getPackets().sendIComponentText(1164, 23, "FairyRing I");
		
	}
	
	public static void sendFairyRingMenuOptions(Player player, int componentId) {
				player.viewingagilityteles = false;
				player.viewingminigameteles = false;
				player.viewingmonsterteles = false;
				player.viewingrsmvactionlocs = false;
				player.viewingskillingteles = false;
				player.viewingtrainingteles = false;
				player.viewinglyreteles = true;
				player.viewingfamouscategories = false;
				player.viewingbrightcategories = true;
				player.viewingmoodycategories = false;
				player.viewingfairyringcategories = false;
				player.viewingextracategories = false;
			if (componentId == 42) {//FairyRing Categories
				player.getInterfaceManager().sendInterface(1160);
				sendFairyRingCategories(player);
			} else if (componentId == 26) {//Main Menu
				sendMainMenu(player);
			} else if (componentId == 28) {//FairyRing I
				player.viewingfamousIteles = false;
				player.viewingfamousIIteles = false;
				player.viewingfamousIIIteles = false;
				player.viewingfamousIIIIteles = false;
				player.viewingbrightIteles = true;
				player.viewingbrightIIteles = false;
				player.viewingbrightIIIteles = false;
				player.viewingbrightIIIIteles = false;
				player.viewingmoodyIteles = false;
				player.viewingmoodyIIteles = false;
				player.viewingmoodyIIIteles = false;
				player.viewingmoodyIIIIteles = false;
				player.viewingfairyIteles = false;
				player.viewingfairyIIteles = false;
				player.viewingfairyIIIteles = false;
				player.viewingfairyIIIIteles = false;
				player.viewingextraIteles = false;
				player.viewingextraIIteles = false;
				player.viewingextraIIIteles = false;
				player.viewingextraIIIIteles = false;
				sendFairyRingITeleports(player);
			} else if (componentId == 29) {//FairyRing II
				player.viewingfamousIteles = false;
				player.viewingfamousIIteles = false;
				player.viewingfamousIIIteles = false;
				player.viewingfamousIIIIteles = false;
				player.viewingbrightIteles = false;
				player.viewingbrightIIteles = true;
				player.viewingbrightIIIteles = false;
				player.viewingbrightIIIIteles = false;
				player.viewingmoodyIteles = false;
				player.viewingmoodyIIteles = false;
				player.viewingmoodyIIIteles = false;
				player.viewingmoodyIIIIteles = false;
				player.viewingfairyIteles = false;
				player.viewingfairyIIteles = false;
				player.viewingfairyIIIteles = false;
				player.viewingfairyIIIIteles = false;
				player.viewingextraIteles = false;
				player.viewingextraIIteles = false;
				player.viewingextraIIIteles = false;
				player.viewingextraIIIIteles = false;
				sendFairyRingIITeleports(player);
			} else if (componentId == 30) {//FairyRing III
				player.viewingfamousIteles = false;
				player.viewingfamousIIteles = false;
				player.viewingfamousIIIteles = false;
				player.viewingfamousIIIIteles = false;
				player.viewingbrightIteles = false;
				player.viewingbrightIIteles = false;
				player.viewingbrightIIIteles = true;
				player.viewingbrightIIIIteles = false;
				player.viewingmoodyIteles = false;
				player.viewingmoodyIIteles = false;
				player.viewingmoodyIIIteles = false;
				player.viewingmoodyIIIIteles = false;
				player.viewingfairyIteles = false;
				player.viewingfairyIIteles = false;
				player.viewingfairyIIIteles = false;
				player.viewingfairyIIIIteles = false;
				player.viewingextraIteles = false;
				player.viewingextraIIteles = false;
				player.viewingextraIIIteles = false;
				player.viewingextraIIIIteles = false;
				sendFairyRingIIITeleports(player);
			} else if (componentId == 31) {//FairyRing IIII
				player.viewingfamousIteles = false;
				player.viewingfamousIIteles = false;
				player.viewingfamousIIIteles = false;
				player.viewingfamousIIIIteles = false;
				player.viewingbrightIteles = false;
				player.viewingbrightIIteles = false;
				player.viewingbrightIIIteles = false;
				player.viewingbrightIIIIteles = true;
				player.viewingmoodyIteles = false;
				player.viewingmoodyIIteles = false;
				player.viewingmoodyIIIteles = false;
				player.viewingmoodyIIIIteles = false;
				player.viewingfairyIteles = false;
				player.viewingfairyIIteles = false;
				player.viewingfairyIIIteles = false;
				player.viewingfairyIIIIteles = false;
				player.viewingextraIteles = false;
				player.viewingextraIIteles = false;
				player.viewingextraIIIteles = false;
				player.viewingextraIIIIteles = false;
				sendFairyRingIIIITeleports(player);
			}
		}
		
	public static void sendFairyRingITeleports (Player player) {
				player.viewingagilityteles = false;
				player.viewingminigameteles = false;
				player.viewingmonsterteles = false;
				player.viewingrsmvactionlocs = false;
				player.viewingskillingteles = false;
				player.viewingtrainingteles = false;	
				player.viewinglyreteles = true;
				player.viewingfamousIteles = true;
				player.viewingfamousIIteles = false;
				player.viewingfamousIIIteles = false;
				player.viewingfamousIIIIteles = false;
				player.getInterfaceManager().sendInterface(1156);
				player.getPackets().sendIComponentText(1156, 190,
						"FairyRing I Teleports");
				player.getPackets().sendIComponentText(1156, 108, "Clocktower Fire");
				player.getPackets().sendIComponentText(1156, 109,
						"This place is literally on fire.");
				player.getPackets().sendIComponentText(1156, 90, "Teleport!");
				player.getPackets().sendIComponentText(1156, 113, "Ruinz oF Uzer");
				player.getPackets().sendIComponentText(1156, 114,
						"Best place to play the Charlie Charlie challenge.");
				player.getPackets().sendIComponentText(1156, 206, "Teleport!");
				player.getPackets().sendIComponentText(1156, 137, "Underground Pass");
				player.getPackets().sendIComponentText(1156, 138,
						"'Follow me into the dirt.'");
				player.getPackets().sendIComponentText(1156, 254, "Teleport!");
				player.getPackets().sendIComponentText(1156, 110, "Ardougne Gallow");
				player.getPackets().sendIComponentText(1156, 111,
						"Ne-ver fall a-sleep!");
				player.getPackets().sendIComponentText(1156, 200, "Teleport!");
				player.getPackets().sendIComponentText(1156, 116, "H.A.M Lair");
				player.getPackets().sendIComponentText(1156, 117,
						"'I hear you brother...'");
				player.getPackets().sendIComponentText(1156, 212, "Teleport!");
				player.getPackets().sendIComponentText(1156, 134,"Map Table");
				player.getPackets().sendIComponentText(1156, 135,
						"Hunter, I think I found your dad.");
				player.getPackets().sendIComponentText(1156, 248, "Teleport!");
				player.getPackets().sendIComponentText(1156, 122, "Killer-Watt Clouds");
				player.getPackets().sendIComponentText(1156, 123,
						"'I remembered black skies'");
				player.getPackets().sendIComponentText(1156, 218, "Teleport!");
				player.getPackets().sendIComponentText(1156, 128, "Strange Creatures");
				player.getPackets().sendIComponentText(1156, 129,
						"'I'd like to make myself believe...'");
				player.getPackets().sendIComponentText(1156, 242, "Teleport!");
				player.getPackets().sendIComponentText(1156, 125, "Lighthouse");
				player.getPackets().sendIComponentText(1156, 126,
						"'You show the lights that stop me'");
				player.getPackets().sendIComponentText(1156, 260, "Teleport!");
				player.getPackets().sendIComponentText(1156, 143, "Guthix Circle");
				player.getPackets().sendIComponentText(1156, 144,
						"'To stop the pain inside and feel again...'");
				player.getPackets().sendIComponentText(1156, 278, "Teleport!");
				player.getPackets().sendIComponentText(1156, 146, "Arandar");
				player.getPackets().sendIComponentText(1156, 147,
						"'If you love me, won't you let me go'");
				player.getPackets().sendIComponentText(1156, 284, "Teleport!");
				player.getPackets().sendIComponentText(1156, 119, "Ruinz of Ullek");
				player.getPackets().sendIComponentText(1156, 120,
						"'To the edge, of the earth.'");
				player.getPackets().sendIComponentText(1156, 230, "Teleport!");
				player.getPackets().sendIComponentText(1156, 131, "Lunar Mine");
				player.getPackets().sendIComponentText(1156, 132,
						"'If you take it from me!'");
				player.getPackets().sendIComponentText(1156, 242, "Teleport!");
				player.getPackets().sendIComponentText(1156, 140, "Ritual of Mahajarrat");
				player.getPackets().sendIComponentText(1156, 141,
						"'Now tell me all a-bout your pain down to the detail...'");
				player.getPackets().sendIComponentText(1156, 224, "Teleport!");
				player.getPackets().sendIComponentText(1156, 149, "Seers Rooftop");
				player.getPackets().sendIComponentText(1156, 150,
						"'We see the stones, falling from the sky...!");
				player.getPackets().sendIComponentText(1156, 266, "Teleport!");
				player.getPackets().sendIComponentText(1156, 152,"Old Lumbridge");
				player.getPackets().sendIComponentText(1156, 142,
						"'I'm a genius with a headache.");
				player.getPackets().sendIComponentText(1156, 272, "Teleport!");
				player.getPackets().sendIComponentText(1156, 167, "Blue Moon Inn  <col=FF0000><shad=FF0000>NEW</shad></col>");
				player.getPackets().sendIComponentText(1156, 168,
						"'Ain't no rest for the wicked'");
				player.getPackets().sendIComponentText(1156, 308, "Teleport!");
				player.getPackets().sendIComponentText(1156, 155, "Edgeville Bar  <col=FF0000><shad=FF0000>NEW</shad></col>");
				player.getPackets().sendIComponentText(1156, 157,
						"");
				player.getPackets().sendHideIComponent(1156, 156, true);
				player.getPackets().sendIComponentText(1156, 290, "Teleport!");
				player.getPackets().sendIComponentText(1156, 159, "Bandos Throne Room Portal  <col=FF0000><shad=FF0000>NEW</shad></col>");
				player.getPackets().sendHideIComponent(1156, 160, true);
				player.getPackets().sendIComponentText(1156, 161,
						"'It begins with a dark glowing ember.'");
				player.getPackets().sendIComponentText(1156, 296, "Teleport!");
				player.getPackets().sendIComponentText(1156, 163, "Fishing Colony  <col=FF0000><shad=FF0000>NEW</shad></col>");
				player.getPackets().sendHideIComponent(1156, 164, true);
				player.getPackets().sendIComponentText(1156, 165,
						"Rune Factor 3");
				player.getPackets().sendIComponentText(1156, 302, "Teleport!");
				player.getPackets().sendIComponentText(1156, 170,"Underground Pass Temple  <col=FF0000><shad=FF0000>NEW</shad></col>");
				player.getPackets().sendIComponentText(1156, 171,
						"'As two hands open doors...!'");
				player.getPackets().sendIComponentText(1156, 314, "Teleport!");
				player.getPackets().sendIComponentText(1156, 318,"Sorcercer's Garden Fountain  <col=FF0000><shad=FF0000>NEW</shad></col>");
				player.getPackets().sendIComponentText(1156, 319,
						"Pedal away on a makeshift bike.");
				player.getPackets().sendIComponentText(1156, 326, "Teleport!");	
			 }
	
	public static void handleFairyRingIButtons(Player player, int componentId) {
			if (componentId == 88) {//Clocktower Fire
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2609, 9639, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 115) {//Ruins of Uzer
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3552, 4964, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 139) {//Underground Pass
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2447, 3315, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 112) {//Ardougne Gallow
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2524, 3305, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 118) {//H.A.M Lair
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3160, 9634, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 136) {//Map Table
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1693, 5469, 2));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 124) {//KillerWatt Clouds
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2659, 5216, 2));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 130) {//Strange Creatures
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3228, 9526, 2));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 127) {//Lighthouse
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2508, 3645, 2));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 145) {//Guthix Circle
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2540, 5772, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 148) {//Arandar
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2347, 3301, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 121) {//Ruins of Ullek
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3409, 2800, 1));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 133) {//Lunar Mine
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2339, 10337, 2));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 142) {//Ritual of Mahjarrat
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2887, 3864, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 151) {//Seers Rooftop
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2701, 3472, 3));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 154) {//Old Lumbridge
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2340, 5719, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 169) {//Blue Moon Inn
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3222, 3399, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 158) {//Edgveille Bar
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3079, 3442, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 162) {//Bandos Throne Room Portal
		    	Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2320, 4242, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 166) {//Fishing Colony
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1501, 4314, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 172) {//Underground Pass Temple
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2146, 4648, 1));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} else if (componentId == 320) {//Sorcerer's Garden Fountain
				Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2913, 5471, 0));
				player.getAppearence().setRenderEmote(-1);
				player.viewingfamousIteles = false;
			} 
		}
		
	public static void sendFairyRingIITeleports (Player player) {
			player.viewingagilityteles = false;
			player.viewingminigameteles = false;
			player.viewingmonsterteles = false;
			player.viewingrsmvactionlocs = false;
			player.viewingskillingteles = false;
			player.viewingtrainingteles = false;	
			player.viewinglyreteles = true;
			player.viewingfamousIteles = false;
			player.viewingfamousIIteles = true;
			player.viewingfamousIIIteles = false;
			player.viewingfamousIIIIteles = false;
			player.getInterfaceManager().sendInterface(1156);
			player.getPackets().sendIComponentText(1156, 190,
					"FairyRing II Teleports");
			player.getPackets().sendIComponentText(1156, 108, "Railroad");
			player.getPackets().sendIComponentText(1156, 109,
					"'Frozen in the lights of a locomotive'");
			player.getPackets().sendIComponentText(1156, 90, "Teleport!");
			player.getPackets().sendIComponentText(1156, 113, "Tower of Life Underground");
			player.getPackets().sendIComponentText(1156, 114,
					"'To make a final remedy.'");
			player.getPackets().sendIComponentText(1156, 206, "Teleport!");
			player.getPackets().sendIComponentText(1156, 137, "Abyssal Rift");
			player.getPackets().sendIComponentText(1156, 138,
					"Known as the road more or less travelled by.");
			player.getPackets().sendIComponentText(1156, 254, "Teleport!");
			player.getPackets().sendIComponentText(1156, 110, "Baxatorian Falls");
			player.getPackets().sendIComponentText(1156, 111,
					"'Tell me your real name...!'");
			player.getPackets().sendIComponentText(1156, 200, "Teleport!");
			player.getPackets().sendIComponentText(1156, 116, "Legendary Magic Trees");
			player.getPackets().sendIComponentText(1156, 117,
					"'I'm a slave...!'");
			player.getPackets().sendIComponentText(1156, 212, "Teleport!");
			player.getPackets().sendIComponentText(1156, 134,"Lletya Overview");
			player.getPackets().sendIComponentText(1156, 135,
					"'Am I a little sick?'");
			player.getPackets().sendIComponentText(1156, 248, "Teleport!");
			player.getPackets().sendIComponentText(1156, 122, "Clan Citadel Dancefloor");
			player.getPackets().sendIComponentText(1156, 123,
					"'Everyday I'm shufflin'...'");
			player.getPackets().sendIComponentText(1156, 218, "Teleport!");
			player.getPackets().sendIComponentText(1156, 128, "Underground Pass Well");
			player.getPackets().sendIComponentText(1156, 129,
					"'What can protect me from the past?'");
			player.getPackets().sendIComponentText(1156, 242, "Teleport!");
			player.getPackets().sendIComponentText(1156, 125, "Catharede Abbey Bell");
			player.getPackets().sendIComponentText(1156, 126,
					"''");
			player.getPackets().sendIComponentText(1156, 260, "Teleport!");
			player.getPackets().sendIComponentText(1156, 143, "Ecotfongus Underground");
			player.getPackets().sendIComponentText(1156, 144,
					"'One more time, I'm on my knees!'");
			player.getPackets().sendIComponentText(1156, 278, "Teleport!");
			player.getPackets().sendIComponentText(1156, 146, "Chaos Altar(Wilderness)");
			player.getPackets().sendIComponentText(1156, 147,
					"'They tore me apart!'");
			player.getPackets().sendIComponentText(1156, 284, "Teleport!");
			player.getPackets().sendIComponentText(1156, 119, "Varrock Rooftop");
			player.getPackets().sendIComponentText(1156, 120,
					"'We are the kings...!'");
			player.getPackets().sendIComponentText(1156, 230, "Teleport!");
			player.getPackets().sendIComponentText(1156, 131, "Camelot Pillars");
			player.getPackets().sendIComponentText(1156, 132,
					"'Will I become my worst enemy...?'");
			player.getPackets().sendIComponentText(1156, 242, "Teleport!");
			player.getPackets().sendIComponentText(1156, 140, "Ice Mountain");
			player.getPackets().sendIComponentText(1156, 141,
					"'And watch that Colorado Sunrise'");
			player.getPackets().sendIComponentText(1156, 224, "Teleport!");
			player.getPackets().sendIComponentText(1156, 149, "VWB");
			player.getPackets().sendIComponentText(1156, 150,
					"Basically Neverland, but as a bank.");
			player.getPackets().sendIComponentText(1156, 266, "Teleport!");
			player.getPackets().sendIComponentText(1156, 152,"Witchaven Pillars");
			player.getPackets().sendIComponentText(1156, 142,
					"'You know to keep your hopes.");
			player.getPackets().sendIComponentText(1156, 272, "Teleport!");
			player.getPackets().sendIComponentText(1156, 167, "N/A");
			player.getPackets().sendIComponentText(1156, 168,
					"Suggest for special prizes");
			player.getPackets().sendIComponentText(1156, 308, "Teleport!");
			player.getPackets().sendIComponentText(1156, 155, "N/A");
			player.getPackets().sendIComponentText(1156, 157,
					"Suggest for special prizes");
			player.getPackets().sendHideIComponent(1156, 156, true);
			player.getPackets().sendIComponentText(1156, 290, "Teleport!");
			player.getPackets().sendIComponentText(1156, 159, "N/A");
			player.getPackets().sendHideIComponent(1156, 160, true);
			player.getPackets().sendIComponentText(1156, 161,
					"Suggest for special prizes");
			player.getPackets().sendIComponentText(1156, 296, "Teleport!");
			player.getPackets().sendIComponentText(1156, 163, "N/A");
			player.getPackets().sendHideIComponent(1156, 164, true);
			player.getPackets().sendIComponentText(1156, 165,
					"Suggest for special prizes");
			player.getPackets().sendIComponentText(1156, 302, "Teleport!");
			player.getPackets().sendIComponentText(1156, 170,"N/A");
			player.getPackets().sendIComponentText(1156, 171,
					"Suggest for special prizes");
			player.getPackets().sendIComponentText(1156, 314, "Teleport!");
			player.getPackets().sendIComponentText(1156, 318,"N/A");
			player.getPackets().sendIComponentText(1156, 319,
					"Suggest for special prizes");
			player.getPackets().sendIComponentText(1156, 326, "Teleport!");	
		 }
	
	public static void handleFairyRingIIButtons(Player player, int componentId) {
		if (componentId == 88) {//Railroad
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2615, 3501, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 115) {//Tower Of Life Underground
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3040, 4390, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 139) {//Abysaal Rift
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3038, 4833, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 112) {//Baxatorian Falls
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2456, 4568, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 118) {//Legendary Magic Trees
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1300, 4588, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 136) {//Lletya Overview
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2343, 3172, 1));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 124) {//Clan Citadel Dancefloor
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(5376, 4714, 1));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 130) {//Underground Pass Well
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2342, 9621, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 127) {//Catharede Abbey Bell
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3450, 3178, 3));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 145) {//Ecotfongus Underground
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3683, 9888, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 148) {//Chaos Altar(Wilderness)
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3240, 3613, 0));
			player.getControlerManager().startControler("Wilderness");
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 121) {//Varrock Rooftop
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3212, 3467, 2));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 133) {//Camelot Pillars
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2780, 3515, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 142) {//Ice Mountain
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3003, 3470, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 151) {//VWB
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3185, 3440, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIteles = false;
		} else if (componentId == 154) {//Witchaven Pillars
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2697, 3283, 0));
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIteles = false;
		} else if (componentId == 169) {//
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIteles = false;
		} else if (componentId == 158) {//
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIteles = false;
		} else if (componentId == 162) {//
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIteles = false;
		} else if (componentId == 166) {//
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIteles = false;
		} else if (componentId == 172) {//
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIteles = false;
		} else if (componentId == 320) {//
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIteles = false;
		} 
	}
		
	public static void sendFairyRingIIITeleports (Player player) {
		player.viewingagilityteles = false;
		player.viewingminigameteles = false;
		player.viewingmonsterteles = false;
		player.viewingrsmvactionlocs = false;
		player.viewingskillingteles = false;
		player.viewingtrainingteles = false;	
		player.viewinglyreteles = true;
		player.viewingfamousIteles = false;
		player.viewingfamousIIteles = false;
		player.viewingfamousIIIteles = true;
		player.viewingfamousIIIIteles = false;
		player.getInterfaceManager().sendInterface(1156);
		player.getPackets().sendIComponentText(1156, 190,
				"FairyRing III Teleports");
		player.getPackets().sendIComponentText(1156, 108, "Gift of Peace");
		player.getPackets().sendIComponentText(1156, 109,
				"'I couldn't face a life without your lights.'");
		player.getPackets().sendIComponentText(1156, 90, "Teleport!");
		player.getPackets().sendIComponentText(1156, 113, "Grain of Plenty");
		player.getPackets().sendIComponentText(1156, 114,
				"'What can I do?'");
		player.getPackets().sendIComponentText(1156, 206, "Teleport!");
		player.getPackets().sendIComponentText(1156, 137, "Box of Health");
		player.getPackets().sendIComponentText(1156, 138,
				"");
		player.getPackets().sendIComponentText(1156, 254, "Teleport!");
		player.getPackets().sendIComponentText(1156, 110, "Cradle of Life");
		player.getPackets().sendIComponentText(1156, 111,
				"'Sym-pa...-thy!'");
		player.getPackets().sendIComponentText(1156, 200, "Teleport!");
		player.getPackets().sendIComponentText(1156, 116, "Gu'Tanoth Bridge");
		player.getPackets().sendIComponentText(1156, 117,
				"");
		player.getPackets().sendIComponentText(1156, 212, "Teleport!");
		player.getPackets().sendIComponentText(1156, 134,"Mudskipper Point");
		player.getPackets().sendIComponentText(1156, 135,
				"");
		player.getPackets().sendIComponentText(1156, 248, "Teleport!");
		player.getPackets().sendIComponentText(1156, 122, "Port Sarim Hill");
		player.getPackets().sendIComponentText(1156, 123,
				"");
		player.getPackets().sendIComponentText(1156, 218, "Teleport!");
		player.getPackets().sendIComponentText(1156, 128, "Falador High Point");
		player.getPackets().sendIComponentText(1156, 129,
				"");
		player.getPackets().sendIComponentText(1156, 242, "Teleport!");
		player.getPackets().sendIComponentText(1156, 125, "Party Room");
		player.getPackets().sendIComponentText(1156, 126,
				"'Aint nobody dope as me.'");
		player.getPackets().sendIComponentText(1156, 260, "Teleport!");
		player.getPackets().sendIComponentText(1156, 143, "Blue Moon Inn");
		player.getPackets().sendIComponentText(1156, 144,
				"'I gave you everything to die with a smile.'");
		player.getPackets().sendIComponentText(1156, 278, "Teleport!");
		player.getPackets().sendIComponentText(1156, 146, "Edgeville School");
		player.getPackets().sendIComponentText(1156, 147,
				"'Give my props to the speak and spell!'");
		player.getPackets().sendIComponentText(1156, 284, "Teleport!");
		player.getPackets().sendIComponentText(1156, 119, "Monuments");
		player.getPackets().sendIComponentText(1156, 120,
				"'You have a grand stand seat here''");
		player.getPackets().sendIComponentText(1156, 230, "Teleport!");
		player.getPackets().sendIComponentText(1156, 131, "Desert Pyramid");
		player.getPackets().sendIComponentText(1156, 132,
				"'Be-hind...closed...doors...!'");
		player.getPackets().sendIComponentText(1156, 242, "Teleport!");
		player.getPackets().sendIComponentText(1156, 140, "Marko's Spot(Wilderness)");
		player.getPackets().sendIComponentText(1156, 141,
				"Hai Marko <3");
		player.getPackets().sendIComponentText(1156, 224, "Teleport!");
		player.getPackets().sendIComponentText(1156, 149, "VWB");
		player.getPackets().sendIComponentText(1156, 150,
				"Neverland but as a bank.");
		player.getPackets().sendIComponentText(1156, 266, "Teleport!");
		player.getPackets().sendIComponentText(1156, 152,"Isadafar Butterflies");
		player.getPackets().sendIComponentText(1156, 142,
				"");
		player.getPackets().sendIComponentText(1156, 272, "Teleport!");
		player.getPackets().sendIComponentText(1156, 167, "N/A");
		player.getPackets().sendIComponentText(1156, 168,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 308, "Teleport!");
		player.getPackets().sendIComponentText(1156, 155, "N/A");
		player.getPackets().sendIComponentText(1156, 157,
				"Suggest for special prizes");
		player.getPackets().sendHideIComponent(1156, 156, true);
		player.getPackets().sendIComponentText(1156, 290, "Teleport!");
		player.getPackets().sendIComponentText(1156, 159, "N/A");
		player.getPackets().sendHideIComponent(1156, 160, true);
		player.getPackets().sendIComponentText(1156, 161,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 296, "Teleport!");
		player.getPackets().sendIComponentText(1156, 163, "N/A");
		player.getPackets().sendHideIComponent(1156, 164, true);
		player.getPackets().sendIComponentText(1156, 165,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 302, "Teleport!");
		player.getPackets().sendIComponentText(1156, 170,"N/A");
		player.getPackets().sendIComponentText(1156, 171,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 314, "Teleport!");
		player.getPackets().sendIComponentText(1156, 318,"N/A");
		player.getPackets().sendIComponentText(1156, 319,
				"Suggest for special prizes");
		player.getPackets().sendIComponentText(1156, 326, "Teleport!");	
	 }
	
	public static void handleFairyRingIIIButtons(Player player, int componentId) {
	if (componentId == 88) {//"Gift of Peace
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1908, 5222, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 115) {//Grain of Plenty
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2023, 5215, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 139) {//Box of Health
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2145, 5280, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 112) {//Cradle of Life
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2345, 5214, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 118) {//Gu'Tanoth Bridge
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2536, 3012, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 136) {//Mudskipper Point
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2994, 3114, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 124) {//Port Sarim Hill
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3007, 3144, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 130) {//Falador High Point
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2995, 3342, 3));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 127) {//Party Room
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3046, 3377, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 145) {//Blue Moon Inn
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3222, 3402, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 148) {//Edgeville School)
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3081, 3456, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 121) {//Monuments
		player.getPackets().sendGameMessage("<col=FF0000>You have unlocked a new music track: Lolita's Medicine");
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3420, 9887, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 133) {//Desert Pyramid
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3233, 2903, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 142) {//Marko's Spot(Wilderness)
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3099, 3624, 0));
		player.getControlerManager().startControler("Wilderness");
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 151) {//Isadafar Butterflies
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2245, 3183, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingfamousIIIteles = false;
	} else if (componentId == 154) {//Karamja Volcano
		Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2845, 3168, 0));
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} else if (componentId == 169) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} else if (componentId == 158) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} else if (componentId == 162) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} else if (componentId == 166) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} else if (componentId == 172) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} else if (componentId == 320) {//
		player.getAppearence().setRenderEmote(-1);
		//player.viewingfamousIIIteles = false;
	} 
	}
	
	public static void sendFairyRingIIIITeleports (Player player) {
	player.viewingagilityteles = false;
	player.viewingminigameteles = false;
	player.viewingmonsterteles = false;
	player.viewingrsmvactionlocs = false;
	player.viewingskillingteles = false;
	player.viewingtrainingteles = false;	
	player.viewinglyreteles = true;
	player.viewingfamousIteles = false;
	player.viewingfamousIIteles = false;
	player.viewingfamousIIIteles = false;
	player.viewingfamousIIIIteles = true;
	player.getInterfaceManager().sendInterface(1156);
	player.getPackets().sendIComponentText(1156, 190,
			"Fairy Ring IIII Teleports");
	player.getPackets().sendIComponentText(1156, 108, "Juliet Balcony");
	player.getPackets().sendIComponentText(1156, 109,
			"");
	player.getPackets().sendIComponentText(1156, 90, "Teleport!");
	player.getPackets().sendIComponentText(1156, 113, "Varrock Museum");
	player.getPackets().sendIComponentText(1156, 114,
			"");
	player.getPackets().sendIComponentText(1156, 206, "Teleport!");
	player.getPackets().sendIComponentText(1156, 137, "Chaos Altar Stars");
	player.getPackets().sendIComponentText(1156, 138,
			"");
	player.getPackets().sendIComponentText(1156, 254, "Teleport!");
	player.getPackets().sendIComponentText(1156, 110, "Games Room");
	player.getPackets().sendIComponentText(1156, 111,
			"");
	player.getPackets().sendIComponentText(1156, 200, "Teleport!");
	player.getPackets().sendIComponentText(1156, 116, "Temporary POH");
	player.getPackets().sendIComponentText(1156, 117,
			"");
	player.getPackets().sendIComponentText(1156, 212, "Teleport!");
	player.getPackets().sendIComponentText(1156, 134,"Mage Arena");
	player.getPackets().sendIComponentText(1156, 135,
			"");
	player.getPackets().sendIComponentText(1156, 248, "Teleport!");
	player.getPackets().sendIComponentText(1156, 122, "Wizard Tower Bridge");
	player.getPackets().sendIComponentText(1156, 123,
			"");
	player.getPackets().sendIComponentText(1156, 218, "Teleport!");
	player.getPackets().sendIComponentText(1156, 128, "Sorcerer's Tower");
	player.getPackets().sendIComponentText(1156, 129,
			"");
	player.getPackets().sendIComponentText(1156, 242, "Teleport!");
	player.getPackets().sendIComponentText(1156, 125, "Al' Kharid Sparkling Pool");
	player.getPackets().sendIComponentText(1156, 126,
			"");
	player.getPackets().sendIComponentText(1156, 260, "Teleport!");
	player.getPackets().sendIComponentText(1156, 143, "Battlefield");
	player.getPackets().sendIComponentText(1156, 144,
			"");
	player.getPackets().sendIComponentText(1156, 278, "Teleport!");
	player.getPackets().sendIComponentText(1156, 146, "Whirlpool");
	player.getPackets().sendIComponentText(1156, 147,
			"");
	player.getPackets().sendIComponentText(1156, 284, "Teleport!");
	player.getPackets().sendIComponentText(1156, 119, "Ardougne Bridge");
	player.getPackets().sendIComponentText(1156, 120,
			"");
	player.getPackets().sendIComponentText(1156, 230, "Teleport!");
	player.getPackets().sendIComponentText(1156, 131, "ZMI Altar");
	player.getPackets().sendIComponentText(1156, 132,
			"");
	player.getPackets().sendIComponentText(1156, 242, "Teleport!");
	player.getPackets().sendIComponentText(1156, 140, "Sophanem Bridge");
	player.getPackets().sendIComponentText(1156, 141,
			"");
	player.getPackets().sendIComponentText(1156, 224, "Teleport!");
	player.getPackets().sendIComponentText(1156, 149, "First Vexation Home");
	player.getPackets().sendIComponentText(1156, 150,
			"");
	player.getPackets().sendIComponentText(1156, 266, "Teleport!");
	player.getPackets().sendIComponentText(1156, 152,"Second Vexation Home");
	player.getPackets().sendIComponentText(1156, 153,
			"");
	player.getPackets().sendIComponentText(1156, 272, "Teleport!");
	player.getPackets().sendIComponentText(1156, 167, "N/A");
	player.getPackets().sendIComponentText(1156, 168,
			"Suggest for special prizes");
	player.getPackets().sendIComponentText(1156, 308, "Teleport!");
	player.getPackets().sendIComponentText(1156, 155, "N/A");
	player.getPackets().sendIComponentText(1156, 157,
			"Suggest for special prizes");
	player.getPackets().sendHideIComponent(1156, 156, true);
	player.getPackets().sendIComponentText(1156, 290, "Teleport!");
	player.getPackets().sendIComponentText(1156, 159, "N/A");
	player.getPackets().sendHideIComponent(1156, 160, true);
	player.getPackets().sendIComponentText(1156, 161,
			"Suggest for special prizes");
	player.getPackets().sendIComponentText(1156, 296, "Teleport!");
	player.getPackets().sendIComponentText(1156, 163, "N/A");
	player.getPackets().sendHideIComponent(1156, 164, true);
	player.getPackets().sendIComponentText(1156, 165,
			"Suggest for special prizes");
	player.getPackets().sendIComponentText(1156, 302, "Teleport!");
	player.getPackets().sendIComponentText(1156, 170,"N/A");
	player.getPackets().sendIComponentText(1156, 171,
			"Suggest for special prizes");
	player.getPackets().sendIComponentText(1156, 314, "Teleport!");
	player.getPackets().sendIComponentText(1156, 318,"N/A");
	player.getPackets().sendIComponentText(1156, 319,
			"Suggest for special prizes");
	player.getPackets().sendIComponentText(1156, 326, "Teleport!");	
 }

	public static void handleFairyRingIIIIButtons(Player player, int componentId) {
		if (componentId == 88) {//"Juliet Balcony
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3166, 3433, 1));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 115) {//Varrock Museum
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(1759, 4963, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 139) {//Chaos Altar Stars
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2142, 4845, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 112) {//Games Room
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2208, 4956, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 118) {//Temporary POH
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3804, 5726, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 136) {//Mage Arena
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3363, 3294, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 124) {//Wizard Tower Bridge
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3114, 3192, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 130) {//Sorcerer's Tower
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2702, 3405, 3));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 127) {//Al' Kharid Sparkling Pool
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3288, 3230, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 145) {//Battlefield
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2512, 3237, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 148) {//Whirlpool
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3512, 3518, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 121) {//Ardougne Bridge
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2599, 3296, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 133) {//ZMI Altar
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(2454, 3232, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 142) {//Sophanem Bridge
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3274, 2785, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 151) {//First Vexation Home
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3433, 2894, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingfamousIIIIteles = false;
		} else if (componentId == 154) {//Second Vexation Home
			Magic.sendLyreTeleportSpell(player, 0, 0, new WorldTile(3012, 3360, 0));
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIIIteles = false;
		} else if (componentId == 169) {//
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIIIteles = false;
		} else if (componentId == 158) {//
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIIIteles = false;
		} else if (componentId == 162) {//
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIIIteles = false;
		} else if (componentId == 166) {//
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIIIteles = false;
		} else if (componentId == 172) {//
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIIIteles = false;
		} else if (componentId == 320) {//
			player.getAppearence().setRenderEmote(-1);
			//player.viewingfamousIIIIteles = false;
		} 
	}
		*/
	//Extra Categories
	public static void sendExtraMenuOptions(Player player, int componentId) {
		if (componentId == 42) {//Famous Categories
			player.viewingfamouslyreteles = true;
			sendFamousCategories(player);

			
		} else if (componentId == 53) {//Old Lyre
			player.getDialogueManager().startDialogue("LyreE");
			player.getInterfaceManager().closeInterface(1, 2);
		} else if (componentId == 55) {//Random Teleporter
			
		} else if (componentId == 57) {//Nevermind
			player.getInterfaceManager().closeScreenInterface();
		}
	}
	
}
