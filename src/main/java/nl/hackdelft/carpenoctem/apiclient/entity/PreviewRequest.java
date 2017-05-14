package nl.hackdelft.carpenoctem.apiclient.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.hackdelft.carpenoctem.json.JsonObject;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;

public class PreviewRequest extends JsonObject {
	@JsonProperty("slicing_lte")
	public int slicingLTE = 250;
	@JsonProperty("slicing_gte")
	public long slicingGTE = 0;
	@JsonProperty("published_at_lte")
	public long publishedAtLte = Instant.now().toEpochMilli() / 1000;
	@JsonProperty("published_at_gte")
	public long publishedAtGte = Instant.now().minus(Duration.ofDays(28)).toEpochMilli() / 1000;

	public String oql;

	public PreviewRequest(String oql) {
		this.oql = oql;
	}
}
