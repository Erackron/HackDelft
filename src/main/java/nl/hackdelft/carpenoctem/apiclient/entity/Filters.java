package nl.hackdelft.carpenoctem.apiclient.entity;

import nl.hackdelft.carpenoctem.json.JsonObject;

import java.util.Optional;

public class Filters extends JsonObject {
	public Optional<String> paginate;
	public Filter[] filters;
}
