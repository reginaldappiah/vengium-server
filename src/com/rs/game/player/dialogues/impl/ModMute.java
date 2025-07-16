package com.rs.game.player.dialogues.impl;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;

public class ModMute extends Dialogue {
	
	private int playerIndex;
	
	@Override
    public void start() {
		playerIndex = (Integer) parameters[0];
	    Player target = World.getPlayers().get(playerIndex);
        sendOptionsDialogue("Moderate Mute Category: Player: "+target.getDisplayName()+"", "2 Hour Mute",
                "4 Hour Mute", "8 Hour Mute", "16 Hour Mute", "Moderate Categories...");
        stage = 1;
    }
 
    @Override
    public void run(int interfaceId, int componentId) {
         if (stage == 1) {
			if (componentId == OPTION_1) {
			Mute2Hours();
			} 
			else if (componentId == OPTION_2) {
			Mute4Hours();
			}
			else if (componentId == OPTION_3) {
			Mute8Hours();
			}	
			else if (componentId == OPTION_4) {
			Mute16Hours();
			} 
			else if (componentId == OPTION_5) {
			player.getDialogueManager().startDialogue("OffMod");
			}
		}
	}
    
    public void Mute2Hours() {
        Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't mute an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to mute you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setMuted(Utils.currentTimeMillis() + (2 * 60 * 60 * 1000));
        target.forceLogout();
        player.getPackets().sendGameMessage("You have muted " + target.getDisplayName()+" for 2 hours.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been muted for 2 hours by "+player.getDisplayName()+"", true);
    }
    
    public void Mute4Hours() {
    	 Player target = World.getPlayers().get(playerIndex);
         if (target.getRights() == 4) {
             player.sendMessage("You can't mute an administrator.");
             target.sendMessage(""+player.getDisplayName()+" has attempted to mute you.");
             return;
         }
         
         SerializableFilesManager.savePlayer(target);
         target.setMuted(Utils.currentTimeMillis() + (4 * 60 * 60 * 1000));
         target.forceLogout();
         player.getPackets().sendGameMessage("You have muted " + target.getDisplayName()+" for 4 hours.");
         World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been muted for 4 hours by "+player.getDisplayName()+"", true);
    }
    
    public void Mute8Hours() {
    	 Player target = World.getPlayers().get(playerIndex);
         if (target.getRights() == 4) {
             player.sendMessage("You can't mute an administrator.");
             target.sendMessage(""+player.getDisplayName()+" has attempted to mute you.");
             return;
         }
         
         SerializableFilesManager.savePlayer(target);
         target.setMuted(Utils.currentTimeMillis() + (8 * 60 * 60 * 1000));
         target.forceLogout();
         player.getPackets().sendGameMessage("You have muted " + target.getDisplayName()+" for 8 hours.");
         World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been muted for 8 hours by "+player.getDisplayName()+"", true);
    }
    
    public void Mute16Hours() {
    	Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't mute an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to mute you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setMuted(Utils.currentTimeMillis() + (16 * 60 * 60 * 1000));
        target.forceLogout();
        player.getPackets().sendGameMessage("You have muted " + target.getDisplayName()+" for 16 hours.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been muted for 16 hours by "+player.getDisplayName()+"", true);
    }
    
    @Override
    public void finish() {
        // TODO Auto-generated method stub
        
    }

}
  