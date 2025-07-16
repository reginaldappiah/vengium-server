package com.rs.game.player;


import java.util.concurrent.ConcurrentHashMap;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;

import com.rs.Settings;
import com.rs.cache.loaders.AnimationDefinitions;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cores.CoresManager;
import com.rs.game.Animation;
import com.rs.game.ForceMovement;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.minigames.CastleWars;
import com.rs.game.Region;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.minigames.FightPits;
import com.rs.game.minigames.clanwars.ClanWars;
import com.rs.game.minigames.clanwars.WallHandler;
import com.rs.game.npc.NPC;
import com.rs.game.npc.others.Bork;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.Magic;
import com.rs.game.player.content.Notes.Note;
import com.rs.game.player.content.dungeoneering.DungeonPartyManager;
import com.rs.game.player.content.pet.Pets;
import com.rs.game.player.controlers.FightKiln;
import com.rs.game.player.cutscenes.HomeCutScene;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.DisplayNames;
import com.rs.utils.Encrypt;
import com.rs.utils.IPBanL;
import com.rs.utils.NPCSpawns;
import com.rs.utils.PkRank;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.ShopsHandler;
import com.rs.utils.Utils;

public class InterfaceManager {

    public static final int FIXED_WINDOW_ID = 548;
    public static final int RESIZABLE_WINDOW_ID = 746;
    public static final int CHAT_BOX_TAB = 13;
    public static final int FIXED_SCREEN_TAB_ID = 27;
    public static final int RESIZABLE_SCREEN_TAB_ID = 28;
    public static final int FIXED_INV_TAB_ID = 166;
    public static final int RESIZABLE_INV_TAB_ID = 108;
    private final ConcurrentHashMap<Integer, int[]> openedinterfaces = new ConcurrentHashMap<Integer, int[]>();
    public int viewingInterface = 542;
    private Player player;
    private boolean resizableScreen;
    private int windowsPane;

    public InterfaceManager(Player player) {
        this.player = player;
    }

    public void sendTab(int tabId, int interfaceId) {
        player.getPackets().sendInterface(true,
                                          resizableScreen ? RESIZABLE_WINDOW_ID : FIXED_WINDOW_ID, tabId,
                                          interfaceId);
    }

    public void sendChatBoxInterface(int interfaceId) {
        player.getPackets().sendInterface(true, 752, CHAT_BOX_TAB, interfaceId);
    }

    public void closeChatBoxInterface() {
        player.getPackets().closeInterface(CHAT_BOX_TAB);
    }

    public void sendOverlay(int interfaceId, boolean fullScreen) {
        sendTab(resizableScreen ? fullScreen ? 1 : 11 : 0, interfaceId);
    }

    public void closeOverlay(boolean fullScreen) {
        player.getPackets().closeInterface(resizableScreen ? fullScreen ? 1 : 11 : 0);
    }

    public void sendInterface(int interfaceId) {
        player.getPackets()
                .sendInterface(
                        false,
                        resizableScreen ? RESIZABLE_WINDOW_ID : FIXED_WINDOW_ID,
                        resizableScreen ? RESIZABLE_SCREEN_TAB_ID
                                        : FIXED_SCREEN_TAB_ID, interfaceId);
    }

    public void sendInventoryInterface(int childId) {
        player.getPackets().sendInterface(false,
                                          resizableScreen ? RESIZABLE_WINDOW_ID : FIXED_WINDOW_ID,
                                          resizableScreen ? RESIZABLE_INV_TAB_ID : FIXED_INV_TAB_ID,
                                          childId);
    }


    public final void sendInterfaces() {
        if (player.getDisplayMode() == 2 || player.getDisplayMode() == 3) {
            resizableScreen = true;
            sendFullScreenInterfaces();
        }
        else {
            resizableScreen = false;
            sendFixedInterfaces();
        }
        player.getSkills().sendInterfaces();
        player.getCombatDefinitions().sendUnlockAttackStylesButtons();
        player.getMusicsManager().unlockMusicPlayer();
        player.getEmotesManager().unlockEmotesBook();
        player.getInventory().unlockInventoryOptions();
        player.getPrayer().unlockPrayerBookButtons();
        if (player.getFamiliar() != null && player.isRunning()) {
            player.getFamiliar().unlock();
        }
        player.getControlerManager().sendInterfaces();
        player.getPackets().sendWindowsPane(746, 0);
        player.getPackets().sendIComponentText(746, 17, "Vengium BETA");
        player.getPackets().sendIComponentText(746, 183, "Coming July 31st");
    }


    public void replaceRealChatBoxInterface(int interfaceId) {
        player.getPackets().sendInterface(true, 752, 11, interfaceId);
    }

    public void closeReplacedRealChatBoxInterface() {
        player.getPackets().closeInterface(752, 11);
    }

    public void sendWindowPane() {
        player.getPackets().sendWindowsPane(resizableScreen ? 746 : 548, 0);
    }

    public void sendFullScreenInterfaces() {
        player.getPackets().sendIComponentSettings(590, 8, 0, 160, 100);
        player.getPackets().sendIComponentSettings(590, 13, 0, 11, 200);
        player.getPackets().sendWindowsPane(746, 0);
        sendTab(21, 752);
        sendTab(22, 751);
        sendTab(15, 745);
        sendTab(25, 754);
		/*
		 *RSMVer 5 (;;sendinter)
		 */
        if (player.isHiddenInter) {
            sendTab(195, 0);//Attack
            sendTab(196, 0);//Running
            sendTab(197, 0);//Prayer
            sendTab(198, 0);//Summonining
        }
        else if (!player.isHiddenInter) {
            sendTab(195, 748);//Attack
            sendTab(196, 749);//Running
            sendTab(197, 750);//Prayer
            sendTab(198, 747);//Summonining
        }
        player.getPackets().sendInterface(true, 752, 9, 137);
        sendCombatStyles();
        sendTicketSystem();
        sendSkills();
        sendInfoTab();
        sendPlayerPanel();
        sendInventory();
        sendEquipment();
        sendPrayerBook();
        sendMagicBook();
        sendTab(120, 550); // friend list
        sendTab(121, 1109); // 551 ignore now friendchat
        sendTab(122, 1110); // 589 old clan chat now new clan chat
        sendSettings();
        sendEmotes();
        sendTab(125, 187); // music
        sendTab(126, 34); // notes
        sendTab(129, 182); // logout*/
        sendTab(resizableScreen ? 119 : 179, 1140);
        player.getPackets().sendGlobalConfig(823, 1);
    }

    public void sendFixedInterfaces() {
        player.getPackets().sendIComponentSettings(590, 8, 0, 160, 8388614);
        player.getPackets().sendIComponentSettings(590, 13, 0, 11, 2);
        player.getPackets().sendWindowsPane(548, 0);
        sendTab(161, 752);
        sendTab(37, 751);
        sendTab(23, 745);
        sendTab(25, 754);
		/*
		 *RSMVer 5 (;;sendinter)
		 */
        if (player.isHiddenInter) {
            sendTab(151, 0);//Attack
            sendTab(152, 0);//Running
            sendTab(153, 0);//Prayer
            sendTab(155, 0);//Summonining
        }
        else if (!player.isHiddenInter) {
            sendTab(151, 748);//Attack
            sendTab(152, 749);//Running
            sendTab(153, 750);//Prayer
            sendTab(155, 747);//Summonining
        }
        player.getPackets().sendInterface(true, 752, 9, 137);
        sendMagicBook();
        sendPrayerBook();
        sendEquipment();
        sendInventory();
        sendInfoTab();
        sendPlayerPanel();
        sendTab(181, 1109);// 551 ignore now friendchat
        sendTab(182, 1110);// 589 old clan chat now new clan chat
        sendTab(179, 34);
        sendTab(180, 550);// friend list
        sendTab(185, 187);// music
        sendTab(186, 34); // notes
        sendTab(189, 182);
        sendSkills();
        sendEmotes();
        sendSettings();
        sendTicketSystem();
        sendCombatStyles();
    }

    public void sendXPPopup() {
        sendTab(resizableScreen ? 38 : 10, 1213); //xp
    }

    public void sendXPDisplay() {
        sendXPDisplay(1215);  //xp counter
    }

    public void sendXPDisplay(int interfaceId) {
        sendTab(resizableScreen ? 27 : 29, interfaceId);  //xp counter
    }

    public void closeXPPopup() {
        player.getPackets().closeInterface(resizableScreen ? 38 : 10);
    }

    public void closeXPDisplay() {
        player.getPackets().closeInterface(resizableScreen ? 27 : 29);
    }

    public void sendEquipment() {
        sendTab(resizableScreen ? 116 : 176, 387);
    }

    public void closeInterface(int one, int two) {
        player.getPackets().closeInterface(resizableScreen ? two : one);
    }

    public void closeEquipment() {
        player.getPackets().closeInterface(resizableScreen ? 116 : 176);
    }


    public void closeInfoTab() {
        player.getPackets().closeInterface(resizableScreen ? 119 : 179);
    }

    public void sendInventory() {
        sendTab(resizableScreen ? 115 : 175, Inventory.INVENTORY_INTERFACE);
    }

    public void sendInfoTab() {
        sendTab(resizableScreen ? 119 : 179, 930);
        setWindowInterface(resizableScreen ? 162 : 187, 930);
        WorldTasksManager.schedule(new WorldTask() {
            @Override
            public void run() {
                player.getPackets().sendIComponentText(930, 10, "<col=ff0000>Re-open the tab to refresh.	</col>");
                player.getPackets().sendIComponentText(930, 16, "                                       Server: <col=FFFFFF>name</col>              Version: <col=FFFFFF>version</col>                  Online: <col=FFFFFF>" + World.getPlayers().size() + " </col>" +
                        "                                  Owner: <col=FFFFFF>...</col>             Skill Points: <col=FFFFFF> </col>" +
                        "                                                                              Kills: <col=FFFFFF>" + player.getKillCount() + "</col>                                  Deaths: <col=FFFFFF>" + player.getDeathCount() + "</col>");
            }
        }, 0, 5);
    }

    public void setWindowInterface(int componentId, int interfaceId) {
        player.getPackets().sendInterface(true, resizableScreen ? RESIZABLE_WINDOW_ID : FIXED_WINDOW_ID, componentId, interfaceId);
    }

    public void sendPlayerPanel() {
        sendTab(resizableScreen ? 114 : 174, 506);
        if (player.getRights() >= 2 || !player.isPlayer()) {
            player.getPackets().sendIComponentText(506, 0, "<col=ffff00>Vengium Panel");
            player.getPackets().sendIComponentText(506, 4, "          <col=0088ff>Staff           List");
            player.getPackets().sendIComponentText(506, 6, "<col=4EE104>RSMVSet");
            player.getPackets().sendIComponentText(506, 8, "         <col=F8FF02>Quick             Accessories");
            player.getPackets().sendIComponentText(506, 10, "       <col=FF02B0>Vengium         Websites");
            player.getPackets().sendIComponentText(506, 12, "       <col=02FFE7>Account        Settings");
            player.getPackets().sendIComponentText(506, 14, "<col=FF7402>Bank");
            if (player.getRights() < 2) {
                player.getPackets().sendIComponentText(506, 2, "<col=ff0000>                 <col=ff0000>Home                 Rank:Player");
            }
            else if (player.getRights() == 2) {
                player.getPackets().sendIComponentText(506, 2, "<col=ff0000>                 <col=ff0000>Home                 Rank:RSMVer <img=19>");
            }
            else if (player.getRights() == 3) {
                player.getPackets().sendIComponentText(506, 2, "<col=ff0000>                 <col=ff0000>Home                 Rank:Mod <img=0>");
            }
            else if (player.getRights() == 4) {
                player.getPackets().sendIComponentText(506, 2, "<col=ff0000>                 <Col=ff0000>Home                 Rank:Admin <img=1>");
            }
        }
        if (player.isPlayer() || !player.isRSMVerRank()) {
            player.getPackets().sendIComponentText(506, 0, "<col=ffff00>Vengium Panel");
            player.getPackets().sendIComponentText(506, 4, "<col=0088ff>Staff List");
            player.getPackets().sendIComponentText(506, 6, "<col=4EE104>Web-store");
            player.getPackets().sendIComponentText(506, 8, "<col=F8FF02>Training");
            player.getPackets().sendIComponentText(506, 10, "<col=FF02B0>Monsters");
            player.getPackets().sendIComponentText(506, 12, "<col=02FFE7>Skilling");
            player.getPackets().sendIComponentText(506, 14, "<col=FF7402>Mini-games");
            if (player.isPlayer() || player.getRights() < 2) {
                player.getPackets().sendIComponentText(506, 2, "<col=ff0000>                 <col=ff0000>Home                 Rank:Player");
            }
            else if (player.getRights() == 2) {
                player.getPackets().sendIComponentText(506, 2, "<col=ff0000>                 <col=ff0000>Home                 Rank:RSMVer <img=19>");
            }
            else if (player.getRights() == 3) {
                player.getPackets().sendIComponentText(506, 2, "<col=ff0000>                 <col=ff0000>Home                 Rank:Mod <img=0>");
            }
            else if (player.getRights() == 4) {
                player.getPackets().sendIComponentText(506, 2, "<col=ff0000>                 <Col=ff0000>Home                 Rank:Admin <img=1>");
            }
        }


    }

    public void closeInventory() {
        player.getPackets().closeInterface(resizableScreen ? 115 : 175);
    }

    public void closeSkills() {
        player.getPackets().closeInterface(resizableScreen ? 113 : 206);
    }

    public void closeCombatStyles() {
        player.getPackets().closeInterface(resizableScreen ? 111 : 204);
    }

    public void closePlayerPanel() {
        player.getPackets().closeInterface(resizableScreen ? 114 : 174, 506);
    }

    public void closeTicketSystem() {
        player.getPackets().closeInterface(resizableScreen ? 112 : 205);
    }

    public void sendCombatStyles() {
        sendTab(resizableScreen ? 111 : 171, 884);
    }

    public void sendTicketSystem() {
        sendTab(resizableScreen ? 112 : 172, 1019);
        player.getPackets().sendIComponentText(1019, 3, "Player Support");
        player.getPackets().sendIComponentText(1019, 16, "Answer Trivia");
        player.getPackets().sendIComponentText(1019, 18, "Account Manager");
        player.getPackets().sendIComponentText(1019, 11, "Submit Ticket");
        player.getPackets().sendIComponentText(1019, 0, "Answer the trivias using this button. ");
        player.getPackets().sendIComponentText(1019, 8, "Submit a request ticket to online staff members ");
    }

