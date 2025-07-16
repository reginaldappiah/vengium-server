package com.rs.game.player.content;

import com.rs.game.player.Player;

public class Tasksmanager {
	
	public final static void main(String[] args) {
	}
	
	public static void Addcount(int value, Player player, String type) {
		switch (type) {
		case "Woodcutting":
			player.logcount += value;
			player.getPackets().sendGameMessage("<col=008000>You've got " + player.logcount + " logs" + " out of the " + player.neededpoints + " logs");
		break;
			
		case "Cooking":
			player.cookcount += value;
			player.getPackets().sendGameMessage("<col=008000>You've cooked " + player.cookcount + " food " + "out of the " + player.neededpoints + " food");
			break;
		
		case "Firemaking":
			player.firecount += value;
			player.getPackets().sendGameMessage("<col=008000>You've burned " + player.firecount + " logs " + "out of the " + player.neededpoints + " logs");
			break;

		default:
			System.out.println("Not added!");
			break;
		}
		Check(player, type);
		
	}

	private static void Check(Player player, String type) {
		switch (type) {
		case "Woodcutting":
			if(player.neededpoints <= 81) {
				player.neededpoints = 81;
			}
			if(player.logcount == player.neededpoints) {
				 player.skillpoints += 10;
				 player.neededpoints += 150;
				 player.getPackets().sendGameMessage("<col=008000>Your new task is to chop a total of " +player.neededpoints + " logs");
				 player.getPackets().sendGameMessage("<col=008000>You've got " + player.skillpoints +  " skillpoints");
			}
			
			break;
			
		case "Firemaking":
			if(player.neededpoints <= 81) {
				player.neededpoints = 81;
			}
			if(player.logcount == player.neededpoints) {
				 player.skillpoints += 10;
				 player.neededpoints += 150;
				 player.getPackets().sendGameMessage("<col=008000>Your new task is to burn a total of " +player.neededpoints + " logs");
				 player.getPackets().sendGameMessage("<col=008000>You've got " + player.skillpoints +  " skillpoints");
			}
			
			break;
			
		case "Cooking":
			if(player.neededpoints <= 81) {
				player.neededpoints = 81;
			
			}
			if(player.cookcount == player.neededpoints) {
				player.skillpoints += 10;
				player.neededpoints += 150;
				player.getPackets().sendGameMessage("<col=008000>Your new task is to cook a total of " +player.neededpoints + " food");
				 player.getPackets().sendGameMessage("<col=008000>You've got " + player.skillpoints +  " skillpoints");
			}

			default:
			break;
		}
		
	}
}