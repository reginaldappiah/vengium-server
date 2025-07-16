package com.rs.game.player.cutscenes;

import java.util.ArrayList;

import com.rs.Settings;
import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.cutscenes.actions.ConstructMapAction;
import com.rs.game.player.cutscenes.actions.CreateNPCAction;
import com.rs.game.player.cutscenes.actions.CutsceneAction;
import com.rs.game.player.cutscenes.actions.DestroyCachedObjectAction;
import com.rs.game.player.cutscenes.actions.LookCameraAction;
import com.rs.game.player.cutscenes.actions.MoveNPCAction;
import com.rs.game.player.cutscenes.actions.MovePlayerAction;
import com.rs.game.player.cutscenes.actions.NPCAnimationAction;
import com.rs.game.player.cutscenes.actions.NPCFaceTileAction;
import com.rs.game.player.cutscenes.actions.NPCForceTalkAction;
import com.rs.game.player.cutscenes.actions.NPCGraphicAction;
import com.rs.game.player.cutscenes.actions.PlayerAnimationAction;
import com.rs.game.player.cutscenes.actions.PlayerFaceTileAction;
import com.rs.game.player.cutscenes.actions.PlayerForceTalkAction;
import com.rs.game.player.cutscenes.actions.PlayerGraphicAction;
import com.rs.game.player.cutscenes.actions.PlayerMusicEffectAction;
import com.rs.game.player.cutscenes.actions.PlayerTeleportAction;
import com.rs.game.player.cutscenes.actions.PlayerTransformAction;
import com.rs.game.player.cutscenes.actions.PosCameraAction;

import java.util.ArrayList;

public class MilesScene extends Cutscene {
	
	private static int GUTHIX = 1;
	//Pet Rock Scene
	private static int ROCK = 2;
	private static int ROCK2 = 3;
	private static int ROCK3 = 4;
	//Yak City
	private static int YAK = 5;
	private static int YAK2 = 6;
	private static int YAK3 = 7;
	//God Wars Scene
	private static int BANDOS = 8;
	//Corporeal Scene
	private static int CORP = 9;
	//Felidp Hills Scene
	private static int CHINCHOMPA = 10;
	private static int CHINCHOMPA2 = 11;
	private static int CHINCHOMPA3 = 12;
	//Ardy Theiving Scene
	private static int GUARD = 13;
	private static int GUARD2 = 14;
	private static int GUARD3 = 15;
	//Kuradal's Dungeon Scene
	private static int MITH = 16;
	private static int MITH2 = 17;
	//Pest Control Scene
	private static int SPLAT = 18;
	private static int TORCH = 19;
	private static int BRAWL = 20;
    @Override
    public boolean hiddenMinimap() {
        return false;
    }
    