    public void sendSkills() {
        sendTab(resizableScreen ? 113 : 173, 320);
    }

    public void sendSettings() {
        sendSettings(261);
    }

    public void closeSettings() {
        player.getPackets().closeInterface(resizableScreen ? 123 : 183);
    }

    public void sendSettings(int interfaceId) {
        sendTab(resizableScreen ? 123 : 183, interfaceId);
    }

    public void sendPrayerBook() {
        sendTab(resizableScreen ? 117 : 177, 271);
    }

    public void closePrayerBook() {
        player.getPackets().closeInterface(resizableScreen ? 117 : 210);
    }


    public void sendMagicBook() {
        sendTab(resizableScreen ? 118 : 178, player.getCombatDefinitions()
                .getSpellBook());
    }

    public void closeMagicBook() {
        player.getPackets().closeInterface(resizableScreen ? 118 : 211);
    }

    public void sendEmotes() {
        sendTab(resizableScreen ? 124 : 184, 590);
    }

    public void closeEmotes() {
        player.getPackets().closeInterface(resizableScreen ? 124 : 217);
    }

    public boolean addInterface(int windowId, int tabId, int childId) {
        if (openedinterfaces.containsKey(tabId)) {
            player.getPackets().closeInterface(tabId);
        }
        openedinterfaces.put(tabId, new int[]{childId, windowId});
        return openedinterfaces.get(tabId)[0] == childId;
    }

    public boolean containsInterface(int tabId, int childId) {
        if (childId == windowsPane) {
            return true;
        }
        if (!openedinterfaces.containsKey(tabId)) {
            return false;
        }
        return openedinterfaces.get(tabId)[0] == childId;
    }

    public int getTabWindow(int tabId) {
        if (!openedinterfaces.containsKey(tabId)) {
            return FIXED_WINDOW_ID;
        }
        return openedinterfaces.get(tabId)[1];
    }

    public boolean containsInterface(int childId) {
        if (childId == windowsPane) {
            return true;
        }
        for (int[] value : openedinterfaces.values())
            if (value[0] == childId) {
                return true;
            }
        return false;
    }

    public boolean containsTab(int tabId) {
        return openedinterfaces.containsKey(tabId);
    }

    public void removeAll() {
        openedinterfaces.clear();
    }

    public boolean containsScreenInter() {
        return containsTab(resizableScreen ? RESIZABLE_SCREEN_TAB_ID
                                           : FIXED_SCREEN_TAB_ID);
    }

    public void closeScreenInterface() {
        player.getPackets()
                .closeInterface(
                        resizableScreen ? RESIZABLE_SCREEN_TAB_ID
                                        : FIXED_SCREEN_TAB_ID);
    }

    public boolean containsInventoryInter() {
        return containsTab(resizableScreen ? RESIZABLE_INV_TAB_ID
                                           : FIXED_INV_TAB_ID);
    }

    public void closeInventoryInterface() {
        player.getPackets().closeInterface(
                resizableScreen ? RESIZABLE_INV_TAB_ID : FIXED_INV_TAB_ID);
    }

    public boolean containsChatBoxInter() {
        return containsTab(CHAT_BOX_TAB);
    }

    public boolean removeTab(int tabId) {
        return openedinterfaces.remove(tabId) != null;
    }

    public boolean removeInterface(int tabId, int childId) {
        if (!openedinterfaces.containsKey(tabId)) {
            return false;
        }
        if (openedinterfaces.get(tabId)[0] != childId) {
            return false;
        }
        return openedinterfaces.remove(tabId) != null;
    }

    public void sendFadingInterface(int backgroundInterface) {
        if (hasRezizableScreen()) {
            player.getPackets().sendInterface(true, RESIZABLE_WINDOW_ID, 12, backgroundInterface);
        }
        else {
            player.getPackets().sendInterface(true, FIXED_WINDOW_ID, 11, backgroundInterface);
        }
    }

    public void closeFadingInterface() {
        if (hasRezizableScreen()) {
            player.getPackets().closeInterface(12);
        }
        else {
            player.getPackets().closeInterface(11);
        }
    }

    public void sendScreenInterface(int backgroundInterface, int interfaceId) {
        player.getInterfaceManager().closeScreenInterface();

        if (hasRezizableScreen()) {
            player.getPackets().sendInterface(false, RESIZABLE_WINDOW_ID, 40,
                                              backgroundInterface);
            player.getPackets().sendInterface(false, RESIZABLE_WINDOW_ID, 41,
                                              interfaceId);
        }
        else {
            player.getPackets().sendInterface(false, FIXED_WINDOW_ID, 200,
                                              backgroundInterface);
            player.getPackets().sendInterface(false, FIXED_WINDOW_ID, 201,
                                              interfaceId);

        }

        player.setCloseInterfacesEvent(new Runnable() {
            @Override
            public void run() {
                if (hasRezizableScreen()) {
                    player.getPackets().closeInterface(40);
                    player.getPackets().closeInterface(41);
                }
                else {
                    player.getPackets().closeInterface(200);
                    player.getPackets().closeInterface(201);
                }
            }
        });
    }

    public boolean hasRezizableScreen() {
        return resizableScreen;
    }

    public int getWindowsPane() {
        return windowsPane;
    }

    public void setWindowsPane(int windowsPane) {
        this.windowsPane = windowsPane;
    }

    public void bragDuelist6() {
        player.setNextAnimation(new Animation(533));
        player.setNextGraphics(new Graphics(92));
    }

    public void bragDuelist5() {
        player.setNextAnimation(new Animation(511));
        player.setNextGraphics(new Graphics(92));
    }

    public void Salty() {
        player.setNextAnimation(new Animation(329));
    }

    public void FishingTrawler() {
        player.getAppearence().setRenderEmote(152);
    }

    public void CandyCane() {
        player.setNextAnimation(new Animation(12664));
    }

    public void ToyKite() {
        player.setNextAnimation(new Animation(8990));
    }

    public void Bouquet() {
        player.setNextAnimation(new Animation(10951));
    }

    public void Flared() {
        player.setNextAnimation(new Animation(5316));
    }

    public void Suffocate() {
        player.setNextAnimation(new Animation(5862));
    }

    public void Lilly() {
        player.setNextAnimation(new Animation(11622));
    }

    public void Bubbles() {
        player.setNextAnimation(new Animation(10940));
        player.setNextGraphics(new Graphics(721));

    }

    public void Annihilator() {
        player.setNextGraphics(new Graphics(3193));
        player.setNextAnimation(new Animation(16964));
    }

    public void HammerSpin() {
        player.setNextAnimation(new Animation(15149));
        player.setNextGraphics(new Graphics(2953));

    }

    public void ClanPlace() {
        player.setNextAnimation(new Animation(8711));
        player.setNextGraphics(new Graphics(1588, 0, 50));
        return;
    }

