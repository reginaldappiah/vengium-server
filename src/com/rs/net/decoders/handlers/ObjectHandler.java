package com.rs.net.decoders.handlers;

import java.util.concurrent.TimeUnit;

import com.rs.Settings;
import com.rs.cache.loaders.ObjectDefinitions;
import com.rs.cores.CoresManager;
import com.rs.game.Animation;
import com.rs.game.ForceMovement;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.minigames.CastleWars;
import com.rs.game.minigames.Crucible;
import com.rs.game.minigames.FightPits;
import com.rs.game.player.ArdyFarming;
import com.rs.game.player.CathFarming;
import com.rs.game.player.CoordsEvent;
import com.rs.game.player.CutscenesManager;
import com.rs.game.player.FarmingManager;
import com.rs.game.player.OwnedObjectManager;
import com.rs.game.player.Player;
import com.rs.game.player.ClueScrolls;
import com.rs.game.player.QuestManager.Quests;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Bonfire;
import com.rs.game.player.actions.BoxAction.HunterEquipment;
import com.rs.game.player.actions.BoxAction.HunterNPC;
import com.rs.game.player.actions.Cooking;
import com.rs.game.player.actions.Cooking.Cookables;
import com.rs.game.player.actions.CowMilkingAction;
import com.rs.game.player.actions.PlayerCombat;
import com.rs.game.player.actions.Summoning;
import com.rs.game.player.actions.Smithing.ForgingBar;
import com.rs.game.player.actions.Smithing.ForgingInterface;
import com.rs.game.player.actions.Woodcutting;
import com.rs.game.player.actions.Woodcutting.TreeDefinitions;
import com.rs.game.player.actions.mining.EssenceMining;
import com.rs.game.player.actions.mining.EssenceMining.EssenceDefinitions;
import com.rs.game.player.actions.mining.Mining;
import com.rs.game.player.actions.mining.Mining.RockDefinitions;
import com.rs.game.player.actions.mining.MiningBase;
import com.rs.game.player.actions.runecrafting.SihponActionNodes;
import com.rs.game.player.actions.thieving.Thieving;
import com.rs.game.player.content.BonesOnAltar;
import com.rs.game.player.content.BonesOnAltar.Bones;
import com.rs.game.player.content.ClanCitadelTheatre;
import com.rs.game.player.content.Hunter;
import com.rs.game.player.content.Magic;
import com.rs.game.player.content.SkillCapeCustomizer;
import com.rs.game.player.content.SpiritTree;
import com.rs.game.player.content.PartyRoom;
import com.rs.game.player.content.PolyporeDungeon;
import com.rs.game.player.content.Runecrafting;
import com.rs.game.player.content.agility.AdvancedGnomeAgility;
import com.rs.game.player.content.agility.ApeAtollAgility;
import com.rs.game.player.content.agility.BarbarianOutpostAgility;
import com.rs.game.player.content.agility.DorgeshKaanAgility;
import com.rs.game.player.content.agility.GnomeAgility;
import com.rs.game.player.content.agility.KethsiAgility;
import com.rs.game.player.content.agility.PenguinAgility;
import com.rs.game.player.content.agility.Shortcuts;
import com.rs.game.player.content.agility.ThroneRoomAgility;
import com.rs.game.player.content.agility.WildernessAgility;
import com.rs.game.player.content.transportation.WildernessObelisk;
import com.rs.game.player.content.CrystalChest;
import com.rs.game.player.content.FadingScreen;
import com.rs.game.player.content.FountainOfHeroes;
import com.rs.game.player.controlers.Falconry;
import com.rs.game.player.controlers.FightCaves;
import com.rs.game.player.controlers.FightKiln;
import com.rs.game.player.controlers.NomadsRequiem;
import com.rs.game.player.controlers.WGuildControler;
import com.rs.game.player.controlers.Wilderness;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.io.InputStream;
import com.rs.utils.Logger;
import com.rs.utils.PkRank;
import com.rs.utils.Utils;
import com.rs.utils.ShopsHandler;
import com.rs.game.npc.NPC;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.npc.others.FireSpirit;
import com.rs.game.npc.others.LivingRock;
import com.rs.game.npc.pet.Pet;
import com.rs.game.npc.qbd.QueenBlackDragon;
import com.rs.game.npc.slayer.Strykewyrm;
import com.rs.game.player.actions.Fishing;
import com.rs.game.player.actions.Fishing.FishingSpots;
import com.rs.game.player.actions.mining.LivingMineralMining;
import com.rs.game.player.actions.object.House;
import com.rs.game.player.actions.thieving.PickPocketableNPC;
import com.rs.game.player.content.PlayerLook;
import com.rs.game.player.dialogues.impl.Hairdresser;
import com.rs.game.player.dialogues.impl.MiningGuildDwarf;

public final class ObjectHandler {

	private ObjectHandler() {

	}

	public static void handleOption(final Player player, InputStream stream, int option) {
		if (!player.hasStarted() || !player.clientHasLoadedMapRegion() || player.isDead())
			return;
		long currentTime = Utils.currentTimeMillis();
		if (player.getLockDelay() >= currentTime || player.getEmotesManager().getNextEmoteEnd() >= currentTime)
			return;
		boolean forceRun = stream.readUnsignedByte128() == 1;
		final int id = stream.readIntLE();
		int x = stream.readUnsignedShortLE();
		int y = stream.readUnsignedShortLE128();
		
		final WorldTile tile = new WorldTile(x, y, player.getPlane());
		int regionId = tile.getRegionId();
		if (!player.getMapRegionsIds().contains(regionId))
			return;
		WorldObject mapObject = World.getObjectWithId(tile, id);
		if (mapObject == null || mapObject.getId() != id)
			return;
		final WorldObject object = mapObject;

		player.stopAll(false);
		if (forceRun)
			player.setRun(forceRun);
		switch (option) {
		case 1:
			handleOption1(player, object);
			break;
		case 2:
			handleOption2(player, object);
			break;
		case 3:
			handleOption3(player, object);
			break;
		case 4:
			handleOption4(player, object);
			break;
		case 5:
			handleOption5(player, object);
			break;
		case -1:
			handleOptionExamine(player, object);
			break;
		}
	}

