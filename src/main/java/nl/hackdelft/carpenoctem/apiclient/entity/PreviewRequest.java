package nl.hackdelft.carpenoctem.apiclient.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.hackdelft.carpenoctem.json.JsonObject;

public class PreviewRequest extends JsonObject {
	@JsonProperty("slicing_lte")
	public int slicingLTE = 250;
	@JsonProperty("slicing_gte")
	public int slicingGTE = 0;
	public String oql;

	public PreviewRequest(String oql) {
		this.oql = oql;
	}
}
