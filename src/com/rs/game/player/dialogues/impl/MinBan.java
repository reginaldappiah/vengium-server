package com.rs.game.player.dialogues.impl;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;

public class MinBan extends Dialogue {
	
	private int playerIndex;
	
	@Override
    public void start() {
		playerIndex = (Integer) parameters[0];
	    Player target = World.getPlayers().get(playerIndex);
        sendOptionsDialogue("Minor Ban Category: Player: "+target.getDisplayName()+"", "5 Minute Ban",
                "15 Minute Ban", "30 Minute Ban", "1 Hour Ban", "Minor Categories...");
        stage = 1;
    }
 
    @Override
    public void run(int interfaceId, int componentId) {
         if (stage == 1) {
			if (componentId == OPTION_1) {
			Ban5Mins();
			} 
			else if (componentId == OPTION_2) {
			Ban15Mins();
			}
			else if (componentId == OPTION_3) {
			Ban30Mins();
			}	
			else if (componentId == OPTION_4) {
			Ban1Hour();
			} 
			else if (componentId == OPTION_5) {
			player.getDialogueManager().startDialogue("OffMin");
			}
		}
	}
    
    public void Ban5Mins() {
        Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't ban an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to ban you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setBanned(Utils.currentTimeMillis() + (1 * 30 * 10 * 1000));
        target.forceLogout();
        player.getPackets().sendGameMessage("You have banned " + target.getDisplayName()+" for 5 minutes.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been banned for 5 minutes by "+player.getDisplayName()+"", true);
    }
    
    public void Ban15Mins() {
    	 Player target = World.getPlayers().get(playerIndex);
         if (target.getRights() == 4) {
             player.sendMessage("You can't ban an administrator.");
             target.sendMessage(""+player.getDisplayName()+" has attempted to ban you.");
             return;
         }
         
         SerializableFilesManager.savePlayer(target);
         target.setBanned(Utils.currentTimeMillis() + (1 * 30 * 30 * 1000));
         target.forceLogout();
         player.getPackets().sendGameMessage("You have banned " + target.getDisplayName()+" for 15 minutes.");
         World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been banned for 15 minutes by "+player.getDisplayName()+"", true);
    }
    
    public void Ban30Mins() {
    	 Player target = World.getPlayers().get(playerIndex);
         if (target.getRights() == 4) {
             player.sendMessage("You can't ban an administrator.");
             target.sendMessage(""+player.getDisplayName()+" has attempted to ban you.");
             return;
         }
         
         SerializableFilesManager.savePlayer(target);
         target.setBanned(Utils.currentTimeMillis() + (1 * 30 * 60 * 1000));
         target.forceLogout();
         player.getPackets().sendGameMessage("You have banned " + target.getDisplayName()+" for 30 minutes.");
         World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been banned for 30 minutes by "+player.getDisplayName()+"", true);
    }
    
    public void Ban1Hour() {
    	Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't ban an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to ban you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setBanned(Utils.currentTimeMillis() + (1 * 60 * 60 * 1000));
        target.forceLogout();
        player.getPackets().sendGameMessage("You have banned " + target.getDisplayName()+" for 1 hour.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been banned for 1 hour by "+player.getDisplayName()+"", true);
    }
    
    @Override
    public void finish() {
        // TODO Auto-generated method stub
        
    }

}
  