package com.rs.game.player.content.grandexchange;

import java.util.List;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.player.Player;
import com.rs.game.player.content.ItemConstants;
import com.rs.utils.ItemExamines;
import com.rs.utils.Utils;

public class GrandExchange extends GrandExchangeConfigurations {

	/*
	
		player.stopAll();
		sendMainScreenConfigs(player);
		player.getInterfaceManager().closeChatBoxInterface();
		player.getInterfaceManager().closeInventoryInterface();
		player.getInterfaceManager().sendInterface(MAIN_INTERFACE);
		player.getPackets().sendUnlockIComponentOptionSlots(MAIN_INTERFACE,
				209, -1, -1, 1, 2, 3, 5, 6);
		player.getPackets().sendUnlockIComponentOptionSlots(MAIN_INTERFACE,
				211, -1, -1, 1, 2, 3, 5, 6);
		for (int i = 0; i < 6; i++) {
			player.getPackets().sendGrandExchangeBar(player, i, 0,
					Progress.RESET, 0, 0, 0);
		}
		for (GrandExchangeOffer offer : GrandExchangeLoader
				.getOffersForPlayer(player.getDisplayName())) {
			switch (offer.getType()) {
			case BUY:
				if (!offer.isAborted())
					player.getPackets().sendGrandExchangeBar(
							player,
							offer.getSlot(),
							offer.getItem().getId(),
							offer.isFinished() ? Progress.FINISHED_BUYING
									: Progress.BUY_PROGRESSING,
							offer.getPrice(), offer.getSecondaryAmount(),
							offer.getPrimaryAmount());
				else {
					player.getPackets().sendGrandExchangeBar(player,
							offer.getSlot(), offer.getItem().getId(),
							Progress.BUY_ABORTED, offer.getPrice(),
							offer.getSecondaryAmount(),
							offer.getPrimaryAmount());
				}
				break;
			case SELL:
				if (!offer.isAborted())
					player.getPackets().sendGrandExchangeBar(
							player,
							offer.getSlot(),
							offer.getItem().getId(),
							offer.isFinished() ? Progress.FINISHED_SELLING
									: Progress.SELL_PROGRESSING,
							offer.getPrice(), offer.getSecondaryAmount(),
							offer.getPrimaryAmount());
				else {
					player.getPackets().sendGrandExchangeBar(player,
							offer.getSlot(), offer.getItem().getId(),
							Progress.SELL_ABORTED, offer.getPrice(),
							offer.getSecondaryAmount(),
							offer.getPrimaryAmount());
				}
				break;
			}
		}
	}

	/*public void displayOffers(Player player) {
		int interfaceId = 275;
		int startLine = 11;
		player.getInterfaceManager().sendInterface(interfaceId);
		for (int i = 0; i < 300; i++) {
			player.getPackets().sendIComponentText(interfaceId, i, "");
		}
		List<GrandExchangeOffer> offers = GrandExchangeLoader.load();
		for (GrandExchangeOffer offer : offers) {
			if (offer.isFinished()) {
				
			} else {
				if (offers.size() >= 300) {
					return;
				}
				player.getPackets().sendIComponentText(interfaceId, startLine++,
						"[<col=FF0000>" + offer.getType().toString() + "</col>]"
						+ " " + (offer.getPrimaryAmount() - offer.getSecondaryAmount()) 
						+ "x " + offer.getItem().getDefinitions().getName()
						+ " - " + Utils.format(offer.getPrice()) + " each.");
			}
		}
		player.getPackets().sendIComponentText(interfaceId, 1,
				"GrandExchange Offers");
	}

	@SuppressWarnings("unchecked")
	public boolean handleButtonEvent(Player player, int interfaceId,
			int buttonId, int packetId, int slotId, int slotId2) {
		switch (interfaceId) {
		case MAIN_INTERFACE:
			GrandExchangeOffer offer;
			switch (buttonId) {
			case 208:
			case 206: // collecting
				boolean noting = false;
				GrandExchangeOffer o = getOfferBySlot(
						player,
						(Integer) player.getTemporaryAttributtes().get(
								"grand_exchange_slot_clicked"));
				if (o.isAborted()) {
					if (packetId == 67) {
						noting = true;
					}
				}
				ItemsContainer<Item> ic = (ItemsContainer<Item>) player
						.getTemporaryAttributtes().get("grand_exchange_items");
				Item item = buttonId == 206 ? ic.get(0) : ic.get(1);
				if (item.getAmount() > 1 && item.getId() != 995
						&& packetId == 14)
					noting = true;
				if (noting)
					if (item.getDefinitions().isStackable()) {
						
					} else {
						item.setId(item.getDefinitions().getCertId());
					}
				ic.remove(item);
				player.getInventory().addItem(item);
				player.getPackets().sendItems(523 + o.getSlot(), ic);
				// if (o.getType() != Type.SELL && item.getId() == 995)
				// o.setExtraCash(0);
				if ((o.isFinished() || o.isAborted()) && ic.getUsedSlots() == 0) {
					GrandExchangeLoader.removeOffer(o);
					resetInterfaceConfigs(player);
					display(player);
				} else {
					if (o.isAborted()) {

					} else {
						o.setPrimaryAmount(o.getPrimaryAmount()
								- o.getSecondaryAmount());
						o.setSecondaryAmount(0);
					}
					if (ic.getUsedSlots() == 0) { // all of the items have been
													// removed from the
													// container
						player.getTemporaryAttributtes().remove(
								"grand_exchange_items");
					}
				}
				break;
			case 19:
			case 35:
			case 51:
			case 108:
			case 89:
			case 70:
				player.getTemporaryAttributtes().put(
						"grand_exchange_slot_clicked", getSlot(buttonId));
				offer = getOfferBySlot(player, getSlot(buttonId));
				if (packetId == 67) {// abort
					if (offer.isFinished()) {
						player.getPackets()
								.sendGameMessage(
										"That offer has just been completed! You are too late.");
						return true;
					}
					if (offer.getSecondaryAmount() > 0) {
						player.getPackets()
								.sendGameMessage(
										"You need to collect items from the offer before aborting it.");
						return true;
					}
					offer.setAborted(true);
					display(player);
				} else {
					sendCollectInformation(player, buttonId);
				}
				break;
			case 31:
			case 82:
			case 101:
			case 47:
			case 63:
			case 120:
				player.getTemporaryAttributtes().put("grand_exchange_slot_clicked", getSlot(buttonId));
				sendBuyScreen(player);
				break;
			case 83:
			case 32:
			case 48:
			case 102:
			case 121:
			case 64:
				player.getTemporaryAttributtes().put("grand_exchange_slot_clicked", getSlot(buttonId));
				sendSellScreen(player);
				break;
			case 169:// Item Price -1
				try {
					player.getActionCalled().handlePriceMinusOne();
				} catch (Exception e) {
					
				}
				break;
			case 171:// Item Price +1
				try {
					player.getActionCalled().handlePriceAddOne();
				} catch (Exception e) {
					
				}
				break;
			case 179:// Item Price +5%
				try {
					player.getActionCalled().handlePriceAddFivePercent();
				} catch (Exception e) {
					
				}
				break;
			case 181:// Item Price -5%
				try {
					player.getActionCalled().handlePriceMinusFivePercent();
				} catch (Exception e) {
					
				}
				break;
			case 168:// Item Custom Amount
				try {
					player.getActionCalled().handleCustomAmount(player);
				} catch (Exception e) {
					
				}
				break;
			case 177:// Item Custom Price
				try {
					player.getActionCalled().handleCustomPrice();
				} catch (Exception e) {
					
				}
				break;
			case 175:// Item Guide Price
				try {
					player.getActionCalled().handleGuidePrice(player);
				} catch (Exception e) {
					
				}
				break;
			case 155:// Item Amount -1
				try {
					player.getActionCalled().handleAmountMinusOne(player);
				} catch (Exception e) {
					
				}
				break;
			case 157:// Item Amount +1
				try {
					player.getActionCalled().handleAmountAddOne(player);
				} catch (Exception e) {
					
				}
				break;
			case 160:// Item Amount Set 1
				try {
					player.getActionCalled().handleAmountOne(player);
				} catch (Exception e) {
					
				}
				break;
			case 162:// Item Amount +10
				try {
					player.getActionCalled().handleAmountTen(player);
				} catch (Exception e) {
					
				}
				break;
			case 164:// Item Amount +100
				try {
					player.getActionCalled().handleAmountHundred(player);
				} catch (Exception e) {
					
				}
				break;
			case 166:
				offer = (GrandExchangeOffer) player.getTemporaryAttributtes()
						.get("grand_exchange_offer");
				switch (offer.getType()) {
				case BUY:// Item Amount +1000
					try {
						player.getActionCalled().handleAmountThousand(player);
					} catch (Exception e) {
						
					}
					break;
				case SELL:// Item Amount ALL
					try {
						player.getActionCalled().handleAmountAll(player);
					} catch (Exception e) {
						
					}
					break;
				}
				break;
			case 128:// GrandExchangeD Back
				player.getActionCalled().handleBackButton(player);
				break;
			case 186:// Confirm
				try {
					player.getActionCalled().handleConfirm(player);
				} catch (Exception e) {
					
				}
				break;
			case 190:// Choose Item
				player.getActionCalled().handleItemSearch(player);
				break;
			case 200:// Collection Abort
				player.getActionCalled().handleCollectionAbort(player);
				break;
			default:// UnUsed Action
				System.out.println("Unhandled button event: [interfaceId="
						+ interfaceId + ", buttonId=" + buttonId
						+ ", packetId=" + packetId + "]");
				break;
			}
			return true;
		case SELL_INTERFACE:
			Item item = new Item(slotId2);
			if (!ItemConstants.isTradeable(item) || item.getId() == 995) {
				player.getPackets().sendGameMessage(
						"You can't sell such an item.");
				return true;
			}

			if (item.getDefinitions().isNoted()) {
				item.setId(item.getDefinitions().getCertId());
			}
			offer = new GrandExchangeOffer(
					player.getDisplayName(),
					(int) player.getTemporaryAttributtes().get(
							"grand_exchange_slot_clicked"),
					item,
					1,
					ItemDefinitions.getItemDefinitions(item.getId()).getValue(),
					Type.SELL);
			player.getPackets().sendConfig(1109, offer.getItem().getId());
			player.getPackets().sendConfig(1110, 1);
			player.getPackets().sendConfig(1111, offer.getPrice());
			player.getPackets().sendConfig(1114, offer.getPrice());
			player.getPackets().sendIComponentText(105, 143, ItemExamines.getExamine(item));
			player.getTemporaryAttributtes().put("grand_exchange_offer", offer);
			return true;
		}
		return false;
	}

	public GrandExchangeOffer getOfferBySlot(Player player, int slot) {
		for (GrandExchangeOffer offer : GrandExchangeLoader
				.getOffersForPlayer(player.getDisplayName())) {
			if (offer.getSlot() == slot)
				return offer;
		}
		return null;
	}

	private void sendCollectInformation(Player player, int buttonId) {
		player.getPackets().sendConfig(1112, getSlot(buttonId));
		player.getPackets().sendIComponentSettings(105, 206, -1, -1, 6);
		player.getPackets().sendIComponentSettings(105, 208, -1, -1, 6);
		GrandExchangeOffer offer = getOfferBySlot(player, getSlot(buttonId));
		if (offer == null)
			return;
		ItemsContainer<Item> ic = new ItemsContainer<Item>(2, true);
		if (!offer.isAborted()) {
			if (offer.getSecondaryAmount() > 0)
				switch (offer.getType()) {
				case BUY:
					ic.add(new Item(offer.getItem().getId(), offer
							.getSecondaryAmount()));
					break;
				case SELL:
					ic.add(new Item(995, offer.getSecondaryAmount()
							* offer.getPrice()));
					break;
				}
		} else {
			switch (offer.getType()) {
			case BUY:
				ic.add(new Item(995, offer.getPrimaryAmount()
						* offer.getPrice()));
				break;
			case SELL:
				ic.add(new Item(offer.getItem().getId(), offer
						.getPrimaryAmount()));
				break;
			}
		}
		// if (offer.getExtraCash() >= 1) {
		// ic.add(new Item(995, offer.getExtraCash()));
		// }
		player.getTemporaryAttributtes().put("grand_exchange_items", ic);
		player.getPackets().sendConfig(1113, offer.getType().ordinal());
		player.getPackets().sendItems(523 + getSlot(buttonId), ic);
	}

	private void sendMainScreenConfigs(Player player) {
		player.getPackets().sendConfig(563, 4194304);
		player.getPackets().sendConfig(1112, -1);
		player.getPackets().sendConfig(1113, -1);
		player.getPackets().sendConfig(1114, 0);
		player.getPackets().sendConfig(1109, -1);
		player.getPackets().sendConfig(1110, 0);
		player.getPackets().sendConfig(1111, 1);
	}

	private int getSlot(int buttonId) {
		switch (buttonId) {
		case 31:
		case 32:
		case 19:
			return 0;
		case 47:
		case 35:
		case 48:
			return 1;
		case 63:
		case 51:
		case 64:
			return 2;
		case 82:
		case 83:
		case 70:
			return 3;
		case 101:
		case 102:
		case 89:
			return 4;
		case 120:
		case 108:
		case 121:
			return 5;
		default:
			return -1;
		}
	}

	private void sendSellScreen(Player player) {
		resetInterfaceConfigs(player);
		player.getPackets().sendConfig(1113, 1);
		player.getInterfaceManager().sendInventoryInterface(107);
		final Object[] params = new Object[] { "", "", "", "", "Offer", -1, 0,
				7, 4, 93, 7012370 };
		player.getPackets().sendRunScript(149, params);
		player.getPackets().sendItems(93, player.getInventory().getItems());
		player.getPackets().sendHideIComponent(107, 0, false);
		player.getPackets().sendIComponentSettings(107, 18, 0, 27, 1026);
		player.getPackets().sendConfig(
				1112,
				(Integer) player.getTemporaryAttributtes().get(
						"grand_exchange_slot_clicked"));
		player.getPackets().sendHideIComponent(105, 196, true);
	}

	public void sendBuyScreen(Player player) {
		resetInterfaceConfigs(player);
		player.getActionCalled().handleItemSearch(player);
	}

	public void resetInterfaceConfigs(Player player) {
		player.getPackets().sendConfig2(1109, -1);
		player.getPackets().sendConfig2(1110, 0);
		player.getPackets().sendConfig2(1111, 0);
		player.getPackets().sendConfig2(1112, -1);
		player.getPackets().sendConfig2(1113, 0);
	}

	public static GrandExchange get() {
		return INSTANCE;
	}

	private static final GrandExchange INSTANCE = new GrandExchange(); */

}
