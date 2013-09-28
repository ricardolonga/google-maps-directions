package br.com.ricardolonga.googlemapsdirections.business;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;

import br.com.ricardolonga.googlemapsdirections.exception.GoogleDirectionsException;
import br.com.ricardolonga.googlemapsdirections.jsonmapping.DirectionsResponse;

import com.google.gson.Gson;

/**
 * @author Ricardo Longa
 */
public class DirectionsSearch {
	
	@Inject
	private URLBuilder urlBuilder;
	
	public DirectionsSearch from(String from) {
		urlBuilder.from(from);
		return this;
	}
	
	public DirectionsSearch waypoint(String waypoint) {
		urlBuilder.waypoint(waypoint);
		return this;
	}
	
	public DirectionsSearch to(String to) {
		urlBuilder.to(to);
		return this;
	}
	
	public DirectionsSearch sensor(boolean sensor) {
		urlBuilder.sensor(sensor);
		return this;
	}
	
	public DirectionsResponse go() throws GoogleDirectionsException {
		URL url = urlBuilder.getUrl();
		
		String content = request(url);

        DirectionsResponse response = new Gson().fromJson(content, DirectionsResponse.class);
        response.setUrl(url.toString());
        
		return response;
	}

	/**
	 * Executa a URL e retorna a resposta Json.
	 * @param url
	 * @return
	 * @throws GoogleDirectionsException
	 */
	private String request(URL url) throws GoogleDirectionsException {
		String content = "";
		
		try {
        	HttpURLConnection con = (HttpURLConnection) url.openConnection();
			content = IOUtils.toString(con.getInputStream(), URLBuilder.UTF_8);
			con.disconnect();
		} catch (IOException e) {
			throw new GoogleDirectionsException(e);
		}
		
		return content;
	}

}
