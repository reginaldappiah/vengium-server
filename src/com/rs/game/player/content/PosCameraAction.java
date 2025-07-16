package com.rs.game.player.content;

import com.rs.game.player.Player;

public class PosCameraAction {
	
	private int moveLocalX;
	private int moveLocalY;
	private int moveZ;
	private int speed;
	private int speed2;

	public PosCameraAction(int moveLocalX, int moveLocalY, int moveZ,
			int speed, int speed2) {
		this.moveLocalX = moveLocalX;
		this.moveLocalY = moveLocalY;
		this.moveZ = moveZ;
		this.speed = speed;
		this.speed2 = speed2;
	}

	public PosCameraAction(int moveLocalX, int moveLocalY, int moveZ,
			int actionDelay) {
		this(moveLocalX, moveLocalY, moveZ, -1, -1);
	}

	public void process(Player player) {
		player.getPackets().sendCameraPos(player.getLocalX(player, moveLocalX),
				player.getLocalY(player, moveLocalY), moveZ, speed, speed2);

	}
}
