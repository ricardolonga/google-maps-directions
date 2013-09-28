package br.com.ricardolonga.googlemapsdirections.jsonmapping;

/**
 * Armazena a ordem dos pontos de referência, caso seja passado optimize:true como primeiro parâmetro de waypoints.
 */
public class WaypointOrder {

    private Integer order;

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getOrder() {
        return this.order;
    }

}
