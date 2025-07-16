package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.Hit;
import com.rs.game.World;
import com.rs.game.Hit.HitLook;
import com.rs.game.player.Player;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.IPBanL;
import com.rs.utils.IPJail;
import com.rs.utils.IPMute;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;



public class ModCat extends Dialogue {

	
	
	private int playerIndex;

	@Override
	public void start() {
		playerIndex = (Integer) parameters[0];
		Player target = World.getPlayers().get(playerIndex);
		stage = 1;
		if (stage == 1) {
			sendOptionsDialogue("Player Options: " + target.getDisplayName() + "", "<col=FFFF00>Minor Offense.", "<col=FF8400>Moderate Offense.", "<col=787676>Severe Offense."
					, "<col=40FF00>Extra/Helpful Options.", "Nevermind.");
			stage = 2;
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 2) {
			if (componentId == OPTION_1) {
				Player target = World.getPlayers().get(playerIndex);
				sendOptionsDialogue("Pick a Minor Punishment for: "+target.getDisplayName()+"","Minor Ban", "Minor Mute", "Minor Jail", "Main Menu.");
				stage = 10;
			}
			if (componentId == OPTION_2) {
				Player target = World.getPlayers().get(playerIndex);
				sendOptionsDialogue("Pick a Moderate Punishment for: "+target.getDisplayName()+"","Moderate Ban", "Moderte Mute", "Moderate Jail", "Main Menu.");
				stage = 20;
			}
			if (componentId == OPTION_3) {
				player.getPackets().sendGameMessage("<img=15><col=ff0000>You must be an <img=1> to use this category, sorry.");
				end();
			}
			if (componentId == OPTION_4) {
				Player target = World.getPlayers().get(playerIndex);
				sendOptionsDialogue("Staff Help Service for: "+target.getDisplayName()+"", "Give RSMVer Rank",
		                "Give Item - Coming Soon", "Unnull", "Kill", "Main Menu.");
				stage = 40;
			}
			if (componentId == OPTION_5) {
				end();
			}
			} else if (stage == 10) {
			if (componentId == OPTION_1) {//MINOR OFFENSES (1/3) <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
				stage = 11;
				Player target = World.getPlayers().get(playerIndex);
				sendOptionsDialogue("Minor Ban Category for: "+target.getDisplayName()+"", "5 Minute Ban",
		                "15 Minute Ban", "30 Minute Ban", "1 Hour Ban", "Minor Categories...");
			}
			if (componentId == OPTION_2) {
				stage = 12;
				Player target = World.getPlayers().get(playerIndex);
		        sendOptionsDialogue("Minor Mute Category for: "+target.getDisplayName()+"", "5 Minute Mute",
		                "15 Minute Mute", "30 Minute Mute", "1 Hour Mute", "Minor Categories...");
		        
			}
			if (componentId == OPTION_3) {
				stage = 13;
				Player target = World.getPlayers().get(playerIndex);
		        sendOptionsDialogue("Minor Jail Category: Player: "+target.getDisplayName()+"", "5 Minute Jail",
		                "15 Minute Jail", "30 Minute Jail", "1 Hour Jail", "Minor Categories...");
		        
			}
			if (componentId == OPTION_4) {
				stage = 2;
				Player target = World.getPlayers().get(playerIndex);
				sendOptionsDialogue("Player Options: " + target.getDisplayName() + "", "<col=FFFF00>Minor Offense.", "<col=FF8400>Moderate Offense."
						, "<col=FF0000>Severe Offense.", "<col=40FF00>Extra/Helpful Options.", "Nevermind.");
			}
			if (componentId == OPTION_5) {
				end();
			}
			} else if (stage == 11) {//MINOR BANS
			if (componentId == OPTION_1) {
			Ban5Mins();
			end();
			} 
			else if (componentId == OPTION_2) {
			Ban15Mins();
			end();
			}
			else if (componentId == OPTION_3) {
			Ban30Mins();
			end();
			}	
			else if (componentId == OPTION_4) {
			Ban1Hour();
			end();
			} 
			else if (componentId == OPTION_5) {
				stage = 10;
				Player target = World.getPlayers().get(playerIndex);
				sendOptionsDialogue("Pick a Minor Punishment for: "+target.getDisplayName()+"","Minor Ban", "Minor Mute", "Minor Jail", "Main Menu.");
				
			}
			} else if (stage == 12) {//MINOR MUTES
			if (componentId == OPTION_1) {
			Mute5Mins();
			end();
			} 
			else if (componentId == OPTION_2) {
			Mute15Mins();
			end();
			}
			else if (componentId == OPTION_3) {
			Mute30Mins();
			end();
			}	
			else if (componentId == OPTION_4) {
			Mute1Hour();
			end();
			} 
			else if (componentId == OPTION_5) {
				stage = 10;
				Player target = World.getPlayers().get(playerIndex);
				sendOptionsDialogue("Pick a Minor Punishment for: "+target.getDisplayName()+"","Minor Ban", "Minor Mute", "Minor Jail", "Main Menu.");
				
			}
			} else if (stage == 13) {//MINOR JAILS
			if (componentId == OPTION_1) {
			Jail5Mins();
			end();
			} 
			else if (componentId == OPTION_2) {
			Jail15Mins();
			end();
			}
			else if (componentId == OPTION_3) {
			Jail30Mins();
			end();
			}	
			else if (componentId == OPTION_4) {
			Jail1Hour();
			end();
			} 
			else if (componentId == OPTION_5) {
				stage = 10;
				Player target = World.getPlayers().get(playerIndex);
				sendOptionsDialogue("Pick a Minor Punishment for: "+target.getDisplayName()+"","Minor Ban", "Minor Mute", "Minor Jail", "Main Menu.");
				
			}
			} else if (stage == 20) {
			if (componentId == OPTION_1) {//MODERATE OFFENSES (2/3)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
				stage = 21;
				Player target = World.getPlayers().get(playerIndex);
		        sendOptionsDialogue("Moderate Ban Category for: "+target.getDisplayName()+"", "2 Hour Ban",
		                "4 Hour Ban", "8 Hour Ban", "16 Hour Ban", "Moderate Categories...");
		        
			}
			if (componentId == OPTION_2) {
				stage = 22;
				Player target = World.getPlayers().get(playerIndex);
		        sendOptionsDialogue("Moderate Mute Category for: "+target.getDisplayName()+"", "2 Hour Mute",
		                "4 Hour Mute", "8 Hour Mute", "16 Hour Mute", "Moderate Categories...");
		        
			}
			if (componentId == OPTION_3) {
				stage = 23;
				Player target = World.getPlayers().get(playerIndex);
		        sendOptionsDialogue("Moderate Jailed Category: Player: "+target.getDisplayName()+"", "2 Hour Jailed",
		                "4 Hour Jailed", "8 Hour Jailed", "16 Hour Jailed", "Moderate Categories...");
		        
			}
			if (componentId == OPTION_4) {
				stage = 2;
				Player target = World.getPlayers().get(playerIndex);
				sendOptionsDialogue("Player Options: " + target.getDisplayName() + "", "<col=FFFF00>Minor Offense.", "<col=FF8400>Moderate Offense."
						, "<col=FF0000>Severe Offense.", "<col=40FF00>Extra/Helpful Options.", "Nevermind.");
			}
			if (componentId == OPTION_5) {
				end();
			}
			} else if (stage == 21) {//MODERATE BANS
			if (componentId == OPTION_1) {
			Ban2Hours();
			end();
			} 
			else if (componentId == OPTION_2) {
			Ban4Hours();
			end();
			}
			else if (componentId == OPTION_3) {
			Ban8Hours();
			end();
			}	
			else if (componentId == OPTION_4) {
			Ban16Hours();
			end();
			} 
			else if (componentId == OPTION_5) {
				stage = 20;
				Player target = World.getPlayers().get(playerIndex);
				sendOptionsDialogue("Pick a Moderate Punishment for: "+target.getDisplayName()+"","Moderate Ban", "Moderte Mute", "Moderate Jail", "Main Menu.");
			}
			} else if (stage == 12) {//MODERATE MUTES
			if (componentId == OPTION_1) {
			Mute2Hours();
			end();
			} 
			else if (componentId == OPTION_2) {
			Mute4Hours();
			end();
			}
			else if (componentId == OPTION_3) {
			Mute8Hours();
			end();
			}	
			else if (componentId == OPTION_4) {
			Mute16Hours();
			end();
			} 
			else if (componentId == OPTION_5) {
				stage = 20;
				Player target = World.getPlayers().get(playerIndex);
				sendOptionsDialogue("Pick a Moderate Punishment for: "+target.getDisplayName()+"","Moderate Ban", "Moderte Mute", "Moderate Jail", "Main Menu.");
			}
			} else if (stage == 13) {//MODERATE JAILS
			if (componentId == OPTION_1) {
			Jailed2Hours();
			end();
			} 
			else if (componentId == OPTION_2) {
			Jailed4Hours();
			end();
			}
			else if (componentId == OPTION_3) {
			Jailed8Hours();
			end();
			}	
			else if (componentId == OPTION_4) {
			Jailed16Hours();
			end();
			} 
			else if (componentId == OPTION_5) {
				stage = 20;
				Player target = World.getPlayers().get(playerIndex);
				sendOptionsDialogue("Pick a Moderate Punishment for: "+target.getDisplayName()+"","Moderate Ban", "Moderte Mute", "Moderate Jail", "Main Menu.");
			}
			} else if (stage == 30) {
			if (componentId == OPTION_1) {//SEVERE OFFENSES (3/3)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
				stage = 31;
				Player target = World.getPlayers().get(playerIndex);
		        sendOptionsDialogue("Severe Ban Category for: "+target.getDisplayName()+"", "24 Hour Ban",
		                "48 Hour Ban", "Perm Ban", "IP Ban", "Severe Categories...");
		       
			}
			if (componentId == OPTION_2) {
				stage = 32;
				Player target = World.getPlayers().get(playerIndex);
		        sendOptionsDialogue("Severe Mute Category for: "+target.getDisplayName()+"", "24 Hour Mute",
		                "48 Hour Mute", "Perm Mute", "IP Mute", "Severe Categories...");
		    }
			if (componentId == OPTION_3) {
				stage = 33;
				Player target = World.getPlayers().get(playerIndex);
		        sendOptionsDialogue("Severe Jail Category: Player: "+target.getDisplayName()+"", "24 Hour Jail",
		                "48 Hour Jail", "Perm Jail", "IP Jail", "Severe Categories...");
		    }
			if (componentId == OPTION_4) {
				stage = 2;
				Player target = World.getPlayers().get(playerIndex);
				sendOptionsDialogue("Player Options: " + target.getDisplayName() + "", "<col=FFFF00>Minor Offense.", "<col=FF8400>Moderate Offense."
						, "<col=FF0000>Severe Offense.", "<col=40FF00>Extra/Helpful Options.", "Nevermind.");
			}
			if (componentId == OPTION_5) {
				end();
			}
			} else if (stage == 31) {//SEVERE BANS
			if (componentId == OPTION_1) {
			Ban24Hours();
			end();
			} 
			else if (componentId == OPTION_2) {
			Ban48Hours();
			end();
			}
			else if (componentId == OPTION_3) {
			BanPerm();
			end();
			}	
			else if (componentId == OPTION_4) {
			BanIPPerm();
			end();
			} 
			else if (componentId == OPTION_5) {
				stage = 30;
				Player target = World.getPlayers().get(playerIndex);
				sendOptionsDialogue("Pick a Severe Punishment for: "+target.getDisplayName()+"","Severe Ban", "Severe Mute", "Severe Jail", "Main Menu.");
			}
			} else if (stage == 32) {//SEVERE MUTES
			if (componentId == OPTION_1) {
			Mute24Hours();
			end();
			} 
			else if (componentId == OPTION_2) {
			Mute48Hours();
			end();
			}
			else if (componentId == OPTION_3) {
			MutePerm();
			end();
			}	
			else if (componentId == OPTION_4) {
			MuteIPPerm();
			end();
			} 
			else if (componentId == OPTION_5) {
				stage = 30;
				Player target = World.getPlayers().get(playerIndex);
				sendOptionsDialogue("Pick a Severe Punishment for: "+target.getDisplayName()+"","Severe Ban", "Severe Mute", "Severe Jail", "Main Menu.");
			}
			} else if (stage == 33) {//SEVERE JAILS
			if (componentId == OPTION_1) {
			Jail24Hours();
			end();
			} 
			else if (componentId == OPTION_2) {
			Jail48Hours();
			end();
			}
			else if (componentId == OPTION_3) {
			JailPerm();
			end();
			}	
			else if (componentId == OPTION_4) {
			JailIPPerm();
			end();
			} 
			else if (componentId == OPTION_5) {
				stage = 30;
				Player target = World.getPlayers().get(playerIndex);
				sendOptionsDialogue("Pick a Severe Punishment for: "+target.getDisplayName()+"","Severe Ban", "Severe Mute", "Severe Jail", "Main Menu.");
			}
			} else if (stage == 40) {
			if (componentId == OPTION_1) {//STAFF HELP (3/3)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			GiveRSMVer();
			end();
			} 
			else if (componentId == OPTION_2) {
			end();
			}
			else if (componentId == OPTION_3) {
			Unnull();
			end();
			}	
			else if (componentId == OPTION_4) {
			Kill();
			end();
			} 
			else if (componentId == OPTION_5) {
			stage = 2;
			Player target = World.getPlayers().get(playerIndex);
			sendOptionsDialogue("Player Options: " + target.getDisplayName() + "", "<col=FFFF00>Minor Offense.", "<col=FF8400>Moderate Offense."
					, "<col=FF0000>Severe Offense.", "<col=40FF00>Extra/Helpful Options.", "Nevermind.");
			}
		}
	}
	public void ban() {
		Player target = World.getPlayers().get(playerIndex);
		if (target.getRights() == 4) {
			player.sendMessage("You can't ban an administrator.");
			target.sendMessage("" + player.getDisplayName() + " has attempted to ban you.");
			return;
		}

		SerializableFilesManager.savePlayer(target);
		target.setPermBanned(true);
		target.forceLogout();
		player.sm("You have banned " + target.getDisplayName() + ".");
		World.sendWorldMessage("BanHandler: " + target.getDisplayName() + " has been banned by " + player.getDisplayName() + "", true);
	}

