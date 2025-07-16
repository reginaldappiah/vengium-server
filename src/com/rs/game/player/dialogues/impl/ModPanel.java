package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;

public class ModPanel extends Dialogue {

    private int playerIndex;
    
    @Override
    public void start() {
        playerIndex = (Integer) parameters[0];
        Player target = World.getPlayers().get(playerIndex);
        stage = 1;
        if (stage == 1) {
            sendOptionsDialogue("Player Options: "+target.getDisplayName()+"",
                    "<col=ff0000>Ban</col>", 
                    "<col=ff0000>Mute</col>",
                    "<col=ff0000>Kick</col>", 
                    "<col=ff0000>Jail</col>",
                    "<col=ff0000>Main Menu.</col>");
            stage = 2;
        }
    }

    @Override
    public void run(int interfaceId, int componentId) {
        if (stage == 2) {
            if (componentId == OPTION_1) {
            	player.getDialogueManager().startDialogue("Ban");
                end();
            }
            if (componentId == OPTION_2) {
            	player.getDialogueManager().startDialogue("Mute");
                end();
            }
            if (componentId == OPTION_3) {
            	player.getDialogueManager().startDialogue("Kick");
                end();
            }
            if (componentId == OPTION_4) {
            	player.getDialogueManager().startDialogue("Jail");
                end();
            }
            if (componentId == OPTION_5) {
            	player.getDialogueManager().startDialogue("ModCat");
            }
        }
    }

    public void ban() {
        Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 2) {
            player.sendMessage("You can't ban an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to ban you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setPermBanned(true);
        target.forceLogout();
        player.getPackets().sendGameMessage("You have banned " + target.getDisplayName()+".");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been banned by "+player.getDisplayName()+"", true);
    }
    
    public void mute() {
        Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 2) {
            player.sendMessage("You can't mute an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to mute you.");
            return;
        }
        player.getPackets().sendGameMessage("You have muted " + target.getDisplayName()+" for 24 hours.");
        target.setMuted(Utils.currentTimeMillis() + 1440000);
        target.getPackets().sendGameMessage("You have been muted for 24 hours by "+player.getDisplayName()+".");
        World.sendWorldMessage("<col=ff0000><img=5>News: " + target.getDisplayName() + " has been muted for 24 hours by "+player.getDisplayName()+"", true);
    }
    
    public void kick() {
        Player target = World.getPlayers().get(playerIndex);
        target.forceLogout();
        player.getPackets().sendGameMessage("You have kicked: " + target.getDisplayName() + ".");
    }
    
    public void jail() {
        Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 2) {
            player.sendMessage("You can't jail an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to jail you.");
            return;
        }
        target.setJailed(Utils.currentTimeMillis() + 1440000);
        target.getControlerManager().startControler("JailControler");
        target.getPackets().sendGameMessage("You've been Jailed for 24 hours by " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ".");
        player.getPackets().sendGameMessage("You have Jailed 24 hours: " + target.getDisplayName() + ".");
        SerializableFilesManager.savePlayer(target);
    }
    
    @Override
    public void finish() {
        // TODO Auto-generated method stub
        
    }

}