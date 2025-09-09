package com.wired.dto;
import java.time.LocalDateTime;

public class CreateUrlShortenerRequest {
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
}
