package com.rs.game.player.dialogues.impl;
/*package com.rs.game.player.dialogues;

import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.item.Item;

public class TriviaRewards extends Dialogue {
	
	private int category;
	private static String[] titleNames = {"Rap God", "Musical Genius", "Dr. Beat", "Melodic", 
							"Problem Solver", "Cryptic", "Enigmatic", "Brainiac",
							"Elveron Prodigy", "Elveron Citizen", "Elveron Lord", "Elveron PvM",
							"Trivia Master", "Quizler", "Grand Master", "The Wise",
							"Critic", "The Fanatic", "The Captivating", "All Star"};
	private static Item[] itemRewards = {new Item(13103, 1), new Item(19747, 1), new Item(22418, 1), new Item(27343, 1), 
							new Item(15374, 1), new Item(19706, 1), new Item(19707, 1), new Item(19708, 1),
							new Item(26470, 1), new Item(26472, 1), new Item(26468, 1), new Item(26466, 1),
							new Item(25820, 1), new Item(25819, 1), new Item(25817, 1), new Item(25916, 1),
							new Item(26787, 1), new Item(4566, 1), new Item(29998, 1), new Item(28894, 1)};

	@Override
	public void start() {
		sendOptionsDialogue("Select a Category", "Music", "Puzzles", "Server", "General", "Movies");
		stage = -1;
		return;
	}

	@Override
	public void run(int interfaceId, int option) {
		switch (stage) {
		case -1:
			switch (option) {
			case OPTION_1:
				category = 0;
				sendDialogue("You currently have "+player.getTriviaPoints(category)+" points in this category.");
				stage = 1;
				break;
			case OPTION_2:
				category = 1;
				sendDialogue("You currently have "+player.getTriviaPoints(category)+" points in this category.");
				stage = 1;
				break;
			case OPTION_3:
				category = 2;
				sendDialogue("You currently have "+player.getTriviaPoints(category)+" points in this category.");
				stage = 1;
				break;
			case OPTION_4:
				category = 3;
				sendDialogue("You currently have "+player.getTriviaPoints(category)+" points in this category.");
				stage = 1;
				break;
			case OPTION_5:
				category = 4;
				sendDialogue("You currently have "+player.getTriviaPoints(category)+" points in this category.");
				stage = 1;
				break;
			}
			break;
		case 1:
			sendOptionsDialogue("Select a Purchase", "Coins", "Spins", "Titles", "Items", "Nothing");
			stage = 2;
			break;
		case 2:
			switch (option) {
			case OPTION_1:
				sendOptionsDialogue("Select an Amount", "20,000gp (1)", "125,000gp (5)", "400,000gp (15)", "1,000,000gp (30)", "Nothing");
				stage = 3;
				break;
			case OPTION_2:
				sendOptionsDialogue("Select an Amount", "2 Spins (1)", "12 Spins (5)", "40 Spins (15)", "80 Spins (25)", "Nothing");
				stage = 4;
				break;
			case OPTION_3:
				sendOptionsDialogue("Select a Title (10)", getTitleName(category, 0), getTitleName(category, 1), getTitleName(category, 2), getTitleName(category, 3), "Nothing");
				stage = 5;
				break;
			case OPTION_4:
				sendOptionsDialogue("Select an Item", getItem(category, 0).getDefinitions().getName() + " (25)", getItem(category, 1).getDefinitions().getName() + " (30)", getItem(category, 2).getDefinitions().getName() + " (40)", getItem(category, 3).getDefinitions().getName() + " (50)", "Nothing");
				stage = 6;
				break;
			case OPTION_5:
				sendDialogue("You decide to purchase nothing.");
				stage = 20;
				break;
			}
			break;
		case 3:
			switch (option) {
			case OPTION_1:
				if (player.getTriviaPoints(category) >= 1) {
					player.getBank().addItem(995, 20000, true);
					player.takeTriviaPoints(category, 1);
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().sendChatBoxInterface(1189);
					player.getPackets().sendItemOnIComponent(1189, 1, 995, 20000);
					player.getPackets().sendIComponentText(1189, 4, "The coins have been added to your bank.");
					stage = 20;
				} else {
					sendDialogue("You need 1 point to buy this item.");
					stage = 20;
				}
				break;
			case OPTION_2:
				if (player.getTriviaPoints(category) >= 5) {
					player.getBank().addItem(995, 125000, true);
					player.takeTriviaPoints(category, 5);
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().sendChatBoxInterface(1189);
					player.getPackets().sendItemOnIComponent(1189, 1, 995, 125000);
					player.getPackets().sendIComponentText(1189, 4, "The coins have been added to your bank.");
					stage = 20;
				} else {
					sendDialogue("You need 5 points to buy this item.");
					stage = 20;
				}
				break;
			case OPTION_3:
				if (player.getTriviaPoints(category) >= 15) {
					player.getBank().addItem(995, 400000, true);
					player.takeTriviaPoints(category, 15);
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().sendChatBoxInterface(1189);
					player.getPackets().sendItemOnIComponent(1189, 1, 995, 400000);
					player.getPackets().sendIComponentText(1189, 4, "The coins have been added to your bank.");
					stage = 20;
				} else {
					sendDialogue("You need 15 points to buy this item.");
					stage = 20;
				}
				break;
			case OPTION_4:
				if (player.getTriviaPoints(category) >= 30) {
					player.getBank().addItem(995, 1000000, true);
					player.takeTriviaPoints(category, 30);
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().sendChatBoxInterface(1189);
					player.getPackets().sendItemOnIComponent(1189, 1, 995, 1000000);
					player.getPackets().sendIComponentText(1189, 4, "The coins have been added to your bank.");
					stage = 20;
				} else {
					sendDialogue("You need 30 points to buy this item.");
					stage = 20;
				}
				break;
			case OPTION_5:
				sendDialogue("You decide to purchase nothing.");
				stage = 20;
				break;
			}
			break;
		case 4:
			switch (option) {
			case OPTION_1:
				if (player.getTriviaPoints(category) >= 1) {
					player.getBank().addItem(24154, 2, true);
					player.takeTriviaPoints(category, 1);
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().sendChatBoxInterface(1189);
					player.getPackets().sendItemOnIComponent(1189, 1, 24154, 2);
					player.getPackets().sendIComponentText(1189, 4, "The spin tickets have been added to your bank.");
					stage = 20;
				} else {
					sendDialogue("You need 1 point to buy this item.");
					stage = 20;
				}
				break;
			case OPTION_2:
				if (player.getTriviaPoints(category) >= 5) {
					player.getBank().addItem(24154, 12, true);
					player.takeTriviaPoints(category, 5);
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().sendChatBoxInterface(1189);
					player.getPackets().sendItemOnIComponent(1189, 1, 24154, 5);
					player.getPackets().sendIComponentText(1189, 4, "The spin tickets have been added to your bank.");
					stage = 20;
				} else {
					sendDialogue("You need 5 points to buy this item.");
					stage = 20;
				}
				break;
			case OPTION_3:
				if (player.getTriviaPoints(category) >= 15) {
					player.getBank().addItem(24154, 40, true);
					player.takeTriviaPoints(category, 15);
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().sendChatBoxInterface(1189);
					player.getPackets().sendItemOnIComponent(1189, 1, 24154, 15);
					player.getPackets().sendIComponentText(1189, 4, "The spin tickets have been added to your bank.");
					stage = 20;
				} else {
					sendDialogue("You need 15 points to buy this item.");
					stage = 20;
				}
				break;
			case OPTION_4:
				if (player.getTriviaPoints(category) >= 25) {
					player.getBank().addItem(24154, 80, true);
					player.takeTriviaPoints(category, 25);
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().sendChatBoxInterface(1189);
					player.getPackets().sendItemOnIComponent(1189, 1, 24154, 80);
					player.getPackets().sendIComponentText(1189, 4, "The spin tickets have been added to your bank.");
					stage = 20;
				} else {
					sendDialogue("You need 25 points to buy this item.");
					stage = 20;
				}
				break;
			case OPTION_5:
				sendDialogue("You decide to purchase nothing.");
				stage = 20;
				break;
			}
			break;
		case 5:
			switch (option) {
			case OPTION_1:
				if (player.getTriviaPoints(category) >= 10) {
					player.takeTriviaPoints(category, 10);
					player.getAppearence().setTitle(getTitleId(category, 0));
					sendDialogue("You are now using your new title.");
					stage = 20;
				} else {
					sendDialogue("You need 10 points to buy this item.");
					stage = 20;
				}
				break;
			case OPTION_2:
				if (player.getTriviaPoints(category) >= 10) {
					player.takeTriviaPoints(category, 10);
					player.getAppearence().setTitle(getTitleId(category, 1));
					sendDialogue("You are now using your new title.");
					stage = 20;
				} else {
					sendDialogue("You need 10 points to buy this item.");
					stage = 20;
				}
				break;
			case OPTION_3:
				if (player.getTriviaPoints(category) >= 10) {
					player.takeTriviaPoints(category, 10);
					player.getAppearence().setTitle(getTitleId(category, 2));
					sendDialogue("You are now using your new title.");
					stage = 20;
				} else {
					sendDialogue("You need 10 points to buy this item.");
					stage = 20;
				}
				break;
			case OPTION_4:
				if (player.getTriviaPoints(category) >= 10) {
					player.takeTriviaPoints(category, 10);
					player.getAppearence().setTitle(getTitleId(category, 3));
					sendDialogue("You are now using your new title.");
					stage = 20;
				} else {
					sendDialogue("You need 10 points to buy this item.");
					stage = 20;
				}
				break;
			case OPTION_5:
				sendDialogue("You decide to purchase nothing.");
				stage = 20;
				break;
			}
			break;
		case 6:
			switch (option) {
			case OPTION_1:
				if (player.getTriviaPoints(category) >= 25) {
					player.getBank().addItem(getItem(category, 0), true);
					player.takeTriviaPoints(category, 25);
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().sendChatBoxInterface(1189);
					player.getPackets().sendItemOnIComponent(1189, 1, getItem(category, 0).getId(), 1);
					player.getPackets().sendIComponentText(1189, 4, "The "+getItem(category, 0).getDefinitions().getName()+" has been added to your bank.");
					stage = 20;
				} else {
					sendDialogue("You need 25 points to buy this item.");
					stage = 20;
				}
				break;
			case OPTION_2:
				if (player.getTriviaPoints(category) >= 30) {
					player.getBank().addItem(getItem(category, 1), true);
					player.takeTriviaPoints(category, 30);
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().sendChatBoxInterface(1189);
					player.getPackets().sendItemOnIComponent(1189, 1, getItem(category, 1).getId(), 1);
					player.getPackets().sendIComponentText(1189, 4, "The "+getItem(category, 1).getDefinitions().getName()+" has been added to your bank.");
					stage = 20;
				} else {
					sendDialogue("You need 30 points to buy this item.");
					stage = 20;
				}
				break;
			case OPTION_3:
				if (player.getTriviaPoints(category) >= 40) {
					player.getBank().addItem(getItem(category, 2), true);
					player.takeTriviaPoints(category, 40);
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().sendChatBoxInterface(1189);
					player.getPackets().sendItemOnIComponent(1189, 1, getItem(category, 2).getId(), 1);
					player.getPackets().sendIComponentText(1189, 4, "The "+getItem(category, 2).getDefinitions().getName()+" has been added to your bank.");
					stage = 20;
				} else {
					sendDialogue("You need 40 points to buy this item.");
					stage = 20;
				}
				break;
			case OPTION_4:
				if (player.getTriviaPoints(category) >= 50) {
					player.getBank().addItem(getItem(category, 3), true);
					player.takeTriviaPoints(category, 50);
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().sendChatBoxInterface(1189);
					player.getPackets().sendItemOnIComponent(1189, 1, getItem(category, 3).getId(), 1);
					player.getPackets().sendIComponentText(1189, 4, "The "+getItem(category, 3).getDefinitions().getName()+" has been added to your bank.");
					stage = 20;
				} else {
					sendDialogue("You need 50 points to buy this item.");
					stage = 20;
				}
				break;
			case OPTION_5:
				sendDialogue("You decide to purchase nothing.");
				stage = 20;
				break;
			}
			break;
			case 20:
				end();
				break;
			default:
				end();
				break;
		}
	}

	@Override
	public void finish() {

	}
	
	public static String getTitleName(int category, int position) {
		int titlePosition = category*4;
		titlePosition += position;
		return titleNames[titlePosition];
	}
	
	public static int getTitleId(int category, int position) {
		int titleId = category*4;
		titleId += position;
		titleId += 10000;
		return titleId;
	}
	
	public static Item getItem(int category, int position) {
		int itemPosition = category*4;
		itemPosition += position;
		return itemRewards[itemPosition];
	}
}
*/