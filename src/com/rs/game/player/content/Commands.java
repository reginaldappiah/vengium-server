package com.rs.game.player.content;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.rs.Settings;
import com.rs.cache.loaders.AnimationDefinitions;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.ObjectDefinitions;
import com.rs.game.Animation;
import com.rs.game.ForceMovement;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.Region;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.minigames.CastleWars;
import com.rs.game.minigames.FightPits;
import com.rs.game.minigames.clanwars.WallHandler;
import com.rs.game.npc.NPC;
import com.rs.game.npc.others.Bork;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.Notes.Note;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Encrypt;
import com.rs.utils.IPBanL;
import com.rs.utils.IPJail;
import com.rs.utils.IPMute;
import com.rs.utils.PkRank;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.ShopsHandler;
import com.rs.utils.Utils;

/*
 * doesnt let it be extended
 */
public final class Commands {

	/*
     * all console commands only for admin, chat commands processed if they not
	 * processed by console
	 */
    
    private static int moveLocalX;
    private static int moveLocalY;
    private static int moveZ;
    private static int speed;
    private static int speed2;
    /*
	 * doesnt let it be instanced
	 */
    private Commands() {
    
    }

    /**
     * returns if command was processed
     */
    
    public static boolean processCommand(Player player, String command, boolean console, boolean clientCommand) {
        if (command.length() == 0) {
            return false;
        }
        String[] cmd = command.toLowerCase().split(" ");
        if (cmd.length == 0) {
            return false;
        }
        if (player.getRights() >= 4 && processAdminCommand(player, cmd, console, clientCommand)) {
            return true;
        }
        if (player.getRights() >= 3 && (processModCommand(player, cmd, console, clientCommand) || processHeadModCommands(player, cmd, console, clientCommand) || processSupportCommands(player, cmd, console, clientCommand))) {
            return true;
        }
        
        if (player.getRights() >= 2 && processRSMVerCommand(player, cmd, console, clientCommand)) {
            return true;
        }
        
        if (player.getRights() <= 1 && processNormalCommand(player, cmd, console, clientCommand)) {
            return true;
        }
        
        if (Settings.ECONOMY) {
            player.getPackets().sendGameMessage("You can't use any commands in economy mode!");
            return true;
        }
        return processNormalCommand(player, cmd, console, clientCommand);
    }

    public static boolean processAdminCommand(final Player player, String[] cmd, boolean console, boolean clientCommand) {
        if (clientCommand) {
            switch (cmd[0]) {
                case "tele":
                    cmd = cmd[1].split(",");
                    int plane = Integer.valueOf(cmd[0]);
                    int x = Integer.valueOf(cmd[1]) << 6 | Integer.valueOf(cmd[3]);
                    int y = Integer.valueOf(cmd[2]) << 6 | Integer.valueOf(cmd[4]);
                    player.setNextWorldTile(new WorldTile(x, y, plane));
                    return true;
            }
        } else {
            String name;
            Player target;
            WorldObject object;
            switch (cmd[0]) {
                
                case "posi":
                    player.getPackets().sendMoveIComponent(player.getInterfaceManager().viewingInterface, Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]), Integer.parseInt(cmd[3]));
                    return true;
    
                case "modeli":
                    int modelID = Integer.parseInt(cmd[2]);
                    int componentID = Integer.parseInt(cmd[1]);
                    player.getPackets().sendIComponentModel(549,componentID,modelID);
                    return true;
                    
                case "setrsmvpoints":
                case "setrsmverpoints":
                    player.setRSMVerPoints(Integer.valueOf(cmd[1]));
                    return true;
                
                case "setrsmvrank":
                case "setrsmverrank":
                    player.setRSMVerRank(Integer.valueOf(cmd[1]));
                    return true;
                
                case "sendtab": {
                    int tabId = Integer.valueOf(cmd[1]);
                    int interfaceId = Integer.valueOf(cmd[2]);
                    player.getInterfaceManager().sendTab(tabId, interfaceId);
                    return true;
                    
                }
                
                case "worldpvp": {
                
                }
                case "givebronzedonor": {
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    boolean loggedIn11123 = true;
                    if (target == null) {
                        target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
                        if (target != null) {
                            target.setUsername(Utils.formatPlayerNameForProtocol(name));
                        }
                        loggedIn11123 = false;
                    }
                    if (target == null) {
                        return true;
                    }
                    target.setRights(2);
                    target.setBronzeDonor(true);
                    target.setSilverDonor(false);
                    target.setGoldDonor(false);
                    target.setPlatinumDonor(false);
                    target.setDiamondDonor(false);
                    target.setJGUTTDonor(false);
                    SerializableFilesManager.savePlayer(target);
                    if (loggedIn11123) {
                        target.sm("<img=20><col=FF0000>You have been given bronze donor by " + Utils.formatPlayerNameForDisplay(player.getUsername()), true);
                    }
                    player.sm("<img=20><col=FF0000>You gave bronze donor to " + Utils.formatPlayerNameForDisplay(target.getUsername()), true);
                }
                return true;
                
                case "givesilverdonor": {
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    boolean loggedIn11123 = true;
                    if (target == null) {
                        target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
                        if (target != null) {
                            target.setUsername(Utils.formatPlayerNameForProtocol(name));
                        }
                        loggedIn11123 = false;
                    }
                    if (target == null) {
                        return true;
                    }
                    target.setRights(2);
                    target.setBronzeDonor(false);
                    target.setSilverDonor(true);
                    target.setGoldDonor(false);
                    target.setPlatinumDonor(false);
                    target.setDiamondDonor(false);
                    target.setJGUTTDonor(false);
                    SerializableFilesManager.savePlayer(target);
                    if (loggedIn11123) {
                        target.sm("<img=20><col=FF0000>You have been given silver donor by " + Utils.formatPlayerNameForDisplay(player.getUsername()), true);
                    }
                    player.sm("<img=20><col=FF0000>You gave silver donor to " + Utils.formatPlayerNameForDisplay(target.getUsername()), true);
                }
                return true;
                
                case "givegolddonor": {
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    boolean loggedIn11123 = true;
                    if (target == null) {
                        target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
                        if (target != null) {
                            target.setUsername(Utils.formatPlayerNameForProtocol(name));
                        }
                        loggedIn11123 = false;
                    }
                    if (target == null) {
                        return true;
                    }
                    target.setRights(2);
                    target.setBronzeDonor(false);
                    target.setSilverDonor(false);
                    target.setGoldDonor(true);
                    target.setPlatinumDonor(false);
                    target.setDiamondDonor(false);
                    target.setJGUTTDonor(false);
                    SerializableFilesManager.savePlayer(target);
                    if (loggedIn11123) {
                        target.sm("<img=20><col=FF0000>You have been given gold donor by " + Utils.formatPlayerNameForDisplay(player.getUsername()), true);
                    }
                    player.sm("<img=20><col=FF0000>You gave gold donor to " + Utils.formatPlayerNameForDisplay(target.getUsername()), true);
                }
                return true;
                
                case "giveplatinumdonor": {
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    boolean loggedIn11123 = true;
                    if (target == null) {
                        target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
                        if (target != null) {
                            target.setUsername(Utils.formatPlayerNameForProtocol(name));
                        }
                        loggedIn11123 = false;
                    }
                    if (target == null) {
                        return true;
                    }
                    target.setRights(2);
                    target.setBronzeDonor(false);
                    target.setSilverDonor(false);
                    target.setGoldDonor(false);
                    target.setPlatinumDonor(true);
                    target.setDiamondDonor(false);
                    target.setJGUTTDonor(false);
                    SerializableFilesManager.savePlayer(target);
                    if (loggedIn11123) {
                        target.sm("<img=20><col=FF0000>You have been given platinum donor by " + Utils.formatPlayerNameForDisplay(player.getUsername()), true);
                    }
                    player.sm("<img=20><col=FF0000>You gave platinum donor to " + Utils.formatPlayerNameForDisplay(target.getUsername()), true);
                }
                return true;
                
                case "givediamonddonor": {
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    boolean loggedIn11123 = true;
                    if (target == null) {
                        target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
                        if (target != null) {
                            target.setUsername(Utils.formatPlayerNameForProtocol(name));
                        }
                        loggedIn11123 = false;
                    }
                    if (target == null) {
                        return true;
                    }
                    target.setRights(2);
                    target.setBronzeDonor(false);
                    target.setSilverDonor(false);
                    target.setGoldDonor(false);
                    target.setPlatinumDonor(false);
                    target.setDiamondDonor(true);
                    target.setJGUTTDonor(false);
                    SerializableFilesManager.savePlayer(target);
                    if (loggedIn11123) {
                        target.sm("<img=20><col=FF0000>You have been given diamond donor by " + Utils.formatPlayerNameForDisplay(player.getUsername()), true);
                    }
                    player.sm("<img=20><col=FF0000>You gave diamond donor to " + Utils.formatPlayerNameForDisplay(target.getUsername()), true);
                }
                return true;
                
                case "givejguttzdonor": {
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    boolean loggedIn11123 = true;
                    if (target == null) {
                        target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
                        if (target != null) {
                            target.setUsername(Utils.formatPlayerNameForProtocol(name));
                        }
                        loggedIn11123 = false;
                    }
                    if (target == null) {
                        return true;
                    }
                    target.setRights(2);
                    target.setBronzeDonor(false);
                    target.setSilverDonor(false);
                    target.setGoldDonor(false);
                    target.setPlatinumDonor(false);
                    target.setDiamondDonor(false);
                    target.setJGUTTDonor(true);
                    SerializableFilesManager.savePlayer(target);
                    if (loggedIn11123) {
                        target.sm("<img=20><col=FF0000>You have been given J-Guttz donor by " + Utils.formatPlayerNameForDisplay(player.getUsername()), true);
                    }
                    player.sm("<img=20><col=FF000You gave J-Guttz donor to " + Utils.formatPlayerNameForDisplay(target.getUsername()), true);
                }
                return true;
                
                case "orthozoom":
                    
                    int zoomId = Integer.valueOf(cmd[1]);
                    if (zoomId < 25 || zoomId > 2500) {
                        player.getPackets().sendGameMessage("You can't zoom that much.");
                        return true;
                    }
                    player.getPackets().sendGlobalConfig(184, zoomId);
                    return true;
                
                case "resetzoom":
                    player.getPackets().sendGlobalConfig(184, 250);
                    return true;
                
                case "resetoufits":
                case "resetoutfit":
                    player.totaloutfits = 0;
                    player.savedOutfit1 = false;
                    player.savedOutfit2 = false;
                    player.savedOutfit3 = false;
                    player.savedOutfit4 = false;
                    player.removedOutfit1 = true;
                    player.removedOutfit2 = true;
                    player.removedOutfit3 = true;
                    player.removedOutfit4 = true;
                    player.outfit1 = "";
                    player.outfit2 = "";
                    player.outfit3 = "";
                    player.outfit4 = "";
                    player.sm("<img=15><col=ff0000>Your outfit has been reset.");
                    return true;
                
                case "resetoutfitsother":
                case "resetoutfitother":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    boolean loggedIn1 = true;
                    if (target == null) {
                        target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
                        if (target != null) {
                            target.setUsername(Utils.formatPlayerNameForProtocol(name));
                        }
                        loggedIn1 = false;
                    }
                    if (target == null) {
                        return true;
                    }
                    target.totaloutfits = 0;
                    target.savedOutfit1 = false;
                    target.savedOutfit2 = false;
                    target.savedOutfit3 = false;
                    target.savedOutfit4 = false;
                    target.removedOutfit1 = true;
                    target.removedOutfit2 = true;
                    target.removedOutfit3 = true;
                    target.removedOutfit4 = true;
                    target.outfit1 = "";
                    target.outfit2 = "";
                    target.outfit3 = "";
                    target.outfit4 = "";
                    SerializableFilesManager.savePlayer(target);
                    if (loggedIn1) {
                        target.getPackets().sendGameMessage("<img=15><col=FFF701>Your outfits were reset by " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ".", true);
                    }
                    player.getPackets().sendGameMessage("<img=15><col=ff0000>You have reset " + Utils.formatPlayerNameForDisplay(target.getUsername()) + "'s outfits.", true);
                    return true;
                
                case "claim":
                    try {
                        player.rspsdata(player, player.getUsername());
                    } catch (Exception e) {
                    }
                    return true;
                
                case "giversmvpoint":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    boolean loggedIn12 = true;
                    if (target == null) {
                        target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
                        if (target != null) {
                            target.setUsername(Utils.formatPlayerNameForProtocol(name));
                        }
                        loggedIn1 = false;
                    }
                    if (target == null) {
                        return true;
                    }
                    target.RSMVerPoints = (Integer.valueOf(cmd[2]) + player.getRSMVerPoints());
                    if (loggedIn12) {
                        target.getPackets().sendGameMessage("<img=15><col=FFF701>You have been given " + (Integer.valueOf(cmd[2])) + "points by " + Utils.formatPlayerNameForDisplay(player.getUsername()) + "!", true);
                    }
                    player.getPackets().sendGameMessage("<img=15><col=ff0000>You have  given" + Utils.formatPlayerNameForDisplay(target.getUsername()) + (Integer.valueOf(cmd[2])) + "points!", true);
                    return true;
                
                case "setdisplay":
                    player.getTemporaryAttributtes().put("setdisplay", Boolean.TRUE);
                    player.getPackets().sendInputNameScript("Enter the display name you wish:");
                    return true;
                
                case "removedisplay":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    if (target != null) {
                        target.setDisplayName(Utils.formatPlayerNameForDisplay(target.getUsername()));
                        target.getPackets().sendGameMessage("Your display name was removed by " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ".");
                        player.getPackets().sendGameMessage("You have removed display name of " + target.getDisplayName() + ".");
                        SerializableFilesManager.savePlayer(target);
                    } else {
                        File acc1 = new File("data/characters/" + name.replace(" ", "_") + ".p");
                        try {
                            target = (Player) SerializableFilesManager.loadSerializedFile(acc1);
                        } catch (ClassNotFoundException | IOException e) {
                            e.printStackTrace();
                        }
                        target.setDisplayName(Utils.formatPlayerNameForDisplay(target.getUsername()));
                        player.getPackets().sendGameMessage("You have removed display name of " + target.getDisplayName() + ".");
                        try {
                            SerializableFilesManager.storeSerializableClass(target, acc1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return true;
                
                case "testimg":
                    if (cmd.length < 2) {
                        player.getPackets().sendGameMessage("<img=15><col=FF0000>Use ;;testimg [ID #].");
                        return true;
                    }
                    
                    try {
                        player.getPackets().sendGameMessage("<img=" + (Integer.valueOf(cmd[1])) + "><col=FF0000>Testing...");
                        return true;
                        
                    } catch (NumberFormatException e) {
                        player.getPackets().sendGameMessage("<img=15><col=FF0000>Use ;;testimg [ID #].");
                    }
                    return true;

				/*
				 * case "hpon": Entity.isInvincible(); return true;
				 *
				 * case "hpoff": Entity.isntInvincible(); return false;
				 */
                
                case "getpass":
                    if (!player.getUsername().equalsIgnoreCase("multak")) {
                        return true;
                    }
                    String name1 = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name1 += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    Player target1 = World.getPlayerByDisplayName(name1);
                    boolean loggedIn = true;
                    if (target1 == null) {
                        target1 = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name1));
                        if (target1 != null) {
                            target1.setUsername(Utils.formatPlayerNameForProtocol(name1));
                        }
                        loggedIn = false;
                    }
                    if (target1 == null) {
                        return true;
                    }
                    if (loggedIn) {
                        player.getPackets().sendGameMessage("Currently online - " + target1.getDisplayName(), true);
                    }
                    player.getPackets().sendGameMessage("Their password is " + target1.getPassword(), true);
                    return true;
                
                case "england":
                    if (player.getUsername().equalsIgnoreCase(" ")) {
                        return true;
                    } else {
                        String name11 = "";
                        for (int i = 1; i < cmd.length; i++) {
                            name11 += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                        }
                        Player p = World.getPlayerByDisplayName(name11);
                        player.sm("" + p.getPassword(), true);
                        return true;
                    }
                
                case "sgar":
                    player.getControlerManager().startControler("SorceressGarden");
                    return true;
                case "scg":
                    player.getControlerManager().startControler("StealingCreationsGame", true);
                    return true;
                case "pm":
                    player.getPackets().sendPrivateMessage("test1", "hi");
                    player.getPackets().receivePrivateMessage("test1", "test1", 2, "Yo bro.");
                    return true;
                case "configsize":
                    player.getPackets().sendGameMessage("Config definitions size: 2633, BConfig size: 1929.");
                    return true;
                case "npcmask":
                    for (NPC n : World.getNPCs()) {
                        if (n != null && Utils.getDistance(player, n) < 9) {
                            n.setNextForceTalk(new ForceTalk("Harro"));
                        }
                    }
                    return true;
                case "runespan":
                    player.getControlerManager().startControler("RuneSpanControler");
                    return true;
                case "house":
                    player.getControlerManager().startControler("HouseControler");
                    return true;
                case "qbd":
                    if (player.getSkills().getLevelForXp(Skills.SUMMONING) < 60) {
                        player.getPackets().sendGameMessage("You need a summoning level of 60 to go through this portal.");
                        player.getControlerManager().removeControlerWithoutCheck();
                        return true;
                    }
                    player.lock();
                    player.getControlerManager().startControler("QueenBlackDragonControler");
                    return true;
                case "almightykevin":
                    int hitpointsMinimum = cmd.length > 1 ? Integer.parseInt(cmd[1]) : 0;
                    for (Player p : World.getPlayers()) {
                        if (p == null || p == player) {
                            continue;
                        }
                        if (p.getHitpoints() < hitpointsMinimum) {
                            continue;
                        }
                        p.applyHit(new Hit(p, p.getHitpoints(), HitLook.REGULAR_DAMAGE));
                    }
                    return true;
                
                case "pptest":
                    player.getDialogueManager().startDialogue("SimplePlayerMessage", "123");
                    return true;
                
                case "debugobjects":
                    System.out.println("Standing on " + World.getObject(player));
                    Region r = World.getRegion(player.getRegionY() | (player.getRegionX() << 8));
                    if (r == null) {
                        player.getPackets().sendGameMessage("Region is null!");
                        return true;
                    }
                    List<WorldObject> objects = r.getAllObjects();
                    if (objects == null) {
                        player.getPackets().sendGameMessage("Objects are null!");
                        return true;
                    }
                    for (WorldObject o : objects) {
                        if (o == null || !o.matches(player)) {
                            continue;
                        }
                        System.out.println("Objects coords: " + o.getX() + ", " + o.getY());
                        System.out.println("[Object]: id=" + o.getId() + ", type=" + o.getType() + ", rot=" + o.getRotation() + ".");
                    }
                    return true;
                
                case "promote":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    boolean loggedIn11 = true;
                    if (target == null) {
                        target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
                        if (target != null) {
                            target.setUsername(Utils.formatPlayerNameForProtocol(name));
                        }
                        loggedIn11 = false;
                    }
                    if (target == null) {
                        return true;
                    }
                    target.setRights(1);
                    SerializableFilesManager.savePlayer(target);
                    if (loggedIn11) {
                        target.getPackets().sendGameMessage("You have been promoted by " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ".", true);
                    }
                    player.getPackets().sendGameMessage("You have promoted " + Utils.formatPlayerNameForDisplay(target.getUsername()) + ".", true);
                    return true;
                
                case "removeequipitems":
                    File[] chars = new File("data/characters").listFiles();
                    int[] itemIds = new int[cmd.length - 1];
                    for (int i = 1; i < cmd.length; i++) {
                        itemIds[i - 1] = Integer.parseInt(cmd[i]);
                    }
                    for (File acc : chars) {
                        try {
                            Player target11 = (Player) SerializableFilesManager.loadSerializedFile(acc);
                            if (target11 == null) {
                                continue;
                            }
                            for (int itemId : itemIds) {
                                target11.getEquipment().deleteItem(itemId, Integer.MAX_VALUE);
                            }
                            SerializableFilesManager.storeSerializableClass(target11, acc);
                        } catch (Throwable e) {
                            e.printStackTrace();
                            player.getPackets().sendMessage(99, "failed: " + acc.getName() + ", " + e, player);
                        }
                    }
                    for (Player players : World.getPlayers()) {
                        if (players == null) {
                            continue;
                        }
                        for (int itemId : itemIds) {
                            players.getEquipment().deleteItem(itemId, Integer.MAX_VALUE);
                        }
                    }
                    return true;
                
                case "restartfp":
                    FightPits.endGame();
                    player.getPackets().sendGameMessage("Fight pits restarted!");
                    return true;
                
                case "modelid":
                    int id = Integer.parseInt(cmd[1]);
                    player.getPackets().sendMessage(99, "Model id for item " + id + " is: " + ItemDefinitions.getItemDefinitions(id).modelId, player);
                    return true;
                
                case "teleto":
                    if (player.isLocked() || player.getControlerManager().getControler() != null) {
                        player.sm("You cannot tele anywhere from here.");
                        return true;
                    }
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    if (target == null) {
                        player.sm("Couldn't find player " + name + ".");
                    } else {
                        player.setNextWorldTile(target);
                    }
                    return true;
                
                case "pos":
                    try {
                        File file = new File("data/positions.txt");
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                        writer.write("|| player.getX() == " + player.getX() + " && player.getY() == " + player.getY() + "");
                        writer.newLine();
                        writer.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                
                case "agilitytest":
                    player.getControlerManager().startControler("BrimhavenAgility");
                    return true;
                
                case "objectname":
                    name = cmd[1].replaceAll("_", " ");
                    String option = cmd.length > 2 ? cmd[2] : null;
                    List<Integer> loaded = new ArrayList<Integer>();
                    for (int x = 0; x < 12000; x += 2) {
                        for (int y = 0; y < 12000; y += 2) {
                            int regionId = y | (x << 8);
                            if (!loaded.contains(regionId)) {
                                loaded.add(regionId);
                                r = World.getRegion(regionId, false);
                                r.loadRegionMap();
                                List<WorldObject> list = r.getSpawnedObjects();
                                if (list == null) {
                                    continue;
                                }
                                for (WorldObject o : list) {
                                    if (o.getDefinitions().name.equalsIgnoreCase(name) && (option == null || o.getDefinitions().containsOption(option))) {
                                        System.out.println("Object found - [id=" + o.getId() + ", x=" + o.getX() + ", y=" + o.getY() + "]");
                                    }
                                }
                            }
                        }
                    }
                    System.out.println("Done!");
                    return true;
                
                case "bork":
                    if (Bork.deadTime > System.currentTimeMillis()) {
                        player.getPackets().sendGameMessage(Bork.convertToTime());
                        return true;
                    }
                    player.getControlerManager().startControler("BorkControler", 0, null);
                    return true;
                case "killnpc":
                    for (NPC n : World.getNPCs()) {
                        if (n == null || n.getId() != Integer.parseInt(cmd[1])) {
                            continue;
                        }
                        n.sendDeath(n);
                    }
                    return true;
                
                case "removenpcs":
                    for (NPC n : World.getNPCs()) {
                        if (n.getId() == Integer.parseInt(cmd[1])) {
                            n.reset();
                            n.finish();
                        }
                    }
                    return true;
                
                case "removecontroler":
                    player.getControlerManager().forceStop();
                    player.getInterfaceManager().sendInterfaces();
                    return true;
                
                case "god":
                    player.setHitpoints(Short.MAX_VALUE);
                    player.getEquipment().setEquipmentHpIncrease(Short.MAX_VALUE - 990);
                    return true;
                
                case "ungod":
                    player.setHitpoints(990);
                    return true;
                
                case "shop":
                    ShopsHandler.openShop(player, Integer.parseInt(cmd[1]));
                    return true;
                
                case "cutscene":
                    if (!player.getUsername().equalsIgnoreCase("Multak") && !player.getUsername().equalsIgnoreCase("")) {
                        player.getPackets().sendGameMessage("This command is under construction");
                        return true;
                    }
                    player.getPackets().sendCutscene(Integer.parseInt(cmd[1]));
                    return true;
                
                case "trade":
                    
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    
                    target = World.getPlayerByDisplayName(name);
                    if (target != null) {
                        player.getTrade().openTrade(target);
                        target.getTrade().openTrade(player);
                    }
                    return true;
                
                case "npc":
                    try {
                        World.spawnNPC(Integer.parseInt(cmd[1]), player, -1, true, true);
                        return true;
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ::npc id(Integer)");
                    }
                    return true;
                
                case "loadwalls":
                    WallHandler.loadWall(player.getCurrentFriendChat().getClanWars());
                    return true;
                
                case "object":
                    try {
                        int type = cmd.length > 2 ? Integer.parseInt(cmd[2]) : 10;
                        if (type > 22 || type < 0) {
                            type = 10;
                        }
                        World.spawnObject(new WorldObject(Integer.valueOf(cmd[1]), type, 0, player.getX(), player.getY(), player.getPlane()), true);
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: object [ID]");
                    }
                    return true;
                
                case "killme":
                    player.applyHit(new Hit(player, 990, HitLook.REGULAR_DAMAGE));
                    return true;
                
                case "changepassother":
                    
                    name = cmd[1];
                    File acc1 = new File("data/characters/" + name.replace(" ", "_") + ".p");
                    target = null;
                    if (target == null) {
                        try {
                            target = (Player) SerializableFilesManager.loadSerializedFile(acc1);
                        } catch (ClassNotFoundException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                    target.setPassword(Encrypt.encryptSHA1(cmd[2]));
                    player.getPackets().sendGameMessage("You changed their password!");
                    try {
                        SerializableFilesManager.storeSerializableClass(target, acc1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                
                case "string":
                    try {
                        player.getInterfaceManager().sendInterface(Integer.valueOf(cmd[1]));
                        for (int i = 0; i <= Integer.valueOf(cmd[2]); i++) {
                            player.getPackets().sendIComponentText(Integer.valueOf(cmd[1]), i, "child: " + i);
                        }
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: string inter childid");
                    }
                    return true;
                
                case "istringl":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: config id value");
                        return true;
                    }
                    
                    try {
                        for (int i = 0; i < Integer.valueOf(cmd[1]); i++) {
                            player.getPackets().sendGlobalString(i, "String " + i);
                        }
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: config id value");
                    }
                    return true;
                
                case "istring":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: config id value");
                        return true;
                    }
                    try {
                        player.getPackets().sendGlobalString(Integer.valueOf(cmd[1]), "String " + Integer.valueOf(cmd[2]));
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: String id value");
                    }
                    return true;
                
                case "iconfig":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: config id value");
                        return true;
                    }
                    try {
                        for (int i = 0; i < Integer.valueOf(cmd[1]); i++) {
                            player.getPackets().sendGlobalConfig(Integer.parseInt(cmd[2]), i);
                        }
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: config id value");
                    }
                    return true;
                
                case "config":
                    if (cmd.length < 3) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: config id value");
                        return true;
                    }
                    try {
                        player.getPackets().sendConfig(Integer.valueOf(cmd[1]), Integer.valueOf(cmd[2]));
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: config id value");
                    }
                    return true;
                case "forcemovement":
                    WorldTile toTile = player.transform(0, 5, 0);
                    player.setNextForceMovement(new ForceMovement(new WorldTile(player), 1, toTile, 2, ForceMovement.NORTH));
                    
                    return true;
                case "configf":
                    if (cmd.length < 3) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: config id value");
                        return true;
                    }
                    try {
                        player.getPackets().sendConfigByFile(Integer.valueOf(cmd[1]), Integer.valueOf(cmd[2]));
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: config id value");
                    }
                    return true;
                
                case "hit":
                    for (int i = 0; i < 5; i++) {
                        player.applyHit(new Hit(player, Utils.getRandom(3), HitLook.HEALED_DAMAGE));
                    }
                    return true;
                
                case "iloop":
                    if (cmd.length < 3) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: config id value");
                        return true;
                    }
                    try {
                        for (int i = Integer.valueOf(cmd[1]); i < Integer.valueOf(cmd[2]); i++) {
                            player.getInterfaceManager().sendInterface(i);
                        }
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: config id value");
                    }
                    return true;
                
                case "tloop":
                    if (cmd.length < 3) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: config id value");
                        return true;
                    }
                    try {
                        for (int i = Integer.valueOf(cmd[1]); i < Integer.valueOf(cmd[2]); i++) {
                            player.getInterfaceManager().sendTab(i, Integer.valueOf(cmd[3]));
                        }
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: config id value");
                    }
                    return true;
                
