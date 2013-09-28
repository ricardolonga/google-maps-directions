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

@RunWith(Arquillian.class)
public class DirectionsTest {

    @Deployment
    public static JavaArchive createDeployment() {
    	return ShrinkWrap.create(JavaArchive.class)
        				 .addPackages(true, "br.com.ricardolonga.googlemapsdirections")
        				 .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    private static final String FROM = "Florianópolis/SC";
    private static final String TO = "São José/SC";

    @Inject
    DirectionsSearch directionsSearch;

    @Test
    public void the_answer_should_be_ok() throws GoogleDirectionsException {
    	DirectionsResponse response = directionsSearch.from(FROM).to(TO).go();
		Assert.assertTrue(response.getStatus().equals(Status.OK));
    }
    
}