	public void mute() {
		Player target = World.getPlayers().get(playerIndex);
		if (target.getRights() == 4) {
			player.sendMessage("You can't mute an administrator.");
			target.sendMessage("" + player.getDisplayName() + " has attempted to mute you.");
			return;
		}
		player.sm("You have muted " + target.getDisplayName() + " for 24 hours.");
		target.setMuted(Utils.currentTimeMillis() + 1440000);
		target.getPackets().sendGameMessage("You have been muted for 24 hours by " + player.getDisplayName() + ".");
		World.sendWorldMessage("MuteHandler: " + target.getDisplayName() + " has been muted for 24 hours by " + player.getDisplayName() + "", true);
	}

	public void kick() {
		Player target = World.getPlayers().get(playerIndex);
		target.forceLogout();
		player.sm("You have kicked: " + target.getDisplayName() + ".");
	}

	public void jail() {
		Player target = World.getPlayers().get(playerIndex);
		if (target.getRights() == 4) {
			player.sendMessage("You can't jail an administrator.");
			target.sendMessage("" + player.getDisplayName() + " has attempted to jail you.");
			return;
		}
		target.setJailed(Utils.currentTimeMillis() + 1440000);
		target.getControlerManager().startControler("JailControler");
		target.getPackets().sendGameMessage("You've been Jailed for 24 hours by " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ".");
		player.sm("You have Jailed 24 hours: " + target.getDisplayName() + ".");
		SerializableFilesManager.savePlayer(target);
	}
	//MINOR OPTIONS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	public void Ban5Mins() {
        Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't ban an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to ban you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setBanned(Utils.currentTimeMillis() + (1 * 30 * 10 * 1000));
        target.forceLogout();
        player.getPackets().sendGameMessage("You have banned " + target.getDisplayName()+" for 5 minutes.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been banned for 5 minutes by "+player.getDisplayName()+"", true);
    }
    
    public void Ban15Mins() {
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
    
    public void Ban30Mins() {
    	 Player target = World.getPlayers().get(playerIndex);
         if (target.getRights() == 4) {
             player.sendMessage("You can't ban an administrator.");
             target.sendMessage(""+player.getDisplayName()+" has attempted to ban you.");
             return;
         }
         
         SerializableFilesManager.savePlayer(target);
         target.setBanned(Utils.currentTimeMillis() + (1 * 30 * 60 * 1000));
         target.forceLogout();
         player.getPackets().sendGameMessage("You have banned " + target.getDisplayName()+" for 30 minutes.");
         World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been banned for 30 minutes by "+player.getDisplayName()+"", true);
    }
    
    public void Ban1Hour() {
    	Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't ban an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to ban you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setBanned(Utils.currentTimeMillis() + (1 * 60 * 60 * 1000));
        target.forceLogout();
        player.getPackets().sendGameMessage("You have banned " + target.getDisplayName()+" for 1 hour.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been banned for 1 hour by "+player.getDisplayName()+"", true);
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
    
    public void Jail5Mins() {
        Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't jail an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to jail you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setJailed(Utils.currentTimeMillis() + (1 * 30 * 10 * 1000));
        target.forceLogout();
        player.getPackets().sendGameMessage("You have jailed " + target.getDisplayName()+" for 5 minutes.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been jailed for 5 minutes by "+player.getDisplayName()+"", true);
    }
    
    public void Jail15Mins() {
    	 Player target = World.getPlayers().get(playerIndex);
         if (target.getRights() == 4) {
             player.sendMessage("You can't jail an administrator.");
             target.sendMessage(""+player.getDisplayName()+" has attempted to jail you.");
             return;
         }
         
         SerializableFilesManager.savePlayer(target);
         target.setJailed(Utils.currentTimeMillis() + (1 * 30 * 30 * 1000));
         target.forceLogout();
         player.getPackets().sendGameMessage("You have jailed " + target.getDisplayName()+" for 15 minutes.");
         World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been jailed for 15 minutes by "+player.getDisplayName()+"", true);
    }
    
    public void Jail30Mins() {
    	 Player target = World.getPlayers().get(playerIndex);
         if (target.getRights() == 4) {
             player.sendMessage("You can't jail an administrator.");
             target.sendMessage(""+player.getDisplayName()+" has attempted to jail you.");
             return;
         }
         
         SerializableFilesManager.savePlayer(target);
         target.setJailed(Utils.currentTimeMillis() + (1 * 30 * 60 * 1000));
         target.forceLogout();
         player.getPackets().sendGameMessage("You have jailed " + target.getDisplayName()+" for 30 minutes.");
         World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been jailed for 30 minutes by "+player.getDisplayName()+"", true);
    }
    
    public void Jail1Hour() {
    	Player target = World.getPlayers().get(playerIndex);
        if (target.getRights() == 4) {
            player.sendMessage("You can't jail an administrator.");
            target.sendMessage(""+player.getDisplayName()+" has attempted to jail you.");
            return;
        }
        
        SerializableFilesManager.savePlayer(target);
        target.setJailed(Utils.currentTimeMillis() + (1 * 60 * 60 * 1000));
        target.forceLogout();
        player.getPackets().sendGameMessage("You have jailed " + target.getDisplayName()+" for 1 hour.");
        World.sendWorldMessage("<col=ff0000><img=7>News: " + target.getDisplayName() + " has been jailed for 1 hour by "+player.getDisplayName()+"", true);
    }
    //MODERATE OPTIONS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
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
    //SEVERE OPTIONS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
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
    //STAFF HELP OPTIONS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
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