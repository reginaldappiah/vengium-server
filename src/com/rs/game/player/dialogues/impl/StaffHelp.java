package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.Hit;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.Hit.HitLook;
import com.rs.game.player.Player;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;

public class StaffHelp extends Dialogue {
	
	private int playerIndex;
	
	@Override
    public void start() {
		playerIndex = (Integer) parameters[0];
	    Player target = World.getPlayers().get(playerIndex);
        sendOptionsDialogue("Staff Help Category: Player: "+target.getDisplayName()+"", "Give RSMVer Rank",
                "Give Item - Coming Soon", "Unnull", "Kill", "Main Menu.");
        stage = 1;
    }
 
    @Override
    public void run(int interfaceId, int componentId) {
         if (stage == 1) {
			if (componentId == OPTION_1) {
			GiveRSMVer();
			} 
			else if (componentId == OPTION_2) {
			end();
			}
			else if (componentId == OPTION_3) {
			Unnull();
			}	
			else if (componentId == OPTION_4) {
			Kill();
			} 
			else if (componentId == OPTION_5) {
			if (player.getRights() == 3)
			player.getDialogueManager().startDialogue("ModCat");
			else if (player.getRights() == 4)
			player.getDialogueManager().startDialogue("AdminCat");
			}
		}
	}
    
    public void GiveRSMVer() {
        Player target = World.getPlayers().get(playerIndex);
        if (target == null)
			return;
		target.setRights(2);
		SerializableFilesManager.savePlayer(target);
		target.getPackets().sendGameMessage(
				"You have been given the RSMVer rank by "
						+ Utils.formatPlayerNameForDisplay(player
								.getUsername()), true);
		player.getPackets().sendGameMessage(
				"You gave the RSMVer rank to "
						+ Utils.formatPlayerNameForDisplay(target
								.getUsername()), true);
		return; 
      
    }
    
    public void GiveItem() {
    	 Player target = World.getPlayers().get(playerIndex);
         if (target.getRights() == 4) {
             player.sendMessage("You can't ban an administrator.");
             target.sendMessage(""+player.getDisplayName()+" has attempted to ban you.");
             return;
         }
         
         SerializableFilesManager.savePlayer(target);
         target.setBanned(Utils.currentTimeMillis() + (1 * 30 * 30 * 1000));
         target.forceLogout();
         player.getPackets().sendGameMessage("You have banned " + target.getDisplayName()+" for 15 minutes.");
         World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been banned for 15 minutes by "+player.getDisplayName()+"", true);
    }
    
    public void Unnull() {
    	Player target = World.getPlayers().get(playerIndex);
    	target.unlock();
		target.getControlerManager().forceStop();
		if(target.getNextWorldTile() == null) //if controler wont tele the player
		target.setNextWorldTile(Settings.RESPAWN_PLAYER_LOCATION);
		target.sendMessage(""+player.getDisplayName()+" has successfully unnulled you.");
		player.getPackets().sendGameMessage("You have successfully unnulled: "+target.getDisplayName()+".");
    }
    
    public void Kill() {
    	Player target = World.getPlayers().get(playerIndex);
    	target.applyHit(new Hit(target, player.getHitpoints(),
				HitLook.REGULAR_DAMAGE));
		target.stopAll();
    }
    
    @Override
    public void finish() {
        // TODO Auto-generated method stub
        
    }

}