    @Override
    public CutsceneAction[] getActions(Player player) {
        ArrayList<CutsceneAction> actionsList = new ArrayList<CutsceneAction>();
        actionsList.add(new PlayerTeleportAction(2908, 9696, 0));
        actionsList.add(new PosCameraAction(getX(player, 2908), getY(player,
        		9698), 2000, 100, 100, -1));
        actionsList.add(new LookCameraAction(getX(player, 2908), getY(player,
				9692), 2000, 100, 100, -1));
        actionsList.add(new MovePlayerAction(2908, 9696, 0, 127, 3));
        actionsList.add(new MovePlayerAction(2908, 9686, false, 4));
        actionsList.add(new PosCameraAction(getX(player, 2908), getY(player,
        		9691), 2000, 12, 6, -1));
        actionsList.add(new LookCameraAction(getX(player, 2908), getY(player,
				9684), 2000, 100, 100, 6));
        actionsList.add(new CreateNPCAction(GUTHIX, 8008, 2908, 9684, 0, -1));
        actionsList.add(new NPCFaceTileAction(GUTHIX, player.getX(), player.getY(), -1));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Oh finally, you are awake!", 3));
        
        actionsList.add(new PlayerForceTalkAction("Where am I?", 3));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "For now, that does not matter...", 3));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "... You are in the realm of "+Settings.SERVER_NAME+"", 3));
        actionsList.add(new PlayerForceTalkAction("Can you show me around?", 3));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Of course, no problem!", 3));
        
    //Rock Crabs
     	actionsList.add(new ConstructMapAction(332, 461, 4, 6));
     	actionsList.add(new MovePlayerAction(14, 40, 0, Player.TELE_MOVE_TYPE,0));
     	actionsList.add(new PosCameraAction(14, 5, 6000, -1));
     	actionsList.add(new LookCameraAction(14, 20, 3000, -1));
     	actionsList.add(new CreateNPCAction(GUTHIX, 8008, 14, 28, 0, -1));
     	actionsList.add(new CreateNPCAction(ROCK, 1267, 15, 27, 0, -1));
 		actionsList.add(new CreateNPCAction(ROCK2, 1267, 14, 27, 0, -1));
 		actionsList.add(new CreateNPCAction(ROCK3, 1267, 13, 27, 0, -1));
 		actionsList.add(new NPCFaceTileAction(GUTHIX, 14, 0, -1));
 		actionsList.add(new NPCFaceTileAction(ROCK, 15, 0, -1));
 		actionsList.add(new NPCFaceTileAction(ROCK2, 14, 0, -1));
 		actionsList.add(new NPCFaceTileAction(ROCK3, 13, 0, -1));
     	actionsList.add(new PosCameraAction(14, 17, 5000, 1, 1, -1));
     	actionsList.add(new NPCForceTalkAction(GUTHIX, "This is rock crabs.", 2));
     	actionsList.add(new NPCForceTalkAction(GUTHIX, "This is where players typically begin to train.", 2));
     	actionsList.add(new NPCForceTalkAction(GUTHIX, "This is where players typically begin to train.", 1));
     	actionsList.add(new NPCForceTalkAction(GUTHIX, "Simply click the train button on your crystal teleport to get started.", 2));
     	actionsList.add(new NPCForceTalkAction(GUTHIX, "Simply click the train button on your crystal teleport to get started.", 3));
     
      //Yak City
        actionsList.add(new ConstructMapAction(289, 471, 4, 6));
 		actionsList.add(new MovePlayerAction(14, 40, 0, Player.TELE_MOVE_TYPE,0));
 		actionsList.add(new PosCameraAction(14, 5, 6000, -1));
 		actionsList.add(new LookCameraAction(14, 20, 3000, -1));
 		actionsList.add(new CreateNPCAction(GUTHIX, 8008, 14, 28, 0, -1));
 		actionsList.add(new CreateNPCAction(YAK	, 5529, 14, 25, 0, -1));
 		actionsList.add(new CreateNPCAction(YAK2, 5529, 13, 25, 0, -1));
 		actionsList.add(new CreateNPCAction(YAK3, 5529, 12, 25, 0, -1));
 		actionsList.add(new NPCFaceTileAction(GUTHIX, 14, 0, -1));
 		actionsList.add(new NPCFaceTileAction(YAK, 15, 0, -1));
 		actionsList.add(new NPCFaceTileAction(YAK2, 14, 0, -1));
 		actionsList.add(new NPCFaceTileAction(YAK3, 13, 0, -1));
 		actionsList.add(new PosCameraAction(14, 17, 5000, 1, 1, -1));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "Another preferred combat training location.", 2));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "Another preferred combat training location.", 1));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "For quick access to this location, use the command ::yaks.", 2));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "For quick access to this location, use the command ::yaks.", 2));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "For quick access to this location, use the command ::yaks.", 1));
      //God Wars Dungeon
 		actionsList.add(new ConstructMapAction(357, 667, 4, 12));
 		actionsList.add(new MovePlayerAction(14, 45, 2, Player.TELE_MOVE_TYPE,0));
 		actionsList.add(new PosCameraAction(14, 10, 4000, -1));
 		actionsList.add(new LookCameraAction(14, 24, 3000, -1));
 		actionsList.add(new CreateNPCAction(GUTHIX, 8008, 14, 25, 2, -1));
 		actionsList.add(new CreateNPCAction(BANDOS, 6260, 14, 27, 2, -1));
 		actionsList.add(new NPCFaceTileAction(GUTHIX, 14, 24, -1));
 		actionsList.add(new NPCFaceTileAction(BANDOS, 14, 24, -1));
 		actionsList.add(new PosCameraAction(14, 21, 3000, 1, 1, -1));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Once you reach higher levels,-", 2));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "Once you reach higher levels,-", 1));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "-you can take on dungeons such as the God Wars Dungon for a good income.", 2));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "-you can take on dungeons such as the God Wars Dungon for a good income.", 2));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "-you can take on dungeons such as the God Wars Dungon for a good income.", 1));
 		
 	//Corporeal Beast
        actionsList.add(new ConstructMapAction(366, 545, 4, 12));
        actionsList.add(new PlayerTransformAction(6666, -1));
 		actionsList.add(new MovePlayerAction(14, 65, 2, Player.TELE_MOVE_TYPE,0));
 		actionsList.add(new PosCameraAction(14, 10, 4000, -1));
 		actionsList.add(new LookCameraAction(14, 24, 3000, -1));
 		actionsList.add(new CreateNPCAction(GUTHIX, 8008, 14, 25, 2, -1));
 		actionsList.add(new CreateNPCAction(CORP, 8133, 14, 27, 2, -1));
 		actionsList.add(new NPCFaceTileAction(GUTHIX, 14, 24, -1));
 		actionsList.add(new NPCFaceTileAction(CORP, 14, 24, -1));
 		actionsList.add(new PosCameraAction(14, 21, 3000, 1, 1, -1));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Even better,-", 2));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "Even better,-", 1));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "-you can fight giant bosses to recieve extremely valuable drops.", 2));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "-you can fight giant bosses to recieve extremely valuable drops.", 2));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "-you can fight giant bosses to recieve extremely valuable drops.", 1));
       
    //Chinchompa Hunting 
        actionsList.add(new ConstructMapAction(319, 358, 4, 6));
        actionsList.add(new PlayerTransformAction(6666, -1));
 		actionsList.add(new MovePlayerAction(14, 50, 0, Player.TELE_MOVE_TYPE,0));
 		actionsList.add(new PosCameraAction(14, 10, 4000, -1));
 		actionsList.add(new LookCameraAction(14, 24, 3000, -1));
 		actionsList.add(new CreateNPCAction(GUTHIX, 8008, 14, 28, 0, -1));
 		actionsList.add(new CreateNPCAction(CHINCHOMPA, 5080, 15, 27, 0, -1));
 		actionsList.add(new CreateNPCAction(CHINCHOMPA2, 5080, 14, 27, 0, -1));
 		actionsList.add(new CreateNPCAction(CHINCHOMPA3, 5080, 13, 27, 0, -1));
 		actionsList.add(new NPCFaceTileAction(GUTHIX, 14, 24, -1));
 		actionsList.add(new NPCFaceTileAction(CHINCHOMPA, 15, 24, -1));
 		actionsList.add(new NPCFaceTileAction(CHINCHOMPA2, 14, 24, -1));
 		actionsList.add(new NPCFaceTileAction(CHINCHOMPA3, 13, 24, -1));
 		actionsList.add(new PosCameraAction(14, 21, 3000, 1, 1, -1));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Not only is combat a good source of money,-", 2));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "Not only is combat a good source of money,-", 1));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "-but skilling too.", 2));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "-but skilling too.", 2));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "-but skilling too.", 1));
 		actionsList.add(new NPCAnimationAction(GUTHIX, new Animation(2113), -1));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "And who doesn't love hunting chinchompas.", 2));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "And who doesn't love hunting chinchompas.", 2));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "And who doesn't love hunting chinchompas.", 1));
        
     //Ardougne Thieving Stalls
        actionsList.add(new ConstructMapAction(331, 410, 4, 6));
        actionsList.add(new PlayerTransformAction(6666, -1));
 		actionsList.add(new MovePlayerAction(12, 50, 0, Player.TELE_MOVE_TYPE,0));
 		actionsList.add(new PosCameraAction(12, 10, 4000, -1));
 		actionsList.add(new LookCameraAction(12, 24, 3000, -1));
 		actionsList.add(new CreateNPCAction(GUTHIX, 8008, 10, 28, 0, -1));
 		actionsList.add(new CreateNPCAction(GUARD, 21, 11, 29, 0, -1));
 		actionsList.add(new CreateNPCAction(GUARD2, 32, 12, 28, 0, -1));
 		actionsList.add(new CreateNPCAction(GUARD3, 23, 13, 27, 0, -1));
 		actionsList.add(new NPCFaceTileAction(GUTHIX, 10, 24, -1));
 		actionsList.add(new NPCFaceTileAction(GUARD, 11, 28, -1));
 		actionsList.add(new NPCFaceTileAction(GUARD2, 12, 27, -1));
 		actionsList.add(new NPCFaceTileAction(GUARD3, 13, 26, -1));
 		actionsList.add(new PosCameraAction(12, 21, 3000, 1, 1, -1));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Thieving from stalls is also a good source of money,-", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Thieving from stalls is also a good source of money,-", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Thieving from stalls is also a good source of money,-", 1));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "-where you can sell your loots to the general store.", 2));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "-where you can sell your loots to the general store.", 2));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "-where you can sell your loots to the general store.", 2));
        
      //Catherby Fishing
        actionsList.add(new ConstructMapAction(355, 425, 4, 6));
        actionsList.add(new PlayerTransformAction(6666, -1));
 		actionsList.add(new MovePlayerAction(14, 50, 0, Player.TELE_MOVE_TYPE,0));
 		actionsList.add(new PosCameraAction(14, 10, 4000, -1));
 		actionsList.add(new LookCameraAction(14, 24, 3000, -1));
 		actionsList.add(new CreateNPCAction(GUTHIX, 8008, 14, 28, 0, -1));
 		actionsList.add(new NPCFaceTileAction(GUTHIX, 14, 24, -1));
 		actionsList.add(new PosCameraAction(14, 21, 3000, 1, 1, -1));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "There is also fishing which everyone loves.", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "There is also fishing which everyone loves.", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "There is also fishing which everyone loves.", 1));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "Chose to cook the fish or sell it raw!", 2));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "Chose to cook the fish or sell it raw!", 2));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "Chose to cook the fish or sell it raw!", 2));
     //Kuradeal's Slayer Dungeon
        actionsList.add(new ConstructMapAction(200, 659, 4, 6));
        actionsList.add(new PlayerTransformAction(6666, -1));
 		actionsList.add(new MovePlayerAction(14, 50, 0, Player.TELE_MOVE_TYPE,0));
 		actionsList.add(new PosCameraAction(14, 10, 4000, -1));
 		actionsList.add(new LookCameraAction(14, 24, 3000, -1));
 		actionsList.add(new CreateNPCAction(GUTHIX, 8008, 14, 28, 0, -1));
 		actionsList.add(new CreateNPCAction(MITH, 55, 10, 30, 0, -1));
 		actionsList.add(new CreateNPCAction(MITH2, 55, 15, 30, 0, -1));
 		actionsList.add(new NPCFaceTileAction(GUTHIX, 14, 24, -1));
 		actionsList.add(new NPCFaceTileAction(MITH, 14, 24, -1));
 		actionsList.add(new NPCFaceTileAction(MITH2, 14, 24, -1));
 		actionsList.add(new PosCameraAction(14, 21, 3000, 1, 1, -1));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Train up your slayer to access the coolest monsters-", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Train up your slayer to access the coolest monsters-", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Train up your slayer to access the coolest monsters-", 1));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "-and challenge yourself to your limits.", 2));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "-and challenge yourself to your limits.", 2));
 		actionsList.add(new NPCForceTalkAction(GUTHIX, "-and challenge yourself to your limits.", 1));
 	//Pest Control
        actionsList.add(new ConstructMapAction(330, 320, 20, 6));
        actionsList.add(new PlayerTransformAction(6666, -1));
 		actionsList.add(new MovePlayerAction(16, 39, 0, Player.TELE_MOVE_TYPE,0));
 		actionsList.add(new PosCameraAction(16, 14, 4000, -1));
 		actionsList.add(new LookCameraAction(16, 24, 3000, -1));
 		actionsList.add(new CreateNPCAction(GUTHIX, 8008, 16, 32, 0, -1));
 		actionsList.add(new CreateNPCAction(SPLAT, 3731, 15, 32, 0, -1));
 		actionsList.add(new CreateNPCAction(BRAWL, 3776, 17, 32, 0, -1));
 		actionsList.add(new CreateNPCAction(TORCH, 3761, 16, 33, 0, -1));
 		actionsList.add(new NPCFaceTileAction(GUTHIX, 16, 30, -1));
 		actionsList.add(new NPCFaceTileAction(SPLAT, 15, 30, -1));
 		actionsList.add(new NPCFaceTileAction(BRAWL, 17, 30, -1));
 		actionsList.add(new NPCFaceTileAction(TORCH, 16, 30, -1));
 		actionsList.add(new PosCameraAction(16, 24, 3000, 1, 1, -1));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "There are many minigames that bring the community together such as Pest Control.", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "There are many minigames that bring the community together such as Pest Control.", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "There are many minigames that bring the community together such as Pest Control.", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "There are many minigames that bring the community together such as Pest Control.", 1));
      //Soul Wars
        actionsList.add(new ConstructMapAction(234, 393, 10, 6));
        actionsList.add(new PlayerTransformAction(6666, -1));
 		actionsList.add(new MovePlayerAction(18, 39, 0, Player.TELE_MOVE_TYPE,0));
 		actionsList.add(new PosCameraAction(18, 18, 4000, -1));
 		actionsList.add(new LookCameraAction(18, 23, 3000, -1));
 		actionsList.add(new CreateNPCAction(GUTHIX, 8008, 18, 32, 0, -1));
 		actionsList.add(new NPCFaceTileAction(GUTHIX, 18, 0, -1));
 		actionsList.add(new PosCameraAction(18, 23, 3000, 1, 1, -1));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "We have all the classic team minigames that people love,-", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "We have all the classic team minigames that people love,-", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "We have all the classic team minigames that people love,-", 1));
        actionsList.add(new NPCAnimationAction(GUTHIX, new Animation(5845), -1));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "-so hop in on the fun!", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "-so hop in on the fun!", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "-so hop in on the fun!", 1));
       //Back to Guthix
        actionsList.add(new ConstructMapAction(360, 1206, 10, 6));
        actionsList.add(new PlayerTransformAction(-1, -1));
 		actionsList.add(new MovePlayerAction(28, 37, 0, Player.TELE_MOVE_TYPE,0));
 		actionsList.add(new PosCameraAction(26, 50, 4000, -1));
 		actionsList.add(new LookCameraAction(28, 40, 3000, -1));
 		actionsList.add(new CreateNPCAction(GUTHIX, 8008, 28, 36, 0, -1));
 		actionsList.add(new NPCFaceTileAction(GUTHIX, 28, 100, -1));
 		actionsList.add(new PosCameraAction(28, 45, 3000, 1, 1, -1));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Now if you are wanting to know ways to train or make money in any skill,-", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Now if you are wanting to know ways to train or make money in any skill,-", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Now if you are wanting to know ways to train or make money in any skill,-", 1));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "-use your crystal teleport.", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "-use your crystal teleport.", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "-use your crystal teleport.", 3));
        actionsList.add(new PosCameraAction(28, 42, 2000, 100, 100, -1));
        actionsList.add(new LookCameraAction(28, 40, 2000, -1));
        actionsList.add(new PlayerForceTalkAction("How do I get a crystal teleport?", 2));
        actionsList.add(new PlayerForceTalkAction("How do I get a crystal teleport?", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Simply open up your quest tab and click Teleports!", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Simply open up your quest tab and click Teleports!", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "A crystal teleport should then appear in your inventory.", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "A crystal teleport should then appear in your inventory.", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Click to activate it.", 3));
 		actionsList.add(new PlayerForceTalkAction("Then what do I do?", 2));
        actionsList.add(new PlayerForceTalkAction("Then what do I do?", 1));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "You can navigate through the menu,-", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "You can navigate through the menu,-", 2)); 
        actionsList.add(new NPCForceTalkAction(GUTHIX, "-we have a lot of options.", 2)); 
        actionsList.add(new NPCForceTalkAction(GUTHIX, "-we have a lot of options.", 1));
 		actionsList.add(new PlayerForceTalkAction("Now how do I read the guides on skills?", 2));
        actionsList.add(new PlayerForceTalkAction("Now how do I read the guides on skills?", 1));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Click the Skilling option in the crystal teleport-", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Click the Skilling option in the crystal teleport-", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "-and an interface should pop up showing every skill.", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "-and an interface should pop up showing every skill.", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "-and an interface should pop up showing every skill.", 1));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Simply select the skill you desire,-", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Simply select the skill you desire,-", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "-and another interface should pop up.", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "-and another interface should pop up.", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "This interface will give you information about how to train the skill,-", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "This interface will give you information about how to train the skill,-", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "-and how to make money through it.", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "-and how to make money through it.", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "You can also access the shop for the skill through the interface.", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "You can also access the shop for the skill through the interface.", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "You can also access the shop for the skill through the interface.", 1));
        actionsList.add(new PlayerForceTalkAction("Now how do I reach good training locations for the skill?", 2));
        actionsList.add(new PlayerForceTalkAction("Now how do I reach good training locations for the skill?", 1));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "That same interface will have 10 of the most popular teleports relating to that skill.", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "That same interface will have 10 of the most popular teleports relating to that skill.", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "That same interface will have 10 of the most popular teleports relating to that skill.", 1));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Simply click the location you want to go to,-", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Simply click the location you want to go to,-", 1));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "-and it will bring you there.", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "-and it will bring you there.", 2));
        actionsList.add(new PlayerForceTalkAction("What if I want to do some PvM?", 2));
        actionsList.add(new PlayerForceTalkAction("What if I want to do some PvM?", 1));
        actionsList.add(new PlayerForceTalkAction("What do I click since this is for skills?", 2));
        actionsList.add(new PlayerForceTalkAction("What do I click since this is for skills?", 1));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Instead of clicking Skilling, click Monsters and there will be a whole new sweet interface-", 2)); 
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Instead of clicking Skilling, click Monsters and there will be a whole new sweet interface-", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Instead of clicking Skilling, click Monsters and there will be a whole new sweet interface-", 1));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "-that displays the bosses, training locations, dungeons,-", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "-that displays the bosses, training locations, dungeons,-", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "-and more sorted into level groups.", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "-and more sorted into level groups.", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "We have the most locations that any server has out there.", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "We have the most locations that any server has out there.", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "We have the most locations that any server has out there.", 1));
        actionsList.add(new PlayerAnimationAction(new Animation (862), -1));
        actionsList.add(new PlayerForceTalkAction("Wow, thanks!", 2));
        actionsList.add(new PlayerForceTalkAction("Wow, thanks!", 1));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "No problem!", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Now, once you are ready to go into the world, enter that portal..", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Now, once you are ready to go into the world, enter that portal..", 2));
        actionsList.add(new NPCForceTalkAction(GUTHIX, "Now, once you are ready to go into the world, enter that portal..", 1));
        actionsList.add(new PlayerAnimationAction(new Animation (863), -1));
        actionsList.add(new PlayerForceTalkAction("Okay, bye!", 2));
        actionsList.add(new PlayerForceTalkAction("Okay, bye!", 1));
        return actionsList.toArray(new CutsceneAction[actionsList.size()]); 
    }
}
