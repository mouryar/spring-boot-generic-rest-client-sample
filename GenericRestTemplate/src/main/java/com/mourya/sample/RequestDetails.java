package com.mourya.sample;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public class RequestDetails {
	
	private String url;
	private HttpMethod requestType;
	
	public RequestDetails(String url, HttpMethod requestType) {
		super();
		this.url = url;
		this.requestType = requestType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public HttpMethod getRequestType() {
		return requestType;
	}
	public void setRequestType(HttpMethod requestType) {
		this.requestType = requestType;
	}
	
	@Override
	public String toString() {
		return "RequestDetails [url=" + url + ", requestType=" + requestType + "]";
	}
}
