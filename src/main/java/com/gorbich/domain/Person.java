package com.gorbich.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Person {

	private String givenName;
	private String surname;
	private Gender gender;
	private int age;
	private Map<Integer, ArticleInfo> selling = new ConcurrentHashMap<>();
	private Map<Integer, ArticleInfo> buying = new ConcurrentHashMap<>();
	private int discount;

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public boolean isFemale() {
		return gender == Gender.Female;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isVendor() {
		return selling.size() > 0;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public Map<Integer, ArticleInfo> getSelling() {
		return selling;
	}

	public void setSelling(Map<Integer, ArticleInfo> selling) {
		this.selling = selling;
	}

	public Map<Integer, ArticleInfo> getBuying() {
		return buying;
	}

	public void setBuying(Map<Integer, ArticleInfo> buying) {
		this.buying = buying;
	}

}
