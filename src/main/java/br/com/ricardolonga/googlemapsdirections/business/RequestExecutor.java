package br.com.ricardolonga.googlemapsdirections.business;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import br.com.ricardolonga.googlemapsdirections.exception.GoogleDirectionsException;

class RequestExecutor {

    public String execute(URL url) throws GoogleDirectionsException {
        String content = "";

        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            content = IOUtils.toString(con.getInputStream(), URLBuilder.UTF_8);
            con.disconnect();
        } catch (IOException e) {
            throw new GoogleDirectionsException(e);
        }

        return content;
    }

}
