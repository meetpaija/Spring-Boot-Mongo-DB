package com.meetpaija.SptingBootMongoDB.Service;

import java.util.List;

import com.meetpaija.SptingBootMongoDB.Modal.User;

public interface IUserService {
	
	public List<User> getAllUsers(); 
	public List<User> getByFname(String fname);
}
