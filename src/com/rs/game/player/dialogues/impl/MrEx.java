package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.WorldTile;
import com.rs.game.minigames.CastleWars;
import com.rs.game.player.Skills;
import com.rs.game.player.content.Magic;
import com.rs.game.player.controlers.FightCaves;
import com.rs.game.player.controlers.FightKiln;
import com.rs.game.player.dialogues.Dialogue;

public class MrEx extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendEntityDialogue(SEND_2_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name, "Hello adventurer, would you like me to teleport you somewhere?" }, IS_NPC, npcId, 9827);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { player.getDisplayName(), "Yes please. Where can you teleport me to?" }, IS_PLAYER, player.getIndex(), 9827);
			stage = 1;
		} else if (stage == 1) {
			sendOptionsDialogue("Teleports", "Armadyl Chamber", "Bandos Chamber", "Saradomin Chamber", "Zamorak Chamber", "Next page");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1)
				teleportPlayer(2838, 5297, 2);
			else if (componentId == OPTION_2)
				teleportPlayer(2870, 5363, 2);
			else if (componentId == OPTION_3)
				teleportPlayer(2901, 5264, 0);
			else if (componentId == OPTION_4)
				teleportPlayer(2925, 5330, 2);
			else if (componentId == OPTION_5) {
				stage = 3;
				sendOptionsDialogue("Teleports", "Nex Chamber", "Kalphite Queen Lair", "Bork the Ork", "Queen Black Dragon Lair", "Next page");
			}
		} else if (stage == 3) {
			if (componentId == OPTION_1) {
				teleportPlayer(2905, 5203, 0);
			} else if (componentId == OPTION_2)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3507,9493, 0));
			else if (componentId == OPTION_3)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3114, 5528, 0));
			else if (componentId == OPTION_4) {
				end();
				if (player.getSkills().getLevelForXp(Skills.SUMMONING) < 60) {
					player.getPackets().sendGameMessage("You need a summoning level of 60 to go through this portal.");
					return;
				}
				player.getControlerManager().startControler("QueenBlackDragonControler");
			}
			else if (componentId == OPTION_5) {
				stage = 4;
				sendOptionsDialogue("Teleports", "Fight Caves", "Fight Kiln", "Fight Pits", "Corporeal Beast", "Next page");
			}
		} else if (stage == 4) {
			if (componentId == OPTION_1) {
				Magic.sendNormalTeleportSpell(player, 0, 0, FightCaves.OUTSIDE);
			} else if (componentId == OPTION_2) {
				Magic.sendNormalTeleportSpell(player, 0, 0, FightKiln.OUTSIDE);
			} else if (componentId == OPTION_3)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4608, 5061, 0));
			else if (componentId == OPTION_4) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2966, 4383, 2));
			} else if (componentId == OPTION_5) {
				stage = 5;
				sendOptionsDialogue("Teleports", "Easts (PvP)", "Wests (PvP)", "Multi Area (PvP)", "Mage Bank", "Next page");
			}
		} else if (stage == 5) {
			if (componentId == OPTION_1) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3360, 3658, 0));
				player.getControlerManager().startControler("Wilderness");
			} else if (componentId == OPTION_2) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2984, 3596, 0));
				player.getControlerManager().startControler("Wilderness");
			} else if (componentId == OPTION_3) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3240, 3611, 0));
				player.getControlerManager().startControler("Wilderness");
			} else if (componentId == OPTION_4) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2538, 4715, 0));
			} else if (componentId == OPTION_5) {
				stage = 6;
				sendOptionsDialogue("Teleports", "Castle Wars", "Feldip Hills", "Brim Haven Dungeon", "Trivia", "Next page");
			}
		} else if (stage == 6) {
			if (componentId == OPTION_1) {
				Magic.sendNormalTeleportSpell(player, 0, 0, CastleWars.LOBBY);
			} else if (componentId == OPTION_2) {
				player.getPackets().sendGameMessage("Note: Hunter will be trainable here once it's implemented.");
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2570, 2916, 0));
			} else if (componentId == OPTION_3)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2709, 9464, 0));
			else if (componentId == OPTION_4)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2647, 9378, 0));
			else if (componentId == OPTION_5) {
				sendOptionsDialogue("Teleports", "Frost Dragons", "Glacors", "Polypore Dungeon", "Barrows", "Next page");
				stage = 7;
			}
		} else if (stage == 7) {
			if (componentId == OPTION_1)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1297, 4510, 0));
			else if (componentId == OPTION_2)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4183, 5729, 0));
			else if (componentId == OPTION_3)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2852, 9484, 0));
			else if (componentId == OPTION_4)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3565, 3289, 0));
			else if (componentId == OPTION_5) {
				sendOptionsDialogue("Teleports", "Duel Arena", "Dominion Tower", "Gnome Agility Course", "Tormented Demons", "Next page");
				stage = 8;
			}
		} else if (stage == 8) {
			if (componentId == OPTION_1) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3365, 3275, 0));
				player.getControlerManager().startControler("DuelControler");
			} else if (componentId == OPTION_2)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3366, 3083, 0));
			else if (componentId == OPTION_3)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2470, 3436, 0));
		    else if (componentId == OPTION_4)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2562, 5739, 0));
			else if (componentId == OPTION_5) {
				sendOptionsDialogue("Teleports", "Armadyl Chamber", "Bandos Chamber", "Saradomin Chamber", "Zamorak Chamber", "Next page");
				stage = 1;
			}
		}
	}

	private void teleportPlayer(int x, int y, int z) {
		player.setNextWorldTile(new WorldTile(x, y, z));
		player.stopAll();
		player.getControlerManager().startControler("GodWars");
	}

	@Override
	public void finish() {

	}
}