	private static void handleOption1(final Player player, final WorldObject object) {
		final ObjectDefinitions objectDef = object.getDefinitions();
		final int id = object.getId();
		final int x = object.getX();
		final int y = object.getY();
		int destX = player.getX();
		int destY = player.getY();
		if (id == 43529 && destX >= 2484 && destY >= 3417 && destX <= 2487 && destY <= 3422 && player.getPlane() == 3)
			AdvancedGnomeAgility.PreSwing(player, object);
		else if (id == 10598)
			KethsiAgility.PreSwing(player, object);
		else if (id == 69514)
			AdvancedGnomeAgility.RunGnomeBoard(player, object);
		else if (id == 69389)
			AdvancedGnomeAgility.JumpDown(player, object);
		else if (id == 69506)
			AdvancedGnomeAgility.climbUpGnomeTreeBranch(player);
		else if (id == 65365) // wilderness
			WildernessAgility.GateWalk(player);
		else if (id == 65367) // gateback
			WildernessAgility.GateWalkBack(player);
		else if (id == 65734)
			WildernessAgility.climbCliff(player, object);
		else if (id == 65362)
			WildernessAgility.enterObstaclePipe(player, object.getX(), object.getY());
		else if (id == 64696)
			WildernessAgility.swingOnRopeSwing(player, object);
		else if (id == 64698)
			WildernessAgility.walkLog(player);
		else if (id == 64699)
			WildernessAgility.crossSteppingPalletes(player, object);
		else if (id == 6551) {
		 if (player.starterstage == 3 && player.withinDistance(object, 1)) {
			Magic.sendNormalTeleportSpell(player, 0, 0.0D, new WorldTile(2596, 3410, 0), new int[0]);
			player.getHintIconsManager().removeAll();
			 if (player.isRSMVerRank()) {
				 World.sendWorldMessage("<img=1><col=FF0000><shad=FF00000><shad=FF0000>Everyone give a warm welcome to the new RSMVer, " +player.getUsername()+ "<shad=FF0000><col=FF0000>!<img=1>", false);
			 }
			 if (player.isPlayer()) {
				 World.sendWorldMessage("<img=1><col=FF0000><shad=FF00000><shad=FF0000>Everyone give a warm welcome to the new player, " +player.getUsername()+ "<shad=FF0000><col=FF0000>!<img=1>", false);
			 }
		 }
		 if (player.starterstage < 3) {
			 player.sm("<img=20><col=FFFFFF><shad=FF00000>You must talk to the Vengium Guide first!");
		 }
		 if (player.starterstage == 3 && !player.withinDistance(object, 1)) {
			 player.sm("<img=20><col=FFFFFF><shad=FF00000>Get closer!");
		 }
 		}
		
		if (SihponActionNodes.siphon(player, object))
			return;
		player.setCoordsEvent(new CoordsEvent(object, new Runnable() {
			@Override
			public void run() {
				player.stopAll();
				player.faceObject2(object);
				if (!player.getControlerManager().processObjectClick1(object))
					return;
				if (CastleWars.handleObjects(player, id))
					return;
				if (object.getId() == 6)
					player.getDwarfCannon().preRotationSetup(object);

				else if (id == 40760) { // objectid... i use noticeboard
					player.getInterfaceManager().sendInterface(1312);
					// player.getPackets().sendIComponentText(1312, 27,
					// "Item sets");
					player.getPackets().sendIComponentText(1312, 35, "Melee");
					player.getPackets().sendIComponentText(1312, 43, "Ranged");
					player.getPackets().sendIComponentText(1312, 51, "Magic");
					player.getPackets().sendIComponentText(1312, 59, "Hybrid");
					player.getPackets().sendIComponentText(1312, 67, "Range Tank");
					player.getPackets().sendIComponentText(1312, 75, "Pure Melee");
					player.getPackets().sendIComponentText(1312, 83, "Pure Ranged");
					player.getPackets().sendIComponentText(1312, 91, "Pure Magic");
					player.getPackets().sendIComponentText(1312, 99, "???");
				}
				// Wheat Field Teleport
				else if (id == 25014 && object.getX() == 2591 && object.getY() == 4319)
					Magic.sendWheatFieldTeleportSpell(player, 0, 0.0D, new WorldTile(2427, 4446, 0), new int[0]);
				else if (id == 25014 && object.getX() == 2592 && object.getY() == 4319)
					Magic.sendWheatFieldTeleportSpell(player, 0, 0.0D, new WorldTile(2427, 4446, 0), new int[0]);
				else if (id == 25014 && object.getX() == 2591 && object.getY() == 4320)
					Magic.sendWheatFieldTeleportSpell(player, 0, 0.0D, new WorldTile(2427, 4446, 0), new int[0]);
				else if (id == 25014 && object.getX() == 2592 && object.getY() == 4320)
					Magic.sendWheatFieldTeleportSpell(player, 0, 0.0D, new WorldTile(2427, 4446, 0), new int[0]);
				else if (id == 24991 && object.getX() == 2427 && object.getY() == 4446)
					Magic.sendWheatFieldTeleportSpell(player, 0, 0.0D, new WorldTile(2591, 4319, 0), new int[0]);

				/**
				 * Construction Teleport Portals
				 */

				if (id == 47232) {
					if (player.getSkills().getLevel(Skills.SLAYER) < 75) {
						player.sm("You need 75 slayer to enter Kuradal's dungeon.");
						return;
					}
					player.setNextWorldTile(new WorldTile(1661, 5257, 0));
				}

				if (id == 2971) {
					if (player.getY() > object.getY()) {
						player.addWalkSteps(player.getX(), player.getY() - 1, 1, false);
					} else {
						player.addWalkSteps(player.getX(), player.getY() + 1, 1, false);
					}
				}

				if (id == 47236) {
					if (player.getX() == 1650 && player.getY() == 5281 || player.getX() == 1651 && player.getY() == 5281 || player.getX() == 1650 && player.getY() == 5281) {
						player.addWalkSteps(1651, 5280, 1, false);
					}
					if (player.getX() == 1652 && player.getY() == 5280 || player.getX() == 1651 && player.getY() == 5280 || player.getX() == 1653 && player.getY() == 5280) {
						player.addWalkSteps(1651, 5281, 1, false);
					}
					if (player.getX() == 1650 && player.getY() == 5301 || player.getX() == 1650 && player.getY() == 5302 || player.getX() == 1650 && player.getY() == 5303) {
						player.addWalkSteps(1649, 5302, 1, false);
					}
					if (player.getX() == 1649 && player.getY() == 5303 || player.getX() == 1649 && player.getY() == 5302 || player.getX() == 1649 && player.getY() == 5301) {
						player.addWalkSteps(1650, 5302, 1, false);
					}
					if (player.getX() == 1626 && player.getY() == 5301 || player.getX() == 1626 && player.getY() == 5302 || player.getX() == 1626 && player.getY() == 5303) {
						player.addWalkSteps(1625, 5302, 1, false);
					}
					if (player.getX() == 1625 && player.getY() == 5301 || player.getX() == 1625 && player.getY() == 5302 || player.getX() == 1625 && player.getY() == 5303) {
						player.addWalkSteps(1626, 5302, 1, false);
					}
					if (player.getX() == 1609 && player.getY() == 5289 || player.getX() == 1610 && player.getY() == 5289 || player.getX() == 1611 && player.getY() == 5289) {
						player.addWalkSteps(1610, 5288, 1, false);
					}
					if (player.getX() == 1609 && player.getY() == 5288 || player.getX() == 1610 && player.getY() == 5288 || player.getX() == 1611 && player.getY() == 5288) {
						player.addWalkSteps(1610, 5289, 1, false);
					}
					if (player.getX() == 1606 && player.getY() == 5265 || player.getX() == 1605 && player.getY() == 5265 || player.getX() == 1604 && player.getY() == 5265) {
						player.addWalkSteps(1605, 5264, 1, false);
					}
					if (player.getX() == 1606 && player.getY() == 5264 || player.getX() == 1605 && player.getY() == 5264 || player.getX() == 1604 && player.getY() == 5264) {
						player.addWalkSteps(1605, 5265, 1, false);
					}
					if (player.getX() == 1634 && player.getY() == 5254 || player.getX() == 1634 && player.getY() == 5253 || player.getX() == 1634 && player.getY() == 5252) {
						player.addWalkSteps(1635, 5253, 1, false);
					}
					if (player.getX() == 1635 && player.getY() == 5254 || player.getX() == 1635 && player.getY() == 5253 || player.getX() == 1635 && player.getY() == 5252) {
						player.addWalkSteps(1634, 5253, 1, false);
					}
				}

				// POH
				else if (id == 15482) {
					player.getDialogueManager().startDialogue("HousePortal");
				}
				/*
				 * else if (id < 15482 && id >= 15477) //POH
				 * player.getDialogueManager().startDialogue("POHPortal", true);
				 */
				// Start of Runecrafting Portal Exit
				else if (id == 25338) // whirlpool cave
					player.setNextWorldTile(new WorldTile(1772, 5366, 0));
				else if (id == 25336) // whirlpool cave
					player.setNextWorldTile(new WorldTile(1768, 5366, 1));
				else if (id == 2465) // portal
					player.setNextWorldTile(new WorldTile(2596, 3411, 0));
				else if (id == 2469) // portal
					player.setNextWorldTile(new WorldTile(2594, 3412, 0));
				else if (id == 2473) // portal
					player.setNextWorldTile(new WorldTile(2593, 3411, 0));
				else if (id == 2475) // portal
					player.setNextWorldTile(new WorldTile(2594, 3412, 0));
				else if (id == 2466) // portal
					player.setNextWorldTile(new WorldTile(2593, 3411, 0));
				else if (id == 2471) // portal
					player.setNextWorldTile(new WorldTile(2593, 3411, 0));
				else if (id == 2474) // portal
					player.setNextWorldTile(new WorldTile(2596, 3411, 0));
				else if (id == 2467) // portal
					player.setNextWorldTile(new WorldTile(2593, 3411, 0));
				else if (id == 2468) // portal
					player.setNextWorldTile(new WorldTile(2594, 3412, 0));
				else if (id == 2470) // portal
					player.setNextWorldTile(new WorldTile(2596, 3411, 0));
				else if (id == 2472) // portal
					player.setNextWorldTile(new WorldTile(2596, 3411, 0));
				else if (id == 2477) // portal
					player.setNextWorldTile(new WorldTile(2593, 3411, 0));
				// End of Runecrafting Portal Exit
				// Start of Runecrafting Abyss Entrances
				if (id == 7133) // nature rift
					player.setNextWorldTile(new WorldTile(2398, 4841, 0));
				else if (id == 7132) // cosmic rift
					player.setNextWorldTile(new WorldTile(2162, 4833, 0));
				else if (id == 7141) // blood rift
					player.setNextWorldTile(new WorldTile(2462, 4891, 1));
				else if (id == 7129) // fire rift
					player.setNextWorldTile(new WorldTile(2584, 4836, 0));
				else if (id == 7130) // earth rift
					player.setNextWorldTile(new WorldTile(2660, 4839, 0));
				else if (id == 7131) // body rift
					player.setNextWorldTile(new WorldTile(2527, 4833, 0));
				else if (id == 7140) // mind rift
					player.setNextWorldTile(new WorldTile(2794, 4830, 0));
				else if (id == 7139) // air rift
					player.setNextWorldTile(new WorldTile(2845, 4832, 0));
				else if (id == 7137) // water rift
					player.setNextWorldTile(new WorldTile(3482, 4836, 0));
				else if (id == 7136) // death rift
					player.setNextWorldTile(new WorldTile(2207, 4836, 0));
				else if (id == 7135) // law rift
					player.setNextWorldTile(new WorldTile(2464, 4834, 0));
				else if (id == 7134) // chaotic rift
					player.setNextWorldTile(new WorldTile(2269, 4843, 0));
				// End of Runecrafting Abyss Exits
				else if (id == 36000) // UNDER
					player.setNextWorldTile(new WorldTile(2495, 9715, 0));
				else if (id == 12327) // jadinko exit cave
					player.setNextWorldTile(new WorldTile(2596, 3411, 0));
				// LOSTCITY
				if (object.getId() == 2408)
					player.useStairs(828, new WorldTile(2828, 9767, 0), 1, 2);

				/*
				 * Start of farming. Allotment A
				 */

				else if (id == 1293 || id == 1294 || id == 1295 || id == 26720 || id == 26721 || id == 1317 || id == 68974 || id == 68973 || id == 26723)
					SpiritTree.sendSpiritTreeInterface(player);

				if (object.getId() == 8550 && player.canHarvestA == false) {
					FarmingManager.useRake(player, 708);
				} else if (object.getId() == 8550 && player.canHarvestA == true && player.potatoA == true) {
					if (player.getInventory().containsItem(952, 1) || player.spade == true) {
						player.setNextAnimation(new Animation(2272));
						player.getInventory().addItem(1942, 4);
						player.getSkills().addXp(Skills.FARMING, 8000);
						player.canHarvestA = false;
						player.hasHarvestedA = false;
						player.hasPlantedA = false;
						player.potatoA = false;
						player.getPackets().sendConfigByFile(708, 3);
						player.farmingStatusA = 1;
						player.farmStatus();
					} else {
						player.out("You need a spade to dig up your crops");
					}
				} else if (object.getId() == 28716) {
					Summoning.sendInterface(player);
					player.setNextFaceWorldTile(object);
					return;

				} else if (object.getId() == 8550 && player.canHarvestA == true && player.melonA == true) {
					if (player.getInventory().containsItem(952, 1) || player.spade == true) {
						player.setNextAnimation(new Animation(2272));
						player.getInventory().addItem(5982, 4);
						player.getSkills().addXp(Skills.FARMING, 18000);
						player.canHarvestA = false;
						player.hasHarvestedA = false;
						player.hasPlantedA = false;
						player.melonA = false;
						player.getPackets().sendConfigByFile(708, 3);
						player.farmingStatusA = 1;
						player.farmStatus();
					} else {
						player.out("You need a spade to dig up your crops");
					}
				} else if (object.getId() == 8550 && player.canHarvestA == true && player.sweetA == true) {
					if (player.getInventory().containsItem(952, 1) || player.spade == true) {
						player.setNextAnimation(new Animation(2272));
						player.getInventory().addItem(5986, 4);
						player.getSkills().addXp(Skills.FARMING, 30000);
						player.canHarvestA = false;
						player.hasHarvestedA = false;
						player.hasPlantedA = false;
						player.sweetA = false;
						player.getPackets().sendConfigByFile(708, 3);
						player.farmingStatusA = 1;
						player.farmStatus();
					} else {
						player.out("You need a spade to dig up your crops");
					}
				}
				// Melon seeds below.
				// KETHSI
				else if (id == 6649) {
					player.getInterfaceManager().sendInterface(555);
				} else if (id == 6751) {
					KethsiAgility.ClimbUpRamp(player);
				} else if (id == 6752) {
					KethsiAgility.ClimbDownRamp(player);
				} else if (id == 10596) {
					KethsiAgility.runUpWall(player, object);
				} else if (id == 10597) {
					player.setNextFaceWorldTile(new WorldTile(4009, 5725, 2));
					KethsiAgility.climbUpWall(player);
				} else if (id == 10601) {
					KethsiAgility.crossBalanceBeam(player, object);
				} else if (id == 10602) {
					KethsiAgility.jumpOverGap(player, object);
				} else if (id == 10469) {
					KethsiAgility.jumpFloor(player, object);
				} else if (id == 10471) {
					KethsiAgility.jumpFloor2(player, object);
				} else if (id == 10477) {
					KethsiAgility.enterObstaclePipe(player, object);
				} else if (id == 10482) {
					KethsiAgility.climbUpWall(player);
				} else if (id == 10588) {
					KethsiAgility.RunGnomeBoard(player, object);
				} else if (id == 10589) {
					KethsiAgility.RunGnomeBoard2(player, object);
				}
				// Penguin
				else if (id == 21120 && (object.getY() == 4057)) {
					player.setNextWorldTile(new WorldTile(2630, 4057, 1));
				} else if (id == 21126) {
					PenguinAgility.SteppingStone1(player, object);
				} else if (id == 21128) {
					PenguinAgility.SteppingStone2(player, object);
				} else if (id == 21129) {
					PenguinAgility.SteppingStone3(player, object);
				} else if (id == 21130) {
					PenguinAgility.SteppingStone4(player, object);
				} else if (id == 21131) {
					PenguinAgility.SteppingStone5(player, object);
				} else if (id == 21132) {
					PenguinAgility.SteppingStone6(player, object);
				} else if (id == 21133) {
					PenguinAgility.SteppingStone7(player, object);
				}
				// Dorgesh Kaan
				else if (id == 22569 && (object.getY() == 5240)) {
					DorgeshKaanAgility.walkDorgRope(player);
				} else if (id == 22569 && (object.getY() == 5231)) {
					DorgeshKaanAgility.walkBackDorgRope(player);
				} else if (id == 22572 && (object.getY() == 5228)) {
					DorgeshKaanAgility.swingOnRopeSwing(player, object);
				} else if (id == 22572 && (object.getY() == 5229)) {
					DorgeshKaanAgility.swingOnRopeSwing(player, object);
				}
				// Allotment B

				else if (object.getId() == 8551 && player.canHarvestB == false) {
					FarmingManager.useRakeB(player, 709);
				} else if (object.getId() == 8551 && player.canHarvestB == true && player.potatoB == true) {
					if (player.getInventory().containsItem(952, 1) || player.spade == true) {
						player.setNextAnimation(new Animation(2272));
						player.getInventory().addItem(1942, 4);
						player.getSkills().addXp(Skills.FARMING, 8000);
						player.canHarvestB = false;
						player.hasHarvestedB = false;
						player.hasPlantedB = false;
						player.potatoB = false;
						player.getPackets().sendConfigByFile(709, 3);
						player.farmingStatusB = 1;
						player.farmStatus();
					} else {
						player.out("You need a spade to dig up your crops");
					}
				} else if (object.getId() == 8551 && player.canHarvestB == true && player.melonB == true) {
					if (player.getInventory().containsItem(952, 1) || player.spade == true) {
						player.setNextAnimation(new Animation(2272));
						player.getInventory().addItem(5982, 4);
						player.getSkills().addXp(Skills.FARMING, 18000);
						player.canHarvestB = false;
						player.hasHarvestedB = false;
						player.hasPlantedB = false;
						player.melonB = false;
						player.getPackets().sendConfigByFile(709, 3);
						player.farmingStatusB = 1;
						player.farmStatus();
					} else {
						player.out("You need a spade to dig up your crops");
					}
				} else if (object.getId() == 8551 && player.canHarvestB == true && player.sweetB == true) {
					if (player.getInventory().containsItem(952, 1) || player.spade == true) {
						player.setNextAnimation(new Animation(2272));
						player.getInventory().addItem(5986, 4);
						player.getSkills().addXp(Skills.FARMING, 30000);
						player.canHarvestB = false;
						player.hasHarvestedB = false;
						player.hasPlantedB = false;
						player.sweetB = false;
						player.getPackets().sendConfigByFile(709, 3);
						player.farmingStatusB = 1;
						player.farmStatus();
					} else {
						player.out("You need a spade to dig up your crops");
					}
				}

				// Herb Patch

				else if (object.getId() == 8150 && player.canHarvestHerbA == false) {
					FarmingManager.useRakeH(player, 780);
				} else if (object.getId() == 8150 && player.canHarvestHerbA == true && player.guamA == true) {
					if (player.getInventory().containsItem(952, 1) || player.spade == true) {
						player.setNextAnimation(new Animation(2272));
						player.getInventory().addItem(199, 4);
						player.getSkills().addXp(Skills.FARMING, 10000);
						player.canHarvestHerbA = false;
						player.hasHarvestedHerbA = false;
						player.hasPlantedHerb = false;
						player.guamA = false;
						player.getPackets().sendConfigByFile(780, 3);
						player.farmingStatusH = 1;
						player.farmStatus();
					} else {
						player.out("You need a spade to dig up your crops");
					}
				} else if (object.getId() == 8150 && player.canHarvestHerbA == true && player.snapA == true) {
					if (player.getInventory().containsItem(952, 1) || player.spade == true) {
						player.setNextAnimation(new Animation(2272));
						player.getInventory().addItem(3000, 4);
						player.getSkills().addXp(Skills.FARMING, 22000);
						player.canHarvestHerbA = false;
						player.hasHarvestedHerbA = false;
						player.hasPlantedHerb = false;
						player.snapA = false;
						player.getPackets().sendConfigByFile(780, 3);
						player.farmingStatusH = 1;
						player.farmStatus();
					} else {
						player.out("You need a spade to dig up your crops");
					}
				} else if (object.getId() == 8150 && player.canHarvestHerbA == true && player.torstol == true) {
					if (player.getInventory().containsItem(952, 1) || player.spade == true) {
						player.setNextAnimation(new Animation(2272));
						player.getInventory().addItem(219, 4);
						player.getSkills().addXp(Skills.FARMING, 60000);
						player.canHarvestHerbA = false;
						player.hasHarvestedHerbA = false;
						player.hasPlantedHerb = false;
						player.torstol = false;
						player.getPackets().sendConfigByFile(780, 3);
						player.farmingStatusH = 1;
						player.farmStatus();
					} else {
						player.out("You need a spade to dig up your crops");
					}
				}

				// Flower Patch

				else if (object.getId() == 7847 && player.canHarvestFlowerA == false) {
					FarmingManager.useRakeF(player, 728);
				} else if (object.getId() == 7847 && player.canHarvestFlowerA == true && player.gold == true) {
					if (player.getInventory().containsItem(952, 1) || player.spade == true) {
						player.setNextAnimation(new Animation(2272));
						player.getInventory().addItem(6010, 4);
						player.getSkills().addXp(Skills.FARMING, 9000);
						player.canHarvestFlowerA = false;
						player.hasHarvestedFlowerA = false;
						player.hasPlantedFlower = false;
						player.gold = false;
						player.getPackets().sendConfigByFile(728, 3);
						player.farmingStatusF = 1;
						player.farmStatus();
					} else {
						player.out("You need a spade to dig up your crops");
					}
				} else if (object.getId() == 7847 && player.canHarvestFlowerA == true && player.lily == true) {
					if (player.getInventory().containsItem(952, 1) || player.spade == true) {
						player.setNextAnimation(new Animation(2272));
						player.getInventory().addItem(14583, 4);
						player.getSkills().addXp(Skills.FARMING, 20000);
						player.canHarvestFlowerA = false;
						player.hasHarvestedFlowerA = false;
						player.hasPlantedFlower = false;
						player.lily = false;
						player.getPackets().sendConfigByFile(728, 3);
						player.farmingStatusF = 1;
						player.farmStatus();
					} else {
						player.out("You need a spade to dig up your crops");
					}
				}

				// Start of Catherby farming. Allotment A

				else if (object.getId() == 8553 && player.canHarvestCA == false) {
					CathFarming.useRakeCA(player, 711);
				} else if (object.getId() == 8553 && player.canHarvestCA == true && player.potatoCA == true) {
					if (player.getInventory().containsItem(952, 1) || player.spade == true) {
						player.setNextAnimation(new Animation(2272));
						player.getInventory().addItem(1942, 4);
						player.getSkills().addXp(Skills.FARMING, 8000);
						player.canHarvestCA = false;
						player.hasHarvestedCA = false;
						player.hasPlantedCA = false;
						player.potatoCA = false;
						player.getPackets().sendConfigByFile(711, 3);
					} else {
						player.out("You need a spade to dig up your crops");
					}
				} else if (object.getId() == 8553 && player.canHarvestCA == true && player.melonCA == true) {
					if (player.getInventory().containsItem(952, 1) || player.spade == true) {
						player.setNextAnimation(new Animation(2272));
						player.getInventory().addItem(5982, 4);
						player.getSkills().addXp(Skills.FARMING, 18000);
						player.canHarvestCA = false;
						player.hasHarvestedCA = false;
						player.hasPlantedCA = false;
						player.melonCA = false;
						player.getPackets().sendConfigByFile(711, 3);
					} else {
						player.out("You need a spade to dig up your crops");
					}
				} else if (object.getId() == 8553 && player.canHarvestCA == true && player.sweetCA == true) {
					if (player.getInventory().containsItem(952, 1) || player.spade == true) {
						player.setNextAnimation(new Animation(2272));
						player.getInventory().addItem(5986, 4);
						player.getSkills().addXp(Skills.FARMING, 30000);
						player.canHarvestCA = false;
						player.hasHarvestedCA = false;
						player.hasPlantedCA = false;
						player.sweetCA = false;
						player.getPackets().sendConfigByFile(711, 3);
					} else {
						player.out("You need a spade to dig up your crops");
					}
				}
				// Melon seeds below.

				// Allotment B

				else if (object.getId() == 8552 && player.canHarvestCB == false) {
					CathFarming.useRakeCB(player, 710);
				} else if (object.getId() == 8552 && player.canHarvestCB == true && player.potatoCB == true) {
					if (player.getInventory().containsItem(952, 1) || player.spade == true) {
						player.setNextAnimation(new Animation(2272));
						player.getInventory().addItem(1942, 4);
						player.getSkills().addXp(Skills.FARMING, 8000);
						player.canHarvestCB = false;
						player.hasHarvestedCB = false;
						player.hasPlantedCB = false;
						player.potatoCB = false;
						player.getPackets().sendConfigByFile(710, 3);
					} else {
						player.out("You need a spade to dig up your crops");
					}
				} else if (object.getId() == 8552 && player.canHarvestCB == true && player.melonCB == true) {
					if (player.getInventory().containsItem(952, 1) || player.spade == true) {
						player.setNextAnimation(new Animation(2272));
						player.getInventory().addItem(5982, 4);
						player.getSkills().addXp(Skills.FARMING, 18000);
						player.canHarvestCB = false;
						player.hasHarvestedCB = false;
						player.hasPlantedCB = false;
						player.melonCB = false;
						player.getPackets().sendConfigByFile(710, 3);
					} else {
						player.out("You need a spade to dig up your crops");
					}
				} else if (object.getId() == 8552 && player.canHarvestCB == true && player.sweetCB == true) {
					if (player.getInventory().containsItem(952, 1) || player.spade == true) {
						player.setNextAnimation(new Animation(2272));
						player.getInventory().addItem(5986, 4);
						player.getSkills().addXp(Skills.FARMING, 30000);
						player.canHarvestCB = false;
						player.hasHarvestedCB = false;
						player.hasPlantedCB = false;
						player.sweetCB = false;
						player.getPackets().sendConfigByFile(710, 3);
					} else {
						player.out("You need a spade to dig up your crops");
					}
				}

				// Herb Patch

				else if (object.getId() == 8151 && player.canHarvestHerbCA == false) {
					CathFarming.useRakeCH(player, 781);
				} else if (object.getId() == 8151 && player.canHarvestHerbCA == true && player.guamCA == true) {
					if (player.getInventory().containsItem(952, 1) || player.spade == true) {
						player.setNextAnimation(new Animation(2272));
						player.getInventory().addItem(199, 4);
						player.getSkills().addXp(Skills.FARMING, 10000);
						player.canHarvestHerbCA = false;
						player.hasHarvestedHerbCA = false;
						player.hasPlantedHerbC = false;
						player.guamCA = false;
						player.getPackets().sendConfigByFile(781, 3);
					} else {
						player.out("You need a spade to dig up your crops");
					}
				} else if (object.getId() == 8151 && player.canHarvestHerbCA == true && player.snapCA == true) {
					if (player.getInventory().containsItem(952, 1) || player.spade == true) {
						player.setNextAnimation(new Animation(2272));
						player.getInventory().addItem(3000, 4);
						player.getSkills().addXp(Skills.FARMING, 22000);
						player.canHarvestHerbCA = false;
						player.hasHarvestedHerbCA = false;
						player.hasPlantedHerbC = false;
						player.snapCA = false;
						player.getPackets().sendConfigByFile(781, 3);
					} else {
						player.out("You need a spade to dig up your crops");
					}
				} else if (object.getId() == 8151 && player.canHarvestHerbCA == true && player.torstolCA == true) {
					if (player.getInventory().containsItem(952, 1) || player.spade == true) {
						player.setNextAnimation(new Animation(2272));
						player.getInventory().addItem(219, 4);
						player.getSkills().addXp(Skills.FARMING, 60000);
						player.canHarvestHerbCA = false;
						player.hasHarvestedHerbCA = false;
						player.hasPlantedHerbC = false;
						player.torstolCA = false;
						player.getPackets().sendConfigByFile(781, 3);
					} else {
						player.out("You need a spade to dig up your crops");
					}
				}

				// Flower Patch

				else if (object.getId() == 7848 && player.canHarvestFlowerCA == false) {
					CathFarming.useRakeCF(player, 729);
				} else if (object.getId() == 7848 && player.canHarvestFlowerCA == true && player.goldC == true) {
					if (player.getInventory().containsItem(952, 1) || player.spade == true) {
						player.setNextAnimation(new Animation(2272));
						player.getInventory().addItem(6010, 4);
						player.getSkills().addXp(Skills.FARMING, 9000);
						player.canHarvestFlowerCA = false;
						player.hasHarvestedFlowerCA = false;
						player.hasPlantedFlowerC = false;
						player.goldC = false;
						player.getPackets().sendConfigByFile(729, 3);
					} else {
						player.out("You need a spade to dig up your crops");
					}
				} else if (object.getId() == 7848 && player.canHarvestFlowerCA == true && player.lilyC == true) {
					if (player.getInventory().containsItem(952, 1) || player.spade == true) {
						player.setNextAnimation(new Animation(2272));
						player.getInventory().addItem(14583, 4);
						player.getSkills().addXp(Skills.FARMING, 20000);
						player.canHarvestFlowerCA = false;
						player.hasHarvestedFlowerCA = false;
						player.hasPlantedFlowerC = false;
						player.lilyC = false;
						player.getPackets().sendConfigByFile(729, 3);
					} else {
						player.out("You need a spade to dig up your crops");
					}
				}

				// End of Catherby Farming

				// Fally Tree Patch

				else if (object.getId() == 8389 && player.canHarvestTreeA == false) {
					FarmingManager.useRake(player, 701);
				}

				/**
				 * Construction Teleport Portals
				 */

				if (id == 47232) {
					if (player.getSkills().getLevel(Skills.SLAYER) < 75) {
						player.getPackets().sendGameMessage("You need 75 slayer to enter Kuradal's dungeon.");
						return;
					}
					player.setNextWorldTile(new WorldTile(1661, 5257, 0));
				}

				if (id == 2971) {
					if (player.getY() > object.getY()) {
						player.addWalkSteps(player.getX(), player.getY() - 1, 1, false);
					} else {
						player.addWalkSteps(player.getX(), player.getY() + 1, 1, false);
					}
				}

				if (id == 47236) {
					if (player.getX() == 1650 && player.getY() == 5281 || player.getX() == 1651 && player.getY() == 5281 || player.getX() == 1650 && player.getY() == 5281) {
						player.addWalkSteps(1651, 5280, 1, false);
					}
					if (player.getX() == 1652 && player.getY() == 5280 || player.getX() == 1651 && player.getY() == 5280 || player.getX() == 1653 && player.getY() == 5280) {
						player.addWalkSteps(1651, 5281, 1, false);
					}
					if (player.getX() == 1650 && player.getY() == 5301 || player.getX() == 1650 && player.getY() == 5302 || player.getX() == 1650 && player.getY() == 5303) {
						player.addWalkSteps(1649, 5302, 1, false);
					}
					if (player.getX() == 1649 && player.getY() == 5303 || player.getX() == 1649 && player.getY() == 5302 || player.getX() == 1649 && player.getY() == 5301) {
						player.addWalkSteps(1650, 5302, 1, false);
					}
					if (player.getX() == 1626 && player.getY() == 5301 || player.getX() == 1626 && player.getY() == 5302 || player.getX() == 1626 && player.getY() == 5303) {
						player.addWalkSteps(1625, 5302, 1, false);
					}
					if (player.getX() == 1625 && player.getY() == 5301 || player.getX() == 1625 && player.getY() == 5302 || player.getX() == 1625 && player.getY() == 5303) {
						player.addWalkSteps(1626, 5302, 1, false);
					}
					if (player.getX() == 1609 && player.getY() == 5289 || player.getX() == 1610 && player.getY() == 5289 || player.getX() == 1611 && player.getY() == 5289) {
						player.addWalkSteps(1610, 5288, 1, false);
					}
					if (player.getX() == 1609 && player.getY() == 5288 || player.getX() == 1610 && player.getY() == 5288 || player.getX() == 1611 && player.getY() == 5288) {
						player.addWalkSteps(1610, 5289, 1, false);
					}
					if (player.getX() == 1606 && player.getY() == 5265 || player.getX() == 1605 && player.getY() == 5265 || player.getX() == 1604 && player.getY() == 5265) {
						player.addWalkSteps(1605, 5264, 1, false);
					}
					if (player.getX() == 1606 && player.getY() == 5264 || player.getX() == 1605 && player.getY() == 5264 || player.getX() == 1604 && player.getY() == 5264) {
						player.addWalkSteps(1605, 5265, 1, false);
					}
					if (player.getX() == 1634 && player.getY() == 5254 || player.getX() == 1634 && player.getY() == 5253 || player.getX() == 1634 && player.getY() == 5252) {
						player.addWalkSteps(1635, 5253, 1, false);
					}
					if (player.getX() == 1635 && player.getY() == 5254 || player.getX() == 1635 && player.getY() == 5253 || player.getX() == 1635 && player.getY() == 5252) {
						player.addWalkSteps(1634, 5253, 1, false);
					}
				}

				if (id == 15482) {
					player.getDialogueManager().startDialogue("HousePortal");
				}

				/**
				 * Start of Adrougne Farming
				 */
				if (object.getId() == 8555 && player.allotmentA == 0) { // TODO
																		// Finish
																		// all
																		// the
																		// Ardougne
																		// crops.
					ArdyFarming.useRakeAA(player, 713);
				} else if (object.getId() == 8555 && player.allotmentA == 1 && player.canHarvestAA == true) {
					if (player.getInventory().containsItem(952, 1) || player.spade == true) {
						player.setNextAnimation(new Animation(2272));
						player.getInventory().addItem(1942, 4);
						player.getSkills().addXp(Skills.FARMING, 8000);
						player.canHarvestAA = false;
						player.hasHarvestedAA = false;
						player.hasPlantedAA = false;
						player.allotmentA = 0;
						player.getPackets().sendConfigByFile(713, 3);
					} else {
						player.out("You need a spade to dig up your crops");
					}
				} else if (object.getId() == 8555 && player.canHarvestAA == true && player.allotmentA == 2) {
					if (player.getInventory().containsItem(952, 1) || player.spade == true) {
						player.setNextAnimation(new Animation(2272));
						player.getInventory().addItem(5982, 4);
						player.getSkills().addXp(Skills.FARMING, 18000);
						player.canHarvestAA = false;
						player.hasHarvestedAA = false;
						player.hasPlantedAA = false;
						player.allotmentA = 0;
						player.getPackets().sendConfigByFile(713, 3);
					} else {
						player.out("You need a spade to dig up your crops");
					}
				} else if (object.getId() == 8555 && player.canHarvestAA == true && player.allotmentA == 3) {
					if (player.getInventory().containsItem(952, 1) || player.spade == true) {
						player.setNextAnimation(new Animation(2272));
						player.getInventory().addItem(5986, 4);
						player.getSkills().addXp(Skills.FARMING, 30000);
						player.canHarvestAA = false;
						player.hasHarvestedAA = false;
						player.hasPlantedAA = false;
						player.allotmentA = 0;
						player.getPackets().sendConfigByFile(713, 3);
					} else {
						player.out("You need a spade to dig up your crops");
					}
				}

				else if (object.getId() == 8554) {
					player.getPackets().sendConfigByFile(712, 73);
				}

				// Below's method empty's the compost
				else if (object.getId() == 7836) {
					if (player.getInventory().containsItem(6055, 1)) {
						player.setNextAnimation(new Animation(2292));
						player.getInventory().deleteItem(6055, 1);
						player.out("You empty your weeds into the compost bin.");
						player.getPackets().sendConfigByFile(740, 15);
						player.getSkills().addXp(Skills.FARMING, 500);
					} else {
						player.getDialogueManager().startDialogue("SimpleMessage", "You can only empty weeds into the compost bin.");
					}
				}

				/*
				 * else if (object.getId() == 7839) { if
				 * (player.getInventory().containsItem(6055, 1)) {
				 * player.setNextAnimation(new Animation(2292));
				 * player.getInventory().deleteItem(6055, 1);
				 * player.out("You empty your weeds into the compost bin.");
				 * player.getPackets().sendConfigByFile(743, 15);
				 * player.getSkills().addXp(Skills.FARMING, 500); } else {
				 * player.getDialogueManager().startDialogue("SimpleMessage",
				 * "You can only empty weeds into the compost bin."); } }
				 */
				else if (object.getId() == 7839 && player.useCompost == false) {
					player.getDialogueManager().startDialogue("SimpleMessage", "You can use compost bins to create compost, in order to grow compost you must use your weeds on the compost bin.");
				} else if (object.getId() == 7839 && player.useCompost == true) {
					player.getPackets().sendConfigByFile(743, 31);
					player.waitForComp = true;
				} else if (object.getId() == 7839 && player.waitForComp == true) {
					player.getDialogueManager().startDialogue("SimpleMessage", "You must wait for the compost to decompose.");
				}

				else if (object.getId() == 7837) {
					if (player.getInventory().containsItem(6055, 1)) {
						player.setNextAnimation(new Animation(2292));
						player.getInventory().deleteItem(6055, 1);
						player.out("You empty your weeds into the compost bin.");
						player.getPackets().sendConfigByFile(741, 15);
						player.getSkills().addXp(Skills.FARMING, 500);
					} else {
						player.getDialogueManager().startDialogue("SimpleMessage", "You can only empty weeds into the compost bin.");
					}
				}

				else if (object.getId() == 2409 && player.spokeToWarrior == true) {
					player.getDialogueManager().startDialogue("Shamus");
				} else if (id == 2406)
					if (player.getEquipment().getWeaponId() == 772 && player.spokeToWarrior == true && player.spokeToShamus == true && player.spokeToMonk == true) {
						player.setNextWorldTile(new WorldTile(2383, 4458, player.getPlane()));
						player.lostCity();
					} else {
						// player.getPackets().sendGameMessage("You need to wield the dramen staff to enter Zanaris");
					}

				if (object.getId() == 19205)
					Hunter.createLoggedObject(player, object, true);
				HunterNPC hunterNpc = HunterNPC.forObjectId(id);
				if (id == 28716 || id == 67036 || id == 28734) {
					Summoning.sendInterface(player);
					player.setNextFaceWorldTile(object);
					return;
				}
				if (id == 29958 || id == 4019 || id == 50205 || id == 50206 || id == 50207 || id == 53883 || id == 54650 || id == 55605 || id == 56083 || id == 56084 || id == 56085 || id == 56086) {
					final int maxSummoning = player.getSkills().getLevelForXp(23);
					if (player.getSkills().getLevel(23) < maxSummoning) {
						player.lock(5);
						player.getPackets().sendGameMessage("You feel the obelisk", true);
						player.setNextAnimation(new Animation(8502));
						player.setNextGraphics(new Graphics(1308));
						WorldTasksManager.schedule(new WorldTask() {

							@Override
							public void run() {
								player.getSkills().restoreSummoning();
								player.getPackets().sendGameMessage("...and recharge all your skills.", true);
							}
						}, 2);
					} else {
						player.getPackets().sendGameMessage("You already have full summoning.", true);
					}
					return;
				}

				if (hunterNpc != null) {
					if (OwnedObjectManager.removeObject(player, object)) {
						player.setNextAnimation(hunterNpc.getEquipment().getPickUpAnimation());
						player.getInventory().getItems().addAll(hunterNpc.getItems());
						player.getInventory().addItem(hunterNpc.getEquipment().getId(), 1);
						player.getSkills().addXp(Skills.HUNTER, hunterNpc.getXp());
					} else {
						player.getPackets().sendGameMessage("This isn't your trap.");
					}
				} else if (id == HunterEquipment.BOX.getObjectId() || id == 19192) {
					if (OwnedObjectManager.removeObject(player, object)) {
						player.setNextAnimation(new Animation(5208));
						player.getInventory().addItem(HunterEquipment.BOX.getId(), 1);
					} else
						player.getPackets().sendGameMessage("This isn't your trap.");
				} else if (id == HunterEquipment.BRID_SNARE.getObjectId() || id == 19174) {
					if (OwnedObjectManager.removeObject(player, object)) {
						player.setNextAnimation(new Animation(5207));
						player.getInventory().addItem(HunterEquipment.BRID_SNARE.getId(), 1);
					} else
						player.getPackets().sendGameMessage("This isn't your trap.");
				} else if (id == 2350 && (object.getX() == 3352 && object.getY() == 3417 && object.getPlane() == 0))
					player.useStairs(832, new WorldTile(3177, 5731, 0), 1, 2);
				else if (id == 2353 && (object.getX() == 3177 && object.getY() == 5730 && object.getPlane() == 0))
					player.useStairs(828, new WorldTile(3353, 3416, 0), 1, 2);
				else if (id == 11554 || id == 11552)
					player.getPackets().sendGameMessage("That rock is currently unavailable.");

				/* TELEPORT ENTERANCES */
				else if (id == 59921) {
					player.teleportPlayer(2274, 5153, 0);
				}
				/* Waterfall */
				else if (id == 1987) {
					if (object.getX() == 2509 && object.getY() == 3493) {
						player.getPackets().sendGameMessage("You hop on the log raft..");
						player.useStairs(-1, new WorldTile(2512, 3481, 0), 3, 4);
						player.getPackets().sendGameMessage("..and crash on a small island!");
					}
				} else if (id == 10283) {
					if (object.getX() == 2512 && object.getY() == 3475) {
						if (player.getInventory().containsItem(954, 1)) {
							player.useStairs(-1, new WorldTile(2511, 3467, 0), 3, 4);
						} else {
							player.useStairs(-1, new WorldTile(2527, 3413, 0), 3, 4);
							player.getPackets().sendGameMessage("The waterfall washes you down to the river.");
							player.getPackets().sendGameMessage("Be glad you're still in one piece.");
						}
					}
				} else if (id == 2020) {
					if (object.getX() == 2512 && object.getY() == 3465) {
						if (player.getInventory().containsItem(954, 1)) {
							player.useStairs(-1, new WorldTile(2511, 3463, 0), 3, 4);
						} else {
							player.useStairs(-1, new WorldTile(2527, 3413, 0), 3, 4);
							player.getPackets().sendGameMessage("The waterfall washes you down to the river.");
							player.getPackets().sendGameMessage("Be glad you're still in one piece.");
						}
					}
				} else if (id == 2022) {
					if (object.getX() == 2512 && object.getY() == 3463) {
						player.getPackets().sendGameMessage("You get inside the barrel..");
						player.useStairs(-1, new WorldTile(2527, 3413, 0), 3, 4);
						player.getPackets().sendGameMessage("The waterfall washes you down to the river.");
						player.getPackets().sendGameMessage("Be glad you're still in one piece.");
					}
				} else if (id == 37247) {
					if (object.getX() == 2511 && object.getY() == 3464)
						player.useStairs(-1, new WorldTile(2575, 9861, 0), 1, 2);
				} else if (id == 32711) {
					if (object.getX() == 2574 && object.getY() == 9860)
						player.useStairs(-1, new WorldTile(2511, 3463, 0), 1, 2);
				}
				/* Karamja volcano */
				else if (id == 492) {
					if (object.getX() == 2856 && object.getY() == 3168)
						player.useStairs(827, new WorldTile(2857, 9569, 0), 3, 4);
				} else if (id == 1764) {
					if (object.getX() == 2856 && object.getY() == 9569)
						player.useStairs(828, new WorldTile(2857, 3167, 0), 1, 2);
				} else if (id == 45026) {
					if (object.getX() == 2816 && object.getY() == 10155)
						player.useStairs(-1, new WorldTile(1520, 4704, 0), 1, 2);
				} else if (id == 45008) {
					if (object.getX() == 1521 && object.getY() == 4704)
						player.useStairs(-1, new WorldTile(2817, 10155, 0), 1, 2);
				} else if (id == 2563)
					player.getDialogueManager().startDialogue("MuseumGuard");
				else if (id == 5282)
					player.getEctophial().refillEctophial(player);
				else if (id == 38279)
					player.getDialogueManager().startDialogue("RunespanPortalD");
				else if (id == 2491)
					player.getActionManager().setAction(new EssenceMining(object, player.getSkills().getLevel(Skills.MINING) < 30 ? EssenceDefinitions.Rune_Essence : EssenceDefinitions.Pure_Essence));
				else if (id == 2478)
					Runecrafting.craftEssence(player, 556, 1, 5, false, 11, 2, 22, 3, 34, 4, 44, 5, 55, 6, 66, 7, 77, 88, 9, 99, 10);
				else if (id == 2479)
					Runecrafting.craftEssence(player, 558, 2, 5.5, false, 14, 2, 28, 3, 42, 4, 56, 5, 70, 6, 84, 7, 98, 8);
				else if (id == 2480)
					Runecrafting.craftEssence(player, 555, 5, 6, false, 19, 2, 38, 3, 57, 4, 76, 5, 95, 6);
				else if (id == 2481)
					Runecrafting.craftEssence(player, 557, 9, 6.5, false, 26, 2, 52, 3, 78, 4);
				else if (id == 2482)
					Runecrafting.craftEssence(player, 554, 14, 7, false, 35, 2, 70, 3);
				else if (id == 2483)
					Runecrafting.craftEssence(player, 559, 20, 7.5, false, 46, 2, 92, 3);
				else if (id == 2484)
					Runecrafting.craftEssence(player, 564, 27, 8, true, 59, 2);
				else if (id == 2487)
					Runecrafting.craftEssence(player, 562, 35, 8.5, true, 74, 2);
				else if (id == 17010)
					Runecrafting.craftEssence(player, 9075, 40, 8.7, true, 82, 2);
				else if (id == 2486)
					Runecrafting.craftEssence(player, 561, 45, 9, true, 91, 2);
				else if (id == 2485)
					Runecrafting.craftEssence(player, 563, 50, 9.5, true);
				else if (id == 2488)
					Runecrafting.craftEssence(player, 560, 65, 10, true);
				else if (id == 30624)
					Runecrafting.craftEssence(player, 565, 77, 10.5, true);
				else if (id == 2452) {
					int hatId = player.getEquipment().getHatId();
					if (hatId == Runecrafting.AIR_TIARA || hatId == Runecrafting.OMNI_TIARA || player.getInventory().containsItem(1438, 1))
						Runecrafting.enterAirAltar(player);
				} else if (id == 2455) {
					int hatId = player.getEquipment().getHatId();
					if (hatId == Runecrafting.EARTH_TIARA || hatId == Runecrafting.OMNI_TIARA || player.getInventory().containsItem(1440, 1))
						Runecrafting.enterEarthAltar(player);
				} else if (id == 2456) {
					int hatId = player.getEquipment().getHatId();
					if (hatId == Runecrafting.FIRE_TIARA || hatId == Runecrafting.OMNI_TIARA || player.getInventory().containsItem(1442, 1))
						Runecrafting.enterFireAltar(player);
				} else if (id == 2454) {
					int hatId = player.getEquipment().getHatId();
					if (hatId == Runecrafting.WATER_TIARA || hatId == Runecrafting.OMNI_TIARA || player.getInventory().containsItem(1444, 1))
						Runecrafting.enterWaterAltar(player);
				} else if (id == 2457) {
					int hatId = player.getEquipment().getHatId();
					if (hatId == Runecrafting.BODY_TIARA || hatId == Runecrafting.OMNI_TIARA || player.getInventory().containsItem(1446, 1))
						Runecrafting.enterBodyAltar(player);
				} else if (id == 2453) {
					int hatId = player.getEquipment().getHatId();
					if (hatId == Runecrafting.MIND_TIARA || hatId == Runecrafting.OMNI_TIARA || player.getInventory().containsItem(1448, 1))
						Runecrafting.enterMindAltar(player);
				} else if (id == 47120) { // zaros altar
					// recharge if needed
					player.setNextAnimation(new Animation(12563));
					// if (player.getPrayer().getPrayerpoints() < player
					// .getSkills().getLevelForXp(Skills.PRAYER) * 10) {
					// player.lock(12);
					// player.getPrayer().setPrayerpoints(
					// (int) ((player.getSkills().getLevelForXp(
					// Skills.PRAYER) * 10) * 1.15));
					// player.getPrayer().refreshPrayerPoints();
					// }
					// player.getDialogueManager().startDialogue("ZarosAltar");
				} else if (id == 6552) { // zaros altar
					// recharge if needed
					player.setNextAnimation(new Animation(12563));
					if (player.getPrayer().getPrayerpoints() < player.getSkills().getLevelForXp(Skills.PRAYER) * 10) {
						player.lock(12);
						player.getPrayer().setPrayerpoints((int) ((player.getSkills().getLevelForXp(Skills.PRAYER) * 10) * 1.15));
						player.getPrayer().refreshPrayerPoints();
					}
					player.getDialogueManager().startDialogue("ZarosAltar");
				} else if (ClueScrolls.objectSpot(player, object)) {
					return;
				} else if (id == 19222)
					Falconry.beginFalconry(player);
				else if (id == 36786)
					player.getDialogueManager().startDialogue("Banker", 4907);
				else if (id == 42377 || id == 42378)
					player.getDialogueManager().startDialogue("Banker", 2759);
				else if (id == 42217 || id == 782 || id == 34752)
					player.getDialogueManager().startDialogue("Banker", 553);
				else if (id == 57437)
					player.getBank().openBank();
				else if (id == 42425 && object.getX() == 3220 && object.getY() == 3222) { // zaros
																							// portal
					player.useStairs(10256, new WorldTile(3353, 3416, 0), 4, 5, "And you find yourself into a digsite.");
					player.addWalkSteps(3222, 3223, -1, false);
					player.getPackets().sendGameMessage("You examine portal and it aborves you...");
				} else if (id == 9356)
					FightCaves.enterFightCaves(player);
				else if (id == 68107)
					FightKiln.enterFightKiln(player, false);
				else if (id == 68223)
					FightPits.enterLobby(player, false);
				else if (id == 46500 && object.getX() == 3351 && object.getY() == 3415) { // zaros
																							// portal
					player.useStairs(-1, new WorldTile(Settings.RESPAWN_PLAYER_LOCATION.getX(), Settings.RESPAWN_PLAYER_LOCATION.getY(), Settings.RESPAWN_PLAYER_LOCATION.getPlane()), 2, 3, "You found your way back to home.");
					player.addWalkSteps(3351, 3415, -1, false);
				} else if (id == 9293) {
					if (player.getSkills().getLevel(Skills.AGILITY) < 70) {
						player.getPackets().sendGameMessage("You need an agility level of 70 to use this obstacle.", true);
						return;
					}
					int x = player.getX() == 2886 ? 2892 : 2886;
					WorldTasksManager.schedule(new WorldTask() {
						int count = 0;

						@Override
						public void run() {
							player.setNextAnimation(new Animation(844));
							if (count++ == 1)
								stop();
						}

					}, 0, 0);
					player.setNextForceMovement(new ForceMovement(new WorldTile(x, 9799, 0), 3, player.getX() == 2886 ? 1 : 3));
					player.useStairs(-1, new WorldTile(x, 9799, 0), 3, 4);
				} else if (id == 29370 && (object.getX() == 3150 || object.getX() == 3153) && object.getY() == 9906) { // edgeville
																														// dungeon
																														// cut
					if (player.getSkills().getLevel(Skills.AGILITY) < 53) {
						player.getPackets().sendGameMessage("You need an agility level of 53 to use this obstacle.");
						return;
					}
					final boolean running = player.getRun();
					player.setRunHidden(false);
					player.lock(8);
					player.addWalkSteps(x == 3150 ? 3155 : 3149, 9906, -1, false);
					player.getPackets().sendGameMessage("You pulled yourself through the pipes.", true);
					WorldTasksManager.schedule(new WorldTask() {
						boolean secondloop;

						@Override
						public void run() {
							if (!secondloop) {
								secondloop = true;
								player.getAppearence().setRenderEmote(295);
							} else {
								player.getAppearence().setRenderEmote(-1);
								player.setRunHidden(running);
								player.getSkills().addXp(Skills.AGILITY, 7);
								stop();
							}
						}
					}, 0, 5);
				}

				// start forinthry dungeon
				else if (id == 18341 && object.getX() == 3036 && object.getY() == 10172)
					player.useStairs(-1, new WorldTile(3039, 3765, 0), 0, 1);
				else if (id == 20599 && object.getX() == 3038 && object.getY() == 3761)
					player.useStairs(-1, new WorldTile(3037, 10171, 0), 0, 1);
				else if (id == 18342 && object.getX() == 3075 && object.getY() == 10057)
					player.useStairs(-1, new WorldTile(3071, 3649, 0), 0, 1);
				else if (id == 20600 && object.getX() == 3072 && object.getY() == 3648)
					player.useStairs(-1, new WorldTile(3077, 10058, 0), 0, 1);
				// nomads requiem
				else if (id == 18425 && !player.getQuestManager().completedQuest(Quests.NOMADS_REQUIEM))
					NomadsRequiem.enterNomadsRequiem(player);
				else if (id == 42219) {
					player.useStairs(-1, new WorldTile(1886, 3178, 0), 0, 1);
					if (player.getQuestManager().getQuestStage(Quests.NOMADS_REQUIEM) == -2) // for
																								// now,
																								// on
																								// future
																								// talk
																								// with
																								// npc
																								// +
																								// quest
																								// reqs
						player.getQuestManager().setQuestStageAndRefresh(Quests.NOMADS_REQUIEM, 0);
				} else if (id == 8689)
					player.getActionManager().setAction(new CowMilkingAction());
				else if (id == 42220)
					player.useStairs(-1, new WorldTile(3082, 3475, 0), 0, 1);
				// start falador mininig
				else if (id == 30942 && object.getX() == 3019 && object.getY() == 3450)
					player.useStairs(828, new WorldTile(3020, 9850, 0), 1, 2);
				else if (id == 6226 && object.getX() == 3019 && object.getY() == 9850)
					player.useStairs(833, new WorldTile(3018, 3450, 0), 1, 2);
				else if (id == 31002 && player.getQuestManager().completedQuest(Quests.PERIL_OF_ICE_MONTAINS))
					player.useStairs(833, new WorldTile(2998, 3452, 0), 1, 2);
				else if (id == 31012 && player.getQuestManager().completedQuest(Quests.PERIL_OF_ICE_MONTAINS))
					player.useStairs(828, new WorldTile(2996, 9845, 0), 1, 2);
				else if (id == 30943 && object.getX() == 3059 && object.getY() == 9776)
					player.useStairs(-1, new WorldTile(3061, 3376, 0), 0, 1);
				else if (id == 30944 && object.getX() == 3059 && object.getY() == 3376)
					player.useStairs(-1, new WorldTile(3058, 9776, 0), 0, 1);
				else if (id == 2112 && object.getX() == 3046 && object.getY() == 9756) {
					if (player.getSkills().getLevelForXp(Skills.MINING) < 60) {
						player.getDialogueManager().startDialogue("SimpleNPCMessage", MiningGuildDwarf.getClosestDwarfID(player), "Sorry, but you need level 60 Mining to go in there.");
						return;
					}
					WorldObject openedDoor = new WorldObject(object.getId(), object.getType(), object.getRotation() - 1, object.getX(), object.getY() + 1, object.getPlane());
					if (World.removeTemporaryObject(object, 1200, false)) {
						World.spawnTemporaryObject(openedDoor, 1200, false);
						player.lock(2);
						player.stopAll();
						player.addWalkSteps(3046, player.getY() > object.getY() ? object.getY() : object.getY() + 1, -1, false);
					}
				} else if (id == 2113) {
					if (player.getSkills().getLevelForXp(Skills.MINING) < 60) {
						player.getDialogueManager().startDialogue("SimpleNPCMessage", MiningGuildDwarf.getClosestDwarfID(player), "Sorry, but you need level 60 Mining to go in there.");
						return;
					}
					player.useStairs(-1, new WorldTile(3021, 9739, 0), 0, 1);
				} else if (id == 6226 && object.getX() == 3019 && object.getY() == 9740)
					player.useStairs(828, new WorldTile(3019, 3341, 0), 1, 2);
				else if (id == 6226 && object.getX() == 3019 && object.getY() == 9738)
					player.useStairs(828, new WorldTile(3019, 3337, 0), 1, 2);
				else if (id == 6226 && object.getX() == 3018 && object.getY() == 9739)
					player.useStairs(828, new WorldTile(3017, 3339, 0), 1, 2);
				else if (id == 6226 && object.getX() == 3020 && object.getY() == 9739)
					player.useStairs(828, new WorldTile(3021, 3339, 0), 1, 2);
				else if (id == 30963)
					player.getBank().openBank();
				else if (id == 6045)
					player.getPackets().sendGameMessage("You search the cart but find nothing.");
				else if (id == 5906) {
					if (player.getSkills().getLevel(Skills.AGILITY) < 42) {
						player.getPackets().sendGameMessage("You need an agility level of 42 to use this obstacle.");
						return;
					}
					player.lock();
					WorldTasksManager.schedule(new WorldTask() {
						int count = 0;

						@Override
						public void run() {
							if (count == 0) {
								player.setNextAnimation(new Animation(2594));
								WorldTile tile = new WorldTile(object.getX() + (object.getRotation() == 2 ? -2 : +2), object.getY(), 0);
								player.setNextForceMovement(new ForceMovement(tile, 4, Utils.getMoveDirection(tile.getX() - player.getX(), tile.getY() - player.getY())));
							} else if (count == 2) {
								WorldTile tile = new WorldTile(object.getX() + (object.getRotation() == 2 ? -2 : +2), object.getY(), 0);
								player.setNextWorldTile(tile);
							} else if (count == 5) {
								player.setNextAnimation(new Animation(2590));
								WorldTile tile = new WorldTile(object.getX() + (object.getRotation() == 2 ? -5 : +5), object.getY(), 0);
								player.setNextForceMovement(new ForceMovement(tile, 4, Utils.getMoveDirection(tile.getX() - player.getX(), tile.getY() - player.getY())));
							} else if (count == 7) {
								WorldTile tile = new WorldTile(object.getX() + (object.getRotation() == 2 ? -5 : +5), object.getY(), 0);
								player.setNextWorldTile(tile);
							} else if (count == 10) {
								player.setNextAnimation(new Animation(2595));
								WorldTile tile = new WorldTile(object.getX() + (object.getRotation() == 2 ? -6 : +6), object.getY(), 0);
								player.setNextForceMovement(new ForceMovement(tile, 4, Utils.getMoveDirection(tile.getX() - player.getX(), tile.getY() - player.getY())));
							} else if (count == 12) {
								WorldTile tile = new WorldTile(object.getX() + (object.getRotation() == 2 ? -6 : +6), object.getY(), 0);
								player.setNextWorldTile(tile);
							} else if (count == 14) {
								stop();
								player.unlock();
							}
							count++;
						}

					}, 0, 0);
					// BarbarianOutpostAgility start
				} else if (id == 20210)
					BarbarianOutpostAgility.enterObstaclePipe(player, object);
				else if (id == 43526)
					BarbarianOutpostAgility.swingOnRopeSwing(player, object);
				else if (id == 3566)
					BarbarianOutpostAgility.swingOnRopeSwing2(player, object);
				else if (id == 43595 && x == 2550 && y == 3546)
					BarbarianOutpostAgility.walkAcrossLogBalance(player, object);
				else if (id == 20211 && x == 2538 && y == 3545)
					BarbarianOutpostAgility.climbObstacleNet(player, object);
				else if (id == 2302 && x == 2535 && y == 3547)
					BarbarianOutpostAgility.walkAcrossBalancingLedge(player, object);
				else if (id == 1948)
					BarbarianOutpostAgility.climbOverCrumblingWall(player, object);
				else if (id == 43533)
					BarbarianOutpostAgility.runUpWall(player, object);
				else if (id == 43597)
					BarbarianOutpostAgility.climbUpWall(player, object);
				else if (id == 43587)
					BarbarianOutpostAgility.fireSpringDevice(player, object);
				else if (id == 43527)
					BarbarianOutpostAgility.crossBalanceBeam(player, object);
				else if (id == 43531)
					BarbarianOutpostAgility.jumpOverGap(player, object);
				else if (id == 43532)
					BarbarianOutpostAgility.slideDownRoof(player, object);
				else if (id == 29740 && player.getEquipment().getWeaponId() != -1) {
					player.getPackets().sendGameMessage("You don't feel comfortable riding a cart while wearing a weapon.");
				} else if (id == 29740 && player.getEquipment().getWeaponId() == -1) {
					player.getMinecart().ProcessTeleportation(player);
				} else if (id == 67966)
					player.getWhirlpool().ProcessTeleportation(player);
				else if (id == 3581)
					player.getMatrix().ProcessTeleportation(player);
				// rock living caverns
				if (id == 45077) {
					player.lock();
					if (player.getX() != object.getX() || player.getY() != object.getY())
						player.addWalkSteps(object.getX(), object.getY(), -1, false);
					WorldTasksManager.schedule(new WorldTask() {

						private int count;

						@Override
						public void run() {
							if (count == 0) {
								player.setNextFaceWorldTile(new WorldTile(object.getX() - 1, object.getY(), 0));
								player.setNextAnimation(new Animation(12216));
								player.unlock();
							} else if (count == 2) {
								player.setNextWorldTile(new WorldTile(3651, 5122, 0));
								player.setNextFaceWorldTile(new WorldTile(3651, 5121, 0));
								player.setNextAnimation(new Animation(12217));
							} else if (count == 3) {
								// TODO find emote
								// player.getPackets().sendObjectAnimation(new
								// WorldObject(45078, 0, 3, 3651, 5123, 0), new
								// Animation(12220));
							} else if (count == 5) {
								player.unlock();
								stop();
							}
							count++;
						}

					}, 1, 0);
				} else if (id == 45076)
					player.getActionManager().setAction(new Mining(object, RockDefinitions.LRC_Gold_Ore));
				else if (id == 5999)
					player.getActionManager().setAction(new Mining(object, RockDefinitions.LRC_Coal_Ore));
				else if (id == 45078)
					player.useStairs(2413, new WorldTile(3012, 9832, 0), 2, 2);
				else if (id == 45079)
					player.getBank().openDepositBox();

				// champion guild
				else if (id == 24357 && object.getX() == 3188 && object.getY() == 3355)
					player.useStairs(-1, new WorldTile(3189, 3354, 1), 0, 1);
				else if (id == 24359 && object.getX() == 3188 && object.getY() == 3355)
					player.useStairs(-1, new WorldTile(3189, 3358, 0), 0, 1);
				else if (id == 1805 && object.getX() == 3191 && object.getY() == 3363) {
					WorldObject openedDoor = new WorldObject(object.getId(), object.getType(), object.getRotation() - 1, object.getX(), object.getY(), object.getPlane());
					if (World.removeTemporaryObject(object, 1200, false)) {
						World.spawnTemporaryObject(openedDoor, 1200, false);
						player.lock(2);
						player.stopAll();
						player.addWalkSteps(3191, player.getY() >= object.getY() ? object.getY() - 1 : object.getY(), -1, false);
						if (player.getY() >= object.getY())
							player.getDialogueManager().startDialogue("SimpleNPCMessage", 198, "Greetings bolt adventurer. Welcome to the guild of", "Champions.");
					}

				}
				// start of varrock dungeon
				else if (id == 29355 && object.getX() == 3230 && object.getY() == 9904) // varrock
																						// dungeon
																						// climb
																						// to
																						// bear
					player.useStairs(828, new WorldTile(3229, 3503, 0), 1, 2);
				else if (id == 24264)
					player.useStairs(833, new WorldTile(3229, 9904, 0), 1, 2);
				else if (id == 24366)
					player.useStairs(828, new WorldTile(3237, 3459, 0), 1, 2);
				else if (id == 882 && object.getX() == 3237 && object.getY() == 3458)
					player.useStairs(833, new WorldTile(3237, 9858, 0), 1, 2);
				else if (id == 29355 && object.getX() == 3097 && object.getY() == 9867) // edge
																						// dungeon
																						// climb
					player.useStairs(828, new WorldTile(3096, 3468, 0), 1, 2);
				else if (id == 26934)
					player.useStairs(833, new WorldTile(3097, 9868, 0), 1, 2);
				else if (id == 29355 && object.getX() == 3088 && object.getY() == 9971)
					player.useStairs(828, new WorldTile(3087, 3571, 0), 1, 2);
				else if (id == 65453)
					player.useStairs(833, new WorldTile(3089, 9971, 0), 1, 2);
				else if (id == 12389 && object.getX() == 3116 && object.getY() == 3452)
					player.useStairs(833, new WorldTile(3117, 9852, 0), 1, 2);
				else if (id == 29355 && object.getX() == 3116 && object.getY() == 9852)
					player.useStairs(833, new WorldTile(3115, 3452, 0), 1, 2);

				else if (id == 12568)
					ApeAtollAgility.jumpSteppingStone(player, object);
				else if (id == 12570)
					ApeAtollAgility.climbUpTropicalTree(player, object);
				else if (id == 12573)
					ApeAtollAgility.crossMonkeyBars(player, object);
				else if (id == 12576)
					ApeAtollAgility.climbUpSkullSlope(player, object);
				else if (id == 12578)
					ApeAtollAgility.swingRope(player, object);
				else if (id == 12618)
					ApeAtollAgility.climbDownTropicalTree(player, object);
				else if (id == 12622)
					ApeAtollAgility.climbDownVine(player, object);

				else if (id == 69526)
					GnomeAgility.walkGnomeLog(player);
				else if (id == 69383)
					GnomeAgility.climbGnomeObstacleNet(player);
				else if (id == 69508)
					GnomeAgility.climbUpGnomeTreeBranch(player);
				else if (id == 2312)
					GnomeAgility.walkGnomeRope(player);
				else if (id == 4059)
					GnomeAgility.walkBackGnomeRope(player);
				else if (id == 69507)
					GnomeAgility.climbDownGnomeTreeBranch(player);
				else if (id == 69384)
					GnomeAgility.climbGnomeObstacleNet2(player);
				else if (id == 69377 || id == 69378)
					GnomeAgility.enterGnomePipe(player, object.getX(), object.getY());
				else if (Wilderness.isDitch(id)) {// wild ditch
					player.getDialogueManager().startDialogue("WildernessDitch", object);
				} else if (id == 42611) {// Magic Portal
					player.getDialogueManager().startDialogue("MagicPortal");
				} else if (object.getDefinitions().name.equalsIgnoreCase("Obelisk") && object.getY() > 3525) {
					// Who the fuck removed the controler class and the code
					// from SONIC!!!!!!!!!!
					// That was an hour of collecting coords :fp: Now ima kill
					// myself.
				} else if (id == 27254) {// Edgeville portal
					player.getPackets().sendGameMessage("You enter the portal...");
					player.useStairs(10584, new WorldTile(3087, 3488, 0), 2, 3, "..and are transported to Edgeville.");
					player.addWalkSteps(1598, 4506, -1, false);
				} else if (id == 12202) {// mole entrance
					if (!player.getInventory().containsItem(952, 1)) {
						player.getPackets().sendGameMessage("You need a spade to dig this.");
						return;
					}
					if (player.getX() != object.getX() || player.getY() != object.getY()) {
						player.lock();
						player.addWalkSteps(object.getX(), object.getY());
						WorldTasksManager.schedule(new WorldTask() {
							@Override
							public void run() {
								InventoryOptionsHandler.dig(player);
							}

						}, 1);
					} else
						InventoryOptionsHandler.dig(player);
				} else if (id == 12230 && object.getX() == 1752 && object.getY() == 5136) {// mole
																							// exit
					player.setNextWorldTile(new WorldTile(2986, 3316, 0));
				} else if (id == 15522) {// portal sign
					if (player.withinDistance(new WorldTile(1598, 4504, 0), 1)) {// PORTAL
						// 1
						player.getInterfaceManager().sendInterface(327);
						player.getPackets().sendIComponentText(327, 13, "Edgeville");
						player.getPackets().sendIComponentText(327, 14, "This portal will take you to edgeville. There " + "you can multi pk once past the wilderness ditch.");
					}
					if (player.withinDistance(new WorldTile(1598, 4508, 0), 1)) {// PORTAL
						// 2
						player.getInterfaceManager().sendInterface(327);
						player.getPackets().sendIComponentText(327, 13, "Mage Bank");
						player.getPackets().sendIComponentText(327, 14, "This portal will take you to the mage bank. " + "The mage bank is a 1v1 deep wilderness area.");
					}
					if (player.withinDistance(new WorldTile(1598, 4513, 0), 1)) {// PORTAL
						// 3
						player.getInterfaceManager().sendInterface(327);
						player.getPackets().sendIComponentText(327, 13, "Magic's Portal");
						player.getPackets().sendIComponentText(327, 14, "This portal will allow you to teleport to areas that " + "will allow you to change your magic spell book.");
					}
				} else if (id == 38811 || id == 37929) {// corp beast
					if (object.getX() == 2971 && object.getY() == 4382)
						player.getInterfaceManager().sendInterface(650);
					else if (object.getX() == 2918 && object.getY() == 4382) {
						player.stopAll();
						player.setNextWorldTile(new WorldTile(player.getX() == 2921 ? 2917 : 2921, player.getY(), player.getPlane()));
					}
				} else if (id == 37928 && object.getX() == 2883 && object.getY() == 4370) {
					player.stopAll();
					player.setNextWorldTile(new WorldTile(3214, 3782, 0));
					player.getControlerManager().startControler("Wilderness");
				} else if (id == 38815 && object.getX() == 3209 && object.getY() == 3780 && object.getPlane() == 0) {
					if (player.getSkills().getLevelForXp(Skills.WOODCUTTING) < 37 || player.getSkills().getLevelForXp(Skills.MINING) < 45 || player.getSkills().getLevelForXp(Skills.SUMMONING) < 23 || player.getSkills().getLevelForXp(Skills.FIREMAKING) < 47 || player.getSkills().getLevelForXp(Skills.PRAYER) < 55) {
						player.getPackets().sendGameMessage("You need 23 Summoning, 37 Woodcutting, 45 Mining, 47 Firemaking and 55 Prayer to enter this dungeon.");
						return;
					}
					player.stopAll();
					player.setNextWorldTile(new WorldTile(2885, 4372, 2));
					player.getControlerManager().forceStop();
					// TODO all reqs, skills not added
				} else if (id == 48803 && player.isKalphiteLairSetted()) {
					player.setNextWorldTile(new WorldTile(3508, 9494, 0));
				} else if (id == 48802 && player.isKalphiteLairEntranceSetted()) {
					player.setNextWorldTile(new WorldTile(3483, 9510, 2));
				} else if (id == 3829) {
					if (object.getX() == 3483 && object.getY() == 9510) {
						player.useStairs(828, new WorldTile(3226, 3108, 0), 1, 2);
					}
				} else if (id == 3832) {
					if (object.getX() == 3508 && object.getY() == 9494) {
						player.useStairs(828, new WorldTile(3509, 9496, 2), 1, 2);
					}
				} else if (id == 9369) {
					player.getControlerManager().startControler("FightPits");
				} else if (id == 54019 || id == 54020 || id == 55301)
					PkRank.showRanks(player);
				else if (id == 1817 && object.getX() == 2273 && object.getY() == 4680) { // kbd
																							// lever
					Magic.pushLeverTeleport(player, new WorldTile(3067, 10254, 0));
				} else if (id == 1816 && object.getX() == 3067 && object.getY() == 10252) { // kbd
																							// out
																							// lever
					Magic.pushLeverTeleport(player, new WorldTile(2273, 4681, 0));
				} else if (id == 32015 && object.getX() == 3069 && object.getY() == 10256) { // kbd
																								// stairs
					player.useStairs(828, new WorldTile(3017, 3848, 0), 1, 2);
					player.getControlerManager().startControler("Wilderness");
				} else if (id == 1765 && object.getX() == 3017 && object.getY() == 3849) { // kbd
																							// out
																							// stairs
					player.stopAll();
					player.setNextWorldTile(new WorldTile(3069, 10255, 0));
					player.getControlerManager().forceStop();
				} else if (id == 14315) {
					player.getControlerManager().startControler("PestControlLobby", 1);
				} else if (id == 5959) {
					Magic.pushLeverTeleport(player, new WorldTile(2539, 4712, 0));
				} else if (id == 5960) {
					Magic.pushLeverTeleport(player, new WorldTile(3089, 3957, 0));
				} else if (id == 1814) {
					Magic.pushLeverTeleport(player, new WorldTile(3155, 3923, 0));
				} else if (id == 1815) {
					Magic.pushLeverTeleport(player, new WorldTile(2561, 3311, 0));
				} else if (id == 1317 || id == 68973) {
					player.getDialogueManager().startDialogue("SpiritTreeDialogue", object.getId());
				} else if (id == 68974) {
					player.getDialogueManager().startDialogue("MainSpiritTreeDialogue", object.getId());
				} else if (id == 62675)
					player.getCutscenesManager().play("DTPreview");
				else if (id == 17239) {
					player.setNextAnimation(new Animation(7139));
				} else if (id == 62681)
					player.getDominionTower().viewScoreBoard();
				else if (id == 62678 || id == 62679)
					player.getDominionTower().openModes();
				else if (id == 62688)
					player.getDialogueManager().startDialogue("DTClaimRewards");
				else if (id == 62677)
					player.getDominionTower().talkToFace();
				else if (id == 62680)
					player.getDominionTower().openBankChest();

				else if (id == 22437) {
					BarbarianOutpostAgility.Choke(player, object);
				} else if (id == 22435) {
					player.setNextAnimation(new Animation(5862));
					player.setNextWorldTile(new WorldTile(3787, 2821, 0));
					player.setDirection(0);
				}

				// BANDOS THRONE ROOM
				else if (id == 42586 && (player.getPlane() < 1)) {
					player.setNextWorldTile(new WorldTile(2338, 4238, 1));
					player.getAppearence().setRenderEmote(1463);
				} else if (id == 42588 && (player.withinDistance(object))) {
					if (player.getX() == 2339 && player.getY() == 4239 && player.getPlane() == 1) {
						player.getPackets().sendGameMessage("<img=20><col=FF0000>You shiver at the fall from this statue.");
					}
					else {
					player.faceObject2(object);
					player.lock(2);
					player.getAppearence().setRenderEmote(1463);
					player.setNextAnimation(new Animation(11230));
					final WorldTile toTile = (new WorldTile(object.getX(), object.getY(), object.getPlane()));
					player.setNextForceMovement(new ForceMovement(player, 0, toTile, 1, ForceMovement.SOUTH));
					WorldTasksManager.schedule(new WorldTask() {
						@Override
						public void run() {
							player.setNextWorldTile(toTile);
							player.getSkills().addXp(Skills.AGILITY, 15);
							stop();
						}
						 
					 }, 1);
					}
				} else if (id == 42591 && player.withinDistance(object)) {
					player.lock(2);
					player.getAppearence().setRenderEmote(1463);
					player.setNextAnimation(new Animation(11230));
					final WorldTile toTile = (new WorldTile(object.getX(), object.getY(), object.getPlane()));
					player.setNextForceMovement(new ForceMovement(player, 0, toTile, 1, ForceMovement.EAST));
					WorldTasksManager.schedule(new WorldTask() {
						@Override
						public void run() {
							player.setNextWorldTile(toTile);
							player.getSkills().addXp(Skills.AGILITY, 15);
							stop();
						}
						 
					 }, 1);
				
				} else if (id == 42594 && (player.withinDistance(object))) {
					if (player.getX() == 2338 && player.getY() == 4238 && player.getPlane() == 1) {
						player.getPackets().sendGameMessage("<img=20><col=FF0000>You shiver at the height of the statue.");
					}
					else {
						player.lock(2);
						player.getAppearence().setRenderEmote(1463);
						player.setNextAnimation(new Animation(11230));
						final WorldTile toTile = (new WorldTile(object.getX(), object.getY(), object.getPlane()));
						player.setNextForceMovement(new ForceMovement(player, 0, toTile, 1, ForceMovement.SOUTHEAST));
						WorldTasksManager.schedule(new WorldTask() {
							@Override
							public void run() {
								player.setNextWorldTile(toTile);
								player.getSkills().addXp(Skills.AGILITY, 15);
								stop();
							}
							 
						 }, 1);
					}
					
				}
				/* Castle of Lumbridge */
				else if (id == 36687 && x == 3209 && y == 3216)
					player.useStairs(827, new WorldTile(3208, 9616, 0), 3, 4);
				else if (id == 29355 && x == 3209 && y == 9616)
					player.useStairs(828, new WorldTile(3209, 3217, 0), 1, 2);
				/* Heroes' guild */
				else if (id == 67690 && x == 2905 && y == 3516)
					player.useStairs(827, new WorldTile(2891, 9907, 0), 3, 4);
				else if (id == 67691 && x == 2892 && y == 9907)
					player.useStairs(828, new WorldTile(2906, 3516, 0), 1, 2);
				//
				else if (id == 48797)
					player.useStairs(-1, new WorldTile(3877, 5526, 1), 0, 1);
				else if (id == 48798)
					player.useStairs(-1, new WorldTile(3246, 3198, 0), 0, 1);
				else if (id == 48678 && x == 3858 && y == 5533)
					player.useStairs(-1, new WorldTile(3861, 5533, 0), 0, 1);
				else if (id == 48678 && x == 3858 && y == 5543)
					player.useStairs(-1, new WorldTile(3861, 5543, 0), 0, 1);
				else if (id == 48678 && x == 3858 && y == 5533)
					player.useStairs(-1, new WorldTile(3861, 5533, 0), 0, 1);
				else if (id == 48677 && x == 3858 && y == 5543)
					player.useStairs(-1, new WorldTile(3856, 5543, 1), 0, 1);
				else if (id == 48677 && x == 3858 && y == 5533)
					player.useStairs(-1, new WorldTile(3856, 5533, 1), 0, 1);
				else if (id == 48679)
					player.useStairs(-1, new WorldTile(3875, 5527, 1), 0, 1);
				else if (id == 48688)
					player.useStairs(-1, new WorldTile(3972, 5565, 0), 0, 1);
				else if (id == 48683)
					player.useStairs(-1, new WorldTile(3868, 5524, 0), 0, 1);
				else if (id == 48682)
					player.useStairs(-1, new WorldTile(3869, 5524, 0), 0, 1);
				else if (id == 62676) { // dominion exit
					player.useStairs(-1, new WorldTile(3374, 3093, 0), 0, 1);
				} else if (id == 62674) { // dominion entrance
					player.useStairs(-1, new WorldTile(3744, 6405, 0), 0, 1);
				} else if (id == 3192) {
					PkRank.showRanks(player);
					// Start of woodcutting
				} else if (id == 12277)
					player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.STRAIT_VINE_COLLECTABLE));
				else if (id == 12291)
					player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.MUTATED_VINE));
				else if (id == 12274)
					player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.CURLY_VINE));
				else if (id == 12279) {
					player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.CURLY_VINE_COLLECTABLE));
				} else if (id == 12290) {
					player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.STRAIT_VINE));
				} else if (id == 65349) {
					player.useStairs(-1, new WorldTile(3044, 10325, 0), 0, 1);
				} else if (id == 32048 && object.getX() == 3043 && object.getY() == 10328) {
					player.useStairs(-1, new WorldTile(3045, 3927, 0), 0, 1);
				} else if (id == 26194) {
					player.getDialogueManager().startDialogue("PartyRoomLever");
				} else if (id == 61953) {
					ClanCitadelTheatre.OpenCurtain(player);
				} else if (id == 67050) {
					player.useStairs(-1, new WorldTile(3359, 6110, 0), 0, 1);
				} else if (id == 67053) {
					player.useStairs(-1, new WorldTile(3120, 3519, 0), 0, 1);
				} else if (id == 67051) {
					player.getDialogueManager().startDialogue("Marv", false);
				} else if (id == 67052) {
					Crucible.enterCrucibleEntrance(player);
				

						
				} else if (House.isObject(object)) {
					House.HandleObject(player, object);
				
				} else if (id == 9097) {
					player.setNextFaceWorldTile(new WorldTile((player.getX()-1), player.getY(), player.getPlane()));
					WorldTasksManager.schedule(new WorldTask() {
						int count = 0;

						@Override
						public void run() {
							
							if (count == 0) {
								player.lock();
								
								player.teleportPlayer(1947, 4966, 0);
								World.removeTemporaryObject(object, 11000, false);
								player.setNextAnimation(new Animation(2433));
							} else if (count == 11) {
								player.setNextAnimation(new Animation(-1));
								World.spawnObject(object, true);
								player.setNextWorldTile(new WorldTile(1948, 4966, 0));
								player.unlock();
								
							} else if (count == 12) {
								stop();
							}
							
							count++;
						}

					}, 0, 0);
					
				} else if (id == 37335) {
					player.teleportPlayer(3210, 3217, 3);
					WorldTasksManager.schedule(new WorldTask() {
						int count = 0;

						@Override
						public void run() {
							player.faceObject2(object);
							//player.setDirection(8192);
							if (count == 1) {
							player.lock();
							
							World.sendObjectAnimation(object, (new Animation (9979)));
							player.setNextAnimation(new Animation(9977));
							} else if (count == 5) {
							player.setNextAnimation(new Animation(-1));
							} else if (count == 6) {
							player.setNextAnimation(new Animation(2112));
							player.setNextForceTalk(new ForceTalk("All Hail Vengium!"));
							} else if (count == 12) {
							World.sendObjectAnimation(object, (new Animation (9910)));
							player.unlock();
							} else if (count == 13) {
							stop();
							}
							count++;
						}

					}, 0, 1);
				} else if (id == 213) {
					player.setNextGraphics(new Graphics (14, 0, 50));
					player.setNextFaceWorldTile(new WorldTile(3006, 0, 0));
					player.setNextAnimation(new Animation(4455));
					WorldTasksManager.schedule(new WorldTask() {
						int count = 0;

						@Override
						public void run() {
							if (count == 4) {
							FadingScreen.fade(player);
							} else if (count == 6) {
							stop();
							FadingScreen.unfade(player, 4, new Runnable() {
								@Override
								public void run() {
								player.teleportPlayer(3006, 3394, 1);
								}	
							});
							}
							count++;
						}

					}, 0, 1);
				} else if (id == 162) {
						player.setNextWorldTile(new WorldTile(3079, 9786, 0));
						player.faceObject2(object);
						World.sendObjectAnimation(object, (new Animation (3112)));
						player.setNextAnimation(new Animation(2991));
						WorldTasksManager.schedule(new WorldTask() {
							int stage;
							private Player p;
		
							@Override
							public void run() {
								if (stage == 3) {
								World.sendObjectAnimation(object, (new Animation (3111)));
								} else if (stage == 4) {
								stop();
								}
								stage++;
							}
		
						}, 0, 3);
				} if (id == 17052) {
					player.setNextAnimation(new Animation (11230));
					CoresManager.slowExecutor.schedule(new Runnable() {
						int stage;
						private Player p;

						@Override
						public void run() {
							if (stage == 0) {
							player.teleportPlayer(3005, 3392, 0);
							}
							stage++;
						}

					}, 0, TimeUnit.MILLISECONDS);
				} if (id == 35390) {
					if (objectDef.containsOption(0, "Lift")) {
					Shortcuts.passGiantBoulder(player, object, true);
					} else if (objectDef.containsOption(0, "Squeeze past")) {	
					player.setNextAnimation(new Animation(3466));
					}
				} else if (id >= 65616 && id <= 65622) {
				WildernessObelisk.handleObject(object, player);
				return;

				} else {
					switch (objectDef.name.toLowerCase()) {

					case "champions' board":
						player.getInterfaceManager().openRSMVInfo();
					case "trapdoor":

					case "manhole":
						if (objectDef.containsOption(0, "Open")) {
							WorldObject openedHole = new WorldObject(object.getId() + 1, object.getType(), object.getRotation(), object.getX(), object.getY(), object.getPlane());
							// if (World.removeTemporaryObject(object, 60000,
							// true)) {
							player.faceObject2(openedHole);
							World.spawnTemporaryObject(openedHole, 60000, true);
							// }
						}
						break;

					case "fairy ring":
						player.getFairyRing().handleObjects(object);
						break;

					case "barrel":
						if (objectDef.containsOption(0, "Climb-on"))
							player.getAppearence().setRenderEmote(152);
						break;

					case "net trap":
						if (objectDef.containsOption(0, "Dismantle"))
							player.getAppearence().setRenderEmote(267);
						break;

					case "plant":
						if (objectDef.containsOption(0, "Search"))
							player.faceObject2(object);
						player.setNextAnimation(new Animation(5216));
						break;
					// BANDOS THRONE ROOM
					case "ourg statue":
						if (objectDef.containsOption(0, "Jump-to"))
							player.setNextWorldTile(new WorldTile(2343, 4241, 1));
						player.setNextFaceWorldTile(new WorldTile(2340, 4241, 1));
						player.getAppearence().setRenderEmote(1463);
						break;

					case "statue with bowl":
						if (objectDef.containsOption(0, "Climb-on"))
							player.setNextWorldTile(new WorldTile(2343, 4241, 1));
						player.setNextFaceWorldTile(new WorldTile((player.getX()), (player.getY() + 10000), player.getPlane()));
						player.getAppearence().setRenderEmote(1463);
						break;

					// END OF OURG STATUE BALANCING
					case "clump of rocks":
						player.setNextWorldTile(new WorldTile(3229, 9525, 2));
						player.setNextAnimation(new Animation(10709));
						player.setNextGraphics(new Graphics(1932));
						player.setNextFaceWorldTile(new WorldTile(3229, 9526, 2));
						player.setNextForceMovement(new ForceMovement(new WorldTile(3229, 9505, 0), 20, player.getX() == 3229 ? 2 : 2));
						player.getPackets().sendGameMessage("<col=F8FF1C><shad=4856312>Use ;;home after you finish.");
						break;
					// START OF ELECTRIC CHAIR
					case "extractor hat":
						if (objectDef.containsOption(0, "Operate"))
							player.lock();
						WorldTasksManager.schedule(new WorldTask() {
							int count = 0;

							@Override
							public void run() {
								if (count == 0) {
									player.faceObject2(object);
									player.setNextWorldTile(new WorldTile(1962, 5150, 0));
									player.setNextFaceWorldTile(new WorldTile(1965, 5150, 0));
									player.setNextAnimation(new Animation(4885));
								} else if (count == 1) {
									World.removeTemporaryObject(object, 7000, false);
									player.setNextGraphics(new Graphics(808));
								} else if (count == 13) {
									World.spawnObject(object, true);
									player.setNextWorldTile(new WorldTile(1961, 5150, 0));
									player.unlock();
								}
								count++;
							}

						}, 0, 0);
						break;

					// END OF ELECTRIC CHAIR
					case "rat trap":
						if (objectDef.containsOption(0, "Take"))
							player.faceObject2(object);

						player.setNextWorldTile(new WorldTile(2512, 3517, 0));
						player.setNextAnimation(new Animation(6723));
						player.setNextFaceWorldTile(new WorldTile(2512, 3514, 0));
						player.setNextForceMovement(new ForceMovement(new WorldTile(2512, 3510, 0), 6, player.getX() == 2512 ? 2 : 2));
						// player.setNextForceMovement(new ForceMovement(new
						// WorldTile(2512, 3508, 2), 8,player.getX() == 2512 ? 2
						// : 2));
						player.getPackets().sendGameMessage("<col=FF0000>Move to respawn!");
						player.stopAll();
						break;

					case "closed chest":
						if (objectDef.containsOption(0, "Open") && id != 172) {
							player.setNextAnimation(new Animation(536));
							player.lock(2);
							WorldObject openedChest = new WorldObject(object.getId() + 1, object.getType(), object.getRotation(), object.getX(), object.getY(), object.getPlane());
							// if (World.removeTemporaryObject(object, 60000,
							// true)) {
							player.faceObject2(openedChest);
							World.spawnTemporaryObject(openedChest, 60000, true);
							// }
						}
						break;

					case "singing-bowl":
						// player.getCutscenesManager().play("DTPreview");
						break;

					case "warning sign":
						if (objectDef.containsOption(0, "read"))
							player.setNextFaceWorldTile(new WorldTile(3052, 2979, 0));
						player.teleportPlayer(3051, 2979, 0);
						player.setNextAnimation(new Animation(9557));
						break;

					case "open chest":
						if (objectDef.containsOption(0, "Search"))
							player.getPackets().sendGameMessage("You search the chest but find nothing.");
						break;
					case "spiderweb":
						if (object.getRotation() == 2) {
							player.lock(2);
							if (Utils.getRandom(1) == 0) {
								player.addWalkSteps(player.getX(), player.getY() < y ? object.getY() + 2 : object.getY() - 1, -1, false);
								player.getPackets().sendGameMessage("You squeeze though the web.");
							} else
								player.getPackets().sendGameMessage("You fail to squeeze though the web; perhaps you should try again.");
						}
						break;
					case "web":
						if (objectDef.containsOption(0, "Slash")) {
							player.setNextAnimation(new Animation(PlayerCombat.getWeaponAttackEmote(player.getEquipment().getWeaponId(), player.getCombatDefinitions().getAttackStyle())));
							slashWeb(player, object);
						}
						break;
					case "anvil":
						if (objectDef.containsOption(0, "Smith")) {
							ForgingBar bar = ForgingBar.getBar(player);
							if (bar != null)
								ForgingInterface.sendSmithingInterface(player, bar);
							else
								player.getPackets().sendGameMessage("You have no bars which you have smithing level to use.");
						}
						break;
					case "tin ore rocks":
						player.getActionManager().setAction(new Mining(object, RockDefinitions.Tin_Ore));
						break;
					case "gold ore rocks":
						player.getActionManager().setAction(new Mining(object, RockDefinitions.Gold_Ore));
						break;
					case "iron ore rocks":
						player.getActionManager().setAction(new Mining(object, RockDefinitions.Iron_Ore));
						break;
					case "silver ore rocks":
						player.getActionManager().setAction(new Mining(object, RockDefinitions.Silver_Ore));
						break;
					case "coal rocks":
						player.getActionManager().setAction(new Mining(object, RockDefinitions.Coal_Ore));
						break;
					case "clay rocks":
						player.getActionManager().setAction(new Mining(object, RockDefinitions.Clay_Ore));
						break;
					case "copper ore rocks":
						player.getActionManager().setAction(new Mining(object, RockDefinitions.Copper_Ore));
						break;
					case "adamantite ore rocks":
						player.getActionManager().setAction(new Mining(object, RockDefinitions.Adamant_Ore));
						break;
					case "runite ore rocks":
						player.getActionManager().setAction(new Mining(object, RockDefinitions.Runite_Ore));
						break;
					case "granite rocks":
						player.getActionManager().setAction(new Mining(object, RockDefinitions.Granite_Ore));
						break;
					case "sandstone rocks":
						player.getActionManager().setAction(new Mining(object, RockDefinitions.Sandstone_Ore));
						break;
					case "mithril ore rocks":
						player.getActionManager().setAction(new Mining(object, RockDefinitions.Mithril_Ore));
						break;
					case "bank deposit box":
						if (objectDef.containsOption(0, "Deposit"))
							player.getBank().openDepositBox();
						break;
					case "crate":
						if (objectDef.containsOption(0, "Buy"))
							ShopsHandler.openShop(player, 9);
						break;
					case "bank":
					case "chest":
					case "bank chest":
					case "bank booth":
					case "counter":
						if (objectDef.containsOption(0, "Bank") || objectDef.containsOption(0, "Use"))
							player.getBank().openBank();
						break;
						// Woodcutting start
					case "tree":
						if (objectDef.containsOption(0, "Chop down"))
							player.getActionManager().setAction(
									new Woodcutting(object,
											TreeDefinitions.NORMAL));
						break;
					case "evergreen":
						if (objectDef.containsOption(0, "Chop down"))
							player.getActionManager().setAction(
									new Woodcutting(object,
											TreeDefinitions.EVERGREEN));
						break;
					case "hollow tree":
					if (object.getId() == 2289) {
						if (objectDef.containsOption(0, "Chop down"))
							player.getActionManager().setAction(
									new Woodcutting(object,
											TreeDefinitions.HOLLOW1));
					}
					if (object.getId() == 4060) {
						if (objectDef.containsOption(0, "Chop down"))
							player.getActionManager().setAction(
									new Woodcutting(object,
											TreeDefinitions.HOLLOW2));
					}
					break;
					case "swamp tree":
						if (objectDef.containsOption(0, "Chop down"))
							player.getActionManager().setAction(
									new Woodcutting(object,
											TreeDefinitions.SWAMP));
						break;
					case "dead tree":
						if (objectDef.containsOption(0, "Chop down"))
							player.getActionManager().setAction(
									new Woodcutting(object,
											TreeDefinitions.DEAD));
						break;
					case "oak":
						if (objectDef.containsOption(0, "Chop down"))
							player.getActionManager()
									.setAction(
											new Woodcutting(object,
													TreeDefinitions.OAK));
						break;
					case "willow":
						if (objectDef.containsOption(0, "Chop down"))
							player.getActionManager().setAction(
									new Woodcutting(object,
											TreeDefinitions.WILLOW));
						break;
					case "teak":
						if (objectDef.containsOption(0, "Chop down"))
							player.getActionManager().setAction(
									new Woodcutting(object,
											TreeDefinitions.TEAK));
						break;
					case "maple tree":
						if (objectDef.containsOption(0, "Chop down"))
							player.getActionManager().setAction(
									new Woodcutting(object,
											TreeDefinitions.MAPLE));
						break;
					case "mahogany":
						if (objectDef.containsOption(0, "Chop down"))
							player.getActionManager().setAction(
									new Woodcutting(object,
											TreeDefinitions.MAHOGANY));
						break;
					case "arctic pine":
						if (objectDef.containsOption(0, "Chop down"))
							player.getActionManager().setAction(
									new Woodcutting(object,
											TreeDefinitions.ARCTIC_PINE));
						break;
					case "eucalyptus tree":
						if (objectDef.containsOption(0, "Chop down"))
							player.getActionManager().setAction(
									new Woodcutting(object,
											TreeDefinitions.EUCALYPTUS));
						break;
					case "ivy":
						if (objectDef.containsOption(0, "Chop"))
							player.getActionManager()
									.setAction(
											new Woodcutting(object,
													TreeDefinitions.IVY));
						break;
					case "yew":
						if (objectDef.containsOption(0, "Chop down"))
							player.getActionManager()
									.setAction(
											new Woodcutting(object,
													TreeDefinitions.YEW));
						break;
					case "magic tree":
						if (objectDef.containsOption(0, "Chop down"))
							player.getActionManager().setAction(
									new Woodcutting(object,
											TreeDefinitions.MAGIC));
						break;
					case "cursed magic tree":
						if (objectDef.containsOption(0, "Chop down"))
							player.getActionManager().setAction(
									new Woodcutting(object,
											TreeDefinitions.CURSED_MAGIC));
						break;
					/*case "bloodwood tree":
						if (!player.isDivineDonator()) {
							player.sm("You must be a Divine Donator in order to access this feature.");
							return;
						}
						if (objectDef.containsOption(0, "Chop down"))
							player.getActionManager().setAction(
									new Woodcutting(object,
											TreeDefinitions.BLOODWOOD));
						break;*/
					// Woodcutting end
					case "gate":
						// case "large door":
					case "metal door":
					case "long hall door":
						if (object.getType() == 0 && objectDef.containsOption(0, "Open"))
							if (!handleGate(player, object))
								handleDoor(player, object);
						break;

					case "door":
						if (object.getType() == 0 && (objectDef.containsOption(0, "Open") || objectDef.containsOption(0, "Unlock")))
							handleDoor(player, object);
						break;
					case "ladder":
						handleLadder(player, object, 1);
						break;
					case "staircase":
						handleStaircases(player, object, 1);
						break;
					case "small obelisk":
						if (objectDef.containsOption(0, "Renew-points")) {
							int summonLevel = player.getSkills().getLevelForXp(Skills.SUMMONING);
							if (player.getSkills().getLevel(Skills.SUMMONING) < summonLevel) {
								player.lock(3);
								player.setNextAnimation(new Animation(8502));
								player.getSkills().set(Skills.SUMMONING, summonLevel);
								player.getPackets().sendGameMessage("You have recharged your Summoning points.", true);
							} else
								player.getPackets().sendGameMessage("You already have full Summoning points.");
						}
						break;
					case "altar":
						if (objectDef.containsOption(0, "Pray") || objectDef.containsOption(0, "Pray-at")) {
							final int maxPrayer = player.getSkills().getLevelForXp(Skills.PRAYER) * 10;
							if (player.getPrayer().getPrayerpoints() < maxPrayer) {
								player.lock(5);
								player.getPackets().sendGameMessage("You pray to the gods...", true);
								player.setNextAnimation(new Animation(645));
								WorldTasksManager.schedule(new WorldTask() {
									@Override
									public void run() {
										player.getPrayer().restorePrayer(maxPrayer);
										player.getPackets().sendGameMessage("...and recharged your prayer.", true);
									}
								}, 2);
							} else
								player.getPackets().sendGameMessage("You already have full prayer.");
						}
						break;

					case "vine":
					case "gap":
					case "cave entrance":
					case "steps":
						PolyporeDungeon.handleObjects(object, player);
						break;
					case "strange box":
						if (objectDef.containsOption(0, "Open"))
						player.teleportPlayer(2194, 4261, 1);
						player.faceObject2(object);
						player.setNextAnimation(new Animation(7321));
						World.sendObjectAnimation(object, (new Animation (7359)));
						WorldTasksManager.schedule(new WorldTask() {
							int stage;
							private Player p;

							@Override
							public void run() {
								if (stage == 4) {
								World.sendObjectAnimation(object, (new Animation (7358)));
								} else if (stage == 5) {
								stop();
								}
								stage++;
							}

						}, 0, 5);
						break;
					default:
						if (id == 22435) {
							player.setNextAnimation(new Animation(5862));
							player.setNextWorldTile(new WorldTile(3787, 2821, 0));
							player.setDirection(0);
						}
						break;

					}
				}
				if (Settings.DEBUG)
					Logger.log("ObjectHandler", "clicked 1 at object id : " + id + ", " + object.getX() + ", " + object.getY() + ", " + object.getPlane() + ", " + object.getType() + ", " + object.getRotation() + ", " + object.getDefinitions().name);
			}
		}, objectDef.getSizeX(), Wilderness.isDitch(id) ? 4 : objectDef.getSizeY(), object.getRotation()));
	}

	private static void handleOption2(final Player player, final WorldObject object) {
		final ObjectDefinitions objectDef = object.getDefinitions();
		final int id = object.getId();
		player.setCoordsEvent(new CoordsEvent(object, new Runnable() {
			@Override
			public void run() {
				player.stopAll();
				player.faceObject2(object);
				if (!player.getControlerManager().processObjectClick2(object))
					return;
				else if (object.getDefinitions().name.equalsIgnoreCase("furnace"))
					player.getDialogueManager().startDialogue("SmeltingD", object);
				else if (id == 1317 || id == 26721 || id == 1295 || id == 1294 || id == 68974 || id == 68973)
					SpiritTree.sendSpiritTreeInterface(player);
				else if (id == 17010)
					player.getDialogueManager().startDialogue("LunarAltar");
				else if (id == 35390) {
					WorldTasksManager.schedule(new WorldTask() {

						int ticks = 0;
						int id = object.getId();

						@Override
						public void run() {
						   // boolean withinGE = id == 9312;
						    //WorldTile tile = withinGE ? new WorldTile(3139, 3516, 0) : new WorldTile(3143, 3514, 0);
						    player.lock();
						    ticks++;
						    if (ticks == 1) {
							//player.setNextAnimation(new Animation(2589));
							//player.setNextForceMovement(new ForceMovement(object, 1, withinGE ? ForceMovement.WEST : ForceMovement.EAST));
						    } else if (ticks == 3) {
							//player.setNextWorldTile(new WorldTile(3141, 3515, 0));
							//player.setNextAnimation(new Animation(2590));
						    } else if (ticks == 5) {
							//player.setNextAnimation(new Animation(2591));
							//player.setNextWorldTile(tile);
						    } else if (ticks == 6) {
							//player.setNextWorldTile(new WorldTile(tile.getX() + (withinGE ? -1 : 1), tile.getY(), tile.getPlane()));
							player.unlock();
							stop();
						    }
						}
					    }, 0, 0);
				}
						
				String THIEVING_MESSAGE = "You successfully thieve from the stall";
				Animation THIEVING_ANIMATION = new Animation(881);
				boolean THIEVING_SUCCESS = false;// Eigenlijk moet dit false
				if (id == 4875) {
					if (player.getInventory().getFreeSlots() < 1) {
						player.getPackets().sendGameMessage("Not enough space in your inventory.");
						return;
					}
					if (player.getSkills().getLevel(Skills.THIEVING) >= 30) {
						THIEVING_SUCCESS = true;
						player.getInventory().addItem(995, 6500);
						player.getSkills().addXp(17, 70);
					} else {
						player.getPackets().sendGameMessage("You need at least 30 thieving to steal from this stall");
					}
				} else if (id == 4874) {
					if (player.getInventory().getFreeSlots() < 1) {
						player.getPackets().sendGameMessage("Not enough space in your inventory.");
						return;
					}

					if (player.getSkills().getLevel(Skills.THIEVING) >= 1) {
						THIEVING_SUCCESS = true;
						player.getInventory().addItem(995, 5000);
						player.getSkills().addXp(17, 55);
					} else {
						player.getPackets().sendGameMessage("You need at least 1 thieving to steal from this stall");
					}
				} else if (id == 4876) {
					if (player.getInventory().getFreeSlots() < 1) {
						player.getPackets().sendGameMessage("Not enough space in your inventory.");
						return;
					} else if (id == 37719 || id == 37720) {

						player.getAppearence().setRenderEmote(1160);
						return;
					}
					if (player.getSkills().getLevel(Skills.THIEVING) >= 50) {
						THIEVING_SUCCESS = true;
						player.getInventory().addItem(995, 9000);
						player.getSkills().addXp(17, 80);
					} else {
						player.getPackets().sendGameMessage("You need at least 50 thieving to steal from this stall");
					}
				} else if (id == 4877) {
					if (player.getInventory().getFreeSlots() < 1) {
						player.getPackets().sendGameMessage("Not enough space in your inventory.");
						return;
					}
					if (player.getSkills().getLevel(Skills.THIEVING) >= 75) {
						THIEVING_SUCCESS = true;
						player.getInventory().addItem(995, 12000);
						player.getSkills().addXp(17, 90);
					} else {
						player.getPackets().sendGameMessage("You need at least 75 thieving to steal from this stall");
					}
				} else if (id == 4878) {
					if (player.getInventory().getFreeSlots() < 1) {
						player.getPackets().sendGameMessage("Not enough space in your inventory.");
						return;
					}
					if (player.getSkills().getLevel(Skills.THIEVING) >= 85) {
						THIEVING_SUCCESS = true;
						player.getInventory().addItem(995, 18000);
						player.getSkills().addXp(17, 100);
					} else {
						player.getPackets().sendGameMessage("You need at least 85 thieving to steal from this stall");
					}
				}

				if (THIEVING_SUCCESS) {
					player.applyHit(new Hit(player, Utils.getRandom(20) + 15, HitLook.REGULAR_DAMAGE));
					player.setNextAnimation(new Animation(881));
					player.getPackets().sendGameMessage(THIEVING_MESSAGE);
					player.lock(1);
				} else if (id == 62677)
					player.getDominionTower().openRewards();
				else if (id == 6)
					player.getDwarfCannon().pickUpDwarfCannon(0, object);
				else if (id == 28094) {
					if (object.getX() == 3141 && object.getY() == 3507)
						player.useStairs(827, new WorldTile(2909, 10174, 0), 3, 4);
				} else if (id == 62688)
					player.getDialogueManager().startDialogue("SimpleMessage", "You have a Dominion Factor of " + player.getDominionTower().getDominionFactor() + ".");
				else if (id == 68107)
					FightKiln.enterFightKiln(player, true);
				else if (id == 34384 || id == 34383 || id == 14011 || id == 7053 || id == 34387 || id == 34386 || id == 34385)
					Thieving.handleStalls(player, object);
				else if (id == 61953)
					ClanCitadelTheatre.CloseCurtain(player);
				else if (id == 61954)
					ClanCitadelTheatre.ModifyBackdrop(player);
				else if (id == 2418)
					PartyRoom.openPartyChest(player);

				else if (id == 2646) {
					World.removeTemporaryObject(object, 50000, true);
					player.getInventory().addItem(1779, 1);
					// crucible
				} else if (id == 12309)
					ShopsHandler.openShop(player, 163);
				else if (id == 67051)
					player.getDialogueManager().startDialogue("Marv", true);
				// BANDOS THRONE ROOM
				else if (id == 42588 && (player.getPlane() >= 1)) {
					player.setNextWorldTile(new WorldTile((player.getX() - 1), player.getY(), (player.getPlane()) - 1));
					player.getAppearence().setRenderEmote(-1);
				} else if (id == 42591) {
					player.setNextWorldTile(new WorldTile(2338, 4240, 1));
					player.getAppearence().setRenderEmote(1463);
				
					
				} else {
					switch (objectDef.name.toLowerCase()) {

					case "obelisk":

						int summonLevel = player.getSkills().getLevelForXp(Skills.SUMMONING);
						if (player.getSkills().getLevel(Skills.SUMMONING) < summonLevel) {
							player.lock(3);
							player.setNextAnimation(new Animation(8502));
							player.getSkills().set(Skills.SUMMONING, summonLevel);
							player.sm("You have recharged your Summoning points.", true);
						} else
							player.sm("You already have full Summoning points.");

						break;

					case "cabbage":
						if (objectDef.containsOption(1, "Pick") && player.getInventory().addItem(1965, 1)) {
							player.setNextAnimation(new Animation(827));
							player.lock(2);
							World.removeTemporaryObject(object, 60000, false);
						}
						break;

					case "prop machine":
						if (objectDef.containsOption(0, "Modify-Backdrop"))
							ClanCitadelTheatre.ModifyBackdrop(player);
						break;
					// BANDOS THRONE ROOM
					case "ourg statue":
						if (objectDef.containsOption(0, "Jump-down"))
							player.teleportPlayer(2341, 4241, 0);
						player.getAppearence().setRenderEmote(-1);
						player.stopAll();
						break;

					case "bank":
					case "bank chest":
					case "bank booth":
					case "counter":
						if (objectDef.containsOption(1, "Bank"))
							player.getBank().openBank();
						break;
					case "gates":
					case "gate":
					case "metal door":
						if (object.getType() == 0 && objectDef.containsOption(1, "Open"))
							handleGate(player, object);
						break;
					case "door":
						if (object.getType() == 0 && objectDef.containsOption(1, "Open"))
							handleDoor(player, object);
						break;
					case "ladder":
						handleLadder(player, object, 2);
						break;
					case "staircase":
						handleStaircases(player, object, 2);
						break;
					case "wall":
						if (objectDef.containsOption(0, "Lean against"))
							player.getAppearence().setRenderEmote(1160);
						break;
					default:
						break;
					}
				}
				if (Settings.DEBUG)
					Logger.log("ObjectHandler", "clicked 2 at object id : " + id + ", " + object.getX() + ", " + object.getY() + ", " + object.getPlane());
			}
		}, objectDef.getSizeX(), objectDef.getSizeY(), object.getRotation()));
	}

	private static void handleOption3(final Player player, final WorldObject object) {
		final ObjectDefinitions objectDef = object.getDefinitions();
		final int id = object.getId();
		player.setCoordsEvent(new CoordsEvent(object, new Runnable() {
			@Override
			public void run() {
				player.stopAll();
				player.faceObject2(object);
				if (!player.getControlerManager().processObjectClick3(object))
					return;
				if (id == 12309)
					ShopsHandler.openShop(player, 164);
				switch (objectDef.name.toLowerCase()) {
				case "gate":
				case "metal door":
					if (object.getType() == 0 && objectDef.containsOption(2, "Open"))
						handleGate(player, object);
					break;
				case "door":
					if (object.getType() == 0 && objectDef.containsOption(2, "Open"))
						handleDoor(player, object);
					break;
				case "ladder":
					handleLadder(player, object, 3);
					break;
				case "staircase":
					handleStaircases(player, object, 3);
					break;
				default:
					break;
				}
				if (Settings.DEBUG)
					Logger.log("ObjectHandler", "cliked 3 at object id : " + id + ", " + object.getX() + ", " + object.getY() + ", " + object.getPlane() + ", ");
			}
		}, objectDef.getSizeX(), objectDef.getSizeY(), object.getRotation()));
	}

	private static void handleOption4(final Player player, final WorldObject object) {
		final ObjectDefinitions objectDef = object.getDefinitions();
		final int id = object.getId();
		player.setCoordsEvent(new CoordsEvent(object, new Runnable() {
			@Override
			public void run() {
				player.stopAll();
				player.faceObject2(object);
				if (!player.getControlerManager().processObjectClick4(object))
					return;
		
				// living rock Caverns
				if (id == 45076)
					MiningBase.propect(player, "This rock contains a large concentration of gold.");
				else if (id == 5999)
					MiningBase.propect(player, "This rock contains a large concentration of coal.");
				else {
					switch (objectDef.name.toLowerCase()) {
					default:

						break;
					}
				}
				if (Settings.DEBUG)
					Logger.log("ObjectHandler", "cliked 4 at object id : " + id + ", " + object.getX() + ", " + object.getY() + ", " + object.getPlane() + ", ");
			}
		}, objectDef.getSizeX(), objectDef.getSizeY(), object.getRotation()));
	}

	private static void handleOption5(final Player player, final WorldObject object) {
		final ObjectDefinitions objectDef = object.getDefinitions();
		final int id = object.getId();
		player.setCoordsEvent(new CoordsEvent(object, new Runnable() {
			@Override
			public void run() {
				player.stopAll();
				player.faceObject2(object);
				if (!player.getControlerManager().processObjectClick5(object))
					return;
				
				if (id == -1) {
					// unused
					// unused

					// wheat
				} else if (object.getDefinitions().name.equalsIgnoreCase("Magical wheat")) {
					player.sendMessage("You use your RSMV strength to push through the wheat.");
					if (object.getX() > player.getX() && object.getY() == player.getY()) {
						BarbarianOutpostAgility.WheatWalkEAST(player, object);
					} else if (object.getX() < player.getX() && object.getY() == player.getY()) {
						BarbarianOutpostAgility.WheatWalkWEST(player, object);
					} else if (object.getX() == player.getX() && object.getY() > player.getY()) {
						BarbarianOutpostAgility.WheatWalkNORTH(player, object);
					} else if (object.getX() == player.getX() && object.getY() < player.getY()) {
						BarbarianOutpostAgility.WheatWalkSOUTH(player, object);
					} else {
						player.getPackets().sendGameMessage("You can only pass through wheat that you are in front of.");
						return;
					}

				} else if (object.getDefinitions().name.equalsIgnoreCase("Wall")) {
					player.lock();
					player.faceObject2(object);
					player.getAppearence().setRenderEmote(1160);
					return;
				} else {
					switch (objectDef.name.toLowerCase()) {

					case "door hotspot":
						// player.getInterfaceManager().sendInterface(402);
						break;
					case "repair space":
						player.getInterfaceManager().sendInterface(397);
					case "bed space":
						if (!player.getInventory().containsItem(8778, 4)) {
							player.sm("You need 4 oak planks to make a bed");
						} else {
							player.getInventory().deleteItem(8778, 4);
							player.getSkills().addXp(Skills.CONSTRUCTION, 10000);
							player.setNextAnimation(new Animation(898));
							player.sm("You make a bed");

						}

					case "fire":
						if (objectDef.containsOption(4, "Add-logs"))
							Bonfire.addLogs(player, object);
						break;
					default:
						break;
					}
				}
				if (Settings.DEBUG)
					Logger.log("ObjectHandler", "cliked 5 at object id : " + id + ", " + object.getX() + ", " + object.getY() + ", " + object.getPlane() + ", ");
			}
		}, objectDef.getSizeX(), objectDef.getSizeY(), object.getRotation()));
	}

	private static void handleOptionExamine(final Player player, final WorldObject object) {
		if (player.getUsername().equalsIgnoreCase("Multak") || player.getUsername().equalsIgnoreCase("Uchiha")) {
			int offsetX = object.getX() - player.getX();
			int offsetY = object.getY() - player.getY();
			System.out.println("Offsets" + offsetX + " , " + offsetY);
		}
		player.getPackets().sendGameMessage("It's an " + object.getDefinitions().name + ".");
		if (Settings.DEBUG)
			if (Settings.DEBUG)

				Logger.log("ObjectHandler", "examined object id : " + object.getId() + ", " + object.getX() + ", " + object.getY() + ", " + object.getPlane() + ", " + object.getType() + ", " + object.getRotation() + ", " + object.getDefinitions().name);
	}

	private static void slashWeb(Player player, WorldObject object) {

		if (Utils.getRandom(1) == 0) {
			World.spawnTemporaryObject(new WorldObject(object.getId() + 1, object.getType(), object.getRotation(), object.getX(), object.getY(), object.getPlane()), 60000, true);
			player.getPackets().sendGameMessage("You slash through the web!");
		} else
			player.getPackets().sendGameMessage("You fail to cut through the web.");
	}

	private static boolean handleGate(Player player, WorldObject object) {
		if (World.isSpawnedObject(object))
			return false;
		if (object.getRotation() == 0) {

			boolean south = true;
			WorldObject otherDoor = World.getObject(new WorldTile(object.getX(), object.getY() + 1, object.getPlane()), object.getType());
			if (otherDoor == null || otherDoor.getRotation() != object.getRotation() || otherDoor.getType() != object.getType() || !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name)) {
				otherDoor = World.getObject(new WorldTile(object.getX(), object.getY() - 1, object.getPlane()), object.getType());
				if (otherDoor == null || otherDoor.getRotation() != object.getRotation() || otherDoor.getType() != object.getType() || !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name))
					return false;
				south = false;
			}
			WorldObject openedDoor1 = new WorldObject(object.getId(), object.getType(), object.getRotation() + 1, object.getX(), object.getY(), object.getPlane());
			WorldObject openedDoor2 = new WorldObject(otherDoor.getId(), otherDoor.getType(), otherDoor.getRotation() + 1, otherDoor.getX(), otherDoor.getY(), otherDoor.getPlane());
			if (south) {
				openedDoor1.moveLocation(-1, 0, 0);
				openedDoor1.setRotation(3);
				openedDoor2.moveLocation(-1, 0, 0);
			} else {
				openedDoor1.moveLocation(-1, 0, 0);
				openedDoor2.moveLocation(-1, 0, 0);
				openedDoor2.setRotation(3);
			}

			if (World.removeTemporaryObject(object, 60000, true) && World.removeTemporaryObject(otherDoor, 60000, true)) {
				player.faceObject2(openedDoor1);
				World.spawnTemporaryObject(openedDoor1, 60000, true);
				World.spawnTemporaryObject(openedDoor2, 60000, true);
				return true;
			}
		} else if (object.getRotation() == 2) {

			boolean south = true;
			WorldObject otherDoor = World.getObject(new WorldTile(object.getX(), object.getY() + 1, object.getPlane()), object.getType());
			if (otherDoor == null || otherDoor.getRotation() != object.getRotation() || otherDoor.getType() != object.getType() || !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name)) {
				otherDoor = World.getObject(new WorldTile(object.getX(), object.getY() - 1, object.getPlane()), object.getType());
				if (otherDoor == null || otherDoor.getRotation() != object.getRotation() || otherDoor.getType() != object.getType() || !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name))
					return false;
				south = false;
			}
			WorldObject openedDoor1 = new WorldObject(object.getId(), object.getType(), object.getRotation() + 1, object.getX(), object.getY(), object.getPlane());
			WorldObject openedDoor2 = new WorldObject(otherDoor.getId(), otherDoor.getType(), otherDoor.getRotation() + 1, otherDoor.getX(), otherDoor.getY(), otherDoor.getPlane());
			if (south) {
				openedDoor1.moveLocation(1, 0, 0);
				openedDoor2.setRotation(1);
				openedDoor2.moveLocation(1, 0, 0);
			} else {
				openedDoor1.moveLocation(1, 0, 0);
				openedDoor1.setRotation(1);
				openedDoor2.moveLocation(1, 0, 0);
			}
			if (World.removeTemporaryObject(object, 60000, true) && World.removeTemporaryObject(otherDoor, 60000, true)) {
				player.faceObject2(openedDoor1);
				World.spawnTemporaryObject(openedDoor1, 60000, true);
				World.spawnTemporaryObject(openedDoor2, 60000, true);
				return true;
			}
		} else if (object.getRotation() == 3) {

			boolean right = true;
			WorldObject otherDoor = World.getObject(new WorldTile(object.getX() - 1, object.getY(), object.getPlane()), object.getType());
			if (otherDoor == null || otherDoor.getRotation() != object.getRotation() || otherDoor.getType() != object.getType() || !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name)) {
				otherDoor = World.getObject(new WorldTile(object.getX() + 1, object.getY(), object.getPlane()), object.getType());
				if (otherDoor == null || otherDoor.getRotation() != object.getRotation() || otherDoor.getType() != object.getType() || !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name))
					return false;
				right = false;
			}
			WorldObject openedDoor1 = new WorldObject(object.getId(), object.getType(), object.getRotation() + 1, object.getX(), object.getY(), object.getPlane());
			WorldObject openedDoor2 = new WorldObject(otherDoor.getId(), otherDoor.getType(), otherDoor.getRotation() + 1, otherDoor.getX(), otherDoor.getY(), otherDoor.getPlane());
			if (right) {
				openedDoor1.moveLocation(0, -1, 0);
				openedDoor2.setRotation(0);
				openedDoor1.setRotation(2);
				openedDoor2.moveLocation(0, -1, 0);
			} else {
				openedDoor1.moveLocation(0, -1, 0);
				openedDoor1.setRotation(0);
				openedDoor2.setRotation(2);
				openedDoor2.moveLocation(0, -1, 0);
			}
			if (World.removeTemporaryObject(object, 60000, true) && World.removeTemporaryObject(otherDoor, 60000, true)) {
				player.faceObject2(openedDoor1);
				World.spawnTemporaryObject(openedDoor1, 60000, true);
				World.spawnTemporaryObject(openedDoor2, 60000, true);
				return true;
			}
		} else if (object.getRotation() == 1) {

			boolean right = true;
			WorldObject otherDoor = World.getObject(new WorldTile(object.getX() - 1, object.getY(), object.getPlane()), object.getType());
			if (otherDoor == null || otherDoor.getRotation() != object.getRotation() || otherDoor.getType() != object.getType() || !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name)) {
				otherDoor = World.getObject(new WorldTile(object.getX() + 1, object.getY(), object.getPlane()), object.getType());
				if (otherDoor == null || otherDoor.getRotation() != object.getRotation() || otherDoor.getType() != object.getType() || !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name))
					return false;
				right = false;
			}
			WorldObject openedDoor1 = new WorldObject(object.getId(), object.getType(), object.getRotation() + 1, object.getX(), object.getY(), object.getPlane());
			WorldObject openedDoor2 = new WorldObject(otherDoor.getId(), otherDoor.getType(), otherDoor.getRotation() + 1, otherDoor.getX(), otherDoor.getY(), otherDoor.getPlane());
			if (right) {
				openedDoor1.moveLocation(0, 1, 0);
				openedDoor1.setRotation(0);
				openedDoor2.moveLocation(0, 1, 0);
			} else {
				openedDoor1.moveLocation(0, 1, 0);
				openedDoor2.setRotation(0);
				openedDoor2.moveLocation(0, 1, 0);
			}
			if (World.removeTemporaryObject(object, 60000, true) && World.removeTemporaryObject(otherDoor, 60000, true)) {
				player.faceObject2(openedDoor1);
				World.spawnTemporaryObject(openedDoor1, 60000, true);
				World.spawnTemporaryObject(openedDoor2, 60000, true);
				return true;
			}
		}
		return false;
	}

	public static boolean handleDoor(Player player, WorldObject object, long timer) {
		if (World.isSpawnedObject(object))
			return false;
		WorldObject openedDoor = new WorldObject(object.getId(), object.getType(), object.getRotation() + 1, object.getX(), object.getY(), object.getPlane());
		if (object.getRotation() == 0)
			openedDoor.moveLocation(-1, 0, 0);
		else if (object.getRotation() == 1)
			openedDoor.moveLocation(0, 1, 0);
		else if (object.getRotation() == 2)
			openedDoor.moveLocation(1, 0, 0);
		else if (object.getRotation() == 3)
			openedDoor.moveLocation(0, -1, 0);
		if (World.removeTemporaryObject(object, timer, true)) {
			player.faceObject2(openedDoor);
			World.spawnTemporaryObject(openedDoor, timer);
			return true;
		}
		return false;
	}

	private static boolean handleDoor(Player player, WorldObject object) {
		return handleDoor(player, object, 60000);
	}

	private static boolean handleStaircases(Player player, WorldObject object, int optionId) {
		String option = object.getDefinitions().getOption(optionId);
		if (option.equalsIgnoreCase("Climb-up")) {
			if (player.getPlane() == 3)
				return false;
			player.useStairs(-1, new WorldTile(player.getX(), player.getY(), player.getPlane() + 1), 0, 1);
		} else if (option.equalsIgnoreCase("Climb-down")) {
			if (player.getPlane() == 0)
				return false;
			player.useStairs(-1, new WorldTile(player.getX(), player.getY(), player.getPlane() - 1), 0, 1);
		} else if (option.equalsIgnoreCase("Climb")) {
			if (player.getPlane() == 3 || player.getPlane() == 0)
				return false;
			player.getDialogueManager().startDialogue("ClimbNoEmoteStairs", new WorldTile(player.getX(), player.getY(), player.getPlane() + 1), new WorldTile(player.getX(), player.getY(), player.getPlane() - 1), "Go up the stairs.", "Go down the stairs.");
		} else
			return false;
		return false;
	}

	private static boolean handleLadder(Player player, WorldObject object, int optionId) {
		String option = object.getDefinitions().getOption(optionId);
		if (option.equalsIgnoreCase("Climb-up")) {
			if (player.getPlane() == 3)
				return false;
			player.useStairs(828, new WorldTile(player.getX(), player.getY(), player.getPlane() + 1), 1, 2);
		} else if (option.equalsIgnoreCase("Climb-down")) {
			if (player.getPlane() == 0)
				return false;
			player.useStairs(828, new WorldTile(player.getX(), player.getY(), player.getPlane() - 1), 1, 2);
		} else if (option.equalsIgnoreCase("Climb")) {
			if (player.getPlane() == 3 || player.getPlane() == 0)
				return false;
			player.getDialogueManager().startDialogue("ClimbEmoteStairs", new WorldTile(player.getX(), player.getY(), player.getPlane() + 1), new WorldTile(player.getX(), player.getY(), player.getPlane() - 1), "Climb up the ladder.", "Climb down the ladder.", 828);
		} else
			return false;
		return true;
	}

	public static void handleItemOnObject(final Player player, final WorldObject object, final int interfaceId, final Item item) {
		final int itemId = item.getId();
		final ObjectDefinitions objectDef = object.getDefinitions();
		player.setCoordsEvent(new CoordsEvent(object, new Runnable() {
			@Override
			public void run() {
				player.faceObject2(object);
				if (itemId == 1438 && object.getId() == 2452) {
					Runecrafting.enterAirAltar(player);
				} else if (object.getId() == 409) {
					Bones bone = BonesOnAltar.isGood(item);
					if (bone != null) {
						player.getDialogueManager().startDialogue("PrayerD", bone, object);
						return;
					} else {
						player.getPackets().sendGameMessage("");
						return;
					}

				} else if (itemId == CrystalChest.key && object.getId() == 172) {
					CrystalChest.lootChest(player);
				} /*
				 * else if (itemId == FountainOfHeroes.jewellery &&
				 * object.getId() == 36695) {
				 * FountainOfHeroes.rechargeJewellery(player, itemId); }
				 */else if (itemId == 1079 || itemId == 1127 || itemId == 1163 || itemId == 1153 || itemId == 1115 || itemId == 1067 || itemId == 1155 || itemId == 1117 || itemId == 1075 || itemId == 1157 || itemId == 1119 || itemId == 1069 || itemId == 1159 || itemId == 1121 || itemId == 1071 || itemId == 1161 || itemId == 1123 || itemId == 1073 && object.getId() == 15621) {
					WGuildControler.handleItemOnObject(player, object, item);
				} else if (itemId == 6055 && object.getId() == 7839) {
					player.increaseWeed++;
					// player.getInventory().deleteItem(6055, 1);
					player.out("You place a weed in the compost bin. " + player.increaseWeed);
					if (player.increaseWeed <= 14) {
						player.getPackets().sendConfigByFile(743, 1);
					} else if (player.increaseWeed >= 15) {
						player.getPackets().sendConfigByFile(743, 15);
						player.useCompost = true;
					}

					// Start of Farming, Potato Seeds Below.
				} else if (itemId == 5318 && object.getId() == 8550 && player.hasPlantedA == false && player.mustRakeA == true) {
					FarmingManager.plantPotatoA(player, 708);
					player.potatoA = true;
					player.hasPlantedA = true;

				} else if (itemId == 5318 && object.getId() == 8551 && player.hasPlantedB == false && player.mustRakeB == true) {
					FarmingManager.plantPotatoB(player, 709);
					player.potatoB = true;
					player.hasPlantedB = true;
					// End of potato.

					// Water melon, Level needed to grow = 47.
				} else if (itemId == 5321 && object.getId() == 8550 && player.hasPlantedA == false && player.mustRakeA == true) {
					if (player.getSkills().getLevel(Skills.FARMING) < 47) {
						player.getPackets().sendGameMessage("You need a Farming level of 47 to plant this.");
						return;
					}
					FarmingManager.plantMelonA(player, 708);
					player.melonA = true;
					player.hasPlantedA = true;

				} else if (itemId == 5321 && object.getId() == 8551 && player.hasPlantedB == false && player.mustRakeB == true) {
					if (player.getSkills().getLevel(Skills.FARMING) < 47) {
						player.getPackets().sendGameMessage("You need a Farming level of 47 to plant this.");
						return;
					}
					FarmingManager.plantMelonB(player, 709);
					player.melonB = true;
					player.hasPlantedB = true;
					// End of Melon.

					// Start of sweetcorn

				} else if (itemId == 5320 && object.getId() == 8550 && player.hasPlantedA == false && player.mustRakeA == true) {
					if (player.getSkills().getLevel(Skills.FARMING) < 71) {
						player.getPackets().sendGameMessage("You need a Farming level of 71 to plant this.");
						return;
					}
					FarmingManager.plantSweetA(player, 708);
					player.sweetA = true;
					player.hasPlantedA = true;

				} else if (itemId == 5320 && object.getId() == 8551 && player.hasPlantedB == false && player.mustRakeB == true) {
					if (player.getSkills().getLevel(Skills.FARMING) < 71) {
						player.getPackets().sendGameMessage("You need a Farming level of 71 to plant this.");
						return;
					}
					FarmingManager.plantSweetB(player, 709);
					player.sweetB = true;
					player.hasPlantedB = true;

					// End of sweetcorn

					// Guam Herb
				} else if (itemId == 5291 && object.getId() == 8150 && player.hasPlantedHerb == false && player.mustRakeH == true) {
					FarmingManager.plantGuamA(player, 780);
					player.guamA = true;
					player.hasPlantedHerb = true;

				} else if (itemId == 5300 && object.getId() == 8150 && player.hasPlantedHerb == false && player.mustRakeH == true) {
					if (player.getSkills().getLevel(Skills.FARMING) < 62) {
						player.getPackets().sendGameMessage("You need a Farming level of 62 to plant this.");
						return;
					}
					FarmingManager.plantSnap(player, 780);
					player.snapA = true;
					player.hasPlantedHerb = true;

				} else if (itemId == 5304 && object.getId() == 8150 && player.hasPlantedHerb == false && player.mustRakeH == true) {
					if (player.getSkills().getLevel(Skills.FARMING) < 85) {
						player.getPackets().sendGameMessage("You need a Farming level of 85 to plant Torstol Seeds.");
						return;
					}
					FarmingManager.plantTorstol(player, 780);
					player.torstol = true;
					player.hasPlantedHerb = true;

					// Flower Patch, Below is Marigold seeds
				} else if (itemId == 5096 && object.getId() == 7847 && player.hasPlantedFlower == false && player.mustRakeF == true) {
					FarmingManager.plantGold(player, 728);
					player.gold = true;
					player.hasPlantedFlower = true;

					// Below is White Lily seeds
				} else if (itemId == 14589 && object.getId() == 7847 && player.hasPlantedFlower == false && player.mustRakeF == true) {
					if (player.getSkills().getLevel(Skills.FARMING) < 52) {
						player.getPackets().sendGameMessage("You need a Farming level of 52 to plant this.");
						return;
					}
					FarmingManager.plantLily(player, 728);
					player.lily = true;
					player.hasPlantedFlower = true;

					// Start of Catherby Farming

					// Start of Farming, Potato Seeds Below.
				} else if (itemId == 5318 && object.getId() == 8553 && player.hasPlantedCA == false && player.mustRakeCA == true) {
					CathFarming.plantPotatoCA(player, 711);
					player.potatoCA = true;
					player.hasPlantedCA = true;

				} else if (itemId == 5318 && object.getId() == 8552 && player.hasPlantedCB == false && player.mustRakeCB == true) {
					CathFarming.plantPotatoCB(player, 710);
					player.potatoCB = true;
					player.hasPlantedCB = true;
					// End of potato.

					// Water melon, Level needed to grow = 47.
				} else if (itemId == 5321 && object.getId() == 8553 && player.hasPlantedCA == false && player.mustRakeCA == true) {
					if (player.getSkills().getLevel(Skills.FARMING) < 47) {
						player.getPackets().sendGameMessage("You need a Farming level of 47 to plant Melon.");
						return;
					}
					CathFarming.plantMelonCA(player, 711);
					player.melonCA = true;
					player.hasPlantedCA = true;

				} else if (itemId == 5321 && object.getId() == 8552 && player.hasPlantedCB == false && player.mustRakeCB == true) {
					if (player.getSkills().getLevel(Skills.FARMING) < 47) {
						player.getPackets().sendGameMessage("You need a Farming level of 47 to plant Melon.");
						return;
					}
					CathFarming.plantMelonCB(player, 710);
					player.melonCB = true;
					player.hasPlantedCB = true;
					// End of Melon.

				} else if (itemId == 5320 && object.getId() == 8553 && player.hasPlantedCA == false && player.mustRakeCA == true) {
					if (player.getSkills().getLevel(Skills.FARMING) < 71) {
						player.getPackets().sendGameMessage("You need a Farming level of 71 to plant Sweetcorn.");
						return;
					}
					CathFarming.plantSweetCA(player, 711);
					player.sweetCA = true;
					player.hasPlantedCA = true;

				} else if (itemId == 5320 && object.getId() == 8552 && player.hasPlantedCB == false && player.mustRakeCB == true) {
					if (player.getSkills().getLevel(Skills.FARMING) < 71) {
						player.getPackets().sendGameMessage("You need a Farming level of 71 to plant Sweetcorn.");
						return;
					}
					CathFarming.plantSweetCB(player, 710);
					player.sweetCB = true;
					player.hasPlantedCB = true;

					// Guam Herb
				} else if (itemId == 5291 && object.getId() == 8151 && player.hasPlantedHerbC == false && player.mustRakeCH == true) {
					CathFarming.plantGuamC(player, 781);
					player.guamCA = true;
					player.hasPlantedHerbC = true;

				} else if (itemId == 5300 && object.getId() == 8151 && player.hasPlantedHerbC == false && player.mustRakeCH == true) {
					if (player.getSkills().getLevel(Skills.FARMING) < 62) {
						player.getPackets().sendGameMessage("You need a Farming level of 62 to plant this.");
						return;
					}
					CathFarming.plantSnapC(player, 781);
					player.snapCA = true;
					player.hasPlantedHerbC = true;

				} else if (itemId == 5304 && object.getId() == 8151 && player.hasPlantedHerbC == false && player.mustRakeCH == true) {
					if (player.getSkills().getLevel(Skills.FARMING) < 85) {
						player.getPackets().sendGameMessage("You need a Farming level of 85 to plant Torstol Seeds.");
						return;
					}
					CathFarming.plantTorstolC(player, 781);
					player.torstolCA = true;
					player.hasPlantedHerbC = true;

					// Flower Patch, Below is Marigold seeds
				} else if (itemId == 5096 && object.getId() == 7848 && player.hasPlantedFlowerC == false && player.mustRakeCF == true) {
					CathFarming.plantGoldC(player, 729);
					player.goldC = true;
					player.hasPlantedFlowerC = true;

					// Below is White Lily seeds
				} else if (itemId == 14589 && object.getId() == 7848 && player.hasPlantedFlowerC == false && player.mustRakeCF == true) {
					if (player.getSkills().getLevel(Skills.FARMING) < 52) {
						player.getPackets().sendGameMessage("You need a Farming level of 52 to plant this.");
						return;
					}
					CathFarming.plantLilyC(player, 729);
					player.lilyC = true;
					player.hasPlantedFlowerC = true;

					// End of Catherby Farming

					// Start of trees

				} else if (itemId == 5315 && object.getId() == 8389 && player.hasPlantedTree == false) {
					if (player.getSkills().getLevel(Skills.FARMING) < 60) {
						player.getPackets().sendGameMessage("You need a Farming level of 60 to plant this.");
						return;
					}
					FarmingManager.plantYew(player, 701);
					player.yew = true;
					player.hasPlantedTree = true;

				} else if (itemId == 5316 && object.getId() == 8389 && player.hasPlantedTree == false) {
					if (player.getSkills().getLevel(Skills.FARMING) < 75) {
						player.getPackets().sendGameMessage("You need a Farming level of 75 to plant this.");
						return;
					}
					FarmingManager.plantMagic(player, 701);
					player.magic = true;
					player.hasPlantedTree = true;

					/**
					 * Ardougne Farming
					 */
				} else if (itemId == 5318 && object.getId() == 8555 && player.hasPlantedAA == false && player.mustRakeAA == true) {// TODO
																																	// Finish
																																	// all
																																	// seeds
					ArdyFarming.plantPotatoAA(player, 713);
					player.allotmentA = 1;
					player.hasPlantedAA = true;
				} else if (itemId == 5320 && object.getId() == 8555 && player.hasPlantedAA == false && player.mustRakeAA == true) {
					if (player.getSkills().getLevel(Skills.FARMING) < 20) {
						player.getPackets().sendGameMessage("You need a Farming level of 20 to plant Sweetcorn.");
						return;
					}
					ArdyFarming.plantSweetAA(player, 713);
					player.allotmentA = 3;
					player.hasPlantedAA = true;
				} else if (itemId == 5321 && object.getId() == 8555 && player.hasPlantedAA == false && player.mustRakeAA == true) {
					if (player.getSkills().getLevel(Skills.FARMING) < 47) {
						player.getPackets().sendGameMessage("You need a Farming level of 47 to plant Melons.");
						return;
					}
					ArdyFarming.plantMelonAA(player, 713);
					player.allotmentA = 2;
					player.hasPlantedAA = true;
					// End of farming.

				} else if (itemId == 4252 && object.getId() == 5282) {
					player.getEctophial().refillEctophial(player);
				} else if (itemId == 1440 && object.getId() == 2455) {
					Runecrafting.enterEarthAltar(player);
				} else if (itemId == 1442 && object.getId() == 2456) {
					Runecrafting.enterFireAltar(player);
				} else if (itemId == 1444 && object.getId() == 2454) {
					Runecrafting.enterWaterAltar(player);
				} else if (itemId == 1446 && object.getId() == 2457) {
					Runecrafting.enterBodyAltar(player);
				} else if (itemId == 1448 && object.getId() == 2453) {
					Runecrafting.enterMindAltar(player);
				} else if (object.getId() == 733 || object.getId() == 64729) {
					player.setNextAnimation(new Animation(PlayerCombat.getWeaponAttackEmote(-1, 0)));
					slashWeb(player, object);
				} else if (object.getId() == 48803 && itemId == 954) {
					if (player.isKalphiteLairSetted())
						return;
					player.getInventory().deleteItem(954, 1);
					player.setKalphiteLair();
				} else if (object.getId() == 48802 && itemId == 954) {
					if (player.isKalphiteLairEntranceSetted())
						return;
					player.getInventory().deleteItem(954, 1);
					player.setKalphiteLairEntrance();
				} else {
					switch (objectDef.name.toLowerCase()) {
					case "anvil":
						ForgingBar bar = ForgingBar.forId(itemId);
						if (bar != null)
							ForgingInterface.sendSmithingInterface(player, bar);
						break;
					case "fire":
						if (objectDef.containsOption(4, "Add-logs") && Bonfire.addLog(player, object, item))
							return;
					case "range":
					case "cooking range":
					case "stove":
						Cookables cook = Cooking.isCookingSkill(item);
						if (cook != null) {
							player.getDialogueManager().startDialogue("CookingD", cook, object);
							return;
						}
						player.getDialogueManager().startDialogue("SimpleMessage", "You can't cook that on a " + (objectDef.name.equals("Fire") ? "fire" : "range") + ".");
						break;
					default:
						break;
					}
					if (Settings.DEBUG)
						System.out.println("Item on object: " + object.getId());
				}
			}
		}, objectDef.getSizeX(), objectDef.getSizeY(), object.getRotation()));
	}
}
