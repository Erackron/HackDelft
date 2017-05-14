package nl.hackdelft.carpenoctem.apiclient.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.hackdelft.carpenoctem.json.JsonObject;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Article extends JsonObject {
	public long buzz;
	public long epoch;
	public String domain;
	public String url;
	public String title;
	public String description;
	@JsonProperty("abstract")
	public String abstractText;
	public String language;
	public String topic;
	public String id;
	@JsonProperty("translated_title")
	public String translatedTitle;
	@JsonProperty("translated_description")
	public String translatedDescription;
	@JsonProperty("translated_abstract")
	public String translatedAbstract;

	public String getDateTime() {
		return Instant.ofEpochSecond(this.epoch).atOffset(ZoneOffset.UTC).format(DateTimeFormatter.RFC_1123_DATE_TIME);
	}
}