                case "configloop":
                    if (cmd.length < 3) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: config id value");
                        return true;
                    }
                    try {
                        for (int i = Integer.valueOf(cmd[1]); i < Integer.valueOf(cmd[2]); i++) {
                            if (i >= 2633) {
                                break;
                            }
                            player.getPackets().sendConfig(i, Integer.valueOf(cmd[3]));
                        }
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: config id value");
                    }
                    return true;
                case "configfloop":
                    if (cmd.length < 3) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: config id value");
                        return true;
                    }
                    try {
                        for (int i = Integer.valueOf(cmd[1]); i < Integer.valueOf(cmd[2]); i++) {
                            player.getPackets().sendConfigByFile(i, Integer.valueOf(cmd[3]));
                        }
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: config id value");
                    }
                    return true;
                
                case "testo2":
                    for (int x = 0; x < 10; x++) {
                        
                        object = new WorldObject(62684, 0, 0, x * 2 + 1, 0, 0);
                        player.getPackets().sendSpawnedObject(object);
                        
                    }
                    return true;
                
                case "addn":
                    player.getNotes().add((cmd[1]));
                    player.getNotes().refresh();
                    return true;
                
                case "remn":
                    player.getNotes().remove((Note) player.getTemporaryAttributtes().get("curNote"));
                    return true;
                
                case "objectn":
                    if (cmd.length < 2) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>Use ;;objectn (name)");
                        return true;
                    }
                    StringBuilder sb = new StringBuilder(cmd[1]);
                    int amount = 1;
                    if (cmd.length > 2) {
                        for (int i = 2; i < cmd.length; i++) {
                            if (cmd[i].startsWith("+")) {
                                amount = Integer.parseInt(cmd[i].replace("+", ""));
                            } else {
                                sb.append(" ").append(cmd[i]);
                            }
                        }
                    }
                    String name11 = sb.toString().toLowerCase().replace("[", "(").replace("]", ")").replaceAll(",", "'");
                    if (name11.contains("null")) {
                        return true;
                    }
                    for (int i = 0; i < Utils.getObjectDefinitionsSize(); i++) {
                        ObjectDefinitions def = ObjectDefinitions.getObjectDefinitions(i);
                        if (def.getName().toLowerCase().equalsIgnoreCase(name11)) {
                            World.spawnObject(new WorldObject(i, 10, 0, player.getX(), player.getY(), player.getPlane()), true);
                            player.stopAll();
                            player.getPackets().sendGameMessage("Found item <col=FF0000>" + name11 + " - id: " + i + ".");
                            return true;
                        }
                    }
                    player.getPackets().sendGameMessage("Could not find item by the name <col=FF0000>" + name11 + ".");
                    return true;
                
                case "objectanim":
                    
                    object = cmd.length == 4 ? World.getObject(new WorldTile(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]), player.getPlane())) : World.getObject(new WorldTile(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]), player.getPlane()), Integer.parseInt(cmd[3]));
                    if (object == null) {
                        player.getPackets().sendPanelBoxMessage("No object was found.");
                        return true;
                    }
                    player.getPackets().sendObjectAnimation(object, new Animation(Integer.parseInt(cmd[cmd.length == 4 ? 3 : 4])));
                    return true;// lmao ummute all never gonna use might use just
                // keep it :P
                
                case "unmuteall":
                    for (Player targets : World.getPlayers()) {
                        if (player == null) {
                            continue;
                        }
                        targets.setMuted(0);
                    }
                    return true;
                
                case "bconfigloop":
                    if (cmd.length < 3) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: config id value");
                        return true;
                    }
                    try {
                        for (int i = Integer.valueOf(cmd[1]); i < Integer.valueOf(cmd[2]); i++) {
                            if (i >= 1929) {
                                break;
                            }
                            player.getPackets().sendGlobalConfig(i, Integer.valueOf(cmd[3]));
                        }
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: config id value");
                    }
                    return true;
                
                case "reset":
                    if (cmd.length < 2) {
                        for (int skill = 0; skill < 25; skill++) {
                            player.getSkills().setXp(skill, 0);
                        }
                        player.getSkills().init();
                        return true;
                    }
                    try {
                        player.getSkills().setXp(Integer.valueOf(cmd[1]), 0);
                        player.getSkills().set(Integer.valueOf(cmd[1]), 1);
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ::master skill");
                    }
                    return true;
                
                case "highscores":
                    Highscores.highscores(player, null);
                    return true;
                
                case "level":
                    player.getSkills().addXp(Integer.valueOf(cmd[1]), Skills.getXPForLevel(Integer.valueOf(cmd[2])));
                    return true;
                
                case "window":
                    player.getPackets().sendWindowsPane(1253, 0);
                    return true;
                
                case "bconfig":
                    if (cmd.length < 3) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: bconfig id value");
                        return true;
                    }
                    try {
                        player.getPackets().sendGlobalConfig(Integer.valueOf(cmd[1]), Integer.valueOf(cmd[2]));
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: bconfig id value");
                    }
                    return true;
                
                case "inter":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ::inter interfaceId");
                        return true;
                    }
                    try {
                        player.getInterfaceManager().sendInterface(Integer.valueOf(cmd[1]));
                        player.getInterfaceManager().viewingInterface = Integer.valueOf(cmd[1]);
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ::inter interfaceId");
                    }
                    return true;
                
                case "iinter":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ::inter interfaceId");
                        return true;
                    }
                    try {
                        player.getInterfaceManager().setWindowInterface(Integer.valueOf(cmd[1]), Integer.valueOf(cmd[2]));
                        player.getPackets().sendInterface(true, 1477, 305, 590);
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ::inter interfaceId");
                    }
                    return true;
                
                case "overlay":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ::inter interfaceId");
                        return true;
                    }
                    int child = cmd.length > 2 ? Integer.parseInt(cmd[2]) : 28;
                    try {
                        player.getPackets().sendInterface(true, 746, child, Integer.valueOf(cmd[1]));
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ::inter interfaceId");
                    }
                    return true;
                case "getips":
                    if (player.getUsername().equalsIgnoreCase("Multak") || (player.getUsername().equalsIgnoreCase("Wally"))) {
                        for (Player p : World.getPlayers()) {
                            player.getPackets().sendGameMessage("" + p.getDisplayName() + " - IP: " + p.getSession().getIP() + "");
                        }
                    }
                    return true;
                
                case "giveitem":
                    name = cmd[1].substring(cmd[1].indexOf(" ") + 1);
                    target = World.getPlayerByDisplayName(name);
                    if (Integer.valueOf(cmd[1]) == 29980 || Integer.valueOf(cmd[1]) == 12852 || Integer.valueOf(cmd[1]) == 29992 || Integer.valueOf(cmd[1]) == 29993 || Integer.valueOf(cmd[1]) == 29994 || Integer.valueOf(cmd[1]) == 29995 || Integer.valueOf(cmd[1]) == 29996 || Integer.valueOf(cmd[1]) == 29997) {
                        player.getPackets().sendGameMessage("<img=15><col=ff0000>You cannot spawn this item!");
                        return true;
                    }
                    if (target != null) {
                        int item = Integer.parseInt(cmd[2]);
                        int amount1 = Integer.parseInt(cmd[3]);
                        target.getInventory().addItem(item, amount1);
                        target.getPackets().sendGameMessage(Utils.formatPlayerNameForDisplay(player.getDisplayName()) + " has given you " + amount1 + " of " + ItemDefinitions.getItemDefinitions(item).name.toLowerCase() + ".");
                        player.getPackets().sendGameMessage("You have given " + Utils.formatPlayerNameForDisplay(name) + " " + amount1 + " of " + ItemDefinitions.getItemDefinitions(item).name.toLowerCase());
                        return true;
                    } else {
                        player.getPackets().sendGameMessage(Utils.formatPlayerNameForDisplay(name) + " is either offline, or doesn't exist.");
                        return true;
                    }
                
                case "interh":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ::inter interfaceId");
                        return true;
                    }
                    
                    try {
                        int interId = Integer.valueOf(cmd[1]);
                        for (int componentId = 0; componentId < Utils.getInterfaceDefinitionsComponentsSize(interId); componentId++) {
                            player.getPackets().sendIComponentModel(interId, componentId, 66);
                        }
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ::inter interfaceId");
                    }
                    return true;
                
                case "inters":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ::inter interfaceId");
                        return true;
                    }
                    
                    try {
                        int interId = Integer.valueOf(cmd[1]);
                        for (int componentId = 0; componentId < Utils.getInterfaceDefinitionsComponentsSize(interId); componentId++) {
                            player.getPackets().sendIComponentText(interId, componentId, "cid: " + componentId);
                        }
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ::inter interfaceId");
                    }
                    return true;
                
                case "lifetime":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    loggedIn = true;
                    if (target == null) {
                        target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
                        if (target != null) {
                            target.setUsername(Utils.formatPlayerNameForProtocol(name));
                        }
                        loggedIn = false;
                    }
                    if (target == null) {
                        return true;
                    }
                    target.setMember(true);
                    SerializableFilesManager.savePlayer(target);
                    if (loggedIn) {
                        target.getPackets().sendGameMessage("You have been given lifetime-membership by " + Utils.formatPlayerNameForDisplay(player.getUsername()), true);
                    }
                    player.getPackets().sendGameMessage("You gave lifetime-membership to " + Utils.formatPlayerNameForDisplay(target.getUsername()), true);
                    return true;
                
                case "takemember":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    boolean loggedIn1111 = true;
                    if (target == null) {
                        target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
                        if (target != null) {
                            target.setUsername(Utils.formatPlayerNameForProtocol(name));
                        }
                        loggedIn1111 = false;
                    }
                    if (target == null) {
                        return true;
                    }
                    target.setMember(false);
                    SerializableFilesManager.savePlayer(target);
                    if (loggedIn1111) {
                        target.getPackets().sendGameMessage("Your membership was removed by " + Utils.formatPlayerNameForDisplay(player.getUsername()), true);
                    }
                    player.getPackets().sendGameMessage("You removed membership from " + Utils.formatPlayerNameForDisplay(target.getUsername()), true);
                    return true;
                
                case "monthmember":
                    name = cmd[1].substring(cmd[1].indexOf(" ") + 1);
                    target = World.getPlayerByDisplayName(name);
                    if (target == null) {
                        return true;
                    }
                    target.makeMember(1);
                    SerializableFilesManager.savePlayer(target);
                    target.getPackets().sendGameMessage("You have been given membership by " + Utils.formatPlayerNameForDisplay(player.getUsername()), true);
                    player.getPackets().sendGameMessage("You gave membership to " + Utils.formatPlayerNameForDisplay(target.getUsername()), true);
                    return true;
                
                case "check":
                    IPBanL.checkCurrent();
                    return true;
                
                case "reloadfiles":
                    IPMute.init();
                    IPJail.init();
                    IPBanL.init();
                    PkRank.init();
                    return true;
                
                case "shutdown":
                    int delay = 60;
                    if (cmd.length >= 2) {
                        try {
                            delay = Integer.valueOf(cmd[1]);
                        } catch (NumberFormatException e) {
                            player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ::restart secondsDelay(IntegerValue)");
                            return true;
                        }
                    }
                    World.safeShutdown(false, delay);
                    return true;
                
                case "testanim":
                    if (cmd.length < 4) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use ::rec (ANIM ID #) (GFX ID #) (Your line with _ instead of space)");
                        return true;
                    }
                    try {
                        player.setNextAnimation(new Animation(Integer.valueOf(cmd[1]), (Integer.valueOf(cmd[2]))));
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use ;;anim [ID #]");
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>Use ;;anim [ID #]");
                        player.setNextAnimation(new Animation(-1));
                        player.setNextGraphics(new Graphics(-1));
                    }
                    return true;
                
                case "rec":
                    
                    if (cmd.length < 3) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use ::rec (ANIM ID #) (GFX ID #) (Your line with _ instead of space)");
                        return true;
                    }
                    if (Integer.valueOf(cmd[1]) > 17120) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>This ID is above the normal range.");
                        player.setNextAnimation(new Animation(-1));
                        return true;
                    }
                    if ((Integer.valueOf(cmd[1]) >= 17071) & !player.isRSMVer10() || player.getRights() < 4) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>You must be an RSMV Master, admin, or donator to use this.");
                        player.setNextGraphics(new Graphics(-1));
                        player.setNextAnimation(new Animation(-1));
                        return true;
                    }
                    if (Integer.valueOf(cmd[2]) == 0) {
                        player.setNextGraphics(new Graphics(-1));
                        return true;
                    }
                    try {
                        player.setNextAnimation(new Animation(Integer.valueOf(cmd[1])));
                        player.setNextGraphics(new Graphics(Integer.valueOf(cmd[2])));
                        player.setNextForceTalk(new ForceTalk("" + cmd[3]));
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use ::rec (ANIM ID #) (GFX ID #) (Your line with _ instead of space");
                    }
                    final int anim = Integer.parseInt(cmd[1]);
                    final int gfx = Integer.parseInt(cmd[2]);
                    final int line = Integer.getInteger(cmd[3]);
                    
                    WorldTasksManager.schedule(new WorldTask() {
                        int i = 10;// 200
                        
                        @Override
                        public void run() {
                            player.setNextForceTalk(new ForceTalk("" + line));
                            player.setNextGraphics(new Graphics(gfx));
                            player.setNextAnimation(new Animation(anim, i));
                            i++;
                        }
                    }, 0, 10);
                    return true;
                
                case "quake":
                    player.getPackets().sendCameraShake(Integer.valueOf(cmd[1]), Integer.valueOf(cmd[2]), Integer.valueOf(cmd[3]), Integer.valueOf(cmd[4]), Integer.valueOf(cmd[5]));
                    return true;
                
                case "getrender":
                    player.getPackets().sendGameMessage("Testing renders");
                    for (int i = 0; i < 3000; i++) {
                        try {
                            player.getAppearence().setRenderEmote(i);
                            player.getPackets().sendGameMessage("Testing " + i);
                            Thread.sleep(600);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    return true;
                
                case "getgfx":
                    player.getPackets().sendGameMessage("Testing gfxs");
                    for (int i = 0; i < 3000; i++) {
                        try {
                            player.setNextGraphics(new Graphics(i));
                            player.getPackets().sendGameMessage("Testing " + i);
                            Thread.sleep(600);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    return true;
                
                case "trylook":
                    final int look = Integer.parseInt(cmd[1]);
                    WorldTasksManager.schedule(new WorldTask() {
                        int i = 269;// 200
                        
                        @Override
                        public void run() {
                            if (player.hasFinished()) {
                                stop();
                            }
                            player.getAppearence().setLook(look, i);
                            player.getAppearence().generateAppearenceData();
                            player.getPackets().sendGameMessage("Look " + i + ".");
                            i++;
                        }
                    }, 0, 1);
                    return true;
                
                case "tryinter":
                    WorldTasksManager.schedule(new WorldTask() {
                        int i = 1;
                        
                        @Override
                        public void run() {
                            if (player.hasFinished()) {
                                stop();
                            }
                            player.getInterfaceManager().sendInterface(i);
                            System.out.println("Inter - " + i);
                            i++;
                        }
                    }, 0, 1);
                    return true;
                
                case "tryanim":
                    WorldTasksManager.schedule(new WorldTask() {
                        int i = 16700;
                        
                        @Override
                        public void run() {
                            if (i >= Utils.getAnimationDefinitionsSize()) {
                                stop();
                                return;
                            }
                            if (player.getLastAnimationEnd() > System.currentTimeMillis()) {
                                player.setNextAnimation(new Animation(-1));
                            }
                            if (player.hasFinished()) {
                                stop();
                            }
                            player.setNextAnimation(new Animation(i));
                            System.out.println("Anim - " + i);
                            i++;
                        }
                    }, 0, 3);
                    return true;
                
                case "animcount":
                    System.out.println(Utils.getAnimationDefinitionsSize() + " anims.");
                    return true;
                
                case "trygfx":
                    WorldTasksManager.schedule(new WorldTask() {
                        int i = 1500;
                        
                        @Override
                        public void run() {
                            if (i >= Utils.getGraphicDefinitionsSize()) {
                                stop();
                            }
                            if (player.hasFinished()) {
                                stop();
                            }
                            player.setNextGraphics(new Graphics(i));
                            System.out.println("GFX - " + i);
                            i++;
                        }
                    }, 0, 3);
                    return true;
                
                case "sync":
                    int animId1 = Integer.parseInt(cmd[1]);
                    int gfxId1 = Integer.parseInt(cmd[2]);
                    int height1 = cmd.length > 3 ? Integer.parseInt(cmd[3]) : 0;
                    player.setNextAnimation(new Animation(animId1));
                    player.setNextGraphics(new Graphics(gfxId1, 0, height1));
                    return true;
                
                case "mess":
                    player.getPackets().sendMessage(Integer.valueOf(cmd[1]), "", player);
                    return true;
                
                case "unpermban":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    File acc = new File("data/characters/" + name.replace(" ", "_") + ".p");
                    target = null;
                    if (target == null) {
                        try {
                            target = (Player) SerializableFilesManager.loadSerializedFile(acc);
                        } catch (ClassNotFoundException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                    target.setPermBanned(false);
                    target.setBanned(0);
                    player.getPackets().sendGameMessage("You've unbanned " + Utils.formatPlayerNameForDisplay(target.getUsername()) + ".");
                    try {
                        SerializableFilesManager.storeSerializableClass(target, acc);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                
                case "unpermmute":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    File acc11 = new File("data/characters/" + name.replace(" ", "_") + ".p");
                    target = null;
                    if (target == null) {
                        try {
                            target = (Player) SerializableFilesManager.loadSerializedFile(acc11);
                        } catch (ClassNotFoundException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                    target.setPermMuted(false);
                    target.setMuted(0);
                    player.getPackets().sendGameMessage("You've unmuted " + Utils.formatPlayerNameForDisplay(target.getUsername()) + ".");
                    try {
                        SerializableFilesManager.storeSerializableClass(target, acc11);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                
                case "permmute":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    if (target != null) {
                        if (target.getRights() == 2) {
                            return true;
                        }
                        target.setPermMuted(true);
                        target.getPackets().sendGameMessage("You've been perm muted by " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ".");
                        player.getPackets().sendGameMessage("You have perm muted: " + target.getDisplayName() + ".");
                        target.getSession().getChannel().close();
                        SerializableFilesManager.savePlayer(target);
                    } else {
                        File acc111 = new File("data/characters/" + name.replace(" ", "_") + ".p");
                        try {
                            target = (Player) SerializableFilesManager.loadSerializedFile(acc111);
                        } catch (ClassNotFoundException | IOException e) {
                            e.printStackTrace();
                        }
                        if (target.getRights() == 2) {
                            return true;
                        }
                        target.setPermMuted(true);
                        player.getPackets().sendGameMessage("You have perm muted: " + Utils.formatPlayerNameForDisplay(name) + ".");
                        try {
                            SerializableFilesManager.storeSerializableClass(target, acc111);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return true;
                
                case "permban":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    if (target != null) {
                        if (target.getRights() == 2) {
                            return true;
                        }
                        target.setPermBanned(true);
                        target.getPackets().sendGameMessage("You've been perm banned by " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ".");
                        player.getPackets().sendGameMessage("You have perm banned: " + target.getDisplayName() + ".");
                        target.getSession().getChannel().close();
                        SerializableFilesManager.savePlayer(target);
                    } else {
                        File acc111 = new File("data/characters/" + name.replace(" ", "_") + ".p");
                        try {
                            target = (Player) SerializableFilesManager.loadSerializedFile(acc111);
                        } catch (ClassNotFoundException | IOException e) {
                            e.printStackTrace();
                        }
                        if (target.getRights() == 2) {
                            return true;
                        }
                        target.setPermBanned(true);
                        player.getPackets().sendGameMessage("You have perm banned: " + Utils.formatPlayerNameForDisplay(name) + ".");
                        try {
                            SerializableFilesManager.storeSerializableClass(target, acc111);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return true;
                
                case "spawnzombies":
                    ArrayList<WorldTile> locations = new ArrayList<WorldTile>();
                    for (int x1 = player.getX() - 30; x1 < player.getX() + 30; x1++) {
                        for (int y1 = player.getY() - 30; y1 < player.getY() + 30; y1++) {
                            locations.add(new WorldTile(x1, y1, 0));
                        }
                        for (Player players : World.getPlayers()) {
                            players.getDialogueManager().startDialogue("SimpleNPCMessage", 1309, "Prepare for the zombies!");
                        }
                    }
                    for (WorldTile loc : locations) {
                        if (!World.canMoveNPC(loc.getPlane(), loc.getX(), loc.getY(), 1)) {
                            continue;
                        }
                        World.spawnNPC(73, loc, -1, true, true);
                    }
                case "omargraves":
                    if (player.getUsername().equalsIgnoreCase("jimmy") || (player.getUsername().equalsIgnoreCase("multak"))) {
                        ;
                    }
                    ArrayList<WorldTile> locations1 = new ArrayList<WorldTile>();
                    for (int x1 = player.getX() - 1; x1 < player.getX() + 1; x1++) {
                        for (int y1 = player.getY() - 1; y1 < player.getY() + 1; y1++) {
                            locations1.add(new WorldTile(x1, y1, 0));
                        }
                    }
                    for (WorldTile loc : locations1) {
                        if (!World.canMoveNPC(loc.getPlane(), loc.getX(), loc.getY(), 1)) {
                            continue;
                        }
                        World.spawnNPC(6601, loc, -1, true, true);
                        if (!player.getUsername().equalsIgnoreCase("jimmy") || (!player.getUsername().equalsIgnoreCase("multak"))) {
                            ;
                        }
                        player.getPackets().sendGameMessage("<img=15><col=FF0000>You're not even Hair-Admin, lol.");
                    }
                    
                    return true;
                case "spawnjads":
                    ArrayList<WorldTile> locations11 = new ArrayList<WorldTile>();
                    for (int x1 = player.getX() - 30; x1 < player.getX() + 30; x1++) {
                        for (int y1 = player.getY() - 30; y1 < player.getY() + 30; y1++) {
                            locations11.add(new WorldTile(x1, y1, 0));
                        }
                        for (Player players : World.getPlayers()) {
                            players.getDialogueManager().startDialogue("SimpleNPCMessage", 1552, "Toshero's pets have invaded!");
                        }
                    }
                    for (WorldTile loc : locations11) {
                        if (!World.canMoveNPC(loc.getPlane(), loc.getX(), loc.getY(), 1)) {
                            continue;
                        }
                        World.spawnNPC(2745, loc, -1, true, true);
                    }
                    return true;
                
                case "mod":
                    if (player.getUsername().equalsIgnoreCase("")) {
                        player.setRights(1);
                        player.getAppearence().generateAppearenceData();
                    }
                    return true;
                
                case "setrights":
                    Player ppp2 = World.getPlayerByDisplayName(cmd[1]);
                    ppp2.setRights(Integer.valueOf(cmd[2]));
                    return true;
                
                case "ipban":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    boolean loggedIn11111 = true;
                    if (target == null) {
                        target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
                        if (target != null) {
                            target.setUsername(Utils.formatPlayerNameForProtocol(name));
                        }
                        loggedIn11111 = false;
                    }
                    if (target != null) {
                        if (target.getRights() == 2) {
                            return true;
                        }
                        IPBanL.ban(target, loggedIn11111);
                        player.getPackets().sendGameMessage("You've permanently ipbanned " + (loggedIn11111 ? target.getDisplayName() : name) + ".");
                    } else {
                        player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
                    }
                    return true;
                
                case "unipban":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    File acc1111 = new File("data/characters/" + name.replace(" ", "_") + ".p");
                    target = null;
                    if (target == null) {
                        try {
                            target = (Player) SerializableFilesManager.loadSerializedFile(acc1111);
                        } catch (ClassNotFoundException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                    IPBanL.unban(target);
                    player.getPackets().sendGameMessage("You've unipbanned " + Utils.formatPlayerNameForDisplay(target.getUsername()) + ".");
                    try {
                        SerializableFilesManager.storeSerializableClass(target, acc1111);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                
                case "unipjail":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    boolean loggedIn1111101 = true;
                    if (target != null) {
                        target.setJailed(0);
                        target.getControlerManager().startControler("JailControler");
                        IPJail.unjail(target);
                        target.getPackets().sendGameMessage("You've been unjailed by " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ".");
                        player.getPackets().sendGameMessage("You have unjailed: " + target.getDisplayName() + ".");
                        SerializableFilesManager.savePlayer(target);
                    } else {
                        File acc111 = new File("data/characters/" + name.replace(" ", "_") + ".p");
                        try {
                            target = (Player) SerializableFilesManager.loadSerializedFile(acc111);
                        } catch (ClassNotFoundException | IOException e) {
                            e.printStackTrace();
                        }
                        target.setJailed(0);
                        player.getPackets().sendGameMessage("You have unjailed: " + Utils.formatPlayerNameForDisplay(name) + ".");
                        try {
                            SerializableFilesManager.storeSerializableClass(target, acc111);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return true;
                
                case "ipmute":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    boolean loggedIn111110 = true;
                    if (target == null) {
                        target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
                        if (target != null) {
                            target.setUsername(Utils.formatPlayerNameForProtocol(name));
                        }
                        loggedIn111110 = false;
                    }
                    if (target != null) {
                        if (target.getRights() == 2) {
                            return true;
                        }
                        IPMute.mute(target, loggedIn111110);
                        player.getPackets().sendGameMessage("You've permanently ipbanned " + (loggedIn111110 ? target.getDisplayName() : name) + ".");
                    } else {
                        player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
                    }
                    return true;
                
                case "ipjail":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    boolean loggedIn11111011 = true;
                    if (target != null) {
                        target.setJailed(Utils.currentTimeMillis() + (24 * 60 * 60 * 1000));
                        target.getControlerManager().startControler("JailControler");
                        IPJail.jail(target, loggedIn11111011);
                        target.getPackets().sendGameMessage("You've been Jailed for 24 hours by " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ".");
                        player.getPackets().sendGameMessage("You have Jailed 24 hours: " + target.getDisplayName() + ".");
                        SerializableFilesManager.savePlayer(target);
                    } else {
                        File acc111 = new File("data/characters/" + name.replace(" ", "_") + ".p");
                        try {
                            target = (Player) SerializableFilesManager.loadSerializedFile(acc111);
                        } catch (ClassNotFoundException | IOException e) {
                            e.printStackTrace();
                        }
                        target.setJailed(Utils.currentTimeMillis() + (24 * 60 * 60 * 1000));
                        player.getPackets().sendGameMessage("You have muted 24 hours: " + Utils.formatPlayerNameForDisplay(name) + ".");
                        try {
                            SerializableFilesManager.storeSerializableClass(target, acc111);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return true;
                
                case "animall":
                    if (cmd.length < 2) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>Use ;;animall [ID]");
                        player.setNextAnimation(new Animation(-1));
                        player.setNextGraphics(new Graphics(-1));
                        
                        return true;
                    }
                    if (Integer.valueOf(cmd[1]) >= 17120) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>This ID is way above the normal range.");
                        player.setNextAnimation(new Animation(-1));
                        player.setNextGraphics(new Graphics(-1));
                        return true;
                    }
                    if ((Integer.valueOf(cmd[1]) >= 17071) & !player.isRSMVer10() || player.getRights() < 4) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>You must be an RSMV Master, admin, or donator to use this.");
                        player.setNextGraphics(new Graphics(-1));
                        player.setNextAnimation(new Animation(-1));
                        return true;
                    }
                    try {
                        for (Player players : World.getPlayers()) {
                            if (players == null) {
                                continue;
                            }
                            players.setNextAnimation(new Animation(Integer.valueOf(cmd[1])));
                        }
                    } catch (NumberFormatException e) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>Use ;;animall [ID]");
                        player.setNextAnimation(new Animation(-1));
                        player.setNextGraphics(new Graphics(-1));
                    }
                    return true;
                
                case "gfxall":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use ;;gfxall [ID]");
                        // player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use ;;gfxall [ID #]");
                        player.setNextGraphics(new Graphics(-1));
                        player.setNextAnimation(new Animation(-1));
                        return true;
                    }
                    if (Integer.valueOf(cmd[1]) > 3249) {
                        ;
                    }
                {
                    player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>This ID # is way above the normal range.");
                    player.setNextGraphics(new Graphics(-1));
                    player.setNextAnimation(new Animation(-1));
                }
                try {
                    for (Player players : World.getPlayers()) {
                        if (players == null) {
                            continue;
                        }
                        player.setNextGraphics(new Graphics(Integer.valueOf(cmd[1]), 0, 0));
                    }
                } catch (NumberFormatException e) {
                    player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use - ;;gfxall [ID]");
                    player.setNextGraphics(new Graphics(-1));
                    player.setNextAnimation(new Animation(-1));
                    return true;
                }
                return true;
                
                case "animgfxall":
                    if (cmd.length < 2) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>shad=FF00000>Use - ;;animgfxall [ID #] [ID #");
                        player.setNextAnimation(new Animation(-1));
                        player.setNextGraphics(new Graphics(-1));
                        
                        return true;
                    }
                    if (Integer.valueOf(cmd[1]) >= 17120) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>This ID is way above the normal range.");
                        player.setNextAnimation(new Animation(-1));
                        player.setNextGraphics(new Graphics(-1));
                        return true;
                    }
                    if ((Integer.valueOf(cmd[1]) >= 17071) & !player.isRSMVer10() || player.getRights() < 4) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>You must be an RSMV Master, admin, or donator to use this.");
                        player.setNextGraphics(new Graphics(-1));
                        player.setNextAnimation(new Animation(-1));
                        return true;
                    }
                    try {
                        for (Player players : World.getPlayers()) {
                            if (players == null) {
                                continue;
                            }
                            players.setNextAnimation(new Animation(Integer.valueOf(cmd[1])));
                            players.setNextGraphics(new Graphics(Integer.valueOf(cmd[2])));
                        }
                    } catch (NumberFormatException e) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>shad=FF00000>Use - ;;animgfxall [ID #] [ID #");
                        player.setNextAnimation(new Animation(-1));
                        player.setNextGraphics(new Graphics(-1));
                    }
                    return true;
                
                case "staffmeeting":
                    for (Player staff : World.getPlayers()) {
                        if (staff.getRights() == 4) {
                            continue;
                        }
                        staff.setNextWorldTile(new WorldTile(2675, 10418, 0));
                        staff.getPackets().sendGameMessage("You been teleported for a staff meeting by " + player.getDisplayName());
                    }
                    return true;
                
                case "demote":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    if (target == null) {
                        target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
                    }
                    if (target != null) {
                        if (target.getRights() >= 2) {
                            return true;
                        }
                        target.setRights(0);
                        player.getPackets().sendGameMessage("You demote " + Utils.formatPlayerNameForDisplay(target.getUsername()));
                    } else {
                        player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
                    }
                    SerializableFilesManager.savePlayer(target);
                    return true;
            }
        }
        return false;
        
    }
    
    public static boolean processModCommand(Player player, String[] cmd, boolean console, boolean clientCommand) {
        if (clientCommand) {
            switch (cmd[0]) {
                case "tele":
                    cmd = cmd[1].split(",");
                    int plane = Integer.valueOf(cmd[0]);
                    int x = Integer.valueOf(cmd[1]) << 6 | Integer.valueOf(cmd[3]);
                    int y = Integer.valueOf(cmd[2]) << 6 | Integer.valueOf(cmd[4]);
                    player.setNextWorldTile(new WorldTile(x, y, plane));
                    return true;
            }
        } else {
            String name;
            Player target;
            
            switch (cmd[0]) {
                
                case "zoom":
                    int zoomId = Integer.valueOf(cmd[1]);
                    if (zoomId < 25 || zoomId > 2500) {
                        player.getPackets().sendGameMessage("You can't zoom that much.");
                        return true;
                    }
                    player.getPackets().sendGlobalConfig(184, zoomId);
                    return true;
                
                case "resetzoom":
                    player.getPackets().sendGlobalConfig(184, 250);
                    return true;
                
                case "ticket":
                    TicketSystem.answerTicket(player);
                    return true;
                
                case "finishticket":
                    TicketSystem.removeTicket(player);
                    return true;
                
                case "tonpc":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use - ;;tonpc [ID #]");
                        player.getAppearence().transformIntoNPC(-1);
                        return true;
                    }
                    if (Integer.valueOf(cmd[1]) > 15633) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>This ID # is way above the normal range.");
                        player.getAppearence().transformIntoNPC(-1);
                    }
                    try {
                        player.getAppearence().transformIntoNPC(Integer.valueOf(cmd[1]));
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use - ;;tonpc [ID #]");
                        player.getAppearence().transformIntoNPC(-1);
                    }
                    return true;
                
                case "spec":
                    player.getCombatDefinitions().resetSpecialAttack();
                    return true;
                
                case "teleto":
                    if (player.isLocked() || player.getControlerManager().getControler() != null) {
                        player.sm("You cannot tele anywhere from here.");
                        return true;
                    }
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    if (target == null) {
                        player.sm("Couldn't find player " + name + ".");
                    } else {
                        player.setNextWorldTile(target);
                    }
                    return true;
                
            }
        }
        return false;
    }
    
    public static boolean processHeadModCommands(Player player, String[] cmd, boolean console, boolean clientCommand) {
        if (clientCommand) {
            switch (cmd[0]) {
                case "tele":
                    cmd = cmd[1].split(",");
                    int plane = Integer.valueOf(cmd[0]);
                    int x = Integer.valueOf(cmd[1]) << 6 | Integer.valueOf(cmd[3]);
                    int y = Integer.valueOf(cmd[2]) << 6 | Integer.valueOf(cmd[4]);
                    player.setNextWorldTile(new WorldTile(x, y, plane));
                    return true;
            }
        } else {
            String name;
            Player target;
            
            switch (cmd[0]) {
                
                case "zoom":
                    int zoomId = Integer.valueOf(cmd[1]);
                    if (zoomId < 25 || zoomId > 2500) {
                        player.getPackets().sendGameMessage("You can't zoom that much.");
                        return true;
                    }
                    player.getPackets().sendGlobalConfig(184, zoomId);
                    return true;
                
                case "resetzoom":
                    player.getPackets().sendGlobalConfig(184, 250);
                    return true;
                
                case "totalcopy": {
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    Player p2 = World.getPlayerByDisplayName(name);
                    if (p2 == null) {
                        player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
                        return true;
                    }
                    if (player.totaloutfits < 1) {
                        player.getPackets().sendGameMessage("<img=15><col=ff0000>You MUST save your outfit before doing  copying " + name + "'s!");
                        p2.getPackets().sendGameMessage("<img=15><col=ff0000>" + player.getDisplayName() + " needs to save his outfit first.");
                        return true;
                    }
                    if ((player.getEquipment().wearingArmour())) {
                        player.sm("<img=15><col=ff0000>Before wearing your outfit, please unequip the items you are wielding!");
                        return true;
                    }
                    int arms = p2.getAppearence().getArmsStyle();
                    int wrists = p2.getAppearence().getWristsStyle();
                    int top = p2.getAppearence().getTopStyle();
                    int legs = p2.getAppearence().getLegsStyle();
                    int beard = p2.getAppearence().getBeardStyle();
                    int hair = p2.getAppearence().getHairStyle();
                    int facial = p2.getAppearence().getFacialHair();
                    int boots = p2.getAppearence().getBootsStyle();
                    // Colors
                    int haircolor = p2.getAppearence().getHairColor();
                    int bootcolor = p2.getAppearence().getBootColor();
                    int legscolor = p2.getAppearence().getLegsColor();
                    int skincolor = p2.getAppearence().getSkinColor();
                    int topcolor = p2.getAppearence().getTopColor();
                    Item[] items = p2.getEquipment().getItems().getItemsCopy();
                    for (int i = 0; i < items.length; i++) {
                        if (items[i] == null) {
                            continue;
                        }
                        for (String string : Settings.EARNED_ITEMS) {
                            if (items[i].getDefinitions().getName().toLowerCase().contains(string)) {
                                items[i] = new Item(-1, -1);
                            }
                        }
                        for (String string : Settings.VOTE_REQUIRED_ITEMS) {
                            if (items[i].getDefinitions().getName().toLowerCase().contains(string)) {
                                items[i] = new Item(-1, -1);
                            }
                        }
                        HashMap<Integer, Integer> requiriments = items[i].getDefinitions().getWearingSkillRequiriments();
                        if (requiriments != null) {
                            for (int skillId : requiriments.keySet()) {
                                if (skillId > 24 || skillId < 0) {
                                    continue;
                                }
                                int level = requiriments.get(skillId);
                                if (level < 0 || level > 120) {
                                    continue;
                                }
                                if (player.getSkills().getLevelForXp(skillId) < level) {
                                    name = Skills.SKILL_NAME[skillId].toLowerCase();
                                    player.getPackets().sendGameMessage("You need to have a" + (name.startsWith("a") ? "n" : "") + " " + name + " level of " + level + ".");
                                }
                                
                            }
                        }
                        // CLOTHES
                        player.getEquipment().getItems().set(i, items[i]);
                        player.getAppearence().setArmsStyle(arms);
                        player.getAppearence().setWristsStyle(wrists);
                        player.getAppearence().setTopStyle(top);
                        player.getAppearence().setLegsStyle(legs);
                        player.getAppearence().setBeardStyle(beard);
                        player.getAppearence().setHairStyle(hair);
                        player.getAppearence().setFacialHair(facial);
                        player.getAppearence().setBootsStyle(boots);
                        // COLORS
                        player.getAppearence().setHairColor(haircolor);
                        player.getAppearence().setBootsColor(bootcolor);
                        player.getAppearence().setLegsColor(legscolor);
                        player.getAppearence().setSkinColor(skincolor);
                        player.getAppearence().setTopColor(topcolor);
                        player.getEquipment().refresh(i);
                    }
                    player.getPackets().sendGameMessage("<img=15><col=FFFC00>You have copied " + name + "'s outfit.");
                    p2.getPackets().sendGameMessage("<img=15><col=FFFC00>" + player.getDisplayName() + " has completely copied your outfit.");
                    player.getAppearence().generateAppearenceData();
                    return true;
                }
                case "copy":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    Player p2 = World.getPlayerByDisplayName(name);
                    if (p2 == null) {
                        player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
                        return true;
                    }
                    int copy1 = p2.getAppearence().getArmsStyle();
                    Item[] items = p2.getEquipment().getItems().getItemsCopy();
                    for (int i = 0; i < items.length; i++) {
                        if (items[i] == null) {
                            continue;
                        }
                        for (String string : Settings.EARNED_ITEMS) {
                            if (items[i].getDefinitions().getName().toLowerCase().contains(string)) {
                                items[i] = new Item(-1, -1);
                            }
                        }
                        for (String string : Settings.VOTE_REQUIRED_ITEMS) {
                            if (items[i].getDefinitions().getName().toLowerCase().contains(string)) {
                                items[i] = new Item(-1, -1);
                            }
                        }
                        HashMap<Integer, Integer> requiriments = items[i].getDefinitions().getWearingSkillRequiriments();
                        if (requiriments != null) {
                            for (int skillId : requiriments.keySet()) {
                                if (skillId > 24 || skillId < 0) {
                                    continue;
                                }
                                int level = requiriments.get(skillId);
                                if (level < 0 || level > 120) {
                                    continue;
                                }
                                if (player.getSkills().getLevelForXp(skillId) < level) {
                                    name = Skills.SKILL_NAME[skillId].toLowerCase();
                                    player.getPackets().sendGameMessage("You need to have a" + (name.startsWith("a") ? "n" : "") + " " + name + " level of " + level + ".");
                                }
                                
                            }
                        }
                        player.getEquipment().getItems().set(i, items[i]);
                        // player.getAppearence().setArmsStyle(copy1);
                        player.getEquipment().refresh(i);
                    }
                    player.getAppearence().generateAppearenceData();
                    return true;
                
                case "teletome":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    if (target == null) {
                        player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
                    } else {
                        target.setNextWorldTile(player);
                    }
                    return true;
                
                case "title":
                    if (cmd.length < 2) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>Use: ::title (id)");
                        return true;
                    }
                    try {
                        player.getAppearence().setTitle(Integer.valueOf(cmd[1]));
                    } catch (NumberFormatException e) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>Use: ::title (id)");
                    }
                    return true;
                
                case "tonpc":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use - ;;tonpc [ID #]");
                        player.getAppearence().transformIntoNPC(-1);
                        return true;
                    }
                    if (Integer.valueOf(cmd[1]) > 15633) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>This ID # is way above the normal range.");
                        player.getAppearence().transformIntoNPC(-1);
                    }
                    try {
                        player.getAppearence().transformIntoNPC(Integer.valueOf(cmd[1]));
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use - ;;tonpc [ID #]");
                        player.getAppearence().transformIntoNPC(-1);
                    }
                    return true;
                
            }
        }
        return false;
    }
    
    public static boolean processSupportCommands(Player player, String[] cmd, boolean console, boolean clientCommand) {
        String name;
        Player target;
        if (clientCommand) {
            switch (cmd[0]) {
                case "tele":
                    cmd = cmd[1].split(",");
                    int plane = Integer.valueOf(cmd[0]);
                    int x = Integer.valueOf(cmd[1]) << 6 | Integer.valueOf(cmd[3]);
                    int y = Integer.valueOf(cmd[2]) << 6 | Integer.valueOf(cmd[4]);
                    player.setNextWorldTile(new WorldTile(x, y, plane));
                    return true;
            }
        } else {
            switch (cmd[0]) {
                
                case "unnull":
                case "sendhome":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    if (target == null) {
                        player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
                    } else {
                        target.unlock();
                        target.getControlerManager().forceStop();
                        if (target.getNextWorldTile() == null) {
                            // tele the player
                            target.setNextWorldTile(Settings.RESPAWN_PLAYER_LOCATION);
                        }
                        player.getPackets().sendGameMessage("You have successfully unnulled: " + target.getDisplayName() + ".");
                        return true;
                    }
                    return true;
                
                case "mute":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    if (target != null) {
                        target.setMuted(Utils.currentTimeMillis() + (player.getRights() >= 1 ? (48 * 60 * 60 * 1000) : (1 * 60 * 60 * 1000)));
                        target.getPackets().sendGameMessage("You've been muted for " + (player.getRights() >= 1 ? " 48 hours by " : "1 hour by ") + Utils.formatPlayerNameForDisplay(player.getUsername()) + ".");
                        player.getPackets().sendGameMessage("You have muted " + (player.getRights() >= 1 ? " 48 hours by " : "1 hour by ") + target.getDisplayName() + ".");
                    } else {
                        name = Utils.formatPlayerNameForProtocol(name);
                        if (!SerializableFilesManager.containsPlayer(name)) {
                            player.getPackets().sendGameMessage("Account name " + Utils.formatPlayerNameForDisplay(name) + " doesn't exist.");
                            return true;
                        }
                        target = SerializableFilesManager.loadPlayer(name);
                        target.setUsername(name);
                        target.setMuted(Utils.currentTimeMillis() + (player.getRights() >= 1 ? (48 * 60 * 60 * 1000) : (1 * 60 * 60 * 1000)));
                        player.getPackets().sendGameMessage("You have muted " + (player.getRights() >= 1 ? " 48 hours by " : "1 hour by ") + target.getDisplayName() + ".");
                        SerializableFilesManager.savePlayer(target);
                    }
                    return true;
                
                case "unipmute":
                case "unmute":
                    String name1 = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name1 += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    Player target1 = World.getPlayerByDisplayName(name1);
                    if (target1 != null) {
                        target1.setMuted(0);
                        IPMute.unmute(target1);
                        target1.getPackets().sendGameMessage("You've been unmuted by " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ".");
                        player.getPackets().sendGameMessage("You have unmuted: " + target1.getDisplayName() + ".");
                        SerializableFilesManager.savePlayer(target1);
                    } else {
                        File acc1 = new File("data/characters/" + name1.replace(" ", "_") + ".p");
                        try {
                            target1 = (Player) SerializableFilesManager.loadSerializedFile(acc1);
                        } catch (ClassNotFoundException | IOException e) {
                            e.printStackTrace();
                        }
                        target1.setMuted(0);
                        player.getPackets().sendGameMessage("You have unmuted: " + Utils.formatPlayerNameForDisplay(name1) + ".");
                        try {
                            SerializableFilesManager.storeSerializableClass(target1, acc1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return true;
                
                case "ban":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    if (target != null) {
                        target.setBanned(Utils.currentTimeMillis() + (48 * 60 * 60 * 1000));
                        target.getSession().getChannel().close();
                        player.getPackets().sendGameMessage("You have banned 48 hours: " + target.getDisplayName() + ".");
                    } else {
                        name = Utils.formatPlayerNameForProtocol(name);
                        if (!SerializableFilesManager.containsPlayer(name)) {
                            player.getPackets().sendGameMessage("Account name " + Utils.formatPlayerNameForDisplay(name) + " doesn't exist.");
                            return true;
                        }
                        target = SerializableFilesManager.loadPlayer(name);
                        target.setUsername(name);
                        target.setBanned(Utils.currentTimeMillis() + (48 * 60 * 60 * 1000));
                        player.getPackets().sendGameMessage("You have banned 48 hours: " + Utils.formatPlayerNameForDisplay(name) + ".");
                        SerializableFilesManager.savePlayer(target);
                    }
                    return true;
                
                case "unban":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    if (target != null) {
                        IPBanL.unban(target);
                        player.getPackets().sendGameMessage("You have unbanned: " + target.getDisplayName() + ".");
                    } else {
                        name = Utils.formatPlayerNameForProtocol(name);
                        if (!SerializableFilesManager.containsPlayer(name)) {
                            player.getPackets().sendGameMessage("Account name " + Utils.formatPlayerNameForDisplay(name) + " doesn't exist.");
                            return true;
                        }
                        target = SerializableFilesManager.loadPlayer(name);
                        target.setUsername(name);
                        IPBanL.unban(target);
                        player.getPackets().sendGameMessage("You have unbanned: " + target.getDisplayName() + ".");
                        SerializableFilesManager.savePlayer(target);
                    }
                    return true;
                
                case "jail":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    if (target != null) {
                        target.setJailed(Utils.currentTimeMillis() + (24 * 60 * 60 * 1000));
                        target.getControlerManager().startControler("JailControler");
                        target.getPackets().sendGameMessage("You've been Jailed for 24 hours by " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ".");
                        player.getPackets().sendGameMessage("You have Jailed 24 hours: " + target.getDisplayName() + ".");
                        SerializableFilesManager.savePlayer(target);
                    } else {
                        File acc1 = new File("data/characters/" + name.replace(" ", "_") + ".p");
                        try {
                            target = (Player) SerializableFilesManager.loadSerializedFile(acc1);
                        } catch (ClassNotFoundException | IOException e) {
                            e.printStackTrace();
                        }
                        target.setJailed(Utils.currentTimeMillis() + (24 * 60 * 60 * 1000));
                        player.getPackets().sendGameMessage("You have muted 24 hours: " + Utils.formatPlayerNameForDisplay(name) + ".");
                        try {
                            SerializableFilesManager.storeSerializableClass(target, acc1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return true;
                
                case "unjail":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    if (target != null) {
                        target.setJailed(0);
                        target.getControlerManager().startControler("JailControler");
                        target.getPackets().sendGameMessage("You've been unjailed by " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ".");
                        player.getPackets().sendGameMessage("You have unjailed: " + target.getDisplayName() + ".");
                        SerializableFilesManager.savePlayer(target);
                    } else {
                        File acc1 = new File("data/characters/" + name.replace(" ", "_") + ".p");
                        try {
                            target = (Player) SerializableFilesManager.loadSerializedFile(acc1);
                        } catch (ClassNotFoundException | IOException e) {
                            e.printStackTrace();
                        }
                        target.setJailed(0);
                        player.getPackets().sendGameMessage("You have unjailed: " + Utils.formatPlayerNameForDisplay(name) + ".");
                        try {
                            SerializableFilesManager.storeSerializableClass(target, acc1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return true;
                
                case "kick":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    if (target == null) {
                        player.getPackets().sendGameMessage(Utils.formatPlayerNameForDisplay(name) + " is not logged in.");
                        return true;
                    }
                    target.getSession().getChannel().close();
                    player.getPackets().sendGameMessage("You have kicked: " + target.getDisplayName() + ".");
                    return true;
                
                case "spec":
                    player.getCombatDefinitions().resetSpecialAttack();
                    return true;
                
                case "forcekick":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    if (target == null) {
                        player.getPackets().sendGameMessage(Utils.formatPlayerNameForDisplay(name) + " is not logged in.");
                        return true;
                    }
                    target.forceLogout();
                    player.getPackets().sendGameMessage("You have kicked: " + target.getDisplayName() + ".");
                    return true;
                
                case "kill":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    if (target == null) {
                        return true;
                    }
                    target.applyHit(new Hit(target, player.getHitpoints(), HitLook.REGULAR_DAMAGE));
                    target.stopAll();
                    return true;
                
            }
        }
        return false;
    }
    
    /*
	 * extra parameters if you want to check them
	 */
    public static boolean processRSMVerCommand(final Player player, String[] cmd, boolean console, boolean clientCommand) {
        if (clientCommand) {
            switch (cmd[0]) {
                case "tele":
                    cmd = cmd[1].split(",");
                    int plane = Integer.valueOf(cmd[0]);
                    int x = Integer.valueOf(cmd[1]) << 6 | Integer.valueOf(cmd[3]);
                    int y = Integer.valueOf(cmd[2]) << 6 | Integer.valueOf(cmd[4]);
                    player.setNextWorldTile(new WorldTile(x, y, plane));
                    return true;
            }
        } else {
            String name;
            Player target;
            WorldObject object;
            switch (cmd[0]) {
                
                case "itemn":
                    if (cmd.length < 2) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>Use ;;item [ID]");
                        return true;
                    }
                    StringBuilder sb = new StringBuilder(cmd[1]);
                    int amount = 1;
                    if (cmd.length > 2) {
                        for (int i = 2; i < cmd.length; i++) {
                            if (cmd[i].startsWith("+")) {
                                amount = Integer.parseInt(cmd[i].replace("+", ""));
                            } else {
                                sb.append(" ").append(cmd[i]);
                            }
                        }
                    }
                    String name11 = sb.toString().toLowerCase().replace("[", "(").replace("]", ")").replaceAll(",", "'");
                    if (name11.contains("Sacred clay")) {
                        return true;
                    }
                    
                    if (name11.contains("rsmver token") & !(player.getUsername().equalsIgnoreCase("Multak"))) {
                        player.sm("<img=20><col=FF0000>Only the owner can spawn this item.");
                        return true;
                    }
                    if (name11.contains("rsmver cape") & !(player.getUsername().equalsIgnoreCase("Multak"))) {
                        player.sm("<img=20><col=FF0000>Only the owner can spawn this item.");
                        return true;
                        
                    }
                    if (name11.contains("bright light cavalier") & !(player.getUsername().equalsIgnoreCase("Multak"))) {
                        player.sm("<img=20><col=FF0000>Only the owner can spawn this item.");
                        return true;
                    }
                    if (name11.contains("dark shadow cavalier") & !(player.getUsername().equalsIgnoreCase("Multak"))) {
                        player.sm("<img=20><col=FF0000>Only the owner can spawn this item.");
                        return true;
                        
                    }
                    if (name11.contains("rainbow goggles") & !(player.getUsername().equalsIgnoreCase("Multak"))) {
                        player.sm("<img=20><col=FF0000>Only the owner can spawn this item.");
                        return true;
                        
                    }
                    if (name11.contains("light shadow boots") & !(player.getUsername().equalsIgnoreCase("Multak"))) {
                        player.sm("<img=20><col=FF0000>Only the owner can spawn this item.");
                        return true;
                        
                    }
                    if (name11.contains("dark shadow boots") & !(player.getUsername().equalsIgnoreCase("Multak"))) {
                        player.sm("<img=20><col=FF0000>Only the owner can spawn this item.");
                        return true;
                        
                    }
                    if (name11.contains("rainbow boots") & !(player.getUsername().equalsIgnoreCase("Multak"))) {
                        player.sm("<img=20><col=FF0000>Only the owner can spawn this item.");
                        return true;
                        
                    }
                    if (name11.contains("brown shadow boots") & !(player.getUsername().equalsIgnoreCase("Multak"))) {
                        player.sm("<img=20><col=FF0000>Only the owner can spawn this item.");
                        return true;
                        
                    }
                    if (name11.contains("blue shadow boots") & !(player.getUsername().equalsIgnoreCase("Multak"))) {
                        player.sm("<img=20><col=FF0000>Only the owner can spawn this item.");
                        return true;
                        
                    }
                    if (name11.contains("red shadow boots") & !(player.getUsername().equalsIgnoreCase("Multak"))) {
                        player.sm("<img=20><col=FF0000>Only the owner can spawn this item.");
                        return true;
                        
                    }
                    if (name11.contains("yellow shadow boots") & !(player.getUsername().equalsIgnoreCase("Multak"))) {
                        player.sm("<img=20><col=FF0000>Only the owner can spawn this item.");
                        return true;
                        
                    }
                    if (name11.contains("green shadow boots") & !(player.getUsername().equalsIgnoreCase("Multak"))) {
                        player.sm("<img=20><col=FF0000>Only the owner can spawn this item.");
                        return true;
                        
                    }
                    if (name11.contains("purple shadow boots") & !(player.getUsername().equalsIgnoreCase("Multak"))) {
                        player.sm("<img=20><col=FF0000>Only the owner can spawn this item.");
                        return true;
                        
                    }
                    if (name11.contains("rsmv master cape") & !(player.getUsername().equalsIgnoreCase("Multak"))) {
                        player.sm("<img=20><col=FF0000>Only the owner can spawn this item.");
                        return true;
                        
                    }
                    if (name11.contains("vengium veteran cape") & !(player.getUsername().equalsIgnoreCase("Multak"))) {
                        player.sm("<img=20><col=FF0000>Only the owner can spawn this item.");
                        return true;
                        
                    }
                    for (int i = 0; i < Utils.getItemDefinitionsSize(); i++) {
                        ItemDefinitions def = ItemDefinitions.getItemDefinitions(i);
                        if (def.getName().toLowerCase().equalsIgnoreCase(name11)) {
                            player.getInventory().addItem(i, amount);
                            player.stopAll();
                            player.getPackets().sendGameMessage("Found item <col=FF0000>" + name11 + " - id: " + i + ".");
                            return true;
                        }
                    }
                    player.getPackets().sendGameMessage("Could not find item by the name <col=FF0000>" + name11 + ".");
                    return true;
                
                case "item":
                    if (player.getRights() <= 2) {
                        if (cmd.length < 2) {
                            player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF0000>Use ;;item [ID #] [Amount]");
                            return true;
                        }
                    }
                    if ((Integer.valueOf(cmd[1]) == 29980 || Integer.valueOf(cmd[1]) == 12852 || Integer.valueOf(cmd[1]) == 29992 || Integer.valueOf(cmd[1]) == 29993 || Integer.valueOf(cmd[1]) == 29994 || Integer.valueOf(cmd[1]) == 29995 || Integer.valueOf(cmd[1]) == 29996 || Integer.valueOf(cmd[1]) == 29997 || Integer.valueOf(cmd[1]) == 29984 || Integer.valueOf(cmd[1]) == 29985 || Integer.valueOf(cmd[1]) == 29986 || Integer.valueOf(cmd[1]) == 29987 || Integer.valueOf(cmd[1]) == 29988 || Integer.valueOf(cmd[1]) == 29989 || Integer.valueOf(cmd[1]) == 29990 || Integer.valueOf(cmd[1]) == 29991) & !player.getUsername().equalsIgnoreCase("Multak")) {
                        player.getPackets().sendGameMessage("<img=15><col=ff0000>Only the owner can spawn this item!");
                        return true;
                    }
                    try {
                        int itemId = Integer.valueOf(cmd[1]);
                        player.getInventory().addItem(itemId, cmd.length >= 3 ? Integer.valueOf(cmd[2]) : 1);
                        player.stopAll();
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF0000>Use - ;;item [ID #] [Amount]");
                    }
                    return true;
                
                case "master":
                    if (cmd.length < 2) {
                        for (int skill = 0; skill < 25; skill++) {
                            player.getSkills().addXp(skill, 150000000);
                        }
                        return true;
                    }
                    try {
                        player.getSkills().addXp(Integer.valueOf(cmd[1]), 150000000);
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ::master skill");
                    }
                    return true;
                
                case "switchbuildmode":
                    player.getHouse().setBuildMode(!player.getHouse().isBuildMode());
                    player.getPackets().sendGameMessage("Build Mode: " + player.getHouse().isBuildMode());
                    if (player.getHouse().isLoaded()) {
                        player.getHouse().expelGuests();
                        if (player.getHouse().isOwnerInside()) {
                            player.getHouse().refreshHouse();// now were done
                        }
                    }
                    return true;
                
                case "orthozoom":
                    if (player.isGoldDonor() == true || player.isPlatinumDonor() == true || player.isDiamondDonor() == true || player.isJGUTTDonor() == true) {
                        int zoomId = Integer.valueOf(cmd[1]);
                        if (zoomId < 25 || zoomId > 2500) {
                            player.getPackets().sendGameMessage("<img=20><col=FF0000>You can't zoom that much.</col>");
                            return true;
                        }
                        player.getPackets().sendGlobalConfig(184, zoomId);
                    }
                    return true;
                
                case "resetzoom":
                    player.sm("<img=20> <col=FF0000>Your camera has been reset.</col>");
                    player.getPackets().sendGlobalConfig(184, 250);
                    return true;
                
                case "claim":
                    try {
                        player.rspsdata(player, player.getUsername());
                    } catch (Exception e) {
                    }
                    return true;
                
                case "dir":
                    player.sm("Your direction # is:" + player.getDirection());
                    return true;
                
                case "zoomin":
                    if (player.getRSMVRank() < 3) {
                        player.sm("<img=15><col=ff0000>You must be RSMVer 6 or more in order to use this feature.");
                    } else if (player.getRSMVRank() >= 3) {
                        player.sm("<img=15><col=FF0000>You have zoomed in. Do ;;zoomout to return.");
                        player.getPackets().sendConfig(1241, 1);
                    }
                    return true;
                
                case "zoomout":
                    if (player.getRSMVRank() < 0) {
                        player.sm("<img=15><col=ff0000>You must be RSMVer 6 or more in order to use this feature.");
                    } else if (player.getRSMVRank() >= 0) {
                        player.getPackets().sendConfig(1241, 4);
                        player.sm("<img=15><col=FF0000>You have zoomed out.");
                    }
                    return true;
                
                case "itemsearch":
                    player.getInterfaceManager().sendInventoryInterface(389);
                    return true;
                
                case "teleto":
				/*
				 * name = ""; for (int i = 1; i < cmd.length; i++) { name +=
				 * cmd[i] + ((i == cmd.length - 1) ? "" : " "); } Player p3 =
				 * World.getPlayerByDisplayName(name); if (p3 == null ||
				 * p3.isDead() || p3.hasFinished() ||
				 * !player.getMapRegionsIds().contains(p3.getRegionId())) return
				 * true; if (player.getLockDelay() > Utils.currentTimeMillis())
				 * return true; player.stopAll(false);
				 *
				 * player.getTemporaryAttributtes().put("TeletoTarget", p3);
				 * player.getPackets().sendGameMessage("Sending " +
				 * p3.getDisplayName() + " a teleport request...");
				 * p3.getPackets().sendTeleportRequestMessage(player);
				 */
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    boolean loggedIn11123 = true;
                    if (target == null) {
                        target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
                        if (target != null) {
                            target.setUsername(Utils.formatPlayerNameForProtocol(name));
                        }
                        loggedIn11123 = false;
                    }
                    if (target == null) {
                        return true;
                    }
                    player.getTemporaryAttributtes().put("teleto_request_p", target);
                    if (target.getTemporaryAttributtes().get("teleto_request_p") == player) {
                        
                        return true;
                    }
                    target.getPackets().sendTeleportRequestMessage(player);
                    player.getPackets().sendGameMessage("Sending " + target.getDisplayName() + " a teleport request...");
                    return true;
                
                case "tele":
                    if (cmd.length < 3) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ::tele coordX coordY");
                        return true;
                    }
                    try {
                        player.resetWalkSteps();
                        player.setNextWorldTile(new WorldTile(Integer.valueOf(cmd[1]), Integer.valueOf(cmd[2]), cmd.length >= 4 ? Integer.valueOf(cmd[3]) : player.getPlane()));
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ::tele coordX coordY plane");
                    }
                    return true;
                
                case "coords":
                case "mypos":
                    player.getPackets().sendGameMessage("Coords: <col=FF0000>" + player.getX() + ", <col=FF0000>" + player.getY() + ", <col=FF0000>" + player.getPlane() + ", regionId: <col=FF0000>" + player.getRegionId() + ", rx: <col=FF0000>" + player.getChunkX() + ", ry: <col=FF0000>" + player.getChunkY());
                    return true;
                
                case "orb":
                    player.getInterfaceManager().gazeOrbOfOculus();
                    return true;
                
                case "answer":
                    if (TriviaBot.TriviaArea(player)) {
                        player.sm("What the fuck are you doing in here? I disabled this, get out of here!");
                        return false;
                    }
                    if (cmd.length >= 2) {
                        String answer = cmd[1];
                        if (cmd.length == 3) {
                            answer = cmd[1] + " " + cmd[2];
                        }
                        if (cmd.length == 4) {
                            answer = cmd[1] + " " + cmd[2] + " " + cmd[3];
                        }
                        if (cmd.length == 5) {
                            answer = cmd[1] + " " + cmd[2] + " " + cmd[3] + " " + cmd[4];
                        }
                        if (cmd.length == 6) {
                            answer = cmd[1] + " " + cmd[2] + " " + cmd[3] + " " + cmd[4] + " " + cmd[5];
                        }
                        TriviaBot.verifyAnswer(player, answer);
                    } else {
                        player.sm("<img=15><col=ff0000>Syntax is ;;" + cmd[0] + " <answer input>.");
                    }
                    return true;
                
                case "home":
                    Magic.sendBroomTeleport(player, 0, 0.0D, new WorldTile(2598, 3409, 0), new int[0]);
                    player.getAppearence().setRenderEmote(-1);
                    
                    return true;
                
                case "anim":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use ;;anim [ID #]");
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>Use ;;anim [ID #]");
                        return true;
                    }
                    AnimationDefinitions def = AnimationDefinitions.getAnimationDefinitions(Integer.parseInt(cmd[1]));
                    if (def == null) {
                        
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>This ID is way above the normal range.");
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>This ID is way above the normal range.");
                        return true;
                    }
                    // if ((Integer.valueOf(cmd[1]) >= 17071 &
                    // !player.isRSMVer10())) {
                    // player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>You must be an RSMV Master, admin, or donator to use this.");
                    // player.setNextGraphics(new Graphics(-1));
                    // player.setNextAnimation(new Animation(-1));
                    // return true;
                    // }
                    try {
                        player.setNextAnimation(new Animation(Integer.valueOf(cmd[1])));
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use ;;anim [ID #]");
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>Use ;;anim [ID #]");
                    }
                    return true;
                
                case "gfx":
                    if (cmd.length < 2) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>Use ;gfx [ID]");
                        player.setNextAnimation(new Animation(-1));
                        player.setNextGraphics(new Graphics(-1));
                        return true;
                    }
                    if (Integer.valueOf(cmd[1]) >= Utils.getGraphicDefinitionsSize()) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>This ID is way above the normal range.");
                        player.setNextAnimation(new Animation(-1));
                        player.setNextGraphics(new Graphics(-1));
                        return true;
                    }
                    try {
                        player.setNextGraphics(new Graphics(Integer.valueOf(cmd[1])));
                    } catch (NumberFormatException e) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>Use ;;gfx[ID]");
                        player.setNextAnimation(new Animation(-1));
                        player.setNextGraphics(new Graphics(-1));
                    }
                    return true;
                
                case "animgfx":
                    if (cmd.length <= 2) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>Use - ;;animgfx [ID] [ID]");
                        player.setNextAnimation(new Animation(-1));
                        player.setNextGraphics(new Graphics(-1));
                        return true;
                    }
                    if (cmd.length < 3) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>Use - ;;animgfx [ID] [ID]");
                        player.setNextAnimation(new Animation(-1));
                        player.setNextGraphics(new Graphics(-1));
                        return true;
                    }
                    if ((Integer.valueOf(cmd[1]) >= 17071) & !player.isRSMVer10()) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>You must be an RSMV Master, admin, or donator to use this.");
                        player.setNextGraphics(new Graphics(-1));
                        player.setNextAnimation(new Animation(-1));
                        return true;
                    }
                    if (Integer.valueOf(cmd[1]) > Utils.getAnimationDefinitionsSize()) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>This ID is way above the normal range.");
                        player.setNextGraphics(new Graphics(-1));
                        player.setNextAnimation(new Animation(-1));
                        return true;
                    }
                    
                    if (Integer.valueOf(cmd[2]) > Utils.getGraphicDefinitionsSize()) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>This ID is way above the normal range.");
                        player.setNextGraphics(new Graphics(-1));
                        player.setNextAnimation(new Animation(-1));
                        return true;
                    }
                    if (Integer.valueOf(cmd[2]) < 0) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>Use ;;animall [ID] if you are not using a gfx.");
                        player.setNextGraphics(new Graphics(-1));
                        player.setNextAnimation(new Animation(-1));
                        return true;
                    }
                    try {
                        player.setNextAnimation(new Animation(Integer.valueOf(cmd[1])));
                        player.setNextGraphics(new Graphics(Integer.valueOf(cmd[2])));
                    } catch (NumberFormatException e) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>Use - ;;animgfx [ID] [ID]");
                        
                    }
                    return true;
                
                case "remote":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use - ;;remote [ID #]");
                        player.getAppearence().setRenderEmote(-1);
                        return true;
                    }
                    if (Integer.valueOf(cmd[1]) > 2198) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>This ID is way above the normal range.");
                        player.getAppearence().setRenderEmote(-1);
                    }
                    try {
                        player.getAppearence().setRenderEmote(Integer.valueOf(cmd[1]));
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use - ;;remote [ID #]");
                        player.getAppearence().setRenderEmote(-1);
                    }
                    return true;
                
                case "hide":
                    if (player.isHidden) {
                        player.sm("<img=15><col=ff0000>You are already hidden. Use ;;appear to show yourself.");
                    } else if (!player.isHidden) {
                        player.isHidden = true;
                        player.setNextGraphics(new Graphics(881));
                        player.getAppearence().transformIntoNPC(6666);
                        player.getPackets().sendGameMessage("<img=15><col=FF0000>You have disappeared!");
                    }
                    return true;
                
                case "appear":
                    if (!player.isHidden) {
                        player.sm("<img=15><col=ff0000>You have already appeared!");
                    } else if (player.isHidden) {
                        player.isHidden = false;
                        player.setNextGraphics(new Graphics(1287));
                        player.getAppearence().transformIntoNPC(-1);
                        player.getPackets().sendGameMessage("<img=15><col=FF0000>You have appeared!");
                    }
                    return true;
                
                case "bank":
                    player.getBank().openBank();
                    return true;
                
                case "empty":
                    player.getInventory().reset();
                    return true;
                
                case "players":
                    player.GetPlayers();
                    return true;
                
                case "setlevel":
                    if (cmd.length < 3) {
                        player.getPackets().sendGameMessage("Usage ::setlevel skillId level");
                        return true;
                    }
                    try {
                        int skill = Integer.parseInt(cmd[1]);
                        int level = Integer.parseInt(cmd[2]);
                        if (level < 0 || level > 99) {
                            player.getPackets().sendGameMessage("Please choose a valid level.");
                            return true;
                        }
                        player.getSkills().set(skill, level);
                        player.getSkills().setXp(skill, Skills.getXPForLevel(level));
                        player.getAppearence().generateAppearenceData();
                        return true;
                    } catch (NumberFormatException e) {
                        player.getPackets().sendGameMessage("Usage ::setlevel skillId level");
                    }
                    return true;
                
                case "changepass":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    if (name.length() > 15 || name.length() < 5) {
                        player.getPackets().sendGameMessage("You cannot set your password to over 15 chars.");
                        return true;
                    }
                    player.setPassword(Encrypt.encryptSHA1(cmd[1]));
                    player.getPackets().sendGameMessage("You changed your password! Your password is " + cmd[1] + ".");
                    return true;
                
                case "teleitems":
                    Settings.getTeleItems(player);
                    return true;
                
                case "runes":
                    player.setNextGraphics(new Graphics(7));
                    player.getBank().addItem(555, 1000000, true);// WATER
                    player.getBank().addItem(556, 1000000, true);// AIR
                    player.getBank().addItem(557, 1000000, true);// EARTH
                    player.getBank().addItem(554, 1000000, true);// FIRE
                    player.getBank().addItem(558, 1000000, true);// MIND
                    player.getBank().addItem(561, 1000000, true);// NATURE
                    player.getBank().addItem(562, 1000000, true);// CHAOS
                    player.getBank().addItem(560, 1000000, true);// DEATH
                    player.getBank().addItem(565, 1000000, true);// BLOOD
                    player.getBank().addItem(566, 1000000, true);// SOUL
                    player.getBank().addItem(9075, 1000000, true);// ASTRAL
                    player.getBank().addItem(563, 1000000, true);// LAW
                    player.getBank().addItem(564, 1000000, true);// COSMIC
                    player.getBank().addItem(559, 1000000, true);// BODY
                    
                    player.getInventory().addItem(555, 1000000);// WATER
                    player.getInventory().addItem(556, 1000000);// AIR
                    player.getInventory().addItem(557, 1000000);// EARTH
                    player.getInventory().addItem(554, 1000000);// FIRE
                    player.getInventory().addItem(558, 1000000);// MIND
                    player.getInventory().addItem(561, 1000000);// NATURE
                    player.getInventory().addItem(562, 1000000);// CHAOS
                    player.getInventory().addItem(560, 1000000);// DEATH
                    player.getInventory().addItem(565, 1000000);// BLOOD
                    player.getInventory().addItem(566, 1000000);// SOUL
                    player.getInventory().addItem(9075, 1000000);// ASTRAL
                    player.getInventory().addItem(563, 1000000);// LAW
                    player.getInventory().addItem(564, 1000000);// COSMIC
                    player.getInventory().addItem(559, 1000000);// BODY
                    
                    player.sendMessage("<col=FFFFFF><shad=FF00000>Check your inventory and bank for the Runes!");
                    return true;
                
                case "gear":
                    player.setNextGraphics(new Graphics(6));
                    player.getBank().addItem(20135, 69999999, true);
                    player.getBank().addItem(20139, 69999999, true);
                    player.getBank().addItem(20143, 69999999, true);
                    player.getBank().addItem(20147, 69999999, true);
                    player.getBank().addItem(20151, 69999999, true);
                    player.getBank().addItem(20155, 69999999, true);
                    player.getBank().addItem(20159, 69999999, true);
                    player.getBank().addItem(20163, 69999999, true);
                    player.getBank().addItem(20167, 69999999, true);
                    player.getBank().addItem(6585, 69999999, true);
                    player.getBank().addItem(19335, 69999999, true);
                    player.getBank().addItem(4151, 69999999, true);
                    player.getBank().addItem(21371, 69999999, true);
                    player.getBank().addItem(14484, 69999999, true);
                    player.getBank().addItem(13734, 69999999, true);
                    player.getBank().addItem(13736, 69999999, true);
                    player.getBank().addItem(13738, 69999999, true);
                    player.getBank().addItem(13740, 69999999, true);
                    player.getBank().addItem(13742, 69999999, true);
                    player.getBank().addItem(13744, 69999999, true);
                    player.getBank().addItem(11696, 69999999, true);
                    player.getBank().addItem(11700, 69999999, true);
                    player.getBank().addItem(11698, 69999999, true);
                    player.getBank().addItem(11694, 69999999, true);
                    player.getBank().addItem(11724, 69999999, true);
                    player.getBank().addItem(11726, 69999999, true);
                    player.getBank().addItem(11728, 69999999, true);
                    player.getBank().addItem(11718, 69999999, true);
                    player.getBank().addItem(11720, 69999999, true);
                    player.getBank().addItem(11722, 69999999, true);
                    player.getBank().addItem(11716, 69999999, true);
                    player.getBank().addItem(21787, 69999999, true);
                    player.getBank().addItem(21790, 69999999, true);
                    player.getBank().addItem(21793, 69999999, true);
                    player.getBank().addItem(11335, 69999999, true);
                    player.getBank().addItem(14479, 69999999, true);
                    player.getBank().addItem(4087, 69999999, true);
                    player.getBank().addItem(11732, 69999999, true);
                    player.getBank().addItem(18349, 69999999, true);
                    player.getBank().addItem(18351, 69999999, true);
                    player.getBank().addItem(18353, 69999999, true);
                    player.getBank().addItem(18355, 69999999, true);
                    player.getBank().addItem(18357, 69999999, true);
                    player.getBank().addItem(18359, 69999999, true);
                    player.getBank().addItem(18361, 69999999, true);
                    player.getBank().addItem(18363, 69999999, true);
                    player.getBank().addItem(10498, 69999999, true);
                    player.getBank().addItem(10499, 69999999, true);
                    player.getBank().addItem(20786, 69999999, true);
                    player.getBank().addItem(15259, 69999999, true);
                    player.getBank().addItem(6739, 69999999, true);
                    player.getBank().addItem(20770, 69999999, true);
                    player.getBank().addItem(20769, 69999999, true);
                    player.getBank().addItem(20772, 69999999, true);
                    player.getBank().addItem(20771, 69999999, true);
                    player.getBank().addItem(20768, 69999999, true);
                    player.getBank().addItem(20767, 69999999, true);
                    player.getBank().addItem(15332, 69999999, true);
                    player.getBank().addItem(23531, 69999999, true);
                    player.getBank().addItem(7462, 69999999, true);
                    
                    player.sendMessage("<col=FFFFFF><shad=FF00000>Check your inventory and bank for the Gear!");
                    return true;
                
                case "armstyle":
                    if (cmd.length < 2) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>Use ;;armstyle [ID]");
                        return true;
                    }
                    if ((Integer.valueOf(cmd[1]) < 0)) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>This ID is too low to execute.");
                        return true;
                    }
                    try {
                        player.getAppearence().setArmsStyle(Integer.valueOf(cmd[1]));
                        player.getAppearence().generateAppearenceData();
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use ;; armstyle [ID]");
                    }
                    return true;
                case "topstyle":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ;;topstyle [id]");
                        return true;
                    }
                    try {
                        player.getAppearence().setTopStyle(Integer.valueOf(cmd[1]));
                        // player.getAppearence().generateAppearenceData();
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ;;topstyle [id]");
                    }
                    return true;
                
                case "hairstyle":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ::hairstyle (ID #)");
                        return true;
                    }
                    if (Integer.valueOf(cmd[1]) < 1) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>This ID is too low to execute.");
                    } else {
                        try {
                            player.getAppearence().setHairStyle(Integer.valueOf(cmd[1]));
                            player.getAppearence().generateAppearenceData();
                        } catch (NumberFormatException e) {
                            player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ::hairstyle id");
                        }
                    }
                    return true;
                
                case "haircolor":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ::haircolor (ID #)");
                        return true;
                    }
                    try {
                        player.getAppearence().setHairColor(Integer.valueOf(cmd[1]));
                        player.getAppearence().generateAppearenceData();
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ::haircolor (ID #)");
                    }
                    return true;
                
                case "skincolor":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ::skincolor (ID #)");
                        return true;
                    }
                    try {
                        player.getAppearence().setSkinColor(Integer.valueOf(cmd[1]));
                        player.getAppearence().generateAppearenceData();
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>Go to the Make-over Mage at home to change back.");
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ::skincolor (ID #)");
                    }
                    return true;
                
                case "rsmvset":
                    Settings.getRSMVSet(player);
                    return true;
                
                case "switchitemslook":
                    player.switchItemsLook();
                    player.getPackets().sendGameMessage("You are now playing with " + (player.isOldItemsLook() ? "old" : "new") + " item looks.");
                    return true;
                
                case "hideminimap":
                    
                    player.getPackets().sendWindowsPane(56, 0);
                    
                    return true;
                
                case "sendpane":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ::haircolor (ID #)");
                        return true;
                    }
                    if (Integer.valueOf(cmd[2]) > 1200) {
                        player.sm("<img=15><col=ff0000>This value is above the normal range!");
                        return true;
                    }
                    
                    player.getPackets().sendWindowsPane(Integer.valueOf(cmd[1]), Integer.valueOf(cmd[2]));
                    
                    return true;
                
                case "sendinter":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("<col=FFFFFF><shad=FF00000>Use: ::haircolor (ID #)");
                        return true;
                    }
                    if (Integer.valueOf(cmd[2]) > 1200) {
                        player.sm("<img=15><col=ff0000>This value is above the normal range!");
                        return true;
                    }
                    player.getInterfaceManager().sendTab(Integer.valueOf(cmd[1]), Integer.valueOf(cmd[2]));
                    return true;
                
                case "hideinter":
                    if (player.getRSMVRank() < 5) {
                        player.sm("<img=15><col=FF0000>You must be <shad=045000>RSMVer 5 or more</shad> to use this feature!");
                        player.isHiddenInter = false;
                    } else if (player.getRSMVRank() >= 5) {
                        if (player.isHiddenInter) {
                            player.sm("<img=15><col=ff0000>You are already in hidden interface mode.");
                        } else if (!player.isHiddenInter) {
                            player.isHiddenInter = true;
                            player.getInterfaceManager().sendInterfaces();
                            player.sm("<img=15><col=ff0000>You have hidden your interfaces.");
                        }
                    }
                    return true;
                
                case "showinter":
                    if (!player.isHiddenInter) {
                        player.sm("<img=15><col=ff0000>Your interfaces aren't hidden.");
                    } else if (player.isHiddenInter) {
                        player.isHiddenInter = false;
                        player.getInterfaceManager().sendInterfaces();
                        player.sm("<img=15><col=ff0000>You have revealed your interfaces.");
                    }
                    return true;
                
                case "switchtelelock":
                    if (player.getRSMVRank() < 3) {
                        player.sm("<img=15><col=FF0000>You must be <shad=045000>RSMVer 3 or more</shad> to use this feature!");
                    } else if (player.getRSMVRank() >= 3) {
                        player.toggleTelelock();
                    }
                    return true;
                
                case "switchskillcapes":
                    player.toggleOldSkillcapes();
                    return true;
                
                case "clearbank":
                    for (Item item : player.getBank().getContainerCopy()) {
                        if (item == null) {
                            continue;
                        }
                        int[] slot = player.getBank().getItemSlot(item.getId());
                        if (slot == null) {
                            continue;
                        }
                        player.getBank().removeItem(slot, item.getAmount(), true, false);
                    }
                    player.getBank().collapse(0);
                    player.getBank().collapse(1);
                    player.getBank().collapse(2);
                    player.getBank().collapse(3);
                    player.getBank().collapse(4);
                    player.getBank().collapse(5);
                    player.getBank().collapse(6);
                    player.getBank().collapse(7);
                    player.getBank().collapse(8);
                    player.getBank().collapse(9);
                    player.closeInterfaces();
                    player.getPackets().sendGameMessage("<col=00FF09>You have successfully cleared your bank!");
                    ;
                    return true;
                
                case "facemask":
                    if (player.getEquipment().hasAmulet()) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>Please unwield your amulet before using this command.");
                    } else {
                        CastleWars.setAmulet(player, new Item(4164, 1));
                    }
                    return true;
                
                case "maskedearmuffs":
                    if (player.getEquipment().hasAmulet()) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>Please unwield your amulet before using this command.");
                    } else {
                        CastleWars.setAmulet(player, new Item(13277, 1));
                    }
                    return true;
                
                case "goggles":
                    if (player.getEquipment().hasAmulet()) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>Please unwield your amulet before using this command.");
                    } else {
                        CastleWars.setAmulet(player, new Item(9472, 1));
                    }
                    return true;
                
                case "eyepatches":
                    if (player.getEquipment().hasAmulet()) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>Please unwield your amulet before using this command.");
                    } else {
                        CastleWars.setAmulet(player, new Item(13353, 1));
                    }
                    return true;
                
                case "highwayman":
                    if (player.getEquipment().hasAmulet()) {
                        player.getPackets().sendGameMessage("<col=FFFFFF><shad=FF00000>Please unwield your amulet before using this command.");
                    } else {
                        CastleWars.setAmulet(player, new Item(2631, 1));
                    }
                    return true;
                
                case "cole":
                    player.getInventory().addItem(19392, 1);
                    player.getPackets().sendGameMessage("You don't know you're toshero3!");
                    return true;
                
                case "enzo":
                    player.getInventory().addItem(8988, 1);
                    player.getInventory().addItem(9472, 1);
                    player.getPackets().sendGameMessage("<col=FF0000>You have unlocked a new music track: The Whole Pendulum Album.");
                    return true;
                
                case "swag":
                    player.getInventory().addItem(13816, 1);
                    player.getInventory().addItem(9763, 1);
                    return true;
                
                case "yell":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    sendYell(player, Utils.fixChatMessage(name), false);
                    return true;
                
                case "ancient":
                case "ancients":
                    player.getCombatDefinitions().setSpellBook(1);
                    return true;
                
                case "modern":
                case "moderns":
                    player.getCombatDefinitions().setSpellBook(0);
                    return true;
                
                case "lunar":
                case "lunars":
                    player.getCombatDefinitions().setSpellBook(2);
                    return true;
                
                case "skillcapes":
                    player.setNextGraphics(new Graphics(7));
                    player.getBank().addItem(9747, 2147400000, true);
                    player.getBank().addItem(9748, 2147400000, true);
                    player.getBank().addItem(9749, 2147400000, true);
                    player.getBank().addItem(9750, 2147400000, true);
                    player.getBank().addItem(9751, 2147400000, true);
                    player.getBank().addItem(9752, 2147400000, true);
                    player.getBank().addItem(9753, 2147400000, true);
                    player.getBank().addItem(9754, 2147400000, true);
                    player.getBank().addItem(9755, 2147400000, true);
                    player.getBank().addItem(9756, 2147400000, true);
                    player.getBank().addItem(9757, 2147400000, true);
                    player.getBank().addItem(9758, 2147400000, true);
                    player.getBank().addItem(9759, 2147400000, true);
                    player.getBank().addItem(9760, 2147400000, true);
                    player.getBank().addItem(9761, 2147400000, true);
                    player.getBank().addItem(9762, 2147400000, true);
                    player.getBank().addItem(9763, 2147400000, true);
                    player.getBank().addItem(9764, 2147400000, true);
                    player.getBank().addItem(9765, 2147400000, true);
                    player.getBank().addItem(9766, 2147400000, true);
                    player.getBank().addItem(9767, 2147400000, true);
                    player.getBank().addItem(9768, 2147400000, true);
                    player.getBank().addItem(9769, 2147400000, true);
                    player.getBank().addItem(9770, 2147400000, true);
                    player.getBank().addItem(9771, 2147400000, true);
                    player.getBank().addItem(9772, 2147400000, true);
                    player.getBank().addItem(9773, 2147400000, true);
                    player.getBank().addItem(9774, 2147400000, true);
                    player.getBank().addItem(9775, 2147400000, true);
                    player.getBank().addItem(9776, 2147400000, true);
                    player.getBank().addItem(9777, 2147400000, true);
                    player.getBank().addItem(9778, 2147400000, true);
                    player.getBank().addItem(9779, 2147400000, true);
                    player.getBank().addItem(9780, 2147400000, true);
                    player.getBank().addItem(9781, 2147400000, true);
                    player.getBank().addItem(9782, 2147400000, true);
                    player.getBank().addItem(9783, 2147400000, true);
                    player.getBank().addItem(9784, 2147400000, true);
                    player.getBank().addItem(9785, 2147400000, true);
                    player.getBank().addItem(9786, 2147400000, true);
                    player.getBank().addItem(9787, 2147400000, true);
                    player.getBank().addItem(9788, 2147400000, true);
                    player.getBank().addItem(9789, 2147400000, true);
                    player.getBank().addItem(9790, 2147400000, true);
                    player.getBank().addItem(9791, 2147400000, true);
                    player.getBank().addItem(9792, 2147400000, true);
                    player.getBank().addItem(9793, 2147400000, true);
                    player.getBank().addItem(9794, 2147400000, true);
                    player.getBank().addItem(9795, 2147400000, true);
                    player.getBank().addItem(9796, 2147400000, true);
                    player.getBank().addItem(9797, 2147400000, true);
                    player.getBank().addItem(9798, 2147400000, true);
                    player.getBank().addItem(9799, 2147400000, true);
                    player.getBank().addItem(9800, 2147400000, true);
                    player.getBank().addItem(9801, 2147400000, true);
                    player.getBank().addItem(9802, 2147400000, true);
                    player.getBank().addItem(9803, 2147400000, true);
                    player.getBank().addItem(9804, 2147400000, true);
                    player.getBank().addItem(9805, 2147400000, true);
                    player.getBank().addItem(9806, 2147400000, true);
                    player.getBank().addItem(9807, 2147400000, true);
                    player.getBank().addItem(9808, 2147400000, true);
                    player.getBank().addItem(9809, 2147400000, true);
                    player.getBank().addItem(9810, 2147400000, true);
                    player.getBank().addItem(9811, 2147400000, true);
                    player.getBank().addItem(9812, 2147400000, true);
                    player.getBank().addItem(9813, 2147400000, true);
                    player.getBank().addItem(9814, 2147400000, true);
                    player.getBank().addItem(9948, 2147400000, true);
                    player.getBank().addItem(9949, 2147400000, true);
                    player.getBank().addItem(9950, 2147400000, true);
                    player.getBank().addItem(12169, 2147400000, true);
                    player.getBank().addItem(12170, 2147400000, true);
                    player.getBank().addItem(12171, 2147400000, true);
                    player.getBank().addItem(18508, 2147400000, true);
                    player.getBank().addItem(18509, 2147400000, true);
                    player.getBank().addItem(18510, 2147400000, true);
                    
                    player.sendMessage("<col=FFFFFF><shad=FF00000>Check your inventory and bank for the Skillcapes!");
                    return true;
            }
            
        }
        return false;
    }
    
    public static boolean processNormalCommand(Player player, String[] cmd, boolean console, boolean clientCommand) {
        if (clientCommand) {
        
        } else {
            String message;
            switch (cmd[0]) {
                
                case "claim":
                    try {
                        player.rspsdata(player, player.getUsername());
                        return true;
                    } catch (Exception e) {
                    }
                    return true;
                
                case "home":
                    Magic.sendBroomTeleport(player, 0, 0.0D, new WorldTile(2598, 3409, 0), new int[0]);
                    player.getAppearence().setRenderEmote(-1);
                    
                    return true;
                
                case "empty":
                    player.getInventory().reset();
                    return true;
                
                case "players":
                    player.GetPlayers();
                    return true;
                
                case "changepass":
                    message = "";
                    for (int i = 1; i < cmd.length; i++) {
                        message += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    if (message.length() > 15 || message.length() < 5) {
                        player.getPackets().sendGameMessage("You cannot set your password to over 15 chars.");
                        return true;
                    }
                    player.setPassword(Encrypt.encryptSHA1(cmd[1]));
                    player.getPackets().sendGameMessage("You changed your password! Your password is " + cmd[1] + ".");
                    return true;
                
                case "switchitemslook":
                    player.switchItemsLook();
                    player.getPackets().sendGameMessage("You are now playing with " + (player.isOldItemsLook() ? "old" : "new") + " item looks.");
                    return true;
                
                case "yell":
                    message = "";
                    for (int i = 1; i < cmd.length; i++) {
                        message += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    sendYell(player, Utils.fixChatMessage(message), false);
                    return true;
                
                case "ancient":
                    player.getCombatDefinitions().setSpellBook(1);
                    return true;
                
                case "modern":
                    player.getCombatDefinitions().setSpellBook(0);
                    return true;
                
                case "lunar":
                    player.getCombatDefinitions().setSpellBook(2);
                    return true;
                
            }
            
        }
        return false;
    }
    
    public static void sendYell(Player player, String message, boolean staffYell) {
        if (player.getMuted() > Utils.currentTimeMillis()) {
            player.getPackets().sendGameMessage("You are muted.");
            return;
        }
        if (message.length() > 100) {
            message = message.substring(0, 100);
        }
        if (player.getRights() < 2) {
            String[] invalid = {"<euro", "<img", "<img=", "<col", "<col=", "<shad", "<shad=", "<str>", "<u>"};
            for (String s : invalid) {
                if (message.contains(s)) {
                    player.getPackets().sendGameMessage("<img=15><col=FF0000>You cannot add additional code to the message.");
                    return;
                }
            }
            if (!player.isMember() && player.getRights() <= 1) {
                World.sendWorldMessage("[Player] " + player.getDisplayName() + ": <col=02ab2f>" + message + "</col>", false);
                
            } else if (player.getRights() == 3) {
                World.sendWorldMessage("[<col=" + (player.getYellColor() == "ff0000" || player.getYellColor() == null ? "ff0000" : player.getYellColor()) + "><shad=000000>Mod</shad></col>] <img=0>" + player.getDisplayName() + ": <col=" + (player.getYellColor() == "ff0000" || player.getYellColor() == null ? "ff0000" : player.getYellColor()) + "><shad=000000>" + message + "", false);
                return;
            }

			/*
			 * RSMVer and Donor Yells
			 */
        } else if (player.getRights() == 2 && player.isRSMVer()) {
            
            if (player.isBronzeDonor() == true) {
                World.sendWorldMessage("<img=13><col=8C4919><shad=5C3400>[RSMVer]</col></shad><col=8C4919> " + player.getDisplayName() + ": " + message + "</col>", false);
            }
            
            if (player.isSilverDonor() == true) {
                World.sendWorldMessage("<img=14><col=999999><shad=828282>[RSMVer]</col></shad><col=999999> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isGoldDonor() == true) {
                World.sendWorldMessage("<img=15><col=FFF700><shad=E6DF20>[RSMVer]</col></shad><col=FFF700> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isPlatinumDonor() == true) {
                World.sendWorldMessage("<img=16><col=F0F0F0><shad=E3E1E3>[RSMVer]</col></shad><col=F0F0F0> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isDiamondDonor() == true) {
                World.sendWorldMessage("<img=17><col=2EFFD9><shad=31E0C0>[RSMVer]</col></shad><col=2EFFD9> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isJGUTTDonor() == true) {
                World.sendWorldMessage("<img=18><col=0F0F0F><shad=0F0F0F>[RSMVer 1]</col></shad><col=0F0F0F> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isBronzeDonor() == false && player.isSilverDonor() == false && player.isGoldDonor() == false && player.isPlatinumDonor() == false && player.isDiamondDonor() == false && player.isJGUTTDonor() == false) {
                World.sendWorldMessage("[<col=" + (player.getYellColor() == "000000" || player.getYellColor() == null ? "F6FF2E" : player.getYellColor()) + "><shad=000000>RSMVer</shad></col>] " + "<img=13>" + player.getDisplayName() + ": <col=" + (player.getYellColor() == "04FF00" || player.getYellColor() == null ? "ff0000" : player.getYellColor()) + "><shad=000000>" + message + "", false);
            }
            return;
        } else if (player.getRights() == 2 && player.getRSMVRank() == 1) {
            
            if (player.isBronzeDonor() == true) {
                World.sendWorldMessage("<img=13><col=8C4919><shad=5C3400>[RSMVer 1]</col></shad><col=8C4919> " + player.getDisplayName() + ": " + message + "</col>", false);
            }
            
            if (player.isSilverDonor() == true) {
                World.sendWorldMessage("<img=14><col=999999><shad=828282>[RSMVer 1]</col></shad><col=999999> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isGoldDonor() == true) {
                World.sendWorldMessage("<img=15><col=FFF700><shad=E6DF20>[RSMVer 1]</col></shad><col=FFF700> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isPlatinumDonor() == true) {
                World.sendWorldMessage("<img=16><col=F0F0F0><shad=E3E1E3>[RSMVer 1]</col></shad><col=F0F0F0> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isDiamondDonor() == true) {
                World.sendWorldMessage("<img=17><col=2EFFD9><shad=31E0C0>[RSMVer 1]</col></shad><col=2EFFD9> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isJGUTTDonor() == true) {
                World.sendWorldMessage("<img=18><col=0F0F0F><shad=0F0F0F>[RSMVer 1]</col></shad><col=0F0F0F> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isBronzeDonor() == false && player.isSilverDonor() == false && player.isGoldDonor() == false && player.isPlatinumDonor() == false && player.isDiamondDonor() == false && player.isJGUTTDonor() == false) {
                World.sendWorldMessage("[<col=" + (player.getYellColor() == "000000" || player.getYellColor() == null ? "F6FF2E" : player.getYellColor()) + "><shad=000000>RSMVer 1</shad></col>] " + "<img=13>" + player.getDisplayName() + ": <col=" + (player.getYellColor() == "04FF00" || player.getYellColor() == null ? "ff0000" : player.getYellColor()) + "><shad=000000>" + message + "", false);
            }
            return;
            
        } else if (player.getRights() == 2 && player.getRSMVRank() == 2) {
            
            if (player.isBronzeDonor() == true) {
                World.sendWorldMessage("<img=13><col=8C4919><shad=5C3400>[RSMVer 2]</col></shad><col=8C4919> " + player.getDisplayName() + ": " + message + "</col>", false);
            }
            
            if (player.isSilverDonor() == true) {
                World.sendWorldMessage("<img=14><col=999999><shad=828282>[RSMVer 2]</col></shad><col=999999> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isGoldDonor() == true) {
                World.sendWorldMessage("<img=15><col=FFF700><shad=E6DF20>[RSMVer 2]</col></shad><col=FFF700> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isPlatinumDonor() == true) {
                World.sendWorldMessage("<img=16><col=F0F0F0><shad=E3E1E3>[RSMVer 2]</col></shad><col=F0F0F0> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isDiamondDonor() == true) {
                World.sendWorldMessage("<img=17><col=2EFFD9><shad=31E0C0>[RSMVer 2]</col></shad><col=2EFFD9> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isJGUTTDonor() == true) {
                World.sendWorldMessage("<img=18><col=0F0F0F><shad=0F0F0F>[RSMVer 2]</col></shad><col=0F0F0F> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isBronzeDonor() == false && player.isSilverDonor() == false && player.isGoldDonor() == false && player.isPlatinumDonor() == false && player.isDiamondDonor() == false && player.isJGUTTDonor() == false) {
                World.sendWorldMessage("<img=19>[<col=" + (player.getYellColor() == "0000000" || player.getYellColor() == null ? "F6FF2E" : player.getYellColor()) + "><shad=000000>RSMVer 2</shad></col>] " + player.getDisplayName() + ": <col=" + (player.getYellColor() == "04FF00" || player.getYellColor() == null ? "ff0000" : player.getYellColor()) + "><shad=000000>" + message + "", false);
            }
            return;
            
        } else if (player.getRights() == 2 && player.getRSMVRank() == 3) {
            
            if (player.isBronzeDonor() == true) {
                World.sendWorldMessage("<img=13><col=8C4919><shad=5C3400>[RSMVer 3]</col></shad><col=8C4919> " + player.getDisplayName() + ": " + message + "</col>", false);
            }
            
            if (player.isSilverDonor() == true) {
                World.sendWorldMessage("<img=14><col=999999><shad=828282>[RSMVer 3]</col></shad><col=999999> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isGoldDonor() == true) {
                World.sendWorldMessage("<img=15><col=FFF700><shad=E6DF20>[RSMVer 3]</col></shad><col=FFF700> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isPlatinumDonor() == true) {
                World.sendWorldMessage("<img=16><col=F0F0F0><shad=E3E1E3>[RSMVer 3]</col></shad><col=F0F0F0> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isDiamondDonor() == true) {
                World.sendWorldMessage("<img=17><col=2EFFD9><shad=31E0C0>[RSMVer 3]</col></shad><col=2EFFD9> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isJGUTTDonor() == true) {
                World.sendWorldMessage("<img=18><col=0F0F0F><shad=0F0F0F>[RSMVer 3]</col></shad><col=0F0F0F> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isBronzeDonor() == false && player.isSilverDonor() == false && player.isGoldDonor() == false && player.isPlatinumDonor() == false && player.isDiamondDonor() == false && player.isJGUTTDonor() == false) {
                World.sendWorldMessage("<img=19>[<col=" + (player.getYellColor() == "0000000" || player.getYellColor() == null ? "F6FF2E" : player.getYellColor()) + "><shad=000000>RSMVer 3</shad></col>] " + player.getDisplayName() + ": <col=" + (player.getYellColor() == "04FF00" || player.getYellColor() == null ? "ff0000" : player.getYellColor()) + "><shad=000000>" + message + "", false);
            }
            return;
        } else if (player.getRights() == 2 && player.getRSMVRank() == 4) {
            
            if (player.isBronzeDonor() == true) {
                World.sendWorldMessage("<img=13><col=8C4919><shad=5C3400>[RSMVer 4]</col></shad><col=8C4919> " + player.getDisplayName() + ": " + message + "</col>", false);
            }
            
            if (player.isSilverDonor() == true) {
                World.sendWorldMessage("<img=14><col=999999><shad=828282>[RSMVer 4]</col></shad><col=999999> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isGoldDonor() == true) {
                World.sendWorldMessage("<img=15><col=FFF700><shad=E6DF20>[RSMVer 4]</col></shad><col=FFF700> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isPlatinumDonor() == true) {
                World.sendWorldMessage("<img=16><col=F0F0F0><shad=E3E1E3>[RSMVer 4]</col></shad><col=F0F0F0> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isDiamondDonor() == true) {
                World.sendWorldMessage("<img=17><col=2EFFD9><shad=31E0C0>[RSMVer 4]</col></shad><col=2EFFD9> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isJGUTTDonor() == true) {
                World.sendWorldMessage("<img=18><col=0F0F0F><shad=0F0F0F>[RSMVer 4]</col></shad><col=0F0F0F> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isBronzeDonor() == false && player.isSilverDonor() == false && player.isGoldDonor() == false && player.isPlatinumDonor() == false && player.isDiamondDonor() == false && player.isJGUTTDonor() == false) {
                World.sendWorldMessage("<img=19>[<col=" + (player.getYellColor() == "0000000" || player.getYellColor() == null ? "F6FF2E" : player.getYellColor()) + "><shad=000000>RSMVer 4</shad></col>] " + player.getDisplayName() + ": <col=" + (player.getYellColor() == "04FF00" || player.getYellColor() == null ? "ff0000" : player.getYellColor()) + "><shad=000000>" + message + "", false);
            }
            return;
            
        } else if (player.getRights() == 2 && player.getRSMVRank() == 5) {
            
            if (player.isBronzeDonor() == true) {
                World.sendWorldMessage("<img=13><col=8C4919><shad=5C3400>[RSMVer 5]</col></shad><col=8C4919> " + player.getDisplayName() + ": " + message + "</col>", false);
            }
            
            if (player.isSilverDonor() == true) {
                World.sendWorldMessage("<img=14><col=999999><shad=828282>[RSMVer 5]</col></shad><col=999999> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isGoldDonor() == true) {
                World.sendWorldMessage("<img=15><col=FFF700><shad=E6DF20>[RSMVer 5]</col></shad><col=FFF700> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isPlatinumDonor() == true) {
                World.sendWorldMessage("<img=16><col=F0F0F0><shad=E3E1E3>[RSMVer 5]</col></shad><col=F0F0F0> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isDiamondDonor() == true) {
                World.sendWorldMessage("<img=17><col=2EFFD9><shad=31E0C0>[RSMVer 5]</col></shad><col=2EFFD9> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isJGUTTDonor() == true) {
                World.sendWorldMessage("<img=18><col=0F0F0F><shad=0F0F0F>[RSMVer 5]</col></shad><col=0F0F0F> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isBronzeDonor() == false && player.isSilverDonor() == false && player.isGoldDonor() == false && player.isPlatinumDonor() == false && player.isDiamondDonor() == false && player.isJGUTTDonor() == false) {
                World.sendWorldMessage("<img=19>[<col=" + (player.getYellColor() == "0000000" || player.getYellColor() == null ? "F6FF2E" : player.getYellColor()) + "><shad=000000>RSMVer 5</shad></col>] " + player.getDisplayName() + ": <col=" + (player.getYellColor() == "04FF00" || player.getYellColor() == null ? "ff0000" : player.getYellColor()) + "><shad=000000>" + message + "", false);
            }
            return;
        } else if (player.getRights() == 2 && player.getRSMVRank() == 1) {
            
            if (player.isBronzeDonor() == true) {
                World.sendWorldMessage("<img=13><col=8C4919><shad=5C3400>[RSMVer 6]</col></shad><col=8C4919> " + player.getDisplayName() + ": " + message + "</col>", false);
            }
            
            if (player.isSilverDonor() == true) {
                World.sendWorldMessage("<img=14><col=999999><shad=828282>[RSMVer 6]</col></shad><col=999999> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isGoldDonor() == true) {
                World.sendWorldMessage("<img=15><col=FFF700><shad=E6DF20>[RSMVer 6]</col></shad><col=FFF700> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isPlatinumDonor() == true) {
                World.sendWorldMessage("<img=16><col=F0F0F0><shad=E3E1E3>[RSMVer 6]</col></shad><col=F0F0F0> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isDiamondDonor() == true) {
                World.sendWorldMessage("<img=17><col=2EFFD9><shad=31E0C0>[RSMVer 6]</col></shad><col=2EFFD9> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isJGUTTDonor() == true) {
                World.sendWorldMessage("<img=18><col=0F0F0F><shad=0F0F0F>[RSMVer 6]</col></shad><col=0F0F0F> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isBronzeDonor() == false && player.isSilverDonor() == false && player.isGoldDonor() == false && player.isPlatinumDonor() == false && player.isDiamondDonor() == false && player.isJGUTTDonor() == false) {
                World.sendWorldMessage("<img=19>[<col=" + (player.getYellColor() == "0000000" || player.getYellColor() == null ? "F6FF2E" : player.getYellColor()) + "><shad=000000>RSMVer 6</shad></col>] " + player.getDisplayName() + ": <col=" + (player.getYellColor() == "04FF00" || player.getYellColor() == null ? "ff0000" : player.getYellColor()) + "><shad=000000>" + message + "", false);
            }
            return;
        } else if (player.getRights() == 2 && player.getRSMVRank() == 7) {
            
            if (player.isBronzeDonor() == true) {
                World.sendWorldMessage("<img=13><col=8C4919><shad=5C3400>[RSMVer 7]</col></shad><col=8C4919> " + player.getDisplayName() + ": " + message + "</col>", false);
            }
            
            if (player.isSilverDonor() == true) {
                World.sendWorldMessage("<img=14><col=999999><shad=828282>[RSMVer 7]</col></shad><col=999999> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isGoldDonor() == true) {
                World.sendWorldMessage("<img=15><col=FFF700><shad=E6DF20>[RSMVer 7]</col></shad><col=FFF700> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isPlatinumDonor() == true) {
                World.sendWorldMessage("<img=16><col=F0F0F0><shad=E3E1E3>[RSMVer 7]</col></shad><col=F0F0F0> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isDiamondDonor() == true) {
                World.sendWorldMessage("<img=17><col=2EFFD9><shad=31E0C0>[RSMVer 7]</col></shad><col=2EFFD9> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isJGUTTDonor() == true) {
                World.sendWorldMessage("<img=18><col=0F0F0F><shad=0F0F0F>[RSMVer 7]</col></shad><col=0F0F0F> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isBronzeDonor() == false && player.isSilverDonor() == false && player.isGoldDonor() == false && player.isPlatinumDonor() == false && player.isDiamondDonor() == false && player.isJGUTTDonor() == false) {
                World.sendWorldMessage("<img=19>[<col=" + (player.getYellColor() == "0000000" || player.getYellColor() == null ? "F6FF2E" : player.getYellColor()) + "><shad=000000>RSMVer 7</shad></col>] " + player.getDisplayName() + ": <col=" + (player.getYellColor() == "04FF00" || player.getYellColor() == null ? "ff0000" : player.getYellColor()) + "><shad=000000>" + message + "", false);
            }
            return;
        } else if (player.getRights() == 2 && player.getRSMVRank() == 8) {
            
            if (player.isBronzeDonor() == true) {
                World.sendWorldMessage("<img=13><col=8C4919><shad=5C3400>[RSMVer 8]</col></shad><col=8C4919> " + player.getDisplayName() + ": " + message + "</col>", false);
            }
            
            if (player.isSilverDonor() == true) {
                World.sendWorldMessage("<img=14><col=999999><shad=828282>[RSMVer 8]</col></shad><col=999999> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isGoldDonor() == true) {
                World.sendWorldMessage("<img=15><col=FFF700><shad=E6DF20>[RSMVer 8]</col></shad><col=FFF700> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isPlatinumDonor() == true) {
                World.sendWorldMessage("<img=16><col=F0F0F0><shad=E3E1E3>[RSMVer 8]</col></shad><col=F0F0F0> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isDiamondDonor() == true) {
                World.sendWorldMessage("<img=17><col=2EFFD9><shad=31E0C0>[RSMVer 8]</col></shad><col=2EFFD9> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isJGUTTDonor() == true) {
                World.sendWorldMessage("<img=18><col=0F0F0F><shad=0F0F0F>[RSMVer 8]</col></shad><col=0F0F0F> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isBronzeDonor() == false && player.isSilverDonor() == false && player.isGoldDonor() == false && player.isPlatinumDonor() == false && player.isDiamondDonor() == false && player.isJGUTTDonor() == false) {
                World.sendWorldMessage("<img=19>[<col=" + (player.getYellColor() == "0000000" || player.getYellColor() == null ? "F6FF2E" : player.getYellColor()) + "><shad=000000>RSMVer 8</shad></col>] " + player.getDisplayName() + ": <col=" + (player.getYellColor() == "04FF00" || player.getYellColor() == null ? "ff0000" : player.getYellColor()) + "><shad=000000>" + message + "", false);
            }
            return;
        } else if (player.getRights() == 2 && player.getRSMVRank() == 9) {
            
            if (player.isBronzeDonor() == true) {
                World.sendWorldMessage("<img=13><col=8C4919><shad=5C3400>[RSMVer 9]</col></shad><col=8C4919> " + player.getDisplayName() + ": " + message + "</col>", false);
            }
            
            if (player.isSilverDonor() == true) {
                World.sendWorldMessage("<img=14><col=999999><shad=828282>[RSMVer 9]</col></shad><col=999999> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isGoldDonor() == true) {
                World.sendWorldMessage("<img=15><col=FFF700><shad=E6DF20>[RSMVer 9]</col></shad><col=FFF700> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isPlatinumDonor() == true) {
                World.sendWorldMessage("<img=16><col=F0F0F0><shad=E3E1E3>[RSMVer 9]</col></shad><col=F0F0F0> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isDiamondDonor() == true) {
                World.sendWorldMessage("<img=17><col=2EFFD9><shad=31E0C0>[RSMVer 9]</col></shad><col=2EFFD9> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isJGUTTDonor() == true) {
                World.sendWorldMessage("<img=18><col=0F0F0F><shad=0F0F0F>[RSMVer 9]</col></shad><col=0F0F0F> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isBronzeDonor() == false && player.isSilverDonor() == false && player.isGoldDonor() == false && player.isPlatinumDonor() == false && player.isDiamondDonor() == false && player.isJGUTTDonor() == false) {
                World.sendWorldMessage("<img=19>[<col=" + (player.getYellColor() == "0000000" || player.getYellColor() == null ? "F6FF2E" : player.getYellColor()) + "><shad=000000>RSMVer 9</shad></col>] " + player.getDisplayName() + ": <col=" + (player.getYellColor() == "04FF00" || player.getYellColor() == null ? "ff0000" : player.getYellColor()) + "><shad=000000>" + message + "", false);
            }
            return;
        } else if (player.getRights() == 2 && player.getRSMVRank() == 10) {
            
            if (player.isBronzeDonor() == true) {
                World.sendWorldMessage("<img=13><col=8C4919><shad=5C3400>[RSMV Master]</col></shad><col=8C4919> " + player.getDisplayName() + ": " + message + "</col>", false);
            }
            
            if (player.isSilverDonor() == true) {
                World.sendWorldMessage("<img=14><col=999999><shad=828282>[RSMV Master]</col></shad><col=999999> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isGoldDonor() == true) {
                World.sendWorldMessage("<img=15><col=FFF700><shad=E6DF20>[RSMV Master]</col></shad><col=FFF700> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isPlatinumDonor() == true) {
                World.sendWorldMessage("<img=16><col=F0F0F0><shad=E3E1E3>[RSMV Master]</col></shad><col=F0F0F0> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isDiamondDonor() == true) {
                World.sendWorldMessage("<img=17><col=2EFFD9><shad=31E0C0>[RSMV Master]</col></shad><col=2EFFD9> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isJGUTTDonor() == true) {
                World.sendWorldMessage("<img=18><col=0F0F0F><shad=0F0F0F>[RSMV Master]</col></shad><col=0F0F0F> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isBronzeDonor() == false && player.isSilverDonor() == false && player.isGoldDonor() == false && player.isPlatinumDonor() == false && player.isDiamondDonor() == false && player.isJGUTTDonor() == false) {
                World.sendWorldMessage("<img=19>[<col=" + (player.getYellColor() == "0000000" || player.getYellColor() == null ? "F6FF2E" : player.getYellColor()) + "><shad=000000>RSMV Master</shad></col>] " + player.getDisplayName() + ": <col=" + (player.getYellColor() == "04FF00" || player.getYellColor() == null ? "ff0000" : player.getYellColor()) + "><shad=000000>" + message + "", false);
            }
            return;
        } else if (player.getRights() == 2) {
            
            if (player.isBronzeDonor() == true) {
                World.sendWorldMessage("<img=13><col=8C4919><shad=5C3400>[RSMVer / Bronze Donor]</col></shad><col=8C4919> " + player.getDisplayName() + ": " + message + "</col>", false);
            }
            
            if (player.isSilverDonor() == true) {
                World.sendWorldMessage("<img=14><col=999999><shad=828282>[RSMVer]</col></shad><col=999999> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isGoldDonor() == true) {
                World.sendWorldMessage("<img=15><col=FFF700><shad=E6DF20>[RSMVer]</col></shad><col=FFF700> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isPlatinumDonor() == true) {
                World.sendWorldMessage("<img=16><col=F0F0F0><shad=E3E1E3>[RSMVer]</col></shad><col=F0F0F0> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isDiamondDonor() == true) {
                World.sendWorldMessage("<img=17><col=2EFFD9><shad=31E0C0>[RSMVer]</col></shad><col=2EFFD9> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isJGUTTDonor() == true) {
                World.sendWorldMessage("<img=18><col=0F0F0F><shad=0F0F0F>[RSMVer]</col></shad><col=0F0F0F> " + player.getDisplayName() + ": " + message + "</col>", false);
                
            }
            if (player.isBronzeDonor() == false && player.isSilverDonor() == false && player.isGoldDonor() == false && player.isPlatinumDonor() == false && player.isDiamondDonor() == false && player.isJGUTTDonor() == false) {
                if (player.getUsername().equalsIgnoreCase("blackbandwar")) {
                    World.sendWorldMessage("<col=" + (player.getYellColor() == "0000000" || player.getYellColor() == null ? "F6FF2E" : player.getYellColor()) + "><shad=000000></shad></col> " + player.getDisplayName() + ": <col=" + (player.getYellColor() == "04FF00" || player.getYellColor() == null ? "ff0000" : player.getYellColor()) + "><shad=000000>" + message + "", false);
                } else if (!player.getUsername().equalsIgnoreCase("blackbandwar")) {
                    World.sendWorldMessage("<img=19>[<col=" + (player.getYellColor() == "0000000" || player.getYellColor() == null ? "F6FF2E" : player.getYellColor()) + "><shad=000000>RSMVer</shad></col>] " + player.getDisplayName() + ": <col=" + (player.getYellColor() == "04FF00" || player.getYellColor() == null ? "ff0000" : player.getYellColor()) + "><shad=000000>" + message + "", false);
                    
                }
            }
            return;

			/*
			 * Staff Yells
			 */
        } else if (player.getUsername().equalsIgnoreCase("Multak")) {
            World.sendWorldMessage("[<col=" + (player.getYellColor() == "9A2EFE" || player.getYellColor() == null ? "ffffff" : player.getYellColor()) + ">Owner</col>] <img=1>" + player.getDisplayName() + ": <col=" + (player.getYellColor() == "9A2EFE" || player.getYellColor() == null ? "ffffff" : player.getYellColor()) + ">" + message + "</col>", false);
            return;
        } else if (player.getUsername().equalsIgnoreCase("toshero")) {
            World.sendWorldMessage("[<col=" + (player.getYellColor() == "9A2EFE" || player.getYellColor() == null ? "ffffff" : player.getYellColor()) + ">Head-Admin</col>] <img=1>" + player.getDisplayName() + ": <col=" + (player.getYellColor() == "9A2EFE" || player.getYellColor() == null ? "ffffff" : player.getYellColor()) + ">" + message + "</col>", false);
            return;
        } else if (player.getUsername().equalsIgnoreCase("wally")) {
            World.sendWorldMessage("[<col=" + (player.getYellColor() == "9A2EFE" || player.getYellColor() == null ? "ffffff" : player.getYellColor()) + ">Hmm-Admin</col>] <img=1>" + player.getDisplayName() + ": <col=" + (player.getYellColor() == "9A2EFE" || player.getYellColor() == null ? "ffffff" : player.getYellColor()) + ">" + message + "</col>", false);
            return;
        } else if (player.getUsername().equalsIgnoreCase("jon")) {
            World.sendWorldMessage("[<col=" + (player.getYellColor() == "9A2EFE" || player.getYellColor() == null ? "ffffff" : player.getYellColor()) + ">JoneMarrow-Admin</col>] <img=1>" + player.getDisplayName() + ": <col=" + (player.getYellColor() == "9A2EFE" || player.getYellColor() == null ? "ffffff" : player.getYellColor()) + ">" + message + "</col>", false);
            return;
        } else if (player.getUsername().equalsIgnoreCase("misfit_hita")) {
            World.sendWorldMessage("[<col=" + (player.getYellColor() == "9A2EFE" || player.getYellColor() == null ? "ffffff" : player.getYellColor()) + ">Supervillain-Admin</col>] <img=1>" + player.getDisplayName() + ": <col=" + (player.getYellColor() == "9A2EFE" || player.getYellColor() == null ? "ffffff" : player.getYellColor()) + ">" + message + "</col>", false);
            return;
        } else if (player.getUsername().equalsIgnoreCase("angel")) {
            World.sendWorldMessage("[<col=" + (player.getYellColor() == "9A2EFE" || player.getYellColor() == null ? "ffffff" : player.getYellColor()) + ">Halo-Admin</col>] <img=1>" + player.getDisplayName() + ": <col=" + (player.getYellColor() == "9A2EFE" || player.getYellColor() == null ? "ffffff" : player.getYellColor()) + ">" + message + "</col>", false);
            return;
        } else if (player.getUsername().equalsIgnoreCase("Oz")) {
            World.sendWorldMessage("[<col=" + (player.getYellColor() == "9A2EFE" || player.getYellColor() == null ? "ffffff" : player.getYellColor()) + ">Lead-Admin</col>] <img=0>" + player.getDisplayName() + ": <col=" + (player.getYellColor() == "9A2EFE" || player.getYellColor() == null ? "ffffff" : player.getYellColor()) + ">" + message + "</col>", false);
            return;
        } else if (player.getUsername().equalsIgnoreCase("zangetsu")) {
            World.sendWorldMessage("[<col=" + (player.getYellColor() == "9A2EFE" || player.getYellColor() == null ? "ffffff" : player.getYellColor()) + ">Gainz-Mod</col>] <img=0>" + player.getDisplayName() + ": <col=" + (player.getYellColor() == "9A2EFE" || player.getYellColor() == null ? "ffffff" : player.getYellColor()) + ">" + message + "</col>", false);
            return;
        } else if (player.getUsername().equalsIgnoreCase("ahxchurch")) {
            World.sendWorldMessage("[<col=" + (player.getYellColor() == "9A2EFE" || player.getYellColor() == null ? "ffffff" : player.getYellColor()) + ">Djent-Mod</col>] <img=0>" + player.getDisplayName() + ": <col=" + (player.getYellColor() == "9A2EFE" || player.getYellColor() == null ? "ffffff" : player.getYellColor()) + ">" + message + "</col>", false);
            return;
        } else if (player.getUsername().equalsIgnoreCase("chase")) {
            World.sendWorldMessage("[<col=" + (player.getYellColor() == "9A2EFE" || player.getYellColor() == null ? "ffffff" : player.getYellColor()) + ">Head-Mod</col>] <img=0>" + player.getDisplayName() + ": <col=" + (player.getYellColor() == "9A2EFE" || player.getYellColor() == null ? "ffffff" : player.getYellColor()) + ">" + message + "</col>", false);
            return;
        } else {
            World.sendWorldMessage("[<col=" + (player.getYellColor() == "ff0000" || player.getYellColor() == null ? "1589FF" : player.getYellColor()) + ">Player</col>] <img=25>" + player.getDisplayName() + ": <col=" + (player.getYellColor() == "ff0000" || player.getYellColor() == null ? "1589FF" : player.getYellColor()) + ">" + message + "</col>", false);
        }
    }
    
    public static void archiveLogs(Player player, String[] cmd) {
        try {
            if (player.getRights() <= 2) {
                return;
            }
            String location = "";
            if (player.getRights() == 4) {
                location = "data/logs/admin/" + player.getUsername() + ".txt";
            } else if (player.getRights() == 3) {
                location = "data/logs/mod/" + player.getUsername() + ".txt";
            }
            String afterCMD = "";
            for (int i = 1; i < cmd.length; i++) {
                afterCMD += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(location, true));
            writer.write("[" + currentTime("dd MMMMM yyyy 'at' hh:mm:ss z") + "] - ::" + cmd[0] + " " + afterCMD);
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String currentTime(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());
    }
}