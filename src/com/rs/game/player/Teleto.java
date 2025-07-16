package com.rs.game.player;

import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.player.content.ItemConstants;
import com.rs.utils.EconomyPrices;
import com.rs.utils.ItemExamines;

public class Teleto {

	private Player player, target;
	private ItemsContainer<Item> items;
	private boolean tradeModified;
	private boolean accepted;

	public Teleto(Player player) {
		this.player = player; //player reference
	}

	/*
	 * called to both players
	 */
	public void cancelAccepted() {
		boolean canceled = false;
		if(accepted) {
			accepted = false;
			canceled = true;
		}
		if(target.getTeleto().accepted) {
			target.getTeleto().accepted = false;
			canceled = true;
		}
		
	}
	public boolean isTeled() {
		return target != null;
	}

	public void accept(boolean firstStage) {
		synchronized (this) {
			if(!isTeled())
				return;
			synchronized (target.getTeleto()) {
				if(target.getTeleto().accepted) {
				}else{
						player.setCloseInterfacesEvent(null);
						player.closeInterfaces();
					}
				accepted = true;
					return;
				
				}
		}
	}
}
