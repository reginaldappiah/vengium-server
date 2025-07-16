package com.rs.game.player.content;

import java.util.Random;

import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.utils.Misc;

public class ServerMessages {
	
	  private static String help [][] = { 
          {"Use the ticket option in the task interface to get quick help from staff."},
          {"Do ;;itemn guide book and read it to find out all the commands for your rank."},
    	  };
	  
	private static String news [][][] = { help };
    private static int catId;
    
	public static void Run() {
		catId = Misc.random(0, 0);
        int rand = RandomNews(catId);
        String title = "News";
        for(Player player : World.getPlayers()) {
            if(player == null)
                continue;
                player.getPackets().sendGameMessage("<col=AE6B00><shad=FFD61C>["+title+"] "+news[catId][rand][0]+"</col>");
        }
    }
	public static int RandomNews(int i) {
        int random = 0;
        Random rand = new Random();
        random = rand.nextInt(news[i].length);
        return random;
    }
}
