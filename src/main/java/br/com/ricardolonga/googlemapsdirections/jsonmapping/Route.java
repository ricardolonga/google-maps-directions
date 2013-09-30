package br.com.ricardolonga.googlemapsdirections.jsonmapping;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ricardo Longa
 */
public class Route {

    /**
     * Contém uma breve descrição textual do trajeto, adequada para nomear e diferenciar o trajeto das alternativas.
     */
    private String summary;

    /**
     * Contém uma matriz com informações sobre um trecho do trajeto, entre dois locais do trajeto fornecido. Um trecho
     * separado estará presente para cada ponto de referência ou destino especificado. Um trajeto sem pontos de
     * referência terá exatamente um trecho na matriz legs. Cada trecho consiste em uma série de steps.
     */
    private List<Leg> legs;

    /**
     * Ordem dos pontos de referência no trajeto calculado. Esses pontos de referência poderão ser reordenados se a
     * solicitação tiver transmitido optimize:true no parâmetro waypoints.
     */
    private List<WaypointOrder> waypointOrders;

    /**
     * Contém um objeto que possui uma matriz de points codificado que representa um caminho aproximado (suavizado) das
     * rotas resultantes.
     */
    @SerializedName("overview_polyline")
    private OverviewPolyline overviewPolyline;

    /**
     * Contém a caixa delimitadora da janela de visualização desse trajeto.
     */
    private Bounds bounds;

    /**
     * Contém o texto dos direitos autorais a ser exibido para esse trajeto. Você deve manipular e exibir essas
     * informações por conta própria.
     */
    private String copyrights;

    /**
     * Contém uma matriz de avisos a serem exibidos quando essas rotas forem mostradas. Você deve manipular e exibir
     * esses avisos por conta própria.
     */
    private List<Warning> warnings;

    public Bounds getBounds() {
        return this.bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public String getCopyrights() {
        return this.copyrights;
    }

    public void setCopyrights(String copyrights) {
        this.copyrights = copyrights;
    }

    public List<Leg> getLegs() {
        return this.legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    public OverviewPolyline getOverviewPolyline() {
        return this.overviewPolyline;
    }

    public void setOverviewPolyline(OverviewPolyline overviewPolyline) {
        this.overviewPolyline = overviewPolyline;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Warning> getWarnings() {
        return this.warnings;
    }

    public void setWarnings(List<Warning> warnings) {
        this.warnings = warnings;
    }

    public List<WaypointOrder> getWaypoint_order() {
        return this.waypointOrders;
    }

    public void setWaypoint_order(List<WaypointOrder> waypoint_order) {
        this.waypointOrders = waypoint_order;
    }
}