    public void ClanTeleport() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 0) {
                    player.lock();
                    player.setNextGraphics(new Graphics(537));
                    player.setNextAnimation(new Animation(7389));
                }
                if (step == 1) {
                    //player.setNextGraphics(new Graphics(1588));
                }
                if (step == 2) {
                    player.unlock();
                    stop();
                }
                step++;
            }
        }, 0, 3);
    }


    public void HammerBrandish() {
        player.setNextAnimation(new Animation(15150));
    }

    public void WolfDance() {
        player.setNextAnimation(new Animation(14302));
        player.setNextGraphics(new Graphics(120));
    }

    public void DragonFire() {
        player.setNextAnimation(new Animation(6696));
        player.setNextGraphics(new Graphics(1165));
    }

    public void PengDance() {
        player.setNextAnimation(new Animation(14301));
        player.setNextGraphics(new Graphics(119));
    }

    public void BatDance() {
        player.setNextAnimation(new Animation(14298));
        player.setNextGraphics(new Graphics(101));
    }

    public void CatDance() {
        player.setNextAnimation(new Animation(14299));
        player.setNextGraphics(new Graphics(117));
    }

    public void DragonDance() {
        player.setNextAnimation(new Animation(14300));
        player.setNextGraphics(new Graphics(118));
    }

    public void bragDuelist4() {
        player.setNextAnimation(new Animation(510));
        player.setNextGraphics(new Graphics(92));
    }

    public void bragDuelist3() {
        player.setNextAnimation(new Animation(509));
        player.setNextGraphics(new Graphics(92));
    }

    public void bragDuelist2() {
        player.setNextAnimation(new Animation(508));
        player.setNextGraphics(new Graphics(92));
    }

    public void bragDuelist1() {
        player.setNextAnimation(new Animation(507));
        player.setNextGraphics(new Graphics(92));
    }

    public void bragWild() {
        player.setNextAnimation(new Animation(412));
        player.setNextGraphics(new Graphics(91));
    }

    public void transformWild1() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 1) {
                    player.lock();
                    player.getAppearence().transformIntoNPC(2216);
                }
                if (step == 9) {
                    player.getAppearence().transformIntoNPC(-1);
                    player.setNextGraphics(new Graphics(188));
                    player.unlock();
                }
                if (step == 10) {
                    player.setNextAnimation(new Animation(858));
                    stop();
                }
                step++;
            }
        }, 0, 1);
    }

    public void transformWild2() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 1) {
                    player.lock();
                    player.getAppearence().transformIntoNPC(2239);
                }
                if (step == 9) {
                    player.getAppearence().transformIntoNPC(-1);
                    player.setNextGraphics(new Graphics(188));
                    player.unlock();
                }
                if (step == 10) {
                    player.setNextAnimation(new Animation(858));
                    stop();
                }
                step++;
            }
        }, 0, 1);
    }

    public void transformWild3() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 1) {
                    player.lock();
                    player.getAppearence().transformIntoNPC(2358);
                }
                if (step == 9) {
                    player.getAppearence().transformIntoNPC(-1);
                    player.setNextGraphics(new Graphics(188));
                    player.unlock();
                }
                if (step == 10) {
                    player.setNextAnimation(new Animation(858));
                    stop();
                }
                step++;
            }
        }, 0, 1);
    }

    public void transformWild4() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 1) {
                    player.lock();
                    player.getAppearence().transformIntoNPC(2432);
                }
                if (step == 9) {
                    player.getAppearence().transformIntoNPC(-1);
                    player.setNextGraphics(new Graphics(188));
                    player.unlock();
                }
                if (step == 10) {
                    player.setNextAnimation(new Animation(858));
                    stop();
                }
                step++;
            }
        }, 0, 1);
    }

    public void transformWild5() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 1) {
                    player.lock();
                    player.getAppearence().transformIntoNPC(2433);
                }
                if (step == 9) {
                    player.getAppearence().transformIntoNPC(-1);
                    player.setNextGraphics(new Graphics(188));
                    player.unlock();
                }
                if (step == 10) {
                    player.setNextAnimation(new Animation(858));
                    stop();
                }
                step++;
            }
        }, 0, 1);
    }

    public void transformWild6() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 1) {
                    player.lock();
                    player.getAppearence().transformIntoNPC(2434);
                }
                if (step == 9) {
                    player.getAppearence().transformIntoNPC(-1);
                    player.setNextGraphics(new Graphics(188));
                    player.unlock();
                }
                if (step == 10) {
                    player.setNextAnimation(new Animation(858));
                    stop();
                }
                step++;
            }
        }, 0, 1);
    }

    public void transformDuelist1() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 1) {
                    player.lock();
                    player.getAppearence().transformIntoNPC(2719);
                }
                if (step == 9) {
                    player.getAppearence().transformIntoNPC(-1);
                    player.setNextGraphics(new Graphics(188));
                    player.unlock();
                }
                if (step == 10) {
                    player.setNextAnimation(new Animation(858));
                    stop();
                }
                step++;
            }
        }, 0, 1);
    }

    public void transformDuelist2() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 1) {
                    player.lock();
                    player.getAppearence().transformIntoNPC(2720);
                }
                if (step == 9) {
                    player.getAppearence().transformIntoNPC(-1);
                    player.setNextGraphics(new Graphics(188));
                    player.unlock();
                }
                if (step == 10) {
                    player.setNextAnimation(new Animation(858));
                    stop();
                }
                step++;
            }
        }, 0, 1);
    }

    public void transformDuelist3() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 1) {
                    player.lock();
                    player.getAppearence().transformIntoNPC(2721);
                }
                if (step == 9) {
                    player.getAppearence().transformIntoNPC(-1);
                    player.setNextGraphics(new Graphics(188));
                    player.unlock();
                }
                if (step == 10) {
                    player.setNextAnimation(new Animation(858));
                    stop();
                }
                step++;
            }
        }, 0, 1);
    }

    public void transformDuelist4() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 1) {
                    player.lock();
                    player.getAppearence().transformIntoNPC(3092);
                }
                if (step == 9) {
                    player.getAppearence().transformIntoNPC(-1);
                    player.setNextGraphics(new Graphics(188));
                    player.unlock();
                }
                if (step == 10) {
                    player.setNextAnimation(new Animation(858));
                    stop();
                }
                step++;
            }
        }, 0, 1);
    }

    public void transformDuelist5() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 1) {
                    player.lock();
                    player.getAppearence().transformIntoNPC(3131);
                }
                if (step == 9) {
                    player.getAppearence().transformIntoNPC(-1);
                    player.setNextGraphics(new Graphics(188));
                    player.unlock();
                }
                if (step == 10) {
                    player.setNextAnimation(new Animation(858));
                    stop();
                }
                step++;
            }
        }, 0, 1);
    }

    public void transformDuelist6() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 1) {
                    player.lock();
                    player.getAppearence().transformIntoNPC(3224);
                }
                if (step == 9) {
                    player.getAppearence().transformIntoNPC(-1);
                    player.setNextGraphics(new Graphics(188));
                    player.unlock();
                }
                if (step == 10) {
                    player.setNextAnimation(new Animation(858));
                    stop();
                }
                step++;
            }
        }, 0, 1);
    }

    //RSMV ITEMS TELEPORTS
    public void teleportGoldMonastery() {
        Magic.sendArdyCapeTeleportSpell(player, 0, 0.0D, new WorldTile(2606, 3210, 0), new int[0]);
    }

    public void teleportGoldFarm() {
        Magic.sendArdyCapeTeleportSpell(player, 0, 0.0D, new WorldTile(2674, 3375, 0), new int[0]);
    }

    public void teleportSilverMonastery() {
        Magic.sendArdyCapeTeleportSpell(player, 0, 0.0D, new WorldTile(2606, 3210, 0), new int[0]);
    }

    public void teleportSilverFarm() {
        Magic.sendArdyCapeTeleportSpell(player, 0, 0.0D, new WorldTile(2674, 3375, 0), new int[0]);
    }

    public void teleportUpgradedMonastery() {
        Magic.sendArdyCapeTeleportSpell(player, 0, 0.0D, new WorldTile(2606, 3210, 0), new int[0]);
    }

    public void teleportUpgradedFarm() {
        Magic.sendArdyCapeTeleportSpell(player, 0, 0.0D, new WorldTile(2674, 3375, 0), new int[0]);
    }

    public void teleportArdyMonastery() {
        Magic.sendArdyCapeTeleportSpell(player, 0, 0.0D, new WorldTile(2606, 3210, 0), new int[0]);
    }

    public void teleportArdyFarm() {
        Magic.sendArdyCapeTeleportSpell(player, 0, 0.0D, new WorldTile(2674, 3375, 0), new int[0]);
    }

    public void teleportWicked() {
        Magic.sendWickedTeleportSpell(player, 0, 0.0D, new WorldTile(3106, 3163, 1), new int[0]);
    }

    public void TeleportBonesack() {
        if (!player.telelocked) {
            Magic.sendBonesackTeleport(player, 0, 0.0D, new WorldTile(3388, 3517, 0), new int[0]);
        }
        else if (player.telelocked) {
            player.setNextAnimation(new Animation(12055));
        }
        player.setNextGraphics(new Graphics(2133));
        WorldTasksManager.schedule(new WorldTask() {
            int stage;

            @Override
            public void run() {
                if (stage == 2) {
                    player.setNextAnimation(new Animation(-1));
                    player.setNextGraphics(new Graphics(-1));
                    stop();
                }
                stage++;
            }

        }, 1, 2);
        return;
    }

    public void TeleportDungeoneering() {
        if (!player.telelocked) {
            Magic.sendDungeoneeringTeleport(player, 0, 0.0D, new WorldTile(3450, 3696, 0), new int[0]);
        }
        else if (player.telelocked) {
            player.setNextAnimation(new Animation(13652));
        }
        player.setNextGraphics(new Graphics(2602));
        WorldTasksManager.schedule(new WorldTask() {
            int stage;

            @Override
            public void run() {
                if (stage == 3) {
                    player.setNextAnimation(new Animation(-1));
                    player.setNextGraphics(new Graphics(-1));
                    stop();
                }
                stage++;
            }

        }, 1, 2);
        return;
    }

    public void TeleportMorytania() {
        Magic.sendMorytaniaLegsTeleport(player, 0, 0.0D, new WorldTile(3603, 3564, 0), new int[0]);
    }

    public void TeleportBroom() {
        Magic.sendBroomTeleport(player, 0, 0.0D, new WorldTile(2916, 5474, 0), new int[0]);
    }

    //RSMV ITEMS BEGIN
    public void CrystalChimes() {
        player.setNextAnimation(new Animation(7088));
    }

    // RSMV ITEMS
    //DUELISTS AND STUFF...
    public void bragDuelist61() {//correct
        player.setNextAnimation(new Animation(533));
        player.setNextGraphics(new Graphics(92));
    }

    public void bragDuelist51() {//correct
        player.setNextAnimation(new Animation(511));
        player.setNextGraphics(new Graphics(91));
    }

    public void bragDuelist41() {//correct
        player.setNextAnimation(new Animation(510));
        player.setNextGraphics(new Graphics(91));
    }

    public void bragDuelist31() {//correct
        player.setNextAnimation(new Animation(509));
        player.setNextGraphics(new Graphics(91));
    }

    public void bragDuelist21() {//correct
        player.setNextAnimation(new Animation(318));
    }

    public void bragDuelist11() {//correct
        player.getPackets().sendGameMessage("Brag option not availabe in this tier.");
    }

    public void bragWild1() {//correct
        player.getPackets().sendGameMessage("Brag option not availabe in this tier.");
    }

    public void bragWild2() {//correct
        player.setNextAnimation(new Animation(534));
    }

    public void bragWild3() {//correct
        player.setNextAnimation(new Animation(412));
        player.setNextGraphics(new Graphics(91));
    }

    public void bragWild4() {//correct
        player.setNextAnimation(new Animation(412));
        player.setNextGraphics(new Graphics(91));
    }

    public void bragWild5() {//correct
        player.setNextAnimation(new Animation(412));
        player.setNextGraphics(new Graphics(121));
    }

    public void bragWild6() {//DIFF
        player.setNextAnimation(new Animation(361));
        player.setNextGraphics(new Graphics(122));
    }

    public void transformWild11() {
        player.getPackets().sendGameMessage("Transform option not availabe in this tier.");
    }

    public void transformWild21() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 1) {
                    player.lock();
                    player.getAppearence().transformIntoNPC(2239);
                }
                if (step == 9) {
                    player.getAppearence().transformIntoNPC(-1);
                    player.setNextGraphics(new Graphics(188));
                    player.unlock();
                }
                if (step == 10) {
                    player.setNextAnimation(new Animation(858));
                    stop();
                }
                step++;
            }
        }, 0, 1);
    }

    public void transformWild31() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 1) {
                    player.lock();
                    player.getAppearence().transformIntoNPC(2358);
                }
                if (step == 9) {
                    player.getAppearence().transformIntoNPC(-1);
                    player.setNextGraphics(new Graphics(188));
                    player.unlock();
                }
                if (step == 10) {
                    player.setNextAnimation(new Animation(858));
                    stop();
                }
                step++;
            }
        }, 0, 1);
    }

    public void transformWild41() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 1) {
                    player.lock();
                    player.getAppearence().transformIntoNPC(2432);
                }
                if (step == 9) {
                    player.getAppearence().transformIntoNPC(-1);
                    player.setNextGraphics(new Graphics(188));
                    player.unlock();
                }
                if (step == 10) {
                    player.setNextAnimation(new Animation(858));
                    stop();
                }
                step++;
            }
        }, 0, 1);
    }

    public void transformWild51() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 1) {
                    player.lock();
                    player.getAppearence().transformIntoNPC(2433);
                }
                if (step == 9) {
                    player.getAppearence().transformIntoNPC(-1);
                    player.setNextGraphics(new Graphics(188));
                    player.unlock();
                }
                if (step == 10) {
                    player.setNextAnimation(new Animation(858));
                    stop();
                }
                step++;
            }
        }, 0, 1);
    }

    public void transformWild61() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 1) {
                    player.lock();
                    player.getAppearence().transformIntoNPC(2434);
                }
                if (step == 9) {
                    player.getAppearence().transformIntoNPC(-1);
                    player.setNextGraphics(new Graphics(188));
                    player.unlock();
                }
                if (step == 10) {
                    player.setNextAnimation(new Animation(858));
                    stop();
                }
                step++;
            }
        }, 0, 1);
    }

    public void transformDuelist11() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 1) {
                    player.lock();
                    player.getAppearence().transformIntoNPC(2719);
                }
                if (step == 9) {
                    player.getAppearence().transformIntoNPC(-1);
                    player.setNextGraphics(new Graphics(188));
                    player.unlock();
                }
                if (step == 10) {
                    player.setNextAnimation(new Animation(858));
                    stop();
                }
                step++;
            }
        }, 0, 1);
    }

    public void transformDuelist21() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 1) {
                    player.lock();
                    player.getAppearence().transformIntoNPC(2720);
                }
                if (step == 9) {
                    player.getAppearence().transformIntoNPC(-1);
                    player.setNextGraphics(new Graphics(188));
                    player.unlock();
                }
                if (step == 10) {
                    player.setNextAnimation(new Animation(858));
                    stop();
                }
                step++;
            }
        }, 0, 1);
    }

    public void transformDuelist31() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 1) {
                    player.lock();
                    player.getAppearence().transformIntoNPC(2721);
                }
                if (step == 9) {
                    player.getAppearence().transformIntoNPC(-1);
                    player.setNextGraphics(new Graphics(188));
                    player.unlock();
                }
                if (step == 10) {
                    player.setNextAnimation(new Animation(858));
                    stop();
                }
                step++;
            }
        }, 0, 1);
    }

    public void transformDuelist41() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 1) {
                    player.lock();
                    player.getAppearence().transformIntoNPC(3092);
                }
                if (step == 9) {
                    player.getAppearence().transformIntoNPC(-1);
                    player.setNextGraphics(new Graphics(188));
                    player.unlock();
                }
                if (step == 10) {
                    player.setNextAnimation(new Animation(858));
                    stop();
                }
                step++;
            }
        }, 0, 1);
    }

    public void transformDuelist51() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 1) {
                    player.lock();
                    player.getAppearence().transformIntoNPC(3131);
                }
                if (step == 9) {
                    player.getAppearence().transformIntoNPC(-1);
                    player.setNextGraphics(new Graphics(188));
                    player.unlock();
                }
                if (step == 10) {
                    player.setNextAnimation(new Animation(858));
                    stop();
                }
                step++;
            }
        }, 0, 1);
    }

    public void transformDuelist61() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 1) {
                    player.lock();
                    player.getAppearence().transformIntoNPC(3224);
                }
                if (step == 9) {
                    player.getAppearence().transformIntoNPC(-1);
                    player.setNextGraphics(new Graphics(188));
                    player.unlock();
                }
                if (step == 10) {
                    player.setNextAnimation(new Animation(858));
                    stop();
                }
                step++;
            }
        }, 0, 1);
    }

    public void spiderPlayWith() {
        player.setNextAnimation(new Animation(12490));
        player.setNextGraphics(new Graphics(2178));
    }

    public void IvandisFlail() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 0) {
                    player.setNextAnimation(new Animation(9098));
                }
                else if (step == 1) {
                    //player.setNextGraphics(new Graphics(263));
                    World.sendGraphics(player, new Graphics(264, 0, 900),
                                       new WorldTile(player.getX(), (player.getY()),
                                                     (player.getPlane())));
                    //NORTH
                    World.sendGraphics(player, new Graphics(263, 0, 200),
                                       new WorldTile(player.getX(), (player.getY() + 1),
                                                     (player.getPlane())));
                    //NORTHWEST
                    World.sendGraphics(player, new Graphics(263, 0, 200),
                                       new WorldTile((player.getX() - 1), (player.getY() + 1),
                                                     (player.getPlane())));
                    //NORTHEAST
                    World.sendGraphics(player, new Graphics(263, 0, 200),
                                       new WorldTile((player.getX() + 1), (player.getY() + 1),
                                                     (player.getPlane())));
                    //SOUTH
                    World.sendGraphics(player, new Graphics(263, 0, 200),
                                       new WorldTile(player.getX(), (player.getY() - 1),
                                                     (player.getPlane())));
                    //SOUTHWEST
                    World.sendGraphics(player, new Graphics(263, 0, 200),
                                       new WorldTile((player.getX() - 1), (player.getY() - 1),
                                                     (player.getPlane())));
                    //SOUTHTHEAST
                    World.sendGraphics(player, new Graphics(263, 0, 200),
                                       new WorldTile((player.getX() + 1), (player.getY() - 1),
                                                     (player.getPlane())));
                    //WEST
                    World.sendGraphics(player, new Graphics(263, 0, 200),
                                       new WorldTile((player.getX() - 1), player.getY(),
                                                     (player.getPlane())));
                    ;
                    //EAST
                    World.sendGraphics(player, new Graphics(263, 0, 200),
                                       new WorldTile((player.getX() + 1), player.getY(),
                                                     (player.getPlane())));

                    stop();
                }
                step++;
            }

        }, 0, 0);
    }

    public void BSickle() {
        WorldTasksManager.schedule(new WorldTask() {
            int step;

            @Override
            public void run() {
                if (step == 0) {
                    player.setNextAnimation(new Animation(9021));
                }
                else if (step == 1) {
                    //player.setNextGraphics(new Graphics(263));
                    World.sendGraphics(player, new Graphics(264, 0, 700),
                                       new WorldTile(player.getX(), (player.getY()),
                                                     (player.getPlane())));
                    //NORTH
                    World.sendGraphics(player, new Graphics(263, 0, 200),
                                       new WorldTile(player.getX(), (player.getY() + 1),
                                                     (player.getPlane())));
                    //NORTHWEST
                    World.sendGraphics(player, new Graphics(263, 0, 200),
                                       new WorldTile((player.getX() - 1), (player.getY() + 1),
                                                     (player.getPlane())));
                    //NORTHEAST
                    World.sendGraphics(player, new Graphics(263, 0, 200),
                                       new WorldTile((player.getX() + 1), (player.getY() + 1),
                                                     (player.getPlane())));
                    //SOUTH
                    World.sendGraphics(player, new Graphics(263, 0, 200),
                                       new WorldTile(player.getX(), (player.getY() - 1),
                                                     (player.getPlane())));
                    //SOUTHWEST
                    World.sendGraphics(player, new Graphics(263, 0, 200),
                                       new WorldTile((player.getX() - 1), (player.getY() - 1),
                                                     (player.getPlane())));
                    //SOUTHTHEAST
                    World.sendGraphics(player, new Graphics(263, 0, 200),
                                       new WorldTile((player.getX() + 1), (player.getY() - 1),
                                                     (player.getPlane())));
                    //WEST
                    World.sendGraphics(player, new Graphics(263, 0, 200),
                                       new WorldTile((player.getX() - 1), player.getY(),
                                                     (player.getPlane())));
                    ;
                    //EAST
                    World.sendGraphics(player, new Graphics(263, 0, 200),
                                       new WorldTile((player.getX() + 1), player.getY(),
                                                     (player.getPlane())));

                    stop();
                }
                step++;
            }

        }, 0, 0);

    }

    public void ESickle() {
        player.setNextAnimation(new Animation(9104));
        player.setNextGraphics(new Graphics(1604));
    }

    public void DCane() {
        player.setNextAnimation(new Animation(16916));
    }

    public void DHat() {
        player.setNextAnimation(new Animation(16915));
    }

    public void Cithara() {
        player.setNextAnimation(new Animation(15377));
    }

    public void Squirrel() {
        player.setNextGraphics(new Graphics(2145));
        player.setNextAnimation(new Animation(12265));

    }

    public void DiamondCrown() {
        player.setNextAnimation(new Animation(16915));
    }

    public void MinecartRide() {
        player.getAppearence().setRenderEmote(211);
        CastleWars.setWeapon(player, new Item(20793, 1));
        player.getAppearence().generateAppearenceData();
    }

    public void NoRemoteCart() {
        player.getAppearence().setRenderEmote(-1);
        CastleWars.setWeapon(player, null);
        player.getAppearence().generateAppearenceData();
    }

    public void ArougneCape() {
        player.setNextAnimation(new Animation(9601));
        player.setNextGraphics(new Graphics(1683));
    }

    public void Skull() {
        player.setNextAnimation(new Animation(9601));
        player.setNextGraphics(new Graphics(1683));
    }

    public void Pharoh() {
        player.setNextAnimation(new Animation(9596));
        player.setNextGraphics(new Graphics(715));
    }

    public void DiamondSceptre() {
        player.setNextAnimation(new Animation(16916));
    }

    public void Guthix() {
        player.setNextAnimation(new Animation(1337));
    }

    public void Zamorak() {
        player.setNextAnimation(new Animation(1336));
    }

    public void Saradomin() {
        player.setNextAnimation(new Animation(1335));
    }

    public void PenanceHorn() {
        player.setNextAnimation(new Animation(12755));
        player.setNextGraphics(new Graphics(2298));
    }

    public void Horn() {
        player.setNextAnimation(new Animation(12755));
        player.setNextGraphics(new Graphics(2298));
    }

    public void HolyLyre() {
        player.setNextAnimation(new Animation(15377));
    }

    public void ChromeGoggles() {
        player.setNextAnimation(new Animation(15185));
        player.setNextGraphics(new Graphics(1961));
    }

    public void Magnifying() {
        player.setNextAnimation(new Animation(2936));
    }

    public void faladorEmote1() {
        player.setNextAnimation(new Animation(13843));
        player.setNextGraphics(new Graphics(1966));
    }

    public void faladorEmote2() {
        player.setNextAnimation(new Animation(13844));
        player.setNextGraphics(new Graphics(1965));
    }

    public void faladorEmote3() {
        player.setNextAnimation(new Animation(13845));
        player.setNextGraphics(new Graphics(1965));
    }

    public void faladorEmote4() {
        player.setNextAnimation(new Animation(13845));
        player.setNextGraphics(new Graphics(1965));
    }

    public void faladorPrayer1() {
        if (player.getPrayer().getPrayerpoints() < player.getSkills().getLevelForXp(Skills.PRAYER) * 10) {
            player.getPrayer().setPrayerpoints((int) ((player.getSkills().getLevelForXp(Skills.PRAYER) * 10) * 0.55));
            player.getPrayer().refreshPrayerPoints();
            player.setNextGraphics(new Graphics(1964));
        }
    }

    public void faladorPrayer2() {
        if (player.getPrayer().getPrayerpoints() < player.getSkills().getLevelForXp(Skills.PRAYER) * 10) {
            player.getPrayer().setPrayerpoints((int) ((player.getSkills().getLevelForXp(Skills.PRAYER) * 10) * 0.75));
            player.getPrayer().refreshPrayerPoints();
            player.setNextGraphics(new Graphics(1964));
        }
    }

    public void faladorPrayer3() {
        if (player.getPrayer().getPrayerpoints() < player.getSkills().getLevelForXp(Skills.PRAYER) * 10) {
            player.getPrayer().setPrayerpoints((int) ((player.getSkills().getLevelForXp(Skills.PRAYER) * 10) * 1.05));
            player.getPrayer().refreshPrayerPoints();
            player.setNextGraphics(new Graphics(1964));
        }
    }

    public void faladorPrayer4() {
        if (player.getPrayer().getPrayerpoints() < player.getSkills().getLevelForXp(Skills.PRAYER) * 10) {
            player.getPrayer().setPrayerpoints((int) ((player.getSkills().getLevelForXp(Skills.PRAYER) * 10) * 1.25));
            player.getPrayer().refreshPrayerPoints();
            player.setNextGraphics(new Graphics(1964));
        }
    }


    public void gazeOrbOfOculus() {
        player.getPackets().sendWindowsPane(475, 0);
        player.getPackets().sendInterface(true, 475, 57, 751);
        player.getPackets().sendInterface(true, 475, 55, 752);
        player.setCloseInterfacesEvent(new Runnable() {

            @Override
            public void run() {
                player.getPackets().sendWindowsPane(player.getInterfaceManager().hasRezizableScreen() ? 746 : 548, 0);
                player.getPackets().sendResetCamera();

            }

        });
    }

    /*
     * returns lastGameTab
     */
    public int openGameTab(int tabId) {
        player.getPackets().sendGlobalConfig(168, tabId);
        int lastTab = 4; // tabId
        // tab = tabId;
        return lastTab;
    }

    public void sendStaffList() {
        player.getInterfaceManager().sendInterface(1158);
        player.getPackets().sendIComponentText(1158, 74, "<img=1><img=0>Vengium Staff List<img=0><img=1>");
        player.getPackets().sendIComponentText(1158, 8, "--");
        player.getPackets().sendIComponentText(1158, 9, "Username");
        player.getPackets().sendIComponentText(1158, 10, "<col=ff0000>Rank</col>");
        player.getPackets().sendIComponentText(1158, 11, "Online Status");
        player.getPackets().sendIComponentText(1158, 13, "1.");
        player.getPackets().sendIComponentText(1158, 14, "Multak");
        player.getPackets().sendIComponentText(1158, 15, "Creator of " + Settings.SERVER_NAME + "");
        player.getPackets().sendIComponentText(1158, 16, World.containsPlayer("Multak") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
        player.getPackets().sendIComponentText(1158, 18, "2.");
        player.getPackets().sendIComponentText(1158, 19, "Cole");
        player.getPackets().sendIComponentText(1158, 20, "Head-Administrator of " + Settings.SERVER_NAME + "");
        player.getPackets().sendIComponentText(1158, 21, World.containsPlayer("Toshero") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
        player.getPackets().sendIComponentText(1158, 23, "3.");
        player.getPackets().sendIComponentText(1158, 24, "Troy");
        player.getPackets().sendIComponentText(1158, 25, "Administrator of " + Settings.SERVER_NAME + "");
        player.getPackets().sendIComponentText(1158, 26, World.containsPlayer("Wolf") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
        player.getPackets().sendIComponentText(1158, 28, "4.");
        player.getPackets().sendIComponentText(1158, 29, "Enzo");
        player.getPackets().sendIComponentText(1158, 30, "Administrator of " + Settings.SERVER_NAME + "");
        player.getPackets().sendIComponentText(1158, 31, World.containsPlayer("Enzo") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
        player.getPackets().sendIComponentText(1158, 33, "5.");
        player.getPackets().sendIComponentText(1158, 34, "Omar");
        player.getPackets().sendIComponentText(1158, 35, "Administrator of " + Settings.SERVER_NAME + "");
        player.getPackets().sendIComponentText(1158, 36, World.containsPlayer("Blackbandwar") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
        player.getPackets().sendIComponentText(1158, 37, "6.");
        player.getPackets().sendIComponentText(1158, 38, "Stian");
        player.getPackets().sendIComponentText(1158, 39, "Administrator of " + Settings.SERVER_NAME + "");
        player.getPackets().sendIComponentText(1158, 40, World.containsPlayer("Toiletguide") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
        player.getPackets().sendIComponentText(1158, 41, "7.");
        player.getPackets().sendIComponentText(1158, 42, "Trey");
        player.getPackets().sendIComponentText(1158, 43, "Head-Administrator of " + Settings.SERVER_NAME + "");
        player.getPackets().sendIComponentText(1158, 44, World.containsPlayer("99orWoh") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
        player.getPackets().sendIComponentText(1158, 45, "8.");
        player.getPackets().sendIComponentText(1158, 46, "Seb");
        player.getPackets().sendIComponentText(1158, 47, "Moderator of" + Settings.SERVER_NAME + "");
        player.getPackets().sendIComponentText(1158, 48, World.containsPlayer("Sebster107") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
        player.getPackets().sendIComponentText(1158, 49, "9.");
        player.getPackets().sendIComponentText(1158, 50, "Lane");
        player.getPackets().sendIComponentText(1158, 51, "Moderator of" + Settings.SERVER_NAME + "");
        player.getPackets().sendIComponentText(1158, 52, World.containsPlayer("Tosvillain") || World.containsPlayer("ahxchurch") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");
        player.getPackets().sendIComponentText(1158, 53, "10.");
        player.getPackets().sendIComponentText(1158, 54, "Zangetsu");
        player.getPackets().sendIComponentText(1158, 55, "Moderator of" + Settings.SERVER_NAME + "");
        player.getPackets().sendIComponentText(1158, 56, World.containsPlayer("Zangetsu") ? "<col=02AB2F>Online</col>" : "<col=DB0000>Offline</col>" + "");

    }


    public void sendNormalCommands() {
        for (int i = 0; i < 310; i++) {
            player.getPackets().sendIComponentText(275, i,
                                                   "");
        }
        player.getPackets().sendIComponentText(275, 1, "<col=00ffff>Vengium Command List</col>");
        player.getPackets().sendIComponentText(275, 10, "<col=FFA200><shad=FFEF00>Normal Commands");
        player.getPackets().sendIComponentText(275, 11, "<img=15><col=FF0000>====================================<img=15>");
        player.getPackets().sendIComponentText(275, 12, "<img=15><col=FF0000>============BASIC COMMANDS============<img=15>");

        player.getPackets().sendIComponentText(275, 13, "<col=FFFFFF><shad=002FFF>;;home");
        player.getPackets().sendIComponentText(275, 14, "<col=FFFFFF>This command teleports you home.");

        player.getPackets().sendIComponentText(275, 15, "<col=FFFFFF><shad=002FFF>;;players");
        player.getPackets().sendIComponentText(275, 16, "<col=FFFFFF>This command views the players on the server.");

        player.getPackets().sendIComponentText(275, 17, "<col=FFFFFF><shad=002FFF>;;bank");
        player.getPackets().sendIComponentText(275, 18, "<col=FFFFFF>This command opens up your bank.");

        player.getPackets().sendIComponentText(275, 19, "<col=FFFFFF><shad=002FFF>;;item [ID #] [AMOUNT]");
        player.getPackets().sendIComponentText(275, 20, "<col=FFFFFF>This command spawns an item in your inventory based on");
        player.getPackets().sendIComponentText(275, 21, "<col=FFFFFF>the ID number and how many you want. www.itemdb.biz is");
        player.getPackets().sendIComponentText(275, 22, "<col=FFFFFF>the site you can go to in order to get the item ID.");

        player.getPackets().sendIComponentText(275, 23, "<img=15><col=FF0000>==================================<img=15>");
        player.getPackets().sendIComponentText(275, 24, "<img=15><col=FF0000>==========STARTER COMMANDS==========<img=15>");

        player.getPackets().sendIComponentText(275, 25, "<col=FFFFFF><shad=002FFF>;;master");
        player.getPackets().sendIComponentText(275, 26, "<col=FFFFFF>This command gives you gives you the maximum level in.");
        player.getPackets().sendIComponentText(275, 27, "<col=FFFFFF>all skills.");

        player.getPackets().sendIComponentText(275, 28, "<col=FFFFFF><shad=002FFF>;;rsmvset");
        player.getPackets().sendIComponentText(275, 29, "<col=FFFFFF>This command gives you several item emotes in bank.");

        player.getPackets().sendIComponentText(275, 30, "<col=FFFFFF><shad=002FFF>;;teleitems");
        player.getPackets().sendIComponentText(275, 31, "<col=FFFFFF>This command gives you several teleport items in bank");
        player.getPackets().sendIComponentText(275, 32, "<col=FFFFFF>and inventory.");

        player.getPackets().sendIComponentText(275, 33, "<col=FFFFFF><shad=002FFF>;;runes");
        player.getPackets().sendIComponentText(275, 34, "<col=FFFFFF>This command gives you runes in bank and inventory.");

        player.getPackets().sendIComponentText(275, 35, "<col=FFFFFF><shad=002FFF>;;gear");
        player.getPackets().sendIComponentText(275, 36, "<col=FFFFFF>This command gives you several weapons and armour in");
        player.getPackets().sendIComponentText(275, 37, "<col=FFFFFF>bank.");

        player.getPackets().sendIComponentText(275, 38, "<col=FFFFFF><shad=002FFF>;;skillcapes");
        player.getPackets().sendIComponentText(275, 39, "<col=FFFFFF>This command gives you all skillcapes in bank,");
        player.getPackets().sendIComponentText(275, 40, "<col=FFFFFF>including the untrimmed versions.");

        player.getPackets().sendIComponentText(275, 51, "<img=15><col=FF0000>==================================<img=15>");
        player.getPackets().sendIComponentText(275, 52, "<img=15><col=FF0000>==========USEFUL  COMMANDS==========<img=15>");

        player.getPackets().sendIComponentText(275, 53, "<col=FFFFFF><shad=002FFF>;;clearbank");
        player.getPackets().sendIComponentText(275, 54, "<col=FFFFFF>This command clears everything in your bank.");

        player.getPackets().sendIComponentText(275, 55, "<col=FFFFFF><shad=002FFF>;;empty");
        player.getPackets().sendIComponentText(275, 56, "<col=FFFFFF>This command clears everything in your inventory.");

        player.getPackets().sendIComponentText(275, 57, "<col=FFFFFF><shad=002FFF>;;changepass [NEW PASSWORD]");
        player.getPackets().sendIComponentText(275, 58, "<col=FFFFFF>This command changes your password.");

        player.getPackets().sendIComponentText(275, 59, "<col=FFFFFF><shad=002FFF>;;switchitemslook");
        player.getPackets().sendIComponentText(275, 60, "<col=FFFFFF>This command changes switches to old or new item");
        player.getPackets().sendIComponentText(275, 61, "<col=FFFFFF>graphics, depending on which you like best.");

        player.getPackets().sendIComponentText(275, 62, "<col=FFFFFF><shad=002FFF>;;yell [LINE]");
        player.getPackets().sendIComponentText(275, 63, "<col=FFFFFF>This command makes you speak to everyone online.");

        player.getPackets().sendIComponentText(275, 64, "<col=FFFFFF><shad=002FFF>;;ancient");
        player.getPackets().sendIComponentText(275, 65, "<col=FFFFFF>This command changes your spellbook to ancient.");

        player.getPackets().sendIComponentText(275, 66, "<col=FFFFFF><shad=002FFF>;;modern");
        player.getPackets().sendIComponentText(275, 67, "<col=FFFFFF>This command changes your spellbook to normal.");

        player.getPackets().sendIComponentText(275, 68, "<col=FFFFFF><shad=002FFF>;;lunar");
        player.getPackets().sendIComponentText(275, 69, "<col=FFFFFF>This command changes your spellbook to lunar");
        player.getInterfaceManager().sendInterface(275);
    }

    public void sendRSMVerCommands() {
        for (int i = 0; i < 310; i++) {
            player.getPackets().sendIComponentText(275, i,
                                                   "");
        }
        player.getPackets().sendIComponentText(275, 1, "<col=00ffff>Vengium Command List</col>");
        player.getPackets().sendIComponentText(275, 10, "<col=FFA200><shad=FFEF00>RSMVer Commands");
        player.getPackets().sendIComponentText(275, 11, "<img=15><col=FF0000>====================================<img=15>");
        player.getPackets().sendIComponentText(275, 12, "<img=15><col=FF0000>============BASIC COMMANDS============<img=15>");

        player.getPackets().sendIComponentText(275, 13, "<col=FFFFFF><shad=002FFF>;;home");
        player.getPackets().sendIComponentText(275, 14, "<col=FFFFFF>This command teleports you home.");

        player.getPackets().sendIComponentText(275, 15, "<col=FFFFFF><shad=002FFF>;;players");
        player.getPackets().sendIComponentText(275, 16, "<col=FFFFFF>This command views the players on the server.");

        player.getPackets().sendIComponentText(275, 17, "<col=FFFFFF><shad=002FFF>;;bank");
        player.getPackets().sendIComponentText(275, 18, "<col=FFFFFF>This command opens up your bank.");


        player.getPackets().sendIComponentText(275, 19, "<col=FFFFFF><shad=002FFF>;;item [ID #] [AMOUNT]");
        player.getPackets().sendIComponentText(275, 20, "<col=FFFFFF>This command spawns an item in your inventory based on");
        player.getPackets().sendIComponentText(275, 21, "<col=FFFFFF>the ID number and how many you want. www.itemdb.biz is");
        player.getPackets().sendIComponentText(275, 22, "<col=FFFFFF>the site you can go to in order to get the item ID.");

        player.getPackets().sendIComponentText(275, 23, "<col=FFFFFF><shad=002FFF>;;itemn [ITEM NAME]");
        player.getPackets().sendIComponentText(275, 24, "<col=FFFFFF>This command spawns an item in your inventory based on");
        player.getPackets().sendIComponentText(275, 25, "<col=FFFFFF>the actual name of the item. Make sure you spell right.");
        player.getPackets().sendIComponentText(275, 26, "<col=FFFFFF>If you don't know the spelling, go to the RS Wiki site.");

        player.getPackets().sendIComponentText(275, 27, "<img=15><col=FF0000>==================================<img=15>");
        player.getPackets().sendIComponentText(275, 28, "<img=15><col=FF0000>==========STARTER COMMANDS==========<img=15>");

        player.getPackets().sendIComponentText(275, 29, "<col=FFFFFF><shad=002FFF>;;master");
        player.getPackets().sendIComponentText(275, 30, "<col=FFFFFF>This command gives you gives you the maximum level in.");
        player.getPackets().sendIComponentText(275, 31, "<col=FFFFFF>all skills.");

        player.getPackets().sendIComponentText(275, 32, "<col=FFFFFF><shad=002FFF>;;rsmvset");
        player.getPackets().sendIComponentText(275, 33, "<col=FFFFFF>This command gives you several item emotes in bank.");

        player.getPackets().sendIComponentText(275, 34, "<col=FFFFFF><shad=002FFF>;;teleitems");
        player.getPackets().sendIComponentText(275, 35, "<col=FFFFFF>This command gives you several teleport items in bank");
        player.getPackets().sendIComponentText(275, 36, "<col=FFFFFF>and inventory.");

        player.getPackets().sendIComponentText(275, 37, "<col=FFFFFF><shad=002FFF>;;runes");
        player.getPackets().sendIComponentText(275, 38, "<col=FFFFFF>This command gives you runes in bank and inventory.");

        player.getPackets().sendIComponentText(275, 39, "<col=FFFFFF><shad=002FFF>;;gear");
        player.getPackets().sendIComponentText(275, 40, "<col=FFFFFF>This command gives you several weapons and armour in");
        player.getPackets().sendIComponentText(275, 41, "<col=FFFFFF>bank.");

        player.getPackets().sendIComponentText(275, 42, "<col=FFFFFF><shad=002FFF>;;skillcapes");
        player.getPackets().sendIComponentText(275, 43, "<col=FFFFFF>This command gives you all skillcapes in bank,");
        player.getPackets().sendIComponentText(275, 44, "<col=FFFFFF>including the untrimmed versions.");

        player.getPackets().sendIComponentText(275, 45, "<img=15><col=FF0000>==================================<img=15>");
        player.getPackets().sendIComponentText(275, 46, "<img=15><col=FF0000>==========USEFUL  COMMANDS==========<img=15>");

        player.getPackets().sendIComponentText(275, 47, "<col=FFFFFF><shad=002FFF>;;tele [X] [Y] [Z]");
        player.getPackets().sendIComponentText(275, 48, "<col=FFFFFF>This command teleports you somewhere based on the");
        player.getPackets().sendIComponentText(275, 49, "<col=FFFFFF>coordinates, which are X, Y, and Z.");

        player.getPackets().sendIComponentText(275, 50, "<col=918D8D>;;teleto [NAME]");
        player.getPackets().sendIComponentText(275, 51, "<col=918D8D>This command teleports you to someone based on the");
        player.getPackets().sendIComponentText(275, 52, "<col=918D8D>name you enter. This command will be modified later.");

        player.getPackets().sendIComponentText(275, 53, "<col=FFFFFF><shad=002FFF>;;clearbank");
        player.getPackets().sendIComponentText(275, 54, "<col=FFFFFF>This command clears everything in your bank.");

        player.getPackets().sendIComponentText(275, 55, "<col=FFFFFF><shad=002FFF>;;empty");
        player.getPackets().sendIComponentText(275, 56, "<col=FFFFFF>This command clears everything in your inventory.");

        player.getPackets().sendIComponentText(275, 57, "<col=FFFFFF><shad=002FFF>;;hide");
        player.getPackets().sendIComponentText(275, 58, "<col=FFFFFF>This command hides your character.");

        player.getPackets().sendIComponentText(275, 59, "<col=FFFFFF><shad=002FFF>;;appear");
        player.getPackets().sendIComponentText(275, 60, "<col=FFFFFF>This command shows your character after being hidden.");

        player.getPackets().sendIComponentText(275, 61, "<col=FFFFFF><shad=002FFF>;;setlevel [SKILL #] [LEVEL #]");
        player.getPackets().sendIComponentText(275, 62, "<col=FFFFFF>This command allows you to change your level based on.");
        player.getPackets().sendIComponentText(275, 63, "<col=FFFFFF>the skill number you enter and the level you enter.");

        player.getPackets().sendIComponentText(275, 64, "<col=FFFFFF><shad=002FFF>;;setdisplay");
        player.getPackets().sendIComponentText(275, 65, "<col=FFFFFF>This command changes your display name. Ask staff to");
        player.getPackets().sendIComponentText(275, 65, "<col=FFFFFF>get it removed.");

        player.getPackets().sendIComponentText(275, 66, "<col=918D8D>;;removedisplay [NAME]");
        player.getPackets().sendIComponentText(275, 67, "<col=918D8D>This command removes your display name.");

        player.getPackets().sendIComponentText(275, 68, "<col=FFFFFF><shad=002FFF>;;changepass [NEW PASSWORD]");
        player.getPackets().sendIComponentText(275, 69, "<col=FFFFFF>This command changes your password.");

        player.getPackets().sendIComponentText(275, 70, "<col=FFFFFF><shad=002FFF>;;switchitemslook");
        player.getPackets().sendIComponentText(275, 71, "<col=FFFFFF>This command changes switches to old or new item");
        player.getPackets().sendIComponentText(275, 72, "<col=FFFFFF>graphics, depending on which you like best.");

        player.getPackets().sendIComponentText(275, 73, "<col=FFFFFF><shad=002FFF>;;yell [LINE]");
        player.getPackets().sendIComponentText(275, 74, "<col=FFFFFF>This command makes you speak to everyone online.");

        player.getPackets().sendIComponentText(275, 75, "<col=FFFFFF><shad=002FFF>;;ancient");
        player.getPackets().sendIComponentText(275, 76, "<col=FFFFFF>This command changes your spellbook to ancient.");

        player.getPackets().sendIComponentText(275, 77, "<col=FFFFFF><shad=002FFF>;;modern");
        player.getPackets().sendIComponentText(275, 78, "<col=FFFFFF>This command changes your spellbook to normal.");

        player.getPackets().sendIComponentText(275, 79, "<col=FFFFFF><shad=002FFF>;;lunar");
        player.getPackets().sendIComponentText(275, 80, "<col=FFFFFF>This command changes your spellbook to lunar");

        player.getPackets().sendIComponentText(275, 81, "<img=15><col=FF0000>====================================<img=15>");
        player.getPackets().sendIComponentText(275, 82, "<img=15><col=FF0000>============FUN COMMANDS============<img=15>");

        player.getPackets().sendIComponentText(275, 83, "<col=FFFFFF><shad=002FFF>;;anim [ID #]");
        player.getPackets().sendIComponentText(275, 84, "<col=FFFFFF>This command makes you perform an animation. There");
        player.getPackets().sendIComponentText(275, 85, "<col=FFFFFF>are several RSPS animation lists online.");

        player.getPackets().sendIComponentText(275, 86, "<col=FFFFFF><shad=002FFF>;;gfx [ID #]");
        player.getPackets().sendIComponentText(275, 87, "<col=FFFFFF>This command makes you perform a graphic. There.");
        player.getPackets().sendIComponentText(275, 88, "<col=FFFFFF>are several RSPS graphic lists online.");

        player.getPackets().sendIComponentText(275, 89, "<col=FFFFFF><shad=002FFF>;;animgfx [ID #] [ID #]");
        player.getPackets().sendIComponentText(275, 90, "<col=FFFFFF>This command makes you perform both animation and.");
        player.getPackets().sendIComponentText(275, 91, "<col=FFFFFF>graphic in sync.");

        player.getPackets().sendIComponentText(275, 92, "<col=FFFFFF><shad=002FFF>;;remote [ID #]");
        player.getPackets().sendIComponentText(275, 93, "<col=FFFFFF>This command makes you perform a render animation or");
        player.getPackets().sendIComponentText(275, 94, "<col=FFFFFF>remote. There are few RSPS render or remote lists");
        player.getPackets().sendIComponentText(275, 95, "<col=FFFFFF>online.");

        player.getPackets().sendIComponentText(275, 96, "<col=FFFFFF><shad=002FFF>;;tonpc [ID #]");
        player.getPackets().sendIComponentText(275, 97, "<col=FFFFFF>This command transforms you into a NPC. There");
        player.getPackets().sendIComponentText(275, 98, "<col=FFFFFF>are several RSPS NPC lists online.");

        player.getPackets().sendIComponentText(275, 99, "<col=FFFFFF><shad=002FFF>;;title [ID #]");
        player.getPackets().sendIComponentText(275, 100, "<col=FFFFFF>This command gives you a title before or after your.");
        player.getPackets().sendIComponentText(275, 101, "<col=FFFFFF>name. Custom titles are given to those who create an.");
        player.getPackets().sendIComponentText(275, 102, "<col=FFFFFF>RSMV on the server. The RSMV must be 20 or more seconds.");

        player.getPackets().sendIComponentText(275, 103, "<col=FFFFFF><shad=002FFF>;;scream [LINE]");
        player.getPackets().sendIComponentText(275, 104, "<col=FFFFFF>This command makes you say a line in all caps instead of");
        player.getPackets().sendIComponentText(275, 105, "<col=FFFFFF>starting the line with :.");

        player.getPackets().sendIComponentText(275, 106, "<col=FFFFFF><shad=002FFF>;;armstyle [ID #]");
        player.getPackets().sendIComponentText(275, 107, "<col=FFFFFF>This command changes your armstyle no matter what top");
        player.getPackets().sendIComponentText(275, 108, "<col=FFFFFF>you are wearing. Males are 590 - 610 and females are.");
        player.getPackets().sendIComponentText(275, 109, "<col=FFFFFF>in the 400s.");

        player.getPackets().sendIComponentText(275, 110, "<col=FFFFFF><shad=002FFF>;;topstyle [ID #]");
        player.getPackets().sendIComponentText(275, 111, "<col=FFFFFF>This command changes your topstyle.");

        player.getPackets().sendIComponentText(275, 112, "<col=FFFFFF><shad=002FFF>;;hairstyle [ID #]");
        player.getPackets().sendIComponentText(275, 113, "<col=FFFFFF>This command changes your hairstyle.");

        player.getPackets().sendIComponentText(275, 114, "<col=FFFFFF><shad=002FFF>;;haircolor [ID #]");
        player.getPackets().sendIComponentText(275, 115, "<col=FFFFFF>This command changes your haircolor.");

        player.getPackets().sendIComponentText(275, 116, "<col=FFFFFF><shad=002FFF>;;skincolor [ID #]");
        player.getPackets().sendIComponentText(275, 117, "<col=FFFFFF>This command changes your skincolor. Go to the MakeOver");
        player.getPackets().sendIComponentText(275, 118, "<col=FFFFFF>Mage to change back if you can't find your original color.");

        player.getPackets().sendIComponentText(275, 119, "<col=FFFFFF><shad=002FFF>;;facemask");
        player.getPackets().sendIComponentText(275, 120, "<col=FFFFFF>This command enables you to wear a facemask with any hat,");
        player.getPackets().sendIComponentText(275, 121, "<col=FFFFFF>you will be unable to wear an amulet in order to do so.");

        player.getPackets().sendIComponentText(275, 122, "<col=FFFFFF><shad=002FFF>;;maskedearmuffs");
        player.getPackets().sendIComponentText(275, 123, "<col=FFFFFF>This command enables you to wear masked earmuffs with any");
        player.getPackets().sendIComponentText(275, 124, "<col=FFFFFF>hat, but you will be unable to wear an amulet in order");
        player.getPackets().sendIComponentText(275, 125, "<col=FFFFFF>to do so.");

        player.getPackets().sendIComponentText(275, 126, "<col=FFFFFF><shad=002FFF>;;goggles");
        player.getPackets().sendIComponentText(275, 127, "<col=FFFFFF>This command enables you to wear gnome goggles with any");
        player.getPackets().sendIComponentText(275, 128, "<col=FFFFFF>hat, but you will be unable to wear an amulet in order ");
        player.getPackets().sendIComponentText(275, 129, "<col=FFFFFF>to do so.");

        player.getPackets().sendIComponentText(275, 130, "<col=FFFFFF><shad=002FFF>;;eyepatches");
        player.getPackets().sendIComponentText(275, 131, "<col=FFFFFF>This command enables you to wear eyepatches with any");
        player.getPackets().sendIComponentText(275, 132, "<col=FFFFFF>hat, but you will be unable to wear an amulet in order");
        player.getPackets().sendIComponentText(275, 133, "<col=FFFFFF>to do so.");

        player.getPackets().sendIComponentText(275, 134, "<col=FFFFFF><shad=002FFF>;;highwayman");
        player.getPackets().sendIComponentText(275, 135, "<col=FFFFFF>This command enables you to wear a highwayman mask with");
        player.getPackets().sendIComponentText(275, 136, "<col=FFFFFF>any hat, but you will be unable to wear an amulet in order");
        player.getPackets().sendIComponentText(275, 137, "<col=FFFFFF>to do so.");

        player.getPackets().sendIComponentText(275, 138, "<col=FFFFFF><shad=002FFF>;;cole");
        player.getPackets().sendIComponentText(275, 139, "<col=FFFFFF>This command is life.");

        player.getPackets().sendIComponentText(275, 140, "<col=FFFFFF><shad=002FFF>;;enzo");
        player.getPackets().sendIComponentText(275, 141, "<col=FFFFFF>This command is life.");

        player.getPackets().sendIComponentText(275, 142, "<col=FFFFFF><shad=002FFF>;;swag");
        player.getPackets().sendIComponentText(275, 143, "<col=FFFFFF>This command is life.");
        player.getInterfaceManager().sendInterface(275);
    }

    public void sendModCommands() {
        for (int i = 0; i < 310; i++) {
            player.getPackets().sendIComponentText(275, i,
                                                   "");
        }
        player.getPackets().sendIComponentText(275, 1, "<col=00ffff>Vengium Command List</col>");
        player.getPackets().sendIComponentText(275, 10, "<col=FFA200><shad=FFEF00>Moderator Commands");
        player.getPackets().sendIComponentText(275, 11, "<img=15><col=FF0000>====================================<img=15>");
        player.getPackets().sendIComponentText(275, 12, "<img=15><col=FF0000>============BASIC COMMANDS============<img=15>");

        player.getPackets().sendIComponentText(275, 13, "<col=FFFFFF><shad=002FFF>;;home");
        player.getPackets().sendIComponentText(275, 14, "<col=FFFFFF>This command teleports you home.");

        player.getPackets().sendIComponentText(275, 15, "<col=FFFFFF><shad=002FFF>;;players");
        player.getPackets().sendIComponentText(275, 16, "<col=FFFFFF>This command views the players on the server.");

        player.getPackets().sendIComponentText(275, 17, "<col=FFFFFF><shad=002FFF>;;bank");
        player.getPackets().sendIComponentText(275, 18, "<col=FFFFFF>This command opens up your bank.");


        player.getPackets().sendIComponentText(275, 19, "<col=FFFFFF><shad=002FFF>;;item [ID #] [AMOUNT]");
        player.getPackets().sendIComponentText(275, 20, "<col=FFFFFF>This command spawns an item in your inventory based on");
        player.getPackets().sendIComponentText(275, 21, "<col=FFFFFF>the ID number and how many you want. www.itemdb.biz is");
        player.getPackets().sendIComponentText(275, 22, "<col=FFFFFF>the site you can go to in order to get the item ID.");

        player.getPackets().sendIComponentText(275, 23, "<col=FFFFFF><shad=002FFF>;;itemn [ITEM NAME]");
        player.getPackets().sendIComponentText(275, 24, "<col=FFFFFF>This command spawns an item in your inventory based on");
        player.getPackets().sendIComponentText(275, 25, "<col=FFFFFF>the actual name of the item. Make sure you spell right.");
        player.getPackets().sendIComponentText(275, 26, "<col=FFFFFF>If you don't know the spelling, go to the RS Wiki site.");

        player.getPackets().sendIComponentText(275, 27, "<img=15><col=FF0000>==================================<img=15>");
        player.getPackets().sendIComponentText(275, 28, "<img=15><col=FF0000>==========STARTER COMMANDS==========<img=15>");

        player.getPackets().sendIComponentText(275, 29, "<col=FFFFFF><shad=002FFF>;;master");
        player.getPackets().sendIComponentText(275, 30, "<col=FFFFFF>This command gives you gives you the maximum level in.");
        player.getPackets().sendIComponentText(275, 31, "<col=FFFFFF>all skills.");

        player.getPackets().sendIComponentText(275, 32, "<col=FFFFFF><shad=002FFF>;;rsmvset");
        player.getPackets().sendIComponentText(275, 33, "<col=FFFFFF>This command gives you several item emotes in bank.");

        player.getPackets().sendIComponentText(275, 34, "<col=FFFFFF><shad=002FFF>;;teleitems");
        player.getPackets().sendIComponentText(275, 35, "<col=FFFFFF>This command gives you several teleport items in bank");
        player.getPackets().sendIComponentText(275, 36, "<col=FFFFFF>and inventory.");

        player.getPackets().sendIComponentText(275, 37, "<col=FFFFFF><shad=002FFF>;;runes");
        player.getPackets().sendIComponentText(275, 38, "<col=FFFFFF>This command gives you runes in bank and inventory.");

        player.getPackets().sendIComponentText(275, 39, "<col=FFFFFF><shad=002FFF>;;gear");
        player.getPackets().sendIComponentText(275, 40, "<col=FFFFFF>This command gives you several weapons and armour in");
        player.getPackets().sendIComponentText(275, 41, "<col=FFFFFF>bank.");

        player.getPackets().sendIComponentText(275, 42, "<col=FFFFFF><shad=002FFF>;;skillcapes");
        player.getPackets().sendIComponentText(275, 43, "<col=FFFFFF>This command gives you all skillcapes in bank,");
        player.getPackets().sendIComponentText(275, 44, "<col=FFFFFF>including the untrimmed versions.");

        player.getPackets().sendIComponentText(275, 45, "<img=15><col=FF0000>==================================<img=15>");
        player.getPackets().sendIComponentText(275, 46, "<img=15><col=FF0000>==========USEFUL  COMMANDS==========<img=15>");

        player.getPackets().sendIComponentText(275, 47, "<col=FFFFFF><shad=002FFF>;;tele [X] [Y] [Z]");
        player.getPackets().sendIComponentText(275, 48, "<col=FFFFFF>This command teleports you somewhere based on the");
        player.getPackets().sendIComponentText(275, 49, "<col=FFFFFF>coordinates, which are X, Y, and Z.");

        player.getPackets().sendIComponentText(275, 50, "<col=FFFFFF><shad=002FFF>;;teleto [NAME]");
        player.getPackets().sendIComponentText(275, 51, "<col=FFFFFF>This command teleports you to someone based on the");
        player.getPackets().sendIComponentText(275, 52, "<col=FFFFFF>name you enter. This command will be modified later.");

        player.getPackets().sendIComponentText(275, 53, "<col=FFFFFF><shad=002FFF>;;clearbank");
        player.getPackets().sendIComponentText(275, 54, "<col=FFFFFF>This command clears everything in your bank.");

        player.getPackets().sendIComponentText(275, 55, "<col=FFFFFF><shad=002FFF>;;empty");
        player.getPackets().sendIComponentText(275, 56, "<col=FFFFFF>This command clears everything in your inventory.");

        player.getPackets().sendIComponentText(275, 57, "<col=FFFFFF><shad=002FFF>;;hide");
        player.getPackets().sendIComponentText(275, 58, "<col=FFFFFF>This command hides your character.");

        player.getPackets().sendIComponentText(275, 59, "<col=FFFFFF><shad=002FFF>;;appear");
        player.getPackets().sendIComponentText(275, 60, "<col=FFFFFF>This command shows your character after being hidden.");

        player.getPackets().sendIComponentText(275, 61, "<col=FFFFFF><shad=002FFF>;;setlevel [SKILL #] [LEVEL #]");
        player.getPackets().sendIComponentText(275, 62, "<col=FFFFFF>This command allows you to change your level based on.");
        player.getPackets().sendIComponentText(275, 63, "<col=FFFFFF>the skill number you enter and the level you enter.");

        player.getPackets().sendIComponentText(275, 64, "<col=FFFFFF><shad=002FFF>;;setdisplay");
        player.getPackets().sendIComponentText(275, 65, "<col=FFFFFF>This command changes your display name.");

        player.getPackets().sendIComponentText(275, 66, "<col=FFFFFF><shad=002FFF>;;removedisplay [NAME]");
        player.getPackets().sendIComponentText(275, 67, "<col=FFFFFF>This command removes your display name.");

        player.getPackets().sendIComponentText(275, 68, "<col=FFFFFF><shad=002FFF>;;changepass [NEW PASSWORD]");
        player.getPackets().sendIComponentText(275, 69, "<col=FFFFFF>This command changes your password.");

        player.getPackets().sendIComponentText(275, 70, "<col=FFFFFF><shad=002FFF>;;switchitemslook");
        player.getPackets().sendIComponentText(275, 71, "<col=FFFFFF>This command changes switches to old or new item");
        player.getPackets().sendIComponentText(275, 72, "<col=FFFFFF>graphics, depending on which you like best.");

        player.getPackets().sendIComponentText(275, 73, "<col=FFFFFF><shad=002FFF>;;yell [LINE]");
        player.getPackets().sendIComponentText(275, 74, "<col=FFFFFF>This command makes you speak to everyone online.");

        player.getPackets().sendIComponentText(275, 75, "<col=FFFFFF><shad=002FFF>;;ancient");
        player.getPackets().sendIComponentText(275, 76, "<col=FFFFFF>This command changes your spellbook to ancient.");

        player.getPackets().sendIComponentText(275, 77, "<col=FFFFFF><shad=002FFF>;;modern");
        player.getPackets().sendIComponentText(275, 78, "<col=FFFFFF>This command changes your spellbook to normal.");

        player.getPackets().sendIComponentText(275, 79, "<col=FFFFFF><shad=002FFF>;;lunar");
        player.getPackets().sendIComponentText(275, 80, "<col=FFFFFF>This command changes your spellbook to lunar");

        player.getPackets().sendIComponentText(275, 81, "<img=15><col=FF0000>====================================<img=15>");
        player.getPackets().sendIComponentText(275, 82, "<img=15><col=FF0000>=============FUN COMMAND=S============<img=15>");

        player.getPackets().sendIComponentText(275, 83, "<col=FFFFFF><shad=002FFF>;;anim [ID #]");
        player.getPackets().sendIComponentText(275, 84, "<col=FFFFFF>This command makes you perform an animation. There");
        player.getPackets().sendIComponentText(275, 85, "<col=FFFFFF>are several RSPS animation lists online.");

        player.getPackets().sendIComponentText(275, 86, "<col=FFFFFF><shad=002FFF>;;gfx [ID #]");
        player.getPackets().sendIComponentText(275, 87, "<col=FFFFFF>This command makes you perform a graphic. There.");
        player.getPackets().sendIComponentText(275, 88, "<col=FFFFFF>are several RSPS graphic lists online.");

        player.getPackets().sendIComponentText(275, 89, "<col=FFFFFF><shad=002FFF>;;animgfx [ID #] [ID #]");
        player.getPackets().sendIComponentText(275, 90, "<col=FFFFFF>This command makes you perform both animation and.");
        player.getPackets().sendIComponentText(275, 91, "<col=FFFFFF>graphic in sync.");

        player.getPackets().sendIComponentText(275, 92, "<col=FFFFFF><shad=002FFF>;;remote [ID #]");
        player.getPackets().sendIComponentText(275, 93, "<col=FFFFFF>This command makes you perform a render animation or");
        player.getPackets().sendIComponentText(275, 94, "<col=FFFFFF>remote. There are few RSPS render or remote lists");
        player.getPackets().sendIComponentText(275, 95, "<col=FFFFFF>online.");

        player.getPackets().sendIComponentText(275, 96, "<col=FFFFFF><shad=002FFF>;;tonpc [ID #]");
        player.getPackets().sendIComponentText(275, 97, "<col=FFFFFF>This command transforms you into a NPC. There");
        player.getPackets().sendIComponentText(275, 98, "<col=FFFFFF>are several RSPS NPC lists online.");

        player.getPackets().sendIComponentText(275, 99, "<col=FFFFFF><shad=002FFF>;;title [ID #]");
        player.getPackets().sendIComponentText(275, 100, "<col=FFFFFF>This command gives you a title before or after your.");
        player.getPackets().sendIComponentText(275, 101, "<col=FFFFFF>name. Custom titles are given to those who create an.");
        player.getPackets().sendIComponentText(275, 102, "<col=FFFFFF>RSMV on the server. The RSMV must be 20 or more seconds.");

        player.getPackets().sendIComponentText(275, 103, "<col=FFFFFF><shad=002FFF>;;scream [LINE]");
        player.getPackets().sendIComponentText(275, 104, "<col=FFFFFF>This command makes you say a line in all caps instead of");
        player.getPackets().sendIComponentText(275, 105, "<col=FFFFFF>starting the line with :.");

        player.getPackets().sendIComponentText(275, 106, "<col=FFFFFF><shad=002FFF>;;armstyle [ID #]");
        player.getPackets().sendIComponentText(275, 107, "<col=FFFFFF>This command changes your armstyle no matter what top");
        player.getPackets().sendIComponentText(275, 108, "<col=FFFFFF>you are wearing. Males are 590 - 610 and females are.");
        player.getPackets().sendIComponentText(275, 109, "<col=FFFFFF>in the 400s.");

        player.getPackets().sendIComponentText(275, 110, "<col=FFFFFF><shad=002FFF>;;topstyle [ID #]");
        player.getPackets().sendIComponentText(275, 111, "<col=FFFFFF>This command changes your topstyle.");

        player.getPackets().sendIComponentText(275, 112, "<col=FFFFFF><shad=002FFF>;;hairstyle [ID #]");
        player.getPackets().sendIComponentText(275, 113, "<col=FFFFFF>This command changes your hairstyle.");

        player.getPackets().sendIComponentText(275, 114, "<col=FFFFFF><shad=002FFF>;;haircolor [ID #]");
        player.getPackets().sendIComponentText(275, 115, "<col=FFFFFF>This command changes your haircolor.");

        player.getPackets().sendIComponentText(275, 116, "<col=FFFFFF><shad=002FFF>;;skincolor [ID #]");
        player.getPackets().sendIComponentText(275, 117, "<col=FFFFFF>This command changes your skincolor. Go to the MakeOver");
        player.getPackets().sendIComponentText(275, 118, "<col=FFFFFF>Mage to change back if you can't find your original color.");

        player.getPackets().sendIComponentText(275, 119, "<col=FFFFFF><shad=002FFF>;;facemask");
        player.getPackets().sendIComponentText(275, 120, "<col=FFFFFF>This command enables you to wear a facemask with any hat,");
        player.getPackets().sendIComponentText(275, 121, "<col=FFFFFF>you will be unable to wear an amulet in order to do so.");

        player.getPackets().sendIComponentText(275, 122, "<col=FFFFFF><shad=002FFF>;;maskedearmuffs");
        player.getPackets().sendIComponentText(275, 123, "<col=FFFFFF>This command enables you to wear masked earmuffs with any");
        player.getPackets().sendIComponentText(275, 124, "<col=FFFFFF>hat, but you will be unable to wear an amulet in order");
        player.getPackets().sendIComponentText(275, 125, "<col=FFFFFF>to do so.");

        player.getPackets().sendIComponentText(275, 126, "<col=FFFFFF><shad=002FFF>;;goggles");
        player.getPackets().sendIComponentText(275, 127, "<col=FFFFFF>This command enables you to wear gnome goggles with any");
        player.getPackets().sendIComponentText(275, 128, "<col=FFFFFF>hat, but you will be unable to wear an amulet in order ");
        player.getPackets().sendIComponentText(275, 129, "<col=FFFFFF>to do so.");

        player.getPackets().sendIComponentText(275, 130, "<col=FFFFFF><shad=002FFF>;;eyepatches");
        player.getPackets().sendIComponentText(275, 131, "<col=FFFFFF>This command enables you to wear eyepatches with any");
        player.getPackets().sendIComponentText(275, 132, "<col=FFFFFF>hat, but you will be unable to wear an amulet in order");
        player.getPackets().sendIComponentText(275, 133, "<col=FFFFFF>to do so.");

        player.getPackets().sendIComponentText(275, 134, "<col=FFFFFF><shad=002FFF>;;highwayman");
        player.getPackets().sendIComponentText(275, 135, "<col=FFFFFF>This command enables you to wear a highwayman mask with");
        player.getPackets().sendIComponentText(275, 136, "<col=FFFFFF>any hat, but you will be unable to wear an amulet in order");
        player.getPackets().sendIComponentText(275, 137, "<col=FFFFFF>to do so.");

        player.getPackets().sendIComponentText(275, 137, "<col=FFFFFF><shad=002FFF>;;spec");
        player.getPackets().sendIComponentText(275, 137, "<col=FFFFFF>This command resets your spec.");

        player.getPackets().sendIComponentText(275, 138, "<col=FFFFFF><shad=002FFF>;;cole");
        player.getPackets().sendIComponentText(275, 139, "<col=FFFFFF>This command is life.");

        player.getPackets().sendIComponentText(275, 140, "<col=FFFFFF><shad=002FFF>;;enzo");
        player.getPackets().sendIComponentText(275, 141, "<col=FFFFFF>This command is life.");

        player.getPackets().sendIComponentText(275, 142, "<col=FFFFFF><shad=002FFF>;;swag");
        player.getPackets().sendIComponentText(275, 143, "<col=FFFFFF>This command is life.");

        player.getPackets().sendIComponentText(275, 144, "<img=15><col=FF0000>====================================<img=15>");
        player.getPackets().sendIComponentText(275, 145, "<img=15><col=FF0000>==========SECURITY  COMMANDS==========<img=15>");

        player.getPackets().sendIComponentText(275, 146, "<col=FFFFFF><shad=002FFF>;;jail [NAME]");
        player.getPackets().sendIComponentText(275, 147, "<col=FFFFFF>This command jails someone.");

        player.getPackets().sendIComponentText(275, 148, "<col=FFFFFF><shad=002FFF>;;unjail [NAME]");
        player.getPackets().sendIComponentText(275, 149, "<col=FFFFFF>This command unjails someone.");

        player.getPackets().sendIComponentText(275, 150, "<col=FFFFFF><shad=002FFF>;;mute [NAME]");
        player.getPackets().sendIComponentText(275, 151, "<col=FFFFFF>This command mutes someone.");

        player.getPackets().sendIComponentText(275, 152, "<col=FFFFFF><shad=002FFF>;;unmute [NAME]");
        player.getPackets().sendIComponentText(275, 153, "<col=FFFFFF>This command unmutes someone.");

        player.getPackets().sendIComponentText(275, 154, "<col=FFFFFF><shad=002FFF>;;ban [NAME]");
        player.getPackets().sendIComponentText(275, 155, "<col=FFFFFF>This command bans someone.");

        player.getPackets().sendIComponentText(275, 156, "<col=FFFFFF><shad=002FFF>;;unban [NAME]");
        player.getPackets().sendIComponentText(275, 157, "<col=FFFFFF>This command unbans someone.");

        player.getPackets().sendIComponentText(275, 158, "<col=FFFFFF><shad=002FFF>;;kick [NAME]");
        player.getPackets().sendIComponentText(275, 159, "<col=FFFFFF>This command kicks someone.");

        player.getPackets().sendIComponentText(275, 160, "<col=FFFFFF><shad=002FFF>;;forcekick [NAME]");
        player.getPackets().sendIComponentText(275, 161, "<col=FFFFFF>This command forcekicks someone.");

        player.getPackets().sendIComponentText(275, 162, "<col=FFFFFF><shad=002FFF>;;sendhome [NAME]");
        player.getPackets().sendIComponentText(275, 163, "<col=FFFFFF><shad=002FFF>;;unnull [NAME]");
        player.getPackets().sendIComponentText(275, 164, "<col=FFFFFF>This command unnulls someone.");

        player.getPackets().sendIComponentText(275, 165, "<col=FFFFFF><shad=002FFF>;;kill [NAME]");
        player.getPackets().sendIComponentText(275, 166, "<col=FFFFFF>This command kills someone.");
        player.getInterfaceManager().sendInterface(275);
    }

    public void sendAdminCommands() {
        for (int i = 0; i < 310; i++) {
            player.getPackets().sendIComponentText(275, i,
                                                   "");
        }
        player.getPackets().sendIComponentText(275, 1, "<col=00ffff>Vengium Command List</col>");
        player.getPackets().sendIComponentText(275, 10, "<col=FFA200><shad=FFEF00>Administrator Commands");
        player.getPackets().sendIComponentText(275, 11, "<img=15><col=FF0000>====================================<img=15>");
        player.getPackets().sendIComponentText(275, 12, "<img=15><col=FF0000>============BASIC COMMANDS============<img=15>");

        player.getPackets().sendIComponentText(275, 13, "<col=FFFFFF><shad=002FFF>;;home");
        player.getPackets().sendIComponentText(275, 14, "<col=FFFFFF>This command teleports you home.");

        player.getPackets().sendIComponentText(275, 15, "<col=FFFFFF><shad=002FFF>;;players");
        player.getPackets().sendIComponentText(275, 16, "<col=FFFFFF>This command views the players on the server.");

        player.getPackets().sendIComponentText(275, 17, "<col=FFFFFF><shad=002FFF>;;bank");
        player.getPackets().sendIComponentText(275, 18, "<col=FFFFFF>This command opens up your bank.");


        player.getPackets().sendIComponentText(275, 19, "<col=FFFFFF><shad=002FFF>;;item [ID #] [AMOUNT]");
        player.getPackets().sendIComponentText(275, 20, "<col=FFFFFF>This command spawns an item in your inventory based on");
        player.getPackets().sendIComponentText(275, 21, "<col=FFFFFF>the ID number and how many you want. www.itemdb.biz is");
        player.getPackets().sendIComponentText(275, 22, "<col=FFFFFF>the site you can go to in order to get the item ID.");

        player.getPackets().sendIComponentText(275, 23, "<col=FFFFFF><shad=002FFF>;;itemn [ITEM NAME]");
        player.getPackets().sendIComponentText(275, 24, "<col=FFFFFF>This command spawns an item in your inventory based on");
        player.getPackets().sendIComponentText(275, 25, "<col=FFFFFF>the actual name of the item. Make sure you spell right.");
        player.getPackets().sendIComponentText(275, 26, "<col=FFFFFF>If you don't know the spelling, go to the RS Wiki site.");

        player.getPackets().sendIComponentText(275, 27, "<img=15><col=FF0000>==================================<img=15>");
        player.getPackets().sendIComponentText(275, 28, "<img=15><col=FF0000>==========STARTER COMMANDS==========<img=15>");

        player.getPackets().sendIComponentText(275, 29, "<col=FFFFFF><shad=002FFF>;;master");
        player.getPackets().sendIComponentText(275, 30, "<col=FFFFFF>This command gives you gives you the maximum level in.");
        player.getPackets().sendIComponentText(275, 31, "<col=FFFFFF>all skills.");

        player.getPackets().sendIComponentText(275, 32, "<col=FFFFFF><shad=002FFF>;;rsmvset");
        player.getPackets().sendIComponentText(275, 33, "<col=FFFFFF>This command gives you several item emotes in bank.");

        player.getPackets().sendIComponentText(275, 34, "<col=FFFFFF><shad=002FFF>;;teleitems");
        player.getPackets().sendIComponentText(275, 35, "<col=FFFFFF>This command gives you several teleport items in bank");
        player.getPackets().sendIComponentText(275, 36, "<col=FFFFFF>and inventory.");

        player.getPackets().sendIComponentText(275, 37, "<col=FFFFFF><shad=002FFF>;;runes");
        player.getPackets().sendIComponentText(275, 38, "<col=FFFFFF>This command gives you runes in bank and inventory.");

        player.getPackets().sendIComponentText(275, 39, "<col=FFFFFF><shad=002FFF>;;gear");
        player.getPackets().sendIComponentText(275, 40, "<col=FFFFFF>This command gives you several weapons and armour in");
        player.getPackets().sendIComponentText(275, 41, "<col=FFFFFF>bank.");

        player.getPackets().sendIComponentText(275, 42, "<col=FFFFFF><shad=002FFF>;;skillcapes");
        player.getPackets().sendIComponentText(275, 43, "<col=FFFFFF>This command gives you all skillcapes in bank,");
        player.getPackets().sendIComponentText(275, 44, "<col=FFFFFF>including the untrimmed versions.");

        player.getPackets().sendIComponentText(275, 45, "<img=15><col=FF0000>==================================<img=15>");
        player.getPackets().sendIComponentText(275, 46, "<img=15><col=FF0000>==========USEFUL  COMMANDS==========<img=15>");

        player.getPackets().sendIComponentText(275, 47, "<col=FFFFFF><shad=002FFF>;;tele [X] [Y] [Z]");
        player.getPackets().sendIComponentText(275, 48, "<col=FFFFFF>This command teleports you somewhere based on the");
        player.getPackets().sendIComponentText(275, 49, "<col=FFFFFF>coordinates, which are X, Y, and Z.");

        player.getPackets().sendIComponentText(275, 50, "<col=FFFFFF><shad=002FFF>;;teleto [NAME]");
        player.getPackets().sendIComponentText(275, 51, "<col=FFFFFF>This command teleports you to someone based on the");
        player.getPackets().sendIComponentText(275, 52, "<col=FFFFFF>name you enter. This command will be modified later.");

        player.getPackets().sendIComponentText(275, 53, "<col=FFFFFF><shad=002FFF>;;clearbank");
        player.getPackets().sendIComponentText(275, 54, "<col=FFFFFF>This command clears everything in your bank.");

        player.getPackets().sendIComponentText(275, 55, "<col=FFFFFF><shad=002FFF>;;empty");
        player.getPackets().sendIComponentText(275, 56, "<col=FFFFFF>This command clears everything in your inventory.");

        player.getPackets().sendIComponentText(275, 57, "<col=FFFFFF><shad=002FFF>;;hide");
        player.getPackets().sendIComponentText(275, 58, "<col=FFFFFF>This command hides your character.");

        player.getPackets().sendIComponentText(275, 59, "<col=FFFFFF><shad=002FFF>;;appear");
        player.getPackets().sendIComponentText(275, 60, "<col=FFFFFF>This command shows your character after being hidden.");

        player.getPackets().sendIComponentText(275, 61, "<col=FFFFFF><shad=002FFF>;;setlevel [SKILL #] [LEVEL #]");
        player.getPackets().sendIComponentText(275, 62, "<col=FFFFFF>This command allows you to change your level based on.");
        player.getPackets().sendIComponentText(275, 63, "<col=FFFFFF>the skill number you enter and the level you enter.");

        player.getPackets().sendIComponentText(275, 64, "<col=FFFFFF><shad=002FFF>;;setdisplay");
        player.getPackets().sendIComponentText(275, 65, "<col=FFFFFF>This command changes your display name.");

        player.getPackets().sendIComponentText(275, 66, "<col=FFFFFF><shad=002FFF>;;removedisplay [NAME]");
        player.getPackets().sendIComponentText(275, 67, "<col=FFFFFF>This command removes your display name.");

        player.getPackets().sendIComponentText(275, 68, "<col=FFFFFF><shad=002FFF>;;changepass [NEW PASSWORD]");
        player.getPackets().sendIComponentText(275, 69, "<col=FFFFFF>This command changes your password.");

        player.getPackets().sendIComponentText(275, 70, "<col=FFFFFF><shad=002FFF>;;switchitemslook");
        player.getPackets().sendIComponentText(275, 71, "<col=FFFFFF>This command changes switches to old or new item");
        player.getPackets().sendIComponentText(275, 72, "<col=FFFFFF>graphics, depending on which you like best.");

        player.getPackets().sendIComponentText(275, 73, "<col=FFFFFF><shad=002FFF>;;yell [LINE]");
        player.getPackets().sendIComponentText(275, 74, "<col=FFFFFF>This command makes you speak to everyone online.");

        player.getPackets().sendIComponentText(275, 75, "<col=FFFFFF><shad=002FFF>;;ancient");
        player.getPackets().sendIComponentText(275, 76, "<col=FFFFFF>This command changes your spellbook to ancient.");

        player.getPackets().sendIComponentText(275, 77, "<col=FFFFFF><shad=002FFF>;;modern");
        player.getPackets().sendIComponentText(275, 78, "<col=FFFFFF>This command changes your spellbook to normal.");

        player.getPackets().sendIComponentText(275, 79, "<col=FFFFFF><shad=002FFF>;;lunar");
        player.getPackets().sendIComponentText(275, 80, "<col=FFFFFF>This command changes your spellbook to lunar");

        player.getPackets().sendIComponentText(275, 81, "<img=15><col=FF0000>====================================<img=15>");
        player.getPackets().sendIComponentText(275, 82, "<img=15><col=FF0000>============FUN COMMANDS============<img=15>");

        player.getPackets().sendIComponentText(275, 83, "<col=FFFFFF><shad=002FFF>;;anim [ID #]");
        player.getPackets().sendIComponentText(275, 84, "<col=FFFFFF>This command makes you perform an animation. There");
        player.getPackets().sendIComponentText(275, 85, "<col=FFFFFF>are several RSPS animation lists online.");

        player.getPackets().sendIComponentText(275, 86, "<col=FFFFFF><shad=002FFF>;;gfx [ID #]");
        player.getPackets().sendIComponentText(275, 87, "<col=FFFFFF>This command makes you perform a graphic. There.");
        player.getPackets().sendIComponentText(275, 88, "<col=FFFFFF>are several RSPS graphic lists online.");

        player.getPackets().sendIComponentText(275, 89, "<col=FFFFFF><shad=002FFF>;;animgfx [ID #] [ID #]");
        player.getPackets().sendIComponentText(275, 90, "<col=FFFFFF>This command makes you perform both animation and.");
        player.getPackets().sendIComponentText(275, 91, "<col=FFFFFF>graphic in sync.");

        player.getPackets().sendIComponentText(275, 92, "<col=FFFFFF><shad=002FFF>;;remote [ID #]");
        player.getPackets().sendIComponentText(275, 93, "<col=FFFFFF>This command makes you perform a render animation or");
        player.getPackets().sendIComponentText(275, 94, "<col=FFFFFF>remote. There are few RSPS render or remote lists");
        player.getPackets().sendIComponentText(275, 95, "<col=FFFFFF>online.");

        player.getPackets().sendIComponentText(275, 96, "<col=FFFFFF><shad=002FFF>;;tonpc [ID #]");
        player.getPackets().sendIComponentText(275, 97, "<col=FFFFFF>This command transforms you into a NPC. There");
        player.getPackets().sendIComponentText(275, 98, "<col=FFFFFF>are several RSPS NPC lists online.");

        player.getPackets().sendIComponentText(275, 99, "<col=FFFFFF><shad=002FFF>;;title [ID #]");
        player.getPackets().sendIComponentText(275, 100, "<col=FFFFFF>This command gives you a title before or after your.");
        player.getPackets().sendIComponentText(275, 101, "<col=FFFFFF>name. Custom titles are given to those who create an.");
        player.getPackets().sendIComponentText(275, 102, "<col=FFFFFF>RSMV on the server. The RSMV must be 20 or more seconds.");

        player.getPackets().sendIComponentText(275, 103, "<col=FFFFFF><shad=002FFF>;;scream [LINE]");
        player.getPackets().sendIComponentText(275, 104, "<col=FFFFFF>This command makes you say a line in all caps instead of");
        player.getPackets().sendIComponentText(275, 105, "<col=FFFFFF>starting the line with :.");

        player.getPackets().sendIComponentText(275, 106, "<col=FFFFFF><shad=002FFF>;;armstyle [ID #]");
        player.getPackets().sendIComponentText(275, 107, "<col=FFFFFF>This command changes your armstyle no matter what top");
        player.getPackets().sendIComponentText(275, 108, "<col=FFFFFF>you are wearing. Males are 590 - 610 and females are.");
        player.getPackets().sendIComponentText(275, 109, "<col=FFFFFF>in the 400s.");

        player.getPackets().sendIComponentText(275, 110, "<col=FFFFFF><shad=002FFF>;;topstyle [ID #]");
        player.getPackets().sendIComponentText(275, 111, "<col=FFFFFF>This command changes your topstyle.");

        player.getPackets().sendIComponentText(275, 112, "<col=FFFFFF><shad=002FFF>;;hairstyle [ID #]");
        player.getPackets().sendIComponentText(275, 113, "<col=FFFFFF>This command changes your hairstyle.");

        player.getPackets().sendIComponentText(275, 114, "<col=FFFFFF><shad=002FFF>;;haircolor [ID #]");
        player.getPackets().sendIComponentText(275, 115, "<col=FFFFFF>This command changes your haircolor.");

        player.getPackets().sendIComponentText(275, 116, "<col=FFFFFF><shad=002FFF>;;skincolor [ID #]");
        player.getPackets().sendIComponentText(275, 117, "<col=FFFFFF>This command changes your skincolor. Go to the MakeOver");
        player.getPackets().sendIComponentText(275, 118, "<col=FFFFFF>Mage to change back if you can't find your original color.");

        player.getPackets().sendIComponentText(275, 119, "<col=FFFFFF><shad=002FFF>;;facemask");
        player.getPackets().sendIComponentText(275, 120, "<col=FFFFFF>This command enables you to wear a facemask with any hat,");
        player.getPackets().sendIComponentText(275, 121, "<col=FFFFFF>you will be unable to wear an amulet in order to do so.");

        player.getPackets().sendIComponentText(275, 122, "<col=FFFFFF><shad=002FFF>;;maskedearmuffs");
        player.getPackets().sendIComponentText(275, 123, "<col=FFFFFF>This command enables you to wear masked earmuffs with any");
        player.getPackets().sendIComponentText(275, 124, "<col=FFFFFF>hat, but you will be unable to wear an amulet in order");
        player.getPackets().sendIComponentText(275, 125, "<col=FFFFFF>to do so.");

        player.getPackets().sendIComponentText(275, 126, "<col=FFFFFF><shad=002FFF>;;goggles");
        player.getPackets().sendIComponentText(275, 127, "<col=FFFFFF>This command enables you to wear gnome goggles with any");
        player.getPackets().sendIComponentText(275, 128, "<col=FFFFFF>hat, but you will be unable to wear an amulet in order ");
        player.getPackets().sendIComponentText(275, 129, "<col=FFFFFF>to do so.");

        player.getPackets().sendIComponentText(275, 130, "<col=FFFFFF><shad=002FFF>;;eyepatches");
        player.getPackets().sendIComponentText(275, 131, "<col=FFFFFF>This command enables you to wear eyepatches with any");
        player.getPackets().sendIComponentText(275, 132, "<col=FFFFFF>hat, but you will be unable to wear an amulet in order");
        player.getPackets().sendIComponentText(275, 133, "<col=FFFFFF>to do so.");

        player.getPackets().sendIComponentText(275, 134, "<col=FFFFFF><shad=002FFF>;;highwayman");
        player.getPackets().sendIComponentText(275, 135, "<col=FFFFFF>This command enables you to wear a highwayman mask with");
        player.getPackets().sendIComponentText(275, 136, "<col=FFFFFF>any hat, but you will be unable to wear an amulet in order");
        player.getPackets().sendIComponentText(275, 137, "<col=FFFFFF>to do so.");

        player.getPackets().sendIComponentText(275, 138, "<col=FFFFFF><shad=002FFF>;;cole");
        player.getPackets().sendIComponentText(275, 139, "<col=FFFFFF>This command is life.");

        player.getPackets().sendIComponentText(275, 140, "<col=FFFFFF><shad=002FFF>;;enzo");
        player.getPackets().sendIComponentText(275, 141, "<col=FFFFFF>This command is life.");

        player.getPackets().sendIComponentText(275, 142, "<col=FFFFFF><shad=002FFF>;;swag");
        player.getPackets().sendIComponentText(275, 143, "<col=FFFFFF>This command is life.");

        player.getPackets().sendIComponentText(275, 144, "<img=15><col=FF0000>====================================<img=15>");
        player.getPackets().sendIComponentText(275, 145, "<img=15><col=FF0000>==========SECURITY  COMMANDS==========<img=15>");

        player.getPackets().sendIComponentText(275, 146, "<col=FFFFFF><shad=002FFF>;;jail [NAME]");
        player.getPackets().sendIComponentText(275, 147, "<col=FFFFFF>This command jails someone.");

        player.getPackets().sendIComponentText(275, 148, "<col=FFFFFF><shad=002FFF>;;unjail [NAME]");
        player.getPackets().sendIComponentText(275, 149, "<col=FFFFFF>This command unjails someone.");

        player.getPackets().sendIComponentText(275, 150, "<col=FFFFFF><shad=002FFF>;;mute [NAME]");
        player.getPackets().sendIComponentText(275, 151, "<col=FFFFFF>This command mutes someone.");

        player.getPackets().sendIComponentText(275, 152, "<col=FFFFFF><shad=002FFF>;;unmute [NAME]");
        player.getPackets().sendIComponentText(275, 153, "<col=FFFFFF>This command unmutes someone.");

        player.getPackets().sendIComponentText(275, 154, "<col=FFFFFF><shad=002FFF>;;ban [NAME]");
        player.getPackets().sendIComponentText(275, 155, "<col=FFFFFF>This command bans someone.");

        player.getPackets().sendIComponentText(275, 156, "<col=FFFFFF><shad=002FFF>;;unban [NAME]");
        player.getPackets().sendIComponentText(275, 157, "<col=FFFFFF>This command unbans someone.");

        player.getPackets().sendIComponentText(275, 158, "<col=FFFFFF><shad=002FFF>;;kick [NAME]");
        player.getPackets().sendIComponentText(275, 159, "<col=FFFFFF>This command kicks someone.");

        player.getPackets().sendIComponentText(275, 160, "<col=FFFFFF><shad=002FFF>;;forcekick [NAME]");
        player.getPackets().sendIComponentText(275, 161, "<col=FFFFFF>This command forcekicks someone.");

        player.getPackets().sendIComponentText(275, 162, "<col=FFFFFF><shad=002FFF>;;sendhome [NAME]");
        player.getPackets().sendIComponentText(275, 163, "<col=FFFFFF><shad=002FFF>;;unnull [NAME]");
        player.getPackets().sendIComponentText(275, 164, "<col=FFFFFF>This command unnulls someone.");

        player.getPackets().sendIComponentText(275, 165, "<col=FFFFFF><shad=002FFF>;;kill [NAME]");
        player.getPackets().sendIComponentText(275, 166, "<col=FFFFFF>This command kills someone.");
        player.getInterfaceManager().sendInterface(275);
    }

    public void openRSMVInfo() {
        for (int i = 0; i < 310; i++) {
            player.getPackets().sendIComponentText(275, i,
                                                   "");
        }
        player.getPackets().sendIComponentText(275, 1, "<col=00ffff><col=000005><img=23>Public RSMV Activity<img=23></col>");
        player.getPackets().sendIComponentText(275, 10, "<col=FFA200><shad=FFEF00>Upcoming MEPs");
        player.getPackets().sendIComponentText(275, 11, "<col=FFFFFF><shad=000000>My Life For Hire(ADTR) - Hosted by TheChampq!");
        player.getPackets().sendIComponentText(275, 12, "<col=000000><shad=000000><img=1>COMPLETED!");
        player.getPackets().sendIComponentText(275, 13, "<col=000000><shad=000000><img=1>OUT!");
        player.getPackets().sendIComponentText(275, 14, "<col=000000><shad=000000><img=1>NOW!");
        player.getPackets().sendIComponentText(275, 15, "<col=FFFFFF><shad=000000>Tonight Alive(The Edge) - Hosted by TDO!");
        player.getPackets().sendIComponentText(275, 16, "<col=FF0000>============PARTS DONE(2/5)=========<img=15>");
        player.getPackets().sendIComponentText(275, 17, "<col=FFA200>Rawr</col>, <col=FFA200>Royal Wolve</col>,  <col=FFA200>TheChampq</col>, <col=04AE00>Miljard in place of BBW</col>, <col=04AE00>99orWoh</col>");
        player.getPackets().sendIComponentText(275, 18, "<col=FFFFFF><shad=000000>NJ Legion Iced Tea(ADTR) - Hosted by TheChampq!");
        player.getPackets().sendIComponentText(275, 19, "<col=000000><shad=000000><img=1>CANCELLED!");
        player.getPackets().sendIComponentText(275, 20, "<col=FFFFFF><shad=000000>Alarm the Alarm(Write This Down) - Hosted by Jeff!");
        player.getPackets().sendIComponentText(275, 21, "<col=FF0000>============PARTS DONE(2/8)=========<img=15>");
        player.getPackets().sendIComponentText(275, 22, "<col=FFFFFF><shad=000000>I Don't Want To Be Here Anymore(Rise Against) - Hosted by Aleksi!");
        player.getPackets().sendIComponentText(275, 23, "<col=FF0000>============PARTS DONE(N/A)=========<img=15>");
        player.getPackets().sendIComponentText(275, 24, "<col=FFFFFF><shad=000000>Vagrant Stories(Broadway) - Hosted by SoullPicz!");
        player.getPackets().sendIComponentText(275, 25, "<col=FF0000>============PARTS DONE(3/11)=========<img=15>");
        player.getPackets().sendIComponentText(275, 26, "<col=FFA200><shad=FFEF00>CONTESTS");
        player.getPackets().sendIComponentText(275, 27, "<img=1><col=FF0000>RSMV Fight Tournament - Now Active!");
        player.getPackets().sendIComponentText(275, 28, "<img=15><col=04EA00>1. Maximum 20 - 50 seconds.");
        player.getPackets().sendIComponentText(275, 29, "<img=15><col=04EA00>2. No MEP parts or reuploads.");
        player.getPackets().sendIComponentText(275, 30, "<img=15><col=04EA00>3. Due September 15, 2014 aka (9/15/2014).");

        player.getInterfaceManager().sendInterface(275);
        player.unlock();
    }

    public void closeQuests() {
        // TODO Auto-generated method stub

    }

}
