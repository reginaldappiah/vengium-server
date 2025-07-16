package com.rs.game.player.dialogues.impl;

import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.ForceTalk;
import com.rs.game.player.content.interfaces.PrestigeRewards;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.ShopsHandler;

public class PrestigeOne extends Dialogue {

	private int npcId = 659;

	@Override
	public void start() {
		sendOptionsDialogue("RSMVer Prestige System ", "<col=ff0000>I would like to prestige.",
				"I have a few questions I would like you to answer.",
				"I'd like to check my RSMV Prestige points please.",
				"I would like to recieve my prestige title please.",
				"Nevermind.");
		stage = 1;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
		if (componentId == OPTION_1) {
				/*sendNPCDialogue(
						npcId,
						9827,
						"The prestige system gives you the ability to earn benefits. To prestige, you must have 100 points per prestige. For every prestige you will gain one new title and gain abilities.");
				stage = 2;*/
		sendOptionsDialogue("Are you sure you wanna prestige?", "Yes!",
				"No thanks.");
		stage = 5;
		}if (componentId == OPTION_2) {
		sendOptionsDialogue("RSMVer Prestige Questions ", "Who are you?",
				"What is a prestige?",
				"How do I prestige?",
				"What are the benefits per prestige?",
				"Main Menu.");
		stage = 10;
		}if (componentId == OPTION_3) {
		player.getPackets().sendGameMessage(
				"<img=15><col=ff0000> I currently have: " + player.RSMVerPoints
						+ " RSMVer points.");
		player.setNextForceTalk(new ForceTalk("<col=ff0000>I currently have: "
				+ player.RSMVerPoints + " RSMVer points."));
		end();
		}if (componentId == OPTION_4) {
		if (player.RSMVerPoints >= 100 & player.RSMVerPoints < 200) {
			player.getAppearence().setTitle(1100);
		} else if (player.RSMVerPoints >= 200 & player.RSMVerPoints < 300) {
			player.getAppearence().setTitle(1200);
		} else if (player.RSMVerPoints >= 300 & player.RSMVerPoints < 400) {
			player.getAppearence().setTitle(1300);
		} else if (player.RSMVerPoints >= 400 & player.RSMVerPoints < 500) {
			player.getAppearence().setTitle(1400);
		} else if (player.RSMVerPoints >= 500 & player.RSMVerPoints < 600) {
			player.getAppearence().setTitle(1500);
		} else if (player.RSMVerPoints >= 600 & player.RSMVerPoints < 700) {
			player.getAppearence().setTitle(1600);
		} else if (player.RSMVerPoints >= 700 & player.RSMVerPoints < 800) {
			player.getAppearence().setTitle(1700);
		} else if (player.RSMVerPoints == 800) {
			player.getAppearence().setTitle(1800);
		} else if (player.RSMVerPoints >= 900 & player.RSMVerPoints < 1000) {
			player.getAppearence().setTitle(1900);
		} else if (player.RSMVerPoints >= 1000) {
			player.getAppearence().setTitle(2000);
		} else {
			player.getPackets().sendGameMessage(
					"<img=15><col=ff0000>You need to have prestiged to use this.");
		}
		end();
		} else if (componentId == OPTION_5) {
			end();
		}
		
		//QUESTIONS
		
		} else if (stage == 10) {
		if (componentId == OPTION_1) {
		sendNPCDialogue(npcId, 9827,
				"I'm the famous Party Pete, in charge of collecting your prestige points "
				+ "and prestiging you whenever you are eligible. Multak stationed me here, "
				+ "but you can catch me at the Party Room in Falador whenever you want. ");
		stage = 100;
		}if (componentId == OPTION_2) {
			sendNPCDialogue(npcId, 9827,
					"A prestige is basically a ranking for an achievement. It is a way to "
					+ "prove your worth and recieve benefits in return. ");
			stage = 100;
		}if (componentId == OPTION_3) {
			sendNPCDialogue(npcId, 9827,
					"If you want to prestige, just talk to me when you feel you are eligible "
					+ "or have enough points for a certain prestige. If you need to know "
					+ "how many points you have, ask me or look in your account info. ");
			stage = 100;
		}if (componentId == OPTION_4) {
			sendNPCDialogue(npcId, 9827,
					"Here are the benefits!");
			stage = 101;
		} else if (componentId == OPTION_5) {
			end();
		}
		
		
		} else if (stage == 3) {
			sendNPCDialogue(
					npcId,
					9827,
					"Yup!");
			stage = 11;
			
		//MAIN MENU
		} else if (stage == 100) {
			sendOptionsDialogue("RSMVer Prestige Questions ", "Who are you?",
					"What is a prestige?",
					"How do I prestige?",
					"What are the benefits per prestige?",
					"Main Menu.");
			stage = 10;
			
		} else if (stage == 101) {
			PrestigeRewards.openRSMVInfo(player);
			
			
		} else if (stage == 5) {
			if (componentId == OPTION_1) {
				//player.prestige();
			if (player.RSMVerPoints < 100) {
				player.getPackets().sendGameMessage("<img=15><col=ff0000>You need atleast 100 RSMVer points.");
			}if (player.RSMVerPoints >= 100 & player.RSMVerPoints < 200) {
					if (!player.isRSMVer1()) {
						player.setCompletedRSMVerOne();
					}
					else {
					player.sm("<img=15><col=FF0000>You have already reached rank 1.");
					}
			}if (player.RSMVerPoints >= 200 & player.RSMVerPoints < 300) {
					if ((!player.isRSMVer2())) {
					player.setCompletedRSMVerTwo();
					}
				    else {
				    player.sm("<img=15><col=FF0000>You have already reached rank 2.");
				    }
			}if (player.RSMVerPoints >= 300 & player.RSMVerPoints < 400) {
					if  ((!player.isRSMVer3())) {
					player.setCompletedRSMVerThree();
					}
					else {
					player.sm("<img=15><col=FF0000>You have already reached rank 3.");
					}
			}if (player.RSMVerPoints >= 400 & player.RSMVerPoints < 500) {
					if  ((!player.isRSMVer4())) {
					player.setCompletedRSMVerFour();
					}
					else {
					player.sm("<img=15><col=FF0000>You have already reached rank 4.");
					}
			}if (player.RSMVerPoints >= 500 & player.RSMVerPoints < 600) {
					if  ((!player.isRSMVer5())) {
					player.setCompletedRSMVerFive();
					}
					else {
					player.sm("<img=15><col=FF0000>You have already reached rank 5.");
					}
			}if (player.RSMVerPoints >= 600 & player.RSMVerPoints < 700) {
					if  ((!player.isRSMVer6())) {
					player.setCompletedRSMVerSix();
					}
					else {
					player.sm("<img=15><col=FF0000>You have already  reached rank 6.");
					}
			}if (player.RSMVerPoints >= 700 & player.RSMVerPoints < 800) {
					if  ((!player.isRSMVer7())) {
					player.setCompletedRSMVerSeven();
					}
					else {
					player.sm("<img=15><col=FF0000>You have already reached rank 7.");
					}
			}if (player.RSMVerPoints >= 800 & player.RSMVerPoints < 900) {
					if  ((!player.isRSMVer8())) {
					player.setCompletedRSMVerEight();
					}
					else {
					player.sm("<img=15><col=FF0000>You have already reached rank 8.");
					}
			}if (player.RSMVerPoints >= 900 & player.RSMVerPoints < 1000) {
					if  ((!player.isRSMVer9())) {
					player.setCompletedRSMVerNine();
					}
					else {
					player.sm("<img=15><col=FF0000>You have already reached rank 9.");
					}
			}if (player.RSMVerPoints >= 1000) {
					if  ((!player.isRSMVer10())) {
					player.setCompletedRSMVerTen();
					}
					else {
					player.sm("<img=15><col=FF0000>You have already reached the maximum rank.");
					}
			}
			} else if (componentId == OPTION_2) {
				end();
			}
			end();
		}
	}
	@Override
	public void finish() {

	}
}