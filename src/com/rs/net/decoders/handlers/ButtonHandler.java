package com.rs.net.decoders.handlers;

import java.util.HashMap;
import java.util.TimerTask;

import com.rs.Settings;
import com.rs.game.player.content.grandexchange.GrandExchange;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.content.skills.SummoningScroll;
import com.rs.cores.CoresManager;
import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.minigames.Crucible;
import com.rs.game.minigames.duel.DuelControler;
import com.rs.game.npc.NPC;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.npc.familiar.Familiar.SpecialAttack;
import com.rs.game.player.AccountManager;
import com.rs.game.player.ClansManager;
import com.rs.game.player.CombatDefinitions;
import com.rs.game.player.EmotesManager;
import com.rs.game.player.Equipment;
import com.rs.game.player.Inventory;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.SlayerTask;
import com.rs.game.player.actions.FightPitsViewingOrb;
import com.rs.game.player.actions.HomeTeleport;
import com.rs.game.player.actions.Rest;
import com.rs.game.player.actions.Smithing.ForgingInterface;
import com.rs.game.player.actions.Summoning;
import com.rs.game.player.content.AdventurersLog;
import com.rs.game.player.content.ClanCitadelTheatre;
import com.rs.game.player.content.ItemConstants;
import com.rs.game.player.content.Magic;
import com.rs.game.player.content.Notes.Note;
import com.rs.game.player.content.PlayerCustoms;
//import com.rs.game.player.content.PlayerDesign;
import com.rs.game.player.content.PlayerLook;
import com.rs.game.player.content.PointShop;
import com.rs.game.player.content.Runecrafting;
import com.rs.game.player.content.Shop;
import com.rs.game.player.content.SkillCapeCustomizer;
import com.rs.game.player.content.SkillsDialogue;
import com.rs.game.player.content.SpiritTree;
import com.rs.game.player.content.StaffList;
import com.rs.game.player.content.TeletoRequest;
import com.rs.game.player.content.TicketSystem;
import com.rs.game.player.content.TriviaBot;
import com.rs.game.player.content.construction.House;
import com.rs.game.player.content.interfaces.AccountInformation;
import com.rs.game.player.content.interfaces.AccountSettings;
import com.rs.game.player.content.interfaces.DTAgility;
import com.rs.game.player.content.interfaces.DTMonsters;
import com.rs.game.player.content.interfaces.DTRSMVActionLocs;
import com.rs.game.player.content.interfaces.DTWoodcuttingTeleports;
import com.rs.game.player.content.interfaces.LyreMainMenu;
import com.rs.game.player.content.transportation.GnomeGlider;
import com.rs.game.player.dialogues.impl.LevelUp;
import com.rs.game.player.dialogues.impl.Transportation;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.io.InputStream;
import com.rs.net.decoders.WorldPacketsDecoder;
import com.rs.utils.ItemExamines;
import com.rs.utils.Logger;
import com.rs.utils.Utils;

public class ButtonHandler {

