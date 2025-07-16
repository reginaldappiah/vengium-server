package com.rs.cores;

import java.util.ArrayList;
import java.util.List;

import com.rs.game.player.content.grandexchange.GrandExchangeLoader;

public class GrandExchangeThread extends Thread {

	protected GrandExchangeThread() {
		setPriority(Thread.MIN_PRIORITY);
		setName("Grand Exchange Thread");
	}

	/*@Override
	public void run() {
		while(!CoresManager.shutdown) {
			try {
				List<GrandExchangeOffer> list = GrandExchangeLoader.load();
				if (list == null)
					list = new ArrayList<GrandExchangeOffer>();
				List<GrandExchangeOffer> buyerList = getOffersByType(list, Type.BUY);
				List<GrandExchangeOffer> sellerList = getOffersByType(list, Type.SELL);
				for (GrandExchangeOffer b : buyerList) {
					int amountToBuy = b.getPrimaryAmount() - b.getSecondaryAmount();
					if (amountToBuy <= 0)
						continue;
					for (GrandExchangeOffer s : sellerList) {
						int amountToSell = s.getPrimaryAmount() - s.getSecondaryAmount();
						if (amountToSell <= 0)
							continue;
						if (b.getItem().getId() == s.getItem().getId()) {
							if (b.getPrice() >= s.getPrice()) {
								int amount = (int) ((s.getPrimaryAmount() - s.getSecondaryAmount()));
								if (amount == 0) {
									amount++;
								}
								if (amount > amountToBuy) {
									amount = amountToBuy;
								}
								int buyerPriceDifference = (b.getPrice() - s.getPrice()) * amount; // buyer is paying more than the seller wants, therefore recieves this amount as a refund
								b.setSecondaryAmount(b.getSecondaryAmount() + amount);
								s.setSecondaryAmount(s.getSecondaryAmount() + amount);
								amountToBuy -= amount;
								amountToSell -= amount;
								if (buyerPriceDifference > 0)
									b.setExtraCash(buyerPriceDifference);
								GrandExchangeLoader.removeOffer(b);
								GrandExchangeLoader.addOffer(b);
								GrandExchangeLoader.removeOffer(s);
								GrandExchangeLoader.addOffer(s);
								b.notifyUpdated();
								s.notifyUpdated();
							}
						}
					}
				}
			} catch (Exception e) { 
				e.printStackTrace();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Gets a list of offers by the type you want
	 * @param list The list of preloaded offers
	 * @param type The type you want to filter with
	 * @return
	
	private List<GrandExchangeOffer> getOffersByType(List<GrandExchangeOffer> list, Type type) {
		List<GrandExchangeOffer> offers = new ArrayList<GrandExchangeOffer>();
		for (GrandExchangeOffer offer : list) {
			if (offer.isFinished() || offer.isAborted())
				continue;
			if (offer.getType() == type)
				offers.add(offer);
		}
		return offers;
	}*/
}
