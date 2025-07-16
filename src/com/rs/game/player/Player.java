package com.rs.game.player;

import java.net.*;
import java.io.*;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.cores.CoresManager;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.EntityList;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.Poison;
import com.rs.game.Region;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.FloorItem;
import com.rs.game.item.Item;
import com.rs.game.minigames.CastleWars;
import com.rs.game.minigames.clanwars.FfaZone;
import com.rs.game.minigames.clanwars.WarControler;
import com.rs.game.minigames.duel.DuelArena;
import com.rs.game.minigames.duel.DuelRules;
import com.rs.game.npc.NPC;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.npc.godwars.zaros.Nex;
import com.rs.game.npc.pet.Pet;
import com.rs.game.player.actions.PlayerCombat;
import com.rs.game.player.content.BrimhavenMatrix;
import com.rs.game.player.content.DwarfCannon;
import com.rs.game.player.content.Ectophial;
import com.rs.game.player.content.FairyRing;
import com.rs.game.player.content.FriendChatsManager;
import com.rs.game.player.content.Minecart;
import com.rs.game.player.content.MoneyPouch;
import com.rs.game.player.content.Notes;
import com.rs.game.player.content.Notes.Note;
import com.rs.game.player.content.Pots;
import com.rs.game.player.content.ReportAbuse;
import com.rs.game.player.content.SkillCapeCustomizer;
import com.rs.game.player.content.Spiritbag;
import com.rs.game.player.content.Teletab;
import com.rs.game.player.content.TriviaBot;
import com.rs.game.player.content.WarriorsGuild;
import com.rs.game.player.content.Whirlpool;
import com.rs.game.player.content.construction.House;
import com.rs.game.player.content.construction.HouseLocation;
import com.rs.game.player.content.construction.Room;
import com.rs.game.player.content.construction.RoomReference;
import com.rs.game.player.content.construction.ServantType;
import com.rs.game.player.content.pet.PetManager;
import com.rs.game.player.controlers.CorpBeastControler;
import com.rs.game.player.controlers.CrucibleControler;
import com.rs.game.player.controlers.DTControler;
import com.rs.game.player.controlers.FightCaves;
import com.rs.game.player.controlers.FightKiln;
import com.rs.game.player.controlers.GodWars;
import com.rs.game.player.controlers.NomadsRequiem;
import com.rs.game.player.controlers.QueenBlackDragonController;
import com.rs.game.player.controlers.Wilderness;
import com.rs.game.player.controlers.ZGDControler;
import com.rs.game.player.controlers.castlewars.CastleWarsPlaying;
import com.rs.game.player.controlers.castlewars.CastleWarsWaiting;
import com.rs.game.player.controlers.fightpits.FightPitsArena;
import com.rs.game.player.controlers.pestcontrol.PestControlGame;
import com.rs.game.player.controlers.pestcontrol.PestControlLobby;
import com.rs.game.player.customsaves.SavedAnimations;
import com.rs.game.player.customsaves.SavedLocations;
import com.rs.game.player.customsaves.SavedOutfits;
import com.rs.game.player.customsaves.SavedRenderAnimations;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.net.Session;
import com.rs.net.decoders.WorldPacketsDecoder;
import com.rs.net.decoders.handlers.ButtonHandler;
import com.rs.net.encoders.WorldPacketsEncoder;
import com.rs.utils.IsaacKeyPair;
import com.rs.utils.Logger;
import com.rs.utils.MachineInformation;
import com.rs.utils.PkRank;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import com.rs.game.player.content.PlayerLook;

public class Player extends Entity {
	private transient VarsManager varsManager;
	private Entity target;
	public static final int TELE_MOVE_TYPE = 127;
	public static final int WALK_MOVE_TYPE = 1;
	public static final int RUN_MOVE_TYPE = 2;
	private static final long serialVersionUID = 2011932556974180375L;
	private boolean allowsProfanity;
	public transient long tolerance = 0L;
	public transient long idleTime = 0L;
	public transient long dyingTime = 0L;
	public transient long alchDelay = 0L;
	public transient boolean disconnected = false;
	private long lastLoggedIn;
	private transient DwarfCannon dwarfCannon;
	private transient String username;
	private transient Session session;
	private boolean finishedTask;
	private transient boolean clientLoadedMapRegion;
	private transient int displayMode;
	public int maximumOnline = 0;

	public void rspsdata(Player player, String username) {
		try {
			username = username.replaceAll(" ", "_");
			String secret = "dc40b7120e77741d191c0d2b82cea7be"; // YOUR SECRET
																// KEY!
			String email = "multak190@gmail.com"; // This is the one you use to
													// login into RSPS-PAY
			URL url = new URL("http://rsps-pay.com/includes/listener.php?username=" + username + "&secret=" + secret + "&email=" + email);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String results = reader.readLine();
			if (results.toLowerCase().contains("!error:")) {
				// Logger.log(this, "[RSPS-PAY]"+results);
			} else {
				String[] ary = results.split(",");
				for (int i = 0; i < ary.length; i++) {
					switch (ary[i]) {
					case "0":
						player.getPackets().sendGameMessage("Can't find your donation in the database");
						break;
					/*
					 * RSMVer Tokens
					 */
					case "5855": 
						player.getInventory().addItem(29980, 100);
						player.getPackets().sendGameMessage("You have succesfully redeemed 100 RSMVer tokens!");
						break;
					case "5856":
						player.getInventory().addItem(29980, 500);
						player.getPackets().sendGameMessage("You have succesfully redeemed 500 RSMVer tokens!");
						break;
					case "5857":
						player.getInventory().addItem(29980, 1000);
						player.getPackets().sendGameMessage("You have succesfully redeemed 1,000 RSMVer tokens!");
						break;
					/*
					 * Donor Statuses
					 */
					case "6435":	
						player.setBronzeDonor(true);
						player.setSilverDonor(false);
						player.setGoldDonor(false);
						player.setPlatinumDonor(false);
						player.setDiamondDonor(false);;
						player.setJGUTTDonor(false);			
						player.getInventory().addItem(29980, 50);
						player.getPackets().sendGameMessage("You have succesfully redeemed Bronze Donor!");
						break;
					case "6436":
						player.setBronzeDonor(false);
						player.setSilverDonor(true);
						player.setGoldDonor(false);
						player.setPlatinumDonor(false);
						player.setDiamondDonor(false);;
						player.setJGUTTDonor(false);			
						player.getInventory().addItem(29980, 100);
						player.getPackets().sendGameMessage("You have succesfully redeemed Silver Donor!");
						break;
					case "6445":
						player.setBronzeDonor(false);
						player.setSilverDonor(false);
						player.setGoldDonor(true);
						player.setPlatinumDonor(false);
						player.setDiamondDonor(false);;
						player.setJGUTTDonor(false);		
						player.getInventory().addItem(29980, 500);
						player.getPackets().sendGameMessage("You have succesfully redeemed Gold Donor!");
						break;
					case "6458":
						player.setBronzeDonor(false);
						player.setSilverDonor(false);
						player.setGoldDonor(true);
						player.setPlatinumDonor(false);
						player.setDiamondDonor(false);;
						player.setJGUTTDonor(false);		
						player.getInventory().addItem(29980, 100*10);
						player.getPackets().sendGameMessage("You have succesfully redeemed Platinum Donor!");
						break;
					case "6459":
						player.setBronzeDonor(false);
						player.setSilverDonor(false);
						player.setGoldDonor(true);
						player.setPlatinumDonor(false);
						player.setDiamondDonor(false);;
						player.setJGUTTDonor(false);		
						player.getInventory().addItem(29980, 500*10);
						player.getPackets().sendGameMessage("You have succesfully redeemed Diamond Donor!");
						break;
					case "6460":
						player.setBronzeDonor(false);
						player.setSilverDonor(false);
						player.setGoldDonor(true);
						player.setPlatinumDonor(false);
						player.setDiamondDonor(false);;
						player.setJGUTTDonor(false);		
						player.getInventory().addItem(29980, 5000*10);
						player.getPackets().sendGameMessage("You have succesfully redeemed J-Guttz Donor!");
						break;
					
					}
				}
			}
		} catch (IOException e) {
		}
	}
	
	
	private boolean donor = false;
	private boolean bronzedonor = false;
	private boolean silverDonor = false;
	private boolean goldDonor = false;
	private boolean platinumDonor = false;
	private boolean diamondDonor = false;
	private boolean JGUTTDonor = false;
	private long bronzedonorTill;
	private long silverDonorTill;
	private long goldDonorTill;
	private long platinumDonorTill;
	private long diamondDonorTill;
	private long JGUTTDonorTill;

	

	public void setAlchDelay(long delay) {
		this.alchDelay = (delay + Utils.currentTimeMillis());
	}

	public boolean canAlch() {
		return this.alchDelay < Utils.currentTimeMillis();
	}

	/*
	 * Construction
	 */

	private House house;
	private transient RoomReference roomReference;

	public boolean inRing;
	public boolean hasHouse;
	private boolean hasBeenToHouse = false;
	private boolean buildMode;
	private boolean hasConfirmedRoomDeletion = false;
	private int houseX;
	private int houseY;
	private int[] boundChuncks;
	private List<WorldObject> conObjectsToBeLoaded;
	private List<RoomReference> rooms;
	private int place;
	private HouseLocation portalLocation;
	private ServantType butler;

	public int chair1;
	public int chairX1;
	public int chairY1;

	public int chair2;
	public int chairX2;
	public int chairY2;

	public int chair3;
	public int chairX3;
	public int chairY3;

	public int rug1;
	public int rugX1;
	public int rugY1;

	public int fireplace1;
	public int fireplaceX1;
	public int fireplaceY1;

	public int fireplace2;
	public int fireplaceX2;
	public int fireplaceY2;

	public int fireplace3;
	public int fireplaceX3;
	public int fireplaceY3;

	public int bookcase1;
	public int bookcaseX1;
	public int bookcaseY1;

	public int bookcase2;
	public int bookcaseX2;
	public int bookcaseY2;

	public int bookcase3;
	public int bookcaseX3;
	public int bookcaseY3;

	public int bookcase4;
	public int bookcaseX4;
	public int bookcaseY4;

	public int bookcase5;
	public int bookcaseX5;
	public int bookcaseY5;

	public int table1;
	public int tableX1;
	public int tableY1;

	public int small1plant1;
	public int small1plantX1;
	public int small1plantY1;

	public int small2plant1;
	public int small2plantX1;
	public int small2plantY1;

	public int big1plant1;
	public int big1plantX1;
	public int big1plantY1;

	public int big2plant1;
	public int big2plantX1;
	public int big2plantY1;

	public int bench1;
	public int benchX1;
	public int benchY1;

	public int bench2;
	public int benchX2;
	public int benchY2;

	public int bench3;
	public int benchX3;
	public int benchY3;

	public int bench4;
	public int benchX4;
	public int benchY4;

	public int bench5;
	public int benchX5;
	public int benchY5;

	public int bench6;
	public int benchX6;
	public int benchY6;

	public int bench7;
	public int benchX7;
	public int benchY7;

	public int bench8;
	public int benchX8;
	public int benchY8;

	private boolean[] taskType = new boolean[10];
	private boolean boostedtask;
	private transient int screenWidth;
	private transient Ectophial ectophial;
	private transient Whirlpool whirlpool;
	private transient BrimhavenMatrix matrix;
	private transient Teletab teletab;
	private transient Minecart minecart;
	private transient Spiritbag spiritbag;

	private MoneyPouch moneyPouch;
	private boolean inTask;
	private transient int screenHeight;
	private transient WarriorsGuild WarriorsGuild;
	private transient InterfaceManager interfaceManager;
	private transient SavedOutfits savedOutfits;
	private transient SavedLocations savedLocations;
	private transient SavedAnimations savedAnimations;
	private transient SavedRenderAnimations savedRenderAnimations;
	private transient ReportAbuse reportAbuse;
	private int taskAmount;
	private transient DialogueManager dialogueManager;
	private transient HintIconsManager hintIconsManager;
	private transient ActionManager actionManager;
	private transient CutscenesManager cutscenesManager;
	private transient PriceCheckManager priceCheckManager;
	private transient CoordsEvent coordsEvent;
	private transient FriendChatsManager currentFriendChat;
	private transient Trade trade;
	private transient Teleto teleto;
	private transient DuelRules lastDuelRules;
	private transient IsaacKeyPair isaacKeyPair;
	private transient Pet pet;
	public int taskpoints;
	public int dungpoints = 0;
	public int Disasterpoints;
	public boolean rake = false;
	public boolean spade = false;
	public boolean pick = false;
	public boolean isHidden = false;
	public boolean isHiddenInter = false;
	private int[] clanCapeCustomized;
	private int[] clanCapeSymbols;
	public String teletotarget = "";
	public boolean teletoaccepted = false;
	public boolean teletodeclined = false;
	

