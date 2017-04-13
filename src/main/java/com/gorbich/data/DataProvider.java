package com.gorbich.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gorbich.domain.Article;
import com.gorbich.domain.Gender;

public class DataProvider {
	private static final DataProvider instance = new DataProvider();
	private List<String> surNames = new ArrayList<>();
	private final Map<String, Gender> givenNames = new HashMap<>();
	private final Map<Integer, Article> articles = new HashMap<>();

	public static DataProvider getInstance() {
		return instance;
	}

	private DataProvider() {
		init();
	}

	public List<String> getSurNames() {
		return surNames;
	}

	public Map<String, Gender> getGivenNames() {
		return givenNames;
	}

	public Map<Integer, Article> getArticles() {
		return articles;
	}

	private void init() {
		try {
			surNames = readFile("/lastnames.csv");
			readFile("/firstfemalenames.csv").stream().forEach(n -> givenNames.put(n, Gender.Female));
			readFile("/firstmalenames.csv").stream().forEach(n -> givenNames.put(n, Gender.Male));
			int articleNo = 0;
			for (String line : readFile("/articles.csv")) {
				if (!line.trim().isEmpty()) {
					articleNo++;
					Article article = new Article(line, articleNo);
					articles.put(articleNo, article);
				}
			}
			;
		} catch (IOException ex) {
			Logger.getLogger(DataProvider.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private List<String> readFile(String fileName) throws IOException {
		List<String> lines = new ArrayList<>();
		try (InputStream is = getClass().getResourceAsStream(fileName);
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));) {
			String line;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		}
		return lines;
	}
}
