package br.com.ricardolonga.googlemapsdirections.business;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import br.com.ricardolonga.googlemapsdirections.business.URLParameter.ParameterName;
import br.com.ricardolonga.googlemapsdirections.exception.GoogleDirectionsException;

/**
 * Garante a construção da URL corretamente.
 * 
 * @author Ricardo Longa
 */
public class URLBuilder {

    private static final String DEFAULT_LOCALE = "pt-br";

    private StringBuilder baseUrl = new StringBuilder("http://maps.googleapis.com/maps/api/directions/json");

    private URLParameter from = new URLParameter(ParameterName.FROM, true);
    private URLParameter waypoints = new URLParameter(ParameterName.WAYPOINTS, false);
    private URLParameter to = new URLParameter(ParameterName.TO, true);
    private URLParameter locale = new URLParameter(ParameterName.LANGUAGE, true, DEFAULT_LOCALE);
    private URLParameter clientId = new URLParameter(ParameterName.CLIENT_ID, false);
    private URLParameter cryptographicKey = new URLParameter(ParameterName.CRYPTOGRAPHIC_KEY, false);

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

    /**
     * Example: "pt", "en", "fr", ...
     * 
     * @param locale
     */
    public void withLocale(Locale locale) {
        if (locale == null || locale.getLanguage() == null || locale.getLanguage().isEmpty()) {
            return;
        }

        StringBuilder language = new StringBuilder(locale.getLanguage());

        if (locale.getCountry() != null && !locale.getCountry().isEmpty()) {
            language.append("-");
            language.append(locale.getCountry());
        }

        this.locale.setValue(language.toString());
    }

    public void clientId(String clientId) {
        this.clientId.setValue(clientId);
    }

    public void cyptographicKey(String cryptographicKey) {
        this.cryptographicKey.setValue(cryptographicKey);
    }

    public void sensor(boolean sensor) {
        this.sensor = sensor;
    }

    public URL build() throws GoogleDirectionsException {
        validateUrlParams();

        concatUrlParams();

        URL url = null;

        try {
            url = new URL(baseUrl.toString());

            if (asPremierClient()) {
                url = signUrl(url);
            }
        } catch (MalformedURLException | InvalidKeyException | NoSuchAlgorithmException e) {
            throw new GoogleDirectionsException("Error creating the url query.", e);
        }

        return url;
    }

    /**
     * To sign URL are necessary clientId and cryptographicKey.
     * 
     * @return
     */
    private boolean asPremierClient() {
        return cryptographicKey.containsValue() && clientId.containsValue();
    }

    /**
     * Sign the URL as (https://developers.google.com/maps/documentation/business/webservices/auth)
     * 
     * @param url
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws MalformedURLException
     */
    private URL signUrl(URL url) throws NoSuchAlgorithmException, InvalidKeyException, MalformedURLException {
        return new UrlSigner(cryptographicKey.getValue()).sign(url);
    }

    public static final String UTF_8 = "UTF-8";

    private static final String ORIGIN = "?origin=";
    private static final String WAYPOINTS = "&waypoints=";
    private static final String DESTINATION = "&destination=";
    private static final String LANGUAGE = "&language=";
    private static final String SENSOR = "&sensor=";
    private static final String ALTERNATIVES = "&alternatives=";
    private static final String CLIENT_ID = "&client=";

    private void concatUrlParams() throws GoogleDirectionsException {
        try {
            baseUrl.append(ORIGIN).append(URLEncoder.encode(from.getValue(), UTF_8));

            if (waypoints.containsValue()) {
                baseUrl.append(WAYPOINTS).append(URLEncoder.encode(waypoints.getValue(), UTF_8));
            }

            baseUrl.append(DESTINATION).append(URLEncoder.encode(to.getValue(), UTF_8));
            baseUrl.append(LANGUAGE).append(URLEncoder.encode(locale.getValue(), UTF_8));

            if (clientId.containsValue()) {
                baseUrl.append(CLIENT_ID).append(URLEncoder.encode(clientId.getValue(), UTF_8));
            }
        } catch (UnsupportedEncodingException e) {
            throw new GoogleDirectionsException(e);
        }

        baseUrl.append(SENSOR).append(sensor);
        baseUrl.append(ALTERNATIVES).append(alternativesRoutes);
    }

    private void validateUrlParams() throws GoogleDirectionsException {
        this.from.validate();
        this.waypoints.validate();
        this.to.validate();
        this.locale.validate();
        this.clientId.validate();
    }

}
