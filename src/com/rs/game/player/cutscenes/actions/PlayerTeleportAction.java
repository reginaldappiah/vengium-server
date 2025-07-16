package com.rs.game.player.cutscenes.actions;

import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.cutscenes.Cutscene;

public class PlayerTeleportAction extends CutsceneAction {

	private int x, y, z;

	public PlayerTeleportAction(int x, int y, int z) {
		super(-1, -1);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void process(Player player, Object[] cache) {
		Cutscene scene = (Cutscene) cache[0];
		player.setNextWorldTile(new WorldTile(x, y, z));
		player.stopAll();
	}
}