package com.rs.game.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.rs.cache.loaders.ItemDefinitions;



public class Clan implements Serializable {

	public static final int RECRUIT = 0, ADMIN = 100, DEPUTY_OWNER = 125, LEADER = 126, MAX_MEMBERS = 500;

	/**
	 * 
	 */
	private static final long serialVersionUID = 4062231702422979939L;

	private String clanLeaderUsername; // index in list
	private List<ClanMember> members;
	private List<String> bannedUsers;
	private int timeZone;
	private boolean recruiting;
	private boolean isClanTime;
	private int worldId;
	private int clanFlag;
	private boolean guestsInChatCanEnter;
	private boolean guestsInChatCanTalk;
	private String threadId;
	private String motto;
	//private ClanBank clanBank;

	// motif
	private int mottifTop, mottifBottom;
	private int[] mottifColors;

	// channel
	private int minimumRankForKick;

	private transient String clanName; // its also file name so no need to save

	// infle

	public Clan(String clanName, Player leader) {
		setDefaults();
		this.members = new ArrayList<ClanMember>();
		this.bannedUsers = new ArrayList<String>();
		setClanLeaderUsername(addMember(leader, LEADER));
		init(clanName);
		//this.clanBank = new ClanBank();
	}

	public void setDefaults() {
		recruiting = true;
		guestsInChatCanEnter = true;
		guestsInChatCanTalk = true;
		worldId = 1;
		mottifColors = Arrays.copyOf(ItemDefinitions.getItemDefinitions(20709).originalModelColors, 4);
	}

	public void init(String clanName) {
		this.clanName = clanName;
		/*if (this.clanBank == null)
			this.clanBank = new ClanBank();*/
	}
	
	public ClanMember getMemberByName(String username) {
		for (ClanMember member : members) {
			if (member.getUsername().toLowerCase().equals(username.toLowerCase())) {
				return member;
			}
		}
		return null;
	}

	public ClanMember addMember(Player player, int rank) {
		ClanMember member = new ClanMember(player.getUsername(), rank);
		members.add(member);
		return member;
	}

	public void setClanLeaderUsername(ClanMember member) {
		clanLeaderUsername = member.getUsername();
	}

	public int getMemberId(ClanMember member) {
		return members.indexOf(member);
	}

	public List<ClanMember> getMembers() {
		return members;
	}

	public List<String> getBannedUsers() {
		return bannedUsers;
	}

	public String getClanName() {
		return clanName;
	}

	public int getTimeZone() {
		return timeZone;
	}

	public boolean isRecruiting() {
		return recruiting;
	}

	public void switchRecruiting() {
		recruiting = !recruiting;
	}

	public void setTimeZone(int gameTime) {
		this.timeZone = gameTime;
	}

	public int getMinimumRankForKick() {
		return minimumRankForKick;
	}

	public void setMinimumRankForKick(int minimumRankForKick) {
		this.minimumRankForKick = minimumRankForKick;
	}

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	public boolean isGuestsInChatCanEnter() {
		return guestsInChatCanEnter;
	}

	public void switchGuestsInChatCanEnter() {
		this.guestsInChatCanEnter = !guestsInChatCanEnter;
	}

	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public boolean isClanTime() {
		return isClanTime;
	}

	public void switchClanTime() {
		isClanTime = !isClanTime;
	}

	public int getWorldId() {
		return worldId;
	}

	public void setWorldId(int worldId) {
		this.worldId = worldId;
	}

	public int getClanFlag() {
		return clanFlag;
	}

	public void setClanFlag(int clanFlag) {
		this.clanFlag = clanFlag;
	}

	public String getClanLeaderUsername() {
		return clanLeaderUsername;
	}

	public boolean isGuestsInChatCanTalk() {
		return guestsInChatCanTalk;
	}

	public int getMottifTop() {
		return mottifTop;
	}

	public void setMottifTop(int mottifTop) {
		this.mottifTop = mottifTop;
	}

	public int getMottifBottom() {
		return mottifBottom;
	}

	public void setMottifBottom(int mottifBottom) {
		this.mottifBottom = mottifBottom;
	}

	public int[] getMottifColors() {
		return mottifColors;
	}

	public void setMottifColours(int[] mottifColors) {
		this.mottifColors = mottifColors;
	}

	public void switchGuestsInChatCanTalk() {
		guestsInChatCanTalk = !guestsInChatCanTalk;
	}

	/**
	 * @return the clanBank
	 */
	/*public ClanBank getClanBank() {
		return clanBank;
	}

	/**
	 * @param clanBank
	 *            the clanBank to set
	 *//*
	public void setClanBank(ClanBank clanBank) {
		this.clanBank = clanBank;
	}*/
}
