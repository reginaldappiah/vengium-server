package com.rs;

import java.math.BigInteger;

import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;

public final class Settings {

	private transient Player player;
	/**
	 * General client and server settings.
	 */
	public static final String SERVER_NAME = "<col=FF0000>Vengium";
	public static final int PORT_ID = 43594;
	public static final String CACHE_PATH = "cache/";
	public static final int RECEIVE_DATA_LIMIT = 7500;
	public static final int PACKET_SIZE_LIMIT = 7500;
	public static final int CLIENT_BUILD = 718;
	public static final int CUSTOM_CLIENT_BUILD = 3;
	public static final String LOG_PATH = System.getProperty("user.home") + "/Dropbox/logs/";
	
	/**
	 * Launching settings
	 */
	public static boolean DEBUG = true;
	public static boolean HOSTED;
	public static boolean ECONOMY;
	
	/**
	 * If the use of the managment server is enabled.
	 */
	public static boolean MANAGMENT_SERVER_ENABLED = true;

	/**
	 * Graphical User Interface settings
	 */
	public static final String GUI_SIGN = "Vengium GUI";
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	/**
	 * Player settings
	 */
	public static final int START_PLAYER_HITPOINTS = 100;
	public static final WorldTile START_PLAYER_LOCATION = new WorldTile(3094, 3107, 0);
	public static final String START_CONTROLER = "null"; // "NewHomeControler";//
	public static final WorldTile RESPAWN_PLAYER_LOCATION = new WorldTile(2598, 3409, 0);
	public static final long MAX_PACKETS_DECODER_PING_DELAY = 30000; // 30seconds
	public static final int DROP_RATE = 10;
	
	/**
	 * World settings
	 */
	public static final int WORLD_CYCLE_TIME = 600; // the speed of world in ms
	
	/**
	 * Music & Emote settings
	 */
	public static final int AIR_GUITAR_MUSICS_COUNT = 1;
	
	/**
	 * Quest settings
	 */
	public static final int QUESTS = 183;
	
	/**
	 * Memory settings
	 */
	public static final int PLAYERS_LIMIT = 2000;
	public static final int LOCAL_PLAYERS_LIMIT = 250;
	public static final int NPCS_LIMIT = Short.MAX_VALUE;
	public static final int LOCAL_NPCS_LIMIT = 250;
	public static final int STAFF_LIMIT = 10;
	public static final int LOCAL_STAFF_LIMIT = 10;
	public static final int MIN_FREE_MEM_ALLOWED = 30000000; // 30mb
	public static final WorldTile CONTROLER_LOCATION = new WorldTile(2712, 5349, 0);
	/**
	 * Game constants
	 */
	public static final int[] MAP_SIZES = { 104, 120, 136, 168, 72 };
	
	public static final String GRAB_SERVER_TOKEN = "hAJWGrsaETglRjuwxMwnlA/d5W6EgYWx";
	public static final int[] GRAB_SERVER_KEYS = {1441,78700,44880,39771,363186,44375,0,16140,7316
		,271148,810710,216189,379672,454149,933950,21006,25367,17247,1244,1,14856,1494,119,882901,1818764,3963,3618};
	
	
	//an exeption(grab server has his own keyset unlike rest of client)
	public static final BigInteger GRAB_SERVER_PRIVATE_EXPONENT = new BigInteger("95776340111155337321344029627634178888626101791582245228586750697996713454019354716577077577558156976177994479837760989691356438974879647293064177555518187567327659793331431421153203931914933858526857396428052266926507860603166705084302845740310178306001400777670591958466653637275131498866778592148380588481");
	public static final BigInteger GRAB_SERVER_MODULUS =  new BigInteger("119555331260995530494627322191654816613155476612603817103079689925995402263457895890829148093414135342420807287820032417458428763496565605970163936696811485500553506743979521465489801746973392901885588777462023165252483988431877411021816445058706597607453280166045122971960003629860919338852061972113876035333");
	
	public static final BigInteger PRIVATE_EXPONENT = new BigInteger("90587072701551327129007891668787349509347630408215045082807628285770049664232156776755654198505412956586289981306433146503308411067358680117206732091608088418458220580479081111360656446804397560752455367862620370537461050334224448167071367743407184852057833323917170323302797356352672118595769338616589092625");
	public static final BigInteger MODULUS = new BigInteger("102876637271116124732338500663639643113504464789339249490399312659674772039314875904176809267475033772367707882873773291786014475222178654932442254125731622781524413208523465520758537060408541610254619166907142593731337618490879831401461945679478046811438574041131738117063340726565226753787565780501845348613");
	 
