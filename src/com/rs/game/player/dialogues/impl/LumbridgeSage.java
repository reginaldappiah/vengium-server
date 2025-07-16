package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.minigames.CastleWars;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class LumbridgeSage extends Dialogue {

	private int npcId; 
	
	@Override
	public void start() {
		if (Settings.ECONOMY) {
			player.getPackets().sendGameMessage("Lumbridge Sage is in no mood to talk to you.");
			end();
			return;
		}
		npcId = (Integer) parameters[0];
		sendEntityDialogue(SEND_2_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"Hello, I can teleport you to some cool spots on Vengium,",
						" would you like to?" }, IS_NPC, npcId, 9827);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { player.getDisplayName(), "Sure, why not." },
					IS_PLAYER, player.getIndex(), 9827);
			stage = 1;
		} else if (stage == 1) {
			sendOptionsDialogue("<col=FF0000>Where would you like to go?", "Whirlpool",
					"Fish Trawler Swim", "Underwater", "Electric Chair", "More options...");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) { //WHIRLPOOL
				player.getAppearence().setRenderEmote(-1);
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2512, 3511, 0));
			}
			else if (componentId == OPTION_2) { //FISH TRAWLER
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1953, 4825, 0));
				player.getAppearence().setRenderEmote(930);
			}
			else if (componentId == OPTION_3) { //UNDERWATER
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2967, 9483, 2));
				player.getAppearence().setRenderEmote(267);
			}
			else if (componentId == OPTION_4) { //ELECTRIC CHAIR
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1959, 5149, 0));
				player.getAppearence().setRenderEmote(-1);
			}
			else if (componentId == OPTION_5) {//MORE OPTIONS
				stage = 3;
				sendOptionsDialogue("<col=FF0000>Where would you like to go?",
						"Minecart", "Coffin", "Light Creatures",
						"Search Bush", "More Options...");
			}
		} else if (stage == 3) {
		if(componentId == OPTION_1) {// Minecart
			
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2909, 10172, 0));
			player.getAppearence().setRenderEmote(-1);
			end();
		}
		if(componentId == OPTION_2) {//Coffin
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3080, 9782, 0));
			player.getAppearence().setRenderEmote(-1);
			end();
		}
        	if(componentId == OPTION_3) {//Crop Centre
			player.getPackets().sendGameMessage("<col=FF0000>Click on the Clump of rocks to travel to the other side.");
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3228, 9526, 2));
			player.getAppearence().setRenderEmote(-1);
			end();
		}
		if(componentId == OPTION_4) {//Search Bushes
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2642, 3210, 0));
			player.getAppearence().setRenderEmote(-1);
			end();
		}
    	if(componentId == OPTION_5) {
		stage = 4;
		sendOptionsDialogue("<col=FF0000><shad=ff0000>Where would you like to go?",
				"Mos Le' Harmless", "Falador Grapple", "Pier Dive",
				"Yu'Buisk Strange Box", "More Options...");
    	}
		} else if (stage == 4) {
		if(componentId == OPTION_1) {// Mos Le' Harmless
			player.getPackets().sendGameMessage("<col=FF0000>Click the doors to choke on Tony Sixx's...");
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3787, 2821, 0));
			player.getAppearence().setRenderEmote(-1);
			end();
		}
		if(componentId == OPTION_2) {// Falador Gapple
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3006, 3396, 0));
			player.getAppearence().setRenderEmote(-1);
			end();
		}
        	if(componentId == OPTION_3) {//Seculeded Island
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3048, 2979, 0));
			player.getAppearence().setRenderEmote(-1);
			end();
		}
		if(componentId == OPTION_4) {//Yu Buisk Strange Box
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2194, 4260, 1));
			player.getAppearence().setRenderEmote(-1);
			end();
		}
    	if(componentId == OPTION_5) { // More options
		stage = 5;
		sendOptionsDialogue("<col=FF0000>Where would you like to go?",
				"Altar of Zaros", "Trollweiss Sledding", "Circus Tightrope",
				"Zanaris Crop Centre", "More Options...");
		
    		}
		} else if (stage == 5) {
		if(componentId == OPTION_1) {// Zaros Altar
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3182, 5713, 0));
			player.getAppearence().setRenderEmote(-1);
			
		}
		if(componentId == OPTION_2 && player.getEquipment().getWeaponId() != -1) {// Trollweiss Sledding
			player.stopAll();
			player.getPackets().sendGameMessage("<col=FF0000>You don't feel comfortable going sledding while holding an item.");
			}
		if(componentId == OPTION_2 && player.getEquipment().getWeaponId() == -1) {// Trollweiss Sledding
			CastleWars.setWeapon(player, new Item(4084, 1));
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2777, 3866, 0));
			
		}
        if(componentId == OPTION_3) {//Circus Balance
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3551, 5602, 2));
			player.getAppearence().setRenderEmote(330);
		}
		if(componentId == OPTION_4) {//Crop Centre
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2427, 4446, 0));
			player.getAppearence().setRenderEmote(-1);
		}
    	if(componentId == OPTION_5) { // More options
    		player.getAppearence().setRenderEmote(-1);
    		stage = 6;
    		sendOptionsDialogue("<col=FF0000>Where would you like to go?",
    				"Clan Citadel Theatre", "Singing Bowl", "Brimhaven Agility Matrix",
    				"Advanced Gnome Agility", "More Options...");
    		
        		}
    		} else if (stage == 6) {
    		if(componentId == OPTION_1) {// Citadel
    			player.getPackets().sendGameMessage("<col=FF0000>Use the Prop Machine's Make-backdrop option to change the backgrounds.");
    			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4875, 4028, 0));
    			player.getAppearence().setRenderEmote(-1);
    		}
    		if(componentId == OPTION_2) {//Singing Bowl
    			player.getPackets().sendGameMessage("<col=FF0000>Click on the singing bowl.");
    			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2388, 9814, 0));
    			player.getAppearence().setRenderEmote(-1);
    		}
            if(componentId == OPTION_3) {//Brimhaven Matrix
            	player.getPackets().sendGameMessage("<col=FF0000>Tag the Ticket Dispenser!");
            	Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2773, 9568, 3));
    			player.getAppearence().setRenderEmote(-1);
    		}
    		if(componentId == OPTION_4) {//Advanced Gnome Agility
    			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2473, 3418, 3));
    			player.getAppearence().setRenderEmote(-1);
    		}
        	if(componentId == OPTION_5) { // More options
        		player.getAppearence().setRenderEmote(-1);
        		stage = 7;
        		sendOptionsDialogue("<col=FF0000>Where would you like to go?",
        				"Magical Wheat", "Search Bushes", "Kethsi Agility Course",
        				"Green Screen", "Nevermind");
        		
            		}
        		} else if (stage == 7) {
        		if(componentId == OPTION_1) {// Magical Wheat
        			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2592, 4312, 0));
        			player.getAppearence().setRenderEmote(-1);
        		}
        		if(componentId == OPTION_2) {//Singing Bowl
        			player.getPackets().sendGameMessage("<col=FF0000>Click on the singing bowl.");
        			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2388, 9814, 0));
        			player.getAppearence().setRenderEmote(-1);
        		}
                if(componentId == OPTION_3) {//Brimhaven Matrix
                	player.getPackets().sendGameMessage("<col=FF0000>Tag the Ticket Dispenser!");
                	Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2773, 9568, 3));
        			player.getAppearence().setRenderEmote(-1);
        		}
        		if(componentId == OPTION_4) {//Advanced Gnome Agility
        			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2473, 3418, 3));
        			player.getAppearence().setRenderEmote(-1);
        		}
            	if(componentId == OPTION_5) { // More options
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2265, 4231, 1));
            		player.getAppearence().setRenderEmote(-1);
            		end();
    			}
    		}
    	}
	private void teleportPlayer(int x, int y, int z) {
		player.setNextWorldTile(new WorldTile(x, y, z));
		player.stopAll();
		player.getControlerManager().startControler("GodWars");
	}

	@Override
	public void finish() {

	}
}