package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;


public class Dungeoneering extends Dialogue {

	public Dungeoneering() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Pick a Dungeoneering Reward", "Chaotic Rapier (200K)",
				"Chaotic Maul (200K)", "Chaotic Staff (200K)", "Chaotic Crossbow (200K)",
				"Next Page");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				if (player.dungpoints >= 200000) {
				player.getInventory().addItem(18349, 1);
				player.dungpoints -= 200000;
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				end();
			    }else {
				player.getPackets().sendGameMessage("you dont have enough Dungeoneering points.");
				end();
				}
			} else if (componentId == OPTION_2) {
				if (player.dungpoints >= 200000) {
				player.getInventory().addItem(18353, 1);
				player.dungpoints -= 200000;
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				end();
			    }else {
				player.getPackets().sendGameMessage("you dont have enough Dungeoneering points.");
				end();
				}
			} else if (componentId == OPTION_3) {
				if (player.dungpoints >= 200000) {
				player.getInventory().addItem(18355, 1);
				player.dungpoints -= 200000;
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				end();
			    }else {
				player.getPackets().sendGameMessage("you dont have enough Dungeoneering points.");
				end();
				}
			} else if (componentId == OPTION_4) {
				if (player.dungpoints >= 200000) {
				player.getInventory().addItem(18357, 1);
				player.dungpoints -= 200000;
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				end();
			    }else {
				player.getPackets().sendGameMessage("you dont have enough Dungeoneering points.");
				end();
				}
			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("Pick a Dungeoneering Reward",
						"Chaotic Longsword(200k)", "Arcane Stream Necklace (50K)",
						"Ring of Vigour (75K)", "Farseer kiteshield (75K)", "Nevermind.");
				stage = 4;
			}
		} else if (stage == 4) {
			if (componentId == OPTION_1) {	
				if (player.dungpoints >= 200000) {
				player.getInventory().addItem(18351, 1);
				player.dungpoints -= 200000;
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				end();
			    }else {
				player.getPackets().sendGameMessage("you dont have enough Dungeoneering points.");
				end();
				}
			} else if (componentId == OPTION_2) {
				if (player.dungpoints >= 50000) {
				player.getInventory().addItem(18335, 1);
				player.dungpoints -= 50000;
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				end();
			    }else {
				player.getPackets().sendGameMessage("you dont have enough Dungeoneering points.");
				end();
				}
			} else if (componentId == OPTION_3) {
				if (player.dungpoints >= 75000) {
				player.getInventory().addItem(19669, 1);
				player.dungpoints -= 75000;
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				end();
			    }else {
				player.getPackets().sendGameMessage("you dont have enough Dungeoneering points.");
				end();
				}
			} else if (componentId == OPTION_4) {
				if (player.dungpoints >= 75000) {
				player.getInventory().addItem(18363, 1);
				player.dungpoints -= 75000;
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				end();
			    }else {
				player.getPackets().sendGameMessage("you dont have enough Dungeoneering points.");
				end();
				}
		    } else if (componentId == OPTION_5) {
		        end();
			}
		}
	}

	@Override
	public void finish() {
	}

}
