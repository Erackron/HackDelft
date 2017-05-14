package nl.hackdelft.carpenoctem.server;

import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import fi.iki.elonen.NanoHTTPD;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class TemplateResponse {
	private PebbleTemplate template;
	private Map<String, Object> variables;

	public TemplateResponse(PebbleTemplate template, Map<String, Object> variables) {
		this.template = template;
		this.variables = variables;
	}

	public NanoHTTPD.Response getResponse() throws IOException, PebbleException {
		Writer writer = new StringWriter();
		template.evaluate(writer, variables);
		return NanoHTTPD.newFixedLengthResponse(writer.toString());
	}
}
