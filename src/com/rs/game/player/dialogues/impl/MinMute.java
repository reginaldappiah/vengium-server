package com.rs.game.player.dialogues.impl;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;

public class MinMute extends Dialogue {
	
	private int playerIndex;
	
	@Override
    public void start() {
		playerIndex = (Integer) parameters[0];
	    Player target = World.getPlayers().get(playerIndex);
        sendOptionsDialogue("Minor Mute Category: Player: "+target.getDisplayName()+"", "5 Minute Mute",
                "15 Minute Mute", "30 Minute Mute", "1 Hour Mute", "Minor Categories...");
        stage = 1;
    }
 
    @Override
    public void run(int interfaceId, int componentId) {
         if (stage == 1) {
			if (componentId == OPTION_1) {
			Mute5Mins();
			} 
			else if (componentId == OPTION_2) {
			Mute15Mins();
			}
			else if (componentId == OPTION_3) {
			Mute30Mins();
			}	
			else if (componentId == OPTION_4) {
			Mute1Hour();
			} 
			else if (componentId == OPTION_5) {
			player.getDialogueManager().startDialogue("OffMin");
			}
		}
	}
    
    public void Mute5Mins() {
        Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't mute an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to mute you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setMuted(Utils.currentTimeMillis() + (1 * 30 * 10 * 1000));
        target.forceLogout();
        player.getPackets().sendGameMessage("You have muted " + target.getDisplayName()+" for 5 minutes.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been muted for 5 minutes by "+player.getDisplayName()+"", true);
    }
    
    public void Mute15Mins() {
    	 Player target = World.getPlayers().get(playerIndex);
         if (target.getRights() == 4) {
             player.sendMessage("You can't mute an administrator.");
             target.sendMessage(""+player.getDisplayName()+" has attempted to mute you.");
             return;
         }
         
         SerializableFilesManager.savePlayer(target);
         target.setMuted(Utils.currentTimeMillis() + (1 * 30 * 30 * 1000));
         target.forceLogout();
         player.getPackets().sendGameMessage("You have muted " + target.getDisplayName()+" for 15 minutes.");
         World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been muted for 15 minutes by "+player.getDisplayName()+"", true);
    }
    
    public void Mute30Mins() {
    	 Player target = World.getPlayers().get(playerIndex);
         if (target.getRights() == 4) {
             player.sendMessage("You can't mute an administrator.");
             target.sendMessage(""+player.getDisplayName()+" has attempted to mute you.");
             return;
         }
         
         SerializableFilesManager.savePlayer(target);
         target.setMuted(Utils.currentTimeMillis() + (1 * 30 * 60 * 1000));
         target.forceLogout();
         player.getPackets().sendGameMessage("You have muted " + target.getDisplayName()+" for 30 minutes.");
         World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been muted for 30 minutes by "+player.getDisplayName()+"", true);
    }
    
    public void Mute1Hour() {
    	Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't mute an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to mute you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setMuted(Utils.currentTimeMillis() + (1 * 60 * 60 * 1000));
        target.forceLogout();
        player.getPackets().sendGameMessage("You have muted " + target.getDisplayName()+" for 1 hour.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been muted for 1 hour by "+player.getDisplayName()+"", true);
    }
    
    @Override
    public void finish() {
        // TODO Auto-generated method stub
        
    }

}