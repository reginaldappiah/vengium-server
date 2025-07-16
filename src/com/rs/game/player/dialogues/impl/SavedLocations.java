package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class SavedLocations extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Location Settings", "<col=840303>Location 1 Settings", "<col=970303>Location 2 Settings", "<col=C00000>Location 3 Settings", "<col=FF0000>Location 4 Settings", "Nevermind");
    	stage = 6;
    	
	}
	public void run(int interfaceId, int componentId) {
		if (stage == 6) {
		if(componentId == OPTION_1) {//LOCATION SETTINGS 1
		    	sendOptionsDialogue("Location 1", "<col=15FF00>Save Location", "<col=EBFF00>Tele to Location", "<col=FF0000>Remove Location", "Locations Main Menu.");
		    	stage = 11;
			}
			if(componentId == OPTION_2) {//LOCATION SETTINGS 2
		    	sendOptionsDialogue("Location 2", "<col=15FF00>Save Location", "<col=EBFF00>Tele to Location", "<col=FF0000>Remove Location", "Locations Main Menu.");
		    	stage = 21;
			}
			if(componentId == OPTION_3) {//LOCATION SETTINGS 3
		    	sendOptionsDialogue("Location 3", "<col=15FF00>Save Location", "<col=EBFF00>Tele to Location", "<col=FF0000>Remove Location", "Locations Main Menu.");
		    	stage = 31;
			}
			if(componentId == OPTION_4) {//LOCATION SETTINGS 4
		    	sendOptionsDialogue("Location 4", "<col=15FF00>Save Location", "<col=EBFF00>Tele to Location", "<col=FF0000>Remove Location", "Locations Main Menu.");
		    	stage = 41;
			} 
			if(componentId == OPTION_5) {
				end();
			}
			
		} else if (stage == 11) { // 1
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
		} else if (stage == 21) {// 2
			if(componentId == OPTION_1) {
				player.getSavedLocations().saveLocation2(player);
				end();
			}
			if(componentId == OPTION_2) {
				player.getSavedLocations().teletoLocation2(player);
				end();
		    	
			}
			if(componentId == OPTION_3) {
				player.getSavedLocations().removeLocation2(player);
				end();
			}
			if(componentId == OPTION_4) {
				sendOptionsDialogue("Saved Locations", "<col=840303>Location 1 Settings", "<col=970303>Location 2 Settings", "<col=C00000>Location 3 Settings", "<col=FF0000>Location 4 Settings", "Main Menu.");
		    	stage = 6;
			}
		} else if (stage == 31) {// 3
			if(componentId == OPTION_1) {
				player.getSavedLocations().saveLocation3(player);
				end();
			}
			if(componentId == OPTION_2) {
				player.getSavedLocations().teletoLocation3(player);
				end();
		    	
			}
			if(componentId == OPTION_3) {
				player.getSavedLocations().removeLocation3(player);
				end();
			}
			if(componentId == OPTION_4) {
				sendOptionsDialogue("Saved Locations", "<col=840303>Location 1 Settings", "<col=970303>Location 2 Settings", "<col=C00000>Location 3 Settings", "<col=FF0000>Location 4 Settings", "Main Menu.");
		    	stage = 6;
			}
		} else if (stage == 41) {// 4
			if(componentId == OPTION_1) {
				player.getSavedLocations().saveLocation4(player);
				end();
			}
			if(componentId == OPTION_2) {
				player.getSavedLocations().teletoLocation4(player);
				end();
		    	
			}
			if(componentId == OPTION_3) {
				player.getSavedLocations().removeLocation4(player);
				end();
			}
			if(componentId == OPTION_4) {
				sendOptionsDialogue("Saved Locations", "<col=840303>Location 1 Settings", "<col=970303>Location 2 Settings", "<col=C00000>Location 3 Settings", "<col=FF0000>Location 4 Settings", "Main Menu.");
		    	stage = 6;
			}
			}
			}
			public void finish() {
			}
		}
