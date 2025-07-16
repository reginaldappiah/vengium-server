package com.rs.game.player.dialogues.impl.homenpcs;

import com.rs.game.player.Player;
import com.rs.game.player.content.interfaces.DTWoodcuttingTeleports;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.ShopsHandler;

public class WoodcuttingTutor extends Dialogue {

	@Override
	public void start() {
		stage = 0;
		sendOptionsDialogue("What do you need?", "<col=F3FF0D>Shop", "<col=F3FF0D>Teleports", "<img=20><col=FF0000>Learn About Woodcutting", "<col=F3FF0D>Nevermind...");
		
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 0) {
			if (componentId == OPTION_1) {
				ShopsHandler.openShop(player, 167);
				end();
				}
				if (componentId == OPTION_2) {
				DTWoodcuttingTeleports.sendOptions(player);
				end();
				}
				if (componentId == OPTION_3) {
				sendDialogue("On Vengium, woodcutting is an essential skill for",
						"construction, firemaking, fletching, and more.",
						"You can go ahead and use the teleports to find your", 
						"desired location. Use the bank desposits for quick storage.");
				stage = 1;
				}
				if (componentId == OPTION_4) {
				end();
				}
			} 
		
			else if (stage == 1) {
			end();
			} 
		}
		
	

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		
	}

	
}
