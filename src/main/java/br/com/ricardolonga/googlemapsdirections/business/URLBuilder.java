package br.com.ricardolonga.googlemapsdirections.business;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import br.com.ricardolonga.googlemapsdirections.exception.GoogleDirectionsException;

public class URLBuilder {
	
	public static final String UTF_8 = "UTF-8";

	private StringBuilder baseUrl = new StringBuilder("http://maps.googleapis.com/maps/api/directions/json");
	
	private String from;
	private String to;
	private boolean sensor = false;

	public void from(String from) {
		this.from = from;
	}
	
	public void to(String to) {
		this.to = to;
	}
	
	public void sensor(boolean sensor) {
		this.sensor = sensor;
	}
	
	public URL getUrl() throws GoogleDirectionsException {
		createUrl();
		
		try {
            return new URL(baseUrl.toString());
        } catch (MalformedURLException e) {
            throw new GoogleDirectionsException("Error creating the url query.", e);
        }
	}

	private void createUrl() throws GoogleDirectionsException {
		try {
			baseUrl.append("?origin=").append(URLEncoder.encode(from, UTF_8));
			baseUrl.append("&destination=").append(URLEncoder.encode(to, UTF_8));
		} catch (UnsupportedEncodingException e) {
			throw new GoogleDirectionsException(e);
		}

		baseUrl.append("&sensor=").append(sensor);
	}
}
