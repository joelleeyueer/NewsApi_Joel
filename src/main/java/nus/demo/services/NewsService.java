package nus.demo.services;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.stereotype.Service;


import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import nus.demo.models.News;

@Service
public class NewsService {

    public final static String URL = "https://newsapi.org/v2/everything";

	@Value("${newsapi.key}")
	private String apiKey;

    public List<News> getNews(String query) {

		List<News> newsList = new LinkedList<>();

		// Build the URL
		String url = UriComponentsBuilder.fromUriString(URL)
			.queryParam("q", query)
			.queryParam("pageSize", 5)
			//.queryParam("apiKey", apiKey) -> dont put apikey in the query string, put in the header (harder to find)
			.toUriString();

		// System.out.printf("NEVER DO THIS!!!!: NewsAPI URL: %s\n", url);

		// Make the request 
		RequestEntity<Void> req = RequestEntity.get(url)
                .header("X-api-key", apiKey)
				.accept(MediaType.APPLICATION_JSON)
				.build();

		// Make the call to OpenWeather Map 
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> resp = template.exchange(req, String.class);
		String body = resp.getBody();
		JsonReader reader = Json.createReader(new StringReader(body));
		JsonObject obj = reader.readObject();
		JsonArray arr = obj.getJsonArray("articles");


		News news = new News();
		news.setQuery(query);

		return arr.stream()
			.map(v -> v.asJsonObject())
			.map(News::create)
			.toList();

		// Parse the result to News
		// JsonReader reader = Json.createReader(new StringReader(payload));
		// JsonObject newsJson = reader.readObject();
		// JsonObject joSource = newsJson.getJsonObject("source");
        //     JsonObject joName = joSource.getJsonObject("name");
        //     JsonObject joId = joSource.getJsonObject("id");

        // newsJson = newsJson.getJsonObject("author");
        // news.setAuthor(newsJson.toString());

        // newsJson = newsJson.getJsonObject("title");
        // news.setTitle(newsJson.toString());

        // newsJson = newsJson.getJsonObject("description");
        // news.setDescription(newsJson.toString());

        // //to test
        // System.out.println(joName.toString());
        // System.out.println(joId.toString());


		// return newsList;
	}
    
}
