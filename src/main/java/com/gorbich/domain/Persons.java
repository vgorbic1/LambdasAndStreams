package com.gorbich.domain;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import com.gorbich.data.DataProvider;

public class Persons {

	private final static int PersonCount = 500;
	private final Random random = new SecureRandom();
	private final static Persons instance = new Persons();
	private final List<Person> persons = new ArrayList<>();
	private final List<Person> sellers = new ArrayList<>();

	public List<Person> getPersons() {
		return persons;
	}

	private Persons() {
		for (int i = 0; i < PersonCount; i++) {
			Person person = createPerson();
			persons.add(person);
			if (person.isVendor()) {
				sellers.add(person);
			}
		}
		long maxSells = PersonCount * (50 + random.nextInt(50));

		for (int i = 0; i <= maxSells; i++) {
			trySell();
		}
	}

	public static Persons getInstance() {
		return instance;
	}

	private Person createPerson() {
		Person person = new Person();
		List<String> surNames = DataProvider.getInstance().getSurNames();
		person.setSurname(surNames.get(random.nextInt(surNames.size())));
		Map<String, Gender> givenNameInfos = DataProvider.getInstance().getGivenNames();
		List<String> givenNames = givenNameInfos.keySet().stream().collect(Collectors.toList());
		person.setGivenName(givenNames.get(random.nextInt(givenNames.size())));
		person.setGender(givenNameInfos.get(person.getGivenName()));
		person.setAge(15 + random.nextInt(80));
		if (random.nextInt(100) == 0) {
			makeVendor(person);
		}
		return person;
	}

	private void makeVendor(Person person) {
		person.setDiscount(random.nextInt(5) * 5);
		Map<Integer, ArticleInfo> selling = person.getSelling();

		for (int i = 0; i <= random.nextInt(10); i++) {
			int articleNo = 1 + random.nextInt(DataProvider.getInstance().getArticles().size());
			if (selling.containsKey(articleNo)) {
				break;
			}
			selling.put(articleNo, new ArticleInfo(articleNo));
		}
	}

	private void trySell() {
		Person seller = sellers.get(random.nextInt(sellers.size()));
		assert (seller != null);
		Person buyer = persons.get(random.nextInt(persons.size()));
		assert (buyer != null);
		if (seller == buyer) {
			return;
		}
		Map<Integer, ArticleInfo> selling = seller.getSelling();
		Map<Integer, ArticleInfo> buying = buyer.getBuying();
		Object[] articleNumbers = selling.keySet().toArray();
		int index = random.nextInt(articleNumbers.length);
		int articleNo = (int) articleNumbers[index];
		Article article = DataProvider.getInstance().getArticles().get(articleNo);

		if (random.nextInt(1000) < article.getProbability() + seller.getDiscount() / 5) {
			int quantity = 1 + random.nextInt(article.getMaxSells());
			long price = quantity * article.getPrice().getCents() * (100 - seller.getDiscount()) / 100;
			ArticleInfo infoSelling = selling.get(articleNo);
			infoSelling.setQuantity(infoSelling.getQuantity() + quantity);
			infoSelling.getAmount().add(price);
			ArticleInfo infoBuying = buying.containsKey(articleNo) ? buying.get(articleNo) : new ArticleInfo(articleNo);
			infoBuying.addQuantity(quantity);
			infoBuying.addPrice(price);
			buying.put(articleNo, infoBuying);
		}
	}

}
