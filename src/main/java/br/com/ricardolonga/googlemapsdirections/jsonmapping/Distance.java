package br.com.ricardolonga.googlemapsdirections.jsonmapping;

public class Distance {

    /**
     * Valor expressado sempre em metros.
     */
    private Integer value;

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

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
