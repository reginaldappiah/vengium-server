package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;

public class Ban extends Dialogue {

    private int playerIndex;
    
    @Override
    public void start() {
        playerIndex = (Integer) parameters[0];
        Player target = World.getPlayers().get(playerIndex);
        stage = 1;
        if (stage == 1) {
            sendOptionsDialogue("Player Options: "+target.getDisplayName()+"",
                    "<col=ff0000>Ban for 30 Minutes</col>", 
                    "<col=ff0000>Ban for 6 Hours</col>",
                    "<col=ff0000>Ban for 12 Hours</col>", 
                    "<col=ff0000>Ban for 24 Hours</col>",
                    "<col=ff0000>Main Menu.</col>");
            stage = 2;
        }
    }

    @Override
    public void run(int interfaceId, int componentId) {
        if (stage == 2) {
            if (componentId == OPTION_1) {
            	ban30Min();
                end();
            }
            if (componentId == OPTION_2) {
            	ban6Hours();
                end();
            }
            if (componentId == OPTION_3) {
            	ban12Hours();
                end();
            }
            if (componentId == OPTION_4) {
            	ban24Hours();
                end();
            }
            if (componentId == OPTION_5) {
            	player.getDialogueManager().startDialogue("ModPanel");
            }
        }
    }

    public void ban30Min() {
        Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 2) {
            player.sendMessage("You can't ban an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to ban you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setBanned(Utils.currentTimeMillis() + 1800);
        target.forceLogout();
        player.getPackets().sendGameMessage("You have banned " + target.getDisplayName()+" for 30 minutes.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been banned for 30 minutes by "+player.getDisplayName()+"", true);
    }
    
    public void ban6Hours() {
    	 Player target = World.getPlayers().get(playerIndex);
         if (target.getRights() == 2) {
             player.sendMessage("You can't ban an administrator.");
             target.sendMessage(""+player.getDisplayName()+" has attempted to ban you.");
             return;
         }
         
         SerializableFilesManager.savePlayer(target);
         target.setBanned(Utils.currentTimeMillis() + (48 * 60 * 60 * 1000));
         target.forceLogout();
         player.getPackets().sendGameMessage("You have banned " + target.getDisplayName()+" for 6 hours.");
         World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been banned for 6 hours by "+player.getDisplayName()+"", true);
    }
    
    public void ban12Hours() {
    	 Player target = World.getPlayers().get(playerIndex);
         if (target.getRights() == 2) {
             player.sendMessage("You can't ban an administrator.");
             target.sendMessage(""+player.getDisplayName()+" has attempted to ban you.");
             return;
         }
         
         SerializableFilesManager.savePlayer(target);
         target.setBanned(Utils.currentTimeMillis() + 43200);
         target.forceLogout();
         player.getPackets().sendGameMessage("You have banned " + target.getDisplayName()+" for 12 hours.");
         World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been banned for 12 hours by "+player.getDisplayName()+"", true);
    }
    
    public void ban24Hours() {
    	Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 2) {
            player.sendMessage("You can't ban an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to ban you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setBanned(Utils.currentTimeMillis() + 86400);
        target.forceLogout();
        player.getPackets().sendGameMessage("You have banned " + target.getDisplayName()+" for 24 hours.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been banned for 24 hours by "+player.getDisplayName()+"", true);
    }
    
    @Override
    public void finish() {
        // TODO Auto-generated method stub
        
    }

}