package com.rs.game.player.content;

	import com.rs.Settings;
	import com.rs.game.player.Player;
	import com.rs.game.player.content.Magic;
	import com.rs.game.Graphics;
	import com.rs.game.WorldTile;


	/**
	*
	* @author Multak
	*/
	public class TeletoRequest {	
		
public void openTeleRequest (Player player) {
	player.getInterfaceManager().sendInterface(1008);;
}
public static void handleButtons(Player player, int componentId) {

	if (componentId == 29) {//Accept
	
	return;
	}

	else if (componentId == 28) {//Decline
	
	return;
	}

	else if (componentId == 30) {//Ignore
	
	}
	}	
}
