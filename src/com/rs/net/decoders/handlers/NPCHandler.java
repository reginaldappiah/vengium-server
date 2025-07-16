package com.rs.net.decoders.handlers;

import com.rs.Settings;
import com.rs.game.Animation;
import com.rs.game.ForceTalk;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.npc.others.FireSpirit;
import com.rs.game.npc.others.LivingRock;
import com.rs.game.npc.pet.Pet;
import com.rs.game.npc.slayer.Strykewyrm;
import com.rs.game.player.CoordsEvent;
import com.rs.game.player.Player;
import com.rs.game.player.actions.Fishing;
import com.rs.game.player.actions.Fishing.FishingSpots;
import com.rs.game.player.actions.mining.LivingMineralMining;
import com.rs.game.player.actions.mining.MiningBase;
import com.rs.game.player.actions.thieving.PickPocketAction;
import com.rs.game.player.actions.thieving.PickPocketableNPC;
import com.rs.game.player.content.PlayerLook;
import com.rs.game.player.content.interfaces.DTAgility;
import com.rs.game.player.content.interfaces.DTMonsters;
import com.rs.game.player.content.interfaces.DTRSMVActionLocs;
import com.rs.game.player.dialogues.impl.FremennikShipmaster;
import com.rs.io.InputStream;
import com.rs.utils.Logger;
import com.rs.utils.NPCSpawns;
import com.rs.utils.ShopsHandler;
import com.rs.game.WorldTile;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.player.actions.Rest;
import com.rs.game.player.actions.Listen;

public class NPCHandler {
    
    public static void handleExamine(final Player player, InputStream stream) {
        int npcIndex = stream.readUnsignedShort128();
        boolean forceRun = stream.read128Byte() == 1;
        if (forceRun)
            player.setRun(forceRun);
        final NPC npc = World.getNPCs().get(npcIndex);
        if (npc == null || npc.hasFinished() || !player.getMapRegionsIds().contains(npc.getRegionId()))
            return;
        if (player.getRights() > 1) {
            player.getPackets().sendGameMessage("NPC - [id=" + npc.getId() + ", loc=[" + npc.getX() + ", " + npc.getY() + ", " + npc.getPlane() + "]].");
        }
        player.getPackets().sendNPCMessage(0, npc, "It's a " + npc.getDefinitions().name + ".");
        if (Settings.DEBUG)
            Logger.log("NPCHandler", "examined npc: " + npcIndex + ", " + npc.getId());
    }
    
