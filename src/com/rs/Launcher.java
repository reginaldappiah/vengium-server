package com.rs;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.alex.store.Index;
import com.rs.cores.CoresManager;
import com.rs.cache.Cache;
import com.rs.game.player.content.grandexchange.GrandExchangeLoader;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.ItemsEquipIds;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.cache.loaders.ObjectDefinitions;
import com.rs.cores.CoresManager;
import com.rs.game.Region;
import com.rs.game.RegionBuilder;
import com.rs.game.World;
import com.rs.game.npc.combat.CombatScriptsHandler;
import com.rs.game.player.ClansManager;
import com.rs.game.player.Player;
import com.rs.game.player.content.FishingSpotsHandler;
import com.rs.game.player.content.FriendChatsManager;
import com.rs.game.player.controlers.ControlerHandler;
import com.rs.game.player.cutscenes.CutscenesHandler;
import com.rs.game.player.dialogues.DialogueHandler;
import com.rs.net.ServerChannelHandler;
import com.rs.utils.DTRank;
import com.rs.utils.DisplayNames;
import com.rs.utils.IPBanL;
import com.rs.utils.IPJail;
import com.rs.utils.IPMute;
import com.rs.utils.ItemBonuses;
import com.rs.utils.ItemExamines;
import com.rs.utils.Logger;
import com.rs.utils.MapArchiveKeys;
import com.rs.utils.MapAreas;
import com.rs.utils.MusicHints;
import com.rs.utils.NPCBonuses;
import com.rs.utils.NPCCombatDefinitionsL;
import com.rs.utils.NPCDrops;
import com.rs.utils.NPCSpawns;
import com.rs.utils.ObjectSpawns;
import com.rs.utils.PkRank;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.ShopsHandler;
import com.rs.utils.Utils;
import com.rs.utils.huffman.Huffman;
import com.alex.store.Index;

public final class Launcher {

	public static void main(String[] args) throws Exception {
/*		if (args.length < 3) {
			System.out.println("USE: guimode(boolean) debug(boolean) hosted(boolean)");
		    return;
		}
		Settings.HOSTED = Boolean.parseBoolean(args[2]);
		Settings.DEBUG = Boolean.parseBoolean(args[1]);
		long currentTime = Utils.currentTimeMillis();
		if (Settings.HOSTED) {
			// System.setErr(new PrintStream(new
			// FileOutputStream("data/auto/err.txt")));
			// System.setOut(new PrintStream(new
			// FileOutputStream("data/auto/out.txt")));
		}*/
		long currentTime = Utils.currentTimeMillis();
		authenticate();
		//Logger.log("Vengium", "Reading Cache Intake...");
		Cache.init();
		//Logger.log("Vengium", "Loading Cache...");
		ItemsEquipIds.init();
		//Logger.log("Vengium", "Iniating Grand Item EquipIds...");
		//ItemsEquipIds.init();
		//Logger.log("Vengium", "Loading Quests...");
		Huffman.init();
		//Logger.log("Vengium", "Iniating Data Files...");
		DisplayNames.init();
		//Logger.log("Vengium", "Initializing Security...");
		IPBanL.init();
		IPMute.init();
		IPJail.init();
		PkRank.init();
		
		DTRank.init();
		MapArchiveKeys.init();
		MapAreas.init();
		ObjectSpawns.init();
		//Logger.log("Vengium", "Spawning NPCs...");
		NPCSpawns.init();
		NPCCombatDefinitionsL.init();
		NPCBonuses.init();
		//NPCDrops.init();
		//ItemExamines.init();
		ItemBonuses.init();
		MusicHints.init();
		//Logger.log("Vengium", "Loading Shops...");
		ShopsHandler.init();
		//Logger.log("Launcher", "Iniating Grand Exchange...");
		//GrandExchangeLoader.initialize();
		//Logger.log("Launcher", "Iniating Fishing Spots...");
		FishingSpotsHandler.init();
		//Logger.log("Launcher", "Iniating NPC Combat Scripts...");
		CombatScriptsHandler.init();
		//Logger.log("Launcher", "Iniating Dialogues...");
		DialogueHandler.init();
		//Logger.log("Launcher", "Iniating Controlers...");
		ControlerHandler.init();
		//Logger.log("Launcher", "Iniating Cutscenes...");
		CutscenesHandler.init();
		//Logger.log("Launcher", "Iniating Friend Chats Manager...");
		FriendChatsManager.init();
		//Logger.log("Launcher", "Initiating Clans Manager...");
		ClansManager.init();
		//Logger.log("Launcher", "Iniating Cores Manager...");
		CoresManager.init();
		//Logger.log("Launcher", "Iniating World...");
		World.init();
		//Logger.log("Launcher", "Iniating Region Builder...");
		RegionBuilder.init();
		//Logger.log("Launcher", "Initaing Server Channel Handler...");
		try {
			ServerChannelHandler.init();
		} catch (Throwable e) {
			Logger.handle(e);
			Logger.log("Launcher",
					"Failed iniating Server Channel Handler. Shutting down...");
			System.exit(1);
			return;
		}
		Logger.log("Launcher", "Server took "
				+ (Utils.currentTimeMillis() - currentTime)
				+ " milli seconds to launch.");
		addAccountsSavingTask();
		addCleanMemoryTask();
		// Donations.init();
	}

