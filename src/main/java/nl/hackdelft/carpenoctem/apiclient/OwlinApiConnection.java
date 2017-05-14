package nl.hackdelft.carpenoctem.apiclient;

import nl.hackdelft.carpenoctem.apiclient.entity.*;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.ArrayUtils;

public class OwlinApiConnection {
	private final String PROJECTS_URL;
	private final String FILTERS_URL;
	private final String PREVIEW_URL;

	private ApiClient apiClient;

	private Project project;
	private Filters filters;

	private void previewRequest(String oql) throws Exception {
		System.out.println(this.apiClient.PostJson(this.PREVIEW_URL, new PreviewRequest(oql), PreviewResponse.class).toJSON());
	}

	public OwlinApiConnection() throws Exception {
		Configuration clientConfig = new PropertiesConfiguration(OwlinApiConnection.class.getResource("/config.properties").getFile());
		this.PROJECTS_URL = clientConfig.getString("api.projectsurl");
		this.FILTERS_URL = clientConfig.getString("api.filtersurl");
		this.PREVIEW_URL = clientConfig.getString("api.previewurl");

		String host = clientConfig.getString("api.host");
		String apiVersion = clientConfig.getString("api.version");
		String tokensUrl = clientConfig.getString("api.tokensurl");
		Credentials credentials = new Credentials(
				clientConfig.getString("credentials.email"),
				clientConfig.getString("credentials.password")
		);

		// Setup HTTP client
		this.apiClient = new ApiClient(host, apiVersion);
		// Authorize HTTP client
		this.apiClient.authorize(tokensUrl, credentials);
	}

	public void loadProject() throws Exception {
		this.project = this.apiClient.GetJsonArray(this.PROJECTS_URL, Project[].class)[0];
	}

	public void loadFilters() throws Exception {
		if (this.project == null)
			return;

		this.filters = this.apiClient.GetJson(
				String.format("%s/%s/%s", this.PROJECTS_URL, this.project.projectId, this.FILTERS_URL),
				Filters.class
		);
		while (this.filters.paginate.isPresent()) {
			Filters filters = this.apiClient.GetJson(this.filters.paginate.get(), Filters.class);
			this.filters.paginate = filters.paginate;
			this.filters.filters = (Filter[]) ArrayUtils.add(this.filters.filters, filters.filters);
		}
	}
}
