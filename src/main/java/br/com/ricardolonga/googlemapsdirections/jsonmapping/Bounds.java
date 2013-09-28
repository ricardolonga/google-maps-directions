package br.com.ricardolonga.googlemapsdirections.jsonmapping;



/**
 * Caixa delimitadora da janela de visualização da rota.
 * 
 * @author Ricardo Longa
 */
public class Bounds {
	
	/**
	 * Latitude e longitude do limite nordeste da caixa.
	 */
    private Point northeast;
    
    /**
     * Latitude e longitude do limite sudoeste da caixa.
     */
    private Point southwest;

    public Point getNortheast() {
        return this.northeast;
    }

    public void setNortheast(Point northeast) {
        this.northeast = northeast;
    }

    public Point getSouthwest() {
        return this.southwest;
    }

    public void setSouthwest(Point southwest) {
        this.southwest = southwest;
    }
}
