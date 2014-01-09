package br.com.ricardolonga.googlemapsdirections.business;

import br.com.ricardolonga.googlemapsdirections.exception.GoogleDirectionsException;

/**
 * @author Ricardo Longa
 */
public class URLParameter {

    public enum ParameterName {
        FROM,
        WAYPOINTS,
        TO,
        CLIENT_ID,
        CRYPTOGRAPHIC_KEY,
        LANGUAGE
    }

    private ParameterName name;
    private String value = "";
    private boolean required;

    public URLParameter(ParameterName name, boolean required) {
        this.name = name;
        this.required = required;
    }

    public URLParameter(ParameterName name, boolean required, String defaultValue) {
        this.name = name;
        this.required = required;
        this.value = defaultValue;
    }

    public void setValue(String value) {
        if (value == null) {
            return;
        }

        this.value = value;
    }

    public void addValue(String value) {
        if (value == null) {
            return;
        }

        if (this.value == null || this.value.isEmpty()) {
            this.value = value;
            return;
        }

        this.value += "|" + value;
    }

    public String getValue() {
        return value;
    }

    public boolean containsValue() {
        return value != null && !value.isEmpty();
    }

    public void validate() throws GoogleDirectionsException {
        if (!required) {
            return;
        }

        if (value == null || value.isEmpty()) {
            throw new GoogleDirectionsException("The parameter " + name + " can't be null or empty.");
        }
    }

}
