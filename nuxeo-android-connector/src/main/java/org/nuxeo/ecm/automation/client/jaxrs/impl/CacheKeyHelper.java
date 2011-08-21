package org.nuxeo.ecm.automation.client.jaxrs.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.nuxeo.ecm.automation.client.jaxrs.spi.Request;

public class CacheKeyHelper {

	  public static String computeRequestKey(Request request) {

	        String url = request.getUrl();
	        if (url.endsWith("/login")) {
	            // no caching
	            return null;
	        }

	        if (url.endsWith("/automation/")) {
	            // automation operation definitions
	            return "automationDefinitions";
	        }

	        StringBuffer sb = new StringBuffer();
	        sb.append(request.getUrl());
	        sb.append(request.asStringEntity());

	        MessageDigest digest;
	        try {
	            digest = java.security.MessageDigest.getInstance("MD5");
	        } catch (NoSuchAlgorithmException e) {
	            return null;
	        }
	        digest.update(sb.toString().getBytes());
	        byte messageDigest[] = digest.digest();
	        StringBuffer hexString = new StringBuffer();
	        for (int i=0; i<messageDigest.length; i++)
	            hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
	        return hexString.toString();
	    }

}
