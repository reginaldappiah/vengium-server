package com.rs.game.player.content;

import java.util.TimerTask;

import com.rs.cores.CoresManager;
import com.rs.game.WorldTile;
import com.rs.game.Graphics;
import com.rs.game.player.Player;

public class MagicCarpet {
	
	public static WorldTile BASE;
	public static WorldTile UZER = new WorldTile(3469, 3113, 0);
	public static WorldTile BEDABIN = new WorldTile(3180, 3045, 0);
	public static WorldTile POLLN = new WorldTile(3349, 3003, 0);
	public static WorldTile SHANTAY = new WorldTile(3308, 3109, 0);
	public static WorldTile NARDAH = new WorldTile(3401, 2916, 0);
	public static WorldTile MENAPHOS = new WorldTile(3245, 2813, 0);
	public static WorldTile SOPHANOM = new WorldTile(3285, 2813, 0);
	public static WorldTile POLLS = new WorldTile(3351, 2942, 0);
	
	public static int getTravels(Player player) {
		int x = player.getX();
		int y = player.getY();
		if (x >= 3175 && x <= 3189 && y >= 3033 && y <= 3052) { // Bedabin Camp
			return 2;
		} else if (x >= 3294 && x <= 3318 && y >= 3101 && y <= 3116) { // Shantay
																		// Pass
			return 1;
		} else if (x >= 3461 && x <= 3478 && y >= 3102 && y <= 3119) { // Ruins
																		// of
																		// Uzer
			return 3;
		} else if (x >= 3346 && x <= 3362 && y >= 2989 && y <= 3009) { // Pollnivneach
																		// North
			return 4;
		} else if (x >= 3339 && x <= 3356 && y >= 2932 && y <= 2951) { // Pollnivneach
																		// South
			return 5;
		} else if (x >= 3392 && x <= 3407 && y >= 2911 && y <= 2926) { // Nardah
			return 6;
		} else if (x >= 3279 && x <= 3298 && y >= 2803 && y <= 2820) { // Sophanom
			return 7;
		} else if (x >= 3237 && x <= 3254 && y >= 2800 && y <= 2820) { // Menaphos
			return 8;
		} else {
			return 0; // Shouldn't Happen
		}
	}

	public static void takeRug(final Player player, final WorldTile destination) {
		player.setNextWorldTile(BASE);
		player.setNextGraphics(new Graphics(2931));
		player.setRunHidden(true);
		CoresManager.fastExecutor.schedule(new TimerTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 4) {
					player.setNextGraphics(new Graphics(2932));
					player.getAppearence().setRenderEmote(191);
					player.addWalkSteps(destination.getX(), destination.getY(), -1, false);
					player.lock();
				}
				if (player.withinDistance(destination, 1)) {
					player.stopAll(true);
					player.getAppearence().setRenderEmote(-1);
					player.setNextGraphics(new Graphics(2933));
					player.unlock();
					cancel();
				}
				loop++;
			}
		}, 0, 500);
		return;
	}

}
