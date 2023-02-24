package nus.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import nus.demo.models.News;
import nus.demo.services.NewsService;

@SpringBootApplication
public class NewsApiWebsiteApplication implements CommandLineRunner {

	@Autowired
	private NewsService newsService;
	public static void main(String[] args) {
		SpringApplication.run(NewsApiWebsiteApplication.class, args);
	}

	@Override
	public void run(String... args){
		List<News> articles = newsService.getNews("cities+skylines");
		for (News n: articles) {
			System.out.printf("Title: %s\n", n.getTitle());
			System.out.printf("Author: %s\n", n.getAuthor());
			System.out.printf("Published At: %s\n", n.getPublishedAt());
			System.out.printf("Content At: %s\n", n.getContent());
			System.out.printf("URL At: %s\n", n.getUrl());

			System.out.println("---------------------------");
		}
		System.out.printf(">total articles: %d\n", articles.size());
	
	}

}
