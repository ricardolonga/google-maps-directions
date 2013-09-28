package br.com.ricardolonga.googlemapsdirections.business;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;

import br.com.ricardolonga.googlemapsdirections.exception.GoogleDirectionsException;
import br.com.ricardolonga.googlemapsdirections.jsonmapping.DirectionsResponse;

import com.google.gson.Gson;

public class DirectionsSearch {
	
	@Inject
	private URLBuilder urlBuilder;
	
	public DirectionsSearch from(String from) {
		urlBuilder.from(from);
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
		String content = "";
		
		try {
        	HttpURLConnection con = (HttpURLConnection) urlBuilder.getUrl().openConnection();
			content = IOUtils.toString(con.getInputStream(), URLBuilder.UTF_8);
			con.disconnect();
		} catch (IOException e) {
			throw new GoogleDirectionsException(e);
		}

        DirectionsResponse response = new Gson().fromJson(content, DirectionsResponse.class);
        
		return response;
	}

}
