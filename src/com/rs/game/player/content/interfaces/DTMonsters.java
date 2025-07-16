package com.rs.game.player.content.interfaces;

import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.Magic;
import com.rs.game.player.controlers.FightCaves;
import com.rs.game.player.controlers.FightKiln;



public class DTMonsters {

	public static void sendOptions(Player player) {
		player.viewingagilityteles = false;
		player.viewingminigameteles = false;
		player.viewingmonsterteles = true;
		player.viewingrsmvactionlocs = false;
		player.viewingskillingteles = false;
		player.viewingtrainingteles = false;
		player.getInterfaceManager().sendInterface(1156);
		player.getPackets().sendIComponentText(1156, 190,
				"Bosses");
		player.getPackets().sendIComponentText(1156, 108, "Armadyl's Eyrie");
		player.getPackets().sendIComponentText(1156, 109,
				"Combat Level: ");
		player.getPackets().sendIComponentText(1156, 90, "Fight!");
		player.getPackets().sendIComponentText(1156, 113, "Bandos' Stronghold");
		player.getPackets().sendIComponentText(1156, 114,
				"Combat Level: ");
		player.getPackets().sendIComponentText(1156, 206, "Fight!");
		player.getPackets().sendIComponentText(1156, 137, "Saradomin's Encampment");
		player.getPackets().sendIComponentText(1156, 138,
				"Combat Level: ");
		player.getPackets().sendIComponentText(1156, 254, "Fight!");
		player.getPackets().sendIComponentText(1156, 110, "Zamorak's Fortress");
		player.getPackets().sendIComponentText(1156, 111,
				"Combat Level: ");
		player.getPackets().sendIComponentText(1156, 200, "Fight!");
		player.getPackets().sendIComponentText(1156, 116, "Nex's Chamber");
		player.getPackets().sendIComponentText(1156, 117,
				"Combat Level: ");
		player.getPackets().sendIComponentText(1156, 212, "Fight!");
		player.getPackets().sendIComponentText(1156, 134, "Kalphite Queen Lair");
		player.getPackets().sendIComponentText(1156, 135,
				"Combat Level: ");
		player.getPackets().sendIComponentText(1156, 248, "Fight!");
		player.getPackets().sendIComponentText(1156, 119, "Fight Pits");
		player.getPackets().sendIComponentText(1156, 120,
				"Combat Level: ");
		player.getPackets().sendIComponentText(1156, 218, "Fight!");
		player.getPackets().sendIComponentText(1156, 131, "Sunfreet");
		player.getPackets().sendIComponentText(1156, 132,
				"Combat Level: ");
		player.getPackets().sendIComponentText(1156, 242, "Fight!");
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
		player.getPackets().sendIComponentText(1156, 122, "Bork the Ork");
		player.getPackets().sendIComponentText(1156, 123,
				"Combat Level: ");
		player.getPackets().sendIComponentText(1156, 230, "Fight!");
		player.getPackets().sendIComponentText(1156, 128, "Queen Black Dragon Lair");
		player.getPackets().sendIComponentText(1156, 129,
				"Combat Level: ");
		player.getPackets().sendIComponentText(1156, 236, "Fight!");
		player.getPackets().sendIComponentText(1156, 125, "Corporeal Beast");
		player.getPackets().sendIComponentText(1156, 126,
				"Combat Level: ");
		player.getPackets().sendIComponentText(1156, 224, "Fight!");
		player.getPackets().sendIComponentText(1156, 143, "Fight Caves");
		player.getPackets().sendIComponentText(1156, 144,
				"Combat Level: ");
		player.getPackets().sendIComponentText(1156, 266, "Fight!");
		player.getPackets().sendIComponentText(1156, 146, "Fight Kiln");
		player.getPackets().sendIComponentText(1156, 147,
				"Combat Level: ");
		player.getPackets().sendIComponentText(1156, 272, "Fight!");
		player.getPackets().sendIComponentText(1156, 167, "");
		player.getPackets().sendIComponentText(1156, 168,
				"");
		player.getPackets().sendIComponentText(1156, 308, "");
		player.getPackets().sendIComponentText(1156, 155, "");
		player.getPackets().sendIComponentText(1156, 157,
				"");
		player.getPackets().sendHideIComponent(1156, 156, true);// hide
		player.getPackets().sendIComponentText(1156, 290, "");
		player.getPackets().sendIComponentText(1156, 159, "");
		player.getPackets().sendHideIComponent(1156, 160, true);// hide
		player.getPackets().sendIComponentText(1156, 161,
				"");
		player.getPackets().sendIComponentText(1156, 296, "");
		player.getPackets().sendIComponentText(1156, 163, "");
		player.getPackets().sendHideIComponent(1156, 164, true);// hide
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
		if (componentId == 88) {//Armadyl's Eyrie
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2838, 5297, 2));
			player.viewingmonsterteles = false;
		} else if (componentId == 115) {//Bandos Stronghold
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2870, 5363, 2));
			player.viewingmonsterteles = false;
		} else if (componentId == 139) {//Saradomin's Encampment
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2901, 5264, 0));
			player.viewingmonsterteles = false;
		} else if (componentId == 112) {//Zamorak's Chamber
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2925, 5330, 2));
			player.viewingmonsterteles = false;
		} else if (componentId == 118) {//Nex's Fortress
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2905, 5203, 0));
			player.viewingmonsterteles = false;
		} else if (componentId == 136) {//Kalphite Queen Lair
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3507,9493, 0));
			player.viewingmonsterteles = false;
		} else if (componentId == 124) {//Bork the Ork 
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3114, 5528, 0));
			player.getAppearence().setRenderEmote(-1);
			player.viewingmonsterteles = false;
		} else if (componentId == 130) {//Queen Black Dragon Lair
			if (player.getSkills().getLevelForXp(Skills.SUMMONING) < 60) {
				player.getPackets().sendGameMessage("You need a summoning level of 60 to go through this portal.");
				return;
			}
			player.getControlerManager().startControler("QueenBlackDragonControler");
			player.viewingmonsterteles = false;
		} else if (componentId == 127) {//Corporeal Beast
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2966, 4383, 2));
			player.viewingmonsterteles = false;
		} else if (componentId == 145) {//Fight Caves
			Magic.sendNormalTeleportSpell(player, 0, 0, FightCaves.OUTSIDE);
			player.viewingmonsterteles = false;
		} else if (componentId == 148) {//Fight Kiln
			Magic.sendNormalTeleportSpell(player, 0, 0, FightKiln.OUTSIDE);
			player.viewingmonsterteles = false;
		} else if (componentId == 121) {//Fight Pits
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4608, 5061, 0));
			player.viewingmonsterteles = false; 
		} else if (componentId == 121) {//Sunfreet
			player.sm("<img=20><col=FF0000>Coming soon!");
			player.viewingmonsterteles = false;
		} 
		}
}
