package br.com.ricardolonga.googlemapsdirections.jsonmapping;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Corresponde ao trecho até um ponto de referência ou ponto final.
 * 
 * @author Ricardo Longa
 */
public class Leg {

    /**
     * Informações sobre cada etapa (Step) separada do trecho (Leg).
     */
    private List<Step> steps;

    /**
     * Indica a distância total abrangida por esse trecho.
     */
    private Distance distance;

    /**
     * Indica a duração total desse trecho.
     */
    private Duration duration;

    /**
     * Contém as coordenadas de latitude/longitude da origem desse trecho.
     */
    @SerializedName("start_location")
    private Point startLocation;

    /**
     * Contém as coordenadas de latitude/longitude do destino fornecido desse trecho.
     */
    @SerializedName("end_location")
    private Point endLocation;

    /**
     * Contém o endereço legível (geralmente o endereço de uma rua) que reflete o start_location desse trecho.
     */
    @SerializedName("start_address")
    private String startAddress;

    /**
     * Contém o endereço legível (geralmente o endereço de uma rua) que reflete o end_location desse trecho.
     */
    @SerializedName("end_address")
    private String endAddress;

    private List<ViaWaypoint> viaWaypoint;

    public Distance getDistance() {
        return this.distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public Duration getDuration() {
        return this.duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getEndAddress() {
        return this.endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public Point getEndLocation() {
        return this.endLocation;
    }

    public void setEndLocation(Point endLocation) {
        this.endLocation = endLocation;
    }

    public String getStartAddress() {
        return this.startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public Point getStartLocation() {
        return this.startLocation;
    }

    public void setStartLocation(Point startLocation) {
        this.startLocation = startLocation;
    }

    public List<Step> getSteps() {
        return this.steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public List<ViaWaypoint> getViaWaypoint() {
        return this.viaWaypoint;
    }

    public void setViaWaypoint(List<ViaWaypoint> viaWaypoint) {
        this.viaWaypoint = viaWaypoint;
    }
}
