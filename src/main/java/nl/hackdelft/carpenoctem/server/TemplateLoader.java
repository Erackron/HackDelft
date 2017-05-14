package nl.hackdelft.carpenoctem.server;

import com.mitchellbosecke.pebble.error.LoaderException;
import com.mitchellbosecke.pebble.loader.ClasspathLoader;

import java.io.*;

public class TemplateLoader extends ClasspathLoader {
	@Override
	public Reader getReader(String templateName) throws LoaderException {
		// perform the lookup
		InputStream is = TemplateLoader.class.getResourceAsStream("/templates/" + templateName);

		if (is == null) {
			throw new LoaderException(null, "Could not find template \"" + templateName + "\"");
		}

		InputStreamReader isr = new InputStreamReader(is);
		return new BufferedReader(isr);
	}
}
