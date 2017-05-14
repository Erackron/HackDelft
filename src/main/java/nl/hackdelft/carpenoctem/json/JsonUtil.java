package nl.hackdelft.carpenoctem.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.io.IOException;

public final class JsonUtil {
	private static final ObjectMapper objectMapper = new ObjectMapper();
	static {
		objectMapper.registerModule(new Jdk8Module());
	}

	private JsonUtil() {
	}

	public static <T extends JsonObject> T readObjectFromJson(Class<T> type, String json) throws IOException {
		return objectMapper.readValue(json, type);
	}

	public static <T extends JsonObject> T[] readObjectArrayFromJson(Class<T[]> type, String json) throws IOException {
		return objectMapper.readValue(json, type);
	}
}
