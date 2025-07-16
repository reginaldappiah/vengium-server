package com.rs.game.player.customsaves;

import com.rs.game.Graphics;
import com.rs.game.item.Item;
import com.rs.game.minigames.CastleWars;
import com.rs.game.player.Player;

public class SavedOutfits {

	/**
	 * 
	 * @author Multak | Vengium | Reginald Appiah
	 */
	
	Player player;
	
	public SavedOutfits(Player player) {
		this.player = player;
	}
	/*
	 * 	Outfit 4
	 */
	
	/*Boolean male = (Boolean) player.getTemporaryAttributtes().remove(
			"MageMakeOverGender");*/
	
	public void deleteOutfit4(Player player) {
	if (player.getAppearence().isMale() && (player.removedOutfit4 == false)) {
		player.setNextGraphics(new Graphics(7));
		player.sm("<img=15><col=ff0000>You have deleted your 4th RSMV Outfit.");
		player.savedOutfit4 = false;
		player.removedOutfit4 = true;
		player.totaloutfits--;
		player.setOutfitName4("");
		player.getAppearence().resetAppearence();
		player.getAppearence().generateAppearenceData();
		if (player.getEquipment().wearingArmour()) 
			player.getBank().depositAllEquipment(true);
			player.sm("<img=15><col=ff0000>Your clothes have been banked!");
		return;
	} else if (!player.getAppearence().isMale()  && (player.removedOutfit4 == false)) {
		player.setNextGraphics(new Graphics(7));
		player.sm("<img=15><col=ff0000>You have deleted your 4th RSMV Outfit.");
		player.savedOutfit4 = false;
		player.removedOutfit4 = true;
		player.totaloutfits--;
		player.setOutfitName4("");
		player.getAppearence().female();
		player.getAppearence().generateAppearenceData();
	 	if (player.getEquipment().wearingArmour()) 
			player.getBank().depositAllEquipment(true);
			player.sm("<img=15><col=ff0000>Your clothes have been banked!");
		return;
		
	} else if ((player.removedOutfit4 == true)) {//If the player removed it before
		player.sm("<img=15><col=ff0000>You have already deleted your 4th RSMV Outfit!");
		return;
	}
	}
	public void saveOutfit4(Player player) {
	if ((player.savedOutfit4 == false) && (player.removedOutfit4 == true)) {
		player.savedOutfit4 = true;
		player.removedOutfit4 = false;
		player.totaloutfits++;
		player.setNextGraphics(new Graphics(6));
		player.sm("<col=00FF09>You have saved your 4th RSMV outfit.");
		player.armstyle4 = player.getAppearence().getArmsStyle();
		player.wristsstyle4 = player.getAppearence().getWristsStyle();
		player.topstyle4 = player.getAppearence().getTopStyle();
		player.legsstyle4 = player.getAppearence().getLegsStyle();
		player.beardstyle4 = player.getAppearence().getBeardStyle();
		player.hairstyle4 = player.getAppearence().getHairStyle();
		player.facialhair4 = player.getAppearence().getFacialHair();
		player.bootsstyle4 = player.getAppearence().getBootsStyle();
		//Colors
		player.haircolor4 = player.getAppearence().getHairColor();
		player.bootcolor4 = player.getAppearence().getBootColor();
		player.legscolor4 = player.getAppearence().getLegsColor();
		player.renderemote4 = player.getAppearence().getRenderEmote();
		player.skincolor4 = player.getAppearence().getSkinColor();
		player.topcolor4 = player.getAppearence().getTopColor();
		player.boots4 = player.getEquipment().getBootsId();
		player.amulet4 = player.getEquipment().getAmuletId();
		player.aura4 = player.getEquipment().getAuraId();
		player.cape4 = player.getEquipment().getCapeId();	
		player.chest4 = player.getEquipment().getChestId();
		player.gloves4 = player.getEquipment().getGlovesId();
		player.hat4 = player.getEquipment().getHatId();
		player.shield4 = player.getEquipment().getShieldId();
		player.legs4 = player.getEquipment().getLegsId();
		player.ring4 = player.getEquipment().getRingId();
		player.weapon4 = player.getEquipment().getWeaponId();
		player.getAppearence().generateAppearenceData();
		player.getTemporaryAttributtes().put("setoutfitname4", Boolean.TRUE);
		player.getPackets().sendInputNameScript("Enter the outfit name you wish:");
		return;
	} else if ((player.savedOutfit4 == true)) {
		player.sm("<img=15><col=ff0000>You have already saved your 4th RSMV Outfit!  If you want to override your actually saved outfit, delete it, then save again.");
		return;
	} 
	}
	@SuppressWarnings("unused")
	public void wearOutfit4(Player player) {
		//Actual Items
		if ((player.savedOutfit4 == false) && (player.removedOutfit4 == true)) {
		player.sm("<img=15><col=ff0000>Your 4th outfit was recently removed and unsaved! Please save your outfit before wearing it.");
		return;
		} else if ((player.removedOutfit4 == true)) {
		player.sm("<img=15><col=ff0000>Your 4th outfit was recently removed! Please save your outfit before wearing it.");
		return;
		} else if ((player.savedOutfit4) == false && (player.removedOutfit4 == false)) {
		player.sm("<img=15><col=ff0000>You must save before wearing your 4th RSMV outfit!");
		return;
		} else if (((player.savedOutfit4) == false)) {
		player.sm("<img=15><col=ff0000>You must save before wearing your 4th RSMV outfit!");
		return;
		} else if ((player.savedOutfit4 == true) && (player.getEquipment().wearingArmour()) || (player.removedOutfit4 == false) && (player.getEquipment().wearingArmour())) {
		player.sm("<img=15><col=ff0000>Before wearing your outfit, please unequip the items you are wielding!");
		return;
		} else if ((player.savedOutfit4 == true) || (player.removedOutfit4 == false))	
		player.setNextGraphics(new Graphics(1549));
		player.sm("<col=00FF09>You have worn your recently saved 4th RSMV outfit.");
		if (player.boots4 != -1) 
		CastleWars.setBoots(player, new Item(player.boots4, 1));
		else 
		player.sm("<col=FF0000>No boots!");
		if (player.amulet4 != -1)
		CastleWars.setAmulet(player, new Item(player.amulet4, 1));
		else 
		player.sm("<col=FF0000>No amulet!");
		if (player.aura4 != -1)
		CastleWars.setAura(player, new Item(player.aura4, 1));
		else 
		player.sm("<col=FF0000>No aura!");
		if (player.cape4 != -1)
		CastleWars.setCape(player, new Item(player.cape4, 1));
		else 
		player.sm("<col=FF0000>No cape!");
		if (player.chest4 != -1)
		CastleWars.setPlate(player, new Item(player.chest4, 1));
		else 
		player.sm("<col=FF0000>No chest item!");
		if (player.gloves4 != -1)
		CastleWars.setGloves(player, new Item(player.gloves4, 1));
		else 
		player.sm("<col=FF0000>No gloves!");
		if (player.hat4 != -1)
		CastleWars.setHood(player, new Item(player.hat4, 1));
		else 
		player.sm("<col=FF0000>No hat!");
		if (player.shield4 != -1)
		CastleWars.setShield(player, new Item(player.shield4, 1));
		else 
		player.sm("<col=FF0000>No shield!");
		if (player.legs4 != -1)
		CastleWars.setLegs(player, new Item(player.legs4, 1));
		else 
		player.sm("<col=FF0000>No legs!");
		if (player.ring4 != -1)
		CastleWars.setRing(player, new Item(player.ring4, 1));
		else 
		player.sm("<col=FF0000>No ring!");
		if (player.weapon4 != -1)
		CastleWars.setWeapon(player, new Item(player.weapon4, 1));
		else 
		player.sm("<col=FF0000>No weapon!");
		//Under Items
		player.getAppearence().setArmsStyle(player.armstyle4);
		player.getAppearence().setWristsStyle(player.wristsstyle4);
		player.getAppearence().setTopStyle(player.topstyle4);
		player.getAppearence().setLegsStyle(player.legsstyle4);
		player.getAppearence().setBeardStyle(player.beardstyle4);
		player.getAppearence().setHairStyle(player.hairstyle4);
		player.getAppearence().setFacialHair(player.facialhair4);
		player.getAppearence().setBootsStyle(player.bootsstyle4);
		//Colors
		player.getAppearence().setHairColor(player.haircolor4);
		player.getAppearence().setBootsColor(player.bootcolor4);
		player.getAppearence().setLegsColor(player.legscolor4);
		player.getAppearence().setRenderEmote(player.renderemote4);
		player.getAppearence().setSkinColor(player.skincolor4);
		player.getAppearence().setTopColor(player.topcolor4);
		player.getAppearence().generateAppearenceData();
		return;
	}
	/*
	 * 	Outfit 3
	 */
	public void deleteOutfit3(Player player) {
	if (player.getAppearence().isMale() && (player.removedOutfit3 == false)) {
		player.setNextGraphics(new Graphics(7));
		player.sm("<img=15><col=ff0000>You have deleted your 3rd RSMV Outfit.");
		player.savedOutfit3 = false;
		player.removedOutfit3 = true;
		player.totaloutfits--;
		player.setOutfitName3("");
		player.getAppearence().resetAppearence();
		player.getAppearence().generateAppearenceData();
		if (player.getEquipment().wearingArmour())
			player.getBank().depositAllEquipment(true);
			player.sm("<img=15><col=ff0000>Your clothes have been banked!");
		return;
	} else if (!player.getAppearence().isMale()  && (player.removedOutfit3 == false)) {
		player.setNextGraphics(new Graphics(7));
		player.sm("<img=15><col=ff0000>You have deleted your 3rd RSMV Outfit.");
		player.savedOutfit3 = false;
		player.removedOutfit3 = true;
		player.totaloutfits--;
		player.setOutfitName3("");
		player.getAppearence().female();
		player.getAppearence().generateAppearenceData();
	if (player.getEquipment().wearingArmour()) 
		player.getBank().depositAllEquipment(true);
		player.sm("<img=15><col=ff0000>Your clothes have been banked!");
		return;
		
	} else if ((player.removedOutfit3 == true)) {//If the player removed it before
		player.sm("<img=15><col=ff0000>You have already deleted your 3rd RSMV Outfit!");
		return;
	}
	}
	public void saveOutfit3(Player player) {
	if ((player.savedOutfit3 == false) && (player.removedOutfit3 == true)) {
		player.savedOutfit3 = true;
		player.removedOutfit3 = false;
		player.totaloutfits++;
		player.setNextGraphics(new Graphics(6));
		player.sm("<col=00FF09>You have saved your 3rd RSMV outfit.");
		player.armstyle3 = player.getAppearence().getArmsStyle();
		player.wristsstyle3 = player.getAppearence().getWristsStyle();
		player.topstyle3 = player.getAppearence().getTopStyle();
		player.legsstyle3 = player.getAppearence().getLegsStyle();
		player.beardstyle3 = player.getAppearence().getBeardStyle();
		player.hairstyle3 = player.getAppearence().getHairStyle();
		player.facialhair3 = player.getAppearence().getFacialHair();
		player.bootsstyle3 = player.getAppearence().getBootsStyle();
		//Colors
		player.haircolor3 = player.getAppearence().getHairColor();
		player.bootcolor3 = player.getAppearence().getBootColor();
		player.legscolor3 = player.getAppearence().getLegsColor();
		player.renderemote3 = player.getAppearence().getRenderEmote();
		player.skincolor3 = player.getAppearence().getSkinColor();
		player.topcolor3 = player.getAppearence().getTopColor();
		player.boots3 = player.getEquipment().getBootsId();
		player.amulet3 = player.getEquipment().getAmuletId();
		player.aura3 = player.getEquipment().getAuraId();
		player.cape3 = player.getEquipment().getCapeId();	
		player.chest3 = player.getEquipment().getChestId();
		player.gloves3 = player.getEquipment().getGlovesId();
		player.hat3 = player.getEquipment().getHatId();
		player.shield3 = player.getEquipment().getShieldId();
		player.legs3 = player.getEquipment().getLegsId();
		player.ring3 = player.getEquipment().getRingId();
		player.weapon3 = player.getEquipment().getWeaponId();
		player.getAppearence().generateAppearenceData();
		player.getTemporaryAttributtes().put("setoutfitname3", Boolean.TRUE);
		player.getPackets().sendInputNameScript("Enter the outfit name you wish:");
		return;
	} else if ((player.savedOutfit3 == true)) {
		player.sm("<img=15><col=ff0000>You have already saved your 3rd RSMV Outfit!  If you want to override your actually saved outfit, delete it, then save again.");
		return;
	} 
	}
	@SuppressWarnings("unused")
	public void wearOutfit3(Player player) {
		//Actual Items
		if ((player.savedOutfit3 == false) && (player.removedOutfit3 == true)) {
		player.sm("<img=15><col=ff0000>Your 3rd outfit was recently removed and unsaved! Please save your outfit before wearing it.");
		return;
		} else if ((player.removedOutfit3 == true)) {
		player.sm("<img=15><col=ff0000>Your 3rd outfit was recently removed! Please save your outfit before wearing it.");
		return;
		} else if ((player.savedOutfit3) == false && (player.removedOutfit3 == false)) {
		player.sm("<img=15><col=ff0000>You must save before wearing your 3rd RSMV outfit!");
		return;
		} else if (((player.savedOutfit3) == false)) {
		player.sm("<img=15><col=ff0000>You must save before wearing your 3rd RSMV outfit!");
		return;
		} else if ((player.savedOutfit3 == true) && (player.getEquipment().wearingArmour()) || (player.removedOutfit3 == false) && (player.getEquipment().wearingArmour())) {
		player.sm("<img=15><col=ff0000>Before wearing your outfit, please unequip the items you are wielding!");
		return;
		} else if ((player.savedOutfit3 == true) || (player.removedOutfit3 == false))	
		player.setNextGraphics(new Graphics(1549));
		player.sm("<col=00FF09>You have worn your recently saved 3rd RSMV outfit.");
		if (player.boots3 != -1) 
		CastleWars.setBoots(player, new Item(player.boots3, 1));
		else 
		player.sm("<col=FF0000>No boots!");
		if (player.amulet3 != -1)
		CastleWars.setAmulet(player, new Item(player.amulet3, 1));
		else 
		player.sm("<col=FF0000>No amulet!");
		if (player.aura3 != -1)
		CastleWars.setAura(player, new Item(player.aura3, 1));
		else 
		player.sm("<col=FF0000>No aura!");
		if (player.cape3 != -1)
		CastleWars.setCape(player, new Item(player.cape3, 1));
		else 
		player.sm("<col=FF0000>No cape!");
		if (player.chest3 != -1)
		CastleWars.setPlate(player, new Item(player.chest3, 1));
		else 
		player.sm("<col=FF0000>No chest item!");
		if (player.gloves3 != -1)
		CastleWars.setGloves(player, new Item(player.gloves3, 1));
		else 
		player.sm("<col=FF0000>No gloves!");
		if (player.hat3 != -1)
		CastleWars.setHood(player, new Item(player.hat3, 1));
		else 
		player.sm("<col=FF0000>No hat!");
		if (player.shield3 != -1)
		CastleWars.setShield(player, new Item(player.shield3, 1));
		else 
		player.sm("<col=FF0000>No shield!");
		if (player.legs3 != -1)
		CastleWars.setLegs(player, new Item(player.legs3, 1));
		else 
		player.sm("<col=FF0000>No legs!");
		if (player.ring3 != -1)
		CastleWars.setRing(player, new Item(player.ring3, 1));
		else 
		player.sm("<col=FF0000>No ring!");
		if (player.weapon3 != -1)
		CastleWars.setWeapon(player, new Item(player.weapon3, 1));
		else 
		player.sm("<col=FF0000>No weapon!");
		//Under Items
		player.getAppearence().setArmsStyle(player.armstyle3);
		player.getAppearence().setWristsStyle(player.wristsstyle3);
		player.getAppearence().setTopStyle(player.topstyle3);
		player.getAppearence().setLegsStyle(player.legsstyle3);
		player.getAppearence().setBeardStyle(player.beardstyle3);
		player.getAppearence().setHairStyle(player.hairstyle3);
		player.getAppearence().setFacialHair(player.facialhair3);
		player.getAppearence().setBootsStyle(player.bootsstyle3);
		//Colors
		player.getAppearence().setHairColor(player.haircolor3);
		player.getAppearence().setBootsColor(player.bootcolor3);
		player.getAppearence().setLegsColor(player.legscolor3);
		player.getAppearence().setRenderEmote(player.renderemote3);
		player.getAppearence().setSkinColor(player.skincolor3);
		player.getAppearence().setTopColor(player.topcolor3);
		player.getAppearence().generateAppearenceData();
		return;
	}
	
