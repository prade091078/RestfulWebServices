package com.prade.sws.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping({ "api/LearnCRUD/" })
public class UserController {

	@Autowired //Autowiring feature of spring framework enables you to inject the object dependency implicitly. It internally uses setter or constructor injection. Autowiring can't be used to inject primitive and string values.
	private UserDaoService service;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.getAllUsers();
	}
	
	@GetMapping("/users/{id}")
	public User getAUser(@PathVariable int id) {
		User user = service.getAUser(id);
		if(user == null)
			throw new UserNotFoundException("id-"+id);
		
		return user;
	}
	
	/*@SuppressWarnings("deprecation")
	 * 
	 * 	<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-hateoas</artifactId>
		</dependency>
	
	@GetMapping("/users/hate/{id}")
	public EntityModel<User> getAUserHATEOAS(@PathVariable int id) {
		User user = service.getAUser(id);
		if(user == null)
			throw new UserNotFoundException("id-"+id);
		
		//hateoas
		EntityModel<User> model = new EntityModel<User>(user);
		
		//adding link controller to the resource with the name all-users
		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		
		model.add(linkTo.withRel("all-users"));
		
		return model;
	}*/

	@PostMapping("/users/createUser") // return proper status code created
	public void createUser(@RequestBody User user) {
		 service.createAUser(user);
	}
	
	@PostMapping("/users") // return proper status code created
	public ResponseEntity<Object> createUserAndReturn(@Valid @RequestBody User user) {
		User newuser = service.createAUser(user);
		
		// get new id and form URI from current servlet
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(newuser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteUser(id);
		if(user == null)
			throw new UserNotFoundException("id-"+id);		
	}

}
