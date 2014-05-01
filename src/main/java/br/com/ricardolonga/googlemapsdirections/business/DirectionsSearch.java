package br.com.ricardolonga.googlemapsdirections.business;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import br.com.ricardolonga.googlemapsdirections.exception.GoogleDirectionsException;
import br.com.ricardolonga.googlemapsdirections.jsonmapping.DirectionsResponse;

import com.google.gson.Gson;

/**
 * @author Ricardo Longa
 */
public class DirectionsSearch {

    @Inject
    private RequestExecutor requestExecutor;

    private URLBuilder urlBuilder;

    public DirectionsSearch create() {
        urlBuilder = new URLBuilder();
        return this;
    }

    /**
     * Add origin (required).
     * 
     * @param from
     * @return
     */
    public DirectionsSearch from(String from) {
        urlBuilder.from(from);
        return this;
    }

    public DirectionsSearch waypoint(String waypoint) {
        urlBuilder.waypoint(waypoint);
        return this;
    }

    /**
     * Example: "Florianópolis/SC" or "-27.5969039,-48.54945439999999" (lat, lon).
     * 
     * @param waypoints
     * @return
     */
    public DirectionsSearch waypoints(List<String> waypoints) {
        for (String waypoint : waypoints) {
            waypoint(waypoint);
        }

        return this;
    }

    /**
     * Add destination (required).
     * 
     * @param to
     * @return
     */
    public DirectionsSearch to(String to) {
        urlBuilder.to(to);
        return this;
    }

    public DirectionsSearch withAlternativesRoutes() {
        urlBuilder.withAlternativesRoutes();
        return this;
    }

    public DirectionsSearch withProxy(final String username, final String password) {
        Authenticator authenticator = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return (new PasswordAuthentication(username, password.toCharArray()));
            }
        };

        Authenticator.setDefault(authenticator);

        return this;
    }

    public DirectionsSearch withProxy(String host, String port, final String username, final String password) {
        System.setProperty("http.proxyHost", host);
        System.setProperty("http.proxyPort", port);

        return withProxy(username, password);
    }

    /**
     * For Google Premier client.
     * 
     * @param clientId
     * @param cryptographicKey
     * @return
     */
    public DirectionsSearch asPremierClient(String clientId, String cryptographicKey) {
        urlBuilder.clientId(clientId);
        urlBuilder.cyptographicKey(cryptographicKey);
        return this;
    }

    /**
     * Example: "pt", "en", "fr", ... (Default is "pt-BR")
     * 
     * @param locale
     */
    public DirectionsSearch withLocale(Locale locale) {
        urlBuilder.withLocale(locale);
        return this;
    }

    public DirectionsSearch sensor(boolean sensor) {
        urlBuilder.sensor(sensor);
        return this;
    }

    /**
     * Prepare and execute the URL.
     * 
     * @return
     * @throws GoogleDirectionsException
     */
    public DirectionsResponse go() throws GoogleDirectionsException {
        URL url = urlBuilder.build();

        String content = requestExecutor.execute(url);

        DirectionsResponse response = new Gson().fromJson(content, DirectionsResponse.class);
        response.setUrl(url.toString());

        return response;
    }

}
