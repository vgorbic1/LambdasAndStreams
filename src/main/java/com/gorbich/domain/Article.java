package com.gorbich.domain;

public class Article {

	private final int articleNo;
	private String name;
	private Money price;
	private int maxSell;
	private int probability;

	public Article(String articleData, int articleNo) {
		this.articleNo = articleNo;
		try {
			String[] parts = articleData.split(";");
			name = parts[0];
			price = new Money(parts[1]);
			maxSell = Integer.parseInt(parts[2]);
			probability = Integer.parseInt(parts[3]);
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Money getPrice() {
		return price;
	}

	public void setPrice(Money price) {
		this.price = price;
	}

	public int getMaxSells() {
		return maxSell;
	}

	public void setMaxSell(int maxSell) {
		this.maxSell = maxSell;
	}

	public int getProbability() {
		return probability;
	}

	public void setProbability(int probability) {
		this.probability = probability;
	}

}
