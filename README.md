# spring-boot-generic-rest-client-sample

This document gives a way to make use of Spring RestTemplate to consumer Rest web services in a more elegant way. 

Easy way to consume web services is to create separate method for each web service. 
Sample code to consume web services without code optimization. 

```
public String consumeGetRequest(String data, ResponseErrorHandler errorHandler)
			throws ResourceAccessException, Exception {

		restTemplate.setErrorHandler(errorHandler);
		HttpHeaders headers = new HttpHeaders();

		HttpEntity<String> entity = new HttpEntity<String>(data, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/health",
				HttpMethod.GET, entity, String.class);
		return response.getBody();
	}
```
```
	public String cosumePostRequest(String data, ResponseErrorHandler errorHandler)
			throws ResourceAccessException, Exception {

		restTemplate.setErrorHandler(errorHandler);
		HttpHeaders headers = new HttpHeaders();

		HttpEntity<String> entity = new HttpEntity<String>(data, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/create",
				HttpMethod.POST, entity, String.class);
		return response.getBody();
	}
```
If we look at the above code most of the code is repeated. If will be a hell to manage the code if application starts consuming more and more rest web services from different micro services. 

Example: if we would like to add an authentication to request header, we will end up changing the code in each and every method. 

If we could parameterize URL, DATA, RESPONSE TYPE, REQUEST METHOD we can provide more elegant way to consume RestWebservices. 

```
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

public class GenericRestClient<T, V> {

	private RestTemplate restTemplate = new RestTemplate();

	public V execute(RequestDetails requestDetails, T data, ResponseErrorHandler errorHandler, Class<V> genericClass)
			throws ResourceAccessException, Exception {

		restTemplate.setErrorHandler(errorHandler);
		HttpHeaders headers = new HttpHeaders();

		HttpEntity<T> entity = new HttpEntity<T>(data, headers);
		ResponseEntity<V> response = restTemplate.exchange(requestDetails.getUrl(), requestDetails.getRequestType(),
				entity, genericClass);
		return response.getBody();
	}
}
```
Sample ways to consume above generic method for GET and POST request. 

```
new GenericRestClient<String, String>().execute(new RequestDetails("http://localhost:8082/health", HttpMethod.GET), "", responseHandler, String.class);
```
```
new GenericRestClient<String, String>().execute(new RequestDetails("http://localhost:8082/create", HttpMethod.POST), "Data", responseHandler, String.class);
```
Above code parameterized all variable parameters in previous version of RestClient. Now we can use the same method to consume a web services to different parameters, different response body type and request body type.






