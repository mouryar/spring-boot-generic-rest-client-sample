package com.mourya.sample;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String samplePostMethod() {
		// TODO Auto generated method stub
		return "Mourya";
	}

}
