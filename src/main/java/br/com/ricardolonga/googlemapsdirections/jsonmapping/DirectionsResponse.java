package br.com.ricardolonga.googlemapsdirections.jsonmapping;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Element root da resposta do serviço de rotas do Google.
 * 
 * @author Ricardo Longa
 */
public class DirectionsResponse {

    /**
     * Contém metadados sobre a solicitação.
     */
    @SerializedName("status")
    private Status status;

    /**
     * Contém uma matriz de trajetos da origem até o destino. Geralmente terá apenas uma rota, a não ser que seja
     * enviado o parâmetro alternatives:true.
     */
    private List<Route> routes;
    
    private String url;

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Route> getRoutes() {
        return this.routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public void setUrl(String url) {
		this.url = url;
	}
    
    public String getUrl() {
		return url;
	}
    
}
