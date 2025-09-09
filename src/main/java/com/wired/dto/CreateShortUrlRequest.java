package com.wired.dto;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CreateShortUrlRequest {
	private String shortUrl;
	@NotNull
	private String url;
	private LocalDateTime expirationTime;
	public String getUrl(){
		return this.url;
	}
	public LocalDateTime getExpirationTime(){
		return this.expirationTime;
	}
	public void setUrl(String url){
		this.url = url;
	}
	public void setExpirationTime(LocalDateTime expirationTime){
		this.expirationTime = expirationTime;
	}
	public String getShortUrl(){
		return this.shortUrl;
	}
	public void setShortUrl(String shortUrl){
		this.shortUrl = shortUrl;
	}
}
