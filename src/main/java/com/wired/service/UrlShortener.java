package com.wired.service;
import com.wired.repository.ShortUrlRepository;
import com.wired.entity.ShortUrl;
import java.util.Base64;
import java.util.Optional;
import java.time.LocalDateTime;

public class UrlShortener {
	public static String getDomain(String url){
		int startIndex = 0;
		int endIndex = url.indexOf(".com");
		if(url.startsWith("http")){
			startIndex = url.indexOf("/", url.indexOf("/")+1)+1;
			if(endIndex < 0){
				endIndex = url.indexOf("/", startIndex);
			}
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

	public static String regenShortUrl(String shortUrl){
		String regenerated = Base64.getEncoder().encodeToString(shortUrl.getBytes()); 
		return regenerated.substring(regenerated.length()-shortUrl.length(), regenerated.length()-1);
	}

	public static String generateShortUrl(String originalUrl, ShortUrlRepository repo){
		String domain = Base64.getEncoder().encodeToString(getDomain(originalUrl).getBytes());
		String route = Base64.getEncoder().encodeToString(getRoute(originalUrl).getBytes());
		if(domain.length() > 5){
			domain = domain.substring(0, 4);
		}
		if(route.length() > 5){
			route = route.substring(0, 4);
		}
		String shortUrl = route + domain;
		Optional<ShortUrl> entry;
		while((entry = repo.findByShortenedUrl(shortUrl)).isPresent()){
			if(entry.get().getExpirationTime().isAfter(LocalDateTime.now())){
				shortUrl = regenShortUrl(shortUrl);
			}
		}
		return shortUrl;
	}
}
