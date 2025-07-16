package com.rs.game.player.content.interfaces;

import com.rs.game.player.Player;

public class PrestigeRewards {

	public static void openRSMVInfo(Player player) {
		for(int i = 0; i < 310; i++) {
			player.getPackets().sendIComponentText(275, i,
					"");
		}
		player.getPackets().sendIComponentText(275, 1, "<col=00ffff><shad=00000><img=23>Prestige System Rewards<img=23></col>");
		player.getPackets().sendIComponentText(275, 10, "<col=FFA200><shad=FFEF00>Prestige 1");
		
		player.getPackets().sendIComponentText(275, 11, "<col=000000><shad=000000>RSMVer 1 Title");
		player.getPackets().sendIComponentText(275, 12, "<col=000000><shad=000000>Flared Trousers (Black)");
		
		player.getPackets().sendIComponentText(275, 13, "<col=FFA200><shad=FFEF00>Prestige 2");
		player.getPackets().sendIComponentText(275, 14, "<col=000000><shad=000000>RSMVer 2 Title");
		player.getPackets().sendIComponentText(275, 15, "<col=000000><shad=000000>Access to skincolor feature (;;skincolor)");
		
		player.getPackets().sendIComponentText(275, 16, "<col=FFA200><shad=FFEF00>Prestige 3");
		player.getPackets().sendIComponentText(275, 17, "<col=000000><shad=000000>RSMVer 3 Title");
		player.getPackets().sendIComponentText(275, 18, "<col=000000><shad=000000>Access to telelock feature (;;switchtelelock)");
		
		player.getPackets().sendIComponentText(275, 19, "<col=FFA200><shad=FFEF00>Prestige 4");
		player.getPackets().sendIComponentText(275, 20, "<col=000000><shad=000000>RSMVer 4 Title");
		player.getPackets().sendIComponentText(275, 21, "<col=000000><shad=000000>Remote Book (Doll of Iban)");
		
		player.getPackets().sendIComponentText(275, 22, "<col=FFA200><shad=FFEF00>Prestige 5");
		player.getPackets().sendIComponentText(275, 23, "<col=000000><shad=000000>RSMVer 5 Title");
		player.getPackets().sendIComponentText(275, 24, "<col=000000><shad=000000>;;hidemap");
		player.getPackets().sendIComponentText(275, 25, "<col=000000><shad=000000>;;hideinterfaces");
		player.getPackets().sendIComponentText(275, 26, "<col=000000><shad=000000>;;hide");
		player.getPackets().sendIComponentText(275, 27, "<col=000000><shad=000000>Access to save locations feature (;;savelocation)");
		player.getPackets().sendIComponentText(275, 28, "<col=000000><shad=000000>Lyre 2");
		
		player.getPackets().sendIComponentText(275, 29, "<col=FFA200><shad=FFEF00>Prestige 6");
		player.getPackets().sendIComponentText(275, 30, "<col=000000><shad=000000>RSMVer 6 Title");
		player.getPackets().sendIComponentText(275, 31, "<col=000000><shad=000000>Access to save animations feature (;;saveanim)");
		
		player.getPackets().sendIComponentText(275, 32, "<col=FFA200><shad=FFEF00>Prestige 7");
		player.getPackets().sendIComponentText(275, 33, "<col=000000><shad=000000>RSMVer 7 Title");
		player.getPackets().sendIComponentText(275, 34, "<col=000000><shad=000000>Access to save remotes feature (;;saveremote)");
		
		player.getPackets().sendIComponentText(275, 35, "<col=FFA200><shad=FFEF00>Prestige 8");
		player.getPackets().sendIComponentText(275, 36, "<col=000000><shad=000000>RSMVer 8 Title");
		player.getPackets().sendIComponentText(275, 37, "<col=000000><shad=000000>Access to MEP Control NPC");
		
		player.getPackets().sendIComponentText(275, 38, "<col=FFA200><shad=FFEF00>Prestige 9");
		player.getPackets().sendIComponentText(275, 39, "<col=000000><shad=000000>RSMVer 9 Title");
		player.getPackets().sendIComponentText(275, 40, "<col=000000><shad=000000>;;infiniterun");
		player.getPackets().sendIComponentText(275, 41, "<col=000000><shad=000000>;;infinitespec");
		
		player.getPackets().sendIComponentText(275, 42, "<col=FFA200><shad=FFEF00>Prestige 10");
		player.getPackets().sendIComponentText(275, 43, "<col=000000><shad=000000>The RSMV Master Title");
		player.getPackets().sendIComponentText(275, 44, "<col=000000><shad=000000>Master Lyre (includes random option)");
		player.getPackets().sendIComponentText(275, 45, "<col=000000><shad=000000>White Cavalier");
		player.getPackets().sendIComponentText(275, 46, "<col=000000><shad=000000>Vengium '09 Access.");
		player.getPackets().sendIComponentText(275, 47, "<col=000000><shad=000000>RSMV Master Cape");
		player.getPackets().sendIComponentText(275, 48, "<col=000000><shad=000000>Your RSMV Info (Right-click option on player)");
		player.getPackets().sendIComponentText(275, 49, "<col=000000><shad=000000>Your RSMVs featured on the Vengium site and server.");
		player.getPackets().sendIComponentText(275, 50, "<col=00ffff><col=00000>-Your owner, Multak</col>");
		player.getInterfaceManager().sendInterface(275);
		player.unlock();
	}
	
}
