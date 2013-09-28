package br.com.ricardolonga.googlemapsdirections.jsonmapping;


/**
 * Uma etapa é a unidade mais atômica do trajeto de uma rota, contendo uma única etapa que descreve uma única 
 * instrução específica do caminho. Por exemplo, "Virar à esquerda em W. 4th St". 
 * 
 * @author Ricardo Longa
 */
public class Step {

	/**
	 * Contém instruções formatadas.
	 */
	private String htmlInstructions;

	/**
	 * Contém a distância abrangida por essa etapa até a próxima etapa.
	 */
	private Distance distance;
	
	/**
	 * Contém o tempo típico necessário para realizar a etapa.
	 */
    private Duration duration;

    /**
     * Contém o local do ponto inicial dessa etapa.
     */
    private Point startLocation;
    
    /**
     * Contém o local do ponto final dessa etapa.
     */
    private Point endLocation;

    private Polyline polyline;

    /**
     * Driving (default), Walking, Bicycling, Transit (transporte público).
     */
    private TravelMode travelMode;

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

    public Point getEndLocation() {
        return this.endLocation;
    }

    public void setEndLocation(Point endLocation) {
        this.endLocation = endLocation;
    }

    public String getHtmlInstructions() {
        return this.htmlInstructions;
    }

    public void setHtmlInstructions(String htmlInstructions) {
        this.htmlInstructions = htmlInstructions;
    }

    public Polyline getPolyline() {
        return this.polyline;
    }

    public void setPolyline(Polyline polyline) {
        this.polyline = polyline;
    }

    public Point getStartLocation() {
        return this.startLocation;
    }

    public void setStartLocation(Point startLocation) {
        this.startLocation = startLocation;
    }

    public TravelMode getTravelMode() {
        return this.travelMode;
    }

    public void setTravelMode(TravelMode travelMode) {
        this.travelMode = travelMode;
    }
}
