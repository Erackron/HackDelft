package nl.hackdelft.carpenoctem.apiclient.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.hackdelft.carpenoctem.json.JsonObject;

public class Project extends JsonObject {
	@JsonProperty("project_id")
	public String projectId;
	public String title;
	public String color;
	public String[] groups;
	public Object meta;
	@JsonProperty("updated_at")
	public double updated;
}
