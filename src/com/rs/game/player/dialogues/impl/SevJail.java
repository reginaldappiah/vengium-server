package com.rs.game.player.dialogues.impl;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.IPJail;
//import com.rs.utils.IPMute;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;

public class SevJail extends Dialogue {
	
	private int playerIndex;
	
	@Override
    public void start() {
		playerIndex = (Integer) parameters[0];
	    Player target = World.getPlayers().get(playerIndex);
        sendOptionsDialogue("Severe Jail Category: Player: "+target.getDisplayName()+"", "24 Hour Jail",
                "48 Hour Jail", "Perm Jail", "IP Jail", "Severe Categories...");
        stage = 1;
    }
 
    @Override
    public void run(int interfaceId, int componentId) {
         if (stage == 1) {
			if (componentId == OPTION_1) {
			Jail24Hours();
			} 
			else if (componentId == OPTION_2) {
			Jail48Hours();
			}
			else if (componentId == OPTION_3) {
			JailPerm();
			}	
			else if (componentId == OPTION_4) {
			JailIPPerm();
			} 
			else if (componentId == OPTION_5) {
			player.getDialogueManager().startDialogue("OffSev");
			}
		}
	}
    
    public void Jail24Hours() {
        Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't jail an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to jail you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setJailed(Utils.currentTimeMillis() + (24 * 60 * 60 * 1000));
        target.getControlerManager().startControler("JailControler");
        target.forceLogout();
        player.getPackets().sendGameMessage("You have jailed " + target.getDisplayName()+" for 24 hours.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been jailed for 24 hours by "+player.getDisplayName()+"", true);
    }
    
    public void Jail48Hours() {
        Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't jail an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to jail you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setJailed(Utils.currentTimeMillis() + (48 * 60 * 60 * 1000));
        target.getControlerManager().startControler("JailControler");
        target.forceLogout();
        player.getPackets().sendGameMessage("You have jailed " + target.getDisplayName()+" for 48 hours.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been jailed for 48 hours by "+player.getDisplayName()+"", true);
    }
    
    public void JailPerm() {
        Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't jail an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to jail you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.getControlerManager().startControler("JailControler");
        target.setPermJailed(true);
        target.forceLogout();
        player.getPackets().sendGameMessage("You have jailed " + target.getDisplayName()+" permanently.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been jailed permanently by "+player.getDisplayName()+"", true);
    }
    
    public void JailIPPerm() {
		boolean loggedIn = true;
    	Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't jail an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to jail you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.getControlerManager().startControler("JailControler");
        IPJail.jail(target, loggedIn);
        target.forceLogout();
        player.getPackets().sendGameMessage("You have jailed " + target.getDisplayName()+" permanently.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been jailed permanently by "+player.getDisplayName()+"", true);
    }
    @Override
    public void finish() {
        // TODO Auto-generated method stub
        
    }

}