	/*
	 * 	Outfit 2
	 */
	public void deleteOutfit2(Player player) {
	if (player.getAppearence().isMale() && (player.removedOutfit2 == false)) {
		player.setNextGraphics(new Graphics(7));
		player.sm("<img=15><col=ff0000>You have deleted your 2nd RSMV Outfit.");
		player.savedOutfit2 = false;
		player.removedOutfit2 = true;
		player.totaloutfits--;
		player.setOutfitName2("");
		player.getAppearence().resetAppearence();
		player.getAppearence().generateAppearenceData();
		if (player.getEquipment().wearingArmour()) 
			player.getBank().depositAllEquipment(true);
			player.sm("<img=15><col=ff0000>Your clothes have been banked!");
		return;
	} else if (!player.getAppearence().isMale()  && (player.removedOutfit2 == false)) {
		player.setNextGraphics(new Graphics(7));
		player.sm("<img=15><col=ff0000>You have deleted your 2nd RSMV Outfit.");
		player.savedOutfit2 = false;
		player.removedOutfit2 = true;
		player.totaloutfits--;
		player.setOutfitName2("");
		player.getAppearence().female();
		player.getAppearence().generateAppearenceData();
		if (player.getEquipment().wearingArmour()) 
			player.getBank().depositAllEquipment(true);
			player.sm("<img=15><col=ff0000>Your clothes have been banked!");
		return;
		
	} else if ((player.removedOutfit2 == true)) {//If the player removed it before
		player.sm("<img=15><col=ff0000>You have already deleted your 2nd RSMV Outfit!");
		return;
	}
	}
	public void saveOutfit2(Player player) {
	if ((player.savedOutfit2 == false) && (player.removedOutfit2 == true)) {
		player.savedOutfit2 = true;
		player.removedOutfit2 = false;
		player.totaloutfits++;
		player.setNextGraphics(new Graphics(6));
		player.sm("<col=00FF09>You have saved your 2nd RSMV outfit.");
		player.armstyle2 = player.getAppearence().getArmsStyle();
		player.wristsstyle2 = player.getAppearence().getWristsStyle();
		player.topstyle2 = player.getAppearence().getTopStyle();
		player.legsstyle2 = player.getAppearence().getLegsStyle();
		player.beardstyle2 = player.getAppearence().getBeardStyle();
		player.hairstyle2 = player.getAppearence().getHairStyle();
		player.facialhair2 = player.getAppearence().getFacialHair();
		player.bootsstyle2 = player.getAppearence().getBootsStyle();
		//Colors
		player.haircolor2 = player.getAppearence().getHairColor();
		player.bootcolor2 = player.getAppearence().getBootColor();
		player.legscolor2 = player.getAppearence().getLegsColor();
		player.renderemote2 = player.getAppearence().getRenderEmote();
		player.skincolor2 = player.getAppearence().getSkinColor();
		player.topcolor2 = player.getAppearence().getTopColor();
		player.boots2 = player.getEquipment().getBootsId();
		player.amulet2 = player.getEquipment().getAmuletId();
		player.aura2 = player.getEquipment().getAuraId();
		player.cape2 = player.getEquipment().getCapeId();	
		player.chest2 = player.getEquipment().getChestId();
		player.gloves2 = player.getEquipment().getGlovesId();
		player.hat2 = player.getEquipment().getHatId();
		player.shield2 = player.getEquipment().getShieldId();
		player.legs2 = player.getEquipment().getLegsId();
		player.ring2 = player.getEquipment().getRingId();
		player.weapon2 = player.getEquipment().getWeaponId();
		player.getAppearence().generateAppearenceData();
		player.getTemporaryAttributtes().put("setoutfitname2", Boolean.TRUE);
		player.getPackets().sendInputNameScript("Enter the outfit name you wish:");
		return;
	} else if ((player.savedOutfit2 == true)) {
		player.sm("<img=15><col=ff0000>You have already saved your 2nd RSMV Outfit!  If you want to override your actually saved outfit, delete it, then save again.");
		return;
	} 
	}
	@SuppressWarnings("unused")
	public void wearOutfit2(Player player) {
		//Actual Items
		if ((player.savedOutfit2 == false) && (player.removedOutfit2 == true)) {
		player.sm("<img=15><col=ff0000>Your 2nd outfit was recently removed and unsaved! Please save your outfit before wearing it.");
		return;
		} else if ((player.removedOutfit2 == true)) {
		player.sm("<img=15><col=ff0000>Your 2nd outfit was recently removed! Please save your outfit before wearing it.");
		return;
		} else if ((player.savedOutfit2) == false && (player.removedOutfit2 == false)) {
		player.sm("<img=15><col=ff0000>You must save before wearing your 2nd RSMV outfit!");
		return;
		} else if (((player.savedOutfit2) == false)) {
		player.sm("<img=15><col=ff0000>You must save before wearing your 2nd RSMV outfit!");
		return;
		} else if ((player.savedOutfit2 == true) && (player.getEquipment().wearingArmour()) || (player.removedOutfit2 == false) && (player.getEquipment().wearingArmour())) {
		player.sm("<img=15><col=ff0000>Before wearing your outfit, please unequip the items you are wielding!");
		return;
		} else if ((player.savedOutfit2 == true) || (player.removedOutfit2 == false))	
		player.setNextGraphics(new Graphics(1549));
		player.sm("<col=00FF09>You have worn your recently saved 2nd RSMV outfit.");
		if (player.boots2 != -1) 
		CastleWars.setBoots(player, new Item(player.boots2, 1));
		else 
		player.sm("<col=FF0000>No boots!");
		if (player.amulet2 != -1)
		CastleWars.setAmulet(player, new Item(player.amulet2, 1));
		else 
		player.sm("<col=FF0000>No amulet!");
		if (player.aura2 != -1)
		CastleWars.setAura(player, new Item(player.aura2, 1));
		else 
		player.sm("<col=FF0000>No aura!");
		if (player.cape2 != -1)
		CastleWars.setCape(player, new Item(player.cape2, 1));
		else 
		player.sm("<col=FF0000>No cape!");
		if (player.chest2 != -1)
		CastleWars.setPlate(player, new Item(player.chest2, 1));
		else 
		player.sm("<col=FF0000>No chest item!");
		if (player.gloves2 != -1)
		CastleWars.setGloves(player, new Item(player.gloves2, 1));
		else 
		player.sm("<col=FF0000>No gloves!");
		if (player.hat2 != -1)
		CastleWars.setHood(player, new Item(player.hat2, 1));
		else 
		player.sm("<col=FF0000>No hat!");
		if (player.shield2 != -1)
		CastleWars.setShield(player, new Item(player.shield2, 1));
		else 
		player.sm("<col=FF0000>No shield!");
		if (player.legs2 != -1)
		CastleWars.setLegs(player, new Item(player.legs2, 1));
		else 
		player.sm("<col=FF0000>No legs!");
		if (player.ring2 != -1)
		CastleWars.setRing(player, new Item(player.ring2, 1));
		else 
		player.sm("<col=FF0000>No ring!");
		if (player.weapon2 != -1)
		CastleWars.setWeapon(player, new Item(player.weapon2, 1));
		else 
		player.sm("<col=FF0000>No weapon!");
		//Under Items
		player.getAppearence().setArmsStyle(player.armstyle2);
		player.getAppearence().setWristsStyle(player.wristsstyle2);
		player.getAppearence().setTopStyle(player.topstyle2);
		player.getAppearence().setLegsStyle(player.legsstyle2);
		player.getAppearence().setBeardStyle(player.beardstyle2);
		player.getAppearence().setHairStyle(player.hairstyle2);
		player.getAppearence().setFacialHair(player.facialhair2);
		player.getAppearence().setBootsStyle(player.bootsstyle2);
		//Colors
		player.getAppearence().setHairColor(player.haircolor2);
		player.getAppearence().setBootsColor(player.bootcolor2);
		player.getAppearence().setLegsColor(player.legscolor2);
		player.getAppearence().setRenderEmote(player.renderemote2);
		player.getAppearence().setSkinColor(player.skincolor2);
		player.getAppearence().setTopColor(player.topcolor2);
		player.getAppearence().generateAppearenceData();
		return;
	}
	
