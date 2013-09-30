package br.com.ricardolonga.googledirections.tests;

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
import br.com.ricardolonga.googlemapsdirections.business.DirectionsSearch;
import br.com.ricardolonga.googlemapsdirections.exception.GoogleDirectionsException;
import br.com.ricardolonga.googlemapsdirections.jsonmapping.DirectionsResponse;

/**
 * @author Ricardo Longa
 */
@RunWith(Arquillian.class)
public class BoundsTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackages(true, "br.com.ricardolonga.googlemapsdirections").addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
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

    // ============================== //
    // Validating bounds informations //
    // ============================== //

    @Test
    public void the_route_must_have_bounds() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.ARARANGUA).go();
        Assert.assertNotNull(response.getRoutes().get(0).getBounds());
    }

    @Test
    public void the_northeast_bounds_should_have_latitude() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.ARARANGUA).go();
        Assert.assertEquals(Double.valueOf(-27.5846237), response.getRoutes().get(0).getBounds().getNortheast().getLatitude());
    }

    @Test
    public void the_northeast_bounds_should_have_longitude() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.ARARANGUA).go();
        Assert.assertEquals(Double.valueOf(-48.54945439999999), response.getRoutes().get(0).getBounds().getNortheast().getLongitude());
    }

    @Test
    public void the_southwest_bounds_should_have_latitude() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.ARARANGUA).go();
        Assert.assertEquals(Double.valueOf(-28.9397731), response.getRoutes().get(0).getBounds().getSouthwest().getLatitude());
    }

    @Test
    public void the_southwest_bounds_should_have_longitude() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.ARARANGUA).go();
        Assert.assertEquals(Double.valueOf(-49.4977452), response.getRoutes().get(0).getBounds().getSouthwest().getLongitude());
    }
}