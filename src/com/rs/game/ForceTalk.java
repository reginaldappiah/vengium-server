package com.rs.game;

public final class ForceTalk {

	private String text;

	public ForceTalk(String text) {
		this.text = text;
	}

	public String getText() {
		
		text = text.replaceAll("_", " ");
		return text;
	}

}
