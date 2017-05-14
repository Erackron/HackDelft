package nl.hackdelft.carpenoctem.apiclient.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.hackdelft.carpenoctem.json.JsonObject;

import java.time.Instant;
import java.time.ZoneOffset;

public class Statistic extends JsonObject {
	public long epoch;
	@JsonProperty("article_count")
	public long articleCount;

	public String getDate() {
		return Instant.ofEpochSecond(this.epoch).atOffset(ZoneOffset.UTC).toLocalDate().toString();
	}

	@Override
	public String toJSON() {
		return "{\"epoch:\" " + this.epoch + ", \"article_count\": " + this.articleCount + "}";
	}
}
