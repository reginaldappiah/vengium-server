package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class SavedOutfits extends Dialogue {
	
	@Override
	public void start() {
		sendOptionsDialogue("Outfit Settings", "<col=0A9700>Outfit 1 Settings", "<col=0BAC00>Outfit 2 Settings", "<col=0DC100>Outfit 3 Settings", "<col=0FE600>Outfit 4 Settings", "Nevermind");
    	stage = 3;
    	
	}
	public void run(int interfaceId, int componentId) {
			if (stage == 3) {
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
				end();
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
				sendOptionsDialogue("Outfit Settings", "<col=0A9700>Outfit 1 Settings", "<col=0BAC00>Outfit 2 Settings", "<col=0DC100>Outfit 3 Settings", "<col=0FE600>Outfit 4 Settings", "Nevermind");
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
				sendOptionsDialogue("Outfit Settings", "<col=0A9700>Outfit 1 Settings", "<col=0BAC00>Outfit 2 Settings", "<col=0DC100>Outfit 3 Settings", "<col=0FE600>Outfit 4 Settings", "Nevermind");
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
				sendOptionsDialogue("Outfit Settings", "<col=0A9700>Outfit 1 Settings", "<col=0BAC00>Outfit 2 Settings", "<col=0DC100>Outfit 3 Settings", "<col=0FE600>Outfit 4 Settings", "Nevermind");
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
				sendOptionsDialogue("Outfit Settings", "<col=0A9700>Outfit 1 Settings", "<col=0BAC00>Outfit 2 Settings", "<col=0DC100>Outfit 3 Settings", "<col=0FE600>Outfit 4 Settings", "Nevermind");
		    	stage = 3;
			}
			}	
			}
			public void finish() {
			}
		}
		
