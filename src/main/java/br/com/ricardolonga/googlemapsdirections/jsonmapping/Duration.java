package br.com.ricardolonga.googlemapsdirections.jsonmapping;

/**
 * @author Ricardo Longa
 */
public class Duration {

    /**
     * Valor expressado sempre em segundos.
     */
    private Integer value;

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

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
