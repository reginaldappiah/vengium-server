package com.rs.game.player.content;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.player.content.FadingScreen;
import com.rs.game.player.content.agility.BarbarianOutpostAgility;
import com.rs.game.WorldObject;
import com.rs.game.tasks.WorldTasksManager;

/**
 * 
 * @author Tony_Sixx
 */
public class Teletab {

	Player player;
	
	public Teletab(Player player) {
		this.player = player;
	}


	
	public void ProcessTeleportationAir(final Player player) {
			WorldTasksManager.schedule(new WorldTask() {
			int loop;
			
			@Override
			public void run() {
				player.getInterfaceManager().closeChatBoxInterface();			
				WorldObject object = null;
				if (loop == 0) {
				Magic.sendTabletTeleport(player, 0, 0, new WorldTile(2844, 4838, 0));
				} else if (loop == 1) {
					player.setNextAnimation(new Animation(4731));
					//player.setNextGraphics(new Graphics(1));
				} else if (loop == 3) {
					player.closeInterfaces();
					stop();
				}
				loop++;
				}
			}, 0, 1);
		}
		
	public void ProcessTeleportationMind(final Player player) {
			WorldTasksManager.schedule(new WorldTask() {
			int loop;
			
			@Override
			public void run() {
				player.getInterfaceManager().closeChatBoxInterface();			
				WorldObject object = null;
				if (loop == 0) {
				Magic.sendTabletTeleport(player, 0, 0, new WorldTile(2786, 4845, 0));
				} else if (loop == 1) {
					player.setNextAnimation(new Animation(4731));
					//player.setNextGraphics(new Graphics(1));
				} else if (loop == 3) {
					player.closeInterfaces();
					stop();
				}
				loop++;
				}
			}, 0, 1);
		}
		
	public void ProcessTeleportationWater(final Player player) {
			WorldTasksManager.schedule(new WorldTask() {
			int loop;
			
			@Override
			public void run() {
				player.getInterfaceManager().closeChatBoxInterface();			
				WorldObject object = null;
				if (loop == 0) {
				Magic.sendTabletTeleport(player, 0, 0, new WorldTile(3484, 4840, 0));
				} else if (loop == 1) {
					player.setNextAnimation(new Animation(4731));
					//player.setNextGraphics(new Graphics(1));
				} else if (loop == 3) {
					player.closeInterfaces();
					stop();
				}
				loop++;
				}
			}, 0, 1);
		}

	public void ProcessTeleportationEarth(final Player player) {
			WorldTasksManager.schedule(new WorldTask() {
			int loop;
			
			@Override
			public void run() {
				player.getInterfaceManager().closeChatBoxInterface();			
				WorldObject object = null;
				if (loop == 0) {
				Magic.sendTabletTeleport(player, 0, 0, new WorldTile(2658, 4845, 0));
				} else if (loop == 1) {
					player.setNextAnimation(new Animation(4731));
					//player.setNextGraphics(new Graphics(1));
				} else if (loop == 3) {
					player.closeInterfaces();
					stop();
				}
				loop++;
				}
			}, 0, 1);
		}

	public void ProcessTeleportationFire(final Player player) {
			WorldTasksManager.schedule(new WorldTask() {
			int loop;
			
			@Override
			public void run() {
				player.getInterfaceManager().closeChatBoxInterface();			
				WorldObject object = null;
				if (loop == 0) {
				Magic.sendTabletTeleport(player, 0, 0, new WorldTile(2585, 4842, 0));
				} else if (loop == 1) {
					player.setNextAnimation(new Animation(4731));
					//player.setNextGraphics(new Graphics(1));
				} else if (loop == 3) {
					player.closeInterfaces();
					stop();
				}
				loop++;
				}
			}, 0, 1);
		}

	public void ProcessTeleportationBody(final Player player) {
			WorldTasksManager.schedule(new WorldTask() {
			int loop;
			
			@Override
			public void run() {
				player.getInterfaceManager().closeChatBoxInterface();			
				WorldObject object = null;
				if (loop == 0) {
				Magic.sendTabletTeleport(player, 0, 0, new WorldTile(2523, 4844, 0));
				} else if (loop == 1) {
					player.setNextAnimation(new Animation(4731));
					//player.setNextGraphics(new Graphics(1));
				} else if (loop == 3) {
					player.closeInterfaces();
					stop();
				}
				loop++;
				}
			}, 0, 1);
		}