	//public static String[] MEMBERS_ITEMS = { "" };
	
	public static String[] LOST_CITY_ITEMS = {"Fire rune", "Water rune", "Air rune"};
	/**
	 * Item settings
	 */
	public static String[] EARNED_ITEMS = { /*"tokkul", "dagger", "(class",
			"sacred clay", "dominion", "sled", "magic carpet"*/ };
	
	public static String[] REMOVING_ITEMS = { "(class",
		"sacred clay", "dominion", "sled"};
	
	public static String[] VOTE_REQUIRED_ITEMS = {
	
	};
	
	public static final String[] UNWANTED_WORDS = { "nigger" 
	};
	public static final int WARNING_AMOUNT = 50000000; // 50m
	
	public static String[] RARE_ITEMS = {
	"White cavalier", "White Cavalier",
	};
	
	
	public static boolean inApacheEmperorZone(WorldTile tile) {
		return (tile.getX() >= 2830 && tile.getX() <= 2862 && tile.getY() >= 10042 && tile.getY() <= 10062);
	}
	
	public static void getRSMVSet(Player player) {
		player.setNextGraphics(new Graphics(7));
		player.getBank().addItem(10890, 100, 1, true);// Prayer Book
		player.getBank().addItem(3840, 100, 1, true);// Holy Book (White)
		player.getBank().addItem(3842, 100, 1, true);// Prayer Unholy Book (Red)
		player.getBank().addItem(3844, 100, 1, true);// Book of Balance (Green)
		player.getBank().addItem(6865, 100, 1, true);// Marionette (Blue)		
		player.getBank().addItem(6867, 100, 1, true);// Marriontte (Red)
		player.getBank().addItem(6866, 100, 1, true);// Marionette (Green)
		player.getBank().addItem(4079, 100, 1, true);// Yo-yo
		player.getBank().addItem(11949, 100, 1, true);// Snow-globe
		player.getBank().addItem(11749, 100, 1, true);// Chimes
		player.getBank().addItem(22418, 100, 1, true);// Cithera
		player.getBank().addItem(6722, 100, 1, true);// Zombie Head
		player.getBank().addItem(19832, 100, 1, true); // Bone Brooch
		player.getBank().addItem(14057, 100, 1, true); // Broomstick
		player.getBank().addItem(14062, 100, 1, true);// Broomstick Enchantment
		player.getBank().addItem(11258, 100, 1, true); // Jar Generator
		player.getBank().addItem(10014, 100, 1, true); // Black Warlock
		player.getBank().addItem(10016, 100, 1, true); // Snowy Knight
		player.getBank().addItem(10018, 100, 1, true); // Sapphire Glacialis
		player.getBank().addItem(10014, 100, 1, true); // Ruby Harvest
		player.getBank().addItem(19671, 100, 1, true);// Virtus Action
		player.getBank().addItem(13146, 100, 1, true);// Ivandis Flail(1)
		player.getBank().addItem(7637, 100, 1, true);// Silvthril Rod
		player.getBank().addItem(13156, 100, 1, true);// Enchanted Sickle Emerald(b)
		player.getBank().addItem(14742, 100, 1, true);// Lilly
		player.getBank().addItem(10392, 100, 1, true);// Powered Wig (ENHANCED ANGRY)
		player.getBank().addItem(10398, 100, 1, true);// Sleeping Cap (ENHANCED YAWN)
		player.getBank().addItem(10396, 100, 1, true); // Pantaloons (ENHANCED BOW)
		player.getBank().addItem(10394, 100, 1, true); // Flared Trousers (ENHANCED DANCE)
		player.getBank().addItem(11021, 100, 1, true);  // Chicken Head (ENHANCED FLAP)
		player.getBank().addItem(11020, 100, 1, true);  // Chicken Wings (ENHANCED FLAP)
		player.getBank().addItem(11022, 100, 1, true);  // Chicken Legs (ENHANCED FLAP)
		player.getBank().addItem(11019, 100, 1, true);  // Chicken Feet (ENHANCED FLAP)
		player.getBank().addItem(10862, 100, 1, true);// Hard Hat (ENHANCED BECKON)
		player.getBank().addItem(10863, 100, 1, true);// Builder's Shirt (ENHANCED BECKON)
		player.getBank().addItem(10864, 100, 1, true);// Builder's Trousers (ENHANCED BECKON)
		player.getBank().addItem(10864, 100, 1, true);// Builder's Boots (ENHANCED BECKON)
		player.getBank().addItem(10877, 100, 1, true);// Plain Satchel (ENHANCED BECKON)
		player.getBank().addItem(10878, 100, 1, true);// Green Satchel (ENHANCED BECKON)
		player.getBank().addItem(10879, 100, 1, true);// Red Satchel (ENHANCED BECKON)
		player.getBank().addItem(10880, 100, 1, true);// Black Satchel (ENHANCED BECKON)
		player.getBank().addItem(10881, 100, 1, true);// Gold Satchel (ENHANCED BECKON)
		player.getBank().addItem(10882, 100, 1, true);// Rune Satchel (ENHANCED BECKON)
		player.getBank().addItem(14692, 100, 1, true);// Bandos Throne Room Sphere
		player.getBank().addItem(10972, 100, 1, true);// Dorgesh-Kaan Sphere
		player.getBank().addItem(11060, 100, 1, true);// Goblin Village Sphere
		player.getBank().addItem(16, 100, 1, true);// Magic Whistle (ENHANCED BECKON)
		player.getBank().addItem(7673, 100, 1, true);// Boxing Glove (Blue)
		player.getBank().addItem(7671, 100, 1, true);// Boxing Glove (Red)
		player.getBank().addItem(4251, 100, 1, true); // Ectophial
		player.getBank().addItem(11204, 100, 1, true); // Shrink-Me-Quick
		player.getBank().addItem(8940, 100, 1, true);// Rum (Red)
		player.getBank().addItem(8941, 100, 1, true);// Rum (Blue)
		player.getBank().addItem(10952, 100, 1, true);	// Slayer Bell
		player.getBank().addItem(15374, 100, 1, true); // Magnifying Glass
		player.getBank().addItem(3695, 100, 1, true);// Pet Rock
		player.getBank().addItem(20091, 100, 1, true); // Boulder
		player.getBank().addItem(9469, 100, 1, true); // Grand Seed Pod
		player.getBank().addItem(18778, 100, 1, true);// Starved Ancient Effigy (DOESN'T ACCEPT)
		player.getBank().addItem(18781, 100, 1, true);// Gorged Anicent Effigy (ACCEPTS)
		player.getBank().addItem(18782, 100, 1, true);// Dragonkin Lamp 
		player.getBank().addItem(20801, 100, 1, true);// Wildstalker helmet (TIER 1)
		player.getBank().addItem(20802, 100, 1, true);// Wildstalker helmet (TIER 2)
		player.getBank().addItem(20803, 100, 1, true);// Wildstalker helmet (TIER 3)
		player.getBank().addItem(20804, 100, 1, true);// Wildstalker helmet (TIER 4)
		player.getBank().addItem(20805, 100, 1, true);// Wildstalker helmet (TIER 5)
		player.getBank().addItem(20806, 100, 1, true);// Wildstalker helmet (TIER 6)
		player.getBank().addItem(6635, 100, 1, true); // Commoorb (ORIGINAL)
		player.getBank().addItem(9681, 100, 1, true); // Commoorb (LATEST)
		player.getBank().addItem(15440, 100, 1, true);// Penance Master Horn 
		player.getBank().addItem(15067, 100, 1, true);// Starting Horn 
		player.getBank().addItem(20795, 100, 1, true);// Duelist's Hat (TIER 1)
		player.getBank().addItem(20796, 100, 1, true);// Duelist's Hat (TIER 2)
		player.getBank().addItem(20797, 100, 1, true);// Duelist's Hat (TIER 3)
		player.getBank().addItem(20798, 100, 1, true);// Duelist's Hat (TIER 4)
		player.getBank().addItem(20799, 100, 1, true);// Duelist's Hat (TIER 5)
		player.getBank().addItem(20800, 100, 1, true);// Duelist's Hat (TIER 6)
		player.getBank().addItem(15046, 100, 1, true);// Magic Skullball
		player.getBank().addItem(15050, 100, 1, true);// Magic Skullball (DEATH)
		player.getBank().addItem(20667, 100, 1, true);// Vecna's Skull
		player.getBank().addItem(9934, 100, 1, true);// Origami Balloon 
		player.getBank().addItem(9935, 100, 1, true);// Yellow Balloon 
		player.getBank().addItem(9936, 100, 1, true);// Blue Balloon 
		player.getBank().addItem(9937, 100, 1, true);// Red Balloon 
		player.getBank().addItem(9938, 100, 1, true);// Orange Balloon 
		player.getBank().addItem(9939, 100, 1, true);// Green Balloon 
		player.getBank().addItem(9940, 100, 1, true);// Purple Balloon 
		player.getBank().addItem(9941, 100, 1, true);// Pink Balloon 
		player.getBank().addItem(9942, 100, 1, true);// Black Balloon
		player.getBank().addItem(9013, 100, 1, true);// Skull Sceptre
		player.getBank().addItem(9044, 100, 1, true);// Pharaoh's Sceptre
		player.getBank().addItem(19748, 100, 1, true);// Ardougne Cloak
		player.getBank().addItem(19967, 100, 1, true);// JuJu Spiritbag
		player.getBank().addItem(13663, 100, 1, true);// Circus Ticket
		player.getBank().addItem(15215, 100, 1, true);// Bonesack (e)
		player.getBank().addItem(24137, 100, 1, true);// Morytania Legs
		player.getBank().addItem(14577, 100, 1, true);// Falador Shield 1
		player.getBank().addItem(14578, 100, 1, true);// Falador Shield 2
		player.getBank().addItem(14579, 100, 1, true);// Falador Shield 3
		player.getBank().addItem(19749, 100, 1, true);// Falador Shield 4
		player.getBank().addItem(20311, 100, 1, true);// Plank-Make Urn
		player.getBank().addItem(13732, 100, 1, true); // Spybook
		player.getBank().addItem(10896, 100, 1, true);// Wolf Transformation
		player.getBank().addItem(14696, 100, 1, true);// Nature Growth
		player.getBank().addItem(12842, 100, 1, true);// ROFL-Copter	
		player.getBank().addItem(21415, 100, 1, true);// Summoning Ring
		player.getBank().addItem(15707, 100, 1, true);// Dungeoneering Ring
		player.getBank().addItem(13560, 100, 1, true); // Explorer's Ring
		player.getBank().addItem(15673, 100, 1, true);// Squirrel Ears
		player.getBank().addItem(20077, 100, 1, true);// Salty Claws Hat
		player.getBank().addItem(24419, 100, 1, true);// Diamond Hat
		player.getBank().addItem(24418, 100, 1, true);// Diamond Cane
		player.getBank().addItem(19329, 100, 1, true);// Wolf Staff
		player.getBank().addItem(19327, 100, 1, true);// Bat Staff
		player.getBank().addItem(19331, 100, 1, true);// Cat Staff
		player.getBank().addItem(19323, 100, 1, true);// Dragon Staff
		player.getBank().addItem(19325, 100, 1, true);// Penguin Staff
		player.getBank().addItem(24455, 100, 1, true);// Annihilator
		player.getBank().addItem(11283, 100, 1, true);// Dragon Fireshield
		player.getBank().addItem(12780, 100, 1, true);// Pest Control Tele
	player.getPackets().sendGameMessage("<img=20><col=FFFFFF><shad=FF0000>Check your bank for the RSMV set!");
	return;
	}
	
