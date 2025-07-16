package com.rs.game.player.dialogues.impl;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;

public class ModJail extends Dialogue {
	
	private int playerIndex;
	
	@Override
    public void start() {
		playerIndex = (Integer) parameters[0];
	    Player target = World.getPlayers().get(playerIndex);
        sendOptionsDialogue("Moderate Jailed Category: Player: "+target.getDisplayName()+"", "2 Hour Jailed",
                "4 Hour Jailed", "8 Hour Jailed", "16 Hour Jailed", "Moderate Categories...");
        stage = 1;
    }
 
    @Override
    public void run(int interfaceId, int componentId) {
         if (stage == 1) {
			if (componentId == OPTION_1) {
			Jailed2Hours();
			} 
			else if (componentId == OPTION_2) {
			Jailed4Hours();
			}
			else if (componentId == OPTION_3) {
			Jailed8Hours();
			}	
			else if (componentId == OPTION_4) {
			Jailed16Hours();
			} 
			else if (componentId == OPTION_5) {
			player.getDialogueManager().startDialogue("OffMod");
			}
		}
	}
    
    public void Jailed2Hours() {
        Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't jail an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to jail you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setJailed(Utils.currentTimeMillis() + (2 * 60 * 60 * 1000));
        target.forceLogout();
        player.getPackets().sendGameMessage("You have jailed " + target.getDisplayName()+" for 2 hours.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been jailed for 2 hours by "+player.getDisplayName()+"", true);
    }
    
    public void Jailed4Hours() {
    	 Player target = World.getPlayers().get(playerIndex);
         if (target.getRights() == 4) {
             player.sendMessage("You can't jail an administrator.");
             target.sendMessage(""+player.getDisplayName()+" has attempted to jail you.");
             return;
         }
         
         SerializableFilesManager.savePlayer(target);
         target.setJailed(Utils.currentTimeMillis() + (4 * 60 * 60 * 1000));
         target.forceLogout();
         player.getPackets().sendGameMessage("You have jailed " + target.getDisplayName()+" for 4 hours.");
         World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been jailed for 4 hours by "+player.getDisplayName()+"", true);
    }
    
    public void Jailed8Hours() {
    	 Player target = World.getPlayers().get(playerIndex);
         if (target.getRights() == 4) {
             player.sendMessage("You can't jail an administrator.");
             target.sendMessage(""+player.getDisplayName()+" has attempted to jail you.");
             return;
         }
         
         SerializableFilesManager.savePlayer(target);
         target.setJailed(Utils.currentTimeMillis() + (8 * 60 * 60 * 1000));
         target.forceLogout();
         player.getPackets().sendGameMessage("You have jailed " + target.getDisplayName()+" for 8 hours.");
         World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been jailed for 8 hours by "+player.getDisplayName()+"", true);
    }
    
    public void Jailed16Hours() {
    	Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't jail an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to jail you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setJailed(Utils.currentTimeMillis() + (16 * 60 * 60 * 1000));
        target.forceLogout();
        player.getPackets().sendGameMessage("You have jailed " + target.getDisplayName()+" for 16 hours.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been jailed for 16 hours by "+player.getDisplayName()+"", true);
    }
    
    @Override
    public void finish() {
        // TODO Auto-generated method stub
        
    }

}
  