package br.com.ricardolonga.googlemapsdirections.business;

import br.com.ricardolonga.googlemapsdirections.exception.GoogleDirectionsException;

/**
 * @author Ricardo Longa
 */
public class URLParameter {

    public enum ParameterName {
        FROM,
        WAYPOINTS,
        TO
    }

    private ParameterName name;
    private String value;
    private boolean required;

    public URLParameter(ParameterName name, boolean required) {
        this.name = name;
        this.required = required;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void addValue(String waypoint) {
        if (value == null || "".equalsIgnoreCase(value)) {
            value = waypoint;
            return;
        }

        value += "|" + waypoint;
    }

    public String getValue() {
        return value;
    }

    public boolean containsValue() {
        return value != null && !"".equalsIgnoreCase(value);
    }

    public void validate() throws GoogleDirectionsException {
        if (!required) {
            return;
        }

        if (value == null || "".equalsIgnoreCase(value)) {
            throw new GoogleDirectionsException("The parameter " + name + " can't be null or empty.");
        }
    }

}
