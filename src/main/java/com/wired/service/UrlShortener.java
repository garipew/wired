package com.wired.service;
import java.util.Base64;

public class UrlShortener {
	public static String getDomain(String url){
		int startIndex = 0;
		int endIndex = url.indexOf(".com");
		if(url.startsWith("http")){
			startIndex = url.indexOf("/", url.indexOf("/")+1)+1;
		}
		if(url.contains("www.") && url.indexOf("www.") < endIndex){
			startIndex = url.indexOf(".")+1;
		}
		return url.substring(startIndex, endIndex);
	}

	public static String getRoute(String url){
		String domain = getDomain(url);
		return url.substring(url.indexOf(domain) + domain.length() + ".com/".length());
	}

	public static String generateShortUrl(String originalUrl){
		String domain = Base64.getEncoder().encodeToString(getDomain(originalUrl).getBytes());
		String route = Base64.getEncoder().encodeToString(getRoute(originalUrl).getBytes());
		System.out.println("Domain: " + getDomain(originalUrl));
		System.out.println("Route: " + getRoute(originalUrl));
		if(domain.length() > 5){
			domain = domain.substring(0, 4);
		}
		if(route.length() > 5){
			route = route.substring(0, 4);
		}
		String shortUrl = route + domain;
		return shortUrl;
	}
}
