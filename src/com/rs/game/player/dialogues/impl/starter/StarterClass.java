package com.rs.game.player.dialogues.impl.starter;

import com.rs.Settings;
import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;

public class StarterClass extends Dialogue {

	@Override
	public void start() {
		sendDialogue("<col=FF0000><shad=FF0000>Let's get your account set up!");
		stage = -10;
		
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -10) {
			stage = -11;
				sendOptionsDialogue( "<col=FF0000><shad=FF0000>Choose your Vengium class:", "<col=FF0000><img=19> RSMVer", "<col=FF00000>Player");
	}	else if (stage == -11) {
			if (componentId == OPTION_1) {
				stage = -12;
				sendOptionsDialogue( "Choose your RSMVer class:", "Director", "Syncer/Helper", "Both");
			} else if (componentId == OPTION_2) {
				stage = -13;
				sendOptionsDialogue("Choose your player class:", "Normal Mode", "<col=FF0000>Ironman Mode");
			}
	} else if (stage == -12) { //ADD CREATION TABS
		if (componentId == OPTION_1) {// DIRECTOR
			player.setRights(2);
			Settings.getRSMVSet(player);
			Settings.getTeleItems(player);
			Settings.getGear(player); {
			}
			player.getInventory().addItem(1856, 1); //Guide Book
			player.getInventory().addItem(15484, 1); //Orb of Oculus
			player.getBank().addItem(15484, 100, true);  //Orb of Oculus
			player.getInventory().addItemMoneyPouch(995, 1000000000);// 1B
			player.starterstage = 3;
			for (int skill = 0; skill < 25; skill++) {
			player.getSkills().addXp(skill, 150000000);
			}
			player.getPackets().sendGameMessage("<img=20><col=FFFFFF><shad=FF00000>You are playing as an RSMV director!");
			
		} else if (componentId == OPTION_2) {// SYNCER
			player.setRights(2);
			Settings.getRSMVSet(player);
			Settings.getTeleItems(player);
			Settings.getGear(player); {
			}
			player.getInventory().addItem(1856, 1); //Guide Book
			player.getInventory().addItemMoneyPouch(995, 1000000000);// 1B
			player.starterstage = 3;
			for (int skill = 0; skill < 25; skill++) {
			player.getSkills().addXp(skill, 150000000);
			}
			player.getPackets().sendGameMessage("<img=20><col=FFFFFF><shad=FF00000>You are playing as an RSMV syncer/helper!");
			
		} else if (componentId == OPTION_3) {// BOTH
			player.setRights(2);
			Settings.getRSMVSet(player);
			Settings.getTeleItems(player);
			Settings.getGear(player); {
			}
			player.getInventory().addItem(1856, 1); //Guide Book
			player.getInventory().addItem(15484, 1); //Orb of Oculus
			player.getBank().addItem(15484, 100, true);  //Orb of Oculus
			player.getInventory().addItemMoneyPouch(995, 1000000000);// 1B
			player.starterstage = 3;
			for (int skill = 0; skill < 25; skill++) {
			player.getSkills().addXp(skill, 150000000);
			}
			player.getPackets().sendGameMessage("<img=20><col=FFFFFF><shad=FF00000>You are playing as an RSMVer!");
		}
		player.starterstage = 3;
		player.getInterfaceManager().sendInterfaces();
		player.closeInterfaces();
		player.unlock();
		player.sm("<img=20><col=FFFFFF><shad=FF00000>You may now enter Vengium through this portal.");
		player.getHintIconsManager().addHintIcon(3091, 3106, 0, 100, 0, 0, -1, false);
	
	}	else if (stage == -13) { // NORMAL
		if (componentId == OPTION_1) {
			player.getSkills().setXp(1, Skills.getXPForLevel(75));
			player.getSkills().set(2, 90);
			player.getSkills().setXp(2, Skills.getXPForLevel(90));
			player.getSkills().set(3, 99);
			player.getSkills().setXp(3, Skills.getXPForLevel(99));
			player.getSkills().set(4, 90);
			player.getSkills().setXp(4, Skills.getXPForLevel(90));
			player.getSkills().set(5, 70);
			player.getSkills().setXp(5, Skills.getXPForLevel(70));
			player.getSkills().set(6, 94);
			player.getSkills().setXp(6, Skills.getXPForLevel(94));
			player.starterstage = 3;
			player.getPackets().sendGameMessage("<img=20><col=FFFFFF><shad=FF00000>You are playing as a normal player!");
		}
		player.gameMode = 1;
		player.isPlayer();
		player.starterstage = 3;
		
		player.getInterfaceManager().sendInterfaces();
		player.closeInterfaces();
		player.unlock();
		player.sm("<img=20><col=FFFFFF><shad=FF00000>You may now enter Vengium through this portal.");
		player.getHintIconsManager().addHintIcon(3091, 3106, 0, 100, 0, 0, -1, false);
	}
		
	}

	@Override
	public void finish() {

	}

}
