package com.gorbich.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.gorbich.conditions.Condition;
import com.gorbich.conditions.YoungerThanCondition;
import com.gorbich.domain.ArticleInfo;
import com.gorbich.domain.Person;
import com.gorbich.domain.Persons;

public class StreamsDemo {

	public static void main(String[] args) {

		List<Person> result;
		StreamsDemo demo = new StreamsDemo();

		System.out.println("started...");
		Persons persons = Persons.getInstance();
		System.out.println("created " + persons.getPersons().size() + " persons.\n");

		
		/** ==== Parameterizing (old way) ==== **/

		// result = demo.getPersonsLessThan20Years(persons.getPersons());

		// result = demo.getPersonsByAgeRange(persons.getPersons(), 30, 40);

		// result = demo.getPersonsByCondition(persons.getPersons(), new YoungerThanCondition(20));

		
		/** ==== Lambdas (same method is used) ==== **/

		// result = demo.getPersonsByConditionLambda(persons.getPersons(), person -> person.getAge() < 20);

		//result = demo.getPersonsByConditionLambda(persons.getPersons(), p -> p.isVendor());

		
		/** ==== Streams (look implementation in corresponding methods) ==== **/

		 result = demo.getYoungFemales(persons.getPersons());

		// System.out.println("Average Age is: " + demo.showAverageAge(persons.getPersons()) + "\n");
		
		// System.out.println("Total Count of Selling is: " + demo.showTotalSelling(persons.getPersons()) + "\n");

		// System.out.println("Alternative Total Count of Selling is: " + demo.showTotalSellingAlt(persons.getPersons()) + "\n");

		// System.out.println("Average age of females is: " + demo.showAverageAgeFemaleDynamic(persons.getPersons()) + "\n");

		 
		/** ==== Display the Result ==== **/

		demo.showResult(result); // uncomment at least one "result"

	}

	/**
	 * Create a simple filter within a loop
	 */
	private List<Person> getPersonsLessThan20Years(List<Person> persons) {
		List<Person> result = new ArrayList<>();
		for (Person person : persons) {
			if (person.getAge() < 20) {
				result.add(person);
			}
		}
		return result;
	}

	/**
	 * Create a restrictive filter within the loop.
	 */
	private List<Person> getPersonsByAgeRange(List<Person> persons, int from, int to) {
		List<Person> result = new ArrayList<>();
		for (Person person : persons) {
			if (person.getAge() >= from && person.getAge() <= to) {
				result.add(person);
			}
		}
		return result;
	}

	/**
	 * Create a Condition interface and use filters as corresponding objects.
	 * Pass the interface as an argument in the calling method.
	 */
	private List<Person> getPersonsByCondition(List<Person> persons, Condition<Person> condition) {
		List<Person> result = new ArrayList<>();
		for (Person person : persons) {
			if (condition.test(person)) {
				result.add(person);
			}
		}
		return result;
	}

	/**
	 * This rime a Lambda expressions are used as an argument in the calling method
	 * instead of an interface and filter objects.
	 * The body of the method is the same as previous.
	 */
	private List<Person> getPersonsByConditionLambda(List<Person> persons, Condition<Person> condition) {
		List<Person> result = new ArrayList<>();
		for (Person person : persons) {
			if (condition.test(person)) {
				result.add(person);
			}
		}
		return result;
	}

	/**
	 * The code person.stream() emits all data as a stream using an internal
	 * iterator. Lines 2 and 3 act as two chained filters; and in the last line,
	 * the resulting data is collected into a list.
	 */
	private List<Person> getYoungFemales(List<Person> persons) {
		List<Person> result = persons.stream().filter(p -> p.getAge() < 20).filter(p -> p.isFemale())
				.collect(Collectors.toList());
		return result;
	}

	/**
	 * Mapping takes an object of the stream (Person) and yields a different
	 * one. The stream will continue with the person’s age. Next, calculate the
	 * average of the age. At the end of the process chain, get the result from
	 * type Double and assign it to the variable on the left side (averageAge).
	 */
	private double showAverageAge(List<Person> persons) {
		double averageAge = persons.stream().filter(p -> p.getAge() < 20 && p.isFemale()).mapToInt(Person::getAge)
				.average().getAsDouble();
		return averageAge;
	}

	/**
	 * With a traditional approach two loops will be implemented: one loop
	 * to iterate the persons and the inner one to iterate through the
	 * selling. By streams, it is realized with two loops also, but these
	 * loops are hidden to the developer. The code reads like a serial
	 * process: take all persons, determine the vendors, and for each
	 * calculate the amount and then the final sum. Could you have made it
	 * shorter without lambdas and streams?
	 */
	private long showTotalSelling(List<Person> persons) {
		long totalSelling = persons.stream().filter(Person::isVendor)
				.mapToLong(p -> p.getSelling().values().stream().mapToLong(ArticleInfo::getQuantity).sum()).sum();
	    return totalSelling;
	}

	/**
	 * If you don’t like the duplicate call of the sum function, realize the
	 * calculation by flatten the “stream of streams.” For this use a
	 * flatMapToLong.
	 */
	private long showTotalSellingAlt(List<Person> persons) {
		long totalSelling = persons.stream().filter(Person::isVendor)
				.flatMapToLong(p -> p.getSelling().values().stream().mapToLong(ArticleInfo::getQuantity)).sum();
		return totalSelling;
	}

	/**
	 * Dynamically Build Processing Chain
	 */
	private double showAverageAgeFemaleDynamic(List<Person> persons) {
		Stream<Person> stream = persons.stream();
		boolean female = true; // this may be a separate method that returns a boolean
		boolean youngAge = true; // this may be a separate method that returns a boolean
		if (female) {
			stream = stream.filter(Person::isFemale);
		}
		if (youngAge) {
			stream = stream.filter(p -> p.getAge() < 20);
		}
		double average = stream.mapToInt(p -> p.getAge()).average().getAsDouble();
		return average;
	}
	
	/**
	 * Format the result
	 */
	private void showResult(List<Person> result) {
		System.out.println("ResultSet: \n--------");
		for (Person person : result) {
			System.out.println(person.getGivenName() + " " + person.getSurname() + " " + person.getAge());
		}
	}
}
