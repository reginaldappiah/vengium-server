package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.item.Item;
import com.rs.game.player.dialogues.Dialogue;

public class QuickAccessories extends Dialogue {
	
	@Override
    public void start() {
        sendOptionsDialogue("Quick Accessories:", "<col=F8FF02>Teleport Items",
                "<col=F8FF02>Runes", "<col=F8FF02>Empty Inventory", "<col=F8FF02>Clear Bank", "Nevermind...");
        stage = 1;
    }
 
    @Override
    public void run(int interfaceId, int componentId) {
         if (stage == 1) {
			if (componentId == OPTION_1) {
			Settings.getTeleItems(player);
			end();
			} 
			else if (componentId == OPTION_2) {
			Settings.getRunes(player);
			end();
			}
			else if (componentId == OPTION_3) {
			EmptyInventory();
			end();
			}	
			else if (componentId == OPTION_4) {
			ClearBank();
			end();
			} 
			else if (componentId == OPTION_5) {
			end();
			}
		}
	}
    
    public void EmptyInventory() {
    	player.getInventory().reset();
    }
    public void ClearBank() {
    	for (Item item : player.getBank().getContainerCopy()) {
			if (item == null) {
				continue;
			}
			int[] slot = player.getBank().getItemSlot(item.getId());
			if (slot == null) {
				continue;
			}
			player.getBank().removeItem(slot, item.getAmount(), true, false);
		}
		player.getBank().collapse(0);
        player.getBank().collapse(1);
        player.getBank().collapse(2);
        player.getBank().collapse(3);
        player.getBank().collapse(4);
        player.getBank().collapse(5);
        player.getBank().collapse(6);
        player.getBank().collapse(7);
        player.getBank().collapse(8);
        player.getBank().collapse(9);
        player.closeInterfaces();
        player.getPackets().sendGameMessage("<col=00FF09>You have successfully cleared your bank!");;
    }
    
    @Override
    public void finish() {
        // TODO Auto-generated method stub
        
    }

}