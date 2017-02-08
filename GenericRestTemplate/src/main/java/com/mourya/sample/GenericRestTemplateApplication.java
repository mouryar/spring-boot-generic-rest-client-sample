package com.mourya.sample;

import java.io.IOException;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;


public class GenericRestTemplateApplication {

	public static void main(String[] args) {
		
		
		ResponseErrorHandler responseHandler = new ResponseErrorHandler() {
			
			@Override
			public boolean hasError(ClientHttpResponse response) throws IOException {
				
				if (response.getStatusCode() != HttpStatus.OK) {
					System.out.println(response.getStatusText());
				}
				return response.getStatusCode() == HttpStatus.OK ? false : true;
			}
			
			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
				// TODO Auto-generated method stub
				
			}
		};

		try {
			
			String responseBody = new GenericRestClient<String, String>().execute(
					new RequestDetails("http://localhost:8082/health", HttpMethod.GET), " ", responseHandler, String.class);

			System.out.println(responseBody);
			
			responseBody = new GenericRestClient<String, String>().execute(
					new RequestDetails("http://localhost:8082/create", HttpMethod.POST), "Data", responseHandler, String.class);
			
			System.out.println(responseBody);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
}
