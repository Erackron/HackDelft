package nl.hackdelft.carpenoctem.apiclient.entity;


import nl.hackdelft.carpenoctem.json.JsonObject;
import nl.hackdelft.carpenoctem.json.JsonUtil;

import java.io.IOException;

public class AuthToken extends JsonObject {
	public String token;

	public static AuthToken fromJson(String json) {
		try {
			return JsonUtil.readObjectFromJson(AuthToken.class, json);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
