package br.com.ricardolonga.googlemapsdirections.business;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import br.com.ricardolonga.googlemapsdirections.business.URLParameter.ParameterName;
import br.com.ricardolonga.googlemapsdirections.exception.GoogleDirectionsException;

/**
 * Garante a construção da URL corretamente.
 * 
 * @author Ricardo Longa
 */
public class URLBuilder {

    public static final String UTF_8 = "UTF-8";

    private StringBuilder baseUrl = new StringBuilder("http://maps.googleapis.com/maps/api/directions/json");

    private URLParameter from = new URLParameter(ParameterName.FROM, true);
    private URLParameter waypoints = new URLParameter(ParameterName.WAYPOINTS, false);
    private URLParameter to = new URLParameter(ParameterName.TO, true);

    private boolean sensor = false;
    private boolean alternativesRoutes = false;

    public void from(String from) {
        this.from.setValue(from);
    }

    public void waypoint(String waypoint) {
        this.waypoints.addValue(waypoint);
    }

    public void to(String to) {
        this.to.setValue(to);
    }

    public void withAlternativesRoutes() {
        this.alternativesRoutes = true;
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
        validate();

        try {
            baseUrl.append("?origin=").append(URLEncoder.encode(from.getValue(), UTF_8));

            if (waypoints.containsValue()) {
                baseUrl.append("&waypoints=").append(URLEncoder.encode(waypoints.getValue(), UTF_8));
            }

            baseUrl.append("&destination=").append(URLEncoder.encode(to.getValue(), UTF_8));
        } catch (UnsupportedEncodingException e) {
            throw new GoogleDirectionsException(e);
        }

        baseUrl.append("&sensor=").append(sensor);
        baseUrl.append("&alternatives=").append(alternativesRoutes);
    }

    private void validate() throws GoogleDirectionsException {
        this.from.validate();
        this.waypoints.validate();
        this.to.validate();
    }

}