	/*public static void setWebsitePlayersOnline(int number) throws IOException {
		URL url;
		url = new URL("http://gladeonrsps.com/update.php?players="+number+"&pass=gladeonrspshostedbydikkekontandcrowley");
		InputStream is = url.openStream();
		is.close();
	}*/

	private static void addCleanMemoryTask() {
		CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					cleanMemory(Runtime.getRuntime().freeMemory() < Settings.MIN_FREE_MEM_ALLOWED);
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, 0, 10, TimeUnit.MINUTES);
	}
	
	private static void addAccountsSavingTask() {
        CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    saveFiles();
                    Logger.log("Online",
                            "There are currently " + (World.getPlayers().size())
                                    + " players playing Vengium" + ".");
                } catch (Throwable e) {
                    Logger.handle(e);
                }

            }
        }, 1, 1, TimeUnit.MINUTES);//can be changed to seconds using "TimeUnit.SECONDS" as of now every one minute it will save the players.
    }
	
	public static void saveFiles() {
		for (Player player : World.getPlayers()) {
			if (player == null || !player.hasStarted() || player.hasFinished())
				continue;
			SerializableFilesManager.savePlayer(player);
		}
		DisplayNames.save();
		IPBanL.save();
		IPMute.save();
		IPJail.save();
		PkRank.save();
		DTRank.save();
	}

	public static void cleanMemory(boolean force) {
		if (force) {
			ItemDefinitions.clearItemsDefinitions();
			NPCDefinitions.clearNPCDefinitions();
			ObjectDefinitions.clearObjectDefinitions();
			//for (Region region : World.getRegions().values())
				//region//ripped of fmy fking src im sorry? o.o wait whats your source th
		}
		for (Index index : Cache.STORE.getIndexes())
			index.resetCachedFiles();
		CoresManager.fastExecutor.purge();
		System.gc();
	}

	public static void shutdown() {
		try {
			closeServices();
		} finally {
			System.exit(0);
		}
	}

	public static void closeServices() {
		ServerChannelHandler.shutdown();
		CoresManager.shutdown();
		if (Settings.HOSTED) {
			try {
				//setWebsitePlayersOnline(0);
			} catch (Throwable e) {
				Logger.handle(e);
			}
		}
	}

	public static void restart() {
		closeServices();
		System.gc();
		try {
			Runtime.getRuntime().exec("java -server -Xms2048m -Xmx20000m -cp bin;/data/libs/netty-3.2.7.Final.jar;/data/libs/FileStore.jar Launcher false false true false");
			System.exit(0);
		} catch (Throwable e) {
			Logger.handle(e);
		}

	}
	
	public static void authenticate() {
			System.out.println("" + "\n--------------------------------------------------------------------------------" + "\n                           Vengium ~ RSMV Away                             	" + "\n                            Created by Multak                         	" + "\n--------------------------------------------------------------------------------");
			Logger.log("Vengium", "Successfully passed the Core Utilities authentication!");
		}

	private Launcher() {

	}

}
