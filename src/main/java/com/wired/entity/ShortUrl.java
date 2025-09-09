package com.wired.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ShortUrl {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String originalUrl;
	private String shortenedUrl;
	private LocalDateTime expirationTime;
	public void setOriginalUrl(String originalUrl){
		this.originalUrl = originalUrl;
	}
	public void setShortenedUrl(String shortenedUrl){
		this.shortenedUrl = shortenedUrl;
	}
	public void setExpirationTime(LocalDateTime expirationTime){
		this.expirationTime = expirationTime;
	}
	public String getOriginalUrl(){
		return this.originalUrl;
	}
	public String getShortenedUrl(){
		return this.shortenedUrl;
	}
	public LocalDateTime getExpirationTime(){
		return this.expirationTime;
	}
}
