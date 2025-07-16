package com.rs.game.player.content.grandexchange;

import java.io.Serializable;

import com.rs.game.player.Player;
import com.rs.utils.SerializableFilesManager;

public class GrandExchangeAction implements Serializable {

	private static final long serialVersionUID = -666991824680663510L;

	private transient Player player;

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	// TODO Extra Coins

	/*public void handleAmountAddOne(Player player) {
		GrandExchangeOffer offer;
		player.getTemporaryAttributtes().remove("note_item");
		player.getTemporaryAttributtes().remove("added_all_item");
		offer = (GrandExchangeOffer) player.getTemporaryAttributtes().get("grand_exchange_offer");
		switch (offer.getType()) {
		case BUY:
			if (offer.getPrimaryAmount() + 1 < 0) {
				// Do Nothing
				return;
			}
			offer.setPrimaryAmount(offer.getPrimaryAmount() + 1);
			player.getPackets().sendConfig(1110, offer.getPrimaryAmount());
			break;
		case SELL:
			if (offer.getPrimaryAmount() + 1 < 0) {
				// Do Nothing
				return;
			}
			if (offer.getPrimaryAmount() + 1 == player.getInventory().getNumerOf(offer.getItem().getId())
					+ player.getInventory().getNumerOf(offer.getItem().getDefinitions().getCertId())) {
				handleAmountAll(player);
				return;
			}
			offer.setPrimaryAmount(offer.getPrimaryAmount() + 1);
			player.getPackets().sendConfig(1110, offer.getPrimaryAmount());
			break;
		}
	}
	
	public void handleAmountOne(Player player) {
		GrandExchangeOffer offer;
		player.getTemporaryAttributtes().remove("note_item");
		player.getTemporaryAttributtes().remove("added_all_item");
		offer = (GrandExchangeOffer) player.getTemporaryAttributtes().get("grand_exchange_offer");
		switch (offer.getType()) {
		case BUY:
			if (offer.getPrimaryAmount() + 1 < 0) {
				// Do Nothing
				return;
			}
			offer.setPrimaryAmount(offer.getPrimaryAmount() + 1);
			player.getPackets().sendConfig(1110, offer.getPrimaryAmount());
			break;
		case SELL:
			offer.setPrimaryAmount(1);
			player.getPackets().sendConfig(1110, offer.getPrimaryAmount());
			break;
		}
	}
	
	public void handleAmountMinusOne(Player player) {
		GrandExchangeOffer offer;
		player.getTemporaryAttributtes().remove("note_item");
		player.getTemporaryAttributtes().remove("added_all_item");
		offer = (GrandExchangeOffer) player.getTemporaryAttributtes().get("grand_exchange_offer");
		switch (offer.getType()) {
		case BUY:
			if (offer.getPrimaryAmount() - 1 < 0) {
				// Do nothing.
				return;
			}
			offer.setPrimaryAmount(offer.getPrimaryAmount() - 1);
			player.getPackets().sendConfig(1110, offer.getPrimaryAmount());
			break;
		case SELL:
			if (offer.getPrimaryAmount() - 1 < 0) {
				// Do nothing.
				return;
			}
			if (offer.getPrimaryAmount() - 1 == player.getInventory().getNumerOf(offer.getItem().getId())
					+ player.getInventory().getNumerOf(offer.getItem().getDefinitions().getCertId())) {
				handleAmountAll(player);
				return;
			}
			offer.setPrimaryAmount(offer.getPrimaryAmount() - 1);
			player.getPackets().sendConfig(1110, offer.getPrimaryAmount());
			break;
		}
	}

	public void handleAmountTen(Player player) {
		GrandExchangeOffer offer;
		player.getTemporaryAttributtes().remove("note_item");
		player.getTemporaryAttributtes().remove("added_all_item");
		offer = (GrandExchangeOffer) player.getTemporaryAttributtes().get("grand_exchange_offer");
		switch (offer.getType()) {
		case BUY:
			if (offer.getPrimaryAmount() + 10 < 0) {
				// Do Nothing
				return;
			}
			offer.setPrimaryAmount(offer.getPrimaryAmount() + 10);
			player.getPackets().sendConfig(1110, offer.getPrimaryAmount());
			break;
		case SELL:
			offer.setPrimaryAmount(10);
			player.getPackets().sendConfig(1110, offer.getPrimaryAmount());
			if (offer.getPrimaryAmount() == player.getInventory().getNumerOf(offer.getItem().getId())
					+ player.getInventory().getNumerOf(offer.getItem().getDefinitions().getCertId())) {
				handleAmountAll(player);
				return;
			}
			break;
		}
	}

	public void handleAmountHundred(Player player) {
		GrandExchangeOffer offer;
		player.getTemporaryAttributtes().remove("note_item");
		player.getTemporaryAttributtes().remove("added_all_item");
		offer = (GrandExchangeOffer) player.getTemporaryAttributtes().get("grand_exchange_offer");
		switch (offer.getType()) {
		case BUY:
			if (offer.getPrimaryAmount() + 100 < 0) {
				// Do Nothing
				return;
			}
			offer.setPrimaryAmount(offer.getPrimaryAmount() + 100);
			player.getPackets().sendConfig(1110, offer.getPrimaryAmount());
			break;
		case SELL:
			offer.setPrimaryAmount(100);
			player.getPackets().sendConfig(1110, offer.getPrimaryAmount());
			if (offer.getPrimaryAmount() == player.getInventory().getNumerOf(offer.getItem().getId())
					+ player.getInventory().getNumerOf(offer.getItem().getDefinitions().getCertId())) {
				handleAmountAll(player);
				return;
			}
			break;
		}
	}

	public void handleAmountThousand(Player player) {// Buy Only
		GrandExchangeOffer offer;
		player.getTemporaryAttributtes().remove("note_item");
		player.getTemporaryAttributtes().remove("added_all_item");
		offer = (GrandExchangeOffer) player.getTemporaryAttributtes().get("grand_exchange_offer");
		if (offer.getPrimaryAmount() + 1000 < 0) {
			// Do Nothing
			return;
		}
		offer.setPrimaryAmount(offer.getPrimaryAmount() + 1000);
		player.getPackets().sendConfig(1110, offer.getPrimaryAmount());
	}
	
	public void handleAmountAll(Player player) {// Sell Only
		GrandExchangeOffer offer;
		offer = (GrandExchangeOffer) player.getTemporaryAttributtes().get("grand_exchange_offer");
		int amount = 0;
		if (player.getInventory().contains(offer.getItem().getDefinitions().getCertId())
				&& player.getInventory().contains(offer.getItem().getId())) {
			amount = player.getInventory().getNumerOf(offer.getItem().getDefinitions().getCertId()) 
					+ player.getInventory().getNumerOf(offer.getItem().getId());
			player.getTemporaryAttributtes().put("added_all_item", offer);
		} else if (!player.getInventory().contains(offer.getItem().getId())) {//Noted
			amount = player.getInventory().getNumerOf(offer.getItem().getDefinitions().getCertId());
		} else if (!player.getInventory().contains(offer.getItem().getDefinitions().getCertId())) {//Un-noted
			amount = player.getInventory().getNumerOf(offer.getItem().getId());
		}
		offer.setPrimaryAmount(amount);
		player.getPackets().sendConfig(1110, offer.getPrimaryAmount());
	}
	
	public void handleGuidePrice(Player player) {
		GrandExchangeOffer offer;
		offer = (GrandExchangeOffer) player.getTemporaryAttributtes().get("grand_exchange_offer");
		offer.setPrice(offer.getItem().getDefinitions().getValue());
		player.getPackets().sendConfig(1111, offer.getPrice());
	}
	
	public void handleCustomPrice() {
		GrandExchangeOffer offer;
		offer = (GrandExchangeOffer) player.getTemporaryAttributtes().get("grand_exchange_offer");
		switch (offer.getType()) {
		case BUY:
			player.getPackets().sendRunScript(108, new Object[] { "Enter the price you wish to buy for:" });
			break;
		case SELL:
			player.getPackets().sendRunScript(108, new Object[] { "Enter the price you wish to sell for:" });
			break;
		}
		player.getTemporaryAttributtes().put("setting_geoffer_price", true);
	}
	
	public void handleCustomAmount(Player player) {
		GrandExchangeOffer offer;
		player.getTemporaryAttributtes().remove("note_item");
		player.getTemporaryAttributtes().remove("added_all_item");
		offer = (GrandExchangeOffer) player.getTemporaryAttributtes().get("grand_exchange_offer");
		switch (offer.getType()) {
		case BUY:
			player.getPackets().sendRunScript(108, new Object[] { "Enter the amount you wish to purchase:" });
			break;
		case SELL:
			player.getPackets().sendRunScript(108, new Object[] { "Enter the amount you wish to sell:" });
			break;
		}
		player.getTemporaryAttributtes().put("setting_geoffer_amount", true);
	}
	
	public void handlePriceAddFivePercent() {
		GrandExchangeOffer offer;
		offer = (GrandExchangeOffer) player.getTemporaryAttributtes().get("grand_exchange_offer");
		if (offer.getPrice() + (offer.getPrice() * 0.05) < 0) {
			// Do Nothing
			return;
		}
		offer.setPrice((int) (offer.getPrice() + (offer.getPrice() * 0.05)));
		player.getPackets().sendConfig(1111, offer.getPrice());
	}
	
	public void handlePriceMinusFivePercent() {
		GrandExchangeOffer offer;
		offer = (GrandExchangeOffer) player.getTemporaryAttributtes().get("grand_exchange_offer");
		if (offer.getPrice() - (offer.getPrice() * 0.05) < 1) {
			// Do Nothing
			return;
		}
		offer.setPrice((int) (offer.getPrice() - (offer.getPrice() * 0.05)));
		player.getPackets().sendConfig(1111, offer.getPrice());
	}
	
	public void handlePriceAddOne() {
		GrandExchangeOffer offer;
		offer = (GrandExchangeOffer) player.getTemporaryAttributtes().get("grand_exchange_offer");
		if (offer.getPrice() + 1 < 0) {
			// Do Nothing
			return;
		}
		offer.setPrice(offer.getPrice() + 1);
		player.getPackets().sendConfig(1111, offer.getPrice());
	}
	
	public void handlePriceMinusOne() {
		GrandExchangeOffer offer;
		offer = (GrandExchangeOffer) player.getTemporaryAttributtes().get("grand_exchange_offer");
		if (offer.getPrice() - 1 < 1) {
			// Do Nothing
			return;
		}
		offer.setPrice(offer.getPrice() - 1);
		player.getPackets().sendConfig(1111, offer.getPrice());
	}
	
	public void handleBackButton(Player player) {
		player.getPackets().closeInterface(7);
		player.getInterfaceManager().sendTab(player.getInterfaceManager().getWindowsPane() == 746 ? 21 : 161, 752);
		player.getInterfaceManager().closeInventoryInterface();
		player.getInterfaceManager().closeInventory();
		player.getInterfaceManager().sendInventory();
		player.getInventory().unlockInventoryOptions();
		player.getInterfaceManager().openGameTab(4);
		GrandExchange.get().resetInterfaceConfigs(player);
		GrandExchange.get().display(player);
	}
	
	public void handleItemSearch(Player player) {
		GrandExchange.get().resetInterfaceConfigs(player);
		player.getPackets().sendConfig(1112,
				(Integer) player.getTemporaryAttributtes().get("grand_exchange_slot_clicked"));
		player.getPackets().sendConfig(1113, 0);
		player.getPackets().sendConfig1(1241, 16750848);
		player.getPackets().sendConfig1(1242, 15439903);
		player.getPackets().sendConfig1(741, -1);
		player.getPackets().sendConfig1(743, -1);
		player.getPackets().sendConfig1(744, 0);
		player.getPackets().sendInterface(true, 752, 7, 389);
		player.getPackets().sendRunScript(570, new Object[] { "Grand Exchange Item Search" });
	}
	
	public void handleConfirm(Player player) {
		GrandExchangeOffer offer;
		offer = (GrandExchangeOffer) player.getTemporaryAttributtes().get("grand_exchange_offer");
		int price = offer.getPrimaryAmount() * offer.getPrice();
		long price2 = (long) offer.getPrimaryAmount() * offer.getPrice();
		if (price2 > Integer.MAX_VALUE
				|| price2 < 0) {
			player.getPackets().sendGameMessage("The price of the transaction is too high.");
			return;
		}
		if (price == 0) {
			player.getPackets().sendGameMessage("Please place an amount for transaction.");
			return;
		}
		switch (offer.getType()) {
		case BUY:
			if (player.takeMoney(price)) {
				player.getTemporaryAttributtes().remove("grand_exchange_offer");
				SerializableFilesManager.savePlayer(player);
				GrandExchangeLoader.addOffer(offer);
				GrandExchange.get().display(player);
			} else {
				player.getPackets().sendGameMessage("You do not have enough coins in your inventory to cover the offer.");
			}
			break;
		case SELL:
			if (player.getTemporaryAttributtes().get("added_all_item") != null
				&& player.getTemporaryAttributtes().get("added_all_item").equals(offer)) {
				if (offer.getPrimaryAmount() > player.getInventory().getNumerOf(offer.getItem().getId())
						+ player.getInventory().getNumerOf(offer.getItem().getDefinitions().getCertId())) {
					player.getPackets()
							.sendGameMessage(
									"You do not have enough of this item in your inventory to cover the offer.");
					return;
				}
				player.getInventory().deleteItem(offer.getItem().getId(), offer.getPrimaryAmount());
				offer.setPrimaryAmount(offer.getPrimaryAmount() - player.getInventory().getNumerOf(offer.getItem().getId()));
				player.getInventory().deleteItem(offer.getItem().getDefinitions().getCertId(), offer.getPrimaryAmount());
				if (player.getTemporaryAttributtes().get("noting_item") != null
						&& player.getTemporaryAttributtes().get("noting_item").equals(offer)) {
					offer.getItem().setId(offer.getItem().getId() - 1);
				}
				player.getTemporaryAttributtes().remove("grand_exchange_offer");
				player.getTemporaryAttributtes().remove("noting_item");
				player.getTemporaryAttributtes().remove("added_all_item");
				SerializableFilesManager.savePlayer(player);
				GrandExchangeLoader.addOffer(offer);
				GrandExchange.get().display(player);
			} else {
				if (player.getInventory().containsItem(offer.getItem().getDefinitions().getCertId(), 1)) {
					offer.getItem().setId(offer.getItem().getId());
					player.getTemporaryAttributtes().put("noting_item", offer);
				}
				if (offer.getPrimaryAmount() > player.getInventory().getNumerOf(offer.getItem().getId())
						+ player.getInventory().getNumerOf(offer.getItem().getDefinitions().getCertId())) {
					player.getPackets()
							.sendGameMessage(
									"You do not have enough of this item in your inventory to cover the offer.");
					return;
				}
				if (player.getTemporaryAttributtes().get("noting_item") != null
						&& player.getTemporaryAttributtes().get("noting_item").equals(offer)) {
					offer.getItem().setId(offer.getItem().getDefinitions().getCertId());
				}
				if (player.getInventory().containsItem(offer.getItem().getId(), offer.getPrimaryAmount())) {
					player.getInventory().deleteItem(offer.getItem().getId(), offer.getPrimaryAmount());
					if (player.getTemporaryAttributtes().get("noting_item") != null
							&& player.getTemporaryAttributtes().get("noting_item").equals(offer)) {
						offer.getItem().setId(offer.getItem().getDefinitions().getCertId());
					}
					player.getTemporaryAttributtes().remove("grand_exchange_offer");
					player.getTemporaryAttributtes().remove("noting_item");
					player.getTemporaryAttributtes().remove("added_all_item");
					SerializableFilesManager.savePlayer(player);
					GrandExchangeLoader.addOffer(offer);
					GrandExchange.get().display(player);
				}
			}
			break;
		}
	}
	
	public void handleCollectionAbort(Player player) {
		GrandExchangeOffer i = GrandExchange.get().getOfferBySlot(
				player, (Integer) player.getTemporaryAttributtes().get("grand_exchange_slot_clicked"));
		if (i.isFinished()) {
			player.getPackets()
					.sendGameMessage(
							"That offer has just been completed! You are too late.");
			return;
		}
		if (i.getSecondaryAmount() > 0) {
			player.getPackets()
					.sendGameMessage(
							"Please collect your awaiting items before aborting the offer.");
			return;
		}
		i.setAborted(true);
		GrandExchange.get().display(player);
	}*/
}
