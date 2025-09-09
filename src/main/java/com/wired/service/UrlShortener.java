package com.wired.service;
import java.util.Base64;

public class UrlShortener {
	public static String generateShortUrl(String originalUrl){
		String shortUrl = Base64.getEncoder().encodeToString(originalUrl.getBytes());
		if(shortUrl.length() > 10){
			shortUrl = shortUrl.substring(0, 9);
		}
		return shortUrl;
	}
}
