package com.rs.game.player.dialogues.impl;

import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.content.interfaces.AccountInformation;
import com.rs.game.player.dialogues.Dialogue;

public class InterfaceAccount extends Dialogue {

	@Override
	public void start() {
		    sendOptionsDialogue("Primary Account Settings", "<col=0A9700>Outfits", "<col=002FFF>Telelock", "<col=ff0000>Saved Locations<col=ffffff><shad=ffffff>(UNDER CONSTRUCTION)", "<col=FFF700>Saved Remotes<col=ffffff><shad=ffffff>(UNDER CONSTRUCTION)", "More Options...<col=ffffff><shad=ffffff>(LOCKED)");
			stage = 1;
	}
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {//MAIN MENU OPTIONS: OUTFIT
	    if(componentId == OPTION_1) {
	    	//end();
	    	sendOptionsDialogue("Outfit Settings", "<col=0A9700>Outfit 1 Settings", "<col=0BAC00>Outfit 2 Settings", "<col=0DC100>Outfit 3 Settings", "<col=0FE600>Outfit 4 Settings", "Main Menu.");
	    	stage = 3;
		}
		if(componentId == OPTION_2) {//MAIN MENU OPTION: TELELOCK
			sendOptionsDialogue("Telelock Toggle", "<col=0A9700>ENABLE TELELOCK", "<col=ff0000>DISABLE TELELOCK", "<col=0DC100>Check Telelock Status", "Main Menu.");
			stage = 4;
		}
        if(componentId == OPTION_3) {
        	end();
        	/*sendOptionsDialogue("Saved Locations", "<col=840303>Location 1 Settings", "<col=970303>Location 2 Settings", "<col=C00000>Location 3 Settings", "<col=FF0000>Location 4 Settings", "Main Menu.");
	    	stage = 6;*/
	    }
		if(componentId == OPTION_4) {
			//Magic.sendDrakanTeleport(player, 0, 0, new WorldTile(3638, 3365, 0));
			end();
		}
		if(componentId == OPTION_5) {
			end();
			/*sendOptionsDialogue("Primary Account Settings", "Saved AnimGFXs", "Previous Options...", "Nevermind.");
	    	stage = 2;*/
		}
		
		
		/*
		 * TELELOCK
		 */

		} else if (stage == 4) {
			if(componentId == OPTION_1) {//Telelock Activate
				player.setNextGraphics(new Graphics(7));
				player.sm("<img=15><col=13E700>You have activated your telelock. Teleports no longer initiate.");
		    	player.activateTelelock();
		    	end();
			}
			if(componentId == OPTION_2) {//Telelock Deactivate
				player.setNextGraphics(new Graphics(7));
				player.sm("<img=15><col=ff0000>You have deactivated your telelock. Teleport will initiate.");
				player.deactivateTelelock();
		    	end();
			}
			if(componentId == OPTION_3) {//Change to ACCOUNT INFO INTERFACE!
		    	AccountInformation.sendInterface(player);
		    	end();
			}
			if(componentId == OPTION_4) {//MAIN MENU
				sendOptionsDialogue("Primary Account Settings", "<col=0A9700><shad=0A9700>Outfits", "<col=001EFF><shad=001EFF>Telelock", "<col=840303>Saved Locations", "Saved Anims - Coming Soon", "Nevermind");
				stage = 1;
			}
			
			
		/*
		 * SAVED LOCATIONS
		 */
		
		} else if (stage == 6) {
			if(componentId == OPTION_1) {//LOCATION SETTINGS 1
		    	sendOptionsDialogue("Location 1", "<col=15FF00>Save Location", "<col=EBFF00>Tele to Location", "Remove Location", "Locations Main Menu.");
		    	stage = 11;
			}
			if(componentId == OPTION_2) {//LOCATION SETTINGS 2
		    	sendOptionsDialogue("Location 2", "<col=15FF00>Save Location", "<col=EBFF00>Tele to Location", "Remove Location", "Locations Main Menu.");
		    	stage = 21;
			}
			if(componentId == OPTION_3) {//LOCATION SETTINGS 3
		    	sendOptionsDialogue("Location 3", "<col=15FF00>Save Location", "<col=EBFF00>Tele to Location", "Remove Location", "Locations Main Menu.");
		    	stage = 31;
			}
			if(componentId == OPTION_4) {//LOCATION SETTINGS 4
		    	sendOptionsDialogue("Location 4", "<col=15FF00>Save Location", "<col=EBFF00>Tele to Location", "Remove Location", "Locations Main Menu.");
		    	stage = 41;
			} 
			if(componentId == OPTION_5) {
				sendOptionsDialogue("Saved Locations", "<col=840303>Location 1 Settings", "<col=970303>Location 2 Settings", "<col=C00000>Location 3 Settings", "<col=FF0000>Location 4 Settings", "Main Menu.");
		    	stage = 6;
			}
			
		} else if (stage == 11) {
			if(componentId == OPTION_1) {
				player.getSavedLocations().saveLocation1(player);
				end();
			}
			if(componentId == OPTION_2) {
				player.getSavedLocations().teletoLocation1(player);
				end();
		    	
			}
			if(componentId == OPTION_3) {
				player.getSavedLocations().removeLocation1(player);
				end();
			}
			if(componentId == OPTION_4) {
				sendOptionsDialogue("Saved Locations", "<col=840303>Location 1 Settings", "<col=970303>Location 2 Settings", "<col=C00000>Location 3 Settings", "<col=FF0000>Location 4 Settings", "Main Menu.");
		    	stage = 6;
			}
		
		/*
		 * OUTFITS
		 */
			
		} else if (stage == 3) {
			if(componentId == OPTION_1) {
		    	sendOptionsDialogue("Outfit 1", "<col=15FF00>Save Outfit", "<col=EBFF00>Wear Outfit", "<col=FF0000>Delete Outfit", "Outfit Main Menu.");
		    	stage = 10;
			}
			if(componentId == OPTION_2) {
		    	sendOptionsDialogue("Outfit 2", "<col=15FF00>Save Outfit", "<col=EBFF00>Wear Outfit", "<col=FF0000>Delete Outfit", "Outfit Main Menu.");
		    	stage = 20;
			}
			if(componentId == OPTION_3) {
		    	sendOptionsDialogue("Outfit 3", "<col=15FF00>Save Outfit", "<col=EBFF00>Wear Outfit", "<col=FF0000>Delete Outfit", "Outfit Main Menu.");
		    	stage = 30;
			}
			if(componentId == OPTION_4) {
		    	sendOptionsDialogue("Outfit 4", "<col=15FF00>Save Outfit", "<col=EBFF00>Wear Outfit", "<col=FF0000>Delete Outfit", "Outfit Main Menu.");
		    	stage = 40;
			} 
			if(componentId == OPTION_5) {
				sendOptionsDialogue("Outfit Settings", "<col=0A9700>Outfit 1 Settings", "<col=0BAC00>Outfit 2 Settings", "<col=0DC100>Outfit 3 Settings", "<col=0FE600>Outfit 4 Settings", "Main Menu.");
		    	stage = 3;
			}
		
		} else if (stage == 10) {// OUTFIT 1
			if(componentId == OPTION_1) {
				player.getSavedOutfits().saveOutfit1(player);
				end();
			}
			if(componentId == OPTION_2) {
				player.getSavedOutfits().wearOutfit1(player);
				end();
			}
			if(componentId == OPTION_3) {
				player.getSavedOutfits().deleteOutfit1(player);
				end();
			}
			if(componentId == OPTION_4) {
				sendOptionsDialogue("Outfit Settings", "<col=0A9700>Outfit 1 Settings", "<col=0BAC00>Outfit 2 Settings", "<col=0DC100>Outfit 3 Settings", "<col=0FE600>Outfit 4 Settings", "Main Menu.");
		    	stage = 3;
			}
		} else if (stage == 20) {// OUTFIT 2
			if(componentId == OPTION_1) {
				player.getSavedOutfits().saveOutfit2(player);
				end();
			}
			if(componentId == OPTION_2) {
				player.getSavedOutfits().wearOutfit2(player);
				end();
			}
			if(componentId == OPTION_3) {
				player.getSavedOutfits().deleteOutfit2(player);
				end();
			}
			if(componentId == OPTION_4) {
				sendOptionsDialogue("Outfit Settings", "<col=0A9700>Outfit 1 Settings", "<col=0BAC00>Outfit 2 Settings", "<col=0DC100>Outfit 3 Settings", "<col=0FE600>Outfit 4 Settings", "Main Menu.");
		    	stage = 3;
			}
		} else if (stage == 30) {// OUTFIT 3
			if(componentId == OPTION_1) {
				player.getSavedOutfits().saveOutfit3(player);
				end();
			}
			if(componentId == OPTION_2) {
				player.getSavedOutfits().wearOutfit3(player);
				end();
			}
			if(componentId == OPTION_3) {
				player.getSavedOutfits().deleteOutfit3(player);
				end();
			}
			if(componentId == OPTION_4) {
				sendOptionsDialogue("Outfit Settings", "<col=0A9700>Outfit 1 Settings", "<col=0BAC00>Outfit 2 Settings", "<col=0DC100>Outfit 3 Settings", "<col=0FE600>Outfit 4 Settings", "Main Menu.");
		    	stage = 3;
			}
		} else if (stage == 40) {// OUTFIT 4
			if(componentId == OPTION_1) {
				player.getSavedOutfits().saveOutfit4(player);
				end();
			}
			if(componentId == OPTION_2) {
				player.getSavedOutfits().wearOutfit4(player);
				end();
			}
			if(componentId == OPTION_3) {
				player.getSavedOutfits().deleteOutfit4(player);
				end();
			}
			if(componentId == OPTION_4) {
				sendOptionsDialogue("Outfit Settings", "<col=0A9700>Outfit 1 Settings", "<col=0BAC00>Outfit 2 Settings", "<col=0DC100>Outfit 3 Settings", "<col=0FE600>Outfit 4 Settings", "Main Menu.");
		    	stage = 3;
			}
			}
			}
			@Override
			public void finish() {
			}
		}
