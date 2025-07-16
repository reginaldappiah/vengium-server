package com.rs.game.player.dialogues.impl;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.IPMute;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;

public class SevMute extends Dialogue {
	
	private int playerIndex;
	
	@Override
    public void start() {
		playerIndex = (Integer) parameters[0];
	    Player target = World.getPlayers().get(playerIndex);
        sendOptionsDialogue("Severe Mute Category: Player: "+target.getDisplayName()+"", "24 Hour Mute",
                "48 Hour Mute", "Perm Mute", "IP Mute", "Severe Categories...");
        stage = 1;
    }
 
    @Override
    public void run(int interfaceId, int componentId) {
         if (stage == 1) {
			if (componentId == OPTION_1) {
			Mute24Hours();
			} 
			else if (componentId == OPTION_2) {
			Mute48Hours();
			}
			else if (componentId == OPTION_3) {
			MutePerm();
			}	
			else if (componentId == OPTION_4) {
			MuteIPPerm();
			} 
			else if (componentId == OPTION_5) {
			player.getDialogueManager().startDialogue("OffSev");
			}
		}
	}
    
    public void Mute24Hours() {
        Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't mute an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to mute you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setMuted(Utils.currentTimeMillis() + (24 * 60 * 60 * 1000));
        target.forceLogout();
        player.getPackets().sendGameMessage("You have muted " + target.getDisplayName()+" for 24 hours.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been muted for 24 hours by "+player.getDisplayName()+"", true);
    }
    
    public void Mute48Hours() {
        Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't mute an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to mute you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setMuted(Utils.currentTimeMillis() + (48 * 60 * 60 * 1000));
        target.forceLogout();
        player.getPackets().sendGameMessage("You have muted " + target.getDisplayName()+" for 48 hours.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been muted for 48 hours by "+player.getDisplayName()+"", true);
    }
    
    public void MutePerm() {
        Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't mute an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to mute you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setPermMuted(true);
        target.forceLogout();
        player.getPackets().sendGameMessage("You have muted " + target.getDisplayName()+" permanently.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been muted permanently by "+player.getDisplayName()+"", true);
    }
    
    public void MuteIPPerm() {
		boolean loggedIn = true;
    	Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't mute an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to mute you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        IPMute.mute(target, loggedIn);
        target.forceLogout();
        player.getPackets().sendGameMessage("You have muted " + target.getDisplayName()+" permanently.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been muted permanently by "+player.getDisplayName()+"", true);
    }
    @Override
    public void finish() {
        // TODO Auto-generated method stub
        
    }

}