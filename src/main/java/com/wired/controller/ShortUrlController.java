package com.wired.controller;
import com.wired.dto.CreateShortUrlRequest;
import com.wired.repository.ShortUrlRepository;
import com.wired.entity.ShortUrl;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.wired.service.UrlShortener;

@RestController
public class ShortUrlController{
	private final ShortUrlRepository repo;

	public ShortUrlController(ShortUrlRepository repo){
		this.repo = repo;
	}

	@PostMapping("/")
	public ResponseEntity<CreateShortUrlRequest> createShortUrl(@RequestBody CreateShortUrlRequest shortRequest){
		shortRequest.setExpirationTime(shortRequest.getExpirationTime().now().plusHours(5));
		ShortUrl newShortUrl = new ShortUrl();
		newShortUrl.setOriginalUrl(shortRequest.getUrl());
		newShortUrl.setShortenedUrl(UrlShortener.generateShortUrl(shortRequest.getUrl()));
		newShortUrl.setExpirationTime(shortRequest.getExpirationTime());
		repo.save(newShortUrl);
		return ResponseEntity.status(HttpStatus.CREATED).body(shortRequest);
	}
}
