package com.gorbich.conditions;

import com.gorbich.domain.Person;

public class YoungerThanCondition implements Condition<Person>{
	
	private final int age;
	
	public YoungerThanCondition(int age) {
		this.age = age;
	}
	
	public boolean test(Person person) {
		return person.getAge() < age;
	}
}
