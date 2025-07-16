package com.rs.game.player.dialogues.impl;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;

public class MinJail extends Dialogue {
	
	private int playerIndex;
	
	@Override
    public void start() {
		playerIndex = (Integer) parameters[0];
	    Player target = World.getPlayers().get(playerIndex);
        sendOptionsDialogue("Minor Jail Category: Player: "+target.getDisplayName()+"", "5 Minute Jail",
                "15 Minute Jail", "30 Minute Jail", "1 Hour Jail", "Minor Categories...");
        stage = 1;
    }
 
    @Override
    public void run(int interfaceId, int componentId) {
         if (stage == 1) {
			if (componentId == OPTION_1) {
			Jail5Mins();
			} 
			else if (componentId == OPTION_2) {
			Jail15Mins();
			}
			else if (componentId == OPTION_3) {
			Jail30Mins();
			}	
			else if (componentId == OPTION_4) {
			Jail1Hour();
			} 
			else if (componentId == OPTION_5) {
			player.getDialogueManager().startDialogue("OffMin");
			}
		}
	}
    
    public void Jail5Mins() {
        Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't jail an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to jail you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setJailed(Utils.currentTimeMillis() + (1 * 30 * 10 * 1000));
        target.forceLogout();
        player.getPackets().sendGameMessage("You have jailed " + target.getDisplayName()+" for 5 minutes.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been jailed for 5 minutes by "+player.getDisplayName()+"", true);
    }
    
    public void Jail15Mins() {
    	 Player target = World.getPlayers().get(playerIndex);
         if (target.getRights() == 4) {
             player.sendMessage("You can't jail an administrator.");
             target.sendMessage(""+player.getDisplayName()+" has attempted to jail you.");
             return;
         }
         
         SerializableFilesManager.savePlayer(target);
         target.setJailed(Utils.currentTimeMillis() + (1 * 30 * 30 * 1000));
         target.forceLogout();
         player.getPackets().sendGameMessage("You have jailed " + target.getDisplayName()+" for 15 minutes.");
         World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been jailed for 15 minutes by "+player.getDisplayName()+"", true);
    }
    
    public void Jail30Mins() {
    	 Player target = World.getPlayers().get(playerIndex);
         if (target.getRights() == 4) {
             player.sendMessage("You can't jail an administrator.");
             target.sendMessage(""+player.getDisplayName()+" has attempted to jail you.");
             return;
         }
         
         SerializableFilesManager.savePlayer(target);
         target.setJailed(Utils.currentTimeMillis() + (1 * 30 * 60 * 1000));
         target.forceLogout();
         player.getPackets().sendGameMessage("You have jailed " + target.getDisplayName()+" for 30 minutes.");
         World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been jailed for 30 minutes by "+player.getDisplayName()+"", true);
    }
    
    public void Jail1Hour() {
    	Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't jail an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to jail you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setJailed(Utils.currentTimeMillis() + (1 * 60 * 60 * 1000));
        target.forceLogout();
        player.getPackets().sendGameMessage("You have jailed " + target.getDisplayName()+" for 1 hour.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been jailed for 1 hour by "+player.getDisplayName()+"", true);
    }
    
    @Override
    public void finish() {
        // TODO Auto-generated method stub
        
    }

}