	public void changeAllotment(final Player player) {
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			public void run() {
				if (this.loop == 5) {
					player.getDialogueManager().startDialogue("SimpleMessage", new Object[] { "Once the allotment is raked you can then use a potato seed on the allotment which will then make the potatos start to grow." });
					player.getPackets().sendConfigByFile(708, 6);
				} else if (this.loop == 10) {
					player.getDialogueManager().startDialogue("SimpleMessage", new Object[] { "The potatos will gradually grow, leaving you time to do a farming run to the other farming locations." });
					player.getPackets().sendConfigByFile(708, 8);
				} else if (this.loop == 15) {
					player.getDialogueManager().startDialogue("SimpleMessage", new Object[] { "Once they are fully grown they will be harvestable. Simply click on the potatos to harvest them." });
					player.getPackets().sendConfigByFile(708, 10);
				} else if (this.loop == 18) {
					player.getDialogueManager().startDialogue("SimpleMessage", new Object[] { "Now you know the basics you can test it out for yourself!" });
					player.getPackets().sendConfigByFile(708, 0);
					player.firstScene = true;
					player.setNextWorldTile(new WorldTile(3052, 3304, 0));
				}
				this.loop += 1;
			}
		}, 0, 1);
	}

	public boolean firstScene = false;
	public boolean hasEscavated = false;
	public int farmingStatusA = 0;
	public String farmStatusA = "Needs Raking.";
	public int farmingStatusB = 0;
	public String farmStatusB = "Needs Raking.";
	public int farmingStatusF = 0;
	public String farmStatusF = "Needs Raking.";
	public int farmingStatusH = 0;
	public String farmStatusH = "Needs Raking.";

	public void farmStatus() {
		switch (this.farmingStatusA) {
		case 0:
			this.farmStatusA = "Needs Raking.";
			break;
		case 1:
			this.farmStatusA = "This patch has been raked.";
			break;
		case 2:
			this.farmStatusA = "Growing.";
			break;
		case 3:
			this.farmStatusA = "Fully Grown, Ready to be harvested.";
		}
		switch (this.farmingStatusB) {
		case 0:
			this.farmStatusB = "Needs Raking.";
			break;
		case 1:
			this.farmStatusB = "This patch has been raked.";
			break;
		case 2:
			this.farmStatusB = "Growing.";
			break;
		case 3:
			this.farmStatusB = "Fully Grown, Ready to be harvested.";
		}
		switch (this.farmingStatusF) {
		case 0:
			this.farmStatusF = "Needs Raking.";
			break;
		case 1:
			this.farmStatusF = "This patch has been raked.";
			break;
		case 2:
			this.farmStatusF = "Growing.";
			break;
		case 3:
			this.farmStatusF = "Fully Grown, Ready to be harvested.";
		}
		switch (this.farmingStatusH) {
		case 0:
			this.farmStatusH = "Needs Raking.";
			break;
		case 1:
			this.farmStatusH = "This patch has been raked.";
			break;
		case 2:
			this.farmStatusH = "Growing.";
			break;
		case 3:
			this.farmStatusH = "Fully Grown, Ready to be harvested..";
		}
	}

	public boolean canHarvestA = false;
	public boolean hasHarvestedA = false;
	public boolean hasPlantedA = false;
	public boolean canHarvestB = false;
	public boolean hasHarvestedB = false;
	public boolean hasPlantedB = false;
	public boolean mustRakeA = false;
	public boolean mustRakeB = false;
	public boolean mustRakeH = false;
	public boolean mustRakeF = false;
	public boolean canHarvestHerbA = false;
	public boolean hasHarvestedHerbA = false;
	public boolean hasPlantedHerb = false;
	public boolean canHarvestFlowerA = false;
	public boolean hasHarvestedFlowerA = false;
	public boolean hasPlantedFlower = false;
	public int totaloutfits;
	public boolean savedOutfit1 = false;
	public boolean savedOutfit2 = false;
	public boolean savedOutfit3 = false;
	public boolean savedOutfit4 = false;
	public boolean removedOutfit1 = true;
	public boolean removedOutfit2 = true;
	public boolean removedOutfit3 = true;
	public boolean removedOutfit4 = true;
	
	//RSMVer Points
		public int RSMVerPoints;
	
	//Donor
		private int Donorpoints;

		
	private List<String> cachedChatMessages;
	private long lastChatMessageCache;
	public boolean canHarvestTreeA = false;
	public boolean hasHarvestedTreeA = false;
	public boolean hasPlantedTree = false;
	public int chop = 0;
	public boolean hasAxe = false;
	public boolean potatoA = false;
	public boolean potatoB = false;
	public boolean melonA = false;
	public boolean melonB = false;
	public boolean guamA = false;
	public boolean snapA = false;
	public boolean torstol = false;
	public boolean gold = false;
	public boolean lily = false;
	public boolean yew = false;
	public boolean magic = false;
	public boolean sweetA = false;
	public boolean sweetB = false;
	public int increaseFarmWc = 0;
	public boolean canHarvestCA = false;
	public boolean hasHarvestedCA = false;
	public boolean hasPlantedCA = false;
	public boolean canHarvestCB = false;
	public boolean hasHarvestedCB = false;
	public boolean hasPlantedCB = false;
	public boolean mustRakeCA = false;
	public boolean mustRakeCB = false;
	public boolean mustRakeCH = false;
	public boolean mustRakeCF = false;
	public boolean canHarvestHerbCA = false;
	public boolean hasHarvestedHerbCA = false;
	public boolean hasPlantedHerbC = false;
	public boolean canHarvestFlowerCA = false;
	public boolean hasHarvestedFlowerCA = false;
	public boolean hasPlantedFlowerC = false;
	public boolean potatoCA = false;
	public boolean potatoCB = false;
	public boolean melonCA = false;
	public boolean melonCB = false;
	public boolean sweetCA = false;
	public boolean sweetCB = false;
	public boolean guamCA = false;
	public boolean snapCA = false;
	public boolean torstolCA = false;
	public boolean goldC = false;
	public boolean lilyC = false;
	public int allotmentA = 0;
	public int allotmentB = 0;
	public int allotmentH = 0;
	public int allotmentF = 0;
	public boolean canHarvestAA = false;
	public boolean hasHarvestedAA = false;
	public boolean hasPlantedAA = false;
	public boolean canHarvestAB = false;
	public boolean hasHarvestedAB = false;
	public boolean hasPlantedAB = false;
	public boolean canHarvestHerbAA = false;
	public boolean canHarvestFlowerAF = false;
	public boolean mustRakeAA = false;
	public boolean mustRakeAB = false;
	public boolean mustRakeAH = false;
	public boolean mustRakeAF = false;
	public int increaseWeed = 0;
	public boolean useCompost = false;
	public boolean waitForComp = false;

	public void resetFarm() {
		this.farmingStatusA = 0;
		this.farmingStatusB = 0;
		this.farmingStatusF = 0;
		this.farmingStatusH = 0;
		this.hasEscavated = false;
		this.waitForComp = false;
		this.useCompost = false;
		this.increaseWeed = 0;
		this.canHarvestA = false;
		this.canHarvestB = false;
		this.canHarvestHerbA = false;
		this.canHarvestFlowerA = false;
		this.canHarvestTreeA = false;
		this.hasHarvestedA = false;
		this.hasHarvestedB = false;

		this.hasPlantedA = false;
		this.hasPlantedB = false;
		this.hasPlantedHerb = false;
		this.hasPlantedFlower = false;
		this.hasPlantedTree = false;

		this.mustRakeA = false;
		this.mustRakeB = false;
		this.mustRakeH = false;
		this.mustRakeF = false;

		this.mustRakeCA = false;
		this.mustRakeCB = false;
		this.mustRakeCH = false;
		this.mustRakeCF = false;

		this.potatoA = false;
		this.potatoB = false;
		this.melonA = false;
		this.melonB = false;
		this.sweetA = false;
		this.sweetB = false;
		this.guamA = false;
		this.snapA = false;
		this.torstol = false;
		this.gold = false;
		this.lily = false;
		this.magic = false;
		this.yew = false;
		this.chop = 0;

		this.canHarvestCA = false;
		this.canHarvestCB = false;
		this.canHarvestHerbCA = false;
		this.canHarvestFlowerCA = false;
		this.hasHarvestedCA = false;
		this.hasHarvestedCB = false;

		this.hasPlantedCA = false;
		this.hasPlantedCB = false;
		this.hasPlantedHerbC = false;
		this.hasPlantedFlowerC = false;

		this.potatoCA = false;
		this.potatoCB = false;
		this.melonCA = false;
		this.melonCB = false;
		this.sweetCA = false;
		this.sweetCB = false;
		this.guamCA = false;
		this.snapCA = false;
		this.goldC = false;
		this.lilyC = false;

		this.allotmentA = 0;
		this.allotmentB = 0;
		this.allotmentH = 0;
		this.allotmentF = 0;

		this.canHarvestAA = false;
		this.hasHarvestedAA = false;
		this.hasPlantedAA = false;
		this.canHarvestAB = false;
		this.hasHarvestedAB = false;
		this.hasPlantedAB = false;

		this.canHarvestHerbAA = false;
		this.canHarvestFlowerAF = false;

		this.mustRakeAA = false;
		this.mustRakeAB = false;
		this.mustRakeAH = false;
		this.mustRakeAF = false;
	}

	public void addNest(Player player) {
		int[] birdNests = { 5070, 5071, 5072, 5073, 5074 };
		int i = Utils.getRandom(4);
		double chance = Math.random() * 100.0D;
		if ((chance <= 0.1D) && (player.getInventory().getFreeSlots() > 0)) {
			World.addGroundItem(new Item(birdNests[i]), new WorldTile(player), player, true, 180L);
			player.getPackets().sendGameMessage("A bird nest fell out of the tree!");
		}
	}

	public void seedNest() {
		int[] seedNest = { 5313, 5316, 5315, 5314 };
		int i = Utils.getRandom(3);
		getInventory().addItem(seedNest[i], 1);
		getInventory().deleteItem(5073, 1);
		out("You recieve a random tree seed from the birds nest.");
	}

	public void ringNest() {
		int[] ringNest = { 1635, 1637, 1639, 1641, 1643 };
		int i = Utils.getRandom(4);
		getInventory().addItem(ringNest[i], 1);
		getInventory().deleteItem(5074, 1);
		out("You recieve a random ring from the birds nest.");
	}

	
	private transient ConcurrentLinkedQueue<LogicPacket> logicPackets;
	private transient LocalPlayerUpdate localPlayerUpdate;
	private transient LocalNPCUpdate localNPCUpdate;
	private int temporaryMovementType;
	private boolean updateMovementType;
	private transient boolean started;
	private transient boolean running;
	public boolean rfd1;
	public boolean rfd2;
	public boolean rfd3;
	public boolean rfd4;
	public boolean rfd5 = false;
	private transient long packetsDecoderPing;
	private transient boolean resting;
	private transient boolean listening;
	private transient boolean canPvp;
	private transient boolean cantTrade;
	private transient long lockDelay;
	private transient long foodDelay;
	private transient long potDelay;
	private transient long boneDelay;
	private transient long ashDelay;
	private transient Runnable closeInterfacesEvent;
	private transient long lastPublicMessage;
	private transient long polDelay;
	private transient List<Integer> switchItemCache;
	private transient boolean disableEquip;
	private transient MachineInformation machineInformation;
	private transient boolean castedVeng;
	private transient boolean invulnerable;
	private transient double hpBoostMultiplier;
	private transient boolean largeSceneView;
	private static final int lastlogged = 0;
	public boolean viewingrsmvactionlocs = false;
	public boolean viewingmonsterteles = false;
	public boolean viewingtrainingteles = false;
	public boolean viewingskillingteles = false;
	public boolean viewingminigameteles = false;
	public boolean viewingagilityteles = false;
	public boolean viewingwoodcuttingteles = false;
	public boolean viewingfamouslyreteles = false;
	//Under Construction
	public boolean viewinglyreteles = false;
	//New RSMV Lyre Categories
	public boolean viewingfamouscategories = false;
	public boolean viewingbrightcategories = false;
	public boolean viewingmoodycategories = false;
	public boolean viewingfairyringcategories = false;
	public boolean viewingextracategories = false;
	//New RSMV Lyre Famous 
	public boolean viewingfamousIteles = false;
	public boolean viewingfamousIIteles = false;
	public boolean viewingfamousIIIteles = false;
	public boolean viewingfamousIIIIteles = false;
	//New RSMV Lyre Bright
	public boolean viewingbrightIteles = false;
	public boolean viewingbrightIIteles = false;
	public boolean viewingbrightIIIteles = false;
	public boolean viewingbrightIIIIteles = false;
	//New RSMV Lyre Moody
	public boolean viewingmoodyIteles = false;
	public boolean viewingmoodyIIteles = false;
	public boolean viewingmoodyIIIteles = false;
	public boolean viewingmoodyIIIIteles = false;
	//New RSMV Lyre Fairy
	public boolean viewingfairyIteles = false;
	public boolean viewingfairyIIteles = false;
	public boolean viewingfairyIIIteles = false;
	public boolean viewingfairyIIIIteles = false;
	//New RSMV Lyre Extra
	public boolean viewingextraIteles = false;
	public boolean viewingextraIIteles = false;
	public boolean viewingextraIIIteles = false;
	public boolean viewingextraIIIIteles = false;
	
	public int firecount;
	public int boughtdung;
	public int cookcount;
	public int logcount;
	public int neededpoints;
	public int skillpoints;
	private String password;
	private int outfit;
	private int rights;
	private String displayName;
	private String lastIP;
	private long creationDate;
	private Appearence appearence;
	private Inventory inventory;
	private Equipment equipment;
	private Skills skills;
	private CombatDefinitions combatDefinitions;
	private Prayer prayer;
	private Bank bank;
	private ControlerManager controlerManager;
	private MusicsManager musicsManager;
	private EmotesManager emotesManager;
	private FriendsIgnores friendsIgnores;
	private FairyRing fairyRing;
	private DominionTower dominionTower;
	private Familiar familiar;
	private AuraManager auraManager;
	private QuestManager questManager;
	private PetManager petManager;
	private byte runEnergy;
	private boolean allowChatEffects;
	private boolean mouseButtons;
	private int privateChatSetup;
	private int friendChatSetup;
	private int skullDelay;
	private int skullId;
	private boolean forceNextMapLoadRefresh;
	private long poisonImmune;
	private long fireImmune;
	private boolean killedQueenBlackDragon;
	private int runeSpanPoints;
	public int boneType;
	public boolean bonesGrinded;
	public int unclaimedEctoTokens;
	private Toolbelt toolbelt;
	private int xpRate;
	private int lastBonfire;
	private int[] pouches;
	private long displayTime;
	private long muted;
	private long jailed;
	private long banned;
	private boolean permJailed;
	private boolean permBanned;
	private boolean permMuted;
	private boolean filterGame;
	private boolean xpLocked;
	private boolean yellOff;
	private int publicStatus;
	private int clanStatus;
	private int tradeStatus;
	private int assistStatus;
	private boolean member;
	private long memberTill;
	private String recovQuestion;
	private String recovAnswer;
	private String lastMsg;
	private boolean profanityFilter;

	private ArrayList<String> passwordList = new ArrayList<String>();
	private ArrayList<String> ipList = new ArrayList<String>();
	private ArrayList<Notes.Note> pnotes;
	private int killCount;
	private int deathCount;
	private ChargesManager charges;
	private boolean[] killedBarrowBrothers;
	private int hiddenBrother;
	private int barrowsKillCount;
	private int pestPoints;
	private transient ClansManager clanManager;
	private transient ClansManager guestClanManager;
	private int clanChatSetup;
	private String clanName;
	private int guestChatSetup;
	private boolean connectedClanChannel;
	private int[] maxedCapeCustomized;
	private int[] completionistCapeCustomized;
	private boolean completedFightCaves;
	private boolean completedFightKiln;
	private boolean wonFightPits;
	private boolean talkedWithMarv;
	private int crucibleHighScore;
	private int overloadDelay;
	private int prayerRenewalDelay;
	private String currentFriendChatOwner;
	private int summoningLeftClickOption;
	private List<String> ownedObjectsManagerKeys;
	private boolean khalphiteLairEntranceSetted;
	private boolean khalphiteLairSetted;
	private int votes;
	private static boolean LockON;
	private static boolean LockOFF;
	private static boolean oldItemsLook;

	public ArrayList<Notes.Note> getCurNotes() {
		return this.pnotes;
	}

	private String yellColor = "ff0000";
	private long voted;
	public int cluenoreward;
	private long stopDelay;

	public Player(String password) {
		super(Settings.START_PLAYER_LOCATION);
		setHitpoints(100);
		this.password = password;
		this.appearence = new Appearence();
		this.inventory = new Inventory();

		this.equipment = new Equipment();
		this.skills = new Skills();

		this.combatDefinitions = new CombatDefinitions();
		this.prayer = new Prayer();
		this.bank = new Bank();
		this.controlerManager = new ControlerManager();
		this.musicsManager = new MusicsManager();
		this.emotesManager = new EmotesManager();
		this.friendsIgnores = new FriendsIgnores();
		this.dominionTower = new DominionTower();
		this.charges = new ChargesManager();
		this.auraManager = new AuraManager();
		this.questManager = new QuestManager();
		this.petManager = new PetManager();
		this.moneyPouch = new MoneyPouch();
		this.toolbelt = new Toolbelt();
		this.runEnergy = 100;
		this.allowChatEffects = true;
		this.mouseButtons = true;
		this.pouches = new int[4];
		resetBarrows();
		SkillCapeCustomizer.resetSkillCapes(this);
		this.ownedObjectsManagerKeys = new LinkedList<String>();
		this.passwordList = new ArrayList<String>();
		this.ipList = new ArrayList<String>();
		this.creationDate = Utils.currentTimeMillis();
	}

	public void init(Session session, String username, int displayMode, int screenWidth, int screenHeight, MachineInformation machineInformation, IsaacKeyPair isaacKeyPair) {
		if (this.dominionTower == null) {
			this.dominionTower = new DominionTower();
		}
		if (this.auraManager == null) {
			this.auraManager = new AuraManager();
		}
		if (this.fairyRing == null) {
			this.fairyRing = new FairyRing(this);
		}
		if (this.questManager == null) {
			this.questManager = new QuestManager();
		}
		if (this.dwarfCannon == null) {
			this.dwarfCannon = new DwarfCannon(this);
		}
		if (this.WarriorsGuild == null) {
			this.WarriorsGuild = new WarriorsGuild();
		}
		if (this.petManager == null) {
			this.petManager = new PetManager();
		}
		if(toolbelt == null)
			toolbelt = new Toolbelt();
		if (roomReference == null)
			roomReference = new RoomReference();
		if (conObjectsToBeLoaded == null) {
			conObjectsToBeLoaded = new ArrayList<WorldObject>();
		}
		if(moneyPouch == null)
			moneyPouch = new MoneyPouch();
		if (house == null)
			house = new House();
		// if (rooms == null) {
		rooms = new ArrayList<RoomReference>();
		rooms.add(new RoomReference(Room.GARDEN, 4, 4, 0, 0));
		rooms.add(new RoomReference(Room.PARLOUR, 5, 5, 0, 0));
		rooms.add(new RoomReference(Room.KITCHEN, 3, 5, 0, 3));
		rooms.add(new RoomReference(Room.PORTALROOM, 3, 3, 0, 2));
		rooms.add(new RoomReference(Room.SKILLHALL1, 5, 4, 0, 0));
		rooms.add(new RoomReference(Room.QUESTHALL1, 5, 3, 0, 0));
		rooms.add(new RoomReference(Room.GAMESROOM, 6, 3, 0, 1));
		rooms.add(new RoomReference(Room.BOXINGROOM, 6, 2, 0, 1));
		rooms.add(new RoomReference(Room.BEDROOM, 6, 4, 0, 0));
		rooms.add(new RoomReference(Room.DININGROOM, 4, 5, 0, 0));
		rooms.add(new RoomReference(Room.WORKSHOP, 4, 3, 0, 0));
		rooms.add(new RoomReference(Room.CHAPEL, 2, 4, 0, 3));
		rooms.add(new RoomReference(Room.STUDY, 4, 3, 0, 3));
		rooms.add(new RoomReference(Room.COSTUMEROOM, 4, 2, 0, 2));
		rooms.add(new RoomReference(Room.THRONEROOM, 5, 2, 0, 2));
		rooms.add(new RoomReference(Room.FANCYGARDEN, 3, 4, 0, 3));
		rooms.add(new RoomReference(Room.MENAGERIE, 4, 4, 0, 0));

		this.session = session;
		this.username = username;
		this.displayMode = displayMode;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.machineInformation = machineInformation;
		this.outfit = this.outfit;

		this.isaacKeyPair = isaacKeyPair;
		this.varsManager = new VarsManager(this);

		this.notes = new Notes(this);
		this.interfaceManager = new InterfaceManager(this);
		this.savedOutfits = new SavedOutfits(this);
		this.savedLocations = new SavedLocations(this);
		this.savedAnimations = new SavedAnimations(this);
		this.savedRenderAnimations = new SavedRenderAnimations(this);
		this.dialogueManager = new DialogueManager(this);
		setAlchDelay(0L);
		this.hintIconsManager = new HintIconsManager(this);
		this.ectophial = new Ectophial(this);
		this.whirlpool = new Whirlpool(this);
		this.matrix = new BrimhavenMatrix(this);
		this.teletab = new Teletab(this);
		this.minecart = new Minecart(this);
		this.spiritbag = new Spiritbag(this);
		this.priceCheckManager = new PriceCheckManager(this);
		this.localPlayerUpdate = new LocalPlayerUpdate(this);
		this.localNPCUpdate = new LocalNPCUpdate(this);
		this.actionManager = new ActionManager(this);
		this.cutscenesManager = new CutscenesManager(this);
		this.trade = new Trade(this);

		this.appearence.setPlayer(this);
		this.moneyPouch.setPlayer(this);
		this.inventory.setPlayer(this);
		this.equipment.setPlayer(this);
		this.skills.setPlayer(this);
		this.combatDefinitions.setPlayer(this);
		this.prayer.setPlayer(this);
		this.bank.setPlayer(this);
		this.controlerManager.setPlayer(this);
		this.musicsManager.setPlayer(this);
		this.emotesManager.setPlayer(this);
		this.friendsIgnores.setPlayer(this);
		this.dominionTower.setPlayer(this);
		this.toolbelt.setPlayer(this);
		this.auraManager.setPlayer(this);
		this.charges.setPlayer(this);
		this.questManager.setPlayer(this);
		this.petManager.setPlayer(this);
		this.house.setPlayer(this);
		this.temporaryMovementType = -1;
		this.logicPackets = new ConcurrentLinkedQueue<LogicPacket>();
		this.switchItemCache = Collections.synchronizedList(new ArrayList<Integer>());
		initEntity();
		this.packetsDecoderPing = Utils.currentTimeMillis();
		World.addPlayer(this);
		this.spokeToMonk = true;
		World.updateEntityRegion(this);
		if (Settings.DEBUG) {
			Logger.log(this, "Initiated player: " + username + ", pass: " + this.password);
		}
		if (this.passwordList == null) {
			this.passwordList = new ArrayList<String>();
		}
		if (this.ipList == null) {
			this.ipList = new ArrayList<String>();
		}
		updateIPnPass();
	}

	public void setClanChatSetup(int clanChatSetup) {
		this.clanChatSetup = clanChatSetup;
	}

	public void setGuestChatSetup(int guestChatSetup) {
		this.guestChatSetup = guestChatSetup;
	}

	public void kickPlayerFromClanChannel(String name) {
		if (this.clanManager == null) {
			return;
		}
		this.clanManager.kickPlayerFromChat(this, name);
	}

	public void sendClanChannelMessage(ChatMessage message) {
		if (this.clanManager == null) {
			return;
		}
		this.clanManager.sendMessage(this, message);
	}

	public void sendGuestClanChannelMessage(ChatMessage message) {
		if (this.guestClanManager == null) {
			return;
		}
		this.guestClanManager.sendMessage(this, message);
	}

	public void sendClanChannelQuickMessage(QuickChatMessage message) {
		if (this.clanManager == null) {
			return;
		}
		this.clanManager.sendQuickMessage(this, message);
	}

	public void sendGuestClanChannelQuickMessage(QuickChatMessage message) {
		if (this.guestClanManager == null) {
			return;
		}
		this.guestClanManager.sendQuickMessage(this, message);
	}

	public int getClanStatus() {
		return this.clanStatus;
	}

	public void setClanStatus(int clanStatus) {
		this.clanStatus = clanStatus;
	}

	public ClansManager getClanManager() {
		return this.clanManager;
	}

	public void setClanManager(ClansManager clanManager) {
		this.clanManager = clanManager;
	}

	public ClansManager getGuestClanManager() {
		return this.guestClanManager;
	}

	public void setGuestClanManager(ClansManager guestClanManager) {
		this.guestClanManager = guestClanManager;
	}

	public String getClanName() {
		return this.clanName;
	}

	public void setClanName(String clanName) {
		this.clanName = clanName;
	}

	public boolean isConnectedClanChannel() {
		return this.connectedClanChannel;
	}

	public void setConnectedClanChannel(boolean connectedClanChannel) {
		this.connectedClanChannel = connectedClanChannel;
	}

	public void setWildernessSkull() {
		this.skullDelay = 3000;
		this.skullId = 0;
		this.appearence.generateAppearenceData();
	}

	public void setFightPitsSkull() {
		this.skullDelay = 2147483647;
		this.skullId = 1;
		this.appearence.generateAppearenceData();
	}

	public void setSkullInfiniteDelay(int skullId) {
		this.skullDelay = 2147483647;
		this.skullId = skullId;
		this.appearence.generateAppearenceData();
	}

	public void removeSkull() {
		this.skullDelay = -1;
		this.appearence.generateAppearenceData();
	}

	public boolean hasSkull() {
		return this.skullDelay > 0;
	}

	public int setSkullDelay(int delay) {
		return this.skullDelay = delay;
	}

	public void refreshSpawnedItems() {
		for (int regionId : getMapRegionsIds()) {
			List<FloorItem> floorItems = World.getRegion(regionId).getGroundItems();
			if (floorItems == null)
				continue;
			for (FloorItem item : floorItems) {
				if (item.isInvisible() && (item.hasOwner() && !getUsername().equals(item.getOwner())) || item.getTile().getPlane() != getPlane())
					continue;
				getPackets().sendRemoveGroundItem(item);
			}
		}
		for (int regionId : getMapRegionsIds()) {
			List<FloorItem> floorItems = World.getRegion(regionId).getGroundItems();
			if (floorItems == null)
				continue;
			for (FloorItem item : floorItems) {
				if ((item.isInvisible()) && (item.hasOwner() && !getUsername().equals(item.getOwner())) || item.getTile().getPlane() != getPlane())
					continue;
				getPackets().sendGroundItem(item);
			}
		}
	}

	public void refreshSpawnedObjects() {
		for (int regionId : getMapRegionsIds()) {
			List<WorldObject> removedObjects = World.getRegion(regionId).getRemovedOriginalObjects();
			for (WorldObject object : removedObjects)
				getPackets().sendDestroyObject(object);
			List<WorldObject> spawnedObjects = World.getRegion(regionId).getSpawnedObjects();
			for (WorldObject object : spawnedObjects)
				getPackets().sendSpawnedObject(object);
		}
	}
	public void refreshObjects() {
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 1) {
					checkObjects();
				} else if (loop == 100) {
					checkObjects();
				} else if (loop == 150) {
					checkObjects();
				} else if (loop == 200) {
					checkObjects();
				} else if (loop == 500) {
					checkObjects();
				}
				loop++;
			}
		}, 0, 1);
	}

	public void checkObjects() {
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 1) {
					closeInterfaces();
					if (table1 == 1) {
						World.spawnObject(new WorldObject(13293, 10, 0, tableX1, tableY1, 0), true);
					} else if (table1 == 2) {
						World.spawnObject(new WorldObject(13294, 10, 0, tableX1, tableY1, 0), true);
					} else if (table1 == 3) {
						World.spawnObject(new WorldObject(13295, 10, 0, tableX1, tableY1, 0), true);
					} else if (table1 == 4) {
						World.spawnObject(new WorldObject(13296, 10, 0, tableX1, tableY1, 0), true);
					} else if (table1 == 5) {
						World.spawnObject(new WorldObject(13297, 10, 0, tableX1, tableY1, 0), true);
					} else if (table1 == 6) {
						World.spawnObject(new WorldObject(13298, 10, 0, tableX1, tableY1, 0), true);
					} else if (table1 == 7) {
						World.spawnObject(new WorldObject(13299, 10, 0, tableX1, tableY1, 0), true);
					}
					if (small1plant1 == 1) {
						World.spawnObject(new WorldObject(13431, 10, 0, small1plantX1, small1plantY1, 0), true);
					} else if (small1plant1 == 2) {
						World.spawnObject(new WorldObject(13432, 10, 0, small1plantX1, small1plantY1, 0), true);
					} else if (small1plant1 == 3) {
						World.spawnObject(new WorldObject(13433, 10, 0, small1plantX1, small1plantY1, 0), true);
					}
					if (small2plant1 == 1) {
						World.spawnObject(new WorldObject(13434, 10, 0, small2plantX1, small2plantY1, 0), true);
					} else if (small2plant1 == 2) {
						World.spawnObject(new WorldObject(13435, 10, 0, small2plantX1, small2plantY1, 0), true);
					} else if (small2plant1 == 3) {
						World.spawnObject(new WorldObject(13436, 10, 0, small2plantX1, small2plantY1, 0), true);
					}
					if (big1plant1 == 1) {
						World.spawnObject(new WorldObject(13425, 10, 0, big1plantX1, big1plantY1, 0), true);
					} else if (big1plant1 == 2) {
						World.spawnObject(new WorldObject(13426, 10, 0, big1plantX1, big1plantY1, 0), true);
					} else if (big1plant1 == 3) {
						World.spawnObject(new WorldObject(13427, 10, 0, big1plantX1, big1plantY1, 0), true);
					}
					if (big2plant1 == 1) {
						World.spawnObject(new WorldObject(13428, 10, 0, big2plantX1, big2plantY1, 0), true);
					} else if (big2plant1 == 2) {
						World.spawnObject(new WorldObject(13429, 10, 0, big2plantX1, big2plantY1, 0), true);
					} else if (big2plant1 == 3) {
						World.spawnObject(new WorldObject(13430, 10, 0, big2plantX1, big2plantY1, 0), true);
					}
					if (chair1 == 1) {
						World.spawnObject(new WorldObject(13581, 10, 0, chairX1, chairY1, 0), true);
					} else if (chair1 == 2) {
						World.spawnObject(new WorldObject(13582, 10, 0, chairX1, chairY1, 0), true);
					} else if (chair1 == 3) {
						World.spawnObject(new WorldObject(13583, 10, 0, chairX1, chairY1, 0), true);
					} else if (chair1 == 4) {
						World.spawnObject(new WorldObject(13584, 10, 0, chairX1, chairY1, 0), true);
					} else if (chair1 == 5) {
						World.spawnObject(new WorldObject(13585, 10, 0, chairX1, chairY1, 0), true);
					} else if (chair1 == 6) {
						World.spawnObject(new WorldObject(13586, 10, 0, chairX1, chairY1, 0), true);
					} else if (chair1 == 7) {
						World.spawnObject(new WorldObject(13587, 10, 0, chairX1, chairY1, 0), true);
					}
					if (chair2 == 1) {
						World.spawnObject(new WorldObject(13581, 10, 0, chairX2, chairY2, 0), true);
					} else if (chair2 == 2) {
						World.spawnObject(new WorldObject(13582, 10, 0, chairX2, chairY2, 0), true);
					} else if (chair2 == 3) {
						World.spawnObject(new WorldObject(13583, 10, 0, chairX2, chairY2, 0), true);
					} else if (chair2 == 4) {
						World.spawnObject(new WorldObject(13584, 10, 0, chairX2, chairY2, 0), true);
					} else if (chair2 == 5) {
						World.spawnObject(new WorldObject(13585, 10, 0, chairX2, chairY2, 0), true);
					} else if (chair2 == 6) {
						World.spawnObject(new WorldObject(13586, 10, 0, chairX2, chairY2, 0), true);
					} else if (chair2 == 7) {
						World.spawnObject(new WorldObject(13587, 10, 0, chairX2, chairY2, 0), true);
					}
					if (chair3 == 1) {
						World.spawnObject(new WorldObject(13581, 10, 0, chairX3, chairY3, 0), true);
					} else if (chair3 == 2) {
						World.spawnObject(new WorldObject(13582, 10, 0, chairX3, chairY3, 0), true);
					} else if (chair3 == 3) {
						World.spawnObject(new WorldObject(13583, 10, 0, chairX3, chairY3, 0), true);
					} else if (chair3 == 4) {
						World.spawnObject(new WorldObject(13584, 10, 0, chairX3, chairY3, 0), true);
					} else if (chair3 == 5) {
						World.spawnObject(new WorldObject(13585, 10, 0, chairX3, chairY3, 0), true);
					} else if (chair3 == 6) {
						World.spawnObject(new WorldObject(13586, 10, 0, chairX3, chairY3, 0), true);
					} else if (chair3 == 7) {
						World.spawnObject(new WorldObject(13587, 10, 0, chairX3, chairY3, 0), true);
					}
					if (fireplace1 == 1) {
						World.spawnObject(new WorldObject(13609, 10, 1, fireplaceX1, fireplaceY1, 0), true);
					} else if (fireplace1 == 2) {
						World.spawnObject(new WorldObject(13611, 10, 1, fireplaceX1, fireplaceY1, 0), true);
					} else if (fireplace1 == 3) {
						World.spawnObject(new WorldObject(13613, 10, 1, fireplaceX1, fireplaceY1, 0), true);
					}
					if (fireplace2 == 1) {
						World.spawnObject(new WorldObject(13609, 10, 1, fireplaceX2, fireplaceY2, 0), true);
					} else if (fireplace2 == 2) {
						World.spawnObject(new WorldObject(13611, 10, 1, fireplaceX2, fireplaceY2, 0), true);
					} else if (fireplace2 == 3) {
						World.spawnObject(new WorldObject(13613, 10, 1, fireplaceX2, fireplaceY2, 0), true);
					}
					if (fireplace3 == 1) {
						World.spawnObject(new WorldObject(13609, 10, 2, fireplaceX3, fireplaceY3, 0), true);
					} else if (fireplace3 == 2) {
						World.spawnObject(new WorldObject(13611, 10, 2, fireplaceX3, fireplaceY3, 0), true);
					} else if (fireplace3 == 3) {
						World.spawnObject(new WorldObject(13613, 10, 2, fireplaceX3, fireplaceY3, 0), true);
					}
					if (bookcase1 == 1) {
						World.spawnObject(new WorldObject(13597, 10, 2, bookcaseX1, bookcaseY1, 0), true);
					} else if (bookcase1 == 2) {
						World.spawnObject(new WorldObject(13598, 10, 2, bookcaseX1, bookcaseY1, 0), true);
					} else if (bookcase1 == 3) {
						World.spawnObject(new WorldObject(13599, 10, 2, bookcaseX1, bookcaseY1, 0), true);
					}
					if (bookcase2 == 1) {
						World.spawnObject(new WorldObject(13597, 10, 0, bookcaseX2, bookcaseY2, 0), true);
					} else if (bookcase2 == 2) {
						World.spawnObject(new WorldObject(13598, 10, 0, bookcaseX2, bookcaseY2, 0), true);
					} else if (bookcase2 == 3) {
						World.spawnObject(new WorldObject(13599, 10, 0, bookcaseX2, bookcaseY2, 0), true);
					}
					if (bookcase3 == 1) {
						World.spawnObject(new WorldObject(13597, 10, 0, bookcaseX3, bookcaseY3, 0), true);
					} else if (bookcase3 == 2) {
						World.spawnObject(new WorldObject(13598, 10, 0, bookcaseX3, bookcaseY3, 0), true);
					} else if (bookcase3 == 3) {
						World.spawnObject(new WorldObject(13599, 10, 0, bookcaseX3, bookcaseY3, 0), true);
					}
					if (bookcase4 == 1) {
						World.spawnObject(new WorldObject(13597, 10, 0, bookcaseX4, bookcaseY4, 0), true);
					} else if (bookcase4 == 2) {
						World.spawnObject(new WorldObject(13598, 10, 0, bookcaseX4, bookcaseY4, 0), true);
					} else if (bookcase4 == 3) {
						World.spawnObject(new WorldObject(13599, 10, 0, bookcaseX4, bookcaseY4, 0), true);
					}
					if (bookcase5 == 1) {
						World.spawnObject(new WorldObject(13597, 10, 0, bookcaseX5, bookcaseY5, 0), true);
					} else if (bookcase5 == 2) {
						World.spawnObject(new WorldObject(13598, 10, 0, bookcaseX5, bookcaseY5, 0), true);
					} else if (bookcase5 == 3) {
						World.spawnObject(new WorldObject(13599, 10, 0, bookcaseX5, bookcaseY5, 0), true);
					}
					if (bench1 == 1) {
						World.spawnObject(new WorldObject(13300, 10, 2, benchX1, benchY1, 0), true);
					} else if (bench1 == 2) {
						World.spawnObject(new WorldObject(13301, 10, 2, benchX1, benchY1, 0), true);
					} else if (bench1 == 3) {
						World.spawnObject(new WorldObject(13302, 10, 2, benchX1, benchY1, 0), true);
					} else if (bench1 == 4) {
						World.spawnObject(new WorldObject(13303, 10, 2, benchX1, benchY1, 0), true);
					} else if (bench1 == 5) {
						World.spawnObject(new WorldObject(13304, 10, 2, benchX1, benchY1, 0), true);
					} else if (bench1 == 6) {
						World.spawnObject(new WorldObject(13305, 10, 2, benchX1, benchY1, 0), true);
					} else if (bench1 == 7) {
						World.spawnObject(new WorldObject(13306, 10, 2, benchX1, benchY1, 0), true);
					}
					if (bench2 == 1) {
						World.spawnObject(new WorldObject(13300, 10, 2, benchX2, benchY2, 0), true);
					} else if (bench2 == 2) {
						World.spawnObject(new WorldObject(13301, 10, 2, benchX2, benchY2, 0), true);
					} else if (bench2 == 3) {
						World.spawnObject(new WorldObject(13302, 10, 2, benchX2, benchY2, 0), true);
					} else if (bench2 == 4) {
						World.spawnObject(new WorldObject(13303, 10, 2, benchX2, benchY2, 0), true);
					} else if (bench2 == 5) {
						World.spawnObject(new WorldObject(13304, 10, 2, benchX2, benchY2, 0), true);
					} else if (bench2 == 6) {
						World.spawnObject(new WorldObject(13305, 10, 2, benchX2, benchY2, 0), true);
					} else if (bench2 == 7) {
						World.spawnObject(new WorldObject(13306, 10, 2, benchX2, benchY2, 0), true);

					}
					if (bench3 == 1) {
						World.spawnObject(new WorldObject(13300, 10, 2, benchX3, benchY3, 0), true);
					} else if (bench3 == 2) {
						World.spawnObject(new WorldObject(13301, 10, 2, benchX3, benchY3, 0), true);
					} else if (bench3 == 3) {
						World.spawnObject(new WorldObject(13302, 10, 2, benchX3, benchY3, 0), true);
					} else if (bench3 == 4) {
						World.spawnObject(new WorldObject(13303, 10, 2, benchX3, benchY3, 0), true);
					} else if (bench3 == 5) {
						World.spawnObject(new WorldObject(13304, 10, 2, benchX3, benchY3, 0), true);
					} else if (bench3 == 6) {
						World.spawnObject(new WorldObject(13305, 10, 2, benchX3, benchY3, 0), true);
					} else if (bench3 == 7) {
						World.spawnObject(new WorldObject(13306, 10, 2, benchX3, benchY3, 0), true);
					}
					if (bench4 == 1) {
						World.spawnObject(new WorldObject(13300, 10, 2, benchX4, benchY4, 0), true);
					} else if (bench4 == 2) {
						World.spawnObject(new WorldObject(13301, 10, 2, benchX4, benchY4, 0), true);
					} else if (bench4 == 3) {
						World.spawnObject(new WorldObject(13302, 10, 2, benchX4, benchY4, 0), true);
					} else if (bench4 == 4) {
						World.spawnObject(new WorldObject(13303, 10, 2, benchX4, benchY4, 0), true);
					} else if (bench4 == 5) {
						World.spawnObject(new WorldObject(13304, 10, 2, benchX4, benchY4, 0), true);
					} else if (bench4 == 6) {
						World.spawnObject(new WorldObject(13305, 10, 2, benchX4, benchY4, 0), true);
					} else if (bench4 == 7) {
						World.spawnObject(new WorldObject(13306, 10, 2, benchX4, benchY4, 0), true);
					}
					if (bench5 == 1) {
						World.spawnObject(new WorldObject(13300, 10, 0, benchX5, benchY5, 0), true);
					} else if (bench5 == 2) {
						World.spawnObject(new WorldObject(13301, 10, 0, benchX5, benchY5, 0), true);
					} else if (bench5 == 3) {
						World.spawnObject(new WorldObject(13302, 10, 0, benchX5, benchY5, 0), true);
					} else if (bench5 == 4) {
						World.spawnObject(new WorldObject(13303, 10, 0, benchX5, benchY5, 0), true);
					} else if (bench5 == 5) {
						World.spawnObject(new WorldObject(13304, 10, 0, benchX5, benchY5, 0), true);
					} else if (bench5 == 6) {
						World.spawnObject(new WorldObject(13305, 10, 0, benchX5, benchY5, 0), true);
					} else if (bench5 == 7) {
						World.spawnObject(new WorldObject(13306, 10, 0, benchX5, benchY5, 0), true);
					}
					if (bench6 == 1) {
						World.spawnObject(new WorldObject(13300, 10, 0, benchX6, benchY6, 0), true);
					} else if (bench6 == 2) {
						World.spawnObject(new WorldObject(13301, 10, 0, benchX6, benchY6, 0), true);
					} else if (bench6 == 3) {
						World.spawnObject(new WorldObject(13302, 10, 0, benchX6, benchY6, 0), true);
					} else if (bench6 == 4) {
						World.spawnObject(new WorldObject(13303, 10, 0, benchX6, benchY6, 0), true);
					} else if (bench6 == 5) {
						World.spawnObject(new WorldObject(13304, 10, 0, benchX6, benchY6, 0), true);
					} else if (bench6 == 6) {
						World.spawnObject(new WorldObject(13305, 10, 0, benchX6, benchY6, 0), true);
					} else if (bench6 == 7) {
						World.spawnObject(new WorldObject(13306, 10, 0, benchX6, benchY6, 0), true);
					}
					if (bench7 == 1) {
						World.spawnObject(new WorldObject(13300, 10, 0, benchX7, benchY7, 0), true);
					} else if (bench7 == 2) {
						World.spawnObject(new WorldObject(13301, 10, 0, benchX7, benchY7, 0), true);
					} else if (bench7 == 3) {
						World.spawnObject(new WorldObject(13302, 10, 0, benchX7, benchY7, 0), true);
					} else if (bench7 == 4) {
						World.spawnObject(new WorldObject(13303, 10, 0, benchX7, benchY7, 0), true);
					} else if (bench7 == 5) {
						World.spawnObject(new WorldObject(13304, 10, 0, benchX7, benchY7, 0), true);
					} else if (bench7 == 6) {
						World.spawnObject(new WorldObject(13305, 10, 0, benchX7, benchY7, 0), true);
					} else if (bench7 == 7) {
						World.spawnObject(new WorldObject(13306, 10, 0, benchX7, benchY7, 0), true);
					}
					if (bench8 == 1) {
						World.spawnObject(new WorldObject(13300, 10, 0, benchX8, benchY8, 0), true);
					} else if (bench8 == 2) {
						World.spawnObject(new WorldObject(13301, 10, 0, benchX8, benchY8, 0), true);
					} else if (bench8 == 3) {
						World.spawnObject(new WorldObject(13302, 10, 0, benchX8, benchY8, 0), true);
					} else if (bench8 == 4) {
						World.spawnObject(new WorldObject(13303, 10, 0, benchX8, benchY8, 0), true);
					} else if (bench8 == 5) {
						World.spawnObject(new WorldObject(13304, 10, 0, benchX8, benchY8, 0), true);
					} else if (bench8 == 6) {
						World.spawnObject(new WorldObject(13305, 10, 0, benchX8, benchY8, 0), true);
					} else if (bench8 == 7) {
						World.spawnObject(new WorldObject(13306, 10, 0, benchX8, benchY8, 0), true);
					}
					stop();

				}
				loop++;
			}
		}, 0, 1);
	}// TODO

	public void start() {
		loadMapRegions();
		this.started = true;
		run();
		if (isDead()) {
			sendDeath(null);
		}
	}

	public void stopAll() {
		stopAll(true);
	}

	public void stopAll(boolean stopWalk) {
		stopAll(stopWalk, true);
	}

	public void stopAll(boolean stopWalk, boolean stopInterface) {
		stopAll(stopWalk, stopInterface, true);
	}

	public void stopAll(boolean stopWalk, boolean stopInterfaces, boolean stopActions) {
		this.coordsEvent = null;
		if (stopInterfaces) {
			closeInterfaces();
		}
		if (stopWalk) {
			resetWalkSteps();
		}
		if (stopActions) {
			this.actionManager.forceStop();
		}
		this.combatDefinitions.resetSpells(false);
	}

	public void reset(boolean attributes) {
		super.reset(attributes);
		refreshHitPoints();
		this.hintIconsManager.removeAll();
		this.skills.restoreSkills();
		this.combatDefinitions.resetSpecialAttack();
		this.prayer.reset();
		this.combatDefinitions.resetSpells(true);
		this.resting = false;
		this.listening = false;
		this.skullDelay = 0;
		this.foodDelay = 0L;
		this.potDelay = 0L;
		this.poisonImmune = 0L;
		this.fireImmune = 0L;
		this.castedVeng = false;
		setRunEnergy(100);
		this.appearence.generateAppearenceData();
	}

	public void reset() {
		reset(true);
	}

	public void closeInterfaces() {
		if (this.interfaceManager.containsScreenInter()) {
			this.interfaceManager.closeScreenInterface();
		}
		if (this.interfaceManager.containsInventoryInter()) {
			this.interfaceManager.closeInventoryInterface();
		}
		this.dialogueManager.finishDialogue();
		if (this.closeInterfacesEvent != null) {
			this.closeInterfacesEvent.run();
			this.closeInterfacesEvent = null;
		}
	}

	public void setClientHasntLoadedMapRegion() {
		this.clientLoadedMapRegion = false;
	}

	public void loadMapRegions() {
		boolean wasAtDynamicRegion = isAtDynamicRegion();
		super.loadMapRegions();
		this.clientLoadedMapRegion = false;
		if (isAtDynamicRegion()) {
			getPackets().sendDynamicMapRegion(!this.started);
			if (!wasAtDynamicRegion) {
				this.localNPCUpdate.reset();
			}
		} else {
			getPackets().sendMapRegion(!this.started);
			if (wasAtDynamicRegion) {
				this.localNPCUpdate.reset();
			}
		}
		this.forceNextMapLoadRefresh = false;
	}

	public void processLogicPackets() {
		LogicPacket packet;
		while ((packet = (LogicPacket) this.logicPackets.poll()) != null)
			WorldPacketsDecoder.decodeLogicPacket(this, packet);
	}

	public void processEntity() {
		processLogicPackets();
		this.cutscenesManager.process();
		if ((this.coordsEvent != null) && (this.coordsEvent.processEvent(this))) {
			this.coordsEvent = null;
		}
		super.processEntity();
		if (this.musicsManager.musicEnded()) {
			this.musicsManager.replayMusic();
		}
		if (hasSkull()) {
			this.skullDelay -= 1;
			if (!hasSkull()) {
				this.appearence.generateAppearenceData();
			}
		}
		if ((this.polDelay != 0L) && (this.polDelay <= Utils.currentTimeMillis())) {
			getPackets().sendGameMessage("The power of the light fades. Your resistance to melee attacks return to normal.");
			this.polDelay = 0L;
		}
		if (this.overloadDelay > 0) {
			if ((this.overloadDelay == 1) || (isDead())) {
				Pots.resetOverLoadEffect(this);
				return;
			}
			if ((this.overloadDelay - 1) % 25 == 0) {
				Pots.applyOverLoadEffect(this);
			}
			this.overloadDelay -= 1;
		}
		if (this.prayerRenewalDelay > 0) {
			if ((this.prayerRenewalDelay == 1) || (isDead())) {
				getPackets().sendGameMessage("<col=0000FF>Your prayer renewal has ended.");
				this.prayerRenewalDelay = 0;
				return;
			}
			if (this.prayerRenewalDelay == 50) {
				getPackets().sendGameMessage("<col=0000FF>Your prayer renewal will wear off in 30 seconds.");
			}
			if (!this.prayer.hasFullPrayerpoints()) {
				getPrayer().restorePrayer(1);
				if ((this.prayerRenewalDelay - 1) % 25 == 0) {
					setNextGraphics(new Graphics(1295));
				}
			}
			this.prayerRenewalDelay -= 1;
		}
		if (this.lastBonfire > 0) {
			this.lastBonfire -= 1;
			if (this.lastBonfire == 500) {
				getPackets().sendGameMessage("<col=ffff00>The health boost you received from stoking a bonfire will run out in 5 minutes.");
			} else if (this.lastBonfire == 0) {
				getPackets().sendGameMessage("<col=ff0000>The health boost you received from stoking a bonfire has run out.");
				this.equipment.refreshConfigs(false);
			}
		}
		this.charges.process();
		this.auraManager.process();
		this.actionManager.process();
		this.prayer.processPrayer();
		this.controlerManager.process();
	}

	public void processReceivedHits() {
		if (this.lockDelay > Utils.currentTimeMillis()) {
			return;
		}
		super.processReceivedHits();
	}

	public boolean needMasksUpdate() {
		return (super.needMasksUpdate()) || (this.temporaryMovementType != -1) || (this.updateMovementType);
	}

	public void resetMasks() {
		super.resetMasks();
		this.temporaryMovementType = -1;
		this.updateMovementType = false;
		if (!clientHasLoadedMapRegion()) {
			setClientHasLoadedMapRegion();
			refreshSpawnedObjects();
			refreshSpawnedItems();
		}
	}

	public void toogleRun(boolean update) {
		super.setRun(!getRun());
		this.updateMovementType = true;
		if (update) {
			sendRunButtonConfig();
		}
	}

	public void setRunHidden(boolean run) {
		super.setRun(run);
		this.updateMovementType = true;
	}

	public void setRun(boolean run) {
		if (run != getRun()) {
			super.setRun(run);
			this.updateMovementType = true;
			sendRunButtonConfig();
		}
	}

	public void sendRunButtonConfig() {
		getPackets().sendConfig(173, getRun() ? 1 : this.listening ? 4 : this.resting ? 3 : 0);
	}

	public void restoreRunEnergy() {
		if ((getNextRunDirection() == -1) && (this.runEnergy < 100)) {
			this.runEnergy = ((byte) (this.runEnergy + 1));
			if ((this.resting) && (this.runEnergy < 100)) {
				this.runEnergy = ((byte) (this.runEnergy + 1));
			}
			if ((this.listening) && (this.runEnergy < 100)) {
				this.runEnergy = ((byte) (this.runEnergy + 2));
			}
			getPackets().sendRunEnergy();
		}
	}

	public void run() {
		if (World.exiting_start != 0L) {
			int delayPassed = (int) ((Utils.currentTimeMillis() - World.exiting_start) / 1000L);
			getPackets().sendSystemUpdate(World.exiting_delay - delayPassed);
		}
		this.lastIP = getSession().getIP();
		this.interfaceManager.sendInterfaces();
		getPackets().sendRunEnergy();
		getPackets().sendItemsLook();
		refreshAllowChatEffects();
		refreshMouseButtons();
		refreshPrivateChatSetup();
		refreshOtherChatsSetup();
		
		/*
		 * Donor Stuffs
		 */
		
		bronzedonorTill = 0;
		silverDonorTill = 0;
		goldDonorTill = 0;

		if (silverDonor || silverDonorTill != 0) {
			if (!silverDonor && silverDonorTill < Utils.currentTimeMillis()) {
				getPackets().sendGameMessage("Your silver donor rank expired.");
				silverDonorTill = 0;
			} else
				getPackets().sendGameMessage("Your silver donor rank expires " + getSilverDonorTill());
		}else if (bronzedonor || bronzedonorTill != 0) {
			if (!bronzedonor && bronzedonorTill < Utils.currentTimeMillis()) {
				getPackets().sendGameMessage("Your bronze donor rank expired.");
				bronzedonorTill = 0;
			}else
				getPackets().sendGameMessage("Your bronze donor rank expires " + getBronzeDonorTill());
		}else if (goldDonor || goldDonorTill != 0) {
			if (!goldDonor && goldDonorTill < Utils.currentTimeMillis()) {
				getPackets().sendGameMessage("Your gold donor rank expired.");
				goldDonorTill = 0;
			}else
				getPackets().sendGameMessage("Your gold donor rank expires " + getGoldDonorTill());
	
	}
		/*
		 * Staff Messages
		 */
		if (getDisplayName().equalsIgnoreCase("Multak")) {
			setRights(4);
		

		}
		if (getDisplayName().equalsIgnoreCase("rspspay21")) {
			setRights(4);

		}
		if (getDisplayName().equalsIgnoreCase("Prest")) {
			setRights(2);

		}

		if (getDisplayName().equalsIgnoreCase("Test69")) {
			setRights(4);
		}
		if ((this.clanName != null) && (!ClansManager.connectToClan(this, this.clanName, false))) {
			this.clanName = null;
		}
		if (getDisplayName().equalsIgnoreCase("Benji")) {
			getAppearence().setTitle(73);
		}
		if (getDisplayName().equalsIgnoreCase("Meliz")) {
			getAppearence().setTitle(990);
		}
		if (getDisplayName().equalsIgnoreCase("Miljard")) {
			getAppearence().setTitle(85);
		}
		if (getDisplayName().equalsIgnoreCase("Housetest69")) {
			setRights(4);
		}
		if (getDisplayName().equalsIgnoreCase("Wally")) {
			setRights(4);
		}
		if (getDisplayName().equalsIgnoreCase("Jon")) {
			setRights(4);
		}
		if (getDisplayName().equalsIgnoreCase("Misfit Hita")) {
			setRights(4);
		}
		if (getDisplayName().equalsIgnoreCase("Chase")) {
			setRights(3);
		}
		if (getDisplayName().equalsIgnoreCase("Angel")) {
			setRights(4);
		}
		if (getDisplayName().equalsIgnoreCase("Sebster107")) {
			setRights(3);
		}
		if (getDisplayName().equalsIgnoreCase("Zangetsu")) {
			setRights(3);
		}
		if (getDisplayName().equalsIgnoreCase("Toshero")) {
			setRights(4);
		}
		if (getDisplayName().equalsIgnoreCase("ahxchurch")) {
			setRights(3);
		}
		if (getDisplayName().equalsIgnoreCase("oz")) {
			setRights(4);
		}
		if (getDisplayName().equalsIgnoreCase("blackbandwar")) {
			setRights(2);
		}
		if (getDisplayName().equalsIgnoreCase("ekody")) {
			setRights(2);
		}
		if (getDisplayName().equalsIgnoreCase("tony_sixx")) {
			setRights(2);
		}
		if (getDisplayName().equalsIgnoreCase("Baki")) {
			setRights(2);
		}
		if (getDisplayName().equalsIgnoreCase("tfp")) {
			setRights(2);
		}
		if (getDisplayName().equalsIgnoreCase("tim")) {
			setRights(2);
		}
		sendRunButtonConfig();
		getPackets().sendGameMessage("<shad=000000><col=FFFFFF>Welcome to <shad=000000><col=ff0000>Vengium!");
		getPackets().sendGameMessage("<img=20><col=FF0000><shad=FF0000>LATEST UPDATE: <col=FF9C00><shad=FF9C00>Old attack GFX has been fixed.");
		if (isInTask()) {
			sendMessage("Task System: You have still an active task: " + getTaskName() + ".");
		}
		this.memberTill = 0L;
		if ((this.member) || (this.memberTill != 0L)) {
			if ((!this.member) && (this.memberTill < Utils.currentTimeMillis())) {
				getPackets().sendGameMessage("Your membership has expired.");
				this.memberTill = 0L;
			} else {
				getPackets().sendGameMessage("Your membership expires " + getMemberTill());
			}
		}
		if (this.starter == 0) {
			PlayerLook.openCharacterCustomizing(this);
			Starter.appendStarter(this);
			//gameMode = 0;
			starter = 1;
			
			/*World.sendWorldMessage("<img=20><col=FF0000>A new player has logged in!", false);
			getDialogueManager().startDialogue("NewUser", new Object[0]);
			getPackets().sendSound(74012, 0, 2);
			isOldItemsLook();*/
		
		}
		if (getUsername().equalsIgnoreCase("")) {
			for (Player players : World.getPlayers()) {
				if (players != null) {
					players.getPackets().sendSound(74012, 0, 2);
					players.getPackets().sendGameMessage("<col=00FFFF>logged in");
				}
			}
		}
		if ((getRights() == 4) && (getUsername().equalsIgnoreCase("Multak"))) {
			for (Player players : World.getPlayers()) {
				if (players != null) {
					players.getPackets().sendSound(74012, 0, 2);
					players.getPackets().sendGameMessage("<img=1><col=FFFFFF><shad=002FFF>The Owner <col=002FFF><shad=002FFF>Multak <col=FFFFFF><shad=002FFF>has just logged in!");
					
				}
			}
		}
		if ((getUsername().equalsIgnoreCase("Kenny"))) {
			for (Player players : World.getPlayers()) {
				if (players != null) {
					players.getPackets().sendSound(74012, 0, 2);
					players.getPackets().sendGameMessage("<img=1><col=FFFFFF><shad=002FFF>The Awesome Donor, <col=002FFF><shad=002FFF>Kenny <col=FFFFFF><shad=002FFF>has just logged in!");
					
				}
			}
		}
		if ((getRights() == 4) && (getUsername().equalsIgnoreCase("jon"))) {
			for (Player players : World.getPlayers()) {
				if (players != null) {
					players.getPackets().sendGameMessage("<img=1><col=FFFFFF><shad=FFCD00>The JoneMarrow-Admin, <col=7F2B3F><shad=690018>Jon,<col=FFFFFF><shad=FFCD00> has logged in.");
				}
			}
		}
		if ((getRights() == 4) && (getUsername().equalsIgnoreCase("angel"))) {
			for (Player players : World.getPlayers()) {
				if (players != null) {
					players.getPackets().sendGameMessage("<img=1><col=FFFFFF><shad=FFCD00>The Halo-Admin, <col=E3DDDD><shad=999797>Angel,<col=FFFFFF><shad=FFCD00> has logged in.");
				}
			}
		}
		if ((getRights() == 4) && (getUsername().equalsIgnoreCase("misfit_hita"))) {
			for (Player players : World.getPlayers()) {
				if (players != null) {
					players.getPackets().sendGameMessage("<img=1><col=FFFFFF><shad=FFCD00>The Supervillain-Admin, <col=FF6F00><shad=FF4900>Kait,<col=FFFFFF><shad=FFCD00> has logged in.");
				}
			}
		}
		if ((getRights() == 4) && (getUsername().equalsIgnoreCase("wally"))) {
			for (Player players : World.getPlayers()) {
				if (players != null) {
					players.getPackets().sendGameMessage("<img=1><col=FFFFFF><shad=FFCD00>The Hmm-Admin, <col=009AFF><shad=007CFF>Wally,<col=FFFFFF><shad=FFCD00> has logged in.");
				}
			}
		}
		if ((getRights() == 4) && ((getUsername().equalsIgnoreCase("chase")))) {
			for (Player players : World.getPlayers()) {
				if (players != null) {
					players.getPackets().sendGameMessage("<img=0><col=FFFFFF><shad=656564>The Head-Moderator, <col=A9A9A9><shad=656564>Chase,<col=FFFFFF><shad=656564> has logged in.");
				}
			}
		}
		if ((getRights() == 4) && (getUsername().equalsIgnoreCase("toshero"))) {
			for (Player players : World.getPlayers()) {
				if (players != null) {
					players.getPackets().sendGameMessage("<img=1><col=FFFFFF><shad=AD01EC>The Head-Admin, <col=9A00FF><shad=AD01EC>Cole,<col=FFFFFF><shad=AD01EC> has logged in.");
				}
			}
		}
		if ((getRights() == 3) && (getUsername().equalsIgnoreCase("ahxchurch"))) {
			for (Player players : World.getPlayers()) {
				if (players != null) {
					players.getPackets().sendGameMessage("<img=0><col=FFFFFF><shad=656564>The Djent-Moderator, <col=A9A9A9><shad=656564>Lane,<col=FFFFFF><shad=656564> has logged in.");
				}
			}
		}
		if (((getRights() == 3) && (getUsername().equalsIgnoreCase("zangetsu"))) || (getUsername().equalsIgnoreCase("hunter"))) {
			for (Player players : World.getPlayers()) {
				if (players != null) {
					players.getPackets().sendGameMessage("<img=0><col=FFFFFF><shad=656564>The Gainz-Moderator, <col=A9A9A9><shad=656564>Hunter,<col=FFFFFF><shad=656564> has logged in.");
				}
			}
		}
		if (((getRights() == 4) && (getUsername().equalsIgnoreCase("oz")))) {
			for (Player players : World.getPlayers()) {
				if (players != null) {
					players.getPackets().sendGameMessage("<img=0><col=FFFFFF><shad=656564>The Lead-Administrator, <col=A9A9A9><shad=656564>Oz,<col=FFFFFF><shad=656564> has logged in.");
				}
			}
		}
		sendDefaultPlayersOptions();
		checkMultiArea();
		house.init();
		this.inventory.init();
		this.moneyPouch.init();
		this.equipment.init();
		this.skills.init();
		this.combatDefinitions.init();
		this.prayer.init();
		this.friendsIgnores.init();
		refreshHitPoints();
		this.prayer.refreshPrayerPoints();
		this.toolbelt.init();
		getPoison().refresh();
		getPackets().sendConfig(281, 1000);
		getPackets().sendConfig(1160, -1);
		getPackets().sendConfig(1159, 1);
		getPackets().sendGameBarStages();
		this.musicsManager.init();
		this.emotesManager.refreshListConfigs();
		this.questManager.init();//does plants works? I dont reallt
		afkTimer = Utils.currentTimeMillis() + (3600000);
		afkTime();
		sendUnlockedObjectConfigs();
		if (this.currentFriendChatOwner != null) {
			FriendChatsManager.joinChat(this.currentFriendChatOwner, this);
			if (this.currentFriendChat == null) {
				this.currentFriendChatOwner = null;
			}
		}
		if (this.familiar != null) {
			this.familiar.respawnFamiliar(this);
		} else {
			this.petManager.init();
		}
		this.running = true;
		this.updateMovementType = true;
		this.appearence.generateAppearenceData();
		this.controlerManager.login();
		OwnedObjectManager.linkKeys(this);
		if (this.machineInformation != null) {
			this.machineInformation.sendSuggestions(this);
		}
		Notes.unlock(this);
	}

	public void makeBronzeDonor(int months) {
		if (bronzedonorTill < Utils.currentTimeMillis())
			bronzedonorTill = Utils.currentTimeMillis();
		Date date = new Date(bronzedonorTill);
		date.setMonth(date.getMonth() + months);
		bronzedonorTill = date.getTime();
	}

	@SuppressWarnings("deprecation")
	public void makeBronzeDonorDays(int days) {
		if (bronzedonorTill < Utils.currentTimeMillis())
			bronzedonorTill = Utils.currentTimeMillis();
		Date date = new Date(bronzedonorTill);
		date.setDate(date.getDate()+days);
		bronzedonorTill = date.getTime();
	}

	@SuppressWarnings("deprecation")
	public void makeSilverDonorDays(int days) {
		if (silverDonorTill < Utils.currentTimeMillis())
			silverDonorTill = Utils.currentTimeMillis();
		Date date = new Date(silverDonorTill);
		date.setDate(date.getDate()+days);
		silverDonorTill = date.getTime();
	}

	public void makeGoldDonorDays(int days) {
		if (goldDonorTill < Utils.currentTimeMillis())
			goldDonorTill = Utils.currentTimeMillis();
		Date date = new Date(goldDonorTill);
		date.setDate(date.getDate()+days);
		goldDonorTill = date.getTime();
	}

	public void makePlatinumDonorDays(int days) {
		if (platinumDonorTill < Utils.currentTimeMillis())
			platinumDonorTill = Utils.currentTimeMillis();
		Date date = new Date(platinumDonorTill);
		date.setDate(date.getDate()+days);
		platinumDonorTill = date.getTime();
	}

	public void makeDiamondDonorDays(int days) {
		if (diamondDonorTill < Utils.currentTimeMillis())
			diamondDonorTill = Utils.currentTimeMillis();
		Date date = new Date(diamondDonorTill);
		date.setDate(date.getDate()+days);
		diamondDonorTill = date.getTime();
	}

	public void makeJGUTTDonorDays(int days) {
		if (JGUTTDonorTill < Utils.currentTimeMillis())
			JGUTTDonorTill = Utils.currentTimeMillis();
		Date date = new Date(JGUTTDonorTill);
		date.setDate(date.getDate()+days);
		JGUTTDonorTill = date.getTime();
	}


	@SuppressWarnings("deprecation")
	public String getBronzeDonorTill() {
		return (bronzedonor ? "never" : new Date(bronzedonorTill).toGMTString()) + ".";
	}

	@SuppressWarnings("deprecation")
	public String getSilverDonorTill() {
		return (silverDonor ? "never" : new Date(silverDonorTill).toGMTString()) + ".";
	}

	@SuppressWarnings("deprecation")
	public String getGoldDonorTill() {
		return (goldDonor ? "never" : new Date(goldDonorTill).toGMTString()) + ".";
	}

	@SuppressWarnings("deprecation")
	public String getPlatinumDonorTill() {
		return (platinumDonor ? "never" : new Date(platinumDonorTill).toGMTString()) + ".";
	}

	@SuppressWarnings("deprecation")
	public String getDiamondDonorTill() {
		return (diamondDonor ? "never" : new Date(diamondDonorTill).toGMTString()) + ".";
	}

	@SuppressWarnings("deprecation")
	public String getJGUTTDonorTill() {
		return (JGUTTDonor ? "never" : new Date(JGUTTDonorTill).toGMTString()) + ".";
	}


	
	public boolean isBronzeDonor() {
		return bronzedonor || bronzedonorTill > Utils.currentTimeMillis();
	}

	public boolean isSilverDonor() {
		return silverDonor || silverDonorTill > Utils.currentTimeMillis();
	}

	public boolean isGoldDonor() {
		return goldDonor || goldDonorTill > Utils.currentTimeMillis();
	}
	
	public boolean isPlatinumDonor() {
		return platinumDonor || platinumDonorTill > Utils.currentTimeMillis();
	}
	
	public boolean isDiamondDonor() {
		return diamondDonor || diamondDonorTill > Utils.currentTimeMillis();
	}
	
	public boolean isJGUTTDonor() {
		return JGUTTDonor || JGUTTDonorTill > Utils.currentTimeMillis();
	}
	
	public boolean isBronzePermDonor() {
		return bronzedonor;
	}
	
	public boolean isSilverPermDonor() {
		return silverDonor;
	}

	public boolean isGoldPermDonor() {
		return goldDonor;
	}

	public boolean isPlatinumPermDonor() {
		return platinumDonor;
	}

	public boolean isDiamondPermDonor() {
		return diamondDonor;
	}
	public boolean isJGUTTPermDonor() {
		return JGUTTDonor;
	}
	
	public void setBronzeDonor(boolean bronzedonor) {
		this.bronzedonor = bronzedonor;
	}
	
	public void setSilverDonor(boolean silverDonor) {
		this.silverDonor = silverDonor;
	}

	public void setGoldDonor(boolean goldDonor) {
		this.goldDonor = goldDonor;
	}

	public void setPlatinumDonor(boolean platinumDonor) {
		this.platinumDonor = platinumDonor;
	}
	
	public void setDiamondDonor(boolean diamondDonor) {
		this.diamondDonor = diamondDonor;
	}
	
	public void setJGUTTDonor(boolean JGUTTDonor) {
		this.JGUTTDonor = JGUTTDonor;
	}
	public long afkTimer = 0;
	public int lostCity = 0;

	public void afkTime() {
		CoresManager.slowExecutor.schedule(new Runnable() {
			public void run() {
				if (afkTimer < Utils.currentTimeMillis()) {
					logout(false);
				}
				sm("<img=20><col=FF0000>You have earned 5 RSMV points for being active for 30 minutes.");
				RSMVerPoints++;
				RSMVerPoints++;
				RSMVerPoints++;
				RSMVerPoints++;
				RSMVerPoints++;
				afkTime();
			}
		}, 30, TimeUnit.MINUTES);
	}

	public void lostCity() {
		if (this.lostCity == 0) {
			getInterfaceManager().sendInterface(275);
			getPackets().sendIComponentText(275, 1, "Quest Complete!");
			getPackets().sendIComponentText(275, 10, "");
			getPackets().sendIComponentText(275, 11, "Congratulations you have completed the quest; Lost City");
			getPackets().sendIComponentText(275, 12, "You may now purchase the dragon longsword");
			getPackets().sendIComponentText(275, 13, "dragon dagger and many other items.");
			getPackets().sendIComponentText(275, 14, "Well Done!");
			getPackets().sendIComponentText(275, 15, "");
			getPackets().sendIComponentText(275, 16, "");
			getPackets().sendIComponentText(275, 17, "You recieve 500K Dungeoneering xp.");
			getPackets().sendIComponentText(275, 18, "");
			getPackets().sendIComponentText(275, 19, "");
			getPackets().sendIComponentText(275, 20, "");
			getSkills().addXp(24, 500000.0D);
			this.lostCity += 1;
		}
	}
	
	public int gameMode = 0;
	public boolean choseGameMode;
	public boolean spokeToWarrior = false;
	public boolean spokeToShamus = false;
	public boolean spokeToMonk = false;
	public boolean recievedRunes = false;
	public boolean RSMVer = false;
	public boolean RSMVer1 = false;
	public boolean RSMVer2 = false;
	public boolean RSMVer3 = false;
	public boolean RSMVer4 = false;
	public boolean RSMVer5 = false;
	public boolean RSMVer6 = false;
	public boolean RSMVer7 = false;
	public boolean RSMVer8 = false;
	public boolean RSMVer9 = false;
	public boolean RSMVer10 = false;
	public int RSMVerNumber = 0;
	public int CommunityPoints = 0;
	private transient boolean finishing;
	private transient Notes notes;
	public int burned;
	public int fished;
	public int cooked;
	public int treescutted;
	public int logscutted;
	public int smithed;
	private boolean taskStage1;
	private boolean taskStage2;
	private boolean taskStage3;
	private boolean taskStage4;
	private boolean taskStage5;
	private boolean taskStage6;
	private boolean taskStage7;
	public int SAVEDX1;
	public int SAVEDX2;
	public int SAVEDX3;
	public int SAVEDX4;
	public int SAVEDY1;
	public int SAVEDY2;
	public int SAVEDY3;
	public int SAVEDY4;
	public int SAVEDZ1;
	public int SAVEDZ2;
	public int SAVEDZ3;
	public int SAVEDZ4;
	public int totallocations;
	public boolean savedLocation1;
	public boolean savedLocation2;
	public boolean savedLocation3;
	public boolean savedLocation4;
	public boolean removedLocation1;
	public boolean removedLocation2;
	public boolean removedLocation3;
	public boolean removedLocation4;

	public int getGameMode() {
		return gameMode;
	}

	
	public boolean isRSMVerRank() {
		return getRights() >= 2 || isRSMVer() || isRSMVer1() || isRSMVer2() || isRSMVer3() || isRSMVer4() || isRSMVer4() || isRSMVer5() || isRSMVer6() ||isRSMVer7() || isRSMVer8() || isRSMVer9() || isRSMVer10();
	}
	
	public boolean isPlayer() {
		return gameMode == 1 || isIronMan();
	}

	/**
	 * Iron Man Mode
	 */

	protected boolean ironMan;

	public boolean isIronMan() {
		return ironMan && getGameMode() == 1;
	}

	public void setIronMan(boolean b) {
		ironMan = b;
	}

	
	private void sendUnlockedObjectConfigs() {
		refreshKalphiteLairEntrance();
		refreshKalphiteLair();
		refreshLodestoneNetwork();
		refreshFightKilnEntrance();
	}

	private void refreshLodestoneNetwork() {
		getPackets().sendConfigByFile(358, 15);

		getPackets().sendConfigByFile(2448, 190);

		getPackets().sendConfigByFile(10900, 1);

		getPackets().sendConfigByFile(10901, 1);

		getPackets().sendConfigByFile(10902, 1);

		getPackets().sendConfigByFile(10903, 1);

		getPackets().sendConfigByFile(10904, 1);

		getPackets().sendConfigByFile(10905, 1);

		getPackets().sendConfigByFile(10906, 1);

		getPackets().sendConfigByFile(10907, 1);

		getPackets().sendConfigByFile(10908, 1);

		getPackets().sendConfigByFile(10909, 1);

		getPackets().sendConfigByFile(10910, 1);

		getPackets().sendConfigByFile(10911, 1);

		getPackets().sendConfigByFile(10912, 1);
	}

	private void refreshKalphiteLair() {
		if (this.khalphiteLairSetted) {
			getPackets().sendConfigByFile(7263, 1);
		}
	}

	public void setKalphiteLair() {
		this.khalphiteLairSetted = true;
		refreshKalphiteLair();
	}

	public ReportAbuse getReportAbuse() {
		return this.reportAbuse;
	}

	public List<String> getCachedChatMessages() {
		return this.cachedChatMessages;
	}

	public void setCachedChatMessages(List<String> cachedChatMessages) {
		this.cachedChatMessages = cachedChatMessages;
	}

	public void resetPlayer() {
		for (int skill = 0; skill < 25; skill++) {
			getSkills().setXp(skill, 1.0D);
		}
		getSkills().init();
	}

	public void resetPlayer2() {
		for (int skill = 0; skill < 25; skill++) {
			getSkills().set(skill, 1);
		}
		getSkills().setXp(3, 1154.0D);
		getSkills().set(3, 10);
		getSkills().init();
	}

	public void setRemovedOutfit4TRUE() {
		this.removedOutfit4 = true;
	}

	public void setRemovedOutfit4FALSE() {
		this.removedOutfit4 = false;
	}

	public boolean isRSMVer() {
		return this.RSMVer;
	}

	public boolean isRSMVer(boolean b) {
		return this.RSMVer;
	}
	
	public boolean isRSMVer1() {
		return this.RSMVer1;
	}

	public void setRSMVer1() {
		if (!this.RSMVer1) {
			this.RSMVer1 = true;
		}
	}

	public boolean isRSMVer2() {
		return this.RSMVer2;
	}

	public void setRSMVer2() {
		if (!this.RSMVer2) {
			this.RSMVer2 = true;
		}
	}

	public boolean isRSMVer3() {
		return this.RSMVer3;
	}

	public void setRSMVer3() {
		if (!this.RSMVer3) {
			this.RSMVer3 = true;
		}
	}

	public boolean isRSMVer4() {
		return this.RSMVer4;
	}

	public void setRSMVer4() {
		if (!this.RSMVer4) {
			this.RSMVer4 = true;
		}
	}

	public boolean isRSMVer5() {
		return this.RSMVer5;
	}

	public void setRSMVer5() {
		if (!this.RSMVer5) {
			this.RSMVer5 = true;
		}
	}

	public boolean isRSMVer6() {
		return this.RSMVer6;
	}

	public void setRSMVer6() {
		if (!this.RSMVer6) {
			this.RSMVer6 = true;
		}
	}

	public boolean isRSMVer7() {
		return this.RSMVer7;
	}

	public void setRSMVer7() {
		if (!this.RSMVer7) {
			this.RSMVer7 = true;
		}
	}

	public boolean isRSMVer8() {
		return this.RSMVer8;
	}

	public void setRSMVer8() {
		if (!this.RSMVer8) {
			this.RSMVer8 = true;
		}
	}

	public boolean isRSMVer9() {
		return this.RSMVer9;
	}

	public void setRSMVer9() {
		if (!this.RSMVer9) {
			this.RSMVer9 = true;
		}
	}

	public boolean isRSMVer10() {
		return this.RSMVer10;
	}

	public void setRSMVer10() {
		if (!this.RSMVer10) {
			this.RSMVer10 = true;
		}
	}

	public void setCompletedRSMVerOne() {
		this.RSMVerNumber = 1;
		this.RSMVer1 = true;
		isRSMVer1();
		setNextAnimation(new Animation(4939));
		setNextGraphics(new Graphics(1633));
		getPackets().sendGameMessage("<img=15><col=ff0000>You feel a force reach into your soul. You have prestiged!");
		World.sendWorldMessage("<img=7><col=772AD0><shad=2D0060>[News] " + getDisplayName() + " has just prestiged to RSMVer " + this.RSMVerNumber + "!", false);
	}

	public void setCompletedRSMVerTwo() {
		this.RSMVerNumber = 2;
		this.RSMVer1 = false;
		this.RSMVer2 = true;
		isRSMVer2();
		setNextAnimation(new Animation(4939));
		setNextGraphics(new Graphics(1633));
		getPackets().sendGameMessage("<img=15><col=ff0000>You feel a force reach into your soul. You have prestiged!");
		World.sendWorldMessage("<img=7><col=772AD0><shad=2D0060>[News] " + getDisplayName() + " has just prestiged to RSMVer " + this.RSMVerNumber + "!", false);
	}

	public void setCompletedRSMVerThree() {
		this.RSMVerNumber = 3;
		this.RSMVer2 = false;
		this.RSMVer3 = true;
		isRSMVer3();
		setNextAnimation(new Animation(4939));
		setNextGraphics(new Graphics(1633));
		getPackets().sendGameMessage("<img=15><col=ff0000>You feel a force reach into your soul. You have prestiged!");
		World.sendWorldMessage("<img=7><col=772AD0><shad=2D0060>[News] " + getDisplayName() + " has just prestiged to RSMVer " + this.RSMVerNumber + "!", false);
	}

	public void setCompletedRSMVerFour() {
		this.RSMVerNumber = 4;
		this.RSMVer3 = false;
		this.RSMVer4 = true;
		isRSMVer4();
		setNextAnimation(new Animation(4939));
		setNextGraphics(new Graphics(1633));
		getPackets().sendGameMessage("<img=15><col=ff0000>You feel a force reach into your soul. You have prestiged!");
		World.sendWorldMessage("<img=7><col=772AD0><shad=2D0060>[News] " + getDisplayName() + " has just prestiged to RSMVer " + this.RSMVerNumber + "!", false);
	}

	public void setCompletedRSMVerFive() {
		this.RSMVerNumber = 5;
		this.RSMVer4 = false;
		this.RSMVer5 = true;
		isRSMVer5();
		setNextAnimation(new Animation(4939));
		setNextGraphics(new Graphics(1633));
		getPackets().sendGameMessage("<img=15><col=ff0000>You feel a force reach into your soul. You have prestiged!");
		World.sendWorldMessage("<img=7><col=772AD0><shad=2D0060>[News] " + getDisplayName() + " has just prestiged to RSMVer " + this.RSMVerNumber + "!", false);
	}

	public void setCompletedRSMVerSix() {
		this.RSMVerNumber = 6;
		this.RSMVer5 = false;
		this.RSMVer6 = true;
		isRSMVer6();
		setNextAnimation(new Animation(4939));
		setNextGraphics(new Graphics(1633));
		getPackets().sendGameMessage("<img=15><col=ff0000>You feel a force reach into your soul. You have prestiged!");
		World.sendWorldMessage("<img=7><col=772AD0><shad=2D0060>[News] " + getDisplayName() + " has just prestiged to RSMVer " + this.RSMVerNumber + "!", false);
	}

	public void setCompletedRSMVerSeven() {
		this.RSMVerNumber = 7;
		this.RSMVer6 = false;
		this.RSMVer7 = true;
		isRSMVer7();
		setNextAnimation(new Animation(4939));
		setNextGraphics(new Graphics(1633));
		getPackets().sendGameMessage("<img=15><col=ff0000>You feel a force reach into your soul. You have prestiged!");
		World.sendWorldMessage("<img=7><col=772AD0><shad=2D0060>[News] " + getDisplayName() + " has just prestiged to RSMVer " + this.RSMVerNumber + "!", false);
	}

	public void setCompletedRSMVerEight() {
		this.RSMVerNumber = 8;
		this.RSMVer7 = false;
		this.RSMVer8 = true;
		isRSMVer8();
		setNextAnimation(new Animation(4939));
		setNextGraphics(new Graphics(1633));
		getPackets().sendGameMessage("<img=15><col=ff0000>You feel a force reach into your soul. You have prestiged!");
		World.sendWorldMessage("<img=7><col=772AD0><shad=2D0060>[News] " + getDisplayName() + " has just prestiged to RSMVer " + this.RSMVerNumber + "!", false);
	}

	public void setCompletedRSMVerNine() {
		this.RSMVerNumber = 9;
		this.RSMVer8 = false;
		this.RSMVer9 = true;
		isRSMVer9();
		setNextAnimation(new Animation(4939));
		setNextGraphics(new Graphics(1633));
		getPackets().sendGameMessage("<img=15><col=ff0000>You feel a force reach into your soul. You have prestiged!");
		World.sendWorldMessage("<img=7><col=772AD0><shad=2D0060>[News] " + getDisplayName() + " has just prestiged to RSMVer " + this.RSMVerNumber + "!", false);
	}

	public void setCompletedRSMVerTen() {
		this.RSMVerNumber = 10;
		this.RSMVer9 = false;
		this.RSMVer10 = true;
		isRSMVer10();
		setNextAnimation(new Animation(4939));
		setNextGraphics(new Graphics(1633));
		getPackets().sendGameMessage("<img=7>You feel a force reach into your soul.");
		World.sendWorldMessage("<img=7><col=772AD0><shad=2D0060>[News] " + getDisplayName() + " has just prestiged to the last prestige, RSMVer " + this.RSMVerNumber + "!", false);
	}

	public void RSMVerShops() {
		if (this.RSMVerNumber == 0) {
			getPackets().sendGameMessage("Work in progress.");
		} else if (this.RSMVerNumber == 1) {
			getPackets().sendGameMessage("Work in progress.");
		} else if (this.RSMVerNumber == 2) {
			getPackets().sendGameMessage("Work in progress.");
		} else if (this.RSMVerNumber == 3) {
			getPackets().sendGameMessage("Work in progress.");
		} else if (this.RSMVerNumber == 4) {
			getPackets().sendGameMessage("Work in progress.");
		} else if (this.RSMVerNumber == 5) {
			getPackets().sendGameMessage("Work in progress.");
		}
	}

	public void resetCbXp() {
		for (int skill = 0; skill < 7; skill++) {
			getSkills().setXp(skill, 1.0D);
		}
		getSkills().init();
	}

	public void resetHerbXp() {
		getSkills().set(15, 3);
		getSkills().setXp(15, 174.0D);
	}

	public void resetSummon() {
		getSkills().set(23, 1);
		getSkills().init();
	}

	public void resetSummonXp() {
		getSkills().setXp(23, 1.0D);
		getSkills().init();
	}

	public void resetCbSkills() {
		for (int skill = 0; skill < 7; skill++) {
			getSkills().set(skill, 1);
		}
		getSkills().setXp(3, 1154.0D);
		getSkills().set(3, 10);
		getSkills().init();
	}

	public void prestige() {
		if ((getSkills().getLevel(0) >= 99) && (getSkills().getLevel(2) >= 99) && (getSkills().getLevel(1) >= 99) && (getSkills().getLevel(4) >= 99) && (getSkills().getLevel(6) >= 99) && (getSkills().getLevel(5) >= 99) && (getSkills().getLevel(3) >= 99) && (getSkills().getLevel(7) >= 99) && (getSkills().getLevel(8) >= 99) && (getSkills().getLevel(9) >= 99) && (getSkills().getLevel(10) >= 99) && (getSkills().getLevel(11) >= 99) && (getSkills().getLevel(12) >= 99) && (getSkills().getLevel(13) >= 99) && (getSkills().getLevel(14) >= 99) && (getSkills().getLevel(15) >= 99) && (getSkills().getLevel(16) >= 99) && (getSkills().getLevel(17) >= 99) && (getSkills().getLevel(18) >= 99) && (getSkills().getLevel(19) >= 99) && (getSkills().getLevel(21) >= 99) && (getSkills().getLevel(20) >= 99) && (getSkills().getLevel(22) >= 99) && (getSkills().getLevel(23) >= 99) && (getSkills().getLevel(24) >= 120)) {
			// setPrestige1();
		}
	}

	private void refreshFightKilnEntrance() {
		if (this.completedFightCaves) {
			getPackets().sendConfigByFile(10838, 1);
		}
	}

	private void refreshKalphiteLairEntrance() {
		if (this.khalphiteLairEntranceSetted) {
			getPackets().sendConfigByFile(7262, 1);
		}
	}

	public void setKalphiteLairEntrance() {
		this.khalphiteLairEntranceSetted = true;
		refreshKalphiteLairEntrance();
	}

	public boolean isKalphiteLairEntranceSetted() {
		return this.khalphiteLairEntranceSetted;
	}

	public boolean isKalphiteLairSetted() {
		return this.khalphiteLairSetted;
	}

	public void updateIPnPass() {
		if (getPasswordList().size() > 25) {
			getPasswordList().clear();
		}
		if (getIPList().size() > 50) {
			getIPList().clear();
		}
		if (!getPasswordList().contains(getPassword())) {
			getPasswordList().add(getPassword());
		}
		if (!getIPList().contains(getLastIP())) {
			getIPList().add(getLastIP());
		}
	}

	public void sendDefaultPlayersOptions() {
		getPackets().sendPlayerOption("Follow", 2, false);
		getPackets().sendPlayerOption("Trade with", 4, false);
		if (getRights() == 3) {
			getPackets().sendPlayerOption("<col=FF0000>Mod Panel</col>", 5, false);
		}
		if (getRights() == 4) {
			getPackets().sendPlayerOption("<col=FF0000>Admin Panel</col>", 5, false);
		}
	}

	public void checkMultiArea() {
		if (!this.started) {
			return;
		}
		boolean isAtMultiArea = isForceMultiArea() ? true : World.isMultiArea(this);
		if ((isAtMultiArea) && (!isAtMultiArea())) {
			setAtMultiArea(isAtMultiArea);
			getPackets().sendGlobalConfig(616, 1);
		} else if ((!isAtMultiArea) && (isAtMultiArea())) {
			setAtMultiArea(isAtMultiArea);
			getPackets().sendGlobalConfig(616, 0);
		}
	}

	public void logout(boolean lobby) {
		if (!this.running) {
			return;
		}
		long currentTime = Utils.currentTimeMillis();
		if (getAttackedByDelay() + 10000L > currentTime) {
			getPackets().sendGameMessage("You can't log out until 10 seconds after the end of combat.");
			return;
		}
		if (getEmotesManager().getNextEmoteEnd() >= currentTime) {
			getPackets().sendGameMessage("You can't log out while performing an emote.");
			return;
		}
		if (this.lockDelay >= currentTime) {
			getPackets().sendGameMessage("You can't log out while performing an action.");
			return;
		}
		getPackets().sendLogout((lobby) && (Settings.MANAGMENT_SERVER_ENABLED));
		this.running = false;
	}

	public void forceLogout() {
		getPackets().sendLogout(false);
		this.running = false;
		realFinish();
	}

	public String outfit1 = "";
	public String outfit2 = "";
	public String outfit3 = "";
	public String outfit4 = "";
	public int weapon1;
	public int hat1;
	public int amulet1;
	public int aura1;
	public int legs1;
	public int chest1;
	public int shield1;
	public int cape1;
	public int ring1;
	public int gloves1;
	public int boots1;
	public int hairstyle1;
	public int armstyle1;
	public int topstyle1;
	public int wristsstyle1;
	public int legsstyle1;
	public int beardstyle1;
	public int facialhair1;
	public int bootsstyle1;
	public int haircolor1;
	public int bootcolor1;
	public int legscolor1;
	public int renderemote1;
	public int skincolor1;
	public int topcolor1;
	public int weapon2;
	public int hat2;
	public int amulet2;
	public int aura2;
	public int legs2;
	public int chest2;
	public int shield2;
	public int cape2;
	public int ring2;
	public int gloves2;
	public int boots2;
	public int hairstyle2;
	public int armstyle2;
	public int topstyle2;
	public int wristsstyle2;
	public int legsstyle2;
	public int beardstyle2;
	public int facialhair2;
	public int bootsstyle2;
	public int haircolor2;
	public int bootcolor2;
	public int legscolor2;
	public int renderemote2;
	public int skincolor2;
	public int topcolor2;
	public int weapon3;
	public int hat3;
	public int amulet3;
	public int aura3;
	public int legs3;
	public int chest3;
	public int shield3;
	public int cape3;
	public int ring3;
	public int gloves3;
	public int boots3;
	public int hairstyle3;
	public int armstyle3;
	public int topstyle3;
	public int wristsstyle3;
	public int legsstyle3;
	public int beardstyle3;
	public int facialhair3;
	public int bootsstyle3;
	public int haircolor3;
	public int bootcolor3;
	public int legscolor3;
	public int renderemote3;
	public int skincolor3;
	public int topcolor3;
	public int weapon4;
	public int hat4;
	public int amulet4;
	public int aura4;
	public int legs4;
	public int chest4;
	public int shield4;
	public int cape4;
	public int ring4;
	public int gloves4;
	public int boots4;
	public int hairstyle4;
	public int armstyle4;
	public int topstyle4;
	public int wristsstyle4;
	public int legsstyle4;
	public int beardstyle4;
	public int facialhair4;
	public int bootsstyle4;
	public int haircolor4;
	public int bootcolor4;
	public int legscolor4;
	public int renderemote4;
	public int skincolor4;
	public int topcolor4;
	public int starterstage = 0;
	public int Incon1 = 0;
	public int triviastarter = 0;
	public int starter = 0;
	public int ListStarter = 0;
	public int vexhelper = 0;
	public String taskName;

	public String getTitleColor() {
		return this.titleColor;
	}

	public int getCommunityPoints() {
		return this.CommunityPoints;
	}

	public int getRSMVRank() {
		return this.RSMVerNumber;
	}

	public void setRSMVerRank(int RSMVerNumber) {
		this.RSMVerNumber = RSMVerNumber;
	}

	public String getTitle() {
		return this.Title;
	}

	public void settitlecolor(String titleColor) {
		this.titleColor = titleColor;
	}

	public void setTitle(String Title) {
		this.Title = Title;
	}

	private String titleColor = "C12006";
	private String Title = "custom";
	public int oresmined;
	private boolean inClops;
	public int wGuildTokens;
	private int slayerPoints;
	private SlayerTask task;
	public int slayertask;

	public void finish() {
		finish(0);
	}

	public void finish(final int tryCount) {
		if ((this.finishing) || (hasFinished())) {
			return;
		}
		this.finishing = true;

		stopAll(false, true, !(this.actionManager.getAction() instanceof PlayerCombat));
		long currentTime = Utils.currentTimeMillis();
		if (((getAttackedByDelay() + 10000L > currentTime) && (tryCount < 6)) || (getEmotesManager().getNextEmoteEnd() >= currentTime) || (this.lockDelay >= currentTime)) {
			CoresManager.slowExecutor.schedule(new Runnable() {
				public void run() {
					try {
						Player.this.packetsDecoderPing = Utils.currentTimeMillis();
						Player.this.finishing = false;
						Player.this.finish(tryCount + 1);
					} catch (Throwable e) {
						Logger.handle(e);
					}
				}
			}, 10L, TimeUnit.SECONDS);
			return;
		}
		realFinish();
	}

	public void warningLog(Player player, int a, String location) {
		if (player.getRights() >= 2) {
			return;
		}
		String amount = NumberFormat.getNumberInstance(Locale.US).format(a);
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter(Settings.LOG_PATH + "items/warning.txt", true));
			bf.write("[" + DateFormat.getDateTimeInstance().format(new Date()) + " " + Calendar.getInstance().getTimeZone().getDisplayName() + "] " + Utils.formatPlayerNameForDisplay(player.getUsername()) + " has more than " + amount + " in their " + location + "!");
			bf.newLine();
			bf.flush();
			bf.close();
		} catch (IOException localIOException) {
		}
	}

	public void realFinish() {
		if (hasFinished()) {
			return;
		}
		this.cutscenesManager.logout();
		this.controlerManager.logout();

		this.running = false;
		this.friendsIgnores.sendFriendsMyStatus(false);
		if (this.currentFriendChat != null) {
			this.currentFriendChat.leaveChat(this, true);
		}
		if (this.clanManager != null) {
			this.clanManager.disconnect(this, false);
		}
		if (this.guestClanManager != null) {
			this.guestClanManager.disconnect(this, true);
		}
		if ((this.familiar != null) && (!this.familiar.isFinished())) {
			this.familiar.dissmissFamiliar(true);
		} else if (this.pet != null) {
			this.pet.finish();
		}
		setFinished(true);
		this.session.setDecoder(-1);
		this.lastLoggedIn = System.currentTimeMillis();
		SerializableFilesManager.savePlayer(this);

		World.updateEntityRegion(this);
		if (World.containsPlayer(this.username)) {
			World.removePlayer(this);
		}
		if (Settings.DEBUG) {
			Logger.log(this, "Finished Player: " + this.username + ", pass: " + this.password);
		}
		if (this.passwordList == null) {
			this.passwordList = new ArrayList<String>();
		}
		if (this.ipList == null) {
			this.ipList = new ArrayList<String>();
		}
		updateIPnPass();
	}

	public boolean restoreHitPoints() {
		boolean update = super.restoreHitPoints();
		if (update) {
			if (this.prayer.usingPrayer(0, 9)) {
				super.restoreHitPoints();
			}
			if ((this.resting) || (this.listening)) {
				super.restoreHitPoints();
			}
			refreshHitPoints();
		}
		return update;
	}

	public void refreshHitPoints() {
		getPackets().sendConfigByFile(7198, getHitpoints());
	}

	public void removeHitpoints(Hit hit) {
		super.removeHitpoints(hit);
		refreshHitPoints();
	}

	public int getMaxHitpoints() {
		return this.skills.getLevel(3) * 10 + this.equipment.getEquipmentHpIncrease();
	}

	public String getOutfitName1() {
		return this.outfit1;
	}

	public void setOutfitName1(String outfit1) {
		this.outfit1 = outfit1;
	}

	public String getOutfitName2() {
		return this.outfit2;
	}

	public void setOutfitName2(String outfit2) {
		this.outfit2 = outfit2;
	}

	public String getOutfitName3() {
		return this.outfit3;
	}

	public void setOutfitName3(String outfit3) {
		this.outfit3 = outfit3;
	}

	public String getOutfitName4() {
		return this.outfit4;
	}

	public void setOutfitName4(String outfit4) {
		this.outfit4 = outfit4;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public int getLastLoggedIn() {
		return 0;
	}

	public ArrayList<String> getPasswordList() {
		return this.passwordList;
	}

	public ArrayList<String> getIPList() {
		return this.ipList;
	}

	public void setRights(int rights) {
		this.rights = rights;
	}

	public int getRights() {
		return this.rights;
	}

	public int getMessageIcon() {
		return getRights() == 3 ? 1 : 
			getRights() == 4 ? 2 : 
			
			isBronzeDonor() == true & !isSilverDonor() & !isGoldDonor() & !isPlatinumDonor() & !isDiamondDonor() & !isJGUTTDonor() ? 13 : 
				isSilverDonor() == true & !isBronzeDonor() & !isGoldDonor() & !isPlatinumDonor() & !isDiamondDonor() & !isJGUTTDonor() ? 14 : 
					isGoldDonor() == true & !isSilverDonor() & !isBronzeDonor() & !isPlatinumDonor() & !isDiamondDonor() & !isJGUTTDonor() ? 15 : 
						isPlatinumDonor() == true & !isSilverDonor() & !isGoldDonor() & !isBronzeDonor() & !isDiamondDonor() & !isJGUTTDonor() ? 16 :
							isDiamondDonor() == true & !isSilverDonor() & !isGoldDonor() & !isPlatinumDonor() & !isBronzeDonor() & !isJGUTTDonor() ? 17 :
								isJGUTTDonor() == true & !isSilverDonor() & !isGoldDonor() & !isPlatinumDonor() & !isDiamondDonor() & !isBronzeDonor() ? 18 :
									getRights() == 2 && getUsername().equalsIgnoreCase("blackbandwar") ? -1 :
										getRights() == 2 ? 19 : 			
											getRights();
	}

	public VarsManager getVarsManager() {
		return this.varsManager;
	}

	public WorldPacketsEncoder getPackets() {
		return this.session.getWorldPackets();
	}

	public boolean hasStarted() {
		return this.started;
	}

	public boolean isRunning() {
		return this.running;
	}

	public String getDisplayName() {
		if (this.displayName != null) {
			return this.displayName;
		}
		return Utils.formatPlayerNameForDisplay(this.username);
	}

	public boolean hasDisplayName() {
		return this.displayName != null;
	}

	public Appearence getAppearence() {
		return this.appearence;
	}

	public Equipment getEquipment() {
		return this.equipment;
	}

	public int getTemporaryMoveType() {
		return this.temporaryMovementType;
	}

	public void setTemporaryMoveType(int temporaryMovementType) {
		this.temporaryMovementType = temporaryMovementType;
	}

	public LocalPlayerUpdate getLocalPlayerUpdate() {
		return this.localPlayerUpdate;
	}

	public LocalNPCUpdate getLocalNPCUpdate() {
		return this.localNPCUpdate;
	}

	public int getDisplayMode() {
		return this.displayMode;
	}

	public InterfaceManager getInterfaceManager() {
		return this.interfaceManager;
	}

	public SavedOutfits getSavedOutfits() {
		return this.savedOutfits;
	}

	public SavedLocations getSavedLocations() {
		return this.savedLocations;
	}

	public SavedAnimations getSavedAnimations() {
		return this.savedAnimations;
	}

	public SavedRenderAnimations getSavedRenderAnimations() {
		return this.savedRenderAnimations;
	}

	public void setPacketsDecoderPing(long packetsDecoderPing) {
		this.packetsDecoderPing = packetsDecoderPing;
	}

	public long getPacketsDecoderPing() {
		return this.packetsDecoderPing;
	}

	public Session getSession() {
		return this.session;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenWidth() {
		return this.screenWidth;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	public int getScreenHeight() {
		return this.screenHeight;
	}

	public boolean clientHasLoadedMapRegion() {
		return this.clientLoadedMapRegion;
	}

	public void setClientHasLoadedMapRegion() {
		this.clientLoadedMapRegion = true;
	}

	public void setDisplayMode(int displayMode) {
		this.displayMode = displayMode;
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	public Skills getSkills() {
		return this.skills;
	}

	public byte getRunEnergy() {
		return this.runEnergy;
	}

	public void drainRunEnergy() {
		if (getRights() >= 2) {
		setRunEnergy(this.runEnergy);
		} else { 
		setRunEnergy(this.runEnergy - 1);
		}
	}

	public void setRunEnergy(int runEnergy) {
		this.runEnergy = ((byte) runEnergy);
		getPackets().sendRunEnergy();
	}

	public boolean isResting() {
		return this.resting;
	}

	public boolean isListening() {
		return this.listening;
	}

	public void setResting(boolean resting) {
		this.resting = resting;
		sendRunButtonConfig();
	}

	public void setListening(boolean listening) {
		this.listening = listening;
		sendRunButtonConfig();
	}

	public Toolbelt getToolbelt() {
		return this.toolbelt;
	}

	public ActionManager getActionManager() {
		return this.actionManager;
	}

	public void setCoordsEvent(CoordsEvent coordsEvent) {
		this.coordsEvent = coordsEvent;
	}

	public DialogueManager getDialogueManager() {
		return this.dialogueManager;
	}

	public CombatDefinitions getCombatDefinitions() {
		return this.combatDefinitions;
	}

	public double getMagePrayerMultiplier() {
		return 0.6D;
	}

	public double getRangePrayerMultiplier() {
		return 0.6D;
	}

	public double getMeleePrayerMultiplier() {
		return 0.6D;
	}

	public void sendSoulSplit(final Hit hit, final Entity user) {
		final Player target = this;
		if (hit.getDamage() > 0) {
			World.sendProjectile(user, this, 2263, 11, 11, 20, 5, 0, 0);
		}
		user.heal(hit.getDamage() / 5);
		this.prayer.drainPrayer(hit.getDamage() / 5);
		WorldTasksManager.schedule(new WorldTask() {
			public void run() {
				Player.this.setNextGraphics(new Graphics(2264));
				if (hit.getDamage() > 0) {
					World.sendProjectile(target, user, 2263, 11, 11, 20, 5, 0, 0);
				}
			}
		}, 0);
	}

	public void sendDragonFire(final Hit hit, final Entity user) {
		final Player target = this;
		if (hit.getDamage() > 0) {
			World.sendProjectile(user, this, 1166, 11, 11, 20, 5, 0, 0);
		}
		user.heal(hit.getDamage() / 200);
		WorldTasksManager.schedule(new WorldTask() {
			public void run() {
				Player.this.setNextGraphics(new Graphics(1167));
				if (hit.getDamage() > 0) {
					World.sendProjectile(target, user, 1167, 11, 11, 20, 5, 0, 0);
				}
				Player.this.getAuraManager().deactivateShield();
			}
		}, 0);
	}



	public void welcomeInterface() {
		getInterfaceManager().sendInterface(1225);
		getPackets().sendIComponentText(1225, 8, "<shad=0>Welcome to Vengium!");
		getPackets().sendIComponentText(1225, 5, "I am glad that you have decided to join my server!");
		getPackets().sendIComponentText(1225, 21, "I would like to notify you that if your gameplay screen is black upon login, just give it some time to load the cache entirely or restart your client.");
		getPackets().sendIComponentText(1225, 22, "If this persists, press the `/~ button on your keyboard and in the consol type 'displayfps'. Your screen won't be black when Cache is at 100%.");
	}

	public void handleIngoingHit(Hit hit) {
		if ((hit.getLook() != Hit.HitLook.MELEE_DAMAGE) && (hit.getLook() != Hit.HitLook.RANGE_DAMAGE) && (hit.getLook() != Hit.HitLook.MAGIC_DAMAGE)) {
			return;
		}
		if (this.invulnerable) {//you good to go test it
			//fuck
			hit.setDamage(0);
			return;
		}
		if (this.auraManager.usingPenance()) {
			int amount = (int) (hit.getDamage() * 0.2D);
			if (amount > 0) {
				this.prayer.restorePrayer(amount);
			}
		}
		Entity source = hit.getSource();
		if (source == null) {
			return;
		}
		if (this.polDelay > Utils.currentTimeMillis()) {
			hit.setDamage((int) (hit.getDamage() * 0.5D));
		}
		if ((this.prayer.hasPrayersOn()) && (hit.getDamage() != 0)) {
			if (hit.getLook() == Hit.HitLook.MAGIC_DAMAGE) {
				if (this.prayer.usingPrayer(0, 17)) {
					hit.setDamage((int) (hit.getDamage() * source.getMagePrayerMultiplier()));
				} else if (this.prayer.usingPrayer(1, 7)) {
					int deflectedDamage = (source instanceof Nex) ? 0 : (int) (hit.getDamage() * 0.1D);
					hit.setDamage((int) (hit.getDamage() * source.getMagePrayerMultiplier()));
					if (deflectedDamage > 0) {
						source.applyHit(new Hit(this, deflectedDamage, Hit.HitLook.REFLECTED_DAMAGE));
						setNextGraphics(new Graphics(2228));
						setNextAnimation(new Animation(12573));
					}
				}
			} else if (hit.getLook() == Hit.HitLook.RANGE_DAMAGE) {
				if (this.prayer.usingPrayer(0, 18)) {
					hit.setDamage((int) (hit.getDamage() * source.getRangePrayerMultiplier()));
				} else if (this.prayer.usingPrayer(1, 8)) {
					int deflectedDamage = (source instanceof Nex) ? 0 : (int) (hit.getDamage() * 0.1D);
					hit.setDamage((int) (hit.getDamage() * source.getRangePrayerMultiplier()));
					if (deflectedDamage > 0) {
						source.applyHit(new Hit(this, deflectedDamage, Hit.HitLook.REFLECTED_DAMAGE));
						setNextGraphics(new Graphics(2229));
						setNextAnimation(new Animation(12573));
					}
				}
			} else if (hit.getLook() == Hit.HitLook.MELEE_DAMAGE) {
				if (this.prayer.usingPrayer(0, 19)) {
					hit.setDamage((int) (hit.getDamage() * source.getMeleePrayerMultiplier()));
				} else if (this.prayer.usingPrayer(1, 9)) {
					int deflectedDamage = (source instanceof Nex) ? 0 : (int) (hit.getDamage() * 0.1D);
					hit.setDamage((int) (hit.getDamage() * source.getMeleePrayerMultiplier()));
					if (deflectedDamage > 0) {
						source.applyHit(new Hit(this, deflectedDamage, Hit.HitLook.REFLECTED_DAMAGE));
						setNextGraphics(new Graphics(2230));
						setNextAnimation(new Animation(12573));
					}
				}
			}
		}
		if (hit.getDamage() >= 200) {
			if (hit.getLook() == Hit.HitLook.MELEE_DAMAGE) {
				int reducedDamage = hit.getDamage() * this.combatDefinitions.getBonuses()[11] / 100;
				if (reducedDamage > 0) {
					hit.setDamage(hit.getDamage() - reducedDamage);
					hit.setSoaking(new Hit(source, reducedDamage, Hit.HitLook.ABSORB_DAMAGE));
				}
			} else if (hit.getLook() == Hit.HitLook.RANGE_DAMAGE) {
				int reducedDamage = hit.getDamage() * this.combatDefinitions.getBonuses()[13] / 100;
				if (reducedDamage > 0) {
					hit.setDamage(hit.getDamage() - reducedDamage);
					hit.setSoaking(new Hit(source, reducedDamage, Hit.HitLook.ABSORB_DAMAGE));
				}
			} else if (hit.getLook() == Hit.HitLook.MAGIC_DAMAGE) {
				int reducedDamage = hit.getDamage() * this.combatDefinitions.getBonuses()[12] / 100;
				if (reducedDamage > 0) {
					hit.setDamage(hit.getDamage() - reducedDamage);
					hit.setSoaking(new Hit(source, reducedDamage, Hit.HitLook.ABSORB_DAMAGE));
				}
			}
		}
		int shieldId = this.equipment.getShieldId();
		if (shieldId == 13742) {
			if (Utils.getRandom(100) <= 70) {
				hit.setDamage((int) (hit.getDamage() * 0.75D));
			}
		} else if (shieldId == 13740) {
			int drain = (int) (Math.ceil(hit.getDamage() * 0.3D) / 2.0D);
			if (this.prayer.getPrayerpoints() >= drain) {
				hit.setDamage((int) (hit.getDamage() * 0.7D));
				this.prayer.drainPrayer(drain);
			}
		}
		if ((this.castedVeng) && (hit.getDamage() >= 4)) {
			this.castedVeng = false;
			setNextForceTalk(new ForceTalk("Taste vengeance!"));
			source.applyHit(new Hit(this, (int) (hit.getDamage() * 0.75D), Hit.HitLook.REGULAR_DAMAGE));
		}
		if ((source instanceof Player)) {
			Player p2 = (Player) source;
			if (p2.prayer.hasPrayersOn()) {
				if (p2.prayer.usingPrayer(0, 24)) {
					int drain = hit.getDamage() / 4;
					if (drain > 0) {
						this.prayer.drainPrayer(drain);
					}
				} else {
					if (hit.getDamage() == 0) {
						return;
					}
					if (!p2.prayer.isBoostedLeech()) {
						if (hit.getLook() == Hit.HitLook.MELEE_DAMAGE) {
							if (p2.prayer.usingPrayer(1, 19)) {
								if (Utils.getRandom(4) == 0) {
									p2.prayer.increaseTurmoilBonus(this);
									p2.prayer.setBoostedLeech(true);
								}
							} else if (p2.prayer.usingPrayer(1, 1)) {
								if (Utils.getRandom(4) == 0) {
									if (p2.prayer.reachedMax(0)) {
										p2.getPackets().sendGameMessage("Your opponent has been weakened so much that your sap curse has no effect.", true);
									} else {
										p2.prayer.increaseLeechBonus(0);
										p2.getPackets().sendGameMessage("Your curse drains Attack from the enemy, boosting your Attack.", true);
									}
									p2.setNextAnimation(new Animation(12569));
									p2.setNextGraphics(new Graphics(2214));
									p2.prayer.setBoostedLeech(true);
									World.sendProjectile(p2, this, 2215, 35, 35, 20, 5, 0, 0);
									WorldTasksManager.schedule(new WorldTask() {
										public void run() {
											Player.this.setNextGraphics(new Graphics(2216));
										}
									}, 1);
								}
							} else {
								if ((p2.prayer.usingPrayer(1, 10)) && (Utils.getRandom(7) == 0)) {
									if (p2.prayer.reachedMax(3)) {
										p2.getPackets().sendGameMessage("Your opponent has been weakened so much that your leech curse has no effect.", true);
									} else {
										p2.prayer.increaseLeechBonus(3);
										p2.getPackets().sendGameMessage("Your curse drains Attack from the enemy, boosting your Attack.", true);
									}
									p2.setNextAnimation(new Animation(12575));
									p2.prayer.setBoostedLeech(true);
									World.sendProjectile(p2, this, 2231, 35, 35, 20, 5, 0, 0);
									WorldTasksManager.schedule(new WorldTask() {
										public void run() {
											Player.this.setNextGraphics(new Graphics(2232));
										}
									}, 1);
									return;
								}
								if ((p2.prayer.usingPrayer(1, 14)) && (Utils.getRandom(7) == 0)) {
									if (p2.prayer.reachedMax(7)) {
										p2.getPackets().sendGameMessage("Your opponent has been weakened so much that your leech curse has no effect.", true);
									} else {
										p2.prayer.increaseLeechBonus(7);
										p2.getPackets().sendGameMessage("Your curse drains Strength from the enemy, boosting your Strength.", true);
									}
									p2.setNextAnimation(new Animation(12575));
									p2.prayer.setBoostedLeech(true);
									World.sendProjectile(p2, this, 2248, 35, 35, 20, 5, 0, 0);
									WorldTasksManager.schedule(new WorldTask() {
										public void run() {
											Player.this.setNextGraphics(new Graphics(2250));
										}
									}, 1);
									return;
								}
							}
						}
						if (hit.getLook() == Hit.HitLook.RANGE_DAMAGE) {
							if (p2.prayer.usingPrayer(1, 2)) {
								if (Utils.getRandom(4) == 0) {
									if (p2.prayer.reachedMax(1)) {
										p2.getPackets().sendGameMessage("Your opponent has been weakened so much that your sap curse has no effect.", true);
									} else {
										p2.prayer.increaseLeechBonus(1);
										p2.getPackets().sendGameMessage("Your curse drains Range from the enemy, boosting your Range.", true);
									}
									p2.setNextAnimation(new Animation(12569));
									p2.setNextGraphics(new Graphics(2217));
									p2.prayer.setBoostedLeech(true);
									World.sendProjectile(p2, this, 2218, 35, 35, 20, 5, 0, 0);
									WorldTasksManager.schedule(new WorldTask() {
										public void run() {
											Player.this.setNextGraphics(new Graphics(2219));
										}
									}, 1);
								}
							} else if ((p2.prayer.usingPrayer(1, 11)) && (Utils.getRandom(7) == 0)) {
								if (p2.prayer.reachedMax(4)) {
									p2.getPackets().sendGameMessage("Your opponent has been weakened so much that your leech curse has no effect.", true);
								} else {
									p2.prayer.increaseLeechBonus(4);
									p2.getPackets().sendGameMessage("Your curse drains Range from the enemy, boosting your Range.", true);
								}
								p2.setNextAnimation(new Animation(12575));
								p2.prayer.setBoostedLeech(true);
								World.sendProjectile(p2, this, 2236, 35, 35, 20, 5, 0, 0);
								WorldTasksManager.schedule(new WorldTask() {
									public void run() {
										Player.this.setNextGraphics(new Graphics(2238));
									}
								});
								return;
							}
						}
						if (hit.getLook() == Hit.HitLook.MAGIC_DAMAGE) {
							if (p2.prayer.usingPrayer(1, 3)) {
								if (Utils.getRandom(4) == 0) {
									if (p2.prayer.reachedMax(2)) {
										p2.getPackets().sendGameMessage("Your opponent has been weakened so much that your sap curse has no effect.", true);
									} else {
										p2.prayer.increaseLeechBonus(2);
										p2.getPackets().sendGameMessage("Your curse drains Magic from the enemy, boosting your Magic.", true);
									}
									p2.setNextAnimation(new Animation(12569));
									p2.setNextGraphics(new Graphics(2220));
									p2.prayer.setBoostedLeech(true);
									World.sendProjectile(p2, this, 2221, 35, 35, 20, 5, 0, 0);
									WorldTasksManager.schedule(new WorldTask() {
										public void run() {
											Player.this.setNextGraphics(new Graphics(2222));
										}
									}, 1);
								}
							} else if ((p2.prayer.usingPrayer(1, 12)) && (Utils.getRandom(7) == 0)) {
								if (p2.prayer.reachedMax(5)) {
									p2.getPackets().sendGameMessage("Your opponent has been weakened so much that your leech curse has no effect.", true);
								} else {
									p2.prayer.increaseLeechBonus(5);
									p2.getPackets().sendGameMessage("Your curse drains Magic from the enemy, boosting your Magic.", true);
								}
								p2.setNextAnimation(new Animation(12575));
								p2.prayer.setBoostedLeech(true);
								World.sendProjectile(p2, this, 2240, 35, 35, 20, 5, 0, 0);
								WorldTasksManager.schedule(new WorldTask() {
									public void run() {
										Player.this.setNextGraphics(new Graphics(2242));
									}
								}, 1);
								return;
							}
						}
						if ((p2.prayer.usingPrayer(1, 13)) && (Utils.getRandom(10) == 0)) {
							if (p2.prayer.reachedMax(6)) {
								p2.getPackets().sendGameMessage("Your opponent has been weakened so much that your leech curse has no effect.", true);
							} else {
								p2.prayer.increaseLeechBonus(6);
								p2.getPackets().sendGameMessage("Your curse drains Defence from the enemy, boosting your Defence.", true);
							}
							p2.setNextAnimation(new Animation(12575));
							p2.prayer.setBoostedLeech(true);
							World.sendProjectile(p2, this, 2244, 35, 35, 20, 5, 0, 0);
							WorldTasksManager.schedule(new WorldTask() {
								public void run() {
									Player.this.setNextGraphics(new Graphics(2246));
								}
							}, 1);
							return;
						}
						if ((p2.prayer.usingPrayer(1, 15)) && (Utils.getRandom(10) == 0)) {
							if (getRunEnergy() <= 0) {
								p2.getPackets().sendGameMessage("Your opponent has been weakened so much that your leech curse has no effect.", true);
							} else {
								p2.setRunEnergy(p2.getRunEnergy() > 90 ? 100 : p2.getRunEnergy() + 10);
								setRunEnergy(p2.getRunEnergy() > 10 ? getRunEnergy() - 10 : 0);
							}
							p2.setNextAnimation(new Animation(12575));
							p2.prayer.setBoostedLeech(true);
							World.sendProjectile(p2, this, 2256, 35, 35, 20, 5, 0, 0);
							WorldTasksManager.schedule(new WorldTask() {
								public void run() {
									Player.this.setNextGraphics(new Graphics(2258));
								}
							}, 1);
							return;
						}
						if ((p2.prayer.usingPrayer(1, 16)) && (Utils.getRandom(10) == 0)) {
							if (this.combatDefinitions.getSpecialAttackPercentage() <= 0) {
								p2.getPackets().sendGameMessage("Your opponent has been weakened so much that your leech curse has no effect.", true);
							} else {
								p2.combatDefinitions.restoreSpecialAttack();
								this.combatDefinitions.desecreaseSpecialAttack(10);
							}
							p2.setNextAnimation(new Animation(12575));
							p2.prayer.setBoostedLeech(true);
							World.sendProjectile(p2, this, 2252, 35, 35, 20, 5, 0, 0);
							WorldTasksManager.schedule(new WorldTask() {
								public void run() {
									Player.this.setNextGraphics(new Graphics(2254));
								}
							}, 1);
							return;
						}
						if ((p2.prayer.usingPrayer(1, 4)) && (Utils.getRandom(10) == 0)) {
							p2.setNextAnimation(new Animation(12569));
							p2.setNextGraphics(new Graphics(2223));
							p2.prayer.setBoostedLeech(true);
							if (this.combatDefinitions.getSpecialAttackPercentage() <= 0) {
								p2.getPackets().sendGameMessage("Your opponent has been weakened so much that your sap curse has no effect.", true);
							} else {
								this.combatDefinitions.desecreaseSpecialAttack(10);
							}
							World.sendProjectile(p2, this, 2224, 35, 35, 20, 5, 0, 0);
							WorldTasksManager.schedule(new WorldTask() {
								public void run() {
									Player.this.setNextGraphics(new Graphics(2225));
								}
							}, 1);
						}
					}
				}
			}
		} else {
			NPC n = (NPC) source;
			if (n.getId() == 13448) {
				sendSoulSplit(hit, n);
			}
		}
	}

	public void sendDeath(final Entity source) {
		if ((this.prayer.hasPrayersOn()) && (getTemporaryAttributtes().get("startedDuel") != Boolean.TRUE)) {
			if (this.prayer.usingPrayer(0, 22)) {
				setNextGraphics(new Graphics(437));
				final Player target = this;
				if (isAtMultiArea()) {
					for (Iterator<?> localIterator1 = getMapRegionsIds().iterator(); localIterator1.hasNext();) {
						int regionId = ((Integer) localIterator1.next()).intValue();
						List<Integer> playersIndexes = World.getRegion(regionId).getPlayerIndexes();
						Player player;
						if (playersIndexes != null) {
							for (Iterator<Integer> localIterator2 = playersIndexes.iterator(); localIterator2.hasNext();) {
								int playerIndex = ((Integer) localIterator2.next()).intValue();
								player = (Player) World.getPlayers().get(playerIndex);
								if ((player != null) && (player.hasStarted()) && (!player.isDead()) && (!player.hasFinished()) && (player.withinDistance(this, 1)) && (player.isCanPvp())) {
									if (target.getControlerManager().canHit(player)) {
										player.applyHit(new Hit(target, Utils.getRandom((int) (this.skills.getLevelForXp(5) * 2.5D)), Hit.HitLook.REGULAR_DAMAGE));
									}
								}
							}
						}
						List<Integer> npcsIndexes = World.getRegion(regionId).getNPCsIndexes();
						if (npcsIndexes != null) {
							for (int npcIndex : npcsIndexes) {
								NPC npc = World.getNPCs().get(npcIndex);
								if (npc == null || npc.isDead() || npc.hasFinished() || !npc.withinDistance(this, 1) || !npc.getDefinitions().hasAttackOption() || !target.getControlerManager().canHit(npc))
									continue;
								npc.applyHit(new Hit(target, Utils.getRandom((int) (skills.getLevelForXp(Skills.PRAYER) * 2.5)), HitLook.REGULAR_DAMAGE));
							}
						}
					}
				} else if ((source != null) && (source != this) && (!source.isDead()) && (!source.hasFinished()) && (source.withinDistance(this, 1))) {
					source.applyHit(new Hit(target, Utils.getRandom((int) (this.skills.getLevelForXp(5) * 2.5D)), Hit.HitLook.REGULAR_DAMAGE));
				}
				WorldTasksManager.schedule(new WorldTask() {
					public void run() {
						World.sendGraphics(target, new Graphics(438), new WorldTile(target.getX() - 1, target.getY(), target.getPlane()));
						World.sendGraphics(target, new Graphics(438), new WorldTile(target.getX() + 1, target.getY(), target.getPlane()));
						World.sendGraphics(target, new Graphics(438), new WorldTile(target.getX(), target.getY() - 1, target.getPlane()));
						World.sendGraphics(target, new Graphics(438), new WorldTile(target.getX(), target.getY() + 1, target.getPlane()));
						World.sendGraphics(target, new Graphics(438), new WorldTile(target.getX() - 1, target.getY() - 1, target.getPlane()));
						World.sendGraphics(target, new Graphics(438), new WorldTile(target.getX() - 1, target.getY() + 1, target.getPlane()));
						World.sendGraphics(target, new Graphics(438), new WorldTile(target.getX() + 1, target.getY() - 1, target.getPlane()));
						World.sendGraphics(target, new Graphics(438), new WorldTile(target.getX() + 1, target.getY() + 1, target.getPlane()));
					}
				});
			} else if (this.prayer.usingPrayer(1, 17)) {
				World.sendProjectile(this, new WorldTile(getX() + 2, getY() + 2, getPlane()), 2260, 24, 0, 41, 35, 30, 0);
				World.sendProjectile(this, new WorldTile(getX() + 2, getY(), getPlane()), 2260, 41, 0, 41, 35, 30, 0);
				World.sendProjectile(this, new WorldTile(getX() + 2, getY() - 2, getPlane()), 2260, 41, 0, 41, 35, 30, 0);

				World.sendProjectile(this, new WorldTile(getX() - 2, getY() + 2, getPlane()), 2260, 41, 0, 41, 35, 30, 0);
				World.sendProjectile(this, new WorldTile(getX() - 2, getY(), getPlane()), 2260, 41, 0, 41, 35, 30, 0);
				World.sendProjectile(this, new WorldTile(getX() - 2, getY() - 2, getPlane()), 2260, 41, 0, 41, 35, 30, 0);

				World.sendProjectile(this, new WorldTile(getX(), getY() + 2, getPlane()), 2260, 41, 0, 41, 35, 30, 0);
				World.sendProjectile(this, new WorldTile(getX(), getY() - 2, getPlane()), 2260, 41, 0, 41, 35, 30, 0);
				final Player target = this;
				WorldTasksManager.schedule(new WorldTask() {
					public void run() {
						Player.this.setNextGraphics(new Graphics(2259));
						if (Player.this.isAtMultiArea()) {
							for (Iterator<?> localIterator1 = Player.this.getMapRegionsIds().iterator(); localIterator1.hasNext();) {
								int regionId = ((Integer) localIterator1.next()).intValue();
								List<Integer> playersIndexes = World.getRegion(regionId).getPlayerIndexes();
								Player player;
								if (playersIndexes != null) {
									for (Iterator<Integer> localIterator2 = playersIndexes.iterator(); localIterator2.hasNext();) {
										int playerIndex = ((Integer) localIterator2.next()).intValue();
										player = (Player) World.getPlayers().get(playerIndex);
										if ((player != null) && (player.hasStarted()) && (!player.isDead()) && (!player.hasFinished()) && (player.isCanPvp())) {
											if (player.withinDistance(target, 2)) {
												if (target.getControlerManager().canHit(player)) {
													player.applyHit(new Hit(target, Utils.getRandom(Player.this.skills.getLevelForXp(5) * 3), Hit.HitLook.REGULAR_DAMAGE));
												}
											}
										}
									}
								}
							}

						} else if ((source != null) && (source != target) && (!source.isDead()) && (!source.hasFinished()) && (source.withinDistance(target, 2))) {
							source.applyHit(new Hit(target, Utils.getRandom(Player.this.skills.getLevelForXp(5) * 3), Hit.HitLook.REGULAR_DAMAGE));
						}
						World.sendGraphics(target, new Graphics(2260), new WorldTile(Player.this.getX() + 2, Player.this.getY() + 2, Player.this.getPlane()));
						World.sendGraphics(target, new Graphics(2260), new WorldTile(Player.this.getX() + 2, Player.this.getY(), Player.this.getPlane()));
						World.sendGraphics(target, new Graphics(2260), new WorldTile(Player.this.getX() + 2, Player.this.getY() - 2, Player.this.getPlane()));

						World.sendGraphics(target, new Graphics(2260), new WorldTile(Player.this.getX() - 2, Player.this.getY() + 2, Player.this.getPlane()));
						World.sendGraphics(target, new Graphics(2260), new WorldTile(Player.this.getX() - 2, Player.this.getY(), Player.this.getPlane()));
						World.sendGraphics(target, new Graphics(2260), new WorldTile(Player.this.getX() - 2, Player.this.getY() - 2, Player.this.getPlane()));

						World.sendGraphics(target, new Graphics(2260), new WorldTile(Player.this.getX(), Player.this.getY() + 2, Player.this.getPlane()));
						World.sendGraphics(target, new Graphics(2260), new WorldTile(Player.this.getX(), Player.this.getY() - 2, Player.this.getPlane()));

						World.sendGraphics(target, new Graphics(2260), new WorldTile(Player.this.getX() + 1, Player.this.getY() + 1, Player.this.getPlane()));
						World.sendGraphics(target, new Graphics(2260), new WorldTile(Player.this.getX() + 1, Player.this.getY() - 1, Player.this.getPlane()));
						World.sendGraphics(target, new Graphics(2260), new WorldTile(Player.this.getX() - 1, Player.this.getY() + 1, Player.this.getPlane()));
						World.sendGraphics(target, new Graphics(2260), new WorldTile(Player.this.getX() - 1, Player.this.getY() - 1, Player.this.getPlane()));
					}
				});
			}
		}
		setNextAnimation(new Animation(-1));
		if (!this.controlerManager.sendDeath()) {
			return;
		}
		lock(7L);
		stopAll();
		if (this.familiar != null) {
			this.familiar.sendDeath(this);
		}
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			public void run() {
				if (this.loop == 0) {
					Player.this.setNextAnimation(new Animation(836));
				} else if (this.loop == 1) {
					Player.this.getPackets().sendGameMessage("Oh dear, you have died.");
					if ((source instanceof Player)) {
						Player killer = (Player) source;
						killer.setAttackedByDelay(4L);
					}
				} else if (this.loop == 3) {
					Player.this.controlerManager.startControler("DeathEvent", new Object[0]);
				} else if (this.loop == 4) {
					Player.this.getPackets().sendMusicEffect(90);
					stop();
				}
				this.loop += 1;
			}
		}, 0, 1);
	}

	public void sendItemsOnDeath(Player killer) {
		if (this.rights == 2) {
			return;
		}
		this.charges.die();
		this.auraManager.removeAura();
		CopyOnWriteArrayList<Item> containedItems = new CopyOnWriteArrayList<Item>();
		for (int i = 0; i < 14; i++) {
			if ((this.equipment.getItem(i) != null) && (this.equipment.getItem(i).getId() != -1) && (this.equipment.getItem(i).getAmount() != -1)) {
				containedItems.add(new Item(this.equipment.getItem(i).getId(), this.equipment.getItem(i).getAmount()));
			}
		}
		for (int i = 0; i < 28; i++) {
			if ((this.inventory.getItem(i) != null) && (this.inventory.getItem(i).getId() != -1) && (this.inventory.getItem(i).getAmount() != -1)) {
				containedItems.add(new Item(getInventory().getItem(i).getId(), getInventory().getItem(i).getAmount()));
			}
		}
		if (containedItems.isEmpty()) {
			return;
		}
		int keptAmount = 0;
		if ((!(this.controlerManager.getControler() instanceof CorpBeastControler)) && (!(this.controlerManager.getControler() instanceof CrucibleControler))) {
			keptAmount = hasSkull() ? 0 : 3;
			if ((this.prayer.usingPrayer(0, 10)) || (this.prayer.usingPrayer(1, 0))) {
				keptAmount++;
			}
		}
		if ((this.member) && (Utils.random(2) == 0)) {
			keptAmount++;
		}
		CopyOnWriteArrayList<Item> keptItems = new CopyOnWriteArrayList<Item>();
		Item lastItem = new Item(1, 1);
		Item item;
		for (int i = 0; i < keptAmount; i++) {
			for (Iterator<Item> localIterator = containedItems.iterator(); localIterator.hasNext();) {
				item = (Item) localIterator.next();
				int price = item.getDefinitions().getValue();
				if (price >= lastItem.getDefinitions().getValue()) {
					lastItem = item;
				}
			}
			keptItems.add(lastItem);
			containedItems.remove(lastItem);
			lastItem = new Item(1, 1);
		}
		this.inventory.reset();
		this.equipment.reset();
		for (Item item1 : keptItems) {
			getInventory().addItem(item1);
		}
		for (Item item1 : containedItems) {
			World.addGroundItem(item1, getLastWorldTile(), killer == null ? this : killer, true, 60, 0);
		}
	}

	public void increaseKillCount(Player killed) {
		killed.deathCount += 1;
		PkRank.checkRank(killed);
		if (killed.getSession().getIP().equals(getSession().getIP())) {
			return;
		}
		this.killCount += 1;
		getPackets().sendGameMessage("<col=ff0000>You have killed " + killed.getDisplayName() + ", you have now " + this.killCount + " kills.");
		PkRank.checkRank(this);
	}

	public void increaseKillCountSafe(Player killed) {
		PkRank.checkRank(killed);
		if (killed.getSession().getIP().equals(getSession().getIP())) {
			return;
		}
		this.killCount += 1;
		getPackets().sendGameMessage("<col=ff0000>You have killed " + killed.getDisplayName() + ", you have now " + this.killCount + " kills.");
		PkRank.checkRank(this);
	}

	public void sendRandomJail(Player p) {
		p.resetWalkSteps();
		switch (Utils.getRandom(6)) {
		case 0:
			p.setNextWorldTile(new WorldTile(2669, 10387, 0));
			break;
		case 1:
			p.setNextWorldTile(new WorldTile(2669, 10383, 0));
			break;
		case 2:
			p.setNextWorldTile(new WorldTile(2669, 10379, 0));
			break;
		case 3:
			p.setNextWorldTile(new WorldTile(2673, 10379, 0));
			break;
		case 4:
			p.setNextWorldTile(new WorldTile(2673, 10385, 0));
			break;
		case 5:
			p.setNextWorldTile(new WorldTile(2677, 10387, 0));
			break;
		case 6:
			p.setNextWorldTile(new WorldTile(2677, 10383, 0));
		}
	}

	public int getSize() {
		return this.appearence.getSize();
	}

	public boolean isCanPvp() {
		return this.canPvp;
	}

	public void setCanPvp(boolean canPvp) {
		this.canPvp = canPvp;
		this.appearence.generateAppearenceData();
		getPackets().sendPlayerOption(canPvp ? "Attack" : "null", 1, true);
		getPackets().sendPlayerUnderNPCPriority(canPvp);
	}

	public Prayer getPrayer() {
		return this.prayer;
	}

	public long getLockDelay() {
		return this.lockDelay;
	}

	public boolean isLocked() {
		return this.lockDelay >= Utils.currentTimeMillis();
	}

	public void lock() {
		this.lockDelay = 9223372036854775807L;
	}

	public void lock(long time) {
		this.lockDelay = (Utils.currentTimeMillis() + time * 600L);
	}

	public void unlock() {
		this.lockDelay = 0L;
	}

	public void useStairs(int emoteId, WorldTile dest, int useDelay, int totalDelay) {
		useStairs(emoteId, dest, useDelay, totalDelay, null);
	}

	public void useStairs(int emoteId, final WorldTile dest, int useDelay, int totalDelay, final String message) {
		stopAll();
		lock(totalDelay);
		if (emoteId != -1) {
			setNextAnimation(new Animation(emoteId));
		}
		if (useDelay == 0) {
			setNextWorldTile(dest);
		} else {
			WorldTasksManager.schedule(new WorldTask() {
				public void run() {
					if (Player.this.isDead()) {
						return;
					}
					Player.this.setNextWorldTile(dest);
					if (message != null) {
						Player.this.getPackets().sendGameMessage(message);
					}
				}
			}, useDelay - 1);
		}
	}

	public Bank getBank() {
		return this.bank;
	}

	public ControlerManager getControlerManager() {
		return this.controlerManager;
	}

	public void switchMouseButtons() {
		this.mouseButtons = (!this.mouseButtons);
		refreshMouseButtons();
	}

	public void switchAllowChatEffects() {
		this.allowChatEffects = (!this.allowChatEffects);
		refreshAllowChatEffects();
	}

	public void refreshAllowChatEffects() {
		getPackets().sendConfig(171, this.allowChatEffects ? 0 : 1);
	}

	public void refreshMouseButtons() {
		getPackets().sendConfig(170, this.mouseButtons ? 0 : 1);
	}

	public void refreshPrivateChatSetup() {
		getPackets().sendConfig(287, this.privateChatSetup);
	}

	public void refreshOtherChatsSetup() {
		int value = this.friendChatSetup << 6;
		getPackets().sendConfig(1438, value);
	}

	public void setPrivateChatSetup(int privateChatSetup) {
		this.privateChatSetup = privateChatSetup;
	}

	public void setFriendChatSetup(int friendChatSetup) {
		this.friendChatSetup = friendChatSetup;
	}

	public int getPrivateChatSetup() {
		return this.privateChatSetup;
	}

	public boolean isForceNextMapLoadRefresh() {
		return this.forceNextMapLoadRefresh;
	}

	public void setForceNextMapLoadRefresh(boolean forceNextMapLoadRefresh) {
		this.forceNextMapLoadRefresh = forceNextMapLoadRefresh;
	}

	public FriendsIgnores getFriendsIgnores() {
		return this.friendsIgnores;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void addPotDelay(long time) {
		this.potDelay = (time + Utils.currentTimeMillis());
	}

	public long getPotDelay() {
		return this.potDelay;
	}

	public void addFoodDelay(long time) {
		this.foodDelay = (time + Utils.currentTimeMillis());
	}

	public long getFoodDelay() {
		return this.foodDelay;
	}

	public long getBoneDelay() {
		return this.boneDelay;
	}

	public long getAshDelay() {
		return this.ashDelay;
	}

	public void addBoneDelay(long time) {
		this.boneDelay = (time + Utils.currentTimeMillis());
	}

	public void addAshDelay(long time) {
		this.ashDelay = (time + Utils.currentTimeMillis());
	}

	public void addPoisonImmune(long time) {
		this.poisonImmune = (time + Utils.currentTimeMillis());
		getPoison().reset();
	}

	public long getPoisonImmune() {
		return this.poisonImmune;
	}

	public void addFireImmune(long time) {
		this.fireImmune = (time + Utils.currentTimeMillis());
	}

	public long getFireImmune() {
		return this.fireImmune;
	}

	public void heal(int ammount, int extra) {
		super.heal(ammount, extra);
		refreshHitPoints();
	}

	public MusicsManager getMusicsManager() {
		return this.musicsManager;
	}

	public HintIconsManager getHintIconsManager() {
		return this.hintIconsManager;
	}

	public boolean isCastVeng() {
		return this.castedVeng;
	}

	public void setCastVeng(boolean castVeng) {
		this.castedVeng = castVeng;
	}

	public int getKillCount() {
		return this.killCount;
	}

	public int getBarrowsKillCount() {
		return this.barrowsKillCount;
	}

	public int setBarrowsKillCount(int barrowsKillCount) {
		return this.barrowsKillCount = barrowsKillCount;
	}

	public int setKillCount(int killCount) {
		return this.killCount = killCount;
	}

	public int getDeathCount() {
		return this.deathCount;
	}

	public int setDeathCount(int deathCount) {
		return this.deathCount = deathCount;
	}

	public void setCloseInterfacesEvent(Runnable closeInterfacesEvent) {
		this.closeInterfacesEvent = closeInterfacesEvent;
	}

	public long getMuted() {
		return this.muted;
	}

	public void setMuted(long muted) {
		this.muted = muted;
	}

	public long getJailed() {
		return this.jailed;
	}

	public void setJailed(long jailed) {
		this.jailed = jailed;
	}

	public boolean isPermJailed() {
		return this.permJailed;
	}

	public void setPermJailed(boolean permJailed) {
		this.permJailed = permJailed;
	}

	public boolean isPermMuted() {
		return this.permMuted;
	}

	public void setPermMuted(boolean permMuted) {
		this.permMuted = permMuted;
	}

	public boolean isPermBanned() {
		return this.permBanned;
	}

	public void setPermBanned(boolean permBanned) {
		this.permBanned = permBanned;
	}

	public long getBanned() {
		return this.banned;
	}

	public void setBanned(long banned) {
		this.banned = banned;
	}

	public ChargesManager getCharges() {
		return this.charges;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean[] getKilledBarrowBrothers() {
		return this.killedBarrowBrothers;
	}

	public void setHiddenBrother(int hiddenBrother) {
		this.hiddenBrother = hiddenBrother;
	}

	public int getHiddenBrother() {
		return this.hiddenBrother;
	}

	public void resetBarrows() {
		this.hiddenBrother = -1;
		this.killedBarrowBrothers = new boolean[7];
		this.barrowsKillCount = 0;
	}

	public int getVotes() {
		return this.votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public boolean isMember() {
		return (this.member) || (this.memberTill > Utils.currentTimeMillis());
	}

	public boolean isLifetime() {
		return this.member;
	}

	public void setMember(boolean member) {
		this.member = member;
	}

	public int getWGuildTokens() {
		return this.wGuildTokens;
	}

	public void setWGuildTokens(int tokens) {
		this.wGuildTokens = tokens;
	}

	public boolean inClopsRoom() {
		return this.inClops;
	}

	public void setInClopsRoom(boolean in) {
		this.inClops = in;
	}

	public void makeMember(int months) {
		if (this.memberTill < Utils.currentTimeMillis()) {
			this.memberTill = Utils.currentTimeMillis();
		}
		Date date = new Date(this.memberTill);
		date.setMonth(date.getMonth() + months);
		this.memberTill = date.getTime();
	}

	public String getMemberTill() {
		return (this.member ? "never" : new Date(this.memberTill).toGMTString()) + ".";
	}

	public String getRecovQuestion() {
		return this.recovQuestion;
	}

	public void setRecovQuestion(String recovQuestion) {
		this.recovQuestion = recovQuestion;
	}

	public String getRecovAnswer() {
		return this.recovAnswer;
	}

	public void setRecovAnswer(String recovAnswer) {
		this.recovAnswer = recovAnswer;
	}

	public String getLastMsg() {
		return this.lastMsg;
	}

	public void setLastMsg(String lastMsg) {
		this.lastMsg = lastMsg;
	}

	public int[] getPouches() {
		return this.pouches;
	}

	public EmotesManager getEmotesManager() {
		return this.emotesManager;
	}

	public String getLastIP() {
		return this.lastIP;
	}

	public String getLastHostname() {
		try {
			InetAddress addr = InetAddress.getByName(getLastIP());
			return addr.getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}

	public PriceCheckManager getPriceCheckManager() {
		return this.priceCheckManager;
	}

	public void setPestPoints(int pestPoints) {
		this.pestPoints = pestPoints;
	}

	public int getPestPoints() {
		return this.pestPoints;
	}

	public boolean isUpdateMovementType() {
		return this.updateMovementType;
	}

	public long getLastPublicMessage() {
		return this.lastPublicMessage;
	}

	public void setLastPublicMessage(long lastPublicMessage) {
		this.lastPublicMessage = lastPublicMessage;
	}

	public CutscenesManager getCutscenesManager() {
		return this.cutscenesManager;
	}

	public void kickPlayerFromFriendsChannel(String name) {
		if (this.currentFriendChat == null) {
			return;
		}
		this.currentFriendChat.kickPlayerFromChat(this, name);
	}

	public void sendFriendsChannelMessage(String message) {
		if (this.currentFriendChat == null) {
			return;
		}
		this.currentFriendChat.sendMessage(this, message);
	}

	public void sendFriendsChannelQuickMessage(QuickChatMessage message) {
		if (this.currentFriendChat == null) {
			return;
		}
		this.currentFriendChat.sendQuickMessage(this, message);
	}

	public void sendPublicChatMessage(PublicChatMessage message) {
		for (Iterator<?> localIterator1 = getMapRegionsIds().iterator(); localIterator1.hasNext();) {
			int regionId = ((Integer) localIterator1.next()).intValue();
			List<Integer> playersIndexes = World.getRegion(regionId).getPlayerIndexes();
			if (playersIndexes != null) {
				for (Integer playerIndex : playersIndexes) {
					Player p = (Player) World.getPlayers().get(playerIndex.intValue());
					if ((p != null) && (p.hasStarted()) && (!p.hasFinished()) && (p.getLocalPlayerUpdate().getLocalPlayers()[getIndex()] != null)) {
						p.getPackets().sendPublicMessage(this, message);
					}
				}
			}
		}
	}

	public int[] getCompletionistCapeCustomized() {
		return this.completionistCapeCustomized;
	}

	public void setCompletionistCapeCustomized(int[] skillcapeCustomized) {
		this.completionistCapeCustomized = skillcapeCustomized;
	}

	public int[] getMaxedCapeCustomized() {
		return this.maxedCapeCustomized;
	}

	public void setMaxedCapeCustomized(int[] maxedCapeCustomized) {
		this.maxedCapeCustomized = maxedCapeCustomized;
	}

	public void setSkullId(int skullId) {
		this.skullId = skullId;
	}

	public int getSkullId() {
		return this.skullId;
	}

	public boolean isFilterGame() {
		return this.filterGame;
	}

	public void setFilterGame(boolean filterGame) {
		this.filterGame = filterGame;
	}

	public void addLogicPacketToQueue(LogicPacket packet) {
		for (LogicPacket p : this.logicPackets) {
			if (p.getId() == packet.getId()) {
				this.logicPackets.remove(p);
				break;
			}
		}
		this.logicPackets.add(packet);
	}

	public DominionTower getDominionTower() {
		return this.dominionTower;
	}

	public void setPrayerRenewalDelay(int delay) {
		this.prayerRenewalDelay = delay;
	}

	public int getOverloadDelay() {
		return this.overloadDelay;
	}

	public void setOverloadDelay(int overloadDelay) {
		this.overloadDelay = overloadDelay;
	}

	public Trade getTrade() {
		return this.trade;
	}

	public Teleto getTeleto() {
		return this.teleto;
	}

	public void setTeleBlockDelay(long teleDelay) {
		getTemporaryAttributtes().put("TeleBlocked", Long.valueOf(teleDelay + Utils.currentTimeMillis()));
	}

	public long getTeleBlockDelay() {
		Long teleblock = (Long) getTemporaryAttributtes().get("TeleBlocked");
		if (teleblock == null) {
			return 0L;
		}
		return teleblock.longValue();
	}

	public void setPrayerDelay(long teleDelay) {
		getTemporaryAttributtes().put("PrayerBlocked", Long.valueOf(teleDelay + Utils.currentTimeMillis()));
		this.prayer.closeAllPrayers();
	}

	public long getPrayerDelay() {
		Long teleblock = (Long) getTemporaryAttributtes().get("PrayerBlocked");
		if (teleblock == null) {
			return 0L;
		}
		return teleblock.longValue();
	}

	public Familiar getFamiliar() {
		return this.familiar;
	}

	public void setFamiliar(Familiar familiar) {
		this.familiar = familiar;
	}

	public FriendChatsManager getCurrentFriendChat() {
		return this.currentFriendChat;
	}

	public void setCurrentFriendChat(FriendChatsManager currentFriendChat) {
		this.currentFriendChat = currentFriendChat;
	}

	public String getCurrentFriendChatOwner() {
		return this.currentFriendChatOwner;
	}

	public void setCurrentFriendChatOwner(String currentFriendChatOwner) {
		this.currentFriendChatOwner = currentFriendChatOwner;
	}

	public int getSummoningLeftClickOption() {
		return this.summoningLeftClickOption;
	}

	public void setSummoningLeftClickOption(int summoningLeftClickOption) {
		this.summoningLeftClickOption = summoningLeftClickOption;
	}

	public boolean canSpawn() {
		if ((Wilderness.isAtWild(this)) || ((getControlerManager().getControler() instanceof FightPitsArena)) || ((getControlerManager().getControler() instanceof CorpBeastControler)) || ((getControlerManager().getControler() instanceof PestControlLobby)) || ((getControlerManager().getControler() instanceof PestControlGame)) || ((getControlerManager().getControler() instanceof ZGDControler)) || ((getControlerManager().getControler() instanceof GodWars)) || ((getControlerManager().getControler() instanceof DTControler)) || ((getControlerManager().getControler() instanceof DuelArena)) || ((getControlerManager().getControler() instanceof CastleWarsPlaying)) || ((getControlerManager().getControler() instanceof CastleWarsWaiting)) || ((getControlerManager().getControler() instanceof FightCaves)) || ((getControlerManager().getControler() instanceof FightKiln)) || (FfaZone.inPvpArea(this)) || ((getControlerManager().getControler() instanceof NomadsRequiem)) || ((getControlerManager().getControler() instanceof QueenBlackDragonController)) || ((getControlerManager().getControler() instanceof WarControler))) {
			return false;
		}
		if ((getControlerManager().getControler() instanceof CrucibleControler)) {
			CrucibleControler controler = (CrucibleControler) getControlerManager().getControler();
			return !controler.isInside();
		}
		return true;
	}

	public long getPolDelay() {
		return this.polDelay;
	}

	public void addPolDelay(long delay) {
		this.polDelay = (delay + Utils.currentTimeMillis());
	}

	public void setPolDelay(long delay) {
		this.polDelay = delay;
	}

	public List<Integer> getSwitchItemCache() {
		return this.switchItemCache;
	}

	public AuraManager getAuraManager() {
		return this.auraManager;
	}

	public boolean allowsProfanity() {
		return this.allowsProfanity;
	}

	public void switchProfanityFilter() {
		this.profanityFilter = (!this.profanityFilter);
		refreshProfanityFilter();
	}

	public void refreshProfanityFilter() {
		getVarsManager().sendVarBit(8780, this.profanityFilter ? 0 : 1);
	}

	public void allowsProfanity(boolean allowsProfanity) {
		this.allowsProfanity = allowsProfanity;
	}

	public int getMovementType() {
		if (getTemporaryMoveType() != -1) {
			return getTemporaryMoveType();
		}
		return getRun() ? 2 : 1;
	}

	public List<String> getOwnedObjectManagerKeys() {
		if (this.ownedObjectsManagerKeys == null) {
			this.ownedObjectsManagerKeys = new LinkedList<String>();
		}
		return this.ownedObjectsManagerKeys;
	}

	public boolean hasInstantSpecial(int weaponId) {
		switch (weaponId) {
		case 35:
		case 1377:
		case 4153:
		case 8280:
		case 13472:
		case 14632:
		case 15486:
		case 22207:
		case 22209:
		case 22211:
		case 22213:
			return true;
		}
		return false;
	}

	public boolean hasInstantSpecialShield(int shieldId) {
		switch (shieldId) {
		case 11283:
			return true;
		}
		return false;
	}

	public void performInstantSpecialShield(int shieldId) {
		Player p2 = (Player) this.target;
	}

	public void performInstantSpecial(int weaponId) {
		int specAmt = PlayerCombat.getSpecialAmmount(weaponId);
		if (this.combatDefinitions.hasRingOfVigour()) {
			specAmt = (int) (specAmt * 0.9D);
		}
		if (this.combatDefinitions.getSpecialAttackPercentage() < specAmt) {
			getPackets().sendGameMessage("You don't have enough power left.");
			this.combatDefinitions.desecreaseSpecialAttack(0);
			return;
		}
		if (((getSwitchItemCache().size() > 0) && (getRights() == 4)) || (getRights() == 3)) {
			getCombatDefinitions().resetSpecialAttack();
			return;
		}
		if ((getSwitchItemCache().size() > 0) && (getRights() <= 2)) {
			ButtonHandler.submitSpecialRequest(this);
			return;
		}
		switch (weaponId) {
		case 4153:
			this.combatDefinitions.setInstantAttack(true);
			this.combatDefinitions.switchUsingSpecialAttack();
			Entity target = (Entity) getTemporaryAttributtes().get("last_target");
			if ((target != null) && (target.getTemporaryAttributtes().get("last_attacker") == this) && ((!(getActionManager().getAction() instanceof PlayerCombat)) || (((PlayerCombat) getActionManager().getAction()).getTarget() != target))) {
				getActionManager().setAction(new PlayerCombat(target));
			}
			break;
		case 1377:
		case 13472:
			setNextAnimation(new Animation(1056));
			setNextGraphics(new Graphics(246));
			int defence = (int) (this.skills.getLevelForXp(1) * 0.9D);
			int attack = (int) (this.skills.getLevelForXp(0) * 0.9D);
			int range = (int) (this.skills.getLevelForXp(4) * 0.9D);
			int magic = (int) (this.skills.getLevelForXp(6) * 0.9D);
			int strength = (int) (this.skills.getLevelForXp(2) * 1.2D);
			this.skills.set(1, defence);
			this.skills.set(0, attack);
			this.skills.set(4, range);
			this.skills.set(6, magic);
			this.skills.set(2, strength);
			this.combatDefinitions.desecreaseSpecialAttack(specAmt);
			break;
		case 35:
			setNextAnimation(new Animation(1057));
			setNextGraphics(new Graphics(247));
		case 8280:
		case 14632:
			setNextAnimation(new Animation(1057));
			setNextGraphics(new Graphics(247));
			final boolean enhanced = weaponId == 14632;
			this.skills.set(1, enhanced ? (int) (this.skills.getLevelForXp(1) * 1.15D) : this.skills.getLevel(1) + 8);
			WorldTasksManager.schedule(new WorldTask() {
				int count = 5;

				public void run() {
					if ((Player.this.isDead()) || (Player.this.hasFinished()) || (Player.this.getHitpoints() >= Player.this.getMaxHitpoints())) {
						stop();
						return;
					}
					Player.this.heal(enhanced ? 80 : 40);
					if (this.count-- == 0) {
						stop();
						return;
					}
				}
			}, 4, 2);
			this.combatDefinitions.desecreaseSpecialAttack(specAmt);
			break;
		case 15486:
		case 22207:
		case 22209:
		case 22211:
		case 22213:
			setNextAnimation(new Animation(12804));
			setNextGraphics(new Graphics(2319));
			setNextGraphics(new Graphics(2321));
			addPolDelay(60000L);
			this.combatDefinitions.desecreaseSpecialAttack(specAmt);
		}
	}

	public void setDisableEquip(boolean equip) {
		this.disableEquip = equip;
	}

	public boolean isEquipDisabled() {
		return this.disableEquip;
	}

	public void addDisplayTime(long i) {
		this.displayTime = (i + Utils.currentTimeMillis());
	}

	public long getDisplayTime() {
		return this.displayTime;
	}

	public int getPublicStatus() {
		return this.publicStatus;
	}

	public void setPublicStatus(int publicStatus) {
		this.publicStatus = publicStatus;
	}

	public int[] getClanCapeCustomized() {
		return this.clanCapeCustomized;
	}

	public void setClanCapeCustomized(int[] clanCapeCustomized) {
		this.clanCapeCustomized = clanCapeCustomized;
	}

	public int[] getClanCapeSymbols() {
		return this.clanCapeSymbols;
	}

	public void setClanCapeSymbols(int[] clanCapeSymbols) {
		this.clanCapeSymbols = clanCapeSymbols;
	}

	public int getTradeStatus() {
		return this.tradeStatus;
	}

	public void setTradeStatus(int tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public int getAssistStatus() {
		return this.assistStatus;
	}

	public void setAssistStatus(int assistStatus) {
		this.assistStatus = assistStatus;
	}

	public Notes getNotes() {
		return this.notes;
	}

	public int getHouseX() {
		return houseX;
	}

	public void setHouseX(int houseX) {
		this.houseX = houseX;
	}

	public int getHouseY() {
		return houseY;
	}

	public void setHouseY(int houseY) {
		this.houseY = houseY;
	}

	public boolean hasBeenToHouse() {
		return hasBeenToHouse;
	}

	public void setBeenToHouse(boolean flag) {
		hasBeenToHouse = flag;
	}

	public int[] getBoundChuncks() {
		return boundChuncks;
	}

	public void setBoundChuncks(int[] boundChuncks) {
		this.boundChuncks = boundChuncks;
	}

	public List<WorldObject> getConObjectsToBeLoaded() {
		return conObjectsToBeLoaded;
	}

	public boolean isBuildMode() {
		return buildMode;
	}

	public void setBuildMode(boolean buildMode) {
		this.buildMode = buildMode;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public List<RoomReference> getRooms() {
		return rooms;
	}

	public RoomReference getCurrentRoom() {
		for (RoomReference r : rooms) {
			if (r.getX() == this.getX() && r.getY() == this.getY()) {
				return r;
			}
		}
		return null;
	}

	public House getHouse() {
		return house;
	}

	public int getRoomX() {
		return Math.round(getXInRegion() / 8);
	}

	public int getRoomY() {
		return Math.round(getYInRegion() / 8);
	}

	public RoomReference getRoomReference() {
		return roomReference;
	}

	public HouseLocation getHouseLocation() {
		return portalLocation;
	}

	public void setHouseLocation(HouseLocation h) {
		this.portalLocation = h;
	}

	public boolean isHasConfirmedRoomDeletion() {
		return hasConfirmedRoomDeletion;
	}

	public void setHasConfirmedRoomDeletion(boolean hasConfirmedRoomDeletion) {
		this.hasConfirmedRoomDeletion = hasConfirmedRoomDeletion;
	}

	public ServantType getButler() {
		return butler;
	}

	public void setButler(ServantType butler) {
		this.butler = butler;
	}

	public RoomReference getRoomFor(int x, int y) {
		for (RoomReference r : this.getRooms()) {
			if (r.getX() == x && r.getY() == y) {
				return r;
			}
		}
		return null;
	}

	public IsaacKeyPair getIsaacKeyPair() {
		return this.isaacKeyPair;
	}

	public QuestManager getQuestManager() {
		return this.questManager;
	}

	public boolean isCompletedFightCaves() {
		return this.completedFightCaves;
	}

	public void setCompletedFightCaves() {
		if (!this.completedFightCaves) {
			this.completedFightCaves = true;
			refreshFightKilnEntrance();
		}
	}

	public boolean isCompletedFightKiln() {
		return this.completedFightKiln;
	}

	public void setCompletedFightKiln() {
		this.completedFightKiln = true;
	}

	public boolean isWonFightPits() {
		return this.wonFightPits;
	}

	public void setWonFightPits() {
		this.wonFightPits = true;
	}

	public boolean isCantTrade() {
		return this.cantTrade;
	}

	public void out(String text) {
		getPackets().sendGameMessage(text);
	}

	public void out(String text, int delay) {
		out(text);
	}

	public void setCantTrade(boolean canTrade) {
		this.cantTrade = canTrade;
	}

	public String getYellColor() {
		return this.yellColor;
	}

	public void setYellColor(String yellColor) {
		this.yellColor = yellColor;
	}

	public void sm(String message) {
		getPackets().sendGameMessage(message);
	}

	public void sm(String message, boolean filter) {
		getPackets().sendGameMessage(message, filter);
	}

	public Pet getPet() {
		return this.pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public PetManager getPetManager() {
		return this.petManager;
	}

	public void setPetManager(PetManager petManager) {
		this.petManager = petManager;
	}

	public boolean isXpLocked() {
		return this.xpLocked;
	}

	public void setXpLocked(boolean locked) {
		this.xpLocked = locked;
	}

	public int getLastBonfire() {
		return this.lastBonfire;
	}

	public void setLastBonfire(int lastBonfire) {
		this.lastBonfire = lastBonfire;
	}

	public boolean isYellOff() {
		return this.yellOff;
	}

	public void setYellOff(boolean yellOff) {
		this.yellOff = yellOff;
	}

	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
	}

	public double getHpBoostMultiplier() {
		return this.hpBoostMultiplier;
	}

	public void setHpBoostMultiplier(double hpBoostMultiplier) {
		this.hpBoostMultiplier = hpBoostMultiplier;
	}

	public boolean isKilledQueenBlackDragon() {
		return this.killedQueenBlackDragon;
	}

	public void setKilledQueenBlackDragon(boolean killedQueenBlackDragon) {
		this.killedQueenBlackDragon = killedQueenBlackDragon;
	}

	public boolean hasLargeSceneView() {
		return this.largeSceneView;
	}

	public void setLargeSceneView(boolean largeSceneView) {
		this.largeSceneView = largeSceneView;
	}

	public boolean isTelelockEnabled() {
		return this.telelocked;
	}

	public void toggleTelelock() {
		this.telelocked = (!this.telelocked);
		setNextGraphics(new Graphics(7));
		getPackets().sendGameMessage("You are now playing with telelock" + (istelelocked() ? "<col=15FF00> ON<col=ffffff>, you will not be teleported" : "<col=FF0000> OFF<col=ffffff>, you will be teleported") + ".");
	}

	public void toggleOldSkillcapes() {
		this.Oldskillcapes = (!this.Oldskillcapes);
		setNextGraphics(new Graphics(7));
		getPackets().sendGameMessage("<img=20>You are now playing with old skillcapes" + (isOldskillcapes() ? "<col=15FF00> ON<col=ffffff>" : "<col=FF0000> OFF<col=ffffff>") + ".");
	}
	
	public void toggleNews() {
		this.NewsOn = (!this.NewsOn);
		setNextGraphics(new Graphics(7));
		getPackets().sendGameMessage("You have turned news messages" + (hasNewsOn() ? "<col=FF0000> OFF<col=ffffff>" : "<col=15FF00> ON<col=ffffff>") + ".");
	}

	public void toggleTrivia() {
		this.TriviaOn = (!this.TriviaOn);
		setNextGraphics(new Graphics(7));
		getPackets().sendGameMessage("You have turned trivia messages" + (hasTriviaOn() ? "<col=FF0000> OFF<col=ffffff>" : "<col=15FF00> ON<col=ffffff>") + ".");
	}

	public void deactivateTelelock() {
		this.telelocked = false;
	}

	public void activateTelelock() {
		this.telelocked = true;
	}

	public static boolean hastelelockON() {
		return LockON;
	}

	public static boolean hastelelockOFF() {
		return LockOFF;
	}

	public static boolean isOldItemsLook() {
		return oldItemsLook;
	}

	public boolean isOldItemsLook1() {
		return oldItemsLook;
	}

	public void switchItemsLook() {
		oldItemsLook = !oldItemsLook;
		getPackets().sendItemsLook();
	}

	public int getRuneSpanPoints() {
		return this.runeSpanPoints;
	}

	public void setRuneSpanPoint(int runeSpanPoints) {
		this.runeSpanPoints = runeSpanPoints;
	}

	public void addRunespanPoints(int points) {
		this.runeSpanPoints += points;
	}

	public int getdungpoints() {
		return this.dungpoints;
	}

	public void setNpcPoints(int dungpoints) {
		this.dungpoints = dungpoints;
	}

	public int getDisasterpoints() {
		return this.Disasterpoints;
	}

	public void setDisasterpoints(int Disasterpoints) {
		this.Disasterpoints = Disasterpoints;
	}

	public DuelRules getLastDuelRules() {
		return this.lastDuelRules;
	}

	public void setLastDuelRules(DuelRules duelRules) {
		this.lastDuelRules = duelRules;
	}

	public boolean isTalkedWithMarv() {
		return this.talkedWithMarv;
	}

	public void setTalkedWithMarv() {
		this.talkedWithMarv = true;
	}

	public int getCrucibleHighScore() {
		return this.crucibleHighScore;
	}

	public void increaseCrucibleHighScore() {
		this.crucibleHighScore += 1;
	}

	public void setVoted(long ms) {
		this.voted = (ms + Utils.currentTimeMillis());
	}

	public boolean hasVoted() {
		return (isMember()) || (this.voted > Utils.currentTimeMillis());
	}

	public int getXpRate() {
		return this.xpRate;
	}

	public void setXpRate(int xpRate) {
		this.xpRate = xpRate;
	}

	public void sendMessage(String text) {
		getPackets().sendGameMessage(text, false);
	}

	public boolean GetPlayers() {
		int number = 0;
		for (int i = 0; i < 310; i++) {
			getPackets().sendIComponentText(275, i, "");
		}
		for (Player p5 : World.getPlayers()) {
			if (p5 != null) {
				number++;
				String titles = "";
				if (p5.isPlayer()) {
					titles = "<col=000000><shad=000000>[Player]";
				}
				if (p5.getRights() == 2 || p5.isRSMVer()) {
					if (p5.getUsername().equalsIgnoreCase("blackbandwar")) {
					titles = "<col=FFFFFF><shad=FF0000>";
					} else if (!p5.getUsername().equalsIgnoreCase("blackbandwar")) {
					titles = "<col=FFFFFF><shad=FF0000>[RSMVer]";
					}
				}
				if (p5.getRights() == 2 && p5.getRSMVRank() == 1) {
					titles = "<col=FFFFFF><shad=FF0000>[RSMVer 1]";
				}
				if (p5.getRights() == 2 && p5.getRSMVRank() == 2) {
					titles = "<col=FFFFFF><shad=FF0000>[RSMVer 2]";
				}
				if (p5.getRights() == 2 && p5.getRSMVRank() == 3) {
					titles = "<col=FFFFFF><shad=FF0000>[RSMVer 3]";
				}
				if (p5.getRights() == 2 && p5.getRSMVRank() == 4) {
					titles = "<col=FFFFFF><shad=FF0000>[RSMVer 4]";
				}
				if (p5.getRights() == 2 && p5.getRSMVRank() == 5) {
					titles = "<col=FFFFFF><shad=FF0000>[RSMVer 5]";
				}
				if (p5.getRights() == 2 && p5.getRSMVRank() == 6) {
					titles = "<col=FFFFFF><shad=FF0000>[RSMVer 6]";
				}
				if (p5.getRights() == 2 && getRSMVRank() == 7) {
					titles = "<col=FFFFFF><shad=FF0000>[RSMVer 7]";
				}
				if (p5.getRights() == 2 && p5.getRSMVRank() == 8) {
					titles = "<col=FFFFFF><shad=FF0000>[RSMVer 8]";
				}
				if (p5.getRights() == 2 && p5.getRSMVRank() == 9) {
					titles = "<col=FFFFFF><shad=FF0000>[RSMVer 9]";
				}
				if (p5.getRights() == 2 && p5.getRSMVRank() == 10) {
					titles = "<col=FFFFFF><shad=FF0000>[RSMVer 10]";
				}
				if (p5.getRights() == 3) {
					titles = "<col=FFFFFF><shad=656564>[Mod]";
				}
				if (p5.getRights() == 4) {
					titles = "<col=ffffff><shad=0000FF>[Admin]";
				}
				if (p5.getUsername().equalsIgnoreCase("Multak")) {
					titles = "<col=ffffff><shad=0000FF>[Owner]";
				}
				if (p5.getUsername().equalsIgnoreCase("Toshero")) {
					titles = "<col=FFFFFF><shad=AD01EC>[Head-Admin]";
				}
				if (p5.getUsername().equalsIgnoreCase("Oz")) {
					titles = "<col=ffffff><shad=FF00D5>[Lead-Admin]";
				}
				if (p5.getUsername().equalsIgnoreCase("chase")) {
					titles = "<col=ffffff><shad=FFCD00>[Head-Mod]";
				}
				if (p5.getUsername().equalsIgnoreCase("wally")) {
					titles = "<col=ffffff><shad=FFCD00>[Hmm-Admin]";
				}
				if (p5.getUsername().equalsIgnoreCase("misfit_hita")) {
					titles = "<col=ffffff><shad=FFCD00>[Supervillain-Admin]";
				}
				if (p5.getUsername().equalsIgnoreCase("angel")) {
					titles = "<col=ffffff><shad=FFCD00>[Halo-Admin]";
				}
				if (p5.getUsername().equalsIgnoreCase("jon")) {
					titles = "<col=ffffff><shad=FFCD00>[JoneMarrow-Admin]";
				}
				if ((p5.getUsername().equalsIgnoreCase("zangetsu")) || (p5.getUsername().equalsIgnoreCase("hunter"))) {
					titles = "<col=ffffff><shad=868686>[Gainz-Mod]";
				}
				if (p5.getUsername().equalsIgnoreCase("ahxchurch")) {
					titles = "<col=ffffff><shad=868686>[Djent-Mod]";
				}
				getPackets().sendIComponentText(275, 10 + number, titles + p5.getDisplayName());
			}
		}
		getPackets().sendIComponentText(275, 10, "<col=00ffff>Players Online: " + number + "</col>");
		getPackets().sendIComponentText(275, 1, "<col=00ffff>Players Currently Online</col>");
		getInterfaceManager().sendInterface(275);
		return true;
	}

	public boolean GetStaff() {
		getInterfaceManager().sendStaffList();
		return true;
	}

	public boolean isInTask() {
		return this.inTask;
	}

	public void setInTask(boolean inTask) {
		this.inTask = inTask;
	}

	public boolean isTaskStage1() {
		return this.taskStage1;
	}

	public void setTaskStage1(boolean taskStage1) {
		this.taskStage1 = taskStage1;
	}

	public boolean isTaskStage2() {
		return this.taskStage2;
	}

	public void setTaskStage2(boolean taskStage2) {
		this.taskStage2 = taskStage2;
	}

	public boolean isTaskStage3() {
		return this.taskStage3;
	}

	public void setTaskStage3(boolean taskStage3) {
		this.taskStage3 = taskStage3;
	}

	public boolean isTaskStage4() {
		return this.taskStage4;
	}

	public void setTaskStage4(boolean taskStage4) {
		this.taskStage4 = taskStage4;
	}

	public boolean isTaskStage5() {
		return this.taskStage5;
	}

	public void setTaskStage5(boolean taskStage5) {
		this.taskStage5 = taskStage5;
	}

	public boolean isTaskStage6() {
		return this.taskStage6;
	}

	public void setTaskStage6(boolean taskStage6) {
		this.taskStage6 = taskStage6;
	}

	public boolean isTaskStage7() {
		return this.taskStage7;
	}

	public void setTaskStage7(boolean taskStage7) {
		this.taskStage7 = taskStage7;
	}

	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void teleportPlayer(int x, int y, int z) {
		setNextWorldTile(new WorldTile(x, y, z));
		stopAll();
	}

	public boolean isBoostedtask() {
		return this.boostedtask;
	}

	public void setBoostedtask(boolean boostedtask) {
		this.boostedtask = boostedtask;
	}

	public void tutorialTeleport(int x, int y, int z) {
		setNextWorldTile(new WorldTile(x, y, z));
	}

	public boolean[] getTaskType() {
		return this.taskType;
	}

	public void setTaskType(boolean[] taskType) {
		this.taskType = taskType;
	}

	public int getTaskAmount() {
		return this.taskAmount;
	}

	public void setTaskAmount(int taskAmount) {
		this.taskAmount = taskAmount;
	}

	public boolean isFinishedTask() {
		return this.finishedTask;
	}

	public void setFinishedTask(boolean finishedTask) {
		this.finishedTask = finishedTask;
	}

	public DwarfCannon getDwarfCannon() {
		return this.dwarfCannon;
	}

	public FairyRing getFairyRing() {
		return this.fairyRing;
	}

	public Ectophial getEctophial() {
		return this.ectophial;
	}

	public Whirlpool getWhirlpool() {
		return this.whirlpool;
	}

	public BrimhavenMatrix getMatrix() {
		return this.matrix;
	}

	public Teletab getTeletab() {
		return this.teletab;
	}

	public Minecart getMinecart() {
		return this.minecart;
	}

	public boolean isOldskillcapes() {
		return this.Oldskillcapes;
	}
	public boolean istelelocked() {
		return this.telelocked;
	}

	public boolean hasNewsOn() {
		return this.NewsOn;
	}

	public boolean hasTriviaOn() {
		return this.TriviaOn;
	}

	public boolean istelelocked1() {
		return this.telelocked;
	}

	public void switchTelelock() {
		this.telelocked = (!this.telelocked);
	}

	public Spiritbag getSpiritbag() {
		return this.spiritbag;
	}

	public void resetStopDelay() {
		this.stopDelay = 0L;
	}

	public void addStopDelay(long delay) {
		this.stopDelay = (Utils.currentTimeMillis() + delay * 600L);
	}

	public void climbUpPolyporeVine(final WorldObject object, final int locX, final int locY, final int plane) {
		lock();
		WorldTasksManager.schedule(new WorldTask() {
			private int count;

			public void run() {
				if (this.count == 0) {
					Player.this.setNextFaceWorldTile(new WorldTile(object.getX(), object.getY(), object.getPlane()));
					Player.this.setNextAnimation(new Animation(15456));
					Player.this.unlock();
				} else if (this.count == 2) {
					Player.this.setNextWorldTile(new WorldTile(locX, locY, plane));
					Player.this.setNextAnimation(new Animation(-1));
				} else if (this.count == 3.5D) {
					Player.this.unlock();
					stop();
				}
				this.count += 1;
			}
		}, 1, 0);
	}

	public void climbPolyporeVine(final WorldObject object, final int locX, final int locY, final int plane) {
		lock();
		WorldTasksManager.schedule(new WorldTask() {
			private int count;

			public void run() {
				if (this.count == 0) {
					Player.this.setNextFaceWorldTile(new WorldTile(object.getX(), object.getY(), object.getPlane()));
					Player.this.setNextAnimation(new Animation(15458));
					Player.this.unlock();
				} else if (this.count == 2) {
					Player.this.setNextWorldTile(new WorldTile(locX, locY, plane));
					Player.this.setNextAnimation(new Animation(15459));
				} else if (this.count == 3) {
					Player.this.unlock();
					stop();
				}
				this.count += 1;
			}
		}, 1, 0);
	}

	public void jumpGap(final WorldObject object, final int locX, final int locY, final int plane) {
		if (getSkills().getLevel(16) < 73) {
			getDialogueManager().startDialogue("Agile", new Object[0]);
			getPackets().sendGameMessage("You need an agility level of 73 to use this shortcut.");
			return;
		}
		lock();
		setNextFaceWorldTile(new WorldTile(object.getX(), object.getY(), object.getPlane()));
		WorldTasksManager.schedule(new WorldTask() {
			private int count;

			public void run() {
				if (this.count == 0) {
					Player.this.setNextFaceWorldTile(new WorldTile(object.getX(), object.getY(), object.getPlane()));
					Player.this.setNextAnimation(new Animation(15461));
					Player.this.unlock();
				} else if (this.count == 4) {
					Player.this.setNextWorldTile(new WorldTile(locX, locY, plane));
					Player.this.setNextAnimation(new Animation(15459));
				} else if (this.count == 5) {
					Player.this.unlock();
					stop();
				}
				this.count += 1;
			}
		}, 1, 0);
	}

	public void pickNeemVine(final WorldObject object) {
		lock();
		WorldTasksManager.schedule(new WorldTask() {
			private int count;

			public void run() {
				if (this.count++ == 1) {
					Player.this.setNextFaceWorldTile(new WorldTile(object.getX(), object.getY(), object.getPlane()));
					if (!Player.this.getInventory().containsItem(1935, 1)) {
						Player.this.out("You need a jug to get neem oil", 0);
						return;
					}
					if (Utils.random(0) > 1) {
						Player.this.out("You manage to get some need oil", 0);
					}
					Player.this.getInventory().addItem(22444, 1);
					Player.this.setNextAnimation(new Animation(15460));
					Player.this.unlock();
					stop();
				}
				this.count += 1;
			}
		}, 1, 0);
		unlock();
	}

	public int DFS = 0;
	public boolean Oldskillcapes = false;
	private boolean talkedWithKuradal;
	public boolean telelocked = false;
	public boolean NewsOn = true;
	public boolean TriviaOn = true;
	public boolean telelockDisabled;
	// trivia shit
	public int[] triviaPoints;
	public boolean hasAnswered;
	public boolean startedstreak = false;
	public boolean endedstreak = true;
	public int streak = 0;
	public int position = TriviaBot.position;
	

	public void addTriviaPoints(int i) {
		triviaPoints[i]++;
	}

	public int getTriviaPoints(int category) {
		return triviaPoints[category];
	}

	public void takeTriviaPoints(int category, int amount) {
		triviaPoints[category] -= amount;
	}

	public void setTriviaPoints(int amount) {
		for (int i = 0; i < triviaPoints.length; i++)
			triviaPoints[i] = amount;
	}

	public void DfsSpec(int shieldId) {
		this.combatDefinitions.hasDfs();
	}

	public void setSlayerPoints(int slayerPoints) {
		this.slayerPoints = slayerPoints;
	}

	public int getSlayerPoints() {
		return this.slayerPoints;
	}

	public void setTask(SlayerTask task) {
		this.task = task;
	}

	public SlayerTask getTask() {
		return this.task;
	}

	public boolean isTalkedWithKuradal() {
		return this.talkedWithKuradal;
	}

	public void setTalkedWithKuradal() {
		this.talkedWithKuradal = true;
	}

	public void falseWithKuradal() {
		this.talkedWithKuradal = false;
	}

	public int getOutfits() {
		return this.totaloutfits;
	}

	public int getRSMVerPoints() {
		return this.RSMVerPoints;
	}

	public void setRSMVerPoints(int RSMVerPoints) {
		this.RSMVerPoints = RSMVerPoints;
	}

	
	public MoneyPouch getMoneyPouch() {
		return this.moneyPouch;
	}

	public boolean isFilteringProfanity() {
		return this.profanityFilter;
	}
	
	public WorldTile getTile() {
		return new WorldTile(getX(), getY(), getPlane());
	}
	
	public boolean isviewingagilityteles() {
		return (viewingagilityteles == true && viewingrsmvactionlocs == false && viewingmonsterteles == false 
			&& viewingminigameteles == false && viewingtrainingteles == false && viewingskillingteles == false
			&& viewingwoodcuttingteles == false);
	}
	public boolean isviewingrsmvactionlocs() {
		return (viewingagilityteles == false && viewingrsmvactionlocs == true && viewingmonsterteles == false 
			&& viewingminigameteles == false && viewingtrainingteles == false && viewingskillingteles == false
			&& viewingwoodcuttingteles == false);
	}
	public boolean isviewingmonsterteles() {
		return (viewingagilityteles == false && viewingrsmvactionlocs == false && viewingmonsterteles == true 
			&& viewingminigameteles == false && viewingtrainingteles == false && viewingskillingteles == false
			&& viewingwoodcuttingteles == false);
	}
	public boolean isviewingminigameteles() {
		return (viewingagilityteles == false && viewingrsmvactionlocs == false && viewingmonsterteles == false 
			&& viewingminigameteles == true && viewingtrainingteles == false && viewingskillingteles == false
			&& viewingwoodcuttingteles == false);
	}
	public boolean isviewingtrainingteles() {
		return (viewingagilityteles == false && viewingrsmvactionlocs == false && viewingmonsterteles == false 
			&& viewingminigameteles == false && viewingtrainingteles == true && viewingskillingteles == false
			&& viewingwoodcuttingteles == false);
	}
	public boolean isviewingskillingteles() {
		return (viewingagilityteles == false && viewingrsmvactionlocs == false && viewingmonsterteles == false 
			&& viewingminigameteles == false && viewingtrainingteles == false && viewingskillingteles == true
			&& viewingwoodcuttingteles == false);
	}
	//Skilling
	public boolean isviewingwoodcuttingteles() {
		return (viewingagilityteles == false && viewingrsmvactionlocs == false && viewingmonsterteles == false 
			&& viewingminigameteles == false && viewingtrainingteles == false && viewingskillingteles == false
			&& viewingwoodcuttingteles == true);
	}
	public boolean isviewinglyreteles() {
		return (viewingagilityteles == false && viewingrsmvactionlocs == false && viewingmonsterteles == false 
			&& viewingminigameteles == false && viewingtrainingteles == false && viewingskillingteles == false
			&& viewingwoodcuttingteles == false && viewinglyreteles == true && viewingfamouscategories == false
			&& viewingbrightcategories == false && viewingmoodycategories == false && viewingfairyringcategories == false
			&& viewingextracategories == false);
	}
	public boolean isviewingfamouscategories() {
		return (viewingagilityteles == false && viewingrsmvactionlocs == false && viewingmonsterteles == false 
			&& viewingminigameteles == false && viewingtrainingteles == false && viewingskillingteles == false
			&& viewingwoodcuttingteles == false && viewinglyreteles == true && viewingfamouscategories == true
			&& viewingbrightcategories == false && viewingmoodycategories == false && viewingfairyringcategories == false
			&& viewingextracategories == false);
	}
	public boolean isviewingbrightcategories() {
		return (viewingagilityteles == false && viewingrsmvactionlocs == false && viewingmonsterteles == false 
			&& viewingminigameteles == false && viewingtrainingteles == false && viewingskillingteles == false
			&& viewingwoodcuttingteles == false && viewinglyreteles == true && viewingfamouscategories == false
			&& viewingbrightcategories == true && viewingmoodycategories == false && viewingfairyringcategories == false
			&& viewingextracategories == false);
	}
	public boolean isviewingmoodycategories() {
		return (viewingagilityteles == false && viewingrsmvactionlocs == false && viewingmonsterteles == false 
			&& viewingminigameteles == false && viewingtrainingteles == false && viewingskillingteles == false
			&& viewingwoodcuttingteles == false && viewinglyreteles == true && viewingfamouscategories == false
			&& viewingbrightcategories == false && viewingmoodycategories == true && viewingfairyringcategories == false
			&& viewingextracategories == false);
	}
	public boolean isviewingfairyringcategories() {
		return (viewingagilityteles == false && viewingrsmvactionlocs == false && viewingmonsterteles == false 
			&& viewingminigameteles == false && viewingtrainingteles == false && viewingskillingteles == false
			&& viewingwoodcuttingteles == false && viewinglyreteles == true && viewingfamouscategories == false
			&& viewingbrightcategories == false && viewingmoodycategories == false && viewingfairyringcategories == true
			&& viewingextracategories == false);
	}
	public boolean isviewingextracategories() {
		return (viewingagilityteles == false && viewingrsmvactionlocs == false && viewingmonsterteles == false 
			&& viewingminigameteles == false && viewingtrainingteles == false && viewingskillingteles == false
			&& viewingwoodcuttingteles == false && viewinglyreteles == true && viewingfamouscategories == false
			&& viewingbrightcategories == false && viewingmoodycategories == false && viewingfairyringcategories == false
			&& viewingextracategories == true);
	}
	public boolean isviewingfamousIteles() {
		return (viewingfamousIteles == true && viewingfamousIIteles == false && viewingfamousIIIteles == false && viewingfamousIIIIteles == false &&
				viewingbrightIteles == false && viewingbrightIIteles == false && viewingbrightIIIteles == false && viewingbrightIIIIteles == false &&
				viewingmoodyIIteles == false && viewingmoodyIIIteles == false && viewingmoodyIIIIteles == false && viewingmoodyIIIIteles == false &&
				viewingfairyIIIteles == false && viewingfairyIIIIteles == false && viewingfairyIIIteles == false && viewingfairyIIIIteles == false &&
				viewingextraIIIteles == false && viewingextraIIIIteles == false && viewingextraIIIteles == false && viewingextraIIIIteles == false);
		
	}
	public boolean isviewingfamousIIteles() {
		return (viewingfamousIteles == false && viewingfamousIIteles == true && viewingfamousIIIteles == false && viewingfamousIIIIteles == false &&
				viewingbrightIteles == false && viewingbrightIIteles == false && viewingbrightIIIteles == false && viewingbrightIIIIteles == false &&
				viewingmoodyIIteles == false && viewingmoodyIIIteles == false && viewingmoodyIIIIteles == false && viewingmoodyIIIIteles == false &&
				viewingfairyIIIteles == false && viewingfairyIIIIteles == false && viewingfairyIIIteles == false && viewingfairyIIIIteles == false &&
				viewingextraIIIteles == false && viewingextraIIIIteles == false && viewingextraIIIteles == false && viewingextraIIIIteles == false);
		
	}
	public boolean isviewingfamousIIIteles() {
		return (viewingfamousIteles == false && viewingfamousIIteles == false && viewingfamousIIIteles == true && viewingfamousIIIIteles == false &&
				viewingbrightIteles == false && viewingbrightIIteles == false && viewingbrightIIIteles == false && viewingbrightIIIIteles == false &&
				viewingmoodyIIteles == false && viewingmoodyIIIteles == false && viewingmoodyIIIIteles == false && viewingmoodyIIIIteles == false &&
				viewingfairyIIIteles == false && viewingfairyIIIIteles == false && viewingfairyIIIteles == false && viewingfairyIIIIteles == false &&
				viewingextraIIIteles == false && viewingextraIIIIteles == false && viewingextraIIIteles == false && viewingextraIIIIteles == false);
		
	}
	public boolean isviewingfamousIIIIteles() {
		return (viewingfamousIteles == false && viewingfamousIIteles == false && viewingfamousIIIteles == false && viewingfamousIIIIteles == true &&
				viewingbrightIteles == false && viewingbrightIIteles == false && viewingbrightIIIteles == false && viewingbrightIIIIteles == false &&
				viewingmoodyIIteles == false && viewingmoodyIIIteles == false && viewingmoodyIIIIteles == false && viewingmoodyIIIIteles == false &&
				viewingfairyIIIteles == false && viewingfairyIIIIteles == false && viewingfairyIIIteles == false && viewingfairyIIIIteles == false &&
				viewingextraIIIteles == false && viewingextraIIIIteles == false && viewingextraIIIteles == false && viewingextraIIIIteles == false);
		
	}
}
