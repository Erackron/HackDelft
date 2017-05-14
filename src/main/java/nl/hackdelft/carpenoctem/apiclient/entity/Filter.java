package nl.hackdelft.carpenoctem.apiclient.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.hackdelft.carpenoctem.json.JsonObject;

public class Filter extends JsonObject {
	@JsonProperty("filter_id")
	public String filterId;
	public String[] labels;
	@JsonProperty("query_id")
	public String queryId;
	public String status;
	public String title;
	@JsonProperty("updated_at")
	public double updated;
}
