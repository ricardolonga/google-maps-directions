package br.com.ricardolonga.googledirections.tests;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

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
import br.com.ricardolonga.googledirections.helpers.ProxyHelper;
import br.com.ricardolonga.googlemapsdirections.business.DirectionsSearch;
import br.com.ricardolonga.googlemapsdirections.exception.GoogleDirectionsException;
import br.com.ricardolonga.googlemapsdirections.jsonmapping.DirectionsResponse;

/**
 * @author Ricardo Longa
 */
@RunWith(Arquillian.class)
public class LegTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackages(true, "br.com.ricardolonga.googlemapsdirections").addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    DirectionsSearch directionsSearch;

//    @Before
//    public void init() {
//        Authenticator authenticator = new Authenticator() {
//            @Override
//            public PasswordAuthentication getPasswordAuthentication() {
//                return (new PasswordAuthentication(ProxyHelper.USER, ProxyHelper.PASS.toCharArray()));
//            }
//        };
//        Authenticator.setDefault(authenticator);
//
//        System.setProperty("http.proxyHost", ProxyHelper.PROXY_HOST);
//        System.setProperty("http.proxyPort", ProxyHelper.PROXY_PORT);
//    }

    // ============================= //
    // Validating route informations //
    // ============================= //

    @Test
    public void should_have_one_leg() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.CAXIAS_DO_SUL).go();
        Assert.assertEquals(1, response.getRoutes().get(0).getLegs().size());
    }

    @Test
    public void should_have_two_legs() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).waypoint(AddressesHelper.SAO_JOSE).to(AddressesHelper.CAXIAS_DO_SUL).go();
        Assert.assertEquals(2, response.getRoutes().get(0).getLegs().size());
    }

    @Test
    public void should_have_three_legs() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).waypoint(AddressesHelper.SAO_JOSE).waypoint(AddressesHelper.PALHOCA).to(AddressesHelper.CAXIAS_DO_SUL).go();
        Assert.assertEquals(3, response.getRoutes().get(0).getLegs().size());
    }

    @Test
    public void distance_should_be_436_km() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.CAXIAS_DO_SUL).go();
        Assert.assertNotNull(response.getRoutes().get(0).getLegs().get(0).getDistance().getText());
        Assert.assertEquals(Integer.valueOf(436240), response.getRoutes().get(0).getLegs().get(0).getDistance().getValue());
    }

    @Test
    public void duration_should_be_5_hours_24_minutes() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.CAXIAS_DO_SUL).go();
        Assert.assertNotNull(response.getRoutes().get(0).getLegs().get(0).getDuration().getText());
        Assert.assertEquals(Integer.valueOf(19432), response.getRoutes().get(0).getLegs().get(0).getDuration().getValue());
    }

    @Test
    public void start_address_should_not_be_null() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.CAXIAS_DO_SUL).go();
        Assert.assertNotNull(response.getRoutes().get(0).getLegs().get(0).getStartAddress());
    }

    @Test
    public void end_address_should_not_be_null() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.CAXIAS_DO_SUL).go();
        Assert.assertNotNull(response.getRoutes().get(0).getLegs().get(0).getEndAddress());
    }

    @Test
    public void start_location_should_not_be_null() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.CAXIAS_DO_SUL).go();
        Assert.assertNotNull(response.getRoutes().get(0).getLegs().get(0).getStartLocation());
    }

    @Test
    public void the_start_location_should_have_latitude() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.CAXIAS_DO_SUL).go();
        Assert.assertEquals(Double.valueOf(-27.5969039), response.getRoutes().get(0).getLegs().get(0).getStartLocation().getLatitude());
    }

    @Test
    public void the_start_location_should_have_longitude() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.CAXIAS_DO_SUL).go();
        Assert.assertEquals(Double.valueOf(-48.54945439999999), response.getRoutes().get(0).getLegs().get(0).getStartLocation().getLongitude());
    }

    @Test
    public void end_location_should_not_be_null() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.CAXIAS_DO_SUL).go();
        Assert.assertNotNull(response.getRoutes().get(0).getLegs().get(0).getEndLocation());
    }

    @Test
    public void the_end_location_should_have_latitude() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.CAXIAS_DO_SUL).go();
        Assert.assertEquals(Double.valueOf(-29.1678244), response.getRoutes().get(0).getLegs().get(0).getEndLocation().getLatitude());
    }

    @Test
    public void the_end_location_should_have_longitude() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.CAXIAS_DO_SUL).go();
        Assert.assertEquals(Double.valueOf(-51.17938299999999), response.getRoutes().get(0).getLegs().get(0).getEndLocation().getLongitude());
    }

}