	public static void handleButtons(final Player player, InputStream stream, int packetId) {
		int interfaceHash = stream.readIntV2();
		int interfaceId = interfaceHash >> 16;
		if (Utils.getInterfaceDefinitionsSize() <= interfaceId) {
			// hack, or server error or client error
			// player.getSession().getChannel().close();
			return;
		}
		if (player.isDead() || !player.getInterfaceManager().containsInterface(interfaceId))
			return;
		final int componentId = interfaceHash - (interfaceId << 16);
		if (componentId != 65535 && Utils.getInterfaceDefinitionsComponentsSize(interfaceId) <= componentId) {
			// hack, or server error or client error
			// player.getSession().getChannel().close();
			return;
		}
		final int slotId2 = stream.readUnsignedShort128();
		final int slotId = stream.readUnsignedShortLE128();
		if (!player.getControlerManager().processButtonClick(interfaceId, componentId, slotId, packetId))
			return;
		if (interfaceId == 105 || interfaceId == 107) {
			// GrandExchange.get().handleButtonEvent(player, interfaceId,
			// componentId, packetId, slotId, slotId2);
		}
		if (interfaceId == 190 && componentId == 15) {
			if (slotId == 68) {
				if (player.spokeToWarrior == false && player.spokeToShamus == false) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Lost-City Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11, "Speak to the Warrior West of Draynor");
					player.getPackets().sendIComponentText(275, 12, "<u>Requirements</u>");
					player.getPackets().sendIComponentText(275, 13, "<col=ffff00>31 Crafting, 36 Woodcutting</col>");
					player.getPackets().sendIComponentText(275, 14, "-=-Tips-=-");
					player.getPackets().sendIComponentText(275, 15, "Use the 'Quests & Minigames' teleport at home to start the quest.");
					player.getPackets().sendIComponentText(275, 16, "The lodestone works, remember to take full use of it.");
					player.getPackets().sendIComponentText(275, 17, "You will need the skills required to complete the quest");
					player.getPackets().sendIComponentText(275, 18, "The Monk Of Entrana removes everything in your inventory.");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
				} else if (player.spokeToWarrior == true && player.spokeToShamus == false) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Lost-City Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11, "Shamus appears to be in one of the trees around this location.");
					player.getPackets().sendIComponentText(275, 12, "The Warrior told me the tree displays 'Chop Tree'");
					player.getPackets().sendIComponentText(275, 13, "");
					player.getPackets().sendIComponentText(275, 14, "-=-Tips-=-");
					player.getPackets().sendIComponentText(275, 15, "Use the 'Quests & Minigames' teleport at home to start the quest.");
					player.getPackets().sendIComponentText(275, 16, "The lodestone works, remember to take full use of it.");
					player.getPackets().sendIComponentText(275, 17, "You will need the skills required to complete the quest");
					player.getPackets().sendIComponentText(275, 18, "The Monk Of Entrana removes everything in your inventory.");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
				} else if (player.spokeToWarrior == true && player.spokeToShamus == true) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Lost-City Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11, "I should go find the Monk of Entrana, Who is located at Port Sarim.");
					player.getPackets().sendIComponentText(275, 12, "");
					player.getPackets().sendIComponentText(275, 13, "");
					player.getPackets().sendIComponentText(275, 14, "-=-Tips-=-");
					player.getPackets().sendIComponentText(275, 15, "Use the 'Quests & Minigames' teleport at home to start the quest.");
					player.getPackets().sendIComponentText(275, 16, "The lodestone works, remember to take full use of it.");
					player.getPackets().sendIComponentText(275, 17, "You will need the skills required to complete the quest");
					player.getPackets().sendIComponentText(275, 18, "The Monk Of Entrana removes everything in your inventory.");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
				} else if (player.spokeToWarrior == true && player.spokeToShamus == true && player.spokeToMonk == true) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Lost-City Quest");
					player.getPackets().sendIComponentText(275, 10, "The other side of Entrana is a ladder which leads to a cave");
					player.getPackets().sendIComponentText(275, 11, "I should go down the ladder and search for the dramen tree.");
					player.getPackets().sendIComponentText(275, 12, "In order to chop the dramen tree I must have a axe.");
					player.getPackets().sendIComponentText(275, 13, "The zombies must drop a axe of some sort.");
					player.getPackets().sendIComponentText(275, 14, "-=-Tips-=-");
					player.getPackets().sendIComponentText(275, 15, "Use the 'Quests & Minigames' teleport at home to start the quest.");
					player.getPackets().sendIComponentText(275, 16, "The lodestone works, remember to take full use of it.");
					player.getPackets().sendIComponentText(275, 17, "You will need the skills required to complete the quest");
					player.getPackets().sendIComponentText(275, 18, "The Monk Of Entrana removes everything in your inventory.");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
				} else if (player.spokeToWarrior == true && player.spokeToShamus == true && player.spokeToMonk == true && player.lostCity == 1) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Lost-City Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11, "");
					player.getPackets().sendIComponentText(275, 12, "Congratulations Quest Complete!");
					player.getPackets().sendIComponentText(275, 13, "");
					player.getPackets().sendIComponentText(275, 14, "");
					player.getPackets().sendIComponentText(275, 15, "");
					player.getPackets().sendIComponentText(275, 16, "");
					player.getPackets().sendIComponentText(275, 17, "");
					player.getPackets().sendIComponentText(275, 18, "");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
				}
			}
		}
	else if ((interfaceId == 590 && componentId == 8) || interfaceId == 464) {
		player.getEmotesManager().useBookEmote(slotId2 + 2);
	
	} else if (interfaceId == 1019) {
			if (componentId == 18) {
				AccountInformation.sendInterface(player);
				return;
			} else if (componentId == 16) {
				player.getPackets().sendInputLongTextScript("Please enter your answer:");
				player.getTemporaryAttributtes().put("answer_trivia", Boolean.TRUE);
				return;
			} else if (componentId == 9) {
				if (player.isLocked() || player.getRights() == 3 || player.getRights() == 4) {
					player.sm("You may not submit a ticket! You are a staff member.");
				} else {
					TicketSystem.requestTicket(player);
				}
			}
		}
		if (interfaceId == 506) {
			PlayerCustoms.handleButtons(player, componentId);
		}

		if (interfaceId == 72) {
			AccountSettings.handleButtons(player, componentId);
		}// sorry keyboard stuckbecause it's wrieless
			// p213123
		if (interfaceId == 1008) {
			TeletoRequest.handleButtons(player, componentId);
		}

		if (interfaceId == 548 || interfaceId == 746) {
			if ((interfaceId == 548 && componentId == 148) || (interfaceId == 746 && componentId == 199)) {
				if (player.getInterfaceManager().containsScreenInter() || player.getInterfaceManager().containsInventoryInter()) {
					// TODO cant open sound
					player.getPackets().sendGameMessage("Please finish what you're doing before opening the world map.");
					return;
				}
				// world map open
				player.getPackets().sendRootInterface(755, 0);
				player.getPackets().sendGlobalConfig(622, player.getTileHash()); // center
				player.getPackets().sendGlobalConfig(674, player.getTileHash()); // player
				// position
			} else if ((interfaceId == 548 && componentId == 194) || (interfaceId == 746 && componentId == 204)) {
				player.getMoneyPouch().switchPouch();
			} else if ((interfaceId == 548 && componentId == 17) || (interfaceId == 746 && componentId == 54)) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					player.getSkills().switchXPDisplay();
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
					player.getSkills().switchXPPopup();
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
					player.getSkills().setupXPCounter();
			} else if ((interfaceId == 746 && componentId == 207) || (interfaceId == 548 && componentId == 159)) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					player.getMoneyPouch().switchPouch();
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
					player.getMoneyPouch().withdrawPouch();
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
					player.getMoneyPouch().examinePouch();
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					if (player.getInterfaceManager().containsScreenInter() || player.isLocked()) {
						player.getPackets().sendGameMessage("Please finish what you're doing before opening the price checker.");
						return;
					}
					player.stopAll();
					player.getPriceCheckManager().openPriceCheck();
				}
			}
		} else if (interfaceId == 34) {// notes interface
			switch (componentId) {
			case 35:
			case 37:
			case 39:
			case 41:
				Note current = (Note) player.getTemporaryAttributtes().get("curNote");
				current.setColour(componentId - (34 + (componentId == 35 ? 0 : componentId + 1)));
				player.getNotes().refresh();
				player.getPackets().sendHideIComponent(34, 16, true);
				break;
			case 3:
				player.getPackets().sendRunScript(109, new Object[] { "Please enter the note text." });
				player.getTemporaryAttributtes().put("entering_note", Boolean.TRUE);
				break;
			case 9:
				switch (packetId) {
				case WorldPacketsDecoder.ACTION_BUTTON1_PACKET:
					Note note = player.getNotes().getNotes().get(slotId);
					if (player.getTemporaryAttributtes().get("curNote") == note) {
						player.getTemporaryAttributtes().remove("curNote");
						player.getPackets().sendConfig(1439, -1);
						return;
					} else {
						player.getTemporaryAttributtes().put("curNote", note);
						player.getPackets().sendConfig(1439, slotId);
					}
					break;
				case WorldPacketsDecoder.ACTION_BUTTON2_PACKET:
					player.getTemporaryAttributtes().put("curNote", player.getNotes().getNotes().get(slotId));
					player.getPackets().sendRunScript(109, new Object[] { "Please edit the note text." });
					player.getTemporaryAttributtes().put("editing_note", Boolean.TRUE);
					player.getPackets().sendConfig(1439, slotId);
					break;
				case WorldPacketsDecoder.ACTION_BUTTON3_PACKET:
					player.getTemporaryAttributtes().put("curNote", player.getNotes().getNotes().get(slotId));
					player.getPackets().sendHideIComponent(34, 16, false);
					player.getPackets().sendConfig(1439, slotId);
					break;
				}
				break;
			case 8:
				Note note = (Note) player.getTemporaryAttributtes().get("curNote");
				player.getNotes().remove(note);
				break;
			}

		} else if (interfaceId == 182) {
			if (player.getInterfaceManager().containsInventoryInter())
				return;
			if (componentId == 6 || componentId == 13)
				if (!player.hasFinished())
					player.logout(componentId == 6);
		} else if (interfaceId == 1165) {
			// if (componentId == 22)
			// Summoning.closeDreadnipInterface(player);
		} else if (interfaceId == 60) {
			PointShop.handle(componentId, interfaceId, player);
		} else if (interfaceId == 880) {
			if (componentId >= 7 && componentId <= 19)
				Familiar.setLeftclickOption(player, (componentId - 7) / 2);
			else if (componentId == 21)
				Familiar.confirmLeftOption(player);
			else if (componentId == 25)
				Familiar.setLeftclickOption(player, 7);
		} else if (interfaceId == 662) {
			if (player.getFamiliar() == null) {
				if (player.getPet() == null) {
					return;
				}
				if (componentId == 49)
					player.getPet().call();
				else if (componentId == 51)
					player.getDialogueManager().startDialogue("DismissD");
				return;
			}
			if (componentId == 49)
				player.getFamiliar().call();
			else if (componentId == 51)
				player.getDialogueManager().startDialogue("DismissD");
			else if (componentId == 67)
				player.getFamiliar().takeBob();
			else if (componentId == 69)
				player.getFamiliar().renewFamiliar();
			else if (componentId == 74) {
				if (player.getFamiliar().getSpecialAttack() == SpecialAttack.CLICK)
					player.getFamiliar().setSpecial(true);
				if (player.getFamiliar().hasSpecialOn())
					player.getFamiliar().submitSpecial(player);
			}
		} else if (interfaceId == 747) {
			if (componentId == 8) {
				Familiar.selectLeftOption(player);
			} else if (player.getPet() != null) {
				if (componentId == 11 || componentId == 20) {
					player.getPet().call();
				} else if (componentId == 12 || componentId == 21) {
					player.getDialogueManager().startDialogue("DismissD");
				} else if (componentId == 10 || componentId == 19) {
					player.getPet().sendFollowerDetails();
				}
			} else if (player.getFamiliar() != null) {
				if (componentId == 11 || componentId == 20)
					player.getFamiliar().call();
				else if (componentId == 12 || componentId == 21)
					player.getDialogueManager().startDialogue("DismissD");
				else if (componentId == 13 || componentId == 22)
					player.getFamiliar().takeBob();
				else if (componentId == 14 || componentId == 23)
					player.getFamiliar().renewFamiliar();
				else if (componentId == 19 || componentId == 10)
					player.getFamiliar().sendFollowerDetails();
				else if (componentId == 18) {
					if (player.getFamiliar().getSpecialAttack() == SpecialAttack.CLICK)
						player.getFamiliar().setSpecial(true);
					if (player.getFamiliar().hasSpecialOn())
						player.getFamiliar().submitSpecial(player);
				}
			}
		} else if (interfaceId == 309)
			PlayerLook.handleHairdresserSalonButtons(player, componentId, slotId);
		else if (interfaceId == 729)
			PlayerLook.handleThessaliasMakeOverButtons(player, componentId, slotId);
		else if (interfaceId == 728)
			PlayerLook.handleYrsaShoes(player, componentId, slotId);
		else if (interfaceId == 187) {
			if (componentId == 1) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					player.getMusicsManager().playAnotherMusic(slotId / 2);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
					player.getMusicsManager().sendHint(slotId / 2);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
					player.getMusicsManager().addToPlayList(slotId / 2);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
					player.getMusicsManager().removeFromPlayList(slotId / 2);
			} else if (componentId == 4)
				player.getMusicsManager().addPlayingMusicToPlayList();
			else if (componentId == 10)
				player.getMusicsManager().switchPlayListOn();
			else if (componentId == 11)
				player.getMusicsManager().clearPlayList();
			else if (componentId == 13)
				player.getMusicsManager().switchShuffleOn();
		} else if ((interfaceId == 590 && componentId == 8) || interfaceId == 464) {
			player.getEmotesManager().useBookEmote(interfaceId == 464 ? componentId : EmotesManager.getId(slotId, packetId));
		} else if (interfaceId == 192) {
			if (componentId == 2)
				player.getCombatDefinitions().switchDefensiveCasting();
			else if (componentId == 7)
				player.getCombatDefinitions().switchShowCombatSpells();
			else if (componentId == 9)
				player.getCombatDefinitions().switchShowTeleportSkillSpells();
			else if (componentId == 11)
				player.getCombatDefinitions().switchShowMiscallaneousSpells();
			else if (componentId == 13)
				player.getCombatDefinitions().switchShowSkillSpells();
			else if (componentId >= 15 & componentId <= 17)
				player.getCombatDefinitions().setSortSpellBook(componentId - 15);
			else
				Magic.processNormalSpell(player, componentId, packetId);
		} else if (interfaceId == 398) {
			if (componentId == 19)
				player.getInterfaceManager().sendSettings();
			/*
			 * else if (componentId == 15 || componentId == 1)
			 * player.getHouse().setBuildMode(componentId == 15); else if
			 * (componentId == 25 || componentId == 26)
			 * player.getHouse().setArriveInPortal(componentId == 25); else if
			 * (componentId == 27) player.getHouse().expelGuests(); else if
			 * (componentId == 29) House.leaveHouse(player); } else if
			 * (interfaceId == 402) { if (componentId >= 93 && componentId <=
			 * 115) player.getHouse().createRoom(componentId - 93); } else if
			 * (interfaceId == 394 || interfaceId == 396) { if (componentId ==
			 * 11) player.getHouse().build(slotId);
			 */
		} else if (interfaceId == 334) {
			if (componentId == 22)
				player.closeInterfaces();
			else if (componentId == 21)
				player.getTrade().accept(false);
		} else if (interfaceId == 335) {
			if (componentId == 18)
				player.getTrade().accept(true);
			else if (componentId == 20)
				player.closeInterfaces();
			else if (componentId == 32) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					player.getTrade().removeItem(slotId, 1);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
					player.getTrade().removeItem(slotId, 5);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
					player.getTrade().removeItem(slotId, 10);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
					player.getTrade().removeItem(slotId, Integer.MAX_VALUE);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					player.getTemporaryAttributtes().put("trade_item_X_Slot", slotId);
					player.getTemporaryAttributtes().put("trade_isRemove", Boolean.TRUE);
					player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON9_PACKET)
					player.getTrade().sendValue(slotId, false);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET)
					player.getTrade().sendExamine(slotId, false);
			} else if (componentId == 35) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					player.getTrade().sendValue(slotId, true);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET)
					player.getTrade().sendExamine(slotId, true);
			}
		} else if (interfaceId == 336) {
			if (componentId == 0) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					player.getTrade().addItem(slotId, 1);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
					player.getTrade().addItem(slotId, 5);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
					player.getTrade().addItem(slotId, 10);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
					player.getTrade().addItem(slotId, Integer.MAX_VALUE);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					player.getTemporaryAttributtes().put("trade_item_X_Slot", slotId);
					player.getTemporaryAttributtes().remove("trade_isRemove");
					player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON9_PACKET)
					player.getTrade().sendValue(slotId);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET)
					player.getInventory().sendExamine(slotId);
			}
		} else if (interfaceId == 300) {
			ForgingInterface.handleIComponents(player, componentId);
		} else if (interfaceId == 206) {
			if (componentId == 15) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					player.getPriceCheckManager().removeItem(slotId, 1);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
					player.getPriceCheckManager().removeItem(slotId, 5);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
					player.getPriceCheckManager().removeItem(slotId, 10);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
					player.getPriceCheckManager().removeItem(slotId, Integer.MAX_VALUE);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					player.getTemporaryAttributtes().put("pc_item_X_Slot", slotId);
					player.getTemporaryAttributtes().put("pc_isRemove", Boolean.TRUE);
					player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
				}
			}
		} else if (interfaceId == 672) {
			if (componentId == 19)
				SummoningScroll.sendInterface(player);
			if (componentId == 16) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					Summoning.createPouch(player, slotId2, 1);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					Summoning.createPouch(player, slotId2, 5);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					Summoning.createPouch(player, slotId2, 10);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					Summoning.createPouch(player, slotId2, Integer.MAX_VALUE);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					Summoning.createPouch(player, slotId2, 28);// x
					player.getPackets().sendGameMessage("You currently need " + ItemDefinitions.getItemDefinitions(slotId2).getCreateItemRequirements());
				}

			}
		} else if (interfaceId == 207) {
			if (componentId == 0) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					player.getPriceCheckManager().addItem(slotId, 1);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
					player.getPriceCheckManager().addItem(slotId, 5);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
					player.getPriceCheckManager().addItem(slotId, 10);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
					player.getPriceCheckManager().addItem(slotId, Integer.MAX_VALUE);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					player.getTemporaryAttributtes().put("pc_item_X_Slot", slotId);
					player.getTemporaryAttributtes().remove("pc_isRemove");
					player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON9_PACKET)
					player.getInventory().sendExamine(slotId);
			}
		} else if (interfaceId == 665) {
			if (player.getFamiliar() == null || player.getFamiliar().getBob() == null)
				return;
			if (componentId == 0) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					player.getFamiliar().getBob().addItem(slotId, 1);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
					player.getFamiliar().getBob().addItem(slotId, 5);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
					player.getFamiliar().getBob().addItem(slotId, 10);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
					player.getFamiliar().getBob().addItem(slotId, Integer.MAX_VALUE);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					player.getTemporaryAttributtes().put("bob_item_X_Slot", slotId);
					player.getTemporaryAttributtes().remove("bob_isRemove");
					player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON9_PACKET)
					player.getInventory().sendExamine(slotId);
			}

			// INTERFACE PK
		} else if (interfaceId == 1312) {
			if (componentId == 35) {
				player.getInventory().addItem(4151, 1);
				player.getInventory().addItem(1079, 1);
				player.getInventory().addItem(1127, 1);
				player.getInventory().addItem(20072, 1);
				player.getInventory().addItem(1215, 1);
				player.getInventory().addItem(1052, 1);
				player.getInventory().addItem(6737, 1);
				player.getInventory().addItem(10828, 1);
				player.getInventory().addItem(11732, 1);
				player.getInventory().addItem(7462, 1);
				player.getInventory().addItem(6585, 1);
				player.closeInterfaces();
			}
			if (componentId == 43) {
				player.getInventory().addItem(2497, 1);
				player.getInventory().addItem(2503, 1);
				player.getInventory().addItem(7462, 1);
				player.getInventory().addItem(10828, 1);
				player.getInventory().addItem(861, 1);
				player.getInventory().addItem(892, 1000);
				player.getInventory().addItem(2577, 1);
				player.getInventory().addItem(10499, 1);
				player.getInventory().addItem(6733, 1);
				player.getInventory().addItem(6585, 1);
				player.closeInterfaces();
			}
			if (componentId == 51) {
				player.getInventory().addItem(6585, 1);
				player.getInventory().addItem(6920, 1);
				player.getInventory().addItem(7462, 1);
				player.getInventory().addItem(4103, 1);
				player.getInventory().addItem(4101, 1);
				player.getInventory().addItem(15486, 1);
				player.getInventory().addItem(6731, 1);
				player.getInventory().addItem(10828, 1);
				player.getInventory().addItem(2414, 1);
				player.getInventory().addItem(6889, 1);
				player.closeInterfaces();
			}
			if (componentId == 59) {
				player.getInventory().addItem(6585, 1);
				player.getInventory().addItem(6920, 1);
				player.getInventory().addItem(7462, 1);
				player.getInventory().addItem(4103, 1);
				player.getInventory().addItem(4101, 1);
				player.getInventory().addItem(15486, 1);
				player.getInventory().addItem(6731, 1);
				player.getInventory().addItem(10828, 1);
				player.getInventory().addItem(2414, 1);
				player.getInventory().addItem(6889, 1);
				player.getInventory().addItem(4151, 1);
				player.getInventory().addItem(1079, 1);
				player.getInventory().addItem(1127, 1);
				player.getInventory().addItem(20072, 1);
				player.getInventory().addItem(1215, 1);
				player.getInventory().addItem(1052, 1);
				player.getInventory().addItem(6737, 1);
				player.getInventory().addItem(10828, 1);
				player.getInventory().addItem(11732, 1);
				player.getInventory().addItem(7462, 1);
				player.getInventory().addItem(6585, 1);
				player.closeInterfaces();
			}
			if (componentId == 67) {
				player.getInventory().addItem(7462, 1);
				player.getInventory().addItem(2497, 1);
				player.getInventory().addItem(2503, 1);
				player.getInventory().addItem(1079, 1);
				player.getInventory().addItem(1163, 1);
				player.getInventory().addItem(6585, 1);
				player.getInventory().addItem(9185, 1);
				player.getInventory().addItem(9244, 1000);
				player.getInventory().addItem(1163, 1);
				player.getInventory().addItem(1201, 1);
				player.getInventory().addItem(2437, 10);
				player.getInventory().addItem(2441, 10);
				player.getInventory().addItem(2443, 10);
				player.getInventory().addItem(3025, 10);
				player.getInventory().addItem(386, 120);
				player.closeInterfaces();
			}
			if (componentId == 75) {
				player.getInventory().addItem(1067, 1);
				player.getInventory().addItem(1115, 1);
				player.getInventory().addItem(1153, 1);
				player.getInventory().addItem(4151, 1);
				player.getInventory().addItem(8845, 1);
				player.getInventory().addItem(3105, 1);
				player.getInventory().addItem(7459, 1);
				player.getInventory().addItem(6585, 1);
				player.getInventory().addItem(6737, 1);
				player.getInventory().addItem(2437, 10);
				player.getInventory().addItem(2441, 10);
				player.getInventory().addItem(2443, 10);
				player.getInventory().addItem(3025, 10);
				player.getInventory().addItem(1215, 1);
				player.closeInterfaces();
			}
			if (componentId == 83) {
				player.getInventory().addItem(1129, 1);
				player.getInventory().addItem(2497, 1);
				player.getInventory().addItem(2491, 1);
				player.getInventory().addItem(1169, 1);
				player.getInventory().addItem(3105, 1);
				player.getInventory().addItem(6733, 1);
				player.getInventory().addItem(11230, 100);
				player.getInventory().addItem(861, 1);
				player.getInventory().addItem(892, 1000);
				player.getInventory().addItem(9185, 1);
				player.getInventory().addItem(9244, 100);
				player.getInventory().addItem(11235, 1);
				player.getInventory().addItem(11212, 15);
				player.getInventory().addItem(2437, 10);
				player.getInventory().addItem(3025, 10);
				player.getInventory().addItem(2445, 10);
				player.closeInterfaces();
			}
			if (componentId == 91) {
				player.getInventory().addItem(6106, 1);
				player.getInventory().addItem(6107, 1);
				player.getInventory().addItem(6108, 1);
				player.getInventory().addItem(6109, 1);
				player.getInventory().addItem(6110, 1);
				player.getInventory().addItem(6111, 1);
				player.getInventory().addItem(4675, 1);
				player.getInventory().addItem(6889, 1);
				player.getInventory().addItem(6731, 1);
				player.closeInterfaces();
				// END

			}
			if (componentId == 99) {
				player.getPackets().sendGameMessage("Not added, Any suggestions?");
				player.closeInterfaces();
			}
		} else if (interfaceId == 671) {
			if (player.getFamiliar() == null || player.getFamiliar().getBob() == null)
				return;
			if (componentId == 27) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					player.getFamiliar().getBob().removeItem(slotId, 1);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
					player.getFamiliar().getBob().removeItem(slotId, 5);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
					player.getFamiliar().getBob().removeItem(slotId, 10);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
					player.getFamiliar().getBob().removeItem(slotId, Integer.MAX_VALUE);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					player.getTemporaryAttributtes().put("bob_item_X_Slot", slotId);
					player.getTemporaryAttributtes().put("bob_isRemove", Boolean.TRUE);
					player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
				}
			} else if (componentId == 29)
				player.getFamiliar().takeBob();
		} else if (interfaceId == 916) {
			SkillsDialogue.handleSetQuantityButtons(player, componentId);
		} else if (interfaceId == 193) {
			if (componentId == 5)
				player.getCombatDefinitions().switchShowCombatSpells();
			else if (componentId == 7)
				player.getCombatDefinitions().switchShowTeleportSkillSpells();
			else if (componentId >= 9 && componentId <= 11)
				player.getCombatDefinitions().setSortSpellBook(componentId - 9);
			else if (componentId == 18)
				player.getCombatDefinitions().switchDefensiveCasting();
			else
				Magic.processAncientSpell(player, componentId, packetId);
		} else if (interfaceId == 430) {
			if (componentId == 5)
				player.getCombatDefinitions().switchShowCombatSpells();
			else if (componentId == 7)
				player.getCombatDefinitions().switchShowTeleportSkillSpells();
			else if (componentId == 9)
				player.getCombatDefinitions().switchShowMiscallaneousSpells();
			else if (componentId >= 11 & componentId <= 13)
				player.getCombatDefinitions().setSortSpellBook(componentId - 11);
			else if (componentId == 20)
				player.getCombatDefinitions().switchDefensiveCasting();
			else
				Magic.processLunarSpell(player, componentId, packetId);
		} else if (interfaceId == 261) {
			if (player.getInterfaceManager().containsInventoryInter())
				return;
			if (componentId == 22) {
				if (player.getInterfaceManager().containsScreenInter()) {
					player.getPackets().sendGameMessage("Please close the interface you have open before setting your graphic options.");
					return;
				}
				player.stopAll();
				player.getInterfaceManager().sendInterface(742);
			} else if (componentId == 12)
				player.switchAllowChatEffects();
			else if (componentId == 13) { // chat setup
				player.getInterfaceManager().sendSettings(982);
			} else if (componentId == 16) {// house options
				player.getInterfaceManager().sendSettings(398);
			} else if (componentId == 14)
				player.switchMouseButtons();
			else if (componentId == 24) // audio options
				player.getInterfaceManager().sendSettings(429);
			else if (componentId == 26)
				AdventurersLog.open(player);
		} else if (interfaceId == 429) {
			if (componentId == 18)
				player.getInterfaceManager().sendSettings();
		} else if (interfaceId == 982) {
			if (componentId == 5)
				player.getInterfaceManager().sendSettings();
			else if (componentId == 41)
				player.setPrivateChatSetup(player.getPrivateChatSetup() == 0 ? 1 : 0);
			else if (componentId >= 49 && componentId <= 66)
				player.setPrivateChatSetup(componentId - 48);
			else if (componentId >= 72 && componentId <= 91)
				player.setFriendChatSetup(componentId - 72);
		} else if (interfaceId == 271) {
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					if (componentId == 8 || componentId == 42)
						player.getPrayer().switchPrayer(slotId);

					else if (componentId == 43 && player.getPrayer().isUsingQuickPrayer())
						player.getPrayer().switchSettingQuickPrayer();
				}
			});
		} else if (interfaceId == 320) {
			player.stopAll();
			int lvlupSkill = -1;
			int skillMenu = -1;
			switch (componentId) {
			case 150: // Attack
				skillMenu = 1;
				if (player.getTemporaryAttributtes().remove("leveledUp[0]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 1);
				} else {
					lvlupSkill = 0;
					player.getPackets().sendConfig(1230, 10);
				}
				break;
			case 9: // Strength
				skillMenu = 2;
				if (player.getTemporaryAttributtes().remove("leveledUp[2]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 2);
				} else {
					lvlupSkill = 2;
					player.getPackets().sendConfig(1230, 20);
				}
				break;
			case 22: // Defence
				skillMenu = 5;
				if (player.getTemporaryAttributtes().remove("leveledUp[1]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 5);
				} else {
					lvlupSkill = 1;
					player.getPackets().sendConfig(1230, 40);
				}
				break;
			case 40: // Ranged
				skillMenu = 3;
				if (player.getTemporaryAttributtes().remove("leveledUp[4]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 3);
				} else {
					lvlupSkill = 4;
					player.getPackets().sendConfig(1230, 30);
				}
				break;
			case 58: // Prayer
				if (player.getTemporaryAttributtes().remove("leveledUp[5]") != Boolean.TRUE) {
					skillMenu = 7;
					player.getPackets().sendConfig(965, 7);
				} else {
					lvlupSkill = 5;
					player.getPackets().sendConfig(1230, 60);
				}
				break;
			case 71: // Magic
				if (player.getTemporaryAttributtes().remove("leveledUp[6]") != Boolean.TRUE) {
					skillMenu = 4;
					player.getPackets().sendConfig(965, 4);
				} else {
					lvlupSkill = 6;
					player.getPackets().sendConfig(1230, 33);
				}
				break;
			case 84: // Runecrafting
				if (player.getTemporaryAttributtes().remove("leveledUp[20]") != Boolean.TRUE) {
					skillMenu = 12;
					player.getPackets().sendConfig(965, 12);
				} else {
					lvlupSkill = 20;
					player.getPackets().sendConfig(1230, 100);
				}
				break;
			case 102: // Construction
				skillMenu = 22;
				if (player.getTemporaryAttributtes().remove("leveledUp[21]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 22);
				} else {
					lvlupSkill = 21;
					player.getPackets().sendConfig(1230, 698);
				}
				break;
			case 145: // Hitpoints
				skillMenu = 6;
				if (player.getTemporaryAttributtes().remove("leveledUp[3]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 6);
				} else {
					lvlupSkill = 3;
					player.getPackets().sendConfig(1230, 50);
				}
				break;
			case 15: // Agility
				skillMenu = 8;
				if (player.getTemporaryAttributtes().remove("leveledUp[16]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 8);
				} else {
					lvlupSkill = 16;
					player.getPackets().sendConfig(1230, 65);
				}
				break;
			case 28: // Herblore
				skillMenu = 9;
				if (player.getTemporaryAttributtes().remove("leveledUp[15]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 9);
				} else {
					lvlupSkill = 15;
					player.getPackets().sendConfig(1230, 75);
				}
				break;
			case 46: // Thieving
				skillMenu = 10;
				if (player.getTemporaryAttributtes().remove("leveledUp[17]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 10);
				} else {
					lvlupSkill = 17;
					player.getPackets().sendConfig(1230, 80);
				}
				break;
			case 64: // Crafting
				skillMenu = 11;
				if (player.getTemporaryAttributtes().remove("leveledUp[12]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 11);
				} else {
					lvlupSkill = 12;
					player.getPackets().sendConfig(1230, 90);
				}
				break;
			case 77: // Fletching
				skillMenu = 19;
				if (player.getTemporaryAttributtes().remove("leveledUp[9]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 19);
				} else {
					lvlupSkill = 9;
					player.getPackets().sendConfig(1230, 665);
				}
				break;
			case 90: // Slayer
				skillMenu = 20;
				if (player.getTemporaryAttributtes().remove("leveledUp[18]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 20);
				} else {
					lvlupSkill = 18;
					player.getPackets().sendConfig(1230, 673);
				}
				break;
			case 108: // Hunter
				skillMenu = 23;
				if (player.getTemporaryAttributtes().remove("leveledUp[22]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 23);
				} else {
					lvlupSkill = 22;
					player.getPackets().sendConfig(1230, 689);
				}
				break;
			case 140: // Mining
				skillMenu = 13;
				if (player.getTemporaryAttributtes().remove("leveledUp[14]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 13);
				} else {
					lvlupSkill = 14;
					player.getPackets().sendConfig(1230, 110);
				}
				break;
			case 135: // Smithing
				skillMenu = 14;
				if (player.getTemporaryAttributtes().remove("leveledUp[13]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 14);
				} else {
					lvlupSkill = 13;
					player.getPackets().sendConfig(1230, 115);
				}
				break;
			case 34: // Fishing
				skillMenu = 15;
				if (player.getTemporaryAttributtes().remove("leveledUp[10]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 15);
				} else {
					lvlupSkill = 10;
					player.getPackets().sendConfig(1230, 120);
				}
				break;
			case 52: // Cooking
				skillMenu = 16;
				if (player.getTemporaryAttributtes().remove("leveledUp[7]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 16);
				} else {
					lvlupSkill = 7;
					player.getPackets().sendConfig(1230, 641);
				}
				break;
			case 130: // Firemaking
				skillMenu = 17;
				if (player.getTemporaryAttributtes().remove("leveledUp[11]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 17);
				} else {
					lvlupSkill = 11;
					player.getPackets().sendConfig(1230, 649);
				}
				break;
			case 125: // Woodcutting
				skillMenu = 18;
				if (player.getTemporaryAttributtes().remove("leveledUp[8]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 18);
				} else {
					lvlupSkill = 8;
					player.getPackets().sendConfig(1230, 660);
				}
				break;
			case 96: // Farming
				skillMenu = 21;
				if (player.getTemporaryAttributtes().remove("leveledUp[19]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 21);
				} else {
					lvlupSkill = 19;
					player.getPackets().sendConfig(1230, 681);
				}
				break;
			case 114: // Summoning
				skillMenu = 24;
				if (player.getTemporaryAttributtes().remove("leveledUp[23]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 24);
				} else {
					lvlupSkill = 23;
					player.getPackets().sendConfig(1230, 705);
				}
				break;
			case 120: // Dung
				skillMenu = 25;
				if (player.getTemporaryAttributtes().remove("leveledUp[24]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 25);
				} else {
					lvlupSkill = 24;
					player.getPackets().sendConfig(1230, 705);
				}
				break;
			}

			/*
			 * player.getInterfaceManager().sendInterface( lvlupSkill != -1 ?
			 * 741 : 499);
			 */
			player.getInterfaceManager().sendScreenInterface(317, 1218);
			player.getPackets().sendInterface(false, 1218, 1, 1217); // seems to
																		// fix
			if (lvlupSkill != -1)
				LevelUp.switchFlash(player, lvlupSkill, false);
			if (skillMenu != -1)
				player.getTemporaryAttributtes().put("skillMenu", skillMenu);
		} else if (interfaceId == 1218) {
			if ((componentId >= 33 && componentId <= 55) || componentId == 120 || componentId == 151 || componentId == 189)
				player.getPackets().sendInterface(false, 1218, 1, 1217); // seems
																			// to
																			// fix
		} else if (interfaceId == 499) {
			int skillMenu = -1;
			if (player.getTemporaryAttributtes().get("skillMenu") != null)
				skillMenu = (Integer) player.getTemporaryAttributtes().get("skillMenu");
			if (componentId >= 10 && componentId <= 25)
				player.getPackets().sendConfig(965, ((componentId - 10) * 1024) + skillMenu);
			else if (componentId == 29)
				// close inter
				player.stopAll();

		} else if (interfaceId == 387) {
			if (player.getInterfaceManager().containsInventoryInter())
				return;
			if (componentId == 6) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					int hatId = player.getEquipment().getHatId();
					if (hatId == 24437 || hatId == 24439 || hatId == 24440 || hatId == 24441) {
						player.getDialogueManager().startDialogue("FlamingSkull", player.getEquipment().getItem(Equipment.SLOT_HAT), -1);
						return;

						// RSMV EMOTES BEGIN!
						// =============================================================================================================================================================================================
					} else if (hatId == 16916) {// DHAT
						player.getInterfaceManager().DHat();
						return;
					} else if (hatId == 20077) {// salty
						player.getInterfaceManager().Salty();
						return;
					} else if (hatId == 20800) {// TIER 6 CORRECT
						player.getInterfaceManager().bragDuelist61();
						return;
					} else if (hatId == 20799) {// CORRECT
						player.getInterfaceManager().bragDuelist51();
						return;
					} else if (hatId == 20798) {// CORRECT
						player.getInterfaceManager().bragDuelist41();
						return;
					} else if (hatId == 20797) {// CORRECT
						player.getInterfaceManager().bragDuelist31();
						return;
					} else if (hatId == 20796) {// NEED ANIM
						player.getInterfaceManager().bragDuelist21();
						return;
					} else if (hatId == 20795) {// CORRECT
						player.getInterfaceManager().bragDuelist11();
						return;
					} else if (hatId == 20801) {// TIER 6 CORRECT
						player.getInterfaceManager().bragWild1();
						return;
					} else if (hatId == 20802) {// CORRECT
						player.getInterfaceManager().bragWild2();
						return;
					} else if (hatId == 20803) {// CORRECT
						player.getInterfaceManager().bragWild3();
						return;
					} else if (hatId == 20804) {// CORRECT
						player.getInterfaceManager().bragWild4();
						return;
					} else if (hatId == 20805) {// NEED ANIM
						player.getInterfaceManager().bragWild5();
						return;
					} else if (hatId == 20806) {// CORRECT
						player.getInterfaceManager().bragWild6();
						return;
					} else if (hatId == 24419) {
						player.getInterfaceManager().DiamondCrown();
						return;
					} else if (hatId == 22412) {
						player.getInterfaceManager().ChromeGoggles();
						return;
					}

				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					int hatId = player.getEquipment().getHatId();
					if (hatId == 20800) {// Duelist cap 6
						player.getInterfaceManager().transformDuelist61();
						return;
					} else if (hatId == 20799) {// Duelist cap 5
						player.getInterfaceManager().transformDuelist51();
						return;
					} else if (hatId == 20798) {// Duelist cap 4
						player.getInterfaceManager().transformDuelist41();
						return;
					} else if (hatId == 20797) {// Duelist cap 3
						player.getInterfaceManager().transformDuelist31();
						return;
					} else if (hatId == 20796) {// Duelist cap 2
						player.getInterfaceManager().transformDuelist21();
						return;
					} else if (hatId == 20795) {// Duelist cap 1
						player.getInterfaceManager().transformDuelist11();
						return;
					} else if (hatId == 20801) {// Wildstalker helmet 1
						player.getInterfaceManager().transformWild11();
						return;
					} else if (hatId == 20802) {// Wildstalker helmet 2
						player.getInterfaceManager().transformWild21();
						return;
					} else if (hatId == 20803) {// Wildstalker helmet 3
						player.getInterfaceManager().transformWild31();
						return;
					} else if (hatId == 20804) {// Wildstalker helmet 4
						player.getInterfaceManager().transformWild41();
						return;
					} else if (hatId == 20805) {// Wildstalker helmet 5
						player.getInterfaceManager().transformWild51();
						return;
					} else if (hatId == 20806) {// Wildstalker helmet 6
						player.getInterfaceManager().transformWild61();
						return;
					} else if (hatId == 16916) {// DHAT
						player.getInterfaceManager().DHat();
						return;
					} else if (hatId == 15673) {
						player.getInterfaceManager().Squirrel();
						return;
					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					int hatId = player.getEquipment().getHatId();
					if (hatId == 22332) {
						player.getInterfaceManager().teleportWicked();
						return;
					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					ButtonHandler.sendRemove(player, Equipment.SLOT_HAT);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET)
					player.getEquipment().sendExamine(Equipment.SLOT_HAT);
			} else if (componentId == 9) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					int capeId = player.getEquipment().getCapeId();
					if (capeId == 20769 || capeId == 20771 || capeId == 28013 || capeId == 19748) {
						player.getSkills().restoreSummoning();
						player.setNextAnimation(new Animation(8502));
						player.setNextGraphics(new Graphics(1308));
						player.getPackets().sendGameMessage("You restored your Summoning points with your awesome cape!", true);
					}
				}
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					int capeId = player.getEquipment().getCapeId();
					if (capeId == 20769 || capeId == 20771 || capeId == 28013)
						SkillCapeCustomizer.startCustomizing(player, capeId);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					int capeId = player.getEquipment().getCapeId();
					if (capeId == 20767) {
						SkillCapeCustomizer.startCustomizing(player, capeId);
					} else if (capeId == 19748) {
						Magic.ArdyTele1(player, 0, 0, new WorldTile(2606, 3217, 0));
					} else if (capeId == 20771) {
						player.getInterfaceManager().teleportGoldMonastery();
						return;
					} else if (capeId == 20769) {
						player.getInterfaceManager().teleportSilverMonastery();
						return;
					} else if (capeId == 28013) {
						player.getInterfaceManager().teleportUpgradedMonastery();
						return;
					} else if (capeId == 15345) {
						player.getInterfaceManager().teleportArdyMonastery();
						return;
					} else if (capeId == 15347) {
						player.getInterfaceManager().teleportArdyMonastery();
						return;
					} else if (capeId == 15349) {
						player.getInterfaceManager().teleportArdyMonastery();
						return;
					} else if (capeId == 15215) {
						player.getInterfaceManager().TeleportBonesack();
						return;

					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					int capeId = player.getEquipment().getCapeId();
					if (capeId == 20771) {
						Magic.ArdyTele2(player, 0, 0, new WorldTile(2674, 3375, 0));
						return;
					}
					if (capeId == 19748) {
						Magic.ArdyTele2(player, 0, 0, new WorldTile(2674, 3375, 0));
					} else if (capeId == 20769) {
						player.getInterfaceManager().teleportSilverFarm();
						return;
					} else if (capeId == 28013) {
						player.getInterfaceManager().teleportUpgradedFarm();
						return;
					} else if (capeId == 15347) {
						player.getInterfaceManager().teleportArdyFarm();
						return;
					} else if (capeId == 15349) {
						player.getInterfaceManager().teleportArdyFarm();
						return;
					}

				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					ButtonHandler.sendRemove(player, Equipment.SLOT_CAPE);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET)
					player.getEquipment().sendExamine(Equipment.SLOT_CAPE);
			} else if (componentId == 12) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					int amuletId = player.getEquipment().getAmuletId();
					if (amuletId <= 1712 && amuletId >= 1706 || amuletId >= 10354 && amuletId <= 10361) {
						if (Magic.sendItemTeleportSpell(player, true, Transportation.EMOTE, Transportation.GFX, 4, new WorldTile(3087, 3496, 0))) {
							Item amulet = player.getEquipment().getItem(Equipment.SLOT_AMULET);
							if (amulet != null) {
								amulet.setId(amulet.getId() - 2);
								player.getEquipment().refresh(Equipment.SLOT_AMULET);
							}
						}
					} else if (amuletId == 1704 || amuletId == 10352)
						player.getPackets().sendGameMessage("The amulet has ran out of charges. You need to recharge it if you wish it use it once more.");
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					int amuletId = player.getEquipment().getAmuletId();
					if (amuletId <= 1712 && amuletId >= 1706 || amuletId >= 10354 && amuletId <= 10361) {
						if (Magic.sendItemTeleportSpell(player, true, Transportation.EMOTE, Transportation.GFX, 4, new WorldTile(2918, 3176, 0))) {
							Item amulet = player.getEquipment().getItem(Equipment.SLOT_AMULET);
							if (amulet != null) {
								amulet.setId(amulet.getId() - 2);
								player.getEquipment().refresh(Equipment.SLOT_AMULET);
							}
						}
					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					int amuletId = player.getEquipment().getAmuletId();
					if (amuletId <= 1712 && amuletId >= 1706 || amuletId >= 10354 && amuletId <= 10361) {
						if (Magic.sendItemTeleportSpell(player, true, Transportation.EMOTE, Transportation.GFX, 4, new WorldTile(3105, 3251, 0))) {
							Item amulet = player.getEquipment().getItem(Equipment.SLOT_AMULET);
							if (amulet != null) {
								amulet.setId(amulet.getId() - 2);
								player.getEquipment().refresh(Equipment.SLOT_AMULET);
							}
						}
					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					int amuletId = player.getEquipment().getAmuletId();
					if (amuletId <= 1712 && amuletId >= 1706 || amuletId >= 10354 && amuletId <= 10361) {
						if (Magic.sendItemTeleportSpell(player, true, Transportation.EMOTE, Transportation.GFX, 4, new WorldTile(3293, 3163, 0))) {
							Item amulet = player.getEquipment().getItem(Equipment.SLOT_AMULET);
							if (amulet != null) {
								amulet.setId(amulet.getId() - 2);
								player.getEquipment().refresh(Equipment.SLOT_AMULET);
							}
						}
					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					ButtonHandler.sendRemove(player, Equipment.SLOT_AMULET);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET)
					player.getEquipment().sendExamine(Equipment.SLOT_AMULET);

			} else if (componentId == 15) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					int weaponId = player.getEquipment().getWeaponId();
					if (weaponId == 15484)
						player.getInterfaceManager().gazeOrbOfOculus();
					if (weaponId == 13137)
						player.getInterfaceManager().IvandisFlail();
					else if (weaponId == 15353) // Eek The Spider
						player.getDialogueManager().startDialogue("Eek", false);
					if (weaponId == 15374)
						player.getInterfaceManager().Magnifying();
					if (weaponId == 9013)
						player.getInterfaceManager().Skull();
					if (weaponId == 15426)
						player.getInterfaceManager().CandyCane();
					if (weaponId == 12844)
						player.getInterfaceManager().ToyKite();
					if (weaponId == 20714)
						player.getInterfaceManager().Bouquet();
					if (weaponId == 9044)
						player.getInterfaceManager().Pharoh();
					if (weaponId == 14742)
						player.getInterfaceManager().Lilly();
					if (weaponId == 20716) // Bubbles
						player.getInterfaceManager().Bubbles();
					if (weaponId == 20084) // Hammer
						player.getInterfaceManager().HammerBrandish();
					// if(weaponId == 20709) //Clan
					// player.getInterfaceManager().ClanPlace();
					if (weaponId == 11283) // DragonShield
						player.getInterfaceManager().DragonFire();
					if (weaponId == 24455) // Annihillator
						player.getInterfaceManager().Annihilator();
					if (weaponId == 19329) // WOLF SAFF
						player.getInterfaceManager().WolfDance();
					if (weaponId == 19325) // PENG SAFF
						player.getInterfaceManager().PengDance();
					if (weaponId == 19323) // DRAG SAFF
						player.getInterfaceManager().DragonDance();
					if (weaponId == 19331) // CAT SAFF
						player.getInterfaceManager().CatDance();
					if (weaponId == 19327) // BAT SAFF
						player.getInterfaceManager().BatDance();
					if (weaponId <= 13146 && weaponId >= 13117) // Ivandis Flail
						player.getInterfaceManager().IvandisFlail();
					if (weaponId == 13156) // Enchanted Sickle (b)
						player.getInterfaceManager().ESickle();
					if (weaponId == 2963) // Blessed Sickle
						player.getInterfaceManager().BSickle();
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					ButtonHandler.sendRemove(player, Equipment.SLOT_WEAPON);

				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					int weaponId = player.getEquipment().getWeaponId();
					if (weaponId == 15353)
						player.getInterfaceManager().spiderPlayWith();
					if (weaponId == 9044)
						player.getInterfaceManager().Pharoh();
					if (weaponId == 20084) // Hammer
						player.getInterfaceManager().HammerSpin();
					if (weaponId == 20709) // Clan
						player.getInterfaceManager().ClanTeleport();
					if (weaponId == 11283) // DragonShield
						player.getInterfaceManager().DragonFire();
					if (weaponId == 14057) // Broomstick
						player.getInterfaceManager().TeleportBroom();
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET)
					player.getEquipment().sendExamine(Equipment.SLOT_WEAPON);
			} else if (componentId == 18) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					int chestId = player.getEquipment().getChestId();
					if (chestId == 26140) {// Obsidian Platebody
						player.getPackets().sendGameMessage("Option2");
						return;

					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					ButtonHandler.sendRemove(player, Equipment.SLOT_CHEST);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET)
					player.getEquipment().sendExamine(Equipment.SLOT_CHEST);
			} else if (componentId == 21) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					int shieldId = player.getEquipment().getShieldId();
					// if (player.hasInstantSpecialShield(shieldId));
					// player.performInstantSpecialShield(shieldId, player);

					if (shieldId == 11283)
						player.getAuraManager().activateShield();

					else if (shieldId == 14577)
						player.getInterfaceManager().faladorPrayer1();
					else if (shieldId == 14578)
						player.getInterfaceManager().faladorPrayer2();
					else if (shieldId == 14579)
						player.getInterfaceManager().faladorPrayer3();
					else if (shieldId == 19749)
						player.getInterfaceManager().faladorPrayer4();
					else if (shieldId == 24418)
						player.getInterfaceManager().DiamondSceptre();
					if (shieldId == 3844)
						player.getInterfaceManager().Guthix();
					if (shieldId == 3842)
						player.getInterfaceManager().Zamorak();
					if (shieldId == 3840)
						player.getInterfaceManager().Saradomin();
					if (shieldId == 22418)
						player.getInterfaceManager().Cithara();
					else if (componentId == 21 && packetId == 67 && player.getEquipment().getShieldId() == 11283)
						CombatDefinitions.hasDragonFire(player);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					ButtonHandler.sendRemove(player, Equipment.SLOT_SHIELD);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					int shieldId = player.getEquipment().getShieldId();
					if (shieldId == 19749)
						player.getInterfaceManager().faladorPrayer4();
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					int shieldId = player.getEquipment().getShieldId();
					if (shieldId == 14577)
						player.getInterfaceManager().faladorEmote1();
					else if (shieldId == 14578)
						player.getInterfaceManager().faladorEmote2();
					else if (shieldId == 14579)
						player.getInterfaceManager().faladorEmote3();
					else if (shieldId == 19749)
						player.getInterfaceManager().faladorEmote4();
					else if (shieldId == 16916)
						player.getInterfaceManager().DCane();
					if (shieldId == 15440)
						player.getInterfaceManager().Horn();
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET)
					player.getEquipment().sendExamine(Equipment.SLOT_SHIELD);
			} else if (componentId == 24) {

				if (packetId == WorldPacketsDecoder.ACTION_BUTTON6_PACKET) {
					int LegsId = player.getEquipment().getLegsId();
					if (LegsId == 15353)
						Magic.MortLegs(player, 0, 0, new WorldTile(3604, 3564, 0));
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					ButtonHandler.sendRemove(player, Equipment.SLOT_LEGS);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
					Magic.MortLegs(player, 0, 0, new WorldTile(3604, 3564, 0));
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
					Magic.MortLegs(player, 0, 0, new WorldTile(3604, 3564, 0));
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET)
					Magic.MortLegs(player, 0, 0, new WorldTile(3604, 3564, 0));
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET)
					player.getEquipment().sendExamine(Equipment.SLOT_LEGS);
			} else if (componentId == 27) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					int handsId = player.getEquipment().getHandsId();

				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					ButtonHandler.sendRemove(player, Equipment.SLOT_HANDS);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET)
					player.getEquipment().sendExamine(Equipment.SLOT_HANDS);
			} else if (componentId == 30) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					int feetId = player.getEquipment().getFeetId();
					if (feetId == 26126) {// Obsidian Boots
						player.getPackets().sendGameMessage("Option2");
						return;
					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					ButtonHandler.sendRemove(player, Equipment.SLOT_FEET);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET)
					player.getEquipment().sendExamine(Equipment.SLOT_FEET);
			} else if (componentId == 33) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					int ringId = player.getEquipment().getRingId();
					if (ringId == 2552) {// Ring of duelling 8
						player.getPackets().sendGameMessage("Option2");
						return;
					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					int ringId = player.getEquipment().getRingId();
					if (ringId == 2552) {// Ring of duelling 8
						player.getPackets().sendGameMessage("Option3");
						return;
					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					int ringId = player.getEquipment().getRingId();
					if (ringId == 2552) {// Ring of duelling 8
						player.getPackets().sendGameMessage("Option4");
						return;
					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					int ringId = player.getEquipment().getRingId();
					if (ringId == 2552) {// Ring of duelling 8
						player.getPackets().sendGameMessage("Option5");
						return;
					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					ButtonHandler.sendRemove(player, Equipment.SLOT_RING);

				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET)
					player.getEquipment().sendExamine(Equipment.SLOT_RING);
			} else if (componentId == 36) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					int arrowsId = player.getEquipment().getArrowsId();
					if (arrowsId == 0) {// Use For Null
						player.getPackets().sendGameMessage("Option2");
						return;
					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					ButtonHandler.sendRemove(player, Equipment.SLOT_ARROWS);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET)
					player.getEquipment().sendExamine(Equipment.SLOT_ARROWS);
			} else if (componentId == 45) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					ButtonHandler.sendRemove(player, Equipment.SLOT_AURA);
					player.getAuraManager().removeAura();
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET)
					player.getEquipment().sendExamine(Equipment.SLOT_AURA);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
					player.getAuraManager().activate();
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
					player.getAuraManager().sendAuraRemainingTime();
			} else if (componentId == 40) {
				player.stopAll();
				player.getInterfaceManager().sendInterface(17);
			} else if (componentId == 37) {
				openEquipmentBonuses(player, false);
			}

			// ENDDDDD--------------------------------------------------------------------------------------
		} else if (interfaceId == 449) {
			if (componentId == 1) {
				Shop shop = (Shop) player.getTemporaryAttributtes().get("Shop");
				if (shop == null)
					return;
				shop.sendInventory(player);
			} else if (componentId == 21) {
				Shop shop = (Shop) player.getTemporaryAttributtes().get("Shop");
				if (shop == null)
					return;
				Integer slot = (Integer) player.getTemporaryAttributtes().get("ShopSelectedSlot");
				if (slot == null)
					return;
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					shop.buy(player, slot, 1);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
					shop.buy(player, slot, 5);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
					shop.buy(player, slot, 10);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
					shop.buy(player, slot, 50);

			}

		} else if (interfaceId == 1265) {
			Shop shop = (Shop) player.getTemporaryAttributtes().get("Shop");
			if (shop == null)
				return;
			Integer slot = (Integer) player.getTemporaryAttributtes().get("ShopSelectedSlot");
			boolean isBuying = player.getTemporaryAttributtes().get("shop_buying") != null;
			if (componentId == 20) {
				player.getTemporaryAttributtes().put("ShopSelectedSlot", slotId);
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					shop.sendInfo(player, slotId, isBuying);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
					shop.handleShop(player, slotId, 1);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
					shop.handleShop(player, slotId, 5);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
					shop.handleShop(player, slotId, 10);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET)
					shop.handleShop(player, slotId, 50);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON6_PACKET)
					player.getPackets().sendGameMessage("You aren't allowed to buy all of that item.");
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON9_PACKET)
					shop.handleShop(player, slotId, 500);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET)
					shop.sendExamine(player, slotId);
			} else if (componentId == 201) {
				if (slot == null)
					return;
				if (isBuying)
					shop.buy(player, slot, shop.getAmount());
				else {
					shop.sell(player, slot, shop.getAmount());
					player.getPackets().sendConfig(2563, 0);
					player.getPackets().sendConfig(2565, 1); // this is to
																// update the
																// tab.
				}
			} else if (componentId == 208) {
				shop.setAmount(player, shop.getAmount() + 5);
			} else if (componentId == 15) {
				shop.setAmount(player, shop.getAmount() + 1);
			} else if (componentId == 214) {
				if (shop.getAmount() > 1)
					shop.setAmount(player, shop.getAmount() - 1);
			} else if (componentId == 217) {
				if (shop.getAmount() > 1)
					shop.setAmount(player, shop.getAmount() - 5);
			} else if (componentId == 220) {
				shop.setAmount(player, 1);
			} else if (componentId == 211) {
				if (slot == null)
					return;
				shop.setAmount(player, isBuying ? shop.getMainStock()[slot].getAmount() : player.getInventory().getItems().getItems()[slot].getAmount());
			} else if (componentId == 29) {
				player.getPackets().sendConfig(2561, 93);
				player.getTemporaryAttributtes().remove("shop_buying");
				shop.setAmount(player, 1);
			} else if (componentId == 28) {
				player.getTemporaryAttributtes().put("shop_buying", true);
				shop.setAmount(player, 1);
			}
		} else if (interfaceId == 1266) {
			if (componentId == 0) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON9_PACKET)
					player.getInventory().sendExamine(slotId);
				else {
					Shop shop = (Shop) player.getTemporaryAttributtes().get("Shop");
					if (shop == null)
						return;
					player.getPackets().sendConfig(2563, slotId);
					if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
						shop.sendValue(player, slotId);
					else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
						shop.sell(player, slotId, 1);
					else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
						shop.sell(player, slotId, 5);
					else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
						shop.sell(player, slotId, 10);
					else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET)
						shop.sell(player, slotId, 50);
				}
			}
		} else if (interfaceId == 734) {
			player.getFairyRing().handleButtons(interfaceId, componentId);
		} else if (interfaceId == 864) {
			SpiritTree.handleButtons(player, slotId);
		} else if (interfaceId == 138) {
			GnomeGlider.handleButtons(player, componentId);
		} else if (interfaceId == 163) {
			if (componentId == 15)
				SlayerTask.sendBuy(player);

			if (componentId == 22)
				player.getPackets().sendGameMessage("This currently does not work.");

			if (componentId == 23)
				player.getPackets().sendGameMessage("This currently does not work.");

			if (componentId == 14)
				player.getPackets().sendGameMessage("This currently does not work.");

			if (componentId == 24)
				if (player.getSlayerPoints() >= 400) {
					player.getInventory().addItem(15492, 1);
					player.setSlayerPoints(player.getSlayerPoints() - 400);
					SlayerTask.sendLearn(player);
				} else {
					player.getPackets().sendGameMessage("You need 400 Slayer Points to purchase a Full Slayer Helm.");
				}
		} else if (interfaceId == 388) {
			// ClanCitadelTheatre.handleLighting(player, componentId);
		} else if (interfaceId == 797) {
			ClanCitadelTheatre.handleModifyMachine(player, componentId);
		} else if (interfaceId == 164) {
			if (componentId == 24)
				if (player.getSlayerPoints() >= 400) {
					player.getSkills().addXp(Skills.SLAYER, 8883.3);
					player.setSlayerPoints(player.getSlayerPoints() - 400);
					SlayerTask.sendBuy(player);
				} else {
					player.getPackets().sendGameMessage("You need 400 Slayer Points to purchase 10,000 experience.");
				}
			if (componentId == 26)
				if (player.getSlayerPoints() >= 75) {
					player.getInventory().addItem(13281, 1);
					player.setSlayerPoints(player.getSlayerPoints() - 75);
					SlayerTask.sendBuy(player);
				} else {
					player.getPackets().sendGameMessage("You need 75 Slayer Points to purchase a Ring of Slaying (8).");
				}
			if (componentId == 28)
				if (player.getSlayerPoints() >= 35) {
					player.getInventory().addItem(560, 250);
					player.getInventory().addItem(556, 750);
					player.setSlayerPoints(player.getSlayerPoints() - 35);
					SlayerTask.sendBuy(player);
				} else {
					player.getPackets().sendGameMessage("You need 35 Slayer Points to purchase runes for Slayer Dart.");
				}
			if (componentId == 37)
				if (player.getSlayerPoints() >= 35) {
					player.getInventory().addItem(13280, 250);
					player.setSlayerPoints(player.getSlayerPoints() - 35);
					SlayerTask.sendBuy(player);
				} else {
					player.getPackets().sendGameMessage("You need 35 Slayer Points to purchase Broad Bolts.");
				}
			if (componentId == 39)
				if (player.getSlayerPoints() >= 35) {
					player.getInventory().addItem(4160, 250);
					player.setSlayerPoints(player.getSlayerPoints() - 35);
					SlayerTask.sendBuy(player);
				} else {
					player.getPackets().sendGameMessage("You need 35 Slayer Points to purchase Broad Arrows.");
				}
			if (componentId == 16)
				SlayerTask.sendLearn(player);

			if (componentId == 17)
				player.getPackets().sendGameMessage("Currently does not work.");
		} else if (interfaceId == 640) {
			if (componentId == 18 || componentId == 22) {
				player.getTemporaryAttributtes().put("WillDuelFriendly", true);
				player.getPackets().sendConfig(283, 67108864);
			} else if (componentId == 19 || componentId == 21) {
				player.getTemporaryAttributtes().put("WillDuelFriendly", false);
				player.getPackets().sendConfig(283, 134217728);
			} else if (componentId == 20) {
				DuelControler.challenge(player);
			}
		} else if (interfaceId == 650) {
			if (componentId == 15) {
				player.stopAll();
				player.setNextWorldTile(new WorldTile(2974, 4384, player.getPlane()));
				player.getControlerManager().startControler("CorpBeastControler");
			} else if (componentId == 16)
				player.closeInterfaces();
		} else if (interfaceId == 667) {
			if (componentId == 14) {
				if (slotId >= 14)
					return;
				Item item = player.getEquipment().getItem(slotId);
				if (item == null)
					return;
				if (packetId == 3)
					player.getPackets().sendGameMessage(ItemExamines.getExamine(item));
				else if (packetId == 216) {
					sendRemove(player, slotId);
					ButtonHandler.refreshEquipBonuses(player);
				}
			} else if (componentId == 46 && player.getTemporaryAttributtes().remove("Banking") != null) {
				player.getBank().openBank();
			}
		} else if (interfaceId == 670) {
			if (componentId == 0) {
				if (slotId >= player.getInventory().getItemsContainerSize())
					return;
				Item item = player.getInventory().getItem(slotId);
				if (item == null)
					return;
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					if (sendWear(player, slotId, item.getId()))
						ButtonHandler.refreshEquipBonuses(player);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
					player.getInventory().sendExamine(slotId);
			}
		} else if (interfaceId == Inventory.INVENTORY_INTERFACE) { // inventory
			if (componentId == 0) {
				if (slotId > 27 || player.getInterfaceManager().containsInventoryInter())
					return;
				Item item = player.getInventory().getItem(slotId);
				if (item == null || item.getId() != slotId2)
					return;
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					InventoryOptionsHandler.handleItemOption1(player, slotId, slotId2, item);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
					InventoryOptionsHandler.handleItemOption2(player, slotId, slotId2, item);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
					InventoryOptionsHandler.handleItemOption3(player, slotId, slotId2, item);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
					InventoryOptionsHandler.handleItemOption4(player, slotId, slotId2, item);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET)
					InventoryOptionsHandler.handleItemOption5(player, slotId, slotId2, item);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON6_PACKET)
					InventoryOptionsHandler.handleItemOption6(player, slotId, slotId2, item);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON7_PACKET)
					InventoryOptionsHandler.handleItemOption7(player, slotId, slotId2, item);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET)
					InventoryOptionsHandler.handleItemOption8(player, slotId, slotId2, item);
			}
		} else if (interfaceId == 742) {
			if (componentId == 46) // close
				player.stopAll();
		} else if (interfaceId == 743) {
			if (componentId == 20) // close
				player.stopAll();
		} else if (interfaceId == 741) {
			if (componentId == 9) // close
				player.stopAll();
		} else if (interfaceId == 749) {
			if (componentId == 4) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) // activate
					player.getPrayer().switchQuickPrayers();
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) // switch
					player.getPrayer().switchSettingQuickPrayer();
			}
		} else if (interfaceId == 750) {
			if (componentId == 4) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					player.toogleRun(player.isResting() ? false : true);
					if (player.isResting())
						player.stopAll();
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					if (player.isResting()) {
						player.stopAll();
						return;
					}
					long currentTime = Utils.currentTimeMillis();
					if (player.getEmotesManager().getNextEmoteEnd() >= currentTime) {
						player.getPackets().sendGameMessage("You can't rest while perfoming an emote.");
						return;
					}
					if (player.getLockDelay() >= currentTime) {
						player.getPackets().sendGameMessage("You can't rest while perfoming an action.");
						return;
					}
					player.stopAll();
					player.getActionManager().setAction(new Rest());
				}
			}
		} else if (interfaceId == 11) {
			if (componentId == 17) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					player.getBank().depositItem(slotId, 1, false);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
					player.getBank().depositItem(slotId, 5, false);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
					player.getBank().depositItem(slotId, 10, false);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
					player.getBank().depositItem(slotId, Integer.MAX_VALUE, false);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					player.getTemporaryAttributtes().put("bank_item_X_Slot", slotId);
					player.getTemporaryAttributtes().remove("bank_isWithdraw");
					player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON9_PACKET)
					player.getInventory().sendExamine(slotId);
			} else if (componentId == 18)
				player.getBank().depositAllInventory(false);
			else if (componentId == 20)
				player.getBank().depositAllEquipment(false);
		} else if (interfaceId == 398) {
			if (componentId == 19)
				player.getInterfaceManager().sendSettings();
			else if (componentId == 15 || componentId == 1)//it should work but we might edit on wrong file
				player.getHouse().setBuildMode(componentId == 15);
			else if (componentId == 25 || componentId == 26)
				player.getHouse().setArriveInPortal(componentId == 25);
			else if (componentId == 27)
				player.getHouse().expelGuests();
			else if (componentId == 29)
			House.leaveHouse(player);//nea
		} else if (interfaceId == 402) {
			if (componentId >= 93 && componentId <= 115)
				player.getHouse().createRoom(componentId - 93);
		} else if (interfaceId == 394 || interfaceId == 396) {
			if (componentId == 11)//youw ant that button? it's from teh cache bcuz  it send the config and the config from the cache or let me test something
				player.getHouse().build(slotId);

		} else if (interfaceId == 762) {
			if (componentId == 15)
				player.getBank().switchInsertItems();
			else if (componentId == 19)
				player.getBank().switchWithdrawNotes();

			else if (componentId == 33)
				player.getBank().depositAllInventory(true);
			else if (componentId == 37)
				player.getBank().depositAllEquipment(true);
			else if (componentId == 35)
				player.getBank().depositAllMoneyPouch(true);
			else if (componentId == 46) {
				player.closeInterfaces();
				player.getInterfaceManager().sendInterface(767);
				player.setCloseInterfacesEvent(new Runnable() {
					@Override
					public void run() {
						player.getBank().openBank();
					}
				});
			} else if (componentId >= 46 && componentId <= 64) {
				int tabId = 9 - ((componentId - 46) / 2);
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					player.getBank().setCurrentTab(tabId);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
					player.getBank().collapse(tabId);
			} else if (componentId == 95) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					player.getBank().withdrawItem(slotId, 1);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
					player.getBank().withdrawItem(slotId, 5);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
					player.getBank().withdrawItem(slotId, 10);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
					player.getBank().withdrawLastAmount(slotId);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					player.getTemporaryAttributtes().put("bank_item_X_Slot", slotId);
					player.getTemporaryAttributtes().put("bank_isWithdraw", Boolean.TRUE);
					player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON9_PACKET)
					player.getBank().withdrawItem(slotId, Integer.MAX_VALUE);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON6_PACKET)
					player.getBank().withdrawItemButOne(slotId);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET)
					player.getBank().sendExamine(slotId);

			} else if (componentId == 119) {
				openEquipmentBonuses(player, true);
			}
		} else if (interfaceId == 763) {
			if (componentId == 0) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					player.getBank().depositItem(slotId, 1, true);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
					player.getBank().depositItem(slotId, 5, true);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
					player.getBank().depositItem(slotId, 10, true);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
					player.getBank().depositLastAmount(slotId);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					player.getTemporaryAttributtes().put("bank_item_X_Slot", slotId);
					player.getTemporaryAttributtes().remove("bank_isWithdraw");
					player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON9_PACKET)
					player.getBank().depositItem(slotId, Integer.MAX_VALUE, true);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET)
					player.getInventory().sendExamine(slotId);
			}
		} else if (interfaceId == 767) {
			if (componentId == 10)
				player.getBank().openBank();
		} else if (interfaceId == 884) {
			if (componentId == 4) {
				int weaponId = player.getEquipment().getWeaponId();
				if (player.hasInstantSpecial(weaponId)) {
					player.performInstantSpecial(weaponId);
					return;
				}
				submitSpecialRequest(player);
			} else if (componentId >= 7 && componentId <= 10)
				player.getCombatDefinitions().setAttackStyle(componentId - 7);
			else if (componentId == 11)
				player.getCombatDefinitions().switchAutoRelatie();
		} else if (interfaceId == 755) {
			if (componentId == 44)
				player.getPackets().sendWindowsPane(player.getInterfaceManager().hasRezizableScreen() ? 746 : 548, 2);
			else if (componentId == 42) {
				player.getHintIconsManager().removeAll();// TODO find hintIcon
															// index
				player.getPackets().sendConfig(1159, 1);
			}
		} else if (interfaceId == 20)
			SkillCapeCustomizer.handleSkillCapeCustomizer(player, componentId);
		// ////
		else if (interfaceId == 1089) {
			if (componentId == 30)
				player.getTemporaryAttributtes().put("clanflagselection", slotId);
			else if (componentId == 26) {
				Integer flag = (Integer) player.getTemporaryAttributtes().remove("clanflagselection");
				player.stopAll();
				if (flag != null)
					ClansManager.setClanFlagInterface(player, flag);
			}
		} else if (interfaceId == 1096) {
			if (componentId == 41)
				ClansManager.viewClammateDetails(player, slotId);
			else if (componentId == 94)
				ClansManager.switchGuestsInChatCanEnterInterface(player);
			else if (componentId == 95)
				ClansManager.switchGuestsInChatCanTalkInterface(player);
			else if (componentId == 96)
				ClansManager.switchRecruitingInterface(player);
			else if (componentId == 97)
				ClansManager.switchClanTimeInterface(player);
			else if (componentId == 124)
				ClansManager.openClanMottifInterface(player);
			else if (componentId == 131)
				ClansManager.openClanMottoInterface(player);
			else if (componentId == 240)
				ClansManager.setTimeZoneInterface(player, -720 + slotId * 10);
			else if (componentId == 262)
				player.getTemporaryAttributtes().put("editclanmatejob", slotId);
			else if (componentId == 276)
				player.getTemporaryAttributtes().put("editclanmaterank", slotId);
			else if (componentId == 309)
				ClansManager.kickClanmate(player);
			else if (componentId == 318)
				ClansManager.saveClanmateDetails(player);
			else if (componentId == 290)
				ClansManager.setWorldIdInterface(player, slotId);
			else if (componentId == 297)
				ClansManager.openForumThreadInterface(player);
			else if (componentId == 346)
				ClansManager.openNationalFlagInterface(player);
			else if (componentId == 113)
				ClansManager.showClanSettingsClanMates(player);
			else if (componentId == 120)
				ClansManager.showClanSettingsSettings(player);
			else if (componentId == 386)
				ClansManager.showClanSettingsPermissions(player);
			else if (componentId >= 395 && componentId <= 475) {
				int selectedRank = (componentId - 395) / 8;
				if (selectedRank == 10)
					selectedRank = 125;
				else if (selectedRank > 5)
					selectedRank = 100 + selectedRank - 6;
				ClansManager.selectPermissionRank(player, selectedRank);
			} else if (componentId == 489)
				ClansManager.selectPermissionTab(player, 1);
			else if (componentId == 498)
				ClansManager.selectPermissionTab(player, 2);
			else if (componentId == 506)
				ClansManager.selectPermissionTab(player, 3);
			else if (componentId == 514)
				ClansManager.selectPermissionTab(player, 4);
			else if (componentId == 522)
				ClansManager.selectPermissionTab(player, 5);
		} else if (interfaceId == 1105) {
			if (componentId == 63 || componentId == 66)
				ClansManager.setClanMottifTextureInterface(player, componentId == 66, slotId);
			else if (componentId == 35)
				ClansManager.openSetMottifColor(player, 0);
			else if (componentId == 80)
				ClansManager.openSetMottifColor(player, 1);
			else if (componentId == 92)
				ClansManager.openSetMottifColor(player, 2);
			else if (componentId == 104)
				ClansManager.openSetMottifColor(player, 3);
			else if (componentId == 120)
				player.stopAll();
		} else if (interfaceId == 1110) {
			if (componentId == 82)
				ClansManager.joinClanChatChannel(player);
			else if (componentId == 75)
				ClansManager.openClanDetails(player);
			else if (componentId == 78)
				ClansManager.openClanSettings(player);
			else if (componentId == 91)
				ClansManager.joinGuestClanChat(player);
			else if (componentId == 95)
				ClansManager.banPlayer(player);
			else if (componentId == 99)
				ClansManager.unbanPlayer(player);
			else if (componentId == 11)
				ClansManager.unbanPlayer(player, slotId);
			else if (componentId == 109)
				ClansManager.leaveClan(player);
		} else if (interfaceId == 1079)
			player.closeInterfaces();
		// ////
		else if (interfaceId == 1056) {
			if (componentId == 173)
				player.getInterfaceManager().sendInterface(917);
		} else if (interfaceId == 751) {
			if (componentId == 26) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
					player.getFriendsIgnores().setPrivateStatus(0);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
					player.getFriendsIgnores().setPrivateStatus(1);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
					player.getFriendsIgnores().setPrivateStatus(2);
			} else if (componentId == 14) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
					player.GetPlayers();
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
					player.getFriendsIgnores().setPrivateStatus(1);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
					player.getFriendsIgnores().setPrivateStatus(2);
			} else if (componentId == 32) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
					player.setFilterGame(false);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
					player.setFilterGame(true);
			} else if (componentId == 29) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
					player.setPublicStatus(0);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
					player.setPublicStatus(1);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
					player.setPublicStatus(2);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET)
					player.setPublicStatus(3);
			} else if (componentId == 0) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
					player.getFriendsIgnores().setFriendsChatStatus(0);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
					player.getFriendsIgnores().setFriendsChatStatus(1);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
					player.getFriendsIgnores().setFriendsChatStatus(2);
			} else if (componentId == 23) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
					player.setClanStatus(0);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
					player.setClanStatus(1);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
					player.setClanStatus(2);
			} else if (componentId == 20) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
					player.setTradeStatus(0);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
					player.setTradeStatus(1);
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
					player.setTradeStatus(2);
			} else if (componentId == 17) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
					player.GetStaff();
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
					player.GetStaff();
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET)
					player.GetStaff();
				else if (packetId == WorldPacketsDecoder.ACTION_BUTTON9_PACKET) {
					player.GetStaff();
				}
			}
		} /*else if (interfaceId == 1163 || interfaceId == 1164 || interfaceId == 1168 || interfaceId == 1170 || interfaceId == 1173)
			//player.getDominionTower().handleButtons(interfaceId, componentId);*/
			
		else if (interfaceId == 900)
			PlayerLook.handleMageMakeOverButtons(player, componentId);
		else if (interfaceId == 1028)
			PlayerLook.handleCharacterCustomizingButtons(player, componentId, slotId);
		else if (interfaceId == 1108 || interfaceId == 1109)
			player.getFriendsIgnores().handleFriendChatButtons(interfaceId, componentId, packetId);
		else if (interfaceId == 1079)
			player.closeInterfaces();

		else if (interfaceId == 374) {
			if (componentId >= 5 && componentId <= 9)
				player.setNextWorldTile(new WorldTile(FightPitsViewingOrb.ORB_TELEPORTS[componentId - 5]));
			else if (componentId == 15)
				player.stopAll();
		} else if (interfaceId == 1092) {
			player.stopAll();
			WorldTile destTile = null;
			switch (componentId) {
			case 47:
				destTile = HomeTeleport.LUMBRIDGE_LODE_STONE;
				break;
			case 42:
				destTile = HomeTeleport.BURTHORPE_LODE_STONE;
				break;
			case 39:
				destTile = HomeTeleport.LUNAR_ISLE_LODE_STONE;
				break;
			case 7:
				destTile = HomeTeleport.BANDIT_CAMP_LODE_STONE;
				break;
			case 50:
				destTile = HomeTeleport.TAVERLY_LODE_STONE;
				break;
			case 40:
				destTile = HomeTeleport.ALKARID_LODE_STONE;
				break;
			case 51:
				destTile = HomeTeleport.VARROCK_LODE_STONE;
				break;
			case 45:
				destTile = HomeTeleport.EDGEVILLE_LODE_STONE;
				break;
			case 46:
				destTile = HomeTeleport.FALADOR_LODE_STONE;
				break;
			case 48:
				destTile = HomeTeleport.PORT_SARIM_LODE_STONE;
				break;
			case 44:
				destTile = HomeTeleport.DRAYNOR_VILLAGE_LODE_STONE;
				break;
			case 41:
				destTile = HomeTeleport.ARDOUGNE_LODE_STONE;
				break;
			case 43:
				destTile = HomeTeleport.CATHERBAY_LODE_STONE;
				break;
			case 52:
				destTile = HomeTeleport.YANILLE_LODE_STONE;
				break;
			case 49:
				destTile = HomeTeleport.SEERS_VILLAGE_LODE_STONE;
				break;
			}
			if (destTile != null)
				player.getActionManager().setAction(new HomeTeleport(destTile));
		
		} else if (interfaceId == 1214)
			player.getSkills().handleSetupXPCounter(componentId);
		else if (interfaceId == 1292) {
			if (componentId == 12)
				Crucible.enterArena(player);
			else if (componentId == 13)
				player.closeInterfaces();
		} else if (interfaceId == 1156 && player.isviewingrsmvactionlocs() == true) {
			DTRSMVActionLocs.handleButtons(player, componentId);
			return;
		} else if (interfaceId == 1156 && player.isviewingagilityteles() == true) {
			DTAgility.handleButtons(player, componentId);
			return;
		} else if (interfaceId == 1156 && player.isviewingmonsterteles() == true) {
			DTMonsters.handleButtons(player, componentId);
			return;
		} else if (interfaceId == 1156 && player.isviewingwoodcuttingteles() == true) {
			DTWoodcuttingTeleports.handleButtons(player, componentId);
			return;
		} else if (interfaceId == 1160 && player.isviewinglyreteles() == true) {
			LyreMainMenu.sendMainMenuOptions(player, componentId);		
			return;		
		
		//Interface for Viewing Categories
		} else if (interfaceId == 1164 && player.isviewingfamouscategories() == true) {
			LyreMainMenu.sendFamousMenuOptions(player, componentId);		
			return;	
		} else if (interfaceId == 1164 && player.isviewingbrightcategories() == true) {
			LyreMainMenu.sendBrightMenuOptions(player, componentId);		
			return;	
		} /*else if (interfaceId == 1164 && player.isviewingmoodycategories() == true) {
			LyreMainMenu.sendMoodyMenuOptions(player, componentId);		
			return;
		} else if (interfaceId == 1164 && player.isviewingfairyringcategories() == true) {
			LyreMainMenu.sendFairyRingMenuOptions(player, componentId);		
			return;
		} else if (interfaceId == 1164 && player.isviewingextracategories() == true) {
			LyreMainMenu.sendExtraMenuOptions(player, componentId);		
			return;
		}*/
				
		//Interface for Viewing Famous Teleports
		//Famous I
		else if (interfaceId == 1156 && player.isviewingfamousIteles() == true) {
			LyreMainMenu.handleFamousIButtons(player, componentId);	
			return;		
		} else if (interfaceId == 1156 && player.isviewingfamousIIteles() == true) {
			LyreMainMenu.handleFamousIIButtons(player, componentId);	
			return;		
		} else if (interfaceId == 1156 && player.isviewingfamousIIIteles() == true) {
			LyreMainMenu.handleFamousIIIButtons(player, componentId);	
			return;		
		} else if (interfaceId == 1156 && player.isviewingfamousIIIIteles() == true) {
			LyreMainMenu.handleFamousIIIIButtons(player, componentId);	
			return;		
		}
		
		if (Settings.DEBUG)
			Logger.log("ButtonHandler", "InterfaceId " + interfaceId + ", componentId " + componentId + ", slotId " + slotId + ", slotId2 " + slotId2 + ", PacketId: " + packetId);
	}

	public static void sendRemove(Player player, int slotId) {
		if (slotId >= 15)
			return;
		player.stopAll(false, false);
		Item item = player.getEquipment().getItem(slotId);
		if (item == null || !player.getInventory().addItem(item.getId(), item.getAmount()))
			return;
		player.getEquipment().getItems().set(slotId, null);
		player.getEquipment().refresh(slotId);
		player.getAppearence().generateAppearenceData();
		if (Runecrafting.isTiara(item.getId()))
			player.getPackets().sendConfig(491, 0);
		if (slotId == 3)
			player.getCombatDefinitions().desecreaseSpecialAttack(0);
		if (slotId == 12)
			;
		player.getAppearence().transformIntoNPC(-1);
		player.unlock();

	}

	public static boolean sendWear(Player player, int slotId, int itemId) {
		if (player.hasFinished() || player.isDead())
			return false;
		player.stopAll(false, false);
		Item item = player.getInventory().getItem(slotId);
		String itemName = item.getDefinitions() == null ? "" : item.getDefinitions().getName().toLowerCase();
		if (item == null || item.getId() != itemId)
			return false;
		if (item.getDefinitions().isNoted() || !item.getDefinitions().isWearItem(player.getAppearence().isMale())) {
			player.getPackets().sendGameMessage("You can't wear that.");
			return true;
		}
		int targetSlot = Equipment.getItemSlot(itemId);
		if (targetSlot == -1) {
			player.getPackets().sendGameMessage("You can't wear that.");
			return true;
		}
		if (!ItemConstants.canWear(item, player))
			return true;
		boolean isTwoHandedWeapon = targetSlot == 3 && Equipment.isTwoHandedWeapon(item);
		if (isTwoHandedWeapon && !player.getInventory().hasFreeSlots() && player.getEquipment().hasShield()) {
			player.getPackets().sendGameMessage("Not enough free space in your inventory.");
			return true;
		}
		HashMap<Integer, Integer> requiriments = item.getDefinitions().getWearingSkillRequiriments();
		boolean hasRequiriments = true;
		if (requiriments != null) {
			for (int skillId : requiriments.keySet()) {
				if (skillId > 24 || skillId < 0)
					continue;
				int level = requiriments.get(skillId);
				if (level < 0 || level > 120)
					continue;
				if (player.getSkills().getLevelForXp(skillId) < level) {
					if (hasRequiriments) {
						player.getPackets().sendGameMessage("You are not high enough level to use this item.");
					}
					hasRequiriments = false;
					String name = Skills.SKILL_NAME[skillId].toLowerCase();
					player.getPackets().sendGameMessage("You need to have a" + (name.startsWith("a") ? "n" : "") + " " + name + " level of " + level + ".");
				}

			}
		}
		if (!hasRequiriments)
			return true;
		if (!player.getControlerManager().canEquip(targetSlot, itemId))
			return false;
		player.stopAll(false, false);
		player.getInventory().deleteItem(slotId, item);
		if (targetSlot == 3) {
			if (isTwoHandedWeapon && player.getEquipment().getItem(5) != null) {
				if (!player.getInventory().addItem(player.getEquipment().getItem(5).getId(), player.getEquipment().getItem(5).getAmount())) {
					player.getInventory().getItems().set(slotId, item);
					player.getInventory().refresh(slotId);
					return true;
				}
				player.getEquipment().getItems().set(5, null);
			}
		} else if (targetSlot == 5) {
			if (player.getEquipment().getItem(3) != null && Equipment.isTwoHandedWeapon(player.getEquipment().getItem(3))) {
				if (!player.getInventory().addItem(player.getEquipment().getItem(3).getId(), player.getEquipment().getItem(3).getAmount())) {
					player.getInventory().getItems().set(slotId, item);
					player.getInventory().refresh(slotId);
					return true;
				}
				player.getEquipment().getItems().set(3, null);
			}

		}
		if (player.getEquipment().getItem(targetSlot) != null && (itemId != player.getEquipment().getItem(targetSlot).getId() || !item.getDefinitions().isStackable())) {
			if (player.getInventory().getItems().get(slotId) == null) {
				player.getInventory().getItems().set(slotId, new Item(player.getEquipment().getItem(targetSlot).getId(), player.getEquipment().getItem(targetSlot).getAmount()));
				player.getInventory().refresh(slotId);
			} else
				player.getInventory().addItem(new Item(player.getEquipment().getItem(targetSlot).getId(), player.getEquipment().getItem(targetSlot).getAmount()));
			player.getEquipment().getItems().set(targetSlot, null);
		}
		if (targetSlot == Equipment.SLOT_AURA)
			player.getAuraManager().removeAura();
		int oldAmt = 0;
		if (player.getEquipment().getItem(targetSlot) != null) {
			oldAmt = player.getEquipment().getItem(targetSlot).getAmount();
		}
		Item item2 = new Item(itemId, oldAmt + item.getAmount());
		player.getEquipment().getItems().set(targetSlot, item2);
		player.getEquipment().refresh(targetSlot, targetSlot == 3 ? 5 : targetSlot == 3 ? 0 : 3);
		player.getAppearence().generateAppearenceData();
		player.getPackets().sendSound(2240, 0, 1);
		if (targetSlot == 3)
			player.getCombatDefinitions().desecreaseSpecialAttack(0);
		player.getCharges().wear(targetSlot);
		return true;
	}

	public static boolean sendWear2(Player player, int slotId, int itemId) {
		if (player.hasFinished() || player.isDead())
			return false;
		player.stopAll(false, false);
		Item item = player.getInventory().getItem(slotId);
		if (item == null || item.getId() != itemId)
			return false;

		if (item.getDefinitions().isNoted() || !item.getDefinitions().isWearItem(player.getAppearence().isMale()) && itemId != 4084) {
			player.getPackets().sendGameMessage("You can't wear that.");
			return false;
		}
		String itemName = item.getDefinitions() == null ? "" : item.getDefinitions().getName().toLowerCase();
		for (String strings : Settings.RARE_ITEMS) {
			if (itemName.contains(strings) && !(player.getRights() >= 3)) {
				player.sm("You need to be an admin to equip " + itemName + ".");
				return false;
			}
		}
		String itemName1 = item.getDefinitions() == null ? "" : item.getDefinitions().getName().toLowerCase();
		int targetSlot = Equipment.getItemSlot(itemId);
		if (itemId == 2133)
			targetSlot = 2;
		if (targetSlot == -1) {
			player.getPackets().sendGameMessage("You can't wear that.");
			return false;
		}
		if (!ItemConstants.canWear(item, player))
			return false;
		boolean isTwoHandedWeapon = targetSlot == 3 && Equipment.isTwoHandedWeapon(item);
		if (isTwoHandedWeapon && !player.getInventory().hasFreeSlots() && player.getEquipment().hasShield()) {
			player.getPackets().sendGameMessage("Not enough free space in your inventory.");
			return false;
		}
		HashMap<Integer, Integer> requiriments = item.getDefinitions().getWearingSkillRequiriments();
		boolean hasRequiriments = true;
		if (requiriments != null) {
			for (int skillId : requiriments.keySet()) {
				if (skillId > 24 || skillId < 0)
					continue;
				int level = requiriments.get(skillId);
				if (level < 0 || level > 120)
					continue;
				if (player.getSkills().getLevelForXp(skillId) < level) {
					if (hasRequiriments)
						player.getPackets().sendGameMessage("You are not high enough level to use this item.");
					hasRequiriments = false;
					String name = Skills.SKILL_NAME[skillId].toLowerCase();
					player.getPackets().sendGameMessage("You need to have a" + (name.startsWith("a") ? "n" : "") + " " + name + " level of " + level + ".");
				}

			}
		}
		if (!hasRequiriments)
			return false;
		if (!player.getControlerManager().canEquip(targetSlot, itemId))
			return false;
		player.getInventory().getItems().remove(slotId, item);
		if (targetSlot == 3) {
			if (isTwoHandedWeapon && player.getEquipment().getItem(5) != null) {
				if (!player.getInventory().getItems().add(player.getEquipment().getItem(5))) {
					player.getInventory().getItems().set(slotId, item);
					return false;
				}
				player.getEquipment().getItems().set(5, null);
			}
		} else if (targetSlot == 5) {
			if (player.getEquipment().getItem(3) != null && Equipment.isTwoHandedWeapon(player.getEquipment().getItem(3))) {
				if (!player.getInventory().getItems().add(player.getEquipment().getItem(3))) {
					player.getInventory().getItems().set(slotId, item);
					return false;
				}
				player.getEquipment().getItems().set(3, null);
			}

		}
		if (player.getEquipment().getItem(targetSlot) != null && (itemId != player.getEquipment().getItem(targetSlot).getId() || !item.getDefinitions().isStackable())) {
			if (player.getInventory().getItems().get(slotId) == null) {
				player.getInventory().getItems().set(slotId, new Item(player.getEquipment().getItem(targetSlot).getId(), player.getEquipment().getItem(targetSlot).getAmount()));
			} else
				player.getInventory().getItems().add(new Item(player.getEquipment().getItem(targetSlot).getId(), player.getEquipment().getItem(targetSlot).getAmount()));
			player.getEquipment().getItems().set(targetSlot, null);
		}
		if (targetSlot == Equipment.SLOT_AURA)
			player.getAuraManager().removeAura();
		int oldAmt = 0;
		if (player.getEquipment().getItem(targetSlot) != null) {
			oldAmt = player.getEquipment().getItem(targetSlot).getAmount();
		}
		Item item2 = new Item(itemId, oldAmt + item.getAmount());
		player.getEquipment().getItems().set(targetSlot, item2);
		player.getEquipment().refresh(targetSlot, targetSlot == 3 ? 5 : targetSlot == 3 ? 0 : 3);
		if (targetSlot == 3)
			player.getCombatDefinitions().desecreaseSpecialAttack(0);
		player.getCharges().wear(targetSlot);
		return true;
	}

	public static void submitSpecialRequest(final Player player) {
		CoresManager.fastExecutor.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					WorldTasksManager.schedule(new WorldTask() {
						@Override
						public void run() {
							player.getCombatDefinitions().switchUsingSpecialAttack();
						}
					}, 0);
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, 200);
	}

	public static void sendWear(Player player, int[] slotIds) {
		if (player.hasFinished() || player.isDead())
			return;
		boolean worn = false;
		Item[] copy = player.getInventory().getItems().getItemsCopy();
		for (int slotId : slotIds) {
			Item item = player.getInventory().getItem(slotId);
			if (item == null)
				continue;
			if (sendWear2(player, slotId, item.getId()))
				worn = true;
		}
		player.getInventory().refreshItems(copy);
		if (worn) {
			player.getAppearence().generateAppearenceData();
			player.getPackets().sendSound(2240, 0, 1);
		}
	}

	public static void openEquipmentBonuses(final Player player, boolean banking) {
		player.stopAll();
		player.getInterfaceManager().sendInventoryInterface(670);
		player.getInterfaceManager().sendInterface(667);
		player.getPackets().sendConfigByFile(4894, banking ? 1 : 0);
		player.getPackets().sendItems(93, player.getInventory().getItems());
		player.getPackets().sendInterSetItemsOptionsScript(670, 0, 93, 4, 7, "Equip", "Compare", "Stats", "Examine");
		player.getPackets().sendUnlockIComponentOptionSlots(670, 0, 0, 27, 0, 1, 2, 3);
		player.getPackets().sendIComponentSettings(667, 14, 0, 13, 1030);
		refreshEquipBonuses(player);
		if (banking) {
			player.getTemporaryAttributtes().put("Banking", Boolean.TRUE);
			player.setCloseInterfacesEvent(new Runnable() {
				@Override
				public void run() {
					player.getTemporaryAttributtes().remove("Banking");
				}

			});
		}
	}

	public static void refreshEquipBonuses(Player player) {
		player.getPackets().sendIComponentText(667, 28, "Stab: +" + player.getCombatDefinitions().getBonuses()[0]);
		player.getPackets().sendIComponentText(667, 29, "Slash: +" + player.getCombatDefinitions().getBonuses()[1]);
		player.getPackets().sendIComponentText(667, 30, "Crush: +" + player.getCombatDefinitions().getBonuses()[2]);
		player.getPackets().sendIComponentText(667, 31, "Magic: +" + player.getCombatDefinitions().getBonuses()[3]);
		player.getPackets().sendIComponentText(667, 32, "Range: +" + player.getCombatDefinitions().getBonuses()[4]);
		player.getPackets().sendIComponentText(667, 33, "Stab: +" + player.getCombatDefinitions().getBonuses()[5]);
		player.getPackets().sendIComponentText(667, 34, "Slash: +" + player.getCombatDefinitions().getBonuses()[6]);
		player.getPackets().sendIComponentText(667, 35, "Crush: +" + player.getCombatDefinitions().getBonuses()[7]);
		player.getPackets().sendIComponentText(667, 36, "Magic: +" + player.getCombatDefinitions().getBonuses()[8]);
		player.getPackets().sendIComponentText(667, 37, "Range: +" + player.getCombatDefinitions().getBonuses()[9]);
		player.getPackets().sendIComponentText(667, 38, "Summoning: +" + player.getCombatDefinitions().getBonuses()[10]);
		player.getPackets().sendIComponentText(667, 39, "Absorb Melee: +" + player.getCombatDefinitions().getBonuses()[CombatDefinitions.ABSORVE_MELEE_BONUS] + "%");
		player.getPackets().sendIComponentText(667, 40, "Absorb Magic: +" + player.getCombatDefinitions().getBonuses()[CombatDefinitions.ABSORVE_MAGE_BONUS] + "%");
		player.getPackets().sendIComponentText(667, 41, "Absorb Ranged: +" + player.getCombatDefinitions().getBonuses()[CombatDefinitions.ABSORVE_RANGE_BONUS] + "%");
		player.getPackets().sendIComponentText(667, 42, "Strength: " + player.getCombatDefinitions().getBonuses()[14]);
		player.getPackets().sendIComponentText(667, 43, "Ranged Str: " + player.getCombatDefinitions().getBonuses()[15]);
		player.getPackets().sendIComponentText(667, 44, "Prayer: +" + player.getCombatDefinitions().getBonuses()[16]);
		player.getPackets().sendIComponentText(667, 45, "Magic Damage: +" + player.getCombatDefinitions().getBonuses()[17] + "%");
	}
}