	public void ProcessTeleportationCosmic(final Player player) {
			WorldTasksManager.schedule(new WorldTask() {
			int loop;
			
			@Override
			public void run() {
				player.getInterfaceManager().closeChatBoxInterface();			
				WorldObject object = null;
				if (loop == 0) {
				Magic.sendTabletTeleport(player, 0, 0, new WorldTile(2142, 4837, 0));
				} else if (loop == 1) {
					player.setNextAnimation(new Animation(4731));
					//player.setNextGraphics(new Graphics(1));
				} else if (loop == 3) {
					player.closeInterfaces();
					stop();
				}
				loop++;
				}
			}, 0, 1);
		}

	public void ProcessTeleportationChaos(final Player player) {
			WorldTasksManager.schedule(new WorldTask() {
			int loop;
			
			@Override
			public void run() {
				player.getInterfaceManager().closeChatBoxInterface();			
				WorldObject object = null;
				if (loop == 0) {
				Magic.sendTabletTeleport(player, 0, 0, new WorldTile(2271, 4846, 0));
				} else if (loop == 1) {
					player.setNextAnimation(new Animation(4731));
					//player.setNextGraphics(new Graphics(1));
				} else if (loop == 3) {
					player.closeInterfaces();
					stop();
				}
				loop++;
				}
			}, 0, 1);
		}

	public void ProcessTeleportationNature(final Player player) {
			WorldTasksManager.schedule(new WorldTask() {
			int loop;
			
			@Override
			public void run() {
				player.getInterfaceManager().closeChatBoxInterface();			
				WorldObject object = null;
				if (loop == 0) {
				Magic.sendTabletTeleport(player, 0, 0, new WorldTile(2400, 4847, 0));
				} else if (loop == 1) {
					player.setNextAnimation(new Animation(4731));
					//player.setNextGraphics(new Graphics(1));
				} else if (loop == 3) {
					player.closeInterfaces();
					stop();
				}
				loop++;
				}
			}, 0, 1);
		}

	public void ProcessTeleportationLaw(final Player player) {
			WorldTasksManager.schedule(new WorldTask() {
			int loop;
			
			@Override
			public void run() {
				player.getInterfaceManager().closeChatBoxInterface();			
				WorldObject object = null;
				if (loop == 0) {
				Magic.sendTabletTeleport(player, 0, 0, new WorldTile(2464, 4837, 0));
				} else if (loop == 1) {
					player.setNextAnimation(new Animation(4731));
					//player.setNextGraphics(new Graphics(1));
				} else if (loop == 3) {
					player.closeInterfaces();
					stop();
				}
				loop++;
				}
			}, 0, 1);
		}

	public void ProcessTeleportationDeath(final Player player) {
			WorldTasksManager.schedule(new WorldTask() {
			int loop;
			
			@Override
			public void run() {
				player.getInterfaceManager().closeChatBoxInterface();			
				WorldObject object = null;
				if (loop == 0) {
				Magic.sendTabletTeleport(player, 0, 0, new WorldTile(2205, 4839, 0));
				} else if (loop == 1) {
					player.setNextAnimation(new Animation(4731));
					//player.setNextGraphics(new Graphics(1));
				} else if (loop == 3) {
					player.closeInterfaces();
					stop();
				}
				loop++;
				}
			}, 0, 1);
		}

	public void ProcessTeleportationBlood(final Player player) {
			WorldTasksManager.schedule(new WorldTask() {
			int loop;
			
			@Override
			public void run() {
				player.getInterfaceManager().closeChatBoxInterface();			
				WorldObject object = null;
				if (loop == 0) {
				Magic.sendTabletTeleport(player, 0, 0, new WorldTile(2463, 4900, 1));
				} else if (loop == 1) {
					player.setNextAnimation(new Animation(4731));
					//player.setNextGraphics(new Graphics(1));
				} else if (loop == 3) {
					player.unlock();
					player.closeInterfaces();
					stop();
				}
				loop++;
				}
			}, 0, 1);
		}

