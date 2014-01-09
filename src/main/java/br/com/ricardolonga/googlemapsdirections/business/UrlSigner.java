package br.com.ricardolonga.googlemapsdirections.business;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class UrlSigner {

    private byte[] binaryKey;

    public UrlSigner(String cryptographicKey) {
        binaryKey = Base64.decodeBase64(cryptographicKey.replace('-', '+').replace('_', '/'));
    }

    public URL sign(URL url) throws NoSuchAlgorithmException, InvalidKeyException, MalformedURLException {
        String resource = url.getPath() + '?' + url.getQuery();

        SecretKeySpec sha1Key = new SecretKeySpec(binaryKey, "HmacSHA1");

        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(sha1Key);

        byte[] sigBytes = mac.doFinal(resource.getBytes());

        String signature = Base64.encodeBase64String(sigBytes);

        signature = signature.replace('+', '-');
        signature = signature.replace('/', '_');

        return new URL(url.getProtocol() + "://" + url.getHost() + resource + "&signature=" + signature);
    }

}
