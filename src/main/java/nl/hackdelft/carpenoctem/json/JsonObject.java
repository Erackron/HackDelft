package nl.hackdelft.carpenoctem.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public abstract class JsonObject {
	private ObjectWriter objectWriter = new ObjectMapper().writer();

	public String toJSON() {
		try {
			return objectWriter.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public String toString() {
		return toJSON();
	}
}