	public static void getTeleItems(Player player) {
		player.setNextGraphics(new Graphics(7));
		player.getBank().addItem(13599, 101, 2, true);//Air Altar Teleport
		player.getBank().addItem(13600, 101, 2, true);//Mind Altar Teleport
		player.getBank().addItem(13601, 101, 2, true);//Water Altar Teleport
		player.getBank().addItem(13602, 101, 2, true);//Earth Altar Teleport
		player.getBank().addItem(13603, 101, 2, true);//Fire Altar Teleport
		player.getBank().addItem(13604, 101, 2, true);//Body Altar Teleport
		player.getBank().addItem(13605, 101, 2, true);//Cosmic Altar Teleport
		player.getBank().addItem(13606, 101, 2, true);//Chaos Altar Teleport
		player.getBank().addItem(13611, 101, 2, true);//Astral Altar Teleport
		player.getBank().addItem(13607, 101, 2, true);//Nature Altar Teleport
		player.getBank().addItem(13608, 101, 2, true);//Law Altar Teleport
		player.getBank().addItem(13609, 101, 2, true);//Death Altar Teleport
		player.getBank().addItem(13610, 101, 2, true);//Blood Altar Teleport
		player.getBank().addItem(13598, 101, 2, true);//RC Guild Teleport
		player.getBank().addItem(8007, 101, 2, true); // Varrock Teleport
		player.getBank().addItem(8008, 101, 2, true); // Lumbridge Teleport
		player.getBank().addItem(8009, 101, 2, true); // Falador Teleport
		player.getBank().addItem(8010, 101, 2, true); // Camelot Teleport
		player.getBank().addItem(8011, 101, 2, true); // Ardougne Teleport
		player.getBank().addItem(8012, 101, 2, true); // Watchtower Teleport
		player.getBank().addItem(18809, 101, 2, true); // Rimmington Teleport
		player.getBank().addItem(18810, 101, 2, true); // Taverly Teleport
		player.getBank().addItem(18811, 101, 2, true); // Pollnivneach Teleport
		player.getBank().addItem(18812, 101, 2, true); // Relleka Teleport
		player.getBank().addItem(18813, 101, 2, true); // Brimhaven Teleport
		player.getBank().addItem(18814, 101, 2, true); // Yanille Teleport
		player.getBank().addItem(20175, 101, 2, true); // Trollheim Teleport
		player.getBank().addItem(21576, 101, 2, true);// Darkan's Medallion
		player.getBank().addItem(3690, 101, 2, true);// Lyre
		
		player.getBank().addItem(19475, 101, 2, true); // Nardah Teleport
		player.getBank().addItem(19476, 101, 2, true);// Bandit Camp Teleport
		player.getBank().addItem(19477, 101, 2, true);// Miscellenia Teleport
		player.getBank().addItem(19478, 101, 2, true);// Phoenix Lait Teleport
		player.getBank().addItem(19479, 101, 2, true);// Tai Bwo Wannai
		player.getBank().addItem(19480, 101, 2, true);// Lumber Yard Teleport

		player.getInventory().addItem(21576, 1);// Darkan's Medallion
		player.getInventory().addItem(3690, 1);// Official Lyre

	player.sendMessage("<img=20><col=FFFFFF><shad=FF00000>Check your inventory and bank for the Teleport Items!");
	return;
	}
	
