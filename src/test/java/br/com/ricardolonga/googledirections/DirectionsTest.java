package br.com.ricardolonga.googledirections;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.ricardolonga.googlemapsdirections.business.DirectionsSearch;
import br.com.ricardolonga.googlemapsdirections.exception.GoogleDirectionsException;
import br.com.ricardolonga.googlemapsdirections.jsonmapping.DirectionsResponse;
import br.com.ricardolonga.googlemapsdirections.jsonmapping.Status;

/**
 * @author Ricardo Longa
 */
@RunWith(Arquillian.class)
public class DirectionsTest {

    @Deployment
    public static JavaArchive createDeployment() {
    	return ShrinkWrap.create(JavaArchive.class)
        				 .addPackages(true, "br.com.ricardolonga.googlemapsdirections")
        				 .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    private static final String FLORIANOPOLIS = "Florianópolis/SC";
    private static final String SAO_JOSE = "São José/SC";
    private static final String PALHOCA = "Palhoça/SC";
    private static final String PAULO_LOPES = "Paulo Lopes/SC";
    private static final String GAROPABA = "Garopaba/SC";
    private static final String IMBITUBA = "Imbituba/SC";
    private static final String LAGUNA = "Laguna/SC";
    private static final String TUBARAO = "Tubarão/SC";
    private static final String ICARA = "Içara/SC";
    private static final String CRICIUMA = "Criciúma/SC";
    private static final String ARARANGUA = "Araranguá/SC";

    @Inject
    DirectionsSearch directionsSearch;

    @Test
    public void the_answer_should_be_ok() throws GoogleDirectionsException {
    	DirectionsResponse response = directionsSearch.from(FLORIANOPOLIS).to(ARARANGUA).go();
		Assert.assertTrue(response.getStatus().equals(Status.OK));
    }
    
    @Test
    public void without_from_should_be_returned_error() {
    	try {
    		directionsSearch.to(ARARANGUA).go();
    	} catch (GoogleDirectionsException e) {
    		Assert.assertEquals("The parameter FROM can't be null or empty.", e.getMessage());
    	}
    }
    
    @Test
    public void without_to_should_be_returned_error() {
    	try {
    		directionsSearch.from(FLORIANOPOLIS).go();
		} catch (GoogleDirectionsException e) {
			Assert.assertEquals("The parameter TO can't be null or empty.", e.getMessage());
		}
    }
    
    @Test
    public void with_intermediate_points_the_answer_should_be_ok() throws GoogleDirectionsException {
    	DirectionsResponse response = directionsSearch.from(FLORIANOPOLIS).waypoint(SAO_JOSE).to(ARARANGUA).go();
		Assert.assertTrue(response.getStatus().equals(Status.OK));
    }
    
    @Test
    public void with_more_than_8_points_the_answer_should_be_error() throws GoogleDirectionsException {
    	DirectionsResponse response = directionsSearch.from(FLORIANOPOLIS)
    												  .waypoint(SAO_JOSE)
    												  .waypoint(PALHOCA)
    												  .waypoint(PAULO_LOPES)
    												  .waypoint(GAROPABA)
    												  .waypoint(IMBITUBA)
    												  .waypoint(LAGUNA)
    												  .waypoint(TUBARAO)
    												  .waypoint(ICARA)
    												  .waypoint(CRICIUMA)
    												  .to(ARARANGUA)
    												  .go();
		Assert.assertTrue(response.getStatus().equals(Status.MAX_WAYPOINTS_EXCEEDED));
    }
    
    
}