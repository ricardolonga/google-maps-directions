package br.com.ricardolonga.googlemapsdirections.jsonmapping;

public class Distance {
	
	/**
	 * Valor expressado sempre em metros.
	 */
	private Number value;

	/**
	 * Contém uma representação legível da distância.
	 */
    private String text;

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Number getValue() {
        return this.value;
    }

    public void setValue(Number value) {
        this.value = value;
    }
}
