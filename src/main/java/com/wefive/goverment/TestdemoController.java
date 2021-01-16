package com.wefive.goverment;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestdemoController {
	/*public String hello() {
		return "hello world!";
	}*/
	@RequestMapping("/hello")
	public String hello(String from) {
		return "hello "+from +" returned by server";
	}
}