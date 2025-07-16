package com.rs.game.player.content.agility;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.ForceMovement;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;
import com.rs.utils.Utils.EntityDirection;

public class BarbarianOutpostAgility {

	
	
	public static void enterObstaclePipe(final Player player, WorldObject object) {
		if(!Agility.hasLevel(player, 35))
			return;
		 player.lock(4);
		 player.setNextAnimation(new Animation(10580));
		 final WorldTile toTile = new WorldTile(object.getX(), player.getY() >= 3561 ?  3558 : 3561, object.getPlane());
		 player.setNextForceMovement(new ForceMovement(player, 0, toTile, 2, player.getY() >= 3561 ? ForceMovement.SOUTH : ForceMovement.NORTH));
		 WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.setNextWorldTile(toTile);
				player.getSkills().addXp(Skills.AGILITY, 1/20);
			}	
			 
		 }, 1);
	}
	
	public static void runUpWall(final Player player, WorldObject object) {
		if(!Agility.hasLevel(player, 90))
			return;
		player.lock(10);
		final WorldTile toTile = new WorldTile(2538, 3545, 2);
		 WorldTasksManager.schedule(new WorldTask() {
			 
			boolean secondLoop;
			@Override
			public void run() {
				
				if(!secondLoop) {
					player.setNextForceMovement(new ForceMovement(player, 7, toTile, 8, ForceMovement.NORTH));
					player.setNextAnimation(new Animation(10492));
					secondLoop = true;
				}else{
					player.setNextAnimation(new Animation(10493));
					player.setNextWorldTile(toTile);
					player.getSkills().addXp(Skills.AGILITY, 15);
					stop();
				}
				
			}
			 
		 }, 1, 6);
	}
	
	public static void climbUpWall(final Player player, WorldObject object) {
		if(!Agility.hasLevel(player, 90))
			return;
		player.useStairs(10023, new WorldTile(2536, 3546, 3), 2, 3);
		 WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.setNextAnimation(new Animation(11794));
				player.getSkills().addXp(Skills.AGILITY, 15);
			}
			 
		 }, 1);
	}
	
	public static void fireSpringDevice(final Player player, final WorldObject object) {
		if(!Agility.hasLevel(player, 90))
			return;
		player.lock(5);
		player.addWalkSteps(2533, 3547, -1, false);
		final WorldTile toTile = new WorldTile(2532, 3553, 3);
		 WorldTasksManager.schedule(new WorldTask() {
			 
			 boolean secondLoop;
			 
			@Override
			public void run() {
				if(!secondLoop) {
					player.setNextForceMovement(new ForceMovement(player, 1, toTile, 3, ForceMovement.NORTH));
					player.setNextAnimation(new Animation(4189));
					World.sendObjectAnimation(player, object, new Animation(11819));
					
					secondLoop = true;
				}else{
					player.setNextWorldTile(toTile);
					player.getSkills().addXp(Skills.AGILITY, 15);
					stop();
				}
			}
			 
		 }, 1, 1);
	}
	
	public static void crossBalanceBeam(final Player player, final WorldObject object) {
		if(!Agility.hasLevel(player, 90))
			return;
		player.lock(4);
		final WorldTile toTile = new WorldTile(2536, 3553, 3);
		player.setNextForceMovement(new ForceMovement(player, 1, toTile, 3, ForceMovement.EAST));
		player.setNextAnimation(new Animation(16079));
		player.getAppearence().setRenderEmote(330);
		 WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.setNextWorldTile(toTile);
				player.getSkills().addXp(Skills.AGILITY, 15);
				player.setNextAnimation(new Animation(-1));
				stop();
			}
			 
		 }, 2);
	}
	
	
	public static void jumpOverGap(final Player player, final WorldObject object) {
		if(!Agility.hasLevel(player, 90))
			return;
		player.lock(1);
		player.setNextAnimation(new Animation(2586));
		player.getAppearence().setRenderEmote(-1);
		 WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.setNextWorldTile(new WorldTile(2538, 3553, 2));
				player.setNextAnimation(new Animation(2588));
				player.getSkills().addXp(Skills.AGILITY, 15);
				stop();
			}
			 
		 }, 0);
	}
	
	public static void slideDownRoof(final Player player, final WorldObject object) {
		if(!Agility.hasLevel(player, 90))
			return;
		player.lock(6);
		player.setNextAnimation(new Animation(11792));
		final WorldTile toTile = new WorldTile(2544, player.getY(), 0);
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 5, ForceMovement.EAST));
		 WorldTasksManager.schedule(new WorldTask() {
			 int stage;
			@Override
			public void run() {
				if(stage == 0) {
					player.setNextWorldTile(new WorldTile(2541, player.getY(), 1));
					player.setNextAnimation(new Animation(11790));
					stage = 1;
				}else if (stage == 1) {
					stage = 2;
				}else if (stage == 2) {
					player.setNextAnimation(new Animation(11791));
					stage = 3;
				}else if (stage == 3) {
					player.setNextWorldTile(toTile);
					player.setNextAnimation(new Animation(2588));
					player.getSkills().addXp(Skills.AGILITY, 15);
					 if (getStage(player) == 1) {
						removeStage(player);
						player.getSkills().addXp(Skills.AGILITY, 615);
					}
					stop();
				}
			}
			 
		 }, 0, 0);
	}
	
	public static void Whirlpool(final Player player, final WorldObject object) {
		if(!Agility.hasLevel(player, 1))
			return;
		player.lock(6);
		player.setNextWorldTile(new WorldTile(2512, 3517, 0));
		player.setNextAnimation(new Animation(6723));
		player.setNextFaceWorldTile(new WorldTile(2512, 3514, 0));
		player.setNextForceMovement(new ForceMovement(new WorldTile(2512, 3509, 0), 7,player.getX() == 2512 ? 2 : 2));
	}
		
	public static void Minecart(final Player player, final WorldObject object) {
		if(!Agility.hasLevel(player, 1))
			return;
		player.lock(6);
		player.getInterfaceManager().MinecartRide();
		final WorldTile toTile = new WorldTile(2936, 10171, 0);
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 10, ForceMovement.EAST));
		 WorldTasksManager.schedule(new WorldTask() {
			 int stage;
			@Override
			public void run() {
				if(stage == 0) {
					player.setNextWorldTile(new WorldTile(2909, 10171, 0));
					stage = 1;
				}else if (stage == 1) {
					stage = 2;
				}else if (stage == 2) {
					stage = 3;
				}else if (stage == 3) {
					player.setNextWorldTile(toTile);
					 if (getStage(player) == 1) {
						removeStage(player);
					}
					stop();
				}
			}
			 
		 }, 0, 0);
	}
	public static void WheatWalkEAST(final Player player, final WorldObject object) {
		if(!Agility.hasLevel(player, 1))
			return;
		player.lock(2);
		player.setNextAnimation(new Animation(6595));
		final WorldTile toTile = new WorldTile((player.getX()+2),(player.getY()), player.getPlane());
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 9, ForceMovement.EAST));
		 WorldTasksManager.schedule(new WorldTask() {
			 int stage;
			@Override
			public void run() {
				if(stage == 0) {
					player.setNextWorldTile(new WorldTile(player.getX(), player.getY(), player.getPlane()));
					stage = 1;
				}else if (stage == 1) {
					stage = 2;
				}else if (stage == 2) {
					stage = 3;
				}else if (stage == 3) {
					player.setNextWorldTile(toTile);
					 if (getStage(player) == 1) {
						removeStage(player);
					}
					stop();
				}
			}
			 
		 }, 0, 0);
	}
	public static void WheatWalkWEST(final Player player, final WorldObject object) {
		if(!Agility.hasLevel(player, 1))
			return;
		player.lock(2);
		player.setNextAnimation(new Animation(6595));
		final WorldTile toTile = new WorldTile((player.getX()-2),(player.getY()), player.getPlane());
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 9, ForceMovement.WEST));
		 WorldTasksManager.schedule(new WorldTask() {
			 int stage;
			@Override
			public void run() {
				if(stage == 0) {
					player.setNextWorldTile(new WorldTile(player.getX(), player.getY(), player.getPlane()));
					stage = 1;
				}else if (stage == 1) {
					stage = 2;
				}else if (stage == 2) {
					stage = 3;
				}else if (stage == 3) {
					player.setNextWorldTile(toTile);
					 if (getStage(player) == 1) {
						removeStage(player);
					}
					stop();
				}
			}
			 
		 }, 0, 0);
	}
	public static void WheatWalkNORTH(final Player player, final WorldObject object) {
		if(!Agility.hasLevel(player, 1))
			return;
		player.lock(2);
		player.setNextAnimation(new Animation(6595));
		final WorldTile toTile = new WorldTile((player.getX()),(player.getY()+2), player.getPlane());
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 9, ForceMovement.NORTH));
		 WorldTasksManager.schedule(new WorldTask() {
			 int stage;
			@Override
			public void run() {
				if(stage == 0) {
					player.setNextWorldTile(new WorldTile(player.getX(), player.getY(), player.getPlane()));
					stage = 1;
				}else if (stage == 1) {
					stage = 2;
				}else if (stage == 2) {
					stage = 3;
				}else if (stage == 3) {
					player.setNextWorldTile(toTile);
					 if (getStage(player) == 1) {
						removeStage(player);
					}
					stop();
				}
			}
			 
		 }, 0, 0);
	}
	public static void WheatWalkSOUTH(final Player player, final WorldObject object) {
		if(!Agility.hasLevel(player, 1))
			return;
		player.lock(2);
		player.setNextAnimation(new Animation(6595));
		final WorldTile toTile = new WorldTile((player.getX()),(player.getY()-2), player.getPlane());
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 9, ForceMovement.SOUTH));
		 WorldTasksManager.schedule(new WorldTask() {
			 int stage;
			@Override
			public void run() {
				if(stage == 0) {
					player.setNextWorldTile(new WorldTile(player.getX(), player.getY(), player.getPlane()));
					stage = 1;
				}else if (stage == 1) {
					stage = 2;
				}else if (stage == 2) {
					stage = 3;
				}else if (stage == 3) {
					player.setNextWorldTile(toTile);
					 if (getStage(player) == 1) {
						removeStage(player);
					}
					stop();
				}
			}
			 
		 }, 0, 0);
	}
	public static void WhirlpoolWalk(final Player player, final WorldObject object) {
		if(!Agility.hasLevel(player, 1))
			return;
		player.lock(6);
		player.setNextAnimation(new Animation(16645));
		final WorldTile toTile = new WorldTile(2512, 3517, 0);
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 5, ForceMovement.NORTH));
		 WorldTasksManager.schedule(new WorldTask() {
			 int stage;
			@Override
			public void run() {
				if(stage == 0) {
					player.setNextWorldTile(new WorldTile(2512, 3511, 0));
					stage = 1;
				}else if (stage == 1) {
					stage = 2;
				}else if (stage == 2) {
					stage = 3;
				}else if (stage == 3) {
					player.setNextWorldTile(toTile);
					 if (getStage(player) == 1) {
						removeStage(player);
					}
					stop();
				}
			}
			 
		 }, 0, 0);
	}

	public static void ElectricChair(final Player player, final WorldObject object) {
		if(!Agility.hasLevel(player, 1))
			return;
		player.lock(6);
		player.setNextAnimation(new Animation(4885));
		player.setNextGraphics(new Graphics(807));
		player.setNextWorldTile(new WorldTile(1962, 5150, 0));
		final WorldTile toTile = new WorldTile(1962, 5150, 0);
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 5, ForceMovement.EAST));
		 WorldTasksManager.schedule(new WorldTask() {
			 int stage;
			@Override
			public void run() {
				if(stage == 0) {
					player.setNextWorldTile(new WorldTile(1962, 5150, 0));
					stage = 1;
				}else if (stage == 1) {
					stage = 2;
				}else if (stage == 2) {
					stage = 3;
				}else if (stage == 3) {
					player.setNextWorldTile(toTile);
					player.getSkills().addXp(Skills.AGILITY, 15);
					 if (getStage(player) == 1) {
						removeStage(player);
					}
					stop();
				}
			}
			 
		 }, 0, 0);
	}
	
	public static void GoblinStart(final Player player, final WorldObject object) {
		if(!Agility.hasLevel(player, 1))
			return;
		player.lock(6);
		player.setNextAnimation(new Animation(-1));
		player.getAppearence().setRenderEmote(1463);
		player.setNextWorldTile(new WorldTile(2343, 4241, 1));
		final WorldTile toTile = new WorldTile(2343, 4241, 1);
		 WorldTasksManager.schedule(new WorldTask() {
			 int stage;
			@Override
			public void run() {
				if(stage == 0) {
					player.setNextWorldTile(new WorldTile(2343, 4241, 1));
					stage = 1;
				}else if (stage == 1) {
					stage = 2;
				}else if (stage == 2) {
					stage = 3;
				}else if (stage == 3) {
					player.setNextWorldTile(toTile);
					player.getSkills().addXp(Skills.AGILITY, 15);
					 if (getStage(player) == 1) {
						removeStage(player);
					}
					stop();
				}
			}
			 
		 }, 0, 0);
	}
	
	public static void Choke(final Player player, final WorldObject object) {
		if(!Agility.hasLevel(player, 1))
			return;
		player.lock(6);
		player.setNextAnimation(new Animation(5862));
		player.setNextWorldTile(new WorldTile(3787, 2821, 0));
		final WorldTile toTile = new WorldTile(3787, 2821, 0);
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 5, ForceMovement.SOUTH));
		 WorldTasksManager.schedule(new WorldTask() {
			 int stage;
			@Override
			public void run() {
				if(stage == 0) {
					player.setNextWorldTile(new WorldTile(3787, 2821, 0));
					stage = 1;
				}else if (stage == 1) {
					stage = 2;
				}else if (stage == 2) {
					stage = 3;
				}else if (stage == 3) {
					player.setNextWorldTile(toTile);
					player.getSkills().addXp(Skills.AGILITY, 15);
					 if (getStage(player) == 1) {
						removeStage(player);
					}
					stop();
				}
			}
			 
		 }, 0, 0);
	}
	
	
	public static void swingOnRopeSwing(final Player player, WorldObject object) {
		if(!Agility.hasLevel(player, 35))
			return;
		if(player.getY() != 3554) {
			 player.getPackets().sendGameMessage("You'll need to get closer to make this jump.");
			 return;
		}
		 player.lock(4);
		 player.setNextAnimation(new Animation(751));
		 World.sendObjectAnimation(player, object, new Animation(497));
		 final WorldTile toTile = new WorldTile(object.getX(), 3549, object.getPlane());
		 player.setNextForceMovement(new ForceMovement(player, 1, toTile, 3, ForceMovement.SOUTH));
		player.getSkills().addXp(Skills.AGILITY, 22);
		player.getPackets().sendGameMessage("You skilfully swing across.", true);
		 WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.setNextWorldTile(toTile);
				setStage(player, 0);
			}	
			 
		 }, 1);
	}
	public static void swingOnRopeSwing2(final Player player, WorldObject object) {
		if(!Agility.hasLevel(player, 35))
			return;
		if(player.getY() != 9567) {
			 player.getPackets().sendGameMessage("You'll need to get closer to make this jump.");
			 return;
		}
		 player.lock(4);
		 player.setNextAnimation(new Animation(751));
		 World.sendObjectAnimation(player, object, new Animation(497));
		 final WorldTile toTile = new WorldTile(2764, object.getY(), object.getPlane());
		 player.setNextForceMovement(new ForceMovement(player, 1, toTile, 3, ForceMovement.WEST));
		player.getSkills().addXp(Skills.AGILITY, 22);
		player.getPackets().sendGameMessage("You skilfully swing across.", true);
		 WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.setNextWorldTile(toTile);
				setStage(player, 1);
			}	
		
		 }, 0);
	}
	public static void MatrixWalk(final Player player, final WorldObject object) {
		if(!Agility.hasLevel(player, 1))
			return;
		player.lock(6);
		player.setNextAnimation(new Animation(16645));
		final WorldTile toTile = new WorldTile(2777, 9568, 3);
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 5, ForceMovement.EAST));
		 WorldTasksManager.schedule(new WorldTask() {
			 int stage;
			@Override
			public void run() {
				if(stage == 0) {
					player.setNextWorldTile(new WorldTile(2773, 9568, 3));
					stage = 1;
				}else if (stage == 1) {
					stage = 2;
				}else if (stage == 2) {
					stage = 3;
				}else if (stage == 3) {
					player.setNextWorldTile(toTile);
					 if (getStage(player) == 1) {
						removeStage(player);
					}
					stop();
				}
			}
			 
		 }, 0, 0);
	}
	public static void Matrix(final Player player, final WorldObject object) {
		if(!Agility.hasLevel(player, 1))
			return;
		
		player.lock(6);
		player.setNextWorldTile(new WorldTile(2777, 9568, 3));
		player.setNextAnimation(new Animation(1110));
		player.setNextFaceWorldTile(new WorldTile(2778, 9568, 3));
		
	}
	public static void MatrixDart(final Player player, final WorldObject object) {
		World.sendProjectile(player, (new WorldTile((player.getX()+6), player.getY(), player.getPlane())), (new WorldTile((player.getX()-10), player.getY(), player.getPlane())), 270, 0, 0, 20, 0, 0, 0);
	}

	public static void walkAcrossLogBalance(final Player player, final WorldObject object) {
		if(!Agility.hasLevel(player, 35))
			return;
		if(player.getY() != object.getY()) {
			player.addWalkSteps(2777, 9568, -1, false);
			player.lock(2);
			WorldTasksManager.schedule(new WorldTask() {

				@Override
				public void run() {
					walkAcrossLogBalanceEnd(player, object);
				}
			}, 1);
		}else
			walkAcrossLogBalanceEnd(player, object);
	}
	
	private static void walkAcrossLogBalanceEnd(final Player player, WorldObject object) {
		 player.getPackets().sendGameMessage("You walk carefully across the slippery log...", true);
		 player.lock(17);
		 player.setNextAnimation(new Animation(9908));
		 final WorldTile toTile = new WorldTile(2541, object.getY(), object.getPlane());
		 player.setNextForceMovement(new ForceMovement(player, 0, toTile, 16, ForceMovement.WEST));
		 WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.setNextWorldTile(toTile);
				player.getSkills().addXp(Skills.AGILITY, 13);
				 player.getPackets().sendGameMessage("... and make it safely to the other side.", true);
				 if (getStage(player) == 0)
						setStage(player, 1);
			}	
			 
		 }, 15);
	}
	
	public static void walkAcrossBalancingLedge(final Player player, final WorldObject object) {
		if(!Agility.hasLevel(player, 35))
			return;
		 player.getPackets().sendGameMessage("You put your food on the ledge and try to edge across...", true);
		 player.lock(5);
		 player.setNextAnimation(new Animation(753));
		 player.getAppearence().setRenderEmote(157);
		 final WorldTile toTile = new WorldTile(2532, object.getY(), object.getPlane());
		 player.setRun(true);
		 player.addWalkSteps(toTile.getX(), toTile.getY(), -1, false);
		 WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				 player.setNextAnimation(new Animation(759));
				 player.getAppearence().setRenderEmote(-1);
				player.getSkills().addXp(Skills.AGILITY, 22);
				player.getPackets().sendGameMessage("You skilfully edge across the gap.", true);
				if (getStage(player) == 2)
					setStage(player, 3);
			}	 
		 }, 3);
	}
	
	
	public static void climbObstacleNet(final Player player, WorldObject object) {
		if(!Agility.hasLevel(player, 35) || player.getY() < 3545 || player.getY() > 3547) 
			return;
		player.getPackets().sendGameMessage("You climb the netting...", true);
		player.getSkills().addXp(Skills.AGILITY, 8.2);
		player.useStairs(828, new WorldTile(object.getX()-1, player.getY(), 1), 1, 2);
		if (getStage(player) == 1)
			setStage(player, 2);
	}
	
	public static void climbOverCrumblingWall(final Player player, WorldObject object) {
		if(!Agility.hasLevel(player, 35))
			return;
		if(player.getX() >= object.getX()) {
			 player.getPackets().sendGameMessage("You cannot climb that from this side.");
			 return;
		}
		player.getPackets().sendGameMessage("You climb the low wall...", true);
		 player.lock(3);
		 player.setNextAnimation(new Animation(4853));
		 final WorldTile toTile = new WorldTile(object.getX()+1, object.getY(), object.getPlane());
		 player.setNextForceMovement(new ForceMovement(player, 0, toTile, 2, ForceMovement.EAST));
		 WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.setNextWorldTile(toTile);
				player.getSkills().addXp(Skills.AGILITY, 13.7);
				int stage = getStage(player);
				if (stage == 3)
					setStage(player, 4);
				else if (stage == 4) {
					removeStage(player);
					player.getSkills().addXp(Skills.AGILITY, 46.2);
				}
			}	
			 
		 }, 1);
	}
	public static void OriginalBalloon(final Player player) {
		
		WorldTasksManager.schedule(new WorldTask() {
			int stage;
		@Override
		public void run() {
			if (stage == 0) {
				player.lock();
			
				player.setNextAnimation(new Animation(6665));
				player.setNextGraphics(new Graphics(1157));
			} else if (stage == 2) {
				World.sendProjectile(player, (new WorldTile(player.getX(), player.getY(), player.getPlane())), (new WorldTile((player.getX()+2), player.getY(), player.getPlane())), 1158, 50, 1, 1, 35, 10, 0);
			} else if (stage == 4) {
				World.sendGraphics(player, new Graphics(1159),
						new WorldTile(player.getX() + 2, player.getY(),
								player.getPlane()));
			} else if (stage == 8) {
				stop();
				player.unlock();
				}
				stage++;
			}
			 
	 }, 0, 1);
}
	public static void removeStage(Player player) {
		player.getTemporaryAttributtes().remove("BarbarianOutpostCourse");
	}

	public static void setStage(Player player, int stage) {
		player.getTemporaryAttributtes().put("BarbarianOutpostCourse", stage);
	}

	public static int getStage(Player player) {
		Integer stage = (Integer) player.getTemporaryAttributtes().get(
				"BarbarianOutpostCourse");
		if (stage == null)
			return -1;
		return stage;
	}
	
}
