package com.meetpaija.SptingBootMongoDB.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetpaija.SptingBootMongoDB.Modal.User;
import com.meetpaija.SptingBootMongoDB.Repository.IUserRepository;
import com.meetpaija.SptingBootMongoDB.Service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	IUserRepository userRepo=null;
	
	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public List<User> getByFname(String fname) {
		return userRepo.getByFname(fname);
	}
	
	

}
