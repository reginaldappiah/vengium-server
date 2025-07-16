package com.rs.game.player.controlers;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.npc.wguild.AnimatedArmour;
import com.rs.game.player.HintIcon;
import com.rs.game.player.Player;
import com.rs.game.player.actions.PlayerCombat;
import com.rs.game.player.controlers.Controler;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class WGuildControler extends Controler {

private int tick;
private static NPC animatedArmour;

@Override
public void start() {
sendInterfaces();
}

@Override
public void process() {
sendInterfaces();
if(player.getTemporaryAttributtes().get("killednpc") != null) {
player.setWGuildTokens((player.getWGuildTokens() + (Integer) player.getTemporaryAttributtes().get("killednpc")) );
player.getTemporaryAttributtes().remove("killednpc");
sendInterfaces();
return;
}
if(player.inClopsRoom()) {
if(player.getWGuildTokens() <= 20) {
player.getPackets().sendGameMessage("<col=00ff00>Your time is about to run out. Please leave the cyclops room.", true);
}
if(player.getWGuildTokens() <= 10) {
player.setNextWorldTile(new WorldTile(2843, 3536, 2));
player.getPackets().sendGameMessage("Your time has run out.");
player.setInClopsRoom(false);
return;
}
if(tick == 20) {
player.setWGuildTokens((player.getWGuildTokens() - 10));
sendInterfaces();
player.getPackets().sendGameMessage("<col=00ff00>Your tokens reduce by 10.", true);
tick = 0;
}
tick++;
}
}

@Override
public boolean processMagicTeleport(WorldTile toTile) {
if(player.inClopsRoom()) {
player.getDialogueManager()
.startDialogue("SimpleMessage",
new Object[] {"A magical force prevents you from teleporting." });
return false;
} else {
stop();
return true;
}
}

@Override
public boolean processItemTeleport(WorldTile toTile) {
if(player.inClopsRoom()) {
player.getDialogueManager()
.startDialogue("SimpleMessage",
new Object[] {"A magical force prevents you from teleporting." });
return false;
} else {
stop();
return true;
}
}

public static void handleItemOnObject(final Player player, WorldObject object, Item item) {
player.getControlerManager().startControler("WGuildControler", new Object[] { });
if(isArmour(item.getId())) {
if(player.getY() != 3533) {
player.getPackets().sendGameMessage("You cannot add the armour from this side.");
return;
}
int npcId = 0;
int armourSlot = 0;
switch(getArmour(item)) {
case "bronze":
npcId = 4278;
armourSlot = 1;
break;
case "iron":
npcId = 4279;
armourSlot = 0;
break;
case "steel":
npcId = 4280;
armourSlot = 2;
break;
case "mithril":
npcId = 4282;
armourSlot = 3;
break;
case "adamant":
npcId = 4283;
armourSlot = 4;
break;
case "rune":
npcId = 4284;
armourSlot = 5;
break;
}
final int finalNpc = npcId;
if(!contains(player, ARMOURS[armourSlot])) {
player.getPackets().sendGameMessage("You do not have all the required armour pieces.");
return;
}
player.setNextAnimation(new Animation(827));
player.getPackets().sendGameMessage("You place the armour on the animator and it starts to glow");
deleteItems(player, ARMOURS[armourSlot]);
WorldTasksManager.schedule(new WorldTask() {
@Override
public void run() {
player.addWalkSteps(player.getX(), player.getY()+5);
stop();
}
}, 3);
WorldTasksManager.schedule(new WorldTask() {
@Override
public void run() {
animatedArmour = new AnimatedArmour(finalNpc, new WorldTile(player.getX(), player.getY()-5, player.getPlane()), -1, true, true);
animatedArmour.setNextForceTalk(new ForceTalk("I'm ALIVE!"));
animatedArmour.addWalkSteps(animatedArmour.getX(), animatedArmour.getY()+4);
animatedArmour.setTarget(player);
((AnimatedArmour) animatedArmour).setPlayerCon(player);
animatedArmour.setAttackedBy(player);
player.getHintIconsManager().addHintIcon(animatedArmour, 0, -1, false);
stop();
}
}, 6);
}
}

@Override
public boolean canAttack(Entity target) {
if(target instanceof AnimatedArmour) {
AnimatedArmour n = (AnimatedArmour) target;
if(player != n.getPlayerCon()) {
player.getPackets().sendGameMessage("It's not after you.");
return false;
}
}
return true;
}

public static void deleteItems(Player player, int[] items) {
for(int i = 0; i < items.length; i++) 
player.getInventory().deleteItem(items[i], 1);
}

public static boolean isArmour(int itemId) {
for(int i = 0; i < ARMOURS.length; i++) {
for(int i2 = 0; i2 < ARMOURS[i].length; i2++) {
if(itemId == ARMOURS[i][i2]) {
return true;
}
}
}
return false;
}

public static String getArmour(Item item) {
String name = item.getDefinitions().getName().toLowerCase();
if(name.contains("bronze")) {
return "bronze";
} else if(name.contains("steel")) {
return "steel";
} else if(name.contains("iron")) {
return "iron";
} else if(name.contains("mithril")) {
return "mithril";
} else if(name.contains("adamant")) {
return "adamant";
} else if(name.contains("rune")) {
return "rune";
}
return null;
}

public static boolean contains(Player player, int[] item) {
for(int i = 0; i < item.length; i++) {
if(!player.getInventory().containsItem(item[i], 1)) {
return false;
}
}
return true;
}

@Override
public boolean processObjectTeleport(WorldTile tile) {
if(player.inClopsRoom()) {
player.getDialogueManager()
.startDialogue("SimpleMessage",
new Object[] {"A magical force prevents you from teleporting." });
return false;
} else {
stop();
return true;
}
}

public void stop() {
if(animatedArmour != null) {
animatedArmour.finish();
}
player.getPackets().closeInterface(player.getInterfaceManager().hasRezizableScreen() ? 11 : 8);
removeControler();
}

@Override
public boolean processObjectClick1(WorldObject object) {
start();
if(object.getId() == 66599 || object.getId() == 66601) {
if(player.getX() == 2846) {
enterClopsRoom();
} else if(player.getX() == 2847) {
leaveClopsRoom();
}
return false;
}
if(animatedArmour != null) {
animatedArmour.finish();
}
return true;
}

@Override
public boolean logout() {
return false;
}

@Override
public boolean login() {
start();
return false;
}

@Override
public boolean processButtonClick(int interfaceId, int componentId, int slotId, int packetId) {
if(interfaceId == 271) {
if(player.getPrayer().isAncientCurses()) {
player.getPackets().sendGameMessage("The warriors of this guild forbid such dark prayers.");
return false;
}
}
return true;
}

public void enterClopsRoom() {
if(player.getWGuildTokens() < 100) {
player.getPackets().sendGameMessage("You must have over 100 tokens to enter.");
return;
}
player.setInClopsRoom(true);
player.setNextWorldTile(new WorldTile(player.getX()+1, player.getY(), player.getPlane()));
}

public void leaveClopsRoom() {
player.setInClopsRoom(false);
player.setNextWorldTile(new WorldTile(player.getX()-1, player.getY(), player.getPlane()));
}

public void sendInterfaces() {
player.getInterfaceManager().sendTab(player.getInterfaceManager().hasRezizableScreen() ? 11 : 8, 1057);
player.getPackets().sendConfig(2030, player.getWGuildTokens());
}

public static void dropDefender(Player player, NPC npc) {
int itemId = 8844;
for(int i = 0; i < DEFENDERS.length; i++) {
if(player.getInventory().containsItem(DEFENDERS[i], 1)) {
	if(DEFENDERS[i] == 20072) {
		itemId = 20072;
} else {
itemId = DEFENDERS[i + 1];
}	
}
}
World.addGroundItem(new Item(itemId, 1), new WorldTile(
npc.getCoordFaceX(npc.getSize()), npc.getCoordFaceY(npc.getSize()), npc.getPlane()), player,
false, 180, true);
}
//	 Iron	 Bronze	 Steel
public static int[][] ARMOURS = {{1153, 1115, 1067 }, { 1155, 1117, 1075 }, { 1157, 1119, 1069 }, 
//Mithril	 Adamant	 Rune
{ 1159, 1121, 1071 }, { 1161, 1123, 1073 }, { 1163, 1127, 1079 }};
public static int[] DEFENDERS = { 8844, 8845, 8846, 8847, 8848, 8849, 8850, 20072 };

}