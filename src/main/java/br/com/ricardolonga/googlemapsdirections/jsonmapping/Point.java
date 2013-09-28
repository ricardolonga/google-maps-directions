package br.com.ricardolonga.googlemapsdirections.jsonmapping;

/**
 * @author Ricardo Longa
 */
public class Point {

    private Double latitude;
    private Double longitude;

    public Number getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
