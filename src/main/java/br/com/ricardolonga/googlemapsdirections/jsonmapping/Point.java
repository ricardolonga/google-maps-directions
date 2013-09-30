package br.com.ricardolonga.googlemapsdirections.jsonmapping;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ricardo Longa
 */
public class Point {

    @SerializedName("lat")
    private Double latitude;

    @SerializedName("lng")
    private Double longitude;

    public Double getLatitude() {
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
