package com.rs.game.player.dialogues.impl;

import com.rs.game.player.Player;
import com.rs.game.player.content.MagicCarpet;
import com.rs.game.player.dialogues.Dialogue;

public class RugMerchant extends Dialogue {

	@Override
	public void start() {
		if ((boolean) parameters[0])
			getLocations(player);
		else
			sendPlayerDialogue(9827, "Hello.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			stage = 0;
			sendNPCDialogue(2291, 9827, "Greetings, desert traveller. Do you require the services of Ali Morrisane's flying carpet fleet?");
			break;
		case 0:
			stage = 1;
			sendOptionsDialogue("Choose an option:", "Tell me about Ali Morrisane.", "Tell me about this magic carpet fleet.", "Yes.", "No.");
			break;
		case 1:
			switch (componentId) {
			case OPTION_1:
				stage = 2;
				sendPlayerDialogue(9827, "Tell me about Ali Morrisane.");
				break;
			case OPTION_2:
				stage = 12;
				sendPlayerDialogue(9827, "Tell me about this magic carpet fleet.");
				break;
			case OPTION_3:
				stage = 17;
				sendNPCDialogue(2291, 9827, "From here you can travel to many locations.");
				break;
			default:
				stage = -2;
				sendPlayerDialogue(9827, "No.");
				break;
			}
			break;
		case 2:
			stage = 3;
			sendNPCDialogue(2291, 9827, "What? You haven't heard of Ali Morrisane? Possibly the<br>greatest salesman in all the Kharidian Desert, if not all<br>RuneScape?");
			break;
		case 3:
			stage = 4;
			sendPlayerDialogue(9827, "I can't say that I have, but he must be the ambitious type<br>to set up his own airline.");
			break;
		case 4:
			stage = 5;
			sendNPCDialogue(2291, 9827, "I reckon that he's trying to take on those gnomes at their<br>own game, and I'd bet good money that he'll probably win.");
			break;
		case 5:
			stage = 6;
			sendPlayerDialogue(9827, "Huh? You've lost me.");
			break;
		case 6:
			stage = 7;
			sendNPCDialogue(2291, 9827, "You know, the little guys...the ones that aren't dwarves.");
			break;
		case 7:
			stage = 8;
			sendPlayerDialogue(9827, "Yeah, gnomes - I'm with you that far.");
			break;
		case 8:
			stage = 9;
			sendNPCDialogue(2291, 9827, "Well, they have already established Gnome Air...");
			break;
		case 9:
			stage = 10;
			sendPlayerDialogue(9827, "Go on.");
			break;
		case 10:
			stage = 11;
			sendNPCDialogue(2291, 9827, "Anyway, I think Ali M's setup here will prove really<br>successful, and maybe once we're properly established we<br>could compete with those gnomes.");
			break;
		case 11:
			stage = -2;
			sendPlayerDialogue(9827, "I'll watch this space.");
			break;
		case 12:
			stage = 13;
			sendNPCDialogue(2291, 9827, "The latest idea from the great Ali Morrisane. Desert<br>travel will never be the same again.");
			break;
		case 13:
			stage = 14;
			sendPlayerDialogue(9827, "So how does it work?");
			break;
		case 14:
			stage = 15;
			sendNPCDialogue(2291, 9827, "The carpet or the whole enterprise?");
			break;
		case 15:
			stage = -2;
			sendPlayerDialogue(9827, "Nevermind.");
			break;
		case 16:
			stage = 17;
			sendNPCDialogue(2291, 9827, "The second major carpet hub station in south Pollnivneach<br>is in easy walking distance from there.");
			break;
		case 17:
			getLocations(player);
			break;
		case 18:
			end();
			switch (componentId) {
			case OPTION_1:
				MagicCarpet.takeRug(player, MagicCarpet.UZER);
				break;
			case OPTION_2:
				MagicCarpet.takeRug(player, MagicCarpet.BEDABIN);
				break;
			case OPTION_3:
				MagicCarpet.takeRug(player, MagicCarpet.POLLN);
				break;
			case OPTION_4:
				MagicCarpet.takeRug(player, MagicCarpet.POLLS);
				break;
			case OPTION_5:
				end();
				break;
			}
		case 19:
			end();
			switch (componentId) {
			case OPTION_1:
				MagicCarpet.takeRug(player, MagicCarpet.SHANTAY);
				break;
			case OPTION_2:
				end();
				break;
			}
		case 20:
			end();
			switch (componentId) {
			case OPTION_1:
				MagicCarpet.takeRug(player, MagicCarpet.SHANTAY);
				break;
			case OPTION_2:
				MagicCarpet.takeRug(player, MagicCarpet.NARDAH);
				break;
			case OPTION_3:
				MagicCarpet.takeRug(player, MagicCarpet.MENAPHOS);
				break;
			case OPTION_4:
				MagicCarpet.takeRug(player, MagicCarpet.SOPHANOM);
				break;
			case OPTION_5:
				end();
				break;
			}
		case 21:
			end();
			switch (componentId) {
			case OPTION_1:
				MagicCarpet.takeRug(player, MagicCarpet.POLLS);
				break;
			case OPTION_2:
				end();
				break;
			}
		default:
			end();
			break;
		}
	}
	
	public void getLocations(Player player) {
		int options = MagicCarpet.getTravels(player);
		if (options == 1) {
			stage = 18;
			MagicCarpet.BASE = MagicCarpet.SHANTAY;
			sendOptionsDialogue("Where do you wish to travel?", "I want to travel to Uzer.", "I want to travel to the Bedabin Camp.", "I want to travel to north Pollnivneach.", "I want to travel to south Pollnivneach.", "I don't want to travel any of those places.");
		} else if (options == 2 || options == 3 || options == 4) {
			stage = 19;
			if (options == 2)
				MagicCarpet.BASE = MagicCarpet.BEDABIN;
			else if (options == 3)
				MagicCarpet.BASE = MagicCarpet.UZER;
			else
				MagicCarpet.BASE = MagicCarpet.POLLN;
			sendOptionsDialogue("Where do you wish to travel?", "I want to travel to the Shantay Pass.", "I don't want to travel any of those places.");
		} else if (options == 5) {
			stage = 20;
			MagicCarpet.BASE = MagicCarpet.POLLS;
			sendOptionsDialogue("Where do you wish to travel?", "I want to travel to the Shantay Pass.", "I want to travel to Nardah.", "I want to travel to Menaphos", "I want to travel to Sophanom.", "I don't want to travel any of those places.");
		} else if (options == 6 || options == 7 || options == 8) {
			stage = 21;
			if (options == 6)
				MagicCarpet.BASE = MagicCarpet.NARDAH;
			else if (options == 7)
				MagicCarpet.BASE = MagicCarpet.SOPHANOM;
			else
				MagicCarpet.BASE = MagicCarpet.MENAPHOS;
			sendOptionsDialogue("Where do you wish to travel?", "I want to travel to the South of Pollnivneach.", "I don't want to travel any of those places.");
		}
	}

	@Override
	public void finish() {

	}

}
