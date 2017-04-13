package com.gorbich.domain;

public class ArticleInfo {

	private final int articleNo;
	private long quantity;
	private Money amount;

	public ArticleInfo(int articleNo) {
		this.articleNo = articleNo;
		amount = new Money();
	}

	public int getArticleNo() {
		return articleNo;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public Money getAmount() {
		return amount;
	}

	public void setAmount(Money amount) {
		this.amount = amount;
	}

	public void addQuantity(long quantity) {
		this.quantity += quantity;
	}

	public void addPrice(long cents) {
		amount.add(cents);
	}
}
