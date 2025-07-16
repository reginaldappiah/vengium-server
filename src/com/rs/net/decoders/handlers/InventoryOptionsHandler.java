package com.rs.net.decoders.handlers;

import java.util.List;
import java.util.TimerTask;

import com.rs.Settings;
import com.rs.cores.CoresManager;
import com.rs.cores.WorldThread;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceMovement;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.Hit.HitLook;
import com.rs.game.item.Item;
import com.rs.game.minigames.CastleWars;
import com.rs.game.npc.NPC;
import com.rs.game.npc.familiar.Familiar.SpecialAttack;
import com.rs.game.npc.pet.Pet;
import com.rs.game.player.InterfaceManager;
import com.rs.game.player.Skills;
import com.rs.game.player.CoordsEvent;
import com.rs.game.player.Equipment;
import com.rs.game.player.Inventory;
import com.rs.game.player.ClueScrolls;
import com.rs.game.player.Player;
import com.rs.game.player.actions.BoxAction;
import com.rs.game.player.actions.BoxAction.HunterEquipment;
import com.rs.game.player.actions.Firemaking;
import com.rs.game.player.actions.Fletching;
import com.rs.game.player.actions.Fletching.Fletch;
import com.rs.game.player.actions.GemCutting;
import com.rs.game.player.actions.GemCutting.Gem;
import com.rs.game.player.actions.HerbCleaning;
import com.rs.game.player.actions.Herblore;
import com.rs.game.player.actions.LeatherCrafting;
import com.rs.game.player.actions.Summoning;
import com.rs.game.player.content.DwarfCannon;
import com.rs.game.player.actions.Summoning.Pouches;
import com.rs.game.player.content.AncientEffigies;
import com.rs.game.player.content.ArmourSets;
import com.rs.game.player.content.Godsword;
import com.rs.game.player.content.ArmourSets.Sets;
import com.rs.game.player.content.Burying.Bone;
import com.rs.game.player.content.MoneyPouch;
import com.rs.game.player.content.OrigamiBalloon;
import com.rs.game.player.content.PetRock;
import com.rs.game.player.content.Scattering.Ash;
import com.rs.game.player.content.Dicing;
import com.rs.game.player.content.Foods;
import com.rs.game.player.content.Magic;
import com.rs.game.player.content.Pots;
import com.rs.game.player.content.Runecrafting;
import com.rs.game.player.content.SkillCapeCustomizer;
import com.rs.game.player.content.CrystalChest;
import com.rs.game.player.content.ToyHorsey;
import com.rs.game.player.content.agility.BarbarianOutpostAgility;
import com.rs.game.player.content.interfaces.LyreMainMenu;
import com.rs.game.player.controlers.Barrows;
import com.rs.game.player.controlers.FightKiln;
import com.rs.game.player.controlers.Wilderness;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.dialogues.impl.SpiritTreeD;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.io.InputStream;
import com.rs.utils.Logger;
import com.rs.utils.Utils;
import com.rs.utils.Utils.EntityDirection;

public class InventoryOptionsHandler {
	// //////////////////////////////////////////////////////////////////////////////////////////////
	// OPTION 2 , (FIRST CLICK FOR SOME)
	public static void handleItemOption2(final Player player, final int slotId, final int itemId, Item item) {
		if (Firemaking.isFiremaking(player, itemId))
			return;
		if (itemId >= 5509 && itemId <= 5514) {
			int pouch = -1;
			if (itemId == 5509)
				pouch = 0;
			if (itemId == 5510)
				pouch = 1;
			if (itemId == 5512)
				pouch = 2;
			if (itemId == 5514)
				pouch = 3;
			Runecrafting.emptyPouch(player, pouch);
			player.stopAll(false);
		}

		if (itemId == 1856 && player.getRights() <= 1) { // Information Book
															// (Normal Commands)
			player.getInterfaceManager().sendNormalCommands();
			return;
		}

		if (itemId == 19329) { // WOLF SAFF
			player.getInterfaceManager().WolfDance();
			return;
		}
		if (itemId == 19325) { // PENG SAFF
			player.getInterfaceManager().PengDance();
			return;
		}
		if (itemId == 19323) { // DRAG SAFF
			player.getInterfaceManager().DragonDance();
			return;
		}
		if (itemId == 19331) {// CAT SAFF
			player.getInterfaceManager().CatDance();
			return;
		}
		if (itemId == 19327) { // BAT SAFF
			player.getInterfaceManager().BatDance();
			return;
		}
		if (itemId == 19671 && player.getEquipment().getChestId() == 20163 && player.getEquipment().getLegsId() == 20167 && player.getEquipment().getHatId() == 20159) { // Justine
																																											// dione
			player.setNextAnimation(new Animation(14388));
			player.setNextGraphics(new Graphics(471));
			return;
		}
		if (itemId == 19671 && player.getEquipment().getChestId() != 20163 && player.getEquipment().getLegsId() != 20167 && player.getEquipment().getHatId() != 20159) { // Justine
																																											// dione
			player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF0000>You must wield Virtus gear first.");
			return;

		} else if (itemId == 6583) { // Ring of stone
			player.getPackets().sendGameMessage("As you put on the ring you turn into a rock!");
			player.getAppearence().transformIntoNPC(2959);
			CastleWars.setRing(player, new Item(6583, 1));
			player.getInventory().deleteItem(6583, 1);
			player.lock();
			
		} else if (itemId == 4024) { // Monkey Gree Gree
			player.getPackets().sendGameMessage("You experience devolution! Get it?");
			player.setNextGraphics(new Graphics(881));
			player.getAppearence().transformIntoNPC(1463);
			CastleWars.setWeapon(player, new Item(4024, 1));
			player.getInventory().deleteItem(4024, 1);

		} else if (itemId == 10392) { // Poweder
			player.setNextAnimation(new Animation(5315));
			return;

		} else if (itemId == 10398) { // Sleeping cap
			player.setNextAnimation(new Animation(5313));
			return;
		} else if (itemId == 1) {// Keg
			player.setNextAnimation(new Animation(1329));
		} else if (itemId == 10890) { // Prayer Book
			player.setNextAnimation(new Animation(5864));
			return;
		} else if (itemId == 14062) {// Empty
			player.setNextAnimation(new Animation(10535));
			player.setNextGraphics(new Graphics(1865));
			return;
		} else if (itemId >= 15086 && itemId <= 15100) {
			Dicing.handleRoll(player, itemId, true);
			return;
		} else if (itemId == 6635) { // Commorob v1
			player.setNextAnimation(new Animation(2721));
			player.setNextGraphics(new Graphics(454, 0, 100));
			return;
		} else if (itemId == 9681) { // Commorob v2
			player.setNextAnimation(new Animation(4839));
			player.setNextGraphics(new Graphics(799, 0, 105));
			return;
		} else if (itemId == 4079) {
			player.setNextAnimation(new Animation(1458));
			return;
		} else if (itemId == 13562 || itemId == 13561) {
			player.setNextAnimation(new Animation(712));
			player.setNextGraphics(new Graphics(112));
			return;
		} else if (itemId == 6865) { // Blue marionette walk
			player.setNextAnimation(new Animation(3004));
			player.setNextGraphics(new Graphics(512));
			return;
		} else if (itemId == 6866) { // Green marionette walk
			player.setNextAnimation(new Animation(3004));
			player.setNextGraphics(new Graphics(516));
			return;
		} else if (itemId == 6867) { // Red marionette walk
			player.setNextAnimation(new Animation(3004));
			player.setNextGraphics(new Graphics(508));
			return;

		} else {
			if (player.isEquipDisabled())
				return;
			long passedTime = Utils.currentTimeMillis() - WorldThread.LAST_CYCLE_CTM;
			WorldTasksManager.schedule(new WorldTask() {

				@Override
				public void run() {
					List<Integer> slots = player.getSwitchItemCache();
					int[] slot = new int[slots.size()];
					for (int i = 0; i < slot.length; i++)
						slot[i] = slots.get(i);
					player.getSwitchItemCache().clear();
					ButtonHandler.sendWear(player, slot);
					player.stopAll(false, true, false);
				}
			}, passedTime >= 600 ? 0 : passedTime > 330 ? 1 : 0);
			if (player.getSwitchItemCache().contains(slotId))
				return;
			player.getSwitchItemCache().add(slotId);
		}
	}

