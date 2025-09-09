package com.wired.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.wired.entity.UrlShortener;

public interface UrlShortenerRepository extends JpaRepository<UrlShortener, Long> {
	UrlShortener findByOriginalUrl(String originalUrl);
	UrlShortener findByShortenedUrl(String shortenedUrl);
}
