package br.com.ricardolonga.googlemapsdirections.jsonmapping;

/**
 * @author Ricardo Longa
 */
public class Duration {

    /**
     * Valor expressado sempre em segundos.
     */
    private Number value;
    
    /**
     * Contém uma representação legível da duração.
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