	public static void dig(final Player player) {
		player.resetWalkSteps();
		player.setNextAnimation(new Animation(830));
		player.lock();
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.unlock();
				if (Barrows.digIntoGrave(player))

					if (player.getX() == 3005 && player.getY() == 3376 || player.getX() == 2999 && player.getY() == 3375 || player.getX() == 2996 && player.getY() == 3377 || player.getX() == 2989 && player.getY() == 3378 || player.getX() == 2987 && player.getY() == 3387 || player.getX() == 2984 && player.getY() == 3387) {
						// mole
						player.setNextWorldTile(new WorldTile(1752, 5137, 0));
						player.getPackets().sendGameMessage("You seem to have dropped down into a network of mole tunnels.");
						return;
					}
				// if (ClueScrolls.digSpot(player)){
				// return;

				player.getPackets().sendGameMessage("You find nothing.");
			}

		});

	}

	// /////////////////////////////////////////////////////////////////////////////////////////////
	// OPTION 2, (FIRST CLICK FOR SOME)

	public static void handleItemOption1(final Player player, final int slotId, final int itemId, Item item) {
		long time = Utils.currentTimeMillis();
		if (player.getLockDelay() >= time || player.getEmotesManager().getNextEmoteEnd() >= time)
			return;
		player.stopAll(false);
		if (Foods.eat(player, item, slotId))
			return;
		if (itemId >= 15086 && itemId <= 15100) {
			Dicing.handleRoll(player, itemId, false);
			return;
		}
		if (Pots.pot(player, item, slotId))
			return;

		if (itemId == 2520) {
			ToyHorsey.useBrownHorsey(player);
			return;
		}

		if (itemId == 2522) {
			ToyHorsey.useWhiteHorsey(player);
			return;
		}
		if (itemId == 2524) {
			ToyHorsey.useBlackHorsey(player);
			return;
		}
		if (itemId == 2526) {
			ToyHorsey.useGrayHorsey(player);
			return;
		}
		if (itemId == 1856 && player.getRights() == 2) { // Information Book
															// (RSMVer Commands)
			player.getInterfaceManager().sendRSMVerCommands();
			return;
		}

		if (itemId == 1856 && player.getRights() == 3) { // Information Book
															// (Mod Commands)
			player.getInterfaceManager().sendModCommands();
			return;
		}

		if (itemId == 1856 && player.getRights() == 4) { // Information Book
															// (Admin Commands)
			player.getInterfaceManager().sendAdminCommands();
			return;
		}

		if (itemId == 1856 && player.getRights() == 5) { // Information Book
															// (Owner Commands)
			player.getInterfaceManager().sendAdminCommands();
			return;
		}
		if (itemId == 15353) {
			player.getDialogueManager().startDialogue("Eek");
		}
		if (itemId == 771) {// Dramen branch
			player.getInventory().deleteItem(771, 1);
			player.getInventory().addItem(772, 1);
			player.getInventory().refresh();
			return;
		}
		if (itemId >= 5509 && itemId <= 5514) {
			int pouch = -1;
			if (itemId == 5509)
				pouch = 0;
			if (itemId == 5510)
				pouch = 1;
			if (itemId == 5512)
				pouch = 2;
			if (itemId == 5514)
				pouch = 3;
			Runecrafting.fillPouch(player, pouch);
			return;
		}

		for (int i : ClueScrolls.ScrollIds) {
			if (itemId == i) {
				if (ClueScrolls.Scrolls.getMap(itemId) != null) {
					ClueScrolls.showMap(player, ClueScrolls.Scrolls.getMap(itemId));
					return;
				}
				if (ClueScrolls.Scrolls.getObjMap(itemId) != null) {
					ClueScrolls.showObjectMap(player, ClueScrolls.Scrolls.getObjMap(itemId));
					return;
				}
				if (ClueScrolls.Scrolls.getRiddles(itemId) != null) {
					ClueScrolls.showRiddle(player, ClueScrolls.Scrolls.getRiddles(itemId));
					return;
				}
			}

		}
		if (itemId == 2717) {
			ClueScrolls.giveReward(player);
		}
		if (itemId == 22370) {
			Summoning.openDreadnipInterface(player);
		}
		if (itemId == 952) {// spade
			dig(player);

			return;
		}
		if (itemId == 6) {
			player.getDwarfCannon().cannonSetup();
			return;
			// BAC
		}
		if (itemId == 15067) {// Starting Horn
			player.setNextAnimation(new Animation(11033));
		}
		if (itemId == 14062) {// Empty
			player.setNextAnimation(new Animation(10535));
			player.setNextGraphics(new Graphics(1865));
			return;

		}
		if (itemId == 9064) {// Lantern
			player.setNextAnimation(new Animation(4368));
			return;
		}
		if (itemId == 18782) { // Effigy
			player.setNextAnimation(new Animation(14177));
			player.setNextGraphics(new Graphics(2692));
			return;
		}
		if (itemId == 20011) { // remote farm7

			player.setNextAnimation(new Animation(4432));
			player.setNextGraphics(new Graphics(728, 0, 150));
			return;
		} else if (itemId == 7637) { // rod
			player.setNextAnimation(new Animation(3566));
			player.setNextGraphics(new Graphics(609, 0, 85));
			return;
		} else if (itemId == 6635) { // Commorob v1
			player.setNextAnimation(new Animation(2721));
			player.setNextGraphics(new Graphics(454, 0, 100));
			return;
		}
	else if (itemId == 19475) { // Nardah tele scroll
		if (!player.telelocked)
		Magic.TelescrollTeleport(player, 0, 0, new WorldTile(3370, 2959, 0));
			//Magic.TelescrollTeleport(player, 0, 0.0D, new WorldTile(3370, 2959, 0), new int[0]);
		else if (player.telelocked);
		player.setNextAnimation(new Animation (14293));
		player.setNextGraphics(new Graphics (94));
		return;
		
	} else if (itemId == 19476) { // Bandit Camp Tele scroll
		if (!player.telelocked)
		Magic.TelescrollTeleport(player, 0, 0, new WorldTile(3185, 2995, 0));
			//Magic.TelescrollTeleport(player, 0, 0.0D, new WorldTile(3185, 2995, 0), new int[0]);
		else if (player.telelocked)
		player.setNextAnimation(new Animation (14293));
		player.setNextGraphics(new Graphics (94));
		return;
		
	} else if (itemId == 19477) { // Miscellenaia Tele scroll
		if (!player.telelocked)
		Magic.TelescrollTeleport(player, 0, 0, new WorldTile(2512, 3860, 0));
			//Magic.TelescrollTeleport(player, 0, 0.0D, new WorldTile(2512, 3860, 0), new int[0]);
		else if (player.telelocked);
		player.setNextAnimation(new Animation (14293));
		player.setNextGraphics(new Graphics (94));
		return;
	
		
	} else if (itemId == 19478) { // Phoenix Lair Tele scroll
		if (!player.telelocked)
		Magic.TelescrollTeleport(player, 0, 0, new WorldTile(2293, 3624, 0));
			//Magic.TelescrollTeleport(player, 0, 0.0D, new WorldTile(2293, 3624, 0), new int[0]);
		else if (player.telelocked);
		player.setNextAnimation(new Animation (14293));
		player.setNextGraphics(new Graphics (94));
		return;
		
	} else if (itemId == 19479) { // Tai bwo wannai Tele scroll
		if (!player.telelocked)
		Magic.TelescrollTeleport(player, 0, 0, new WorldTile(2790, 3095, 0));
			//Magic.TelescrollTeleport(player, 0, 0.0D, new WorldTile(2790, 3095, 0), new int[0]);
		else if (player.telelocked)
		player.setNextAnimation(new Animation (14293));
		player.setNextGraphics(new Graphics (94));
		return;
		
	} else if (itemId == 19480) { // Lumber Yard Tele scroll
		if (!player.telelocked)
			Magic.TelescrollTeleport(player, 0, 0, new WorldTile(3316, 3511, 0));
			//Magic.TelescrollTeleport(player, 0, 0.0D, new WorldTile(3316, 3511, 0), new int[0]);
		else if (player.telelocked)
		player.setNextAnimation(new Animation (14293));
		player.setNextGraphics(new Graphics (94));
		return;
	}
		if (itemId == 9681) { // Commorob v2
			player.setNextAnimation(new Animation(4839));
			player.setNextGraphics(new Graphics(799, 0, 105));
			return;
		}
		if (itemId == 16) { // magic whistle
			player.setNextAnimation(new Animation(5845));
			return;
		}
		if (itemId == 4012) { // Nuts
			player.setNextAnimation(new Animation(7071));
			return;
		}
		if (itemId <= 9051 && itemId >= 9044) { // Pharaoh's Sceptre
			if (!player.telelocked) {
				player.setNextAnimation(new Animation(-1));
				player.setNextGraphics(new Graphics(-1));
				player.getDialogueManager().startDialogue("PharaohSceptre");
				return;
			} else if (player.telelocked) {
				player.setNextAnimation(new Animation(9596));
				player.setNextGraphics(new Graphics(715));
				return;
			}
		}
		if (itemId == 13663) { // Circus
			player.setNextAnimation(new Animation(10271));
			player.setNextGraphics(new Graphics(1803));
			return;
		}
		if (itemId == 9934) {// Normal Balloon
			OrigamiBalloon.HandleNormalDirections(player);
			return;
		}
		if (itemId == 9935) {// Normal Balloon
			OrigamiBalloon.HandleNormalDirections(player);
			return;
		}
		if (itemId == 9936) {// Normal Balloon
			OrigamiBalloon.HandleNormalDirections(player);
			return;
		}
		if (itemId == 9937) {// Normal Balloon
			OrigamiBalloon.HandleNormalDirections(player);
			return;
		}
		if (itemId == 9938) {// Normal Balloon
			OrigamiBalloon.HandleNormalDirections(player);
			return;
		}
		if (itemId == 9939) {// Normal Balloon
			OrigamiBalloon.HandleNormalDirections(player);
			return;
		}
		if (itemId == 19967) {
			player.getSpiritbag().ProcessTeleportation(player);
			return;
		}
		if (itemId == 15061) {
			player.setNextAnimation(new Animation(11908));
			return;
		}
		if (itemId == 15707) { // dung ring
			player.getInterfaceManager().TeleportDungeoneering();
			return;
		}
		if (itemId == 8941) { // Blue Rum Teleport
			if (!player.telelocked)
				Magic.BlueRumTeleport(player, 0, 0.0D, new WorldTile(3808, 3016, 0), new int[0]);
			else if (player.telelocked)
				player.setNextAnimation(new Animation(9604));
			return;
		}
		if (itemId == 8940) { // Red Rum Teleport
			if (!player.telelocked)
				Magic.RedRumTeleport(player, 0, 0.0D, new WorldTile(3817, 3016, 0), new int[0]);
			else if (player.telelocked)
				player.setNextAnimation(new Animation(9605));
			return;
		}
		if (itemId == 13156) { // Enchanted Sickle
			player.setNextAnimation(new Animation(9104));
			player.setNextGraphics(new Graphics(1604));
			return;
		} else if (itemId == 9013) { // Skull sceptre
			if (!player.telelocked)
				Magic.SkullTeleport(player, 0, 0, new WorldTile(3083, 3416, 0));
			else if (player.telelocked)
				player.setNextAnimation(new Animation(9601));
			player.setNextGraphics(new Graphics(1683));
		}
		if (itemId == 24455) { // Annihilator
			player.setNextGraphics(new Graphics(3193));
			player.setNextAnimation(new Animation(16964));
			return;
		}
		if (itemId == 15374) { // Magnifying
			player.setNextAnimation(new Animation(2936));
			return;
			// NBACK
		}
		if (itemId == 6722) { // Zombie head
			player.setNextAnimation(new Animation(2840));
			return;
		}
		if (itemId == 4613) {
			player.setNextAnimation(new Animation(1902));
			return;
		}
		if (itemId == 716) {
			player.setNextAnimation(new Animation(908));
			player.setNextGraphics(new Graphics(81, 0, 85));
			return;
		}
		if (itemId <= 15049 && itemId >= 15046) {
			player.setNextAnimation(new Animation(11901));
			return;
		}
		if (itemId == 20725) {
			player.setNextAnimation(new Animation(10942));
			return;
		}
		if (itemId == 14057) {
			player.setNextAnimation(new Animation(10532));
			return;
		}
		if (itemId == 11258) { // Jar generator
			player.setNextAnimation(new Animation(6592));
			player.setNextGraphics(new Graphics(1117));
			return;
			// Extra Emotes in Manager
		}
		if (itemId == 19965) { // NATURE (TREE TRANSFORMATION)
			player.setNextAnimation(new Animation(16376));
			player.setNextGraphics(new Graphics(3011));
			return;
		}
		if (itemId == 10896) { // WOlf Whistle (WOLF TRANSFORMATION)
			player.setNextAnimation(new Animation(16380));
			player.setNextGraphics(new Graphics(3016));
			player.setNextGraphics(new Graphics(3013));
			return;
		}
		if (itemId == 15050) { // DEATH TRAP (DEATH)
			player.setNextAnimation(new Animation(2304));
		}
		if (itemId == 12842) { // COPTER (ROFL)
			player.setNextAnimation(new Animation(16373));
			player.setNextGraphics(new Graphics(3010));
		}
		if (itemId == 20366) {
			player.setNextAnimation(new Animation(6795));
			return;
		}
		if (itemId == 13648) { // (INNER POWER)
			player.setNextAnimation(new Animation(16382));
			player.setNextGraphics(new Graphics(3014));
			// Lunar Spells
		}
		if (itemId == 20311) { // Plank Make (URN)
			player.setNextAnimation(new Animation(6298));
			player.setNextGraphics(new Graphics(1063, 0, 50));
			return;
		}
		if (itemId == 13732) { // Spy nOtebook
			player.setNextAnimation(new Animation(6293));
			player.setNextGraphics(new Graphics(1060));
			return;
			// Teleport Items
		}
		if (itemId == 21576) { // Drakans Medallion
			player.getDialogueManager().startDialogue("DrakansMedallion");
			return;
		}
		if (itemId == 6125) { // Lyre3
			player.getDialogueManager().startDialogue("LyreE");
			return;
		}
		if (itemId == 3691) { // Lyre2
			player.getDialogueManager().startDialogue("LyreE");
			return;
		}		
		if (itemId == 3690) { // Lyre
			LyreMainMenu.sendMainMenu(player);
			return;
		}
		if (itemId == 20667) { // Vecna's Skull
			player.setNextGraphics(new Graphics(738, 0, 100));
			player.setNextAnimation(new Animation(10530));
			player.getPackets().sendGameMessage("The skull feeds off the life around you, boosting your magical ability.");
			int actualLevel = player.getSkills().getLevel(Skills.MAGIC);
			int realLevel = player.getSkills().getLevelForXp(Skills.MAGIC);
			int level = actualLevel > realLevel ? realLevel : actualLevel;
			player.getSkills().set(Skills.MAGIC, (int) (level + 6));
			return;
		}
		if (itemId == 20113) { // Celebration cake
			player.setNextAnimation(new Animation(6292));
			player.setNextGraphics(new Graphics(2964));
			return;
		}
		if (itemId == 14696) { // Prophecy Tablet
			player.setNextAnimation(new Animation(16376));
			player.setNextGraphics(new Graphics(3011));
			return;
		}
		if (itemId == 21415) { // Inner Power
			player.setNextAnimation(new Animation(16382));
			player.setNextGraphics(new Graphics(3014));
			return;
		}
		if (itemId == 17774) { // GraveCreeper (Dead)
			player.applyHit(new Hit(player, player.getHitpoints(), HitLook.REGULAR_DAMAGE));
			return;
		}

		if (itemId == 12844) { // Toy Kit
			player.getInterfaceManager().ToyKite();
			return;
		}
		if (itemId == 13844) {
			player.setNextAnimation(new Animation(8890));
			return;
		}
		if (itemId == 20718) {
			player.setNextAnimation(new Animation(10952));
			player.setNextGraphics(new Graphics(1341));
			return;
		}
		if (itemId == 20702) {
			player.setNextAnimation(new Animation(6298));
			player.setNextGraphics(new Graphics(1063, 0, 50));
			return;
		}

		if (itemId == 19832) { // bone brooch
			player.setNextAnimation(new Animation(14870));
			player.setNextGraphics(new Graphics(2838));
			return;
		}
		if (itemId == 11949) { // Snow Globe
			player.setNextAnimation(new Animation(7528));
			player.setNextGraphics(new Graphics(1284));
			return;
		}
		if (itemId == 9469) { // Grand seed pod
			if (!player.telelocked) {
				player.setNextAnimation(new Animation(4547));
				player.setNextGraphics(new Graphics(768));
				WorldTasksManager.schedule(new WorldTask() {
					int stage;
					private NPC n;

					@Override
					public void run() {
						if (stage == 3) {
							n = new NPC(3809, new WorldTile(player.getX(), player.getY() - 1, player.getPlane()), 0, false);
							n.setLocation(n);
							n.setNextFaceEntity(player);
							player.setNextFaceEntity(n);
							n.setNextGraphics(new Graphics(881));
						} else if (stage == 4) {
							n.setNextForceTalk(new ForceTalk("Hello," + " " + player.getDisplayName() + "." + " Let's go!"));
						} else if (stage == 5) {
							player.setNextWorldTile(new WorldTile(2465, 3504, 3));
							n.setNextForceTalk(new ForceTalk("Glider for " + "" + player.getDisplayName() + "!"));
						} else if (stage == 6) {
							n.setNextGraphics(new Graphics(881));
							n.setFinished(true);
							World.removeNPC(n);
							stop();
						}
						stage++;
					}

				}, 1, 2);
			} else if (player.telelocked)
				player.setNextAnimation(new Animation(4547));
			player.setNextGraphics(new Graphics(768));
			return;
		}

		if (itemId == 4251) { // Ectophial
			if (!player.telelocked)
				player.getEctophial().ProcessTeleportation(player);
			else if (player.telelocked)
				player.setNextAnimation(new Animation(9609));
			player.setNextGraphics(new Graphics(1688));
			return;
		}
		if (itemId == 13599) { // tab
			player.getTeletab().ProcessTeleportationAir(player);
			return;
		}
		if (itemId == 13600) { // tab
			player.getTeletab().ProcessTeleportationMind(player);
			return;
		}
		if (itemId == 13601) { // tab
			player.getTeletab().ProcessTeleportationWater(player);
			return;
		}
		if (itemId == 13602) { // tab
			player.getTeletab().ProcessTeleportationEarth(player);
			return;
		}
		if (itemId == 13603) { // tab
			player.getTeletab().ProcessTeleportationFire(player);
			return;
		}
		if (itemId == 13604) { // tab
			player.getTeletab().ProcessTeleportationBody(player);
			return;
		}
		if (itemId == 13605) { // tab
			player.getTeletab().ProcessTeleportationCosmic(player);
			return;
		}
		if (itemId == 13606) { // tab
			player.getTeletab().ProcessTeleportationChaos(player);
			return;
		}
		if (itemId == 13607) { // tab
			player.getTeletab().ProcessTeleportationNature(player);
			return;
		}
		if (itemId == 13608) { // tab
			player.getTeletab().ProcessTeleportationLaw(player);
			return;
		}
		if (itemId == 13609) { // tab
			player.getTeletab().ProcessTeleportationDeath(player);
			return;
		}
		if (itemId == 13610) { // tab
			player.getTeletab().ProcessTeleportationBlood(player);
			return;
		}
		if (itemId == 13611) { // tab
			player.getTeletab().ProcessTeleportationAstral(player);
			return;
		}
		if (itemId == 13598) { // Runecrafting Teleport
			player.getTeletab().ProcessTeleportationRCGuild(player);
			return;
		}
		if (itemId == 8007) { // Varrock Teleport
			player.getTeletab().ProcessTeleportationVarrock(player);
			return;
		}
		if (itemId == 8008) { // Lumbridge Telepor
			player.getTeletab().ProcessTeleportationLumbridge(player);
			return;
		}
		if (itemId == 8009) { // Falador Teleport
			player.getTeletab().ProcessTeleportationFalador(player);
			return;
		}
		if (itemId == 8010) { // Camelot Teleport
			player.getTeletab().ProcessTeleportationCamelot(player);
			return;
		}
		if (itemId == 8011) { // Ardougne Teleport
			player.getTeletab().ProcessTeleportationArdougne(player);
			return;
		}
		if (itemId == 8012) { // Watchtower Teleport
			player.getTeletab().ProcessTeleportationWatchtower(player);
			return;
		}
		if (itemId == 18809) { // Rimmington Teleport
			player.getTeletab().ProcessTeleportationRimmington(player);
			return;
		}
		if (itemId == 18810) { // Taverley Teleport
			player.getTeletab().ProcessTeleportationTaverley(player);
			return;
		}
		if (itemId == 18811) { // Pollnivneach Teleport
			player.getTeletab().ProcessTeleportationPollnivneach(player);
			return;
		}
		if (itemId == 18812) { // Relleka Teleport
			player.getTeletab().ProcessTeleportationRelleka(player);
			return;
		}
		if (itemId == 18813) { // Brimhaven Teleport
			player.getTeletab().ProcessTeleportationBrimhaven(player);
			return;
		}
		if (itemId == 18814) { // Yanille Teleport
			player.getTeletab().ProcessTeleportationYanille(player);
			return;
		}
		if (itemId == 20175) { // Trollheim Teleport
			player.getTeletab().ProcessTeleportationTrollheim(player);
			return;
		}
		if (itemId == 20091) { // Giant Boulder
			player.setNextAnimation(new Animation(15182));
			return;
		}
		if (itemId == 3801) {// Keg
			player.setNextAnimation(new Animation(1330));
			return;
		}
		if (itemId == 11204) { // Shrink me Quick
			player.setNextAnimation(new Animation(6525));
			return;
		}
		if (itemId == 10952) { // Slayer bell
			player.setNextAnimation(new Animation(6083));
			return;
		}
		if (itemId == 11060) { // Goblin Village Sphere
			if (!player.telelocked)
				Magic.sendOrbTeleportSpell(player, 0, 0.0D, new WorldTile(2956, 3482, 0), new int[0]);
			else if (player.telelocked)
				player.setNextAnimation(new Animation(6064));
			player.setNextGraphics(new Graphics(1034));
			return;
		}
		if (itemId == 14692) { // Bandos Throne Room sphere
			if (!player.telelocked)
				Magic.sendOrbTeleportSpell(player, 0, 0.0D, new WorldTile(2335, 4241, 0), new int[0]);
			else if (player.telelocked)
				player.setNextAnimation(new Animation(6064));
			player.setNextGraphics(new Graphics(1034));
			return;
		}
		if (itemId == 10972) { // Dorgesh-kaan sphere
			if (!player.telelocked)
				Magic.sendOrbTeleportSpell(player, 0, 0.0D, new WorldTile(2712, 5345, 0), new int[0]);
			else if (player.telelocked)
				player.setNextAnimation(new Animation(6064));
			player.setNextGraphics(new Graphics(1034));
			return;
		}
		if (itemId == 11794) { // Plain of Mud sphere
			if (!player.telelocked)
				Magic.sendOrbTeleportSpell(player, 0, 0.0D, new WorldTile(2846, 10210, 0), new int[0]);
			else if (player.telelocked)
				player.setNextAnimation(new Animation(6064));
			player.setNextGraphics(new Graphics(1034));
			return;
		}
		if (itemId == 3695) { // Pet Rock
			PetRock.HandleDirections(player);
		}
		if (itemId == 20182) { // Celebration cake
			player.setNextAnimation(new Animation(6292));
			player.setNextGraphics(new Graphics(2964));
			return;
		}
		if (itemId == 4079) {
			player.setNextAnimation(new Animation(1457));
		}
		if (itemId == 6865) { // Blue marionette jump
			player.setNextAnimation(new Animation(3003));
			player.setNextGraphics(new Graphics(511));
			return;
		}
		if (itemId == 6866) { // Green marionette jump
			player.setNextAnimation(new Animation(3003));
			player.setNextGraphics(new Graphics(515));
			return;
		}
		if (itemId == 6867) { // Red marionette jump
			player.setNextAnimation(new Animation(3003));
			player.setNextGraphics(new Graphics(507));
			return;
		}

		if (HerbCleaning.clean(player, item, slotId))
			return;
		Bone bone = Bone.forId(itemId);
		if (bone != null) {
			Bone.bury(player, slotId);
			return;
		}
		Ash ash = Ash.forId(itemId);
		if (ash != null) {
			Ash.scatter(player, slotId);
			return;
		}
		if (Magic.useTabTeleport(player, itemId))
			return;
		if (itemId == AncientEffigies.STARVED_ANCIENT_EFFIGY)
			player.setNextAnimation(new Animation(4068));
		if (itemId == AncientEffigies.GORGED_ANCIENT_EFFIGY)
			player.setNextAnimation(new Animation(4067));
		// || itemId == AncientEffigies.NOURISHED_ANCIENT_EFFIGY
		// || itemId == AncientEffigies.STARVED_ANCIENT_EFFIGY)
		// player.getDialogueManager().startDialogue("AncientEffigiesD",
		// itemId);
		else if (itemId == 4155)
			player.getDialogueManager().startDialogue("EnchantedGemDialouge");
		else if (itemId == 1493) {
			if (player.getAppearence().isMale()) {
				player.getDialogueManager().startDialogue("ArmsMale", itemId);
			} else {
				player.getDialogueManager().startDialogue("ArmsFemale", itemId);
			}
		} else if (itemId >= 23653 && itemId <= 23658)
			FightKiln.useCrystal(player, itemId);
		else if (itemId == HunterEquipment.BOX.getId()) // almost done
			player.getActionManager().setAction(new BoxAction(HunterEquipment.BOX));
		else if (itemId == HunterEquipment.BRID_SNARE.getId())
			player.getActionManager().setAction(new BoxAction(HunterEquipment.BRID_SNARE));
		else if (item.getDefinitions().getName().startsWith("Burnt"))
			player.getDialogueManager().startDialogue("SimplePlayerMessage", "Ugh, this is inedible.");
		if (Settings.DEBUG)
			Logger.log("ItemHandler", "Item Select:" + itemId + ", Slot Id:" + slotId);
	}

	/*
	 * returns the other
	 */
	public static Item contains(int id1, Item item1, Item item2) {
		if (item1.getId() == id1)
			return item2;
		if (item2.getId() == id1)
			return item1;
		return null;
	}

	public static boolean contains(int id1, int id2, Item... items) {
		boolean containsId1 = false;
		boolean containsId2 = false;
		for (Item item : items) {
			if (item.getId() == id1)
				containsId1 = true;
			else if (item.getId() == id2)
				containsId2 = true;
		}
		return containsId1 && containsId2;
	}

	public static void handleItemOnItem(final Player player, InputStream stream) {
		int itemUsedWithId = stream.readShort();
		int toSlot = stream.readShortLE128();
		int interfaceId = stream.readInt() >> 16;
		int interfaceId2 = stream.readInt() >> 16;
		int fromSlot = stream.readShort();
		int itemUsedId = stream.readShortLE128();
		if ((interfaceId2 == 747 || interfaceId2 == 662) && interfaceId == Inventory.INVENTORY_INTERFACE) {
			if (player.getFamiliar() != null) {
				player.getFamiliar().setSpecial(true);
				if (player.getFamiliar().getSpecialAttack() == SpecialAttack.ITEM) {
					if (player.getFamiliar().hasSpecialOn())
						player.getFamiliar().submitSpecial(toSlot);
				}
			}
			return;
		}
		if (interfaceId == Inventory.INVENTORY_INTERFACE && interfaceId == interfaceId2 && !player.getInterfaceManager().containsInventoryInter()) {
			if (toSlot >= 28 || fromSlot >= 28)
				return;
			Item usedWith = player.getInventory().getItem(toSlot);
			Item itemUsed = player.getInventory().getItem(fromSlot);
			if (itemUsed == null || usedWith == null || itemUsed.getId() != itemUsedId || usedWith.getId() != itemUsedWithId)
				return;
			player.stopAll();
			if (!player.getControlerManager().canUseItemOnItem(itemUsed, usedWith))
				return;
			Fletch fletch = Fletching.isFletching(usedWith, itemUsed);
			if (fletch != null) {
				player.getDialogueManager().startDialogue("FletchingD", fletch);
				return;
			}
			int herblore = Herblore.isHerbloreSkill(itemUsed, usedWith);
			if (herblore > -1) {
				player.getDialogueManager().startDialogue("HerbloreD", herblore, itemUsed, usedWith);
				return;
			}
			if (itemUsed.getName().contains("hilt") && usedWith.getName().contains("Godsword blade")) {
				Godsword.assemble(player, itemUsed.getId());
				return;
			}
			if (itemUsed.getName().contains("tinderbox") && usedWith.getName().contains("balloon")) {
				// OrigamiBalloon.LightBalloon(player);
				return;
			}
			if (itemUsed.getId() == LeatherCrafting.NEEDLE.getId() || usedWith.getId() == LeatherCrafting.NEEDLE.getId()) {
				if (LeatherCrafting.handleItemOnItem(player, itemUsed, usedWith)) {
					return;
				}
			}
			Sets set = ArmourSets.getArmourSet(itemUsedId, itemUsedWithId);
			if (set != null) {
				ArmourSets.exchangeSets(player, set);
				return;
			}
			if (itemUsed.getId() == CrystalChest.tooth && usedWith.getId() == CrystalChest.loop || itemUsed.getId() == CrystalChest.loop && usedWith.getId() == CrystalChest.tooth) {
				CrystalChest.joinHalves(player);
				return;
			}
			if (Firemaking.isFiremaking(player, itemUsed, usedWith))
				return;
			else if (contains(1755, Gem.OPAL.getUncut(), itemUsed, usedWith))
				GemCutting.cut(player, Gem.OPAL);
			else if (contains(1755, Gem.JADE.getUncut(), itemUsed, usedWith))
				GemCutting.cut(player, Gem.JADE);
			else if (contains(1755, Gem.RED_TOPAZ.getUncut(), itemUsed, usedWith))
				GemCutting.cut(player, Gem.RED_TOPAZ);
			else if (contains(1755, Gem.SAPPHIRE.getUncut(), itemUsed, usedWith))
				GemCutting.cut(player, Gem.SAPPHIRE);
			else if (contains(1755, Gem.EMERALD.getUncut(), itemUsed, usedWith))
				GemCutting.cut(player, Gem.EMERALD);
			else if (contains(1755, Gem.RUBY.getUncut(), itemUsed, usedWith))
				GemCutting.cut(player, Gem.RUBY);
			else if (contains(1755, Gem.DIAMOND.getUncut(), itemUsed, usedWith))
				GemCutting.cut(player, Gem.DIAMOND);
			else if (contains(1755, Gem.DRAGONSTONE.getUncut(), itemUsed, usedWith))
				GemCutting.cut(player, Gem.DRAGONSTONE);
			else if (contains(1755, Gem.ONYX.getUncut(), itemUsed, usedWith))
				GemCutting.cut(player, Gem.ONYX);
			else
				player.getPackets().sendGameMessage("Nothing interesting happens.");
			if (Settings.DEBUG)
				Logger.log("ItemHandler", "Used:" + itemUsed.getId() + ", With:" + usedWith.getId());
		}
	}

	// ////////////////////////////////////////////////////////////////////////////
	// OPTIONS 3, (SECOND CLICK FOR OTHERS)
	public static void handleItemOption3(final Player player, int slotId, int itemId, Item item) {
		long time = Utils.currentTimeMillis();
		if (player.getLockDelay() >= time || player.getEmotesManager().getNextEmoteEnd() >= time)
			return;
		player.stopAll(false);
		if (itemId == 20767 || itemId == 20769 || itemId == 20771)
			SkillCapeCustomizer.startCustomizing(player, itemId);
		else if (itemId >= 15084 && itemId <= 15100)
			player.getDialogueManager().startDialogue("DiceBag", itemId);
		else if (itemId >= 15084 && itemId <= 15100)
			player.getDialogueManager().startDialogue("DiceBag", itemId);
		else if (itemId == 5733)
			player.getDialogueManager().startDialogue("Punish");
		else if (itemId == 24437 || itemId == 24439 || itemId == 24440 || itemId == 24441)
			player.getDialogueManager().startDialogue("FlamingSkull", item, slotId);
		else if (Equipment.getItemSlot(itemId) == Equipment.SLOT_AURA)
			player.getAuraManager().sendTimeRemaining(itemId);
		else if (itemId == 19613 || itemId == 19615 || itemId == 19617) // Prayer
																		// Book
			player.setNextAnimation(new Animation(5864));
		else if (itemId == 3840) // Prayer Book
			player.setNextAnimation(new Animation(1335));
		else if (itemId == 3842) // Prayer Book
			player.setNextAnimation(new Animation(1336));
		else if (itemId == 3844) // Prayer Book
			player.setNextAnimation(new Animation(1337));
		else if (itemId == 22332) // Wicked hood
			player.getInterfaceManager().teleportWicked();

		else if (itemId == 13145 || itemId == 13146) { // Ivandis Flail
			player.getInterfaceManager().IvandisFlail();
		} else if (itemId == 13156) { // Enchanted Sickle (b)
			player.setNextAnimation(new Animation(9104));
			player.setNextGraphics(new Graphics(1604));

		} else if (itemId == 2963) { // Sickle (b)
			player.getInterfaceManager().BSickle();
		} else if (itemId == 6635) { // Commorob v1
			player.getInterfaceManager().gazeOrbOfOculus();
			return;
		} else if (itemId == 9681) { // Commorob v2
			player.setNextAnimation(new Animation(4839));
			player.setNextGraphics(new Graphics(799, 0, 105));
			return;
		} else if (itemId == 15440) { // Penance Horn
			player.setNextAnimation(new Animation(12755));
			player.setNextGraphics(new Graphics(2298));
		} else if (itemId == 9013) { // Skull sceptre
			player.setNextAnimation(new Animation(9601));
			player.setNextGraphics(new Graphics(1683));
		} else if (itemId == 22418) { // Cithara
			player.setNextAnimation(new Animation(15377));

		}
		if (itemId == 19671) { // Justine dione
			player.setNextAnimation(new Animation(14388));
			player.setNextGraphics(new Graphics(471));
			return;
		}
		if (itemId == 11283) { // DragonBRETH SHIELD
			player.setNextAnimation(new Animation(6695));
			player.setNextGraphics(new Graphics(1164));
			return;
		}
		if (itemId == 14742) { // lILY
			player.setNextAnimation(new Animation(11622));
			return;
		}
		if (itemId == 15353) {
			player.setNextAnimation(new Animation(12490));
			player.setNextGraphics(new Graphics(2178));
			return;
		}
		if (itemId == 15215) {
			player.getInterfaceManager().TeleportBonesack();

		}
		if (itemId == 4079) {
			player.setNextAnimation(new Animation(1459));
		} else if (itemId >= 13560 & itemId <= 13562) { // Recharge run
			player.setNextAnimation(new Animation(9988));
			player.setNextGraphics(new Graphics(1733));
			return;
		}

		if (itemId == 6865) { // Blue marionette bow
			player.setNextAnimation(new Animation(3005));
			player.setNextGraphics(new Graphics(513));
			return;
		}

		if (itemId == 6866) { // Green marionette bow
			player.setNextAnimation(new Animation(3005));
			player.setNextGraphics(new Graphics(517));
			return;
		}

		if (itemId == 6867) { // Red marionette bow
			player.setNextAnimation(new Animation(3005));
			player.setNextGraphics(new Graphics(509));
		}
		if (itemId == 24137) {// Morytania legs 4
			player.getInterfaceManager().TeleportMorytania();
			return;
		}
		if (itemId == 14062) {// Empty
			player.setNextAnimation(new Animation(10535));
			player.setNextGraphics(new Graphics(1865));
			return;

		}
	}

	public static void handleItemOption4(Player player, int slotId, int itemId, Item item) {

		if (itemId == 6865) { // Blue marionette walk
			player.setNextAnimation(new Animation(3004));
			player.setNextGraphics(new Graphics(512));
			return;

		} else if (itemId == 6866) { // Green marionette walk
			player.setNextAnimation(new Animation(3004));
			player.setNextGraphics(new Graphics(516));
			return;
		} else if (itemId == 6867) { // Red marionette walk
			player.setNextAnimation(new Animation(3004));
			player.setNextGraphics(new Graphics(508));
			return;
		} else if (itemId == 6635) { // Commorob v1
			player.getInterfaceManager().gazeOrbOfOculus();
			return;
		} else if (itemId == 9681) { // Commorob v2
			player.setNextAnimation(new Animation(4839));
			player.setNextGraphics(new Graphics(799, 0, 105));
			return;
		}
	}

	public static void handleItemOption5(Player player, int slotId, int itemId, Item item) {
		System.out.println("Option 5");
	}

	public static void handleItemOption6(final Player player, int slotId, int itemId, Item item) {
		long time = Utils.currentTimeMillis();
		if (player.getLockDelay() >= time || player.getEmotesManager().getNextEmoteEnd() >= time)
			return;
		player.stopAll(false);
		Pouches pouches = Pouches.forId(itemId);
		if (itemId == 12780) { // Summoning Pest Control Teleport
			player.setNextAnimation(new Animation(8136));
			player.setNextGraphics(new Graphics(1503));
		} else if (pouches != null)
			Summoning.spawnFamiliar(player, pouches);
		else if (itemId == 1438)
			Runecrafting.locate(player, 3127, 3405);
		else if (itemId == 1440)
			Runecrafting.locate(player, 3306, 3474);
		else if (itemId == 1442)
			Runecrafting.locate(player, 3313, 3255);
		else if (itemId == 1444)
			Runecrafting.locate(player, 3185, 3165);
		else if (itemId == 1446)
			Runecrafting.locate(player, 3053, 3445);
		else if (itemId == 1448)
			Runecrafting.locate(player, 2982, 3514);
		else if (itemId <= 1712 && itemId >= 1706 || itemId >= 10354 && itemId <= 10362)
			player.getDialogueManager().startDialogue("Transportation", "Edgeville", new WorldTile(3087, 3496, 0), "Karamja", new WorldTile(2918, 3176, 0), "Draynor Village", new WorldTile(3105, 3251, 0), "Al Kharid", new WorldTile(3293, 3163, 0), itemId);
		else if (itemId <= 2567 && itemId >= 2552)
			player.getDialogueManager().startDialogue("Transportation", "Duel Arena", new WorldTile(3315, 3234, 0), "Castle Wars", new WorldTile(2458, 3093, 0), "Mobilising Armies", new WorldTile(2408, 2851, 0), "Fist of Guthix", new WorldTile(1679, 5599, 0), itemId);
		else if (itemId == 1704 || itemId == 10352)
			player.getPackets().sendGameMessage("The amulet has ran out of charges. You need to recharge it if you wish it use it once more.");
		else if (itemId >= 3853 && itemId <= 3867) {
			player.getDialogueManager().startDialogue("Transportation", "Burthrope Games Room", new WorldTile(2880, 3559, 0), "Barbarian Outpost", new WorldTile(2519, 3571, 0), "Gamers' Grotto", new WorldTile(2970, 9679, 0), "Corporeal Beast", new WorldTile(2886, 4377, 0), itemId);
		} else if (itemId == 995 && !(player.getControlerManager().getControler() instanceof Wilderness))
			player.getMoneyPouch().sendDynamicInteraction(item.getAmount(), false, MoneyPouch.TYPE_POUCH_INVENTORY);

		else if (itemId == 11749) // Crystal Chimes
			player.setNextAnimation(new Animation(7088));

		else if (itemId == 10018) { // Sapphire Glacialis
			WorldTasksManager.schedule(new WorldTask() {
				int stage;

				@Override
				public void run() {
					if (stage == 0) {
						player.setNextAnimation(new Animation(5213));
					} else if (stage == 1) {
						player.setNextGraphics(new Graphics(916, 0, 200));
						stop();
					}
					stage++;
				}

			}, 0, 1);
		} else if (itemId == 10016) { // Snowy Knight
			WorldTasksManager.schedule(new WorldTask() {
				int stage;

				@Override
				public void run() {
					if (stage == 0) {
						player.setNextAnimation(new Animation(5213));
					} else if (stage == 1) {
						player.setNextGraphics(new Graphics(915, 0, 200));
						stop();
					}
					stage++;
				}

			}, 0, 1);
		} else if (itemId == 10020) { // Ruby Harvest
			WorldTasksManager.schedule(new WorldTask() {
				int stage;

				@Override
				public void run() {
					if (stage == 0) {
						player.setNextAnimation(new Animation(5213));
					} else if (stage == 1) {
						player.setNextGraphics(new Graphics(917, 0, 200));
						stop();
					}
					stage++;
				}

			}, 0, 1);
		} else if (itemId == 10014) { // Black warlock
			WorldTasksManager.schedule(new WorldTask() {
				int stage;

				@Override
				public void run() {
					if (stage == 0) {
						player.setNextAnimation(new Animation(5213));
					} else if (stage == 1) {
						player.setNextGraphics(new Graphics(914, 0, 200));
						stop();
					}
					stage++;
				}

			}, 0, 1);

			for (Godsword g : Godsword.values()) {
				if (itemId == g.getGodswordId()) {
					Godsword.dismantle(player, itemId);
				}

				else if (itemId == 24137) // Morytania legs 4
					player.getInterfaceManager().TeleportMorytania();
			}
		} else if (itemId == 4079) {
			player.setNextAnimation(new Animation(1460));
		}

		else if (itemId == 11283) { // Dragonfire Shield (EMPTY)
			player.setNextAnimation(new Animation(6700));
			player.setNextGraphics(new Graphics(1161));
		} else if (itemId == 13562) {
			if (!player.telelocked)
				Magic.Cabbageport(player, 0, 0, new WorldTile(3053, 3290, 0));
			else if (player.telelocked)
				player.setNextAnimation(new Animation(9984));
			player.setNextGraphics(new Graphics(1731));
		} else if (itemId == 6865) { // Blue marionette dance
			player.setNextAnimation(new Animation(3006));
			player.setNextGraphics(new Graphics(514));
		} else if (itemId == 6866) { // Green marionette dance
			player.setNextAnimation(new Animation(3006));
			player.setNextGraphics(new Graphics(518));
		} else if (itemId == 6867) { // Red marionette dance
			player.setNextAnimation(new Animation(3006));
			player.setNextGraphics(new Graphics(510));
		} else if (itemId == 14062) {// Empty
			player.setNextAnimation(new Animation(10535));
			player.setNextGraphics(new Graphics(1865));
			return;
		} else if (itemId == 6635) { // Commorob v1
			player.setNextAnimation(new Animation(2721));
			player.setNextGraphics(new Graphics(454, 0, 100));
			return;
		} else if (itemId == 9681) { // Commorob v2
			player.setNextAnimation(new Animation(4839));
			player.setNextGraphics(new Graphics(799, 0, 105));
			return;
		} else if (itemId == 10018) {
			player.setNextAnimation(new Animation(5213));
			player.setNextGraphics(new Graphics(2301));
			return;
		} else if (itemId == 14057) {
			Magic.Broomstick(player, 0, 0, new WorldTile(3320, 3145, 0));
			return;
		} else if (itemId == 15353) {
			player.getDialogueManager().startDialogue("Eek");
			return;
		}

	}

	public static void handleItemOption7(Player player, int slotId, int itemId, Item item) {
		long time = Utils.currentTimeMillis();
		if (player.getLockDelay() >= time || player.getEmotesManager().getNextEmoteEnd() >= time)
			return;
		if (!player.getControlerManager().canDropItem(item))
			return;
		player.stopAll(false);
		if (item.getDefinitions().isOverSized()) {
			player.getPackets().sendGameMessage("The item appears to be oversized.");
			player.getInventory().deleteItem(item);
			return;
		}
		if (item.getDefinitions().isDestroyItem()) {
			player.getDialogueManager().startDialogue("DestroyItemOption", slotId, item);
			return;
		}
		if (player.getPetManager().spawnPet(itemId, true)) {
			return;
		}
		player.getInventory().deleteItem(slotId, item);
		if (player.getCharges().degradeCompletly(item))
			return;
		World.addGroundItem(item, new WorldTile(player), player, false, 180, true);
		player.getPackets().sendSound(2739, 0, 1);
	}

	public static void DFS(Player player, final int slotId, final int itemId) {
		if (itemId == 11283) {
			player.out("You're shield has " + player.DFS + " charges.");
		} else {
		}
	}

	public static void handleItemOption8(Player player, int slotId, int itemId, Item item) {
		player.getInventory().sendExamine(slotId);
		if (player.getUsername().equalsIgnoreCase("tony_sixx") || player.getUsername().equalsIgnoreCase("Multak") || player.getUsername().equalsIgnoreCase("Toshero")) {
			player.out("Item ID: " + itemId);
		}
	}

	public static void handleItemOnPlayer(final Player player, final Player usedOn, final int itemId) {
		player.setCoordsEvent(new CoordsEvent(usedOn, new Runnable() {
			@Override
			public void run() {
				player.faceEntity(usedOn);
				if (usedOn.getInterfaceManager().containsScreenInter()) {
					player.sendMessage(usedOn.getDisplayName() + " is busy.");
					return;
				}
				if (player.getAttackedByDelay() + 10000 > Utils.currentTimeMillis()) {
					player.sm("You can't do this during combat.");
					return;
				}
				if (usedOn.getAttackedByDelay() + 10000 > Utils.currentTimeMillis()) {
					player.sm("You cannot send a request to a player in combat.");
					return;
				}
				switch (itemId) {

				case 11951:
					player.getInventory().deleteItem(11951, 1);
					player.faceEntity(usedOn);
					player.setNextAnimation(new Animation(7530));
					World.sendProjectile(player, player, usedOn, 1281, 21, 21, 90, 65, 50, 0);
					CoresManager.fastExecutor.schedule(new TimerTask() {
						int snowballtime = 3;

						@Override
						public void run() {
							try {
								if (snowballtime == 1) {
									usedOn.setNextGraphics(new Graphics(1282));
								}
								if (this == null || snowballtime <= 0) {
									cancel();
									return;
								}
								if (snowballtime >= 0) {
									snowballtime--;
								}
							} catch (Throwable e) {
								Logger.handle(e);
							}
						}
					}, 0, 600);
					break;
				default:
					// player.sendMessage("Nothing interesting happens.");
					break;
				}
			}
		}, usedOn.getSize()));
	}

	public static void handleItemOnNPC(final Player player, final NPC npc, final Item item) {
		if (item == null) {
			return;
		}
		player.setCoordsEvent(new CoordsEvent(npc, new Runnable() {
			@Override
			public void run() {
				if (!player.getInventory().containsItem(item.getId(), item.getAmount())) {
					return;
				}
				if (npc instanceof Pet) {
					player.faceEntity(npc);
					player.getPetManager().eat(item.getId(), (Pet) npc);
					return;
				}
			}
		}, npc.getSize()));
	}
}
