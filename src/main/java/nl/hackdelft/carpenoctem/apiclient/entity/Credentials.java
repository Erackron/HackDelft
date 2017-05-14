package nl.hackdelft.carpenoctem.apiclient.entity;

import nl.hackdelft.carpenoctem.json.JsonObject;

public class Credentials extends JsonObject {
	public String email;
	public String password;

	public Credentials(String email, String password) {
		this.email = email;
		this.password = password;
	}
}
