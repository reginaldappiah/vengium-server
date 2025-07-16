package com.rs.game.player.content.interfaces;

import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.minigames.CastleWars;
import com.rs.game.player.Player;
import com.rs.game.player.content.Magic;



public class DTAgility {
	public static void sendOptions(Player player) {
		player.viewingagilityteles = true;
		player.viewingminigameteles = false;
		player.viewingmonsterteles = false;
		player.viewingrsmvactionlocs = false;
		player.viewingskillingteles = false;
		player.viewingtrainingteles = false;
		player.getInterfaceManager().sendInterface(1156);
		player.getPackets().sendIComponentText(1156, 190,
				"Agility Courses");
		player.getPackets().sendIComponentText(1156, 108, "Gnome Agility Course");
		player.getPackets().sendIComponentText(1156, 109,
				"Like a gnome, speed your way through the course.");
		player.getPackets().sendIComponentText(1156, 90, "Teleport!");
		player.getPackets().sendIComponentText(1156, 113, "Barbarian Outpost Agility Course");
		player.getPackets().sendIComponentText(1156, 114,
				"Toughen and face the hardest course of all.");
		player.getPackets().sendIComponentText(1156, 206, "Teleport!");
		player.getPackets().sendIComponentText(1156, 137, "Wilderness");
		player.getPackets().sendIComponentText(1156, 138,
				"<col=FF0000>WARNING: Level 50 wilderness!");
		player.getPackets().sendIComponentText(1156, 254, "Teleport!");
		player.getPackets().sendIComponentText(1156, 110, "Ape Atoll Agility Course");
		player.getPackets().sendIComponentText(1156, 111,
				"Get your monkey greegree and swing around.");
		player.getPackets().sendIComponentText(1156, 200, "Teleport!");
		player.getPackets().sendIComponentText(1156, 116, "Brimhaven Agility Course");
		player.getPackets().sendIComponentText(1156, 117,
				"Jump, fall, and get cut by blades.");
		player.getPackets().sendIComponentText(1156, 212, "Teleport!");
		player.getPackets().sendIComponentText(1156, 134, "Dorgesh-Kaan Agility Course");
		player.getPackets().sendIComponentText(1156, 135,
				"Intially combined with a mission.");
		player.getPackets().sendIComponentText(1156, 248, "Teleport!");
		player.getPackets().sendIComponentText(1156, 122, "Kethsi Agility Course");
		player.getPackets().sendIComponentText(1156, 123,
				"Enagage in agility in a gloomy setting.");
		
		player.getPackets().sendIComponentText(1156, 284, "");
		player.getPackets().sendIComponentText(1156, 119, "Agility Pyramid Course");
		player.getPackets().sendIComponentText(1156, 120,
				"Climnb to the top of the pyramid.");
		player.getPackets().sendIComponentText(1156, 218, "Teleport!");
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
		player.getPackets().sendIComponentText(1156, 230, "Teleport!");
		player.getPackets().sendIComponentText(1156, 128, "Bandos Throne Room Agility Course");
		player.getPackets().sendIComponentText(1156, 129,
				"Jump on statues and balance yourself.");
		player.getPackets().sendIComponentText(1156, 236, "Teleport!");
		player.getPackets().sendIComponentText(1156, 125, "Burthorpe Agility Course");
		player.getPackets().sendIComponentText(1156, 126,
				"Climb walls and swing on monkey bars.");
		player.getPackets().sendIComponentText(1156, 224, "Teleport!");
		player.getPackets().sendIComponentText(1156, 143, "Werewolf Skullball Agility Course");
		player.getPackets().sendIComponentText(1156, 144,
				"Run the althele-themed obstacle course.");
		player.getPackets().sendIComponentText(1156, 266, "Teleport!");
		player.getPackets().sendIComponentText(1156, 146,"Penguin Agility Course");
		player.getPackets().sendIComponentText(1156, 147,
				"Become a penguin and finish the course.");
		player.getPackets().sendIComponentText(1156, 272, "Teleport!");
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
	if (componentId == 88) {//Gnome Stronghold Course
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2474, 3440, 0));
		player.viewingagilityteles = false;
	} else if (componentId == 115) {//Barbarian Outpost Course
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2548, 3554, 0));
		player.viewingagilityteles = false;
	} else if (componentId == 139) {//Wilderness Course
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2998, 3916, 0));
		player.viewingagilityteles = false;
	} else if (componentId == 112) {//Ape Atoll Course
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2758, 2751, 0));
		player.viewingagilityteles = false;
	} else if (componentId == 118) {//Brimhaven Course
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2805, 9589, 3));
		player.viewingagilityteles = false;
	} else if (componentId == 136) {//Dorgesh-Kaan Agility Course 
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2722, 5243, 3));
		player.viewingagilityteles = false;
	} else if (componentId == 124) {//Ketshi Agility Course
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4014, 5704, 0));
		player.getAppearence().setRenderEmote(-1);
		player.viewingagilityteles = false;
	} else if (componentId == 130) {//Bandos Throne Room Agility Course
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2332, 4243, 0));
		
		player.viewingagilityteles = false;
	} else if (componentId == 127) {//Burthorpe Agility Course
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2918, 3549, 0));
		
		player.viewingagilityteles = false;
	} else if (componentId == 145) {//Werewolf Skullball Agility Course
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3538, 9866, 0));
		player.viewingagilityteles = false;
	} else if (componentId == 148) {//Penguin Agility Course
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2641, 4049, 1));	
		
		player.viewingagilityteles = false;
	} else if (componentId == 121) {//Agility Pyramid Course
		Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3346, 2829, 0));

		player.viewingagilityteles = false;
	} 
	}
}
