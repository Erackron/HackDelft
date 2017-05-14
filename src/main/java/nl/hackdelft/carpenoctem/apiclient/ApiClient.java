package nl.hackdelft.carpenoctem.apiclient;


import com.fasterxml.jackson.core.JsonProcessingException;
import nl.hackdelft.carpenoctem.apiclient.entity.AuthToken;
import nl.hackdelft.carpenoctem.apiclient.entity.Credentials;
import nl.hackdelft.carpenoctem.apiclient.entity.PreviewRequest;
import nl.hackdelft.carpenoctem.json.JsonObject;
import nl.hackdelft.carpenoctem.json.JsonUtil;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class ApiClient {
	private final String HOST;
	private final String API_VERSION;
	private AuthToken authToken = null;

	public ApiClient(String host,String apiVersion) throws NoSuchAlgorithmException {
		this.HOST = host;
		this.API_VERSION = apiVersion;
	}

	public String Get(String path) throws IOException {
		Request req = addAuthToken(Request.Get(constructURL(path)));
		return req.execute().returnContent().asString();
	}

	public <T extends JsonObject> T GetJson(String path, Class<T> outputClass) throws IOException {
		return JsonUtil.readObjectFromJson(outputClass, this.Get(path));
	}

	public <T extends JsonObject> T[] GetJsonArray(String path, Class<T[]> outputClass) throws IOException {
		return JsonUtil.readObjectArrayFromJson(outputClass, this.Get(path));
	}

	public String Post(String path, JsonObject data) throws IOException {
		Request req = addAuthToken(Request.Post(constructURL(path)));
		return req.bodyString(data.toJSON(), ContentType.APPLICATION_JSON)
				.execute().returnContent().asString();
	}

	public <T extends JsonObject> T PostJson(String path, JsonObject data, Class<T> outputClass) throws IOException {
		return JsonUtil.readObjectFromJson(outputClass, this.Post(path, data));
	}

	public <T extends JsonObject> T[] PostJsonArray(String path, JsonObject data, Class<T[]> outputClass) throws IOException {
		return JsonUtil.readObjectArrayFromJson(outputClass, this.Post(path, data));
	}


	public void authorize(String path, Credentials credentials) throws IOException {
		this.setAuthorization(AuthToken.fromJson(this.Post(path, credentials)));
	}

	private Request addAuthToken(Request request) {
		if (this.authToken != null) {
			return request.addHeader("Authorization", authToken.token);
		}
		return request;
	}

	private void setAuthorization(AuthToken authToken) {
		this.authToken = authToken;
	}

	private String constructURL(String path) {
		return String.format("%s/%s/%s", this.HOST, this.API_VERSION, path);
	}
}
