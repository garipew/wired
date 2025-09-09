package com.wired.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.wired.entity.ShortUrl;
import java.util.Optional;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
	Optional<ShortUrl> findByOriginalUrl(String originalUrl);
	Optional<ShortUrl> findByShortenedUrl(String shortenedUrl);
}