    public static void handleOption1(final Player player, InputStream stream) {
        int npcIndex = stream.readUnsignedShort128();
        boolean forceRun = stream.read128Byte() == 1;
        final NPC npc = World.getNPCs().get(npcIndex);
        if (npc == null || npc.isCantInteract() || npc.isDead() || npc.hasFinished() || !player.getMapRegionsIds().contains(npc.getRegionId()))
            return;
        player.stopAll(false);
        if (forceRun)
            player.setRun(forceRun);
        if (npc.getDefinitions().name.contains("Banker") || npc.getDefinitions().name.contains("banker")) {
            player.faceEntity(npc);
            if (!player.withinDistance(npc, 2))
                return;
            npc.faceEntity(player);
            player.getDialogueManager().startDialogue("Banker", npc.getId());
            return;
        }
        player.setCoordsEvent(new CoordsEvent(npc, new Runnable() {
            @Override
            public void run() {
                npc.resetWalkSteps();
                player.faceEntity(npc);
                if (!player.getControlerManager().processNPCClick1(npc))
                    return;
                FishingSpots spot = FishingSpots.forId(npc.getId() | 1 << 24);
                if (spot != null) {
                    player.getActionManager().setAction(new Fishing(spot, npc));
                    return; // its a spot, they wont face us
                } else if (npc.getId() >= 8837 && npc.getId() <= 8839) {
                    player.getActionManager().setAction(new LivingMineralMining((LivingRock) npc));
                    return;
                } else if (npc.getId() == 6537) {
                    ShopsHandler.openShop(player, 165);
                } else if (npc.getId() >= 2291 && npc.getId() <= 2294)
                    player.getDialogueManager().startDialogue("RugMerchant", false);
                
                else if (npc.getId() == 8726) {
                    DTAgility.sendOptions(player);
                } else if (npc.getId() == 7929) {
                    DTRSMVActionLocs.sendOptions(player);
                } else if (npc.getId() == 3709) {
                    DTMonsters.sendOptions(player);
                }
                /*
				 * NEW HOME NPCS
				 */
                else if (npc.getId() == 4906) {
                    player.getDialogueManager().startDialogue("WoodcuttingTutor", npc.getId());
                } else if (npc.getId() == 6653) {
                    
                    player.getDialogueManager().startDialogue("Elof", npc.getId());
                    npc.faceEntity(player);
                } else if (npc.getId() == 2205)
                    player.getDialogueManager().startDialogue("DwarvenBoatman", npc.getId());
				
				/* Starter NPC */
                else if (npc.getId() == 945) {
                    if (player.starterstage == 0)
                        player.getDialogueManager().startDialogue("Guthix", npc.getId());
                    else if (player.starterstage == 2)
                        player.getDialogueManager().startDialogue("StarterClass", npc.getId());
                    else if (player.starterstage == 3)
                        player.sendMessage("You may leave through the portal now.");
                    else
                        player.getDialogueManager().startDialogue("NewPlayerTutorial", npc.getId());
                }
				
				/* Shops */
                else if (npc.getId() == 1208)
                    ShopsHandler.openShop(player, 137);
                else if (npc.getId() == 6715)
                    player.getDialogueManager().startDialogue("EstateAgent", npc.getId());
                else if (npc.getId() == 9085)
                    player.getDialogueManager().startDialogue("Kuradal", false);
                else if (npc.getId() == 5532)
                    player.getDialogueManager().startDialogue("SorceressGardenNPCs", npc);
                else if (npc.getId() == 5563)
                    player.getDialogueManager().startDialogue("SorceressGardenNPCs", npc);
                else if (npc.getId() == 5559)
                    player.sendDeath(npc);
                else if (npc.getId() == 15451 && npc instanceof FireSpirit) {
                    FireSpirit spirit = (FireSpirit) npc;
                    spirit.giveReward(player);
                } else if (npc.getId() == 949)
                    player.getDialogueManager().startDialogue("QuestGuide", npc.getId(), null);
                else if (npc.getId() >= 1 && npc.getId() <= 6 || npc.getId() >= 7875 && npc.getId() <= 7884)
                    player.getDialogueManager().startDialogue("Man", npc.getId());
                else if (npc.getId() == 198)
                    player.getDialogueManager().startDialogue("Guildmaster", npc.getId());
                else if (npc.getId() == 9462)
                    Strykewyrm.handleStomping(player, npc);
                else if (npc.getId() == 9707)
                    player.getDialogueManager().startDialogue("FremennikShipmaster", npc.getId(), true);
                else if (npc.getId() == 9708)
                    player.getDialogueManager().startDialogue("FremennikShipmaster", npc.getId(), false);
                else if ((npc.getId() >= 3809 && npc.getId() <= 3812) || npc.getId() == 1800)
                    player.getDialogueManager().startDialogue("GnomeGlider", npc.getId());
                else if (npc.getId() == 2253)
                    player.getDialogueManager().startDialogue("Npcshop2", npc.getId());
                else if (npc.getId() == 2676)
                    player.getDialogueManager().startDialogue("MakeOverMage", npc.getId(), 0);
                else if (npc.getId() == 598)
                    player.getDialogueManager().startDialogue("Hairdresser", npc.getId());
                else if (npc.getId() == 548)
                    player.getDialogueManager().startDialogue("Thessalia", npc.getId());
                else if (npc.getId() == 659)
                    player.getDialogueManager().startDialogue("PrestigeOne");
                else if (npc.getId() == 579)
                    player.getDialogueManager().startDialogue("DrogoDwarf", npc.getId());
                else if (npc.getId() == 582) //dwarves general store
                    player.getDialogueManager().startDialogue("GeneralStore", npc.getId(), 31);
                else if (npc.getId() == 528 || npc.getId() == 529) //edge
                    player.getDialogueManager().startDialogue("GeneralStore", npc.getId(), 1);
                else if (npc.getId() == 522 || npc.getId() == 523) //varrock
                    player.getDialogueManager().startDialogue("GeneralStore", npc.getId(), 8);
                else if (npc.getId() == 520 || npc.getId() == 521) //lumbridge
                    player.getDialogueManager().startDialogue("GeneralStore", npc.getId(), 4);
                else if (npc.getId() == 594)
                    player.getDialogueManager().startDialogue("Nurmof", npc.getId());
                else if (npc.getId() == 665)
                    player.getDialogueManager().startDialogue("BootDwarf", npc.getId());
                else if (npc.getId() == 382 || npc.getId() == 3294 || npc.getId() == 4316)
                    player.getDialogueManager().startDialogue("MiningGuildDwarf", npc.getId(), false);
                else if (npc.getId() == 3295)
                    player.getDialogueManager().startDialogue("MiningGuildDwarf", npc.getId(), true);
                else if (npc.getId() == 537)
                    player.getDialogueManager().startDialogue("Scavvo", npc.getId());
                else if (npc.getId() == 536)
                    player.getDialogueManager().startDialogue("Valaine", npc.getId());
                else if (npc.getId() == 4563) //Crossbow Shop
                    player.getDialogueManager().startDialogue("Hura", npc.getId());
                else if (npc.getId() == 2617)
                    player.getDialogueManager().startDialogue("TzHaarMejJal", npc.getId());
                else if (npc.getId() == 2618)
                    player.getDialogueManager().startDialogue("TzHaarMejKah", npc.getId());
                else if (npc.getId() == 13768)
                    player.getDialogueManager().startDialogue("Dunghandler", npc.getId());
                else if (npc.getId() == 278)
                    player.getControlerManager().startControler("RecipeforDisaster");
                else if (npc.getId() == 654)
                    player.getDialogueManager().startDialogue("Shamus", npc.getId());
                else if (npc.getId() == 650)
                    player.getDialogueManager().startDialogue("Warrior", npc.getId());
                else if (npc.getId() == 2729 || npc.getId() == 657 || npc.getId() == 2728)
                    player.getDialogueManager().startDialogue("MonkOfEntrana", npc.getId());
                if (npc instanceof Pet) {
                    Pet pet = (Pet) npc;
                    if (pet != player.getPet()) {
                        player.getPackets().sendGameMessage("This isn't your pet.");
                        return;
                    }
                    player.setNextAnimation(new Animation(827));
                    pet.pickup();
                } else {
					/*player.getPackets().sendGameMessage(
							"Nothing interesting happens.");*/
                    if (Settings.DEBUG)
                        System.out.println("cliked 1 at npc id : " + npc.getId() + ", " + npc.getX() + ", " + npc.getY() + ", " + npc.getPlane());
                }
            }
        }, npc.getSize()));
    }
    
