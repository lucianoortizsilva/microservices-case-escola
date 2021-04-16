package com.lucianoortizsilva.eurekaclient;

import org.springframework.web.bind.annotation.RequestMapping;

public interface HelloWorldController {

	@RequestMapping("/test")
	String getTest();

}