package nl.hackdelft.carpenoctem.apiclient.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.hackdelft.carpenoctem.json.JsonObject;

import java.time.Duration;
import java.time.Instant;

public class PreviewStatsRequest extends JsonObject {
	@JsonProperty("published_at_lte")
	public long publishedAtLte = Instant.now().toEpochMilli() / 1000;
	@JsonProperty("published_at_gte")
	public long publishedAtGte = Instant.now().minus(Duration.ofDays(28)).toEpochMilli() / 1000;
	@JsonProperty("stats_interval")
	public long interval = 86400; // Per day
	@JsonProperty("stats_min_doc_count")
	public long minStatsCount = 0;

	public String oql;

	public PreviewStatsRequest(String oql) {
		this.oql = oql;
	}
}