    public static void handleOption2(final Player player, InputStream stream) {
        int npcIndex = stream.readUnsignedShort128();
        boolean forceRun = stream.read128Byte() == 1;
        final NPC npc = World.getNPCs().get(npcIndex);
        final NPCDefinitions npcDef = npc.getDefinitions();
        if (npc == null || npc.isCantInteract() || npc.isDead() || npc.hasFinished() || !player.getMapRegionsIds().contains(npc.getRegionId()))
            return;
        player.stopAll(false);
        if (forceRun)
            player.setRun(forceRun);
        if (npc.getDefinitions().name.contains("Banker") || npc.getDefinitions().name.contains("banker")) {
            player.faceEntity(npc);
            if (!player.withinDistance(npc, 2))
                return;
            npc.faceEntity(player);
            player.getBank().openBank();
            return;
        }
        player.setCoordsEvent(new CoordsEvent(npc, new Runnable() {
            @Override
            public void run() {
                npc.resetWalkSteps();
                player.faceEntity(npc);
                FishingSpots spot = FishingSpots.forId(npc.getId() | (2 << 24));
                if (spot != null) {
                    player.getActionManager().setAction(new Fishing(spot, npc));
                    return;
                }
                PickPocketableNPC pocket = PickPocketableNPC.get(npc.getId());
                if (pocket != null) {
                    player.getActionManager().setAction(new PickPocketAction(npc, pocket));
                    return;
                }
                if (npc instanceof Familiar) {
                    if (npc.getDefinitions().hasOption("store")) {
                        if (player.getFamiliar() != npc) {
                            player.getPackets().sendGameMessage("That isn't your familiar.");
                            return;
                        }
                        player.getFamiliar().store();
                    } else if (npc.getDefinitions().hasOption("cure")) {
                        if (player.getFamiliar() != npc) {
                            player.getPackets().sendGameMessage("That isn't your familiar.");
                            return;
                        }
                        if (!player.getPoison().isPoisoned()) {
                            player.getPackets().sendGameMessage("Your arent poisoned or diseased.");
                            return;
                        } else {
                            player.getFamiliar().drainSpecial(2);
                            player.addPoisonImmune(120);
                        }
                    }
                    return;
                }
                npc.faceEntity(player);
                if (!player.getControlerManager().processNPCClick2(npc))
                    return;
                
                if (npcDef.name.toLowerCase().contains("musician")) {
                    player.stopAll();
                    player.getActionManager().setAction(new Listen());
                    return;
                }
                if (npc.getId() == 9707) {
                    FremennikShipmaster.sail(player, true);
                } else if (npc.getId() == 659)
                    if (player.RSMVerPoints >= 1000 || player.isBronzeDonor() || player.isSilverDonor() || player.isGoldDonor() || player.isPlatinumDonor() || player.isDiamondDonor() || player.isJGUTTDonor()) {
                        ShopsHandler.openShop(player, 166);
                    } else {
                        player.getPackets().sendGameMessage("<img=15><col=ff0000>You need the RSMVer Prestige 10 or a donor to use this shop.");
                    }
                else if (npc.getId() == 9708)
                    FremennikShipmaster.sail(player, false);
                else if (npc.getId() == 8726)
                    player.getDialogueManager().startDialogue("AgilityLocations", npc.getId());
                else if (npc.getId() == 13455 || npc.getId() == 2617 || npc.getId() == 2618 || npc.getId() == 15194)
                    player.getBank().openBank();
				/* Keldagrim */
                else if (npc.getId() == 1843)
                    player.useStairs(-1, new WorldTile(2836, 10143, 0), 3, 4);
				/* Shops */
                else if (npc.getId() == 520 || npc.getId() == 521)
                    ShopsHandler.openShop(player, 161);
                else if (npc.getId() == 522 || npc.getId() == 523)
                    ShopsHandler.openShop(player, 1);
                else if (npc.getId() == 548)
                    ShopsHandler.openShop(player, 2);
                else if (npc.getId() == 546)
                    ShopsHandler.openShop(player, 3);
                else if (npc.getId() == 551 || npc.getId() == 552)
                    ShopsHandler.openShop(player, 4);
                else if (npc.getId() == 595)
                    ShopsHandler.openShop(player, 5);
                else if (npc.getId() == 550)
                    ShopsHandler.openShop(player, 7);
                else if (npc.getId() == 549)
                    ShopsHandler.openShop(player, 8);
                else if (npc.getId() == 576)
                    ShopsHandler.openShop(player, 10);
                else if (npc.getId() == 692)
                    ShopsHandler.openShop(player, 11);
                    //else if (npc.getId() == )
                    //ShopsHandler.openShop(player, );
                else if (npc.getId() == 545)
                    ShopsHandler.openShop(player, 13);
                else if (npc.getId() == 1658)
                    ShopsHandler.openShop(player, 14);
                    //else if (npc.getId() == )
                    //ShopsHandler.openShop(player, );
                    //else if (npc.getId() == )
                    //ShopsHandler.openShop(player, );
                    //..
                else if (npc.getId() == 571)
                    ShopsHandler.openShop(player, 23);
                else if (npc.getId() == 569)
                    ShopsHandler.openShop(player, 24);
                else if (npc.getId() == 570)
                    ShopsHandler.openShop(player, 25);
                else if (npc.getId() == 573)
                    ShopsHandler.openShop(player, 26);
                else if (npc.getId() == 572)
                    ShopsHandler.openShop(player, 27);
                else if (npc.getId() == 589)
                    ShopsHandler.openShop(player, 28);
                else if (npc.getId() == 590)
                    ShopsHandler.openShop(player, 29);
                else if (npc.getId() == 557)
                    ShopsHandler.openShop(player, 30);
                else if (npc.getId() == 11699)
                    ShopsHandler.openShop(player, 31);
                else if (npc.getId() == 556)
                    ShopsHandler.openShop(player, 32);
                else if (npc.getId() == 559)
                    ShopsHandler.openShop(player, 33);
                else if (npc.getId() == 583)
                    ShopsHandler.openShop(player, 34);
                else if (npc.getId() == 540)
                    ShopsHandler.openShop(player, 35);
                else if (npc.getId() == 541)
                    ShopsHandler.openShop(player, 36);
                else if (npc.getId() == 542)
                    ShopsHandler.openShop(player, 37);
                else if (npc.getId() == 544)
                    ShopsHandler.openShop(player, 38);
                else if (npc.getId() == 2306)
                    ShopsHandler.openShop(player, 39);
                else if (npc.getId() == 932)
                    ShopsHandler.openShop(player, 40);
                else if (npc.getId() == 933)
                    ShopsHandler.openShop(player, 41);
                else if (npc.getId() == 1917)
                    ShopsHandler.openShop(player, 42);
                else if (npc.getId() == 538)
                    ShopsHandler.openShop(player, 43);
                else if (npc.getId() == 580)
                    ShopsHandler.openShop(player, 44);
                else if (npc.getId() == 577)
                    ShopsHandler.openShop(player, 45);
                else if (npc.getId() == 584)
                    ShopsHandler.openShop(player, 46);
                else if (npc.getId() == 581)
                    ShopsHandler.openShop(player, 47);
                else if (npc.getId() == 4251)
                    ShopsHandler.openShop(player, 48);
                else if (npc.getId() == 4294)
                    ShopsHandler.openShop(player, 49);
                else if (npc.getId() == 4293)
                    ShopsHandler.openShop(player, 50);
                else if (npc.getId() == 4295)
                    ShopsHandler.openShop(player, 51);
                else if (npc.getId() == 586)
                    ShopsHandler.openShop(player, 52);
                else if (npc.getId() == 797)
                    ShopsHandler.openShop(player, 53);
                else if (npc.getId() == 587)
                    ShopsHandler.openShop(player, 55);
                    //else if (npc.getId() == 933)
                    //ShopsHandler.openShop(player, 56);
                else if (npc.getId() == 2270)
                    ShopsHandler.openShop(player, 57);
                else if (npc.getId() == 519)
                    ShopsHandler.openShop(player, 58);
                else if (npc.getId() == 575)
                    ShopsHandler.openShop(player, 59);
                else if (npc.getId() == 562)
                    ShopsHandler.openShop(player, 60);
                else if (npc.getId() == 563)
                    ShopsHandler.openShop(player, 61);
                else if (npc.getId() == 1972)
                    ShopsHandler.openShop(player, 62);
                else if (npc.getId() == 848)
                    ShopsHandler.openShop(player, 63);
                else if (npc.getId() == 601)
                    ShopsHandler.openShop(player, 64);
                else if (npc.getId() == 600)
                    ShopsHandler.openShop(player, 65);
                else if (npc.getId() == 851)
                    ShopsHandler.openShop(player, 66);
                else if (npc.getId() == 602)
                    ShopsHandler.openShop(player, 67);
                else if (npc.getId() == 603)
                    ShopsHandler.openShop(player, 68);
                else if (npc.getId() == 747)
                    ShopsHandler.openShop(player, 69);
                else if (npc.getId() == 1866)
                    ShopsHandler.openShop(player, 70);
                else if (npc.getId() == 836)
                    ShopsHandler.openShop(player, 71);
                else if (npc.getId() == 3045)
                    ShopsHandler.openShop(player, 72);
                else if (npc.getId() == 5109)
                    ShopsHandler.openShop(player, 73);
                else if (npc.getId() == 3038)
                    ShopsHandler.openShop(player, 74);
                else if (npc.getId() == 593)
                    ShopsHandler.openShop(player, 75);
                else if (npc.getId() == 1526)
                    ShopsHandler.openShop(player, 76);
                else if (npc.getId() == 2039)
                    ShopsHandler.openShop(player, 77);
                else if (npc.getId() == 874)
                    ShopsHandler.openShop(player, 78);
                else if (npc.getId() == 14864)
                    ShopsHandler.openShop(player, 79);
                else if (npc.getId() == 7048)
                    ShopsHandler.openShop(player, 80);
                else if (npc.getId() == 7053)
                    ShopsHandler.openShop(player, 81);
                else if (npc.getId() == 4518)
                    ShopsHandler.openShop(player, 82);
                else if (npc.getId() == 1315)
                    ShopsHandler.openShop(player, 83);
                else if (npc.getId() == 1282)
                    ShopsHandler.openShop(player, 84);
                else if (npc.getId() == 1316)
                    ShopsHandler.openShop(player, 85);
                else if (npc.getId() == 1301)
                    ShopsHandler.openShop(player, 86);
                else if (npc.getId() == 1303)
                    ShopsHandler.openShop(player, 87);
                else if (npc.getId() == 5483)
                    ShopsHandler.openShop(player, 88);
                else if (npc.getId() == 5485)
                    ShopsHandler.openShop(player, 89);
                else if (npc.getId() == 5486)
                    ShopsHandler.openShop(player, 90);
                else if (npc.getId() == 5484)
                    ShopsHandler.openShop(player, 92);
                else if (npc.getId() == 5509)
                    ShopsHandler.openShop(player, 93);
                else if (npc.getId() == 209)
                    ShopsHandler.openShop(player, 94);
                else if (npc.getId() == 594)
                    ShopsHandler.openShop(player, 95);
                else if (npc.getId() == 4563 || npc.getId() == 4559 || npc.getId() == 4558)
                    ShopsHandler.openShop(player, 96);
                else if (npc.getId() == 3671)
                    ShopsHandler.openShop(player, 97);
                else if (npc.getId() == 970)
                    ShopsHandler.openShop(player, 98);
                else if (npc.getId() == 2233)
                    ShopsHandler.openShop(player, 99);
                else if (npc.getId() == 1860)
                    ShopsHandler.openShop(player, 100);
                else if (npc.getId() == 585)
                    ShopsHandler.openShop(player, 101);
                else if (npc.getId() == 1042)
                    ShopsHandler.openShop(player, 102);
                else if (npc.getId() == 1038)
                    ShopsHandler.openShop(player, 103);
                else if (npc.getId() == 1039)
                    ShopsHandler.openShop(player, 104);
                else if (npc.getId() == 2307)
                    ShopsHandler.openShop(player, 105);
                else if (npc.getId() == 1688)
                    ShopsHandler.openShop(player, 106);
                else if (npc.getId() == 4650)
                    ShopsHandler.openShop(player, 107);
                else if (npc.getId() == 3541)
                    ShopsHandler.openShop(player, 108);
                else if (npc.getId() == 520)
                    ShopsHandler.openShop(player, 109);
                else if (npc.getId() == 588)
                    ShopsHandler.openShop(player, 110);
                else if (npc.getId() == 2623)
                    ShopsHandler.openShop(player, 111);
                else if (npc.getId() == 2622)
                    ShopsHandler.openShop(player, 112);
                else if (npc.getId() == 2620)
                    ShopsHandler.openShop(player, 113);
                else if (npc.getId() == 793 || npc.getId() == 794)
                    ShopsHandler.openShop(player, 114);
                else if (npc.getId() == 560)
                    ShopsHandler.openShop(player, 115);
                else if (npc.getId() == 516)
                    ShopsHandler.openShop(player, 116);
                else if (npc.getId() == 517)
                    ShopsHandler.openShop(player, 117);
                else if (npc.getId() == 4716)
                    ShopsHandler.openShop(player, 118);
                else if (npc.getId() == 3166)
                    ShopsHandler.openShop(player, 119);
                else if (npc.getId() == 3161)
                    ShopsHandler.openShop(player, 120);
                else if (npc.getId() == 4362)
                    ShopsHandler.openShop(player, 121);
                else if (npc.getId() == 3163)
                    ShopsHandler.openShop(player, 122);
                else if (npc.getId() == 3162)
                    ShopsHandler.openShop(player, 123);
                else if (npc.getId() == 2548)
                    ShopsHandler.openShop(player, 124);
                else if (npc.getId() == 5266)
                    ShopsHandler.openShop(player, 125);
                else if (npc.getId() == 1980)
                    ShopsHandler.openShop(player, 126);
                else if (npc.getId() == 1982)
                    ShopsHandler.openShop(player, 127);
                else if (npc.getId() == 5264)
                    ShopsHandler.openShop(player, 128);
                else if (npc.getId() == 5268)
                    ShopsHandler.openShop(player, 129);
                else if (npc.getId() == 1393)
                    ShopsHandler.openShop(player, 130);
                else if (npc.getId() == 2353)
                    ShopsHandler.openShop(player, 131);
                else if (npc.getId() == 2357)
                    ShopsHandler.openShop(player, 132);
                else if (npc.getId() == 2356)
                    ShopsHandler.openShop(player, 133);
                else if (npc.getId() == 11547)
                    ShopsHandler.openShop(player, 134);
                else if (npc.getId() == 246)
                    ShopsHandler.openShop(player, 135);
                else if (npc.getId() == 1834)
                    ShopsHandler.openShop(player, 136);
                else if (npc.getId() == 564)
                    ShopsHandler.openShop(player, 139);
                else if (npc.getId() == 566)
                    ShopsHandler.openShop(player, 140);
                else if (npc.getId() == 682)
                    ShopsHandler.openShop(player, 141);
                else if (npc.getId() == 683)
                    ShopsHandler.openShop(player, 142);
                else if (npc.getId() == 1436)
                    ShopsHandler.openShop(player, 143);
                else if (npc.getId() == 1434)
                    ShopsHandler.openShop(player, 144);
                else if (npc.getId() == 1435)
                    ShopsHandler.openShop(player, 145);
                else if (npc.getId() == 1437)
                    ShopsHandler.openShop(player, 146);
                else if (npc.getId() == 1433)
                    ShopsHandler.openShop(player, 147);
                else if (npc.getId() == 2160 || npc.getId() == 2191)
                    ShopsHandler.openShop(player, 148);
                else if (npc.getId() == 2156)
                    ShopsHandler.openShop(player, 149);
                else if (npc.getId() == 2159)
                    ShopsHandler.openShop(player, 150);
                else if (npc.getId() == 2157)
                    ShopsHandler.openShop(player, 151);
                else if (npc.getId() == 2158)
                    ShopsHandler.openShop(player, 152);
                else if (npc.getId() == 2162)
                    ShopsHandler.openShop(player, 153);
                else if (npc.getId() == 2151)
                    ShopsHandler.openShop(player, 155);
                else if (npc.getId() == 2153)
                    ShopsHandler.openShop(player, 156);
                else if (npc.getId() == 2152)
                    ShopsHandler.openShop(player, 157);
                else if (npc.getId() == 4248)
                    ShopsHandler.openShop(player, 158);
                else if (npc.getId() == 2154)
                    ShopsHandler.openShop(player, 159);
                else if (npc.getId() == 2161)
                    ShopsHandler.openShop(player, 160);
                else if (npc.getId() == 2676)
                    PlayerLook.openMageMakeOver(player);
                else if (npc.getId() == 598)
                    PlayerLook.openHairdresserSalon(player);
                else if ((npc.getId() >= 3809 && npc.getId() <= 3812) || npc.getId() == 1800)
                    player.getInterfaceManager().sendInterface(138);
                else if (npc instanceof Pet) {
                    if (npc != player.getPet()) {
                        player.getPackets().sendGameMessage("This isn't your pet!");
                        return;
                    }
                    Pet pet = player.getPet();
                    player.getPackets().sendMessage(99, "Pet [id=" + pet.getId() + ", hunger=" + pet.getDetails().getHunger() + ", growth=" + pet.getDetails().getGrowth() + ", stage=" + pet.getDetails().getStage() + "].", player);
                } else {
                    player.getPackets().sendGameMessage("Nothing interesting happens.");
                    if (Settings.DEBUG)
                        System.out.println("cliked 2 at npc id : " + npc.getId() + ", " + npc.getX() + ", " + npc.getY() + ", " + npc.getPlane());
                }
            }
        }, npc.getSize()));
    }
    
