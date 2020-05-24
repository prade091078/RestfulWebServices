package com.prade.sws.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "api/world/" })
public class HelloWorldController {

	@GetMapping(path="/hello")
	public String helloWorld(){
	
		return "Hello World";
	}
	
	@GetMapping(path="/hello-bean")
	public HelloWorldBean helloWorldBean(){
		return new HelloWorldBean("Hello world Bean");
	}
	
	@GetMapping(path="/hello-bean/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
		return new HelloWorldBean(String.format("Welcome to learning world, %s",name));
	}
	
}
