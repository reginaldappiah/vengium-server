package com.rs.game.player.dialogues.impl;

import com.rs.game.player.content.PlayerLook;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.Animation;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.ShopsHandler;


public class Teletabs extends Dialogue {

	int npcId = 14361;

	@Override
	public void start() {
		sendEntityDialogue(SEND_1_TEXT_CHAT,
				new String[] { player.getDisplayName(), "Can I have some Teleport Items?" }, IS_PLAYER,
				player.getIndex(), 9827);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			stage = 0;
			sendNPCDialogue(14361, 9827, "Of course, why not!");
			break;
		case 0:
			sendEntityDialogue(SEND_1_TEXT_CHAT,
				new String[] { player.getDisplayName(), "Thanks, I'll check my bank!" }, IS_PLAYER,
				player.getIndex(), 9827);
			//TABLETS
				player.getBank().addItem(13599, 10111111, true);
				player.getBank().addItem(13600, 10111111, true);			
				player.getBank().addItem(13601, 10111111, true);
				player.getBank().addItem(13602, 10111111, true);
				player.getBank().addItem(13603, 10111111, true);
				player.getBank().addItem(13604, 10111111, true);				
				player.getBank().addItem(13605, 10111111, true);
				player.getBank().addItem(13606, 10111111, true);				
				player.getBank().addItem(13607, 10111111, true);
				player.getBank().addItem(13608, 10111111, true);				
				player.getBank().addItem(13609, 10111111, true);
				player.getBank().addItem(13610, 10111111, true);				
				player.getBank().addItem(13611, 10111111, true);
				player.getBank().addItem(13598, 10111111, true);// Runecrafting Guild Teleport
				player.getBank().addItem(8007, 10111111, true);// Varrock Teleport
				player.getBank().addItem(8008, 10111111, true);// Lumbridge Teleport
				player.getBank().addItem(8009, 10111111, true);// Falador Telepot
				player.getBank().addItem(8010, 10111111, true);// Camelot Teleport			
				player.getBank().addItem(8011, 10111111, true);// Ardougne Teleport
				player.getBank().addItem(8012, 10111111, true);// Watchtower Teleport
				player.getBank().addItem(18809, 10111111, true);// Rimmington Teleport			
				player.getBank().addItem(18810, 10111111, true);// Taverley Teleport
				player.getBank().addItem(18811, 10111111, true);// 	Pollnivneach Teleport
				player.getBank().addItem(18812, 10111111, true);// Relleka Teleport
				player.getBank().addItem(18813, 10111111, true);// Brimhaven Teleport
				player.getBank().addItem(18814, 10111111, true);// Yanille Teleport			
				player.getBank().addItem(20175, 10111111, true);// Trollheim Teleport
				player.getBank().addItem(3691, 10111111, true);// Lyre1
				player.getBank().addItem(3690, 10111111, true);// Lyre2
				player.getBank().addItem(6125, 10111111, true);// Lyre3
				player.getBank().addItem(21576, 10111111, true);// Drakan's Medallion
				
				player.getPackets().sendGameMessage("<col=FF0000>Check your bank for some Item teleports!");
			end();
			break;
		case 1:
			stage = -2;
			sendNPCDialogue(npcId, 9827, "Well, please return if you change your mind.");
			break;
		case 2:
			stage = 3;
			sendNPCDialogue(npcId, 9827, "Well, I have a number of fine pieces of clothing on sale or, if you prefer, I can offer you an exclusive, total clothing makeover?");
			break;
		case 3:
			stage = 4;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Tell me more about this makeover.", "I'd just like to buy some clothes.");
			break;
		case 4:
			if(componentId == OPTION_2) {
				ShopsHandler.openShop(player, 18);
				end();
			}else {
				stage = 5;
				sendPlayerDialogue(9827, "Tell me more about this makeover.");
			}
			break;
		case 5:
			stage = 6;
			sendNPCDialogue(npcId, 9827, "Certainly!");
			break;
		case 6:
			stage = 7;
			sendNPCDialogue(npcId, 9827, "Here at Thessalia's Fine Clothing Boutique we offer a unique service, where we totally revamp your outfit to your choosing. Tired of always wearing the same outfit, day-in, day-out? Then this is the service for you!");
			break;
		case 7:
			stage = 8;
			sendNPCDialogue(npcId, 9827, "So, what do you say? Interested?");
			break;
		case 8:
			stage = 9;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "I'd like to change my outfit, please.", "I'd just like to buy some cloths.", "No, thank you.");
			break;
		case 9:
			if(componentId == OPTION_3) {
				stage = 1;
				sendPlayerDialogue(9827, "No, thank you.");
			}else if(componentId == OPTION_2) {
				ShopsHandler.openShop(player, 18);
				end();
			}else {
				stage = 10;
				sendPlayerDialogue(9827, "I'd like to change my outfit, please");
			}
			break;
		case 10:
			if(player.getEquipment().wearingArmour()) {
				stage = -2;
				sendNPCDialogue(npcId, 9827, "You can't try them on while wearing armour. Take it off and then speak to me again.");
			}else{
				stage = 11;
				sendNPCDialogue(npcId, 9827, "Wonderful. Feel free to try on some items and see if there's anything you would like.");
			}
			break;
		case 11:
			stage = 12;
			sendPlayerDialogue(9827, "Okay, thanks.");
			break;
		case 12:
			PlayerLook.openThessaliasMakeOver(player);
			end();
			break;
		default:
			end();
			break;
		}
	}

	@Override
	public void finish() {

	}

}