    public static void handleOption3(final Player player, InputStream stream) {
        int npcIndex = stream.readUnsignedShort128();
        boolean forceRun = stream.read128Byte() == 1;
        final NPC npc = World.getNPCs().get(npcIndex);
        if (npc == null || npc.isCantInteract() || npc.isDead() || npc.hasFinished() || !player.getMapRegionsIds().contains(npc.getRegionId()))
            return;
        player.stopAll(false);
        if (forceRun)
            player.setRun(forceRun);
        player.setCoordsEvent(new CoordsEvent(npc, new Runnable() {
            @Override
            public void run() {
                npc.resetWalkSteps();
                if (!player.getControlerManager().processNPCClick3(npc))
                    return;
                player.faceEntity(npc);
                if (npc.getId() >= 8837 && npc.getId() <= 8839) {
                    MiningBase.propect(player, "You examine the remains...", "The remains contain traces of living minerals.");
                    return;
                    
                }
                npc.faceEntity(player);
                if (npc.getId() == 548)
                    PlayerLook.openThessaliasMakeOver(player);
				/* Shops */
                else if (npc.getId() == 1301)
                    PlayerLook.openYrsaShop(player);
                else if (npc.getId() == 5913)
                    ShopsHandler.openShop(player, 6);
                else if (npc.getId() == 1597 || npc.getId() == 8273 || npc.getId() == 8462 || npc.getId() == 8274 || npc.getId() == 1598 || npc.getId() == 7780 || npc.getId() == 8275 || npc.getId() == 8467 || npc.getId() == 9084)
                    ShopsHandler.openShop(player, 54);
                else if (npc.getId() == 5532) {
                    npc.setNextForceTalk(new ForceTalk("Senventior Disthinte Molesko!"));
                    player.getControlerManager().startControler("SorceressGarden");
                    
                } else
                    player.getPackets().sendGameMessage("Nothing interesting happens.");
            }
        }, npc.getSize()));
        if (Settings.DEBUG)
            System.out.println("cliked 3 at npc id : " + npc.getId() + ", " + npc.getX() + ", " + npc.getY() + ", " + npc.getPlane());
    }
}
