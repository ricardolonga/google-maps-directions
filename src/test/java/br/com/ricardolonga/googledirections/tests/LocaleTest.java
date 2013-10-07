package br.com.ricardolonga.googledirections.tests;

import java.util.Locale;

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
public class LocaleTest {

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
    public void in_default_locale() throws GoogleDirectionsException {
        // DirectionsResponse response =
        // directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.ARARANGUA).go();
        //
        // System.out.println("Default locale: " + response.getUrl());
        // System.out.println("Default locale: " +
        // response.getRoutes().get(0).getLegs().get(0).getSteps().get(0).getHtmlInstructions());
        // System.out.println("");
        //
        // Assert.assertEquals("Siga na direção <b>noroeste</b> na <b>R. Ten. Silveira</b> em direção à <b>R. Arcipreste Paiva</b>",
        // response.getRoutes().get(0).getLegs().get(0).getSteps().get(0).getHtmlInstructions());
    }

    // @Test
    public void in_portuguese() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.ARARANGUA).withLocale(new Locale("pt")).go();

        System.out.println("Portuguese locale (pt): " + response.getUrl());
        System.out.println("Portuguese locale (pt): " + response.getRoutes().get(0).getLegs().get(0).getSteps().get(0).getHtmlInstructions());
        System.out.println("");

        Assert.assertEquals("Siga para <b>noroeste</b> na <b>R. Ten. Silveira</b> em direção a <b>R. Arcipreste Paiva</b>", response.getRoutes().get(0).getLegs().get(0).getSteps().get(0).getHtmlInstructions());
    }

    // @Test
    public void in_italian() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.ARARANGUA).withLocale(Locale.ITALIAN).go();

        System.out.println("Italian locale (it): " + response.getUrl());
        System.out.println("Italian locale (it): " + response.getRoutes().get(0).getLegs().get(0).getSteps().get(0).getHtmlInstructions());
        System.out.println("");

        Assert.assertEquals("Procedi in direzione <b>nordovest</b> da <b>R. Ten. Silveira</b> verso <b>R. Arcipreste Paiva</b>", response.getRoutes().get(0).getLegs().get(0).getSteps().get(0).getHtmlInstructions());
    }

    // @Test
    public void in_canadian_english() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.ARARANGUA).withLocale(Locale.CANADA).go();

        System.out.println("Canadian English locale (en-ca): " + response.getUrl());
        System.out.println("Canadian English locale (en-ca): " + response.getRoutes().get(0).getLegs().get(0).getSteps().get(0).getHtmlInstructions());
        System.out.println("");

        Assert.assertEquals("Head <b>northwest</b> on <b>R. Ten. Silveira</b> toward <b>R. Arcipreste Paiva</b>", response.getRoutes().get(0).getLegs().get(0).getSteps().get(0).getHtmlInstructions());
    }

    // @Test
    public void in_canadian_french() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.ARARANGUA).withLocale(Locale.CANADA_FRENCH).go();

        System.out.println("Canadian French locale (fr-ca): " + response.getUrl());
        System.out.println("Canadian French locale (fr-ca): " + response.getRoutes().get(0).getLegs().get(0).getSteps().get(0).getHtmlInstructions());
        System.out.println("");

        Assert.assertEquals("Aller en direction <b>nord-ouest</b> sur <b>R. Ten. Silveira</b> vers <b>R. Arcipreste Paiva</b>", response.getRoutes().get(0).getLegs().get(0).getSteps().get(0).getHtmlInstructions());
    }

    // @Test
    public void in_french() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.ARARANGUA).withLocale(Locale.FRENCH).go();

        System.out.println("French locale (fr): " + response.getUrl());
        System.out.println("French locale (fr): " + response.getRoutes().get(0).getLegs().get(0).getSteps().get(0).getHtmlInstructions());
        System.out.println("");

        Assert.assertEquals("Prendre la direction <b>nord-ouest</b> sur <b>R. Ten. Silveira</b> vers <b>R. Arcipreste Paiva</b>", response.getRoutes().get(0).getLegs().get(0).getSteps().get(0).getHtmlInstructions());
    }

    // @Test
    public void in_france() throws GoogleDirectionsException {
        DirectionsResponse response = directionsSearch.create().from(AddressesHelper.FLORIANOPOLIS).to(AddressesHelper.ARARANGUA).withLocale(Locale.FRANCE).go();

        System.out.println("France locale (fr-fr): " + response.getUrl());
        System.out.println("France locale (fr-fr): " + response.getRoutes().get(0).getLegs().get(0).getSteps().get(0).getHtmlInstructions());
        System.out.println("");

        Assert.assertEquals("Prendre la direction <b>nord-ouest</b> sur <b>R. Ten. Silveira</b> vers <b>R. Arcipreste Paiva</b>", response.getRoutes().get(0).getLegs().get(0).getSteps().get(0).getHtmlInstructions());
    }

}