package com.rs.game.player.content.interfaces;

import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.player.Player;

public class Teleto {
	
	private Player player, target;

	public Teleto(Player player) {
		this.player = player; //player reference
	}
	
	public void sendTeleToRequest(Player target) {
	target.getTemporaryAttributtes().get("teleto_request_p");
	player.getInterfaceManager().sendInterface(1008);
	}
}
