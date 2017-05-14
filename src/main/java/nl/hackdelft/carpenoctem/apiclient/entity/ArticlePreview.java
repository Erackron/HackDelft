package nl.hackdelft.carpenoctem.apiclient.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.hackdelft.carpenoctem.json.JsonObject;

public class ArticlePreview extends JsonObject {
	public int ddup_count;
	public String domain;
	public String id;
	public String language;
	@JsonProperty("published_at")
	public long published;
	public String title;
	public String text;
	public String url;
}
