package com.meetpaija.SptingBootMongoDB.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetpaija.SptingBootMongoDB.Modal.User;
import com.meetpaija.SptingBootMongoDB.ServiceImpl.UserServiceImpl;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;
	
	@RequestMapping("/hello")
	public String testBoot() {
		return "Hello world";
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUserData() {
		return new ResponseEntity<List<User>>(userServiceImpl.getAllUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/users/{fname}")
	public ResponseEntity<List<User>> getByfname(@PathVariable(value="fname") String fname) {
		return new ResponseEntity<List<User>>(userServiceImpl.getByFname(fname), HttpStatus.OK);
	}
	
}
