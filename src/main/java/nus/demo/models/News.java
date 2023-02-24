package nus.demo.models;
import jakarta.json.JsonObject;


public class News {
    private String query;
    private String sourceId;
    private String sourceName;
    private String author;
	private String title;
	private String description;
    private String url;
    private String category;
    private String language;
    private String country;
    private String publishedAt;
    private String content;

    public String getQuery() {
        return query;
    }
    public void setQuery(String query) {
        this.query = query;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getSourceId() {
        return sourceId;
    }
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }
    public String getSourceName() {
        return sourceName;
    }
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public void setContent(String content) { this.content = content; }
	public String getContent() { return this.content; }

	public void setPublishedAt(String publishedAt) { this.publishedAt = publishedAt; }
	public String getPublishedAt() { return this.publishedAt; }

    public static News create(JsonObject jo) {
		System.out.printf("body: %s\n", jo.toString());
		News news = new News();
		news.setAuthor(jo.getString("author", "anon"));
		news.setTitle(jo.getString("title"));
        news.setDescription(jo.getString("description"));
        news.setPublishedAt(jo.getString("publishedAt"));
        news.setUrl(jo.getString("url"));


		return news;
	}

    
	
}