	public static void getRunes(Player player) {
		player.setNextGraphics(new Graphics(7));
		player.getBank().addItem(555, 1000000, 3, true);// Water Runes
		player.getBank().addItem(556, 1000000, 3, true);// Air Runes
		player.getBank().addItem(557, 1000000, 3, true);// Earth Runes
		player.getBank().addItem(554, 1000000, 3, true);// Fire Runes
		player.getBank().addItem(558, 1000000, 3, true);// Mind Runes
		player.getBank().addItem(561, 1000000, 3, true);// Nature Runes
		player.getBank().addItem(562, 1000000, 3, true);// Chaos Runes
		player.getBank().addItem(560, 1000000, 3, true);// Death Runes
		player.getBank().addItem(565, 1000000, 3, true);// Blood Runes
		player.getBank().addItem(566, 1000000, 3, true);// Soul Runes
		player.getBank().addItem(9075, 1000000, 3, true);// Astral Runes
		player.getBank().addItem(563, 1000000, 3, true);// Law Runes
		player.getBank().addItem(564, 1000000, 3, true);// Cosmic Runes
		player.getBank().addItem(559, 1000000, 3, true);// Body Runes

		player.getInventory().addItem(555, 1000000);// Water Runes
		player.getInventory().addItem(556, 1000000);// Air Runes
		player.getInventory().addItem(557, 1000000);// Earth Runes
		player.getInventory().addItem(554, 1000000);// Fire Runes
		player.getInventory().addItem(558, 1000000);// Mind Runes
		player.getInventory().addItem(561, 1000000);// Nature Runes
		player.getInventory().addItem(562, 1000000);// Chaos Runes
		player.getInventory().addItem(560, 1000000);// Death Runes
		player.getInventory().addItem(565, 1000000);// Blood Runes
		player.getInventory().addItem(566, 1000000);// Soul Runes
		player.getInventory().addItem(9075, 1000000);// Astral Runes
		player.getInventory().addItem(563, 1000000);// Law Runes
		player.getInventory().addItem(564, 1000000);// Cosmic Runes
		player.getInventory().addItem(559, 1000000);// Body Runes
			
		player.sendMessage("<img=20><col=FFFFFF><shad=FF00000>Check your inventory and bank for the runes!");
		return;
	}
	