	/*
	 * 	Outfit 1
	 */
	public void deleteOutfit1(Player player) {
	if (player.getAppearence().isMale() && (player.removedOutfit1 == false)) {
		player.setNextGraphics(new Graphics(7));
		player.sm("<img=15><col=ff0000>You have deleted your 1st RSMV Outfit.");
		player.savedOutfit1 = false;
		player.removedOutfit1 = true;
		player.totaloutfits--;
		player.setOutfitName1("");
		player.getAppearence().resetAppearence();
		player.getAppearence().generateAppearenceData();
		if (player.getEquipment().wearingArmour()) 	
			player.getBank().depositAllEquipment(true);
			player.sm("<img=15><col=ff0000>Your clothes have been banked!");
		return;
	} else if (!player.getAppearence().isMale()  && (player.removedOutfit1 == false)) {
		player.setNextGraphics(new Graphics(7));
		player.sm("<img=15><col=ff0000>You have deleted your 1st RSMV Outfit.");
		player.savedOutfit1 = false;
		player.removedOutfit1 = true;
		player.totaloutfits--;
		player.setOutfitName1("");
		player.getAppearence().female();
		player.getAppearence().generateAppearenceData();
		if (player.getEquipment().wearingArmour())	
			player.getBank().depositAllEquipment(true);
			player.sm("<img=15><col=ff0000>Your clothes have been banked!");
		return;
		
	} else if ((player.removedOutfit1 == true)) {//If the player removed it before
		player.sm("<img=15><col=ff0000>You have already deleted your 1st RSMV Outfit!");
		return;
	}
	}
	public void saveOutfit1(Player player) {
	if ((player.savedOutfit1 == false) && (player.removedOutfit1 == true)) {
		player.savedOutfit1 = true;
		player.removedOutfit1 = false;
		player.totaloutfits++;
		player.setNextGraphics(new Graphics(6));
		player.sm("<col=00FF09>You have saved your 1st RSMV outfit.");
		player.armstyle1 = player.getAppearence().getArmsStyle();
		player.wristsstyle1 = player.getAppearence().getWristsStyle();
		player.topstyle1 = player.getAppearence().getTopStyle();
		player.legsstyle1 = player.getAppearence().getLegsStyle();
		player.beardstyle1 = player.getAppearence().getBeardStyle();
		player.hairstyle1 = player.getAppearence().getHairStyle();
		player.facialhair1 = player.getAppearence().getFacialHair();
		player.bootsstyle1 = player.getAppearence().getBootsStyle();
		//Colors
		player.haircolor1 = player.getAppearence().getHairColor();
		player.bootcolor1 = player.getAppearence().getBootColor();
		player.legscolor1 = player.getAppearence().getLegsColor();
		player.renderemote1 = player.getAppearence().getRenderEmote();
		player.skincolor1 = player.getAppearence().getSkinColor();
		player.topcolor1 = player.getAppearence().getTopColor();
		player.boots1 = player.getEquipment().getBootsId();
		player.amulet1 = player.getEquipment().getAmuletId();
		player.aura1 = player.getEquipment().getAuraId();
		player.cape1 = player.getEquipment().getCapeId();	
		player.chest1 = player.getEquipment().getChestId();
		player.gloves1 = player.getEquipment().getGlovesId();
		player.hat1 = player.getEquipment().getHatId();
		player.shield1 = player.getEquipment().getShieldId();
		player.legs1 = player.getEquipment().getLegsId();
		player.ring1 = player.getEquipment().getRingId();
		player.weapon1 = player.getEquipment().getWeaponId();
		player.getAppearence().generateAppearenceData();
		player.getTemporaryAttributtes().put("setoutfitname1", Boolean.TRUE);
		player.getPackets().sendInputNameScript("Enter the outfit name you wish:");
		return;
	} else if ((player.savedOutfit1 == true)) {
		player.sm("<img=15><col=ff0000>You have already saved your 1st RSMV Outfit!  If you want to override your actually saved outfit, delete it, then save again.");
		return;
	} 
	}
	@SuppressWarnings("unused")
	public void wearOutfit1(Player player) {
		//Actual Items
		if ((player.savedOutfit1 == false) && (player.removedOutfit1 == true)) {
		player.sm("<img=15><col=ff0000>Your 1st outfit was recently removed and unsaved! Please save your outfit before wearing it.");
		return;
		} else if ((player.removedOutfit1 == true)) {
		player.sm("<img=15><col=ff0000>Your 1st outfit was recently removed! Please save your outfit before wearing it.");
		return;
		} else if ((player.savedOutfit1) == false && (player.removedOutfit1 == false)) {
		player.sm("<img=15><col=ff0000>You must save before wearing your 1st RSMV outfit!");
		return;
		} else if (((player.savedOutfit1) == false)) {
		player.sm("<img=15><col=ff0000>You must save before wearing your 1st RSMV outfit!");
		return;
		} else if ((player.savedOutfit1 == true) && (player.getEquipment().wearingArmour()) || (player.removedOutfit1 == false) && (player.getEquipment().wearingArmour())) {
		player.sm("<img=15><col=ff0000>Before wearing your outfit, please unequip the items you are wielding!");
		return;
		} else if ((player.savedOutfit1 == true) || (player.removedOutfit1 == false))	
		player.setNextGraphics(new Graphics(1549));
		player.sm("<col=00FF09>You have worn your recently saved 1st RSMV outfit.");
		if (player.boots1 != -1) 
		CastleWars.setBoots(player, new Item(player.boots1, 1));
		else 
		player.sm("<col=FF0000>No boots!");
		if (player.amulet1 != -1)
		CastleWars.setAmulet(player, new Item(player.amulet1, 1));
		else 
		player.sm("<col=FF0000>No amulet!");
		if (player.aura1 != -1)
		CastleWars.setAura(player, new Item(player.aura1, 1));
		else 
		player.sm("<col=FF0000>No aura!");
		if (player.cape1 != -1)
		CastleWars.setCape(player, new Item(player.cape1, 1));
		else 
		player.sm("<col=FF0000>No cape!");
		if (player.chest1 != -1)
		CastleWars.setPlate(player, new Item(player.chest1, 1));
		else 
		player.sm("<col=FF0000>No chest item!");
		if (player.gloves1 != -1)
		CastleWars.setGloves(player, new Item(player.gloves1, 1));
		else 
		player.sm("<col=FF0000>No gloves!");
		if (player.hat1 != -1)
		CastleWars.setHood(player, new Item(player.hat1, 1));
		else 
		player.sm("<col=FF0000>No hat!");
		if (player.shield1 != -1)
		CastleWars.setShield(player, new Item(player.shield1, 1));
		else 
		player.sm("<col=FF0000>No shield!");
		if (player.legs1 != -1)
		CastleWars.setLegs(player, new Item(player.legs1, 1));
		else 
		player.sm("<col=FF0000>No legs!");
		if (player.ring1 != -1)
		CastleWars.setRing(player, new Item(player.ring1, 1));
		else 
		player.sm("<col=FF0000>No ring!");
		if (player.weapon1 != -1)
		CastleWars.setWeapon(player, new Item(player.weapon1, 1));
		else 
		player.sm("<col=FF0000>No weapon!");
		//Under Items
		player.getAppearence().setArmsStyle(player.armstyle1);
		player.getAppearence().setWristsStyle(player.wristsstyle1);
		player.getAppearence().setTopStyle(player.topstyle1);
		player.getAppearence().setLegsStyle(player.legsstyle1);
		player.getAppearence().setBeardStyle(player.beardstyle1);
		player.getAppearence().setHairStyle(player.hairstyle1);
		player.getAppearence().setFacialHair(player.facialhair1);
		player.getAppearence().setBootsStyle(player.bootsstyle1);
		//Colors
		player.getAppearence().setHairColor(player.haircolor1);
		player.getAppearence().setBootsColor(player.bootcolor1);
		player.getAppearence().setLegsColor(player.legscolor1);
		player.getAppearence().setRenderEmote(player.renderemote1);
		player.getAppearence().setSkinColor(player.skincolor1);
		player.getAppearence().setTopColor(player.topcolor1);
		player.getAppearence().generateAppearenceData();
		return;
	}
}
