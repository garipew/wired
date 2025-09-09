package com.wired.controller;
import com.wired.dto.CreateShortUrlRequest;
import com.wired.repository.ShortUrlRepository;
import com.wired.entity.ShortUrl;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import com.wired.service.UrlShortener;
import java.util.Optional;
import java.time.LocalDateTime;

@RestController
public class ShortUrlController{
	private final ShortUrlRepository repo;

	public ShortUrlController(ShortUrlRepository repo){
		this.repo = repo;
	}

	@GetMapping("/{shortUrl}")
	public ResponseEntity<String> redirect(@PathVariable String shortUrl){
		Optional<ShortUrl> entry = repo.findByShortenedUrl(shortUrl);
		HttpHeaders headers = new HttpHeaders();
		if(entry.isPresent() && entry.get().getExpirationTime().isAfter(LocalDateTime.now())){
			headers.add("Location", entry.get().getOriginalUrl());
			return ResponseEntity.status(HttpStatus.FOUND).headers(headers).body("");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
	}

	@PostMapping("/")
	public ResponseEntity<CreateShortUrlRequest> createShortUrl(@RequestBody CreateShortUrlRequest shortRequest){
		shortRequest.setExpirationTime(shortRequest.getExpirationTime().now().plusHours(5));
		ShortUrl newShortUrl = new ShortUrl();
		newShortUrl.setOriginalUrl(shortRequest.getUrl());
		newShortUrl.setShortenedUrl(UrlShortener.generateShortUrl(shortRequest.getUrl(), repo));
		newShortUrl.setExpirationTime(shortRequest.getExpirationTime());
		shortRequest.setShortUrl(newShortUrl.getShortenedUrl());
		repo.save(newShortUrl);
		return ResponseEntity.status(HttpStatus.CREATED).body(shortRequest);
	}
}
