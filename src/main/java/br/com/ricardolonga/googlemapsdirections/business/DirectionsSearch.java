package br.com.ricardolonga.googlemapsdirections.business;

import java.io.IOException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.IOUtils;

import br.com.ricardolonga.googlemapsdirections.exception.GoogleDirectionsException;
import br.com.ricardolonga.googlemapsdirections.jsonmapping.DirectionsResponse;

import com.google.gson.Gson;

/**
 * @author Ricardo Longa
 */
public class DirectionsSearch {

    private URLBuilder urlBuilder;

    public DirectionsSearch create() {
        urlBuilder = new URLBuilder();
        return this;
    }

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

    public DirectionsSearch to(String to) {
        urlBuilder.to(to);
        return this;
    }

    public DirectionsSearch withAlternativesRoutes() {
        urlBuilder.withAlternativesRoutes();
        return this;
    }

    public DirectionsSearch withProxy(String host, String port, final String username, final String password) {
        Authenticator authenticator = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return (new PasswordAuthentication(username, password.toCharArray()));
            }
        };

        Authenticator.setDefault(authenticator);

        System.setProperty("http.proxyHost", host);
        System.setProperty("http.proxyPort", port);

        return this;
    }

    public DirectionsSearch withClientId(String clientId) {
        urlBuilder.withClientId(clientId);
        return this;
    }

    /**
     * Default is "pt-BR".
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

    public DirectionsResponse go() throws GoogleDirectionsException {
        URL url = urlBuilder.getUrl();

        String content = request(url);

        DirectionsResponse response = new Gson().fromJson(content, DirectionsResponse.class);
        response.setUrl(url.toString());

        return response;
    }

    /**
     * Executa a URL e retorna a resposta Json.
     * 
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
