package com.rs.game.player.dialogues.impl;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;

public class ModBan extends Dialogue {
	
	private int playerIndex;
	
	@Override
    public void start() {
		playerIndex = (Integer) parameters[0];
	    Player target = World.getPlayers().get(playerIndex);
        sendOptionsDialogue("Moderate Ban Category: Player: "+target.getDisplayName()+"", "2 Hour Ban",
                "4 Hour Ban", "8 Hour Ban", "16 Hour Ban", "Moderate Categories...");
        stage = 1;
    }
 
    @Override
    public void run(int interfaceId, int componentId) {
         if (stage == 1) {
			if (componentId == OPTION_1) {
			Ban2Hours();
			} 
			else if (componentId == OPTION_2) {
			Ban4Hours();
			}
			else if (componentId == OPTION_3) {
			Ban8Hours();
			}	
			else if (componentId == OPTION_4) {
			Ban16Hours();
			} 
			else if (componentId == OPTION_5) {
			player.getDialogueManager().startDialogue("OffMod");
			}
		}
	}
    
    public void Ban2Hours() {
        Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't ban an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to ban you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setBanned(Utils.currentTimeMillis() + (2 * 60 * 60 * 1000));
        target.forceLogout();
        player.getPackets().sendGameMessage("You have banned " + target.getDisplayName()+" for 2 hours.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been banned for 2 hours by "+player.getDisplayName()+"", true);
    }
    
    public void Ban4Hours() {
    	 Player target = World.getPlayers().get(playerIndex);
         if (target.getRights() == 4) {
             player.sendMessage("You can't ban an administrator.");
             target.sendMessage(""+player.getDisplayName()+" has attempted to ban you.");
             return;
         }
         
         SerializableFilesManager.savePlayer(target);
         target.setBanned(Utils.currentTimeMillis() + (4 * 60 * 60 * 1000));
         target.forceLogout();
         player.getPackets().sendGameMessage("You have banned " + target.getDisplayName()+" for 4 hours.");
         World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been banned for 4 hours by "+player.getDisplayName()+"", true);
    }
    
    public void Ban8Hours() {
    	 Player target = World.getPlayers().get(playerIndex);
         if (target.getRights() == 4) {
             player.sendMessage("You can't ban an administrator.");
             target.sendMessage(""+player.getDisplayName()+" has attempted to ban you.");
             return;
         }
         
         SerializableFilesManager.savePlayer(target);
         target.setBanned(Utils.currentTimeMillis() + (8 * 60 * 60 * 1000));
         target.forceLogout();
         player.getPackets().sendGameMessage("You have banned " + target.getDisplayName()+" for 8 hours.");
         World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been banned for 8 hours by "+player.getDisplayName()+"", true);
    }
    
    public void Ban16Hours() {
    	Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't ban an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to ban you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setBanned(Utils.currentTimeMillis() + (16 * 60 * 60 * 1000));
        target.forceLogout();
        player.getPackets().sendGameMessage("You have banned " + target.getDisplayName()+" for 16 hours.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been banned for 16 hours by "+player.getDisplayName()+"", true);
    }
    
    @Override
    public void finish() {
        // TODO Auto-generated method stub
        
    }

}
  