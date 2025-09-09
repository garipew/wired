package com.wired.controller;
import com.wired.dto.CreateUrlShortenerRequest;
import com.wired.repository.UrlShortenerRepository;
import com.wired.entity.UrlShortener;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
public class UrlShortenerController{
	private final UrlShortenerRepository repo;

	public UrlShortenerController(UrlShortenerRepository repo){
		this.repo = repo;
	}

	@PostMapping("/")
	public ResponseEntity<CreateUrlShortenerRequest> createShortUrl(@RequestBody CreateUrlShortenerRequest shortRequest){
		shortRequest.setExpirationTime(shortRequest.getExpirationTime().now().plusHours(5));
		UrlShortener newUrlShortener = new UrlShortener();
		newUrlShortener.setOriginalUrl(shortRequest.getUrl());
		newUrlShortener.setShortenedUrl("http://test@localhost:8080/");
		newUrlShortener.setExpirationTime(shortRequest.getExpirationTime());
		repo.save(newUrlShortener);
		return ResponseEntity.status(HttpStatus.CREATED).body(shortRequest);
	}
}
