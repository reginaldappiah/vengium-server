package com.rs.game.player.dialogues.impl;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.IPBanL;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;

public class SevBan extends Dialogue {
	
	private int playerIndex;
	
	@Override
    public void start() {
		playerIndex = (Integer) parameters[0];
	    Player target = World.getPlayers().get(playerIndex);
        sendOptionsDialogue("Severe Ban Category: Player: "+target.getDisplayName()+"", "24 Hour Ban",
                "48 Hour Ban", "Perm Ban", "IP Ban", "Severe Categories...");
        stage = 1;
    }
 
    @Override
    public void run(int interfaceId, int componentId) {
         if (stage == 1) {
			if (componentId == OPTION_1) {
			Ban24Hours();
			} 
			else if (componentId == OPTION_2) {
			Ban48Hours();
			}
			else if (componentId == OPTION_3) {
			BanPerm();
			}	
			else if (componentId == OPTION_4) {
			BanIPPerm();
			} 
			else if (componentId == OPTION_5) {
			player.getDialogueManager().startDialogue("OffSev");
			}
		}
	}
    
    public void Ban24Hours() {
        Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't ban an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to ban you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setBanned(Utils.currentTimeMillis() + (24 * 60 * 60 * 1000));
        target.forceLogout();
        player.getPackets().sendGameMessage("You have banned " + target.getDisplayName()+" for 24 hours.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been banned for 24 hours by "+player.getDisplayName()+"", true);
    }
    
    public void Ban48Hours() {
        Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't ban an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to ban you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setBanned(Utils.currentTimeMillis() + (48 * 60 * 60 * 1000));
        target.forceLogout();
        player.getPackets().sendGameMessage("You have banned " + target.getDisplayName()+" for 48 hours.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been banned for 48 hours by "+player.getDisplayName()+"", true);
    }
    
    public void BanPerm() {
        Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't ban an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to ban you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setPermBanned(true);
        target.forceLogout();
        player.getPackets().sendGameMessage("You have banned " + target.getDisplayName()+" permanently.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been banned permanently by "+player.getDisplayName()+"", true);
    }
    
    public void BanIPPerm() {
		boolean loggedIn = true;
    	Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't ban an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to ban you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        IPBanL.ban(target, loggedIn);
        target.forceLogout();
        player.getPackets().sendGameMessage("You have banned " + target.getDisplayName()+" permanently.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been banned permanently by "+player.getDisplayName()+"", true);
    }
    @Override
    public void finish() {
        // TODO Auto-generated method stub
        
    }

}
  