package com.gorbich.domain;

public class Money {
	private long cents;

	Money() {
		cents = 0;
	}

	Money(String value) {
		setValue(value);
	}

	public long getCents() {
		return cents;
	}

	public void setCents(long cents) {
		this.cents = cents;
	}

	public String getValue() {
		return cents / 100 + "." + cents % 100;
	}

	public void setValue(String value) {
		int pos = value.indexOf(".");
		if (pos == -1) {
			cents = 100 * Long.parseLong(value);
		} else {
			cents = 100 * Long.parseLong(value.substring(0, pos));
			String decimals = value.substring(pos + 1) + "00";
			cents += Long.parseLong(decimals.substring(0, 2));
		}
	}

	public void add(long cents) {
		cents += cents;
	}

}