	public static void getGear(Player player) {
		player.setNextGraphics(new Graphics(6));
		player.getBank().addItem(20135, 69999999, 4, true);
		player.getBank().addItem(20139, 69999999, 4, true);
		player.getBank().addItem(20143, 69999999, 4, true);
		player.getBank().addItem(20147, 69999999, 4, true);
		player.getBank().addItem(20151, 69999999, 4, true);
		player.getBank().addItem(20155, 69999999, 4, true);
		player.getBank().addItem(20159, 69999999, 4, true);
		player.getBank().addItem(20163, 69999999, 4, true);
		player.getBank().addItem(20167, 69999999, 4, true);
		player.getBank().addItem(6585, 69999999, 4, true);
		player.getBank().addItem(19335, 69999999, 4, true);
		player.getBank().addItem(4151, 69999999, 4, true);
		player.getBank().addItem(21371, 69999999, 4, true);
		player.getBank().addItem(14484, 69999999, 4, true);
		player.getBank().addItem(13734, 69999999, 4, true);
		player.getBank().addItem(13736, 69999999, 4, true);
		player.getBank().addItem(13738, 69999999, 4, true);
		player.getBank().addItem(13740, 69999999, 4, true);
		player.getBank().addItem(13742, 69999999, 4, true);
		player.getBank().addItem(13744, 69999999, 4, true);
		player.getBank().addItem(11696, 69999999, 4, true);
		player.getBank().addItem(11700, 69999999, 4, true);
		player.getBank().addItem(11698, 69999999, 4, true);
		player.getBank().addItem(11694, 69999999, 4, true);
		player.getBank().addItem(11724, 69999999, 4, true);
		player.getBank().addItem(11726, 69999999, 4, true);
		player.getBank().addItem(11728, 69999999, 4, true);
		player.getBank().addItem(11718, 69999999, 4, true);
		player.getBank().addItem(11720, 69999999, 4, true);
		player.getBank().addItem(11722, 69999999, 4, true);
		player.getBank().addItem(11716, 69999999, 4, true);
		player.getBank().addItem(21787, 69999999, 4, true);
		player.getBank().addItem(21790, 69999999, 4, true);
		player.getBank().addItem(21793, 69999999, 4, true);
		player.getBank().addItem(11335, 69999999, 4, true);
		player.getBank().addItem(14479, 69999999, 4, true);
		player.getBank().addItem(4087, 69999999, 4, true);
		player.getBank().addItem(11732, 69999999, 4, true);
		player.getBank().addItem(18349, 69999999, 4, true);
		player.getBank().addItem(18351, 69999999, 4, true);
		player.getBank().addItem(18353, 69999999, 4, true);
		player.getBank().addItem(18355, 69999999, 4, true);
		player.getBank().addItem(18357, 69999999, 4, true);
		player.getBank().addItem(18359, 69999999, 4, true);
		player.getBank().addItem(18361, 69999999, 4, true);
		player.getBank().addItem(18363, 69999999, 4, true);
		player.getBank().addItem(10498, 69999999, 4, true);
		player.getBank().addItem(10499, 69999999, 4, true);
		player.getBank().addItem(20786, 69999999, 4, true);
		player.getBank().addItem(15259, 69999999, 4, true);
		player.getBank().addItem(6739, 69999999, 4, true);
		player.getBank().addItem(20770, 69999999, 4, true);
		player.getBank().addItem(20769, 69999999, 4, true);
		player.getBank().addItem(20772, 69999999, 4, true);
		player.getBank().addItem(20771, 69999999, 4, true);
		player.getBank().addItem(20768, 69999999, 4, true);
		player.getBank().addItem(20767, 69999999, 4, true);
		player.getBank().addItem(15332, 69999999, 4, true);
		player.getBank().addItem(23531, 69999999, 4, true);
		player.getBank().addItem(7462, 69999999, 4, true);
	
	player.sendMessage("<img=20><col=FFFFFF><shad=FF00000>Check your inventory and bank for the gear!");
	}
	private Settings() {

	}
}
