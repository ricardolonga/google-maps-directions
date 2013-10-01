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
public class RouteTest {

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

    // ============================= //
    // Validating route informations //
    // ============================= //

    @Test
    public void should_have_one_route() throws GoogleDirectionsException {
        // DirectionsResponse response =
        // directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.ARARANGUA).go();
        // Assert.assertEquals(1, response.getRoutes().size());
    }

    // @Test
    public void should_have_more_than_one_route() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.CAXIAS_DO_SUL).withAlternativesRoutes().go();
        Assert.assertTrue(response.getRoutes().size() > 1);
    }

    // @Test
    public void should_have_copyrights() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.CAXIAS_DO_SUL).go();
        Assert.assertTrue(response.getRoutes().get(0).getCopyrights() != null && !"".equalsIgnoreCase(response.getRoutes().get(0).getCopyrights()));
    }

    // @Test
    public void should_have_summary() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.CAXIAS_DO_SUL).go();
        Assert.assertTrue(response.getRoutes().get(0).getSummary() != null && !"".equalsIgnoreCase(response.getRoutes().get(0).getSummary()));
    }

}