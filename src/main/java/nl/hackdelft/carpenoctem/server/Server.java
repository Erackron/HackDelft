package nl.hackdelft.carpenoctem.server;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import fi.iki.elonen.NanoHTTPD;
import nl.hackdelft.carpenoctem.apiclient.OwlinApiConnection;
import nl.hackdelft.carpenoctem.apiclient.entity.Article;

import java.util.HashMap;
import java.util.Map;

public class Server extends NanoHTTPD {
	private final OwlinApiConnection API;
	private final PebbleTemplate homeTemplate;
	private PebbleTemplate articleTemplate;

	public static void main(String[] args) {
		try {
			new Server();
		} catch (Exception exception) {
			System.err.println("Couldn't start server:\n" + exception);
		}
	}

	public Server() throws Exception {
		super(8080);
		start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
		System.out.println("\nRunning! Point your browser to http://localhost:8080/ \n");

		// Load templates
		PebbleEngine pebbleEngine = new PebbleEngine.Builder().loader(new TemplateLoader()).build();
		homeTemplate = pebbleEngine.getTemplate("home.html");
		articleTemplate = pebbleEngine.getTemplate("article.html");

		// Preload API requests
		this.API = new OwlinApiConnection();
		this.API.loadProject();
		this.API.loadFilters();
	}

	@Override
	public Response serve(IHTTPSession session) {
		String[] path = session.getUri().replaceAll("^/", "").split("/");
		String action = (path.length > 0) ? path[0] : "home";
		switch (action) {
			case "article":
				if (path.length > 1) {
					try {
						Article article = this.API.getArticle(path[1]);
						Map<String, Object> context = new HashMap<>();
						context.put("article", article);

						return new TemplateResponse(articleTemplate, context).getResponse();
					} catch (Exception exception) {
						return newFixedLengthResponse(Response.Status.INTERNAL_ERROR, "text/html",
								"<html><head><title>500 Internal Server Error</title></head><body><h1>Internal Server Error</h1>"
										+ exception.toString() + "</html>");
					}
				}
			default:
			case "home":
				try {
					return new TemplateResponse(homeTemplate, new HashMap<>()).getResponse();
				} catch (Exception exception) {
					return newFixedLengthResponse(Response.Status.INTERNAL_ERROR, "text/html",
							"<html><head><title>500 Internal Server Error</title></head><body><h1>Internal Server Error</h1>"
									+ exception.toString() + "</html>");
				}
		}
	}
}
