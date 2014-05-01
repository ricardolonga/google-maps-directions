package br.com.ricardolonga.googledirections.tests;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.ricardolonga.googledirections.helpers.AddressesHelper;
import br.com.ricardolonga.googledirections.helpers.PremierClientHelper;
import br.com.ricardolonga.googlemapsdirections.business.DirectionsSearch;
import br.com.ricardolonga.googlemapsdirections.exception.GoogleDirectionsException;
import br.com.ricardolonga.googlemapsdirections.jsonmapping.DirectionsResponse;
import br.com.ricardolonga.googlemapsdirections.jsonmapping.Status;

/**
 * @author Ricardo Longa
 */
@RunWith(Arquillian.class)
public class HowToUseTest {

    @Deployment
    public static JavaArchive createDeployment() {
        // @formatter:off
        return ShrinkWrap.create(JavaArchive.class)
                         .addPackages(true, "br.com.ricardolonga.googlemapsdirections")
                         .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        // @formatter:on
    }

    @Inject
    DirectionsSearch directionsSearch;

    @Before
    public void init() {
        // Authenticator authenticator = new Authenticator() {
        // @Override
        // public PasswordAuthentication getPasswordAuthentication() {
        // return (new PasswordAuthentication(ProxyHelper.USER, ProxyHelper.PASS.toCharArray()));
        // }
        // };
        // Authenticator.setDefault(authenticator);
        //
        // System.setProperty("http.proxyHost", ProxyHelper.PROXY_HOST);
        // System.setProperty("http.proxyPort", ProxyHelper.PROXY_PORT);
    }

    @Test
    public void the_answer_should_be_ok() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.ARARANGUA).go();
        Assert.assertTrue(response.getStatus().equals(Status.OK));
    }

    @Test(expected = GoogleDirectionsException.class)
    public void without_from_should_be_returned_error() throws GoogleDirectionsException {
        directionsSearch.create().to(AddressesHelper.ARARANGUA).go();
    }

    @Test(expected = GoogleDirectionsException.class)
    public void without_to_should_be_returned_error() throws GoogleDirectionsException {
        directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).go();
    }

    @Test
    public void with_intermediate_points_the_answer_should_be_ok() throws GoogleDirectionsException {
        // @formatter:off
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS)
                                                               .waypoint(AddressesHelper.SAO_JOSE)
                                                               .to(AddressesHelper.ARARANGUA).go();
        // @formatter:on

        Assert.assertTrue(response.getStatus().equals(Status.OK));
    }

    @Test
    public void with_more_than_8_points_the_answer_should_be_error() throws GoogleDirectionsException {
        // @formatter:off
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS)
                                                               .waypoint(AddressesHelper.SAO_JOSE)
                                                               .waypoint(AddressesHelper.PALHOCA)
                                                               .waypoint(AddressesHelper.PAULO_LOPES)
                                                               .waypoint(AddressesHelper.GAROPABA)
                                                               .waypoint(AddressesHelper.IMBITUBA)
                                                               .waypoint(AddressesHelper.LAGUNA)
                                                               .waypoint(AddressesHelper.TUBARAO)
                                                               .waypoint(AddressesHelper.ICARA)
                                                               .waypoint(AddressesHelper.CRICIUMA)
                                                               .to(AddressesHelper.ARARANGUA).go();
        // @formatter:on

        Assert.assertTrue(response.getStatus().equals(Status.MAX_WAYPOINTS_EXCEEDED));
    }

    @Test
    public void with_alternatives_and_without_alternatives() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.CAXIAS_DO_SUL).withAlternativesRoutes().go();

        Assert.assertEquals(3, response.getRoutes().size());

        response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.CAXIAS_DO_SUL).go();

        Assert.assertEquals(1, response.getRoutes().size());
    }

    @Test
    public void with_multi_waypoints() throws GoogleDirectionsException {
        List<String> waypoints = new ArrayList<String>();

        waypoints.add(AddressesHelper.SAO_JOSE);
        waypoints.add(AddressesHelper.PALHOCA);

        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).waypoints(waypoints).to(AddressesHelper.ARARANGUA).go();

        Assert.assertTrue(response.getStatus().equals(Status.OK));
    }

    /**
     * Throws exception because the clientId and cryptographicKey are incorrect.
     * 
     * @throws GoogleDirectionsException
     */
    @Test(expected = GoogleDirectionsException.class)
    public void as_premier_client() throws GoogleDirectionsException {
        // @formatter:off
        directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS)
                                 .asPremierClient(PremierClientHelper.CLIENT_ID, PremierClientHelper.CRYPTOGRAPHIC_KEY)
                                 .to(AddressesHelper.ARARANGUA).go();
        // @formatter:on
    }
}