	public void ProcessTeleportationAstral(final Player player) {
			WorldTasksManager.schedule(new WorldTask() {
			int loop;
			
			@Override
			public void run() {
				player.getInterfaceManager().closeChatBoxInterface();			
				WorldObject object = null;
				if (loop == 0) {
				Magic.sendTabletTeleport(player, 0, 0, new WorldTile(2158, 3868, 0));
				} else if (loop == 1) {
					player.setNextAnimation(new Animation(4731));
					//player.setNextGraphics(new Graphics(1));
				} else if (loop == 3) {
					player.closeInterfaces();
					stop();
				}
				loop++;
				}
			}, 0, 1);
		}
	public void ProcessTeleportationRCGuild(final Player player) {
		WorldTasksManager.schedule(new WorldTask() {
		int loop;
		
		@Override
		public void run() {
			player.getInterfaceManager().closeChatBoxInterface();			
			WorldObject object = null;
			if (loop == 0) {
			Magic.sendTabletTeleport(player, 0, 0, new WorldTile(1693, 5469, 2));
			} else if (loop == 1) {
				player.setNextAnimation(new Animation(4731));
				//player.setNextGraphics(new Graphics(1));
			} else if (loop == 3) {
				player.closeInterfaces();
				stop();
			}
			loop++;
			}
		}, 0, 1);
	}
	public void ProcessTeleportationVarrock(final Player player) {
		WorldTasksManager.schedule(new WorldTask() {
		int loop;
		
		@Override
		public void run() {
			player.getInterfaceManager().closeChatBoxInterface();			
			WorldObject object = null;
			if (loop == 0) {
			Magic.sendTabletTeleport(player, 0, 0, new WorldTile(3218, 3426, 0));
			} else if (loop == 1) {
				player.setNextAnimation(new Animation(4731));
				//player.setNextGraphics(new Graphics(1));
			} else if (loop == 3) {
				player.closeInterfaces();
				stop();
			}
			loop++;
			}
		}, 0, 1);
	}
	public void ProcessTeleportationLumbridge(final Player player) {
		WorldTasksManager.schedule(new WorldTask() {
		int loop;
		
		@Override
		public void run() {
			player.getInterfaceManager().closeChatBoxInterface();			
			WorldObject object = null;
			if (loop == 0) {
			Magic.sendTabletTeleport(player, 0, 0, new WorldTile(3222, 3219, 0));
			} else if (loop == 1) {
				player.setNextAnimation(new Animation(4731));
				//player.setNextGraphics(new Graphics(1));
			} else if (loop == 3) {
				player.closeInterfaces();
				stop();
			}
			loop++;
			}
		}, 0, 1);
	}
	public void ProcessTeleportationFalador(final Player player) {
		WorldTasksManager.schedule(new WorldTask() {
		int loop;
		
		@Override
		public void run() {
			player.getInterfaceManager().closeChatBoxInterface();			
			WorldObject object = null;
			if (loop == 0) {
			Magic.sendTabletTeleport(player, 0, 0, new WorldTile(2967, 3380, 0));
			} else if (loop == 1) {
				player.setNextAnimation(new Animation(4731));
				//player.setNextGraphics(new Graphics(1));
			} else if (loop == 3) {
				player.closeInterfaces();
				stop();
			}
			loop++;
			}
		}, 0, 1);
	}
	public void ProcessTeleportationCamelot(final Player player) {
		WorldTasksManager.schedule(new WorldTask() {
		int loop;
		
		@Override
		public void run() {
			player.getInterfaceManager().closeChatBoxInterface();			
			WorldObject object = null;
			if (loop == 0) {
			Magic.sendTabletTeleport(player, 0, 0, new WorldTile(2757, 3478, 0));
			} else if (loop == 1) {
				player.setNextAnimation(new Animation(4731));
				//player.setNextGraphics(new Graphics(1));
			} else if (loop == 3) {
				player.closeInterfaces();
				stop();
			}
			loop++;
			}
		}, 0, 1);
	}
	public void ProcessTeleportationArdougne(final Player player) {
		WorldTasksManager.schedule(new WorldTask() {
		int loop;
		
		@Override
		public void run() {
			player.getInterfaceManager().closeChatBoxInterface();			
			WorldObject object = null;
			if (loop == 0) {
			Magic.sendTabletTeleport(player, 0, 0, new WorldTile(2663, 3306, 0));
			} else if (loop == 1) {
				player.setNextAnimation(new Animation(4731));
				//player.setNextGraphics(new Graphics(1));
			} else if (loop == 3) {
				player.closeInterfaces();
				stop();
			}
			loop++;
			}
		}, 0, 1);
	}
	public void ProcessTeleportationWatchtower(final Player player) {
		WorldTasksManager.schedule(new WorldTask() {
		int loop;
		
		@Override
		public void run() {
			player.getInterfaceManager().closeChatBoxInterface();			
			WorldObject object = null;
			if (loop == 0) {
			Magic.sendTabletTeleport(player, 0, 0, new WorldTile(2555, 3115, 0));
			} else if (loop == 1) {
				player.setNextAnimation(new Animation(4731));
				//player.setNextGraphics(new Graphics(1));
			} else if (loop == 3) {
				player.closeInterfaces();
				stop();
			}
			loop++;
			}
		}, 0, 1);
	}
	public void ProcessTeleportationRimmington(final Player player) {
		WorldTasksManager.schedule(new WorldTask() {
		int loop;
		
		@Override
		public void run() {
			player.getInterfaceManager().closeChatBoxInterface();			
			WorldObject object = null;
			if (loop == 0) {
			Magic.sendTabletTeleport(player, 0, 0, new WorldTile(2957, 3217, 0));
			} else if (loop == 1) {
				player.setNextAnimation(new Animation(4731));
				//player.setNextGraphics(new Graphics(1));
			} else if (loop == 3) {
				player.closeInterfaces();
				stop();
			}
			loop++;
			}
		}, 0, 1);
	}
	public void ProcessTeleportationTaverley(final Player player) {
		WorldTasksManager.schedule(new WorldTask() {
		int loop;
		
		@Override
		public void run() {
			player.getInterfaceManager().closeChatBoxInterface();			
			WorldObject object = null;
			if (loop == 0) {
			Magic.sendTabletTeleport(player, 0, 0, new WorldTile(2894, 3415, 0));
			} else if (loop == 1) {
				player.setNextAnimation(new Animation(4731));
				//player.setNextGraphics(new Graphics(1));
			} else if (loop == 3) {
				player.closeInterfaces();
				stop();
			}
			loop++;
			}
		}, 0, 1);
	}
	public void ProcessTeleportationPollnivneach(final Player player) {
		WorldTasksManager.schedule(new WorldTask() {
		int loop;
		
		@Override
		public void run() {
			player.getInterfaceManager().closeChatBoxInterface();			
			WorldObject object = null;
			if (loop == 0) {
			Magic.sendTabletTeleport(player, 0, 0, new WorldTile(3348, 2982, 0));
			} else if (loop == 1) {
				player.setNextAnimation(new Animation(4731));
				//player.setNextGraphics(new Graphics(1));
			} else if (loop == 3) {
				player.closeInterfaces();
				stop();
			}
			loop++;
			}
		}, 0, 1);
	}
	public void ProcessTeleportationRelleka(final Player player) {
		WorldTasksManager.schedule(new WorldTask() {
		int loop;
		
		@Override
		public void run() {
			player.getInterfaceManager().closeChatBoxInterface();			
			WorldObject object = null;
			if (loop == 0) {
			Magic.sendTabletTeleport(player, 0, 0, new WorldTile(2662, 3646, 0));
			} else if (loop == 1) {
				player.setNextAnimation(new Animation(4731));
				//player.setNextGraphics(new Graphics(1));
			} else if (loop == 3) {
				player.closeInterfaces();
				stop();
			}
			loop++;
			}
		}, 0, 1);
	}
	public void ProcessTeleportationBrimhaven(final Player player) {
		WorldTasksManager.schedule(new WorldTask() {
		int loop;
		
		@Override
		public void run() {
			player.getInterfaceManager().closeChatBoxInterface();			
			WorldObject object = null;
			if (loop == 0) {
			Magic.sendTabletTeleport(player, 0, 0, new WorldTile(2802, 3174, 0));
			} else if (loop == 1) {
				player.setNextAnimation(new Animation(4731));
				//player.setNextGraphics(new Graphics(1));
			} else if (loop == 3) {
				player.closeInterfaces();
				stop();
			}
			loop++;
			}
		}, 0, 1);
	}
	public void ProcessTeleportationYanille(final Player player) {
		WorldTasksManager.schedule(new WorldTask() {
		int loop;
		
		@Override
		public void run() {
			player.getInterfaceManager().closeChatBoxInterface();			
			WorldObject object = null;
			if (loop == 0) {
			Magic.sendTabletTeleport(player, 0, 0, new WorldTile(2558, 3090, 0));
			} else if (loop == 1) {
				player.setNextAnimation(new Animation(4731));
				//player.setNextGraphics(new Graphics(1));
			} else if (loop == 3) {
				player.closeInterfaces();
				stop();
			}
			loop++;
			}
		}, 0, 1);
	}
	public void ProcessTeleportationTrollheim(final Player player) {
		WorldTasksManager.schedule(new WorldTask() {
		int loop;
		
		@Override
		public void run() {
			player.getInterfaceManager().closeChatBoxInterface();			
			WorldObject object = null;
			if (loop == 0) {
			Magic.sendTabletTeleport(player, 0, 0, new WorldTile(2891, 3673, 0));
			} else if (loop == 1) {
				player.setNextAnimation(new Animation(4731));
				//player.setNextGraphics(new Graphics(1));
			} else if (loop == 3) {
				player.closeInterfaces();
				stop();
			}
			loop++;
			}
		}, 0, 1);
	}



	
}